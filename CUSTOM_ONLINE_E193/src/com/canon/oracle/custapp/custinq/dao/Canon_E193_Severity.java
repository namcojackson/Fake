package com.canon.oracle.custapp.custinq.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

import com.canon.oracle.custapp.util.CanonCustAppDBUtil;
import com.canon.oracle.custapp.util.CanonCustAppExceptionUtil;

/**
 * Title:		Canon_E193_Severity<br>
 * Description:	This is the main class of billing issues Functions<br>
 * <p>
 * CopyRight:	Canon<br>
 * Company:		Canon<br>
 * @author:		Subba<br>
 * @version:	1.0, (15-Sep-2005)<br>
 * @modified by:<br>
 * @version:<br>
 * @modification Detail:<br>
 * <pre>
 * Date           By         Description
 * ----------    ---------- ---------------------------------------------
 * 03/03/2016    Anvesh      S21 Changes
 * </pre>
 */

public class Canon_E193_Severity {

	/**
	 * Canon_E193_Severity constructor.
	 */
	public Canon_E193_Severity() {
		super();
	}
	/**
	 * This method will return the list of severity.
	 * <p>
	 * @return java.util.ArrayList.
	 * @param iOrgId int.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	public ArrayList getSeverity() throws CanonCustAppExceptionUtil {
		ArrayList alNonBillIssue = new ArrayList();
		//Get Connection
		CanonCustAppDBUtil connSeverityUtil = null;
		Connection connSeverity = null;
	    CallableStatement pCall = null;
	    ResultSet rsSeverity = null;
	    try {
	    	connSeverityUtil = new CanonCustAppDBUtil();
	    	connSeverity = (Connection)(connSeverityUtil.getConnection());
	    	pCall = connSeverity.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_severity(?)}");
	    	pCall.registerOutParameter(1, OracleTypes.CURSOR);

	    	pCall.execute();
	    	
	    	rsSeverity = (ResultSet) pCall.getObject(1);
	    	
	    	while(rsSeverity.next()) {
	    		alNonBillIssue.add(rsSeverity.getString(1));
	    	}	    	
	    }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Severity, Method:getSeverity()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Severity, Method:getSeverity()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
	    		if(rsSeverity != null) rsSeverity.close();
	    		if(connSeverityUtil != null) connSeverityUtil.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Severity, Method:getSeverity()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Severity, Method:getSeverity()"));
	    	}
	    }
		return alNonBillIssue;
	}
	
	
	/**
	 * This method will return the list of Credit Reason Codes.
	 * <p>
	 * @return java.util.ArrayList.
	 * @param iOrgId int.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	public ArrayList getCreditReasonCodes(String strInvSource) throws CanonCustAppExceptionUtil {
		ArrayList alReasonCd = new ArrayList();
		//Get Connection
		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
	    CallableStatement pCall = null;
	    ResultSet rs = null;
	    try {
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());
	    	
	    	pCall = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_credit_reason(?, ?)}");
	    	
	    	pCall.setString(1, strInvSource);
	    	pCall.registerOutParameter(2, OracleTypes.CURSOR);

	    	pCall.execute();
	    	
	    	rs = (ResultSet) pCall.getObject(2);
	    	
	    	while(rs.next()) {
	    		alReasonCd.add(rs.getString(1));
	    	}	    	
	    }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Severity, Method:getCreditReasonCodes()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Severity, Method:getCreditReasonCodes()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
	    		if(rs != null) rs.close();
	    		if(connUtil != null) connUtil.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Severity, Method:getCreditReasonCodes()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Severity, Method:getCreditReasonCodes()"));
	    	}
	    }
		return alReasonCd;
	}
	
	
	
	
	
	
	
	/**
	 * This method will return the list of Frequency Codes.
	 * <p>
	 * @return java.util.ArrayList.
	 * @param iOrgId int.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	public ArrayList getFrequencyCodes() throws CanonCustAppExceptionUtil {
		ArrayList alFrequencyCd = new ArrayList();
		//Get Connection
		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
	    CallableStatement pCall = null;
	    ResultSet rs = null;
	    try {
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());
	    	
	    	pCall = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.SELECT_BILL_FREQUENCY(?)}");
	    	
	    	
	    	pCall.registerOutParameter(1, OracleTypes.CURSOR);

	    	pCall.execute();
	    	
	    	rs = (ResultSet) pCall.getObject(1);
	    	
	    	while(rs.next()) {
	    		alFrequencyCd.add(rs.getString(1));
	    	}	    	
	    }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Severity, Method:getFrequencyCodes()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Severity, Method:getFrequencyCodes()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
	    		if(rs != null) rs.close();
	    		if(connUtil != null) connUtil.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Severity, Method:getFrequencyCodes()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Severity, Method:getFrequencyCodes()"));
	    	}
	    }
		return alFrequencyCd;
	}
	

	/**
	 * This method will return the list of Frequency Codes.
	 * <p>
	 * @return java.util.ArrayList.
	 * @param iOrgId int.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	public ArrayList getResolutionCode() throws CanonCustAppExceptionUtil {
		ArrayList alResCd = new ArrayList();
		//Get Connection
		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
	    CallableStatement pCall = null;
	    ResultSet rs = null;
	    try {
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());
	    	
	    	pCall = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.SELECT_RES_CODE(?)}");
	    	
	    	
	    	pCall.registerOutParameter(1, OracleTypes.CURSOR);

	    	pCall.execute();
	    	
	    	rs = (ResultSet) pCall.getObject(1);
	    	
	    	while(rs.next()) {
	    		alResCd.add(rs.getString(1));
	    	}	    	
	    }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Severity, Method:getFrequencyCodes()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Severity, Method:getFrequencyCodes()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
	    		if(rs != null) rs.close();
	    		if(connUtil != null) connUtil.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Severity, Method:getFrequencyCodes()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Severity, Method:getFrequencyCodes()"));
	    	}
	    }
		return alResCd;
	}
	
	
}


