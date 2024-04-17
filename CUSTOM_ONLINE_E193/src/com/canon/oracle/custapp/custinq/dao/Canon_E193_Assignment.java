package com.canon.oracle.custapp.custinq.dao;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

import org.apache.commons.mail.Email;

import canon.apps.common.CanonConstants;
import canon.apps.common.CanonCustomProfile;
import canon.apps.common.CanonEmailUtil;

import com.canon.common.CanonCommonUtil; 
import com.canon.oracle.custapp.custinq.beans.Canon_E193_AssignmentObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_AssignmentPObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_CRBaseObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_CRMeterObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_CRPriceObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_CloseEmailObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_LineIdObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj;
import com.canon.oracle.custapp.util.CanonCustAppDBUtil;
import com.canon.oracle.custapp.util.CanonCustAppExceptionUtil;

/**
 * Canon_E193_Assignment Retrieves Amin information from database Creation date:
 * 
 * @author: Date By Description
 *          ----------------------------------------------------------------
 *          01/30/2016 Mangala Shenoy Modified for S21 changes
 */
public class Canon_E193_Assignment {
	
	private   String clsName="Canon_E193_Assignment";
	public   final String HAIL_CREDIT_REBILL ="{call CANON_E193_CS_EVOLUTION_PKG.HAIL_CREDIT_REBILL(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    CanonCommonUtil util;
	String SCH_NAME="";	
	/**
	 * Canon_E193_Assignment constructor.
	 */
	public Canon_E193_Assignment() {
		 util = new CanonCommonUtil();
		 SCH_NAME = CanonConstants.SCHEMA_NAME;
	}

