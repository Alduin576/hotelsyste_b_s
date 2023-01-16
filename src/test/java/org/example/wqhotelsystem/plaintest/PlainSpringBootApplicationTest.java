package org.example.wqhotelsystem.plaintest;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by wuqi on 2023/1/16
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
//测试数据源Druid是否配置完成
public class PlainSpringBootApplicationTest {

    //注入数据源
    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();//获取连接
        System.out.println(connection);

        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource数据源最大连接数："+druidDataSource.getMaxActive());

        connection.close();//关闭连接
    }


}
