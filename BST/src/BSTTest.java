
/**
* BST.java
* @author Tan Dung Dong
* @author Truong Phu Vu
* CIS 22C Lab 5
*/
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BSTTest {
	public static void main(String[] args) {
		BST<Integer> t = new BST<>();
		IntegerComparator c = new IntegerComparator();
		System.out.println("/****TESTING INSERT****/\n");
		t.insert(5, c);
		t.insert(2, c);
		t.insert(3, c);
		t.insert(12, c);
		t.insert(112, c);
		t.insert(11, c);
		t.insert(7, c);
		t.insert(88, c);
		t.insert(65, c);

		BST<Integer> t1 = new BST<>();
		t1.insert(1, c);
		t1.insert(2, c);
		t1.insert(3, c);
		t1.insert(4, c);
		t1.insert(5, c);
		t1.insert(6, c);
		System.out.println();
		System.out.print("Print tree: ");
		t.inOrderPrint();
		System.out.println();
		System.out.print("Print tree t1:");
		t1.inOrderPrint();

		System.out.println("/****TESTING getHeigth()****/");
		System.out.println("Get height list t, should print 4: " + t.getHeight());
		System.out.println("Get height of list t1, should print 5: " + t1.getHeight());

		System.out.println("\n/****TESTING getRoot()****/");
		try {
			System.out.println("Should print 5: " + t.getRoot());
			System.out.println("Should print 1: " + t1.getRoot());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		System.out.println("\n/****TESTING findMin()****/");
		System.out.println("Min of list t, should print 2: " + t.findMin());
		System.out.println("Min of list t1, should print 1: " + t1.findMin());

		System.out.println("\n/****TESTING findMax()****/");
		System.out.println("Max of list t, should print 112: " + t.findMax());
		System.out.println("Max of list t1, should print 6: " + t1.findMax());

		System.out.println("\n/****TESTING SEARCH()****/");
		System.out.println("Search 112 in t: " + t.search(112, true, c));
		System.out.println("Search 88 in t: " + t.search(88, true, c));
		System.out.println("Search 4 in t1" + t1.search(4, true, c));

		System.out.println("\n/****TESTING inOrderPrint()****/");
		System.out.println("Should print from smallest to largest:");
		System.out.print("List of t: ");
		t.inOrderPrint();
		System.out.print("\nList of t1: ");
		t1.inOrderPrint();

		System.out.println("\n\n/****TESTING preOrderPrint()****/");
		System.out.print("preOrderPrint() of t(print 5 2 3 12 11 7 112 88 65): ");
		t.preOrderPrint();
		System.out.print("\npreOrderPrint() of t1 (print 1 2 3 4 5 6): ");
		t1.preOrderPrint();

		System.out.println("\n\n/****TESTING postOrderPrint()****/");
		System.out.print("postOrderPrint() of t (print 3 2 7 11 65 88 112 12 5): ");
		t.postOrderPrint();
		System.out.print("\npostOrderPrint() of t1 (print 6 5 4 3 2 1): ");
		t1.postOrderPrint();

		BST<Integer> copy = new BST<>(t1, c);
		System.out.println("\n\n/****TESTING COPY()****/");
		System.out.print("Should print the list the same as inOrderPrint() of t1: ");
		copy.inOrderPrint();
		BST<Integer> copy1 = new BST<>(t, c);
		System.out.print("\nShould print out the list the same as inOrderPrint() of t: ");
		copy1.inOrderPrint();

		System.out.println("\n/****TESTING REMOVE()****/");
		System.out.print("Print t list again from smallest to largest: ");
		t.inOrderPrint();
		t.remove(112, c);
		System.out.print("\nPrint t list without 112: ");
		t.inOrderPrint();
		t.remove(7, c);
		System.out.print("\nPrint t list without 7 and 112: ");
		t.inOrderPrint();

		System.out.print("\nPrint t1 list again: ");
		t1.inOrderPrint();
		t1.remove(5, c);
		System.out.print("\nPrint t1 list without number 5: ");
		t1.inOrderPrint();
		t1.remove(3, c);
		System.out.print("\nPrint t1 list without 3 and 5: ");
		t1.inOrderPrint();
		BST<Integer> tree = new BST<>();
		tree.insert(10, c);
		tree.insert(9, c);
		tree.insert(8, c);
		tree.insert(7, c);
		tree.insert(6, c);
		System.out.print("\nPrint tree: ");
		tree.inOrderPrint();
		tree.remove(9, c);
		System.out.print("\nPrint preOrderPrint() without 9: ");
		tree.inOrderPrint();
		tree.remove(10, c);
		System.out.print("\nPrint inOrderPrint() without 9 and 10: ");
		tree.inOrderPrint();

	}
}

class IntegerComparator<T extends Comparable<T>> implements Comparator<T> {

	public int compare(T a, T b) {
		return a.compareTo(b);
	}

	public boolean equals(Object obj) {
		return this.equals(obj);
	}

}