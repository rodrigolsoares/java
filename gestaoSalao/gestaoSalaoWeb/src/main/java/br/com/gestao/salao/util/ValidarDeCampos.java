package br.com.gestao.salao.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarDeCampos {

	
	private static Pattern pattern;
	private static Matcher matcher;
 
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final String CPF_PATTERN = "[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}-?[0-9]{2}";
	
	private static final String CNPJ_PATTERN = "[0-9]{1}?\\.[0-9]{3}?\\.[0-9]{3}?";
	
	private static final String RG_PATTERN = "[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}-?[A-Za-z0-9]{1}";
	
	
	
	
	public static boolean email(final String hex) {
		
		pattern = Pattern.compile(EMAIL_PATTERN);
		
		matcher = pattern.matcher(hex);
		return matcher.matches();
 
	}
	
	public static boolean cpf(final String hex) {
		
		pattern = Pattern.compile(CPF_PATTERN);
		
		matcher = pattern.matcher(hex);
		return matcher.matches();
 
	}
	
	public static boolean cnpj(final String hex) {
		
		pattern = Pattern.compile(CNPJ_PATTERN);
		
		matcher = pattern.matcher(hex);
		return matcher.matches();
 
	}
	
	public static boolean rg(final String hex) {
		
		pattern = Pattern.compile(RG_PATTERN);
		
		matcher = pattern.matcher(hex);
		return matcher.matches();
 
	}
	
	
	
	
	
	
	
}
