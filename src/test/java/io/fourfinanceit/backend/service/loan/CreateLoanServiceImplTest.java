package io.fourfinanceit.backend.service.loan;

import io.fourfinanceit.backend.database.loan.LoanDAO;
import io.fourfinanceit.backend.domain.Loan;
import io.fourfinanceit.backend.dto.LoanCreateDTO;
import io.fourfinanceit.backend.exception.AnalyzerException;
import io.fourfinanceit.backend.exception.LoanError;
import io.fourfinanceit.backend.exception.LoanException;
import io.fourfinanceit.backend.service.risk.LoanApplicationService;
import io.fourfinanceit.backend.util.LoanConverter;
import io.fourfinanceit.backend.validator.LoanValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CreateLoanServiceImplTest {

    @Mock
    private LoanValidator loanValidator;

    @Mock
    private LoanApplicationService loanApplicationService;

    @Mock
    private LoanConverter loanConverter = new LoanConverter();

    @Mock
    LoanDAO loanDAO;

    private static final Long USER_ID = 1L;
    private static final Integer TERM = 30;
    private static final BigDecimal AMOUNT = new BigDecimal(300.00);

    @InjectMocks
    private LoanService loanService = new LoanServiceImpl();

    @Test
    public void shouldValidateLoanBeforeCreate() throws Exception {
        LoanCreateDTO loanDTO = getDefaultLoan();
        loanService.createLoan(loanDTO,USER_ID);

        verify(loanValidator).validateLoan(loanDTO);
    }

    @Test(expected = LoanException.class)
    public void shouldThrowExceptionIfValidationFails() throws Exception {
        LoanCreateDTO loanDTO = getDefaultLoan();
        doThrow(new LoanException(LoanError.VALIDATION_FAILS))
                .when(loanValidator).validateLoan(loanDTO);
        loanService.createLoan(loanDTO,USER_ID);
    }

    @Test
    public void shouldPerformRiskAnalyze() throws Exception {
        LoanCreateDTO loanDTO = getDefaultLoan();
        Loan loan = new Loan();
        when(loanConverter.convertDTOToDomain(loanDTO, USER_ID)).thenReturn(loan);

        loanService.createLoan(loanDTO,USER_ID);

        verify(loanApplicationService).analyzeLoan(loan);
    }

    @Test
    public void shouldThrowRejectExceptionIfAnalyzeThrowException() throws Exception {
        LoanCreateDTO loanDTO = getDefaultLoan();
        doThrow(new AnalyzerException()).when(loanApplicationService).analyzeLoan(any());
        try {
            loanService.createLoan(loanDTO, USER_ID);
            fail("No Exception thrown");
        } catch (LoanException e ){
            assertEquals(LoanError.REJECT, e.getLoanError());
        }
    }

    @Test
    public void shouldPersistLoanIfValidationAndAnalyzePass() throws Exception {
        LoanCreateDTO loanCreateDTO = getDefaultLoan();
        Loan loan = new Loan();
        when(loanConverter.convertDTOToDomain(loanCreateDTO, USER_ID)).thenReturn(loan);
        loanService.createLoan(loanCreateDTO, USER_ID);

        verify(loanDAO).create(loan);
    }

    @Test
    public void shouldThrowExceptionIfPersistFails() throws Exception {
        doThrow(new RuntimeException()).when(loanDAO).create(any());
        try {
            loanService.createLoan(new LoanCreateDTO(TERM, AMOUNT),USER_ID);
            fail("No Exception thrown");
        } catch (LoanException e) {
            assertEquals(LoanError.DB_ERROR, e.getLoanError());
        }
    }

    @Test
    public void shouldReturnLoanFullDTO() throws Exception {


    }

    private LoanCreateDTO getDefaultLoan() {
        return new LoanCreateDTO(TERM, AMOUNT );
    }
}