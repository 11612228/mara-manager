package com.cly.manager.service;

import com.cly.manager.bean.EducationBean;

import java.util.List;

public interface EducationService {
    List<EducationBean> getEducationList(int uid) throws Exception;
    boolean addEducationBean(EducationBean educationBean);
    boolean deleteEducationBean(int uid);
}
