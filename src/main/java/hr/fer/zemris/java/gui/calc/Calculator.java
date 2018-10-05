package hr.fer.zemris.java.gui.calc;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.function.DoubleBinaryOperator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import hr.fer.zemris.java.gui.layouts.CalcLayout;

import hr.fer.zemris.java.gui.layouts.RCPosition;

/**
 * Class that represents a calculator and all of its funcionality. It replicates
 * the common calculator on the operation systems and constructs an interactive
 * graphical interface when ran.
 * 
 * @author Dinz
 *
 */
public class Calculator extends JFrame {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 5016122500695877672L;

	/**
	 * Checks if the calculators state has been inverted.
	 */
	private boolean inverted = false;

	/**
	 * Constructs a new calculator.
	 */
	public Calculator() {
		super();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Calculator");
		setLocation(50, 50);
		setSize(600, 600);
		this.setMinimumSize(new Dimension(400, 300));
		initGUI();

	}

	/**
	 * Main method that runs the calculator.
	 * 
	 * @param args
	 *            Arguments from the command line.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Calculator calculator = new Calculator();
				calculator.setVisible(true);
			}
		});
	}

	/**
	 * Method that initializes the graphical user interface and adds all of the
	 * components of the calculator to the screen.
	 */
	private void initGUI() {
		getContentPane().setLayout(new CalcLayout(5));
		getContentPane().setBackground(Color.WHITE);

		CalcModelImpl model = new CalcModelImpl();

		JLabel displayLabel = new JLabel("0");
		displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		displayLabel.setBackground(Color.LIGHT_GRAY);
		displayLabel.setOpaque(true);

		JCheckBox inv = new JCheckBox("inv");
		inv.addActionListener(e -> {
			if (!inverted) {
				inverted = true;
			} else {
				inverted = false;
			}
		});
		getContentPane().add(inv, new RCPosition(5, 7));

		model.addCalcValueListener(e -> {
			displayLabel.setText(model.toString());
		});

		getContentPane().add(displayLabel, new RCPosition(1, 1));

		addNumbers(getContentPane(), model);
		addUnaryOperations(getContentPane(), model);
		addBinaryOperations(getContentPane(), model);
		addSpecialOperations(getContentPane(), model);
		addSwitchableOperations(getContentPane(), model);

	}

	/**
	 * Method that adds special operation buttons to the calculator.
	 * 
	 * @param contentPane
	 *            Container where the buttons will be stored.
	 * @param model
	 *            Calculator model.
	 */
	private void addSpecialOperations(Container contentPane, CalcModelImpl model) {
		new SpecialOperationButton("clr", new RCPosition(1, 7), contentPane, model);
		new SpecialOperationButton("res", new RCPosition(2, 7), contentPane, model);
		new SpecialOperationButton("push", new RCPosition(3, 7), contentPane, model);
		new SpecialOperationButton("pop", new RCPosition(4, 7), contentPane, model);

	}

	/**
	 * Method that adds binary operation buttons to the calculator.
	 * 
	 * @param contentPane
	 *            Container where the buttons will be stored.
	 * @param model
	 *            Calculator model.
	 */
	private void addBinaryOperations(Container contentPane, CalcModelImpl model) {
		new BinaryOperationButton("+", new RCPosition(5, 6), contentPane, model);
		new BinaryOperationButton("-", new RCPosition(4, 6), contentPane, model);
		new BinaryOperationButton("*", new RCPosition(3, 6), contentPane, model);
		new BinaryOperationButton("/", new RCPosition(2, 6), contentPane, model);
		new BinaryOperationButton("=", new RCPosition(1, 6), contentPane, model);

	}

