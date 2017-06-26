package org.com.cim.data.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "ad", schema = "public")
public class Ad implements java.io.Serializable {

	private static final long serialVersionUID = 9876543211L;
	
	private Long adId;
	private String partnerId;
	private Integer duration;
	private String adContent;
	private Date createdDate;
	private Boolean actvFlag;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ad_id", unique = true, nullable = false)
	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	@Column(name = "partner_id", length = 20)
	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	@Column(name = "duration")
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Column(name = "ad_content", length = 1000)
	public String getAdContent() {
		return adContent;
	}

	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false, length = 35, columnDefinition="DATETIME")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "ACTV_FLAG")
	@Type(type = "yes_no")
	public Boolean getActvFlag() {
		return actvFlag;
	}

	public void setActvFlag(Boolean actvFlag) {
		this.actvFlag = actvFlag;
	}

}
