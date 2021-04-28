package com.mph.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiPredicate;

import com.mph.controller.EmployeeController;
import com.mph.controller.EmployeeInterface;
import com.mph.exceptions.EmployeeNotFoundException;
import com.mph.model.Employee;

public class MainClass {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EmployeeInterface ec =new EmployeeController();
		
		ArrayList<Employee> list = null;
		
		Scanner sc = new Scanner(System.in);
		
		String con = null;
		
		String user = "saurabh";
		String pass = "password";
		
		System.out.println("Enter USERNAME : ");
		String us = sc.next();
		System.out.println("Enter PASSWORD : ");
		String pa = sc.next();
		
		

		try {
			
			BiPredicate<String, String> pred = (u1,p1)->u1.equals(user)&&p1.equals(pass);
			if(pred.test(us, pa))
			{
				System.out.println("Loading.......");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				do {
					System.out.println("Enter Your Choice : ");
					System.out.println("1. ADD EMPLOYEE");
					System.out.println("2. VIEW EMPLOYEE");
					System.out.println("3. SORT EMPLOYEE BY NAME");
					System.out.println("4. SORT EMPLOYEE BY FIRST LETTER");
					System.out.println("5. SERIALIZE EMPLOYEE DATA");
					System.out.println("6. DESERIALIZE");
					System.out.println("7. ResultSet Metadata");
					System.out.println("8. type_forward_only_rs");
					System.out.println("9. type_scroll_insensitive_rs");
					System.out.println("10. type_scroll_sensitive_rs_update");
					System.out.println("11. batchUpdate");
					int choice = sc.nextInt();
					
					switch(choice) {
					
						case 1 : {
							list = ec.addEmployee();
							break;
						}
						case 2 : {
							ec.viewEmployee(list);
							break;
						}
						case 3 : {
							ec.sortEmployeeByName(list);
							break;
						}
						case 4 : {
							ec.sortEmployeeByFirstLetter(list);
							break;
						}
						case 5 :{
							ec.serializeEmployee(list);
							break;
						}
						case 6 : {
							ec.deserializeEmployee();
							break;
						}
						case 7 : {
							ec.rsmd();
							break;
						}
						case 8 : {
							ec.type_forward_only_rs();
							break;
						}
						case 9 : {
							ec.type_scroll_insensitive_rs();
							break;
						}
						case 10 : {
							ec.type_scroll_sensitive_rs_insert();
							break;
						}
						case 11 : {
							ec.batchUpdate();
							break;
						}
						
						default : {
							System.out.println("Entered Wrong Choice!!!!");
							System.exit(0);
						}
					}
					
					System.out.println("Do you want to continue?(Y or y for YES)");
					con = sc.next();
					
				}while(con.equals("Y") || con.equals("y"));

			}
			else {
				
				throw new EmployeeNotFoundException();
			}
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			main(args);
			
		}finally {
			System.out.println("Run Again!!!!");
		}
		
		
		
		
				
		System.out.println("Thank You BYE BYE!!!!");
		System.exit(0);
		
	}

}
