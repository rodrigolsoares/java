package br.com.exercicio.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.mockito.Mockito;

import br.com.exercicio.calculadora.dao.LocacaoDAO;
import br.com.exercicio.entidades.Filme;
import br.com.exercicio.entidades.Locacao;
import br.com.exercicio.entidades.Usuario;
import br.com.exercicio.utils.DataUtils;


/**
 * Exemplo Error Collector
 * */
public class AssertTestErrorCollector {
	
	LocacaoService service;

	@Before
	public void init() {
		service = new LocacaoService();
		service.setDao(Mockito.mock(LocacaoDAO.class));
	}
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Test
	public void testLocacao() {
		
		//Cenário
		Usuario usuario = new Usuario("Usuário 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));
		
		try {
			
			//Ação
			Locacao locacao = service.alugarFilme(usuario, filmes);
			
			
			//Validação
			error.checkThat(locacao.getValor(), is(equalTo(5.0)));
			error.checkThat(locacao.getValor(), is(not(6.0)));
			error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
			error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true) );
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Erro, isso não pode acontecer");
		}
		
	}
}
