package org.com.cim.web;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.List;

import org.com.cim.common.dto.AdDto;
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

/*@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdAppIT {
    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void createClient() {
        ResponseEntity<Response<AdDto>> responseEntity = restTemplate.postForEntity("/clients", new CreateClientRequest("Foo"), Response.class);
        AdDto client = responseEntity.getBody().getData();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Foo", client.getName());
    }
}
*/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdAppIT {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testFindAdByPartnerId1() {


		JSONObject request = new JSONObject();
		try {
			request.put("partnerId", "p1");
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
		ResponseEntity<String> loginResponse = restTemplate.exchange("/ad/", HttpMethod.POST, entity, String.class);
		
		if (loginResponse.getStatusCode() == HttpStatus.OK) {
		  try {
			JSONObject userJson = new JSONObject(loginResponse.getBody());
			System.out.println(userJson);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		} else if (loginResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
			
		  // nono... bad credentials
		}
		
	}
	
	@Test
	public void testFindAdByPartnerId() {
		ResponseEntity<Response> responseEntity = this.restTemplate.getForEntity("/ad/p1", Response.class);
		LinkedHashMap<String, String> lh = (LinkedHashMap<String, String>) responseEntity.getBody().getData();
		System.out.println(lh.keySet()+", "+ lh.values());
		assertEquals("p1", lh.get("partnerId"));
		
	}
	
	
	@Test
	public void testFindAdByPartnerId2() {
		ResponseEntity<Response> responseEntity = this.restTemplate.getForEntity("/ad/p1", Response.class);
		LinkedHashMap<String, String> lh = (LinkedHashMap<String, String>) responseEntity.getBody().getData();
		System.out.println(lh.keySet()+", "+ lh.values());
		assertEquals("p1", lh.get("partnerId"));
		
	}

}