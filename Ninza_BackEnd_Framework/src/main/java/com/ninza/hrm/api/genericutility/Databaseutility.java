package com.ninza.hrm.api.genericutility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.mysql.cj.jdbc.Driver;

public class Databaseutility {
	
	static Connection conn;
	static ResultSet set;
	static FileUtility flib=new FileUtility();
	
	
	public void getConnectionToDataBase() throws IOException {
		Driver driverreff;
		try {
			driverreff=new Driver();
			DriverManager.registerDriver(driverreff);
			 conn=DriverManager.getConnection(flib.readDataFromPropertiesFile("DBURL")
					, flib.readDataFromPropertiesFile("DB_Username")
					,flib.readDataFromPropertiesFile("DB_Password"));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public static boolean executeQueryVerifyAndGetData(String query,int columnIndex,String expectedData) throws SQLException {
		boolean flag=false;
		
				 set=conn.createStatement().executeQuery(query);
				while(set.next()) {
					if(set.getString(columnIndex).equals(expectedData)) {
						flag=true;
						break;
					}
				}
				if(flag) {
					System.out.println(expectedData+"====>data verify in data Base table");
					return true;
				}else {
					System.out.println(expectedData+"====>data verify in data Base table");
					return false;
				}
			
		
	}
	public void closeDBConnection() throws SQLException {
		conn.close();
	}

}
