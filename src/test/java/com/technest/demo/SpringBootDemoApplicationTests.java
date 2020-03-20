package com.technest.demo;

//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.technest.rest.SpringBootDemoApplication;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes=SpringBootDemoApplication.class)
//public class SpringBootDemoApplicationTests {
//
//	@Test
//	public void contextLoads() {
//	}
//
//}
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpHeaders;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import com.technest.rest.SpringBootDemoApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SpringBootDemoApplication.class)
class SpringBootDemoApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@DisplayName("test get account")
	void testGet() {
		String message = this.restTemplate.getForObject("/account/", String.class);

		assertEquals(
				"{\"accountList\":[{\"id\":1,\"name\":\"food\",\"currency\":\"EUR\",\"balance\":22.22,\"treasury\":false},"
						+ "{\"id\":2,\"name\":\"work\",\"currency\":\"USD\",\"balance\":11.22,\"treasury\":false},"
						+ "{\"id\":3,\"name\":\"sport\",\"currency\":\"EUR\",\"balance\":33.22,\"treasury\":false},"
						+ "{\"id\":4,\"name\":\"ac1\",\"currency\":\"EUR\",\"balance\":44.22,\"treasury\":false},"
						+ "{\"id\":5,\"name\":\"ac2\",\"currency\":\"EUR\",\"balance\":55.22,\"treasury\":false},"
						+ "{\"id\":6,\"name\":\"ac3\",\"currency\":\"EUR\",\"balance\":21.22,\"treasury\":false},"
						+ "{\"id\":7,\"name\":\"ac4\",\"currency\":\"EUR\",\"balance\":23.22,\"treasury\":false},"
						+ "{\"id\":8,\"name\":\"ac5\",\"currency\":\"EUR\",\"balance\":24.22,\"treasury\":true}]}",
				message);
	}

	@Test
	@DisplayName("test post account content type txt fail")
	void testPostFailContent() throws RestClientException, URISyntaxException {		
		URI uri = new URI("/account/");
		org.springframework.http.HttpHeaders headers = restTemplate.headForHeaders(uri);
		headers.add("Content-Type", "application/json");
		String message = this.restTemplate.postForObject(uri,
				"{\"name\":\"ac99\",\"currency\":\"EUR\",\"balance\":99.99,\"treasury\":false}", String.class);
		assertEquals(
				"{\"message\":\"Server Error\",\"details\":[\"Content type 'text/plain;charset=UTF-8' not supported\"]}",
				message);
	}

	@Test
	@DisplayName("test post account ok")
	void testPostOK() throws RestClientException, URISyntaxException {
		URI uri = new URI("/account/");
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("HeaderName", "value");
		headers.add("Content-Type", "application/json");
		HttpEntity<String> request = new HttpEntity<String>(
				"{\"name\":\"ac99\",\"currency\":\"EUR\",\"balance\":99.99,\"treasury\":false}", headers);
		String message = restTemplate.postForObject(uri, request, String.class);
		assertEquals(null, message);
	}

	@Test
	@DisplayName("test post account fail converting string 'aaa' to number")
	void testPostFailNumber() throws RestClientException, URISyntaxException {
		URI uri = new URI("/account/");
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("HeaderName", "value");
		headers.add("Content-Type", "application/json");
		HttpEntity<String> request = new HttpEntity<String>(
				"{\"name\":\"ac99\",\"currency\":\"EUR\",\"balance\":\"aaa\",\"treasury\":false}", headers);
		String message = restTemplate.postForObject(uri, request, String.class);
		assertEquals("{\"message\":\"Server Error\",\"details\":"
				+ "[\"JSON parse error: Cannot deserialize value of type `float` from String \\\"a\\\": "
				+ "not a valid Float value; nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: "
				+ "Cannot deserialize value of type `float` from String \\\"a\\\": not a valid Float value\\n at "
				+ "[Source: (PushbackInputStream); line: 1, column: 43] (through reference chain: "
				+ "com.technest.rest.model.Account[\\\"balance\\\"])\"]}", message);
	}

}