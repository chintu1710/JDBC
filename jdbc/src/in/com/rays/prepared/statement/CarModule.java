package in.com.rays.prepared.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarModule {

	public void add(CarBean bean) throws SQLException {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("insert into car values(?,?,?,?,?)");

			ps.setInt(1, bean.getId());
			ps.setString(2, bean.getCarModel());
			ps.setString(3, bean.getCoustomerName());
			ps.setDouble(4, bean.getRentPerday());
			ps.setInt(5, bean.getDays());

			int i = ps.executeUpdate();

			conn.commit();

			System.out.println("Record Inserted" + i);

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	public void update(CarBean bean) throws Exception {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

			conn.setAutoCommit(false);

			PreparedStatement ps = conn
					.prepareStatement("update car set carModel=?, costomerName=?, rentPerday=?, days=? where id=?");
			ps.setString(1, bean.getCarModel());
			ps.setString(2, bean.getCoustomerName());
			ps.setDouble(3, bean.getRentPerday());
			ps.setInt(4, bean.getDays());
			ps.setInt(5, bean.getId());
			
			int i = ps.executeUpdate();

			conn.commit();

			System.out.println("Record Updated" + i);

		} catch (Exception e) {

			conn.rollback();
			e.printStackTrace();

		} finally {

			conn.close();
		}
	}

	public CarBean FindByPk(int id) throws Exception {

		Connection conn = null;
		CarBean bean = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

			PreparedStatement ps = conn.prepareStatement("select * from car where id=?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new CarBean();
				bean.setId(rs.getInt(1));
				bean.setCarModel(rs.getString(2));
				bean.setCoustomerName(rs.getString(3));
				bean.setRentPerday(rs.getDouble(4));
				bean.setDays(rs.getInt(5));

				System.out.println("Record Found");
			}

		} catch (Exception e) {

			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return bean;
	}

}
