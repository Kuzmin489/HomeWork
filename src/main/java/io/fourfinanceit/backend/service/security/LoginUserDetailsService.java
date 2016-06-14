package io.fourfinanceit.backend.service.security;

import io.fourfinanceit.backend.database.client.ClientDAO;
import io.fourfinanceit.backend.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    private ClientDAO clientDAO;

    @Transactional()
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientDAO.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        List<GrantedAuthority> authorities = new ArrayList<>();
        if(client.getAdmin()) {
            authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        }
        return new UserSecurityEntity(client.getLogin(), client.getPassword(), authorities, client.getId());
    }
}
