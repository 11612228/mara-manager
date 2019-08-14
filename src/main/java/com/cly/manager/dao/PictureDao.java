package com.cly.manager.dao;

import com.cly.manager.bean.PictureBean;
import com.cly.manager.bean.PublicationBean;

import java.util.List;

public interface PictureDao {
    List<PictureBean> fetchAlbumList() throws Exception;
    List<PictureBean> fetchPictureBeanList()throws Exception;
    List<PictureBean> fetchPictureBeanList(int year) throws Exception;
    PictureBean fetchPicture(int pid)throws Exception;


    boolean addPicture(PictureBean pictureBean);
    boolean updatePicture(PictureBean pictureBean);
    boolean deletePicture(int pid);
}
