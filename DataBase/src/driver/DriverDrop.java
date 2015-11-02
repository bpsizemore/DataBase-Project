package driver;

import java.util.regex.Matcher;
import java.util.regex.*;

import adt.HashTable;
import adt.Response;
import adt.TableList;
public class DriverDrop implements Driver{
	private final Pattern pattern;
	private TableList<HashTable> tables;
	public DriverDrop(TableList<HashTable> tables2) {
		this.tables = tables2;
		pattern = Pattern.compile(
			"\\s*drop\\s*table\\s*([\\w][\\w\\d_]*)",
			
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
			Name = matcher.group(1);
			return true;
		}
	}

	private String Name;
	@Override
	public Response execute() {
		if (tables.get(Name) == null) {
			return new Response(false, "A table with the name "+Name+" does not exist.");
		}
		tables.remove(Name);
		return new Response(true, "The table named "+Name+"has been dropped successfully.");
	}
	
}
