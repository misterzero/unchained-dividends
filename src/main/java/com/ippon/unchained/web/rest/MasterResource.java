package com.ippon.unchained.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ippon.unchained.config.DividendsContractConfiguration;
import com.ippon.unchained.domain.Authority;
import com.ippon.unchained.domain.Investor;
import com.ippon.unchained.domain.User;
import com.ippon.unchained.security.AuthoritiesConstants;
import com.ippon.unchained.security.SecurityUtils;
import com.ippon.unchained.service.DividendsContractService;
import com.ippon.unchained.service.InvestorService;
import com.ippon.unchained.service.UserService;
import com.ippon.unchained.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.abi.datatypes.generated.Uint256;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Master.
 */
@RestController
@RequestMapping("/api")
public class MasterResource {

    private final Logger log = LoggerFactory.getLogger(InvestorResource.class);

    private final DividendsContractService dividendsContractService;

    private final DividendsContractConfiguration dividendsContractConfiguration;

    public MasterResource(DividendsContractService dividendsContractService, DividendsContractConfiguration dividendsContractConfiguration) {
        this.dividendsContractService = dividendsContractService;
        this.dividendsContractConfiguration = dividendsContractConfiguration;
    }

    /**
     * GET  /tokens : get the "id" investor.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the total tokens, or with status 404 (Not Found)
     */
    @GetMapping("/tokens")
    @Timed
    public ResponseEntity<Uint256> getMasterTotalTokens() {
        // TODO: Move the dividendsContractService call to a MasterService layer?
        log.debug("REST request to get Master Total Tokens");
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dividendsContractService.getMasterTotalTokens(dividendsContractConfiguration.getContract())));
    }
}
