package com.cly.manager.bean;

import java.io.Serializable;

public class BannerBean implements Serializable {
    private int bid;
    private String imgSrc;
    private String bName;

    public int getBid() {
        return bid;
    }

    public void setBid(int pid) {
        this.bid = bid;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String pName) {
        this.bName = pName;
    }
}
