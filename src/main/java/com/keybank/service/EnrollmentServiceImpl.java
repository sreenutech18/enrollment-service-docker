/** @Copyright 2022 Keybank pvt ltd. All rights are reserved, you should not
 *  disclose the information outside otherwise terms and conditions will apply
 */
package com.keybank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.keybank.builder.EnrollmentRequestBuilder;
import com.keybank.builder.EnrollmentResponseBuilder;
import com.keybank.controller.EnrollmentController;
import com.keybank.dao.EnrollmentDao;
import com.keybank.exception.BusinessException;
import com.keybank.exception.O2ServiceException;
import com.keybank.exception.SystemException;
import com.keybank.model.EnrollmentDaoRequest;
import com.keybank.model.EnrollmentDaoResponse;
import com.keybank.model.EnrollmentRequest;
import com.keybank.model.EnrollmentResponse;
import com.keybank.model.O2ServiceRequest;
import com.keybank.model.O2ServiceResponse;
import com.keybank.svcclient.O2ServiceClient;

/**
 * @author sreenu,09-Mar-2022 Description :
 */
@Component
public class EnrollmentServiceImpl implements EnrollmentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentServiceImpl.class);

	@Autowired
	EnrollmentDao enrollmentDao;

	@Autowired
	O2ServiceClient o2ServiceClient;

	@Autowired
	EnrollmentRequestBuilder enrollmentRequestBuilder;
	
	@Autowired
	EnrollmentResponseBuilder enrollmentResponseBuilder;

	@Override
	public EnrollmentResponse enrollment(EnrollmentRequest enrollmentRequest, String clienId)
			throws BusinessException, SystemException, O2ServiceException {

		
		LOGGER.debug(" Entered Enrollment Service ");
		LOGGER.info(" EnrollmentRequest object is :", enrollmentRequest);
		
		EnrollmentDaoResponse enrollmentDaoResponse = null;

		//get the request from controller

		//prepare the O2ServiceRequest

		O2ServiceRequest o2ServiceRequest = enrollmentRequestBuilder.buildO2Request(enrollmentRequest);

		//call o2 service and get the response

		O2ServiceResponse o2ServiceResponse = o2ServiceClient.enrollment(o2ServiceRequest);
		
		System.out.println("o2ServiceResponse is :"+o2ServiceResponse);

		if ("0".equals(o2ServiceResponse.getRespCode())) {

			EnrollmentDaoRequest enrollDaoRequest = enrollmentRequestBuilder.buildEnrollDaoRequest(enrollmentRequest,clienId);

			enrollmentDaoResponse = enrollmentDao.enrollment(enrollDaoRequest);
			
			System.out.println("enrollmentDaoResponse is :"+enrollmentDaoResponse);

		}
		
		System.out.println("enrollmentResponseBuilder :"+enrollmentResponseBuilder);

		//prepare enrollment response
		EnrollmentResponse enrollmentResponse = enrollmentResponseBuilder.buildEnrollResponse(enrollmentDaoResponse);

		LOGGER.debug(" Exit from  Enrollment Service ");
		LOGGER.info(" enrollmentResponse object is :", enrollmentRequest);

		return enrollmentResponse;
	}

}
