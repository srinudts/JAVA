package org.com.cim.common.dto;

import java.util.Date;

public class AdDto {

	private Long adId;
	private String partnerId;
	private Integer duration;
	private String adContent;
	private Date createdDate;
	private Boolean actvFlag;

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getAdContent() {
		return adContent;
	}

	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getActvFlag() {
		return actvFlag;
	}

	public void setActvFlag(Boolean actvFlag) {
		this.actvFlag = actvFlag;
	}

}
