package com.ippon.unchained.service.impl;

import com.ippon.unchained.service.DividendsService;
import com.ippon.unchained.service.solidity.Dividends;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.datatypes.Address;
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
public class DividendsServiceImpl implements DividendsService{

    private static final Logger LOGGER = Logger.getLogger(DividendsServiceImpl.class);

    @Autowired
    Web3j web3j;

    private List<List<Object>> accountData = new ArrayList<>();
    private final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    private final BigInteger GAS_LIMIT = BigInteger.valueOf(500_000L);
    private final BigInteger INITIAL_WEI_VALUE = BigInteger.ZERO;
    private final Address MASTER_ADDRESS = new Address("0xffcf8fdee72ac11b5c542428b35eef5769c409f0");
    private String contractAddress;


    public DividendsServiceImpl(){

        //Data is stored as private/public/balance
        accountData.add(new ArrayList(Arrays.asList("4f3edf983ac636a65a842ce7c78d9aa706d3b113bce9c46f30d7d21715b23b1d", "0x90f8bf6a479f320ead074411a4b0e7944ea8c9c1")));
        accountData.add(new ArrayList(Arrays.asList("6cbed15c793ce57650b9877cf6fa156fbef513c4e6134f022a85b1ffdd59b2a1", "0xffcf8fdee72ac11b5c542428b35eef5769c409f0")));
        accountData.add(new ArrayList(Arrays.asList("6370fd033278c143179d81c5526140625662b8daa446c22ee2d73db3707e620c", "0x22d491bde2303f2f43325b2108d26f1eaba1e32b")));
        accountData.add(new ArrayList(Arrays.asList("646f1ce2fdad0e6deeeb5c7e8e5543bdde65e86029e2fd9fc169899c440a7913", "0xe11ba2b4d45eaed5996cd0823791e0c93114882d")));
        accountData.add(new ArrayList(Arrays.asList("add53f9a7e588d003326d1cbf9e4a43c061aadd9bc938c843a79e7b4fd2ad743", "0xd03ea8624c8c5987235048901fb614fdca89b117")));
        accountData.add(new ArrayList(Arrays.asList("395df67f0c2d2d9fe1ad08d1bc8b6627011959b79c53d7dd6a3536a33ab8a4fd", "0x95ced938f7991cd0dfcb48f0a06a40fa1af46ebc")));
        accountData.add(new ArrayList(Arrays.asList("e485d098507f54e7733a205420dfddbe58db035fa577fc294ebd14db90767a52", "0x3e5e9111ae8eb78fe1cc3bb8915d5d461f3ef9a9")));
        accountData.add(new ArrayList(Arrays.asList("a453611d9419d0e56f499079478fd72c37b251a94bfde4d19872c44cf65386e3", "0x28a8746e75304c0780e011bed21c72cd78cd535e")));
        accountData.add(new ArrayList(Arrays.asList("829e924fdf021ba3dbbc4225edfece9aca04b929d6e75613329ca6f1d31c0bb4", "0xaca94ef8bd5ffee41947b4585a84bda5a3d3da6e")));
        accountData.add(new ArrayList(Arrays.asList("b0057716d5917badaf911b193b12b910811c1497b5bada8d7711f758981c3773", "0x1df62f291b2e969fb0849d99d9ce41e2f137006e")));

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
    public void deployDividends(){

        LOGGER.info("Deploying Dividends");

        Credentials credentials = Credentials.create((String) accountData.get(0).get(0));
        Dividends Dividends = null;

        try {
            Dividends = Dividends.deploy(
                web3j,
                credentials,
                GAS_PRICE,
                GAS_LIMIT,
                INITIAL_WEI_VALUE,
                MASTER_ADDRESS).get();

        } catch (InterruptedException e) {
            LOGGER.error("Unable to deploy Dividends: " + e.getMessage());
            e.printStackTrace();
        } catch (ExecutionException e) {
            LOGGER.error("Unable to deploy Dividends: " + e.getMessage());
            e.printStackTrace();
        }

        contractAddress = Dividends.getContractAddress();

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
    public BigDecimal getBalanceDividends() throws ExecutionException, InterruptedException, IOException {
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
