package za.co.neslotech.spring.trainspringbootinsight.auth.filter;

import io.jsonwebtoken.io.Decoders;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import za.co.neslotech.spring.trainspringbootinsight.service.BasicAuthenticationService;

import java.io.IOException;

@Component
public class BasicAuthenticationFilter extends OncePerRequestFilter {

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_BASIC = "Basic";
    public static final String SEPARATOR = ":";
    private final BasicAuthenticationService basicAuthService;
    private final UserDetailsService userDetailsService;

    public BasicAuthenticationFilter(final BasicAuthenticationService basicAuthService,
                                     final UserDetailsService userDetailsService) {
        this.basicAuthService = basicAuthService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull final HttpServletRequest request,
                                    @NonNull final HttpServletResponse response,
                                    final FilterChain filterChain)
    throws ServletException, IOException {

        processBasicAuth(request);
        filterChain.doFilter(request, response);
    }

    private void processBasicAuth(final HttpServletRequest request) {

        final var authHeader = request.getHeader(HEADER_AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith(AUTHORIZATION_BASIC)) {

            var data = authHeader.substring(AUTHORIZATION_BASIC.length() + 1);

            if (!data.isEmpty()) {

                data = new String(Decoders.BASE64.decode(data));
                final var dataArray = data.split(SEPARATOR);

                if (dataArray.length == 2) {

                    final var username = dataArray[0];
                    final var password = dataArray[1];

                    basicAuthService.authenticate(username, password);
                    final var user = userDetailsService.loadUserByUsername(username);
                    updateSecurityContext(user, request);
                }
            }
        }
    }

    private void updateSecurityContext(final UserDetails user, final HttpServletRequest request) {
        final var authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext()
                .setAuthentication(authToken);
    }
}
