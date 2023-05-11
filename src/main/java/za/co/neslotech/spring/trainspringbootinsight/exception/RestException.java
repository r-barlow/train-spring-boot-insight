package za.co.neslotech.spring.trainspringbootinsight.exception;

public class RestException {

    private final String message;
    private final int statusCode;

    public RestException(final String message, final int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
