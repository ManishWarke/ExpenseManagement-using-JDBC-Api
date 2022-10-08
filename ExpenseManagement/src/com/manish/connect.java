package com.manish;

import java.util.Scanner;
import java.io.Reader;
import java.sql.*;

public class connect {
	
	
	public static void main(String[] args) {
		Connection con=null;
		Statement stmt=null;
		PreparedStatement pstmt=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver class registered");
			
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
		System.out.println("Established the connection");
		stmt=con.createStatement();	
		Scanner sc=new Scanner(System.in);
		int option;
		String Username=null;
		String Password=null;
		int a=1;
		{
			System.out.println("welcome to expenceManager");
			System.out.println("please select one of the following");
			System.out.println("1:create a new account");
			System.out.println("2:login to your account");
		}
		while(a>=1) {
			
			
			option=sc.nextInt();
			switch (option) {
			
			case 1: 
				System.out.println("Enter unique username");
				Username=sc.next();
				System.out.println("Enter unique password");
				Password=sc.next();
				
				if (Username != " " || Password != " ") {
				
				//using placeholder concept i am inserting values in query
				pstmt=con.prepareStatement("insert into accountdetails.login values(?,?)");
				 pstmt.setString(1, Username);
				 pstmt.setString(2, Password);
				 pstmt.executeUpdate();
				 //System.out.println("executed the query");
				 System.out.println("account got created");
				 System.out.println("press 2 for login ");
				 
				break;
				
				
			}else {
				System.out.println("All Field Required!");
				}
				break;
				
			case 2:
				System.out.println("Enter username ");
				String Username1 = sc.next();
				System.out.println("Enter password ");
				String Password1 = sc.next();
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("Select * from accountdetails.login");
				rs.next();
				
				a++;
				
				
				if(rs.next()) {
					
					String name=rs.getString("username");
					String pass=rs.getString("password");
					
					System.out.println(name+" "+pass);
					if (name.equals(Username1) || pass.equals(Password1) ) {
						
						System.out.println("logged in");
						
						
						
					}else {
						System.out.println("invalid credebtials");
						break;
						}

				}
				while(true) {
					
				
				System.out.println("select the following tasks to perform");
				System.out.println("1:add expenses");
				System.out.println("2:aupdate expenses");
				System.out.println("3:remove expenses");
				System.out.println("4:calculate total expenses\n");
				option=sc.nextInt();
				switch(option) {
				case 1:
					System.out.println("Enter ExpenseId");
					int expenseId=sc.nextInt();
					System.out.println("Enter ExpenseName");
					String expensename=sc.next();
					System.out.println("Enter Expenseamount");
					double amount=sc.nextDouble();
					
					//String insert="insert into accountdetails.expenses where expenseId='"+expenseId+"'and expenseDiscription='"+expensename+"'and expenseAmount='"+amount+"' and duedate='"+date+"'";

					pstmt=con.prepareStatement("insert into accountdetails.expenses values(?,?,?)");
					 pstmt.setInt(1, expenseId);
					 pstmt.setString(2, expensename);
					 pstmt.setDouble(3, amount);
					
					 pstmt.executeUpdate();
					 System.out.println("task saved");
					 break;
				case 2:
					System.out.println("Enter the id at which you want to update your expense");
					int id=sc.nextInt();
					
					System.out.println("Enter ExpenseName");
					expensename=sc.next();
					System.out.println("Enter Expenseamount");
					amount=sc.nextDouble();
					
					pstmt=con.prepareStatement("update accountdetails.expenses set expenseDiscription=?, expenseAmount=? where expenseId=? ");
					pstmt.setInt(3, id);
					 pstmt.setString(1, expensename);
					 pstmt.setDouble(2, amount);
					 pstmt.executeUpdate();
					 System.out.println("expense updated");
					 break;
				case 3:
					System.out.println("Enter the ExpenseId to remove following expense");
					id=sc.nextInt();
					pstmt.executeUpdate("delete from accountdetails.expenses where expenseId="+id+"");
					 System.out.println("expense removed");
					 break;
				case 4:
					
					pstmt=con.prepareStatement("select sum(expenseAmount) from accountdetails.expenses");
					rs=pstmt.executeQuery();
					while(rs.next()) {
						
						String total=rs.getString("sum(expenseAmount)");
						
						
						System.out.println(total);
						
					
					}
					break;
				}
					
				}
			}
							
			}
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			if(stmt!=null) {
				
				try {
					stmt.close();
				} catch (SQLException e) {
					
					
					e.printStackTrace();
				}
				System.out.println("closed the statement  ");
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				System.out.println("closed the connection ");
			}


	}
		
		
		
			

					
		
				


				}
			}
