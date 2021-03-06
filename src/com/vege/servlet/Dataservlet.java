package com.vege.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vege.DB.MysqlDB;
import com.vege.information.User;


import net.sf.json.JSONArray;

/**
 * Servlet implementation class Dataservlet
 */
@WebServlet("/Dataservlet")

public class Dataservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dataservlet() {
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
		String name = request.getParameter("name");
		String manage = request.getParameter("manage");
		String location = request.getParameter("location");
		String channels = request.getParameter("channels");
		System.out.println("year:"+year+";"+"month:"+month+";"+"days:"+days+";"+"market:"+market+";"+"manage:"+manage+";"+"name:"+name+";"+"location:"+location+";"+"channels:"+channels+";");*/
		MysqlDB mysqldb = new MysqlDB();
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String market = request.getParameter("market");
		String result = request.getParameter("result");
		User user = new User();
		user.setYear(year);
		user.setMonth(month);
		user.setMarket(market);
		user.setResult(result);
		//System.out.println(user.getResult());
		Connection connection = mysqldb.getConnection();
		Statement stmt = null; 
		ResultSet rs = null;
		try {
			stmt=connection.createStatement();
			String sql="select manage,sampname,location,channels,testidx,result,YEAR(testtm) as year,MONTH(testtm) as month,DAY(testtm) as days from testtb where YEAR(testtm)='"+user.getYear()+"' and MONTH(testtm)='"+user.getMonth()+"' and result='"+ user.getResult() +"' and testaddr='"+ user.getMarket() +"' ORDER BY testtm";
			rs = stmt.executeQuery(sql);
			List list = new ArrayList();
		/*	while(rs.next()) {
				String manage = rs.getString("manage");
				String sampname = rs.getString("sampname");
				String location = rs.getString("location");
				String channels = rs.getString("channels");
				String testidx = rs.getString("testidx");
				float result = rs.getFloat("result");
				Date testtm = ()rs.getDate(6);
			}*/
			JSONArray jsonData = JSONArray.fromObject(convertList(rs));
			//System.out.println(jsonData.toString());
			out.print(jsonData);
			stmt.close();
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
