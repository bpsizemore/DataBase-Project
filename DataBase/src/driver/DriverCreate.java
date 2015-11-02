package driver;

import java.util.regex.Matcher;
import java.util.regex.*;

import adt.HashTable;
import adt.Response;
import adt.TableList;
public class DriverCreate implements Driver{

	private TableList<HashTable> tables;
	private final Pattern pattern;
	public DriverCreate(TableList<HashTable> tables2) {
		this.tables = tables2;
		pattern = Pattern.compile(
				"\\s*create\\s*table\\s*([a-zA-Z][\\w_]*)\\s*\\(\\s*((?:[a-zA-z][\\w_]*)+\\s*(?:[a-zA-Z][\\w_]*\\s*[,]\\s*)*(?:[a-zA-Z][\\w_]*))\\s*\\)\\s*",
				//"\\s*create\\s*table\\s*([\\w][\\w\\d_]*)\\s*\\(\\s*([\\w][\\w\\d_]*)\\s*,\\)\\s*",
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
			
			
			String[] columnMatches = matcher.group(2).split(","); //get the mathcing group of all the column names
			for (int i=0;i<columnMatches.length;i++) { 
				columnMatches[i] = columnMatches[i].trim(); // remove whitespace in front and behidn the column names 
			}
			
			this.tableName = matcher.group(1); //tablename will always be the first group
			this.columns = columnMatches; 
			this.numColumns = columns.length;
			// create the new table.
			
			
			for (int i=0;i<this.columns.length;i++) {
				for (int x=0;x<this.columns.length;x++) {
					if (i != x && columns[i].equalsIgnoreCase(columns[x])) {
						return false;
					}
				}
			}
			
			// create a string array of the columnNames
			return true;
		}
	}
	private String[] columns;
	private String tableName;
	private int numColumns;
	
	@Override
	public Response execute() {
		//make sure there are no duplicate tableNames
		if (tables.get(tableName) != null) {
			return new Response(false, "A table with the name "+tableName+" already exists.");
		} else {
		
			HashTable newtable = new HashTable();
			newtable.create(columns);
		
			this.tables.add(newtable , tableName);
			return new Response(true,"The table "+tableName+" has been created with "+numColumns+" columns.");
		}
	}
}
