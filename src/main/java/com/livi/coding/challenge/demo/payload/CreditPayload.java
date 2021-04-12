package com.livi.coding.challenge.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Instantiates a new credit payload.
 *
 * @param creditScore the credit score
 */
@AllArgsConstructor

/**
 * Instantiates a new credit payload.
 */
@NoArgsConstructor

/**
 * Gets the credit score.
 *
 * @return the credit score
 */
@Getter

/**
 * Sets the credit score.
 *
 * @param creditScore the new credit score
 */
@Setter

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@ToString

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Builder
public class CreditPayload {

	/** The credit score. */
	private Long creditScore;

}
