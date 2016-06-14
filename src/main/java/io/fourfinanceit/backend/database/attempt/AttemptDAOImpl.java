package io.fourfinanceit.backend.database.attempt;

import io.fourfinanceit.backend.database.CRUDOperationDAOImpl;
import io.fourfinanceit.backend.domain.Attempt;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class AttemptDAOImpl  extends CRUDOperationDAOImpl<Attempt, String> implements AttemptDAO {

    @Override
    public void increaseAttempt(String ip, Long userId) {
        List<Attempt> oldAttemptList = getCurrentSession().createCriteria(Attempt.class)
                .add(Restrictions.eq("ip",ip)).add(Restrictions.eq("userId",userId)).list();
        if(oldAttemptList.size() > 0) {
            Long attemptCount = oldAttemptList.get(0).getAttemptCount();
            super.update(oldAttemptList.get(0).setAttemptCount(++attemptCount));
        } else {
            super.create(new Attempt().setIp(ip).setUserId(userId).setAttemptCount(1L));
        }
    }

    @Override
    public List<Long> getAttemptsCountForUser(Long userId) {
        List<Attempt> attemptList = getCurrentSession().createCriteria(Attempt.class)
                .add(Restrictions.eq("userId",userId)).list();
        if(attemptList == null) {
            return Collections.emptyList();
        } else {
            return attemptList.stream()
                    .map(Attempt::getAttemptCount)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Long getAttemptsCount(String ip) {
        List<Attempt> oldAttemptList = getCurrentSession().createCriteria(Attempt.class)
                .add(Restrictions.eq("ip",ip)).list();
        if(oldAttemptList.size() > 0) {
            return oldAttemptList.get(0).getAttemptCount();
        } else {
            return 0L;
        }
    }


    @Override
    public void resetAllAttempts() {
        Query query = getCurrentSession().createQuery("DELETE FROM Attempt");
        query.executeUpdate();
    }


}
