package io.fourfinanceit.backend.database.loan;

import io.fourfinanceit.backend.database.CRUDOperationDAOImpl;
import io.fourfinanceit.backend.domain.Loan;
import org.springframework.stereotype.Component;

@Component
public class LoanDAOImpl extends CRUDOperationDAOImpl<Loan, Long> implements LoanDAO{

}
