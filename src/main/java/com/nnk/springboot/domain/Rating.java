package com.nnk.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 
 * implementation of business object: Rating that will be manipulated by the
 * other layers.
 *
 * the Rating has five attributes : id, moodysRating, sandPRating, fitchRating
 * and orderNumber
 */
@Entity
@Table(name = "rating")
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 4)
	private Integer id;
	@NotBlank(message = "Moody's Rating is mandatory!")
	@Column(name = "moodysRating", length = 125)
	private String moodysRating;
	@NotBlank(message = "Sand P Rating is mandatory!")
	@Column(name = "sandPRating", length = 125)
	private String sandPRating;
	@NotBlank(message = "Fitch Rating is mandatory!")
	@Column(name = "fitchRating", length = 125)
	private String fitchRating;
	@NotNull(message = "Order Number is mandatory!")
	@Column(name = "orderNumber")
	@Min(value = 1, message = "Order Number must be at least 1!")
	private Integer orderNumber;

	/**
	 * Instantiates a new rating.
	 *
	 * @param moodysRating the moodys rating
	 * @param sandPRating  the sand P rating
	 * @param fitchRating  the fitch rating
	 * @param orderNumber  the order number
	 */
	public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
		super();
		this.moodysRating = moodysRating;
		this.sandPRating = sandPRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", moodysRating=" + moodysRating + ", sandPRating=" + sandPRating + ", fitchRating="
				+ fitchRating + ", orderNumber=" + orderNumber + "]";
	}

	public Rating() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMoodysRating() {
		return moodysRating;
	}

	public void setMoodysRating(String moodysRating) {
		this.moodysRating = moodysRating;
	}

	public String getSandPRating() {
		return sandPRating;
	}

	public void setSandPRating(String sandPRating) {
		this.sandPRating = sandPRating;
	}

	public String getFitchRating() {
		return fitchRating;
	}

	public void setFitchRating(String fitchRating) {
		this.fitchRating = fitchRating;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

}
