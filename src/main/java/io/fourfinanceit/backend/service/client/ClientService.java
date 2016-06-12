package io.fourfinanceit.backend.service.client;

import io.fourfinanceit.backend.dto.ClientDTO;

public interface ClientService {
    ClientDTO getClient(Long clientId);
}
