package test;

import org.junit.Test;
import org.systemexception.h2embedded.api.Logger;
import org.systemexception.h2embedded.impl.LoggerImpl;

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
		assertTrue(new File("info.log").exists());
	}

	@Test
	public void testDebug() throws Exception {
		sut.info("TestDebug");
		assertTrue(new File("debug.log").exists());
	}

	@Test
	public void testError() throws Exception {
		sut.info("TestError");
		assertTrue(new File("error.log").exists());
	}
}