package com.ippon.unchained.service;

import com.ippon.unchained.domain.RoundOfInvestment;
import java.util.List;

/**
 * Service Interface for managing RoundOfInvestment.
 */
public interface RoundOfInvestmentService {

    /**
     * Save a roundOfInvestment.
     *
     * @param roundOfInvestment the entity to save
     * @return the persisted entity
     */
    RoundOfInvestment save(RoundOfInvestment roundOfInvestment);

    /**
     *  Get all the roundOfInvestments.
     *
     *  @return the list of entities
     */
    List<RoundOfInvestment> findAll();

    /**
     *  Get the "id" roundOfInvestment.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    RoundOfInvestment findOne(Long id);

    /**
     *  Delete the "id" roundOfInvestment.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
