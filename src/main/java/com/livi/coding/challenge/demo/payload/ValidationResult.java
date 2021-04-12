package com.livi.coding.challenge.demo.payload;

import lombok.Builder;
import lombok.Data;


/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Data

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Builder
public class ValidationResult {

	/** The message. */
	private String message;

	/** The success. */
	private Boolean success;

}
