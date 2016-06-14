package io.fourfinanceit.backend.service.loan;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LoanConfigurationService {

    @Value("${loan.max.term}")
    private String maxTerm;

    @Value("${loan.max.amount}")
    private String maxAmount;

    @Value("${loan.min.term}")
    private String minTerm;

    @Value("${loan.min.amount}")
    private String minAmount;
    @Value("${loan.max.attempts}")
    private String maxAttempts;

    public Integer getMaxTerm() {
        return Integer.parseInt(maxTerm);
    }

    public BigDecimal getMaxAmount() {
        return new BigDecimal(maxAmount);
    }

    public Integer getMinTerm() {
        return Integer.parseInt(minTerm);
    }

    public BigDecimal getMinAmount() {
        return new BigDecimal(minAmount);
    }

    public Long getMaxAttempts() {
        return Long.parseLong(maxAttempts);
    }
}
