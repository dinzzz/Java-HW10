package hr.fer.zemris.java.gui.charts;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;

/**
 * Class that represents a bar chart component. This class loads the bar chart
 * on the screen based on its data.
 * 
 * @author Dinz
 *
 */
public class BarChartComponent extends JComponent {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -2290688286165650352L;

	/**
	 * Barchart data that is used for the display.
	 */
	private BarChart chart;

	/**
	 * Constructs a new bar chart component.
	 * 
	 * @param chart
	 *            Bar chart data.
	 */
	public BarChartComponent(BarChart chart) {
		this.chart = chart;
	}

	/**
	 * Gets the bar chart data.
	 * 
	 * @return Bar chart data.
	 */
	public BarChart getChart() {
		return this.chart;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintComponent(Graphics g) {
		String xAxis = this.getChart().getxAxis();
		String yAxis = this.getChart().getyAxis();
		int maxY = this.getChart().getMaxY();
		int minY = this.getChart().getMinY();
		int difference = this.getChart().getDifference();
		int numberOfIterations = (maxY - minY) / difference;

		g.setColor(getForeground());
		getWidth();

		FontMetrics fm = g.getFontMetrics();
		int xWidth = fm.stringWidth(xAxis);
		int yWidth = fm.stringWidth(yAxis);
		int height = fm.getAscent();

		g.drawString(xAxis, (this.getWidth() - xWidth) / 2, this.getHeight() - height);

		Graphics2D g2d = (Graphics2D) g;
		AffineTransform old = g2d.getTransform();
		
		AffineTransform at = new AffineTransform();		
		at.rotate(-Math.PI / 2);		
		g2d.setTransform(at);
		g.drawString(yAxis, -(this.getHeight() + yWidth) / 2, 2 * height);

		g2d.setTransform(old);

		int lineDifference = (this.getHeight() - 8 * height) / numberOfIterations;

		for (int i = minY, j = 0; i <= maxY; i += difference) {
			g.drawString(Integer.toString(i), 5 * height - fm.stringWidth(Integer.toString(i)),
					this.getHeight() - (5 * height + lineDifference * j)

			);
			int x1 = 6 * height;
			int y1 = this.getHeight() - (5 + 5 * height + lineDifference * j);
			int x2 = this.getWidth() - height;
			int y2 = this.getHeight() - (5 + 5 * height + lineDifference * j);
			g.drawLine(x1, y1, x2, y2);

			if (i == minY) {
				int x[] = { x2 - 5, x2 + 5, x2 - 5 };
				int y[] = { y2 - 5, y2, y2 + 5 };
				g.fillPolygon(x, y, 3);
			}
			j++;
		}

		int valuesSize = this.getChart().getValues().size();
		int xLineDifference = (this.getWidth() - 8 * height) / valuesSize;
		for (int k = 0; k < valuesSize; k++) {
			g.drawString(Integer.toString(this.getChart().getValues().get(k).getX()),
					(8 * height + xLineDifference * k), this.getHeight() - (3 * height)

			);

			int x1 = this.getWidth() - (2 * height + xLineDifference * k) - 2;
			int y1 = this.getHeight() - (4 * height);
			int x2 = this.getWidth() - (2 * height + xLineDifference * k) - 2;
			int y2 = (2 * height);
			if (k == 0) {
				g.drawLine(x1, y1, x2, y2);
				int xFix = 6 * height;
				x1 = x2 = xFix;

				int x[] = { xFix - 5, xFix, xFix + 5 };
				int y[] = { 2 * height, height, 2 * height };
				g.fillPolygon(x, y, 3);

			}
			int i = 0;
			if (k != 0) {
				i = valuesSize - k;
			}

			int helper = 0;
			if (minY < 0) {
				helper = Math.abs(minY);
			}
			if (minY > 0) {
				helper = -1 * minY;
			}

			int heightRec = lineDifference * ((this.getChart().getValues().get(i).getY()) + helper) / difference;
			int rectangleX = x1;
			int rectangleY = -heightRec + this.getHeight() - (5 + 5 * height);
			int widthRec = xLineDifference;
			g.drawRect(rectangleX, rectangleY, widthRec, heightRec);
			g.setColor(Color.CYAN);
			g.fillRect(rectangleX, rectangleY, widthRec, heightRec);
			g.setColor(getForeground());

			g.drawLine(x1, y1, x2, y2);

		}

	}

}
