package io.fourfinanceit.backend.service.risk;


import io.fourfinanceit.backend.domain.Loan;
import io.fourfinanceit.backend.exception.AnalyzerException;


public interface LoanApplicationService {
    void analyzeLoan(Loan loan) throws AnalyzerException;
}
