package org.systemexception.h2embedded.enums;

/**
 * @author leo
 * @date 07/06/15 21:53
 */
public enum DatabaseProperties {

	DATABASE_USER("h2"), DATABASE_PASSWORD("h2"), DATABASE_URL("jdbc:h2:mem");

	private final String label;

	DatabaseProperties(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return label;
	}
}
