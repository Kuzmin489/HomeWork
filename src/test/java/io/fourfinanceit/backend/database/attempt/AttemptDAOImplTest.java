package io.fourfinanceit.backend.database.attempt;

import io.fourfinanceit.backend.database.DatabaseHibernateTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@Transactional
public class AttemptDAOImplTest extends DatabaseHibernateTest {

    private final static String IP_ONE = "10.10.10.100";
    private final static String IP_TWO = "10.10.10.101";
    private final static Long USER_ID = 1L;

    @Autowired
    private AttemptDAO attemptDAO;

    @Test
    @Ignore
    public void shouldReturnZeroIfNoAttemptsDone() throws Exception {
        Long attemptCountForIp = attemptDAO.getAttemptsCount(IP_ONE);
        assertThat(attemptCountForIp, is(0L));
    }

    @Test
    @Ignore
    public void shouldIncreaseAttempt() throws Exception {
        Long attemptCountForIp = attemptDAO.getAttemptsCount(IP_ONE);
        assertThat(attemptCountForIp, is(0L));

        attemptDAO.increaseAttempt(IP_ONE, USER_ID);
        assertThat(attemptDAO.getAttemptsCount(IP_ONE), is(1L));

    }

    @Test
    public void shouldGetAttemptForUser() throws Exception {
        List<Long> attemptCountsForUser = attemptDAO.getAttemptsCountForUser(USER_ID);
        assertThat(attemptCountsForUser.size(), is(0));

        attemptDAO.increaseAttempt(IP_ONE, USER_ID);
        attemptDAO.increaseAttempt(IP_TWO, USER_ID);

        List<Long> attemptCountsForUserAfterUpdate = attemptDAO.getAttemptsCountForUser(USER_ID);
        assertThat(attemptCountsForUserAfterUpdate.size(), is(2));
    }

    @Test
    public void shouldResetAllAttempts() throws Exception {
        attemptDAO.increaseAttempt(IP_ONE, USER_ID);
        attemptDAO.increaseAttempt(IP_TWO, USER_ID);

        assertThat(attemptDAO.getAttemptsCount(IP_ONE), is(1L));
        assertThat(attemptDAO.getAttemptsCount(IP_TWO), is(1L));

        attemptDAO.resetAllAttempts();

        assertThat(attemptDAO.getAttemptsCount(IP_ONE), is(0L));
        assertThat(attemptDAO.getAttemptsCount(IP_TWO), is(0L));
    }


}