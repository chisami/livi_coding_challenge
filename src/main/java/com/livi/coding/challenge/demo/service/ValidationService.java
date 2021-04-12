package com.livi.coding.challenge.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.livi.coding.challenge.demo.para.CreditServiceRequestModel;
import com.livi.coding.challenge.demo.payload.ValidationResult;
import com.livi.coding.challenge.demo.utils.MessageSourceUtils;


/**
 * The Class ValidationService.
 */
@Component
public class ValidationService {

//	@Value("${livi.max-employee-no}")
//	private int maxEmployeeNo;
//
//	@Value("${livi.max-operate-year-no}")
//	private int maxOperateYearNo;

	/** The min employee no. */
@Value("${livi.min-employee-no}")
	private int minEmployeeNo;

	/** The min operate year no. */
	@Value("${livi.min-operate-year-no}")
	private int minOperateYearNo;

	/**
	 * Validate with param.
	 *
	 * @param param the param
	 * @return the validation result
	 */
	public ValidationResult validateWithParam(CreditServiceRequestModel param) {

		Integer employeeNo = param.getNumberOfEmployees();
		Integer yearNo = param.getNumberOfYearsOperated();

//		if (employeeNo > maxEmployeeNo || employeeNo < minEmployeeNo) {
		if (employeeNo < minEmployeeNo) {

			return ValidationResult.builder()
//					.message(MessageSourceUtils.getMessage("Livi1003", new Integer[] { minEmployeeNo, maxEmployeeNo }))
					.message(MessageSourceUtils.getMessage("Livi1005", new Integer[] { minEmployeeNo }))
					.success(false).build();
		}

//		if (yearNo > maxOperateYearNo || yearNo < minOperateYearNo) {
		if (yearNo < minOperateYearNo) {

			return ValidationResult.builder().message(
//					MessageSourceUtils.getMessage("Livi1004", new Integer[] { minOperateYearNo, maxOperateYearNo }))
					MessageSourceUtils.getMessage("Livi1006", new Integer[] { minOperateYearNo }))
					.success(false).build();
		}

		return ValidationResult.builder().success(true).build();

	}

}
