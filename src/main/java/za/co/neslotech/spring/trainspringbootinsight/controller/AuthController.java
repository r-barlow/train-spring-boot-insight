package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.neslotech.spring.trainspringbootinsight.auth.model.AuthenticationResponse;
import za.co.neslotech.spring.trainspringbootinsight.model.User;
import za.co.neslotech.spring.trainspringbootinsight.service.BasicAuthenticationService;
import za.co.neslotech.spring.trainspringbootinsight.service.JwtAuthenticationService;
import za.co.neslotech.spring.trainspringbootinsight.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtAuthenticationService jwtService;
    private final BasicAuthenticationService basicAuthService;
    private final UserService userService;

    public AuthController(final JwtAuthenticationService jwtService, final BasicAuthenticationService basicAuthService,
                          final UserService userService) {
        this.jwtService = jwtService;
        this.basicAuthService = basicAuthService;
        this.userService = userService;
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(final @RequestBody User user) {

        final var newUser = userService.create(user);
        return ResponseEntity.ok(new AuthenticationResponse(jwtService.createToken(newUser)));
    }

    @PostMapping
    @RequestMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate() {

        return ResponseEntity.ok(
                new AuthenticationResponse(
                        jwtService.createToken(userService.findByUsername(basicAuthService.getContextUsername()
                        ))));
    }

}
