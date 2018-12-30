package com.rong.friend.oauthserver.common.model;

import java.io.Serializable;
import java.util.Date;

public class Relatedtome implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1887382112703314560L;

	private String id;

    private String userid;

    private String commentid;

    private String messageid;

    private String fabulousid;

    private String content;

    private Date createdt;
    
    private Integer read;

	private Integer status;
    
    private User user;
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Fabulous getFabulous() {
		return fabulous;
	}

	public void setFabulous(Fabulous fabulous) {
		this.fabulous = fabulous;
	}

	private Comment comment;
    
    private Message message;
    
    private Fabulous fabulous;

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

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid == null ? null : commentid.trim();
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid == null ? null : messageid.trim();
    }

    public String getFabulousid() {
        return fabulousid;
    }

    public void setFabulousid(String fabulousid) {
        this.fabulousid = fabulousid == null ? null : fabulousid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatedt() {
        return createdt;
    }

    public void setCreatedt(Date createdt) {
        this.createdt = createdt;
    }

    public Integer getRead() {
		return read;
	}

	public void setRead(Integer read) {
		this.read = read;
	}
    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}