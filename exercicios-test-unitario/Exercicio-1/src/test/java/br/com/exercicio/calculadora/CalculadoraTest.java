package br.com.exercicio.calculadora;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.exercicio.exception.NaoPodeDividirPorZeroException;

public class CalculadoraTest {
	
	private Calculadora calculadora;
	
	@Before
	public void initialize() {
		calculadora = new Calculadora();
	}
	
	@Test
	public void somaDoisValores() {
		int a = 5, b = 3;
		int resultado = calculadora.soma(a,b);
		Assert.assertEquals(8, resultado);
	}
	
	@Test
	public void subitraiDoisValores() {
		int a = 5, b = 3;
		int resultado = calculadora.subtrai(a,b);
		Assert.assertEquals(2, resultado);
	}
	
	
	@Test
	public void multiplicaDoisValores() {
		int a = 5, b = 3;
		int resultado = calculadora.multiplica(a,b);
		Assert.assertEquals(15, resultado);
	}
	
	@Test
	public void dividiDoisValores() throws NaoPodeDividirPorZeroException{
		int a = 6, b = 3;
		int resultado = calculadora.dividi(a,b);
		Assert.assertEquals(2, resultado);
	}
	
	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void naopodedividiporZero() throws NaoPodeDividirPorZeroException{
		int a = 6, b = 0;
		calculadora.dividi(a,b);
	}
}
