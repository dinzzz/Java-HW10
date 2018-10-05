package hr.fer.zemris.java.gui.prim;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Class that represents a model for prime numbers lists.
 * 
 * @author Dinz
 *
 */
public class PrimListModel implements ListModel<Integer> {

	/**
	 * List of stored prime numbers.
	 */
	private List<Integer> elements = new ArrayList<>();

	/**
	 * List of listeners of this model.
	 */
	private List<ListDataListener> listeners = new ArrayList<>();

	/**
	 * Latest stored prime number.
	 */
	private int currentPrime = 1;

	/**
	 * Constructs a new prime numbers list model.
	 */
	public PrimListModel() {
		elements.add(1);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return size of the storage of the prime numbers.
	 */
	@Override
	public int getSize() {
		return elements.size();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param index
	 *            Index of the element.
	 * @return Prime numbers at the given index.
	 */
	@Override
	public Integer getElementAt(int index) {
		return elements.get(index);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param l
	 */
	@Override
	public void addListDataListener(ListDataListener l) {
		listeners.add(l);

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param l
	 *            Listener.
	 */
	@Override
	public void removeListDataListener(ListDataListener l) {
		listeners.remove(l);
	}

	/**
	 * Method that adds the next prime number to the list.
	 */
	public void next() {
		addNextPrime(currentPrime);

		int pos = elements.size();
		ListDataEvent event = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, pos, pos);
		for (ListDataListener l : listeners) {
			l.intervalAdded(event);
		}
	}

	/**
	 * Method that calculates the next prime number and does the background job of
	 * adding.
	 * 
	 * @param prime
	 */
	private void addNextPrime(int prime) {
		while (true) {
			prime++;

			if (prime == 2) {
				elements.add(2);
				currentPrime = 2;
				return;
			}

			if (isPrime(prime)) {
				currentPrime = prime;
				elements.add(prime);
				return;
			}

		}
	}

	/**
	 * Checks if the number is prime.
	 * 
	 * @param number
	 *            Number to be checked.
	 * @return True if the number is prime, false otherwise.
	 */
	private boolean isPrime(int number) {
		if (number % 2 == 0) {
			return false;
		}
		for (int i = 3; i * i <= number; i += 2) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

}
