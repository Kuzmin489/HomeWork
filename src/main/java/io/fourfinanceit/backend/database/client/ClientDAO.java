package io.fourfinanceit.backend.database.client;


import io.fourfinanceit.backend.database.CRUDOperationDAO;
import io.fourfinanceit.backend.domain.Client;

public interface ClientDAO extends CRUDOperationDAO<Client, Long> {
    Client findByUserName(String userName);
}
