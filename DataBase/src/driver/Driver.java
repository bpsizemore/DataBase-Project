// Optional for submission.
// Recommended but may be redesigned.

package driver;

import adt.Response;

/**
 * Validates a query string's syntax and provides
 * a process to run if the syntax is valid.
 */
public interface Driver {
	/**
	 * Validates a query string according to a driver-specific syntax.
	 * 
	 * If valid, returns {@code true} and (as a side-effect) prepares the
	 * necessary persistent data for the {@link #process()} method to utilize
	 * when called subsequently.
	 * 
	 * Otherwise, returns {@code false}.
	 * 
	 * @param query		A string containing a purported SQL query.
	 * @return			Whether the query string is valid.
	 */
	public boolean valid(String query);
	
	/**
	 * Processes the query according to a driver-specific behavior, utilizing
	 * the persistent data furnished by {@link #valid(String)}.
	 * 
	 * Can and will only be called after {@link #valid(String)} has previously
	 * returned a {@code true} result.
	 * 
	 * Returns a {@link Response} object.
	 * 
	 * @return The response of the query process.
	 */
	public Response execute();
}
