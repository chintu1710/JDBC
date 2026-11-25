package in.co.rays.connection;

public class TestRestaurant {

	public static void main(String[] args) {
		try {
			testadd();
//			testupdate();
//			testFindByPK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testadd() throws Exception {
		RestaurantModule module = new RestaurantModule();
		RestaurantBean bean = new RestaurantBean();

		
		bean.setItemName("pizza");
		bean.setCategory("Junk food");
		bean.setPrice(999);
		bean.setAvailable(false);

		module.add(bean);
	}
	
	public static void testupdate() throws Exception {

	    RestaurantModule module = new RestaurantModule();
	    RestaurantBean bean = new RestaurantBean();

	    bean.setItemId(190);
	    bean.setItemName("Burger");
	    bean.setCategory("Fast Food");
	    bean.setPrice(149.99);
	    bean.setAvailable(true);

	    module.update(bean);
	}
	
	public static void testFindByPk() throws Exception {

	    RestaurantModule module = new RestaurantModule();

	    RestaurantBean bean = module.findByPk(190);

	    if (bean != null) {
	        System.out.println("Id: " + bean.getItemId());
	        System.out.println("Name: " + bean.getItemName());
	        System.out.println("Category: " + bean.getCategory());
	        System.out.println("Price: " + bean.getPrice());
	        System.out.println("Available: " + bean.getisAvailable());
	    } else {
	        System.out.println("Item not found");
	    }
	}
	
	public static void testDelete() throws Exception {
	    RestaurantModule module = new RestaurantModule();
	    module.delete(190);
	}
	
	
}