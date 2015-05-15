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
public class ComplexTest {

	@InjectMocks
	private Calculadora calc;
	
	
	@Mock
	private Calcinterface ci;
	
	@Before
	public void start() {}
	
	
	@Test
	public void testComplex1() {
		//Testa se a expressão introduzida tem o resultado esperado
		calc.setExp("atan(-1)*tanh(23)/7!-log(3)*sinh(33)-cbrt(345)^exp(34)");
		String result = calc.getExp();
		assertThat(result, Matchers.is(Matchers.equalTo ("Argumentos inválidos")));
	}
	
	@Test
	public void testComplex2() {
		//Testa se a expressão introduzida tem o resultado esperado
		calc.setExp("sqrt(2789/3*cos(23)-abs(-1.345))*sin(555)/ceil(1.2345)");
		String result = calc.getExp();
		assertThat(result, Matchers.is(Matchers.equalTo ("NaN")));
	}
	
	@Test
	public void testComplex3() {
		//Testa se a expressão introduzida tem o resultado esperado
		calc.setExp("sqrt(2789)+3*cos(23)-abs(-1.345)*sin(555)-ceil(1.2345)");
		String result = calc.getExp();
		assertThat(result, Matchers.is(Matchers.equalTo ("48.03791953343696")));
	}
	
	@Test
	public void testComplex4() {
		//Testa se a expressão introduzida tem o resultado esperado
		calc.setExp("sqrt(2789)+3*cos(23)-abs(-1.345)*sin(555)-ceil(1.2345)*abs(-1.345)*sin(555)-ceil(1.2345)");
		String result = calc.getExp();
		assertThat(result, Matchers.is(Matchers.equalTo ("45.688789310224124")));
	}
	
	@Test
	public void testComplex5() {
		//Testa se a expressão introduzida tem o resultado esperado
		calc.setExp("sin(555)-floor(1.2345)+tanh(5)*sqrt(2789)+3*cos(23)-abs(-1.345)*sin(555)-ceil(1.2345)*abs(-1.345)*sin(555)-ceil(1.2345)/cbrt(234)");
		String result = calc.getExp();
		assertThat(result, Matchers.is(Matchers.equalTo ("47.23271961281943")));
	}
	
	@Test
	public void testComplex6() {
		//Testa se a expressão introduzida tem o resultado esperado
		calc.setExp("1/0");
		String result = calc.getExp();
		assertThat(result, Matchers.is(Matchers.equalTo ("Divisao por zero")));
	}

}
