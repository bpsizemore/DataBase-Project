// Required for submission.
// Do not modify.

package adt;

/**
 * Encapsulates a query result comprising a boolean indicating success
 * or failure, a message describing the outcome, and the columns and rows
 * that constitute the outcome. All fields other than success are optional,
 * but if the rows are included then so must the columns be.
 * 
 * The accessors are designed never to return null so that response
 * components can be safely accessed for length without null pointers.
 */
public class Response {
	private boolean success;
	private String message;
	private String[] columns;
	private String[][] rows;
	
	public Response(boolean success) {
		this.success = success;
	}
	public Response(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	public Response(boolean success, String[] columns, String[][] rows) {
		this.success = success;
		this.columns = columns;
		this.rows = rows;
	}
	public Response(boolean success, String message, String[] columns, String[][] rows) {
		this.success = success;
		this.message = message;
		this.columns = columns;
		this.rows = rows;
	}
	
	public boolean success() {
		return success;
	}
	public String message() {
		return message != null ? message : "";
	}
	public String[] columns() {
		return columns != null ? columns : new String[0];
	}
	public String[][] rows() {
		return rows != null ? rows : new String[0][0];
	}
}