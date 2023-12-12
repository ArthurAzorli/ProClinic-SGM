package utils;

import java.util.ArrayList;

public class Text {
	
	public static String toName(String nome) {
		String res = "";
		String partes [] = nome.split("\\ ");
		for (String p: partes) {
			res += p.substring(0, 1).toUpperCase() + p.substring(1).toLowerCase() + " ";
		}
		return res.substring(0, res.length()-1);
	}
	
	public static boolean ExistIn(String str, ArrayList<String> lista) {
		for (String s:lista) {
			if (s.equals(str)) {return true;}
		}
		return false;
	}
	
	public static String toMoney(Double valor) {
		return toMoney(valor.toString());
	}
	
	public static String toMoney(String valor) {
		valor = valor.replace("R$", "");
		valor = valor.replace(" ", "");
		
		String partes[] = valor.split("\\.");
		if (partes.length == 1) {partes = valor.split("\\,");}
		if (partes[0].length() == 0) {partes[0] = "0";}
		
		valor = "R$ "+ partes[0];
		if (partes.length == 1 || partes[1].length() == 0) {
			valor += "," + "00";
		} else if (partes[1].length() == 1) {
			valor += "," + partes[1] + "0";
		} else {
			valor += "," + partes[1];
		}
		
		return valor;
	}
	
	public static Double moneyToDouble(String valor) {
		valor = valor.replace("R$", "");
		valor = valor.replace(" ", "");
		valor = valor.replace(",", ".");
		
		return Double.parseDouble(valor);
		
	}
}
