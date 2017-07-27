package com.ippon.unchained.service.impl;

import com.ippon.unchained.service.MasterService;
import com.ippon.unchained.domain.Master;
import com.ippon.unchained.repository.MasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Master.
 */
@Service
@Transactional
public class MasterServiceImpl implements MasterService{

    private final Logger log = LoggerFactory.getLogger(MasterServiceImpl.class);

    private final MasterRepository masterRepository;

    public MasterServiceImpl(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    /**
     * Save a master.
     *
     * @param master the entity to save
     * @return the persisted entity
     */
    @Override
    public Master save(Master master) {
        log.debug("Request to save Master : {}", master);
        return masterRepository.save(master);
    }

    /**
     *  Get all the masters.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Master> findAll() {
        log.debug("Request to get all Masters");
        return masterRepository.findAll();
    }

    /**
     *  Get one master by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Master findOne(Long id) {
        log.debug("Request to get Master : {}", id);
        return masterRepository.findOne(id);
    }

    /**
     *  Delete the  master by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Master : {}", id);
        masterRepository.delete(id);
    }
}
