package br.com.gestao.salao.mobile.util;

import org.primefaces.context.RequestContext;

public class PoupUpJsf {
	
	private static final String POUP_UP_RESULDADO = "PF('modlResultado').show();";

	public static void poupUpResultado(){
		RequestContext.getCurrentInstance().execute(POUP_UP_RESULDADO);
		
	}
	

}
