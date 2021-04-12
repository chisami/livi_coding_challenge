package com.livi.coding.challenge.demo.para;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Data

/**
 * Instantiates a new credit service request model.
 *
 * @param numberOfEmployees the number of employees
 * @param companyType the company type
 * @param numberOfYearsOperated the number of years operated
 */
@AllArgsConstructor

/**
 * Instantiates a new credit service request model.
 */
@NoArgsConstructor

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Builder
public class CreditServiceRequestModel {

	/** The number of employees. */
	@NotNull
	@ApiModelProperty(value = "Number of Employees", required = true)
	private int numberOfEmployees;

	/** The company type. */
	@NotNull
	@NotBlank(message = "{Livi1002}")
	@ApiModelProperty(value = "Company Type", required = true)
	private String companyType;

	/** The number of years operated. */
	@NotNull
	@ApiModelProperty(value = "Number of Years Operated", required = true)
	private int numberOfYearsOperated;


}
