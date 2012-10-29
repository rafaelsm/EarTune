package br.com.rads.eartune.util;

import java.text.SimpleDateFormat;
import java.util.Date;

abstract class DateHelper {

	
	public static String dateToString(Date date){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		return dateFormat.format(date);
	}
	
}
