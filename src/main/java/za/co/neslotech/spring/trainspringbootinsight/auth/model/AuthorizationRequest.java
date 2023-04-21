package za.co.neslotech.spring.trainspringbootinsight.auth.model;

public class AuthorizationRequest {

    private String Username;
    private String Password;

    public String getUsername() {
        return Username;
    }

    public void setUsername(final String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(final String password) {
        Password = password;
    }
}
