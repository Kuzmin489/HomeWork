package io.fourfinanceit.backend.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal amount;

    private Integer term;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public BigDecimal getAmount() {
        return amount;
    }

    public Loan setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public Integer getTerm() {
        return term;
    }

    public Loan setTerm(Integer term) {
        this.term = term;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Loan setId(Long id) {
        this.id = id;
        return this;
    }
}
