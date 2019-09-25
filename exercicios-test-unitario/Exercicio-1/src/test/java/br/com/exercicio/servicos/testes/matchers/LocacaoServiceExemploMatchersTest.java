package br.com.exercicio.servicos.testes.matchers;

import static br.com.exercicio.servicos.testes.matchers.MatchersProprios.caiEm;
import static br.com.exercicio.servicos.testes.matchers.MatchersProprios.caiNumaTerca;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.exercicio.entidades.Filme;
import br.com.exercicio.entidades.Locacao;
import br.com.exercicio.entidades.Usuario;
import br.com.exercicio.exception.FilmeSemEstoqueException;
import br.com.exercicio.exception.LocadoraException;
import br.com.exercicio.exception.UsuarioException;
import br.com.exercicio.servicos.LocacaoService;
import br.com.exercicio.utils.DataUtils;

public class LocacaoServiceExemploMatchersTest {
	
	private LocacaoService service;
	
	@Before
	public void initialize() {
		service = new LocacaoService();
	}

	@Test
	@Ignore
	public void deveAlugarFilme() throws UsuarioException, FilmeSemEstoqueException, LocadoraException {
		
		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		Usuario usuario = new Usuario("Usuário 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		
		//assertThat(locacao.getDataRetorno(), caiEm(Calendar.MONDAY));
		//assertThat(locacao.getDataRetorno(), caiNumaSegunda());
		
		assertThat(locacao.getDataRetorno(), caiEm(Calendar.TUESDAY));
		assertThat(locacao.getDataRetorno(), caiNumaTerca());
	}

	
	

	
}
