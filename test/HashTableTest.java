import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class HashTableTest {

	@Test
	public void testHashTable() {
		
		String[] columns = new String[] {"Name","Age","Sex"};
		HashTable test1 = new HashTable(columns);
		
		HashTable test2 = new HashTable("Major");
		
		// HashTable test3 = new HashTable("");
		// ask about how to check code that throws an exception in testing again.
	}
	// HashTable test3 = new HashTable("");
	// ask about how to check code that throws an exception in testing again.


// HashTable test3 = new HashTable("");
// ask about how to check code that throws an exception in testing again.

	/* public void testCreate()
	 * This test is included in the testHashTable() as Create() is encapsulated by a method call from HashTable()
	 */
	// junit.org expected exceptions testing
	// do try and throw an error and fail if the error isn't thrown. do nothing if it is. or like assert true=true
	@Test
	public void testColumns() {
		String[] columns = new String[] {"Name","Age","Sex"};
		HashTable test1 = new HashTable(columns);
		
		for (int i=0;i<columns.length;i++) {
		assertEquals(columns[i], test1.columns()[i]);
		}
	}

	@Test
	public void testInsert() {
		String[] columns = new String[] {"Name","Age","Sex"};
		HashTable test1 = new HashTable(columns);
		
		String[] data = new String[] {"Brian","20","Male"};
		String[] data2 = new String[] {"Brians","20","Male"};
		String[] data3 = new String[] {"Shelby","22","Female"};
		test1.insert(data);
		assertArrayEquals(data, test1.retrieve("Brian"));
		test1.insert(data2);
		assertArrayEquals(data2, test1.retrieve("Brians"));
		test1.insert(data3);
		assertEquals("22", test1.retrieve("Shelby")[1]);
		test1.delete("Brians");
		assertArrayEquals(null, test1.retrieve("Brians"));
	}

	@Test
	public void testUpdate() {
		String[] columns = new String[] {"name","age","sex"};
		HashTable test = new HashTable(columns);
		
		String[] data = new String[] {"brian","20","male"};
		test.insert(data);
		assertArrayEquals(data, test.retrieve("brian"));
		
		String[] data1 = new String[] {"brian","22","male"};
		test.update(data1);
		
		assertArrayEquals(data1, test.retrieve("brian"));
		
		String[] data2 = new String[] {"brian","20",""};
		test.update(data2);
		assertArrayEquals(data2, test.retrieve("brian"));
	}

	@Test
	public void testRetrieveString() {
		String[] columns = new String[] {"Name","Age","Sex"};
		HashTable test1 = new HashTable(columns);
		
		String[] data = new String[] {"Brian","20","Male"};
		String[] data2 = new String[] {"Brians","20","Male"};
		String[] data3 = new String[] {"Shelby","22","Female"};
		test1.insert(data);
		assertArrayEquals(data, test1.retrieve("Brian"));
		test1.insert(data2);
		assertArrayEquals(data2, test1.retrieve("Brians"));
		test1.insert(data3);
		assertEquals("22", test1.retrieve("Shelby")[1]);
		test1.delete("Brians");
		assertArrayEquals(null, test1.retrieve("Brians"));
		assertArrayEquals(null, test1.retrieve("weiner"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testRetrieveStringArray() {
		HashTable test = new HashTable("Name","Pet","Age");
		String[] data = new String[] {"brian","cat","20"};
		String[] data1 = new String[] {"shelby","dog","20"};
		test.insert(data);
		test.insert(data1);
		assertArrayEquals(data, test.retrieve("","","")[0]);
		assertArrayEquals(data1, test.retrieve("","","")[1]);
		assertArrayEquals(data, test.retrieve("brian","","")[0]);
		assertArrayEquals(data1, test.retrieve("","dog","")[0]);
		assertArrayEquals(data, test.retrieve("","","20")[0]);
		assertArrayEquals(data1, test.retrieve("","","20")[1]);
		test.delete("shelby");
		System.out.println("before last test");
		assertArrayEquals(null, test.retrieve("","dog",""));
	}

	@Test
	public void testDeleteString() {
		String[] columns = new String[] {"Color","Size","Weight"};
		HashTable test = new HashTable(columns);
		
		String[] data1 = new String[] {"blue","large","light"};
		test.insert(data1);
		assertArrayEquals(data1, test.retrieve("blue"));
		test.delete("blue");
		assertArrayEquals(null, test.retrieve("blue"));
	}

	@Test
	public void testDeleteStringArray() {
		HashTable test = new HashTable("Name","Pet","Age");
		String[] data = new String[] {"brian","cat","20"};
		test.insert(data);
		assertArrayEquals(data, test.retrieve("","","")[0]);

		assertArrayEquals(data, test.delete(data)[0]);
		//System.out.print(Arrays.toString(sometest));
		
		
	}

	@Test
	public void testGetTableSize() {
		HashTable TablesizeTest = new HashTable("Test");
		assertEquals(100, TablesizeTest.getTableSize());
	}

	@Test
	public void testAlpha() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testReHash() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testHash() {
		HashTable test = new HashTable("Test");
		test.insert("somehash");
		assertEquals("somehash",test.retrieve("somehash")[0]);
	}

}
