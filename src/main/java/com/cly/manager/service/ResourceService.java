package com.cly.manager.service;

import com.cly.manager.bean.ResourceBean;

import java.util.List;

public interface ResourceService {
    List<ResourceBean> getResourceBeanList() throws Exception;
    ResourceBean getResourceBean(int resid) throws Exception;
    boolean addResourceBean(ResourceBean resourceBean);
    boolean deleteResourceBean(int resid);
    boolean updateResourceBean(ResourceBean resourceBean);
}
