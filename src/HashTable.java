import java.util.Arrays;

/* The really big questions holding me back are, how should I do the data object, or name the variables made by the rows. (see farther on) And... I think that's it.
 * Is my DataBase going to be efficient enough? I know that the time is essentially O(alpha) where Alpha is the number of rows divided by the number items. Meaning that 
 * that I can rehash the database when alpha reaches something like .8 to keep the time a nice constant. This may be beyond the scope of the project.
 
 * Reaser OKAY-ed Array of linked lists where each object in the LL is an array.
 */

public class HashTable implements Table {
//An Array of Linked lists, where each list contains an Object (TableData), where each object is essentially one database entry. 
	
	private LinkedListDB[] table;
	private int tableSize = 100;
	private int entries = 0;
	private String[] columnNames;
	/* constructor 
	 * 
	 */
	public HashTable(String... columns) {

		final int columnNumber = columns.length;

		create(columns);
	}
	
	@Override
	public void create(String... columns) {
		// TODO Auto-generated method stub
		//Verify that column names are not empty strings.
		for (int i=0;i<columns.length;i++) {
			if (columns[i].equals("")) {
				throw new IllegalArgumentException("Every column must have a name. They cannot be empty strings.");
			}
		}
		columnNames = columns;
		table = new LinkedListDB[tableSize];
		// this is how you initialize an empty data object in the database. table[0].add(new String[columnNumber]);
		//table[index].add(new String[]{"primary key","value2","value3"});
	//	for (int i=0;i<tableSize;i++){
	//	this.table[i] = new LinkedListDB<String[]>(); // forsome reason eveyr call to any this.table[some index] is null. even though I just created it above. 
	//	}
		
	}

	@Override
	public String[] columns() {
		return this.columnNames;
	}

	@Override
	public void insert(String... data) {
		// TODO Auto-generated method stub
		int index = hash(data[0]);
		index = index % this.getTableSize();
		
		//check to make sure that alpha is at an acceptable level and resize if necessary.
/*		if (this.alpha() >= 1) {
			this.ReHash();
		}
*/		
		
		if (this.table[index] != null) { // if there is an item in the first LL node
			for(int i=0; i < this.table[index].size(); i++) {
				if (this.table[index]!= null && data[0] == this.table[index].get(i)[0]) { //check all existing LL nodes to see if primary key already exists in the database
					throw new IllegalStateException("An entry with an identical primary key already exists in the database"); // set up exception things. 
				}
			}
		}
				//figure out how to call the index of the table that i'm in right now.
		if (this.table[index] == null) {		
			this.table[index] = new LinkedListDB(data);
			entries++;
		}
		else {
			this.table[index].add(data); // add the new entry to the database
			entries++;
		}
	}

	@Override
	public void update(String... data) {
		// TODO Auto-generated method stub
		int index = hash(data[0]);
		index = index % this.getTableSize();
		
		if (this.table[index] != null) { // if there is an item in the first LL node
			for(int i=0; i < this.table[index].size(); i++) {
				if (this.table[index].get(i) != null && data[0].equals(this.table[index].get(i)[0])) { //check all existing LL nodes looking for a primary key match
					for (int c=0;c < this.table[index].get(i).length;c++) { //loop to shuffle through all columns for this entry
				if (this.table[index].get(i)[c] != null && !this.table[index].get(i)[c].equals("") && data[c].equals(null)){
							//if a value exists and new value is null, do not update.
						}
						else {
							this.table[index].get(i)[c] = data[c]; // put our new data in place. 
						}
					}
				}
			}
		}
	}

	@Override
	public String[] retrieve(String key) {
		// TODO Auto-generated method stub
		
		int index = hash(key);
		index = index % tableSize;
		if (this.table[index] == null) { //if the list does not exist
			return null;
		}
		else {
			for (int i=0;i < this.table[index].size();i++) {
				if (this.table[index].get(i) != null && this.table[index].get(i)[0] != null && this.table[index].get(i)[0].equals(key)) { //see if the primary key we are looking for matches the primary key in the node
					return this.table[index].get(i); // return the matching data entry
				}
			}
			return null; //if no matches in the list were found return null
		}
	}

	@Override
	public String[][] retrieve(String... filter) {
		String[] goodFilter = new String[columnNames.length];
		int matches = 0;
		if (filter.length != columnNames.length) { //if filter isn't of the proper length, format a new filter that will work
			for (int x = 0;x<columnNames.length;x++) {
				if (x >= columnNames.length-1) { 
					goodFilter[x] = null;
				}
				else {
					goodFilter[x] = filter[x]; 
				}
			}
		}
		else { //if filter is proper length just copy it over into goodfilter while catching nulls
			for (int i=0;i<filter.length;i++) {
				goodFilter[i] = filter[i];
			}
		}
		
		String[][] results = new String[tableSize][columnNames.length];
		
		System.out.println("The goodfilter is "+ Arrays.toString(goodFilter));
		
		for (int i=0;i < getTableSize()-1; i++) {
			if (this.table[i] != null) {
				for (int x=0; x < this.table[i].size();x++) {
					boolean match = true;
					if (this.table[i].get(x) != null) {
						System.out.println(this.table[i].get(x).length);
						for (int y=0;
								y < this.table[i].get(x).length;
								y++) {
					if (this.table[i].get(x)[y] != null && goodFilter[y].equals(this.table[i].get(x)[y]) || goodFilter[y] == "") {
						// 	only do anything if it doesn't match, and then set match = false;
								//System.out.println("this thing is matching");
								//System.out.println("test" + this.table[i].get(x)[y]);
							}
							else {
								match = false;
							}
							if (y == this.table[i].get(x).length-1 && match == true) {
								results[matches] = this.table[i].get(x); //if the filter has went through and we've checked all spots, add this item to the matched list
								//System.out.println("test" + this.table[i].get(x)[0]);
								//System.out.println("results" + results[matches][0]);
								match = false;
								matches ++;
								break;
							}	
						}	
					}
				}
			}
		}
		
		String[][] goodResults = new String[matches][columnNames.length];
		for (int i=0;i < matches;i++) {
			goodResults[i] = results[i]; //place the results in our proper length array.
		}
		return goodResults;
	}

