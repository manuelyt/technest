package com.technest.rest.model;

public class Account {

	public Account() {
	}

	public Account(Integer id, String name, String currency, float balance, boolean treasury) {
		super();
		this.id = id;
		this.name = name;
		this.currency = currency;
		this.balance = balance;
		this.treasury = treasury;
	}

	private Integer id;
	private String name;
	private String currency;
	private float balance;
	private boolean treasury;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public boolean isTreasury() {
		return treasury;
	}

	public void setTreasury(boolean treasury) {
		this.treasury = treasury;
	}

	public void setAll(Integer id, String name, String currency, float balance, boolean treasury) {
		this.id = id;
		this.name = name;
		this.currency = currency;
		this.balance = balance;
		this.treasury = treasury;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", currency=" + currency + ", balance=" + balance
				+ ", treasury=" + treasury + "]";
	}
}
