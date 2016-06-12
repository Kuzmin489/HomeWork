package io.fourfinanceit.backend.util;

import io.fourfinanceit.backend.domain.Loan;
import io.fourfinanceit.backend.dto.LoanDTO;
import io.fourfinanceit.backend.dto.LoanFullDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class LoanConverter {
    public LoanDTO convertLoan(Loan loan) {
        return new LoanDTO().setId(loan.getId())
                .setAmount(loan.getAmount().setScale(2))
                .setTerm(loan.getTerm());
    }

    public LoanFullDTO convertFullLoan(Loan loan) {
        return new LoanFullDTO(convertLoan(loan), Collections.emptyList());
    }

}
