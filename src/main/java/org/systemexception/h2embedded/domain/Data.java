package org.systemexception.h2embedded.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author leo
 * @date 07/06/15 20:49
 */
@Entity
public class Data implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "DATA_ID", unique = true, nullable = false)
	private int dataId;
	@Column(name = "DATA_VALUE", nullable = true)
	private String dataValue;

	public Data() {
	}

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Data data = (Data) o;

		if (dataId != data.dataId) return false;
		return dataValue.equals(data.dataValue);

	}

	@Override
	public int hashCode() {
		int result = dataId;
		result = 31 * result + dataValue.hashCode();
		return result;
	}
}
