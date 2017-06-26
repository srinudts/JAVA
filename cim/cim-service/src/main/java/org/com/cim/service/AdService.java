package org.com.cim.service;

import java.util.List;

import org.com.cim.common.dto.AdDto;

public interface AdService {

	public AdDto saveAd(AdDto dto) throws Exception;

	public List<AdDto> findAll() throws Exception;

	public AdDto findAdByPartnerId(String partnerId) throws Exception;

}
