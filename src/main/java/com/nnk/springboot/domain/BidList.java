package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;

/**
 * 
 * implementation of business object: Bid List that will be manipulated by the
 * other layers.
 *
 * the Bid List has twenty-one attributes : BidListId, account, type,
 * bidQuantity, askQuantity, bid, ask, benchmark, bidListDate, commentary,
 * security, status, trader, book, creationName, creationDate, revisionName,
 * revisionDate, dealName, sourceListId and side
 */
@Entity
@Table(name = "bidlist")
public class BidList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bidListId", length = 4)
	private Integer bidListId;
	@NotBlank(message = "Account is mandatory!")
	@Column(name = "account", length = 30)
	private String account;
	@NotBlank(message = "Type is mandatory!")
	@Column(name = "type", length = 30)
	private String type;
	@NotNull(message = "Bid Quantity is mandatory!")
	@Digits(fraction = 3, integer = 10)
	@Column(name = "bidQuantity")
	@Min(value = 1,message = "Bid Quantity must be not null!")
	private Double bidQuantity;
	@Digits(fraction = 3, integer = 10)
	@Column(name = "askQuantity")
	@Min(value = 1)
	private Double askQuantity;
	@Digits(fraction = 3, integer = 10)
	@Column(name = "bid")
	@Min(value = 1)
	private Double bid;
	@Digits(fraction = 3, integer = 10)
	@Column(name = "ask")
	@Min(value = 1)
	private Double ask;
	@Column(name = "benchmark", length = 125)
	private String benchmark;
	@Column(name = "bidListDate")
	private Timestamp bidListDate; /**/
	@Column(name = "commentary", length = 125)
	private String commentary;
	@Column(name = "security", length = 125)
	private String security;
	@Column(name = "status", length = 10)
	private String status;
	@Column(name = "trader", length = 125)
	private String trader;
	@Column(name = "book", length = 125)
	private String book;
	@Column(name = "creationName", length = 125)
	private String creationName;
	@Column(name = "creationDate")
	private Timestamp creationDate;
	@Column(name = "revisionName", length = 125)
	private String revisionName;
	@Column(name = "revisionDate")
	private Timestamp revisionDate;
	@Column(name = "dealName", length = 125)
	private String dealName;
	@Column(name = "dealType", length = 125)
	private String dealType;
	@Column(name = "sourceListId", length = 125)
	private String sourceListId;
	@Column(name = "side", length = 125)
	private String side;

	@Override
	public String toString() {
		return "BidList [account=" + account + ", type=" + type + ", bidQuantity=" + bidQuantity + ", askQuantity="
				+ askQuantity + ", bid=" + bid + ", ask=" + ask + ", benchmark=" + benchmark + ", bidListDate="
				+ bidListDate + ", commentary=" + commentary + ", security=" + security + ", status=" + status
				+ ", trader=" + trader + ", book=" + book + ", creationName=" + creationName + ", creationDate="
				+ creationDate + ", revisionName=" + revisionName + ", revisionDate=" + revisionDate + ", dealName="
				+ dealName + ", dealType=" + dealType + ", sourceListId=" + sourceListId + ", side=" + side
				+ ", getBidListId()=" + getBidListId() + ", getAccount()=" + getAccount() + ", getType()=" + getType()
				+ ", getBidQuantity()=" + getBidQuantity() + ", getAskQuantity()=" + getAskQuantity() + ", getBid()="
				+ getBid() + ", getAsk()=" + getAsk() + ", getBenchmark()=" + getBenchmark() + ", getBidListDate()="
				+ getBidListDate() + ", getCommentary()=" + getCommentary() + ", getSecurity()=" + getSecurity()
				+ ", getStatus()=" + getStatus() + ", getTrader()=" + getTrader() + ", getBook()=" + getBook()
				+ ", getCreationName()=" + getCreationName() + ", getCreationDate()=" + getCreationDate()
				+ ", getRevisionName()=" + getRevisionName() + ", getRevisionDate()=" + getRevisionDate()
				+ ", getDealName()=" + getDealName() + ", getDealType()=" + getDealType() + ", getSourceListId()="
				+ getSourceListId() + ", getSide()=" + getSide() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	/**
	 * Instantiates a new bid list.
	 *
	 * @param account     the account
	 * @param type        the type
	 * @param bidQuantity the bid quantity
	 */
	public BidList(@NotBlank(message = "Account is mandatory") String account,
			@NotBlank(message = "Type is mandatory") String type,
			@Digits(fraction = 3, integer = 10) Double bidQuantity) {
		super();
		this.account = account;
		this.type = type;
		this.bidQuantity = bidQuantity;
	}

	public BidList() {
		super();
	}

	public Integer getBidListId() {
		return bidListId;
	}

	public void setBidListId(Integer bidListId) {
		this.bidListId = bidListId;
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

	public Double getBidQuantity() {
		return bidQuantity;
	}

	public void setBidQuantity(Double bidQuantity) {
		this.bidQuantity = bidQuantity;
	}

	public Double getAskQuantity() {
		return askQuantity;
	}

	public void setAskQuantity(Double askQuantity) {
		this.askQuantity = askQuantity;
	}

	public Double getBid() {
		return bid;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}

	public Double getAsk() {
		return ask;
	}

	public void setAsk(Double ask) {
		this.ask = ask;
	}

	public String getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}

	public Timestamp getBidListDate() {
		return bidListDate;
	}

	public void setBidListDate(Timestamp bidListDate) {
		this.bidListDate = bidListDate;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
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
