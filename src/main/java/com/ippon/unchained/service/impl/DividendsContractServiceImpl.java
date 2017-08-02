package com.ippon.unchained.service.impl;

import com.ippon.unchained.service.DividendsContractService;
import com.ippon.unchained.service.solidity.DividendsContract;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.Type;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;


@Service
@Transactional
public class DividendsContractServiceImpl implements DividendsContractService{

    private static final Logger LOGGER = Logger.getLogger(DividendsContractServiceImpl.class);


    private List<List<Object>> accountData = new ArrayList<>();


    public DividendsContractServiceImpl(){

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


    public void init(DividendsContract contract,Address a1, Address a2, Address a3){
    	LOGGER.info("Init function");
        contract.init(a1,a2,a3);
    }

    public Uint256 getMasterTotalTokens(DividendsContract contract){
    	LOGGER.info("getMasterTotalToken function");
    	try {
			Uint256 val = contract.getMasterTotalTokens().get();
			LOGGER.info("total tokens: "+val);
			return val;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public Uint256 getInvestorInvestment(DividendsContract contract,Address a){
    	LOGGER.info("getInvestorInvestment function");
    	try {
    		Uint256 val = contract.getInvestorInvestment(a).get();
    		LOGGER.info("investment: "+val);
    		return val;
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public void distributeDividends(DividendsContract contract,Uint256 dividends){
    	LOGGER.info("distributeDividends function");
    	try {
    		contract.distributeDividends(dividends).get();
    		LOGGER.info("dividends have been distributed.");
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void masterRoundOfInvestment(DividendsContract contract, Uint256 currentValueOfTheCompany){
    	LOGGER.info("masterRoundOfInvestment function");
        contract.masterRoundOfInvestment(currentValueOfTheCompany);
        LOGGER.info("end of the round of investment");
    }

    public Uint256 getMasterValueOfOneToken(DividendsContract contract){
    	LOGGER.info("getMasterValueOfOneToken function");
    	try{
    		Uint256 val = contract.getMasterValueOfOneToken().get();
    		LOGGER.info("value of one token: "+val);
    		return val;
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public Uint256 getInvestorTokens(DividendsContract contract, Address a){
    	LOGGER.info("getInvestorTokens function");
    	try{
    		Uint256 val = contract.getInvestorTokens(a).get();
    		LOGGER.info("tokens: "+val);
    		return val;
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public List<Type> investors(DividendsContract contract, Address a){
    	LOGGER.info("investors map");
    	try{
    		List<Type> i = contract.investors(a).get();
    		LOGGER.info("list of all the investors");
    		return i;
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public void newInvestor(DividendsContract contract, Address investorAddress, Uint256 moneyInvested){
    	LOGGER.info("newInvestor funnction");
    	try{
    		contract.newInvestor(investorAddress, moneyInvested).get();
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public Uint256 getInvestorDividendsEarned(DividendsContract contract, Address a){
    	LOGGER.info("getInvestorDividendsEarned");
    	try{
    		Uint256 val = contract.getInvestorsDividendsEarned(a).get();
    		LOGGER.info("dividends earned: "+val);
    		return val;
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public void updateInvestorMoneyInvested(DividendsContract contract,Address investorAddress,Uint256 moneyInvested){
    	LOGGER.info("updateInvestorMoneyInvested function");
    	try{
    		contract.updateInvestorMoneyInvested(investorAddress, moneyInvested).get();
    		LOGGER.info("money invested: "+moneyInvested);
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public Bool isInvestor(DividendsContract contract, Address investorsAddress){
    	LOGGER.info("isInvestor function");
    	try {
    		Bool b = contract.isInvestor(investorsAddress).get();
    		LOGGER.info("is investor response: "+b);
    		return b;
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public Uint256 getInvestorsTotalMoneyInvested(DividendsContract contract, Address a){
    	LOGGER.info("getInvestorTotalMoneyInvested function");
    	try{
	    	Uint256 val = contract.getInvestorsTotalMoneyInvested(a).get();
	    	LOGGER.info("total money invested: "+val);
	    	return val;
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public Address investorNumberI(DividendsContract contract, Uint256 i){
    	LOGGER.info("investorNumberI function");
    	try{
    		Address a = contract.investorsList(i).get();
    		LOGGER.info("address of the investor number "+i+": "+a);
    		return a;
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public Uint256 getInvestorsCount(DividendsContract contract){
    	LOGGER.info("getInvestorsCount function");
    	try{
    		Uint256 val = contract.getInvestorsCount().get();
    		LOGGER.info("count of investors: "+val);
    		return val;
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    public Uint256 getMasterTotalMoneyInvested(DividendsContract contract){
    	System.out.println("getMasterTotalMoneyInvested function");
    	try{
    		Uint256 val = contract.getMasterTotalMoneyInvested().get();
    		System.out.println("total money invested: "+val);
    		return val;
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }


	@Override
	public String getClientVersion() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Map<String, BigInteger> getAccountMap() throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<List<Object>> getAccountData() throws ExecutionException, InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public BigDecimal getBalanceDividends() throws ExecutionException, InterruptedException, IOException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String getContractAddress() {
		// TODO Auto-generated method stub
		return null;
	}

}
