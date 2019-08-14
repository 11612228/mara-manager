package com.cly.manager.service;

import com.cly.manager.bean.AwardBean;

import java.util.List;

public interface AwardService {
    List<AwardBean> getAwardBeanList(int uid) throws Exception;
    boolean addAwardBean(AwardBean awardBean);
    boolean deleteAwardBean(int uid);
}
