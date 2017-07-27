package com.ippon.unchained.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Master.
 */
@Entity
@Table(name = "master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Master implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_tokens")
    private Integer totalTokens;

    @Column(name = "dividend_for_one_token")
    private Integer dividendForOneToken;

    @Column(name = "current_value_of_one_token")
    private Integer currentValueOfOneToken;

    @Column(name = "total_money_invested")
    private Integer totalMoneyInvested;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalTokens() {
        return totalTokens;
    }

    public Master totalTokens(Integer totalTokens) {
        this.totalTokens = totalTokens;
        return this;
    }

    public void setTotalTokens(Integer totalTokens) {
        this.totalTokens = totalTokens;
    }

    public Integer getDividendForOneToken() {
        return dividendForOneToken;
    }

    public Master dividendForOneToken(Integer dividendForOneToken) {
        this.dividendForOneToken = dividendForOneToken;
        return this;
    }

    public void setDividendForOneToken(Integer dividendForOneToken) {
        this.dividendForOneToken = dividendForOneToken;
    }

    public Integer getCurrentValueOfOneToken() {
        return currentValueOfOneToken;
    }

    public Master currentValueOfOneToken(Integer currentValueOfOneToken) {
        this.currentValueOfOneToken = currentValueOfOneToken;
        return this;
    }

    public void setCurrentValueOfOneToken(Integer currentValueOfOneToken) {
        this.currentValueOfOneToken = currentValueOfOneToken;
    }

    public Integer getTotalMoneyInvested() {
        return totalMoneyInvested;
    }

    public Master totalMoneyInvested(Integer totalMoneyInvested) {
        this.totalMoneyInvested = totalMoneyInvested;
        return this;
    }

    public void setTotalMoneyInvested(Integer totalMoneyInvested) {
        this.totalMoneyInvested = totalMoneyInvested;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Master master = (Master) o;
        if (master.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), master.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Master{" +
            "id=" + getId() +
            ", totalTokens='" + getTotalTokens() + "'" +
            ", dividendForOneToken='" + getDividendForOneToken() + "'" +
            ", currentValueOfOneToken='" + getCurrentValueOfOneToken() + "'" +
            ", totalMoneyInvested='" + getTotalMoneyInvested() + "'" +
            "}";
    }
}
