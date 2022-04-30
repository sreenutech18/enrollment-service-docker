/** @Copyright 2022 Keybank pvt ltd. All rights are reserved, you should not
 *  disclose the information outside otherwise terms and conditions will apply
 */
package com.keybank.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.keybank.exception.BusinessException;
import com.keybank.exception.EnrollmentRequestInvalidException;
import com.keybank.exception.O2ServiceException;
import com.keybank.exception.SystemException;
import com.keybank.model.EnrollmentRequest;
import com.keybank.model.EnrollmentResponse;
import com.keybank.service.EnrollmentService;
import com.keybank.validator.EnrollmentValidator;

/**
 * @author sreenu,09-Mar-2022 Description :
 */

@RequestMapping("/v1")
@RestController

public class EnrollmentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentController.class);

	@Autowired
	EnrollmentValidator validator;

	@Autowired
	EnrollmentService enrollmentService;

	

	// @PostMapping("/enrollment")
	@RequestMapping(value = "/enrollment", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public EnrollmentResponse enrollment(@RequestBody EnrollmentRequest enrollmentRequest,
			@RequestHeader(name = "clientId", required = true) String clientId,
			@RequestHeader(name = "correlationId", required = true) String requestId,
			@RequestHeader(name = "msgTs", required = true) String messageTs) throws EnrollmentRequestInvalidException, BusinessException, SystemException, O2ServiceException {

		MDC.put("client-id", clientId);
		MDC.put("correlationId", requestId);
		MDC.put("messagetimestamp", messageTs);
		LOGGER.debug(" Entered into controller -Enrollment API");
		LOGGER.info(" EnrollmentRequest object is :", enrollmentRequest);

		//get the request from client

		//validate the request
		validator.validateRequest(enrollmentRequest);
		
		// 5. call service layer and get the response

		EnrollmentResponse serivceResponse = enrollmentService.enrollment(enrollmentRequest,clientId);

		// 19. get the response from service layer

		// 20. verify the response, if need modify the response

		// 21. prepare final controller response

		// 22. convert response object to json

		// 23. send response json to consumer/client

		System.out.println(" Exit from controller -Enrollment API");
		
		
		
		LOGGER.debug(" Exit from controller -Enrollment API");
		LOGGER.info(" serivceResponse  is :", serivceResponse);

		return serivceResponse;

	}

}
