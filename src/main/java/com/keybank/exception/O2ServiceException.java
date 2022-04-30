/** @Copyright 2022 Keybank pvt ltd. All rights are reserved, you should not
 *  disclose the information outside otherwise terms and conditions will apply
 */
package com.keybank.exception;

/**
 * @author sreenu,18-Mar-2022
 * Description :
 */
public class O2ServiceException extends Exception {
	
	private String respCode;
	private String respMsg;

	public O2ServiceException(String respCode, String respMsg) {

		this.respCode = respCode;
		this.respMsg = respMsg;

	}

	public String getRespCode() {
		return respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

}
