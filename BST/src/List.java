
/**
* @author Truong Phu Vu
* @author Tan Dung Dong
* CIS 22C, Lab 3
*/

import java.util.NoSuchElementException;

public class List<T> {
	private class Node {
		private T data;
		private Node next;
		private Node prev;

		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}

	private int length;
	private Node first;
	private Node last;
	private Node iterator;

	/**** CONSTRUCTOR ****/

	/**
	 * Instantiates a new List with default values
	 * 
	 * @postcondition an empty list is created
	 */
	public List() {
		first = null;
		last = null;
		iterator = null;
		length = 0;
	}

	/**
	 * Instantiates a new List by copying another List
	 * 
	 * @param original the List to make a copy of
	 * @postcondition a new List object, which is an identical but separate copy of
	 *                the List original
	 */
	public List(List<T> original) {
		if (original == null) {
			return;
		}
		if (original.length == 0) {
			length = 0;
			first = null;
			last = null;
			iterator = null;
		} else {
			Node temp = original.first;
			while (temp != null) {
				addLast(temp.data);
				temp = temp.next;
			}
			iterator = null;
		}
	}

	/**** ACCESSORS ****/

	/**
	 * Returns the value stored in the first node
	 * 
	 * @precondition !isEmpty()
	 * @return the value stored at node first
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getFirst(): " + "List is empty. Cannot return the first element!");
		}
		return first.data;
	}

	/**
	 * Returns the value stored in the last node
	 * 
	 * @precondition !isEmpty()
	 * @return the value stored in the node last
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getLast(): " + "List is empty " + ".Cannot return the last element!");
		}
		return last.data;
	}

	/**
	 * Returns the current length of the list
	 * 
	 * @return the length of the list from 0 to n
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Returns whether the list is currently empty
	 * 
	 * @return whether the list is empty
	 */
	public boolean isEmpty() {
		return length == 0;
	}

	/**
	 * returns the element currently pointed at by the iterator
	 * 
	 * @throws NullPointerException when iterator is off end
	 * @return the element currently pointed at by the iterator
	 */
	public T getIterator() throws NullPointerException {
		if (first == null) {
			throw new NullPointerException("getIterator(): " + "Iterator is off end of the list!");
		} else {
			return iterator.data;
		}
	}

	/**
	 * returns whether the iterator is off the end of the list, i.e. set to null
	 * 
	 * @return whether the iterator is off the end of the list, i.e. set to null
	 */
	public boolean offEnd() {
		return iterator == null;
	}

	/**
	 * Determines whether two Lists have the same data in the same order
	 * 
	 * @param L the List to compare to this List
	 * @return whether the two Lists are equal
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof List)) {
			return false;
		} else {
			List<T> L = (List<T>) o;
			if (this.length != L.length) {
				return false;
			} else {
				Node temp1 = this.first;
				Node temp2 = L.first;
				while (temp1 != null) { // Lists are same length
					if (!(temp1.data.equals(temp2.data))) {
						return false;
					}
					temp1 = temp1.next;
					temp2 = temp2.next;
				}
				return true;
			}
		}
	}

	/**** MUTATORS ****/

	/**
	 * Creates a new first element
	 * 
	 * @param data the data to insert at the front of the list
	 * @postcondition the first new node is created
	 */
	public void addFirst(T data) {
		Node N = new Node(data);
		// if the list is empty
		if (first == null) {
			first = last = N;
			length++;
		}
		// if the list is not empty
		else {
			N.next = first;
			first.prev = N;
			N.prev = null;
			first = N;
			length++;
		}

	}

	/**
	 * Creates a new last element
	 * 
	 * @param data the data to insert at the end of the list
	 * @postcondition new last node is created
	 */
	public void addLast(T data) {
		Node N = new Node(data);
		if (first == null) {
			first = last = N;
			length++;
		} else {
			last.next = N;
			N.prev = last;
			last = N;
			length++;
		}
	}

