package org.com.cim.web.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.com.cim.common.dto.AdDto;
import org.com.cim.service.AdService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

@WebAppConfiguration
public class AdControllerTest {
	
	private static MockMvc mockMvc;
	private static AdController mockAdController;
	private static AdService mockAdService;
	
	@BeforeClass
	public static void setUpMethod() {
		mockAdController = new AdController();
		mockMvc = MockMvcBuilders.standaloneSetup(mockAdController).build();
		ReflectionTestUtils.setField(mockAdController, "adService", mockAdService = mock(AdService.class));
	}

	@Test
	public void testFindAdByPartnerId() throws Exception {
		when(mockAdService.findAdByPartnerId(any(String.class))).thenReturn(createAdDto());
		
		MvcResult result = mockMvc.perform(get("/ad/ALLURI"))
									.andExpect(status().isOk())
									.andReturn();
		 Assert.hasText("\"partnerId\":\"srini\"", result.getResponse().getContentAsString());
		 Assert.hasText("\"duration\":1000", result.getResponse().getContentAsString());
		 Assert.hasText("\"actvFlag\":true", result.getResponse().getContentAsString());
	}
	
	@Test
	public void testSaveAd() throws Exception {
		when(mockAdService.saveAd(any(AdDto.class))).thenReturn(createAdDto());
		
		MvcResult result = mockMvc.perform(post("/ad")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"partnerId\":\"SRINI\",\"AdContent\":\"Ad Agency\",\"duration\":100}".getBytes()))
				.andExpect(status().isCreated())
				.andReturn();
		
		Assert.hasText("\"partnerId\":\"srini\"", result.getResponse().getContentAsString());
		Assert.hasText("\"duration\":1000", result.getResponse().getContentAsString());
		Assert.hasText("\"actvFlag\":true", result.getResponse().getContentAsString());
	}
	
	private AdDto createAdDto(){
		AdDto ad = new AdDto();
		ad.setActvFlag(true);
		ad.setPartnerId("srini");
		ad.setDuration(1000);
		return ad;
	}

}