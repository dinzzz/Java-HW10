package hr.fer.zemris.java.gui.charts;

/**
 * Class that represents the basic XY value storage used in bar charts.
 * 
 * @author Dinz
 *
 */
public class XYValue {

	/**
	 * X value.
	 */
	private int x;

	/**
	 * Y value.
	 */
	private int y;

	/**
	 * Constructs a new XYValue.
	 * 
	 * @param x
	 *            X value.
	 * @param y
	 *            Y value.
	 */
	public XYValue(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructs a new XY value with values intialized to 0.
	 */
	public XYValue() {
		this(0, 0);
	}

	/**
	 * Gets the x value.
	 * 
	 * @return X value.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the y value.
	 * 
	 * @return Y value.
	 */
	public int getY() {
		return y;
	}

}
