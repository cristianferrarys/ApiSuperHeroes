package com.api.superheroes.exception;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;

public class ValidationException extends Exception {

  private static final long serialVersionUID = -1906787086988110417L;

  @Getter
  @Setter
  private String errorCode;

  public ValidationException(final String message) {
    super(message);
  }

  public ValidationException(final String message, final String errorCode) {
    super(message);
    setErrorCode(errorCode);
  }

  public ValidationException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ValidationException(final String message, final String errorCode, final Throwable cause) {
    super(message, cause);
    setErrorCode(errorCode);
  }

  public boolean hasErrorCode() {
    return !StringUtils.isBlank(getErrorCode());
  }

}
