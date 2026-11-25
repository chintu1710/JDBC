package in.com.rays.prepared.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MovieModule {

	public void add(MovieBean bean) throws Exception {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("insert into movie values(?,?,?,?,?)");

			ps.setInt(1, bean.getMovieId());
			ps.setString(2, bean.getTitle());
			ps.setString(3, bean.getGenre());
			ps.setInt(4, bean.getDration());
			ps.setDouble(5, bean.getRating());

			int i = ps.executeUpdate();

			conn.commit();

			System.out.println("Record Inserted " + i);

		} catch (Exception e) {

			if (conn != null) {
				conn.rollback();
			}
			e.printStackTrace();

		} finally {

			if (conn != null) {
				conn.close();
			}
		}
	}

	public void update(MovieBean bean) throws Exception {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

			conn.setAutoCommit(false);

			PreparedStatement ps = conn
					.prepareStatement("update movie set title=?, genre=?, duration=?, rating=? where movieId=?");

			ps.setString(1, bean.getTitle());
			ps.setString(2, bean.getGenre());
			ps.setInt(3, bean.getDration());
			ps.setDouble(4, bean.getRating());
			ps.setInt(5, bean.getMovieId());

			int i = ps.executeUpdate();

			conn.commit();

			System.out.println("Record Updated " + i);

		} catch (Exception e) {

				conn.rollback();
			e.printStackTrace();

		} finally {

				conn.close();
			
		}
	}

	public MovieBean FindByPk(int movieId) throws Exception {

		Connection conn = null;
		MovieBean bean = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

			PreparedStatement ps = conn.prepareStatement("select * from movie where movieId=?");
			ps.setInt(1, movieId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new MovieBean();
				bean.setMovieId(rs.getInt(1));
				bean.setTitle(rs.getString(2));
				bean.setGenre(rs.getString(3));
				bean.setDration(rs.getInt(4));
				bean.setMovieName(rs.getString(5));

				bean.setRating(rs.getDouble(6));
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			conn.close();

		}

		return bean;
	}

}