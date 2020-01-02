package com.spm.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	
}
