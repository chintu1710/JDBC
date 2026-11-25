package in.com.rays.prepared.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class QuestionModule {

	public void add(QuestionBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Examination", "root", "root");

			PreparedStatement ps = conn.prepareStatement("insert into question values(?,?,?,?,?,?,?)");

			ps.setInt(1, bean.getId());
			ps.setString(2, bean.getQuestionText());
			ps.setString(3, bean.getOption1());
			ps.setString(4, bean.getOption2());
			ps.setString(5, bean.getOption3());
			ps.setString(6, bean.getOption4());
			ps.setString(7, bean.getAnswer());

			int i = ps.executeUpdate();
			System.out.println("Data inserted = " + i);

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		
		} finally {
			conn.close();
		}
	}

	public void getQuestions() throws Exception {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Examination", "root", "root");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM question");

			ResultSet rs = ps.executeQuery();
			
			

			while (rs.next()) {

				System.out.println(rs.getInt("id"));
				System.out.println(rs.getString("question_text"));
			}

		} catch (Exception e) {

			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void startQuiz() throws Exception {

		Scanner sc = new Scanner(System.in);
		int score = 0;
		int total = 0;

		System.out.print("Enter your name : ");
		String name = sc.nextLine().trim();

		System.out.println(sc);

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Examination", "root", "root");
				PreparedStatement ps = conn.prepareStatement("select * from question");
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				total++;
				String correctAnswer = rs.getString("answer");

				System.out.println();
				System.out.println(rs.getString("question_text"));
				System.out.println("A. " + rs.getString("option1"));
				System.out.println("B. " + rs.getString("option2"));
				System.out.println("C. " + rs.getString("option3"));
				System.out.println("D. " + rs.getString("option4"));

				System.out.print("Enter Answer : ");
				String userAnswer = sc.nextLine().trim();

				if (userAnswer.equalsIgnoreCase(correctAnswer)) {
					score++;
				}
			}

			System.out.println();
			System.out.println("Your Score : " + score + " / " + total);

			try (PreparedStatement ps2 = conn.prepareStatement("insert into result(name, score, date) values(?,?,?)")) {

				ps2.setString(1, name);
				ps2.setInt(2, score);
				ps2.setDate(3, new java.sql.Date(System.currentTimeMillis()));

				int i = ps2.executeUpdate();
				System.out.println("Result saved = " + i);

				System.out.println("----------------");
				System.out.println("Exam Completed");
				System.out.println("Score = " + score);
				System.out.println("----------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}