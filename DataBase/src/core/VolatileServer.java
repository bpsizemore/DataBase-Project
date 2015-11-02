// Required for submission.
// Modify to meet the specification.
// Will be subject to direct JUnit testing.

package core;
import adt.HashTable;
import adt.Response;
import adt.TableList;

import java.util.ArrayList;
import driver.Driver;
import driver.DriverDrop;
import driver.DriverEcho;
import driver.DriverRange;
import driver.DriverDrop;
import driver.DriverCreate;
import driver.DriverShow;


/**
 * Implements a server which exists only in memory,
 * not in permanent storage.
 */
public class VolatileServer implements Server {
	// TODO: store the persistent collection of tables

	TableList<HashTable> tables = new TableList<HashTable>();
	public Response[] interpret(String script) {
		Driver CreateTable = new DriverCreate(tables);
		Driver DropTable = new DriverDrop(tables);
		Driver ShowTables = new DriverShow(tables);
		
		
		// TODO: separate script input by ";" delimiter. Then run a for loop and put every single line through it to see if valid, if valid perform the command, and store the result in result[]. After performing all commands, return all results in result[]
		// TODO: wrongly assumes script to be single query
		String scripts[] = script.split("\\;");
		Response Responses[] = new Response[scripts.length];
		for (int i=0; i<scripts.length;i++) {
			if (CreateTable.valid(scripts[i])) {
				Responses[i] = CreateTable.execute();
			}
			else if(DropTable.valid(scripts[i])) {
				Responses[i] = DropTable.execute();
			}
			
			else if (ShowTables.valid(scripts[i])) {
				Responses[i] = ShowTables.execute();
			}		
			else {
					Responses[i] = new Response(false, "Invalid query: " + scripts[i]);
			}
		}
		return Responses;
	}
}