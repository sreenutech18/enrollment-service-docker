/** @Copyright 2022 Keybank pvt ltd. All rights are reserved, you should not
 *  disclose the information outside otherwise terms and conditions will apply
 */
package com.keybank.svcclient;

import com.keybank.exception.O2ServiceException;
import com.keybank.model.O2ServiceRequest;
import com.keybank.model.O2ServiceResponse;

/**
 * @author sreenu,09-Mar-2022
 * Description :
 */


public interface O2ServiceClient {
	
	
	public O2ServiceResponse enrollment(O2ServiceRequest request) throws O2ServiceException;

}
