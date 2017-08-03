package com.ippon.unchained.service.impl;


import com.ippon.unchained.UnchainedApp;
import com.ippon.unchained.config.DividendsContractConfiguration;
import com.ippon.unchained.service.DividendsContractService;
import com.ippon.unchained.service.solidity.DividendsContract;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthAccounts;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnchainedApp.class)
public class DividendContractServiceImplTest {

    private static final Logger log = Logger.getLogger(DividendContractServiceImplTest.class);
    private static final Address TEST_ADDRESS1 = new Address("0x22d491bde2303f2f43325b2108d26f1eaba1e32b");
    private static final Address TEST_ADDRESS2 = new Address("0xe11ba2b4d45eaed5996cd0823791e0c93114882d");
    private static final Address TEST_ADDRESS3 = new Address("0xd03ea8624c8c5987235048901fb614fdca89b117");
    private static final Address TEST_ADDRESS4 = new Address("0x95ced938f7991cd0dfcb48f0a06a40fa1af46ebc");

    @Autowired
    DividendsContractService dividendsContractService;

    @Autowired
    DividendsContractConfiguration config;

    @Autowired
    private Web3j web3j;

    private DividendsContract contract;

    @Before
    public void setup() {
        contract = config.getContract();
    }

    @Test
    public void testDeployDividends() throws ExecutionException, InterruptedException {
        EthAccounts accounts = web3j.ethAccounts().sendAsync().get();

        assertThat(contract.getContractAddress().equals(config.getContract().getContractAddress()));
        assertThat(accounts.getAccounts().size() == 10);
    }

    @Test
    public void testInit() throws ExecutionException, InterruptedException {
        dividendsContractService.init(contract,TEST_ADDRESS1,TEST_ADDRESS2,TEST_ADDRESS3);
        log.info("Investor 1 tokens:" + dividendsContractService.getInvestorTokens(contract, TEST_ADDRESS1).getValue());
        log.info("Investor 2 tokens:" + dividendsContractService.getInvestorTokens(contract, TEST_ADDRESS2).getValue());
        log.info("Investor 3 tokens:" + dividendsContractService.getInvestorTokens(contract, TEST_ADDRESS3).getValue());
        log.info("Total tokens: " + dividendsContractService.getMasterTotalTokens(contract).getValue());
        log.info("Investors: " + dividendsContractService.getInvestorsCount(contract).getValue());

        int totalTokens = dividendsContractService.getMasterTotalTokens(contract).getValue().intValue();
        assertThat(totalTokens == 1000000);
        assertThat(dividendsContractService.getInvestorsCount(contract).getValue().intValue() == 3);
    }

    @Test
    public void testMaster() {
        log.info("Total tokens: " + dividendsContractService.getMasterTotalTokens(contract).getValue());
    }

    @Test
    public void testInvestmentRound() {
        log.info("TokenValue: " + dividendsContractService.getMasterValueOfOneToken(contract).getValue());
        dividendsContractService.masterRoundOfInvestment(contract, new Uint256(10000000));
        log.info("New TokenValue: " + dividendsContractService.getMasterValueOfOneToken(contract).getValue());
    }

    @Test
    public void testDistributeDividends() {
        dividendsContractService.init(contract,TEST_ADDRESS1,TEST_ADDRESS2,TEST_ADDRESS3);
        dividendsContractService.updateInvestorMoneyInvested(contract, TEST_ADDRESS3, new Uint256(500));
        dividendsContractService.distributeDividends(contract,new Uint256(10));
        log.info("Investor1: " + dividendsContractService.getInvestorDividendsEarned(contract, TEST_ADDRESS3).getValue());
    }

    @Test
    public void testGetInvestorTokens() {
        dividendsContractService.init(contract,TEST_ADDRESS1,TEST_ADDRESS2,TEST_ADDRESS3);
        log.info("Investor 1 tokens: " + dividendsContractService.getInvestorTokens(contract, TEST_ADDRESS1).getValue());
        assertThat(dividendsContractService.getInvestorTokens(contract, TEST_ADDRESS1).getValue().intValue() == 500000);
    }

    @Test
    public void testInvestors() {
        dividendsContractService.init(contract,TEST_ADDRESS1,TEST_ADDRESS2,TEST_ADDRESS3);
        dividendsContractService.investors(contract,TEST_ADDRESS3);
    }

    @Test
    public void testNewInvestor() throws ExecutionException, InterruptedException {
        log.info("Create new Investor 4;");

        dividendsContractService.newInvestor(contract, TEST_ADDRESS4, new Uint256(1000));
        int investment = dividendsContractService
            .getInvestorInvestment(contract, TEST_ADDRESS4)
            .getValue().intValue();
        assertThat(investment == 1000);
        log.info("Investment: " + dividendsContractService.getInvestorInvestment(contract, TEST_ADDRESS4));

        dividendsContractService.updateInvestorMoneyInvested(contract, TEST_ADDRESS4, new Uint256(500));
        investment = dividendsContractService
            .getInvestorsTotalMoneyInvested(contract, TEST_ADDRESS4)
            .getValue().intValue();
        assertThat(investment == 1500);

        log.info("Investor tokens: " + dividendsContractService.getInvestorTokens(contract, TEST_ADDRESS4).getValue());
        log.info("Total Investment: " + dividendsContractService.getInvestorsTotalMoneyInvested(contract, TEST_ADDRESS4).getValue());
    }

    @Test
    public void testUpdateInvestorMoneyInvested() throws ExecutionException, InterruptedException {
        dividendsContractService.newInvestor(contract, TEST_ADDRESS4, new Uint256(1000));

        dividendsContractService.updateInvestorMoneyInvested(contract, TEST_ADDRESS4, new Uint256(1000));
        int investment = dividendsContractService
            .getInvestorsTotalMoneyInvested(contract, TEST_ADDRESS4)
            .getValue().intValue();
        log.info("Money invested: " + investment);
        assertThat(investment == 2000);
    }

    @Test
    public void testGetInvestorsCount() {
        dividendsContractService.init(contract,TEST_ADDRESS1,TEST_ADDRESS2,TEST_ADDRESS3);
        assertThat(dividendsContractService.getInvestorsCount(contract).getValue().intValue() == 3);
    }

    @Test
    public void testInvestorNumberI () {}
}
