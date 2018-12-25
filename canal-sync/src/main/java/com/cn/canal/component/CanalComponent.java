package com.cn.canal.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.cn.canal.client.AbstractCanalClient;

@Component
@Order(value = 1)
public class CanalComponent implements ApplicationRunner {
	 
	@Autowired
	private AbstractCanalClient client;
	

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		client.start();
	}

}
