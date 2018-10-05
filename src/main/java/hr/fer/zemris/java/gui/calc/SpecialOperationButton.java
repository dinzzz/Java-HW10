package hr.fer.zemris.java.gui.calc;

import java.awt.Color;
import java.awt.Container;
import java.util.EmptyStackException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import hr.fer.zemris.java.gui.layouts.RCPosition;

/**
 * Class that represents a special operator button. These buttons are used in a
 * calculator implementation.
 * 
 * @author Dinz
 *
 */
public class SpecialOperationButton extends JButton {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 5586007123096514773L;

	/**
	 * Container where the buttons are stored.
	 */
	private Container container;

	/**
	 * Constructs a new special operation button.
	 * 
	 * @param value
	 *            Operation that button represents.
	 * @param position
	 *            Position of the button on the screen.
	 * @param container
	 *            Container where the buttons are stored.
	 * @param model
	 *            Calculator model.
	 */
	public SpecialOperationButton(String value, RCPosition position, Container container, CalcModelImpl model) {
		super(value);
		this.container = container;
		this.setBackground(Color.RED);
		addActionListener(value, model);
		container.add(this, position);
	}

	/**
	 * Method that adds an action listener to the special operation button.
	 * 
	 * @param value
	 *            Operation that button represents.
	 * @param model
	 *            Calculator model.
	 */
	private void addActionListener(String value, CalcModelImpl model) {
		if (value.equals("clr")) {
			addClearListener(model);
		} else if (value.equals("res")) {
			addResetListener(model);
		} else if (value.equals("push")) {
			addPushListener(model);
		} else if (value.equals("pop")) {
			addPopListener(model);
		}

	}

	/**
	 * Method that adds a listener to the "pop" button.
	 * 
	 * @param model
	 *            Calculator model.
	 */
	private void addPopListener(CalcModelImpl model) {
		this.addActionListener(e -> {
			try {
				model.setValue(model.pop());
			} catch (EmptyStackException ex) {
				JOptionPane.showMessageDialog(container, "Stack is empty.", "Empty stack!", JOptionPane.ERROR_MESSAGE);
			}
		});

	}

	/**
	 * Method that adds a listener to the "push" button.
	 * 
	 * @param model
	 *            Calculator model.
	 */
	private void addPushListener(CalcModelImpl model) {
		this.addActionListener(e -> {
			model.push(model.getValue());
			model.setValue(0);
		});

	}

	/**
	 * Method that adds a listener to the "res" button.
	 * 
	 * @param model
	 *            Calculator model.
	 */
	private void addResetListener(CalcModelImpl model) {
		this.addActionListener(e -> {
			model.clearAll();
		});

	}

	/**
	 * Method that adds a listener to the "clr" button.
	 * 
	 * @param model
	 *            Calculator model.
	 */
	private void addClearListener(CalcModelImpl model) {
		this.addActionListener(e -> {
			model.clear();
		});

	}
}
