package org.com.cim.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.com.cim.common.dto.AdDto;
import org.com.cim.data.dao.AdDao;
import org.com.cim.data.domain.Ad;
import org.com.cim.service.AdService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

@WebAppConfiguration
public class AdServiceImplTest {
	
	private static AdService adService;
	private static AdDao mockAdDao;
	
	@BeforeClass
	public static void setUpMethod() {
		adService = new AdServiceImpl();
		ReflectionTestUtils.setField(adService, "adDao", mockAdDao = mock(AdDao.class));
	}
	
	@Test
	public void findAdByPartnerId() throws Exception {
		List<Ad> ads = new ArrayList<>();
		Ad ad = new Ad();
		ad.setPartnerId("SRINI");
		ad.setActvFlag(true);
		ads.add(ad);
		
		when(mockAdDao.findByPartnerId(any(String.class))).thenReturn(ads);
		AdDto adDto = adService.findAdByPartnerId("SRINI");
		
		assertEquals(true, adDto.getActvFlag());
	}
}
