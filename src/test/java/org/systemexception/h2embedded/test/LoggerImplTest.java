package org.systemexception.h2embedded.test;

import org.junit.Test;
import org.systemexception.logger.api.Logger;
import org.systemexception.logger.impl.LoggerImpl;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * @author leo
 * @date 07/06/15 18:09
 */
public class LoggerImplTest {

	private static final Logger sut = LoggerImpl.getFor(LoggerImplTest.class);

	@Test
	public void testInfo() throws Exception {
		sut.info("TestInfo");
		assertTrue(new File("database_info.log").exists());
	}

	@Test
	public void testDebug() throws Exception {
		sut.debug("TestDebug");
		assertTrue(new File("database_debug.log").exists());
	}

	@Test
	public void testError() throws Exception {
		sut.error("TestError", new Exception());
		assertTrue(new File("database_error.log").exists());
	}
}