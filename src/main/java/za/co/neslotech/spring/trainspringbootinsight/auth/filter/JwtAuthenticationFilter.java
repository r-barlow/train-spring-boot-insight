package za.co.neslotech.spring.trainspringbootinsight.auth.filter;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import za.co.neslotech.spring.trainspringbootinsight.exception.InvalidAuthorizationRequest;
import za.co.neslotech.spring.trainspringbootinsight.service.JwtAuthenticationService;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String AUTHORIZATION_BEARER = "Bearer";

    private final JwtAuthenticationService jwtService;
    private final UserDetailsService userDetailsService;
    private final HandlerExceptionResolver resolver;

    public JwtAuthenticationFilter(final JwtAuthenticationService jwtService,
                                   final UserDetailsService userDetailsService,
                                   @Qualifier("handlerExceptionResolver") final HandlerExceptionResolver resolver) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(@NonNull final HttpServletRequest request,
                                    @NonNull final HttpServletResponse response, @NonNull final FilterChain filterChain)
    throws ServletException, IOException {

        try {
            String path = request.getRequestURI();
            if (!path.startsWith("/api/v1/auth")) {
                processJwtToken(request);
            }
        } catch (InvalidAuthorizationRequest e) {
            resolver.resolveException(request, response, null, e);
        }

        filterChain.doFilter(request, response);
    }

    private void processJwtToken(final HttpServletRequest request)
    throws InvalidAuthorizationRequest {

        final var authHeader = request.getHeader(HEADER_AUTHORIZATION);

        if (authHeader == null)
            throw new InvalidAuthorizationRequest(HttpStatus.UNAUTHORIZED, "No authorization header present!");

        var authArray = authHeader.split("\\s+");

        if (authArray.length < 2)
            throw new InvalidAuthorizationRequest(HttpStatus.UNAUTHORIZED, "Invalid authorization header!");

        if (!authArray[0].startsWith(AUTHORIZATION_BEARER))
            throw new InvalidAuthorizationRequest(HttpStatus.UNAUTHORIZED, "Authorization method not supported!");

        final var token = authArray[1];
        final var username = jwtService.getUsername(token);

        if (username != null && SecurityContextHolder.getContext()
                .getAuthentication() == null) {

            final var user = userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(token, user)) {

                updateSecurityContext(user, request);
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
