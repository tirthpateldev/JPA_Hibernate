package com.gjj.igden.dao.daoUtil;

@SuppressWarnings("ALL")
public class DaoException extends Exception {
  private StringBuilder additionalMessage = new StringBuilder("");
  private Exception exception;

  @SuppressWarnings("WeakerAccess")
  public DaoException(ExceptionBuilder builder) {
    additionalMessage = builder.additionalMessageBuilder;
    exception = builder.exception;
  }

  public void printErrMessage() {
    System.err.println(additionalMessage.toString());
  }

  public void printSimpleMessage() {
    System.out.println(additionalMessage.toString());
  }

  @Override
  public void printStackTrace() {
    exception.printStackTrace();
  }

  public void appendMessage(String message) {
    additionalMessage.append(message).append(" ");
  }

  @Override
  public String getMessage() {
    return additionalMessage.toString();
  }

  public static class ExceptionBuilder {
    private StringBuilder additionalMessageBuilder = new StringBuilder("");
    private Exception exception;

    public ExceptionBuilder() {
    }

    public ExceptionBuilder setException(Exception exception) {
      this.exception = exception;
      return this;
    }

    public DaoException build() {
      return new DaoException(this);
    }
  }
}