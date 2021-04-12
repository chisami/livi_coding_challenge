package com.livi.coding.challenge.demo.controlleradvice;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.livi.coding.challenge.demo.constant.MessageCodeConstants;
import com.livi.coding.challenge.demo.exeception.CreditServiceException;
import com.livi.coding.challenge.demo.payload.GeneralApiResponse;

import lombok.extern.slf4j.Slf4j;


/** The Constant log. */
@Slf4j
@ControllerAdvice
public class DefaultExceptionAdvice {

	/** The show stack trace. */
	@Value("${livi.error-show-stack-trace:false}")
	private boolean showStackTrace;

	/**
	 * Exception handler.
	 *
	 * @param exception the exception
	 * @param response the response
	 * @return the response entity
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<GeneralApiResponse<String>> exceptionHandler(Exception exception,
			HttpServletResponse response) {

		boolean isRequiredPrintStackTrace = true;
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		log.info(
				"com.livi.coding.challenge.demo.controlleradvice.ExceptionAdvice.exceptionHandler(Exception, HttpServletResponse)");

		GeneralApiResponse<String> resp = new GeneralApiResponse<>();
		resp.setSuccess(false);
		resp.setCode(MessageCodeConstants.CODE_FAIL);

		String message;

		if (exception instanceof CreditServiceException) {
			message = exception.getMessage();

		} else if (exception instanceof AccessDeniedException) {

			message = "Access denied.";
			httpStatus = HttpStatus.FORBIDDEN;

		} else if (exception instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException casted = (MethodArgumentNotValidException) exception;

			message = "Validation failed for : " + casted.getBindingResult().getObjectName() + "."
					+ casted.getBindingResult().getFieldError().getField() + "  :  "
					+ casted.getBindingResult().getFieldError().getDefaultMessage();

		} else {

			message = "System error occurred. Please contact to system administrator.";
		}

		if (showStackTrace) {

			message += "\n\n" + ExceptionUtils.getStackTrace(exception);

		}

		if (isRequiredPrintStackTrace) {
			log.error("Exception: " + exception, exception);
		}

		resp.setMessage(message);

		return ResponseEntity.status(httpStatus).body(resp);
	}

}
