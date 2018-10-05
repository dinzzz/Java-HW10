package hr.fer.zemris.java.gui.layouts;

/**
 * Class that represents a calculator layout exception. It is thrown when there
 * is something wrong with setting up the components in the calculator layout.
 * 
 * @author Dinz
 *
 */
public class CalcLayoutException extends RuntimeException {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -3503721957041493250L;

	/**
	 * Constructs a new calculator layout exception.
	 */
	public CalcLayoutException() {
		super();
	}

	/**
	 * Constructs a new calculator layout exception with appropriate message.
	 * 
	 * @param message
	 *            Message of the exception.
	 */
	public CalcLayoutException(String message) {
		super(message);
	}
}
