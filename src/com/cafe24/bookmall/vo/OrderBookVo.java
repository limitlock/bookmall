package com.cafe24.bookmall.vo;

public class OrderBookVo {

	private Long bookNo;
	private Long orderNo;
	private Long amount;
	private String bookName;

	public Long getBookNo() {
		return bookNo;
	}

	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[BookNo]: " + bookNo + ", [BookName]: " + bookName + ", [amount]: " + amount;
	}

}
