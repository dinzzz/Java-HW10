package hr.fer.zemris.java.gui.calc;

import java.awt.Color;
import java.awt.Container;
import java.util.function.DoubleBinaryOperator;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import hr.fer.zemris.java.gui.layouts.RCPosition;

/**
 * Class that represents a binary operation button. This button is a part of a
 * calculator which handles binary operations like adding, subtraction, division
 * and multiplication.
 * 
 * @author Dinz
 *
 */
public class BinaryOperationButton extends JButton {
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 6937855466390585231L;

	/**
	 * Container where the button is contained.
	 */
	private Container container;

	/**
	 * Constructs a new binary operation button.
	 * 
	 * @param value
	 *            Type of the operation in string format.
	 * @param position
	 *            Position of the button.
	 * @param container
	 *            Container where the button is contained.
	 * @param model
	 *            Calculator model.
	 */
	public BinaryOperationButton(String value, RCPosition position, Container container, CalcModelImpl model) {
		super(value);
		this.container = container;
		this.setBackground(Color.CYAN);
		addActionListener(value, model);
		container.add(this, position);
	}

	/**
	 * Method that adds action listeners to all binary operation buttons.
	 * 
	 * @param value
	 *            Type of the operation.
	 * @param model
	 *            Calculator model.
	 */
	private void addActionListener(String value, CalcModelImpl model) {
		if (value.equals("+")) {
			addPlusListener(model);
		} else if (value.equals("=")) {
			addEqualsListener(model);
		} else if (value.equals("-")) {
			addMinusListener(model);
		} else if (value.equals("*")) {
			addMultiplyListener(model);
		} else if (value.equals("/")) {
			addDivideListener(model);
		}

	}

	/**
	 * Adds the listener to the "=" button.
	 * 
	 * @param model
	 *            Calculator model.
	 */
	private void addEqualsListener(CalcModelImpl model) {
		this.addActionListener(e -> {
			if (model.isActiveOperandSet()) {
				try {
					model.setValue(model.getPendingBinaryOperation().applyAsDouble(model.getActiveOperand(),
							model.getValue()));
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(container, "Result is NaN", "Invalid operation",
							JOptionPane.ERROR_MESSAGE);
				}
				model.clearActiveOperand();
				model.setPendingBinaryOperation(null);
			}
		});

	}

	/**
	 * Adds the listener to the "+" button.
	 * 
	 * @param model
	 *            Calculator model.
	 */
	private void addPlusListener(CalcModelImpl model) {
		this.addActionListener(e -> {
			if (model.getPendingBinaryOperation() != null) {
				try {
					model.setValue(model.getPendingBinaryOperation().applyAsDouble(model.getActiveOperand(),
							model.getValue()));
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(container, "Result is NaN", "Invalid operation",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			model.setActiveOperand(model.getValue());
			model.setPendingBinaryOperation(Double::sum);
			model.setValue(0);
		});
	}

	/**
	 * Adds the listener to the "-" button.
	 * 
	 * @param model
	 *            Calculator model.
	 */
	private void addMinusListener(CalcModelImpl model) {
		this.addActionListener(e -> {
			if (model.getPendingBinaryOperation() != null) {
				try {
					model.setValue(model.getPendingBinaryOperation().applyAsDouble(model.getActiveOperand(),
							model.getValue()));
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(container, "Result is NaN", "Invalid operation",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			model.setActiveOperand(model.getValue());
			model.setPendingBinaryOperation(new Subtract());
			model.setValue(0);
		});
	}

	/**
	 * Adds the listener to the "/" button.
	 * 
	 * @param model
	 *            Calculator model.
	 */
	private void addDivideListener(CalcModelImpl model) {
		this.addActionListener(e -> {
			if (model.getPendingBinaryOperation() != null) {
				try {
					model.setValue(model.getPendingBinaryOperation().applyAsDouble(model.getActiveOperand(),
							model.getValue()));
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(container, "Result is NaN", "Invalid operation",
							JOptionPane.ERROR_MESSAGE);
				}
			}

			model.setActiveOperand(model.getValue());
			model.setPendingBinaryOperation(new Divide());
			model.setValue(0);

		});

	}

	/**
	 * Adds the listener to the "*" button.
	 * 
	 * @param model
	 *            Calculator model.
	 */
	private void addMultiplyListener(CalcModelImpl model) {
		this.addActionListener(e -> {
			if (model.getPendingBinaryOperation() != null) {
				try {
					model.setValue(model.getPendingBinaryOperation().applyAsDouble(model.getActiveOperand(),
							model.getValue()));
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(container, "Result is NaN", "Invalid operation",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			model.setActiveOperand(model.getValue());
			model.setPendingBinaryOperation(new Multiply());
			model.setValue(0);
		});
	}

	/**
	 * Class that represents a divide double binary operator which is used for
	 * division in a calculator.
	 * 
	 * @author Dinz
	 *
	 */
	public static class Divide implements DoubleBinaryOperator {

		/**
		 * Applies the division operation on two operands.
		 */
		@Override
		public double applyAsDouble(double left, double right) {
			return left / right;
		}

	}

	/**
	 * Class that represents a subtract double binary operator which is used for
	 * subtraction in a calculator.
	 * 
	 * @author Dinz
	 *
	 */
	public static class Subtract implements DoubleBinaryOperator {

		/**
		 * Applies the subtraction operation on two operands.
		 */
		@Override
		public double applyAsDouble(double left, double right) {
			return left - right;
		}

	}

	/**
	 * Class that represents a multiply double binary operator which is used for
	 * subtraction in a calculator.
	 * 
	 * @author Dinz
	 *
	 */
	public static class Multiply implements DoubleBinaryOperator {

		/**
		 * Applies the multiplication operation on two operands.
		 */
		@Override
		public double applyAsDouble(double left, double right) {
			return left * right;
		}

	}

}
