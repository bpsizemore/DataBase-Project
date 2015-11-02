// Required for submission.
// Do not modify.

package core;

import adt.Response;

/**
 * Models a database server with an internal state that
 * persists between method calls representing the collection
 * of tables and their data in the database.
 * 
 * There should be a parameterless constructor (explicitly
 * defined or automatically included) which handles any
 * necessary initialization for the server.
 */
public interface Server {
	/**
	 * Attempt to interpret an SQL script string by parsing it
	 * into individual queries, attempting to execute them in
	 * sequence, returning their responses in sequence, and applying
	 * their side-effects to the applicable tables and data.
	 * 
	 * The script may contain zero or more queries to be interpreted
	 * in sequence. It should not be presumed to be a single query
	 * nor should it be presumed to have valid syntax.
	 * 
	 * @param query		An SQL script string.
	 * @return			The sequential responses from the queries.
	 */
	public Response[] interpret(String script);
}
