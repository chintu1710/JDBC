package in.com.rays.callablestatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class TestCollableStatement {

	public static void main(String[] args) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

		CallableStatement cs = conn.prepareCall("{call searchById(?)}");

		cs.setInt(1, 3);

		ResultSet rs = cs.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getInt("id"));
			System.out.println(rs.getString("first_name"));
			System.out.println(rs.getString("last_name"));
			System.out.println(rs.getString("login"));
			System.out.println(rs.getString("password"));
			System.out.println(rs.getDate("dob")); 
			
		}

	}

}
