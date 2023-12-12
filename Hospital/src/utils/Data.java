package utils;

import java.sql.Date;
import java.sql.Time;

public class Data {	
	public static String toDateBrFormat(Date data) {
		try {
			String dataUs[] = data.toString().split("\\-"); 
			String ano = dataUs[0];
			String mes = dataUs[1];
			String dia = dataUs[2];
			
			return String.format("%s/%s/%s", dia, mes, ano);
		} catch (Exception ex) {
			return null;
		}
		
	}
	
	public static Date toDateUsFormat(String data) {
		try {
			String dataBr[] = data.split("\\/");
			Integer dia = Integer.parseInt(dataBr[0]);
			Integer mes = Integer.parseInt(dataBr[1]);
			Integer ano = Integer.parseInt(dataBr[2]);
			
			return new Date(ano-1900, mes-1, dia);
		} catch(Exception ex) {
			return null;
		}
		
	}
	
	public static boolean BirthdayIsValid(Date data) {
		if (data == null) {
			return false;
		}
		
		//data baseada na data de aniversario de Kane Tanaka, a pessoa msi velha do mundo
		if (data.getYear()+1900<=1846) {
			return false;
		} 
		
		return true;
	}
	
	public static Time toTime(String horario) {
		return Time.valueOf(horario);
		
	}
	
	public static boolean timeIsValid(String horario) {
		try {
			Time.valueOf(horario);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
}


