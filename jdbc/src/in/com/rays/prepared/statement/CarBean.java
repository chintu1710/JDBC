package in.com.rays.prepared.statement;

public class CarBean {
	
	private int id;
	private String carModel;
	private String coustomerName;	
	private double rentPerday;
	private int days;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCoustomerName() {
		return coustomerName;
	}

	public void setCoustomerName(String coustomerName) {
		this.coustomerName = coustomerName;
	}

	public double getRentPerday() {
		return rentPerday;
	}

	public void setRentPerday(double rentPerday) {
		this.rentPerday = rentPerday;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
	

}
