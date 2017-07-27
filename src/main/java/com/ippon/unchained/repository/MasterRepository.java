package com.ippon.unchained.repository;

import com.ippon.unchained.domain.Master;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Master entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MasterRepository extends JpaRepository<Master,Long> {
    
}
