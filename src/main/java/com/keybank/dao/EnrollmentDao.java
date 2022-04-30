/** @Copyright 2022 Keybank pvt ltd. All rights are reserved, you should not
 *  disclose the information outside otherwise terms and conditions will apply
 */
package com.keybank.dao;

import com.keybank.exception.BusinessException;
import com.keybank.exception.SystemException;
import com.keybank.model.EnrollmentDaoRequest;
import com.keybank.model.EnrollmentDaoResponse;

/**
 * @author sreenu,09-Mar-2022
 * Description :
 */
public interface EnrollmentDao {
	
	 EnrollmentDaoResponse enrollment(EnrollmentDaoRequest daoRequest) throws BusinessException, SystemException;



}
