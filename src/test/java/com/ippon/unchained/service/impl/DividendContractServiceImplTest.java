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
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

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

        assertThat(contract.getContractAddress().equals(dividendsContractService.getContractAddress()));
        assertThat(accounts.getAccounts().size() == 10);
    }

    @Test
    public void testMaster() {
        log.info("Total tokens: " + dividendsContractService.getMasterTotalTokens(contract).getValue());
    }
}