	/**
	 * Get Department from database. Creation date: (7/27/2005 5:01:24 PM)
	 * 
	 * @return java.util.ArrayList
	 */
	public ArrayList getDept() throws CanonCustAppExceptionUtil {

		CallableStatement cstmtDept = null;
		ResultSet rsDept = null;
		ArrayList alDept = new ArrayList();

		// Get Connection
		CanonCustAppDBUtil connDept = new CanonCustAppDBUtil();
		Connection connDeptConnection = (Connection) (connDept.getConnection());

		try {
			cstmtDept = connDeptConnection
					.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_assign_dept(?)}");
			cstmtDept.registerOutParameter(1, OracleTypes.CURSOR);
			cstmtDept.execute();

			rsDept = (ResultSet) cstmtDept.getObject(1);

			while (rsDept.next()) {
				Canon_E193_AssignmentObj objDept = new Canon_E193_AssignmentObj();
				objDept.setStrAttribute2(rsDept.getString(1));
				objDept.setStrAttribute3(rsDept.getString(2));
				alDept.add(objDept);
			}
			cstmtDept.close();
			rsDept.close();
			connDeptConnection.close();
			connDept.releaseConnection();
		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:getDept()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:getDept()"));
		} finally {
			try {
				if (cstmtDept != null)
					cstmtDept.close();
				if (rsDept != null)
					rsDept.close();
				if (connDeptConnection != null)
					connDeptConnection.close();
				if (connDept != null)
					connDept.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:getDept()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:getDept()"));
			}
		}
		return alDept;
	}

	/**
	 * Get CRM Roles from database. Creation date: (7/27/2005 5:01:24 PM)
	 * 
	 * @param strDeptCode
	 *            java.lang.String
	 * @return java.util.ArrayList
	 */
	public ArrayList getRole(String strDeptCode)
			throws CanonCustAppExceptionUtil {

		CallableStatement cstmtRole = null;
		ResultSet rsRole = null;
		ArrayList alRole = new ArrayList();

		// Get Connection
		CanonCustAppDBUtil connRole = new CanonCustAppDBUtil();
		Connection connRoleConnection = (Connection) (connRole.getConnection());

		try {
			cstmtRole = connRoleConnection
					.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_assign_role(?,?)}");
			cstmtRole.setString(1, strDeptCode);
			cstmtRole.registerOutParameter(2, OracleTypes.CURSOR);
			cstmtRole.execute();

			rsRole = (ResultSet) cstmtRole.getObject(2);

			while (rsRole.next()) {
				Canon_E193_AssignmentObj objRole = new Canon_E193_AssignmentObj();
				// Start changes for S21 by Mangala
				// objRole.setIAttribute1(rsRole.getInt(1));
				objRole.setIAttribute1(rsRole.getString(1));
				// End Changes for S21 by Mangala
				objRole.setStrAttribute2(rsRole.getString(2));
				objRole.setStrAttribute3(rsRole.getString(3));
				objRole.setStrAttribute4(rsRole.getString(4));
				alRole.add(objRole);
			}

			cstmtRole.close();
			rsRole.close();
			connRoleConnection.close();
			connRole.releaseConnection();
		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:getRole()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:getRole()"));
		} finally {
			try {
				if (cstmtRole != null)
					cstmtRole.close();
				if (rsRole != null)
					rsRole.close();
				if (connRoleConnection != null)
					connRoleConnection.close();
				if (connRole != null)
					connRole.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:getRole()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:getRole()"));
			}
		}
		return alRole;
	}

	/**
	 * Get CRM Resources from database. Creation date: (7/27/2005 5:01:24 PM)
	 * 
	 * @param iOrgId
	 *            int
	 * @param iRoleId
	 *            int
	 * @return java.util.ArrayList
	 */
	public ArrayList getResource(int iOrgId, String iRoleId)
			throws CanonCustAppExceptionUtil {

		CallableStatement cstmtResource = null;
		ResultSet rsResource = null;
		ArrayList alResource = new ArrayList();

		// Get Connection
		CanonCustAppDBUtil connResource = new CanonCustAppDBUtil();
		Connection connResourceConnection = (Connection) (connResource
				.getConnection());

		try {
			cstmtResource = connResourceConnection
					.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_assign_resource(?,?,?)}");
			cstmtResource.setInt(1, iOrgId);
			cstmtResource.setString(2, iRoleId);
			cstmtResource.registerOutParameter(3, OracleTypes.CURSOR);
			cstmtResource.execute();

			rsResource = (ResultSet) cstmtResource.getObject(3);

			while (rsResource.next()) {
				Canon_E193_AssignmentObj objResource = new Canon_E193_AssignmentObj();
				// Start Changes for S21 by Mangala
				// objResource.setIAttribute1(rsResource.getInt(1));
				objResource.setIAttribute1(rsResource.getString(1));
				// Start Changes for S21 by Mangala
				objResource.setStrAttribute2(rsResource.getString(2));
				objResource.setStrAttribute3(rsResource.getString(3));
				objResource.setStrAttribute3(rsResource.getString(4));
				alResource.add(objResource);
			}

			cstmtResource.close();
			rsResource.close();
			connResourceConnection.close();
			connResource.releaseConnection();
		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:getResource()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:getResource()"));
		} finally {
			try {
				if (cstmtResource != null)
					cstmtResource.close();
				if (rsResource != null)
					rsResource.close();
				if (connResourceConnection != null)
					connResourceConnection.close();
				if (connResource != null)
					connResource.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:getResource()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:getResource()"));
			}
		}
		return alResource;
	}

	/**
	 * This method will insert assignment.
	 * <p>
	 * 
	 * @return int.
	 * @param assignLists
	 *            java.util.ArrayList.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */

	public int addAssignments(ArrayList assignLists)
			throws CanonCustAppExceptionUtil {
		int assignId = 0;
		// Get Connection
		CanonCustAppDBUtil connNonBillUtil = null;
		Connection connNonBill = null;
		CallableStatement pCall = null;
		try {
			connNonBillUtil = new CanonCustAppDBUtil();
			connNonBill = (Connection) (connNonBillUtil.getConnection());
			boolean isError = false;
			if (assignLists != null && assignLists.size() > 0) {
				Canon_E193_TicketLinesObj linesObj = null;
				for (int i = 0; i < assignLists.size(); i++) {
					linesObj = (Canon_E193_TicketLinesObj) assignLists.get(i);
					pCall = connNonBill
							.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.add_assignments(?,?,?,?,?,?,?)}");
					pCall.setInt(1, linesObj.getLineId());
					pCall.setString(2, linesObj.getDeptCode());
					// Start Changes for S21 by Mangala
					//pCall.setInt(3, linesObj.getRoleId());
					pCall.setString(3, linesObj.getRoleId());
					// pCall.setInt(4, linesObj.getResourceId());
					pCall.setString(4, linesObj.getResourceId());
					// End Changes for S21 by Mangala
					pCall.setString(5, linesObj.getCreatedBy());
					// pCall.setString(6, linesObj.getConBillStatus());
					pCall.registerOutParameter(6, OracleTypes.NUMBER);
					pCall.registerOutParameter(7, OracleTypes.NUMBER);

					pCall.execute();

					assignId = pCall.getInt(6);
					int iErrorLC = pCall.getInt(7);

					if (iErrorLC == -1) {
						isError = true;
						break;
					}
				}
				if (isError)
					throw new CanonCustAppExceptionUtil(100001,
							"Insert assignment failed",
							"Class: Canon_E193_Assignment, Method:addAssignments()");
			}
		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:addAssignments()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:addAssignments()"));
		} finally {
			try {
				if (pCall != null)
					pCall.close();
				if (connNonBillUtil != null)
					connNonBillUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:addNonBillLines()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:addNonBillLines()"));
			}
		}
		return assignId;
	}
	
	
	private String getUserCloseEmailBody(String customerName, int ticektNumber, String formatedDate){
		StringBuilder body = new StringBuilder();
		body.append("Dear ");
		body.append(customerName);
		body.append(",\n\n");
		body.append("Your Customer Care Inquiry ");
		body.append(ticektNumber);
		body.append(" was closed on ");
		body.append(formatedDate);
		body.append(".\n\n");
		body.append("If you have additional questions regarding this inquiry, please call our \n");
		body.append("Customer Service Department at 1-800-613-2228 with the above confirmation number.\n\n");
		body.append("Thank you,\n");
		body.append("Customer Service\n");
		body.append("Canon Solutions America, Inc.\n");
		return body.toString();
	}
	
	private String getCreatorEmailBody(String requestUrl, int iTktId){
		StringBuilder body = new StringBuilder();
		body.append("This is an Auto Notification Email. Ticket ");
		body.append(iTktId);
		body.append(" has been closed. Please refer Canon \n");
		body.append("Customer Care Application for this ticket resolution. \n");
		if(requestUrl != null && !requestUrl.trim().isEmpty())
		{
			body.append("\nPlease login using the link provided:\n\n");
			body.append(requestUrl.split("e193")[0]);
			body.append("e193/jsp/canon_E193_csEIHome.jsp");
		}
		body.append("\n\nThank You, \nCustomer Service");
		return body.toString();
	}
	
	private String[] splitEmailAddress(String emailAddressString)
	{
		String[] receiptEmails = null;
		if(emailAddressString != null && !emailAddressString.trim().isEmpty())
		{
			StringTokenizer receiptTokens = new StringTokenizer(
					emailAddressString, ",");
			receiptEmails = new String[receiptTokens
					.countTokens()];
			int emailCount = 0;
			while (receiptTokens.hasMoreTokens()) {
				receiptEmails[emailCount] = receiptTokens.nextToken();
				emailCount++;
			}
		}
		return receiptEmails;
	}
	
	
	public void logException(String programName, String attribute1, String attribute2, 	
			String attribute3, String attribute4, String attribute5, String errorMsg) throws CanonCustAppExceptionUtil{
		CanonCustAppDBUtil connNonBillUtil = null;
		Connection connNonBill = null;
		CallableStatement pCall = null;
		try
		{
			System.out.println("Canon_E193_Assignment logException() attribute1 : " + attribute1
					+ " attribute2 : " + attribute2 + " attribute3 : " + attribute3 + " attribute4 " + attribute4
					+ " attribute5 : " + attribute5 + " errorMsg : " + errorMsg);
			connNonBillUtil = new CanonCustAppDBUtil();
			connNonBill = (Connection) (connNonBillUtil.getConnection());
			pCall = connNonBill.prepareCall("{call CANON_E193_CS_SQLS_PKG.debug_msg(?, ?, ?, ?, ?, ?, ?)}");
			pCall.setString(1, programName);
			pCall.setString(2, attribute1);
			pCall.setString(3, attribute2);
			pCall.setString(4, attribute3);
			pCall.setString(5, attribute4);
			pCall.setString(6, attribute5);
			pCall.setString(7, errorMsg);
			pCall.execute();
			
			//System.out.println("Canon_E193_Assignment logException() end");
		} catch (CanonCustAppExceptionUtil csExp) {
			csExp.printStackTrace();
			throw (csExp);
		} catch (SQLException eSQLExp) {
			eSQLExp.printStackTrace();
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:logException()"));
		} catch (Exception eExp) {
			eExp.printStackTrace();
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:logException()"));
		} finally {
			try {
				if (pCall != null)
					pCall.close();
				if (connNonBillUtil != null)
					connNonBillUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:logException()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:logException()"));
			}
		}
	}
	
	public Canon_E193_CloseEmailObj getCloseEmailDetails(int iTktId) throws CanonCustAppExceptionUtil{
		CanonCustAppDBUtil connNonBillUtil = null;
		Connection connNonBill = null;
		CallableStatement pCall = null;
		Canon_E193_CloseEmailObj closeEmailObj = null;
		try
		{
			System.out.println("Canon_E193_Assignment getCloseEmailDetails() iTktId " + iTktId);
			connNonBillUtil = new CanonCustAppDBUtil();
			connNonBill = (Connection) (connNonBillUtil.getConnection());
			pCall = connNonBill.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.GET_CLOSE_EMAIL_DETAILS(?, ?, ?, ?, ?)}");
			pCall.setInt(1, iTktId);
			pCall.registerOutParameter(2, OracleTypes.VARCHAR);
			pCall.registerOutParameter(3, OracleTypes.VARCHAR);
			pCall.registerOutParameter(4, OracleTypes.VARCHAR);
			pCall.registerOutParameter(5, OracleTypes.VARCHAR);
			pCall.execute();
			
			closeEmailObj = new Canon_E193_CloseEmailObj();
			closeEmailObj.setSkipNotificationFlag(pCall.getString(2)); 
			closeEmailObj.setEmailAddress(CanonCommonUtil
					.checkNull(pCall.getString(3)));
			closeEmailObj.setSubject(CanonCommonUtil
					.checkNull(pCall.getString(4)));
			closeEmailObj.setComments(CanonCommonUtil
					.checkNull(pCall.getString(5)));
			
			//System.out.println("Canon_E193_Assignment getCloseEmailDetails() closeEmailObj " + closeEmailObj);
		} catch (CanonCustAppExceptionUtil csExp) {
			csExp.printStackTrace();
			throw (csExp);
		} catch (SQLException eSQLExp) {
			eSQLExp.printStackTrace();
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:getCloseEmailDetails()"));
		} catch (Exception eExp) {
			eExp.printStackTrace();
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:getCloseEmailDetails()"));
		} finally {
			try {
				if (pCall != null)
					pCall.close();
				if (connNonBillUtil != null)
					connNonBillUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:getCloseEmailDetails()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:getCloseEmailDetails()"));
			}
		}
		return closeEmailObj;
	}
	
	public String insertOrUpdateCloseEmail(int ticketId, String skipNotificationFlag, 
			String emailAddress, String subject, String comments, String userId) throws CanonCustAppExceptionUtil{
		CanonCustAppDBUtil connNonBillUtil = null;
		Connection connNonBill = null;
		CallableStatement pCall = null;
		String status = "";
		try
		{
			connNonBillUtil = new CanonCustAppDBUtil();
			connNonBill = (Connection) (connNonBillUtil.getConnection());
			pCall = connNonBill.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.UPSERT_CLOSE_EMAIL(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			pCall.setInt(1, ticketId);
			pCall.setString(2, skipNotificationFlag);
			pCall.setString(3, emailAddress);
			pCall.setString(4, subject);
			pCall.setString(5, comments);
			pCall.setString(6, userId);
			pCall.setString(7, null);
			pCall.setString(8, null);
			pCall.setString(9, null);
			pCall.setString(10, null);
			pCall.setString(11, null);
			pCall.registerOutParameter(12, OracleTypes.VARCHAR);
			pCall.execute();
			
			status = pCall.getString(12);
			
		} catch (CanonCustAppExceptionUtil csExp) {
			csExp.printStackTrace();
			throw (csExp);
		} catch (SQLException eSQLExp) {
			eSQLExp.printStackTrace();
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:insertOrUpdateCloseEmail()"));
		} catch (Exception eExp) {
			eExp.printStackTrace();
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:insertOrUpdateCloseEmail()"));
		} finally {
			try {
				if (pCall != null)
					pCall.close();
				if (connNonBillUtil != null)
					connNonBillUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:insertOrUpdateCloseEmail()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:insertOrUpdateCloseEmail()"));
			}
		}
		return status;
	}
	
	private int customerClosedTicketNotification(boolean skipEmailNotification, String email_address,
			String email_subject, String email_body, int iTktId, String strStatus, String subjectInit,
			String userId) throws CanonCustAppExceptionUtil{
		
		CanonCustAppDBUtil connNonBillUtil = null;
		Connection connNonBill = null;
		CallableStatement pCall = null;
		
		String receiptEmail = "";
		String emailSubject = "";
		String emailBody = "";
		String[] receiptEmails = null;
		String custContactName = "";
		Email email = null;
		int iErrorLC = 0;
		try {
			System.out.println("Canon_E193_Assignment customerClosedTicketNotification() iTktId " + iTktId 
					+  " strStatus " + strStatus + " userId " + userId);
			
			if (!skipEmailNotification) {
				receiptEmail = email_address;
				emailSubject = email_subject;
				emailBody = email_body;
			
				/*System.out.println("Canon_E193_Assignment customerClosedTicketNotification() receiptEmail " 
						+ receiptEmail + " emailSubject " + emailSubject + " emailBody " + emailBody);*/
					
				if (receiptEmail == null || receiptEmail.trim().isEmpty()) {
					connNonBillUtil = new CanonCustAppDBUtil();
					connNonBill = (Connection) (connNonBillUtil.getConnection());
					pCall = connNonBill.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.GET_USER_NOTIF_DTLS(?,?,?,?,?,?,?)}");			
					pCall.setInt(1, iTktId);
					pCall.setString(2, strStatus);
					pCall.registerOutParameter(3, OracleTypes.VARCHAR);
					pCall.registerOutParameter(4, OracleTypes.VARCHAR);
					pCall.registerOutParameter(5, OracleTypes.VARCHAR);
					pCall.registerOutParameter(6, OracleTypes.VARCHAR);
					pCall.registerOutParameter(7, OracleTypes.DATE);
					pCall.execute();
					receiptEmail = pCall.getString(6);
					custContactName = pCall.getString(5);
					/*System.out.println("Canon_E193_Assignment customerClosedTicketNotification() receiptEmail " 
							+ receiptEmail + " custContactName " + custContactName);*/
				}
				
				if (custContactName == null
						|| "".equalsIgnoreCase(custContactName.trim()))
					custContactName="Customer";
				
				if(receiptEmail != null && !receiptEmail.trim().isEmpty())
				{
					receiptEmails = splitEmailAddress(receiptEmail);
					/*System.out.println("Canon_E193_Assignment customerClosedTicketNotification() receiptEmails " 
							+ receiptEmails );*/
					if(emailSubject == null
							|| emailSubject.trim().isEmpty()
							|| emailBody == null || emailBody.trim().isEmpty()){
						emailSubject = "Canon Solutions America - Closed Customer Care Inquiry Confirmation";
						DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
						Date date = new Date();
						emailBody = getUserCloseEmailBody(custContactName, iTktId, dateFormat.format(date));
						/*System.out.println("Canon_E193_Assignment customerClosedTicketNotification() emailSubject " 
								+ emailSubject + " emailBody " + emailBody);*/
					}
					
					if (emailSubject != null && emailSubject.trim().length() > 0
							&& emailBody != null && emailBody.trim().length() > 0) {
						email = CanonEmailUtil.createSimpleEmail(); 
						email.setSubject(subjectInit + emailSubject);
						email.setMsg(emailBody);
						email.addTo(receiptEmails);
						try {
							email.send();
						} catch (org.apache.commons.mail.EmailException eex) {
							iErrorLC = -1;
							logException("CUSTOMER_CLOSE_NOTIFICATION", "TICKET# "+iTktId, 
									null, "Resource# " +userId, null, null, eex.getMessage());
							eex.printStackTrace();
						}
					} else {
						System.out.println("Customer email not sent.");
					}
				} else {
					System.out.println("Customer email not sent.");
				}
			}
		} catch (CanonCustAppExceptionUtil csExp) {
			csExp.printStackTrace();
			logException("CUSTOMER_CLOSE_NOTIFICATION", "TICKET# "+iTktId, 
					null, "Resource# " +userId, null, null, csExp.getMessage());
			throw (csExp);
		} catch (SQLException eSQLExp) {
			eSQLExp.printStackTrace();
			logException("CUSTOMER_CLOSE_NOTIFICATION", "TICKET# "+iTktId, 
					null, "Resource# " +userId, null, null, eSQLExp.getMessage());
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:customerClosedTicketNotification()"));
		} catch (Exception eExp) {
			eExp.printStackTrace();
			logException("CUSTOMER_CLOSE_NOTIFICATION", "TICKET# "+iTktId, 
					null, "Resource# " +userId, null, null, eExp.getMessage());
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:customerClosedTicketNotification()"));
		} finally {
			try {
				if (pCall != null)
					pCall.close();
				if (connNonBillUtil != null)
					connNonBillUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:customerClosedTicketNotification()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:customerClosedTicketNotification()"));
			}
		}	
		
		return iErrorLC;
	}
	
	private int createdUserClosedTicketNotification(int iTktId, String subjectInit,
			String requestUrl, String userId) throws CanonCustAppExceptionUtil{
		
		CanonCustAppDBUtil connNonBillUtil = null;
		Connection connNonBill = null;
		CallableStatement pCall = null;
		
		String receiptEmail = "";
		String emailSubject = "";
		String[] receiptEmails = null;
		String body = "";
		Email email = null;
		int iErrorLC = 0;
		try {
				System.out.println("Canon_E193_Assignment createdUserClosedTicketNotification() iTktId " + iTktId);
				connNonBillUtil = new CanonCustAppDBUtil();
				connNonBill = (Connection) (connNonBillUtil.getConnection());
				pCall = connNonBill.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.GET_CLOSING_NOTIF_DTLS(?,?)}");			
				pCall.setInt(1, iTktId);
				pCall.registerOutParameter(2, OracleTypes.VARCHAR);
				pCall.execute();
				
				receiptEmail = pCall.getString(2);
				
				if(receiptEmail != null && !receiptEmail.trim().isEmpty())
				{
					StringTokenizer receiptTokens = new StringTokenizer(
							receiptEmail, ",");
					receiptEmails = new String[receiptTokens
							.countTokens()];
					int emailCount = 0;
					while (receiptTokens.hasMoreTokens()) {
						receiptEmails[emailCount] = receiptTokens.nextToken();
						emailCount++;
					}
					emailSubject=subjectInit + "Ticket Number : "+iTktId+" - Closed";
					email = CanonEmailUtil.createSimpleEmail();
					email.setSubject(emailSubject);
					body = getCreatorEmailBody(requestUrl, iTktId);
					email.setMsg(body);
					email.addTo(receiptEmails);
					
					//System.out.println("Canon_E193_Assignment createdUserClosedTicketNotification() Body " + body + " receiptEmails " + receiptEmails);
					try {
						email.send();
					} catch (org.apache.commons.mail.EmailException eex) {
						iErrorLC = -1;
						eex.printStackTrace();
						logException("CREATED_USER_CLOSE_NOTIFICATION", "TICKET# "+iTktId, 
								null, "Resource# " +userId, null, null, eex.getMessage());
					}
				} else {
					System.out.println("Ticket Creator email not sent.");
				}
		} catch (CanonCustAppExceptionUtil csExp) {
			csExp.printStackTrace();
			logException("CREATED_USER_CLOSE_NOTIFICATION", "TICKET# "+iTktId, 
					null, "Resource# " +userId, null, null, csExp.getMessage());
			throw (csExp);
		} catch (SQLException eSQLExp) {
			eSQLExp.printStackTrace();
			logException("CREATED_USER_CLOSE_NOTIFICATION", "TICKET# "+iTktId, 
					null, "Resource# " +userId, null, null, eSQLExp.getMessage());
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:createdUserClosedTicketNotification()"));
		} catch (Exception eExp) {
			eExp.printStackTrace();
			logException("CREATED_USER_CLOSE_NOTIFICATION", "TICKET# "+iTktId, 
					null, "Resource# " +userId, null, null, eExp.getMessage());
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:createdUserClosedTicketNotification()"));
		} finally {
			try {
				if (pCall != null)
					pCall.close();
				if (connNonBillUtil != null)
					connNonBillUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:createdUserClosedTicketNotification()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:createdUserClosedTicketNotification()"));
			}
		}	
		
		return iErrorLC;
	}

	/**
	 * This method will update assignment status.
	 * <p>
	 * 
	 * @return boolean.
	 * @param int
	 * @param int
	 * @param String
	 * @param int
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */

	public boolean updateStatus(int iOrgId, int iLineId, String strSeverity,String strStatus, 
			String iUpdatedBy, String strNotes)
			throws CanonCustAppExceptionUtil {
		boolean isTrue = false;
		// Get Connection
		CanonCustAppDBUtil connNonBillUtil = null;
		Connection connNonBill = null;
		CallableStatement pCall = null;
		System.out.println("Inside updateStatus:iOrgId "+iOrgId+" iLineId:"+iLineId
				+ " strSeverity:"+strSeverity+" strStatus:"+strStatus+" iUpdatedBy:"+iUpdatedBy);
		try {
			connNonBillUtil = new CanonCustAppDBUtil();
			connNonBill = (Connection) (connNonBillUtil.getConnection());

			pCall = connNonBill
					.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.update_ticket_line(?,?,?,?,?,?,?)}");

			pCall.setInt(1, iOrgId);
			pCall.setInt(2, iLineId);
			pCall.setString(3, strSeverity);
			pCall.setString(4, strStatus);
			pCall.setString(5, iUpdatedBy);
			pCall.setString(6, strNotes);

			pCall.registerOutParameter(7, OracleTypes.NUMBER);

			pCall.execute();
			int iErrorLC = pCall.getInt(7);
			System.out.println("iErrorLC:" + iErrorLC);
			
			if (iErrorLC == -1) {
				isTrue = false;
			} else if (iErrorLC > 0) {
				isTrue = true;
			} else {
				isTrue = true;
			}
			
			if (!isTrue)
				throw new CanonCustAppExceptionUtil(100001,
						"update assignment failed",
						"Class: Canon_E193_Assignment, Method:updateStatus()");
				
			
			
		} catch (CanonCustAppExceptionUtil csExp) {
			csExp.printStackTrace();
			throw (csExp);
		} catch (SQLException eSQLExp) {
			eSQLExp.printStackTrace();
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:updateStatus()"));
		} catch (Exception eExp) {
			eExp.printStackTrace();
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:updateStatus()"));
		} finally {
			try {
				if (pCall != null)
					pCall.close();
				if (connNonBillUtil != null)
					connNonBillUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:updateStatus()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:updateStatus()"));
			}
		}
		return isTrue;
	}
	
	public void closeTicketNotification(String headerStatus, 
			int iTktId,boolean skipEmailNotification, String email_address,
			String email_subject,String email_body, String requestUrl, String userId)
			throws CanonCustAppExceptionUtil {
		
		System.out.println("Inside closeTicketNotification - iTktId :"+iTktId+" headerStatus: "+headerStatus
				+" skipEmailNotification: "+skipEmailNotification+" email_address: "+email_address
				+" requestUrl: "+requestUrl);
		try {
							
			if (headerStatus != null && "CLOSE".equals(headerStatus)) {
				String subjectInit = "";
				try {
					subjectInit = CanonCommonUtil
							.checkNull(CanonCustomProfile
									.getUserProfileValue("CANON_CUSTOM_EMAIL_SUBJECT_INIT"));
				} catch (Exception exception) {
					System.err
							.println("Error occured from getting CANON_CUSTOM_EMAIL_SUBJECT_INIT "
									+ exception.getMessage());
				}
				customerClosedTicketNotification(skipEmailNotification,
						email_address, email_subject, email_body, iTktId,
						"CLOSED", subjectInit, userId);
				createdUserClosedTicketNotification(iTktId, subjectInit,
						requestUrl, userId);
			}
			
		} catch (Exception eExp) {
			System.err.println("Failed to send email to customer and/or creator. Ticket Number : " + iTktId);
		} 
	}

	/**
	 * This method is for call wrap up used in ticket summary.
	 * <p>
	 * 
	 * @return boolean.
	 * @param int
	 * @param int
	 * @param String
	 * @param int
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */

	public ArrayList callWrapUp(int iOrgId, int iTicketId, int iLineId,
			String strStatus, String iUpdatedBy)
			throws CanonCustAppExceptionUtil {
		boolean isTrue = false;
		// Get Connection
		CanonCustAppDBUtil connNonBillUtil = null;
		Connection connNonBill = null;
		CallableStatement pCall = null;
		ResultSet rsLineId = null;
		ArrayList alLineId = new ArrayList();
		try {
			connNonBillUtil = new CanonCustAppDBUtil();
			connNonBill = (Connection) (connNonBillUtil.getConnection());

			pCall = connNonBill
					.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.ticket_summary_call_wrap(?,?,?,?,?,?,?)}");

			pCall.setInt(1, iOrgId);
			pCall.setInt(2, iTicketId);
			pCall.setInt(3, iLineId);
			pCall.setString(4, strStatus);
			pCall.setString(5, iUpdatedBy);

			pCall.registerOutParameter(6, OracleTypes.NUMBER);
			pCall.registerOutParameter(7, OracleTypes.CURSOR);
			pCall.execute();

			int iErrorLC = pCall.getInt(6);
			rsLineId = (ResultSet) pCall.getObject(7);
			while (rsLineId.next()) {
				Canon_E193_LineIdObj objLineId = new Canon_E193_LineIdObj();
				objLineId.setiLineId(rsLineId.getInt(1));
				alLineId.add(objLineId);
			}
			if (iErrorLC == -1)
				isTrue = false;
			else
				isTrue = true;
			if (!isTrue)
				throw new CanonCustAppExceptionUtil(100001,
						"update assignment failed",
						"Class: Canon_E193_Assignment, Method:callWrapUp()");
		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:callWrapUp()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:callWrapUp()"));
		} finally {
			try {
				if (pCall != null)
					pCall.close();
				if (connNonBillUtil != null)
					connNonBillUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:callWrapUp()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:callWrapUp()"));
			}
		}
		// return isTrue;
		return alLineId;
	}
	
	
	public void updateCRInfo(int iTicketId, int iLineId,
			String strStatus, String returnCodes)
			throws CanonCustAppExceptionUtil {
		// Get Connection
		CanonCustAppDBUtil connNonBillUtil = null;
		Connection connNonBill = null;
		CallableStatement pCall = null;
		try {
			connNonBillUtil = new CanonCustAppDBUtil();
			connNonBill = (Connection) (connNonBillUtil.getConnection());

			pCall = connNonBill
					.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.UPDATE_CR_INFO(?,?,?,?)}");

			pCall.setInt(1, iTicketId);
			pCall.setInt(2, iLineId);
			pCall.setString(3, strStatus);
			pCall.setString(4, returnCodes);			
			pCall.execute();

			
		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:updateCRInfo()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:updateCRInfo()"));
		} finally {
			try {
				if (pCall != null)
					pCall.close();
				if (connNonBillUtil != null)
					connNonBillUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:updateCRInfo()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:updateCRInfo()"));
			}
		}
	}

	/**
	 * This method is for header update used in ticket status.
	 * <p>
	 * 
	 * @return boolean.
	 * @param int
	 * @param int
	 * @param String
	 * @param int
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */

	public boolean headerUpdate(int iOrgId, int iTicketId, String strStatus,
			String iUpdatedBy, String strResolCd) throws CanonCustAppExceptionUtil {
		boolean isTrue = false;
		// Get Connection
		CanonCustAppDBUtil connectionheaderUpdate = null;
		Connection connheaderUpdate = null;
		CallableStatement cstmtheaderUpdate = null;
		try {
			connectionheaderUpdate = new CanonCustAppDBUtil();
			connheaderUpdate = (Connection) (connectionheaderUpdate
					.getConnection());

			cstmtheaderUpdate = connheaderUpdate
					.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.ticket_status_header_update(?,?,?,?,?,?)}");

			cstmtheaderUpdate.setInt(1, iOrgId);
			cstmtheaderUpdate.setInt(2, iTicketId);
			cstmtheaderUpdate.setString(3, strStatus);
			cstmtheaderUpdate.setString(4, iUpdatedBy);
			cstmtheaderUpdate.setString(5, strResolCd);
			cstmtheaderUpdate.registerOutParameter(6, OracleTypes.NUMBER);

			cstmtheaderUpdate.execute();

			int iErrorLC = cstmtheaderUpdate.getInt(6);

			if (iErrorLC == -1)
				isTrue = false;
			else
				isTrue = true;

			if (!isTrue)
				throw new CanonCustAppExceptionUtil(100001,
						"update lines and assignements failed",
						"Class: Canon_E193_Assignment, Method:headerUpdate()");

		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:headerUpdate()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:headerUpdate()"));
		} finally {
			try {
				if (cstmtheaderUpdate != null)
					cstmtheaderUpdate.close();
				if (connheaderUpdate != null)
					connheaderUpdate.close();
				if (connectionheaderUpdate != null)
					connectionheaderUpdate.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:headerUpdate()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:headerUpdate()"));
			}
		}
		return isTrue;
	}

	/**
	 * This method is used in ticket assignment details.
	 * <p>
	 * 
	 * @return java.util.ArrayList.
	 * @param int
	 * @param int
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */

	public ArrayList getAssignments(int iOrgId, int iTicketId, int iLineId)
			throws CanonCustAppExceptionUtil {

		CallableStatement cstmtAssignments = null;
		ResultSet rsAssignments = null;
		ArrayList alAssignments = new ArrayList();

		// Get Connection
		CanonCustAppDBUtil connAssignments = new CanonCustAppDBUtil();
		Connection connAssignmentsConnection = (Connection) (connAssignments
				.getConnection());

		try {
			cstmtAssignments = connAssignmentsConnection
					.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_assignment_details(?,?,?,?)}");
			cstmtAssignments.setInt(1, iOrgId);
			cstmtAssignments.setInt(2, iTicketId);
			cstmtAssignments.setInt(3, iLineId);
			cstmtAssignments.registerOutParameter(4, OracleTypes.CURSOR);
			cstmtAssignments.execute();

			rsAssignments = (ResultSet) cstmtAssignments.getObject(4);

			while (rsAssignments.next()) {
				Canon_E193_AssignmentPObj objAssignments = new Canon_E193_AssignmentPObj();
				objAssignments.setILineId(rsAssignments.getInt(1));
				objAssignments.setILineNum(rsAssignments.getInt(2));
				objAssignments.setStrLineStatus(rsAssignments.getString(3));
				objAssignments.setIAssignId(rsAssignments.getInt(4));
				objAssignments.setStrAssignStatus(rsAssignments.getString(5));
				objAssignments.setStrAssignToRes(rsAssignments.getString(6));
				objAssignments.setStrAssignToRole(rsAssignments.getString(7));
				objAssignments.setStrAssignToDept(rsAssignments.getString(8));
				objAssignments.setStrAssignByRes(rsAssignments.getString(9));
				objAssignments.setStrAssignByRole(rsAssignments.getString(10));
				objAssignments.setStrAssignByDept(rsAssignments.getString(11));
				objAssignments.setStrCreatedBy(rsAssignments.getString(12));
				objAssignments.setStrCreatedDate(rsAssignments.getString(13));
				objAssignments.setStrLastUpdtBy(rsAssignments.getString(14));
				objAssignments.setStrLastUpdtDate(rsAssignments.getString(15));
				objAssignments.setStrStatusTime(rsAssignments.getString(16));
				alAssignments.add(objAssignments);
			}

			cstmtAssignments.close();
			rsAssignments.close();
			connAssignmentsConnection.close();
			connAssignments.releaseConnection();
		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:getAssignments()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:getAssignments()"));
		} finally {
			try {
				if (cstmtAssignments != null)
					cstmtAssignments.close();
				if (rsAssignments != null)
					rsAssignments.close();
				if (connAssignmentsConnection != null)
					connAssignmentsConnection.close();
				if (connAssignments != null)
					connAssignments.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:getAssignments()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:getAssignments()"));
			}
		}
		return alAssignments;
	}

	// this is add for consoldiated Bill Generate Status

	public void updateConsolidateSts(int iOrgId, int ticketId, String conBillSts)
			throws CanonCustAppExceptionUtil {
		boolean isTrue = false;
		// Get Connection
		CanonCustAppDBUtil connNonBillUtil = null;
		Connection connNonBill = null;
		CallableStatement pCall = null;
		try {
			connNonBillUtil = new CanonCustAppDBUtil();
			connNonBill = (Connection) (connNonBillUtil.getConnection());

			pCall = connNonBill
					.prepareCall("{call canon_e193_cs_evolution_pkg.update_cs_headers(?,?,?,?)}");

			pCall.setInt(1, iOrgId);
			pCall.setInt(2, ticketId);
			pCall.setString(3, conBillSts);
			pCall.registerOutParameter(4, OracleTypes.VARCHAR);
			pCall.execute();
			String iErrorLC = pCall.getString(4);
			if ("N".equalsIgnoreCase(iErrorLC))
				isTrue = false;
			else
				isTrue = true;
			if (!isTrue)
				throw new CanonCustAppExceptionUtil(100001,
						"update updateConsolidateSts failed",
						"Class: Canon_E193_Assignment, Method:updateConsolidateSts()");
		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:updateConsolidateSts()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:updateConsolidateSts()"));
		} finally {
			try {
				if (pCall != null)
					pCall.close();
				if (connNonBillUtil != null)
					connNonBillUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:updateConsolidateSts()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:updateConsolidateSts()"));
			}
		}
		// return isTrue;
	}
	
	//Start changes for S21 by Mangala
	
	public static void printRes( Object[] resObj){
		
	      
		  for (int k = 0; k < resObj.length; k++) {
			   
			  if(resObj[k]!=null) {
						  if(resObj[k] instanceof String){
							  System.out.println(k + " : " +resObj[k]);
						  }else if( resObj[k] instanceof List){
							  
							  List l = (ArrayList) resObj[k] ;
							      for(int j=0;j<l.size();j++)
							       System.out.println( k+" : "+ ( l.get(j) ) .toString() );
						            }
					  
                       else{
							  
							  System.out.println(k + " : "+ resObj[k]);
						    } 
						  
					  
				  }	 else{
					  
					  System.out.println(k + " : "+ resObj[k]);
				  } 
		  }	
	}
	
	public  Object[] getCrDtls(int pLineId, String pUpdatedBy){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     
	     Object[] resObj = new Object[12];
	    // util.logMsg(true,clsName+".getCallDetails", " Getting C")
	     System.out.println("getCrDtls :- pLineId "+  pLineId + " pUpdatedBy : " + pUpdatedBy);
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = (CallableStatement) connection.prepareCall(HAIL_CREDIT_REBILL);
			  if (cstmt != null) {
				
				  cstmt.setInt(1,pLineId);
				  cstmt.setString(2,pUpdatedBy);
				  cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
				  cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
				  cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
				  cstmt.registerOutParameter(6, OracleTypes.VARCHAR);
				  cstmt.registerOutParameter(7, OracleTypes.VARCHAR);
				  cstmt.registerOutParameter(8, OracleTypes.NUMBER);
				  cstmt.registerOutParameter(9, OracleTypes.VARCHAR);
				  cstmt.registerOutParameter(10, OracleTypes.VARCHAR);
				  cstmt.registerOutParameter(11, OracleTypes.VARCHAR); // Newly Added for "custIncdtAsgPsnCd"
				  cstmt.registerOutParameter(12, OracleTypes.ARRAY, SCH_NAME+".CANON_E218_MTR_READ_CHNG_TBL");
				  cstmt.registerOutParameter(13, OracleTypes.ARRAY, SCH_NAME+".CANON_E218_BASE_CHANGES_TBL");
				  cstmt.registerOutParameter(14, OracleTypes.ARRAY, SCH_NAME+".CANON_E218_PRICE_CHANGES_TBL");
	              Map map = (Map) connection.getTypeMap();
	              map.put(SCH_NAME+".CANON_E218_MTR_READ_CHNG_REC", Canon_E193_CRMeterObj.class); 
	              map.put(SCH_NAME+".CANON_E218_BASE_CHANGES_REC", Canon_E193_CRBaseObj.class);
	              map.put(SCH_NAME+".CANON_E218_PRICE_CHANGES_REC", Canon_E193_CRPriceObj.class);
	              
	              cstmt.execute();
	              int outStrIndx=3;
	   	           int i=0;
	   	              for(;outStrIndx<15;outStrIndx++) // Value modified 14 to 15 for Defect#17205
	   	              {
	   	            	//System.out.println("Indx:"+outStrIndx);
	   	            	  if(outStrIndx<8 || outStrIndx==9 ||outStrIndx==10 ||outStrIndx==11) // Added another ||outStrIndx==11 for Defect#17205
	   	            	  {
	   	            		resObj[i] = cstmt.getString(outStrIndx)==null?"":cstmt.getString(outStrIndx);
	  	            	    //System.out.println("Indx_1:"+resObj[i]);	 
	   	            	  }
	   	            	  else if(outStrIndx==8)
	   	            	  {
	   	            		resObj[i] = cstmt.getInt(outStrIndx);
	   	            		//System.out.println("Indx_2:"+resObj[i]);  
	   	            	  }
	   	            	  else{
	   	            		Object listObj[] = null;
							if (cstmt.getObject(outStrIndx) != null) {
								listObj = (Object[]) ((Array) cstmt.getObject(outStrIndx)).getArray();
								//System.out.println("---####@@@---" +listObj.toString());
							}	
							
							if(outStrIndx==12) //// Value Modified 11 To 12 for Defect#17205
							{
								//System.out.println("---####---");
								List<Canon_E193_CRMeterObj> metertList = new ArrayList<Canon_E193_CRMeterObj>();
								if(listObj!=null && listObj.length>0)
			    					  for (int k = 0; k < listObj.length; k++) {
			    						  if(listObj[k]!=null){
			    						   //System.out.println(" MetertList!!! "+listObj[k]);
			    						   metertList.add((Canon_E193_CRMeterObj)listObj[k]);	
			    						  }
			    				         }
								resObj[i]=metertList;
								//System.out.println("@@@!!!!!_12 " + resObj[1]+ " , " +resObj[2] + " , " +resObj[3]);
							}
							else if(outStrIndx==13) // Value Modified 12 To 13 for Defect#17205
							{
								List<Canon_E193_CRBaseObj> baseList = new ArrayList<Canon_E193_CRBaseObj>();
								if(listObj!=null && listObj.length>0)
			    					  for (int k = 0; k < listObj.length; k++) {
			    						  if(listObj[k]!=null){
			    						   //System.out.println(" BaseList!!! "+listObj[k]);
			    						   baseList.add((Canon_E193_CRBaseObj)listObj[k]);	
			    						  }
			    				         }
								resObj[i]=baseList;
								//System.out.println("@@@!!!!!_13 " + resObj[1]+ " , " +resObj[2]);
							}
							else if(outStrIndx==14) // Value Modified 13 To 14 for Defect#17205
							{
								List<Canon_E193_CRPriceObj> priceList = new ArrayList<Canon_E193_CRPriceObj>();
								if(listObj!=null && listObj.length>0)
			    					  for (int k = 0; k < listObj.length; k++) {
			    						  if(listObj[k]!=null){
			    						   //System.out.println(" PriceList!!! "+listObj[k]); 
			    						   priceList.add((Canon_E193_CRPriceObj)listObj[k]);
			    						  }
			    				         }
								resObj[i]=priceList;
								//System.out.println("@@@!!!!!_14 " + resObj[1]+ " , " +resObj[2]+ " , " +resObj[3]);
							}
	   	            	  }
	   	            	  i++;
	   	              }	              
	              util.logMsg(false,clsName+".getCrDtls", "getCrDtls OUT PARAMETERS  "); 
	              //printRes(resObj);
	        }else {
	        	util.logMsg(true,clsName+".getCrDtls", " CallableStatement IS NULL");
			}
			} else {
				util.logMsg(true,clsName+".getCrDtls", " Connection IS NULL");
		    }
			
	     } catch (Exception ex) {
	    	 util.logMsg(true,clsName+".getCrDtls", ex.getMessage());
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			 util.logMsg(true,clsName+".getCrDtls", exp.getMessage());
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 util.logMsg(true,clsName+".getCallDetails", ex.getMessage());
	    		 }
	    	 }
	    	}
		  return resObj;		
	} 
	
	/*public boolean lineUpdate(int lineId, String retCode, String errMsg,
			Canon_E193_TicketHeaderObj[] errTbl) throws CanonCustAppExceptionUtil {
		boolean isTrue = false;
		// Get Connection
		CanonCustAppDBUtil connectionheaderUpdate = null;
		Connection connheaderUpdate = null;
		CallableStatement cstmtheaderUpdate = null;
		try {
			connectionheaderUpdate = new CanonCustAppDBUtil();
			connheaderUpdate = (Connection) (connectionheaderUpdate
					.getConnection());

			cstmtheaderUpdate = connheaderUpdate
					.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.ticket_status_header_update(?,?,?,?,?)}");

			cstmtheaderUpdate.setInt(1, iOrgId);
			cstmtheaderUpdate.setInt(2, iTicketId);
			cstmtheaderUpdate.setString(3, strStatus);
			cstmtheaderUpdate.setString(4, iUpdatedBy);

			cstmtheaderUpdate.registerOutParameter(5, OracleTypes.NUMBER);

			cstmtheaderUpdate.execute();

			int iErrorLC = cstmtheaderUpdate.getInt(5);

			if (iErrorLC == -1)
				isTrue = false;
			else
				isTrue = true;

			if (!isTrue)
				throw new CanonCustAppExceptionUtil(100001,
						"update lines and assignements failed",
						"Class: Canon_E193_Assignment, Method:headerUpdate()");

		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:headerUpdate()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:headerUpdate()"));
		} finally {
			try {
				if (cstmtheaderUpdate != null)
					cstmtheaderUpdate.close();
				if (connheaderUpdate != null)
					connheaderUpdate.close();
				if (connectionheaderUpdate != null)
					connectionheaderUpdate.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:headerUpdate()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:headerUpdate()"));
			}
		}
		return isTrue;
	}*/
	//End changes for S21 by Mangala

	public void updateNotes(int iOrgId, int lineId, String userId)
			throws CanonCustAppExceptionUtil {
		boolean isTrue = false;
		// Get Connection
		CanonCustAppDBUtil connNonBillUtil = null;
		Connection connNonBill = null;
		CallableStatement pCall = null;
		try {
			connNonBillUtil = new CanonCustAppDBUtil();
			connNonBill = (Connection) (connNonBillUtil.getConnection());

			pCall = connNonBill
					.prepareCall("{call canon_e193_cs_evolution_pkg.add_notes(?,?,?,?,?,?)}");

			pCall.setInt(1, iOrgId);
			pCall.setInt(2, lineId);
			pCall.setString(3, userId);
			pCall.setString(4, "Please Review");
			pCall.registerOutParameter(5, OracleTypes.VARCHAR);
			pCall.registerOutParameter(6, OracleTypes.VARCHAR);
			pCall.execute();
			String strNotesId = pCall.getString(5);
			String strRetStatus = pCall.getString(6);
		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:updateNotes()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:updateNotes()"));
		} finally {
			try {
				if (pCall != null)
					pCall.close();
				if (connNonBillUtil != null)
					connNonBillUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:updateNotes()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:updateNotes()"));
			}
		}
		// return isTrue;
	}
	/**
	 * Added By Basudev A
	 * CreditRebill API in update mode. Pass the status as Cancelled in the API.
	 * @param iTicketId
	 * @return A ArrayList which contains output for query
	 */
	public ArrayList cancelCreditRebillForCloseTicket(int iTicketId) throws CanonCustAppExceptionUtil{
		// Get Connection
		      //  boolean isTrue = false;
				CanonCustAppDBUtil connNonBillUtil = null;
				Connection connNonBill = null;
				CallableStatement pCall = null;
				ArrayList allOutParameterList = new ArrayList();
				try {
					connNonBillUtil = new CanonCustAppDBUtil();
					connNonBill = (Connection) (connNonBillUtil.getConnection());

					pCall = connNonBill.prepareCall("{call CANON_E193_CS_SQLS_PKG.CANCEL_CR_REBILL(?,?,?)}");
					pCall.setInt(1, iTicketId);
					pCall.registerOutParameter(2, OracleTypes.NUMBER);
					pCall.registerOutParameter(3, OracleTypes.NUMBER);
					pCall.execute();
					int svcCrRebilPk =    pCall.getInt(2);
					int svcCrRebilDtlPk = pCall.getInt(3);
					System.out.println("Ticket Id:- " + iTicketId +"  svcCrRebilPk!!! " + svcCrRebilPk + " svcCrRebilDtlPk!!! "+ svcCrRebilDtlPk);
					allOutParameterList.add(new Integer(svcCrRebilPk));
					allOutParameterList.add(new Integer(svcCrRebilDtlPk));
				} catch (CanonCustAppExceptionUtil csExp) {
					throw (csExp);
				} catch (SQLException eSQLExp) {
					throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
							"Class: Canon_E193_Assignment, Method:getCancelStatusFlag()"));
				} catch (Exception eExp) {
					throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
							"Class: Canon_E193_Assignment, Method:getCancelStatusFlag()"));
				} finally {
					try {
						if (pCall != null)
							pCall.close();
						if (connNonBillUtil != null)
							connNonBillUtil.releaseConnection();
					} catch (CanonCustAppExceptionUtil csExp) {
						throw (csExp);
					} catch (SQLException eSQLExp) {
						throw (new CanonCustAppExceptionUtil(100001,
								eSQLExp.toString(),
								"Class: Canon_E193_Assignment, Method:getCancelStatusFlag()"));
					} catch (Exception eExp) {
						throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
								"Class: Canon_E193_Assignment, Method:getCancelStatusFlag()"));
					}
				}
		return allOutParameterList;
	} // End.
	
	public void sendEmailNotificationToUser(int iTicketId, String status, String userId) throws CanonCustAppExceptionUtil {
		
		// Get Connection
		CanonCustAppDBUtil connNonBillUtil = null;
		Connection connNonBill = null;
		CallableStatement pCall = null;
		try {
			connNonBillUtil = new CanonCustAppDBUtil();
			connNonBill = (Connection) (connNonBillUtil.getConnection());

			pCall = connNonBill.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.GET_USER_NOTIF_DTLS(?,?,?,?,?,?,?)}");			
			pCall.setInt(1, iTicketId);
			pCall.setString(2, status);
			pCall.registerOutParameter(3, OracleTypes.VARCHAR);
			pCall.registerOutParameter(4, OracleTypes.VARCHAR);
			pCall.registerOutParameter(5, OracleTypes.VARCHAR);
			pCall.registerOutParameter(6, OracleTypes.VARCHAR);
			pCall.registerOutParameter(7, OracleTypes.DATE);
			pCall.execute();
			String receiptEmail=pCall.getString(6);	
			String receiptEmails[]=null;
			if(receiptEmail != null && !"".equalsIgnoreCase(receiptEmail.trim()))
			{
				StringTokenizer receiptTokens = new StringTokenizer(
						receiptEmail, ",");
				receiptEmails = new String[receiptTokens
						.countTokens()];
				int emailCount = 0;
				while (receiptTokens.hasMoreTokens()) {
					receiptEmails[emailCount] = receiptTokens.nextToken();
					emailCount++;
				}
								
			
				String custContactName=pCall.getString(5);
				if(custContactName == null || "".equalsIgnoreCase(custContactName))
				{
					custContactName="User";
				}
				
				DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
				Date date = new Date();
				String subjectInit = "";
				try {
					subjectInit = CanonCommonUtil
							.checkNull(CanonCustomProfile
									.getUserProfileValue("CANON_CUSTOM_EMAIL_SUBJECT_INIT"));
				} catch (Exception exception) {
					System.err
							.println("Error occured from getting CANON_CUSTOM_EMAIL_SUBJECT_INIT "
									+ exception.getMessage());
				}
				
				String emailSubject="Canon Solutions America - Open Customer Care Inquiry Confirmation";
				String emailBody="Dear "+custContactName+", \n\nYour Customer Care Inquiry was opened on "+dateFormat.format(date)+" and the confirmation ticket number is "+iTicketId+ "."+
						"\nAn associate from Canon Solutions America will be in touch with you regarding your request. \n"+
						"\nIf you would like to check the status of your request, please call our Customer Service Department at 1-800-613-2228 with the above confirmation number."+
						"\n\nThank You, \nCustomer Service \nCanon Solutions America, Inc.";
				Email email = CanonEmailUtil.createSimpleEmail(); 
				email.setSubject(subjectInit + emailSubject);
				if (receiptEmails != null)
				{
					email.setMsg(emailBody).addTo(receiptEmails);
					try {
						email.send();
					} catch (org.apache.commons.mail.EmailException eex) {
						eex.printStackTrace();
						logException("CUSTOMER_OPEN_NOTIFICATION", "TICKET# "+iTicketId, 
								null, "Resource# " +userId, null, null, eex.getMessage());
					}
				}
			
			}
		} catch (CanonCustAppExceptionUtil csExp) {
			csExp.printStackTrace();
			logException("CUSTOMER_OPEN_NOTIFICATION", "TICKET# "+iTicketId, 
					null, "Resource# " +userId, null, null, csExp.getMessage());
			throw (csExp);
		} catch (SQLException eSQLExp) {
			eSQLExp.printStackTrace();
			logException("CUSTOMER_OPEN_NOTIFICATION", "TICKET# "+iTicketId, 
					null, "Resource# " +userId, null, null, eSQLExp.getMessage());
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:sendEmailNotificationToUser()"));
		} catch (Exception eExp) {
			eExp.printStackTrace();
			logException("CUSTOMER_OPEN_NOTIFICATION", "TICKET# "+iTicketId, 
					null, "Resource# " +userId, null, null, eExp.getMessage());
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:sendEmailNotificationToUser()"));
		} finally {
			try {
				if (pCall != null)
					pCall.close();
				if (connNonBillUtil != null)
					connNonBillUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:sendEmailNotificationToUser()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:sendEmailNotificationToUser()"));
			}
		}
	}
	
	
