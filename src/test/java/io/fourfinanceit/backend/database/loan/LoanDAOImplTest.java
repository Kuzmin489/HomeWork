package io.fourfinanceit.backend.database.loan;

import io.fourfinanceit.backend.database.DatabaseHibernateTest;
import io.fourfinanceit.backend.domain.Client;
import io.fourfinanceit.backend.domain.Loan;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

@Transactional
public class LoanDAOImplTest  extends DatabaseHibernateTest {

    @Autowired
    private LoanDAO loanDAO;

    @Test
    public void testCreateLoan() throws Exception {
        Loan loan = createDefaultLoan(1).get(0);
        loanDAO.create(loan);
        assertThat(loan.getId(), is(notNullValue()));

    }

    @Test
    public void testUpdateLoan() throws Exception {
        Loan loan = createDefaultLoan(1).get(0);
        loanDAO.create(loan);
        loanDAO.update(loan.setAmount(new BigDecimal(500.00)));

        Loan loanFromDb = loanDAO.getById(loan.getId()).orElseThrow(Exception::new);

        assertThat(loanFromDb.getAmount().compareTo(loan.getAmount()), is(0));

    }

    @Test
    public void testDeleteLoan() throws Exception {
        List<Loan> loanList = createDefaultLoan(2);
        loanList.forEach(loanDAO::create);
        loanDAO.delete(loanList.get(0));

        Optional<Loan> deletedLoan = loanDAO.getById(loanList.get(0).getId());
        assertThat(deletedLoan.isPresent(), is(false));

    }

    private List<Loan> createDefaultLoan(Integer amount) {
        return IntStream.rangeClosed(1, amount)
                .mapToObj(i -> new Loan()
                .setClient(new Client().setId(0L))
                .setAmount(new BigDecimal(i * 10))
                .setLoanEndDate(LocalDate.now().plusDays(i * 5)))
                .collect(Collectors.toList());
    }


}