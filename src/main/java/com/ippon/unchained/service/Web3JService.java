package com.ippon.unchained.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface Web3JService {

    String getClientVersion() throws IOException;

    Map<String, BigInteger> getAccountMap() throws InterruptedException, ExecutionException;

    List<List<Object>> getAccountData() throws ExecutionException, InterruptedException;

    String getContractAddress();
}
