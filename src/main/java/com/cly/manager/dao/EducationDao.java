package com.cly.manager.dao;

import com.cly.manager.bean.EducationBean;

import java.util.List;

public interface EducationDao {
    List<EducationBean> fetchEducationBeanList(int uid) throws Exception;
}
