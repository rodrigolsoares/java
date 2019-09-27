package br.com.exercicio.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.com.exercicio.entidades.Usuario;

public class AssertTest {
	
	@Test
	public void test() {
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		Assert.assertEquals(0.51, 0.51, 0.1);
		Assert.assertEquals(Math.PI, 3.14, 0.1);
		
		int i = 5;
		Integer i2 = 5;
		Assert.assertEquals(i, i2.intValue());
		Assert.assertEquals(Integer.valueOf(i), i2);
		
		Assert.assertEquals("bola", "bola");
		Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
		Assert.assertTrue("bola".startsWith("bo"));
		
		
		Usuario us1 = new Usuario("Usuário 1");
		Usuario us2 = new Usuario("Usuário 1");
		Usuario us3 = null; 
		
		Assert.assertEquals(us1, us2);
		Assert.assertSame(us1, us1);
		Assert.assertNotSame(us1, us2);
		
		Assert.assertNotNull(us1);
		Assert.assertNull(us3);
		
		
	}
}
