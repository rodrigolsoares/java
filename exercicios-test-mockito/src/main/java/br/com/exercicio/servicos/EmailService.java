package br.com.exercicio.servicos;

import br.com.exercicio.entidades.Usuario;

public interface EmailService {
	
	public void notificarAtraso(Usuario usuario);
}
