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
            String bName = resultSet.getString("bName");

            BannerBean bannerBean = new BannerBean();
            bannerBean.setBid(bid);
            bannerBean.setImgSrc(imgSrc);
            bannerBean.setbName(bName);

            bannerBeanList.add(bannerBean);

        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return bannerBeanList;
    }
}
