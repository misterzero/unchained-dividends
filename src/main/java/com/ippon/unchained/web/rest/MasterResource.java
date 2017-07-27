package com.ippon.unchained.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ippon.unchained.domain.Master;
import com.ippon.unchained.service.MasterService;
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
 * REST controller for managing Master.
 */
@RestController
@RequestMapping("/api")
public class MasterResource {

    private final Logger log = LoggerFactory.getLogger(MasterResource.class);

    private static final String ENTITY_NAME = "master";

    private final MasterService masterService;

    public MasterResource(MasterService masterService) {
        this.masterService = masterService;
    }

    /**
     * POST  /masters : Create a new master.
     *
     * @param master the master to create
     * @return the ResponseEntity with status 201 (Created) and with body the new master, or with status 400 (Bad Request) if the master has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/masters")
    @Timed
    public ResponseEntity<Master> createMaster(@RequestBody Master master) throws URISyntaxException {
        log.debug("REST request to save Master : {}", master);
        if (master.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new master cannot already have an ID")).body(null);
        }
        Master result = masterService.save(master);
        return ResponseEntity.created(new URI("/api/masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /masters : Updates an existing master.
     *
     * @param master the master to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated master,
     * or with status 400 (Bad Request) if the master is not valid,
     * or with status 500 (Internal Server Error) if the master couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/masters")
    @Timed
    public ResponseEntity<Master> updateMaster(@RequestBody Master master) throws URISyntaxException {
        log.debug("REST request to update Master : {}", master);
        if (master.getId() == null) {
            return createMaster(master);
        }
        Master result = masterService.save(master);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, master.getId().toString()))
            .body(result);
    }

    /**
     * GET  /masters : get all the masters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of masters in body
     */
    @GetMapping("/masters")
    @Timed
    public List<Master> getAllMasters() {
        log.debug("REST request to get all Masters");
        return masterService.findAll();
    }

    /**
     * GET  /masters/:id : get the "id" master.
     *
     * @param id the id of the master to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the master, or with status 404 (Not Found)
     */
    @GetMapping("/masters/{id}")
    @Timed
    public ResponseEntity<Master> getMaster(@PathVariable Long id) {
        log.debug("REST request to get Master : {}", id);
        Master master = masterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(master));
    }

    /**
     * DELETE  /masters/:id : delete the "id" master.
     *
     * @param id the id of the master to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteMaster(@PathVariable Long id) {
        log.debug("REST request to delete Master : {}", id);
        masterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
