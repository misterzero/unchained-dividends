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
public final class Dividends extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b604051602080610c70833981016040528080519150505b60038054600160a060020a031916600160a060020a0383161790555b505b610c1d806100536000396000f3006060604052361561013b5763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663184b95598114610140578063199dc2691461016d578063234d725214610192578063254f9087146101c35780632ccc8727146101ed5780632f46c2ce1461021f5780633243c7911461023d5780633f25ed06146102555780634c1167361461027f578063696c5639146102a45780636f7bc9be146102d557806378d033f7146103255780637cc18a231461034f5780637e215c26146103855780639ed153c0146103b9578063ae909cff14610403578063cd25a6861461042a578063cd9531e81461045b578063cee2a9cf14610491578063cf2ed605146104c4578063d365a08e146104f5578063ed21187a14610524578063f2c0b39314610549578063fe702d801461057f575b600080fd5b341561014b57600080fd5b61016b600160a060020a03600435811690602435811690604435166105a9565b005b341561017857600080fd5b610180610610565b60405190815260200160405180910390f35b341561019d57600080fd5b610180600160a060020a036004351661062f565b60405190815260200160405180910390f35b34156101ce57600080fd5b6101d9600435610651565b604051901515815260200160405180910390f35b34156101f857600080fd5b6102036004356106a1565b604051600160a060020a03909116815260200160405180910390f35b341561022a57600080fd5b61016b6004356024356044356106d3565b005b341561024857600080fd5b61016b600435610744565b005b341561026057600080fd5b6101d96004356107e0565b604051901515815260200160405180910390f35b341561028a57600080fd5b610180610801565b60405190815260200160405180910390f35b34156102af57600080fd5b610180600160a060020a0360043516610822565b60405190815260200160405180910390f35b34156102e057600080fd5b6102f4600160a060020a0360043516610841565b60405194855260208501939093526040808501929092526060840152901515608083015260a0909101905180910390f35b341561033057600080fd5b6101d9600435610873565b604051901515815260200160405180910390f35b341561035a57600080fd5b6101d9600160a060020a036004351660243561089b565b604051901515815260200160405180910390f35b341561039057600080fd5b610180600160a060020a03600435166024356108dc565b60405190815260200160405180910390f35b34156103c457600080fd5b6103d8600160a060020a0360043516610977565b6040518085815260200184815260200183815260200182815260200194505050505060405180910390f35b341561040e57600080fd5b6101d96109a0565b604051901515815260200160405180910390f35b341561043557600080fd5b610180600160a060020a0360043516610a4f565b60405190815260200160405180910390f35b341561046657600080fd5b6101d9600160a060020a0360043516602435610a71565b604051901515815260200160405180910390f35b341561049c57600080fd5b6101d9600160a060020a0360043516610ab9565b604051901515815260200160405180910390f35b34156104cf57600080fd5b610180600160a060020a0360043516610ade565b60405190815260200160405180910390f35b341561050057600080fd5b610203610b00565b604051600160a060020a03909116815260200160405180910390f35b341561052f57600080fd5b610180610b0f565b60405190815260200160405180910390f35b341561055457600080fd5b6101d9600160a060020a0360043516602435610b16565b604051901515815260200160405180910390f35b341561058a57600080fd5b6101d9600435610b54565b604051901515815260200160405180910390f35b6105b9620f4240612710806106d3565b6105c5836113886108dc565b506105d3836207a120610b16565b506105e082610bb86108dc565b506105ee82620493e0610b16565b506105fb816107d06108dc565b5061060981620186a0610b16565b505b505050565b600354600160a060020a03166000908152600260205260409020545b90565b600160a060020a0381166000908152602081905260409020600201545b919050565b600354600160a060020a03166000908152600260205260408120548281151561067657fe5b60038054600160a060020a03166000908152600260205260409020929091049101555060015b919050565b60018054829081106106af57fe5b906000526020600020900160005b915054906101000a9004600160a060020a031681565b60038054600160a060020a039081166000908152600260208190526040808320889055845484168352808320600101879055935490921681529182200155828181151561071c57fe5b60038054600160a060020a03166000908152600260205260409020929091049101555b505050565b600080600061075284610b54565b50600092505b60015483101561060957600180548490811061077057fe5b906000526020600020900160005b9054600354600160a060020a039081166000908152600260208181526040808420909201546101009690960a90940490921680825292819052208054600190910180549184029091019055925090505b600190920191610758565b5b50505050565b6000806107ec83610651565b506107f56109a0565b90508091505b50919050565b60038054600160a060020a0316600090815260026020526040902001545b90565b600160a060020a0381166000908152602081905260409020545b919050565b600060208190529081526040902080546001820154600283015460038401546004909401549293919290919060ff1685565b600354600160a060020a0316600090815260026020526040902080548201905560015b919050565b60006108a683610ab9565b15156108b157600080fd5b50600160a060020a038216600090815260208190526040902060019081018054830190555b92915050565b60006108e783610ab9565b156108f157600080fd5b600160a060020a03831660009081526020819052604081208181556001808201929092556002810184905560038101849055600401805460ff191682179055805481908082016109418382610ba6565b916000526020600020900160005b8154600160a060020a038089166101009390930a928302920219161790550390505b92915050565b600260208190526000918252604090912080546001820154928201546003909201549092919084565b600080808080805b600154841015610a385760018054859081106109c057fe5b906000526020600020900160005b9054600160a060020a036101009290920a9004811660008181526020818152604080832060029081015460038054909716855292529091209092015490945090925082811515610a1a57fe5b049050610a278382610b16565b50938401935b6001909301926109a8565b610a4185610873565b50600195505b505050505090565b600160a060020a0381166000908152602081905260409020600101545b919050565b6000610a7c83610ab9565b1515610a8757600080fd5b50600160a060020a03821660009081526020819052604090206002810182905560030180548201905560015b92915050565b600160a060020a03811660009081526020819052604090206004015460ff165b919050565b600160a060020a0381166000908152602081905260409020600301545b919050565b600354600160a060020a031681565b6001545b90565b6000610b2183610ab9565b1515610b2c57600080fd5b50600160a060020a038216600090815260208190526040902080548201905560015b92915050565b600354600160a060020a031660009081526002602052604081205482811515610b7957fe5b600354600160a060020a03166000908152600260208190526040909120929091049101555060015b919050565b81548183558181151161060b5760008381526020902061060b918101908301610bd0565b5b505050565b61062c91905b80821115610bea5760008155600101610bd6565b5090565b905600a165627a7a723058205e2f5fb30f9ee14c60744702790fd471650d64e15614b45ce26bc4c8032e26490029";

    private Dividends(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Dividends(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<TransactionReceipt> init(Address a1, Address a2, Address a3) {
        Function function = new Function("init", Arrays.<Type>asList(a1, a2, a3), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
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

    public Future<TransactionReceipt> newInvestor(Address investorAddress, Uint256 moneyInvested) {
        Function function = new Function("newInvestor", Arrays.<Type>asList(investorAddress, moneyInvested), Collections.<TypeReference<?>>emptyList());
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

    public static Future<Dividends> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Address masteraddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(masteraddress));
        return deployAsync(Dividends.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Future<Dividends> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Address masteraddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(masteraddress));
        return deployAsync(Dividends.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Dividends load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Dividends(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Dividends load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Dividends(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
