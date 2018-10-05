package hr.fer.zemris.java.gui.charts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Class that executes the output of the bar chart to the screen.
 * 
 * @author Dinz
 *
 */
public class BarChartDemo extends JFrame {
	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -1531898701761032216L;

	/**
	 * Constructs a new bar chart demo class.
	 * 
	 * @param chart
	 *            Bar chart data.
	 * @param path
	 *            Path where the data is stored.
	 */
	public BarChartDemo(BarChart chart, Path path) {
		super();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Bar Chart Demonstration");
		setLocation(50, 50);
		setSize(600, 600);
		this.setMinimumSize(new Dimension(400, 300));
		initGUI(chart, path);

	}

	/**
	 * Main method that executes the class.
	 * 
	 * @param args
	 *            Arguments from the command line.
	 */
	public static void main(String[] args) {
		
		if(args.length != 1) {
			System.out.println("Invalid number of arguments.");
			System.exit(0);
		}

		Path path = Paths.get(args[0]);
		BarChart chart = parseFile(path);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				BarChartDemo barChart = new BarChartDemo(chart, path);
				barChart.setVisible(true);
			}
		});
	}

	/**
	 * Method that parses a input file data into a valid bar chart data.
	 * 
	 * @param path
	 *            Path of the file.
	 * @return Valid bar chart data.
	 */
	private static BarChart parseFile(Path path) {
		try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
			String xAxis = br.readLine();
			String yAxis = br.readLine();

			String valuesStr = br.readLine();
			List<XYValue> values = valuesToList(valuesStr);

			String yMinStr = br.readLine();
			int yMin = Integer.parseInt(yMinStr);
			String yMaxStr = br.readLine();
			int yMax = Integer.parseInt(yMaxStr);		
			String diffStrS = br.readLine();
			int diff = Integer.parseInt(diffStrS);
			if(diff <= 0) {
				System.out.println("Difference between adjacent Y values must not be less then zero.");
				System.exit(0);
			}

			return new BarChart(values, xAxis, yAxis, yMin, yMax, diff);

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("File not found");
			System.exit(0);
		} catch (NumberFormatException ex) {
			System.out.println("Invalid input.");
			System.exit(0);
		} catch (IllegalArgumentException ex) {
			System.out.println("Invalid input.");
			System.exit(0);
		}

		return null;
	}

	/**
	 * Method that transforms the values from the string format into the valid
	 * values list.
	 * 
	 * @param valuesStr
	 *            Values in the string format.
	 * @return List of values.
	 */
	private static List<XYValue> valuesToList(String valuesStr) {
		List<XYValue> values = new ArrayList<>();

		String[] split = valuesStr.split("\\s+");
		for (int i = 0; i < split.length; i++) {
			String[] valueSplit = split[i].split(",");

			if (valueSplit.length != 2) {
				throw new IllegalArgumentException("Invalid input.");
			}

			int x = Integer.parseInt(valueSplit[0]);
			int y = Integer.parseInt(valueSplit[1]);
			values.add(new XYValue(x, y));
		}

		return values;
	}

	/**
	 * Method that initializes the graphical user interface.
	 * 
	 * @param chart
	 *            Bar chart data.
	 * @param path
	 *            Path of the file.
	 */
	public void initGUI(BarChart chart, Path path) {
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(Color.WHITE);
		JComponent barChart = new BarChartComponent(chart);
		getContentPane().add(barChart, BorderLayout.CENTER);
		getContentPane().add(new JLabel(path.toAbsolutePath().toString(), SwingConstants.CENTER), BorderLayout.NORTH);

	}
}
