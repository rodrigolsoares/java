package br.com.exercicio.servicos;



import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.exercicio.entidades.Filme;
import br.com.exercicio.entidades.Locacao;
import br.com.exercicio.entidades.Usuario;
import br.com.exercicio.servicos.LocacaoService;
import br.com.exercicio.utils.DataUtils;

public class LocacaoServiceTest {

	@Test
	public void teste() {
		
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));
		
		try {
			
			Locacao  locacao = service.alugarFilme(usuario, filmes);
			Assert.assertEquals(5.0, locacao.getValor(), 01);
			Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
			Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Erro, isso não pode acontecer");
		}
		
		
	}
}
