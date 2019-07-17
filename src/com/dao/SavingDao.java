package com.dao;

import com.util.*;

import java.sql.*;
import java.util.Date;


public class SavingDao {
	public static float getsS (double d){
		double r = 0.5 * d;
		double S = Math.PI * Math.pow(r,2.0);
		return (float)S;
	}
	public static int waterSecToHour (String device) throws Exception {
		//��water_sec�е�һСʱ����ȡ���ۼӲ�����water_consumption��ɾ��ÿ�����ݣ����ɹ����error��
		//��������Ϊɾ����water_sec�е�����
		double d = 0.0;
		float S = getsS(d);
		Timestamp time = new Timestamp(new Date().getTime());
		Timestamp ts = TimeConvert.roundHour(time);
		Timestamp subts = TimeConvert.roundSubHour(time);
		ResultSet rs = SavingDB.getVelocities(device, subts, ts);
		float volume = 0.0f;
		while(rs.next()){//volume��λ
			volume = rs.getFloat("velocity") * S + volume;
		}
		rs.close();
		int count = 0;
		int flag = SavingDB.insertWaterConsumption(device, volume, subts, ts);
		if(flag!=1)
			System.out.println("Error!");
		else
			count = SavingDB.deleteItemsFromWaterSec(device, subts, ts);
		return count;
	}
	public static int eleSecToMin (String device) throws Exception {
		//��ele_secȡ��һ���ӵ�����ֵ����ele_min��ɾÿ�����ݣ����ɹ����error��
		//��������Ϊɾ����ele_sec�е�����
		Timestamp time = new Timestamp(new Date().getTime());
		Timestamp subts = TimeConvert.roundSubMin(time);
		Timestamp ts = TimeConvert.roundMin(time);
		float w = SavingDB.getEnergiesFromEleSec(device, subts, ts);
		int flag = SavingDB.insertEleMin(device, w, subts, ts);
		int count = 0;
		if(flag == 1)
			count = SavingDB.deleteItemsFromEleSec(device, subts, ts);
		else
			System.out.println("Error!");
		return count;
	}
	public static int eleMinToHour (String device) throws Exception {
		//��ele_min�е�һСʱ������ȡ���ۼӲ�����ele_hour��ɾ��ÿ�������ݣ����ɹ����error��
		//��������Ϊɾ����ele_min�е�����
		Timestamp time = new Timestamp(new Date().getTime());
		Timestamp subts = TimeConvert.roundSubHour(time);
		Timestamp ts = TimeConvert.roundHour(time);
		ResultSet rs = SavingDB.getEnergiesMin(device, subts, ts);
		float energy = 0.0f;
		while(rs.next()){
			energy = rs.getFloat("energy") + energy;
		}
		rs.close();
		int count = 0;
		int flag = SavingDB.insertEleHour(device, energy, subts, ts);
		if(flag!=1)
			System.out.println("Error!");
		else
			count = SavingDB.deleteItemsFromEleMin(device, subts, ts);
		return count;
	}
	public static int eleHourToDay (String device) throws Exception {
		//��ele_hour�е�һ������ȡ���ۼӲ�����ele_day��ɾ��ÿСʱ���ݣ����ɹ����error��
		//��������Ϊɾ����ele_hour�е�����
		Timestamp time = new Timestamp(new Date().getTime());
		Timestamp subts = TimeConvert.roundSubDay(time);
		Timestamp ts = TimeConvert.roundDay(time);
		ResultSet rs = SavingDB.getEnergiesHour(device, subts, ts);
		float energy = 0.0f;
		while(rs.next()){
			energy = rs.getFloat("energy") + energy;
		}
		rs.close();
		int count = 0;
		int flag = SavingDB.insertEleDay(device, energy, subts, ts);
		if(flag!=1)
			System.out.println("Error!");
		else
			count = SavingDB.deleteItemsFromEleHour(device, subts, ts);
		return count;
	}
	public static int eleDayToMonth (String device) throws Exception {
		Timestamp time = new Timestamp(new Date().getTime());
		Timestamp ts = TimeConvert.roundMonth(time);
		Timestamp subts = TimeConvert.roundSubMonth(time);
		ResultSet re = SavingDB.getEnergiesDay(device, subts, ts);
		float energy = 0.0f;
		if(re.next()){
			energy = re.getFloat("energy") + energy;
		}
		re.close();
		int count = 0;
		int flag = SavingDB.insertEleMonth(device, energy, subts, ts);
		if (flag!=1)
			System.out.println("Error!");
		else
			count = SavingDB.deleteItemsFromEleDay(device, subts, ts);
		return count;
	}
	public static int eleMonthToYear (String device) throws Exception {
		Timestamp time = new Timestamp(new Date().getTime());
		Timestamp ts = TimeConvert.roundYear(time);
		Timestamp subts = TimeConvert.roundSubYear(TimeConvert.roundSubYear(TimeConvert.roundSubYear(time)));
		ResultSet re = SavingDB.getEnergiesMonth(device, subts, ts);
		float energy = 0.0f;
		if(re.next()){
			energy = re.getFloat("energy") + energy;
		}
		re.close();
		int count = SavingDB.deleteItemsFromEleMonth(device, subts, ts);;
		int flag = SavingDB.insertEleMonth(device, energy, subts, ts);
		if (flag!=1)
			System.out.println("Error!");
//		else{
//			count = SavingDB.deleteItemsFromEleMonth(device, subts, ts);
//		}				
		return count;
	}
}
