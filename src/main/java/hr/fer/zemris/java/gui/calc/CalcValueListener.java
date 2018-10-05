package hr.fer.zemris.java.gui.calc;

/**
 * Interface that represents a calculator value listener which is then notified
 * when the value on the calculator changes.
 * 
 * @author Dinz
 *
 */
public interface CalcValueListener {
	/**
	 * Method that notifies the listeners when the calculator value changes.
	 * 
	 * @param model
	 *            Calculator model.
	 */
	void valueChanged(CalcModel model);
}