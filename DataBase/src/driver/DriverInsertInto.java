package driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adt.HashTable;
import adt.Response;
import adt.TableList;

public class DriverInsertInto implements Driver{

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
			
			//get tableName and verify existence
			tableName = matcher.group(1); //get tableName
			if (tables.get(tableName) == null) { //if tableName does not exist.
				return false;
			}
			
			//get data to insert
			columnData = matcher.group(2).split(","); //get the matching group of all the column names
			for (int i=0;i<columnData.length;i++) { 
				columnData[i] = columnData[i].trim(); // remove whitespace in front and behind the column names 
			}
			
			//check length of data vs num columns in table
			if (tables.get(tableName).columns().length != columnData.length) {
				return false;
			}
			
			return true;
		}
	}
	private String[] columnData;
	private String tableName;
	
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
	
	}
}
