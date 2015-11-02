// Optional for submission.
// Not exhaustive and needs improvement.

package core;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import adt.Response;

public class VolatileServerTest {
	@Before
	public void initialize() {
		DB = new VolatileServer();
	}
	
	private Server DB;
	
	@Test
	public void testRangeQuery() {
		Response[] responses = DB.interpret("RANGE(5);");
		assertThat(responses.length, is(1));
		
		assertThat(responses[0].success(),	is( true ));
		assertThat(responses[0].message(),	is( "" ));
		assertThat(responses[0].columns(),	is( new String[]{"number"} ));
		assertThat(responses[0].rows(),		is( new String[][]{
			{"1"},
			{"2"},
			{"3"},
			{"4"},
			{"5"}
		} ));
	}
	
	@Test
	public void testRangeQueryWithName() {
		Response[] responses = DB.interpret("RANGE(3,x);");
		assertThat(responses.length, is(1));
		
		assertThat(responses[0].success(),	is( true ));
		assertThat(responses[0].message(),	is( "" ));
		assertThat(responses[0].columns(),	is( new String[]{"x"} ));
		assertThat(responses[0].rows(),		is( new String[][]{
			{"1"},
			{"2"},
			{"3"}
		} ));
	}
	
	@Test
	public void testEchoQuery() {
		String data = "The quick brown fox jumps over the lazy dog.";
		
		Response[] responses = DB.interpret("ECHO " + data + ";");
		assertThat(responses.length, is(1));
		
		assertThat(responses[0].success(),	is( true ));
		assertThat(responses[0].message(),	is( data ));
		assertThat(responses[0].columns(),	is( new String[0] ));
		assertThat(responses[0].rows(),		is( new String[0][0] ));
	}
}
