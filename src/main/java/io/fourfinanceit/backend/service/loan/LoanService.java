package io.fourfinanceit.backend.service.loan;

import io.fourfinanceit.backend.dto.LoanDTO;
import io.fourfinanceit.backend.dto.LoanFullDTO;

public interface LoanService {

    LoanFullDTO getLoan(Long id);
}
