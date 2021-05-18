package com.api.superheroes.exception;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;

public class ControllerException extends RuntimeException {

  private static final long serialVersionUID = 2234421271307729555L;

  @Getter
  @Setter
  private String errorCode;

  public ControllerException(final String message) {
    super(message);
  }

  public ControllerException(final String message, final String errorCode) {
    super(message);
    setErrorCode(errorCode);
  }

  public ControllerException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ControllerException(final String message, final String errorCode, final Throwable cause) {
    super(message, cause);
    setErrorCode(errorCode);
  }

  public boolean hasErrorCode() {
    return !StringUtils.isBlank(getErrorCode());
  }

}
