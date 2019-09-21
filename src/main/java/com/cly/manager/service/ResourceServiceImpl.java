package com.cly.manager.service;

import com.cly.manager.bean.ResourceBean;
import com.cly.manager.dao.ResourceDao;
import com.cly.manager.dao.ResourceDaoImpl;

import java.util.List;

public class ResourceServiceImpl implements ResourceService {
    ResourceDao resourceDao = new ResourceDaoImpl();

    @Override
    public List<ResourceBean> getResourceBeanList() throws Exception {
        return resourceDao.fetchResourceBeanList();
    }

    @Override
    public ResourceBean getResourceBean(int resid) throws Exception {
        return resourceDao.fetchResourceBean(resid);
    }

    @Override
    public boolean addResourceBean(ResourceBean resourceBean) {
        return resourceDao.addResourceBean(resourceBean);
    }

    @Override
    public boolean deleteResourceBean(int resid) {
        return resourceDao.deleteResourceBean(resid);
    }

    @Override
    public boolean updateResourceBean(ResourceBean resourceBean) {
        return resourceDao.updateResourceBean(resourceBean);
    }
}
