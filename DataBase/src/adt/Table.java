// Required for submission.
// Do not modify.

package adt;

/**
 * Models the canonical table discussed in lecture, with
 * unlimited stored rows and defined columns, wherein the
 * first column is recognized as the sole primary key
 * and all data is encoded as strings for simplicity.
 * 
 * Any exceptional behavior not addressed explicitly
 * should simply be avoided.
 * 
 * There should be no defined constructors. All initialization
 * should be handled by the {@link #create(String...)} method.
 */
public interface Table {
	/**
	 * Destroys the current table (if any) and initializes this
	 * table with the given non-null column names, such that the
	 * first name provided is the primary key.
	 * 
	 * @param columns The names for one or more columns.
	 */
	void create(String... columns);
	
	/**
	 * Retrieves the names of the table columns in sequence
	 * with the primary key at index zero.
	 * 
	 * This must operate in O(1) time.
	 * 
	 * @return The names of the columns.
	 */
	String[] columns();	
	
	/**
	 * Inserts the given data into the table. The first element
	 * of the data is the primary key. The data will be inserted
	 * to each field sequentially starting with the primary key.
	 * 
	 * The method throws an IllegalStateException if a row with
	 * the given primary key already exists.
	 * 
	 * The primary key must be unique. All values must be non-null.
	 * Empty strings are acceptable for all values.
	 * 
	 * This must operate in O(alpha) time.
	 * 
	 * @param data The data row to be inserted sequentially.
	 */
	void insert(String... data);
	
	/**
	 * Updates the given data within the table. The first element
	 * of the data is the primary key. The data will be updated
	 * for each field sequentially starting with the primary key.
	 * 
	 * The method throws an IllegalStateException if a row with
	 * the given primary key cannot be found.
	 * 
	 * The data must in the same format as that prescribed by
	 * the {@link #insert(String...)} method, except that fields
	 * other than the primary key may be null. Any such null field
	 * will be ignored when updating the existing row data and the
	 * old field value will be kept in the row without change.
	 * 
	 * This must operate in O(alpha) time.
	 * 
	 * @param data The data row to be updated sequentially.
	 */
	void update(String... data);
	
	/**
	 * Retrieves an entire row of data matching the given
	 * primary key value or null if there is no such row.
	 * 
	 * This must operate in O(alpha) time.
	 * 
	 * @param key	The value of the primary key.
	 * @return		The row corresponding to the primary key.
	 */
	String[] retrieve(String key);	
	
	/**
	 * Retrieves an array of all rows of data matching the
	 * non-null values of the given filter or an empty array
	 * if there are no such rows. Only rows differing from the
	 * non-null filter values will be excluded in the result
	 * array. This means that an all-null filter will return
	 * all of the rows in the table, since none are excluded.
	 * 
	 * This must operate in O(n+m) time.
	 * 
	 * @param filter	The values that must be matched.
	 * @return			The matching rows.
	 */
	String[][] retrieve(String... filter);
	
	/**
	 * Returns the result of {@link #retrieve(String)} while
	 * also deleting the retrieved row from the table.
	 * 
	 * @see #retrieve(String)
	 */
	String[] delete(String key);	
	
	/**
	 * Returns the result of {@link #retrieve(String[])} while
	 * also deleting the retrieved row(s) from the table.
	 * 
	 * @see #retrieve(String[])
	 */
	String[][] delete(String... filter);
}
