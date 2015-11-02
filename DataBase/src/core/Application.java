// Required for submission.
// Modify to meet the specification.

package core;

import java.util.Arrays;
import java.util.Scanner;

import adt.Response;

/**
 * Provides an interactive prompt for a user to engage with
 * a database server created at runtime.
 */
public class Application {
	public static void main(String[] args) { //Currently this accepts ONE line. IT needs to accept repeated infinite lines.
		Scanner in = new Scanner(System.in);
		boolean running = true;
		boolean processing = true;
		
		while (running) { //start the prompt
			System.out.print(">> ");
			while (processing) { //process input (This ensures that multiline input will be processed.) Find a way to separate by lines. Probably semicolons
				String line = in.nextLine();

				// process this line
				Server DB = new VolatileServer();
				Response out = DB.interpret(line)[0];

				System.out.println("Success: " + out.success());
				System.out.println("Message: " + out.message());
				System.out.println("Columns: " + Arrays.deepToString(out.columns()));
				System.out.println("Rows:    " + Arrays.deepToString(out.rows()));
				
				if (in.nextLine() == null) {
					processing = false;
				}
			}
		}
		in.close();
		
	}
}
