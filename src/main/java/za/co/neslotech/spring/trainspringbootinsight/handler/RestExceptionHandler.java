package za.co.neslotech.spring.trainspringbootinsight.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(final EntityNotFoundException exception,
                                                          final WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(),
                                       HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            @NonNull final HttpRequestMethodNotSupportedException exception,
            @NonNull final HttpHeaders headers, @NonNull final HttpStatusCode status,
            @NonNull final WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(), headers,
                                       status, request);
    }
}
