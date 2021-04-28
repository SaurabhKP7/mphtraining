package com.mph.controller;

import java.util.ArrayList;
import java.util.List;

import com.mph.model.Employee;

public interface EmployeeInterface {

		public ArrayList<Employee> addEmployee();
		public void viewEmployee(ArrayList<Employee> list);
		public void sortEmployeeByName(ArrayList<Employee> list);
		public void sortEmployeeByFirstLetter(ArrayList<Employee> list);
		public void serializeEmployee(ArrayList<Employee> list);
		public void deserializeEmployee();
		void rsmd();
		void type_forward_only_rs();
		void type_scroll_insensitive_rs();
		void type_scroll_sensitive_rs_insert();
		public void batchUpdate();
}
