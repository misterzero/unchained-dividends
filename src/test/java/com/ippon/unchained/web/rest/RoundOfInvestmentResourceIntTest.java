package com.ippon.unchained.web.rest;

import com.ippon.unchained.UnchainedApp;

import com.ippon.unchained.domain.RoundOfInvestment;
import com.ippon.unchained.repository.RoundOfInvestmentRepository;
import com.ippon.unchained.service.ExtendedUserService;
import com.ippon.unchained.service.InvestorService;
import com.ippon.unchained.service.RoundOfInvestmentService;
import com.ippon.unchained.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RoundOfInvestmentResource REST controller.
 *
 * @see RoundOfInvestmentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnchainedApp.class)
public class RoundOfInvestmentResourceIntTest {

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_TOTAL_MONEY_INVESTED = 1D;
    private static final Double UPDATED_TOTAL_MONEY_INVESTED = 2D;

    private static final Integer DEFAULT_TOKEN_VALUE = 1;
    private static final Integer UPDATED_TOKEN_VALUE = 2;

    @Autowired
    private RoundOfInvestmentRepository roundOfInvestmentRepository;

    @Autowired
    private RoundOfInvestmentService roundOfInvestmentService;

    @Autowired
    private InvestorService investorService;

    @Autowired
    private ExtendedUserService extendedUserService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRoundOfInvestmentMockMvc;

