package org.huebel.exception;

public class NewspaperException extends Exception {
	private static final long serialVersionUID = -8830129523006232857L;

	public NewspaperException() {
		super();
	}

	public NewspaperException(String s) {
		super(s);
	}

	public NewspaperException(String message, Throwable cause) {
		super(message, cause);
	}

	public NewspaperException(Throwable cause, String message) {
		super(message, cause);
	}

	public NewspaperException(Throwable cause) {
		super(cause);
	}
}
