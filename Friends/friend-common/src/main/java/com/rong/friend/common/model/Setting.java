package com.rong.friend.model;

import java.io.Serializable;
import java.util.Date;

public class Setting implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6212925361638774504L;

	private String id;

    private String userid;

    private Integer mute;

    private Integer shock;

    private Date createdt;
    
    private User user;

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Integer getMute() {
        return mute;
    }

    public void setMute(Integer mute) {
        this.mute = mute;
    }

    public Integer getShock() {
        return shock;
    }

    public void setShock(Integer shock) {
        this.shock = shock;
    }

    public Date getCreatedt() {
        return createdt;
    }

    public void setCreatedt(Date createdt) {
        this.createdt = createdt;
    }
}