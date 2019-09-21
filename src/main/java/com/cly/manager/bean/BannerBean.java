package com.cly.manager.bean;

import java.io.Serializable;

public class BannerBean implements Serializable {
    private int bid;
    private String imgSrc;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
