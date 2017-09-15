package com.gjj.igden.service.util;
@SuppressWarnings("ALL")
public class ServiceException extends Exception {

	private StringBuilder additionalMessage = new StringBuilder("");
	private Exception exception;

	@SuppressWarnings("WeakerAccess")
	public ServiceException(ExceptionBuilder builder) {
		additionalMessage = builder.additionalMessageBuilder;
		exception = builder.exception;
	}

	public void printErrMessage() {
		System.err.println(additionalMessage.toString());
	}

	public void printSimpleMessage() {
		System.out.println(additionalMessage.toString());
	}

	public void appendMessage(String message) {
		additionalMessage.append(message).append(" ");
	}

	@Override
	public String getMessage() {
		return additionalMessage.toString();
	}

	@Override
	public void printStackTrace() {
		exception.printStackTrace();
	}

	public static class ExceptionBuilder {

		private StringBuilder additionalMessageBuilder = new StringBuilder("");
		private Exception exception;

		public ExceptionBuilder() {
		}

		public ExceptionBuilder setAdditionalMessage(String additionalMessage) {
			additionalMessageBuilder.append(additionalMessage).append(" ");
			return this;
		}

		public ExceptionBuilder setException(Exception exception) {
			this.exception = exception;
			return this;
		}

		public ServiceException build() {
			return new ServiceException(this);
		}
	}
}