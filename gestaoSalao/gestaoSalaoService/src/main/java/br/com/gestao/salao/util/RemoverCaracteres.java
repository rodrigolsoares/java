package br.com.gestao.salao.util;

public class RemoverCaracteres {
	
	public static String somenteNumeros(String str){  
	  
		if(str != null && !"".equals(str)){
	    	return str.replaceAll("\\D", "");
	    }else{
	    	return "";
	    }
	}
	
}
