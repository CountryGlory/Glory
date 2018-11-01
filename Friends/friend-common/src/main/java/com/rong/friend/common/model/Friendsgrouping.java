package com.rong.friend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Friendsgrouping implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7905807098261447025L;

	private String id;

    private String userid;

    private String name;

    private Integer status;

    private Date createdt;
    
    private User user;
    
    private List<Friends> friendList;

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Friends> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<Friends> friendList) {
		this.friendList = friendList;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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