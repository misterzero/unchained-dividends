package com.ippon.unchained.service.impl;

import com.ippon.unchained.service.DividendsContractService;
import com.ippon.unchained.service.solidity.DividendsContract;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
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
    	System.out.println("Init function");
    	try {
			contract.init(a1,a2,a3).get();
			System.out.println("end of the init function");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public Uint256 getMasterTotalTokens(DividendsContract contract){
    	System.out.println("getMasterTotalToken function");
    	try {
			Uint256 val = contract.getMasterTotalTokens().get();
			System.out.println("total tokens: "+val);
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
    	System.out.println("getInvestorInvestment function");
    	try {
    		Uint256 val = contract.getInvestorInvestment(a).get();
    		System.out.println("investment: "+val);
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
    	System.out.println("distributeDividends function");
    	try {
    		contract.distributeDividends(dividends).get();
    		System.out.println("dividends have been distributed.");
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void masterRoundOfInvestment(DividendsContract contract, Uint256 currentValueOfTheCompany){
    	System.out.println("masterRoundOfInvestment function");
    	try{
    		contract.masterRoundOfInvestment(currentValueOfTheCompany).get();
    		System.out.println("end of the round of investment");
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public Uint256 getMasterValueOfOneToken(DividendsContract contract){
    	System.out.println("getMasterValueOfOneToken function");
    	try{
    		Uint256 val = contract.getMasterValueOfOneToken().get();
    		System.out.println("value of one token: "+val);
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
    	System.out.println("getInvestorTokens function");
    	try{
    		Uint256 val = contract.getInvestorTokens(a).get();
    		System.out.println("tokens: "+val);
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
    	System.out.println("investors map");
    	try{
    		List<Type> i = contract.investors(a).get();
    		System.out.println("list of all the investors");
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
    	System.out.println("newInvestor funnction");
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
    	System.out.println("getInvestorDividendsEarned");
    	try{
    		Uint256 val = contract.getInvestorsDividendsEarned(a).get();
    		System.out.println("dividends earned: "+val); 
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
    	System.out.println("updateInvestorMoneyInvested function");
    	try{
    		contract.updateInvestorMoneyInvested(investorAddress, moneyInvested).get();
    		System.out.println("money invested: "+moneyInvested);
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public Bool isInvestor(DividendsContract contract, Address investorsAddress){
    	System.out.println("isInvestor function");
    	try {
    		Bool b = contract.isInvestor(investorsAddress).get();
    		System.out.println("is investor response: "+b);
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
    	System.out.println("getInvestorTotalMoneyInvested function");
    	try{
	    	Uint256 val = contract.getInvestorsTotalMoneyInvested(a).get();
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
    
    public Address investorNumberI(DividendsContract contract, Uint256 i){
    	System.out.println("investorNumberI function");
    	try{
    		Address a = contract.investorsList(i).get();
    		System.out.println("address of the investor number "+i+": "+a);
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
    	System.out.println("getInvestorsCount function");
    	try{
    		Uint256 val = contract.getInvestorsCount().get();
    		System.out.println("count of investors: "+val);
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
	public void deployDividends() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getContractAddress() {
		// TODO Auto-generated method stub
		return null;
	}

}
