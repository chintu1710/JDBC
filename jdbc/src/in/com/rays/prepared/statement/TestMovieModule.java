package in.com.rays.prepared.statement;

public class TestMovieModule {

	public static void main(String[] args) {

		try {
//			testAdd();
//			testUpdate();
			testFindByPk();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testAdd() throws Exception {

		MovieBean bean = new MovieBean();
		MovieModule module = new MovieModule();

		bean.setMovieId(1);
		bean.setTitle("The Shawshank Redemption");
		bean.setMovieName("The Shawshank Redemption");
		bean.setGenre("Drama");
		bean.setDration(142);
		bean.setRating(9.3);

		module.add(bean);
	}

	public static void testUpdate() throws Exception {

		MovieBean bean = new MovieBean();
		MovieModule module = new MovieModule();

		bean.setMovieId(1);
		bean.setTitle("The Shawshank Redemption");
		bean.setMovieName("The Shawshank Redemption");
		bean.setGenre("Drama");
		bean.setDration(144);
		bean.setRating(9.4);

		module.update(bean);
	}

	public static void testFindByPk() throws Exception {

		MovieModule module = new MovieModule();

		MovieBean bean = new MovieBean();

		bean.setMovieId(11);

		bean = module.FindByPk(11);

		if (bean != null) {

			System.out.println(bean.getMovieId());
			System.out.println(bean.getTitle());
			System.out.println(bean.getMovieName());
			System.out.println(bean.getGenre());
			System.out.println(bean.getDration());
			System.out.println(bean.getRating());

		} else {
			System.out.println("Record not found");
		}
	}

}
