package com.api.superheroes.exception;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;

public class DaoException extends Exception {

  private static final long serialVersionUID = 4272958049257457746L;

  @Getter
  @Setter
  private String errorCode;

  public DaoException(final String message) {
    super(message);
  }

  public DaoException(final String message, final String errorCode) {
    super(message);
    setErrorCode(errorCode);
  }

  public DaoException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public DaoException(final String message, final String errorCode, final Throwable cause) {
    super(message, cause);
    setErrorCode(errorCode);
  }

  public boolean hasErrorCode() {
    return !StringUtils.isBlank(getErrorCode());
  }

}
