package pt.uc.dei.aor.paj;

import static org.junit.Assert.*;

import org.junit.Test;

public class FactorialTest {

	@Test
	public void testFactorial() {
		Junit test = new Junit();
		String result = test.factorial("3");
		assertEquals("6.0", result);
		String result2 = test.factorial("0");
		assertEquals("1.0", result2);
	}

}
