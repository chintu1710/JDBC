package in.com.rays.prepared.statement;

public class TestProductBean {

	public static void main(String[] args) {
		try {
//            testAdd();
//            testUpdate();
			testFindByPk();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testAdd() throws Exception {
		ProductBean bean = new ProductBean();
		ProductModule module = new ProductModule();

		bean.setId(22);
		bean.setProduct_name("Laptop");
		bean.setCategory("Electronics");
		bean.setPrice("50000");
		bean.setQuantity("10");

		module.add(bean);
		System.out.println(" record inserted");
	}

	public static void testUpdate() throws Exception {
		ProductBean bean = new ProductBean();
		ProductModule module = new ProductModule();

		bean.setId(20);
		bean.setProduct_name("Dell Laptop");
		bean.setCategory("Electronics");
		bean.setPrice("52000");
		bean.setQuantity("8");

		module.update(bean);
		System.out.println(" record updated");
	}

	public static void testFindByPk() throws Exception {
		ProductModule module = new ProductModule();

		ProductBean bean = new ProductBean();
		bean.setId(20);

		bean = module.FindByPk(20);

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getProduct_name());
			System.out.println(bean.getCategory());
			System.out.println(bean.getPrice());
			System.out.println(bean.getQuantity());
		} else {
			System.out.println("Record Not Found");
		}
	}
}
