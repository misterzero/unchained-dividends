package com.ippon.unchained.service.solidity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.3.0.
 */
public final class DividendsContract extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b604051602080610d45833981016040528080519150505b60038054600160a060020a031916600160a060020a0383161790555b505b610cf2806100536000396000f300606060405236156101515763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663184b9559811461015657806319786b4714610183578063199dc269146101a8578063234d7252146101cd578063254f9087146101fe5780632ccc8727146102285780632f46c2ce1461025a5780633243c791146102785780633f25ed06146102905780634c116736146102ba578063696c5639146102df5780636f7bc9be1461031057806378d033f7146103605780637cc18a231461038a5780637e215c26146103c0578063810300cb146103f45780639ed153c01461041e578063ae909cff14610468578063cd25a6861461048f578063cd9531e8146104c0578063cee2a9cf146104f6578063cf2ed60514610529578063d365a08e1461055a578063ed21187a14610589578063f2c0b393146105ae578063fe702d80146105e4575b600080fd5b341561016157600080fd5b610181600160a060020a036004358116906024358116906044351661060e565b005b341561018e57600080fd5b610196610675565b60405190815260200160405180910390f35b34156101b357600080fd5b610196610697565b60405190815260200160405180910390f35b34156101d857600080fd5b610196600160a060020a03600435166106b6565b60405190815260200160405180910390f35b341561020957600080fd5b6102146004356106d8565b604051901515815260200160405180910390f35b341561023357600080fd5b61023e600435610728565b604051600160a060020a03909116815260200160405180910390f35b341561026557600080fd5b61018160043560243560443561075a565b005b341561028357600080fd5b6101816004356107cb565b005b341561029b57600080fd5b610214600435610867565b604051901515815260200160405180910390f35b34156102c557600080fd5b610196610888565b60405190815260200160405180910390f35b34156102ea57600080fd5b610196600160a060020a03600435166108a9565b60405190815260200160405180910390f35b341561031b57600080fd5b61032f600160a060020a03600435166108c8565b60405194855260208501939093526040808501929092526060840152901515608083015260a0909101905180910390f35b341561036b57600080fd5b6102146004356108fa565b604051901515815260200160405180910390f35b341561039557600080fd5b610214600160a060020a0360043516602435610922565b604051901515815260200160405180910390f35b34156103cb57600080fd5b610196600160a060020a0360043516602435610963565b60405190815260200160405180910390f35b34156103ff57600080fd5b6102146004356109fe565b604051901515815260200160405180910390f35b341561042957600080fd5b61043d600160a060020a0360043516610a29565b6040518085815260200184815260200183815260200182815260200194505050505060405180910390f35b341561047357600080fd5b610214610a52565b604051901515815260200160405180910390f35b341561049a57600080fd5b610196600160a060020a0360043516610b20565b60405190815260200160405180910390f35b34156104cb57600080fd5b610214600160a060020a0360043516602435610b42565b604051901515815260200160405180910390f35b341561050157600080fd5b610214600160a060020a0360043516610b8a565b604051901515815260200160405180910390f35b341561053457600080fd5b610196600160a060020a0360043516610baf565b60405190815260200160405180910390f35b341561056557600080fd5b61023e610bd1565b604051600160a060020a03909116815260200160405180910390f35b341561059457600080fd5b610196610be0565b60405190815260200160405180910390f35b34156105b957600080fd5b610214600160a060020a0360043516602435610be7565b604051901515815260200160405180910390f35b34156105ef57600080fd5b610214600435610c29565b604051901515815260200160405180910390f35b61061e620f42406127108061075a565b61062a83611388610963565b50610638836207a120610be7565b5061064582610bb8610963565b5061065382620493e0610be7565b50610660816107d0610963565b5061066e81620186a0610be7565b505b505050565b600354600160a060020a03166000908152600260205260409020600101545b90565b600354600160a060020a03166000908152600260205260409020545b90565b600160a060020a0381166000908152602081905260409020600201545b919050565b600354600160a060020a0316600090815260026020526040812054828115156106fd57fe5b60038054600160a060020a03166000908152600260205260409020929091049101555060015b919050565b600180548290811061073657fe5b906000526020600020900160005b915054906101000a9004600160a060020a031681565b60038054600160a060020a03908116600090815260026020819052604080832088905584548416835280832060010187905593549092168152918220015582818115156107a357fe5b60038054600160a060020a03166000908152600260205260409020929091049101555b505050565b60008060006107d984610c29565b50600092505b60015483101561066e5760018054849081106107f757fe5b906000526020600020900160005b9054600354600160a060020a039081166000908152600260208181526040808420909201546101009690960a90940490921680825292819052208054600190910180549184029091019055925090505b6001909201916107df565b5b50505050565b600080610873836106d8565b5061087c610a52565b90508091505b50919050565b60038054600160a060020a0316600090815260026020526040902001545b90565b600160a060020a0381166000908152602081905260409020545b919050565b600060208190529081526040902080546001820154600283015460038401546004909401549293919290919060ff1685565b600354600160a060020a0316600090815260026020526040902080548201905560015b919050565b600061092d83610b8a565b151561093857600080fd5b50600160a060020a038216600090815260208190526040902060019081018054830190555b92915050565b600061096e83610b8a565b1561097857600080fd5b600160a060020a03831660009081526020819052604081208181556001808201929092556002810184905560038101849055600401805460ff191682179055805481908082016109c88382610c7b565b916000526020600020900160005b8154600160a060020a038089166101009390930a928302920219161790550390505b92915050565b600354600160a060020a0316600090815260026020526040902060019081018054830190555b919050565b600260208190526000918252604090912080546001820154928201546003909201549092919084565b60008080808080805b600154841015610afe576001805485908110610a7357fe5b906000526020600020900160005b9054600160a060020a036101009290920a900416600081815260208190526040812060020154919450909250821115610af25760038054600160a060020a03166000908152600260205260409020015482811515610adb57fe5b049050610ae88382610be7565b5094850194938101935b5b600190930192610a5b565b610b07866108fa565b50610b11856109fe565b50600196505b50505050505090565b600160a060020a0381166000908152602081905260409020600101545b919050565b6000610b4d83610b8a565b1515610b5857600080fd5b50600160a060020a03821660009081526020819052604090206002810182905560030180548201905560015b92915050565b600160a060020a03811660009081526020819052604090206004015460ff165b919050565b600160a060020a0381166000908152602081905260409020600301545b919050565b600354600160a060020a031681565b6001545b90565b6000610bf283610b8a565b1515610bfd57600080fd5b50600160a060020a03821660009081526020819052604081208054830181556002015560015b92915050565b600354600160a060020a031660009081526002602052604081205482811515610c4e57fe5b600354600160a060020a03166000908152600260208190526040909120929091049101555060015b919050565b81548183558181151161067057600083815260209020610670918101908301610ca5565b5b505050565b61069491905b80821115610cbf5760008155600101610cab565b5090565b905600a165627a7a723058202313f923522e398dc793d599e860cd0a6066c9ee70ad0a8aa1ce040eebbee3760029";

    private DividendsContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private DividendsContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<TransactionReceipt> init(Address a1, Address a2, Address a3) {
        Function function = new Function("init", Arrays.<Type>asList(a1, a2, a3), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> getMasterTotalMoneyInvested() {
        Function function = new Function("getMasterTotalMoneyInvested", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getMasterTotalTokens() {
        Function function = new Function("getMasterTotalTokens", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getInvestorInvestment(Address a) {
        Function function = new Function("getInvestorInvestment", 
                Arrays.<Type>asList(a), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> updateMasterCurrentValueOfOneToken(Uint256 currentValueOfTheCompany) {
        Function function = new Function("updateMasterCurrentValueOfOneToken", Arrays.<Type>asList(currentValueOfTheCompany), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> investorsList(Uint256 param0) {
        Function function = new Function("investorsList", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> createMaster(Uint256 totalTokens, Uint256 totalMoneyInvested, Uint256 currentValueOfTheCompany) {
        Function function = new Function("createMaster", Arrays.<Type>asList(totalTokens, totalMoneyInvested, currentValueOfTheCompany), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> distributeDividends(Uint256 dividends) {
        Function function = new Function("distributeDividends", Arrays.<Type>asList(dividends), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> masterRoundOfInvestment(Uint256 currentValueOfTheCompany) {
        Function function = new Function("masterRoundOfInvestment", Arrays.<Type>asList(currentValueOfTheCompany), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> getMasterValueOfOneToken() {
        Function function = new Function("getMasterValueOfOneToken", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getInvestorTokens(Address a) {
        Function function = new Function("getInvestorTokens", 
                Arrays.<Type>asList(a), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<List<Type>> investors(Address param0) {
        Function function = new Function("investors", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> updateMasterTotalTokens(Uint256 moreTokens) {
        Function function = new Function("updateMasterTotalTokens", Arrays.<Type>asList(moreTokens), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> updateInvestorDividendsEarned(Address investorAddress, Uint256 dividendsEarned) {
        Function function = new Function("updateInvestorDividendsEarned", Arrays.<Type>asList(investorAddress, dividendsEarned), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> newInvestor(Address investorAddress, Uint256 moneyInvested) {
        Function function = new Function("newInvestor", Arrays.<Type>asList(investorAddress, moneyInvested), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> updateMasterTotalMoneyInvested(Uint256 moneyToAdd) {
        Function function = new Function("updateMasterTotalMoneyInvested", Arrays.<Type>asList(moneyToAdd), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<List<Type>> master(Address param0) {
        Function function = new Function("master", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> giveTokens() {
        Function function = new Function("giveTokens", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> getInvestorsDividendsEarned(Address a) {
        Function function = new Function("getInvestorsDividendsEarned", 
                Arrays.<Type>asList(a), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> updateInvestorMoneyInvested(Address investorAddress, Uint256 moneyInvested) {
        Function function = new Function("updateInvestorMoneyInvested", Arrays.<Type>asList(investorAddress, moneyInvested), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bool> isInvestor(Address investorsAddress) {
        Function function = new Function("isInvestor", 
                Arrays.<Type>asList(investorsAddress), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getInvestorsTotalMoneyInvested(Address a) {
        Function function = new Function("getInvestorsTotalMoneyInvested", 
                Arrays.<Type>asList(a), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> masterAddress() {
        Function function = new Function("masterAddress", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getInvestorsCount() {
        Function function = new Function("getInvestorsCount", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> updateInvestorTokens(Address investorAddress, Uint256 tokens) {
        Function function = new Function("updateInvestorTokens", Arrays.<Type>asList(investorAddress, tokens), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> updateMasterDividendForOneToken(Uint256 dividends) {
        Function function = new Function("updateMasterDividendForOneToken", Arrays.<Type>asList(dividends), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<DividendsContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Address masteraddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(masteraddress));
        return deployAsync(DividendsContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Future<DividendsContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Address masteraddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(masteraddress));
        return deployAsync(DividendsContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static DividendsContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DividendsContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static DividendsContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DividendsContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
