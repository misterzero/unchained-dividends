package com.ippon.unchained.service;

import com.ippon.unchained.domain.Dividend;
import java.util.List;

/**
 * Service Interface for managing Dividend.
 */
public interface DividendService {

    /**
     * Save a dividend.
     *
     * @param dividend the entity to save
     * @return the persisted entity
     */
    Dividend save(Dividend dividend);

    /**
     *  Get all the dividends.
     *
     *  @return the list of entities
     */
    List<Dividend> findAll();

    /**
     *  Get the "id" dividend.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Dividend findOne(Long id);

    /**
     *  Delete the "id" dividend.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
