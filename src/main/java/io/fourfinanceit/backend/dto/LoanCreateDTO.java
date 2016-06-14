package io.fourfinanceit.backend.dto;

import java.math.BigDecimal;


public class LoanCreateDTO {
    private Integer termOfDays;
    private BigDecimal amount;

    public LoanCreateDTO() {
    }

    public LoanCreateDTO(Integer termOfDays, BigDecimal amount) {
        this.termOfDays = termOfDays;
        this.amount = amount;
    }

    public Integer getTermOfDays() {
        return termOfDays;
    }

    public LoanCreateDTO setTermOfDays(Integer termOfDays) {
        this.termOfDays = termOfDays;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LoanCreateDTO setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
}
