package br.com.exercicio.servicos;

import static br.com.exercicio.utils.DataUtils.adicionarDias;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.exercicio.dao.LocacaoDAO;
import br.com.exercicio.entidades.Filme;
import br.com.exercicio.entidades.Locacao;
import br.com.exercicio.entidades.Usuario;
import br.com.exercicio.exception.FilmeSemEstoqueException;
import br.com.exercicio.exception.LocadoraException;
import br.com.exercicio.exception.UsuarioException;
import br.com.exercicio.utils.DataUtils;

public class LocacaoService {
	
	private LocacaoDAO dao;
	private ScpcService scpc;
	private EmailService email;
	
	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException, UsuarioException{
		
	
		
		if(usuario == null) {
			throw new UsuarioException("Usuário vazio");
		}
		
		if(filmes == null ||  filmes.isEmpty()) {
			throw new LocadoraException("Filmes vazio");
		}
		
		for(Filme filme : filmes) {
			
			if(filme == null ||  filme.getEstoque().intValue() == 0) {
				throw new FilmeSemEstoqueException("Filme sem estoque");
			}
			
			
		}
		
		if(scpc.possuiNegativacao(usuario)) {
			throw new LocadoraException("Usuario Negativado");
		}
		
		Locacao locacao = new Locacao();
		
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setFilmes(filmes);
	
	
		Double valor = 0d;
		
		for (int i = 0; i < filmes.size(); i++) {
			
			Filme filme = filmes.get(i);
			
			switch(i) {
				case 2: valor = valor + filme.getPrecoLocacao() * 0.75; break;
				case 3: valor = valor + filme.getPrecoLocacao() * 0.50; break;
				case 4: valor = valor + filme.getPrecoLocacao() * 0.25; break;
				case 5: continue;
				default: valor = valor + filme.getPrecoLocacao(); break;
			}
		
		}
		
		locacao.setValor(valor);
		
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		
		if(DataUtils.verificarDiaSemana(dataEntrega, Calendar.MONDAY)) {
			dataEntrega = adicionarDias(dataEntrega, 1);
		}
		
		locacao.setDataRetorno(dataEntrega);
		
		dao.salvar(locacao);
		
		return locacao;
	}
	
	public void notificarAtrasos() {
		List<Locacao> locacoes = dao.obterLocacoesPendentes();
		
		for(Locacao locacao : locacoes) {
			email.notificarAtraso(locacao.getUsuario());
		}
	}

	public void setDao(LocacaoDAO dao) {
		this.dao = dao;
	}

	public void setScpc(ScpcService scpc) {
		this.scpc = scpc;
	}

	public void setEmail(EmailService email) {
		this.email = email;
	}
	
	
	
}