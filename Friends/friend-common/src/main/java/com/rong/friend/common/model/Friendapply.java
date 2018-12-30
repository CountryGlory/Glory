package com.rong.friend.oauthserver.common.model;

import java.io.Serializable;
import java.util.Date;

public class Friendapply implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7764249197212714159L;

	private String id;

    private String senduserid;

    private String touserid;

    private String content;

    private Integer status;
    
    private Integer read;

    private Date createdt;
    
    private User sendUser;
    
	private User toUser;

    public User getSendUser() {
		return sendUser;
	}

	public void setSendUser(User sendUser) {
		this.sendUser = sendUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSenduserid() {
        return senduserid;
    }

    public void setSenduserid(String senduserid) {
        this.senduserid = senduserid == null ? null : senduserid.trim();
    }

    public String getTouserid() {
        return touserid;
    }

    public void setTouserid(String touserid) {
        this.touserid = touserid == null ? null : touserid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Integer getRead() {
		return read;
	}

	public void setRead(Integer read) {
		this.read = read;
	}

    public Date getCreatedt() {
        return createdt;
    }

    public void setCreatedt(Date createdt) {
        this.createdt = createdt;
    }
}