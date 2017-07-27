package com.ippon.unchained.web.rest;

import com.ippon.unchained.UnchainedApp;

import com.ippon.unchained.domain.Investor;
import com.ippon.unchained.repository.InvestorRepository;
import com.ippon.unchained.service.InvestorService;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the InvestorResource REST controller.
 *
 * @see InvestorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnchainedApp.class)
public class InvestorResourceIntTest {

    private static final Integer DEFAULT_TOKENS = 1;
    private static final Integer UPDATED_TOKENS = 2;

    private static final Integer DEFAULT_DIVIDENDS_EARNED = 1;
    private static final Integer UPDATED_DIVIDENDS_EARNED = 2;

    private static final Boolean DEFAULT_IS_INVESTOR = false;
    private static final Boolean UPDATED_IS_INVESTOR = true;

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private InvestorService investorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restInvestorMockMvc;

    private Investor investor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InvestorResource investorResource = new InvestorResource(investorService);
        this.restInvestorMockMvc = MockMvcBuilders.standaloneSetup(investorResource)
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
    public static Investor createEntity(EntityManager em) {
        Investor investor = new Investor()
            .tokens(DEFAULT_TOKENS)
            .dividendsEarned(DEFAULT_DIVIDENDS_EARNED)
            .isInvestor(DEFAULT_IS_INVESTOR);
        return investor;
    }

    @Before
    public void initTest() {
        investor = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvestor() throws Exception {
        int databaseSizeBeforeCreate = investorRepository.findAll().size();

        // Create the Investor
        restInvestorMockMvc.perform(post("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investor)))
            .andExpect(status().isCreated());

        // Validate the Investor in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeCreate + 1);
        Investor testInvestor = investorList.get(investorList.size() - 1);
        assertThat(testInvestor.getTokens()).isEqualTo(DEFAULT_TOKENS);
        assertThat(testInvestor.getDividendsEarned()).isEqualTo(DEFAULT_DIVIDENDS_EARNED);
        assertThat(testInvestor.isIsInvestor()).isEqualTo(DEFAULT_IS_INVESTOR);
    }

    @Test
    @Transactional
    public void createInvestorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = investorRepository.findAll().size();

        // Create the Investor with an existing ID
        investor.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvestorMockMvc.perform(post("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investor)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllInvestors() throws Exception {
        // Initialize the database
        investorRepository.saveAndFlush(investor);

        // Get all the investorList
        restInvestorMockMvc.perform(get("/api/investors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(investor.getId().intValue())))
            .andExpect(jsonPath("$.[*].tokens").value(hasItem(DEFAULT_TOKENS)))
            .andExpect(jsonPath("$.[*].dividendsEarned").value(hasItem(DEFAULT_DIVIDENDS_EARNED)))
            .andExpect(jsonPath("$.[*].isInvestor").value(hasItem(DEFAULT_IS_INVESTOR.booleanValue())));
    }

    @Test
    @Transactional
    public void getInvestor() throws Exception {
        // Initialize the database
        investorRepository.saveAndFlush(investor);

        // Get the investor
        restInvestorMockMvc.perform(get("/api/investors/{id}", investor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(investor.getId().intValue()))
            .andExpect(jsonPath("$.tokens").value(DEFAULT_TOKENS))
            .andExpect(jsonPath("$.dividendsEarned").value(DEFAULT_DIVIDENDS_EARNED))
            .andExpect(jsonPath("$.isInvestor").value(DEFAULT_IS_INVESTOR.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingInvestor() throws Exception {
        // Get the investor
        restInvestorMockMvc.perform(get("/api/investors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvestor() throws Exception {
        // Initialize the database
        investorService.save(investor);

        int databaseSizeBeforeUpdate = investorRepository.findAll().size();

        // Update the investor
        Investor updatedInvestor = investorRepository.findOne(investor.getId());
        updatedInvestor
            .tokens(UPDATED_TOKENS)
            .dividendsEarned(UPDATED_DIVIDENDS_EARNED)
            .isInvestor(UPDATED_IS_INVESTOR);

        restInvestorMockMvc.perform(put("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedInvestor)))
            .andExpect(status().isOk());

        // Validate the Investor in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeUpdate);
        Investor testInvestor = investorList.get(investorList.size() - 1);
        assertThat(testInvestor.getTokens()).isEqualTo(UPDATED_TOKENS);
        assertThat(testInvestor.getDividendsEarned()).isEqualTo(UPDATED_DIVIDENDS_EARNED);
        assertThat(testInvestor.isIsInvestor()).isEqualTo(UPDATED_IS_INVESTOR);
    }

    @Test
    @Transactional
    public void updateNonExistingInvestor() throws Exception {
        int databaseSizeBeforeUpdate = investorRepository.findAll().size();

        // Create the Investor

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInvestorMockMvc.perform(put("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investor)))
            .andExpect(status().isCreated());

        // Validate the Investor in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteInvestor() throws Exception {
        // Initialize the database
        investorService.save(investor);

        int databaseSizeBeforeDelete = investorRepository.findAll().size();

        // Get the investor
        restInvestorMockMvc.perform(delete("/api/investors/{id}", investor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Investor.class);
        Investor investor1 = new Investor();
        investor1.setId(1L);
        Investor investor2 = new Investor();
        investor2.setId(investor1.getId());
        assertThat(investor1).isEqualTo(investor2);
        investor2.setId(2L);
        assertThat(investor1).isNotEqualTo(investor2);
        investor1.setId(null);
        assertThat(investor1).isNotEqualTo(investor2);
    }
}
