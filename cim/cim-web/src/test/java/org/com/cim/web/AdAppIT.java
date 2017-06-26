package org.com.cim.web;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.com.cim.common.dto.AdDto;
import org.com.cim.common.form.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
	public void testFindAdByPartnerId() {
		ResponseEntity<Response> responseEntity = this.restTemplate.getForEntity("/ad", Response.class);
		List<AdDto> adDto =(List<AdDto>)responseEntity.getBody().getData();
		assertEquals(false, adDto.get(0).getActvFlag());
		
	}

}