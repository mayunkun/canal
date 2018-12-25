package com.cn.canal.respository.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "dynamics", type = "dynamic", shards = 8, replicas = 1)
public class Dynamics implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 3109321167867179061L;
	
	@Id
	private String dynamicsId;
	@Version
	private Long version;
	@Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
	private String dynamicTitle; 
	
	@Field(type = FieldType.Keyword)
	private String dynamicText; 
	private String createUser; 
	private String dynamicStar; 
	private String visibleRange; 
	private String dynamicImgs; 
	private String dynamicLinkId; 
	private String dynamicType; 
	private String dynamicAddress; 
	private String dynamicSource; 
	private String dynamicClassify; 
	private String parentId; 
	private Date issueTime; 
	private Date createTime; 
	private Date updateTime; 
	private String dynamicStatus; 
	private String sourceDynamicId; 
	private String operator; 
	private String isTop;
	
	@Field(type = FieldType.Nested)
	private List<User> dynamicUsers;
	
	public String getDynamicsId() {
		return dynamicsId;
	}
	public void setDynamicsId(String dynamicsId) {
		this.dynamicsId = dynamicsId;
	}
	public String getDynamicTitle() {
		return dynamicTitle;
	}
	public void setDynamicTitle(String dynamicTitle) {
		this.dynamicTitle = dynamicTitle;
	}
	public String getDynamicText() {
		return dynamicText;
	}
	public void setDynamicText(String dynamicText) {
		this.dynamicText = dynamicText;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getDynamicStar() {
		return dynamicStar;
	}
	public void setDynamicStar(String dynamicStar) {
		this.dynamicStar = dynamicStar;
	}
	public String getVisibleRange() {
		return visibleRange;
	}
	public void setVisibleRange(String visibleRange) {
		this.visibleRange = visibleRange;
	}
	public String getDynamicImgs() {
		return dynamicImgs;
	}
	public void setDynamicImgs(String dynamicImgs) {
		this.dynamicImgs = dynamicImgs;
	}
	public String getDynamicLinkId() {
		return dynamicLinkId;
	}
	public void setDynamicLinkId(String dynamicLinkId) {
		this.dynamicLinkId = dynamicLinkId;
	}
	public String getDynamicType() {
		return dynamicType;
	}
	public void setDynamicType(String dynamicType) {
		this.dynamicType = dynamicType;
	}
	public String getDynamicAddress() {
		return dynamicAddress;
	}
	public void setDynamicAddress(String dynamicAddress) {
		this.dynamicAddress = dynamicAddress;
	}
	public String getDynamicSource() {
		return dynamicSource;
	}
	public void setDynamicSource(String dynamicSource) {
		this.dynamicSource = dynamicSource;
	}
	public String getDynamicClassify() {
		return dynamicClassify;
	}
	public void setDynamicClassify(String dynamicClassify) {
		this.dynamicClassify = dynamicClassify;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Date getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getSourceDynamicId() {
		return sourceDynamicId;
	}
	public void setSourceDynamicId(String sourceDynamicId) {
		this.sourceDynamicId = sourceDynamicId;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getIsTop() {
		return isTop;
	}
	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}
	public List<User> getDynamicUsers() {
		return dynamicUsers;
	}
	public void setDynamicUsers(List<User> dynamicUsers) {
		this.dynamicUsers = dynamicUsers;
	}
	public String getDynamicStatus() {
		return dynamicStatus;
	}
	public void setDynamicStatus(String dynamicStatus) {
		this.dynamicStatus = dynamicStatus;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}

}
