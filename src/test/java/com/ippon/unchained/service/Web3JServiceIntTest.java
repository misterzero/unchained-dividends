package com.ippon.unchained.service;

import com.ippon.unchained.UnchainedApp;
import org.apache.http.conn.HttpHostConnectException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnchainedApp.class)
public class Web3JServiceIntTest {

    @Autowired
    Web3JService web3JService;


    @Test
    public void testGetClientVersion() throws IOException {
        assertThat(web3JService.getClientVersion()).startsWith("EthereumJS TestRPC/v1.0.1/ethereum-js");
    }

    @Test
    public void testGetAccountsMap() throws InterruptedException, ExecutionException {
        assertThat(web3JService.getAccountMap().size() == 10);
    }

    @Test
    public void testGetAccountMapItemValue() throws ExecutionException, InterruptedException {
        assertThat(web3JService.getAccountMap().values().toArray()[0].getClass().equals(BigDecimal.class));
    }
}