	/**
	 * Method that adds number buttons to the calculator.
	 * 
	 * @param contentPane
	 *            Container where the buttons will be stored.
	 * @param model
	 *            Calculator model.
	 */
	private void addNumbers(Container contentPane, CalcModelImpl model) {
		new NumberButton(0, new RCPosition(5, 3), contentPane, model);
		new NumberButton(1, new RCPosition(4, 3), contentPane, model);
		new NumberButton(2, new RCPosition(4, 4), contentPane, model);
		new NumberButton(3, new RCPosition(4, 5), contentPane, model);
		new NumberButton(4, new RCPosition(3, 3), contentPane, model);
		new NumberButton(5, new RCPosition(3, 4), contentPane, model);
		new NumberButton(6, new RCPosition(3, 5), contentPane, model);
		new NumberButton(7, new RCPosition(2, 3), contentPane, model);
		new NumberButton(8, new RCPosition(2, 4), contentPane, model);
		new NumberButton(9, new RCPosition(2, 5), contentPane, model);
	}

	/**
	 * Method that adds unary operation buttons to the calculator.
	 * 
	 * @param contentPane
	 *            Container where the buttons will be stored.
	 * @param model
	 *            Calculator model.
	 */
	private void addUnaryOperations(Container contentPane, CalcModelImpl model) {
		new UnaryOperationButton("1/x", new RCPosition(2, 1), contentPane, model);
		new UnaryOperationButton("+/-", new RCPosition(5, 4), contentPane, model);
		new UnaryOperationButton(".", new RCPosition(5, 5), contentPane, model);

	}

	/**
	 * Method that adds switchable operation buttons to the calculator. These
	 * buttons, when inverted have different calculation operations.
	 * 
	 * @param contentPane
	 *            Container where the buttons will be stored.
	 * @param model
	 *            Calculator model.
	 */
	private void addSwitchableOperations(Container contentPane, CalcModelImpl model) {
		new SwitchableOperationsButton("sin", new RCPosition(2, 2), contentPane, model);
		new SwitchableOperationsButton("cos", new RCPosition(3, 2), contentPane, model);
		new SwitchableOperationsButton("tan", new RCPosition(4, 2), contentPane, model);
		new SwitchableOperationsButton("ctg", new RCPosition(5, 2), contentPane, model);
		new SwitchableOperationsButton("ln", new RCPosition(4, 1), contentPane, model);
		new SwitchableOperationsButton("log", new RCPosition(3, 1), contentPane, model);
		new SwitchableOperationsButton("x^n", new RCPosition(5, 1), contentPane, model);

	}

	/**
	 * Class that represents buttons which can be inverted in the calculator.
	 * 
	 * @author Dinz
	 *
	 */
	private class SwitchableOperationsButton extends JButton {

		/**
		 * Serial
		 */
		private static final long serialVersionUID = 1108521182749995629L;

		/**
		 * Container where the buttons are stored.
		 */
		private Container container;

		/**
		 * Constructs a new switchable operation button.
		 * 
		 * @param value
		 *            Operation of the button.
		 * @param position
		 *            Position of the button on the interface.
		 * @param container
		 *            Container where the buttons are stored.
		 * @param model
		 *            Calculator model.
		 */
		public SwitchableOperationsButton(String value, RCPosition position, Container container, CalcModelImpl model) {
			super(value);
			SwitchableOperationsButton.this.container = container;
			SwitchableOperationsButton.this.setBackground(Color.YELLOW);
			addActionListener(value, model);
			container.add(SwitchableOperationsButton.this, position);
		}

		/**
		 * Method that adds an action listener to the switchable operation button.
		 * 
		 * @param value
		 *            Operation of the button.
		 * @param model
		 *            Calculator model.
		 */
		private void addActionListener(String value, CalcModelImpl model) {
			if (value.equals("sin")) {
				addSinusListener(model);
			} else if (value.equals("cos")) {
				addCosListener(model);
			} else if (value.equals("tan")) {
				addTanListener(model);
			} else if (value.equals("ctg")) {
				addCtgListener(model);
			} else if (value.equals("log")) {
				addLogListener(model);
			} else if (value.equals("ln")) {
				addLnListener(model);
			} else if (value.equals("x^n")) {
				addPowerListener(model);
			}

		}

