package ir.snapp.pay.side.project.vamak.core.cfg;

import ir.snapp.pay.side.project.vamak.commons.dto.wrapper.ApiResp;
import ir.snapp.pay.side.project.vamak.commons.dto.wrapper.Error;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Set;
import java.util.TreeSet;

import static ir.snapp.pay.side.project.vamak.commons.dto.wrapper.ApiResp.createFailedResp;
import static ir.snapp.pay.side.project.vamak.commons.dto.wrapper.Outcome.FAILED;
import static ir.snapp.pay.side.project.vamak.commons.dto.wrapper.Reason.INVALID;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toCollection;
import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Order(HIGHEST_PRECEDENCE)
@ControllerAdvice(annotations = Api.class)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e, WebRequest request) {
        if (e instanceof AccessDeniedException ade) {
            throw ade;
        }
        var body = createFailedResp(Set.of(new Error()));
        return super.handleExceptionInternal(e, body, new HttpHeaders(), INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleValidationException(ConstraintViolationException cve, WebRequest request) {
        var body = createFailedResp(
                cve.getConstraintViolations()
                        .stream()
                        .map(cv ->
                                Error.builder()
                                        .reason(INVALID)
                                        .param(getParamName(cv))
                                        .description(cv.getMessage())
                                        .values(new String[]{String.valueOf(cv.getInvalidValue())})
                                        .build()
                        ).collect(toCollection(TreeSet::new))
        );
        return super.handleExceptionInternal(cve, body, new HttpHeaders(), BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var body = createFailedResp(
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(fe ->
                                Error.builder()
                                        .reason(INVALID)
                                        .param(fe.getField())
                                        .description(fe.getDefaultMessage())
                                        .values(new String[]{String.valueOf(fe.getRejectedValue())})
                                        .build()
                        ).collect(toCollection(TreeSet::new))
        );
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (isNull(body)) {
            body = createFailedResp(Set.of(new Error()));
        } else if (!(body instanceof ApiResp<?>)) {
            var wrapper = new ApiResp<>();
            wrapper.setOutcome(FAILED);
            wrapper.setResult(body);
            body = wrapper;
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private static String getParamName(ConstraintViolation<?> cv) {
        var itr = cv.getPropertyPath().iterator();
        var param = "";
        while (itr.hasNext()) {
            param = itr.next().getName();
        }
        return param;
    }
}
