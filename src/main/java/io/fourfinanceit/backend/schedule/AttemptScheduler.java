package io.fourfinanceit.backend.schedule;

import io.fourfinanceit.backend.service.attempt.ApplicationAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AttemptScheduler {

    @Autowired
    private ApplicationAttemptService applicationAttemptService;

    //every day at 00:00am
    @Scheduled(cron = "0 0 0 * * *")
    public void resetAttempts() {
        applicationAttemptService.resetAllAttempts();
    }
}
