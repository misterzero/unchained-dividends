package com.ippon.unchained.repository;

import com.ippon.unchained.domain.Investor;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Investor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestorRepository extends JpaRepository<Investor,Long> {
    List<Investor> findByAccountId(Long accountId);
}
