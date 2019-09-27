package br.com.exercicio.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.exercicio.calculadora.CalculadoraTest;
import br.com.exercicio.servicos.tdd.LocacaoServiceTestTdd;
import br.com.exercicio.servicos.testes.matchers.LocacaoServiceExemploMatchersTest;
import br.com.exercicio.servicos.testes.parametrizaveis.LocacaoServiceParametersTest;

@RunWith(Suite.class)
@SuiteClasses({
	CalculadoraTest.class,
	LocacaoServiceTestTdd.class,
	LocacaoServiceExemploMatchersTest.class,
	LocacaoServiceParametersTest.class
})
public class ExemploSuiteTest {
	//Exemplo suite de test
}
