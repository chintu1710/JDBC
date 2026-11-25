package in.co.rays.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import in.com.rays.prepared.statement.MovieBean;
import in.com.rays.prepared.statement.MovieModule;

public class RestaurantModule {

	public void add(RestaurantBean bean) throws Exception {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO Restaurant VALUES(?, ?, ?, ?, ?)");

			ps.setInt(1, findByNext());
			ps.setString(2, bean.getItemName());
			ps.setString(3, bean.getCategory());
			ps.setDouble(4, bean.getPrice());
			ps.setBoolean(5, bean.getisAvailable());

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

	public void update(RestaurantBean bean) throws Exception {
		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(
					"UPDATE Restaurant SET itemName=?, category=?, price=?, isAvailable=? WHERE itemId=?");

			ps.setString(1, bean.getItemName());
			ps.setString(2, bean.getCategory());
			ps.setDouble(3, bean.getPrice());
			ps.setBoolean(4, bean.getisAvailable());
			ps.setInt(5, bean.getItemId());
			
			int i = ps.executeUpdate();

			conn.commit();

			

			System.out.println(i);

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		}
	}

	public RestaurantBean findByPk(int itemId) throws Exception {
		Connection conn = null;
		RestaurantBean bean = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Restaurant WHERE itemId=?");
			ps.setInt(1, itemId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new RestaurantBean();
				bean.setItemId(rs.getInt(1));
				bean.setItemName(rs.getString(2));
				bean.setCategory(rs.getString(3));
				bean.setPrice(rs.getDouble(4));
				bean.setAvailable(rs.getBoolean(5));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return bean;
	}
	
	public void delete(int itemId) throws Exception {

	    Connection conn = null;

	    try {

	        Class.forName("com.mysql.cj.jdbc.Driver");

	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

	        conn.setAutoCommit(false);

	        PreparedStatement ps = conn.prepareStatement(
	            "DELETE FROM Restaurant WHERE itemId=?");

	        ps.setInt(1, itemId);

	        int i = ps.executeUpdate();

	        conn.commit();

	        System.out.println("Record Deleted " + i);

	    } catch (Exception e) {
	        if (conn != null) conn.rollback();
	        e.printStackTrace();
	    } finally {
	        if (conn != null) conn.close();
	    }
	}
	
	public int findByNext() throws Exception {
	    Connection conn = null;
	    RestaurantBean bean = null;
	    
	    int pk = 0;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

	        PreparedStatement ps = conn.prepareStatement(
	            "select max(itemId) from  restaurant");

	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	        	pk  = rs.getInt(1);   
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (conn != null) conn.close();  
	    }
	    return pk + 1 ;
	}
}
