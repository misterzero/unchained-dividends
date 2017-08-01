package com.ippon.unchained.config;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import com.ippon.unchained.domain.ExtendedUser;
import com.ippon.unchained.domain.User;
import com.ippon.unchained.service.ExtendedUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;

import com.ippon.unchained.service.DividendsContractService;
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
    private static final Address ADDRESS1 = new Address("0x22d491bde2303f2f43325b2108d26f1eaba1e32b");
    private static final Address ADDRESS2 = new Address("0xe11ba2b4d45eaed5996cd0823791e0c93114882d");
    private static final Address ADDRESS3 = new Address("0xd03ea8624c8c5987235048901fb614fdca89b117");

    @Autowired
    public DividendsContractService dividendsContractService;

    @Autowired
    public ExtendedUserService extendedUserService;

	public DividendsContract  deployDividends(){
        LOGGER.info("Deploying Dividends");

        Credentials credentials = Credentials.create((String) "4f3edf983ac636a65a842ce7c78d9aa706d3b113bce9c46f30d7d21715b23b1d");
        // Creating three investor users
        extendedUserService.save(new ExtendedUser().accountId(5L).address("0x22d491bde2303f2f43325b2108d26f1eaba1e32b"));
        extendedUserService.save(new ExtendedUser().accountId(6L).address("0xe11ba2b4d45eaed5996cd0823791e0c93114882d"));
        extendedUserService.save(new ExtendedUser().accountId(7L).address("0xd03ea8624c8c5987235048901fb614fdca89b117"));

        DividendsContract DividendsContract = null;

        try {
            DividendsContract = DividendsContract.deploy(
                web3j,
                credentials,
                GAS_PRICE,
                GAS_LIMIT,
                INITIAL_WEI_VALUE,
                MASTER_ADDRESS).get();
            return DividendsContract;

        } catch (InterruptedException e) {
            LOGGER.error("Unable to deploy DividendsContract: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            LOGGER.error("Unable to deploy DividendsContract: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

	@Bean
	@Scope(value = "singleton")
	public DividendsContract getContract(){
		DividendsContract contract = deployDividends();
		dividendsContractService.init(contract,ADDRESS1,ADDRESS2,ADDRESS3);
		return contract;
	}

}
