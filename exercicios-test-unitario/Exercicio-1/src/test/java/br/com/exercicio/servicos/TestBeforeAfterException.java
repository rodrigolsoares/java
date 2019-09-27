package br.com.exercicio.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import br.com.exercicio.calculadora.dao.LocacaoDAO;
import br.com.exercicio.entidades.Filme;
import br.com.exercicio.entidades.Locacao;
import br.com.exercicio.entidades.Usuario;
import br.com.exercicio.utils.DataUtils;

public class TestBeforeAfterException {
	
	private LocacaoService service;
	
	private static int contador;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@BeforeClass
	public static void initializeClass() {
		System.out.println("before Class");
	}
	
	@Before
	public void initialize() {
		System.out.println("before");
		contador++;
		service = new LocacaoService();
		service.setDao(Mockito.mock(LocacaoDAO.class));
	}
	
	@After
	public void destroy() {
		System.out.println("After");
		System.out.println("contador: " + contador);
		
	}
	
	@AfterClass
	public static void destroyClass() {
		System.out.println("After Class");
	}
	
	@Test
	public void testLocacao() throws Exception {

		Usuario usuario = new Usuario("Usuário 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

		Locacao locacao = service.alugarFilme(usuario, filmes);

		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(locacao.getValor(), is(not(6.0)));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true) );
		
	}
	
	/**
	 * Exemplo 1*
	 * */
	@Test(expected = Exception.class)
	public void testLocacaoSemEstoque() throws Exception {

		service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0));

		service.alugarFilme(usuario, filmes);
		
	}

	@Test
	public void testLocacaoSemEstoque2() throws Exception {
		
		service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0));

		try{
			service.alugarFilme(usuario, filmes);
			Assert.fail("Deveria lançar uma exceção");
			
		}catch (Exception e) {
			assertThat(e.getMessage(), is("Filme sem estoque"));
		}
		
	}
	
	@Test
	public void testLocacaoSemEstoque3() throws Exception {
		
		service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0));

		expectedException.expect(Exception.class);
		expectedException.expectMessage("Filme sem estoque");

		service.alugarFilme(usuario, filmes);
		
	}
}
