package com.rong.friend.oauthserver.common.model;

import java.io.Serializable;
import java.util.Date;

public class Feedback implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6584045716905909362L;

	private String id;

    private String userid;

    private String fbcontent;

    private String fbimages;

    private Integer fbfraction;

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

    public String getFbcontent() {
        return fbcontent;
    }

    public void setFbcontent(String fbcontent) {
        this.fbcontent = fbcontent == null ? null : fbcontent.trim();
    }

    public String getFbimages() {
        return fbimages;
    }

    public void setFbimages(String fbimages) {
        this.fbimages = fbimages == null ? null : fbimages.trim();
    }

    public Integer getFbfraction() {
        return fbfraction;
    }

    public void setFbfraction(Integer fbfraction) {
        this.fbfraction = fbfraction;
    }

    public Date getCreatedt() {
        return createdt;
    }

    public void setCreatedt(Date createdt) {
        this.createdt = createdt;
    }
}