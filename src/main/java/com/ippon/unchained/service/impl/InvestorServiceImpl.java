package com.ippon.unchained.service.impl;

import com.ippon.unchained.service.InvestorService;
import com.ippon.unchained.domain.Investor;
import com.ippon.unchained.repository.InvestorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Investor.
 */
@Service
@Transactional
public class InvestorServiceImpl implements InvestorService{

    private final Logger log = LoggerFactory.getLogger(InvestorServiceImpl.class);

    private final InvestorRepository investorRepository;

    public InvestorServiceImpl(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    /**
     * Save a investor.
     *
     * @param investor the entity to save
     * @return the persisted entity
     */
    @Override
    public Investor save(Investor investor) {
        log.debug("Request to save Investor : {}", investor);
        return investorRepository.save(investor);
    }

    /**
     *  Get all the investors.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Investor> findAll() {
        log.debug("Request to get all Investors");
        return investorRepository.findAll();
    }

    /**
     *  Get one investor by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Investor findOne(Long id) {
        log.debug("Request to get Investor : {}", id);
        return investorRepository.findOne(id);
    }

    /**
     *  Delete the  investor by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Investor : {}", id);
        investorRepository.delete(id);
    }
}
