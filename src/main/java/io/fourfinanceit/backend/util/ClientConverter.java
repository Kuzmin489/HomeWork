package io.fourfinanceit.backend.util;

import io.fourfinanceit.backend.domain.Client;
import io.fourfinanceit.backend.dto.ClientDTO;
import io.fourfinanceit.backend.dto.LoanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientConverter {

    @Autowired
    private LoanConverter loanConverter;

    public ClientDTO convertClient(Client client) {
        List<LoanDTO> loanDTOList = new ArrayList<>();
        if (client.getLoanList() != null) {
            loanDTOList = client.getLoanList().stream()
                    .map(e -> loanConverter.convertLoan(e))
                    .collect(Collectors.toList());
        }
        return new ClientDTO().setFirstName(client.getFirstName())
                .setLastName(client.getLastName())
                .setLogin(client.getLogin())
                .setLoanList(loanDTOList);
    }
}
