/** @Copyright 2022 Keybank pvt ltd. All rights are reserved, you should not
 *  disclose the information outside otherwise terms and conditions will apply
 */
package com.keybank.builder;

import org.springframework.stereotype.Component;

import com.keybank.model.EnrollmentDaoRequest;
import com.keybank.model.EnrollmentRequest;
import com.keybank.model.O2ServiceRequest;

/**
 * @author sreenu,03-Apr-2022 Description :
 */

@Component
public class EnrollmentRequestBuilder {

	/**
	 * Description : This method is used to preare O2ServiceRequest with the help of EnrollmentRequest
	 * @param enrollmentRequest
	 * @return O2ServiceRequest
	 */
	public O2ServiceRequest buildO2Request(EnrollmentRequest enrollmentRequest) {

		O2ServiceRequest o2servicerequest = new O2ServiceRequest();

		o2servicerequest.setCustomerName(enrollmentRequest.getCustomerName());
		o2servicerequest.setMobileNum(enrollmentRequest.getMobileNum());
		o2servicerequest.setPaymentDate(enrollmentRequest.getPaymentDate());
		o2servicerequest.setBillDate(enrollmentRequest.getBillDate());
		o2servicerequest.setAmount(enrollmentRequest.getAmount());

		return o2servicerequest;

	}
	
	/**
	 * Description : This method is used to prepare enrollmentDaoRequest with the help of EnrollmentRequest
	 * @param enrollmentRequest
	 * @return EnrollmentDaoRequest
	 */
	
	public EnrollmentDaoRequest buildEnrollDaoRequest(EnrollmentRequest enrollmentRequest, String clientId) {
		
		EnrollmentDaoRequest daoRequest = new EnrollmentDaoRequest();
		daoRequest.setClientId(clientId);
		daoRequest.setCustomerName(enrollmentRequest.getCustomerName());
		daoRequest.setMobileNum(enrollmentRequest.getMobileNum());
		daoRequest.setPaymentDate(enrollmentRequest.getPaymentDate());
		daoRequest.setBillDate(enrollmentRequest.getBillDate());
		daoRequest.setAmount(enrollmentRequest.getAmount());
		daoRequest.setCardNum(enrollmentRequest.getAccountDetails().getCardNumber());
		daoRequest.setCvvNum(enrollmentRequest.getAccountDetails().getCvv());
		daoRequest.setExpDate(enrollmentRequest.getAccountDetails().getExpDate());
		daoRequest.setProviderType("O2");
		daoRequest.setNameOncard(enrollmentRequest.getAccountDetails().getNameOnCard());
	
		
	return daoRequest;
	
	}

}
