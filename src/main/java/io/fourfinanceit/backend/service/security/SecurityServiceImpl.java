package io.fourfinanceit.backend.service.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public Long getCurrentUserId() {

        UserSecurityEntity user = (UserSecurityEntity) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();

        return user.getUserId();
    }
}
