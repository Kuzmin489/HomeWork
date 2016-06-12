package io.fourfinanceit.backend.database.client;

import io.fourfinanceit.backend.database.DatabaseHibernateTest;
import io.fourfinanceit.backend.domain.Client;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

@Transactional
public class ClientDAOTest extends DatabaseHibernateTest {

    @Autowired
    private ClientDAO clientDAO;

    @Test
    public void testCreateWithoutLoans() throws Exception {
        Client client = createDefaultClients(1).get(0);
        clientDAO.create(client);

        assertThat(client.getId(), is(notNullValue()));
    }

    @Test
    public void testCreateUser() throws Exception {
        Client client = createDefaultClients(1).get(0);

        clientDAO.create(client);
        assertThat(client.getId(), is(notNullValue()));
    }

    @Test
    public void testRemoveUser() throws Exception {
        List<Client> clients  = createDefaultClients(3);
        clients.forEach(e -> clientDAO.create(e));
        clientDAO.delete(clients.get(0));

        Boolean clientExist = clientDAO.getAll()
                .stream()
                .filter( e -> e.getId().equals(clients.get(0).getId()))
                .findAny().isPresent();
        assertThat(clientExist, is(false));

    }

    @Test
    public void testFindByUserName() throws Exception {
        List<Client> clients  = createDefaultClients(2);
        clients.forEach(e -> clientDAO.create(e));

        Client client = clientDAO.findByUserName(clients.get(0).getLogin());
        assertThat(client, is(notNullValue()));
        assertThat(client.getId(), is(notNullValue()));

    }

    private List<Client> createDefaultClients(Integer amount) {
        return IntStream.rangeClosed(1, amount)
                .mapToObj( i -> new Client()
                .setLogin("login" + i)
                .setPassword("password" + i)
                .setFirstName("Name" + i)
                .setLastName("LastName" + i)
                .setAdmin(false))
                .collect(Collectors.toList());
    }
}