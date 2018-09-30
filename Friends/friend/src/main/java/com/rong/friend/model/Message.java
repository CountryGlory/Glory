package com.rong.friend.model;

import java.util.Date;

public class Message {
    private String id;

    private String content;

    private String commenterid;

    private String userid;

    private Date createdt;

    private String reply;

    private Date lastcreatedt;

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