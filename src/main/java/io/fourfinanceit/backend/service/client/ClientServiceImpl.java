package io.fourfinanceit.backend.service.client;


import io.fourfinanceit.backend.database.client.ClientDAO;
import io.fourfinanceit.backend.domain.Client;
import io.fourfinanceit.backend.dto.ClientDTO;
import io.fourfinanceit.backend.util.ClientConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl  implements ClientService{

    @Autowired
    ClientDAO clientDAO;

    @Autowired
    ClientConverter clientConverter;

    @Override
    public ClientDTO getClient(Long clientId) {
        Client client = clientDAO.getById(clientId);
        return clientConverter.convertClient(client);
    }
}
