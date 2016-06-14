package io.fourfinanceit.backend.service.attempt;

public interface ApplicationAttemptService {
    void increaseLoanAttempt(String ip, Long userId);
    Long getAttemptCountForIp(String ip);
    void resetAllAttempts();
    Long getMaxAttemptCountForUser(Long userId);
}
