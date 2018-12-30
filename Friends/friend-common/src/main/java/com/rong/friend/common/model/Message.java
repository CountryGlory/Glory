package com.rong.friend.oauthserver.common.model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1538222590506665896L;

	private String id;

    private String content;

    private String commenterid;

    private String userid;

    private Date createdt;

    private String reply;

    private Date lastcreatedt;
    
    private Comment comment;
    
    private User user;

    public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCommenterid() {
        return commenterid;
    }

    public void setCommenterid(String commenterid) {
        this.commenterid = commenterid == null ? null : commenterid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Date getCreatedt() {
        return createdt;
    }

    public void setCreatedt(Date createdt) {
        this.createdt = createdt;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    public Date getLastcreatedt() {
        return lastcreatedt;
    }

    public void setLastcreatedt(Date lastcreatedt) {
        this.lastcreatedt = lastcreatedt;
    }
}