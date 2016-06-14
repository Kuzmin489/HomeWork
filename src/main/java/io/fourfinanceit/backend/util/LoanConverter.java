package io.fourfinanceit.backend.util;

import io.fourfinanceit.backend.domain.Client;
import io.fourfinanceit.backend.domain.Loan;
import io.fourfinanceit.backend.dto.LoanCreateDTO;
import io.fourfinanceit.backend.dto.LoanDTO;
import io.fourfinanceit.backend.dto.LoanFullDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

@Component
public class LoanConverter {
    public LoanDTO convertLoan(Loan loan) {
        return new LoanDTO().setId(loan.getId())
                .setAmount(loan.getAmount().setScale(2, BigDecimal.ROUND_DOWN))
                .setEndTerm(loan.getLoanEndDate());
    }

    public LoanFullDTO convertFullLoan(Loan loan) {
        return new LoanFullDTO(convertLoan(loan), Collections.emptyList());
    }

    public Loan convertDTOToDomain(LoanCreateDTO loanDTO, Long userId) {
        return new Loan()
                .setLoanEndDate(LocalDate.now().plusDays(loanDTO.getTermOfDays()))
                .setAmount(loanDTO.getAmount())
                .setClient(new Client().setId(userId));
    }
}
