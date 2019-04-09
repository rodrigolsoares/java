package br.com.gestao.salao.util;

import org.primefaces.context.RequestContext;

public class PoupUpJsf {
	
	private static final String POUP_UP_AGENDAMENTO = "modalAgendamento.show()";
	private static final String POUP_SUG_REGISTRO_NAO_ENCONTRADO = "modalNaoEncontrado.show()";

	public static void poupUpResultadoAgendamento(){
		RequestContext.getCurrentInstance().execute(POUP_UP_AGENDAMENTO);
		
	}
	
	public static void poupUpSugestionRegistroNaoEncontrado(){
		RequestContext.getCurrentInstance().execute(POUP_SUG_REGISTRO_NAO_ENCONTRADO);
		
	}

}
