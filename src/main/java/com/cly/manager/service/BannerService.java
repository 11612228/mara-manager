package com.cly.manager.service;

import com.cly.manager.bean.BannerBean;

import java.util.List;

public interface BannerService {
    List<BannerBean> getRecentBannerBeanList() throws Exception;
    BannerBean getBannerBean(int bid) throws Exception;
    List<BannerBean> getBannerBeanList() throws Exception;
    boolean addBannerBean(BannerBean bannerBean);
    boolean deleteBannerBean(int bid);
    boolean updateBannerBean(BannerBean bannerBean);
}
