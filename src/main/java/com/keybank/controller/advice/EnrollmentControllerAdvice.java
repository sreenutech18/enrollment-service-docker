/** @Copyright 2022 Keybank pvt ltd. All rights are reserved, you should not
 *  disclose the information outside otherwise terms and conditions will apply
 */
package com.keybank.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.keybank.exception.BusinessException;
import com.keybank.exception.EnrollmentRequestInvalidException;
import com.keybank.exception.O2ServiceException;
import com.keybank.exception.SystemException;
import com.keybank.model.EnrollmentResponse;

/**
 * @author sreenu,09-Mar-2022 Description :
 */

@RestControllerAdvice
public class EnrollmentControllerAdvice {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentControllerAdvice.class);

	@ExceptionHandler(value = EnrollmentRequestInvalidException.class)
	@ResponseBody
	public EnrollmentResponse handleException(EnrollmentRequestInvalidException e) {

		LOGGER.error(" enrollment request invalid exception ", e);

		EnrollmentResponse enrollmentResponse = buildErrorResponse(e.getRespCode(), e.getRespMsg());

		return enrollmentResponse;
	}

	@ExceptionHandler(value = BusinessException.class)
	@ResponseBody
	public EnrollmentResponse handleBusinessException(BusinessException e) {

		LOGGER.error(" enrollment request buiness exception ", e);

		EnrollmentResponse enrollmentResponse = buildErrorResponse(e.getRespCode(), e.getRespMsg());

		return enrollmentResponse;
	}

	@ExceptionHandler(value = SystemException.class)
	@ResponseBody
	public EnrollmentResponse handleSystemException(SystemException e) {

		LOGGER.error(" enrollment request system exception ", e);

		EnrollmentResponse enrollmentResponse = buildErrorResponse(e.getRespCode(), e.getRespMsg());

		return enrollmentResponse;
	}
	
	
	@ExceptionHandler(value = O2ServiceException.class)
	@ResponseBody
	public EnrollmentResponse o2serviceException(O2ServiceException e) {

		LOGGER.error(" enrollment o2service exception ", e);

		EnrollmentResponse enrollmentResponse = buildErrorResponse(e.getRespCode(), e.getRespMsg());

		return enrollmentResponse;
	}
	

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public EnrollmentResponse GenericException(Exception e) {

		LOGGER.error(" enrollment unknown exception ", e);
		
		e.printStackTrace();

		EnrollmentResponse enrollmentResponse = buildErrorResponse("111", "unknown error");

		return enrollmentResponse;
	}

	private EnrollmentResponse buildErrorResponse(String respCode, String respMsg) {
		EnrollmentResponse enrollmentResponse = new EnrollmentResponse();
		enrollmentResponse.setRespCode(respCode);
		enrollmentResponse.setRespMsg(respMsg);
		return enrollmentResponse;
	}

}
