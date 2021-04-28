package com.mph.exceptions;

public class EmployeeNotFoundException extends Exception {

	public EmployeeNotFoundException() {
		// TODO Auto-generated constructor stub
		System.out.println("From the Employee Exception Constructor");
	}

	@Override
	public String toString() {
		return "EmployeeNotFoundException : Please enter a valid username and Password";
	}
	
	
}
