package com.ippon.unchained.repository;

import com.ippon.unchained.domain.Dividend;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Dividend entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DividendRepository extends JpaRepository<Dividend,Long> {
    
}
