package io.fourfinanceit.backend.validator;



import io.fourfinanceit.backend.dto.LoanCreateDTO;
import io.fourfinanceit.backend.dto.LoanDTO;
import io.fourfinanceit.backend.exception.LoanException;

import java.math.BigDecimal;

public interface LoanValidator {
    void validateLoan(LoanCreateDTO loanCreateDTO) throws LoanException;
}
