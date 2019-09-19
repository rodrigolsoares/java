package br.com.exercicio.servicos.tdd;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.com.exercicio.entidades.Filme;
import br.com.exercicio.entidades.Locacao;
import br.com.exercicio.entidades.Usuario;
import br.com.exercicio.exception.FilmeSemEstoqueException;
import br.com.exercicio.exception.LocadoraException;
import br.com.exercicio.exception.UsuarioException;
import br.com.exercicio.servicos.LocacaoService;
import br.com.exercicio.utils.DataUtils;

public class LocacaoServiceTest {
	
	private LocacaoService service;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	

	@Before
	public void initialize() {
		service = new LocacaoService();
	}

	@Test
	public void deveAlugarFilme() throws UsuarioException, FilmeSemEstoqueException, LocadoraException {
		
		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

		Locacao locacao = service.alugarFilme(usuario, filmes);

		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(locacao.getValor(), is(not(6.0)));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true) );
		
	}

	@Test(expected = FilmeSemEstoqueException.class)
	public void naoDeveAlugarFilmeSemEstoque() throws UsuarioException, FilmeSemEstoqueException, LocadoraException {

		service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0));

		service.alugarFilme(usuario, filmes);
		
	}

	@Test(expected = UsuarioException.class)
	public void naoDeveAlugarFilmeSemUsuario() throws UsuarioException, FilmeSemEstoqueException, LocadoraException {

		service = new LocacaoService();
		Usuario usuario = null;
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));
		
		service.alugarFilme(usuario, filmes);
		
	}
	
	@Test
	public void devePagar75percentDescontoNoTerceiroFilme() throws UsuarioException, FilmeSemEstoqueException, LocadoraException {

		service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0),
				new Filme("Filme 3", 2, 4.0));

		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		assertThat(resultado.getValor(), is(11.0));
		
	}
	
	@Test
	public void devePagar50percentDescontoNoQuartoFilme() throws UsuarioException, FilmeSemEstoqueException, LocadoraException {

		service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0),
				new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0));

		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		assertThat(resultado.getValor(), is(13.0));
		
	}
	
	@Test
	public void devePagar25percentDescontoNoQuintoFilme() throws UsuarioException, FilmeSemEstoqueException, LocadoraException {

		service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme(";Filme 2", 2, 4.0),
				new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0), new Filme("Filme 5", 2, 4.0));

		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		assertThat(resultado.getValor(), is(14.0));
		
	}
	
	@Test
	public void sextoFilmeDeveSerGratis() throws UsuarioException, FilmeSemEstoqueException, LocadoraException {

		service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0),
				new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0), new Filme("Filme 5", 2, 4.0), new Filme("Filme 6", 2, 4.0));

		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		assertThat(resultado.getValor(), is(14.0));
		
	}
	
	@Test
	//@Ignore
	public void naoDeveDevolverFilmeNoDomingo()throws UsuarioException, FilmeSemEstoqueException, LocadoraException {
		
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));
		
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		boolean isSegunda = DataUtils.verificarDiaSemana(resultado.getDataRetorno(), Calendar.MONDAY);
		assertThat(isSegunda, is(true));
		
	}

	
}
