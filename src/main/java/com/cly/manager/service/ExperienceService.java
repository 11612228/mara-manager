package com.cly.manager.service;

import com.cly.manager.bean.ExperienceBean;

import java.util.List;

public interface ExperienceService {
    List<ExperienceBean> getExperienceBeanList(int uid) throws Exception;
}
