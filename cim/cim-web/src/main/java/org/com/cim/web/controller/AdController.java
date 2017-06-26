package org.com.cim.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.com.cim.common.dto.AdDto;
import org.com.cim.common.form.JsonError;
import org.com.cim.common.form.Response;
import org.com.cim.service.AdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/ad")
public class AdController {

	private static final Logger LOG = LoggerFactory.getLogger(AdController.class);

	@Autowired
	AdService adService;

	/**
	 * 
	 * @param requestId
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<Response<List<AdDto>>> getAllAds(
			@RequestHeader(value = "requestId", required = false) final String requestId) {
		Response<List<AdDto>> resp = new Response<>(requestId);

		List<AdDto> dtos = new ArrayList<>();
		try {
			LOG.debug("Headers:: requestId={}", requestId);
			dtos = adService.findAll();
			resp.setData(dtos);
		} catch (Exception e) {
			LOG.error("Error in getAssignedTasksForStudent due to {}", e.getMessage());
			JsonError error = new JsonError(null, e.getMessage());
			resp.getErrors().add(error);
		}

		HttpStatus respStatus = HttpStatus.OK;
		if (dtos.isEmpty()) {
			respStatus = HttpStatus.NOT_FOUND;
		}

		return new ResponseEntity<Response<List<AdDto>>>(resp, respStatus);
	}


	@GetMapping("/{partnerId}")
	public ResponseEntity<Response<AdDto>> getAdByParterId(
			@RequestHeader(value = "requestId", required = false) final String requestId,
			@PathVariable("partnerId") String partnerId) {
		Response<AdDto> resp = new Response<>(requestId);

		AdDto dto = null;
		try {
			LOG.debug("Headers:: requestId={}", requestId);
			dto = adService.findAdByPartnerId(partnerId);
			resp.setData(dto);
		} catch (Exception e) {
			LOG.error("Error in getting the active add due to {}", e.getMessage());
			JsonError error = new JsonError(null, e.getMessage());
			resp.getErrors().add(error);
		}

		HttpStatus respStatus = HttpStatus.OK;
		if (dto==null) {
			respStatus = HttpStatus.NOT_FOUND;
		}

		return new ResponseEntity<Response<AdDto>>(resp, respStatus);
	}



	/**
	 * 
	 * @param dto
	 * @param ucBuilder
	 * @param requestId
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<Response<AdDto>> saveAddResponse(@RequestBody AdDto dto,
			UriComponentsBuilder ucBuilder,
			@RequestHeader(value = "requestId", required = false) final String requestId) {
		
		Response<AdDto> resp = new Response<>(requestId);
		try {
			LOG.debug("Headers:: requestId={}", requestId);
			dto.setCreatedDate(new Date());
			dto.setActvFlag(true);
			dto = adService.saveAd(dto);
			resp.setData(dto);
		} catch (Exception e) {
			LOG.error("Error in saving ad, due to {}", e.getMessage());
			JsonError error = new JsonError(null, e.getMessage());
			resp.getErrors().add(error);
		}

		HttpStatus respStatus = HttpStatus.CREATED;
		if (dto == null) {
			respStatus = HttpStatus.NOT_FOUND;
		}

		return new ResponseEntity<Response<AdDto>>(resp, respStatus);

	}

}