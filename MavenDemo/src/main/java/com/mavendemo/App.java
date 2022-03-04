package com.mavendemo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class App 
{
	//commit 1
	//commit 2
	//commit 3
	static  Connection con = null;	
	public static void main( String[] args ) throws SQLException, ClassNotFoundException 
    {
        System.out.println( "Hello AGS Health...!" );
        con = App.databaseConnection();
        Statement stmt = null;
        stmt = con.createStatement();
        //INSERT
       //stmt.executeUpdate("INSERT INTO test.employee VALUES(3,'Kavita',45000,'Pshycologist');");
       //System.out.println("Record Inserted"); 
         
        //VIEW
         stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM test.employee");
     //   System.out.println("ID\t NAME\t SALARY\t DESIGNATION");
        
        ResultSetMetaData metadata = rs.getMetaData();
        for (int i=1; i<=metadata.getColumnCount(); i++) 
        {    
          String columnName = metadata.getColumnName(i);
          System.out.print(columnName +"\t ");
        }
        System.out.println();
        
        while(rs.next())
        {
        	System.out.println(rs.getString(1)+"\t "+rs.getString(2)+"\t "+rs.getString(3)+ "\t "+rs.getString(4));	
        }
        System.out.println( "Successfully Printed." );
        
        //UPDATE
        stmt.executeUpdate("UPDATE test.employee set name = 'Heena', salary=47000, designation = 'cook' where id = 4");
        System.out.println("Recored Updated");
        
        
        // DELETE 
   /*     stmt = con.createStatement();
        stmt.executeUpdate("DELETE FROM test.employee where id ="+3);
        System.out.println("Record Deleted.");   */
    }
    
    public static Connection databaseConnection()throws SQLException, ClassNotFoundException
    {
    	Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","P@ssw0rd@123");
        System.out.println("Connection Established :- "+con);
		return con;
    }
}
