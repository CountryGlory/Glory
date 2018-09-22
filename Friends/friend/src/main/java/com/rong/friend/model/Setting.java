package com.rong.friend.model;

import java.util.Date;

public class Setting {
    private String id;

    private String userid;

    private Integer mute;

    private Integer shock;

    private Date createdt;

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