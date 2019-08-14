package com.cly.manager.service;

import com.cly.manager.bean.AwardBean;
import com.cly.manager.dao.AwardDao;
import com.cly.manager.dao.AwardDaoImpl;

import java.util.List;

public class AwardServiceImpl implements AwardService  {
    private AwardDao awardDao = new AwardDaoImpl();

    @Override
    public List<AwardBean> getAwardBeanList(int uid) throws Exception {
        return awardDao.fetchAwardBeanList(uid);
    }

    @Override
    public boolean addAwardBean(AwardBean awardBean) {
       return awardDao.addAwardBean(awardBean);
    }

    @Override
    public boolean deleteAwardBean(int uid) {
        return awardDao.deleteAwardBean(uid);
    }
}
