package com.cly.manager.dao;

import com.cly.manager.bean.AwardBean;

import java.util.List;

public interface AwardDao {
    List<AwardBean> fetchAwardBeanList(int uid) throws Exception;
}
