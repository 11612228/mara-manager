package com.cly.manager.service;

import com.cly.manager.bean.PictureBean;
import com.cly.manager.dao.PictureDao;
import com.cly.manager.dao.PictureDaoImpl;

import java.util.List;

public class PictureServiceImpl implements PictureService {
    PictureDao pictureDao = new PictureDaoImpl();
    @Override
    public List<PictureBean> getPictureBeanList(int year) throws Exception {
        return pictureDao.fetchPictureBeanList(year);
    }

    @Override
    public List<PictureBean> getAlbumList() throws Exception {
        return pictureDao.fetchAlbumList();
    }

    @Override
    public boolean addPicture(PictureBean pictureBean) {
        return false;
    }

    @Override
    public boolean updatePicture(PictureBean pictureBean) {
        return false;
    }
}
