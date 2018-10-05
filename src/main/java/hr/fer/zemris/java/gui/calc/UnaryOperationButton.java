package hr.fer.zemris.java.gui.calc;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import hr.fer.zemris.java.gui.layouts.RCPosition;

/**
 * Class that represents an unary operation buttons. These buttons are used for
 * the Calculator implementation.
 * 
 * @author Dinz
 *
 */
public class UnaryOperationButton extends JButton {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1349582286597811447L;

	/**
	 * Container where the buttons will be stored.
	 */
	private Container container;

	/**
	 * Constructs a new unary operation button.
	 * 
	 * @param value
	 *            Operation that button represents.
	 * @param position
	 *            Position where the button will be added on the screen.
	 * @param container
	 *            Container where the buttons will be stored.
	 * @param model
	 *            Container model.
	 */
	public UnaryOperationButton(String value, RCPosition position, Container container, CalcModelImpl model) {
		super(value);
		this.container = container;
		this.setBackground(Color.ORANGE);
		addActionListener(value, model);
		container.add(this, position);
	}

	/**
	 * Method that adds an action to the unary operaion button.
	 * 
	 * @param value
	 *            Operation that buttons represents.
	 * @param model
	 *            Container model.
	 */
	private void addActionListener(String value, CalcModelImpl model) {

		if (value.equals("+/-")) {
			addSwitchListener(model);
		} else if (value.equals(".")) {
			addDecimalListener(model);
		} else if (value.equals("1/x")) {
			addReciListener(model);
		}

	}

	/**
	 * Method that adds a listener to the "." button.
	 * 
	 * @param model
	 *            Calculator model.
	 */
	private void addDecimalListener(CalcModelImpl model) {
		this.addActionListener(e -> {
			model.insertDecimalPoint();
		});

	}

	/**
	 * Method that adds a listener to the "+/-" button.
	 * 
	 * @param model
	 *            Calculator model.
	 */
	private void addSwitchListener(CalcModelImpl model) {
		this.addActionListener(e -> {
			model.swapSign();
		});

	}

	/**
	 * Method that adds a listener to the "1/x" button.
	 * 
	 * @param model
	 *            Calculator model.
	 */
	private void addReciListener(CalcModelImpl model) {
		this.addActionListener(e -> {
			try {
				model.setValue(Math.pow(model.getValue(), -1));
			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(container, "Result is NaN", "Invalid operation",
						JOptionPane.ERROR_MESSAGE);
			}
		});

	}
}
