package com;

import java.sql.Connection;
import java.sql.DriverManager;

public class Payment {
	
	//Method to connect to the DB
	private Connection connect(){ 
		
					Connection con = null; 
					
					try{ 
							Class.forName("com.mysql.cj.jdbc.Driver"); 

							//Provide the correct details: DBServer/DBName, user name, password 
							con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payment", "root", ""); 
						} 
					catch (Exception e) {
						e.printStackTrace();
						} 
					
					return con; 
		} 

}