public void sendEmailNotificationToAsignee(int iTicketId,int iLineId, String requestUrl,String userId) throws CanonCustAppExceptionUtil {
		
		// Get Connection
		CanonCustAppDBUtil connNonBillUtil = null;
		Connection connNonBill = null;
		CallableStatement pCall = null;
		try {
			System.out.println("Canon_E193_Assignment sendEmailNotificationToAsignee method iTicketId : " 
					+ iTicketId + " iLineId : " + iLineId + " requestUrl : " + requestUrl);	
			connNonBillUtil = new CanonCustAppDBUtil();
			connNonBill = (Connection) (connNonBillUtil.getConnection());
			pCall = connNonBill.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.GET_ASSIGNEE_NOTIF_DTLS(?,?,?,?,?,?,?,?,?)}");			
			pCall.setInt(1, iTicketId);
			pCall.setInt(2, iLineId);
			pCall.registerOutParameter(3, OracleTypes.VARCHAR);
			pCall.registerOutParameter(4, OracleTypes.VARCHAR);
			pCall.registerOutParameter(5, OracleTypes.VARCHAR);
			pCall.registerOutParameter(6, OracleTypes.VARCHAR);
			pCall.registerOutParameter(7, OracleTypes.VARCHAR);
			pCall.registerOutParameter(8, OracleTypes.VARCHAR);
			pCall.registerOutParameter(9, OracleTypes.VARCHAR);
			pCall.execute();
			String receiptEmail=pCall.getString(6);
			String receiptEmails[]=null;
			if(receiptEmail != null &&  !"".equalsIgnoreCase(receiptEmail.trim()))
			{
				StringTokenizer receiptTokens = new StringTokenizer(
						receiptEmail, ",");
				receiptEmails = new String[receiptTokens
						.countTokens()];
				int emailCount = 0;
				while (receiptTokens.hasMoreTokens()) {
					receiptEmails[emailCount] = receiptTokens.nextToken();
					emailCount++;
				}	
			
				String l_category=pCall.getString(3);
				if(l_category==null || "".equalsIgnoreCase(l_category))
					l_category=" ";
				String l_sub_category=pCall.getString(4);
				if(l_sub_category==null ||"".equalsIgnoreCase(l_sub_category))
					l_sub_category=" ";
				String l_created_by_name=pCall.getString(7);
				if(l_created_by_name==null ||"".equalsIgnoreCase(l_created_by_name))
					l_created_by_name="User";
				String l_reason=pCall.getString(5);
				if(l_reason==null ||"".equalsIgnoreCase(l_reason))
					l_reason=" ";
				
				String ticket_created_by_name=pCall.getString(8);
				if(ticket_created_by_name==null ||"".equalsIgnoreCase(ticket_created_by_name))
					ticket_created_by_name="User";
				
				String severity = pCall.getString(9);
				if(severity==null ||"".equalsIgnoreCase(severity))
					severity="NORMAL";
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
				Date date = new Date();
				 
				String subjectInit = "";
				try {
					subjectInit = CanonCommonUtil
							.checkNull(CanonCustomProfile
									.getUserProfileValue("CANON_CUSTOM_EMAIL_SUBJECT_INIT"));
				} catch (Exception exception) {
					System.err
							.println("Error occured from getting CANON_CUSTOM_EMAIL_SUBJECT_INIT "
									+ exception.getMessage());
				}
				
				String urlContent = requestUrl != null ? "\nPlease login using the link provided:\n"+
						"\n"+requestUrl.split("e193")[0]+"e193/jsp/canon_E193_csEIHome.jsp" : "" ;
				 
				String emailBody="This is an Auto Notification Email. Please refer to the information below to query or update "
						+ "\nyour ticket using the Canon Customer Care Application. "+
						"\n\nTicket "+iTicketId+"- ("+l_category +" and "+l_sub_category+") assigned by \n"+		 
						l_created_by_name+" for Reason Code "+l_reason+" on "+dateFormat.format(date)+ ".\n"
				            +urlContent +
				            "\n\nThank You, \nCustomer Service";	
				 String emailSubject="Canon Solutions America - Open Customer Care Inquiry Confirmation - " + severity;
				Email email = CanonEmailUtil.createSimpleEmail(); 
				email.setSubject(subjectInit + emailSubject);
				if(receiptEmails!=null)
				{
					email.setMsg(emailBody).addTo(receiptEmails);
					try {
						email.send();
					} catch (org.apache.commons.mail.EmailException eex) {
						eex.printStackTrace();
						logException("ASSINGNEE_OPEN_NOTIFICATION", "TICKET# " +iTicketId, 
								"Line# " + iLineId , "Resource# " +userId, null, null, eex.getMessage());
					}
				}
				//System.out.println("Ticker creation Asignee email End");	
			}
		} catch (CanonCustAppExceptionUtil csExp) {
			csExp.printStackTrace();
			logException("ASSINGNEE_OPEN_NOTIFICATION", "TICKET# " + iTicketId, 
					"Line# " + iLineId, "Resource# " +userId, null, null, csExp.getMessage());
			throw (csExp);
		} catch (SQLException eSQLExp) {
			eSQLExp.printStackTrace();
			logException("ASSINGNEE_OPEN_NOTIFICATION", "TICKET# " + iTicketId, 
					"Line# " + iLineId, "Resource# " +userId, null, null, eSQLExp.getMessage());
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(),
					"Class: Canon_E193_Assignment, Method:sendEmailNotificationToAsignee()"));
		} catch (Exception eExp) {
			eExp.printStackTrace();
			logException("ASSINGNEE_OPEN_NOTIFICATION", "TICKET# " + iTicketId, 
					"Line# " + iLineId, "Resource# " + userId, null, null, eExp.getMessage());
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
					"Class: Canon_E193_Assignment, Method:sendEmailNotificationToAsignee()"));
		} finally {
			try {
				if (pCall != null)
					pCall.close();
				if (connNonBillUtil != null)
					connNonBillUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001,
						eSQLExp.toString(),
						"Class: Canon_E193_Assignment, Method:updateNotes()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(),
						"Class: Canon_E193_Assignment, Method:updateNotes()"));
			}
		}
	}

}
