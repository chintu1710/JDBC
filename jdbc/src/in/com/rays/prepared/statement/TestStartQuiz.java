package in.com.rays.prepared.statement;

public class TestStartQuiz {
	public static void main(String[] args) {

		try {

			QuestionModule model = new QuestionModule();

			model.startQuiz();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
