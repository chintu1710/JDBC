package in.com.rays.prepared.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentModule {

	Connection conn = null;
	int i = 0;

	public void add(StudentBean bean) throws Exception {

		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");

			conn.setAutoCommit(false);

			ps = conn.prepareStatement("INSERT INTO students (id, name, email, course) VALUES (?, ?, ?, ?)");

			ps.setInt(1, bean.getId());
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getEmail());
			ps.setString(4, bean.getCourse());

			i = ps.executeUpdate();

			conn.commit();

			System.out.println("Record Inserted Successfully: " + i);

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();

		} finally {
			conn.close();

		}
	}

	public void update(StudentBean bean) throws Exception {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");

			conn.setAutoCommit(false);

			PreparedStatement ps = conn
					.prepareStatement("UPDATE students SET name = ?, email = ?, course = ? WHERE id = ?");

			ps.setString(1, bean.getName());
			ps.setString(2, bean.getEmail());
			ps.setString(3, bean.getCourse());
			ps.setInt(4, bean.getId());

			int i = ps.executeUpdate();
			
			System.out.println(i);

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();

		} finally {
			conn.close();
		}

	}

	public StudentBean findByPK(int id) throws Exception {

		Connection conn = null;
		StudentBean bean = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM students WHERE id = ?");

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setCourse(rs.getString("course"));
				
			}

			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return bean;
	}
}
