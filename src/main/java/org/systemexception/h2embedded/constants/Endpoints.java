package org.systemexception.h2embedded.constants;

/**
 * @author leo
 * @date 27/02/16 13:21
 */
public class Endpoints {

	public static final String PATH_SEPARATOR = "/";
	// ENDPOINTS
	public static final String H2_CONSOLE = PATH_SEPARATOR + "h2-console";
	public static final String CONTEXT = PATH_SEPARATOR + "api" + PATH_SEPARATOR + "data";
	// PARAMETERS
	public static final String VARIABLE_ID = "id";
	public static final String DATA_ID = PATH_SEPARATOR + "{"+ VARIABLE_ID + "}";
}
