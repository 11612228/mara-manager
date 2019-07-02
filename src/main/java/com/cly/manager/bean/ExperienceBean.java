package com.cly.manager.bean;

import java.io.Serializable;

public class ExperienceBean implements Serializable {
    int eid;
    int uid;
    String experience;
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
