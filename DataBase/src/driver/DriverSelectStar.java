package driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adt.HashTable;
import adt.Response;
import adt.TableList;

public class DriverSelectStar implements Driver{
	
	
	

	private TableList<HashTable> tables;
	private final Pattern pattern;
	public DriverSelectStar(TableList<HashTable> tables2) {
		this.tables = tables2;
		pattern = Pattern.compile(
				"",
			Pattern.CASE_INSENSITIVE
		);
	}

	@Override
	public boolean valid(String query) {
		Matcher matcher = pattern.matcher(query);
		if (!matcher.matches()) {
			return false;
		}
		else {
			
			//get tablename
			String tableName = matcher.group(1);
			
			//verify table exists
			if (tables.get(tableName) != null) {
				return false;
			}
			
			//create a filter with the correct length of null columns to retrieve data from the hashTable
			int filterLength = tables.get(tableName).columns().length;
			String filter[] = new String[filterLength=1];
			for (int i=0; i<=filterLength-1;i++) {
				filter[i] = null;
			}
			
			// get all data from the table and put it in a variable
			String rawRows[][] = tables.get(tableName).retrieve(filter);
			
			//for other select method, get only the columns we want.
			
			
			
			
			//do the order things
			// if it needs reordered, then modify it, and store final data to be presented in rows[][]
			
			/*
			 * In the order clause you can split by ,
			 * after splitting by comma, start with the last one and check to see if it contains ASC DESC or nothing and sort appropriately.
			 * move one by one until you've sorted by each thing specified in the order clause
			 * 
			 */
			String[] columnMatches = matcher.group(2).split(","); //get the matching group of all the column names
			for (int i=0;i<columnMatches.length;i++) { 
				columnMatches[i] = columnMatches[i].trim(); // remove whitespace in front and behind the column names 
			}
			int num[];
			String sort[];
			for (int i=columnMatches.length-1;i >=0;i--) {
				sort = columnMatches[i].split(" ");
				
				for (int x=0; x<tables.get(tableName).columns().length;x++) {
					if (sort[0].equals(tables.get(tableName).columns()[x])) {
						num = new int[] {x};
						if (sort.length == 1) {
							sortAsc(rawRows,num);
						} else if (sort[1].equalsIgnoreCase("desc")) {
							sortDesc(rawRows,num);
						} else {
							sortAsc(rawRows,num);
						}
					}
				}
			}
			return true;
		}
	}
	private String[][] rows;
	private String[] columnData;
	private String tableName;
	
	@Override
	public Response execute() {
			return new Response(true,tables.get(tableName).columns(),rows);	
	}
	
	public static String[][] sortAsc(String data[][], int sortBy[]) {
		boolean inMySpot = false;
		String moving[];
		if (sortBy.length > data[0].length) {
			//it's fucked throw an error
		}
		else {
			for (int i=0; i < sortBy.length;i++) { //this loop is the entire sort one for each column
				
				for (int x=0; x< data.length;x++) {
					int y=x; //use this instead of x and decrement each loop.
					while (!inMySpot) {
						if (y-1 < 0) {
							inMySpot = true;
						} else if (data[y][sortBy[i]].compareTo(data[y-1][sortBy[i]]) <= 0) {
							inMySpot = true;
						}
						else {
							moving = data[y];
							data[y] = data[y-1];
							data[y-1] = moving;
						}
						y-=1;
					}
					inMySpot = false;
				}
			}
		}
		return data;
	}
	
	public static String[][] sortDesc(String data[][], int sortBy[]) {
		boolean inMySpot = false;
		String moving[];
		if (sortBy.length > data[0].length) {
			//it's fucked throw an error
		}
		else {
			for (int i=0; i < sortBy.length;i++) { //this loop is the entire sort one for each column
				
				for (int x=0; x< data.length;x++) {
					int y=x; //use this instead of x and decrement each loop.
					while (!inMySpot) {
						if (y-1 < 0) {
							inMySpot = true;
						} else if (data[y-1][sortBy[i]].compareTo(data[y][sortBy[i]]) <= 0) {
							inMySpot = true;
						}
						else {
							moving = data[y];
							data[y] = data[y-1];
							data[y-1] = moving;
						}
						y-=1;
					}
					inMySpot = false;
				}
			}
		}
		return data;
	}
				
}