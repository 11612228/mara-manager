package com.cly.manager.dao;

import com.cly.manager.bean.ExperienceBean;
import com.cly.manager.bean.InterestBean;

import java.util.List;

public interface InterestDao {
    List<InterestBean> fetchInterestBeanList(int uid) throws Exception;
    boolean addInterestBean(InterestBean interestBean);
    boolean deleteInterestBean(int uid);
}
