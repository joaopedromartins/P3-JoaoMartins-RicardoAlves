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
public class LogaritmoTest {

	@InjectMocks
	private Calculadora calc;
	
	
	@Mock
	private Calcinterface ci;
	
	@Before
	public void start() {}
	
	
	@Test
	public void testLogaritmoNatural() {
		//Testa se a expressão introduzida acima tem o valor calculado abaixo
		calc.setExp("log(100)");
		String result = calc.getExp();
		assertThat(result, Matchers.is(Matchers.equalTo ("4.605170185988092")));
	}
	
	@Test
	public void testLogaritmo10() {
		//Testa se a expressão introduzida acima tem o valor calculado abaixo
		calc.setExp("log10(100)");
		String result = calc.getExp();
		assertThat(result, Matchers.is(Matchers.equalTo ("2.0")));
	}
	
	@Test
	public void testLogaritmo2() {
		//Testa se a expressão introduzida acima tem o valor calculado abaixo
		calc.setExp("log2(100)");
		String result = calc.getExp();
		assertThat(result, Matchers.is(Matchers.equalTo ("6.643856189774725")));
	}

}
