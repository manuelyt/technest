
package com.technest.rest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.technest.rest.dao.AccountDAO;
import com.technest.rest.model.Account;
import com.technest.rest.model.Accounts;

@RestController
@RequestMapping(path = "/account")
public class AccountController {
	@Autowired
	private AccountDAO accountDao;

	@GetMapping(path = "/", produces = "application/json")
	public Accounts getAccounts() {
		return accountDao.getAllAccounts();
	}

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addEmployee(
			@RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
			@RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
			@RequestBody Account account) throws Exception {
		// Generate resource id
		Integer id = accountDao.getAllAccounts().getAccountList().size() + 1;
		account.setId(id);

		// add resource
		accountDao.addAccount(account);

		// Create resource location
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(account.getId())
				.toUri();

		// Send location in response
		return ResponseEntity.created(location).build();
	}
}
