package org.systemexception.h2embedded.model;

import org.springframework.data.annotation.Id;

/**
 * @author leo
 * @date 07/06/15 20:49
 */
public class Data {

	@Id
	private String id;

	public String getDataId() {
		return id;
	}

	public void setDataId(int dataId) {
		this.id = id;
	}
}
