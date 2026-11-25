package in.com.rays.prepared.statement;

public class TestCarModule {

	public static void main(String[] args) {

		try {

//			testAdd();
			 testUpdate();
//			 testFindByPk();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testAdd() throws Exception {

		CarBean bean = new CarBean();
		CarModule module = new CarModule();

		bean.setId(1);
		bean.setCarModel("BMW");
		bean.setCoustomerName("Ravi");
		bean.setRentPerday(5000);
		bean.setDays(5);

		module.add(bean);
		System.out.println("Record Inserted");
	}

	
	public static void testUpdate() throws Exception {

		CarBean bean = new CarBean();
		CarModule module = new CarModule();

		bean.setId(1);
		bean.setCarModel("Audi");
		bean.setCoustomerName("Ravi");
		bean.setRentPerday(6000);
		bean.setDays(5);

		module.update(bean);
		System.out.println("Record Updated");
	}
	
	public static void testFindByPk() throws Exception {

		CarModule module = new CarModule();

		CarBean bean = new CarBean();
		bean.setId(1);

		bean = module.FindByPk(1);

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getCarModel());
			System.out.println(bean.getCoustomerName());
			
			System.out.println(bean.getRentPerday());
			System.out.println(bean.getDays());
		} else {
			System.out.println("Record Not Found");
		}
	}
}