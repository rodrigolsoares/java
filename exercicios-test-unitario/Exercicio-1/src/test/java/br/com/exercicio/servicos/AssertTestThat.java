package br.com.exercicio.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.exercicio.entidades.Filme;
import br.com.exercicio.entidades.Locacao;
import br.com.exercicio.entidades.Usuario;
import br.com.exercicio.utils.DataUtils;


/**
 * Exemplo Fluente interface
 * */
public class AssertTestThat {
	
	@Test
	public void testLocacao() {
		
		//Cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));
		
		
		try {
			
			//Ação
			Locacao locacao = service.alugarFilme(usuario, filmes);
			
			//Validação
			assertThat(locacao.getValor(), is(equalTo(5.0)));
			assertThat(locacao.getValor(), is(not(6.0)));
			assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
			assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true) );
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Erro, isso não pode acontecer");
		}

		
	}
}
