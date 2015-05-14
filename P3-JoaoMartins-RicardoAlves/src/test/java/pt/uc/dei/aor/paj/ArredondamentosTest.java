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
public class ArredondamentosTest {

	@InjectMocks
	private Calculadora calc;
	
	
	@Mock
	private Calcinterface ci;
	
	@Before
	public void start() {}
	
	
	@Test
	public void testCeilCalculadora() {
		calc.setExp("ceil(1.89)");
		String result = calc.getExp();
		//Testa se a expressão introduzida é arredondada para cima
		assertThat(result, Matchers.is(Matchers.equalTo ("2.0")));
	}
	
	@Test
	public void testFloorCalculadora() {
		calc.setExp("floor(1.89)");
		String result = calc.getExp();
		//Testa se a expressão introduzida é arredondada para baixo
		assertThat(result, Matchers.is(Matchers.equalTo ("1.0")));
	}

}
