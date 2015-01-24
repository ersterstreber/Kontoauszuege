package de.ersterstreber.kontoauszuege.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

	private final String user;
	private final String database;
	private final String password;
	private final int port;
	private final String hostname;
	
	private static Connection con;
	
	
	public MySQL(String hostname, int port, String database, String username, String password) {
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		this.user = username;
		this.password = password;
		
		openConnection();
	}

	public void openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"
					+ this.hostname + ":" + this.port + "/" + this.database,
					this.user, this.password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet executeQuery(String query) {
		try {
			PreparedStatement st = getConnection().prepareStatement(query);
			ResultSet rs = st.executeQuery();
			rs.first();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void updateQuery(String query) {
		try {
			PreparedStatement st = getConnection().prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return con;
	}
	
	public static void closeResources(PreparedStatement st, ResultSet rs) {
		try {
			if (st != null) {
				st.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
