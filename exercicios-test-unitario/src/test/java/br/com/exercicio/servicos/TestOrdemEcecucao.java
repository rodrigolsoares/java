package br.com.exercicio.servicos;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOrdemEcecucao {
	
	private static int contador;
	
	@Test
	public void inicio() {
		contador = 1;
	}
	
	@Test
	public void verificacao() {
		Assert.assertEquals(1, contador);
	}
	
	

}
