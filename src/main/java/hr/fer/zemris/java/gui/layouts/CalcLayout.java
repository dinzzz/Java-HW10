package hr.fer.zemris.java.gui.layouts;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class that represents a calculator layout. This layout is used while creating
 * displays when the components have to be aligned just as the basic calculator
 * is.
 * 
 * @author Dinz
 *
 */
public class CalcLayout implements LayoutManager2 {

	/**
	 * Space between components.
	 */
	private int space;

	/**
	 * Components stored in the layout.
	 */
	private Map<Component, RCPosition> components = new HashMap<>();

	/**
	 * Minimum coordinate of the row.
	 */
	private static final int ROW_MIN = 1;

	/**
	 * Maximum coordinate of the row.
	 */
	private static final int ROW_MAX = 5;

	/**
	 * Minimum coordinate of the column.
	 */
	private static final int COLUMN_MIN = 1;

	/**
	 * Maximum coordinate of the column.
	 */
	private static final int COLUMN_MAX = 7;

	/**
	 * Maximum number of components in the layout.
	 */
	private static final int MAX_COMP = 31;

	/**
	 * COnsutrcts a new calculator layout with spacing set to 0.
	 */
	public CalcLayout() {
		this(0);
	}

	/**
	 * Constructs a new calculator layout with desired space.
	 * 
	 * @param space
	 *            Spacing between components.
	 */
	public CalcLayout(int space) {
		this.space = space;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addLayoutComponent(String name, Component comp) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeLayoutComponent(Component comp) {
		Objects.requireNonNull(comp);
		components.remove(comp);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dimension preferredLayoutSize(Container parent) {
		Objects.requireNonNull(parent);
		double width = 0;
		double height = 0;

		for (Component comp : components.keySet()) {
			if (comp.getPreferredSize() == null) {
				continue;
			}
			if (comp.getPreferredSize().getWidth() > width) {
				if (!components.get(comp).equals(new RCPosition(1, 1))) {
					width = comp.getPreferredSize().getWidth();
				} else {
					width = (comp.getPreferredSize().getWidth() - 4 * space) / 5;
				}
			}
			if (comp.getPreferredSize().getHeight() > height) {
				height = comp.getPreferredSize().getHeight();
			}
		}

		Insets insets = parent.getInsets();
		double prefferedWidth = insets.left + width * COLUMN_MAX + space * (COLUMN_MAX - 1) + insets.right;
		double prefferedHeight = insets.bottom + height * ROW_MAX + space * (ROW_MAX - 1) + insets.top;

		return new Dimension((int) prefferedWidth, (int) prefferedHeight);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dimension minimumLayoutSize(Container parent) {
		Objects.requireNonNull(parent);
		double width = 0;
		double height = 0;

		for (Component comp : components.keySet()) {
			if (comp.getMinimumSize() == null) {
				continue;
			}
			if (comp.getMinimumSize().getWidth() > width) {
				if (!components.get(comp).equals(new RCPosition(1, 1))) {
					width = comp.getMinimumSize().getWidth();
				} else {
					width = (comp.getMinimumSize().getWidth() - 4 * space) / 5;
				}
			}
			if (comp.getMinimumSize().getHeight() > height) {
				height = comp.getMinimumSize().getHeight();
			}
		}

		Insets insets = parent.getInsets();
		double prefferedWidth = insets.left + width * COLUMN_MAX + space * (COLUMN_MAX - 1) + insets.right;
		double prefferedHeight = insets.bottom + height * ROW_MAX + space * (ROW_MAX - 1) + insets.top;

		return new Dimension((int) prefferedWidth, (int) prefferedHeight);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void layoutContainer(Container parent) {
		Objects.requireNonNull(parent);

		Dimension compPrefDim = componentDimension();
		double widthMultiplier = parent.getWidth() / this.preferredLayoutSize(parent).getWidth();
		double heightMultiplier = parent.getHeight() / this.preferredLayoutSize(parent).getHeight();
		Dimension compDim = new Dimension((int) (widthMultiplier * compPrefDim.getWidth()),
				(int) (heightMultiplier * compPrefDim.getHeight()));

		for (Component comp : components.keySet()) {
			setComponentBounds(comp, compDim);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		if (components.size() == MAX_COMP) {
			throw new CalcLayoutException("Maximum component number reached already.");
		}
		Objects.requireNonNull(comp);
		Objects.requireNonNull(constraints);

		if (constraints instanceof String) {
			String[] split = constraints.toString().split(",");
			RCPosition position = new RCPosition(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
			constraints = position;
		}

		RCPosition newPosition = (RCPosition) constraints;
		validatePosition(newPosition);
		if (components.containsValue(newPosition)) {
			throw new CalcLayoutException("Component at the given position already exists.");
		}

		components.put(comp, newPosition);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dimension maximumLayoutSize(Container target) {
		Objects.requireNonNull(target);
		double width = 0;
		double height = 0;

		for (Component comp : components.keySet()) {
			if (comp.getMaximumSize() == null) {
				continue;
			}
			if (comp.getMaximumSize().getWidth() > width) {
				if (!components.get(comp).equals(new RCPosition(1, 1))) {
					width = comp.getMaximumSize().getWidth();
				} else {
					width = (comp.getMaximumSize().getWidth() - 4 * space) / 5;
				}
			}
			if (comp.getMaximumSize().getHeight() > height) {
				height = comp.getMaximumSize().getHeight();
			}
		}

		Insets insets = target.getInsets();
		double prefferedWidth = insets.left + width * COLUMN_MAX + space * (COLUMN_MAX - 1) + insets.right;
		double prefferedHeight = insets.bottom + height * ROW_MAX + space * (ROW_MAX - 1) + insets.top;

		return new Dimension((int) prefferedWidth, (int) prefferedHeight);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getLayoutAlignmentX(Container target) {
		return 0.5f;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getLayoutAlignmentY(Container target) {
		return 0.5f;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void invalidateLayout(Container target) {
	}

	/**
	 * Method that validates if the given position is valid in the calculator
	 * layout.
	 * 
	 * @param position
	 *            Given position.
	 */
	private void validatePosition(RCPosition position) {
		if (position.getRow() > ROW_MAX || position.getRow() < ROW_MIN || position.getColumn() > COLUMN_MAX
				|| position.getColumn() < COLUMN_MIN) {
			throw new CalcLayoutException("Invalid position.");
		}

		if (position.getRow() == 1 && position.getColumn() > 1 && position.getColumn() < 6) {
			throw new CalcLayoutException(
					"Invalid position - you entered the position of the big top left screen component.");
		}
	}

	/**
	 * Method that calculates the component dimension.
	 * 
	 * @return component dimension.
	 */
	private Dimension componentDimension() {
		double width = 0;
		double height = 0;

		for (Component comp : components.keySet()) {
			if (comp.getPreferredSize() == null) {
				continue;
			}
			if (comp.getPreferredSize().getWidth() > width) {
				if (!components.get(comp).equals(new RCPosition(1, 1))) {
					width = comp.getPreferredSize().getWidth();
				} else {
					width = (comp.getPreferredSize().getWidth() - 4 * space) / 5;
				}
			}
			if (comp.getPreferredSize().getHeight() > height) {
				height = comp.getPreferredSize().getHeight();
			}
		}

		return new Dimension((int) width, (int) height);
	}

	/**
	 * Method that sets component bounds.
	 * 
	 * @param comp
	 *            Component.
	 * @param dim
	 *            Dimension of the component.
	 */
	private void setComponentBounds(Component comp, Dimension dim) {
		if (components.get(comp).equals(new RCPosition(1, 1))) {
			int x = 0;
			int y = 0;
			double width = dim.getWidth() * 5 + 4 * this.space;
			double height = dim.getHeight();

			comp.setBounds(x, y, (int) width, (int) height);

		} else {
			double x = (components.get(comp).getColumn() - 1) * (dim.getWidth() + this.space);
			double y = (components.get(comp).getRow() - 1) * (dim.getHeight() + this.space);
			double width = dim.getWidth();
			double height = dim.getHeight();

			comp.setBounds((int) x, (int) y, (int) width, (int) height);
		}
	}

}
