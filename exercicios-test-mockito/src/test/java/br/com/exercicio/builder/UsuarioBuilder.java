package br.com.exercicio.builder;

import br.com.exercicio.entidades.Usuario;

public class UsuarioBuilder {

	private static UsuarioBuilder builder;
	private Usuario usuario;
	
	private UsuarioBuilder() {}
	
	public static UsuarioBuilder umUsuario(){
		builder = new UsuarioBuilder();
		builder.usuario = new Usuario();
		builder.usuario.setNome("Usuario 1");
		return builder;
	}
	
	public Usuario agora(){
		return usuario;
	}
	
	public UsuarioBuilder comNome(String nome) {
		builder.usuario.setNome(nome);
		return builder;
	}
}
