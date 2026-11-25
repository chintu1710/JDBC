package in.com.rays.prepared.statement;

public class TestStudent {

	public static void main(String[] args) {

		try {

//		testadd();
//		testupdate();
			testFindByPk();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testadd() throws Exception {

		StudentBean bean = new StudentBean();
		StudentModule module = new StudentModule();

		bean.setId(1011);
		bean.setName("Durgesh");
		bean.setCourse("CS");
		bean.setEmail("durgesh@1234.com");

		module.add(bean);

	}

	public static void testupdate() throws Exception {

		StudentModule module = new StudentModule();
		StudentBean bean = new StudentBean();

		bean.setId(1010);
		bean.setName("Durgesh Badole");
		bean.setCourse("CS");
		bean.setEmail("durgesh@123.com");

		module.update(bean);
	}

	public static void testFindByPk() throws Exception {

		StudentModule module = new StudentModule();
		StudentBean bean = new StudentBean();

		bean = module.findByPK(10);

		if (bean != null) {

			System.out.println(bean.getCourse());
			System.out.println(bean.getEmail());
			System.out.println(bean.getId());
			System.out.println(bean.getName());

		}

	}
}