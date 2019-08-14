package com.cly.manager.service;

import com.cly.manager.bean.EducationBean;
import com.cly.manager.dao.EducationDao;
import com.cly.manager.dao.EducationDaoImpl;

import java.util.List;

public class EducationServiceImpl implements EducationService {
    private EducationDao educationDao = new EducationDaoImpl();
    @Override
    public List<EducationBean> getEducationList(int uid) throws Exception {
        return educationDao.fetchEducationBeanList(uid);
    }

    @Override
    public boolean addEducationBean(EducationBean educationBean) {
        return educationDao.addEducationBean(educationBean);
    }

    @Override
    public boolean deleteEducationBean(int uid) {
        return educationDao.deleteEducationBean(uid);
    }
}
