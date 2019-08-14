package com.cly.manager.dao;

import com.cly.manager.bean.BannerBean;

import java.util.List;

public interface BannerDao {
    List<BannerBean> fetchBannerBeanList() throws Exception;
    BannerBean fetchBannerBean(int bid) throws Exception;
    boolean deleteBannerBean(int bid);
    boolean addBannerBean(BannerBean bannerBean);
    boolean updateBannerBean(BannerBean bannerBean);

}
