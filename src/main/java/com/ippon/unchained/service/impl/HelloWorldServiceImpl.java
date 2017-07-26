package com.ippon.unchained.service.impl;

import com.ippon.unchained.service.HelloWorldService;
import com.ippon.unchained.service.solidity.HelloWorld;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;


@Service
@Transactional
public class HelloWorldServiceImpl implements HelloWorldService{

    private static final Logger LOGGER = Logger.getLogger(HelloWorldServiceImpl.class);

    @Autowired
    Web3j web3j;

    private List<List<Object>> accountData = new ArrayList<>();
    private final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    private final BigInteger GAS_LIMIT = BigInteger.valueOf(500_000L);
    private final BigInteger INITIAL_WEI_VALUE = BigInteger.ZERO;
    private String contractAddress;


    public HelloWorldServiceImpl(){

        //Data is stored as private/public/balance
        accountData.add(new ArrayList(Arrays.asList("259b452db2511c99382850b30ce76bc422baf5a0269cfad7ba3ad014bec801c1", "0xd6ca369571065b6fb75025031484c325c6deea03")));
        accountData.add(new ArrayList(Arrays.asList("bb97e2df4ca96b843d10779669839861bd442940f8c9ce46c2854f9364aeb304", "0xaeaed48d8075fec4f6433303492836fe31fadec7")));
        accountData.add(new ArrayList(Arrays.asList("4a4d567ae46123e06cc0f7ef7576c0db681702e98875e78274a5c86702b96818", "0xc86174e38168fb37ad71416fb03d1453068233ff")));
        accountData.add(new ArrayList(Arrays.asList("4b13837e8640c99661d2402d87d37dd6a821cfa36c6720234a21fc2a79626f98", "0x20d55bce16347b3486098114c558786d37b6a18d")));
        accountData.add(new ArrayList(Arrays.asList("be0866a3c4bb0a282fe67c9543a63edd12d04ca53df5f28ebc190cf10dfa864f", "0xd4d323646af24a5b85fa2605101ef9bd7e1683f0")));
        accountData.add(new ArrayList(Arrays.asList("553a04d0cdcbb77c18b0e7ef871127b72b9f7c142e3c46cf34b74f2e7a0ec112", "0xa7c3e67e7ed7165d9e797c889f41e3cfe90fecbc")));
        accountData.add(new ArrayList(Arrays.asList("a4f829e31754082e5f6a2209a4cdae33783ba93fc887653672ed83d46e18a09b", "0x6b7436c83a55a8465e8f334437d52025b7c2e40b")));
        accountData.add(new ArrayList(Arrays.asList("5c6e0c9d7002ccee337a00968c33b8d544d433890e646fef8a8ac1df272a8fdf", "0x29fda69b1309ce77d440193e3f5bc4b8ba3496a4")));
        accountData.add(new ArrayList(Arrays.asList("37043c493d4e3fc1e57d1b79237a457980c92012bdea973c74fd363d98f8f00d", "0x697285acef9e3025aab6a2e74cc7901d2491ac16")));
        accountData.add(new ArrayList(Arrays.asList("0f3853d8a00ac4482d76b77114b72b6b3fa3ccb7877414be86b4b0e384f24f25", "0x943484df049f44d692775b5ea8bbb7ed005d9f30")));

    }

    @Override
    public String getClientVersion() {

        LOGGER.info("Obtaining Ethereum Client Version");

        Web3ClientVersion web3ClientVersion = null;
        try {
            web3ClientVersion = web3j.web3ClientVersion().send();
        } catch (IOException e) {
            LOGGER.error("Failed to obtain client version: " + e.getMessage());
            e.printStackTrace();
        }

        return web3ClientVersion.getWeb3ClientVersion();

    }

    public List<List<Object>> getAccountData() {

        LOGGER.info("Obtaining account data");

        List<String> ethereumAccountIds = getAccounts();

        for(int i = 0; i < ethereumAccountIds.size(); i++){

            EthGetBalance balance = null;

            balance = getAccountBalance(ethereumAccountIds.get(i));

            accountData.get(i).add(balance.getBalance());

        }

        return accountData;
    }

    @Override
    public void deployHelloWorld(){

        LOGGER.info("Deploying Hello World");

        Credentials credentials = Credentials.create((String) accountData.get(0).get(0));
        HelloWorld helloWorld = null;

        try {
            helloWorld = HelloWorld.deploy(
                web3j,
                credentials,
                GAS_PRICE,
                GAS_LIMIT,
                INITIAL_WEI_VALUE).get();

        } catch (InterruptedException e) {
            LOGGER.error("Unable to deploy HelloWorld: " + e.getMessage());
            e.printStackTrace();
        } catch (ExecutionException e) {
            LOGGER.error("Unable to deploy HelloWorld: " + e.getMessage());
            e.printStackTrace();
        }

        contractAddress = helloWorld.getContractAddress();

    }

    @Override
    public String getContractAddress() {
        return contractAddress;
    }

    public Map<String, BigInteger> getAccountMap(){

        LOGGER.info("Obtaining account map");

        List<String> ethereumAccountIds = getAccounts();
        Map<String, BigInteger> ethereumAccounts = new HashMap<>();
        String accountId;
        EthGetBalance balance = null;

        for(int i = 0; i < ethereumAccountIds.size(); i++){

            accountId = ethereumAccountIds.get(i);
            balance = getAccountBalance(accountId);

            ethereumAccounts.put(accountId, balance.getBalance());

        }

        return ethereumAccounts;

    }

    @Override
    public BigDecimal getBalanceHelloWorld() throws ExecutionException, InterruptedException, IOException {
        return null;
    }

    private List<String> getAccounts(){

        LOGGER.info("Obtaining all account public keys");

        List<String> ethereumAccountIds;

        EthAccounts accounts = null;
        try {
            accounts = web3j.ethAccounts().sendAsync().get();
        } catch (InterruptedException e) {
            LOGGER.error("Unable to obtain accounts: " + e.getMessage());
            e.printStackTrace();
        } catch (ExecutionException e) {
            LOGGER.error("Unable to obtain accounts: " + e.getMessage());
            e.printStackTrace();
        }
        ethereumAccountIds = accounts.getAccounts();

        return ethereumAccountIds;

    }

    private EthGetBalance getAccountBalance(String accountId){

        LOGGER.info("Obtaining account balance for [" + accountId +"]");

        EthGetBalance balance = null;
        try {
            balance = web3j.ethGetBalance(accountId, DefaultBlockParameterName.LATEST).sendAsync().get();
        } catch (InterruptedException e) {
            LOGGER.error("Unable to retrieve balance for account  ["+ accountId + "]: " + e.getMessage() );
            e.printStackTrace();
        } catch (ExecutionException e) {
            LOGGER.error("Unable to retrieve balance for account  ["+ accountId + "]: " + e.getMessage() );
            e.printStackTrace();
        }

        return balance;

    }

}
