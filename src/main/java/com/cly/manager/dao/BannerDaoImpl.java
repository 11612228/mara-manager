package com.cly.manager.dao;

import com.cly.manager.bean.BannerBean;
import com.cly.manager.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BannerDaoImpl implements BannerDao{
    ConnectDB dbutil = new ConnectDB();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ResultSetMetaData metaData = null;

    @Override
    public List<BannerBean> fetchBannerBeanList() throws Exception {
        List<BannerBean> bannerBeanList = new ArrayList<>();
        connection = dbutil.getConnection();
        String sql="select * from Banner a order by bid desc";
        preparedStatement=connection.prepareStatement(sql);
        resultSet=preparedStatement .executeQuery();

        while(resultSet.next()){
            int bid = resultSet.getInt("bid");
            String imgSrc = resultSet.getString("imgSrc");

            BannerBean bannerBean = new BannerBean();
            bannerBean.setBid(bid);
            bannerBean.setImgSrc(imgSrc);

            bannerBeanList.add(bannerBean);

        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return bannerBeanList;
    }

    @Override
    public BannerBean fetchBannerBean(int bid) throws Exception {
        BannerBean bannerBean = new BannerBean();
        connection = dbutil.getConnection();
        String sql="select * from Banner a where bid = ?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,bid);
        resultSet=preparedStatement .executeQuery();
        while(resultSet.next()){
            String imgSrc = resultSet.getString("imgSrc");
            String bName = resultSet.getString("bName");
            bannerBean.setBid(bid);
            bannerBean.setImgSrc(imgSrc);
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return bannerBean;
    }

    @Override
    public boolean deleteBannerBean(int bid){
        try {
            connection = dbutil.getConnection();
            String sql = "DELETE from Banner where bid = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bid);
            int result = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return result != 0;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean addBannerBean(BannerBean bannerBean) {
        try {
            connection = dbutil.getConnection();
            String sql = "INSERT INTO Banner (imgSrc) VALUES (?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bannerBean.getImgSrc());
            int result = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return result!=0;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateBannerBean(BannerBean bannerBean) {
        try {
            connection = dbutil.getConnection();
            String sql="UPDATE Banner SET imgSrc=? where bid = ?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,bannerBean.getImgSrc());
            preparedStatement.setInt(2,bannerBean.getBid());
            int rtn = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return rtn!=0;
        } catch (Exception e) {
            return false;
        }
    }
}
