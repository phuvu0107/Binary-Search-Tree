import java.util.ArrayList;
public class HashTableTest {
	public static void main (String [] args) {
		HashTable<String> h = new HashTable<String>(21);
		h.put("ann");
		h.put("ben");
		h.put("joe");
		h.put("john");
		h.put("bill");
		h.put("henry");
		h.put("tommy");
		System.out.println("Check hash table:" + h.toString());
		for (int i = 0; i < 15; i++) {
		System.out.println("Check bucket: " + i);
		 h.printBucket(i);
		}
		System.out.println("check contain:" + h.contains("bo"));
	}

}
