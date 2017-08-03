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
    private static final String BINARY = "6060604052341561000f57600080fd5b604051602080610d3e833981016040528080519150505b60038054600160a060020a031916600160a060020a0383161790555b505b610ceb806100536000396000f300606060405236156101515763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663184b9559811461015657806319786b4714610183578063199dc269146101a8578063234d7252146101cd578063254f9087146101fe5780632ccc8727146102285780632f46c2ce1461025a5780633243c791146102785780633f25ed06146102905780634c116736146102ba578063696c5639146102df5780636f7bc9be1461031057806378d033f7146103605780637cc18a231461038a5780637e215c26146103c0578063810300cb146103f45780639ed153c01461041e578063ae909cff14610468578063cd25a6861461048f578063cd9531e8146104c0578063cee2a9cf146104f6578063cf2ed60514610529578063d365a08e1461055a578063ed21187a14610589578063f2c0b393146105ae578063fe702d80146105e4575b600080fd5b341561016157600080fd5b610181600160a060020a036004358116906024358116906044351661060e565b005b341561018e57600080fd5b61019661066e565b60405190815260200160405180910390f35b34156101b357600080fd5b610196610690565b60405190815260200160405180910390f35b34156101d857600080fd5b610196600160a060020a03600435166106af565b60405190815260200160405180910390f35b341561020957600080fd5b6102146004356106d1565b604051901515815260200160405180910390f35b341561023357600080fd5b61023e600435610721565b604051600160a060020a03909116815260200160405180910390f35b341561026557600080fd5b610181600435602435604435610753565b005b341561028357600080fd5b6101816004356107c4565b005b341561029b57600080fd5b610214600435610860565b604051901515815260200160405180910390f35b34156102c557600080fd5b610196610881565b60405190815260200160405180910390f35b34156102ea57600080fd5b610196600160a060020a03600435166108a2565b60405190815260200160405180910390f35b341561031b57600080fd5b61032f600160a060020a03600435166108c1565b60405194855260208501939093526040808501929092526060840152901515608083015260a0909101905180910390f35b341561036b57600080fd5b6102146004356108f3565b604051901515815260200160405180910390f35b341561039557600080fd5b610214600160a060020a036004351660243561091b565b604051901515815260200160405180910390f35b34156103cb57600080fd5b610196600160a060020a036004351660243561095c565b60405190815260200160405180910390f35b34156103ff57600080fd5b6102146004356109f7565b604051901515815260200160405180910390f35b341561042957600080fd5b61043d600160a060020a0360043516610a22565b6040518085815260200184815260200183815260200182815260200194505050505060405180910390f35b341561047357600080fd5b610214610a4b565b604051901515815260200160405180910390f35b341561049a57600080fd5b610196600160a060020a0360043516610b19565b60405190815260200160405180910390f35b34156104cb57600080fd5b610214600160a060020a0360043516602435610b3b565b604051901515815260200160405180910390f35b341561050157600080fd5b610214600160a060020a0360043516610b83565b604051901515815260200160405180910390f35b341561053457600080fd5b610196600160a060020a0360043516610ba8565b60405190815260200160405180910390f35b341561056557600080fd5b61023e610bca565b604051600160a060020a03909116815260200160405180910390f35b341561059457600080fd5b610196610bd9565b60405190815260200160405180910390f35b34156105b957600080fd5b610214600160a060020a0360043516602435610be0565b604051901515815260200160405180910390f35b34156105ef57600080fd5b610214600435610c22565b604051901515815260200160405180910390f35b61061e6103e860646103e8610753565b61062a836101f461095c565b50610636836032610be0565b506106438261012c61095c565b5061064f82601e610be0565b5061065b8160c861095c565b50610667816014610be0565b505b505050565b600354600160a060020a03166000908152600260205260409020600101545b90565b600354600160a060020a03166000908152600260205260409020545b90565b600160a060020a0381166000908152602081905260409020600201545b919050565b600354600160a060020a0316600090815260026020526040812054828115156106f657fe5b60038054600160a060020a03166000908152600260205260409020929091049101555060015b919050565b600180548290811061072f57fe5b906000526020600020900160005b915054906101000a9004600160a060020a031681565b60038054600160a060020a039081166000908152600260208190526040808320889055845484168352808320600101879055935490921681529182200155828181151561079c57fe5b60038054600160a060020a03166000908152600260205260409020929091049101555b505050565b60008060006107d284610c22565b50600092505b6001548310156106675760018054849081106107f057fe5b906000526020600020900160005b9054600354600160a060020a039081166000908152600260208181526040808420909201546101009690960a90940490921680825292819052208054600190910180549184029091019055925090505b6001909201916107d8565b5b50505050565b60008061086c836106d1565b50610875610a4b565b90508091505b50919050565b60038054600160a060020a0316600090815260026020526040902001545b90565b600160a060020a0381166000908152602081905260409020545b919050565b600060208190529081526040902080546001820154600283015460038401546004909401549293919290919060ff1685565b600354600160a060020a0316600090815260026020526040902080548201905560015b919050565b600061092683610b83565b151561093157600080fd5b50600160a060020a038216600090815260208190526040902060019081018054830190555b92915050565b600061096783610b83565b1561097157600080fd5b600160a060020a03831660009081526020819052604081208181556001808201929092556002810184905560038101849055600401805460ff191682179055805481908082016109c18382610c74565b916000526020600020900160005b8154600160a060020a038089166101009390930a928302920219161790550390505b92915050565b600354600160a060020a0316600090815260026020526040902060019081018054830190555b919050565b600260208190526000918252604090912080546001820154928201546003909201549092919084565b60008080808080805b600154841015610af7576001805485908110610a6c57fe5b906000526020600020900160005b9054600160a060020a036101009290920a900416600081815260208190526040812060020154919450909250821115610aeb5760038054600160a060020a03166000908152600260205260409020015482811515610ad457fe5b049050610ae18382610be0565b5094850194938101935b5b600190930192610a54565b610b00866108f3565b50610b0a856109f7565b50600196505b50505050505090565b600160a060020a0381166000908152602081905260409020600101545b919050565b6000610b4683610b83565b1515610b5157600080fd5b50600160a060020a03821660009081526020819052604090206002810182905560030180548201905560015b92915050565b600160a060020a03811660009081526020819052604090206004015460ff165b919050565b600160a060020a0381166000908152602081905260409020600301545b919050565b600354600160a060020a031681565b6001545b90565b6000610beb83610b83565b1515610bf657600080fd5b50600160a060020a03821660009081526020819052604081208054830181556002015560015b92915050565b600354600160a060020a031660009081526002602052604081205482811515610c4757fe5b600354600160a060020a03166000908152600260208190526040909120929091049101555060015b919050565b81548183558181151161066957600083815260209020610669918101908301610c9e565b5b505050565b61068d91905b80821115610cb85760008155600101610ca4565b5090565b905600a165627a7a7230582094692e72918f4fd92bfda103d8830c4b6dcfd63a973a3f68688595d0356f96360029";

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
