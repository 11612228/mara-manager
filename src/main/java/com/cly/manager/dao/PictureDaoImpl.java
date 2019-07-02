package com.cly.manager.dao;

import com.cly.manager.bean.PictureBean;
import com.cly.manager.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PictureDaoImpl implements PictureDao {
    ConnectDB dbutil = new ConnectDB();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ResultSetMetaData metaData = null;

    List<PictureBean> pictureBeanList = null;
    PictureBean pictureBean = null;
    @Override
    public List<PictureBean> fetchAlbumList() throws Exception {
        pictureBeanList = new ArrayList<>();
        connection = dbutil.getConnection();
        String sql="select * from Picture p where pName = 'album' order by year desc";
        preparedStatement=connection.prepareStatement(sql);
        resultSet=preparedStatement .executeQuery();

        while(resultSet.next()){
            int pid = resultSet.getInt("pid");
            String imgSrc = resultSet.getString("imgSrc");
            String pName = resultSet.getString("pName");
            int year = resultSet.getInt("year");

            pictureBean = new PictureBean();
            pictureBean.setPid(pid);
            pictureBean.setImgSrc(imgSrc);
            pictureBean.setpName(pName);
            pictureBean.setYear(year);

            pictureBeanList.add(pictureBean);

        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return pictureBeanList;
    }

    @Override
    public List<PictureBean> fetchPictureBeanList(int year) throws Exception {
        pictureBeanList = new ArrayList<>();
        connection = dbutil.getConnection();
        String sql="select * from Picture p where pName != 'album' and year = ? order by pid desc";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,year);
        resultSet=preparedStatement .executeQuery();

        while(resultSet.next()){
            int pid = resultSet.getInt("pid");
            String imgSrc = resultSet.getString("imgSrc");
            String pName = resultSet.getString("pName");

            pictureBean = new PictureBean();
            pictureBean.setPid(pid);
            pictureBean.setImgSrc(imgSrc);
            pictureBean.setpName(pName);
            pictureBean.setYear(year);

            pictureBeanList.add(pictureBean);

        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return pictureBeanList;
    }


}
