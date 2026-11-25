package in.com.rays.prepared.statement;

import java.sql.Date;

public class EmployeeBean {
	
	private int id;
	private String Name;
	private String department;
	private double slaery;
	private Date joiningdate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public double getSlaery() {
		return slaery;
	}
	public void setSlaery(double slaery) {
		this.slaery = slaery;
	}
	public Date getJoiningdate() {
		return joiningdate;
	}
	public void setJoiningdate(Date joiningdate) {
		this.joiningdate = joiningdate;
	}

	
}
