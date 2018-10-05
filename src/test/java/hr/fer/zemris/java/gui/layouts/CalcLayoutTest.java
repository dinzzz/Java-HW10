package hr.fer.zemris.java.gui.layouts;

import org.junit.Test;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Assert;

public class CalcLayoutTest {
	
	@Test
	public void demoPassTest() {
		JPanel p = new JPanel(new CalcLayout(3));
		p.add(new JLabel("x"), "1,1");
		p.add(new JLabel("y"), "2,3");
		p.add(new JLabel("z"), "2,7");
		p.add(new JLabel("w"), "4,2");
		p.add(new JLabel("a"), "4,5");
		p.add(new JLabel("b"), "4,7");
	}
	
	@Test(expected = CalcLayoutException.class)
	public void demoFailTest() {
		JPanel p = new JPanel(new CalcLayout(3));
		p.add(new JLabel("x"), "1,1");
		p.add(new JLabel("y"), "2,3");
		p.add(new JLabel("z"), "2,7");
		p.add(new JLabel("w"), "4,2");
		p.add(new JLabel("a"), "4,5");
		p.add(new JLabel("b"), "4,7");
		p.add(new JLabel("c"), "4,7");
	}
	
	@Test
	public void dimSizeTest() {
		JPanel p = new JPanel(new CalcLayout(2));
		JLabel l1 = new JLabel(""); l1.setPreferredSize(new Dimension(10,30));
		JLabel l2 = new JLabel(""); l2.setPreferredSize(new Dimension(20,15));
		p.add(l1, new RCPosition(2,2));
		p.add(l2, new RCPosition(3,3));
		Dimension dim = p.getPreferredSize();
		
		int expected = 152;
		int acutal = dim.width;
		
		int expected2 = 158;
		int actual2 = dim.height;
		
		Assert.assertEquals(expected, acutal);
		Assert.assertEquals(expected2, actual2);

	}
	
	@Test
	public void dimSize2Test() {
		JPanel p = new JPanel(new CalcLayout(2));
		JLabel l1 = new JLabel(""); l1.setPreferredSize(new Dimension(108,15));
		JLabel l2 = new JLabel(""); l2.setPreferredSize(new Dimension(16,30));
		p.add(l1, new RCPosition(1,1));
		p.add(l2, new RCPosition(3,3));
		Dimension dim = p.getPreferredSize();
		
		int expected = 152;
		int acutal = dim.width;
		
		int expected2 = 158;
		int actual2 = dim.height;
		
		Assert.assertEquals(expected, acutal);
		Assert.assertEquals(expected2, actual2);
	}
	
	@Test(expected = CalcLayoutException.class)
	public void demoFailTest2() {
		JPanel p = new JPanel(new CalcLayout(3));
		p.add(new JLabel("x"), "1,3");

	}
	
	@Test(expected = CalcLayoutException.class)
	public void demoFailTest3() {
		JPanel p = new JPanel(new CalcLayout(3));
		p.add(new JLabel("x"), "8,3");

	}
}
