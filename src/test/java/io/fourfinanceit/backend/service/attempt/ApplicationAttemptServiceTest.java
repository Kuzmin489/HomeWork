package io.fourfinanceit.backend.service.attempt;

import io.fourfinanceit.backend.database.attempt.AttemptDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationAttemptServiceTest {

    @Mock
    private AttemptDAO attemptDAO;

    @InjectMocks
    private ApplicationAttemptService applicationAttemptService = new ApplicationAttemptServiceImpl();

    private final static String IP = "10.10.10.100";
    private final static Long USER_ID = 1L;

    @Test
    public void shouldInvokeDAOWhenIncreaseAttempt() throws Exception {
        applicationAttemptService.increaseLoanAttempt(IP, USER_ID);
        verify(attemptDAO).increaseAttempt(IP,USER_ID);
    }

    @Test
    public void shouldInvokeDAOWhenGetAttemptForIp() throws Exception {
        when(attemptDAO.getAttemptsCount(IP)).thenReturn(1L);
        Long attempt = applicationAttemptService.getAttemptCountForIp(IP);
        assertThat(attempt, is(1L));
    }

}