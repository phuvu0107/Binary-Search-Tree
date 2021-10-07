
/**
 * HashTable.java
 * @author 
 * @author
 * CIS 22C, Lab 6
 */
import java.util.ArrayList;

public class HashTable<T> {

	private int numElements;
	private ArrayList<List<T>> Table;

	/**
	 * Constructor for the Hash.java class. Initializes the Table to be sized
	 * according to value passed in as a parameter Inserts size empty Lists into the
	 * table. Sets numElements to 0
	 * 
	 * @param size the table size
	 */
	public HashTable(int size) {
		this.Table = new ArrayList<>(size);

		for (int i = 0; i < size; i++) {
			Table.add(i, new List<>());
		}
		this.numElements = 0;

	}

	/** Accessors */

	/**
	 * returns the hash value in the Table for a given Object
	 * 
	 * @param t the Object
	 * @return the index in the Table
	 */
	private int hash(T t) {
		int code = t.hashCode();
		return code % Table.size();
	}

	/**
	 * counts the number of keys at this index
	 * 
	 * @param index the index in the Table
	 * @precondition 0 <= index < Table.length
	 * @return the count of keys at this index
	 * @throws IndexOutOfBoundsException
	 */
	public int countBucket(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= Table.size()) {
			throw new IndexOutOfBoundsException("countBucket():" + "Cannot count when the index is " + "off the table");
		} else {
			return Table.get(index).getLength();
		}
	}

	/**
	 * returns total number of keys in the Table
	 * 
	 * @return total number of keys
	 */
	public int getNumElements() {
		return this.numElements;
	}

	/**
	 * Accesses a specified key in the Table
	 * 
	 * @param t the key to search for
	 * @return the value to which the specified key is mapped, or null if this table
	 *         contains no mapping for the key.
	 * @precondition t != null
	 * @throws NullPointerException if the specified key is null
	 */
	public T get(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException("get(): " + "Canot access a specified key when t is null");
		}
		int bucket = hash(t);
		int location = Table.get(bucket).linearSearch(t);
		if (location != -1) {
			Table.get(bucket).placeIterator();
			Table.get(bucket).iteratorToIndex(location);
			return Table.get(bucket).getIterator();
		} else {
			return null;
		}
	}

	/**
	 * Determines whether a specified key is in the Table
	 * 
	 * @param t the key to search for
	 * @return whether the key is in the Table
	 * @precondition t != null
	 * @throws NullPointerException if the specified key is null
	 */
	public boolean contains(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException("contains(): " + "Cannot determine because the key is null");
		} else {
			int bucket = hash(t);
			return Table.get(bucket).linearSearch(t) != -1;
		}
	}

	/** Mutators */

	/**
	 * Inserts a new element in the Table at the end of the chain in the bucket to
	 * which the key is mapped
	 * 
	 * @param t the key to insert
	 * @precondition t != null
	 * @throws NullPointerException for a null key
	 */
	public void put(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException("put(): " + "Cannot insert because the key is null");
		} else {
			int bucket = hash(t);
			Table.get(bucket).addLast(t);
			numElements++;
		}

	}

	/**
	 * removes the key t from the Table calls the hash method on the key to
	 * determine correct placement has no effect if t is not in the Table or for a
	 * null argument
	 * 
	 * @param t the key to remove
	 * @throws NullPointerException if the key is null
	 */
	public void remove(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException("remove(): " + "Cannot remove beacause the key is null");
		} else {
			int bucket = hash(t);
			Table.get(bucket).placeIterator();
			int location = Table.get(bucket).linearSearch(t);
			if (location == -1) {
				return;
			}
			Table.get(bucket).iteratorToIndex(location);
			Table.get(bucket).removeIterator();
			numElements--;
		}

	}

	/**
	 * Clears this hash table so that it contains no keys.
	 */
	public void clear() {

	}

	/** Additional Methods */

	/**
	 * Prints all the keys at a specified bucket in the Table. Tach key displayed on
	 * its own line, with a blank line separating each key Above the keys, prints
	 * the message "Printing bucket #<bucket>:" Note that there is no <> in the
	 * output
	 * 
	 * @param bucket the index in the Table
	 */
	public void printBucket(int bucket) {
		String sum = "";
		Table.get(bucket).placeIterator();
		while (!Table.get(bucket).offEnd()) {
			sum += Table.get(bucket).getIterator() + "\n";
			Table.get(bucket).advanceIterator();
		}
		System.out.println("Print bucket #" + bucket + "\n" + sum);
	}

	/**
	 * Prints the first key at each bucket along with a count of the total keys with
	 * the message "+ <count> -1 more at this bucket." Each bucket separated with
	 * two blank lines. When the bucket is empty, prints the message "This bucket is
	 * empty." followed by two blank lines
	 */
	public void printTable() {

	}

	/**
	 * Starting at the first bucket, and continuing in order until the last bucket,
	 * concatenates all elements at all buckets into one String
	 */
	@Override
	public String toString() {
		String sum = "";
		for (int i = 0; i < Table.size(); i++) {
			Table.get(i).placeIterator();
			while (!Table.get(i).offEnd()) {
				sum += Table.get(i).getIterator() + " ";
				Table.get(i).advanceIterator();
			}
		}
		return sum + " ";

	}
}