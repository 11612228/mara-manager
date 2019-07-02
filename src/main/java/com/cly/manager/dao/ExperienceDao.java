package com.cly.manager.dao;

import com.cly.manager.bean.ExperienceBean;

import java.util.List;

public interface ExperienceDao {
    List<ExperienceBean> fetchExperienceBeanList(int uid) throws Exception;
}
