package io.fourfinanceit.backend.dto;


import java.math.BigDecimal;

public class LoanDTO {
    private Long id;
    private BigDecimal amount;
    private Integer term;

    public LoanDTO() {
    }

    public LoanDTO(Long id, BigDecimal amount, Integer term) {
        this.id = id;
        this.amount = amount;
        this.term = term;
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

    public LoanDTO setTerm(Integer term) {
        this.term = term;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Integer getTerm() {
        return term;
    }
}
