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

import pt.uc.dei.aor.paj.PickListView;

@RunWith(MockitoJUnitRunner.class)
public class HistoryTest {

	@InjectMocks
	private PickListView p;
	
	@Mock
	private Calcinterface c;
	
	@Before
	public void start() {
		p.init();
		p.init("test");
		p.init("test");
		p.init("test");
		
	}
	
	
	@Test
	public void testArrayHistory() {
		int size = p.getHist().size();
		//Número de itens do Array da History deverá ser 3
		assertEquals(3 , size);
	}

}
