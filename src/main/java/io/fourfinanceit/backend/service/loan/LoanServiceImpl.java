package io.fourfinanceit.backend.service.loan;

import io.fourfinanceit.backend.database.loan.LoanDAO;
import io.fourfinanceit.backend.domain.Loan;
import io.fourfinanceit.backend.dto.LoanCreateDTO;
import io.fourfinanceit.backend.dto.LoanFullDTO;
import io.fourfinanceit.backend.exception.AnalyzerException;
import io.fourfinanceit.backend.exception.LoanError;
import io.fourfinanceit.backend.exception.LoanException;
import io.fourfinanceit.backend.service.risk.LoanApplicationService;
import io.fourfinanceit.backend.validator.LoanValidator;
import io.fourfinanceit.backend.util.LoanConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanDAO loanDAO;

    @Autowired
    private LoanConverter loanConverter;

    @Autowired
    private LoanValidator loanValidator;

    @Autowired
    private LoanApplicationService loanApplicationService;

    private final static Logger LOG = Logger.getLogger(LoanServiceImpl.class);


    @Override
    public LoanFullDTO getLoanInfo(Long id) throws LoanException {
        Loan loan = loanDAO.getById(id)
                .orElseThrow(() -> new LoanException(LoanError.NOT_FOUND));
        return loanConverter.convertFullLoan(loan);
    }

    @Override
    public LoanFullDTO createLoan(LoanCreateDTO loanCreateDTO, Long userId) throws LoanException {
        loanValidator.validateLoan(loanCreateDTO);
        Loan loan = loanConverter.convertDTOToDomain(loanCreateDTO, userId);
        try {
            loanApplicationService.analyzeLoan(loan);
            loanDAO.create(loan);
        } catch (AnalyzerException e) {
            LOG.debug("Analyzer error", e);
            throw new LoanException(LoanError.REJECT);
        } catch (Exception e ) {
            LOG.debug("Service error", e);
            throw new LoanException(LoanError.DB_ERROR);
        }
        return loanConverter.convertFullLoan(loan);
    }
}
