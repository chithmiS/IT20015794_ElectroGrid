package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class Payment {
	
	//Method to connect to the DB
	private Connection connect(){ 
		
					Connection con = null; 
					
					try{ 
							Class.forName("com.mysql.cj.jdbc.Driver"); 

							
							con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payment", "root", ""); 
						} 
					catch (Exception e) {
						e.printStackTrace();
						} 
					
					return con; 
		} 
	
	//Insert payment method
	public String insertPayment(String AccNumber, String totalAmount, String payDate, String cardType, String cardNumber, String cvn)
	{ 
		
				String output = ""; 
				
				try
				{ 
					Connection con = connect();
					
					if (con == null) 
					{
						return "Error while connecting to the database for inserting."; 
						
					} 
					
					// create a prepared statement
					
					String query = " insert into payments(`payID`,`AccNumber`,`totalAmount`,`payDate`,`cardType`,`cardNumber`,`cvn`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)"; 
					
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					
					// binding values
					
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, AccNumber);
					preparedStmt.setDouble(3, Double.parseDouble(totalAmount));
					preparedStmt.setString(4, payDate);
					preparedStmt.setString(5, cardType);
					preparedStmt.setString(6, cardNumber);
					preparedStmt.setString(7, cvn);
					 
					// execute the statement

					preparedStmt.execute(); 
					con.close(); 
					
					String newPayments = readPayments(); 
					output = "{\"status\":\"success\",\"data\":\""+newPayments+"\"}"; 
				} 
				
				catch (Exception e) 
				{ 
					output = "{\"status\":\"error\", \"data\":\"Error while inserting the payment details.\"}"; 
					System.err.println(e.getMessage()); 
				} 
				return output; 
		} 
	
	//Read payments method
	public String readPayments() 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{ 
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the HTML table to be displayed
	 
			 output = "<table border='1'class='table'><tr><th>Payment ID</th><th>Account Number</th>"
					 +"<th>Total Amount</th>" 
					 +"<th>Payment Date</th>"
					 +"<th>Card Type</th>"
					 +"<th>Card Number</th>" 
					 +"<th>CVN</th>"
					 +"<th>Update</th><th>Remove</th></tr>";
	
			 String query = "select * from payments"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set
	 
			 while (rs.next()) 
			 { 
				 
				 String payID = Integer.toString(rs.getInt("payID"));
				 String AccNumber = rs.getString("AccNumber");
				 String totalAmount = Double.toString(rs.getDouble("totalAmount"));
				 String payDate = rs.getString("payDate");
				 String cardType = rs.getString("cardType");
				 String cardNumber = rs.getString("cardNumber");
				 String cvn = rs.getString("cvn");
			 
			// Add into the HTML table
		 
			 output += "<tr><td>" + payID + "</td>";
			 output += "<td>" + AccNumber + "</td>";
			 output += "<td>" + totalAmount + "</td>";
			 output += "<td>" + payDate + "</td>";
			 output += "<td>" + cardType + "</td>";
			 output += "<td>" + cardNumber + "</td>";
			 output += "<td>" + cvn + "</td>";
	 

	 
			 // buttons
	
	 
		    output += "<td><input name='btnUpdate' type='button' value='Update'"
		 			+ "class='btnUpdate btn btn-secondary' data-payid='" + payID + "'></td>"
		 			+ "<td><input name='btnRemove' type='button' value='Remove' "
					+ "class='btnRemove btn btn-danger' data-payid='" + payID + "'></td></tr>"; 
	 
			 } 
	 
			 con.close(); 
	 
			 // Complete the HTML table
	 
				 output += "</table>"; 
				 } 
				 
		catch (Exception e) 
		 { 
		 output = "Error while reading the payment details."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}
	
	//Update payment details method
	public String updatePayment(String payID, String AccNumber, String totalAmount, String payDate, String cardType, String cardNumber, String cvn)
	{ 
			
				String output = ""; 
				
				try{ 
						Connection con = connect(); 
						if (con == null){
							return "Error while connecting to the database for updating.";
							} 
						
						// create a prepared statement
						
						String query = "UPDATE payments SET AccNumber=?,totalAmount=?,payDate=?,cardType=?,cardNumber=?,cvn=? WHERE payID=?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						// binding values
						
						 preparedStmt.setString(1, AccNumber);
						 preparedStmt.setDouble(2, Double.parseDouble(totalAmount));
						 preparedStmt.setString(3, payDate);
						 preparedStmt.setString(4, cardType);
						 preparedStmt.setString(5, cardNumber);
						 preparedStmt.setString(6, cvn);
						 preparedStmt.setInt(7, Integer.parseInt(payID));
						
						// execute the statement
						 
						preparedStmt.execute(); 
						con.close(); 
						String newPayments = readPayments(); 
						output = "{\"status\":\"success\",\"data\":\""+newPayments+"\"}"; 

				} 
				
				catch (Exception e){ 
					
					output = "{\"status\":\"error\",\"data\":\"Error while updating the payment details.\"}"; 

					System.err.println(e.getMessage()); 
					
				} 
				
				return output; 
		} 
	
	//Delete payment method
	public String deletePayment(String payID)
	{ 
			
				String output = ""; 
				
				try{ 
					Connection con = connect(); 
					
					if (con == null){
						return "Error while connecting to the database for deleting."; 
						} 
					
					// create a prepared statement
					
					String query = "delete from payments where payID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					
					// binding values
					
					preparedStmt.setInt(1, Integer.parseInt(payID));
					
					// execute the statement
					
					preparedStmt.execute(); 
					con.close(); 
					String newPayments = readPayments(); 
					 output = "{\"status\":\"success\",\"data\":\""+newPayments+"\"}"; 

				} 
				
				catch (Exception e){ 
					output = "{\"status\":\"error\",\"data\":\"Error while deleting the payment Details.\"}";
					System.err.println(e.getMessage()); 
				} 
				return output; 
		} 
		
}
