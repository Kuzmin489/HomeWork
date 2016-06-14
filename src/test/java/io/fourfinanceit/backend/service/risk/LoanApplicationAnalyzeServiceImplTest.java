package io.fourfinanceit.backend.service.risk;

import io.fourfinanceit.backend.domain.Client;
import io.fourfinanceit.backend.domain.Loan;
import io.fourfinanceit.backend.service.attempt.ApplicationAttemptService;
import io.fourfinanceit.backend.service.loan.LoanConfigurationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoanApplicationAnalyzeServiceImplTest {

    @Mock
    private ApplicationAttemptService applicationAttemptService;

    @Mock
    private RiskAnalyzeService riskAnalyzeService;

    @Mock
    private LoanConfigurationService loanConfigurationService;

    @InjectMocks
    private LoanApplicationService loanApplicationService = new LoanApplicationAnalyzeServiceImpl();

    private final static Long USER_ID = 1L;

    @Before
    public void setUp() throws Exception {
        when(loanConfigurationService.getMaxAttempts()).thenReturn(3L);
        when(loanConfigurationService.getMaxAmount()).thenReturn(new BigDecimal(300));

    }

    @Test
    public void shouldInvokeAnalyzeIfReachedMaxIpCount() throws Exception {
        when(applicationAttemptService.getMaxAttemptCountForUser(USER_ID)).thenReturn(4L);
        Loan loan = new Loan().setAmount(new BigDecimal(200)).setClient(new Client().setId(USER_ID));
        loanApplicationService.analyzeLoan(loan);

        verify(riskAnalyzeService).analyzeRisk(loan);
    }



    @Test
    public void shouldInvokeAnalyzeIfApplyWithMaxAmount() throws Exception {
        when(applicationAttemptService.getMaxAttemptCountForUser(USER_ID)).thenReturn(0L);
        Loan loan = new Loan().setAmount(new BigDecimal(300)).setClient(new Client().setId(USER_ID));
        loanApplicationService.analyzeLoan(loan);

        verify(riskAnalyzeService).analyzeRisk(loan);
    }

    @Test
    public void shouldNotInvokeAnalyzeIfAllOk() throws Exception {
        when(applicationAttemptService.getMaxAttemptCountForUser(USER_ID)).thenReturn(0L);
        Loan loan = new Loan().setAmount(new BigDecimal(250)).setClient(new Client().setId(USER_ID));
        loanApplicationService.analyzeLoan(loan);

        verify(riskAnalyzeService, never()).analyzeRisk(loan);

    }
}