package za.co.neslotech.spring.trainspringbootinsight.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import za.co.neslotech.spring.trainspringbootinsight.exception.InvalidAuthorizationRequest;
import za.co.neslotech.spring.trainspringbootinsight.exception.RestException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(final EntityNotFoundException exception,
                                                          final WebRequest request) {

        return handleExceptionInternal(exception,
                                       new RestException(exception.getMessage(), HttpStatus.NOT_FOUND.value()),
                                       new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            @NonNull final HttpRequestMethodNotSupportedException exception,
            @NonNull final HttpHeaders headers, @NonNull final HttpStatusCode status,
            @NonNull final WebRequest request) {

        return handleExceptionInternal(exception, new RestException(exception.getMessage(), status.value()), headers,
                                       status, request);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<Object> handleHttpClientErrorException(@NonNull final HttpClientErrorException exception,
                                                                    @NonNull final WebRequest request) {

        return handleExceptionInternal(exception, new RestException(exception.getMessage(), exception.getStatusCode()
                .value()), new HttpHeaders(), exception.getStatusCode(), request);
    }

    @ExceptionHandler(InvalidAuthorizationRequest.class)
    protected ResponseEntity<Object> handleHttpClientErrorException(
            @NonNull final InvalidAuthorizationRequest exception,
            @NonNull final WebRequest request) {

        return handleExceptionInternal(exception, new RestException(exception.getMessage(), exception.getStatus()
                .value()), new HttpHeaders(), exception.getStatus(), request);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(
            @NonNull final UsernameNotFoundException exception,
            @NonNull final WebRequest request) {

        return handleExceptionInternal(exception,
                                       new RestException(exception.getMessage(), HttpStatus.NOT_FOUND.value()),
                                       new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
