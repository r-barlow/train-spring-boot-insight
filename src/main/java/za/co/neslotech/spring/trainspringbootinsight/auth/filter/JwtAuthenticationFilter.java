package za.co.neslotech.spring.trainspringbootinsight.auth.filter;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import za.co.neslotech.spring.trainspringbootinsight.service.JwtAuthenticationService;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_BEARER = "Bearer";

    private final JwtAuthenticationService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(final JwtAuthenticationService jwtService,
                                   final UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull final HttpServletRequest request,
                                    @NonNull final HttpServletResponse response, @NonNull final FilterChain filterChain)
    throws ServletException, IOException {

        processJwtToken(request);
        filterChain.doFilter(request, response);
    }

    private void processJwtToken(final HttpServletRequest request) {

        final var authHeader = request.getHeader(HEADER_AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith(AUTHORIZATION_BEARER)) {

            final var token = authHeader.substring(AUTHORIZATION_BEARER.length() + 1);
            final var username = jwtService.getUsername(token);

            if (username != null && SecurityContextHolder.getContext()
                    .getAuthentication() == null) {

                final var user = userDetailsService.loadUserByUsername(username);

                if (jwtService.isTokenValid(token, user)) {

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
