	package Entity;

import lombok.Data;
@Data
	public class Employee {
	    private String id;
	    private String name;
	    private boolean gender;
	    private double salary;
	
	    public Employee() {}
	
	    public Employee(String id, String name, boolean gender, double salary) {
	        this.id = id;
	        this.name = name;
	        this.gender = gender;
	        this.salary = salary;
	    }

	
	
	  
	}
