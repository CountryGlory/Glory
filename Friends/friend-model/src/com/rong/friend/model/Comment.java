package com.rong.friend.model;

import java.util.Date;

public class Comment {
    private String id;

    private String spaceid;

    private String commfouserid;

    private String content;

    private String commtouserid;

    private Integer status;

    private Date createdt;

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

    public String getCommfouserid() {
        return commfouserid;
    }

    public void setCommfouserid(String commfouserid) {
        this.commfouserid = commfouserid == null ? null : commfouserid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCommtouserid() {
        return commtouserid;
    }

    public void setCommtouserid(String commtouserid) {
        this.commtouserid = commtouserid == null ? null : commtouserid.trim();
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