package io.fourfinanceit.backend.service.attempt;

import io.fourfinanceit.backend.database.attempt.AttemptDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;

@Service
@Transactional
public class ApplicationAttemptServiceImpl implements ApplicationAttemptService {

    @Autowired
    private AttemptDAO attemptDAO;

    @Override
    public void increaseLoanAttempt(String ip, Long userId) {
        attemptDAO.increaseAttempt(ip, userId);
    }

    @Override
    public Long getAttemptCountForIp(String ip) {
        return attemptDAO.getAttemptsCount(ip);
    }

    @Override
    public void resetAllAttempts() {
        attemptDAO.resetAllAttempts();
    }

    @Override
    public Long getMaxAttemptCountForUser(Long userId) {
         return attemptDAO.getAttemptsCountForUser(userId).stream()
                 .max(Comparator.naturalOrder())
                 .orElse(0L);
    }
}
