package com.marcosparreiras.gestao_vagas.exceptions;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

  private MessageSource messageSource;

  public ExceptionHandlerController(MessageSource message) {
    this.messageSource = message;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(
    MethodArgumentNotValidException error
  ) {
    List<ErrorMessageDTO> dto = new ArrayList<>();

    error
      .getBindingResult()
      .getFieldErrors()
      .forEach(e -> {
        if (e == null) {
          return;
        }
        String message =
          this.messageSource.getMessage(e, LocaleContextHolder.getLocale());
        ErrorMessageDTO errorMessage = new ErrorMessageDTO(
          message,
          e.getField()
        );
        dto.add(errorMessage);
      });

    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
  }
}
