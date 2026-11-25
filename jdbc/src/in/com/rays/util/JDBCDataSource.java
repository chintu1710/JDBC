package in.com.rays.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCDataSource {

	public static Connection getConnection() {

		ResourceBundle rb = ResourceBundle.getBundle("in.com.rays.bundle.app");

		Connection conn = null;

		try {

			Class.forName(rb.getString("driver"));

			conn = DriverManager.getConnection(rb.getString("url"), rb.getString("password"), rb.getString("username"));

		} catch (Exception e) {
			e.printStackTrace();

		}
		return conn;

	}
	
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public static void tranRollBack(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
