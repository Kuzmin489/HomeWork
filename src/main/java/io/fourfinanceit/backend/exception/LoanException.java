package io.fourfinanceit.backend.exception;


public class LoanException extends Exception {
    private LoanError loanError;

    public LoanException(LoanError loanError) {
        super();
        this.loanError = loanError;
    }

    public LoanError getLoanError() {
        return loanError;
    }
}
