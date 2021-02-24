package exceptions;

public class ValidationException extends Exception {

	private static final long serialVersionUID = -7150976238977332854L;

	private ValidationException() {
		super();
	}

	private ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	private ValidationException(String message) {
		super(message);
	}

	private ValidationException(Throwable cause) {
		super(cause);
	}


}
