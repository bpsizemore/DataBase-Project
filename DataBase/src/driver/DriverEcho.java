// Optional for submission.
// Demonstration only and may be removed.

package driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adt.Response;

public class DriverEcho implements Driver {
	private final Pattern pattern;
	public DriverEcho() {
		pattern = Pattern.compile(
			"ECHO (.*);",
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
			text = matcher.group(1);
			return true;
		}
	}

	private String text;
	
	@Override
	public Response execute() {
		return new Response(true, text);
	}
}
