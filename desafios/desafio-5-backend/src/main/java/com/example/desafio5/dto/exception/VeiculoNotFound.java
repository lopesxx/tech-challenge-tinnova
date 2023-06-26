package com.example.desafio5.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VeiculoNotFound extends RuntimeException{
  public VeiculoNotFound() {
  }

  public VeiculoNotFound(String message) {
    super(message);
  }

  public VeiculoNotFound(String message, Throwable cause) {
    super(message, cause);
  }

  public VeiculoNotFound(Throwable cause) {
    super(cause);
  }

  public VeiculoNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
