package com.linalingling.bbb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/biobehaviorbridge";
    private static final String USER = "postgres";
    private static final String PASSWORD = "my_secret_password";

    public static Connection getConnection() throws SQLException {
        // 這行就是把 null 變成「實體連線」的魔法
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}