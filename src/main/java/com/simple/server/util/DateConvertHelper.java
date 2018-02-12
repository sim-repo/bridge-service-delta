package com.simple.server.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.hibernate.type.CalendarDateType;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.config.AppConfig;



public class DateConvertHelper {
	
	public final static String NAV_DEFAULT_DATE = "1753-01-01 00:00:00";
	public final static String NAV_DEFAULT_TIME = "000000";
	
	@Autowired
	private AppConfig appConfig;
	
	private static final DateTimeFormatter DATE_FORMATTER =  
		    new DateTimeFormatterBuilder()
		        .append(null, new DateTimeParser[]{
		                DateTimeFormat.forPattern("dd/MM/yyyy").getParser(),
		                DateTimeFormat.forPattern("dd.MM.yyyy").getParser(),
		                DateTimeFormat.forPattern("dd-MM-yyyy").getParser(),
		                DateTimeFormat.forPattern("yyyy/MM/dd").getParser(),
		                DateTimeFormat.forPattern("yyyy.MM.dd").getParser(),
		                DateTimeFormat.forPattern("yyyy-MM-dd").getParser(),
		                DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss").getParser(),
		                DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss").getParser(),
		                DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").getParser(),
		                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss").getParser(),
		                DateTimeFormat.forPattern("yyyy.MM.dd HH:mm:ss").getParser(),
		                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").getParser(),
		                DateTimeFormat.forPattern("HH:mm:ss").getParser(),
		                DateTimeFormat.forPattern("HHmmss").getParser(),
		                DateTimeFormat.forPattern("HH-mm-ss").getParser()		                		                
		        })
		        .toFormatter();
	
		
	
	public static String getCurDate(){
		return new SimpleDateFormat(AppConfig.DATEFORMAT).format(Calendar.getInstance().getTime());
	}
	
	public static Date parse(String sDate){
		LocalDateTime localDateTime = DATE_FORMATTER.parseLocalDateTime(sDate);
		return localDateTime.toDate();
	}
	
	public static String format(DateTime date){
		return new SimpleDateFormat(AppConfig.DATEFORMAT).format(date);
	}
	
	public static String format(Date date){
		return new SimpleDateFormat(AppConfig.DATEFORMAT).format(date);
	}
	
	public static String dateToNavFormat(String sDate){
		LocalDate localDate = DATE_FORMATTER.parseLocalDate(sDate);
		DateTime dateTime = new DateTime(localDate.getYear(),localDate.getMonthOfYear(),localDate.getDayOfMonth(),0,0,0);		
		return dateTime.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));				
	}
	
	public static String timeToNavFormat(String sTime){
		LocalTime localTime = DATE_FORMATTER.parseLocalTime(sTime);
		DateTime dateTime = null;
		if(localTime.toString(DateTimeFormat.forPattern("HHmmss")).equals(NAV_DEFAULT_TIME)){
			dateTime = new DateTime(1753, 1, 1, localTime.getHourOfDay(),localTime.getMinuteOfHour(),localTime.getSecondOfMinute());					
		}
		else{
			
			dateTime = new DateTime(1754, 1, 1, localTime.getHourOfDay(),localTime.getMinuteOfHour(),localTime.getSecondOfMinute());
		}
		return dateTime.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
	}

}
