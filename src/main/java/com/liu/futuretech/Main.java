package com.liu.futuretech;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        ConnectionPerformance connectionPerformance = new ConnectionPerformance();
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Connection connection = connectionPerformance.getConnection();
            connection.close();
        }
        Long end = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("start: " + simpleDateFormat.format(new Date(start)));
        System.out.println("end: " + simpleDateFormat.format(new Date(end)));
        /**====执行结果===**/
        //start: 2020-05-14 15:17:46
        //end: 2020-05-14 15:19:24

        //c3p0使用测试
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("mysql");
        Long startc3p0 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            connection.close();
        }
        Long endc3p0 = System.currentTimeMillis();
        System.out.println("startc3p0: " + simpleDateFormat.format(new Date(startc3p0)));
        System.out.println("endc3p0: " + simpleDateFormat.format(new Date(endc3p0)));

        //druid使用测试
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://192.168.10.118:3306/test_dongying?serverTimezone=UTC");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        Long startdruid = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Connection connection = druidDataSource.getConnection();
            System.out.println(i + ": " + druidDataSource.getMaxActive());
            connection.close();
        }
        Long enddruid = System.currentTimeMillis();
        System.out.println("startdruid: " + simpleDateFormat.format(new Date(startdruid)));
        System.out.println("enddruid: " + simpleDateFormat.format(new Date(enddruid)));

    }
}
