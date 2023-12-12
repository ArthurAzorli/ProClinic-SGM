package utils;

import javax.swing.JOptionPane;

import dao.UFDAO;

public class Conselho {
	public static String conselhoFormat(String crm) {
		UFDAO dao = new UFDAO();
		String uf = crm.substring(crm.length()-2, crm.length());
		if (dao.exist(uf)) {
			crm = crm.replace("/", "");
			crm = crm.substring(0,crm.length()-2);
			try {
				Long.parseLong(crm);
			}catch(NumberFormatException e){
			    return null;
			}
			return String.format("%s/%s",crm,uf);
		} else {
			JOptionPane.showMessageDialog(null, "Conselho Inválido!");
		}
		return crm;
	}
	
	public static boolean conselhoIsValid(String crm) {
		UFDAO dao = new UFDAO();
		String uf = crm.substring(crm.length()-2, crm.length());
		if (dao.exist(uf)) {
			return true;
		}
		return false;
	}
}
