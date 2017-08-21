package com.ippon.unchained.service.impl;

import com.ippon.unchained.UnchainedApp;
import com.ippon.unchained.service.HelloWorldService;
import com.ippon.unchained.service.MailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnchainedApp.class)
public class HelloWorldServiceImplIntTest {

    @Autowired
    HelloWorldService helloWorldService;

    @Before
    public void setup() {
        helloWorldService.deployHelloWorld();
    }

    @Test
    public void testGetClientVersion() throws IOException {
        assertThat(helloWorldService.getClientVersion()).startsWith("EthereumJS TestRPC/v1.0.1/ethereum-js");
    }

    @Test
    public void testGetAccountsMap() throws InterruptedException, ExecutionException {
        assertThat(helloWorldService.getAccountMap().size() == 10);
    }

    @Test
    public void testGetAccountMapItemValue() throws ExecutionException, InterruptedException {
        assertThat(helloWorldService.getAccountMap().values().toArray()[0].getClass().equals(BigDecimal.class));
    }

    @Test
    public void testGetAccountData() throws ExecutionException, InterruptedException {
        assertThat(helloWorldService.getAccountData().get(0).get(2).equals(BigDecimal.class));
    }

    @Test
    public void testDeployHelloWorld() {
        assertThat(helloWorldService.getContractAddress().trim().length() > 0);
    }

}
