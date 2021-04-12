package com.livi.coding.challenge.demo.domain;

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
public class Credit {

	/** The number of employees. */
	private int numberOfEmployees;
	
	/** The company type. */
	private String companyType;
	
	/** The number of years operated. */
	private int numberOfYearsOperated;
	
}
