package com.camunda.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.camunda.messaging.SubProcess.ProcessConstants;

public class DatabaseConnectionUtil {
	private String query;
	private Connection con = null;
	private PreparedStatement ps = null;
	
	public DatabaseConnectionUtil(String query) {
		this.query = query;
	}
	
	private void setPreparedStatement() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal", ProcessConstants.DB_USER_NAME, ProcessConstants.DB_USER_PASSWORD);
			this.ps = con.prepareStatement(query);
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public PreparedStatement getPreparedStatement() {
		setPreparedStatement();
		return this.ps;
	}
	
	public void closeConnection() {
		try {
			if(this.ps != null) {
				this.ps.close();
			}
			if(con != null) {
				this.con.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
