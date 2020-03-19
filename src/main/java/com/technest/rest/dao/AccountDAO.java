package com.technest.rest.dao;

import org.springframework.stereotype.Repository;

import com.technest.rest.model.Account;
import com.technest.rest.model.Accounts;

@Repository
public class AccountDAO {
	private static Accounts list = new Accounts();

	static {
		list.getAccountList().add(new Account(1, "food", "EUR", 22.22f, false));
		list.getAccountList().add(new Account(2, "work", "USD", 11.22f, false));
		list.getAccountList().add(new Account(3, "sport", "EUR", 33.22f, false));
		list.getAccountList().add(new Account(4, "ac1", "EUR", 44.22f, false));
		list.getAccountList().add(new Account(5, "ac2", "EUR", 55.22f, false));
		list.getAccountList().add(new Account(6, "ac3", "EUR", 21.22f, false));
		list.getAccountList().add(new Account(7, "ac4", "EUR", 23.22f, false));
		list.getAccountList().add(new Account(8, "ac5", "EUR", 24.22f, true));
	}

	public Accounts getAllAccounts() {
		return list;
	}

	public Account getByName(String name) {
		for (Account acc : list.getAccountList()) {
			if (acc.getName().equals(name))
				return acc;
		}
		return null;
	}

	public void addAccount(Account account) {
		list.getAccountList().add(account);
	}

	public boolean updateAccount(int id, Account account) {
		for (Account acc : list.getAccountList()) {
			if (acc.getId() == id) {
				list.getAccountList().remove(acc);
				list.getAccountList().add(account);
				acc.setAll(id, acc.getName(), acc.getCurrency(), acc.getBalance(), acc.isTreasury());
				return true;
			}
		}
		return false;
	}

	public boolean deleteByName(String name) {
		for (Account acc : list.getAccountList()) {
			if (acc.getName().contentEquals(name)) {
				list.getAccountList().remove(acc);
				return true;
			}
		}
		return false;
	}

	public boolean delete(int id) {
		for (Account acc : list.getAccountList()) {
			if (acc.getId() == id) {
				list.getAccountList().remove(acc);
				return true;
			}
		}
		return false;
	}
}
