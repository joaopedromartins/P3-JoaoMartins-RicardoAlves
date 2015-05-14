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
public class CalculadoraTest {

	@InjectMocks
	private Calculadora calc;
	
	
	@Mock
	private Calcinterface ci;
	
	@Before
	public void start() {
		calc.setExp("(2+3*10-2)/2");
	}
	
	
	@Test
	public void testResultadoCalculadora() {
		String result = calc.getExp();
		//Testa se a expressão introduzida acima tem o valor calculado de 15
		assertThat(result, Matchers.is(Matchers.equalTo ("15.0")));
	}

}
