
/**
* BST.java
* @author Tan Dung Dong
* @author Truong Phu Vu
* CIS 22C Lab 5
*/

import java.util.NoSuchElementException;
import java.util.Comparator;

public class BST<T> {
	public class Node {
		private T data;
		private Node left;
		private Node right;

		public Node(T data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	private Node root;

	/*** CONSTRUCTORS ***/

	/**
	 * Default constructor for BST sets root to null
	 */
	public BST() {
		root = null;
	}

	/**
	 * Copy constructor for BST
	 * 
	 * @param bst the BST of which to make a copy.
	 * @param c   the way the tree is organized
	 */
	public BST(BST<T> bst, Comparator<T> c) {
		System.out.println("copy constructor called");
		if (bst == null) {
			return;
		} else if (bst.getSize() == 0) {
			System.out.println("hello");

			root = null;
		} else {
			System.out.println(bst.getRoot());
			copyHelper(bst.root, c);
		}

	}

	/**
	 * Helper method for copy constructor
	 * 
	 * @param node the node containing data to copy
	 * @param c    the way the tree is organized
	 */
	private void copyHelper(Node node, Comparator<T> c) {
		if (node == null)
			return;
		else {
			this.insert(node.data, c);
			copyHelper(node.left, c);
			copyHelper(node.right, c);
		}
	}

	/*** ACCESSORS ***/

	/**
	 * Returns the data stored in the root
	 * 
	 * @precondition !isEmpty()
	 * @return the data stored in the root
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getRoot() throws NoSuchElementException {
		return root.data;
	}

	/**
	 * Determines whether the tree is empty
	 * 
	 * @return whether the tree is empty
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Returns the current size of the tree (number of nodes)
	 * 
	 * @return the size of the tree
	 */
	public int getSize() {
		int size = 0;
		size = getSize(root);
		return size;
	}

	/**
	 * Helper method for the getSize method
	 * 
	 * @param node the current node to count
	 * @return the size of the tree
	 */
	private int getSize(Node node) {
		if (node == null)
			return 0;
		else {
			int left = getSize(node.left);
			int right = getSize(node.right);
			return left + right + 1;

		}

	}

	/**
	 * Returns the height of tree by counting edges.
	 * 
	 * @return the height of the tree
	 */
	public int getHeight() {
		return getHeight(root);
	}

	/**
	 * Helper method for getHeight method
	 * 
	 * @param node the current node whose height to count
	 * @return the height of the tree
	 */
	private int getHeight(Node node) {
		if (node == null)
			return -1;
		else {
			int left = getHeight(node.left);
			int right = getHeight(node.right);

			if (left > right)
				return left + 1;
			else
				return right + 1;

		}
	}

	/**
	 * Returns the smallest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the smallest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T findMin() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("findMin(): " + "Tree is empty, cannot find min");
		else {
			return findMin(root);
		}
	}

	/**
	 * Recursive helper method to findMin method
	 * 
	 * @param node the current node to check if it is the smallest
	 * @return the smallest value in the tree
	 */
	private T findMin(Node node) {
		if (node.left == null)
			return node.data;
		else {
			return findMin(node.left);
		}

	}

	/**
	 * Returns the largest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the largest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T findMax() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("findMax(): " + "Tree is empty, cannot find max");
		else
			return findMax(root);
	}

	/**
	 * Recursive helper method to findMax method
	 * 
	 * @param node the current node to check if it is the largest
	 * @return the largest value in the tree
	 */
	private T findMax(Node node) {
		if (node.right == null)
			return node.data;
		else
			return findMax(node.right);
	}

	/**
	 * Searches for a specified value in the tree
	 * 
	 * @param data   the value to search for
	 * @param update whether to update the node's data with the given data
	 * @param c      the Comparator that indicates the way the data in the tree was
	 *               ordered
	 * @return the data stored in that Node of the tree is found or null otherwise
	 */
	// We do not use boolean update, I talked to you in office hour
	public T search(T data, boolean update, Comparator<T> c) {
		if (root == null) {
			return null;
		} else {
			return search(data, root, update, c);
		}
	}

	/**
	 * Helper method for the search method
	 * 
	 * @param data the data to search for
	 * @param node the current node to check
	 * @return the data stored in that Node of the tree is found or null otherwise
	 */
	// We do not use boolean update, I talked to you in office hour
	private T search(T data, Node node, boolean update, Comparator<T> c) {
		if (c.compare(data, node.data) == 0) {
			return node.data;
		} else if (c.compare(data, node.data) < 0) {
			if (node.left == null) {
				return null;
			} else
				return search(data, node.left, update, c);
		} else {
			if (node.right == null) {
				return null;
			} else
				return search(data, node.right, update, c);

		}

	}

	/*** MUTATORS ***/

	/**
	 * Inserts a new node in the tree
	 * 
	 * @param data the data to insert
	 * @param c    the Comparator indicating how data in the tree is ordered
	 */
	public void insert(T data, Comparator<T> c) {
		if (root == null) {
			Node node = new Node(data);
			root = node;
		} else {
			insert(data, root, c);
		}
	}

	/**
	 * Helper method to insert Inserts a new value in the tree
	 * 
	 * @param data the data to insert
	 * @param node the current node in the search for the correct location to insert
	 * @param c    the Comparator indicating how data in the tree is ordered
	 */
	private void insert(T data, Node node, Comparator<T> c) {
		if (c.compare(data, node.data) <= 0) {
			if (node.left == null) {
				Node N = new Node(data);
				node.left = N;
			} else {
				insert(data, node.left, c);
			}
		} else {
			if (node.right == null) {
				Node N = new Node(data);
				node.right = N;
			} else {
				insert(data, node.right, c);
			}
		}

	}

	/**
	 * Removes a value from the BST
	 * 
	 * @param data the value to remove
	 * @param c    the Comparator indicating how data in the tree is organized Note:
	 *             updates nothing when the element is not in the tree
	 */
	public void remove(T data, Comparator<T> c) throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("remove(): " + "Tree is empty, cannot remove!");
		else {
			if (this.getSize() == 1 && c.compare(data, root.data) == 0) {
				root = null;
			} else
				root = remove(data, root, c);
		}

	}

