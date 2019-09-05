package com.zhongwang.empl.product.webapp;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description: 用一句话具体描述类的功能
 * @author: slliver
 * @date: 2019/9/3 13:21
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DruidConfigurateTest.class})
public class DruidConfigurateTest {


    @Autowired
    private DruidDataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("select * from sys_user where pkid='05b31a943dce491e98fc910ae015aed3'");
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            String cityName = resultSet.getString("name");
            System.out.println(cityName);
        }
    }


}
