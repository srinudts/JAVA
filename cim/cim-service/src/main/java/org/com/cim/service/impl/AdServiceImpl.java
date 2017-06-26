package org.com.cim.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.com.cim.common.dto.AdDto;
import org.com.cim.data.dao.AdDao;
import org.com.cim.data.domain.Ad;
import org.com.cim.service.AdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdServiceImpl implements AdService {

	private static final Logger LOG = LoggerFactory.getLogger(AdServiceImpl.class);

	@Autowired
	private AdDao adDao;

	private AdDto convertToDto(Ad ad) {
		AdDto dto = new AdDto();
		BeanUtils.copyProperties(ad, dto);
		return dto;
	}

	@Override
	@Transactional
	public List<AdDto> findAll() throws Exception {
		List<Ad> ads = adDao.findAll();
		return ads.stream().map(m -> convertToDto(m)).collect(Collectors.toList());
	}

	@Override
	public AdDto saveAd(AdDto dto) throws Exception {
		Ad ad = new Ad();
		ad.setAdContent(dto.getAdContent());
		ad.setDuration(dto.getDuration());
		ad.setPartnerId(dto.getPartnerId());
		ad.setCreatedDate(dto.getCreatedDate());
		ad.setActvFlag(dto.getActvFlag());

		adDao.save(ad);
		return convertToDto(ad);
	}

	@Override
	@Transactional
	public AdDto findAdByPartnerId(String partnerId) throws Exception {
		List<Ad> ads = adDao.findByPartnerId(partnerId);
		for (Ad ad : ads) {
			if (ad.getActvFlag().booleanValue() == true) {
				return convertToDto(ad);
			}
		}

		throw new RuntimeException("No Active ad found.");

	}

}
