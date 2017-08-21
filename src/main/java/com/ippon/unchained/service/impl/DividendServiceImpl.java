package com.ippon.unchained.service.impl;

import com.ippon.unchained.service.DividendService;
import com.ippon.unchained.domain.Dividend;
import com.ippon.unchained.repository.DividendRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Dividend.
 */
@Service
@Transactional
public class DividendServiceImpl implements DividendService{

    private final Logger log = LoggerFactory.getLogger(DividendServiceImpl.class);

    private final DividendRepository dividendRepository;

    public DividendServiceImpl(DividendRepository dividendRepository) {
        this.dividendRepository = dividendRepository;
    }

    /**
     * Save a dividend.
     *
     * @param dividend the entity to save
     * @return the persisted entity
     */
    @Override
    public Dividend save(Dividend dividend) {
        log.debug("Request to save Dividend : {}", dividend);
        return dividendRepository.save(dividend);
    }

    /**
     *  Get all the dividends.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Dividend> findAll() {
        log.debug("Request to get all Dividends");
        return dividendRepository.findAll();
    }

    /**
     *  Get one dividend by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Dividend findOne(Long id) {
        log.debug("Request to get Dividend : {}", id);
        return dividendRepository.findOne(id);
    }

    /**
     *  Delete the  dividend by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dividend : {}", id);
        dividendRepository.delete(id);
    }
}
