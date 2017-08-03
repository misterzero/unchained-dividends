pragma solidity ^0.4.0;
contract DividendsContract  {

    struct Master {
        uint totalTokens;
        uint totalMoneyInvested;
        uint dividendForOneToken;
        uint currentValueOfOneToken;
    }

    struct Investor {
        uint tokens;
        uint dividendsEarned;
        uint moneyInvested;
        uint totalMoneyInvested;
        bool isInvestor;
    }

    mapping(address => Investor) public investors;
    address[] public investorsList;
    mapping(address => Master) public master;

    address public masterAddress;

    function DividendsContract(address masteraddress){

        masterAddress = masteraddress;
    }

    function isInvestor(address investorsAddress) public constant returns(bool isIndeed){
        return investors[investorsAddress].isInvestor;
    }

    function getInvestorsCount() public constant returns(uint investorsCount){
        return investorsList.length;
    }

    function newInvestor(address investorAddress, uint moneyInvested) public returns(uint rowNumber) {
        if(isInvestor(investorAddress)) throw;
        investors[investorAddress].tokens = 0;
        investors[investorAddress].dividendsEarned = 0;
        investors[investorAddress].moneyInvested = moneyInvested;
        investors[investorAddress].totalMoneyInvested = moneyInvested;
        investors[investorAddress].isInvestor = true;
        return investorsList.push(investorAddress) -1;
    }

    function updateInvestorTokens(address investorAddress, uint tokens) public returns(bool success){
        if(!isInvestor(investorAddress)) throw;
        investors[investorAddress].tokens += tokens;
        investors[investorAddress].moneyInvested = 0;
        return true;
    }

    function updateInvestorDividendsEarned(address investorAddress, uint dividendsEarned) public returns(bool success){
        if(!isInvestor(investorAddress)) throw;
        investors[investorAddress].dividendsEarned += dividendsEarned;
        return true;
    }

    function updateInvestorMoneyInvested(address investorAddress, uint moneyInvested) public returns(bool success){
        if(!isInvestor(investorAddress)) throw;
        investors[investorAddress].moneyInvested = moneyInvested;
        investors[investorAddress].totalMoneyInvested += moneyInvested;
        return true;
    }

    function init(address a1,address a2, address a3) {
        createMaster(1000,100,1000);
        newInvestor(a1,500);
        updateInvestorTokens(a1,50);
        newInvestor(a2,30);
        updateInvestorTokens(a2,300);
        newInvestor(a3,20);
        updateInvestorTokens(a3,10);
    }

    function createMaster(uint totalTokens, uint totalMoneyInvested, uint currentValueOfTheCompany){
        master[masterAddress].totalTokens = totalTokens;
        master[masterAddress].totalMoneyInvested = totalMoneyInvested;
        master[masterAddress].dividendForOneToken = 0;
        master[masterAddress].currentValueOfOneToken = currentValueOfTheCompany/totalTokens;
    }

    function updateMasterTotalTokens(uint moreTokens) public returns(bool success){
        master[masterAddress].totalTokens += moreTokens;
        return true;
    }

    function updateMasterDividendForOneToken(uint dividends) public returns(bool success){
        master[masterAddress].dividendForOneToken = dividends/master[masterAddress].totalTokens;
        return true;
    }

    function updateMasterCurrentValueOfOneToken(uint currentValueOfTheCompany) public returns(bool success){
        master[masterAddress].currentValueOfOneToken = currentValueOfTheCompany/master[masterAddress].totalTokens;
        return true;
    }
    
	function updateMasterTotalMoneyInvested(uint moneyToAdd) public returns(bool success){
        master[masterAddress].totalMoneyInvested+=moneyToAdd;
        return true;
    }
    
    function giveTokens() public returns(bool success){
        uint totalTokensAdded = 0;
        uint totalInvestmentToAddToMaster = 0;
        for(uint k=0;k<investorsList.length;k++){
            address a = investorsList[k];
            uint moneyInvestedByCurrentInvestor = investors[a].moneyInvested;
            if(moneyInvestedByCurrentInvestor>0){
                uint numberOfTokensToAdd = moneyInvestedByCurrentInvestor/master[masterAddress].currentValueOfOneToken;
                updateInvestorTokens(a,numberOfTokensToAdd);
                totalInvestmentToAddToMaster+=moneyInvestedByCurrentInvestor;
                totalTokensAdded+= numberOfTokensToAdd;
            }
        }
        updateMasterTotalTokens(totalTokensAdded);
        updateMasterTotalMoneyInvested(totalInvestmentToAddToMaster);
        return true;
    }

    function masterRoundOfInvestment(uint currentValueOfTheCompany) public returns(bool success){
        updateMasterCurrentValueOfOneToken(currentValueOfTheCompany);
        bool b = giveTokens();
        return b;
    }


    function distributeDividends(uint dividends){
        updateMasterDividendForOneToken(dividends);
        for(uint k=0;k<investorsList.length;k++){
            address a = investorsList[k];
            uint dividendForOneToken = master[masterAddress].dividendForOneToken;
            investors[a].dividendsEarned += dividendForOneToken*investors[a].tokens;
        }
    }

    function getMasterTotalTokens() constant returns(uint totalTokens){
        return master[masterAddress].totalTokens;
    }

    function getMasterValueOfOneToken() constant returns(uint currentValueOfOneToken){
        return master[masterAddress].currentValueOfOneToken;
    }

    function getInvestorTokens(address a) constant returns(uint tokens){
        return investors[a].tokens;
    }

    function getInvestorInvestment(address a) constant returns(uint moneyInvested){
        return investors[a].moneyInvested;
    }

    function getInvestorsDividendsEarned(address a) constant returns(uint dividendendsEarned){
        return investors[a].dividendsEarned;
    }

    function getInvestorsTotalMoneyInvested(address a) constant returns(uint totalMoneyInvested){
        return investors[a].totalMoneyInvested;
    }
    
    function getMasterTotalMoneyInvested() constant returns(uint total){
        return master[masterAddress].totalMoneyInvested;
    }
}
