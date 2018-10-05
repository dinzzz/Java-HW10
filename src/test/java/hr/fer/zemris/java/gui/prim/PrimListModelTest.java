package hr.fer.zemris.java.gui.prim;

import org.junit.Test;
import org.junit.Assert;

public class PrimListModelTest {
	
	private static final double DELTA = 1E-5;

	@Test
	public void addTest() {
		PrimListModel model = new PrimListModel();

		int expected = 1;
		int actual = model.getSize();

		model.next();
		model.next();

		int expected2 = 3;
		int actual2 = model.getSize();

		Assert.assertEquals(expected, actual);
		Assert.assertEquals(expected2, actual2);
	}

	@Test
	public void getElementTest() {
		PrimListModel model = new PrimListModel();

		model.next();
		model.next();
		model.next();
		
		double expected = 3;
		double actual = model.getElementAt(2);
		
		double expected2 = 2;
		double actual2 = model.getElementAt(1);
		
		double expected3 = 5;
		double actual3 = model.getElementAt(3);
		
		Assert.assertEquals(expected, actual, DELTA);
		Assert.assertEquals(expected2, actual2, DELTA);
		Assert.assertEquals(expected3, actual3, DELTA);
		
		
	}
	
	@Test
	public void initializationTest() {
		PrimListModel model = new PrimListModel();
		
		double expected = 1;
		double actual = model.getElementAt(0);
		int expected2 = 1;
		int actual2 = model.getSize();
		Assert.assertEquals(expected, actual, DELTA);
		Assert.assertEquals(expected2, actual2, DELTA);
	}
	//There is no more possible tests since all the other methods are private and with those two tests, the funcionality is proven.

}
