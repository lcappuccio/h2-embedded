package test;

import org.junit.Test;
import org.systemexception.h2embedded.api.Database;
import org.systemexception.h2embedded.impl.DatabaseService;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

/**
 * @author leo
 * @date 07/06/15 21:33
 */
public class DatabaseServiceTest {

	private Database database = new DatabaseService();

	@Test
	public void testIsRunning() throws Exception {
		assertTrue(database.isRunning());
	}

	@Test
	public void testShutdown() throws SQLException {
		assertTrue(database.shutdown());
	}
}