package com.rong.friend.model;

import java.io.Serializable;
import java.util.Date;

public class ChatRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String sendUserId;

    private String msg;

    private String recUserId;

    private Integer status;

    private Date createdt;

    private User sendUser;
    
    private User recUser;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRecUserId() {
		return recUserId;
	}

	public void setRecUserId(String recUserId) {
		this.recUserId = recUserId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedt() {
		return createdt;
	}

	public void setCreatedt(Date createdt) {
		this.createdt = createdt;
	}

	public User getSendUser() {
		return sendUser;
	}

	public void setSendUser(User sendUser) {
		this.sendUser = sendUser;
	}

	public User getRecUser() {
		return recUser;
	}

	public void setRecUser(User recUser) {
		this.recUser = recUser;
	}
}