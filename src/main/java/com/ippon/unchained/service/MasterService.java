package com.ippon.unchained.service;

import com.ippon.unchained.domain.Master;
import java.util.List;

/**
 * Service Interface for managing Master.
 */
public interface MasterService {

    /**
     * Save a master.
     *
     * @param master the entity to save
     * @return the persisted entity
     */
    Master save(Master master);

    /**
     *  Get all the masters.
     *
     *  @return the list of entities
     */
    List<Master> findAll();

    /**
     *  Get the "id" master.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Master findOne(Long id);

    /**
     *  Delete the "id" master.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
