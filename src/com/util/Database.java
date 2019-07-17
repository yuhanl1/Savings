package com.util;

import java.io.*;
import java.util.Properties;
import java.sql.*;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class Database {
	public static Properties myproperties = System.getProperties();
	public static ThreadLocal<Connection> mythreadlocal = new ThreadLocal<Connection>();
	
	static{
		InputStream path = Database.class.getResourceAsStream("jdbc.properties");
		try{
			myproperties.load(path);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static Connection ConnectToMySQL() throws Exception{
		Connection conn = mythreadlocal.get();
		if(conn == null){
			javax.sql.DataSource mydatasource = BasicDataSourceFactory.createDataSource(myproperties);
			conn = mydatasource.getConnection();
			mythreadlocal.set(conn);
		}
		return conn;
	}
	
	public static void StopConnToMySQL() throws SQLException{
		Connection conn = mythreadlocal.get();
		if(conn != null && !conn.isClosed()){
			conn.close();
		}
		mythreadlocal.set(null);
	}
}
