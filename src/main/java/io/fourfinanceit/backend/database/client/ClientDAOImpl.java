package io.fourfinanceit.backend.database.client;

import io.fourfinanceit.backend.database.CRUDOperationDAOImpl;
import io.fourfinanceit.backend.domain.Client;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class ClientDAOImpl extends CRUDOperationDAOImpl<Client, Long> implements ClientDAO {

    @Override
    public Optional<Client> findByUserName(String userName) {

        List<Client> clientList =  getCurrentSession().createCriteria(Client.class)
                .add(Restrictions.eq("login", userName)).list();
        if(clientList.size() > 0) {
            return Optional.of(clientList.get(0));
        } else {
            return Optional.empty();
        }
    }
}
