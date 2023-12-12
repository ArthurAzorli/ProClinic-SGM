package utils;

import java.util.ArrayList;

public class Telefone {
	public static String telefoneFormat(String tel){
		if (tel != null) {
			tel = tel.replace("-", "");
			tel = tel.replace("(", "");
			tel = tel.replace(")", "");
			tel = tel.replace("+", "");
			tel = tel.replace(" ", "");
			
			String p1 = null, p2 = null, p3 = null, p4 = null;
	
			if (tel.length() >= 12) {
				String telInv = new StringBuilder(tel).reverse().toString();
				String i = new StringBuilder(telInv.subSequence(0, 9)).reverse().toString();
				if (i.subSequence(1, 2).equals("3")) {
					p1 = new StringBuilder(telInv.substring(10)).reverse().toString();
					p2 = new StringBuilder(telInv.subSequence(8, 10)).reverse().toString();
					p3 = new StringBuilder(telInv.subSequence(4, 8)).reverse().toString();
					p4 = new StringBuilder(telInv.subSequence(0, 4)).reverse().toString();
					return String.format("+%s (%s) %s-%s", p1, p2, p3, p4);
				} else if (i.subSequence(0, 1).equals("9")){
					p1 = new StringBuilder(telInv.substring(11)).reverse().toString();
					p2 = new StringBuilder(telInv.subSequence(9, 11)).reverse().toString();
					p3 = new StringBuilder(telInv.subSequence(4, 9)).reverse().toString();
					p4 = new StringBuilder(telInv.subSequence(0, 4)).reverse().toString();
					return String.format("+%s (%s) %s-%s", p1, p2, p3, p4);
				}
			} else if (!(tel.length()<10)) { 
				return telefoneFormat("+55"+tel);
			}
		}
		return "";
	}
	
	

}
