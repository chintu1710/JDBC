package in.com.rays.prepared.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeModule {

	public void add(EmployeeBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO employee VALUES(?, ?, ?, ?, ?)");

			ps.setInt(1, bean.getId());
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getDepartment());
			ps.setDouble(4, bean.getSlaery());
			ps.setDate(5, bean.getJoiningdate());

			ps.executeUpdate();

			conn.commit();

			System.out.println("Data Inserted Sucessful");

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

		} finally {
			conn.close();
		}

	}

	public void update(EmployeeBean bean) {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "rays", "rays");

			conn.setAutoCommit(false);

			PreparedStatement ps = conn
					.prepareStatement("UPDATE employee SET name=?, department=?, salary=?, joiningdate=? WHERE id=?");

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
