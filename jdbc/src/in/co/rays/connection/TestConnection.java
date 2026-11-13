package in.co.rays.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestConnection {

	public static void main(String[] args) throws Exception {
		// Load Driver
		Class.forName("com.mysql.cj.jdbc.Driver");

		// Make Connection
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collage", "root", "root");

		// Print database name
		System.out.println("Connection Established: " + conn.getCatalog());

		// Create Statement
		Statement stmt = conn.createStatement();

		int i = stmt.executeUpdate("insert into student values('111' , 'Rahul', 'DAVV' , '2009-08-12')");
		
		System.out.println("Record Inserted"+ i);

	}

}
