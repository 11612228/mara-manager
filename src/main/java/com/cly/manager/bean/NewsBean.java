package com.cly.manager.bean;

import java.io.Serializable;

public class NewsBean implements Serializable {
    private int nid;



    private String imgSrc;
    private String content;
    private String title;
    private int uid;
    private int year;
    private int month;
    private int day;
    public NewsBean(){
    }
    public NewsBean(int nid,int uid,String imgSrc,String content,String title, int year, int month){
        this.nid = nid;
        this.uid = uid;
        this.content = content;
        this.title = title;
        this.imgSrc = imgSrc;
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
