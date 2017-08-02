package com.ippon.unchained.service.impl;

import com.ippon.unchained.service.ExtendedUserService;
import com.ippon.unchained.domain.ExtendedUser;
import com.ippon.unchained.repository.ExtendedUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing ExtendedUser.
 */
@Service
@Transactional
public class ExtendedUserServiceImpl implements ExtendedUserService{

    private final Logger log = LoggerFactory.getLogger(ExtendedUserServiceImpl.class);

    private final ExtendedUserRepository extendedUserRepository;

    public ExtendedUserServiceImpl(ExtendedUserRepository extendedUserRepository) {
        this.extendedUserRepository = extendedUserRepository;
    }

    /**
     * Save a extendedUser.
     *
     * @param extendedUser the entity to save
     * @return the persisted entity
     */
    @Override
    public ExtendedUser save(ExtendedUser extendedUser) {
        log.debug("Request to save ExtendedUser : {}", extendedUser);
        return extendedUserRepository.save(extendedUser);
    }

    /**
     *  Get all the extendedUsers.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ExtendedUser> findAll() {
        log.debug("Request to get all ExtendedUsers");
        return extendedUserRepository.findAll();
    }

    /**
     *  Get one extendedUser by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ExtendedUser findOne(Long id) {
        log.debug("Request to get ExtendedUser : {}", id);
        return extendedUserRepository.findOne(id);
    }

    /**
     *  Get one extendedUser by accountId.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public List<ExtendedUser> findByAccountId(Long id) {
        log.debug("Request to get ExtendedUsers by accountId : {}", id);
        return extendedUserRepository.findByAccountId(id);
    }

    /**
     *  Delete the  extendedUser by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ExtendedUser : {}", id);
        extendedUserRepository.delete(id);
    }
}
