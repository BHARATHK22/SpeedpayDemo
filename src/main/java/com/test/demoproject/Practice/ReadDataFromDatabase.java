package com.test.demoproject.Practice;
import java.sql.*;
public class ReadDataFromDatabase {

	public static void main(String [] args) {
		  String dataBaseName="GmFinance";
	 
		 try{  
			 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
             Connection connection= DriverManager.getConnection("jdbc:ucanaccess://E:\\Tables\\ClientDatabase.accdb");
             System.out.println("Connected Successfully"); 
             PreparedStatement preparedStatement=connection.prepareStatement("select top 1 * from "+dataBaseName+" order by rnd()");
             
             ResultSet resultSet=preparedStatement.executeQuery();
             while(resultSet.next()){
                  String creditAccount=resultSet.getString("Credit_Account");
                  String phone=resultSet.getString("Phone");
                 
                  System.out.println("creditAccount::"+creditAccount);
                  System.out.println("creditAccount::"+phone);
             }
		  
		}catch(Exception ee){
			System.out.println(ee);}  
		  
	}

}
