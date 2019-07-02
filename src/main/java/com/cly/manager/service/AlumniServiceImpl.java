package com.cly.manager.service;

import com.cly.manager.bean.AlumniBean;
import com.cly.manager.dao.AluminDao;
import com.cly.manager.dao.AlumniDaoImpl;

import java.util.List;

public class AlumniServiceImpl implements AlumniService {
    AluminDao aluminDao = new AlumniDaoImpl();
    @Override
    public List<AlumniBean> getAlumniBeanList() throws Exception {
        return aluminDao.fetchAlumniBeanList();
    }
}
