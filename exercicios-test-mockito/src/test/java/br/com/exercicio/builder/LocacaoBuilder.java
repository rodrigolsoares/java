package br.com.exercicio.builder;

import java.util.Date;

import br.com.exercicio.entidades.Locacao;
import br.com.exercicio.entidades.Usuario;

public class LocacaoBuilder {

	private static LocacaoBuilder builder;
	
	private Locacao locacao;
	
	private LocacaoBuilder(){}
	
	public static LocacaoBuilder umaLocacao(){
		builder = new LocacaoBuilder();
		builder.locacao = new Locacao();
		return builder;
	}
	
	public LocacaoBuilder comDataRetono(Date date){
		builder.locacao.setDataRetorno(date);
		return builder;
	}
	
	public LocacaoBuilder comUsuario(Usuario usuario){
		builder.locacao.setUsuario(usuario);
		return builder;
	}
	

	public Locacao agora(){
		return locacao;
	}
}
