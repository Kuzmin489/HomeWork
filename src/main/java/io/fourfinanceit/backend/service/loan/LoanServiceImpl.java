package io.fourfinanceit.backend.service.loan;

import io.fourfinanceit.backend.database.loan.LoanDAO;
import io.fourfinanceit.backend.domain.Loan;
import io.fourfinanceit.backend.dto.LoanFullDTO;
import io.fourfinanceit.backend.exception.ResourceNotFoundException;
import io.fourfinanceit.backend.util.LoanConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanDAO loanDAO;

    @Autowired
    private LoanConverter loanConverter;


    @Override
    public LoanFullDTO getLoan(Long id) {
        Loan loan = loanDAO.getById(id);
        if (loan == null) {
            throw new ResourceNotFoundException("Loan with Id: " + id + " not found");
        }
        return loanConverter.convertFullLoan(loan);
    }
}
