package test;

import org.junit.Test;
import org.systemexception.h2embedded.api.Database;
import org.systemexception.h2embedded.api.Logger;
import org.systemexception.h2embedded.impl.DatabaseService;
import org.systemexception.h2embedded.impl.LoggerImpl;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

/**
 * @author leo
 * @date 07/06/15 21:33
 */
public class DatabaseServiceTest {

    private static final Logger logger = LoggerImpl.getFor(DatabaseService.class);
	private Database sut = new DatabaseService();

	@Test
	public void testIsRunning() throws Exception {
		assertTrue(sut.isRunning());
	}

	@Test
	public void testShutdown() throws SQLException {
		assertTrue(sut.shutdown());
	}

    @Test
    public void testGetTimeStamp() throws SQLException {
        String currentTimeStamp = sut.getCurrentTimeStamp();
        logger.info(currentTimeStamp);
        assert (currentTimeStamp != null);
    }
}