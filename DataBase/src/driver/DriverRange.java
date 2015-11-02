// Optional for submission.
// Demonstration only and may be removed.

package driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adt.Response;

public class DriverRange implements Driver {
	private final Pattern pattern;
	public DriverRange() {
		pattern = Pattern.compile(
			"RANGE\\(([1-9][0-9]*)(?:,(\\w+))?\\);",
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
			bound = Integer.parseInt(matcher.group(1));
			name = matcher.group(2) != null ? matcher.group(2) : "number";
			return true;
		}
	}

	private int bound;
	private String name;
	
	@Override
	public Response execute() {
		String[] columns = new String[]{name};
		
		String[][] rows = new String[bound][1];
		for (int i = 0; i < bound; i++) {
			rows[i][0] = Integer.toString(i+1);
		}
		
		return new Response(
			true,
			columns,
			rows
		);
	}
}
