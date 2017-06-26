package org.com.cim.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.com.cim.data.dao.AdDao;
import org.com.cim.data.domain.Ad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AdSchedulerComponent {

	private static final Logger LOG = LoggerFactory.getLogger(AdSchedulerComponent.class);

	@Autowired
	private AdDao adDao;

	@Scheduled(initialDelay = 5000, fixedDelay = 1000)
	@Transactional
	public void runAdScheduler() {
		
		List<Ad> activeAds = adDao.findActiveAds();
		
//		LOG.debug("Active count={}",activeAds.size());
		
		for (Ad ad : activeAds) {
			long expTime = ad.getCreatedDate().getTime() + (ad.getDuration()*1000);
			long currTime = (new Date()).getTime();
			
			LOG.debug("runAdScheduler. Current time is :: {} / {}", expTime, currTime);
			if(expTime - currTime < 0) {
				ad.setActvFlag(false);
				adDao.save(ad);
			}
		}
		
		

	}
	
}
