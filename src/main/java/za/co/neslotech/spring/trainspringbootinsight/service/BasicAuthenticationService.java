package za.co.neslotech.spring.trainspringbootinsight.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class BasicAuthenticationService {

    private final AuthenticationManager authenticationManager;

    public BasicAuthenticationService(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void authenticate(final String username, final String password) {

        if (username != null && password != null && SecurityContextHolder.getContext()
                .getAuthentication() == null) {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        }
    }

    public String getContextUsername() {

        final var principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