	@Override
	public String[] delete(String key) {
		// TODO Auto-generated method stub
		
		int index = hash(key);
		index = index % tableSize;
		if (this.table[index] == null) { //if the list is empty then the element we are looking for is not in the database
			return null;
		}
		else {
			for (int i=0;i < this.table[index].size();i++) {
				if (this.table[index].get(i)[0].equals(key)) { //see if the primary key we are looking for matches the primary key in the node
					String[] value = this.table[index].get(i); //save the data to return after we remove the list item. 
					this.table[index].remove(i); //remove the matching data entry
					entries --;
					return value; // return the matching data entry
				}
			}
			return null; //if no matches in the list were found return null as the item we are looking for is not in the database
		}
	}

	@Override
	public String[][] delete(String... filter) {
		String[] goodFilter = new String[columnNames.length];
		int matches = 0;
		if (filter.length != columnNames.length) { //if filter isn't of the proper length, format a new filter that will work
			for (int x = 0;x<columnNames.length;x++) {
				if (x >= columnNames.length-1) { 
					goodFilter[x] = null;
				}
				else {
					goodFilter[x] = filter[x]; 
				}
			}
		}
		else { //if filter is proper length just copy it over into goodfilter while catching nulls
			for (int i=0;i<filter.length;i++) {
				goodFilter[i] = filter[i];
			}
		}
		
		String[][] results = new String[tableSize][columnNames.length];
		
		System.out.println("The goodfilter is "+ Arrays.toString(goodFilter));
		
		for (int i=0;i < getTableSize()-1; i++) {
			if (this.table[i] != null) {
				for (int x=0; x < this.table[i].size();x++) {
					boolean match = true;
					if (this.table[i].get(x) != null) {
						System.out.println(this.table[i].get(x).length);
						for (int y=0;y < this.table[i].get(x).length;y++) {
								if (this.table[i].get(x)[y] != null && goodFilter[y].equals(this.table[i].get(x)[y]) || goodFilter[y] == null) {
						// 	only do anything if it doesn't match, and then set match = false;
								System.out.println("this thing is matching");
								System.out.println("test" + this.table[i].get(x)[y]);
							}
							else {
								match = false;
							}
							if (y == this.table[i].get(x).length-1 && match == true) {
								results[matches] = this.table[i].get(x); //if the filter has went through and we've checked all spots, add this item to the matched list
								System.out.println("test" + this.table[i].get(x)[0]);
								System.out.println("results" + results[matches][0]);
								match = false;
								matches ++;
								this.table[i].remove(x); //remove the matched items.
								break;
							}	
						}	
					}
				}
			}
		}

		String[][] goodResults = new String[matches][columnNames.length];
		for (int i=0;i < matches;i++) {
			goodResults[i] = results[i]; //place the results in our proper length array.
		}
		return goodResults;
	}
	
	
	public int getTableSize() { 
		return this.tableSize;  
	}

/*	public float alpha() {
		float alpha;
		int n = this.getTableSize(); //the amount of slots filled in the outermost array. AKA the number of linked lists that exist. We must find this number.
		int m = entries; //the total entries in the database
		
		if (n == 0 || m == 0) {
			return 0;
		}
		
		for (int i=0;i < this.getTableSize();i++) { //iterate through each array index.
			if (this.table[i] != null) { //if the data in the first list node is not null (it exists) increment n by 1.
				n++; 
			}
		}
		
		alpha = (m/n);
		return alpha;
	}
	public void ReHash(String... columns) {
		this.tableSize = this.tableSize * 2;
		HashTable newTable = new HashTable(columns);
		int index;
		for (int i=0; i<this.tableSize/2;i++) { //iterate through every array index
			for (int length=0;length < this.table[i].size();length++) { // iterate through every node in each linked list
				index = hash(this.table[i].get(length)[0]) % tableSize; //rehash each primary key for the new HashTable
				newTable.table[index].add(this.table[i].get(length)); //put the data from the primary key's original spot to it's spot in the new table.
			}
		}
		this.table = newTable.table; //point the table of the old hashtable to the new hashtable.
	}
*/	
	public int hash(String key) { // hashing function used to turn the primary key into a *hopefully* unique index. Collisions are handled with a chaining technique.
		int index = 0;		      // chaining is done with a linked list in every table row, so that multiple database entries can exist in one "row".
		
		if (key == null) { // handle errors in hash function so that they don't have to be handled in each individual function. 
			throw new IllegalArgumentException("The primary key cannot be empty or null.");
		}
		
		for (int i=0;i<key.length();i++){
			switch (i) {
			case(0):
				index += key.charAt(i) * 31;
			break;
			case(1):
				index += key.charAt(i) * 137;
			break;
			case(2):
				index += key.charAt(i) * 83;
			break;
			case(3):
				index += key.charAt(i) * 829;
			break;
			case(4):
				index += key.charAt(i) * 409;
			break;
			default:
				index += key.charAt(i) * 61;
			}
		}
		return index; //note that the index still needs to be modulo-d by the size of the table. 
		// don't forget to add error catching either in this method or higher up for empty strings n things. 
	}
}
