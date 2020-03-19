
package com.technest.rest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.technest.rest.dao.EmployeeDAO;
import com.technest.rest.model.Employee;
import com.technest.rest.model.Transfer;

@RestController
@RequestMapping(path = "/transfer")
public class TransferController {
	@Autowired
	private EmployeeDAO employeeDao;

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addTransfer(
			@RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
			@RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "EUROPE") String headerLocation,
			@RequestBody Transfer tra) throws Exception {

		Employee efrom = employeeDao.getByName(tra.getFrom());
		Employee eto = employeeDao.getByName(tra.getTo());

		efrom.setLastName(efrom.getLastName() + "a");
		eto.setEmail(eto.getEmail() + "b");

		boolean res1 = employeeDao.updateEmployee(efrom.getId(), efrom);
		boolean res2 = employeeDao.updateEmployee(eto.getId(), eto);

		// Create resource location
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{res}").buildAndExpand(res1 & res2)
				.toUri();

		// Send location in response
		return ResponseEntity.created(location).build();
	}
}
