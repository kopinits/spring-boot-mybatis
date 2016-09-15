package com.markus.app.rest.exception;

import java.io.Serializable;
import java.text.MessageFormat;

public class BusinessException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2506354157288425388L;

	/** The code. */
	private final ExceptionCode code;

	/** The params. */
	private final Serializable[] params;

	/**
	 * Instantiates a new business exception.
	 *
	 * @param businessRuntimeException
	 *            the business runtime exception
	 */
	public BusinessException(final BusinessRuntimeException businessRuntimeException) {
		code = businessRuntimeException.getCode();
		params = businessRuntimeException.getParams();
	}

	/**
	 * Instantiates a new business exception.
	 *
	 * @param code
	 *            the code
	 * @param params
	 *            the params
	 */
	public BusinessException(final ExceptionCode code, final Serializable... params) {
		super();
		this.code = code;
		this.params = params;
	}

	/**
	 * Instantiates a new business exception.
	 *
	 * @param thr
	 *            the throwable
	 * @param code
	 *            the code
	 * @param params
	 *            the parameters
	 */
	public BusinessException(final Throwable thr, final ExceptionCode code, final Serializable... params) {
		super(thr);
		this.code = code;
		this.params = params;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public ExceptionCode getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return MessageFormat.format(code.getMessage(), (Object[]) params);
	}

}
