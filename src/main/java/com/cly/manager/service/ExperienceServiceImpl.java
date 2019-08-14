package com.cly.manager.service;

import com.cly.manager.bean.ExperienceBean;
import com.cly.manager.dao.ExperienceDao;
import com.cly.manager.dao.ExperienceDaoImpl;

import java.util.List;

public class ExperienceServiceImpl implements ExperienceService {
    ExperienceDao experienceDao = new ExperienceDaoImpl();
    @Override
    public List<ExperienceBean> getExperienceBeanList(int uid) throws Exception {
        return experienceDao.fetchExperienceBeanList(uid);
    }

    @Override
    public boolean addExperienceBean(ExperienceBean experienceBean) {
        return experienceDao.addExperienceBean(experienceBean);
    }

    @Override
    public boolean deleteExperienceBean(int uid) {
        return experienceDao.deleteExperienceBean(uid);
    }
}
