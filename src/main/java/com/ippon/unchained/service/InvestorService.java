package com.ippon.unchained.service;

import com.ippon.unchained.domain.Investor;
import java.util.List;

/**
 * Service Interface for managing Investor.
 */
public interface InvestorService {

    /**
     * Save a investor.
     *
     * @param investor the entity to save
     * @return the persisted entity
     */
    Investor save(Investor investor);

    /**
     *  Get all the investors.
     *
     *  @return the list of entities
     */
    List<Investor> findAll();

    /**
     *  Get the "id" investor.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Investor findOne(Long id);

    /**
     *  Delete the "id" investor.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
