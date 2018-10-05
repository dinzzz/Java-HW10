package hr.fer.zemris.java.gui.charts;

import java.util.List;

/**
 * Class that represents a bar chart data. This data is used to construct a new
 * bar chart on the screen.
 * 
 * @author Dinz
 *
 */
public class BarChart {

	/**
	 * Values of the elements in the bar chart.
	 */
	private List<XYValue> values;

	/**
	 * X axis label of the chart.
	 */
	private String xAxis;

	/**
	 * Y axis label of the chart.
	 */
	private String yAxis;

	/**
	 * Minimum Y value.
	 */
	private int minY;

	/**
	 * Maximum Y value.
	 */
	private int maxY;

	/**
	 * Difference between two adjacent Y values.
	 */
	private int difference;

	/**
	 * Constructs a new barchart data.
	 * 
	 * @param values
	 *            Values of the elements of the chart.
	 * @param xAxis
	 *            X axis label of the chart.
	 * @param yAxis
	 *            Y axis label of the chart.
	 * @param minY
	 *            Minimum Y value.
	 * @param maxY
	 *            Maximum Y value.
	 * @param difference
	 *            Difference between two adjacent Y values.
	 */
	public BarChart(List<XYValue> values, String xAxis, String yAxis, int minY, int maxY, int difference) {
		super();
		values.sort((x, y) -> Integer.compare(x.getX(), y.getX()));
		this.values = values;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.minY = minY;
		this.maxY = maxY;

		if ((maxY - minY) % difference != 0) {
			double dif = (maxY - minY) / (double) difference;
			int differenceModified = (int) Math.ceil(dif);
			while ((maxY - minY) % differenceModified != 0) {
				differenceModified++;
			}
			this.difference = differenceModified;
		} else {
			this.difference = difference;
		}
	}

	/**
	 * Gets the values of the bar chart data.
	 * 
	 * @return Values of the bar chart data.
	 */
	public List<XYValue> getValues() {
		return values;
	}

	/**
	 * Gets the X axis label.
	 * 
	 * @return X axis label.
	 */
	public String getxAxis() {
		return xAxis;
	}

	/**
	 * Gets the Y axis label.
	 * 
	 * @return Y axis label.
	 */
	public String getyAxis() {
		return yAxis;
	}

	/**
	 * Gets the minimum Y value.
	 * 
	 * @return Minimum Y value.
	 */
	public int getMinY() {
		return minY;
	}

	/**
	 * Gets the maximum Y value.
	 * 
	 * @return Maximum Y value.
	 */
	public int getMaxY() {
		return maxY;
	}

	/**
	 * Gets the difference between two adjacent Y values.
	 * 
	 * @return Difference between two adjacent Y values.
	 */
	public int getDifference() {
		return difference;
	}

}
