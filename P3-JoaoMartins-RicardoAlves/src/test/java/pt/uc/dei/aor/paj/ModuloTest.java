package pt.uc.dei.aor.paj;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ModuloTest {

	@InjectMocks
	private Calculadora calc;
	
	
	@Mock
	private Calcinterface ci;
	
	@Before
	public void start() {}
	
	
	@Test
	public void testModulo1() {
		//Testa se a expressão introduzida tem o resultado esperado
		calc.setExp("abs(-1)");
		String result = calc.getExp();
		assertThat(result, Matchers.is(Matchers.equalTo ("1.0")));
	}
	
	@Test
	public void testModulo2() {
		//Testa se a expressão introduzida tem o resultado esperado
		calc.setExp("abs(1)");
		String result = calc.getExp();
		assertThat(result, Matchers.is(Matchers.equalTo ("1.0")));
	}

}
