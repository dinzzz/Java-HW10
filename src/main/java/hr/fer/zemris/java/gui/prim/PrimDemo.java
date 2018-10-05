package hr.fer.zemris.java.gui.prim;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Class that represents a program which showcases the lists of the prime
 * numbers to the display.
 * 
 * @author Dinz
 *
 */
public class PrimDemo extends JFrame {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 2552614965736703542L;

	/**
	 * Constructs a new primary list class.
	 */
	public PrimDemo() {
		super();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Primary numbers");
		setLocation(50, 50);
		setSize(600, 600);
		this.setMinimumSize(new Dimension(400, 300));
		initGUI();
	}

	/**
	 * Method that initializes graphical user interface and displays the lists.
	 */
	private void initGUI() {
		getContentPane().setLayout(new BorderLayout());

		PrimListModel model = new PrimListModel();

		JList<Integer> list1 = new JList<>(model);
		JList<Integer> list2 = new JList<>(model);

		JButton next = new JButton("Next");
		next.addActionListener(e -> {
			model.next();
		});

		JPanel central = new JPanel(new GridLayout(1, 0));
		central.add(new JScrollPane(list1));
		central.add(new JScrollPane(list2));

		getContentPane().add(central, BorderLayout.CENTER);
		getContentPane().add(next, BorderLayout.PAGE_END);

	}

	/**
	 * Main method that runs the program.
	 * 
	 * @param args
	 *            Arguments from the command line.
	 */
	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			JFrame frame = new PrimDemo();
			frame.pack();
			frame.setVisible(true);
		});
	}
}
