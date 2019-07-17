package com.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeConvert {
	public static Timestamp roundMin (Timestamp time){ //返回整数分钟
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(time);
		str = str.substring(0, str.length()-2);
		str = str + "00";
		Timestamp ts = Timestamp.valueOf(str);
		return ts;		
	}
	public static Timestamp roundSubMin (Timestamp time){ //返回前一分钟
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(time);
		String year = str.substring(0, 4);
		String month = str.substring(5, 7);
		String day = str.substring(8, 10);
		String hour = str.substring(11, 13);
		String minute = str.substring(14,16);
		int y = Integer.parseInt(year);
		int mon = Integer.parseInt(month);
		int d = Integer.parseInt(day);
		int h = Integer.parseInt(hour);
		int min = Integer.parseInt(minute);
		int suby = y-1;
		int submon = mon-1;
		if(submon == 0)
			submon = 12;
		int subd = d-1;
		if(subd == 0){
			if (mon == 1||mon == 4 || mon == 6 || mon == 8 || mon == 9 || mon == 11)
				subd = 31;
			else if(mon == 3){
				if ((y%4 == 0)&&(y%100!=0)||(y%400==0))
					subd = 29;
				else
					subd = 28;
			}
			else
				subd = 30;
			}
		int subh = h-1;
		if(subh == -1)
			subh = 23;
		int submin = min - 1;
		if(submin == -1)
			submin = 59;
		String subyear = String.valueOf(suby);
		String submonth = String.valueOf(submon);
		String subday = String.valueOf(subd);
		String subhour = String.valueOf(subh);
		String subminute = String.valueOf(submin);
		if(submon < 10)
			submonth = "0"+submonth;
		if(subd < 10)
			subday = "0"+subday;
		if(subh < 10)
			subhour = "0"+subhour;
		if(submin < 10)
			subminute = "0"+subminute;
		if(min == 0){
			if(h == 0){
				if(d == 1){
					if(mon == 1)
						str = subyear +"-"+ submonth +"-"+ subday +" "+ subhour +":"+ subminute +":"+ "00";
					else
						str = year +"-"+ submonth +"-"+ subday +" "+ subhour +":"+ subminute +":"+ "00";
				}
				else
					str = year +"-"+ month +"-"+ subday +" "+ subhour +":"+ subminute +":"+ "00";
			}
			else
				str = year +"-"+ month +"-"+ day +" "+ subhour +":"+ subminute +":"+ "00";
		}
		else
			str = year +"-"+ month +"-"+ day +" "+ hour +":"+ subminute +":"+ "00";
		Timestamp ts = Timestamp.valueOf(str);
		return ts;
	}
	public static Timestamp roundHour (Timestamp time){ //返回整小时
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(time);
		str = str.substring(0, str.length()-5);
		str = str + "00:00";
		Timestamp ts = Timestamp.valueOf(str);
		return ts;		
	}
	public static Timestamp roundSubHour (Timestamp time){ //返回前一小时
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(time);
		String year = str.substring(0, 4);
		String month = str.substring(5, 7);
		String day = str.substring(8, 10);
		String hour = str.substring(11, 13);
		int y = Integer.parseInt(year);
		int mon = Integer.parseInt(month);
		int d = Integer.parseInt(day);
		int h = Integer.parseInt(hour);
		int suby = y-1;
		int submon = mon-1;
		if(submon == 0)
			submon = 12;
		int subd = d-1;
		if(subd == 0){
			if (mon == 1||mon == 4 || mon == 6 || mon == 8 || mon == 9 || mon == 11)
				subd = 31;
			else if(mon == 3){
				if ((y%4 == 0)&&(y%100!=0)||(y%400==0))
					subd = 29;
				else
					subd = 28;
			}
			else
				subd = 30;
			}
		int subh = h-1;
		if(subh == -1)
			subh = 23;
		String subyear = String.valueOf(suby);
		String submonth = String.valueOf(submon);
		String subday = String.valueOf(subd);
		String subhour = String.valueOf(subh);
		if(submon < 10)
			submonth = "0"+submonth;
		if(subd < 10)
			subday = "0"+subday;
		if(subh < 10)
			subhour = "0"+subhour;
		if(h == 0){
			if(d == 1){
				if(mon == 1)
					str = subyear +"-"+ submonth +"-"+ subday +" "+ subhour +":"+ "00" +":"+ "00";
				else
					str = year +"-"+ submonth +"-"+ subday +" "+ subhour +":"+ "00" +":"+ "00";
			}
			else
				str = year +"-"+ month +"-"+ subday +" "+ subhour +":"+ "00" +":"+ "00";
		}
		else
			str = year +"-"+ month +"-"+ day +" "+ subhour +":"+ "00" +":"+ "00";
		Timestamp ts = Timestamp.valueOf(str);
		return ts;
	}
	public static Timestamp roundDay (Timestamp time){ //返回整数天
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(time);
		str = str.substring(0, str.length()-8);
		str = str + "00:00:00";
		Timestamp ts = Timestamp.valueOf(str);
		return ts;		
	}
	public static Timestamp roundSubDay (Timestamp time){ //返回前一天
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(time);
		String year = str.substring(0, 4);
		String month = str.substring(5, 7);
		String day = str.substring(8, 10);
		int y = Integer.parseInt(year);
		int mon = Integer.parseInt(month);
		int d = Integer.parseInt(day);
		int suby = y-1;
		int submon = mon-1;
		if(submon == 0)
			submon = 12;
		int subd = d-1;
		if(subd == 0){
			if (mon == 1||mon == 4 || mon == 6 || mon == 8 || mon == 9 || mon == 11)
				subd = 31;
			else if(mon == 3){
				if ((y%4 == 0)&&(y%100!=0)||(y%400==0))
					subd = 29;
				else
					subd = 28;
			}
			else
				subd = 30;
			}
		String subyear = String.valueOf(suby);
		String submonth = String.valueOf(submon);
		String subday = String.valueOf(subd);
		if(submon < 10)
			submonth = "0"+submonth;
		if(subd < 10)
			subday = "0"+subday;
		if(d == 1){
			if(mon == 1)
				str = subyear +"-"+ submonth +"-"+ subday +" "+ "00" +":"+ "00" +":"+ "00";
			else
				str = year +"-"+ submonth +"-"+ subday +" "+ "00" +":"+ "00" +":"+ "00";
		}
		else
			str = year +"-"+ month +"-"+ subday +" "+ "00" +":"+ "00" +":"+ "00";
		Timestamp ts = Timestamp.valueOf(str);
		return ts;
	}
	public static Timestamp roundMonth (Timestamp time){ //返回整数月
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(time);
		str = str.substring(0, str.length()-11);
		str = str + "01 00:00:00";
		Timestamp ts = Timestamp.valueOf(str);
		return ts;		
	}
	public static Timestamp roundSubMonth (Timestamp time){ //返回上一个月
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(time);
		String year = str.substring(0, 4);
		String month = str.substring(5, 7);
		int y = Integer.parseInt(year);
		int mon = Integer.parseInt(month);
		int suby = y-1;
		int submon = mon-1;
		if(submon == 0)
			submon = 12;
		String subyear = String.valueOf(suby);
		String submonth = String.valueOf(submon);
		if(submon < 10)
			submonth = "0"+submonth;
		if(mon == 1)
			str = subyear +"-"+ submonth +"-"+ "01" +" "+ "00" +":"+ "00" +":"+ "00";
		else
			str = year +"-"+ submonth +"-"+ "01" +" "+ "00" +":"+ "00" +":"+ "00";
		Timestamp ts = Timestamp.valueOf(str);
		return ts;
	}
	public static Timestamp roundYear (Timestamp time){ //返回整数年
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(time);
		str = str.substring(0, str.length()-14);
		str = str + "01-01 00:00:00";
		Timestamp ts = Timestamp.valueOf(str);
		return ts;		
	}
	public static Timestamp roundSubYear (Timestamp time){ //返回前一年
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(time);
		String year = str.substring(0, 4);
		int y = Integer.parseInt(year);
		int suby = y-1;
		String subyear = String.valueOf(suby);
		str = subyear +"-"+ "01" +"-"+ "01" +" "+ "00" +":"+ "00" +":"+ "00";
		Timestamp ts = Timestamp.valueOf(str);
		return ts;
	}
	
	public static void main(String[] args) {//验证正确性
		Timestamp ts = roundMin(new Timestamp(new Date().getTime()));
		System.out.println(ts);
		ts = roundSubMin(new Timestamp(new Date().getTime()));
		System.out.println(ts);
		ts = roundHour(new Timestamp(new Date().getTime()));
		System.out.println(ts);
		ts = roundSubHour(new Timestamp(new Date().getTime()));
		System.out.println(ts);
		ts = roundDay(new Timestamp(new Date().getTime()));
		System.out.println(ts);
		ts = roundSubDay(new Timestamp(new Date().getTime()));
		System.out.println(ts);
		ts = roundMonth(new Timestamp(new Date().getTime()));
		System.out.println(ts);
		ts = roundSubMonth(new Timestamp(new Date().getTime()));
		System.out.println(ts);
		ts = roundYear(new Timestamp(new Date().getTime()));
		System.out.println(ts);
		ts = roundSubYear(new Timestamp(new Date().getTime()));
		System.out.println(ts);
	}
}
