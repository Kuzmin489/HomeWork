package io.fourfinanceit.backend.dto;


import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanDTO {
    private Long id;
    private BigDecimal amount;
    private LocalDate endTerm;

    public LoanDTO() {
    }

    public LoanDTO(BigDecimal amount, LocalDate endTerm) {
        this.amount = amount;
        this.endTerm = endTerm;
    }

    public LoanDTO(Long id, BigDecimal amount, LocalDate endTerm) {
        this.id = id;
        this.amount = amount;
        this.endTerm = endTerm;
    }

    public Long getId() {
        return id;
    }

    public LoanDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public LoanDTO setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getEndTerm() {
        return endTerm;
    }

    public LoanDTO setEndTerm(LocalDate endTerm) {
        this.endTerm = endTerm;
        return this;
    }
}
