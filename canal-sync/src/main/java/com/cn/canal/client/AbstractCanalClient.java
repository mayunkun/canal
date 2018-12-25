package com.cn.canal.client;

import java.net.InetSocketAddress;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.cn.canal.respository.EsDynamicsRepository;
import com.cn.canal.respository.bean.Dynamics;
import com.cn.canal.utils.DateUtils;

/**
 * @Auther: kevin
 * @Description:
 * @Company:
 * @Version: 1.0.0
 * @Date: Created in 15:44 2018/6/28
 * @ProjectName: canal
 */
@Component
public class AbstractCanalClient {

	private CanalConnector connector;
	private String filter; // 过滤表达试
	private static int batchSize = 1000;
	
	@Autowired
	private EsDynamicsRepository dynamicsRepository;
	
	public AbstractCanalClient(@Value("${canal.ip}") String ip, 
								@Value("${canal.port}") int port, 
								@Value("${canal.destination}") String destination, 
								@Value("${canal.username}") String username,
								@Value("${canal.password}") String password) {
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(ip , port), destination, username, password);
        setConnector(connector);
        setFilter("jrdd\\..*");
	}
	
	public void setConnector(CanalConnector connector) {
		this.connector = connector;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public void start() throws ParseException {
		try {
			connector.connect();
			connector.subscribe(filter);
			while (true) {
				Message message = connector.getWithoutAck(batchSize);
				long batchId = message.getId();
				int size = message.getEntries().size();
				if (batchId == -1 || size == 0) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					printEntry(message.getEntries());
				}
				connector.ack(batchId); // 提交确认
				// connector.rollback(batchId); // 处理失败, 回滚数据
			}
		} finally {
			connector.disconnect();
		}
	}

	private void printEntry(List<CanalEntry.Entry> entrys) throws ParseException {
		for (CanalEntry.Entry entry : entrys) {
			if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN
					|| entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
				continue;
			}
			
			CanalEntry.RowChange rowChage = null;
			try {
				rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
			} catch (Exception e) {
				throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(), e);
			}

			switch (entry.getHeader().getTableName()) {
			case "jr_dynamics":
				CanalEntry.EventType eventType = rowChage.getEventType();
				for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
					if (eventType == CanalEntry.EventType.DELETE) {
						dynamicsRepository.deleteById(rowData.getBeforeColumnsList().get(0).getValue());
					} else if (eventType == CanalEntry.EventType.INSERT) {
						Dynamics entity = new Dynamics();
						entity.setDynamicsId(rowData.getAfterColumnsList().get(0).getValue());
						entity.setVersion(Long.valueOf("1"));
						entity.setDynamicTitle(rowData.getAfterColumnsList().get(1).getValue());
						entity.setDynamicText(rowData.getAfterColumnsList().get(2).getValue());
						entity.setCreateUser(rowData.getAfterColumnsList().get(3).getValue());
						entity.setDynamicStar(rowData.getAfterColumnsList().get(4).getValue());
						entity.setVisibleRange(rowData.getAfterColumnsList().get(5).getValue());
						entity.setDynamicImgs(rowData.getAfterColumnsList().get(6).getValue());
						entity.setDynamicLinkId(rowData.getAfterColumnsList().get(7).getValue());
						entity.setDynamicType(rowData.getAfterColumnsList().get(8).getValue());
						entity.setDynamicAddress(rowData.getAfterColumnsList().get(9).getValue());
						entity.setDynamicSource(rowData.getAfterColumnsList().get(10).getValue());
						entity.setDynamicClassify(rowData.getAfterColumnsList().get(11).getValue());
						entity.setParentId(rowData.getAfterColumnsList().get(12).getValue());
						entity.setIssueTime(StringUtils.isNotBlank(rowData.getAfterColumnsList().get(13).getValue())?DateUtils.convertStringToDate(rowData.getAfterColumnsList().get(13).getValue()):null);
						entity.setCreateTime(StringUtils.isNotBlank(rowData.getAfterColumnsList().get(14).getValue())?DateUtils.convertStringToDate(rowData.getAfterColumnsList().get(14).getValue()):null);
						entity.setUpdateTime(StringUtils.isNotBlank(rowData.getAfterColumnsList().get(15).getValue())?DateUtils.convertStringToDate(rowData.getAfterColumnsList().get(15).getValue()):null);
						entity.setDynamicStatus(rowData.getAfterColumnsList().get(16).getValue());
						entity.setSourceDynamicId(rowData.getAfterColumnsList().get(17).getValue());
						entity.setOperator(rowData.getAfterColumnsList().get(18).getValue());
						entity.setIsTop(rowData.getAfterColumnsList().get(19).getValue());
						dynamicsRepository.save(entity);
					} else {
						updateDynamic(rowData);
						
//						System.out.println("-------> before");
//						printColumn(rowData.getBeforeColumnsList());
//						System.out.println("-------> after");
//						printColumn(rowData.getAfterColumnsList());
					}
				}
				
			case "jr_star":
				
				break;
				
			case "jr_user":
				
				break;

			default:
				break;
			}
			
