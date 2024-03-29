package com.cly.manager.service;

import com.cly.manager.bean.InterestBean;
import com.cly.manager.dao.InterestDao;
import com.cly.manager.dao.InterestDaoImpl;

import java.util.List;

public class InterestServiceImpl implements InterestService {
    InterestDao interestDao = new InterestDaoImpl();
    @Override
    public List<InterestBean> getInterestBeanList(int uid) throws Exception {
        return interestDao.fetchInterestBeanList(uid);
    }

    @Override
    public boolean addInterestBean(InterestBean interestBean) {
        return interestDao.addInterestBean(interestBean);
    }

    @Override
    public boolean deleteInterestBean(int uid) {
        return interestDao.deleteInterestBean(uid);
    }
}
