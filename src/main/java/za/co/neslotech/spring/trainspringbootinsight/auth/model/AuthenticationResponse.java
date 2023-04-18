package za.co.neslotech.spring.trainspringbootinsight.auth.model;

public class AuthenticationResponse {

    private String token;

    public AuthenticationResponse(final String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }
}
