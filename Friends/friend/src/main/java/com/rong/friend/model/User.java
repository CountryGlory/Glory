package com.rong.friend.model;

import java.util.Date;

public class User {
    private String id;

    private String namenumber;

    private String password;

    private String gesturepwd;

    private String nickname;

    private Date birthday;

    private String headportrait;

    private String email;

    private String weixin;

    private String qq;

    private String sex;

    private String address;

    private Integer lnvisible;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNamenumber() {
        return namenumber;
    }

    public void setNamenumber(String namenumber) {
        this.namenumber = namenumber == null ? null : namenumber.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getGesturepwd() {
        return gesturepwd;
    }

    public void setGesturepwd(String gesturepwd) {
        this.gesturepwd = gesturepwd == null ? null : gesturepwd.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHeadportrait() {
        return headportrait;
    }

    public void setHeadportrait(String headportrait) {
        this.headportrait = headportrait == null ? null : headportrait.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getLnvisible() {
        return lnvisible;
    }

    public void setLnvisible(Integer lnvisible) {
        this.lnvisible = lnvisible;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}