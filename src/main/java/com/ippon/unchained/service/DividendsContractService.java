package com.ippon.unchained.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.generated.Uint256;

import com.ippon.unchained.service.solidity.DividendsContract;

public interface DividendsContractService extends Web3JService{

    BigDecimal getBalanceDividends() throws ExecutionException, InterruptedException, IOException;

    void deployDividends();
    
    void init(DividendsContract contract,Address a1, Address a2, Address a3);
    
    void masterRoundOfInvestment(DividendsContract contract, Uint256 currentValueOfTheCompany);
    
    Uint256 getMasterValueOfOneToken(DividendsContract contract);

    Uint256 getMasterTotalTokens(DividendsContract contract);
    
    void distributeDividends(DividendsContract contract,Uint256 dividends);
<<<<<<< Updated upstream
=======
    
    Uint256 getMasterTotalMoneyInvested(DividendsContract contract);
    
    Uint256 getInvestorInvestment(DividendsContract contract,Address a);
    
    Uint256 getInvestorTokens(DividendsContract contract, Address a);
    
    Uint256 getInvestorDividendsEarned(DividendsContract contract, Address a);
    
    Uint256 getInvestorsTotalMoneyInvested(DividendsContract contract, Address a);
    
    Bool isInvestor(DividendsContract contract, Address a);
>>>>>>> Stashed changes
}
