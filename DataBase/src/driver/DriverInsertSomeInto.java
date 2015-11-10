package driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adt.HashTable;
import adt.Response;
import adt.TableList;

public class DriverInsertSomeInto implements Driver {

	private TableList<HashTable> tables;
	private final Pattern pattern;
	public DriverInsertSomeInto(TableList<HashTable> tables2) {
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
			
			String tableName = matcher.group(1);
			
			//get tableName and verify existence
			tableName = matcher.group(1); //get tableName
			if (tables.get(tableName) == null) { //if tableName does not exist.
				return false;
			}
			
			// get the list of columns
			String columns[] = matcher.group(2).split(","); //get the matching group of all the column names
			for (int i=0;i<columns.length;i++) { 
				columns[i] = columns[i].trim(); // remove whitespace in front and behind the column names 
			}
			
			//TODO: Verify that there are not duplicate columnnames
			
			//get the list of data
			String Data[]  = matcher.group(3).split(","); //get the matching group of all the column names
			for (int i=0;i<Data.length;i++) { 
				Data[i] = Data[i].trim(); // remove whitespace in front and behind the column names 
			}
			
			//if matches it should
			// generate an array of values for each column, find out if it needs to be the right length or if i need to insert blanks to anywhere that isn't the right length.
			
			
			//primary key can be anywhere, but must be included. check to make sure that it is there.
			String primKey = tables.get(tableName).columns()[0];
			boolean primKeyExists = false;
			for (int i=0; i<=columns.length-1;i++) {
				if (primKey.equals(columns[i])); //if the primary key exists within the data that was inputted
					primKeyExists = true;
			}
			
			if (primKeyExists == false) {
				return false;
			}
			
			// get the size of column_data and verify that that size aligns with the amount of columns in the table 
			if (columns.length != tables.get(tableName).columns().length) {
				return false;
			}
			
			//if the amount of data entered doesn't match amount of columns entered
			if (columns.length != columnData.length) {
				return false;
			}
			
			
			//put our data in proper order based on the columns from the table we awnt to insert. if there isn't data for a specific column put an empty string in. 
			//might need to initialize columnData to the proper size. IDK?
			boolean inMyPlace = false;
			for (int i=0; i <= tables.get(tableName).columns().length -1 ; i++) {
				for (int x=0; x <=columns.length-1;x++) {
					if (tables.get(tableName).columns()[i].equals(columns[x])) { //this might need to be .equalsIgnoreCase
						columnData[i] = Data[x];
						inMyPlace = true;
					}
				}
				if (!inMyPlace) {
					columnData[i] = ""; //need to verify that this should be an empty string
				}
				inMyPlace = false;
			}
			
			return true;
		}
	}
	private String[] columnData;
	private String tableName;
	private int numColumns;
	
	@Override
	public Response execute() {
	
		// check to see if the primary key already exists in the table if exists this will fail
		if (tables.get(tableName).retrieve(columnData[0]) != null) {
			return new Response(false, "0 rows were inserted into "+tableName+" because the primary key already exists within the database.");
		} else {
			
			String columns[][] = new String[1][1];
			columns[0] = columnData;
			tables.get(tableName).insert(columnData);
			return new Response(true,"1 row was inserted into "+tableName,tables.get(tableName).columns(),columns);
		}
		
		// insert the data into the table
		
		// make sure that the insert was successful, if not print off a failed insert response.
	
		
		
		// check to see if the primary key already exists in the table if exists this will fail
		
		// insert the data into the table
		
		// make sure that the insert was succesfull, if not print off a failed insert response.
	
	}
}