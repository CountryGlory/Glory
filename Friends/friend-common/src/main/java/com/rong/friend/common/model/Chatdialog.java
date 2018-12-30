package com.rong.friend.oauthserver.common.model;

import java.io.Serializable;
import java.util.Date;

public class Chatdialog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -967289208690402926L;

	private String id;

    private String userid;

    private Integer sortno;

    private String newchat;

    private Integer unreadchat;

    private String friendsid;

    private Date lastdt;
    
    private User user;
    
    private Friends friends;

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Friends getFriends() {
		return friends;
	}

	public void setFriends(Friends friends) {
		this.friends = friends;
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

    public Integer getSortno() {
        return sortno;
    }

    public void setSortno(Integer sortno) {
        this.sortno = sortno;
    }

    public String getNewchat() {
        return newchat;
    }

    public void setNewchat(String newchat) {
        this.newchat = newchat == null ? null : newchat.trim();
    }

    public Integer getUnreadchat() {
        return unreadchat;
    }

    public void setUnreadchat(Integer unreadchat) {
        this.unreadchat = unreadchat;
    }

    public String getFriendsid() {
        return friendsid;
    }

    public void setFriendsid(String friendsid) {
        this.friendsid = friendsid == null ? null : friendsid.trim();
    }

    public Date getLastdt() {
        return lastdt;
    }

    public void setLastdt(Date lastdt) {
        this.lastdt = lastdt;
    }
}