package com.ippon.unchained.repository;

import com.ippon.unchained.domain.RoundOfInvestment;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the RoundOfInvestment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoundOfInvestmentRepository extends JpaRepository<RoundOfInvestment,Long> {
    
}
