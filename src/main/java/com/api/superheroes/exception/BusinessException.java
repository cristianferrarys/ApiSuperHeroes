package com.api.superheroes.exception;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;


public class BusinessException extends Exception {

  private static final long serialVersionUID = -3335859349640344866L;

  @Getter
  @Setter
  private String errorCode;

  public BusinessException(final String message) {
    super(message);
  }

  public BusinessException(final String message, final String errorCode) {
    super(message);
    setErrorCode(errorCode);
  }

  public BusinessException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public BusinessException(final String message, final String errorCode, final Throwable cause) {
    super(message, cause);
    setErrorCode(errorCode);
  }

  public boolean hasErrorCode() {
    return !StringUtils.isBlank(getErrorCode());
  }

}
