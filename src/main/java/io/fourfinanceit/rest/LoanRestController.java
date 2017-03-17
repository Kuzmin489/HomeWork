package io.fourfinanceit.rest;


import io.fourfinanceit.backend.dto.LoanCreateDTO;
import io.fourfinanceit.backend.dto.LoanFullDTO;
import io.fourfinanceit.backend.exception.LoanException;
import io.fourfinanceit.backend.service.attempt.ApplicationAttemptService;
import io.fourfinanceit.backend.service.loan.LoanService;
import io.fourfinanceit.backend.service.security.SecurityService;
import io.fourfinanceit.rest.exception.ResourceNotFoundException;
import io.fourfinanceit.rest.exception.ServiceErrorException;
import io.fourfinanceit.rest.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoanRestController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ApplicationAttemptService applicationAttemptService;

    @RequestMapping(value="/loan/{id}",	method= RequestMethod.GET)
    public ResponseEntity<LoanFullDTO> getLoanInfoById(@PathVariable Long id) {
        try {
            LoanFullDTO loan = loanService.getLoanInfo(id);
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } catch (LoanException e) {
            throw new ResourceNotFoundException();
        }
        
        
        
        int a = 5;
    }

    //Json Example: {"termOfDays":20,"amount":30}
    @RequestMapping(value="/loan",	method= RequestMethod.POST)
    public ResponseEntity<LoanFullDTO> createLoan(@RequestBody LoanCreateDTO loanCreateDTO,
                                                  HttpServletRequest req) {
        try {
            Long userId = securityService.getCurrentUserId();
            applicationAttemptService.increaseLoanAttempt(req.getRemoteAddr(),userId);
            LoanFullDTO loan = loanService.createLoan(loanCreateDTO, userId);
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } catch (LoanException e) {
            switch (e.getLoanError()) {
                case VALIDATION_FAILS:
                    throw new ValidationException(e.getLoanError().toString(),e);
                case NOT_FOUND:
                    throw new ResourceNotFoundException(e.getLoanError().toString(),e);
                default:
                    throw new ServiceErrorException(e.getLoanError().toString(),e);
            }
        }
    }
}
