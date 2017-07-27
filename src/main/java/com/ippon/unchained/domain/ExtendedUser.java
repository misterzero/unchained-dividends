package com.ippon.unchained.domain;

import org.web3j.abi.datatypes.Address;

/**
 * An ExtendedUser.
 */
public class ExtendedUser {

    private Long accountId;

    private Address address;

    private int moneyInvested;

    private int totalMoneyInvested;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getMoneyInvested() {
        return moneyInvested;
    }

    public void setMoneyInvested(int moneyInvested) {
        this.moneyInvested = moneyInvested;
    }

    public int getTotalMoneyInvested() {
        return totalMoneyInvested;
    }

    public void setTotalMoneyInvested(int totalMoneyInvested) {
        this.totalMoneyInvested = totalMoneyInvested;
    }
}
