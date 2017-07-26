pragma solidity ^0.4.9;
contract HelloWorld {

    uint public balance;

    function HelloWorld(){
        balance = 1000;
    }

    function get() constant returns(uint) {
        return balance;
    }

}
