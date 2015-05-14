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
public class TrigonometriaTest {

	@InjectMocks
	private Calculadora calc;
	
	
	@Mock
	private Calcinterface ci;
	
	@Before
	public void start() {
		calc.setExp("cos(32)+sin(44)-tan(21)");
	}
	
	
	@Test
	public void testTrigonometria() {
		String result = calc.getExp();
		//Testa se a expressão introduzida acima tem o valor calculado em baixo
		assertThat(result, Matchers.is(Matchers.equalTo ("2.3794238132485273")));
	}

}
