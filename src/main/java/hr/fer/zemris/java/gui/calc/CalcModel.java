package hr.fer.zemris.java.gui.calc;

import java.util.function.DoubleBinaryOperator;

/**
 * Interface that represents a calculator model which contains the base logic
 * for the functionality of the real calculator.
 * 
 * @author Dinz
 *
 */
public interface CalcModel {
	/**
	 * Adds a value listener to the calculator model.
	 * 
	 * @param l
	 *            Listener.
	 */
	void addCalcValueListener(CalcValueListener l);

	/**
	 * Removes a value listener from the calculator model.
	 * 
	 * @param l
	 *            Listener.
	 */
	void removeCalcValueListener(CalcValueListener l);

	/**
	 * Transforms the current presented value on the calculator to the string
	 * format.
	 * 
	 * @return String format of the current value.
	 */
	String toString();

	/**
	 * Gets the current value on the calculator.
	 * 
	 * @return Current value on the calculator.
	 */
	double getValue();

	/**
	 * Sets the current value on the calculator.
	 * 
	 * @param value
	 *            Value to be set.
	 */
	void setValue(double value);

	/**
	 * Clears the current value on the calculator and sets it to zero.
	 */
	void clear();

	/**
	 * Resets the state of the calculator.
	 */
	void clearAll();

	/**
	 * Swaps the positivity/negativity of the current value.
	 */
	void swapSign();

	/**
	 * Inserts a decimal point to the calculator's current value.
	 */
	void insertDecimalPoint();

	/**
	 * Inserts a digit to the calculator's screen/value.
	 * 
	 * @param digit
	 *            Digit to be inserted.
	 */
	void insertDigit(int digit);

	/**
	 * Checks if there is any active operand in the calculator.
	 * 
	 * @return True if there is an active operand in the calculator, false
	 *         otherwise.
	 */
	boolean isActiveOperandSet();

	/**
	 * Gets the current active operand in the calculator.
	 * 
	 * @return Active operand.
	 */
	double getActiveOperand();

	/**
	 * Sets the active operand in the calculator.
	 * 
	 * @param activeOperand
	 *            Active operand.
	 */
	void setActiveOperand(double activeOperand);

	/**
	 * Clears the active operand from the calculator.
	 */
	void clearActiveOperand();

	/**
	 * Gets the currently pending operation of the calculator.
	 * 
	 * @return Currently pending operator.
	 */
	DoubleBinaryOperator getPendingBinaryOperation();

	/**
	 * Sets the current binary operator of the calculator.
	 * 
	 * @param op
	 *            Binary operator.
	 */
	void setPendingBinaryOperation(DoubleBinaryOperator op);
}