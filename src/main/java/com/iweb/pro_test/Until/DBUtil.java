package com.iweb.pro_test.Until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author tang
 * @date 2023/7/17 18:46
 */
public class DBUtil {
    public static Connection connection;
    private static final String USER_NAME = "root";
    private static final String PASSWORD="a12345";
    private static final String URL="jdbc:mysql://8.130.46.228:3306/project?characterEncoding=utf8";
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws Exception{
        connection=DriverManager.getConnection(URL,USER_NAME,PASSWORD);
        return connection;
    }
    public static void closeConnection(){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
