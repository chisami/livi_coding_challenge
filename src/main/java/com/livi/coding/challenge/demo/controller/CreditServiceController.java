package com.livi.coding.challenge.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livi.coding.challenge.demo.constant.EndpointConstants;
import com.livi.coding.challenge.demo.para.CreditServiceRequestModel;
import com.livi.coding.challenge.demo.payload.CreditPayload;
import com.livi.coding.challenge.demo.payload.GeneralApiResponse;
import com.livi.coding.challenge.demo.payload.ValidationResult;
import com.livi.coding.challenge.demo.security.EndpointReadPermission;
import com.livi.coding.challenge.demo.service.CreditService;
import com.livi.coding.challenge.demo.service.ModelConvertService;
import com.livi.coding.challenge.demo.service.ValidationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;


/** The Constant log. */
@Slf4j
@RestController
@CrossOrigin
@Api(value = "/creditservice")
@RequestMapping(value = "/creditservice")
public class CreditServiceController {

	/** The credit service. */
	@Autowired
	private CreditService creditService;

	/** The validation service. */
	@Autowired
	private ValidationService validationService;

	/** The model convert service. */
	@Autowired
	private ModelConvertService modelConvertService;

	/**
	 * Calculate credit assessment score.
	 *
	 * @param requestModel the request model
	 * @return the response entity
	 */
	@PostMapping("/v1/calculator")
	@ApiOperation("Calculate credit score")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = EndpointConstants.SUCCESS, response = GeneralApiResponse.class),
			@ApiResponse(code = 400, message = EndpointConstants.INVALID_MESSAGE_ARG, response = GeneralApiResponse.class),
			@ApiResponse(code = 401, message = EndpointConstants.USER_NOT_AUTHORIZED, response = GeneralApiResponse.class),
			@ApiResponse(code = 403, message = EndpointConstants.USER_NOT_AUTHENTICATED, response = GeneralApiResponse.class),
			@ApiResponse(code = 404, message = EndpointConstants.RECORD_NOT_FOUND, response = GeneralApiResponse.class) })
	@EndpointReadPermission
	public ResponseEntity<GeneralApiResponse<CreditPayload>> calculateCreditAssessmentScore(
			@RequestBody(required = true) @Validated CreditServiceRequestModel requestModel){

		log.info("CreditServiceRequestModel, numberOfEmployees= {}, companyType = {} , numberOfYearsOperated = {}",
				requestModel.getNumberOfEmployees(), requestModel.getCompanyType(),
				requestModel.getNumberOfYearsOperated());

		// validation
		ValidationResult validatedResult = validationService.validateWithParam(requestModel);

		if (!validatedResult.getSuccess()) {
			GeneralApiResponse<CreditPayload> resp = new GeneralApiResponse<>();
			resp.setSuccess(false);
			resp.setCode("1");
			resp.setMessage("Validation failed: " + validatedResult.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}

		GeneralApiResponse<CreditPayload> apiResponse = new GeneralApiResponse<>(
				creditService.retrieveCreditScore(modelConvertService.convertToEntity(requestModel)));

		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

}
