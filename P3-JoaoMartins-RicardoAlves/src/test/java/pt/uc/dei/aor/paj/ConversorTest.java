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

import pt.uc.dei.aor.paj.Conversor;


@RunWith(MockitoJUnitRunner.class)
public class ConversorTest {

	@InjectMocks
	private Conversor conv;
	
	
	@Mock
	private Calcinterface ci;
	
	@Before
	public void start() {}
	
	
	@Test
	public void testConversor() {
		String result = conv.decompoe("180");
		//Testa se a expressão introduzida é convertida para radianos
		assertEquals("180", result);
	}

}
