package com.spm.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	
	public static int between(long startDate, long endDate) {
		return (int)(endDate - startDate) / (1000 * 60 * 60 * 24);
	}
	
	public static Long parseStringToMiliseconds(String date, String... format) throws ParseException {
		String formatter = new String("dd/MM/yyyy HH:mm:ss");
		if(format !=  null && format.length > 0) {
			formatter = format[0];
		}
		SimpleDateFormat  dateFormat = new SimpleDateFormat(formatter);
		
		return dateFormat.parse(date).getTime();
	}
	
	public static Long getBeginOfCurrentDate() throws ParseException {
		String formatter = new String("dd/MM/yyyy 00:00:00");
		
		SimpleDateFormat  dateFormat = new SimpleDateFormat(formatter);
		
		String dateString = dateFormat.format(Calendar.getInstance().getTime());
		
		formatter = new String("dd/MM/yyyy HH:mm:ss");
		dateFormat = new SimpleDateFormat(formatter);
		
		return dateFormat.parse(dateString).getTime();
	}
	
	public static Long getEndOfCurrentDate() throws ParseException {
		String formatter = new String("dd/MM/yyyy 23:59:59");
		
		SimpleDateFormat  dateFormat = new SimpleDateFormat(formatter);
		
		String dateString = dateFormat.format(Calendar.getInstance().getTime());
		formatter = new String("dd/MM/yyyy HH:mm:ss");
		dateFormat = new SimpleDateFormat(formatter);
		
		return dateFormat.parse(dateString).getTime();
	}
	
}
