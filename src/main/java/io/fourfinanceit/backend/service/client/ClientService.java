package io.fourfinanceit.backend.service.client;

import io.fourfinanceit.backend.dto.ClientDTO;
import io.fourfinanceit.backend.exception.ClientNotFoundException;

public interface ClientService {
    ClientDTO getClient(Long clientId) throws ClientNotFoundException;
}
