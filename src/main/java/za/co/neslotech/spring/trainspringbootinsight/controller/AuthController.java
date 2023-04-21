package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import za.co.neslotech.spring.trainspringbootinsight.auth.model.AuthenticationResponse;
import za.co.neslotech.spring.trainspringbootinsight.auth.model.AuthorizationRequest;
import za.co.neslotech.spring.trainspringbootinsight.model.User;
import za.co.neslotech.spring.trainspringbootinsight.service.JwtAuthenticationService;
import za.co.neslotech.spring.trainspringbootinsight.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtAuthenticationService jwtService;
    private final UserService userService;

    public AuthController(final JwtAuthenticationService jwtService, final UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(final @RequestBody User user) {

        final var newUser = userService.create(user);
        return ResponseEntity.ok(new AuthenticationResponse(jwtService.createToken(newUser)));
    }

    @PostMapping
    @RequestMapping("/authorize")
    public ResponseEntity<AuthenticationResponse> authorize(final @RequestBody AuthorizationRequest request) {

        final var user = userService.findByUsername(request.getUsername());
        if (!userService.verify(user, request.getPassword()))
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);

        return ResponseEntity.ok(
                new AuthenticationResponse(
                        jwtService.createToken(user)));
    }

}
