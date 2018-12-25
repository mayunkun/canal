package com.cn.canal.respository.bean;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "users", type = "user", shards = 8, replicas = 1)
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8870904139559943154L;
	
	@Id
	private String userId; 
	@Version
	private Long version;
	
	private String userName; 
	private String userImg; 
	private String userSex; 
	private String userIntroduct; 
	private String userPhone; 
	private String userWx; 
	private String userPasswd; 
	private String userPasswdSalt; 
	private String clientId; 
	private String clientType; 
	private String userType; 
	private String registType; 
	private String createTime; 
	private String loginTime; 
	private String updateTime; 
	private String userLabel; 
	private String authentication; 
	private String authenticationFile; 
	private String rongcloudToken; 
	private String userStatus;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserIntroduct() {
		return userIntroduct;
	}
	public void setUserIntroduct(String userIntroduct) {
		this.userIntroduct = userIntroduct;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserWx() {
		return userWx;
	}
	public void setUserWx(String userWx) {
		this.userWx = userWx;
	}
	public String getUserPasswd() {
		return userPasswd;
	}
	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}
	public String getUserPasswdSalt() {
		return userPasswdSalt;
	}
	public void setUserPasswdSalt(String userPasswdSalt) {
		this.userPasswdSalt = userPasswdSalt;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getRegistType() {
		return registType;
	}
	public void setRegistType(String registType) {
		this.registType = registType;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUserLabel() {
		return userLabel;
	}
	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}
	public String getAuthentication() {
		return authentication;
	}
	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}
	public String getAuthenticationFile() {
		return authenticationFile;
	}
	public void setAuthenticationFile(String authenticationFile) {
		this.authenticationFile = authenticationFile;
	}
	public String getRongcloudToken() {
		return rongcloudToken;
	}
	public void setRongcloudToken(String rongcloudToken) {
		this.rongcloudToken = rongcloudToken;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	
}
