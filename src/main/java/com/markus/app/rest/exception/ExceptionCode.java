package com.markus.app.rest.exception;

public enum ExceptionCode {

	/*
	 *PLEASE ORDER BY ERROR NUMBER
	 *4xx client errors
	 *5xx server errors
	 */

	UNIQUE_IDENTIFIER_VIOLATED(105),

	/** The validation error. */
	VALIDATION_ERROR(400),

	/** Bad Request. */
	BAD_REQUEST(402),

	/** The forbidden. */
	FORBIDDEN(403),

	OPTIMISTIC_LOCK(410);

	/** The code. */
	private final int code;

	/** The message. */
	private String message = "";

	/**
	 * Instantiates a new exception code.
	 *
	 * @param code
	 *            the code
	 */
	private ExceptionCode(final int code) {
		this.code = code;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message
	 *            the new message
	 */
	void setMessage(final String message) {
		this.message = message;
	}
}
