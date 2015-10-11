package org.systemexception.h2embedded.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author leo
 * @date 07/06/15 20:49
 */
@Entity
public class Data implements Serializable{

	@Id
	@GeneratedValue
	@Column(name = "DATA_ID", unique = true, nullable = false)
	private int id;
	@Column(name = "DATA_VALUE", nullable = true)
	private String dataValue;

	protected Data() {
	}

	public Data(String dataValue) {
		this.dataValue = dataValue;
	}

	public int getDataId() {
		return id;
	}

	public void setDataId(int dataId) {
		this.id = id;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}
}
