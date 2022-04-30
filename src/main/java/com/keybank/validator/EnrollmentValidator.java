/** @Copyright 2022 Keybank pvt ltd. All rights are reserved, you should not
 *  disclose the information outside otherwise terms and conditions will apply
 */
package com.keybank.validator;

import org.springframework.stereotype.Component;

import com.keybank.exception.EnrollmentRequestInvalidException;
import com.keybank.model.EnrollmentRequest;

/**
 * @author sreenu,16-Mar-2022 Description :
 */
@Component
public class EnrollmentValidator {
	
	
	

	public void validateRequest(EnrollmentRequest enrollmentRequest) throws EnrollmentRequestInvalidException {
		
		System.out.println("Entered into validator");
		
		//flag = true;
		
		//some business logic, based on the logic it will decide the flag value will be true or false
		
		//is that logic is going to call backend system and based on backend response we will decide flag is true or false
		
		
	
		
		//based on this flag value, we may have other business logic
		
		
		// TODO : validate the request, if it is valid nothing return else it will
		// return Exception

		if (enrollmentRequest == null) {

			throw new EnrollmentRequestInvalidException("enr001", "No Enrollment details");
		}

		if (enrollmentRequest.getMobileNum() == null || " ".equals(enrollmentRequest.getMobileNum())) {

			throw new EnrollmentRequestInvalidException("enr002", "invalid mobile number");

		}

		if (enrollmentRequest.getBillDate() == null || " ".equals(enrollmentRequest.getBillDate())) {

			throw new EnrollmentRequestInvalidException("enr003", "invalid Bill Date");

		}

		if (enrollmentRequest.getPaymentDate() == null || " ".equals(enrollmentRequest.getPaymentDate())) {

			throw new EnrollmentRequestInvalidException("enr004", "invalid Payment Date");

		}
		
		if (enrollmentRequest.getProviderType() == null || " ".equals(enrollmentRequest.getProviderType())) {

			throw new EnrollmentRequestInvalidException("enr005", "invalid Provider Type");

		}

		// Todo : write validations for all the mandatory elements like cardnumber, cvv, name on card, expdate

	}

}
