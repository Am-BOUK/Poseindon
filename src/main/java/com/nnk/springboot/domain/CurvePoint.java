package com.nnk.springboot.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 
 * implementation of business object: Curve Point that will be manipulated by
 * the other layers.
 *
 * the Curve Point has six attributes : id, curveId, asOfDate, term, value and
 * creationDate
 */
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 4)
	private Integer id;
	@NotNull(message = "Curve ID is mandatory!")
	@Column(name = "curveId")
	@Min(value = 1, message = "Curve ID must not be null!")
	private Integer curveId;
	@Column(name = "asOfDate")
	private Timestamp asOfDate;
	@NotNull(message = "Term is mandatory!")
	@Digits(fraction = 3, integer = 10)
	@Column(name = "term")
	@Min(value = 1, message = "Term must be not null!")
	private Double term;
	@NotNull(message = "Value is mandatory!")
	@Column(name = "value")
	@Min(value = 1, message = "Value must be not null!")
	private Double value;
	@Column(name = "creationDate")
	private Timestamp creationDate;

	/**
	 * Instantiates a new curve point.
	 *
	 * @param curveId the curve id
	 * @param term    the term
	 * @param value   the value
	 */
	public CurvePoint(Integer curveId, Double term, Double value) {
		super();
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}

	public CurvePoint() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCurveId() {
		return curveId;
	}

	public void setCurveId(Integer curveId) {
		this.curveId = curveId;
	}

	public Timestamp getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(Timestamp asOfDate) {
		this.asOfDate = asOfDate;
	}

	public Double getTerm() {
		return term;
	}

	public void setTerm(Double term) {
		this.term = term;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

}
