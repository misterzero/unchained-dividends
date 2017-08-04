package com.ippon.unchained.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ippon.unchained.domain.Authority;
import com.ippon.unchained.domain.Investor;
import com.ippon.unchained.domain.User;
import com.ippon.unchained.security.AuthoritiesConstants;
import com.ippon.unchained.security.SecurityUtils;
import com.ippon.unchained.service.InvestorService;
import com.ippon.unchained.service.UserService;
import com.ippon.unchained.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Investor.
 */
@RestController
@RequestMapping("/api")
public class InvestorResource {

    private final Logger log = LoggerFactory.getLogger(InvestorResource.class);

    private static final String ENTITY_NAME = "investor";

    private final InvestorService investorService;

    private final UserService userService;

    public InvestorResource(InvestorService investorService, UserService userService) {
        this.investorService = investorService;
        this.userService = userService;
    }

    /**
     * POST  /investors : Create a new investor.
     *
     * @param investor the investor to create
     * @return the ResponseEntity with status 201 (Created) and with body the new investor, or with status 400 (Bad Request) if the investor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/investors")
    @Timed
    public ResponseEntity<Investor> createInvestor(@RequestBody Investor investor) throws URISyntaxException {
        log.debug("REST request to save Investor : {}", investor);
        if (investor.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new investor cannot already have an ID")).body(null);
        }
        investor.setMoneyInvested(0);
        Investor result = investorService.save(investor);
        return ResponseEntity.created(new URI("/api/investors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /investors : Updates an existing investor.
     *
     * @param investor the investor to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated investor,
     * or with status 400 (Bad Request) if the investor is not valid,
     * or with status 500 (Internal Server Error) if the investor couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/investors")
    @Timed
    public ResponseEntity<Investor> updateInvestor(@RequestBody Investor investor) throws URISyntaxException {
        log.debug("REST request to update Investor : {}", investor);
        if (investor.getId() == null) {
            return createInvestor(investor);
        }
        log.debug("Investor money invested: " + investor.getMoneyInvested());
        Investor result = investorService.save(investor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, investor.getId().toString()))
            .body(result);
    }

    /**
     * GET  /investors : get all the investors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of investors in body
     */
    @GetMapping("/investors")
    @Timed
    public List<Investor> getAllInvestors() {
        log.debug("REST request to get all Investors");
        return investorService.findAll();
    }

    /**
     * GET  /investors/:id : get the "id" investor.
     *
     * @param id the id of the investor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the investor, or with status 404 (Not Found)
     */
    @GetMapping("/investors/{id}")
    @Timed
    public ResponseEntity<Investor> getInvestor(@PathVariable Long id) {
        log.debug("REST request to get Investor : {}", id);
        User currentUser = userService.getUserWithAuthorities();
        Investor investor;
        Authority authority = new Authority();
        authority.setName(AuthoritiesConstants.ADMIN);
        if (currentUser.getAuthorities().contains(authority)) {
            // The admin users should be able to view the any investor's details...
            investor = investorService.findOne(id);
        } else {
            // ...but regular users should only be able to view their own investor details.
            investor = investorService.findOneByAccountId(currentUser.getId());
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(investor));
    }

    /**
     * DELETE  /investors/:id : delete the "id" investor.
     *
     * @param id the id of the investor to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/investors/{id}")
    @Timed
    public ResponseEntity<Void> deleteInvestor(@PathVariable Long id) {
        log.debug("REST request to delete Investor : {}", id);
        investorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
