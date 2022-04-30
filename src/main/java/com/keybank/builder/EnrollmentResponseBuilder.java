/** @Copyright 2022 Keybank pvt ltd. All rights are reserved, you should not
 *  disclose the information outside otherwise terms and conditions will apply
 */
package com.keybank.builder;

import org.springframework.stereotype.Component;

import com.keybank.model.EnrollmentDaoResponse;
import com.keybank.model.EnrollmentResponse;

/**
 * @author sreenu,03-Apr-2022 Description :
 */

@Component
public class EnrollmentResponseBuilder {

	/**
	 * Description : This method is used to prepare the EnrollmentResponse with the
	 * help of dao response
	 * 
	 * @param daoResp
	 * @return EnrollmentResponse
	 */

	public EnrollmentResponse buildEnrollResponse(EnrollmentDaoResponse daoResp) {

		EnrollmentResponse response = new EnrollmentResponse();

		response.setRespCode(daoResp.getRespCode());
		response.setRespMsg(daoResp.getRespMsg());
		response.setEnrollmentStatus(daoResp.getEnrollmentStatus());

		return response;
	}

}
