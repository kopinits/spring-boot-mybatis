package com.markus.app.rest.exception;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * The Class BusinessRuntimeException.
 *
 * @author GFT Technologies
 * @version 1.0
 */
public class BusinessRuntimeException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8367928348450253313L;

	/** The code. */
	private final ExceptionCode code;

	/** The params. */
	private final Serializable[] params;

	/**
	 * Instantiates a new business runtime exception.
	 *
	 * @param code
	 *            the code
	 * @param params
	 *            the params
	 */
	public BusinessRuntimeException(final ExceptionCode code, final Serializable... params) {
		super();

		this.code = code;
		this.params = params;
	}

	/**
	 * Instantiates a new business runtime exception.
	 *
	 * @param thr
	 *            the thr
	 * @param code
	 *            the code
	 * @param params
	 *            the params
	 */
	public BusinessRuntimeException(final Throwable thr, final ExceptionCode code, final Serializable... params) {
		super(thr);

		this.code = code;
		this.params = params;
	}

	public BusinessRuntimeException(final Throwable thr) {
		super(thr);
		this.code = ExceptionCode.VALIDATION_ERROR;
		this.params = null;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public ExceptionCode getCode() {
		return code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return MessageFormat.format(code.getMessage(), (Object[]) params);
	}

	/**
	 * Gets the params.
	 *
	 * @return the params
	 */
	public Serializable[] getParams() {
		return params;
	}
}
