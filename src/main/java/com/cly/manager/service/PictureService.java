package com.cly.manager.service;


import com.cly.manager.bean.PictureBean;

import java.util.List;

public interface PictureService {
    List<PictureBean> getPictureBeanList()throws Exception;
    List<PictureBean> getPictureBeanList(int year)throws Exception;
    List<PictureBean> getAlbumList() throws Exception;

    PictureBean getPicture(int pid)throws Exception;
    boolean addPicture(PictureBean pictureBean);
    boolean updatePicture(PictureBean pictureBean);
    boolean deletePicture(int pid);
}
