package io.fourfinanceit.rest;


import io.fourfinanceit.backend.dto.LoanFullDTO;
import io.fourfinanceit.backend.service.loan.LoanService;
import io.fourfinanceit.rest.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanRestController {

    @Autowired
    private LoanService loanService;

    @RequestMapping(value="/loan/{id}",	method= RequestMethod.GET)
    public ResponseEntity<LoanFullDTO> getLoanById(@PathVariable Long id) {
        LoanFullDTO loan = loanService.getLoan(id);
        if(loan == null) {
            throw new ResourceNotFoundException();
        }
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }
}
