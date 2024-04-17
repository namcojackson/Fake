package com.canon.oracle.custapp.custinq.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import oracle.jdbc.OracleTypes;
//import oracle.jdbc.driver.OracleTypes;
import oracle.sql.ARRAY;
import canon.apps.common.CanonConstants;

import com.canon.oracle.custapp.custinq.beans.Canon_E193_CRSummaryObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketHeaderObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketStatusObj;
import com.canon.oracle.custapp.util.CanonCustAppDBUtil;
import com.canon.oracle.custapp.util.CanonCustAppExceptionUtil;
import com.canon.oracle.custapp.util.CanonCustAppUtil;

/**
 * Canon_E193_Ticket Retrieves Ticket Information from database
 * Creation date:
 * @author:
 * <pre>
 * Flag  Date       By         Description
 * ----  ---------- ---------- ---------------------------------------------
 * 18-Dec-2006  Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 * 29-Jan-2016  Mangala Shenoy     Modified for S21 Changes
 * </pre>
 */

public class Canon_E193_Ticket {

	/**
	 * Canon_E193_Ticket constructor.
	 */
	public Canon_E193_Ticket() {
		super();
	}

	/**
	 * This method will return the list of history records.
	 * <p>
	 * @return java.util.ArrayList.
	 * @param strAcctType java.lang.String.
	 * @param strAcctValue java.lang.String.
	 * @param strCustAcctId int.
	 * @param iOrgId int.
	 * @param strOrderBy java.lang.String.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	public ArrayList getTicketHistory(String strSearchType, String strSearchValue, int iCustAcctId, int iOrgId, String strOrderName, String strOrderBy, int iPageNo, int iTotPageNo) throws CanonCustAppExceptionUtil {
		ArrayList alHistory = new ArrayList();
		//Get Connection
		CanonCustAppDBUtil connTicketConnection = null;
		Connection connHistConnection = null;
	    CallableStatement pCall = null;
	    ResultSet rsTicket = null;
	    Canon_E193_TicketHeaderObj objTicket = null;
	    try {
	    	System.out.println("in getTicketHistory strSearchType "+strSearchType + " strSearchValue " + strSearchValue
	    			+ " iCustAcctId " + iCustAcctId);
	    	connTicketConnection = new CanonCustAppDBUtil();
	    	connHistConnection = (Connection)(connTicketConnection.getConnection());
	    	pCall = connHistConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_history(?,?,?,?,?,?,?,?,?)}");
	    	pCall.setString(1, strSearchType);
	    	pCall.setString(2, strSearchValue);
	    	pCall.setInt(3, iCustAcctId);
	    	pCall.setInt(4, iOrgId);
	    	pCall.setString(5, strOrderName);
	    	pCall.setString(6, strOrderBy);
	    	pCall.setInt(7, iPageNo);
	    	pCall.registerOutParameter(7, OracleTypes.NUMBER);
	    	pCall.setInt(8, iTotPageNo);
	    	pCall.registerOutParameter(8, OracleTypes.NUMBER);
	    	pCall.registerOutParameter(9, OracleTypes.CURSOR);

	    	pCall.execute();

	    	iPageNo = pCall.getInt(7);
	    	alHistory.add(new Integer(iPageNo));
	    	iTotPageNo = pCall.getInt(8);
	    	alHistory.add(new Integer(iTotPageNo));

	    	rsTicket = (ResultSet) pCall.getObject(9);

	    	while(rsTicket.next()) {
	    		objTicket = new Canon_E193_TicketHeaderObj();
	    		objTicket.setTicketNo(rsTicket.getString(1));
	    		objTicket.setCategory(rsTicket.getString(2));
	    		objTicket.setDate(rsTicket.getString(3));
	    		objTicket.setStatus(rsTicket.getString(4));
	    		objTicket.setAcctName(rsTicket.getString(5));
	    		objTicket.setAcctNo(rsTicket.getString(6));
	    		objTicket.setInvoiceNo(rsTicket.getString(7));
	    		objTicket.setContractNo(rsTicket.getString(8));
	    		objTicket.setOrderNo(rsTicket.getInt(9));
	    		objTicket.setIDaysOpen(rsTicket.getInt(10));
	    		objTicket.setAttribute3(rsTicket.getString(11));
	    		alHistory.add(objTicket);
	    	}
	    }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketHistory()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketHistory()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
	    		if(rsTicket != null) rsTicket.close();
	    		if(connTicketConnection != null) connTicketConnection.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketHistory()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketHistory()"));
	    	}
	    }
		return alHistory;
	}

/* Changed For ITG # 154956 By Chandra Sekhar - Start */
	/**
	 * This method will return the list of Open Tickets.
	 * <p>
	 * @return java.util.ArrayList.
	 * @param strAcctType java.lang.String.
	 * @param strAcctValue java.lang.String.
	 * @param strCustAcctId int.
	 * @param iOrgId int.
	 * @param strOrderBy java.lang.String.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	public ArrayList getOpenTickets(String strAcctNumber, int iPageNo, int iTotPageNo) throws CanonCustAppExceptionUtil {
		ArrayList alHistory = new ArrayList();
		//Get Connection
		CanonCustAppDBUtil connTicketConnection = null;
		Connection connHistConnection = null;
	    CallableStatement pCall = null;
	    ResultSet rsTicket = null;
	    Canon_E193_TicketHeaderObj objOpenTicket = null;
	    try {
	    	System.out.println("in getOpenTickets strAcctNumber "+strAcctNumber);
	    	connTicketConnection = new CanonCustAppDBUtil();
	    	connHistConnection = (Connection)(connTicketConnection.getConnection());
	    	pCall = connHistConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.open_tickets(?,?,?,?)}");
	    	pCall.setString(1, strAcctNumber);
	    	pCall.setInt(2, iPageNo);
	    	pCall.registerOutParameter(2, OracleTypes.NUMBER);
	    	pCall.setInt(3, iTotPageNo);
	    	pCall.registerOutParameter(3, OracleTypes.NUMBER);
	    	pCall.registerOutParameter(4, OracleTypes.CURSOR);

	    	pCall.execute();

	    	iPageNo = pCall.getInt(2);
	    	alHistory.add(new Integer(iPageNo));
	    	iTotPageNo = pCall.getInt(3);
	    	alHistory.add(new Integer(iTotPageNo));

	    	rsTicket = (ResultSet) pCall.getObject(4);

	    	while(rsTicket.next()) {
	    		objOpenTicket = new Canon_E193_TicketHeaderObj();
	    		objOpenTicket.setTicketNo(rsTicket.getString(1));
	    		objOpenTicket.setCategory(rsTicket.getString(2));
	    		objOpenTicket.setDate(rsTicket.getString(3));
	    		objOpenTicket.setStatus(rsTicket.getString(4));
	    		objOpenTicket.setAcctName(rsTicket.getString(5));
	    		objOpenTicket.setAcctNo(rsTicket.getString(6));
	    		objOpenTicket.setInvoiceNo(rsTicket.getString(7));
	    		objOpenTicket.setContractNo(rsTicket.getString(8));
	    		objOpenTicket.setOrderNo(rsTicket.getInt(9));
	    		objOpenTicket.setIDaysOpen(rsTicket.getInt(10));
	    		objOpenTicket.setAttribute3(rsTicket.getString(11));
	    		alHistory.add(objOpenTicket);
	    	}
	    }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Ticket, Method:getOpenTickets()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Ticket, Method:getOpenTickets()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
	    		if(rsTicket != null) rsTicket.close();
	    		if(connTicketConnection != null) connTicketConnection.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Ticket, Method:getOpenTickets()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Ticket, Method:getOpenTickets()"));
	    	}
	    }
		return alHistory;
	}
/* Changed For ITG # 154956 By Chandra Sekhar - End */

