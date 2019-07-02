package com.cly.manager.dao;

import com.cly.manager.bean.AlumniBean;

import java.util.List;

public interface AluminDao {
    public List<AlumniBean> fetchAlumniBeanList() throws Exception;
}
