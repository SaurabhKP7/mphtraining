package com.mph.model;

import java.io.Serializable;

public class Manager extends Employee implements Serializable {
	
	private String dept;
	
	public Manager(int emp_id, String ename) {
		
		super(emp_id, ename);
		
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Manager [dept=" + dept + "]";
	}
	
	
	
}
