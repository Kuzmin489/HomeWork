package io.fourfinanceit.backend.validator;

import io.fourfinanceit.backend.dto.LoanCreateDTO;
import io.fourfinanceit.backend.exception.LoanError;
import io.fourfinanceit.backend.exception.LoanException;
import io.fourfinanceit.backend.service.loan.LoanConfigurationService;
import io.fourfinanceit.backend.service.loan.LoanServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LoanValidatorImpl implements LoanValidator{

    @Autowired
    private LoanConfigurationService loanConfigurationService;

    private final static Logger LOG = Logger.getLogger(LoanValidatorImpl.class);

    @Override
    public void validateLoan(LoanCreateDTO loanCreateDTO) throws LoanException {
        validateTerm(loanCreateDTO.getTermOfDays());
        validateAmount(loanCreateDTO.getAmount());
    }

    private void validateTerm(Integer term) throws LoanException {
        if(term == null) throwValidationException();
        if(loanConfigurationService.getMaxTerm() < term || term < loanConfigurationService.getMinTerm()) {
            throwValidationException();
        }

    }

    private void validateAmount(BigDecimal amount) throws LoanException {
        if(amount == null) throwValidationException();
        if(amount.compareTo(loanConfigurationService.getMaxAmount()) == 1) {
            throwValidationException();
        }
        if(amount.compareTo(loanConfigurationService.getMinAmount()) == -1) {
            throwValidationException();
        }
    }

    private void throwValidationException() throws LoanException {
        LOG.error("validation fails");
        throw new LoanException(LoanError.VALIDATION_FAILS);
    }
}
