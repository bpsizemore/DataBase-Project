package driver;

import java.util.regex.Matcher;
import java.util.regex.*;

import adt.HashTable;
import adt.Response;
import adt.TableList;
public class DriverShow implements Driver{

	private TableList<HashTable> tables;
	private final Pattern pattern;
	public DriverShow(TableList<HashTable> tables2) {
		this.tables = tables2;
		pattern = Pattern.compile(
			"\\s*show\\s*tables\\s*",
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
			// get all of the tablenames in 2d array of size [numTables][1]
			return true;
		}
	}
	private int numTables;
	private String columns[] = new String[] {"table_name"};
	private String rows[][];
	
	@Override
	public Response execute() {
		
		numTables = tables.size();
		rows = new String[numTables][0];
		if (numTables != 0){
			for (int i=0;i<numTables;i++) {
				rows [i][0] = tables.getName(i);
			}
		} 
		
		return new Response(true, "There are "+numTables+" tables.", columns, rows);
	}
}
