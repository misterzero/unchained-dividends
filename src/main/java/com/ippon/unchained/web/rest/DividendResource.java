package com.ippon.unchained.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ippon.unchained.config.DividendsContractConfiguration;
import com.ippon.unchained.domain.Dividend;
import com.ippon.unchained.service.DividendService;
import com.ippon.unchained.service.DividendsContractService;
import com.ippon.unchained.service.DummyClass;
import com.ippon.unchained.service.solidity.DividendsContract;
import com.ippon.unchained.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.abi.datatypes.generated.Uint256;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Dividend.
 */
@RestController
@RequestMapping("/api")
public class DividendResource {

    private final Logger log = LoggerFactory.getLogger(DividendResource.class);

    private static final String ENTITY_NAME = "dividend";

    private final DividendService dividendService;
    
    @Autowired
    private DividendsContractService dividendsContractService;
   
    @Autowired
    private DividendsContractConfiguration dividendsContractConfiguration;

    public DividendResource(DividendService dividendService) {
        this.dividendService = dividendService;
    }

    /**
     * POST  /dividends : Create a new dividend.
     *
     * @param dividend the dividend to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dividend, or with status 400 (Bad Request) if the dividend has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws InterruptedException 
     */
    @PostMapping("/dividends")
    @Timed
    public ResponseEntity<Dividend> createDividend(@RequestBody Dividend dividend) throws URISyntaxException, InterruptedException {
        log.debug("REST request to save Dividend : {}", dividend);
        if (dividend.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new dividend cannot already have an ID")).body(null);
        }
        Dividend result = dividendService.save(dividend);
        ResponseEntity.created(new URI("/api/dividends/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
        Dividend d = new Dividend();
        double amount = DummyClass.getDividendsAmount();
        d.setAmount(amount);
        d.setDate(LocalDate.now());
        executeDistributeDividends(amount);
        Thread.sleep(4000);
        return createDividend(d);
    }
    
    public void executeDistributeDividends(double dividendsAmount){
    	log.info("distribution of dividends in the chaincode begins");
    	Uint256 amount = new Uint256((long)(dividendsAmount)*60000);
		DividendsContract contract= dividendsContractConfiguration.getContract();
		dividendsContractService.distributeDividends(contract, amount);
    }

    /**
     * PUT  /dividends : Updates an existing dividend.
     *
     * @param dividend the dividend to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dividend,
     * or with status 400 (Bad Request) if the dividend is not valid,
     * or with status 500 (Internal Server Error) if the dividend couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws InterruptedException 
     */
    @PutMapping("/dividends")
    @Timed
    public ResponseEntity<Dividend> updateDividend(@RequestBody Dividend dividend) throws URISyntaxException, InterruptedException {
        log.debug("REST request to update Dividend : {}", dividend);
        if (dividend.getId() == null) {
            return createDividend(dividend);
        }
        Dividend result = dividendService.save(dividend);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dividend.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dividends : get all the dividends.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of dividends in body
     */
    @GetMapping("/dividends")
    @Timed
    public List<Dividend> getAllDividends() {
        log.debug("REST request to get all Dividends");
        return dividendService.findAll();
    }

    /**
     * GET  /dividends/:id : get the "id" dividend.
     *
     * @param id the id of the dividend to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dividend, or with status 404 (Not Found)
     */
    @GetMapping("/dividends/{id}")
    @Timed
    public ResponseEntity<Dividend> getDividend(@PathVariable Long id) {
        log.debug("REST request to get Dividend : {}", id);
        Dividend dividend = dividendService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dividend));
    }

    /**
     * DELETE  /dividends/:id : delete the "id" dividend.
     *
     * @param id the id of the dividend to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dividends/{id}")
    @Timed
    public ResponseEntity<Void> deleteDividend(@PathVariable Long id) {
        log.debug("REST request to delete Dividend : {}", id);
        dividendService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
