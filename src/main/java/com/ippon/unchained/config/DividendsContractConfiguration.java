package com.ippon.unchained.config;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import com.ippon.unchained.domain.Dividend;
import com.ippon.unchained.domain.ExtendedUser;
import com.ippon.unchained.domain.Investor;
import com.ippon.unchained.domain.RoundOfInvestment;
import com.ippon.unchained.domain.User;
import com.ippon.unchained.service.ExtendedUserService;
import com.ippon.unchained.service.InvestorService;
import com.ippon.unchained.service.RoundOfInvestmentService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;

import com.ippon.unchained.service.DividendService;
import com.ippon.unchained.service.DividendsContractService;
import com.ippon.unchained.service.DummyClass;
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

    @Autowired
    public InvestorService investorService;
    
    @Autowired
    public RoundOfInvestmentService roundOfInvestmentService;
   
    @Autowired
    public DividendService dividendService;

	public DividendsContract  deployDividends(){
        LOGGER.info("Deploying Dividends");

        Credentials credentials = Credentials.create((String) "4f3edf983ac636a65a842ce7c78d9aa706d3b113bce9c46f30d7d21715b23b1d");
        // Creating three extendedUsers and investors corresponding to users of ID 5, 6, and 7
        if (!(extendedUserService.findByAccountId(5L).size() > 0)) {
            extendedUserService.save(new ExtendedUser().accountId(5L).address("0x22d491bde2303f2f43325b2108d26f1eaba1e32b"));
        }
        if (!(investorService.findByAccountId(5L).size() > 0)) {
            investorService.save(new Investor().accountId(5L).moneyInvested(0));
        }
        if (!(extendedUserService.findByAccountId(6L).size() > 0)) {
            extendedUserService.save(new ExtendedUser().accountId(6L).address("0xe11ba2b4d45eaed5996cd0823791e0c93114882d"));
        }
        if (!(investorService.findByAccountId(6L).size() > 0)) {
            investorService.save(new Investor().accountId(6L).moneyInvested(0));
        }
        if (!(extendedUserService.findByAccountId(7L).size() > 0)) {
            extendedUserService.save(new ExtendedUser().accountId(7L).address("0xd03ea8624c8c5987235048901fb614fdca89b117"));
        }
        if (!(investorService.findByAccountId(7L).size() > 0)) {
            investorService.save(new Investor().accountId(7L).moneyInvested(0));
        }

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
		dataOnInit(contract);
		return contract;
	}
	
	public void dataOnInit(DividendsContract contract){
		for(int k=0;k<10;k++){
			int i1=50*k*k+70;
			int i2=60*k*k+70;
			int i3=40*k*k+70;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dividendsContractService.updateInvestorMoneyInvested(contract, ADDRESS1, new Uint256(50*k+70));
			dividendsContractService.updateInvestorMoneyInvested(contract, ADDRESS2, new Uint256(60*k+70));
			dividendsContractService.updateInvestorMoneyInvested(contract, ADDRESS3, new Uint256(40*k+70));
			dividendsContractService.masterRoundOfInvestment(contract, new Uint256(1000+k*300));
			
			if(roundOfInvestmentService.findAll().size()<10){
				RoundOfInvestment roundOfInvestment = new RoundOfInvestment();
				roundOfInvestment.setEndDate(LocalDate.of(2017, Month.JULY, 15+k));
				roundOfInvestment.setTokenValue(dividendsContractService.getMasterValueOfOneToken(contract).getValue().intValue());
				roundOfInvestment.setTotalMoneyInvested((double)(i1+i2+i3));
				roundOfInvestmentService.save(roundOfInvestment);
			}
			int amount = 500+50*k*k;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dividendsContractService.distributeDividends(contract, new Uint256(amount));
			if(dividendService.findAll().size()<10){
				Dividend dividend= new Dividend();
				dividend.setDate(LocalDate.of(2017, Month.JULY, 15+k));
				dividend.setAmount((double)amount);
				dividendService.save(dividend);
			}
		}
	}

}
