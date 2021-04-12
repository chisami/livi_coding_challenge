package com.livi.coding.challenge.demo.payload;

import com.livi.coding.challenge.demo.constant.MessageCodeConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Gets the data.
 *
 * @return the data
 */
@Getter

/**
 * Sets the data.
 *
 * @param data the new data
 */
@Setter

/**
 * Instantiates a new general api response.
 */
@NoArgsConstructor

/**
 * Instantiates a new general api response.
 *
 * @param success the success
 * @param message the message
 * @param code the code
 * @param data the data
 */
@AllArgsConstructor
public class GeneralApiResponse<T> {
	
	/** The success. */
	private Boolean success = true;
	
	/** The message. */
	private String message = MessageCodeConstants.MESSAGE_SUCCESS;
	
	/** The code. */
	private String code = MessageCodeConstants.CODE_SUCESS;
	
	/** The data. */
	private T data;

	/**
	 * Instantiates a new general api response.
	 *
	 * @param data the data
	 */
	public GeneralApiResponse(T data) {
		this.data = data;
	}

	/**
	 * Instantiates a new general api response.
	 *
	 * @param success the success
	 * @param message the message
	 * @param code the code
	 */
	public GeneralApiResponse(Boolean success, String message, String code) {
		super();
		this.success = success;
		this.message = message;
		this.code = code;
	}

}
