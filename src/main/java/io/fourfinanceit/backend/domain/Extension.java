package io.fourfinanceit.backend.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "extensions")
public class Extension {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer extensionTerm;

    private BigDecimal extensionFee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    public Long getId() {
        return id;
    }

    public Extension setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getExtensionTerm() {
        return extensionTerm;
    }

    public Extension setExtensionTerm(Integer extensionTerm) {
        this.extensionTerm = extensionTerm;
        return this;
    }

    public Extension setLoan(Loan loan) {
        this.loan = loan;
        return this;
    }

    public BigDecimal getExtensionFee() {
        return extensionFee;
    }

    public Extension setExtensionFee(BigDecimal extensionFee) {
        this.extensionFee = extensionFee;
        return this;
    }

    public Loan getLoan() {
        return loan;
    }
}
