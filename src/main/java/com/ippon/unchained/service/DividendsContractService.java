package com.ippon.unchained.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;

import com.ippon.unchained.service.solidity.DividendsContract;

public interface DividendsContractService extends Web3JService{

    BigDecimal getBalanceDividends() throws ExecutionException, InterruptedException, IOException;

    void init(DividendsContract contract,Address a1, Address a2, Address a3);

    void masterRoundOfInvestment(DividendsContract contract, Uint256 currentValueOfTheCompany);

    Uint256 getMasterValueOfOneToken(DividendsContract contract);

    Uint256 getMasterTotalTokens(DividendsContract contract);

    void distributeDividends(DividendsContract contract,Uint256 dividends);

    Uint256 getInvestorTokens(DividendsContract contract, Address a);

    Uint256 getInvestorsCount(DividendsContract contract);

    void updateInvestorMoneyInvested(DividendsContract contract,Address investorAddress,Uint256 moneyInvested);

    Uint256 getInvestorDividendsEarned(DividendsContract contract, Address a);

    List<Type> investors(DividendsContract contract, Address a);

    Uint256 getInvestorsTotalMoneyInvested(DividendsContract contract, Address a);

    void newInvestor(DividendsContract contract, Address investorAddress, Uint256 moneyInvested);

    Uint256 getInvestorInvestment(DividendsContract contract,Address a);
}
