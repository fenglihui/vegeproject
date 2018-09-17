package com.vege.DB;

import java.sql.*;
import java.io.IOException;

public class MysqlDB {
	//static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    //static final String DB_URL = "jdbc:mysql://localhost:3306/vegemarket?serverTimezone=GMT";
    static final String DB_URL = "jdbc:mysql://localhost:3306/vegemarket";
    static final String USER = "root";
    static final String PASS = "vegetables";  
    private static Connection connection = null;
    
    public static Connection getConnection(){

        try {
        	Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
    	} catch(SQLException se) {
    	// ���� JDBC ����
    		se.printStackTrace();
    	} catch(Exception e) {
    	// ���� Class.forName ����
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
