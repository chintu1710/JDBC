package in.co.rays.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TranzectionHandling {

	public static void main(String[] args) throws SQLException {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collage", "root", "root");

			conn.setAutoCommit(false);

			Statement stmt = conn.createStatement();

			int i = stmt.executeUpdate("insert into student vlaues('112' , 'Rahul', 'DAVV' , '2009-08-12')");

			i = stmt.executeUpdate("insert into student values('115' , 'Amit' , 'Sage', '2004-09-15')");

			i = stmt.executeUpdate("insert into student values('112', 'Sumit' , 'CDGI', '2009-07-10')");
			conn.commit();
			System.out.println(i);

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

}
