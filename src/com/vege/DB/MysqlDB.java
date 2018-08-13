package com.vege.DB;

import java.sql.*;

public class MysqlDB {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/vegemarket?serverTimezone=GMT";
    static final String USER = "root";
    static final String PASS = "vegetables"; 
    private static Connection connection = null;
    
    public static Connection getConnection(){

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("���ݿ������쳣");
            e.printStackTrace();
        }
        return connection;
    }
    public  static void closeConnection(Connection connection){

                    if(connection != null){
                        try {
                            connection.close(); // �ر����ݿ�����
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
    }
