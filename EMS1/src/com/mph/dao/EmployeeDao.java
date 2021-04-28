package com.mph.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.mph.model.Employee;
import com.mph.util.MyDBConnection;

public class EmployeeDao {
	
	Connection con;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;

	public void insertEmp(Employee emp) {
		
		con = MyDBConnection.getDBConnection();
		try {
			ps = con.prepareStatement("insert into mphemp values(?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, emp.getEid());
			ps.setString(2, emp.getEname().toUpperCase());
			ps.setDouble(3, emp.getSalary().getBasic());
			ps.setDouble(4, emp.getSalary().getDa());
			ps.setDouble(5, emp.getSalary().getHra());
			ps.setDouble(6, emp.getSalary().getPf());
			ps.setDouble(7, emp.getSalary().getNet());
			ps.setDouble(8, emp.getSalary().getGross());
			ps.setString(9, emp.getManagerDept().getDept().toUpperCase());
			
			int noofrows = ps.executeUpdate();
			System.out.println(noofrows + " Inserted Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void viewEmp() {
		
		con = MyDBConnection.getDBConnection();
		try {
			st = con.createStatement();

			rs = st.executeQuery("select * from mphemp where empid = 1");

			while (rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getDouble(3));
				System.out.println(rs.getDouble(4));
				System.out.println(rs.getDouble(5));
				System.out.println(rs.getDouble(6));
				System.out.println(rs.getDouble(7));
				System.out.println(rs.getDouble(8));
				System.out.println(rs.getString(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertUsingProcedure(Employee emp)
	{
		con = MyDBConnection.getDBConnection();
		try {
			CallableStatement cs = con.prepareCall("{call insertemppro(?,?,?,?,?,?,?,?,?)");
			cs.setInt(1, emp.getEid());
			cs.setString(2, emp.getEname().toUpperCase());
			cs.setDouble(3, emp.getSalary().getBasic());
			cs.setDouble(4, emp.getSalary().getDa());
			cs.setDouble(5, emp.getSalary().getHra());
			cs.setDouble(6, emp.getSalary().getPf());
			cs.setDouble(7, emp.getSalary().getNet());
			cs.setDouble(8, emp.getSalary().getGross());
			cs.setString(9, emp.getManagerDept().getDept().toUpperCase());
			cs.execute();
			System.out.println("Procedure successfully Executed and record inserted !!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rsmd()
	{
		con = MyDBConnection.getDBConnection();
		HashMap<Long, HashMap<String, Object>> hmap = new HashMap<Long, HashMap<String,Object>>();
		try {
			st = con.createStatement();
			rs =st.executeQuery("select * from mphemp");
			
			ResultSetMetaData md = rs.getMetaData();
			System.out.println(md.getColumnCount());
			while(rs.next())
			{
				HashMap<String, Object> row = new HashMap<String, Object>();
				for(int i=1 ; i<md.getColumnCount();i++)
				{
					row.put(md.getColumnName(i), rs.getObject(i));
					hmap.put(rs.getLong("empid"), row);
					
				}
			}
			System.out.println(hmap);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void type_forward_only_rs()
	{
		
		con = MyDBConnection.getDBConnection();
		try {
			ps = con.prepareStatement("select * from mphemp",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
			

			while (rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
			}
			System.out.println(rs.getType());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void type_scroll_insensitive_rs()
	{
		con = MyDBConnection.getDBConnection();
		try {
			ps = con.prepareStatement("select * from mphemp",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
			

			while (rs.next()) {
				
				System.out.println(rs.getString(2));
			}
			System.out.println(rs.getType());
			
			System.out.println("Move Cursor to Ist position");
			rs.first();
			System.out.println(rs.getString("empname"));
			
			System.out.println("Move Cursor to Last position");
			rs.last();
			System.out.println(rs.getString("empname"));
			
			System.out.println("Move Cursor to previous position");
			rs.previous();
			System.out.println(rs.getString("empname"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void type_scroll_sensitive_rs_insert()
	{
		con = MyDBConnection.getDBConnection();
		try {
			ps = con.prepareStatement("select empid, empname, basic_salary, da, hra, pf, net_salary, gross, manager_dept from mphemp where empid=11",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
			
//			System.out.println("Before Insert ==>");
//			while (rs.next()) {
//				System.out.println(rs.getInt(1));
//				System.out.println(rs.getString(2));
//			}
			
			
//			rs.moveToInsertRow();
//			rs.moveToCurrentRow();
//			rs.updateInt("empid",4);
//			rs.absolute(4);
			System.out.println("Before Insert ==>");
			while (rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
//				rs.updateInt("empid", 11);
				rs.updateString("empname", "SAHIL");
				rs.updateRow();
			}

			System.out.println("Updated ...");
			

			System.out.println(rs.getType());
			
//			rs.updateDouble("basic_salary", 12000.00);
//			rs.updateDouble("da", 120.00);
//			rs.updateDouble("hra", 120.00);
//			rs.updateDouble("pf", 1200.00);
//			rs.updateDouble("net_salary", 12350.00);
//			rs.updateDouble("gross", 15000.00);
//			rs.updateString("manager_dept", "IT");
//			rs.insertRow();
			
			
//			System.out.println("Updated ...");
	
			
			System.out.println(rs.getType());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void batchUpdate()
	{
		con = MyDBConnection.getDBConnection();
		
		try {
			st = con.createStatement();
			st.addBatch("update mphemp set empname ='MOHAN' where empid=1");
			st.addBatch("update mphemp set empid=13 where empname ='VIREN'");
			st.addBatch("update mphemp set empname ='SAURABH' where empid=3");
			st.addBatch("update mphemp set empname ='HEENAL' where empid=11");
			
			
			int[] count =st.executeBatch();
			for(int i=0;i<count.length ;i++)
			{
				System.out.println(i + "  "+ count [i] + " times. ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
