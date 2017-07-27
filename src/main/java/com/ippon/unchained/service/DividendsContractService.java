package com.ippon.unchained.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

public interface DividendsContractService extends Web3JService{

    BigDecimal getBalanceDividends() throws ExecutionException, InterruptedException, IOException;

    void deployDividends();

}