	/**
	 * Helper method to the remove method
	 * 
	 * @param data the data to remove
	 * @param node the current node
	 * @param c    the Comparator indicating how data in the tree is organized
	 * @return an updated reference variable
	 */
	private Node remove(T data, Node node, Comparator<T> c) {
		if (node == null)
			return node;
		else if (c.compare(data, node.data) < 0)
			node.left = remove(data, node.left, c);
		else if (c.compare(data, node.data) > 0)
			node.right = remove(data, node.right, c);
		else {
			if (node.right == null && node.left == null)
				node = null;
			else if (node.left != null && node.right == null) {
				node = node.left;

			} else if (node.left == null && node.right != null) {
				node = node.right;
			} else {
				T min = findMin(node.right);
				node.data = min;
				node.right = remove(min, node.right, c);
			}
		}
		return node;
	}

	/*** ADDITONAL OPERATIONS ***/

	/**
	 * Prints the data in pre order to the console followed by a new line
	 */
	public void preOrderPrint() {
		preOrderPrint(root);
	}

	/**
	 * Helper method to preOrderPrint method Prints the data in pre order to the
	 * console followed by a new line
	 */
	private void preOrderPrint(Node node) {
		if (node == null)
			return;
		else {
			System.out.print(node.data + " ");
			preOrderPrint(node.left);
			preOrderPrint(node.right);
		}
	}

	/**
	 * Prints the data in sorted order to the console followed by a new line
	 */
	public void inOrderPrint() {
		inOrderPrint(root);
	}

	/**
	 * Helper method to inOrderPrint method Prints the data in sorted order to the
	 * console followed by a new line
	 */
	private void inOrderPrint(Node node) {
		if (node == null)
			return;
		else {
			inOrderPrint(node.left);
			System.out.print(node.data);
			inOrderPrint(node.right);
		}
	}

	/**
	 * Prints the data in post order to the console followed by a new line
	 */
	public void postOrderPrint() {
		postOrderPrint(root);
	}

	/**
	 * Helper method to postOrderPrint method Prints the data in post order to the
	 * console
	 */
	private void postOrderPrint(Node node) {
		if (node == null)
			return;
		postOrderPrint(node.left);
		postOrderPrint(node.right);
		System.out.print(node.data + " ");
	}

}
