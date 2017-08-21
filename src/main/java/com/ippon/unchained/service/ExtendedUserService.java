package com.ippon.unchained.service;

import com.ippon.unchained.domain.ExtendedUser;
import java.util.List;

/**
 * Service Interface for managing ExtendedUser.
 */
public interface ExtendedUserService {

    /**
     * Save a extendedUser.
     *
     * @param extendedUser the entity to save
     * @return the persisted entity
     */
    ExtendedUser save(ExtendedUser extendedUser);

    /**
     *  Get all the extendedUsers.
     *
     *  @return the list of entities
     */
    List<ExtendedUser> findAll();

    /**
     *  Get the "id" extendedUser.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ExtendedUser findOne(Long id);

    /**
     *  Get the extendedUser by "accountId".
     *
     *  @param accountId the accountId of the user
     *  @return the entity associated with that user
     */
    List<ExtendedUser> findByAccountId(Long accountId);

    /**
     *  Delete the "id" extendedUser.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
