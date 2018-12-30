package com.rong.friend.oauthserver.common.model;

import java.io.Serializable;
import java.util.Date;

public class Fabulous implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7819427002320566136L;

	private String id;

    private String spaceid;

    private String userid;

    private Integer status;

    private Date createdt;
    
    private Look space;
    
    private User user;

    public Look getSpace() {
		return space;
	}

	public void setSpace(Look space) {
		this.space = space;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSpaceid() {
        return spaceid;
    }

    public void setSpaceid(String spaceid) {
        this.spaceid = spaceid == null ? null : spaceid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
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
}