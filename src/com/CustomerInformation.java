package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerInformation {
	
		
		//A common method to connect to the DB
				private Connection connect(){ 
					
								Connection con = null; 
								
								try{ 
										Class.forName("com.mysql.jdbc.Driver"); 
		 
										//Provide the correct details: DBServer/DBName, username, password 
										con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogriddb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
												"root", "");
										
								} 
								catch (Exception e) {
									e.printStackTrace();
									} 
								
								return con; 
					} 
				
				
				//insert  customer information
				
				public String insertCustomerInformation( String Name, String Email, String Address, String NIC, String AccountNumber, String ContactNumber){ 
					
							String output = ""; 
							
							try
							{ 
								Connection con = connect(); 
								
								if (con == null) 
								{
									return "Error while connecting to the database for inserting."; 
									
								} 
								// create a prepared statement
								
								String query = " insert into customer (`Name`,`Email`,`Address`,`NIC`,`AccountNumber`,`ContactNumber`)"+" values (?, ?, ?, ?, ?, ?)"; 
								PreparedStatement preparedStmt = con.prepareStatement(query); 
								// binding values
								
								preparedStmt.setString(1, Name); 
								preparedStmt.setString(2, Email); 
								preparedStmt.setString(3, Address); 
								preparedStmt.setString(4, NIC);
								preparedStmt.setString(5, AccountNumber);
								preparedStmt.setString(6, ContactNumber);
								
								
								// execute the statement
		 
								preparedStmt.execute(); 
								con.close(); 
								
								String newCustomer = readCustomerInformation(); 
								output = "{\"status\":\"success\",\"data\":\""+newCustomer+"\"}"; 
							} 
							
							catch (Exception e) 
							{ 
								output = "{\"status\":\"error\", \"data\":\"Error while inserting the customer information.\"}"; 
								System.err.println(e.getMessage()); 
							} 
							return output; 
					} 
				
				
				//read customer information
				public String readCustomerInformation() 
				{ 
					String output = ""; 
					try
					{ 
						Connection con = connect(); 
				 if (con == null) 
				 { 
				 return "Error while connecting to the database for reading."; 
				 } 
				 // Prepare the html table to be displayed
				 output = "<table border=\"1\" class=\"table\"> <tr>"
						+ "<th>Name</th> <th>Email</th>"
				 		+ "<th>Address</th>"
				 		+ "<th>NIC</th>"
				 		+ "<th>AccountNumber</th>"
				 		+ "<th>ContactNumber</th>"
				 		+ "<th>Update</th>  <th>Remove</th></tr>";
				   
				
				 String query = "select * from customer"; 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query); 
				 
				 // iterate through the rows in the result set
				 while (rs.next()) 
				 { 
				 String CusID = Integer.toString(rs.getInt("CusID")); 
				 String Name = rs.getString("Name"); 
				 String Email = rs.getString("Email"); 
				 String Address = rs.getString("Address"); 
				 String NIC = rs.getString("NIC");
				 String AccountNumber = rs.getString("AccountNumber");
				 String ContactNumber= rs.getString("ContactNumber");
				  
				 
				 
				 
				 // Add into the html table
				 output += "<tr><td><input id='hideCustomerInformationIDUpdate' name='hideCustomerInformationIDUpdate' type='hidden' value='"+CusID+"'>"+Name+"</td>"; 
				 output += "<td>" + Email + "</td>"; 
				 output += "<td>" + Address + "</td>"; 
				 output += "<td>" + NIC + "</td>"; 
				 output += "<td>" + AccountNumber + "</td>";
				 output += "<td>" + ContactNumber + "</td>";
				
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
						 + "class='btnUpdate btn btn-secondary' data-cusid='" + CusID + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-cusid='" + CusID + "'></td></tr>"; 
				 
				 } 
				 con.close(); 
				 // Complete the html table
				 output += "</table>"; 
				 } 
				 
				catch (Exception e) 
				 { 
				 output = "Error while reading the customer informations."; 
				 System.err.println(e.getMessage()); 
				 } 
				return output; 
				}
				
				
				//update customer information
				
				public String updateCustomerInformation(String CusID,String Name, String Email, String Address, String NIC, String AccountNumber,String ContactNumber){ 
					
						String output = ""; 
						
						try{ 
								Connection con = connect(); 
								if (con == null){
									return "Error while connecting to the database for updating.";
									} 
								// create a prepared statement
								String query = "UPDATE customer SET Name=?,Email=?,Address=?,NIC=?,AccountNumber=?,ContactNumber=? WHERE CusID=?"; 
								PreparedStatement preparedStmt = con.prepareStatement(query); 
								// binding values
								preparedStmt.setString(1, Name); 
								preparedStmt.setString(2,Email);
								preparedStmt.setString(3,Address);
								preparedStmt.setString(4,NIC);
								preparedStmt.setString(5,AccountNumber);
								preparedStmt.setString(6,ContactNumber);
								preparedStmt.setInt(7, Integer.parseInt(CusID)); 
								
								// execute the statement
								preparedStmt.execute(); 
								con.close(); 
								String newCustomer = readCustomerInformation(); 
								output = "{\"status\":\"success\",\"data\":\""+newCustomer+"\"}"; 

						} 
						
						catch (Exception e){ 
							
							output = "{\"status\":\"error\",\"data\":\"Error while updating the customer information.\"}"; 

							System.err.println(e.getMessage()); 
							
						} 
						
						return output; 
				} 
				
				
				//delete customer information
				
				
				public String deleteCustomerInformation(String CusID){ 
					
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from customer where CusID=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(CusID)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newCustomer = readCustomerInformation(); 
						 output = "{\"status\":\"success\",\"data\":\""+newCustomer+"\"}"; 

					} 
					
					catch (Exception e){ 
						output = "{\"status\":\"error\",\"data\":\"Error while deleting the customer information.\"}";
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
				

	}




