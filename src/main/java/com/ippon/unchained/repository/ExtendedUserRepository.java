package com.ippon.unchained.repository;

import com.ippon.unchained.domain.ExtendedUser;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the ExtendedUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExtendedUserRepository extends JpaRepository<ExtendedUser,Long> {
    List<ExtendedUser> findByAccountId(Long accountId);
}
