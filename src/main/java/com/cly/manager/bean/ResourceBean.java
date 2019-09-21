package com.cly.manager.bean;

import java.io.Serializable;

public class ResourceBean implements Serializable{
    private int resid;
    private String imgSrc;
    private String pName;
    private String pNameC;

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpNameC() {
        return pNameC;
    }

    public void setpNameC(String pNameC) {
        this.pNameC = pNameC;
    }
}
