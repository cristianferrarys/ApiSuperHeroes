package com.api.superheroes.exception;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;

public class SqlNotFoundException extends Exception {

  private static final long serialVersionUID = 1051990126284704216L;

  @Getter
  @Setter
  private String errorCode;

  public SqlNotFoundException(final String message) {
    super(message);
  }

  public SqlNotFoundException(final String message, final String errorCode) {
    super(message);
    setErrorCode(errorCode);
  }

  public SqlNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public SqlNotFoundException(final String message, final String errorCode, final Throwable cause) {
    super(message, cause);
    setErrorCode(errorCode);
  }

  public boolean hasErrorCode() {
    return !StringUtils.isBlank(getErrorCode());
  }

}
