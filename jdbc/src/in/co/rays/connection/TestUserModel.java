package in.co.rays.connection;

public class TestUserModel {

	public static void main(String[] args) throws Exception {

		try {
//		  testAdd();
//		  testDelete();
//	 testUpdate();
			testFindByPk();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testAdd() throws Exception {
		UserBean bean = new UserBean();

		UserModel model = new UserModel();

		bean.setId(11);
		bean.setFirst_name("Rahul");
		bean.setLastname("Sharma");
		bean.setEmail("chintu@123");
		bean.setLogin("rahul123");
		bean.setPassword("rahul@123");
		bean.setDob(java.sql.Date.valueOf("2000-08-12"));

		model.add(bean);

	}

	public static void testDelete() throws Exception {

		UserModel model = new UserModel();

		model.delete(11);

	}

	public static void testUpdate() throws Exception {

		UserBean bean = new UserBean();

		UserModel model = new UserModel();

		bean.setId(11);
		bean.setFirst_name("Rahul");
		bean.setLastname("Sharma");
		bean.setEmail("chintu@123");
		bean.setLogin("chintu@123");
		bean.setPassword("rahul@123");
		bean.setDob(java.sql.Date.valueOf("2000-08-12"));

		model.update(bean);

	}

	public static void testFindByPk() throws Exception {
		UserModel model = new UserModel();
		UserBean bean = model.findByPK(7); 
		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getFirst_name());
			System.out.println(bean.getLastname());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
		} else {
			System.out.println("Record not found");
		}
	}

}
