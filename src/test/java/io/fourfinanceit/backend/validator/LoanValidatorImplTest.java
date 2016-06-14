package io.fourfinanceit.backend.validator;

import io.fourfinanceit.backend.dto.LoanCreateDTO;
import io.fourfinanceit.backend.exception.LoanException;
import io.fourfinanceit.backend.service.loan.LoanConfigurationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoanValidatorImplTest {

    @Mock
    private LoanConfigurationService loanConfigurationService;

    @InjectMocks
    private LoanValidator loanValidator = new LoanValidatorImpl();

    private final static Integer MAX_TERM = 30;
    private final static BigDecimal MAX_AMOUNT = new BigDecimal(300.00);
    private final static Integer MIN_TERM = 5;
    private final static BigDecimal MIN_AMOUNT = new BigDecimal(10);

    @Before
    public void setUp() throws Exception {
        when(loanConfigurationService.getMaxAmount()).thenReturn(MAX_AMOUNT);
        when(loanConfigurationService.getMaxTerm()).thenReturn(MAX_TERM);
        when(loanConfigurationService.getMinAmount()).thenReturn(MIN_AMOUNT);
        when(loanConfigurationService.getMinTerm()).thenReturn(MIN_TERM);
    }

    @Test(expected = LoanException.class)
    public void shouldThrowExceptionIfAmountEmpty() throws Exception {
        loanValidator.validateLoan(new LoanCreateDTO(30,null));
    }

    @Test(expected = LoanException.class)
    public void shouldThrowExceptionIfTermEmpty() throws Exception {
        loanValidator.validateLoan(new LoanCreateDTO(null,new BigDecimal(50)));
    }

    @Test(expected = LoanException.class)
    public void shouldThrowExceptionIfTermMoreThanMax() throws Exception {
        loanValidator.validateLoan(new LoanCreateDTO(60,new BigDecimal(50)));
    }

    @Test(expected = LoanException.class)
    public void shouldThrowExceptionIfAmountMoreThanMax() throws Exception {
        loanValidator.validateLoan(new LoanCreateDTO(20,new BigDecimal(600)));
    }

    @Test(expected = LoanException.class)
    public void shouldThrowExceptionIfTermLessThanMin() throws Exception {
        loanValidator.validateLoan(new LoanCreateDTO(1,new BigDecimal(300)));
    }

    @Test(expected = LoanException.class)
    public void shouldThrowExceptionIfAmountLessThanMin() throws Exception {
        loanValidator.validateLoan(new LoanCreateDTO(20,new BigDecimal(5.55)));
    }

    @Test
    public void happyPass() throws Exception {
        loanValidator.validateLoan(new LoanCreateDTO(20,new BigDecimal(100.00)));

    }
}