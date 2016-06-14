package io.fourfinanceit.backend.database.attempt;


import io.fourfinanceit.backend.database.CRUDOperationDAO;
import io.fourfinanceit.backend.domain.Attempt;

import java.util.List;

public interface AttemptDAO extends CRUDOperationDAO<Attempt, String> {
    void increaseAttempt(String ip, Long userId);
    void resetAllAttempts();
    Long getAttemptsCount(String ip);
    List<Long> getAttemptsCountForUser(Long userId);
}
