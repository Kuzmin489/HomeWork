package io.fourfinanceit.backend.service.risk;

import io.fourfinanceit.backend.domain.Loan;
import io.fourfinanceit.backend.exception.AnalyzerException;
import io.fourfinanceit.backend.service.attempt.ApplicationAttemptService;
import io.fourfinanceit.backend.service.loan.LoanConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LoanApplicationAnalyzeServiceImpl implements LoanApplicationService {

    @Autowired
    private ApplicationAttemptService applicationAttemptService;

    @Autowired
    private LoanConfigurationService loanConfigurationService;

    @Autowired
    private RiskAnalyzeService riskAnalyzeService;


    @Override
    public void analyzeLoan(Loan loan) throws AnalyzerException {
        Long clientId = loan.getClient().getId();
        Long clientAttempts = applicationAttemptService.getMaxAttemptCountForUser(clientId);
        Long maxAllowedAttempts = loanConfigurationService.getMaxAttempts();
        if(clientAttempts > maxAllowedAttempts) {
            riskAnalyzeService.analyzeRisk(loan);
        }

        BigDecimal maxAllowedLoan = loanConfigurationService.getMaxAmount();
        BigDecimal loanAmount = loan.getAmount();
        if(maxAllowedLoan.equals(loanAmount)) {
            riskAnalyzeService.analyzeRisk(loan);
        }

    }
}
