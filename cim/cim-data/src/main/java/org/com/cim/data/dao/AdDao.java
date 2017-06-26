package org.com.cim.data.dao;

import java.util.List;

import org.com.cim.data.domain.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface AdDao extends JpaRepository<Ad, Long> {
	List<Ad> findByPartnerId(String partnerId);
	
	@Query("SELECT a FROM Ad a WHERE LOWER(a.actvFlag) = 'y'")
    List<Ad> findActiveAds();
}
