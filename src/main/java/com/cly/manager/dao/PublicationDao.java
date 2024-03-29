package com.cly.manager.dao;

import com.cly.manager.bean.PublicationBean;

import java.util.List;

public interface PublicationDao {
    List<PublicationBean> fetchPublicationList()throws Exception;
    List<PublicationBean> fetchPublicationList(int year)throws Exception;
    List<PublicationBean> fetchPublicationListByUid(int uid)throws Exception;
    PublicationBean fetchPublication(int pid)throws Exception;

    boolean addPublication(PublicationBean publicationBean);
    boolean updatePublication(PublicationBean publicationBean);
    boolean deletePublication(int pid);
}
