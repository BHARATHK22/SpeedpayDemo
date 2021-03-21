package com.test.demoproject.Practice;
import java.sql.*;
public class ReadDataFromDatabase {

	public static void main(String [] args) {
		  String dataBaseName="GmFinance";
		  
		  // client Database name will get from config.properties file
		  Connection connection;
		  ResultSet resultSet;
		  String creditAccount=null;
		 try{  
			 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
             connection= DriverManager.getConnection("jdbc:ucanaccess://E:\\Tables\\ClientDatabase.accdb");
             System.out.println("Connected Successfully"); 
             PreparedStatement preparedStatement=connection.prepareStatement("select top 1 * from "+dataBaseName+" order by rnd()");
             
             // Get Random Credit Account
             resultSet=preparedStatement.executeQuery();
             while(resultSet.next()){
                  creditAccount=resultSet.getString("Credit_Account");
                  String phone=resultSet.getString("Phone");
                 
                  System.out.println("creditAccount::"+creditAccount);
                  System.out.println("Phone Number ::"+phone);
                 
             }
             
             //Update phone number for a particular credit account
             String newPhoneNumber= "5555555555551";
             preparedStatement=connection.prepareStatement("update "+dataBaseName+" set Phone="+newPhoneNumber+" where Credit_Account="+creditAccount);
             preparedStatement.executeUpdate();
             System.out.println("data updated successfully\n");
             
             
             //Retrieve particular credit account
             preparedStatement=connection.prepareStatement("select * from "+dataBaseName+" where Credit_Account="+creditAccount);
             resultSet=preparedStatement.executeQuery();
             while(resultSet.next()){
            	 creditAccount=resultSet.getString("Credit_Account");
                 String phone=resultSet.getString("Phone");
                
                 System.out.println("creditAccount       ::"+creditAccount);
                 System.out.println("Updated Phone NUmber::"+phone);
             }
		
		 }catch(Exception e){
			e.printStackTrace();
		}
		  
	
	}
}
