/** @Copyright 2022 Keybank pvt ltd. All rights are reserved, you should not
 *  disclose the information outside otherwise terms and conditions will apply
 */
package com.keybank.service;

import com.keybank.exception.BusinessException;
import com.keybank.exception.O2ServiceException;
import com.keybank.exception.SystemException;
import com.keybank.model.EnrollmentRequest;
import com.keybank.model.EnrollmentResponse;

/**
 * @author sreenu,09-Mar-2022
 * Description :
 */
public interface EnrollmentService {
	

	/**
	 * @param enrollmentRequest
	 * @param clienId
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @throws O2ServiceException
	 */
	EnrollmentResponse enrollment(EnrollmentRequest enrollmentRequest, String clienId)
			throws BusinessException, SystemException, O2ServiceException;

}
