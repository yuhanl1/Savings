package com.dao;

import java.sql.*;
import java.util.Date;
import java.util.*;
import java.util.List;
import com.domain.*;
import com.util.Database;

public class SavingDB {
	public static int insertDevice(String device,String ip,int port) throws Exception{
		int i = Integer.parseInt(device.substring(1));//获取设备序号
		int number;
		if (device.substring(0,1)=="W"){//保持序号的连续性
			number=i;
		}
		else{
			number=100000000+i;
		}
		int count;//返回处理的行数
		Connection conn = Database.ConnectToMySQL();//调用方法建立连接
		String sql = "INSERT INTO basic (no,deviceno,ipaddress,portno,createtime) VALUE(?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);//设置？的变量值
		stmt.setInt(1, number);
		stmt.setString(2,device);
		stmt.setString(3,ip);
		stmt.setInt(4, port);
		stmt.setTimestamp(5, new Timestamp(new Date().getTime()));
		count=stmt.executeUpdate();//执行SQL语句
		Database.StopConnToMySQL();//调用方法断开连接
		return count;//返回已处理行数
	}
	public static int insertWaterSec(String device, float temperature, float velocity) throws Exception{
		int count=0;//返回处理的行数
		//int i = Integer.parseInt(device.substring(1));//获取设备序号
		Connection conn = Database.ConnectToMySQL();//调用方法建立连接
		String sql = "INSERT INTO water_sec (deviceno,temperature,velocity,time) VALUES(?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);//设置？的变量值
		stmt.setString(1, device);
		stmt.setFloat(2, temperature);
		stmt.setFloat(3,velocity);
		stmt.setTimestamp(4, new Timestamp(new Date().getTime()));
		count=stmt.executeUpdate();//执行SQL语句
		Database.StopConnToMySQL();//调用方法断开连接
		return count;
	}
	public static int insertWaterConsumption(String device, float volume, Timestamp begin, Timestamp end) throws Exception{
		int count=0;
		Connection conn = Database.ConnectToMySQL();
		String sql = "INSERT INTO water_consumption(deviceno,volume,time_begin,time_end) VALUES(?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, device);
		stmt.setFloat(2, volume);
		stmt.setTimestamp(3, begin);
		stmt.setTimestamp(4, end);
		count = stmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int insertEleSec(String device, float current, float voltage) throws Exception{
		int count=0;
		Connection conn = Database.ConnectToMySQL();
		String sql = "INSERT INTO ele_sec(deviceno,current,voltage,time) VALUES(?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, device);
		stmt.setFloat(2, current);
		stmt.setFloat(3, voltage);
		stmt.setTimestamp(4, new Timestamp(new Date().getTime()));
		count = stmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int insertEleMin(String device, float energy, Timestamp begin, Timestamp end) throws Exception{
		int count=0;
		Connection conn = Database.ConnectToMySQL();
		String sql = "INSERT INTO ele_min(deviceno,energy,time_end,time_begin) VALUES(?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, device);
		stmt.setFloat(2, energy);
		stmt.setTimestamp(3, end);
		stmt.setTimestamp(4, begin);
		count = stmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int insertEleHour(String device, float energy, Timestamp begin, Timestamp end) throws Exception{
		int count=0;
		Connection conn = Database.ConnectToMySQL();
		String sql = "INSERT INTO ele_hour(deviceno,time_begin,energy,time_end) VALUES(?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, device);
		stmt.setTimestamp(2, begin);
		stmt.setFloat(3, energy);
		stmt.setTimestamp(4, end);
		count = stmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int insertEleDay(String device, float energy, Timestamp begin, Timestamp end) throws Exception{
		int count=0;
		Connection conn = Database.ConnectToMySQL();
		String sql = "INSERT INTO ele_day(deviceno,time_begin,energy,time_end) VALUES(?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, device);
		stmt.setTimestamp(2, begin);
		stmt.setFloat(3, energy);
		stmt.setTimestamp(4, end);
		count = stmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int insertEleMonth(String device, float energy, Timestamp begin, Timestamp end) throws Exception{
		int count=0;
		Connection conn = Database.ConnectToMySQL();
		String sql = "INSERT INTO ele_month(deviceno,time_begin,energy,time_end) VALUES(?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, device);
		stmt.setTimestamp(2, begin);
		stmt.setFloat(3, energy);
		stmt.setTimestamp(4, end);
		count = stmt.executeUpdate();
		Database.StopConnToMySQL();		
		return count;
	}

	
	public static String getIPAddress(String device) throws Exception{
		String IP="0.0.0.0";
		Connection conn = Database.ConnectToMySQL();
		String sql = "SELECT * FROM basic WHERE deviceno= ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		ResultSet rs = pstmt.executeQuery();
		if(rs != null){
			IP = rs.getString("ipaddress");
		}
		rs.close();
		Database.StopConnToMySQL();
		return IP;
	}
	public static int getPortNo(String device) throws Exception{
		int portNo = 8080;
		Connection conn = Database.ConnectToMySQL();
		String sql = "SELECT * FROM basic WHERE deviceno= ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		ResultSet rs = pstmt.executeQuery();
		if(rs != null){
			portNo = rs.getInt("portno");
		}
		rs.close();
		Database.StopConnToMySQL();
		return portNo;
	}
	public static float getTemperature(String device, Timestamp time) throws Exception{
		float temperature = 26.0f;
		Connection conn = Database.ConnectToMySQL();
		String sql = "SELECT * FROM water_sec WHERE deviceno= ?  AND time = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, time);
		ResultSet rs = pstmt.executeQuery();
		if(rs != null){
			temperature = rs.getFloat("temperature");
		}
		rs.close();
		Database.StopConnToMySQL();
		return temperature;
	}
	public static ResultSet getTemperatures(String device, Timestamp begin, Timestamp end) throws Exception{
		Connection conn = Database.ConnectToMySQL();
		String sql = "SELECT temperature FROM water_sec WHERE deviceno= ?  AND time BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3,end);
		ResultSet rs = pstmt.executeQuery();
		Database.StopConnToMySQL();
		return rs;
	}
	public static float getVelocity(String device, Timestamp time) throws Exception{
		float velocity = 1.0f;
		Connection conn = Database.ConnectToMySQL();
		String sql = "SELECT * FROM water_sec WHERE deviceno=? AND time = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, time);
		ResultSet rs = pstmt.executeQuery();
		if(rs != null){
			velocity = rs.getFloat("velocity");
		}
		rs.close();
		Database.StopConnToMySQL();
		return velocity;
	}
	public static ResultSet getVelocities(String device, Timestamp begin, Timestamp end) throws Exception{
		Connection conn = Database.ConnectToMySQL();
		String sql = "SELECT velocity FROM water_sec WHERE deviceno= ?  AND time BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3,end);
		ResultSet rs = pstmt.executeQuery();
		Database.StopConnToMySQL();
		return rs;
	}
	public static float getVolume(String device, Timestamp begin, Timestamp end) throws Exception{
		float volume = 0.0f;
		Connection conn = Database.ConnectToMySQL();
		String sql = "SELECT * FROM water_consumption WHERE deviceno=? AND time_begin =? AND time_end =?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3, end);
		ResultSet rs = pstmt.executeQuery();
		if(rs != null){
			volume = rs.getFloat("volume");
		}
		rs.close();
		Database.StopConnToMySQL();
		return volume;
	}
	public static ResultSet getVolumes(String device, Timestamp begin, Timestamp end) throws Exception{
		Connection conn = Database.ConnectToMySQL();
		String sql = "SELECT volume FROM water_consumption WHERE deviceno= ?  AND time_begin BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3,end);
		ResultSet rs = pstmt.executeQuery();
		Database.StopConnToMySQL();
		return rs;
	}
	public static List<SavingEleSec> getEnergy(String device, Timestamp time) throws Exception{
		List<SavingEleSec> list = new ArrayList<SavingEleSec>();
		float current=0.01f;
		float voltage=220.0f;
		String sql = "SELECT * FROM ele_sec WHERE deviceno= ? AND time = ?";
		//获取电压和电流
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2,time);
		ResultSet rs = pstmt.executeQuery();
		if(rs != null){
			current = rs.getFloat("current");
			voltage = rs.getFloat("voltage");
		}
		rs.close();
		Database.StopConnToMySQL();
		SavingEleSec ses = new SavingEleSec();
		ses.setCurrent(current);
		ses.setVoltage(voltage);
		list.add(ses);
		return list;
	}
	public static float getEnergiesFromEleSec(String device, Timestamp begin,Timestamp end) throws Exception{
		//返回的是计算好的从begin到end这段时间内设备device的电量
		float i=0.001f;
		float u=220.0f;
		float w=0.0f;
		String sql = "SELECT current,voltage FROM ele_sec WHERE deviceno= ? AND time BETWEEN ? AND ?";
		//获取电压和电流
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2,begin);
		pstmt.setTimestamp(3,end);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			i = rs.getFloat("current");
			u = rs.getFloat("voltage");
			w = w + u * i;
		}
		rs.close();
		Database.StopConnToMySQL();
		return w;
	}
	public static float getEnergyMin(String device, Timestamp begin, Timestamp end) throws Exception{
		float energy = 0.0f;
		String sql="SELECT * FROM ele_min WHERE deviceno=? AND time_begin =? AND time_end =?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3, end);
		ResultSet rs = pstmt.executeQuery();
		if(rs != null){
			energy = rs.getFloat("energy");
		}
		rs.close();
		Database.StopConnToMySQL();
		return energy;
	} 
	public static ResultSet getEnergiesMin(String device, Timestamp begin, Timestamp end) throws Exception{
		Connection conn = Database.ConnectToMySQL();
		String sql = "SELECT energy FROM ele_min WHERE deviceno= ?  AND time_begin BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3,end);
		ResultSet rs = pstmt.executeQuery();
		Database.StopConnToMySQL();
		return rs;
	}
	public static float getEnergyDay(String device, Timestamp begin, Timestamp end) throws Exception{
		float energy = 0.0f;
		String sql="SELECT * FROM ele_day WHERE deviceno=? AND time_begin =? AND time_end =?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3, end);
		ResultSet rs = pstmt.executeQuery();
		if(rs != null){
			energy = rs.getFloat("energy");
		}
		rs.close();
		Database.StopConnToMySQL();
		return energy;
	}
	public static ResultSet getEnergiesDay(String device, Timestamp begin, Timestamp end) throws Exception{
		Connection conn = Database.ConnectToMySQL();
		String sql = "SELECT energy FROM ele_day WHERE deviceno= ?  AND time_begin BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3,end);
		ResultSet rs = pstmt.executeQuery();
		Database.StopConnToMySQL();
		return rs;
	}
	public static float getEnergyHour(String device, Timestamp begin, Timestamp end) throws Exception{
		float energy = 0.0f;
		String sql="SELECT * FROM ele_hour WHERE deviceno=? AND time_begin =? AND time_end =?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3, end);
		ResultSet rs = pstmt.executeQuery();
		if(rs != null){
			energy = rs.getFloat("energy");
		}
		rs.close();
		Database.StopConnToMySQL();
		return energy;
	}
	public static ResultSet getEnergiesHour(String device, Timestamp begin, Timestamp end) throws Exception{
		Connection conn = Database.ConnectToMySQL();
		String sql = "SELECT energy FROM ele_hour WHERE deviceno= ?  AND time_begin BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3,end);
		ResultSet rs = pstmt.executeQuery();
		Database.StopConnToMySQL();
		return rs;
	}
	
	public static float getEnergyMonth(String device, Timestamp begin, Timestamp end) throws Exception{
		float energy = 0.0f;
		String sql="SELECT * FROM ele_month WHERE deviceno=? AND time_begin =? AND time_end =?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3, end);
		ResultSet rs = pstmt.executeQuery();
		if(rs != null){
			energy = rs.getFloat("energy");
		}
		rs.close();
		Database.StopConnToMySQL();
		return energy;
	}
	public static ResultSet getEnergiesMonth(String device, Timestamp begin, Timestamp end) throws Exception{
		Connection conn = Database.ConnectToMySQL();
		String sql = "SELECT energy FROM ele_month WHERE deviceno= ?  AND time_begin BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3,end);
		ResultSet rs = pstmt.executeQuery();
		Database.StopConnToMySQL();
		return rs;
	}
	
	public static int deleteItemFromBasic(String device) throws Exception{
		int count = 0;
		String sql="DELETE FROM basic WHERE deviceno =?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		count = pstmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int deleteItemFromWaterSec(String device,Timestamp time) throws Exception{
		int count = 0;
		String sql="DELETE FROM water_sec WHERE deviceno = ? AND time = ?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, time);
		count = pstmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int deleteItemsFromWaterSec(String device,Timestamp begin,Timestamp end) throws Exception{
		int count = 0;
		String sql="DELETE FROM water_sec WHERE deviceno = ? AND time BETWEEN ? AND ?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3, end);
		count = pstmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int deleteItemFromWaterConsumption(String device,Timestamp begin,Timestamp end) throws Exception{
		int count = 0;
		String sql="DELETE FROM water_consumption WHERE deviceno = ? AND time_begin = ? AND time_end=?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3, end);
		count = pstmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int deleteItemsFromWaterConsumption(String device,Timestamp begin,Timestamp end) throws Exception{
		int count = 0;
		String sql="DELETE FROM water_consumption WHERE deviceno = ? AND time_begin BETWEEN ? AND ?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3, end);
		count = pstmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int deleteItemFromEleSec(String device,Timestamp time) throws Exception{
		int count = 0;
		String sql="DELETE FROM ele_sec WHERE deviceno = ? AND time = ?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, time);
		count = pstmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int deleteItemsFromEleSec(String device,Timestamp begin, Timestamp end) throws Exception{
		int count = 0;
		String sql="DELETE FROM ele_sec WHERE deviceno = ? AND time BETWEEN ? AND ?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3, end);
		count = pstmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int deleteItemFromEleMin(String device,Timestamp begin, Timestamp end) throws Exception{
		int count = 0;
		String sql="DELETE FROM ele_min WHERE deviceno = ? AND time_begin = ? AND time_end = ?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3, end);
		count = pstmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int deleteItemsFromEleMin(String device,Timestamp begin, Timestamp end) throws Exception{
		int count = 0;
		String sql="DELETE FROM ele_min WHERE deviceno = ? AND time_begin BETWEEN ? AND ?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3, end);
		count = pstmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int deleteItemFromEleHour(String device,Timestamp begin, Timestamp end) throws Exception{
		int count = 0;
		String sql="DELETE FROM ele_hour WHERE deviceno = ? AND time_begin = ? AND time_end = ?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3, end);
		count = pstmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int deleteItemsFromEleHour(String device,Timestamp begin, Timestamp end) throws Exception{
		int count = 0;
		String sql="DELETE FROM ele_hour WHERE deviceno = ? AND time_begin BETWEEN ? AND ?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3, end);
		count = pstmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int deleteItemFromEleDay(String device,Timestamp begin, Timestamp end) throws Exception{
		int count = 0;
		String sql="DELETE FROM ele_day WHERE deviceno = ? AND time_begin = ? AND time_end = ?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3, end);
		count = pstmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int deleteItemsFromEleDay(String device,Timestamp begin, Timestamp end) throws Exception{
		int count = 0;
		String sql="DELETE FROM ele_day WHERE deviceno = ? AND time_begin BETWEEN ? AND ?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3, end);
		count = pstmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int deleteItemFromEleMonth(String device,Timestamp begin, Timestamp end) throws Exception{
		int count = 0;
		String sql="DELETE FROM ele_month WHERE deviceno = ? AND time_begin = ? AND time_end = ?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3, end);
		count = pstmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}
	public static int deleteItemsFromEleMonth(String device,Timestamp begin, Timestamp end) throws Exception{
		int count = 0;
		String sql="DELETE FROM ele_month WHERE deviceno = ? AND time_begin BETWEEN ? AND ?";
		Connection conn = Database.ConnectToMySQL();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, device);
		pstmt.setTimestamp(2, begin);
		pstmt.setTimestamp(3, end);
		count = pstmt.executeUpdate();
		Database.StopConnToMySQL();
		return count;
	}

}
