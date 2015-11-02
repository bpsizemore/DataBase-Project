package driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adt.HashTable;
import adt.Response;
import adt.TableList;

public class DriverInsertSomeInto {

	private TableList<HashTable> tables;
	private final Pattern pattern;
	public DriverInsertInto(TableList<HashTable> tables2) {
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
			
			//if matches it should
			// generate an array of values for each column, find out if it needs to be the right length or if i need to isnert blanks to anywhere that isn't the right length.
			// believe it should be the correct length in order to do proper insert.
			//primary key must be first column
			//grab the tableName to insert into
			// verify that the table exists.

			// get the size of column_data and verify that that size aligns with the amount of columns in the table 
			
			return true;
		}
	}
	private String[] data;
	private String tableName;
	private int numColumns;
	
	@Override
	public Response execute() {
	
		// check to see if the primary key already exists in the table if exists this will fail
		
		// insert the data into the table
		
		// make sure that the insert was succesfull, if not print off a failed insert response.
	
	}
}