package com.cly.manager.dao;

import com.cly.manager.bean.AwardBean;
import com.cly.manager.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AwardDaoImpl implements AwardDao {
    ConnectDB dbutil = new ConnectDB();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ResultSetMetaData metaData = null;
    @Override
    public List<AwardBean> fetchAwardBeanList(int uid) throws Exception{
        List<AwardBean> awardBeanList = new ArrayList<>();
        connection = dbutil.getConnection();
        String sql="select * from Award a where uid = ?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,uid);
        resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            int aid = resultSet.getInt("aid");
            String award = resultSet.getString("award");

            AwardBean awardBean = new AwardBean();
            awardBean.setAward(award);
            awardBean.setUid(uid);
            awardBean.setAid(aid);

            awardBeanList.add(awardBean);

        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return awardBeanList;
    }
}