	/**
	 * This method will return the total count of history records.
	 * <p>
	 * @return int.
	 * @param strAcctType java.lang.String.
	 * @param strAcctValue java.lang.String.
	 * @param strCustAcctId int.
	 * @param iOrgId int.
	 * @param strOrderBy java.lang.String.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	public int getTicketHistoryCount(String strSearchType, String strSearchValue, int iCustAcctId, int iOrgId, String strOrderBy) throws CanonCustAppExceptionUtil {
		int totalCount = 0;
		CanonCustAppDBUtil connTicketConnection = null;
	    PreparedStatement pstmt = null;
	    ResultSet rsTicket = null;
	    try {
	    	System.out.println("in getTicketHistoryCount iCustAcctId "+iCustAcctId + " strSearchType " + strSearchType 
	    			+ " strSearchValue " + strSearchValue);
	    	String query = getQueryCount(strSearchType, strSearchValue, iCustAcctId, iOrgId, strOrderBy);
	    	if(query != null) {
	    		//Get Connection
	    	    connTicketConnection = new CanonCustAppDBUtil();
		    	pstmt = connTicketConnection.getPreparedStmt(query);
		    	rsTicket = connTicketConnection.getResultSet(pstmt);
		    	while(rsTicket.next()) {
		    		totalCount = rsTicket.getInt(1);
		    	}
	    	}
	    }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketHistoryCount()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketHistoryCount()"));
	    }
	    finally {
	    	try {
	    		if(pstmt != null) pstmt.close();
	    		if(rsTicket != null) rsTicket.close();
	    		if(connTicketConnection != null) connTicketConnection.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketHistoryCount()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketHistoryCount()"));
	    	}
	    }
		return totalCount;
	}

	/**
	 * This method will return the total count of query records.
	 * <p>
	 * @return String.
	 * @param strOption java.lang.String.
	 * @param strOptValue java.lang.String.
	 * @param iCustAcctId int.
	 * @param iOrgId int.
	 * @param strOrderBy java.lang.String.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	private String getQueryCount(String strOption, String strOptValue, int iCustAcctId, int iOrgId, String strOrderBy) throws CanonCustAppExceptionUtil {
    	String strQuery = null;
    	try {
    		System.out.println("in getQueryCount iCustAcctId "+iCustAcctId + " strOption " + strOption 
	    			+ " strOptValue " + strOptValue + " strOrderBy " + strOrderBy);
	    	if(strOption != null || iCustAcctId != 0) {
	    		String strTables = " CANON_E193_CS_HEADERS h, CANON_E193_CS_CATEGORIES c ";
	    		if("serialNo".equalsIgnoreCase(strOption)) strTables = " CANON_E193_CS_LINES l, " + strTables;
	    		strQuery = "SELECT count(1) FROM " + strTables + " WHERE h.CAT_ID = c.CAT_ID AND h.ORG_ID = "+iOrgId;
	    		if(strOption != null) {
		    		if(strOption.equalsIgnoreCase("serialNo")) {
		    			strQuery = strQuery + " AND h.TICKET_ID = l.TICKET_ID AND l.SERIAL_NUMBER = '" + strOptValue + "'";
		    		}else if(strOption.equalsIgnoreCase("acctName")){
		    			strQuery = strQuery + " AND h.CUSTOMER_NAME LIKE '" + strOptValue + "'";
		    		}else if(strOption.equalsIgnoreCase("acctNo")){
		    			strQuery = strQuery + " AND h.CUSTOMER_NUMBER = '" + strOptValue + "'";
		    		}else if(strOption.equalsIgnoreCase("invoiceNo")){
		    			strQuery = strQuery + " AND h.INVOICE_NUMBER = '" + strOptValue + "'";
		    		}else if(strOption.equalsIgnoreCase("contractNo")){
		    			strQuery = strQuery + " AND h.CUST_PH_NUMBER = '" + strOptValue + "'";
		    		}else if(strOption.equalsIgnoreCase("orderNo")){
		    			strQuery = strQuery + " AND h.ORDER_NUMBER = '" + strOptValue + "'";
		    		}else if(strOption.equalsIgnoreCase("ticketNo")){
		    			strQuery = strQuery + " AND h.TICKET_NUMBER = '" + strOptValue + "'";
		    		}else if(strOption.equalsIgnoreCase("ownerNo")){
		    			strQuery = strQuery + " AND h.REP_NAME = '" + strOptValue + "'";
		    		}
	    		}
				if(iCustAcctId != 0) strQuery = strQuery + " AND h.CUSTOMER_NUMBER = '" + iCustAcctId + "'";

				if(strOrderBy == null) strQuery = strQuery + " ORDER BY h.TICKET_NUMBER ASC";
				else strQuery = strQuery + " ORDER BY " + strOrderBy;
	    	}
    	}catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Ticket, Method:getQueryCount()"));
	    }
    	return strQuery;
	}

	/**
	 * This method will return the Ticket header and lines information
	 * @return java.util.ArrayList.
	 * @param iOrgId int.
	 * @param iTicketId int..
	 * @param iResId int.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */

