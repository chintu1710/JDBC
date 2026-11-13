package in.com.rays.prepared.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductModule {

	public void add(ProductBean bean) throws Exception {

		Connection conn = null;
		int i = 0;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");

			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("insert into product values(?,?,?,?,?)");

			ps.setInt(1, bean.getId());
			ps.setString(2, bean.getProduct_name());
			ps.setString(3, bean.getCategory());
			ps.setString(4, bean.getPrice());
			ps.setString(5, bean.getQuantity());

			i = ps.executeUpdate();

			conn.commit();

			System.out.println("Record Inserted : " + i);

		} catch (Exception e) {

			conn.rollback();
			e.printStackTrace();

		} finally {

			if (conn != null) {
				conn.close();
			}
		}
	}

	public void update(ProductBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306//jdbc", "root", "root");

			conn.setAutoCommit(false);

			PreparedStatement ps = conn
					.prepareStatement("update product set product_name=?, category=?, price=?, quantity=? where id=?");

			ps.setString(1, bean.getProduct_name());
			ps.setString(2, bean.getCategory());
			ps.setString(3, bean.getPrice());
			ps.setString(4, bean.getQuantity());
			ps.setInt(5, bean.getId());

			int i = ps.executeUpdate();

			conn.commit();

			System.out.println(i + " Record Updated Successfully");

		} catch (Exception e) {

			e.printStackTrace();
			conn.rollback();

		}

		finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public ProductBean FindByPk(int id) throws Exception {

		Connection conn = null;
		ProductBean bean = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");

			PreparedStatement ps = conn.prepareStatement("select * from product where id=?");

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
                bean = new ProductBean();
				bean.setId(rs.getInt("id"));
				bean.setProduct_name(rs.getString("product_name"));
				bean.setCategory(rs.getString("category"));
				bean.setPrice(rs.getString("price"));
				bean.setQuantity(rs.getString("quantity"));

			}

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
