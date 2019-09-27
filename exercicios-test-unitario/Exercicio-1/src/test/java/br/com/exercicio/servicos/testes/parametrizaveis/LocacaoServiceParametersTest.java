package br.com.exercicio.servicos.testes.parametrizaveis;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import br.com.exercicio.calculadora.dao.LocacaoDAO;
import br.com.exercicio.entidades.Filme;
import br.com.exercicio.entidades.Locacao;
import br.com.exercicio.entidades.Usuario;
import br.com.exercicio.exception.FilmeSemEstoqueException;
import br.com.exercicio.exception.LocadoraException;
import br.com.exercicio.exception.UsuarioException;
import br.com.exercicio.servicos.LocacaoService;

@RunWith(Parameterized.class)
public class LocacaoServiceParametersTest {
		
	private static Filme filme1 = new Filme("Filme 1", 2, 4.0);
	private static Filme filme2 = new Filme("Filme 2", 2, 4.0); 
	private static Filme filme3 = new Filme("Filme 3", 2, 4.0);
	private static Filme filme4 = new Filme("Filme 4", 2, 4.0);
	private static Filme filme5 = new Filme("Filme 5", 2, 4.0); 
	private static Filme filme6 = new Filme("Filme 6", 2, 4.0);
	
	@Parameter
	public List<Filme> filmes;
	
	@Parameter(value = 1)
	public Double valor;
	
	@Parameter(value = 2)
	public String descricao;
	
	
	private LocacaoService service;
	

	@Before
	public void initialize() {
		service = new LocacaoService();
		service.setDao(Mockito.mock(LocacaoDAO.class));
	}
	
	@Parameters(name = "Teste {index} - {2}")
	public static Collection<Object[]> getParametros(){
		return Arrays.asList(new Object[][]{
				{Arrays.asList(filme1, filme2, filme3), 11.0, "3 Filmes 20%"}, 
				{Arrays.asList(filme1, filme2, filme3, filme4), 13.0 , "4 Filmes 50%"},
				{Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0, "5 Filmes 75%"}, 
				{Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14.0, "6 Filmes 100%"} 
		});
	}
	
	@Test
	public void deveCalcularLocacaoConsiderandoDesconto() throws UsuarioException, FilmeSemEstoqueException, LocadoraException {

		Usuario usuario = new Usuario("Usuário 1");

		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		assertThat(resultado.getValor(), is(valor));
		
	}
	
	

	


	
}