	//Start changes for S21 by Mangala
	//public ArrayList getTicketDetails(int iOrgId, int iTicketId, int iResid)
	public ArrayList getTicketDetails(int iOrgId, int iTicketId, String iResid)
	//End Changes for S21 by Mangala
				throws CanonCustAppExceptionUtil {
		System.out.println("in getTicketDetails iTicketId "+iTicketId + " iResid " + iResid);
		CallableStatement cstmtTktDtl = null;
		ArrayList alHeaderLineObj = new ArrayList();

		//Get Connection
		CanonCustAppDBUtil connTktDtl = null;
		Connection connTktDtlConnection = null;
		//Start Changes for S21 by Mangala
		String strHeaderRecName = CanonConstants.SCHEMA_NAME+".CANON_E193_HEADER_REC_TYP";
		String strHeaderTblName = CanonConstants.SCHEMA_NAME+".CANON_E193_HEADER_TBL_TYP";
		String strLineRecName = CanonConstants.SCHEMA_NAME+".CANON_E193_LINE_REC_TYP";
		String strLineTblName = CanonConstants.SCHEMA_NAME+".CANON_E193_LINE_TBL_TYP";
		//String strHeaderRecName = "CANON.CANON_E193_HEADER_REC_TYP";
    	//String strHeaderTblName = "CANON.CANON_E193_HEADER_TBL_TYP";
    	//String strLineRecName = "CANON.CANON_E193_LINE_REC_TYP";
    	//String strLineTblName = "CANON.CANON_E193_LINE_TBL_TYP";
    	String strHeaderBeanName = "com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketHeaderObj";
    	String strLineBeanName = "com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj";
    	//End Changes for S21 by Mangala
		try {
			
			connTktDtl = new CanonCustAppDBUtil();
			connTktDtlConnection = (Connection)(connTktDtl.getConnection());

			cstmtTktDtl = connTktDtlConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.get_ticket_details(?,?,?,?,?)}");

			cstmtTktDtl.setInt(1, iOrgId);
			cstmtTktDtl.setInt(2, iTicketId);
			//Start changes for S21 by Mangala
			//cstmtTktDtl.setInt(3, iResid);
			cstmtTktDtl.setString(3, iResid);
			//End changes for S21 by Mangala
			//cstmtTktDtl.registerOutParameter(4, OracleTypes.ARRAY, strHeaderTblName);
			// Changes for ITG 44555 calling ref cursor in place of Tbl of pl/sql record type Sachin
			cstmtTktDtl.registerOutParameter(4, OracleTypes.CURSOR);
			cstmtTktDtl.registerOutParameter(5, OracleTypes.ARRAY, strLineTblName);

			cstmtTktDtl.execute();

			//ARRAY arrTktHeader = cstmtTktDtl.getARRAY(4);
			// Changes for ITG 44555  Sachin
			ResultSet rsTicket = (ResultSet) cstmtTktDtl.getObject(4);



			Canon_E193_TicketHeaderObj objHeaderObj = new  Canon_E193_TicketHeaderObj();

			if(rsTicket.next()) {

				//VJ Start
				objHeaderObj.setTicketId(rsTicket.getInt(1));
				objHeaderObj.setTicketNo(rsTicket.getString(2));
				objHeaderObj.setCatId(rsTicket.getInt(3));
				objHeaderObj.setStatus(rsTicket.getString(4));
				objHeaderObj.setRecurring(rsTicket.getString(5));
				objHeaderObj.setBillingIssue(rsTicket.getString(6));
				objHeaderObj.setOrgId(rsTicket.getInt(7));
				objHeaderObj.setCustAcctId(rsTicket.getInt(8));
				objHeaderObj.setCustomerName(rsTicket.getString(9));
				objHeaderObj.setCustomerNo(rsTicket.getString(10));
				objHeaderObj.setInvoiceNo(rsTicket.getString(11));
				objHeaderObj.setContractNo(rsTicket.getString(12));
				objHeaderObj.setContractModifier(rsTicket.getString(13));
				objHeaderObj.setOrderNo(rsTicket.getInt(14));
				objHeaderObj.setOrderType(rsTicket.getString(15));
				objHeaderObj.setOrigName(rsTicket.getString(16));
				objHeaderObj.setOrigPhNo(rsTicket.getString(17));
				objHeaderObj.setOrigEmail(rsTicket.getString(18));
				objHeaderObj.setOrigType(rsTicket.getString(19));
				objHeaderObj.setCustContact(rsTicket.getString(20));
				objHeaderObj.setCustPhNo(rsTicket.getString(21));
				objHeaderObj.setCustEmail(rsTicket.getString(22));
				objHeaderObj.setJtfNotesFlag(rsTicket.getString(23));
				objHeaderObj.setAttribute1(rsTicket.getString(24));
				objHeaderObj.setAttribute2(rsTicket.getString(25));
				objHeaderObj.setAttribute3(rsTicket.getString(26));
				objHeaderObj.setAttribute4(rsTicket.getString(27));
				objHeaderObj.setAttribute5(rsTicket.getString(28));

				/* ITG: 73987 : Begin */
				int attrIndex = 29;
				objHeaderObj.setAttribute6(rsTicket.getString(attrIndex++));
				objHeaderObj.setAttribute7(rsTicket.getString(attrIndex++));
				objHeaderObj.setAttribute8(rsTicket.getString(attrIndex++));
				objHeaderObj.setAttribute9(rsTicket.getString(attrIndex++));
				objHeaderObj.setAttribute10(rsTicket.getString(attrIndex++));
				objHeaderObj.setAttribute11(rsTicket.getString(attrIndex++));
				objHeaderObj.setAttribute12(rsTicket.getString(attrIndex++));
				objHeaderObj.setAttribute13(rsTicket.getString(attrIndex++));
				objHeaderObj.setAttribute14(rsTicket.getString(attrIndex++));
				objHeaderObj.setAttribute15(rsTicket.getString(attrIndex++));

				/* ITG: 73987 : End */

                /* ITG: 73987 : Begin */
				objHeaderObj.setOwnerRoleId(rsTicket.getString(39));
				//Start Changes for S21 by Mangala
				//objHeaderObj.setOwnerResId(rsTicket.getInt(40));
				objHeaderObj.setOwnerResId(rsTicket.getString(40));
				//End Changes for S21 by Mangala
				objHeaderObj.setOwnerDeptCode(rsTicket.getString(41));
				objHeaderObj.setCreatedBy(rsTicket.getString(42));
				objHeaderObj.setCreatedByRoleId(rsTicket.getString(43));
				//Start changes for S21 by Mangala
				//objHeaderObj.setCreatedByResId(rsTicket.getInt(44));
				objHeaderObj.setCreatedByResId(rsTicket.getString(44));
				//End changes for S21 by Mangala
				objHeaderObj.setCreatedByDeptCode(rsTicket.getString(45));
				objHeaderObj.setCreationDate(rsTicket.getString(46));
				objHeaderObj.setLastUpdateDate(rsTicket.getString(47));
				objHeaderObj.setLastUpdatedBy(rsTicket.getString(48));
				objHeaderObj.setCategory(rsTicket.getString(49));
				objHeaderObj.setCatIdDesc(rsTicket.getString(50));
				objHeaderObj.setCreatedByResName(rsTicket.getString(51));
				objHeaderObj.setOwnerResName(rsTicket.getString(52));
                /* ITG: 73987 : End */
				objHeaderObj.setCollectorName(rsTicket.getString(53));
				objHeaderObj.setresolutionCode(rsTicket.getString(54));
				//VJ End
				objHeaderObj.setsendEmailFlag(rsTicket.getString(55));

	    	}


			// Changes for ITG 44555  Sachin

			Object[] objHeader = new Object[1];

			//Object[] objHeader = (Object[])CanonCustAppUtil.getRecordObject(arrTktHeader, strHeaderBeanName, connTktDtlConnection, strHeaderRecName);

			objHeader[0] = objHeaderObj;

			ARRAY arrTktLine = (ARRAY) cstmtTktDtl.getArray(5);

			Object[] objLine = (Object[])CanonCustAppUtil.getRecordObject(arrTktLine, strLineBeanName, connTktDtlConnection, strLineRecName);

	    	// Add header object			

			if(objHeader.length > 0)
			{
	    		objHeaderObj = (Canon_E193_TicketHeaderObj)objHeader[0];
	    		alHeaderLineObj.add(objHeaderObj);

				// Add line object
				for(int i=0; i < objLine.length; i++) {
					Canon_E193_TicketLinesObj objLineObj = new  Canon_E193_TicketLinesObj();					
					objLineObj = (Canon_E193_TicketLinesObj)objLine[i];
					alHeaderLineObj.add(objLineObj);
				}
			}

		}catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		}catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketDetails()"));
		}catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketDetails()"));
		}
		finally {
			try {
				if(cstmtTktDtl != null) cstmtTktDtl.close();
				if(connTktDtlConnection != null) connTktDtlConnection.close();
				if(connTktDtl != null) connTktDtl.releaseConnection();
			}catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			}catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketDetails()"));
			}catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketDetails()"));
			}
		}
		return alHeaderLineObj;
	}

	/**
	 * This method will return the Ticket header and lines information
	 * @return java.util.ArrayList.
	 * @param strValueSetType String.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	public ArrayList getTicketStatus() throws CanonCustAppExceptionUtil {
		System.out.println("in getTicketStatus");
		ArrayList alTktSts = new ArrayList();
		ArrayList alTktHdrSts = new ArrayList();
		ArrayList alTktLineSts = new ArrayList();

		CanonCustAppDBUtil connTktSts = null;
		Connection connTktStsConn = null;

	    CallableStatement cstmtTktHdrSts = null;
	    ResultSet rsTktHdrSts = null;
	    CallableStatement cstmtTktLineSts = null;
	    ResultSet rsTktLineSts = null;

	    try {
	    	connTktSts = new CanonCustAppDBUtil();
	    	connTktStsConn = (Connection)(connTktSts.getConnection());

			// Header Status
	    	cstmtTktHdrSts = connTktStsConn.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_ticket_status(CANON_E193_CS_SQLS_PKG.g_ticket_header_status_vv,?)}");
	    	cstmtTktHdrSts.registerOutParameter(1, OracleTypes.CURSOR);

	    	cstmtTktHdrSts.execute();

	    	rsTktHdrSts = (ResultSet) cstmtTktHdrSts.getObject(1);

	    	while(rsTktHdrSts.next()) {
				Canon_E193_TicketStatusObj objTktHdrSts = new Canon_E193_TicketStatusObj();
				objTktHdrSts.setIAttribute1(rsTktHdrSts.getInt(1));
				objTktHdrSts.setStrAttribute2(rsTktHdrSts.getString(2));
	    		alTktHdrSts.add(objTktHdrSts);
	    	}

	    	alTktSts.add(alTktHdrSts);

	    	// Line Status
	    	cstmtTktLineSts = connTktStsConn.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_ticket_status(CANON_E193_CS_SQLS_PKG.g_ticket_line_status_vv,?)}");
			cstmtTktLineSts.registerOutParameter(1, OracleTypes.CURSOR);

			cstmtTktLineSts.execute();

			rsTktLineSts = (ResultSet) cstmtTktLineSts.getObject(1);

			while(rsTktLineSts.next()) {
				Canon_E193_TicketStatusObj objTktLineSts = new Canon_E193_TicketStatusObj();
				objTktLineSts.setIAttribute1(rsTktLineSts.getInt(1));
				objTktLineSts.setStrAttribute2(rsTktLineSts.getString(2));
				alTktLineSts.add(objTktLineSts);
	    	}

	    	alTktSts.add(alTktLineSts);

	    }catch(CanonCustAppExceptionUtil csExp) {
	    	System.err.println("In getTicketStatus Exception csExp"+csExp);
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	System.err.println("In getTicketStatus Exception eSQLExp "+eSQLExp);
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketStatus()"));
	    }catch (Exception eExp) {
	    	System.err.println("In getTicketStatus Exception eExp "+eExp);
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketStatus()"));
	    }
	    finally {
	    	try {
	    		if(cstmtTktHdrSts != null) cstmtTktHdrSts.close();
	    		if(rsTktHdrSts != null) rsTktHdrSts.close();
	    		if(cstmtTktLineSts != null) cstmtTktLineSts.close();
	    		if(rsTktLineSts != null) rsTktLineSts.close();
	    		if(connTktStsConn != null) connTktStsConn.close();
	    		if(connTktSts != null) connTktSts.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Ticket, Method:getSeverity()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Ticket, Method:getSeverity()"));
	    	}
	    }
		return alTktSts;
	}

/**
 * This method will return the no of issue count
 * @return int.
 * @param strParentCatId String.
 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
 */
	public int getIssueCount(int iParentCatId) throws CanonCustAppExceptionUtil {
		System.out.println("in getIssueCount iParentCatId " + iParentCatId);
		int iCount = 0;

		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
	    CallableStatement cStmt = null;

	    try {
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());

	    	cStmt = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.get_issue_count(?,?)}");
	    	cStmt.setInt(1, iParentCatId);
	    	cStmt.registerOutParameter(2, OracleTypes.NUMBER);

	    	cStmt.execute();

	    	iCount = cStmt.getInt(2);

	    }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Ticket, Method:getIssueCount()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Ticket, Method:getIssueCount()"));
	    }
	    finally {
	    	try {
	    		if(cStmt != null) cStmt.close();
	    		if(connUtil != null) connUtil.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Ticket, Method:getIssueCount()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Ticket, Method:getIssueCount()"));
	    	}
	    }
		return iCount;
	}

	/**
	 * This method will return the no of unassigned_line_count for the ticket
	 * and if user has access to ticket_summary
	 * @param iOrgId int.
	 * @param iTicketId int.
	 * @param iResourceId int.
	 * @param iUserId int.
	 * @return java.lang.String.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	//Start changes for S21 by Mangala
	//public String getTicketSummaryAccess(int iOrgId, int iTicketId, int iResourceId, int iUserId) throws CanonCustAppExceptionUtil {
	public String getTicketSummaryAccess(int iOrgId, int iTicketId, String iResourceId, String iUserId) throws CanonCustAppExceptionUtil {
	//End changes for S21 by Mangala
		System.out.println("in getTicketSummaryAccess iTicketId " + iTicketId + " iResourceId "+ iResourceId + " iUserId " + iUserId);
		int iCount = 0;
		String strAccess = "N";
		String strOutput;

		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
	    CallableStatement cStmt = null;

	    try {
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());

	    	cStmt = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.get_ticket_summary_access(?,?,?,?,?,?)}");
	    	cStmt.setInt(1, iOrgId);
	    	cStmt.setInt(2, iTicketId);
	    	//Start changes for S21 by Mangala
	    	//cStmt.setInt(3, iResourceId);
	    	//cStmt.setint(4, iUserId);
	    	cStmt.setString(3, iResourceId);
	    	cStmt.setString(4, iUserId);
	    	//Start changes for S21 by Mangala
	    	cStmt.registerOutParameter(5, java.sql.Types.INTEGER);
			cStmt.registerOutParameter(6, java.sql.Types.VARCHAR);

	    	cStmt.execute();

	    	iCount = cStmt.getInt(5);
	    	strAccess = cStmt.getString(6);

			strOutput = iCount + "|" + strAccess;

	    }catch(CanonCustAppExceptionUtil csExp) {
	    	System.err.println("Exception occured in getTicketSummaryAccess csExp= "+csExp  );
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	System.err.println("Exception occured in getTicketSummaryAccess eSQLExp= "+eSQLExp  );
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketSummaryAccess()"));
	    }catch (Exception eExp) {
	    	System.err.println("Exception occured in getTicketSummaryAccess eExp= "+eExp  );
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketSummaryAccess()"));
	    }
	    finally {
	    	try {
	    		if(cStmt != null) cStmt.close();
	    		if(connUtil != null) connUtil.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketSummaryAccess()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Ticket, Method:getTicketSummaryAccess()"));
	    	}
	    }
		return strOutput;
	}

	/**
	 * This method will return the list of objects
	 * @param iOrgId int.
	 * @param iTicketId int.
	 * @param iLineId int.
	 * @param iLineNo int.
	 * @return java.util.ArrayList.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	public ArrayList getCRSummary(int iOrgId, int iTicketId, int iLineId, int iLineNo) throws CanonCustAppExceptionUtil {
		ArrayList arCRList = new ArrayList();
		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
	    CallableStatement cStmt = null;
	    ResultSet rs = null;
	    Canon_E193_CRSummaryObj objCRSummary = null;
	    System.out.println("in getCRSummary iOrgId = " + iOrgId + " - iTicketId = " + iTicketId + "- iLineId = " + iLineId + "- iLineNo = " + iLineNo);
	    try {
	    	//System.out.println("Inside Try");
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());
	    	//System.out.println("Before Calling select_cr_summary pkg");
	    	cStmt = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_cr_summary(?,?,?,?,?)}");

	    	cStmt.setInt(1, iOrgId);
	    	cStmt.setInt(2, iTicketId);
	    	cStmt.setInt(3, iLineId);
	    	cStmt.setInt(4, iLineNo);
	    	cStmt.registerOutParameter(5, OracleTypes.CURSOR);

	    	cStmt.execute();

	    	rs = (ResultSet) cStmt.getObject(5);
	    	int i = 0;
	    	while(rs.next()) {
	    		i = 0;
	    		objCRSummary = new Canon_E193_CRSummaryObj();
	    		objCRSummary.setICrId(rs.getInt(++i));
	    		objCRSummary.setStrTrxNo(rs.getString(++i));
	    		objCRSummary.setStrTrxDate(rs.getString(++i));
	    		objCRSummary.setStrContractId(rs.getString(++i));
	    		objCRSummary.setStrContractNo(rs.getString(++i));
	    		objCRSummary.setStrFleetContractYN(rs.getString(++i));
	    		objCRSummary.setStrDateBilledFrom(rs.getString(++i));
	    		objCRSummary.setStrDateBilledTo(rs.getString(++i));
	    		objCRSummary.setDOldInvoiceAmount(rs.getDouble(++i));
	    		objCRSummary.setDNewInvoiceAmount(rs.getDouble(++i));
	    		objCRSummary.setDNetCredit(rs.getDouble(++i));
	    		objCRSummary.setStrInvoiceType(rs.getString(++i));
	    		objCRSummary.setStrRebillTrxNumber(rs.getString(++i));
	    		objCRSummary.setStrCmTrxNo(rs.getString(++i));
	    		objCRSummary.setStrCreationDate(rs.getString(++i));
	    		objCRSummary.setICreatedBy(rs.getString(++i));
	    		objCRSummary.setStrLastUpdateDate(rs.getString(++i));
	    		objCRSummary.setILastUpdatedBy(rs.getString(++i));
	    		objCRSummary.setStrPrintYN(rs.getString(++i));
	    		/*objCRSummary.setStrAttribute1(rs.getString(++i));
	    		objCRSummary.setStrAttribute2(rs.getString(++i));
	    		objCRSummary.setStrAttribute3(rs.getString(++i));
	    		objCRSummary.setStrAttribute4(rs.getString(++i));
	    		objCRSummary.setStrAttribute5(rs.getString(++i));*/
	    		arCRList.add(objCRSummary);
	    	}

	    }catch(CanonCustAppExceptionUtil csExp) {
	    	System.err.println("in getCRSummary csExp="+csExp);
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	System.err.println("in getCRSummary eSQLExp ="+eSQLExp);
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Ticket, Method:getCRSummary()"));
	    }catch (Exception eExp) {
	    	System.err.println("in getCRSummary eExp ="+eExp);
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Ticket, Method:getCRSummary()"));
	    }
	    finally {
	    	try {
	    		if(cStmt != null) cStmt.close();
	    		if(rs != null) rs.close();
	    		if(connUtil != null) connUtil.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Ticket, Method:getCRSummary()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Ticket, Method:getCRSummary()"));
	    	}
	    }
		return arCRList;
	}
}
