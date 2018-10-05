package hr.fer.zemris.java.gui.calc;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;

import hr.fer.zemris.java.gui.layouts.RCPosition;

/**
 * Class that represents a single-digit number button. These buttons are used
 * for calculator implementation.
 * 
 * @author Dinz
 *
 */
public class NumberButton extends JButton {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 2394322594622575165L;

	/**
	 * Constructs a new number button with an appropriate listener.
	 * 
	 * @param value
	 *            Value of the number.
	 * @param position
	 *            Position of the button on the screen.
	 * @param container
	 *            Container where the buttons are stored.
	 * @param model
	 *            Calculator model.
	 */
	public NumberButton(int value, RCPosition position, Container container, CalcModelImpl model) {
		super(Integer.toString(value));
		this.setBackground(Color.MAGENTA);
		this.addActionListener(e -> {
			model.insertDigit(value);
		});
		container.add(this, position);
	}

}
