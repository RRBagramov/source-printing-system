package ru.kpfu.printer.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ru.kpfu.printer.services.AuthService;

/**
 * 01.05.2018
 *
 * @author Robert Bagramov.
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public String getCurrentUserRole(Authentication authentication) {
        SimpleGrantedAuthority authority = (SimpleGrantedAuthority) authentication
                .getAuthorities().toArray()[0];

        return authority.getAuthority();
    }
}
