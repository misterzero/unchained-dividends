package com.ippon.unchained.service.impl;

import com.ippon.unchained.service.RoundOfInvestmentService;
import com.ippon.unchained.domain.RoundOfInvestment;
import com.ippon.unchained.repository.RoundOfInvestmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing RoundOfInvestment.
 */
@Service
@Transactional
public class RoundOfInvestmentServiceImpl implements RoundOfInvestmentService{

    private final Logger log = LoggerFactory.getLogger(RoundOfInvestmentServiceImpl.class);

    private final RoundOfInvestmentRepository roundOfInvestmentRepository;

    public RoundOfInvestmentServiceImpl(RoundOfInvestmentRepository roundOfInvestmentRepository) {
        this.roundOfInvestmentRepository = roundOfInvestmentRepository;
    }

    /**
     * Save a roundOfInvestment.
     *
     * @param roundOfInvestment the entity to save
     * @return the persisted entity
     */
    @Override
    public RoundOfInvestment save(RoundOfInvestment roundOfInvestment) {
        log.debug("Request to save RoundOfInvestment : {}", roundOfInvestment);
        return roundOfInvestmentRepository.save(roundOfInvestment);
    }

    /**
     *  Get all the roundOfInvestments.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoundOfInvestment> findAll() {
        log.debug("Request to get all RoundOfInvestments");
        return roundOfInvestmentRepository.findAll();
    }

    /**
     *  Get one roundOfInvestment by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RoundOfInvestment findOne(Long id) {
        log.debug("Request to get RoundOfInvestment : {}", id);
        return roundOfInvestmentRepository.findOne(id);
    }

    /**
     *  Delete the  roundOfInvestment by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RoundOfInvestment : {}", id);
        roundOfInvestmentRepository.delete(id);
    }
}
