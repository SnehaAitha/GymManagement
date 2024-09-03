package com.management.gym.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public final class DateUtil {

	public static LocalDateTime convertDateStringToUTCFormat(String inputDateString) {
		final String DATE_FORMAT = "MM-dd-yyyy HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		TimeZone utcTimeZone = TimeZone.getTimeZone("UTC");
		formatter.setTimeZone(utcTimeZone);	
		
		Date ipputDateInAmerica = new Date();

		try {
			ipputDateInAmerica = formatter.parse(inputDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		LocalDateTime inputDateTime = ipputDateInAmerica.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();		
		return inputDateTime;
		
	}
}

