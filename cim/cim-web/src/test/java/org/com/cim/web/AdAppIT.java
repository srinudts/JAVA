package org.com.cim.web;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import org.com.cim.common.form.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdAppIT {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testSaveAd() {
		JSONObject request = new JSONObject();
		try {
			request.put("partnerId", "pId1");
			request.put("duration", 10);
			request.put("adContent", "TEST");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		// set headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

		// send request and parse result
		ResponseEntity<String> loginResponse = restTemplate.exchange("/ad", HttpMethod.POST, entity, String.class);
		assertEquals(HttpStatus.CREATED, loginResponse.getStatusCode());
	}

	@Test
	public void testFindAdByPartnerId() {
		ResponseEntity<Response> responseEntity = this.restTemplate.getForEntity("/ad/pId1", Response.class);
		LinkedHashMap<String, String> adJson = (LinkedHashMap<String, String>) responseEntity.getBody().getData();
		
		assertEquals("pId1", adJson.get("partnerId"));
		assertEquals(10, adJson.get("duration"));
		assertEquals(true, adJson.get("actvFlag"));
	}

	@Test
	public void testFindAdByPartnerId_InActiveStatus() throws InterruptedException {
		// Wait till 10 seconds to allow Ad status changed to Deactive
		Thread.sleep(10000);
		ResponseEntity<Response> responseEntity = this.restTemplate.getForEntity("/ad/pId1", Response.class);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
}