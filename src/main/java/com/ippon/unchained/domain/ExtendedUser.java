package com.ippon.unchained.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.web3j.abi.datatypes.Address;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * An ExtendedUser.
 */
@Entity
@Table(name = "extended_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ExtendedUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "money_invested")
    private Integer moneyInvested;

    @Column(name = "address")
    private String address;    // TODO: Make sure the database supports data of type "Address" - originally set up as a string

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public ExtendedUser accountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getMoneyInvested() {
        return moneyInvested;
    }

    public ExtendedUser moneyInvested(Integer moneyInvested) {
        this.moneyInvested = moneyInvested;
        return this;
    }

    public void setMoneyInvested(Integer moneyInvested) {
        this.moneyInvested = moneyInvested;
    }

    public String getAddress() {
        return address;
    }

    public ExtendedUser address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExtendedUser extendedUser = (ExtendedUser) o;
        if (extendedUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), extendedUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExtendedUser{" +
            "id=" + getId() +
            ", accountId='" + getAccountId() + "'" +
            ", moneyInvested='" + getMoneyInvested() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
