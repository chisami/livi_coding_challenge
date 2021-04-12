package com.livi.coding.challenge.demo.exeception;

import lombok.extern.slf4j.Slf4j;


/** The Constant log. */
@Slf4j
public class CreditServiceException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The error code. */
	@SuppressWarnings("unused")
	private String errorCode;
	
	/** The message. */
	private String message;

	/**
	 * Instantiates a new credit service exception.
	 *
	 * @param code the code
	 */
	public CreditServiceException(String code) {
		super();

		this.errorCode = code;
	}

	/**
	 * Instantiates a new credit service exception.
	 *
	 * @param code the code
	 * @param message the message
	 */
	public CreditServiceException(String code, String message) {
		super(message);
		this.message = message;
		this.errorCode = code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {

		log.error("[CreditService][Exception] " + message);

		return message;
	}

	/**
	 * Instantiates a new credit service exception.
	 */
	public CreditServiceException() {
		super();
	}

	/**
	 * Instantiates a new credit service exception.
	 *
	 * @param cause the cause
	 */
	public CreditServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new credit service exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public CreditServiceException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
