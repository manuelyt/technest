package com.technest.rest.model;

public class Transfer {

	public Transfer() {

	}

	public Transfer(String from, String to, float balance) {
		super();
		this.from = from;
		this.to = to;
		this.balance = balance;
	}

	private String from;
	private String to;
	private float balance;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Employee [from=" + from + ", to=" + to + ", balance=" + balance + "]";
	}
}
