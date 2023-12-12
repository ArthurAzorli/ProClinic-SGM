package utils;

import javax.swing.JOptionPane;

public class CPF {	
	
	
	public static String cpfFormat(String cpf){
		cpf = cpf.replace("-", "");
		cpf = cpf.replace(".", "");
		
		
		if (cpf.length() == 11) {
			if (cpfIsValid(cpf)) {
				return String.format("%s.%s.%s-%s", cpf.subSequence(0, 3),cpf.subSequence(3, 6), cpf.subSequence(6, 9), cpf.subSequence(9, 11));
			}
		}
		return cpf;
	}
	
	public static boolean cpfIsValid(String cpf) {
		cpf = cpf.replace("-", "");
		cpf = cpf.replace(".", "");
		
		//Adaptado de: https://gist.github.com/clairtonluz/0e82a03e8b6c148608f1
		
		try{
			Long.parseLong(cpf);
		}catch(NumberFormatException e){
		    return false;
		}

	    int d1 = 0, d2 = 0;
	    int digito1 = 0, digito2 = 0, resto = 0;
	    int digitoCPF;
	    String digResult;

	    for (int i = 1; i < cpf.length() - 1; i++) {
	    	digitoCPF = Integer.parseInt(cpf.substring(i- 1, i));
	    	d1 = d1 + (11 - i) * digitoCPF;
	    	d2 = d2 + (12 - i) * digitoCPF;
	    }

	    resto = (d1 % 11);
	    if (resto < 2) {
	    	digito1 = 0;
	    } else {
	    	digito1 = 11 - resto;
	    }

	    d2 += 2 * digito1;

	    resto = (d2 % 11);
	    if (resto < 2) {
	    	digito2 = 0;
	    } else {
	    	digito2 = 11 - resto;
	    }

	    String digVerific = cpf.substring(cpf.length() - 2, cpf.length());
	    digResult = String.valueOf(digito1) + String.valueOf(digito2);
	    return digVerific.equals(digResult);
	}
}
