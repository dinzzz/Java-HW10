package hr.fer.zemris.java.gui.calc;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import java.util.function.DoubleBinaryOperator;

/**
 * Class that represents a concrete implementation of the calculator model.
 * 
 * @author Dinz
 *
 */
public class CalcModelImpl implements CalcModel {

	/**
	 * Currently shown number on the calculator.
	 */
	private String currentNumber = null;

	/**
	 * Currently pending operator of the calculator.
	 */
	private DoubleBinaryOperator pendingOperation = null;

	/**
	 * Currently active operand of the calculator.
	 */
	private double activeOperand;

	/**
	 * Flag that checks if the active operand is set.
	 */
	private boolean activeOperandSet = false;

	/**
	 * List of listeners of the calculator model.
	 */
	private List<CalcValueListener> listeners = new ArrayList<>();

	/**
	 * Stack in the calculator model.
	 */
	private Stack<Double> stack = new Stack<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addCalcValueListener(CalcValueListener l) {
		if (!listeners.contains(l)) {
			listeners.add(l);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeCalcValueListener(CalcValueListener l) {
		if (listeners.contains(l)) {
			listeners.remove(l);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getValue() {
		if (this.currentNumber == null) {
			return 0.0;
		} else {
			return Double.parseDouble(this.currentNumber);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(double value) {
		if (!Double.isNaN(value) && value != Double.NEGATIVE_INFINITY && value != Double.POSITIVE_INFINITY) {
			if (value % 1 == 0) {
				this.currentNumber = Integer.toString((int) value);
			} else {
				this.currentNumber = Double.toString(value);
			}
		} else {
			throw new IllegalArgumentException();
		}
		notifyListeners();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		this.currentNumber = null;
		notifyListeners();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearAll() {
		this.currentNumber = null;
		this.activeOperand = 0.0;
		this.activeOperandSet = false;
		this.pendingOperation = null;

		notifyListeners();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void swapSign() {
		if (currentNumber == null) {
			return;
		}

		if (this.currentNumber.startsWith("-")) {
			this.currentNumber = this.currentNumber.replace("-", "");
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("-");
			sb.append(this.currentNumber);
			this.currentNumber = sb.toString();
		}

		notifyListeners();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertDecimalPoint() {
		if (this.currentNumber == null) {
			this.currentNumber = "0";
		}
		if (this.currentNumber.contains(".")) {
			return;
		}
		this.currentNumber = this.currentNumber.concat(".");

		notifyListeners();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertDigit(int digit) {
		if (this.currentNumber == null) {
			this.currentNumber = Integer.toString(digit);
		} else if (Double.parseDouble(currentNumber) < Double.MAX_VALUE / 10.0) {
			this.currentNumber = this.currentNumber.concat(Integer.toString(digit));
		}

		notifyListeners();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isActiveOperandSet() {
		return this.activeOperandSet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getActiveOperand() {
		if (!isActiveOperandSet()) {
			throw new IllegalStateException("Active operand not set.");
		}
		return this.activeOperand;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setActiveOperand(double activeOperand) {
		this.activeOperand = activeOperand;
		this.activeOperandSet = true;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearActiveOperand() {
		this.activeOperand = 0;
		this.activeOperandSet = false;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DoubleBinaryOperator getPendingBinaryOperation() {
		return this.pendingOperation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPendingBinaryOperation(DoubleBinaryOperator op) {
		this.pendingOperation = op;

	}

	/**
	 * Pushes a value to the calculator's stack.
	 * 
	 * @param value
	 *            Value to be pushed.
	 */
	public void push(double value) {
		stack.push(value);
	}

	/**
	 * Pops the value from the calculator's stack.
	 * 
	 * @return Popped value.
	 */
	public double pop() {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.pop();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		if (this.currentNumber == null) {
			return "0";
		} else {
			if (isInteger(this.currentNumber)) {
				int cn = Integer.parseInt(currentNumber);
				if (cn == 0 && this.currentNumber.startsWith("-")) {
					return "-0";
				}
				return Integer.toString(cn);
			}
			if (isDouble(this.currentNumber)) {
				double cn = Double.parseDouble(currentNumber);
				return Double.toString(cn);
			}
		}
		return this.currentNumber;
	}

	/**
	 * Checks if the string value is integer.
	 * 
	 * @param s
	 *            String value.
	 * @return True if the string is parseable to integer, false otherwise.
	 */
	private static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	/**
	 * Checks if the string value is double.
	 * 
	 * @param s
	 *            String value.
	 * @return True if the string is parseable to double, false otherwise.
	 */
	private static boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	/**
	 * Method that notifies all the listeners about the value change of the
	 * calculator.
	 */
	private void notifyListeners() {
		for (CalcValueListener listener : listeners) {
			listener.valueChanged(this);
		}
	}

}
