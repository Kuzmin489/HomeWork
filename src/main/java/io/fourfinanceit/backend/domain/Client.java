package io.fourfinanceit.backend.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    @Cascade(CascadeType.ALL)
    private List<Loan> loanList;

    @Type(type="yes_no")
    private	Boolean	admin;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Client setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Client setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public String getLogin() {
        return login;
    }

    public Client setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Client setPassword(String password) {
        this.password = password;
        return this;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public Client setAdmin(Boolean admin) {
        this.admin = admin;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Client setId(Long id) {
        this.id = id;
        return this;
    }

}
