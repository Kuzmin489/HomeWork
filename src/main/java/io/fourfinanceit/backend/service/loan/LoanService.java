package io.fourfinanceit.backend.service.loan;

import io.fourfinanceit.backend.dto.LoanCreateDTO;
import io.fourfinanceit.backend.dto.LoanDTO;
import io.fourfinanceit.backend.dto.LoanFullDTO;
import io.fourfinanceit.backend.exception.LoanException;

import java.math.BigDecimal;

public interface LoanService {

    LoanFullDTO getLoanInfo(Long id) throws LoanException;

    LoanFullDTO createLoan(LoanCreateDTO loanCreateDTO, Long userId) throws LoanException;

}