		/**
		 * Adds a listener to the "sin" button. Whn inverted, it becomes "arcsin"
		 * operation.
		 * 
		 * @param model
		 *            Calculator model.
		 */
		private void addSinusListener(CalcModelImpl model) {
			this.addActionListener(e -> {
				if (!inverted) {
					model.setValue(Math.sin(model.getValue()));
				} else {
					try {
						model.setValue(Math.asin(model.getValue()));
					} catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(container, "Result is NaN", "Invalid operation",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}

		/**
		 * Adds a listener to the "cos" button. When inverted it becomes "arccos"
		 * operation.
		 * 
		 * @param model
		 *            Calculator model.
		 */
		private void addCosListener(CalcModelImpl model) {
			this.addActionListener(e -> {
				if (!inverted) {
					model.setValue(Math.cos(model.getValue()));
				} else {
					try {
						model.setValue(Math.acos(model.getValue()));
					} catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(container, "Result is NaN", "Invalid operation",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});

		}

		/**
		 * Adds a listener to the "tan" button. When inverted, it becomes a "arctan"
		 * operation.
		 * 
		 * @param model
		 *            Calculator model-
		 */
		private void addTanListener(CalcModelImpl model) {
			this.addActionListener(e -> {
				if (!inverted) {
					model.setValue(Math.tan(model.getValue()));
				} else {
					try {
						model.setValue(Math.atan(model.getValue()));
					} catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(container, "Result is NaN", "Invalid operation",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});

		}

		/**
		 * Adds a listener to the "ctg" button. When inverted, it becomes a "arcctg"
		 * operation.
		 * 
		 * @param model
		 *            Calculator model.
		 */
		private void addCtgListener(CalcModelImpl model) {
			this.addActionListener(e -> {
				if (!inverted) {
					try {
						model.setValue(Math.pow(Math.tan(model.getValue()), -1));
					} catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(container, "Result is NaN", "Invalid operation",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					try {
						model.setValue(Math.PI / 2 - Math.atan(model.getValue()));
					} catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(container, "Result is NaN", "Invalid operation",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});

		}

		/**
		 * Adds a listener to the "log" button. When inverted it became a "10^n"
		 * operation.
		 * 
		 * @param model
		 *            Calculator model.
		 */
		private void addLogListener(CalcModelImpl model) {
			this.addActionListener(e -> {
				if (!inverted) {
					try {
						model.setValue(Math.log10(model.getValue()));
					} catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(container, "Result is NaN", "Invalid operation",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					model.setValue(Math.pow(10, model.getValue()));
				}
			});

		}

		/**
		 * Adds a listener to the "ln" button. When inverted it becomes "e^n" operation.
		 * 
		 * @param model
		 *            Calculator model.
		 */
		private void addLnListener(CalcModelImpl model) {
			this.addActionListener(e -> {
				if (!inverted) {
					try {
						model.setValue(Math.log(model.getValue()));
					} catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(container, "Result is NaN", "Invalid operation",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					model.setValue(Math.pow(Math.E, model.getValue()));
				}
			});

		}

		/**
		 * Adds a listener to the "x^n" button. When inverted it becomes a "x^-n"
		 * operation.
		 * 
		 * @param model
		 *            Calculator model.
		 */
		private void addPowerListener(CalcModelImpl model) {
			this.addActionListener(e -> {
				if (!inverted) {
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
					model.setPendingBinaryOperation(new Power());
					model.setValue(0);
				} else {
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
					model.setPendingBinaryOperation(new Root());
					model.setValue(0);
				}
			});

		}

	}

	/**
	 * Class that represents a double binary operator for the basic power operation.
	 * 
	 * @author Dinz
	 *
	 */
	public static class Power implements DoubleBinaryOperator {

		/**
		 * Method that applies the power operation on two given operands.
		 */
		@Override
		public double applyAsDouble(double left, double right) {
			return Math.pow(left, right);
		}

	}

	/**
	 * Class that represents a double binary operator for the rooting operation.
	 * 
	 * @author Dinz
	 *
	 */
	public static class Root implements DoubleBinaryOperator {

		/**
		 * Method that applies the rooting operation on two given operands.
		 */
		@Override
		public double applyAsDouble(double left, double right) {
			return Math.pow(left, -1 * right);
		}

	}

}
