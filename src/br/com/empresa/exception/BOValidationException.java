package br.com.empresa.exception;

public class BOValidationException extends BOException {

	public BOValidationException() {

	}

	public BOValidationException(String message) {

		super(message);

	}

	public BOValidationException(Throwable cause) {

		super(cause);

	}

	public BOValidationException(String message, Throwable cause) {

		super(message, cause);

	}

	public BOValidationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {

		super(message, cause, enableSuppression, writableStackTrace);

	}

}
