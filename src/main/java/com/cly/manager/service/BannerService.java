package com.cly.manager.service;

import com.cly.manager.bean.BannerBean;

import java.util.List;

public interface BannerService {
    List<BannerBean> getRecentBannerBeanList() throws Exception;
}
