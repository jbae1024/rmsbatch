package com.skt.rmsbatch;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.skt.rmsbatch.responsepojo.DataSet;
import com.skt.rmsbatch.responsepojo.Record;
import com.skt.rmsbatch.responsepojo.RecordSet;
import com.skt.rmsbatch.responsepojo.Response;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = SpringRestBatchApplication.class)
//@PropertySource("classpath:application.properties")
public class UtilTest {
	
	@Test
	public void toDayTest() {
//		Date today = new Date (); 
//		System.out.println ( today );
		
		GregorianCalendar today = new GregorianCalendar ( );
		
		int year = today.get ( today.YEAR );
		int month = today.get ( today.MONTH ) + 1;
		int yoil = today.get ( today.DAY_OF_MONTH ); 
		
		System.out.println ( month );
		

		GregorianCalendar gc = new GregorianCalendar ( );

		System.out.println ( gc.get ( Calendar.YEAR ) );
		System.out.println ( String.valueOf ( gc.get ( Calendar.MONTH ) + 1 ) );
		System.out.println ( gc.get ( Calendar.DATE ) + 1);
		
		
		Calendar cal = Calendar.getInstance ( );//오늘 날짜를 기준으루..
//		cal.add ( cal.MONTH, -2 ); //2개월 전....
		
		cal.add ( cal.DATE, +25 ); //25일후....
		
		
		System.out.println ( cal.get ( cal.YEAR ) );
		System.out.println ( "달 : " );
		System.out.println ( cal.get ( cal.MONTH ) + 1 );
		System.out.println ( cal.get ( cal.DATE ) );
		
		
		
		Calendar today2 = Calendar.getInstance ();
		today2.add ( Calendar.DATE, -1 );
		Date yesterday = today2.getTime ( );
		
		System.out.println(yesterday);
		
		SimpleDateFormat sdf = new SimpleDateFormat ( "yyyyMMdd" );
		System.out.println(sdf.format(yesterday));
		
	}

}