	/**
	 * removes the element at the front of the list
	 * 
	 * @precondition the list is not empty
	 * @postcondition the first node is removed update the first node
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeFirst() throws NoSuchElementException {
		if (first == null) {
			throw new NoSuchElementException("removeFirst(): " + "Cannot remove because the list is empty!");
		} else if (length == 1) {
			first = last = null;
			iterator = null;
		} else {
			first = first.next;
			first.prev = null;
		}
		length--;
	}

	/**
	 * removes the element at the end of the list
	 * 
	 * @precondition length>0
	 * @postcondition the last node is removed update the last node
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeLast(): " + "Cannot remove because the list is empty!");
		} else if (first == last) {
			first = last = null;
			iterator = null;
			length--;
		} else {
			Node temp = last;
			last.prev.next = null;
			last = last.prev;
			temp.prev = null;
			length--;

		}

	}

	/**
	 * moves the iterator to the beginning of the list
	 * 
	 * @postcondition the iterator is moved to the beginning of the list
	 */
	public void placeIterator() {
		iterator = first;
	}

	/**
	 * removes the element currently referenced by the iterator
	 * 
	 * @precondition iterator != null
	 * @throws NullPointerException when iterator is off end
	 * @postcondition iterator will be null
	 */
	public void removeIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("removeIterator(); " + "Iterator is off end. Cannot remove!)");
		} else if (iterator == first) {
			this.removeFirst();

		} else if (iterator == last) {
			this.removeLast();
		} else {
			iterator.next.prev = iterator.prev;
			iterator.prev.next = iterator.next;
			length--;
			iterator = null;
		}

	}

	/**
	 * adds the element currently referenced by the iterator
	 * 
	 * @precondition iterator != null
	 * @throws NullPointerException when iterator is off end
	 * @postcondition new node is added
	 */
	public void addIterator(T data) throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("addIterator: " + "Iterator is off end. Cannot add!");
		} else if (iterator == last) {
			addLast(data);
		} else {
			Node N = new Node(data);
			N.next = iterator.next;
			iterator.next = N;
			N.next.prev = N;
			N.prev = iterator;
			length++;
		}
	}

	/**
	 * moves the iterator up by one node
	 * 
	 * @throws NullPointerException when iterator is off end
	 * @postcondition the iterator is moved up by one node
	 */
	public void advanceIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("advanceIterator(): " + "Iterator is off end. Cannot move!");
		} else {
			iterator = iterator.next;
		}
	}

	/**
	 * moves the iterator down by one node
	 * 
	 * @throws NullPointerException when iterator is off end
	 * @postcondition the iterator is moved up by one node
	 */
	public void reverseIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("reverseiterator(): " + "Iterator is off end.Cannot move!!");
		} else {
			iterator = iterator.prev;
		}

	}

	/**** ADDITIONAL OPERATIONS ****/

	/**
	 * List with each value on its own line At the end of the List a new line
	 * 
	 * @return the List as a String for display
	 */
	@Override
	public String toString() {
		String result = "";
		Node temp = first;
		while (temp != null) {
			result += temp.data + " ";
			temp = temp.next;
		}
		return result;
	}

	/**
	 * prints the contents of the linked list to the screen in the format #:
	 * <element> followed by a newline
	 */
	public String printNumberedList() {
		Node temp = first;
		int count = 1;
		while (temp != null) {
			System.out.print(count + ". ");
			System.out.print(temp.data);
			temp = temp.next;
			count++;
		}
		return "\n";
	}

	/**
	 * Points the iterator at first and then advances it to the specified index
	 * 
	 * @param index the index where the iterator should be placed
	 * @precondition 0 < index <= length
	 * @throws IndexOutOfBoundsException when precondition is violated
	 */
	public void iteratorToIndex(int index) throws IndexOutOfBoundsException {
		if (index <= 0 || index > length)
			throw new IndexOutOfBoundsException("iteratorToIndex(): " + "invalid index!");
		else {
			for (int i = 1; i < index; i++) {
				iterator = iterator.next;
			}
		}
	}

	/**
	 * Searches the List for the specified value using the linear search algorithm
	 * 
	 * @param value the value to search for
	 * @return the location of value in the List or -1 to indicate not found Note
	 *         that if the List is empty we will consider the element to be not
	 *         found post: position of the iterator remains unchanged
	 */
	public int linearSearch(T value) {
		Node temp = first;
		int index = 1;
		while (temp != null) {
			if (temp.data == value)
				return index;
			temp = temp.next;
			index++;
		}
		return -1;
	}
}
