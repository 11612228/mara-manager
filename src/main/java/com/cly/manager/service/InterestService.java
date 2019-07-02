package com.cly.manager.service;

import com.cly.manager.bean.InterestBean;

import java.util.List;

public interface InterestService {
    List<InterestBean> getInterestBeanList(int uid) throws Exception;
}
