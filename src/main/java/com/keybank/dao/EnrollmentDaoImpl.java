/** @Copyright 2022 Keybank pvt ltd. All rights are reserved, you should not
 *  disclose the information outside otherwise terms and conditions will apply
 */
package com.keybank.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import com.keybank.exception.BusinessException;
import com.keybank.exception.SystemException;
import com.keybank.model.EnrollmentDaoRequest;
import com.keybank.model.EnrollmentDaoResponse;

/**
 * @author sreenu,09-Mar-2022 Description :
 */
@Component
public class EnrollmentDaoImpl extends StoredProcedure implements EnrollmentDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentDaoImpl.class);
	
	@Autowired
	public EnrollmentDaoImpl(JdbcTemplate jdbcTemplate) {
		
		super(jdbcTemplate,"AUTO_BILL_PAYMTS_01");
		
		registerParams();
		
		
	}
	

	
	private void registerParams() {
		
		//register input params
		declareParameter(new SqlParameter("CLIENT_ID_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("CARD_NUM_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("CVV_NUM_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("NAME_ON_CARD_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("EXPDATE_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("MOBILENUM_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("BILL_GEN_DATE_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("BILL_PAYMT_DATE_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("AMOUNT_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("PROVIDER_IN", Types.VARCHAR));
		
		//register output params
		
		declareParameter(new SqlOutParameter("RESP_CODE_OUT", Types.VARCHAR));
		declareParameter(new SqlOutParameter("RESP_MESSAGE_OUT", Types.VARCHAR));
		declareParameter(new SqlOutParameter("ACK_NUM_OUT", Types.VARCHAR));
		
		compile();
		
	}


	@Override
	public EnrollmentDaoResponse enrollment(EnrollmentDaoRequest daoRequest) throws BusinessException, SystemException {

		LOGGER.debug(" Entered into EnrollmentDaoImpl ");
		LOGGER.info(" EnrollmentDaoRequest object is :", daoRequest);
	
		EnrollmentDaoResponse enrollmentDaoResp = new EnrollmentDaoResponse();
		try {
			
			Map<String, Object> inParams = new HashMap<String, Object>();
			
			inParams.put("CLIENT_ID_IN", daoRequest.getClientId());
			inParams.put("CARD_NUM_IN", daoRequest.getCardNum());
			inParams.put("CVV_NUM_IN", daoRequest.getCvvNum());
			inParams.put("NAME_ON_CARD_IN", daoRequest.getNameOncard());
			inParams.put("EXPDATE_IN", daoRequest.getExpDate());
			inParams.put("MOBILENUM_IN", daoRequest.getMobileNum());
			inParams.put("BILL_GEN_DATE_IN", daoRequest.getBillDate());
			inParams.put("BILL_PAYMT_DATE_IN", daoRequest.getPaymentDate());
			inParams.put("AMOUNT_IN", daoRequest.getAmount());
			inParams.put("PROVIDER_IN", daoRequest.getProviderType());
			
			
			Map<String, Object> outParams = super.execute(inParams);
			
			
			String dbRespCode = outParams.get("RESP_CODE_OUT").toString();
			
			String dbRespMsg = outParams.get("RESP_MESSAGE_OUT").toString();
			String ackno = null;
			if( outParams.get("ACK_NUM_OUT") != null  ) {
			 ackno = outParams.get("ACK_NUM_OUT").toString();
			}
			
			System.out.println("respCode :"+dbRespCode+"respMsg :"+dbRespMsg+"ackno :"+ackno);
			
			enrollmentDaoResp = new EnrollmentDaoResponse();

			if ("0".equals(dbRespCode)) {
				enrollmentDaoResp.setRespCode(dbRespCode);
				enrollmentDaoResp.setRespMsg(dbRespMsg);
				enrollmentDaoResp.setEnrollmentStatus("enrollment successully done");
			} else if ("100".equals(dbRespCode) || "101".equals(dbRespCode) || "102".equals(dbRespCode)) {

				throw new BusinessException(dbRespCode, dbRespMsg);
			} else {

				throw new SystemException(dbRespCode, dbRespMsg);
			}
		} catch (BusinessException  be) {

			LOGGER.error("Entered into business Exception ", be);
			
			throw be;
		} catch (SystemException se) {

			LOGGER.error("Entered into system Exception ", se);
			
			throw se;
		}

		LOGGER.debug(" Exit from EnrollmentDaoImpl ");
		LOGGER.info("  enrollmentDaoResp object is :", enrollmentDaoResp);

		return enrollmentDaoResp;
	}

}
