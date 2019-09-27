package br.com.exercicio.dao;

import java.util.List;

import br.com.exercicio.entidades.Locacao;

public interface LocacaoDAO {
	
	public void salvar(Locacao locacao);

	public List<Locacao> obterLocacoesPendentes();
}
