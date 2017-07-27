package com.ippon.unchained.web.rest;

import com.ippon.unchained.UnchainedApp;

import com.ippon.unchained.domain.Master;
import com.ippon.unchained.repository.MasterRepository;
import com.ippon.unchained.service.MasterService;
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
 * Test class for the MasterResource REST controller.
 *
 * @see MasterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnchainedApp.class)
public class MasterResourceIntTest {

    private static final Integer DEFAULT_TOTAL_TOKENS = 1;
    private static final Integer UPDATED_TOTAL_TOKENS = 2;

    private static final Integer DEFAULT_DIVIDEND_FOR_ONE_TOKEN = 1;
    private static final Integer UPDATED_DIVIDEND_FOR_ONE_TOKEN = 2;

    private static final Integer DEFAULT_CURRENT_VALUE_OF_ONE_TOKEN = 1;
    private static final Integer UPDATED_CURRENT_VALUE_OF_ONE_TOKEN = 2;

    private static final Integer DEFAULT_TOTAL_MONEY_INVESTED = 1;
    private static final Integer UPDATED_TOTAL_MONEY_INVESTED = 2;

    @Autowired
    private MasterRepository masterRepository;

    @Autowired
    private MasterService masterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMasterMockMvc;

    private Master master;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MasterResource masterResource = new MasterResource(masterService);
        this.restMasterMockMvc = MockMvcBuilders.standaloneSetup(masterResource)
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
    public static Master createEntity(EntityManager em) {
        Master master = new Master()
            .totalTokens(DEFAULT_TOTAL_TOKENS)
            .dividendForOneToken(DEFAULT_DIVIDEND_FOR_ONE_TOKEN)
            .currentValueOfOneToken(DEFAULT_CURRENT_VALUE_OF_ONE_TOKEN)
            .totalMoneyInvested(DEFAULT_TOTAL_MONEY_INVESTED);
        return master;
    }

    @Before
    public void initTest() {
        master = createEntity(em);
    }

    @Test
    @Transactional
    public void createMaster() throws Exception {
        int databaseSizeBeforeCreate = masterRepository.findAll().size();

        // Create the Master
        restMasterMockMvc.perform(post("/api/masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(master)))
            .andExpect(status().isCreated());

        // Validate the Master in the database
        List<Master> masterList = masterRepository.findAll();
        assertThat(masterList).hasSize(databaseSizeBeforeCreate + 1);
        Master testMaster = masterList.get(masterList.size() - 1);
        assertThat(testMaster.getTotalTokens()).isEqualTo(DEFAULT_TOTAL_TOKENS);
        assertThat(testMaster.getDividendForOneToken()).isEqualTo(DEFAULT_DIVIDEND_FOR_ONE_TOKEN);
        assertThat(testMaster.getCurrentValueOfOneToken()).isEqualTo(DEFAULT_CURRENT_VALUE_OF_ONE_TOKEN);
        assertThat(testMaster.getTotalMoneyInvested()).isEqualTo(DEFAULT_TOTAL_MONEY_INVESTED);
    }

    @Test
    @Transactional
    public void createMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = masterRepository.findAll().size();

        // Create the Master with an existing ID
        master.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMasterMockMvc.perform(post("/api/masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(master)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Master> masterList = masterRepository.findAll();
        assertThat(masterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMasters() throws Exception {
        // Initialize the database
        masterRepository.saveAndFlush(master);

        // Get all the masterList
        restMasterMockMvc.perform(get("/api/masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(master.getId().intValue())))
            .andExpect(jsonPath("$.[*].totalTokens").value(hasItem(DEFAULT_TOTAL_TOKENS)))
            .andExpect(jsonPath("$.[*].dividendForOneToken").value(hasItem(DEFAULT_DIVIDEND_FOR_ONE_TOKEN)))
            .andExpect(jsonPath("$.[*].currentValueOfOneToken").value(hasItem(DEFAULT_CURRENT_VALUE_OF_ONE_TOKEN)))
            .andExpect(jsonPath("$.[*].totalMoneyInvested").value(hasItem(DEFAULT_TOTAL_MONEY_INVESTED)));
    }

    @Test
    @Transactional
    public void getMaster() throws Exception {
        // Initialize the database
        masterRepository.saveAndFlush(master);

        // Get the master
        restMasterMockMvc.perform(get("/api/masters/{id}", master.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(master.getId().intValue()))
            .andExpect(jsonPath("$.totalTokens").value(DEFAULT_TOTAL_TOKENS))
            .andExpect(jsonPath("$.dividendForOneToken").value(DEFAULT_DIVIDEND_FOR_ONE_TOKEN))
            .andExpect(jsonPath("$.currentValueOfOneToken").value(DEFAULT_CURRENT_VALUE_OF_ONE_TOKEN))
            .andExpect(jsonPath("$.totalMoneyInvested").value(DEFAULT_TOTAL_MONEY_INVESTED));
    }

    @Test
    @Transactional
    public void getNonExistingMaster() throws Exception {
        // Get the master
        restMasterMockMvc.perform(get("/api/masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMaster() throws Exception {
        // Initialize the database
        masterService.save(master);

        int databaseSizeBeforeUpdate = masterRepository.findAll().size();

        // Update the master
        Master updatedMaster = masterRepository.findOne(master.getId());
        updatedMaster
            .totalTokens(UPDATED_TOTAL_TOKENS)
            .dividendForOneToken(UPDATED_DIVIDEND_FOR_ONE_TOKEN)
            .currentValueOfOneToken(UPDATED_CURRENT_VALUE_OF_ONE_TOKEN)
            .totalMoneyInvested(UPDATED_TOTAL_MONEY_INVESTED);

        restMasterMockMvc.perform(put("/api/masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMaster)))
            .andExpect(status().isOk());

        // Validate the Master in the database
        List<Master> masterList = masterRepository.findAll();
        assertThat(masterList).hasSize(databaseSizeBeforeUpdate);
        Master testMaster = masterList.get(masterList.size() - 1);
        assertThat(testMaster.getTotalTokens()).isEqualTo(UPDATED_TOTAL_TOKENS);
        assertThat(testMaster.getDividendForOneToken()).isEqualTo(UPDATED_DIVIDEND_FOR_ONE_TOKEN);
        assertThat(testMaster.getCurrentValueOfOneToken()).isEqualTo(UPDATED_CURRENT_VALUE_OF_ONE_TOKEN);
        assertThat(testMaster.getTotalMoneyInvested()).isEqualTo(UPDATED_TOTAL_MONEY_INVESTED);
    }

    @Test
    @Transactional
    public void updateNonExistingMaster() throws Exception {
        int databaseSizeBeforeUpdate = masterRepository.findAll().size();

        // Create the Master

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMasterMockMvc.perform(put("/api/masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(master)))
            .andExpect(status().isCreated());

        // Validate the Master in the database
        List<Master> masterList = masterRepository.findAll();
        assertThat(masterList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMaster() throws Exception {
        // Initialize the database
        masterService.save(master);

        int databaseSizeBeforeDelete = masterRepository.findAll().size();

        // Get the master
        restMasterMockMvc.perform(delete("/api/masters/{id}", master.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Master> masterList = masterRepository.findAll();
        assertThat(masterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Master.class);
        Master master1 = new Master();
        master1.setId(1L);
        Master master2 = new Master();
        master2.setId(master1.getId());
        assertThat(master1).isEqualTo(master2);
        master2.setId(2L);
        assertThat(master1).isNotEqualTo(master2);
        master1.setId(null);
        assertThat(master1).isNotEqualTo(master2);
    }
}
