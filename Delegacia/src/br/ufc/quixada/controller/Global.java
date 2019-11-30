package br.ufc.quixada.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class Global {
	
	
	
	public static Timestamp toTimeStampSql(LocalDateTime date) {
		//return date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth() + " " + date.getHour() + ":" + date.getMinute() + ":" + date.getSecond();
		
		return Timestamp.valueOf(date);
	}
	

	public static LocalDateTime toLocalDateTime(Timestamp date) {
		
		return date.toLocalDateTime();
		
	}
	
	public static String boolToString(boolean bool) {
		if(bool == true) return "Sim";
		else return "Nao";
	}
}
