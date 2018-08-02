package com.vege.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

import com.vege.DB.MysqlDB;
import com.vege.information.*;

import net.sf.json.JSONArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Chartservlet
 */
@WebServlet("/Chartservlet")
public class Chartservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Chartservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String year = "2018";//request.getParameter("year");
		String testaddr = "�������(��˳�ֵ�)";//request.getParameter("market");
		User user = new User();
		user.setYear(year);
		user.setMarket(testaddr);
		MysqlDB mysqldb = new MysqlDB();
		Connection connection = mysqldb.getConnection();
		Statement stmt = null; 
		ResultSet rs = null;
		try {
			stmt=connection.createStatement();
			String sql="select m.Monthnm as month,ifnull(t.manage,0) as total from Mouthtb m left join (select month(testtm) as mon,count(manage) as manage from testtb where year(testtm) = '"+user.getYear()+"' and testaddr='"+user.getMarket()+"' group by month (testtm)) t on m.Monthnm=t.mon";
			rs = stmt.executeQuery(sql);
			JSONArray jsonData = JSONArray.fromObject(convertList(rs));
			System.out.println(jsonData.toString());
			out.print(jsonData);
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private static List convertList(ResultSet rs) throws SQLException {
        List list = new ArrayList();
        ResultSetMetaData rm = rs.getMetaData();
        int columnCount = rm.getColumnCount();
        while (rs.next()) {
            Map rowData = new HashMap();
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(rm.getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
        }
        return list;
    }

}