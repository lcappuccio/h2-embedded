package org.systemexception.h2embedded.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author leo
 * @date 07/06/15 20:49
 */
@Entity
@SequenceGenerator(name= Data.ID_SEQUENCE, initialValue=11, allocationSize=100)
public class Data {

	static final String ID_SEQUENCE = "id_sequence";
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQUENCE)
	@Column(name = "DATA_ID", unique = true, nullable = false)
	private Long dataId;

	@Column(name = "DATA_VALUE")
	private String dataValue;

	@Column(name = "DATA_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataTimestamp;

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public Date getDataTimestamp() {
		return dataTimestamp;
	}

	public void setDataTimestamp(Date dataTimestamp) {
		this.dataTimestamp = dataTimestamp;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Data data = (Data) o;

		if (!Objects.equals(dataId, data.dataId)) return false;
		if (dataValue != null ? !dataValue.equals(data.dataValue) : data.dataValue != null) return false;
		return dataTimestamp != null ? dataTimestamp.equals(data.dataTimestamp) : data.dataTimestamp == null;

	}
}
