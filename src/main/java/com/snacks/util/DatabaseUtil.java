package com.snacks.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseUtil {
    private static String url;
    private static String username;
    private static String password;
    private static String driver;
    private static String imageBaseUrl;  // 图片基础URL
    private static int pageSize;         // 分页大小

    static {
        try {
            Properties prop = new Properties();
            InputStream input = DatabaseUtil.class.getClassLoader()
                    .getResourceAsStream("config.properties");
            prop.load(input);

            url = prop.getProperty("db.url");
            username = prop.getProperty("db.username");
            password = prop.getProperty("db.password");
            driver = prop.getProperty("db.driver");
            imageBaseUrl = prop.getProperty("image.base.url", "http://www.xxx.com");  // 新增
            pageSize = Integer.parseInt(prop.getProperty("page.size", "10"));         // 新增

            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 新增：获取图片基础URL
    public static String getImageBaseUrl() {
        return imageBaseUrl;
    }

    // 新增：获取分页大小
    public static int getPageSize() {
        return pageSize;
    }
}