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
public class CbrtTest {

	@InjectMocks
	private Calculadora calc;
	
	
	@Mock
	private Calcinterface ci;
	
	@Before
	public void start() {
		calc.setExp("cbrt(123)");
	}
	
	
	@Test
	public void testCbrtCalculadora() {
		String result = calc.getExp();
		//Testa se a expressão introduzida acima tem o valor calculado abaixo
		assertThat(result, Matchers.is(Matchers.equalTo ("4.973189833268591")));
	}

}
