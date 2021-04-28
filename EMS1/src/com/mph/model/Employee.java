package com.mph.model;

import java.io.Serializable;
import java.util.Comparator;


//pojo - plain old java object - must only have private instance variables and public getters and setters
public class Employee implements Comparable<Employee>, Serializable{
	
	private int emp_id;
	private String emp_name;
	private Salary salary;
	private Manager managerdept;
	
	
	public Employee(int emp_id, String ename) {
			this.emp_id = emp_id;
			this.emp_name = ename;
	}
	
	public void setEid(int emp_id) {
		
		this.emp_id = emp_id;
	}
	
	public int getEid() {
		
		return emp_id;
	}
	
	public void setEname(String emp_name) {
		
		this.emp_name = emp_name;
	}
	
	public String getEname() {
		
		return emp_name;
	}	
	
	
	
	public Manager getManagerDept() {
		return managerdept;
	}

	public void setManager(Manager manager) {
		this.managerdept = manager;
	}

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", emp_name=" + emp_name + ", salary=" + salary + ", managerdept="
				+ managerdept + "]";
	}

	@Override
	public int compareTo(Employee e1) {
		// TODO Auto-generated method stub
		return this.getEid() - e1.getEid();
	}
	
	public static Comparator<Employee> nameComparator = new Comparator<Employee>() {

		@Override
		public int compare(Employee s1, Employee s2) {
			// TODO Auto-generated method stub
			return s1.getEname().compareTo(s2.getEname());
		}
	};

	

	
}
