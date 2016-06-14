package io.fourfinanceit.backend.service.risk;


import io.fourfinanceit.backend.domain.Loan;

public interface RiskAnalyzeService {
    void analyzeRisk(Loan loan);
}
