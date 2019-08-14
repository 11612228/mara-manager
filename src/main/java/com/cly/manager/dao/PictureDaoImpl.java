package com.cly.manager.dao;

import com.cly.manager.bean.PictureBean;
import com.cly.manager.bean.PublicationBean;
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
    public List<PictureBean> fetchPictureBeanList() throws Exception {
        pictureBeanList = new ArrayList<>();
        connection = dbutil.getConnection();
        String sql="select * from Picture p order by pid desc";
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

    @Override
    public PictureBean fetchPicture(int pid) throws Exception {
        connection = dbutil.getConnection();
        String sql="select * from Picture p where pid = ? order by pid desc";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, pid);
        resultSet=preparedStatement.executeQuery();
        PictureBean pictureBean = new PictureBean();
        while(resultSet.next()){
            String imgSrc = resultSet.getString("imgSrc");
            String pName = resultSet.getString("pName");
            int year = resultSet.getInt("year");

            pictureBean.setpName(pName);
            pictureBean.setImgSrc(imgSrc);
            pictureBean.setYear(year);
            pictureBean.setPid(pid);
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return pictureBean;
    }

    @Override
    public boolean addPicture(PictureBean pictureBean) {
        try {
            connection = dbutil.getConnection();
            String sql="insert into Picture (year,pName,imgSrc) values (?,?,?) ";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,pictureBean.getYear());
            preparedStatement.setString(2,pictureBean.getpName());
            preparedStatement.setString(3,pictureBean.getImgSrc());
            int rtn = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return rtn!=0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updatePicture(PictureBean pictureBean) {
        try {
            connection = dbutil.getConnection();
            String sql="UPDATE Picture SET year=?, pName=?, imgSrc=?where pid = ?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,pictureBean.getYear());
            preparedStatement.setString(2,pictureBean.getpName());
            preparedStatement.setString(3,pictureBean.getImgSrc());
            preparedStatement.setInt(4,pictureBean.getPid());
            int rtn = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return rtn!=0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deletePicture(int pid) {
        try {
            connection = dbutil.getConnection();
            String sql="delete from Picture where pid = ?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, pid);
            int rtn = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return rtn!=0;
        } catch (Exception e) {
            return false;
        }
    }


}