//			CanalEntry.EventType eventType = rowChage.getEventType();
//			System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
//			entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
//			entry.getHeader().getSchemaName(), entry.getHeader().getTableName(), eventType));
//			print(rowChage);
		}
	}
	
	/**
	 * 通过_version保证数据一致
	 * @param rowData
	 * @throws ParseException
	 */
	public void updateDynamic(CanalEntry.RowData rowData) throws ParseException {
		try {
			Dynamics entity = new Dynamics();
			String id = rowData.getAfterColumnsList().get(0).getValue();
			entity.setDynamicsId(id);
			Optional<Dynamics> s = dynamicsRepository.findById(id);
			entity.setVersion(s.equals(Optional.empty())?Long.valueOf("1"):s.get().getVersion()+Long.valueOf("1"));
			entity.setDynamicTitle(rowData.getAfterColumnsList().get(1).getValue());
			entity.setDynamicText(rowData.getAfterColumnsList().get(2).getValue());
			entity.setCreateUser(rowData.getAfterColumnsList().get(3).getValue());
			entity.setDynamicStar(rowData.getAfterColumnsList().get(4).getValue());
			entity.setVisibleRange(rowData.getAfterColumnsList().get(5).getValue());
			entity.setDynamicImgs(rowData.getAfterColumnsList().get(6).getValue());
			entity.setDynamicLinkId(rowData.getAfterColumnsList().get(7).getValue());
			entity.setDynamicType(rowData.getAfterColumnsList().get(8).getValue());
			entity.setDynamicAddress(rowData.getAfterColumnsList().get(9).getValue());
			entity.setDynamicSource(rowData.getAfterColumnsList().get(10).getValue());
			entity.setDynamicClassify(rowData.getAfterColumnsList().get(11).getValue());
			entity.setParentId(rowData.getAfterColumnsList().get(12).getValue());
			entity.setIssueTime(StringUtils.isNotBlank(rowData.getAfterColumnsList().get(13).getValue())?DateUtils.convertStringToDate(rowData.getAfterColumnsList().get(13).getValue()):null);
			entity.setCreateTime(StringUtils.isNotBlank(rowData.getAfterColumnsList().get(14).getValue())?DateUtils.convertStringToDate(rowData.getAfterColumnsList().get(14).getValue()):null);
			entity.setUpdateTime(StringUtils.isNotBlank(rowData.getAfterColumnsList().get(15).getValue())?DateUtils.convertStringToDate(rowData.getAfterColumnsList().get(15).getValue()):null);
			entity.setDynamicStatus(rowData.getAfterColumnsList().get(16).getValue());
			entity.setSourceDynamicId(rowData.getAfterColumnsList().get(17).getValue());
			entity.setOperator(rowData.getAfterColumnsList().get(18).getValue());
			entity.setIsTop(rowData.getAfterColumnsList().get(19).getValue());
			Dynamics dynamic = dynamicsRepository.save(entity);
			System.out.println("=============="+JSONObject.toJSONString(dynamic));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			updateDynamic(rowData);
		}
		
	}
	
//	private static void print(CanalEntry.RowChange rowChage) {
//		CanalEntry.EventType eventType = rowChage.getEventType();
//		for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
//			if (eventType == CanalEntry.EventType.DELETE) {
//				printColumn(rowData.getBeforeColumnsList());
//			} else if (eventType == CanalEntry.EventType.INSERT) {
//				printColumn(rowData.getAfterColumnsList());
//			} else {
//				System.out.println("-------> before");
//				printColumn(rowData.getBeforeColumnsList());
//				System.out.println("-------> after");
//				printColumn(rowData.getAfterColumnsList());
//			}
//		}
//	}
//	
//	private static void printColumn(List<CanalEntry.Column> columns) {
//		for (CanalEntry.Column column : columns) {
//			System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
//		}
//	}

}
