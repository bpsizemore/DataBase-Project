/**
* Models the canonical table discussed in lecture, with
* unlimited stored rows and defined columns, wherein the
* first column is recognized as the sole primary key
* and all data is encoded as strings for simplicity.
* Any exceptional behavior not addressed explicitly in
* the description for each method should result in an
* IllegalArgumentException or IllegalStateException
* as appropriate. The table size should be unbounded.
*/
public interface Table {
/**
* Destroys the current table (if any) and initializes this
* table with the given column names, such that the first
* name provided is the primary key. All column names must
* be at least one character long. The constructor must
* encapsulate a call to this method.
* @param columns The names for one or more columns.
*/
void create(String... columns);
/**
* Retrieves the names of the table columns in sequence
* with the primary key at index zero.
* This must operate in O(1) time.
*/
String[] columns();
/**
* Inserts the given data into the table. The first element
* of the data is the primary key. The data will be inserted
* to each field sequentially starting with the primary key
* for as many elements as are provided up to the number of
* columns in the table. The method throws IllegalStateException
* if a row with the given primary key already exists.
* The primary key must be unique. All values must be non-null.
* Empty strings are acceptable for non-primary key values.
* This must operate in O(alpha) time.
* @param data The data to be inserted sequentially into a new row.
*/
void insert(String... data);
/**
* Updates the given data within the table. The first element
* of the data is the primary key. The data will be updated
* for each field sequentially starting with the primary key
* for as many elements as are provided up to the number of
* columns in the table. The method throws IllegalStateException
* if a row with the given primary key cannot be found.
* The primary key must be unique and non-null.
* Null values will not replace existing field values, but
* empty strings will for non-primary keys.
* This must operate in O(alpha) time.
* @param data The data to be updated sequentially for the existing row.
*/
void update(String... data);
/**
* Retrieves an entire row of data matching the given
* primary key value or null if there is no such row.
* This must operate in O(alpha) time.
* @param key The value of the primary key.
*/
String[] retrieve(String key);
/**
* Retrieves an array of all rows of data matching the
* non-null values of the given filter array or an empty
* array if there are no such rows. Only rows differing
* from the non-null filter values will be excluded in
* the result array, so an all-null filter will return
* all of the rows in the table, since none are excluded.
* This must operate in O(n+m) time.
* @param filter The values that must be matched.
*/
String[][] retrieve(String... filter);
/**
* Returns the result of {@link #retrieve(String)} while
* also deleting the retrieved row from the table.
* @see #retrieve(String)
*/
String[] delete(String key);
/**
* Returns the result of {@link #retrieve(String[])} while
* also deleting the retrieved row(s) from the table.
* @see #retrieve(String[])
*/
String[][] delete(String... filter);
}