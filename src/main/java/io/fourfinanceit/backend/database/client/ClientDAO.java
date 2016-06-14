package io.fourfinanceit.backend.database.client;


import io.fourfinanceit.backend.database.CRUDOperationDAO;
import io.fourfinanceit.backend.domain.Client;

import java.util.Optional;

public interface ClientDAO extends CRUDOperationDAO<Client, Long> {
    Optional<Client> findByUserName(String userName);
}