    private RoundOfInvestment roundOfInvestment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RoundOfInvestmentResource roundOfInvestmentResource = new RoundOfInvestmentResource(roundOfInvestmentService,
            investorService, extendedUserService);
        this.restRoundOfInvestmentMockMvc = MockMvcBuilders.standaloneSetup(roundOfInvestmentResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RoundOfInvestment createEntity(EntityManager em) {
        RoundOfInvestment roundOfInvestment = new RoundOfInvestment()
            .endDate(DEFAULT_END_DATE)
            .totalMoneyInvested(DEFAULT_TOTAL_MONEY_INVESTED)
            .tokenValue(DEFAULT_TOKEN_VALUE);
        return roundOfInvestment;
    }

    @Before
    public void initTest() {
        roundOfInvestment = createEntity(em);
    }

    @Test
    @Transactional
    public void createRoundOfInvestment() throws Exception {
        int databaseSizeBeforeCreate = roundOfInvestmentRepository.findAll().size();

        // Create the RoundOfInvestment
        restRoundOfInvestmentMockMvc.perform(post("/api/round-of-investments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(roundOfInvestment)))
            .andExpect(status().isCreated());

        // Validate the RoundOfInvestment in the database
        List<RoundOfInvestment> roundOfInvestmentList = roundOfInvestmentRepository.findAll();
        assertThat(roundOfInvestmentList).hasSize(databaseSizeBeforeCreate + 1);
        RoundOfInvestment testRoundOfInvestment = roundOfInvestmentList.get(roundOfInvestmentList.size() - 1);
        assertThat(testRoundOfInvestment.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testRoundOfInvestment.getTotalMoneyInvested()).isEqualTo(DEFAULT_TOTAL_MONEY_INVESTED);
        assertThat(testRoundOfInvestment.getTokenValue()).isEqualTo(DEFAULT_TOKEN_VALUE);
    }

    @Test
    @Transactional
    public void createRoundOfInvestmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = roundOfInvestmentRepository.findAll().size();

        // Create the RoundOfInvestment with an existing ID
        roundOfInvestment.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRoundOfInvestmentMockMvc.perform(post("/api/round-of-investments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(roundOfInvestment)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<RoundOfInvestment> roundOfInvestmentList = roundOfInvestmentRepository.findAll();
        assertThat(roundOfInvestmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRoundOfInvestments() throws Exception {
        // Initialize the database
        roundOfInvestmentRepository.saveAndFlush(roundOfInvestment);

        // Get all the roundOfInvestmentList
        restRoundOfInvestmentMockMvc.perform(get("/api/round-of-investments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(roundOfInvestment.getId().intValue())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].totalMoneyInvested").value(hasItem(DEFAULT_TOTAL_MONEY_INVESTED.doubleValue())))
            .andExpect(jsonPath("$.[*].tokenValue").value(hasItem(DEFAULT_TOKEN_VALUE)));
    }

    @Test
    @Transactional
    public void getRoundOfInvestment() throws Exception {
        // Initialize the database
        roundOfInvestmentRepository.saveAndFlush(roundOfInvestment);

        // Get the roundOfInvestment
        restRoundOfInvestmentMockMvc.perform(get("/api/round-of-investments/{id}", roundOfInvestment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(roundOfInvestment.getId().intValue()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.totalMoneyInvested").value(DEFAULT_TOTAL_MONEY_INVESTED.doubleValue()))
            .andExpect(jsonPath("$.tokenValue").value(DEFAULT_TOKEN_VALUE));
    }

    @Test
    @Transactional
    public void getNonExistingRoundOfInvestment() throws Exception {
        // Get the roundOfInvestment
        restRoundOfInvestmentMockMvc.perform(get("/api/round-of-investments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRoundOfInvestment() throws Exception {
        // Initialize the database
        roundOfInvestmentService.save(roundOfInvestment);

        int databaseSizeBeforeUpdate = roundOfInvestmentRepository.findAll().size();

        // Update the roundOfInvestment
        RoundOfInvestment updatedRoundOfInvestment = roundOfInvestmentRepository.findOne(roundOfInvestment.getId());
        updatedRoundOfInvestment
            .endDate(UPDATED_END_DATE)
            .totalMoneyInvested(UPDATED_TOTAL_MONEY_INVESTED)
            .tokenValue(UPDATED_TOKEN_VALUE);

        restRoundOfInvestmentMockMvc.perform(put("/api/round-of-investments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRoundOfInvestment)))
            .andExpect(status().isOk());

        // Validate the RoundOfInvestment in the database
        List<RoundOfInvestment> roundOfInvestmentList = roundOfInvestmentRepository.findAll();
        assertThat(roundOfInvestmentList).hasSize(databaseSizeBeforeUpdate);
        RoundOfInvestment testRoundOfInvestment = roundOfInvestmentList.get(roundOfInvestmentList.size() - 1);
        assertThat(testRoundOfInvestment.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testRoundOfInvestment.getTotalMoneyInvested()).isEqualTo(UPDATED_TOTAL_MONEY_INVESTED);
        assertThat(testRoundOfInvestment.getTokenValue()).isEqualTo(UPDATED_TOKEN_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingRoundOfInvestment() throws Exception {
        int databaseSizeBeforeUpdate = roundOfInvestmentRepository.findAll().size();

        // Create the RoundOfInvestment

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRoundOfInvestmentMockMvc.perform(put("/api/round-of-investments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(roundOfInvestment)))
            .andExpect(status().isCreated());

        // Validate the RoundOfInvestment in the database
        List<RoundOfInvestment> roundOfInvestmentList = roundOfInvestmentRepository.findAll();
        assertThat(roundOfInvestmentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRoundOfInvestment() throws Exception {
        // Initialize the database
        roundOfInvestmentService.save(roundOfInvestment);

        int databaseSizeBeforeDelete = roundOfInvestmentRepository.findAll().size();

        // Get the roundOfInvestment
        restRoundOfInvestmentMockMvc.perform(delete("/api/round-of-investments/{id}", roundOfInvestment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RoundOfInvestment> roundOfInvestmentList = roundOfInvestmentRepository.findAll();
        assertThat(roundOfInvestmentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RoundOfInvestment.class);
        RoundOfInvestment roundOfInvestment1 = new RoundOfInvestment();
        roundOfInvestment1.setId(1L);
        RoundOfInvestment roundOfInvestment2 = new RoundOfInvestment();
        roundOfInvestment2.setId(roundOfInvestment1.getId());
        assertThat(roundOfInvestment1).isEqualTo(roundOfInvestment2);
        roundOfInvestment2.setId(2L);
        assertThat(roundOfInvestment1).isNotEqualTo(roundOfInvestment2);
        roundOfInvestment1.setId(null);
        assertThat(roundOfInvestment1).isNotEqualTo(roundOfInvestment2);
    }
}
