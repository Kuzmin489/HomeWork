package io.fourfinanceit.rest;

import io.fourfinanceit.backend.dto.ClientDTO;
import io.fourfinanceit.backend.exception.ClientNotFoundException;
import io.fourfinanceit.backend.service.client.ClientService;
import io.fourfinanceit.backend.service.security.SecurityService;
import io.fourfinanceit.rest.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private SecurityService securityService;


    //Authorization header Authorization: Basic BaseEncode(login:password)
    @RequestMapping(value="/",	method= RequestMethod.GET)
    public ResponseEntity<ClientDTO> getClientInfo(HttpServletRequest req) {
        try {
            ClientDTO clientDTO = clientService.getClient(securityService.getCurrentUserId());
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        } catch (ClientNotFoundException e) {
            throw new ResourceNotFoundException("Can not get client info", e);
        }
    }
}
