package com.ippon.unchained.config;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;

import com.ippon.unchained.service.impl.DividendsContractServiceImpl;
import com.ippon.unchained.service.solidity.DividendsContract;

@Configuration
public class DividendsContractConfiguration {
	private static final Logger LOGGER = Logger.getLogger(DividendsContractServiceImpl.class);
	@Autowired
	Web3j web3j;

    private final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    private final BigInteger GAS_LIMIT = BigInteger.valueOf(4_300_000L);
    private final BigInteger INITIAL_WEI_VALUE = BigInteger.ZERO;
    private final Address MASTER_ADDRESS = new Address("0xffcf8fdee72ac11b5c542428b35eef5769c409f0");

	public void deployDividends(){
        LOGGER.info("Deploying Dividends");

        Credentials credentials = Credentials.create((String) "4f3edf983ac636a65a842ce7c78d9aa706d3b113bce9c46f30d7d21715b23b1d");
        DividendsContract DividendsContract = null;

        try {
            DividendsContract = DividendsContract.deploy(
                web3j,
                credentials,
                GAS_PRICE,
                GAS_LIMIT,
                INITIAL_WEI_VALUE,
                MASTER_ADDRESS).get();

        } catch (InterruptedException e) {
            LOGGER.error("Unable to deploy DividendsContract: " + e.getMessage());
            e.printStackTrace();
        } catch (ExecutionException e) {
            LOGGER.error("Unable to deploy DividendsContract: " + e.getMessage());
            e.printStackTrace();
        }
    }
	
	@Bean 	
	@Scope(value = "singleton")
	public DividendsContract getContract(){
		deployDividends();
		DividendsContract contract = DividendsContract.load("0x90f8bf6a479f320ead074411a4b0e7944ea8c9c1",
	           web3j,
	           Credentials.create("0x90f8bf6a479f320ead074411a4b0e7944ea8c9c1"),
	           DividendsContract.GAS_PRICE,
	           DividendsContract.GAS_LIMIT);
		return contract;
	}
	
}
