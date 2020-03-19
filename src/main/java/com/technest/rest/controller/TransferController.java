package com.technest.rest.controller;

import java.math.BigDecimal;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.technest.rest.dao.AccountDAO;
import com.technest.rest.model.Account;
import com.technest.rest.model.Transfer;

@RestController
@RequestMapping(path = "/transfer")
public class TransferController {
	@Autowired
	private AccountDAO accountDao;

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addTransfer(
			@RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "EUROPE") String headerLocation,
			@RequestBody Transfer tra) throws Exception {

		Account afrom = accountDao.getByName(tra.getFrom());
		Account ato = accountDao.getByName(tra.getTo());
		boolean res1 = false, res2 = false;
		if (afrom.isTreasury() || afrom.getBalance() >= tra.getBalance()) {
			afrom.setBalance(round(afrom.getBalance() - tra.getBalance()));
			ato.setBalance(round(ato.getBalance() + tra.getBalance()));
			res1 = accountDao.updateAccount(afrom.getId(), afrom);
			res2 = accountDao.updateAccount(ato.getId(), ato);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{res}").buildAndExpand(res1 & res2)
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@SuppressWarnings("deprecation")
	public float round(float bal) {
		return BigDecimal.valueOf(bal).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}

}
