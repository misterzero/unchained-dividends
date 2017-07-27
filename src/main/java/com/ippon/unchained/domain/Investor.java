package com.ippon.unchained.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * An Investor.
 */
@Entity
@Table(name = "investor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Investor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tokens")
    private Integer tokens;

    @Column(name = "dividends_earned")
    private Integer dividendsEarned;

    @Column(name = "is_investor")
    private Boolean isInvestor;

    @Column(name = "money_invested")
    private Integer moneyInvested;

    @Column(name = "total_money_invested")
    private Integer totalMoneyInvested;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTokens() {
        return tokens;
    }

    public Investor tokens(Integer tokens) {
        this.tokens = tokens;
        return this;
    }

    public void setTokens(Integer tokens) {
        this.tokens = tokens;
    }

    public Integer getDividendsEarned() {
        return dividendsEarned;
    }

    public Investor dividendsEarned(Integer dividendsEarned) {
        this.dividendsEarned = dividendsEarned;
        return this;
    }

    public void setDividendsEarned(Integer dividendsEarned) {
        this.dividendsEarned = dividendsEarned;
    }

    public Boolean isIsInvestor() {
        return isInvestor;
    }

    public Investor isInvestor(Boolean isInvestor) {
        this.isInvestor = isInvestor;
        return this;
    }

    public void setIsInvestor(Boolean isInvestor) {
        this.isInvestor = isInvestor;
    }

    public Integer getMoneyInvested() {
        return moneyInvested;
    }

    public Investor moneyInvested(Integer moneyInvested) {
        this.moneyInvested = moneyInvested;
        return this;
    }

    public void setMoneyInvested(Integer moneyInvested) {
        this.moneyInvested = moneyInvested;
    }

    public Integer getTotalMoneyInvested() {
        return totalMoneyInvested;
    }

    public Investor totalMoneyInvested(Integer totalMoneyInvested) {
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
        Investor investor = (Investor) o;
        if (investor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), investor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Investor{" +
            "id=" + getId() +
            ", tokens='" + getTokens() + "'" +
            ", dividendsEarned='" + getDividendsEarned() + "'" +
            ", isInvestor='" + isIsInvestor() + "'" +
            ", moneyInvested='" + getMoneyInvested() + "'" +
            ", totalMoneyInvested='" + getTotalMoneyInvested() + "'" +
            "}";
    }
}
