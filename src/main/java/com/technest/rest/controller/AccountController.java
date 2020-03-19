
package com.technest.rest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
			@RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "EUROPE") String headerLocation,
			@RequestBody Account account) throws Exception {
		Integer id = accountDao.getAllAccounts().getAccountList().size() + 1;
		account.setId(id);

		accountDao.addAccount(account);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(account.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateAccount(
			@RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "EUROPE") String headerLocation,
			@PathVariable String id, @RequestBody Account account) throws Exception {
		boolean res = accountDao.updateAccount(Integer.parseInt(id), account);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{res}").buildAndExpand(res).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteAccount(
			@RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "EUROPE") String headerLocation,
			@PathVariable String id) throws Exception {
		boolean res = accountDao.delete(Integer.parseInt(id));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{res}").buildAndExpand(res).toUri();
		return ResponseEntity.created(location).build();
	}

}
