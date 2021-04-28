package com.mph.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

import com.mph.dao.EmployeeDao;
import com.mph.model.Employee;
import com.mph.model.Manager;
import com.mph.model.Salary;

public class EmployeeController implements EmployeeInterface{
	
//	Employee[] emparr; 
	Employee emp;
	ArrayList<Employee> empList = new ArrayList<Employee>();
	Salary sal;
	Manager mn;
	
	EmployeeDao empdao = new EmployeeDao();
	
	public ArrayList<Employee> addEmployee() {
		
		Scanner sc = new Scanner(System.in);
			
		
			
			sal = new Salary();
			System.out.println("Enter Emp ID : ");
			int eno = sc.nextInt();
			System.out.println("Enter Emp Name : ");
			String ename = sc.next();
			emp =  new Employee(eno, ename);
			
			sal = new Salary();
			
			System.out.println("Is the Employee a Manager?(Y or y for YES)");
			String mnStatus = sc.next();
			
			if(mnStatus.equals("Y") || mnStatus.equals("y")) {
				mn = new Manager(eno, ename);
				System.out.println("Enter Manager Department : ");
				String dept = sc.next();
				mn.setDept(dept);
				emp.setManager(mn);
			}
			
			
			
			System.out.println("Enter Basic Salary : ");
			int basic = sc.nextInt();
			sal.setBasic(basic);
			sal.setHra(basic);
			sal.setDa(basic);
			sal.setPf(basic);
			
			sal.setGross(basic, sal.getHra(), sal.getDa());
			sal.setNet(sal.getGross(), sal.getPf());
			emp.setSalary(sal);
			
//			empList.add(emp);
//			empdao.insertEmp(emp);
			empdao.insertUsingProcedure(emp);
			System.out.println("Employee : "+eno+" Successfully Added");
			
			
			
		return empList;
	}
	
	public void viewEmployee(ArrayList<Employee> list) {
		
//		Iterator<Employee> i= list.iterator();
//
//		while(i.hasNext())
//		{
//			System.out.println(i.next());
//		}
		
//		list.forEach(li->System.out.println(li));
		empdao.viewEmp();
		
	}

	@Override
	public void sortEmployeeByName(ArrayList<Employee> list) {
		
//		Collections.sort(list, Employee.nameComparator);
//		viewEmployee(list);
//		Predicate<Employee> st = emp -> (emp.getEname().startsWith("A"));
		empList.stream()
		.sorted(Comparator.comparing(Employee::getEname))
		.forEach(System.out::println);
		
	}
	
	public void sortEmployeeByFirstLetter(ArrayList<Employee> list) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Letter for sorting : ");
		String s = sc.next();
		Predicate<Employee> st = emp -> (emp.getEname().startsWith(s.toLowerCase()) || emp.getEname().startsWith(s.toUpperCase()));
		
		empList.stream().filter(st)
		.sorted(Comparator.comparing(Employee::getEname))
		.forEach(System.out::println);
	}

	@Override
	public void serializeEmployee(ArrayList<Employee> list) {
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		

		try {
			fos = new FileOutputStream("myEmployeeData.txt");
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(list);
			System.out.println("Employee Data Successfully serialized..............");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fos.close();
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deserializeEmployee() {
		
		ArrayList<Employee> list = new ArrayList<Employee>();
		try (FileInputStream fis = new FileInputStream("myEmployeeData.txt");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			list = (ArrayList) ois.readObject();
			fis.close();
			ois.close();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("------------Deserialized Data--------------");
		for(Employee i:list) {
			
			System.out.println("Employee ID : " + i.getEid());
			System.out.println("Employee Name : " + i.getEname());
			System.out.println("Employee Salary : " + i.getSalary());
			System.out.println("Manager Dept : " + i.getManagerDept());
			
		}
		
	}
	
	public void rsmd()
	{
		empdao.rsmd();
	}
	
	@Override
	public void type_forward_only_rs() {
		empdao.type_forward_only_rs();
		
	}

	
	@Override
	public void type_scroll_insensitive_rs() {
		empdao.type_scroll_insensitive_rs();
		
	}

	
	@Override
	public void type_scroll_sensitive_rs_insert() {
		empdao.type_scroll_sensitive_rs_insert();
		
	}

	@Override
	public void batchUpdate() {
		empdao.batchUpdate();
		
	}
	
	
	
	
	
}
