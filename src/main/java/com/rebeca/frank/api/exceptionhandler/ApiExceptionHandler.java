package com.rebeca.frank.api.exceptionhandler;

import com.rebeca.frank.domain.exception.RecursoNaoEncontradoException;
import com.rebeca.frank.domain.exception.RegraDeConflitoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler
    public ProblemDetail conflictException(RegraDeConflitoException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler
    public ProblemDetail recursoNaoEncontradoException(RecursoNaoEncontradoException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        ex.getBindingResult()
                .getAllErrors()
                .forEach(objectError -> problemDetail.setTitle(objectError.getDefaultMessage()));

        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }
}
