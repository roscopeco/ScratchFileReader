package com.roscopeco.scratch.io;

public class ScratchFormatException extends Exception {
  private static final long serialVersionUID = -6064996004371475460L;

  public ScratchFormatException() {
  }

  public ScratchFormatException(String message) {
    super(message);
  }

  public ScratchFormatException(Throwable cause) {
    super(cause);
  }

  public ScratchFormatException(String message, Throwable cause) {
    super(message, cause);
  }

  public ScratchFormatException(String message, Throwable cause,
      boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
