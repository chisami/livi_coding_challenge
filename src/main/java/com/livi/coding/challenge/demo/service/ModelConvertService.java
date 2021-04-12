package com.livi.coding.challenge.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.livi.coding.challenge.demo.domain.Credit;
import com.livi.coding.challenge.demo.para.CreditServiceRequestModel;


/**
 * The Class ModelConvertService.
 */
@Component
public class ModelConvertService {

	/** The max employee no. */
	@Value("${livi.max-employee-no}")
	private int maxEmployeeNo;

	/** The max operate year no. */
	@Value("${livi.max-operate-year-no}")
	private int maxOperateYearNo;

	/**
	 * Convert to entity.
	 *
	 * @param param the param
	 * @return the credit
	 */
	public Credit convertToEntity(CreditServiceRequestModel param) {

		return Credit.builder().companyType(param.getCompanyType())
				.numberOfEmployees(
						param.getNumberOfEmployees() > maxEmployeeNo ? maxEmployeeNo : param.getNumberOfEmployees())
				.numberOfYearsOperated(param.getNumberOfYearsOperated() > maxOperateYearNo ? maxOperateYearNo
						: param.getNumberOfYearsOperated())
				.build();
	}

}
