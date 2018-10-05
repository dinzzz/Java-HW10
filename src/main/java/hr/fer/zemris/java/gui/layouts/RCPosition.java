package hr.fer.zemris.java.gui.layouts;

/**
 * Class that represents a Row-Column position in the graphical interface
 * layouts.
 * 
 * @author Dinz
 *
 */
public class RCPosition {
	/**
	 * Row position.
	 */
	private int row;

	/**
	 * Column position.
	 */
	private int column;

	/**
	 * Constructs a new Row-Column position.
	 * 
	 * @param row
	 *            Row position.
	 * @param column
	 *            Column position.
	 */
	public RCPosition(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * Gets the row position.
	 * 
	 * @return Row position.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Gets the column position.
	 * 
	 * @return Column position.
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	/**
	 * Method that checks if the two RC Positions are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RCPosition other = (RCPosition) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

}
