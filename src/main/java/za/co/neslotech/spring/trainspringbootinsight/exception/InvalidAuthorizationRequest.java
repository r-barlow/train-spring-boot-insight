package za.co.neslotech.spring.trainspringbootinsight.exception;

import org.springframework.http.HttpStatus;

public class InvalidAuthorizationRequest extends Exception {

    private final HttpStatus status;

    public InvalidAuthorizationRequest(final HttpStatus status, final String message) {
        super(String.format("%d %s: %s", status.value(), status.getReasonPhrase(), message));
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
