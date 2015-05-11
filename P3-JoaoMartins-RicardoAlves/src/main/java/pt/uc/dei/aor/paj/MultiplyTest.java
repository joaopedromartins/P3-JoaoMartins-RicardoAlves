package pt.uc.dei.aor.paj;

import static org.junit.Assert.*;

import org.junit.Test;

public class MultiplyTest {

	@Test
	public void testMultiply() {
		Junit test = new Junit();
		int result = test.multiply(3, 4);
		assertEquals(12, result);
	}

}
