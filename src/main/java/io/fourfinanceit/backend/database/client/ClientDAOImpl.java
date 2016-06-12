package io.fourfinanceit.backend.database.client;

import io.fourfinanceit.backend.database.CRUDOperationDAOImpl;
import io.fourfinanceit.backend.domain.Client;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ClientDAOImpl extends CRUDOperationDAOImpl<Client, Long> implements ClientDAO {

    @Override
    public Client findByUserName(String userName) {

        return (Client) getCurrentSession().createCriteria(Client.class)
                .add(Restrictions.eq("login", userName))
                .uniqueResult();
    }
}
