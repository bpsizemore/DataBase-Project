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
			String[] columnMatches = matcher.group(3).split(","); //get the mathcing group of all the column names
			for (int i=0;i<columnMatches.length;i++) { 
				columnMatches[i] = columnMatches[i].trim(); // remove whitespace in front and behind the column names 
			}
			
			
			return true;
		}
	}
	private String[][] rows;
	private String[] columnData;
	private String tableName;
	
	@Override
	public Response execute() {
	
		// check to see if the primary key already exists in the table if exists this will fail
		if () {
		
			return new Response(false, "0 rows were inserted into "+tableName+" because the primary key already exists within the database.");
		
		} else {

			return new Response(true,tables.get(tableName).columns(),rows);
		}
		
		// insert the data into the table
		
		// make sure that the insert was successful, if not print off a failed insert response.
	
	}
	
	
}
