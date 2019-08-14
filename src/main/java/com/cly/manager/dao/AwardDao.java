package com.cly.manager.dao;

import com.cly.manager.bean.AwardBean;
import com.cly.manager.bean.EducationBean;

import java.util.List;

public interface AwardDao {
    List<AwardBean> fetchAwardBeanList(int uid) throws Exception;
    boolean addAwardBean(AwardBean awardBean);
    boolean deleteAwardBean(int uid);
}
