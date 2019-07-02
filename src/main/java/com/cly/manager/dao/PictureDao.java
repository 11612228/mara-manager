package com.cly.manager.dao;

import com.cly.manager.bean.PictureBean;

import java.util.List;

public interface PictureDao {
    List<PictureBean> fetchAlbumList() throws Exception;
    List<PictureBean> fetchPictureBeanList(int year) throws Exception;
}
