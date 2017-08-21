package com.ippon.unchained.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

public interface HelloWorldService extends Web3JService{

    BigDecimal getBalanceHelloWorld() throws ExecutionException, InterruptedException, IOException;

    void deployHelloWorld();

}
