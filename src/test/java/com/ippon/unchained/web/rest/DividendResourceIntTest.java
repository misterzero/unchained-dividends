package com.ippon.unchained.web.rest;

import com.ippon.unchained.UnchainedApp;

import com.ippon.unchained.domain.Dividend;
import com.ippon.unchained.repository.DividendRepository;
import com.ippon.unchained.service.DividendService;
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
 * Test class for the DividendResource REST controller.
 *
 * @see DividendResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnchainedApp.class)
public class DividendResourceIntTest {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    @Autowired
    private DividendRepository dividendRepository;

    @Autowired
    private DividendService dividendService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDividendMockMvc;

    private Dividend dividend;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DividendResource dividendResource = new DividendResource(dividendService);
        this.restDividendMockMvc = MockMvcBuilders.standaloneSetup(dividendResource)
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
    public static Dividend createEntity(EntityManager em) {
        Dividend dividend = new Dividend()
            .date(DEFAULT_DATE)
            .amount(DEFAULT_AMOUNT);
        return dividend;
    }

    @Before
    public void initTest() {
        dividend = createEntity(em);
    }

    @Test
    @Transactional
    public void createDividend() throws Exception {
        int databaseSizeBeforeCreate = dividendRepository.findAll().size();

        // Create the Dividend
        restDividendMockMvc.perform(post("/api/dividends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dividend)))
            .andExpect(status().isCreated());

        // Validate the Dividend in the database
        List<Dividend> dividendList = dividendRepository.findAll();
        assertThat(dividendList).hasSize(databaseSizeBeforeCreate + 1);
        Dividend testDividend = dividendList.get(dividendList.size() - 1);
        assertThat(testDividend.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testDividend.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void createDividendWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dividendRepository.findAll().size();

        // Create the Dividend with an existing ID
        dividend.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDividendMockMvc.perform(post("/api/dividends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dividend)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Dividend> dividendList = dividendRepository.findAll();
        assertThat(dividendList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDividends() throws Exception {
        // Initialize the database
        dividendRepository.saveAndFlush(dividend);

        // Get all the dividendList
        restDividendMockMvc.perform(get("/api/dividends?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dividend.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())));
    }

    @Test
    @Transactional
    public void getDividend() throws Exception {
        // Initialize the database
        dividendRepository.saveAndFlush(dividend);

        // Get the dividend
        restDividendMockMvc.perform(get("/api/dividends/{id}", dividend.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dividend.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDividend() throws Exception {
        // Get the dividend
        restDividendMockMvc.perform(get("/api/dividends/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDividend() throws Exception {
        // Initialize the database
        dividendService.save(dividend);

        int databaseSizeBeforeUpdate = dividendRepository.findAll().size();

        // Update the dividend
        Dividend updatedDividend = dividendRepository.findOne(dividend.getId());
        updatedDividend
            .date(UPDATED_DATE)
            .amount(UPDATED_AMOUNT);

        restDividendMockMvc.perform(put("/api/dividends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDividend)))
            .andExpect(status().isOk());

        // Validate the Dividend in the database
        List<Dividend> dividendList = dividendRepository.findAll();
        assertThat(dividendList).hasSize(databaseSizeBeforeUpdate);
        Dividend testDividend = dividendList.get(dividendList.size() - 1);
        assertThat(testDividend.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testDividend.getAmount()).isEqualTo(UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingDividend() throws Exception {
        int databaseSizeBeforeUpdate = dividendRepository.findAll().size();

        // Create the Dividend

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDividendMockMvc.perform(put("/api/dividends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dividend)))
            .andExpect(status().isCreated());

        // Validate the Dividend in the database
        List<Dividend> dividendList = dividendRepository.findAll();
        assertThat(dividendList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDividend() throws Exception {
        // Initialize the database
        dividendService.save(dividend);

        int databaseSizeBeforeDelete = dividendRepository.findAll().size();

        // Get the dividend
        restDividendMockMvc.perform(delete("/api/dividends/{id}", dividend.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Dividend> dividendList = dividendRepository.findAll();
        assertThat(dividendList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dividend.class);
        Dividend dividend1 = new Dividend();
        dividend1.setId(1L);
        Dividend dividend2 = new Dividend();
        dividend2.setId(dividend1.getId());
        assertThat(dividend1).isEqualTo(dividend2);
        dividend2.setId(2L);
        assertThat(dividend1).isNotEqualTo(dividend2);
        dividend1.setId(null);
        assertThat(dividend1).isNotEqualTo(dividend2);
    }
}
