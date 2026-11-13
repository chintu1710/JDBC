package in.co.rays.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserModel {

	public int add(UserBean bean) throws SQLException{

		Connection conn = null;
		int i = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO st_user VALUES(?,?,?,?,?,?)");

			ps.setInt(1, bean.getId());
			ps.setString(2, bean.getFirst_name());
			ps.setString(3, bean.getLastname());
			ps.setString(4, bean.getLogin());
			ps.setString(5, bean.getPassword());
			ps.setDate(6, bean.getDob());

			i = ps.executeUpdate();

			conn.commit();

			System.out.println("Record Inserted Successfully");

			ps.close();

		} catch (Exception e) {

			conn.rollback();
			e.printStackTrace();

		} finally {

			if (conn != null) {
				conn.close();
			}
		}

		return i;
	}

	public void delete(int id) throws SQLException {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("DELETE FROM st_user WHERE ID = ?");

			ps.setInt(1, id);

			int i = ps.executeUpdate();

			conn.commit();

			System.out.println("record delete Sucessfully" + " " + id);

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

		finally {
			conn.close();
		}
	}

	public void update(UserBean bean) throws SQLException {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(
					"UPDATE st_user SET FIRST_NAME = ?, LAST_NAME = ?, LOGIN = ?, PASSWORD = ?, DOB = ? WHERE ID = ?");
			ps.setString(1, bean.getFirst_name());
			ps.setString(2, bean.getLastname());
			ps.setString(3, bean.getLogin());
			ps.setString(4, bean.getPassword());
			ps.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			ps.setInt(6, bean.getId());

			int i = ps.executeUpdate();

			conn.commit();

			System.out.println("Record Updated Successfully");

		} catch (Exception e) {

			conn.rollback();
			e.printStackTrace();
		}

		finally {
			conn.close();
		}
	}

	public UserBean findByPK(int id) throws SQLException {

		Connection conn = null;
		UserBean bean = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ST_USER WHERE ID = ?");

			ps.setInt(1, id);

			java.sql.ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				bean = new UserBean();

				bean.setId(rs.getInt(1));
				bean.setFirst_name(rs.getString(2));
				bean.setLastname(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));

			}

		} catch (Exception e) {

			conn.rollback();
			e.printStackTrace();
		}

		finally {
			conn.close();
		}

		return bean;

	}

	public UserBean findByLogin(String login) throws SQLException {

		Connection conn = null;
		UserBean bean = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ST_USER WHERE LOGIN = ?");

			ps.setString(1, login);

			java.sql.ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				bean = new UserBean();

				bean.setId(rs.getInt(1));
				bean.setFirst_name(rs.getString(2));
				bean.setLastname(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));

			}

		} catch (Exception e) {

			conn.rollback();
			e.printStackTrace();
		}

		finally {
			conn.close();
		}

		return bean;

	}

}