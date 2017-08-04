package com.ippon.unchained.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ippon.unchained.config.DividendsContractConfiguration;
import com.ippon.unchained.domain.ExtendedUser;
import com.ippon.unchained.domain.Investor;
import com.ippon.unchained.domain.RoundOfInvestment;
import com.ippon.unchained.service.*;
import com.ippon.unchained.service.solidity.DividendsContract;
import com.ippon.unchained.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RoundOfInvestment.
 */
@RestController
@RequestMapping("/api")
public class RoundOfInvestmentResource {

    private final Logger log = LoggerFactory.getLogger(RoundOfInvestmentResource.class);

    private static final String ENTITY_NAME = "roundOfInvestment";

    private final RoundOfInvestmentService roundOfInvestmentService;

    private final InvestorService investorService;

    private final ExtendedUserService extendedUserService;



    @Autowired
    private DividendsContractService dividendsContractService;

    @Autowired
    private DividendsContractConfiguration dividendsContractConfiguration;

    public RoundOfInvestmentResource(RoundOfInvestmentService roundOfInvestmentService,
                                     InvestorService investorService,
                                     ExtendedUserService extendedUserService) {
        this.roundOfInvestmentService = roundOfInvestmentService;
        this.investorService = investorService;
        this.extendedUserService = extendedUserService;
    }

    /**
     * POST  /round-of-investments : Create a new roundOfInvestment.
     *
     * @param roundOfInvestment the roundOfInvestment to create
     * @return the ResponseEntity with status 201 (Created) and with body the new roundOfInvestment, or with status 400 (Bad Request) if the roundOfInvestment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/round-of-investments")
    @Timed
    public ResponseEntity<RoundOfInvestment> createRoundOfInvestment(@RequestBody RoundOfInvestment roundOfInvestment) throws URISyntaxException {
        log.debug("REST request to save RoundOfInvestment : {}", roundOfInvestment);
        if (roundOfInvestment.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new roundOfInvestment cannot already have an ID")).body(null);
        }
        RoundOfInvestment result = roundOfInvestmentService.save(roundOfInvestment);
        ResponseEntity<RoundOfInvestment> res = ResponseEntity.created(new URI("/api/round-of-investments/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
        executeRoundOfInvestment(roundOfInvestment,roundOfInvestment.getEndDate());

        return res;
    }

    @Async
    public void executeRoundOfInvestment(RoundOfInvestment r,LocalDate d){
    	LocalDate today = LocalDate.now();
    	Timestamp timestamp1 = Timestamp.valueOf(today.atStartOfDay());
    	Timestamp timestamp2 = Timestamp.valueOf(d.atStartOfDay());
    	long d1 = timestamp1.getTime();
    	long d2 = timestamp2.getTime();
    	long n = d2-d1;
    	log.info("time remaining before execution of the script on the chaincode: "+n);
    	try {
			Thread.sleep(90000);
			double valueOfTheCompany = DummyClass.getValueOfTheCompany();
			Uint256 currentValueOfTheCompany =new Uint256((long)(valueOfTheCompany));
			DividendsContract contract= dividendsContractConfiguration.getContract();
			double investorTotal = 0;
            for (Investor i : investorService.findAll()) {
                investorTotal += i.getMoneyInvested();
                i.setMoneyInvested(0);
                investorService.save(i);
            }
			dividendsContractService.masterRoundOfInvestment(contract, currentValueOfTheCompany);
			log.info("round of investment executed with the value of the company: " +valueOfTheCompany);
	        r.setTokenValue(dividendsContractService.getMasterValueOfOneToken(contract).getValue().intValue());
	        r.setTotalMoneyInvested(investorTotal);
	        updateRoundOfInvestment(r);
		} catch (InterruptedException | URISyntaxException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * PUT  /round-of-investments : Updates an existing roundOfInvestment.
     *
     * @param roundOfInvestment the roundOfInvestment to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated roundOfInvestment,
     * or with status 400 (Bad Request) if the roundOfInvestment is not valid,
     * or with status 500 (Internal Server Error) if the roundOfInvestment couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/round-of-investments")
    @Timed
    public ResponseEntity<RoundOfInvestment> updateRoundOfInvestment(@RequestBody RoundOfInvestment roundOfInvestment) throws URISyntaxException {
        log.debug("REST request to update RoundOfInvestment : {}", roundOfInvestment);
        if (roundOfInvestment.getId() == null) {
            return createRoundOfInvestment(roundOfInvestment);
        }
        RoundOfInvestment result = roundOfInvestmentService.save(roundOfInvestment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, roundOfInvestment.getId().toString()))
            .body(result);
    }

    /**
     * GET  /round-of-investments : get all the roundOfInvestments.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of roundOfInvestments in body
     */
    @GetMapping("/round-of-investments")
    @Timed
    public List<RoundOfInvestment> getAllRoundOfInvestments() {
        log.debug("REST request to get all RoundOfInvestments");
        return roundOfInvestmentService.findAll();
    }

    /**
     * GET  /round-of-investments/:id : get the "id" roundOfInvestment.
     *
     * @param id the id of the roundOfInvestment to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the roundOfInvestment, or with status 404 (Not Found)
     */
    @GetMapping("/round-of-investments/{id}")
    @Timed
    public ResponseEntity<RoundOfInvestment> getRoundOfInvestment(@PathVariable Long id) {
        log.debug("REST request to get RoundOfInvestment : {}", id);
        RoundOfInvestment roundOfInvestment = roundOfInvestmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(roundOfInvestment));
    }

    /**
     * DELETE  /round-of-investments/:id : delete the "id" roundOfInvestment.
     *
     * @param id the id of the roundOfInvestment to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/round-of-investments/{id}")
    @Timed
    public ResponseEntity<Void> deleteRoundOfInvestment(@PathVariable Long id) {
        log.debug("REST request to delete RoundOfInvestment : {}", id);
        roundOfInvestmentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
