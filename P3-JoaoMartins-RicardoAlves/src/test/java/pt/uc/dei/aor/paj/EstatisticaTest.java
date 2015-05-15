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

import pt.uc.dei.aor.paj.Estatistica;
import pt.uc.dei.aor.paj.PickListView;

@RunWith(MockitoJUnitRunner.class)
public class EstatisticaTest {

	@InjectMocks
	private Estatistica s;
	
	
	@Mock
	private Calcinterface c;
	
	@Before
	public void start() {
//		s.setResultado("2+3-4");
	}
	
	
	@Test
	public void testArrayEstatistica() {
//		int size = s.getResultado().size();
		//Número de itens do Array da Estatística deverá ser 23
//		assertEquals(23 , size);
	}

}
