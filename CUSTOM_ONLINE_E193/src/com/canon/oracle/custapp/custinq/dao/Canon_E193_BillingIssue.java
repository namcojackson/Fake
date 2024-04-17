package com.canon.oracle.custapp.custinq.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;
import canon.apps.common.CanonConstants;

import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketDetailObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketHeaderObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketSubLinesObj;
import com.canon.oracle.custapp.util.CanonCustAppDBUtil;
import com.canon.oracle.custapp.util.CanonCustAppExceptionUtil;
import com.canon.oracle.custapp.util.CanonCustAppUtil;


/**
 * Title:		Canon_E193_BillingIssue<br>.
 * Description:	This is the main class of billing issues Functions<br>
 * <p>
 * CopyRight:	Canon<br>
 * Company:		Canon<br>
 * @author:		Subba<br>
 * @version:	1.0, (29-Sep-2005)<br>
 * @modified by:<br>
 * @version:<br>
 * @modification Detail:<br>
 * <pre>
 * Flag  			Date          By         Description
 * ----  			---------- 	  ---------- ---------------------------------------------
 * ITG#434465    	05-Feb-2013	  Hema 		  To handle Non-Serialized accessories
 * </pre>
 */

public class Canon_E193_BillingIssue {
	
	private final String strHSqlRecTyp = CanonConstants.SCHEMA_NAME+".CANON_E193_HEADER_REC_TYP";
	private final String strHSqlTableTyp = CanonConstants.SCHEMA_NAME+".CANON_E193_HEADER_TBL_TYP";
	private final String strLSqlRecTyp = CanonConstants.SCHEMA_NAME+".CANON_E193_LINE_REC_TYP";
	private final String strLSqlTableTyp = CanonConstants.SCHEMA_NAME+".CANON_E193_LINE_TBL_TYP";
	private final String strSLSqlRecTyp = CanonConstants.SCHEMA_NAME+".CANON_E193_SUBLINE_REC_TYP";
	private final String strSLSqlTableTyp = CanonConstants.SCHEMA_NAME+".CANON_E193_SUBLINE_TBL_TYP";
	
	//private final String strHSqlRecTyp = "CANON.CANON.CANON_E193_HEADER_REC_TYP";
	//private final String strHSqlTableTyp = "CANON.CANON.CANON_E193_HEADER_TBL_TYP";
	
	private final String strHClassName = "com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketHeaderObj";
	private final String strLCalssName = "com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj";
	private final String strSLClassName = "com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketSubLinesObj";

