package com.cly.manager.service;

import com.cly.manager.bean.BannerBean;
import com.cly.manager.dao.BannerDao;
import com.cly.manager.dao.BannerDaoImpl;

import java.util.List;

public class BannerServiceImpl implements BannerService {
    BannerDao bannerDao = new BannerDaoImpl();

    @Override
    public List<BannerBean> getRecentBannerBeanList() throws Exception {
        List<BannerBean> bannerDaoList = bannerDao.fetchBannerBeanList();
        if (bannerDaoList.size()>4){
            bannerDaoList = bannerDaoList.subList(0,4);
        }
        return bannerDaoList;
    }
    @Override
    public List<BannerBean> getBannerBeanList() throws Exception {
        List<BannerBean> bannerDaoList = bannerDao.fetchBannerBeanList();
        return bannerDaoList;
    }

    @Override
    public boolean addBannerBean(BannerBean bannerBean) {
        return bannerDao.addBannerBean(bannerBean);
    }

    @Override
    public boolean deleteBannerBean(int bid) {
        return bannerDao.deleteBannerBean(bid);
    }

    @Override
    public boolean updateBannerBean(BannerBean bannerBean) {
        return bannerDao.updateBannerBean(bannerBean);
    }

    @Override
    public BannerBean getBannerBean(int bid) throws Exception {
        return bannerDao.fetchBannerBean(bid);
    }
}
