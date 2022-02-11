package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;

/**
 * 
 * implementation of business object: Trade that will be manipulated by the
 * other layers.
 *
 * the Trade has twenty-one attributes : tradeId, account, type, bidQuantity,
 * sellQuantity, buyPrice, sellPrice, tradeDate, security, status, trader,
 * benchmark, book, creationName, creationDate, revisionName, revisionDate,
 * dealName, dealType, sourceListId and side
 */
@Entity
@Table(name = "trade")
public class Trade {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tradeId", length = 4)
	private Integer tradeId;
	@NotBlank(message = "Account is mandatory!")
	@Column(length = 30)
	private String account;
	@NotBlank(message = "Type is mandatory!")
	@Column(length = 30)
	private String type;
	@NotNull(message = "Buy Quantity is mandatory!")
	@Digits(fraction = 2, integer = 10)
	@Column(name = "buyQuantity")
	@Min(value = 0, message = "Buy Quantity must be at least 0!")
	private Double buyQuantity;
	@Digits(fraction = 2, integer = 10)
	@Column(name = "sellQuantity")
	@Min(value = 1)
	private Double sellQuantity;
	@Digits(fraction = 2, integer = 10)
	@Column(name = "buyPrice")
	@Min(value = 1)
	private Double buyPrice;
	@Digits(fraction = 2, integer = 10)
	@Column(name = "sellPrice")
	@Min(value = 1)
	private Double sellPrice;
	@Column(name = "tradeDate")
	private Timestamp tradeDate;
	@Column(length = 125)
	private String security;
	@Column(length = 10)
	private String status;
	@Column(length = 125)
	private String trader;
	@Column(length = 125)
	private String benchmark;
	@Column(length = 125)
	private String book;
	@Column(name = "creationName", length = 125)
	private String creationName;
	@Column(name = "creationDate")
	private Timestamp creationDate;
	@Column(name = "revisionName", length = 125)
	private String revisionName;
	@Column(name = "revisionDate")
	Timestamp revisionDate;
	@Column(name = "dealName", length = 125)
	private String dealName;
	@Column(name = "dealType", length = 125)
	private String dealType;
	@Column(name = "sourceListId", length = 125)
	private String sourceListId;
	@Column(length = 125)
	private String side;

	public Trade(@NotBlank(message = "Accountis mandatory.") String account,
			@NotBlank(message = "Type mandatory.") String type,
			@NotNull(message = "Buy Quantity mandatory.") @Digits(fraction = 2, integer = 10) @Min(0) Double buyQuantity) {
		super();
		this.account = account;
		this.type = type;
		this.buyQuantity = buyQuantity;
	}

	public Trade() {
		super();
	}

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(Double buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	public Double getSellQuantity() {
		return sellQuantity;
	}

	public void setSellQuantity(Double sellQuantity) {
		this.sellQuantity = sellQuantity;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Timestamp getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Timestamp tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrader() {
		return trader;
	}

	public void setTrader(String trader) {
		this.trader = trader;
	}

	public String getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getCreationName() {
		return creationName;
	}

	public void setCreationName(String creationName) {
		this.creationName = creationName;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getRevisionName() {
		return revisionName;
	}

	public void setRevisionName(String revisionName) {
		this.revisionName = revisionName;
	}

	public Timestamp getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(Timestamp revisionDate) {
		this.revisionDate = revisionDate;
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getSourceListId() {
		return sourceListId;
	}

	public void setSourceListId(String sourceListId) {
		this.sourceListId = sourceListId;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

}
