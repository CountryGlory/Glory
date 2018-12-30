package com.rong.friend.oauthserver.common.model;

import java.util.Date;

public class About {
    private String version;

    private String deveemail;

    private String phone;

    private Integer downnumber;

    private Date createdt;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getDeveemail() {
        return deveemail;
    }

    public void setDeveemail(String deveemail) {
        this.deveemail = deveemail == null ? null : deveemail.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getDownnumber() {
        return downnumber;
    }

    public void setDownnumber(Integer downnumber) {
        this.downnumber = downnumber;
    }

    public Date getCreatedt() {
        return createdt;
    }

    public void setCreatedt(Date createdt) {
        this.createdt = createdt;
    }
}