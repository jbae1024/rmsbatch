package com.skt.rmsbatch.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtils{
	
	public String getMovedDate (int moveDay, String format ){
    	
    	Calendar today = Calendar.getInstance ();
    	today.add ( Calendar.DATE, moveDay );
    	
		Date movedDay = today.getTime ();
		SimpleDateFormat sdf = new SimpleDateFormat ( format );
		
    	return sdf.format(movedDay);
    }
	
	public String getMovedDate (int moveDay, Date date, String format ){
    	
    	Calendar currentDay = Calendar.getInstance ();
    	currentDay.setTime(date);
    	currentDay.add ( Calendar.DATE, moveDay );
    	
		Date movedDay = currentDay.getTime ();
		
		SimpleDateFormat sdf = new SimpleDateFormat ( format );
		
    	return sdf.format(movedDay);
    }

	
	public Date getMovedDate( int moveDay ){
		
		Calendar today = Calendar.getInstance ();
    	today.add ( Calendar.DATE, moveDay );
    	
    	Date movedDay = today.getTime ();
    	
		return movedDay;
	}
	
	public int stringToInt( String input ){
		
		int intValue = 0;
		
		if(input != null 
				&& !input.equals("")){
			intValue = Integer.parseInt(input);
		}
		
		return intValue;
	}
	
	public Date stringToDate ( String input, String format ){
		Date date = null;
		try{
			SimpleDateFormat transFormat = new SimpleDateFormat(format);
			if(input != null 
					&& !input.equals("")){
				date = transFormat.parse(input);
    		}
		}catch(ParseException e){
			e.printStackTrace();
		}
		return date;
	}	
	
	public Date getMoveDateMidNight ( int moveDay ){
		
		Date date = null;
		
		try{
			Calendar today = Calendar.getInstance ();
	    	today.add ( Calendar.DATE, moveDay );
	    	Date movedDate = today.getTime ();
	    	
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd"+" 00:00:00");
			String format = transFormat.format(movedDate);
			
			SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			date = dateFormate.parse(format);
			
		}catch(ParseException e){
			e.printStackTrace();
		}
		return date;
	}
	
}
