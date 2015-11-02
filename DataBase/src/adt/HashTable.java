// Required for submission.
// Replace with compliant class from Module 1.

package adt;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This hash table is functional but encapsulates several
 * of the ADT's in the Java Collections Framework instead
 * of implementing the interface from scratch.
 */
public class HashTable implements Table {
	private String[] columns;
	private Map<String,String[]> table;
	
	@Override
	public void create(String... columns) {
		this.table = new HashMap<String,String[]>();
		this.columns = columns;
	}

	@Override
	public String[] columns() {
		return columns;
	}

	@Override
	public void insert(String... data) {
		if (table.containsKey(data[0]))
			throw new IllegalStateException();
		
		table.put(data[0], data);
	}

	@Override
	public void update(String... data) {
		if (!table.containsKey(data[0]))
			throw new IllegalStateException();
			
		String[] row = table.get(data[0]);
		for (int i = 0; i < columns.length; i++) {
			if (data[i] != null)
				row[i] = data[i];
		}
		
		table.put(data[0], row);
	}

	@Override
	public String[] retrieve(String key) {
		return table.get(key);
	}

	@Override
	public String[] delete(String key) {
		return table.remove(key);
	}


	@Override
	public String[][] retrieve(String... filter) {
		return search(false, filter);
	}
	
	@Override
	public String[][] delete(String... filter) {
		return search(true, filter);
	}
	
	private String[][] search(boolean delete, String... filter) {
		ArrayList<String[]> results = new ArrayList<String[]>();
			
		Iterator<Map.Entry<String, String[]>> rows = table.entrySet().iterator();
		while (rows.hasNext()) {
			String[] row = rows.next().getValue();

			boolean mismatch = false;
			for (int i = 0; i < columns.length; i++) {
				if (filter[i] != null && !row[i].equals(filter[i])) {
					mismatch = true;
					break;
				}
			}
			if (!mismatch) {
				results.add(row);
				if (delete)
					rows.remove();
			}
		}
		
		return (String[][]) results.toArray(new String[0][0]);
	}
}
