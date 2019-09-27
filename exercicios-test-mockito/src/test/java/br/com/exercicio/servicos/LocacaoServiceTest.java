package br.com.exercicio.servicos;

import static br.com.exercicio.builder.FilmeBuilder.umFilme;
import static br.com.exercicio.builder.FilmeBuilder.umFilmeSemEstoque;
import static br.com.exercicio.builder.LocacaoBuilder.umaLocacao;
import static br.com.exercicio.builder.UsuarioBuilder.umUsuario;
import static br.com.exercicio.matchers.MatchersProprios.caiNumaSegunda;
import static br.com.exercicio.matchers.MatchersProprios.ehHoje;
import static br.com.exercicio.matchers.MatchersProprios.ehHojeComDiferencaDias;
import static br.com.exercicio.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import br.com.exercicio.builder.UsuarioBuilder;
import br.com.exercicio.dao.LocacaoDAO;
import br.com.exercicio.entidades.Filme;
import br.com.exercicio.entidades.Locacao;
import br.com.exercicio.entidades.Usuario;
import br.com.exercicio.exception.FilmeSemEstoqueException;
import br.com.exercicio.exception.LocadoraException;
import br.com.exercicio.exception.UsuarioException;
import br.com.exercicio.utils.DataUtils;


public class LocacaoServiceTest {
	
	private LocacaoService service;
	private LocacaoDAO dao;
	private ScpcService scpc;
	private EmailService email;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup(){
		
		service = new LocacaoService();
		
		dao = Mockito.mock(LocacaoDAO.class);
		scpc = Mockito.mock(ScpcService.class);
		email = Mockito.mock(EmailService.class);
				
		service.setDao(dao);
		service.setScpc(scpc);
		service.setEmail(email);
	}
	
	@Test
	public void deveAlugarFilme() throws Exception {
		
		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		Usuario usuario = umUsuario().agora();
		List<Filme> filmes = Arrays.asList(umFilme().comValor(5.0).agora());
		
		Locacao locacao = service.alugarFilme(usuario, filmes);
			
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(locacao.getDataLocacao(), ehHoje());
		error.checkThat(locacao.getDataRetorno(), ehHojeComDiferencaDias(1));
	}
	
	@Test(expected = FilmeSemEstoqueException.class)
	public void naoDeveAlugarFilmeSemEstoque() throws Exception{

		Usuario usuario = umUsuario().agora();
		List<Filme> filmes = Arrays.asList(umFilmeSemEstoque().agora());

		service.alugarFilme(usuario, filmes);
	}
	
	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException, UsuarioException, LocadoraException{

		List<Filme> filmes = Arrays.asList(umFilme().agora());
		
		try {
			service.alugarFilme(null, filmes);
			Assert.fail();
		} catch (UsuarioException e) {
			assertThat(e.getMessage(), is("Usuário vazio"));
		}
	}

	@Test
	public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException, UsuarioException{

		Usuario usuario = umUsuario().agora();
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filmes vazio");

		service.alugarFilme(usuario, null);
	}
	
	@Test
	public void deveDevolverNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, LocadoraException, UsuarioException{

		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

		Usuario usuario = umUsuario().agora();
		List<Filme> filmes = Arrays.asList(umFilme().agora());
	
		Locacao retorno = service.alugarFilme(usuario, filmes);

		assertThat(retorno.getDataRetorno(), caiNumaSegunda());
		
	}
	
	@Test
	public void naoDeveAlugarFilmeParaNegativadoScpc() throws FilmeSemEstoqueException, LocadoraException, UsuarioException {
		
		Usuario usuario = umUsuario().agora();
		//Usuario usuario2 = umUsuario().comNome("TESTE").agora();
		
		List<Filme> filmes = Arrays.asList(umFilme().agora());
		
		when(scpc.possuiNegativacao(usuario)).thenReturn(true);
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Usuario Negativado");
		
		service.alugarFilme(usuario, filmes);
		
	}
	
	@Test
	public void deveEnviarEmailParaLocacoesAtrasados() {
		
		Usuario usuario = UsuarioBuilder.umUsuario().agora();
		//Usuario usuario2 = umUsuario().comNome("TESTE").agora();
		
		List<Locacao> locacoes = Arrays.asList(umaLocacao().comDataRetono(obterDataComDiferencaDias(-2)).comUsuario(usuario).agora());
		
		when(dao.obterLocacoesPendentes()).thenReturn(locacoes);
		
		service.notificarAtrasos();
		
		verify(email).notificarAtraso(usuario);
		
	}

}
