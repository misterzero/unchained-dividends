package com.ippon.unchained.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A RoundOfInvestment.
 */
@Entity
@Table(name = "round_of_investment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RoundOfInvestment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "total_money_invested")
    private Double totalMoneyInvested;

    @Column(name = "token_value")
    private Integer tokenValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public RoundOfInvestment endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getTotalMoneyInvested() {
        return totalMoneyInvested;
    }

    public RoundOfInvestment totalMoneyInvested(Double totalMoneyInvested) {
        this.totalMoneyInvested = totalMoneyInvested;
        return this;
    }

    public void setTotalMoneyInvested(Double totalMoneyInvested) {
        this.totalMoneyInvested = totalMoneyInvested;
    }

    public Integer getTokenValue() {
        return tokenValue;
    }

    public RoundOfInvestment tokenValue(Integer tokenValue) {
        this.tokenValue = tokenValue;
        return this;
    }

    public void setTokenValue(Integer tokenValue) {
        this.tokenValue = tokenValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoundOfInvestment roundOfInvestment = (RoundOfInvestment) o;
        if (roundOfInvestment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), roundOfInvestment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RoundOfInvestment{" +
            "id=" + getId() +
            ", endDate='" + getEndDate() + "'" +
            ", totalMoneyInvested='" + getTotalMoneyInvested() + "'" +
            ", tokenValue='" + getTokenValue() + "'" +
            "}";
    }
}
