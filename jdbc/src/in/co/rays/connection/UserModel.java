package in.co.rays.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.com.rays.util.JDBCDataSource;

public class UserModel {

	public int add(UserBean bean) throws Exception {

		Connection conn = null;

		UserBean existBean = findByLogin(bean.getLogin());

		if (existBean != null) {
			throw new RuntimeException("loginId already exist");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_USER VALUES(?, ?, ?, ?, ?, ?)");

			pstmt.setInt(1, bean.getId());
			pstmt.setString(2, bean.getFirst_name());
			pstmt.setString(3, bean.getLastname());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));

			int i = pstmt.executeUpdate();

			conn.commit();
			System.out.println("record inserted successfully");

		} catch (Exception e) {
			JDBCDataSource.tranRollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean.getId();

	}

	public void delete(int id) throws SQLException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("DELETE FROM st_user WHERE ID = ?");

			ps.setInt(1, id);

			int i = ps.executeUpdate();

			conn.commit();

			System.out.println("record delete Sucessfully" + " " + i);

		} catch (Exception e) {
			JDBCDataSource.tranRollBack(conn);
			e.printStackTrace();
		}

		finally {
			JDBCDataSource.closeConnection(conn);
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

			System.out.println("Record Updated Successfully" + i);

		} catch (Exception e) {

			JDBCDataSource.tranRollBack(conn);
			e.printStackTrace();
		}

		finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public UserBean findByPK(int id) throws SQLException {

		Connection conn = null;
		UserBean bean = null;

		try {

			conn = JDBCDataSource.getConnection();

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

			JDBCDataSource.tranRollBack(conn);
			e.printStackTrace();
		}

		finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;

	}

	public UserBean findByLogin(String login) throws SQLException {

		Connection conn = null;
		UserBean bean = null;

		try {

			conn = JDBCDataSource.getConnection();

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

			JDBCDataSource.tranRollBack(conn);
			e.printStackTrace();
		}

		finally {
			JDBCDataSource.tranRollBack(conn);
		}

		return bean;

	}

	public UserBean authenticate(String login, String password) throws SQLException {

		UserBean bean = findByLogin(login);

		if (bean != null && bean.getPassword().equals(password)) {
			return bean;
		}

		return null;

	}

	public List<UserBean> search(UserBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = null;
		List<UserBean> list = new ArrayList<UserBean>();

		StringBuffer sql = new StringBuffer("select * from st_user where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getFirst_name() != null && bean.getFirst_name().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirst_name() + "%'");
			}
			if (bean.getLastname() != null && bean.getLastname().length() > 0) {
				sql.append(" and last_name like '" + bean.getLastname() + "%'");
			}
		}

		if (pageSize > 0) {
			int index = (pageNo - 1) * pageSize;
			sql.append(" limit " + index + "," + pageSize);
		}

		System.out.println("sql ========>" + sql.toString());

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UserBean u = new UserBean();
				u.setId(rs.getInt("id"));
				u.setFirst_name(rs.getString("first_name"));
				u.setLastname(rs.getString("last_name"));
				u.setLogin(rs.getString("login"));
				u.setPassword(rs.getString("password"));
				u.setDob(rs.getDate("dob"));
				list.add(u);
			}

		} catch (Exception e) {
			JDBCDataSource.tranRollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}
}