	/**
	 * Canon_E193_BillingIssue constructor.
	 */
	public Canon_E193_BillingIssue() {
		super();
	}
	/**
	 * This method will return the list of headers.
	 * <p>
	 * @return int.
	 * @param headerObj Canon_E193_TicketHeaderObj.
	 * @param linesObj Canon_E193_TicketLinesObj.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	
	public int addBillHeaderLineSubLines(Canon_E193_TicketHeaderObj[] headerObj, Canon_E193_TicketLinesObj[] lineObj, Canon_E193_TicketSubLinesObj[] subLineObj, String strNotes) throws CanonCustAppExceptionUtil {
		int iTicketId = 0;
		//Get Connection
		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
	    CallableStatement pCall = null;
	    Connection nativeConn=null;
	    try {
	    	boolean isInseart = true;
	    	//connUtil = new CanonCustAppDBUtil();
	    	conn = TransactionScope.getConnection();
	    //	conn = (Connection)(connUtil.getConnection());
	    	
	    	//System.out.println("Connection is established");
	    //	conn = (Connection)com.ibm.ws.rsadapter.jdbc.WSJdbcUtil.getNativeConnection( (com.ibm.ws.rsadapter.jdbc.WSJdbcConnection)conn );  
	    	
	    	 // Connection vendorConn = WSCallHelper.getNativeConnection(conn);
	    	System.out.println("in addBillHeaderLineSubLines");
	    	
	    	//System.out.println("conn is established" + conn);
	    	pCall = conn.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.INSERT_BILL(?,?,?,?,?,?,?,?)}");
	    	//System.out.println("call is set");
	    	setSQLRecTypes(headerObj, lineObj, subLineObj);
	    	//System.out.println("call is set2");
	    	
	    	 nativeConn = TransactionScope.nativeConnection(conn);
	    	
	 	   // 	map.put(strHSqlRecTyp, Class.forName(strHClassName));
	 	   // 	System.out.println("in e193 hello3" + CanonConstants.SCHEMA_NAME+".CANON.CANON_E193_HEADER_TBL_TYP" + "strSqlTableTyp" + strHSqlTableTyp);
	 	    	
	 	    	// Array a = conn.unwrap(oracle.jdbc.OracleConnection.class).createOracleArray(CanonConstants.SCHEMA_NAME+".CANON.CANON_E193_HEADER_TBL_TYP");
	 	    	 
	 	    //	 oracle.jdbc.OracleConnection oraConn = (oracle.jdbc.OracleConnection)conn;
	 	    	// System.out.println("oraconn created" + oraConn);
	 	        //java.sql.Array recArray =  oraConn.createArrayOf(CanonConstants.SCHEMA_NAME+".CANON.CANON_E193_HEADER_TBL_TYP", headerObj);
	 	    	 
	 	    //	oracle.sql.ArrayDescriptor insDescriptor = oracle.sql.ArrayDescriptor.createDescriptor(CanonConstants.SCHEMA_NAME+".CANON.CANON_E193_HEADER_TBL_TYP", conn);
	 	   // 	System.out.println("in E193 hello4");
	 	    //	oracle.sql.ARRAY recArray = new oracle.sql.ARRAY(insDescriptor, conn, headerObj);
	 	    	//System.out.println("in E193 hello5");
	 	    //	pCall.setArray(1,recArray);
	 	   // 	System.out.println(" in E193 hello6");
	    	pCall.setArray(1, CanonCustAppUtil.getArray(headerObj, strHClassName, nativeConn, strHSqlRecTyp, strHSqlTableTyp));
	    	//System.out.println("call is set with 1st array" );
	    	pCall.setArray(2, CanonCustAppUtil.getArray(lineObj, strLCalssName, nativeConn, strLSqlRecTyp, strLSqlTableTyp));
	    	pCall.setArray(3, CanonCustAppUtil.getArray(subLineObj, strSLClassName, nativeConn, strSLSqlRecTyp, strSLSqlTableTyp));
	    	pCall.setString(4, strNotes);
	    	//System.out.println("call is set3");
	    	pCall.registerOutParameter(5, OracleTypes.NUMBER);
	    	pCall.registerOutParameter(6, OracleTypes.NUMBER);
	    	pCall.registerOutParameter(7, OracleTypes.NUMBER);
	    	pCall.registerOutParameter(8, OracleTypes.NUMBER);
	    	//System.out.println("call is set4");
	    	//System.out.println("!!!!!!!!!!!! Before pCall.execute()");
	    	pCall.execute();
	    	//System.out.println("call is executed");
	    	int iErrorHC = pCall.getInt(6);
	    	int iErrorLC = pCall.getInt(7);
	    	int iErrorSLC = pCall.getInt(8);
	    	if(iErrorHC == -1 || iErrorLC == -1 || iErrorSLC == -1) isInseart = false;
	    	else iTicketId = pCall.getInt(5);
	    	System.out.println("iTicketId is " + iTicketId);
	    	if(!isInseart) throw new CanonCustAppExceptionUtil(100001, "insert lines failed", "Class: Canon_E193_BillingIssue, Method:addBillHeaderLineSubLines()");
	    }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_BillingIssue, Method:addBillHeaderLineSubLines()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_BillingIssue, Method:addBillHeaderLineSubLines()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
	    		if(connUtil != null) connUtil.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_BillingIssue, Method:addBillHeaderLineSubLines()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_BillingIssue, Method:addBillHeaderLineSubLines()"));
	    	}
	    }
		return iTicketId;
	}
	
	
	//Start Changes Mangala
	public ArrayList addBillHeaderLineSubLinesS21(Canon_E193_TicketHeaderObj[] headerObj, Canon_E193_TicketLinesObj[] lineObj, Canon_E193_TicketSubLinesObj[] subLineObj, String strNotes) throws CanonCustAppExceptionUtil {
		 ArrayList alDtls = new ArrayList();
		String imessage =null;
		int iTicketId = 0;
		//Get Connection
		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
	    CallableStatement pCall = null;
	    Connection nativeConn=null;
	    Canon_E193_TicketDetailObj objTktDtls = null;
	    try {
	    	boolean isInseart = true;
	    	//connUtil = new CanonCustAppDBUtil();
	    	conn = TransactionScope.getConnection();
	    //	conn = (Connection)(connUtil.getConnection());
	    	
	    	System.out.println("in addBillHeaderLineSubLinesS21");
	    //	conn = (Connection)com.ibm.ws.rsadapter.jdbc.WSJdbcUtil.getNativeConnection( (com.ibm.ws.rsadapter.jdbc.WSJdbcConnection)conn );  
	    	
	    	 // Connection vendorConn = WSCallHelper.getNativeConnection(conn);
	    	//System.out.println("hello conn from native connection");
	    	
	    	//System.out.println("conn is established" + conn);
	    	pCall = conn.prepareCall("{call CANON_E193_CREATE_TKT_PKG.INSERT_BILL(?,?,?,?,?,?,?,?,?)}");
	    	//System.out.println("call is set");
	    	setSQLRecTypes(headerObj, lineObj, subLineObj);
	    	//System.out.println("call is set2");
	    	
	    	 nativeConn = TransactionScope.nativeConnection(conn);
	    	
	 	   	pCall.setArray(1, CanonCustAppUtil.getArray(headerObj, strHClassName, nativeConn, strHSqlRecTyp, strHSqlTableTyp));
	    	//System.out.println("call is set with 1st array" );
	    	pCall.setArray(2, CanonCustAppUtil.getArray(lineObj, strLCalssName, nativeConn, strLSqlRecTyp, strLSqlTableTyp));
	    	pCall.setArray(3, CanonCustAppUtil.getArray(subLineObj, strSLClassName, nativeConn, strSLSqlRecTyp, strSLSqlTableTyp));
	    	pCall.setString(4, strNotes);
	    	//System.out.println("call is set3");
	    	pCall.registerOutParameter(5, OracleTypes.NUMBER);
	    	pCall.registerOutParameter(6, OracleTypes.NUMBER);
	    	pCall.registerOutParameter(7, OracleTypes.NUMBER);
	    	pCall.registerOutParameter(8, OracleTypes.NUMBER);
	    	pCall.registerOutParameter(9, OracleTypes.VARCHAR);
	    	//System.out.println("call is set4");
	    	pCall.execute();
	    	//System.out.println("call is executed");
	    	int iErrorHC = pCall.getInt(6);
	    	int iErrorLC = pCall.getInt(7);
	    	int iErrorSLC = pCall.getInt(8);
	    	imessage =pCall.getString(9);
	    	//alDtls.add(imessage);
	    	//if(iErrorHC == -1 || iErrorLC == -1 || iErrorSLC == -1 && imessage != "") isInseart = false;
	    	//else 
	    		iTicketId = pCall.getInt(5);
	    	System.out.println("iTicketId is " + iTicketId + " imessage is " + imessage);
	    	//System.out.println("imessage is " + imessage);
	    	//System.out.println("iTicketId is " + iTicketId);
	    	//alDtls.add(iTicketId);
	    	objTktDtls = new Canon_E193_TicketDetailObj();
	    	objTktDtls.setTicketId(iTicketId);
	    	objTktDtls.setMessage(imessage);
	    	alDtls.add(objTktDtls);
	    	//System.out.println("alDtls is " + alDtls);
	    	if(!isInseart) throw new CanonCustAppExceptionUtil(100001, "insert lines failed", "Class: Canon_E193_BillingIssue, Method:addBillHeaderLineSubLinesS21()");
	   }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_BillingIssue, Method:addBillHeaderLineSubLinesS21()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_BillingIssue, Method:addBillHeaderLineSubLinesS21()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
	    		if(connUtil != null) connUtil.releaseConnection();
	    		if(nativeConn!=null)
	            	TransactionScope.releaseConnection(nativeConn);
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_BillingIssue, Method:addBillHeaderLineSubLinesS21()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_BillingIssue, Method:addBillHeaderLineSubLinesS21()"));
	    	}
	    }
		return alDtls;
	}
		
		
		//End Changes Mangala
	
	/**
	 * This method will insert the lines and sublines.
	 * <p>
	 * @return boolean.
	 * @param iTicketId int.
	 * @param iOrgId int.
	 * @param linesObj Canon_E193_TicketLinesObj[].
	 * @param subLineObj Canon_E193_TicketSubLinesObj[].
	 * @param strNotes java.lang.String.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	
	public boolean addBillSubLines(int iTicketId, int iOrgId, Canon_E193_TicketLinesObj[] lineObj, Canon_E193_TicketSubLinesObj[] subLineObj, String strNotes) throws CanonCustAppExceptionUtil {
		boolean isInsert = true;
		//Get Connection
		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
		Connection nativeConn=null;
	    CallableStatement pCall = null;
	    try {
	    	System.out.println("in addBillSubLines");
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());
	    	nativeConn = TransactionScope.nativeConnection(conn);
	    	
	    	pCall = conn.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.insert_bill_line(?,?,?,?,?,?,?,?)}");
	    	
	    	setSQLRecTypes(null, lineObj, subLineObj);
	    	
	    	pCall.setInt(1, iTicketId);
	    	pCall.setString(2, null);
	    	pCall.setInt(3, iOrgId);
	    	pCall.setArray(4, CanonCustAppUtil.getArray(lineObj, strLCalssName, nativeConn, strLSqlRecTyp, strLSqlTableTyp));
	    	//System.out.println("call is in setArray 4");
	    	pCall.setArray(5, CanonCustAppUtil.getArray(subLineObj, strSLClassName, nativeConn, strSLSqlRecTyp, strSLSqlTableTyp));
	    	//System.out.println("call is in setArray 5");
	    	pCall.setString(6, strNotes);
	    	pCall.registerOutParameter(7, OracleTypes.NUMBER);
	    	pCall.registerOutParameter(8, OracleTypes.NUMBER);
	    	//System.out.println("call is before execute");
	    	pCall.execute();
	    	//System.out.println("call is after execute");
	    	int iErrorLC = pCall.getInt(7);
	    	int iErrorSLC = pCall.getInt(8);
	    	System.out.println("iErrorSLC is " + iErrorSLC);
	    	if(iErrorLC == -1 || iErrorSLC == -1) {
	    		isInsert = false;
	    		throw new CanonCustAppExceptionUtil(100001, "insert lines and sublines failed", "Class: Canon_E193_NonBillingIssue, Method:addBillSubLines()");
	    	}
	    	
	    }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_BillingIssue, Method:addBillSubLines()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_BillingIssue, Method:addBillSubLines()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
	    		if(connUtil != null) connUtil.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_BillingIssue, Method:addBillSubLines()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_BillingIssue, Method:addBillSubLines()"));
	    	}
	    }
		return isInsert;
	}
	
	/**
	 * This method will return the line object.
	 * <p>
	 * @return canon_E193_TicketLinesObj.
	 * @param iOrgId int.
	 * @param iTicketId int.
	 * @param iLineId int.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	public Canon_E193_TicketLinesObj getBillLine(int iOrgId, int iTicketId, int iLineId) throws CanonCustAppExceptionUtil {
		//Get Connection
		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
	    CallableStatement pCall = null;
	    ResultSet rs = null;
	    Canon_E193_TicketLinesObj objLine = new Canon_E193_TicketLinesObj();
	    try {
	    	System.out.println("in getBillLine iTicketId " + iTicketId + " iLineId " + iLineId);
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());
	    	pCall = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_ticket_line(?,?,?,?)}");
	    	//System.out.println("hello in getBillLine call i sprepared" + iTicketId + iLineId);
	    	pCall.setInt(1, iOrgId);
	    	pCall.setInt(2, iTicketId);
	    	pCall.setInt(3, iLineId);
	    	pCall.registerOutParameter(4, OracleTypes.CURSOR);
	    	//System.out.println("hello in getBillLine query executed 1 " );
	    	pCall.execute();
	    	//System.out.println("hello in getBillLine query executed 2 " );
	    	rs = (ResultSet) pCall.getObject(4);
	    	//System.out.println("hello in getBillLine got resultset " + rs );
	    	while(rs.next()) {
	    		objLine.setLineId(rs.getInt(1));
	    		objLine.setCatId(rs.getInt(2));
	    		objLine.setParentCatId(rs.getInt(3));
	    		objLine.setCatIdDesc(rs.getString(4));
	    		objLine.setReasonCd(rs.getString(5));
	    		objLine.setReason(rs.getString(6));
	    		objLine.setSeverity(rs.getString(7));
	    		objLine.setNoteId(rs.getInt(8));
	    		objLine.setNotes(rs.getString(9));
	    		objLine.setCatCode(rs.getString(10));
	    		//System.out.println("cat code is" + rs.getString(10) + rs.getString(6) + rs.getString(7));
	    	}	    	
	    }catch(CanonCustAppExceptionUtil csExp) {
	    	System.err.println("in exception csExp" + csExp);
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	System.err.println("in exception eSQLExp" + eSQLExp);
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_BillingIssue, Method:getBillLine()"));
	    }catch (Exception eExp) {
	    	System.err.println("in exception eExp" + eExp);
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_BillingIssue, Method:getBillLine()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
	    		if(rs != null) rs.close();
	    		if(connUtil != null) connUtil.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_BillingIssue, Method:getBillLine()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_BillingIssue, Method:getBillLine()"));
	    	}
	    }
		return objLine;
	}
	
	/**
	 * This method will return the list of sub line object.
	 * <p>
	 * @return java.util.ArrayList.
	 * @param iOrgId int.
	 * @param iTicketId int.
	 * @param iLineId int.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	public ArrayList getBillSubLine(int iOrgId, int iTicketId, int iLineId) throws CanonCustAppExceptionUtil {
		//Get Connection
		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
	    CallableStatement pCall = null;
	    ResultSet rs = null;
	    ArrayList subList = new ArrayList();
	    Canon_E193_TicketSubLinesObj objSubLine = null;
	    try {
	    	System.out.println("in getBillSubLine iTicketId " + iTicketId + " iLineId " + iLineId);
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());
	    	pCall = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_ticket_subline(?,?,?,?,?)}");
	    	pCall.setInt(1, iOrgId);
	    	pCall.setInt(2, iTicketId);
	    	pCall.setInt(3, iLineId);
	    	pCall.registerOutParameter(4, OracleTypes.CURSOR);
	    	pCall.registerOutParameter(5, OracleTypes.VARCHAR);
	    	pCall.execute();
	    	
	    	rs = (ResultSet) pCall.getObject(4);
	    	String fleetFalg = pCall.getString(5);

	    	while(rs.next()) {
	    		objSubLine = new Canon_E193_TicketSubLinesObj();
	    		objSubLine.setSubLineId(rs.getInt(1));
	    		objSubLine.setLineId(rs.getInt(2));
	    		objSubLine.setCatId(rs.getInt(3));
	    		objSubLine.setNewFlag(rs.getString(4));
	    		objSubLine.setCrFlag(rs.getString(5));
	    		objSubLine.setCompanyMoveFlag(rs.getString(6));
	    		objSubLine.setCancelEquipFlag(rs.getString(7));
	    		objSubLine.setTaxExamption(rs.getString(8));
	    		objSubLine.setCreditReason(rs.getString(9));
	    		objSubLine.setSerialNo(rs.getString(10));
	    		objSubLine.setObjectType(rs.getString(11));
	    		objSubLine.setObjectValue(rs.getString(12));
	    		objSubLine.setCurrentValue(rs.getString(13));
	    		objSubLine.setNewValue(rs.getString(14));
	    		objSubLine.setAttribute1(rs.getString(15));
	    		objSubLine.setAttribute2(rs.getString(16));
	    		objSubLine.setAttribute3(rs.getString(17));
	    		objSubLine.setAttribute4(rs.getString(18));
	    		objSubLine.setAttribute5(rs.getString(19));
				objSubLine.setInvoiceNumbers(rs.getString(20));
				objSubLine.setProductNumber(rs.getString(21));
				objSubLine.setFleetFlag(fleetFalg);
	    		subList.add(objSubLine);
	    	}
	    }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_BillingIssue, Method:getBillSubLine()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_BillingIssue, Method:getBillSubLine()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
	    		if(rs != null) rs.close();
	    		if(connUtil != null) connUtil.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_BillingIssue, Method:getBillSubLine()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_BillingIssue, Method:getBillSubLine()"));
	    	}
	    }
		return subList;
	}
	
	/**
	 * This method will update the line and subline records.
	 * <p>
	 * @return int.
	 * @param iTicktId.
	 * @param iOrgId int.
	 * @param lineObj Canon_E193_TicketLinesObj[].
	 * @param subLineObj Canon_E193_TicketSubLinesObj[].
	 * @param strNotes String.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	public int updateLineSubLine(int iTicktId, int iOrgId, Canon_E193_TicketLinesObj[] lineObj, Canon_E193_TicketSubLinesObj[] subLineObj, String strNotes) throws CanonCustAppExceptionUtil {
		//Get Connection
		int isUpdated = 0;
		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
		Connection nativeConn=null;
	    CallableStatement pCall = null;
	    try {
	    	System.out.println("in updateLineSubLine iTicktId " + iTicktId);
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());
	    	nativeConn = TransactionScope.nativeConnection(conn);
	    	pCall = conn.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.update_bill_line(?,?,?,?,?,?,?)}");
	    	
	    	setSQLRecTypes(null, lineObj, subLineObj);
	    	
	    	pCall.setInt(1, iTicktId);
	    	pCall.setInt(2, iOrgId);
	    	pCall.setArray(3, CanonCustAppUtil.getArray(lineObj, strLCalssName, nativeConn, strLSqlRecTyp, strLSqlTableTyp));
	    	//System.out.println("after array set 4 updateLineSubLine");
	    	pCall.setArray(4, CanonCustAppUtil.getArray(subLineObj, strSLClassName, nativeConn, strSLSqlRecTyp, strSLSqlTableTyp));
	    	//System.out.println("after array set 5 updateLineSubLine");
	    	pCall.setString(5, strNotes);
	    	pCall.registerOutParameter(6, OracleTypes.NUMBER);
	    	pCall.registerOutParameter(7, OracleTypes.NUMBER);
	    	//System.out.println("before execute updateLineSubLine");
	    	pCall.execute();
	    	//System.out.println("after execute updateLineSubLine");
	    	isUpdated = pCall.getInt(6);
	    	if(isUpdated == -1) throw new CanonCustAppExceptionUtil(100001, "update line and subline failed", "Class: Canon_E193_BillingIssue, Method:updateLineSubLine()");
	    	isUpdated = pCall.getInt(7);
	    	if(isUpdated == -1) throw new CanonCustAppExceptionUtil(100001, "insert line and subline failed", "Class: Canon_E193_BillingIssue, Method:updateLineSubLine()");
	    	
	    }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_BillingIssue, Method:updateLineSubLine()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_BillingIssue, Method:updateLineSubLine()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
	    		if(connUtil != null) connUtil.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_BillingIssue, Method:getNonBillLine()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_BillingIssue, Method:getNonBillLine()"));
	    	}
	    }
		return isUpdated;
	}
	/**
	 * setting sql record type to bean.
	 * @param headerObj
	 * @param lineObj
	 * @param subLineObj
	 * @throws Exception
	 */
	private void setSQLRecTypes(Canon_E193_TicketHeaderObj[] headerObj, Canon_E193_TicketLinesObj[] lineObj, Canon_E193_TicketSubLinesObj[] subLineObj) throws Exception {
    	//System.out.println("headerObj" + headerObj.length + "lineObj " + lineObj.length + " subLineObj " + subLineObj.length);
		if(headerObj != null && headerObj.length > 0) headerObj[0].setSqlTypeName(strHSqlRecTyp);
    	if(lineObj != null && lineObj.length > 0) lineObj[0].setSqlTypeName(strLSqlRecTyp);
    	if(subLineObj != null && subLineObj.length > 0) {
    		for(int i = 0; i < subLineObj.length; i++) {
    			subLineObj[i].setSqlTypeName(strSLSqlRecTyp);
    			//System.out.println("in for loop " + i);
    		}
    	}		
	}
}
