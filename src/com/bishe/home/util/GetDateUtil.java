package com.bishe.home.util;

import java.sql.Timestamp;
import java.util.Date;

public class GetDateUtil {
	
	public static Timestamp getDate(){
		Date date = new Date();       
		Timestamp nousedate = new Timestamp(date.getTime());
		return nousedate;
	}
}
