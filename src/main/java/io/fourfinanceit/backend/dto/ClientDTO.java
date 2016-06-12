package io.fourfinanceit.backend.dto;


import java.util.List;

public class ClientDTO {

    private String login;
    private String firstName;
    private String lastName;
    private List<LoanDTO> loanList;

    public String getLogin() {
        return login;
    }

    public ClientDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ClientDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ClientDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<LoanDTO> getLoanList() {
        return loanList;
    }

    public ClientDTO setLoanList(List<LoanDTO> loanList) {
        this.loanList = loanList;
        return this;
    }
}
