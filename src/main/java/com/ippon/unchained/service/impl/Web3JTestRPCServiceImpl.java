package com.ippon.unchained.service.impl;

import com.ippon.unchained.service.Web3JService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Service
@Transactional
public class Web3JTestRPCServiceImpl implements Web3JService{

    @Autowired
    Web3j web3j;

    public String getClientVersion() throws IOException {

        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();

        return web3ClientVersion.getWeb3ClientVersion();

    }

    public Map<String, BigInteger> getAccountMap()throws InterruptedException, ExecutionException {

        List<String> ethereumAccountIds = getAccounts();
        Map<String, BigInteger> ethereumAccounts = new HashMap<>();

        for(int i = 0; i < ethereumAccountIds.size(); i++){

            EthGetBalance balance = web3j.ethGetBalance(ethereumAccountIds.get(i), DefaultBlockParameterName.LATEST).sendAsync().get();
            ethereumAccounts.put(ethereumAccountIds.get(i), balance.getBalance());

        }

        return ethereumAccounts;

    }

    private List<String> getAccounts()throws InterruptedException, ExecutionException{

        List<String> ethereumAccountIds;

        EthAccounts accounts = web3j.ethAccounts().sendAsync().get();
        ethereumAccountIds = accounts.getAccounts();

        return ethereumAccountIds;

    }

}
