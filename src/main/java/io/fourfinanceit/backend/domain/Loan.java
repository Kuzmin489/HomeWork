package io.fourfinanceit.backend.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal amount;

    private LocalDate loanEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loan")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Extension> extensionList;

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


    public Long getId() {
        return id;
    }

    public Loan setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getLoanEndDate() {
        return loanEndDate;
    }

    public Loan setLoanEndDate(LocalDate loanEndDate) {
        this.loanEndDate = loanEndDate;
        return this;
    }

    public Loan setClient(Client client) {
        this.client = client;
        return this;
    }
}
