package br.com.reciclagem.web.util;

import java.text.DecimalFormat;

public class Formater {
	
	public static String mascaraDinheiro(double valor){  
		DecimalFormat dinheiro = new DecimalFormat("###,###,##0.00");  
        return dinheiro.format(valor);  
    }  
}
