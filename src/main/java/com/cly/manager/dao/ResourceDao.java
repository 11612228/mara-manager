package com.cly.manager.dao;

import com.cly.manager.bean.ResourceBean;

import java.util.List;

public interface ResourceDao {
    List<ResourceBean> fetchResourceBeanList() throws Exception;
    ResourceBean fetchResourceBean(int resid) throws Exception;
    boolean deleteResourceBean(int resid);
    boolean addResourceBean(ResourceBean resourceBean);
    boolean updateResourceBean(ResourceBean resourceBean);
}
