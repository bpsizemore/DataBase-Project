package driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adt.HashTable;
import adt.Response;
import adt.TableList;

public class DriverInsertSomeInto {

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
			
			//if matches it should
			
			
			// generate an array of values for each column, find out if it needs to be the right length or if i need to isnert blanks to anywhere that isn't the right length.
			// generate a list of the columns names.
			// primary key must be included in column names
			//column names may NOT be repeated
			// they may be in any order
			// reorder the column_values so that they are in proper order, with blank items in columns that weren't specified.
			
			//grab the tableName to insert into
			// verify that the table exists.
			// 
			
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
