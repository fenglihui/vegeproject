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
 * Servlet implementation class Testservlet
 */
@WebServlet("/Testservlet")
public class Testservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Testservlet() {
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
	/*	String year = request.getParameter("year");
		String month = request.getParameter("month");
		String days = request.getParameter("days");
		String market = request.getParameter("market");
		String name = request.getParameter("name");
		String manage = request.getParameter("manage");
		String location = request.getParameter("location");
		String channels = request.getParameter("channels");
		System.out.println("year:"+year+";"+"month:"+month+";"+"days:"+days+";"+"market:"+market+";"+"manage:"+manage+";"+"name:"+name+";"+"location:"+location+";"+"channels:"+channels+";");*/
		MysqlDB mysqldb = new MysqlDB();
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String days = request.getParameter("days");
		User user = new User();
		user.setYear(year);
		user.setMonth(month);
		user.setDays(days);
		Connection connection = mysqldb.getConnection();
		Statement stmt = null; 
		ResultSet rs = null;
		try {
			stmt=connection.createStatement();
			String sql="select manage,sampname,testidx,result from testtb where YEAR(testtm)='"+ user.getYear() +"' and MONTH(testtm)='"+ user.getMonth() +"' and DAY(testtm)='"+ user.getDays() +"'";
			rs = stmt.executeQuery(sql);
			JSONArray jsonData = JSONArray.fromObject(convertList(rs));
			System.out.println(jsonData.toString());
			out.print(jsonData);
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/*	User user = new User();
		user.setYear(year);
		user.setMonth(month);
		user.setMarket(market);
		user.setManage(manage);
		user.setName(name);
		user.setLocation(location);
		user.setChannels(channels);
		String data = year + month + market + manage + name + location + channels;
		*/
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
