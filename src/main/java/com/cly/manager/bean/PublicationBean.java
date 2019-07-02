package com.cly.manager.bean;

import java.io.Serializable;

public class PublicationBean implements Serializable {
    private int pid;
    private String imgSrc;
    private String pName;
    private int uid;
    private int year;
    private String content;
    private String pdfUrl;



    public String getpName() {
        return pName;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getaName() {
        return pName;
    }

    public void setpName(String aName) {
        this.pName = pName;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
