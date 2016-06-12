package io.fourfinanceit.rest;

import io.fourfinanceit.backend.dto.ClientDTO;
import io.fourfinanceit.backend.service.client.ClientService;
import io.fourfinanceit.backend.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value="/",	method= RequestMethod.GET)
    public ResponseEntity<ClientDTO> getClientInfo() {
        ClientDTO clientDTO = clientService.getClient(securityService.getCurrentUserId());
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }
}
