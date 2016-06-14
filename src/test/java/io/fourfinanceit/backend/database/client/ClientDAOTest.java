package io.fourfinanceit.backend.database.client;

import io.fourfinanceit.backend.database.DatabaseHibernateTest;
import io.fourfinanceit.backend.domain.Client;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

@Transactional
@Rollback
public class ClientDAOTest extends DatabaseHibernateTest {

    @Autowired
    private ClientDAO clientDAO;

    @Test
    public void testCreateUser() throws Exception {
        Client client = createDefaultClients(1).get(0);

        clientDAO.create(client);
        assertThat(client.getId(), is(notNullValue()));
    }

    @Test
    public void testUpdateUser() throws Exception {
        Client client = createDefaultClients(1).get(0);
        clientDAO.create(client);

        clientDAO.update(client.setFirstName("Renamed"));

        Client clientFromDb = clientDAO.getById(client.getId()).orElseThrow(Exception::new);
        assertThat(clientFromDb.getFirstName(), is("Renamed"));

    }

    @Test
    public void testRemoveUser() throws Exception {
        List<Client> clients  = createDefaultClients(3);
        clients.forEach(e -> clientDAO.create(e));
        clientDAO.delete(clients.get(0));

        Optional<Client> deletedClient = clientDAO.getById(clients.get(0).getId());
        assertThat(deletedClient.isPresent(), is(false));

    }

    @Test
    public void testFindByUserName() throws Exception {
        List<Client> clients  = createDefaultClients(2);
        clients.forEach(e -> clientDAO.create(e));

        Client client = clientDAO.findByUserName(clients.get(0).getLogin()).orElseThrow(Exception::new);
        assertThat(client, is(notNullValue()));
        assertThat(client.getId(), is(notNullValue()));

    }

    private List<Client> createDefaultClients(Integer amount) {
        return IntStream.rangeClosed(1, amount)
                .mapToObj( i -> new Client()
                .setLogin("test" + i)
                .setPassword("password" + i)
                .setFirstName("Name" + i)
                .setLastName("LastName" + i)
                .setAdmin(false))
                .collect(Collectors.toList());
    }
}