package com.cly.manager.dao;

import com.cly.manager.bean.InterestBean;

import java.util.List;

public interface InterestDao {
    List<InterestBean> fetchInterestBeanList(int uid) throws Exception;
}
