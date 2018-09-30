package com.rong.friend.model;

import java.io.Serializable;
import java.util.Date;

public class Friends implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -128106690924760135L;

	private String id;

    private String fgid;

    private String userid;

    private String remark;

    private Integer status;

    private Date createdt;
    
    private Friendsgrouping friendsgrouping;
    
    private User user;

    public Friendsgrouping getFriendsgrouping() {
		return friendsgrouping;
	}

	public void setFriendsgrouping(Friendsgrouping friendsgrouping) {
		this.friendsgrouping = friendsgrouping;
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

    public String getFgid() {
        return fgid;
    }

    public void setFgid(String fgid) {
        this.fgid = fgid == null ? null : fgid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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