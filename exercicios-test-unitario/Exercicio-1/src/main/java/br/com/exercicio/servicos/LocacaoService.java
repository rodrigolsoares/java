package br.com.exercicio.servicos;

import static br.com.exercicio.utils.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import br.com.exercicio.entidades.Filme;
import br.com.exercicio.entidades.Locacao;
import br.com.exercicio.entidades.Usuario;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws Exception{
		
		if(usuario == null) {
			throw new Exception("Usuário vazio");
		}
		
		if(filmes == null ||  filmes.isEmpty()) {
			throw new Exception("Filmes vazio");
		}
		
		for(Filme filme : filmes) {
			
			if(filme == null ||  filme.getEstoque().intValue() == 0) {
				throw new Exception("Filme sem estoque");
			}
			
			
		}
		
		Locacao locacao = new Locacao();
		
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setFilmes(filmes);
	
		Double valor = filmes.stream().mapToDouble(f -> f.getPrecoLocacao()).sum();
		locacao.setValor(valor);
		
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}
}