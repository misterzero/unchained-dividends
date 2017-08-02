package com.ippon.unchained.service.impl;

import com.ippon.unchained.service.DividendsContractService;
import com.ippon.unchained.service.InvestorService;
import com.ippon.unchained.service.solidity.DividendsContract;
import com.ippon.unchained.web.rest.ExtendedUserResource;
import com.ippon.unchained.config.DividendsContractConfiguration;
import com.ippon.unchained.domain.ExtendedUser;
import com.ippon.unchained.domain.Investor;
import com.ippon.unchained.repository.ExtendedUserRepository;
import com.ippon.unchained.repository.InvestorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.datatypes.Address;

import java.util.Iterator;
import java.util.List;

/**
 * Service Implementation for managing Investor.
 */
@Service
@Transactional
public class InvestorServiceImpl implements InvestorService{

    private final Logger log = LoggerFactory.getLogger(InvestorServiceImpl.class);

    private final InvestorRepository investorRepository;
    
    @Autowired
    private ExtendedUserRepository extendedUserRepository;
    
    @Autowired
    private  DividendsContractService dividendsContractService;
    
    @Autowired
    private  DividendsContractConfiguration dividendsContractConfiguration;

    
    public InvestorServiceImpl(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
        
    }

    /**
     * Save a investor.
     *
     * @param investor the entity to save
     * @return the persisted entity
     */
    @Override
    public Investor save(Investor investor) {
        log.debug("Request to save Investor : {}", investor);
        return investorRepository.save(investor);
    }

    /**
     *  Get all the investors.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Investor> findAll() {
        log.debug("Request to get all Investors");
        List<Investor> investors = investorRepository.findAll();
        investors = setInvestorsInformation(investors);
        return investors;
    }
    
    //TODO move to repositoryImpl
    public List<Investor> setInvestorsInformation(List<Investor> investors){
    	DividendsContract contract= dividendsContractConfiguration.getContract();
        List<ExtendedUser> users = extendedUserRepository.findAll();
    	for(ExtendedUser e : users){
    		long userId = e.getAccountId();
    		for(Investor i: investors){
    			long investorId = i.getAccountId();
    			if(investorId == userId){
    				Address a = new Address(e.getAddress());
    				if(dividendsContractService.isInvestor(contract, a).getValue()){
    					i.setIsInvestor(true);
	    				i.setDividendsEarned(dividendsContractService.getInvestorDividendsEarned(contract, a).getValue().intValue());
	    				i.setMoneyInvested(dividendsContractService.getInvestorInvestment(contract, a).getValue().intValue());
	    				i.setTokens(dividendsContractService.getInvestorTokens(contract, a).getValue().intValue());
	    				i.setTotalMoneyInvested(dividendsContractService.getInvestorsTotalMoneyInvested(contract, a).getValue().intValue());
    				}
    				else{
    					i.setIsInvestor(false);
    				}
    			}
    		}
    	}
    	return investors;
    }

    /**
     *  Get one investor by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Investor findOne(Long id) {
        log.debug("Request to get Investor : {}", id);
        Investor investor = investorRepository.findOne(id);
        investor = setInvestorInformation(investor);
        return investor;
    }
    
    //TODO put in repositoryImplementation
    
    public Investor setInvestorInformation(Investor investor){
    	DividendsContract contract= dividendsContractConfiguration.getContract();
    	List<ExtendedUser> users = extendedUserRepository.findAll();
    	for(ExtendedUser e: users){
    		if(e.getAccountId()==investor.getAccountId()){
    			Address a = new Address(e.getAddress());
				if(dividendsContractService.isInvestor(contract, a).getValue()){
					investor.setIsInvestor(true);
					investor.setDividendsEarned(dividendsContractService.getInvestorDividendsEarned(contract, a).getValue().intValue());
					investor.setMoneyInvested(dividendsContractService.getInvestorInvestment(contract, a).getValue().intValue());
					investor.setTokens(dividendsContractService.getInvestorTokens(contract, a).getValue().intValue());
					investor.setTotalMoneyInvested(dividendsContractService.getInvestorsTotalMoneyInvested(contract, a).getValue().intValue());
				}
				else{
					investor.setIsInvestor(false);
				}
    		}
    	}
    	return investor;
    }

    /**
     *  Get one investor by id.
     *
     *  @param accountId the id of the user
     *  @return the (list of) entities corresponding to this user
     */
    @Transactional(readOnly = true)
    public List<Investor> findByAccountId(Long accountId) {
        log.debug("Request to get Investor by accountId : {}", accountId);
        return investorRepository.findByAccountId(accountId);
    }

    /**
     *  Delete the  investor by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Investor : {}", id);
        investorRepository.delete(id);
    }
}
