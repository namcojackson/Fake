package com.canon.oracle.custapp.custinq.dao;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.Map;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;
import canon.apps.common.CanonConstants;

import com.canon.oracle.custapp.custinq.beans.Canon_E193_ReasonCodeObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketHeaderObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketSubLinesObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketDetailObj;
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

public class Canon_E193_CR_BillingIssue {
	
	private final String strHSqlRecTyp = CanonConstants.SCHEMA_NAME+".CANON_E193_HEADER_REC_TYP";
	private final String strHSqlTableTyp = CanonConstants.SCHEMA_NAME+".CANON_E193_HEADER_TBL_TYP";
	private final String strLSqlRecTyp = CanonConstants.SCHEMA_NAME+".CANON_E193_LINE_REC_TYP";
	private final String strLSqlTableTyp = CanonConstants.SCHEMA_NAME+".CANON_E193_LINE_TBL_TYP";
	private final String strSLSqlRecTyp = CanonConstants.SCHEMA_NAME+".CANON_E193_SUBLINE_REC_TYP";
	private final String strSLSqlTableTyp = CanonConstants.SCHEMA_NAME+".CANON_E193_SUBLINE_TBL_TYP";

	private final String strHSqlRecTyp1 = CanonConstants.SCHEMA_NAME+".CANON_E193_S21_HDR_REC";
	private final String strHSqlTableTyp1 = CanonConstants.SCHEMA_NAME+".CANON_E193_S21_HDR_TBL";
	private final String strLSqlRecTyp1 = CanonConstants.SCHEMA_NAME+".CANON_E193_S21_LINE_REC";
	private final String strLSqlTableTyp1 = CanonConstants.SCHEMA_NAME+".CANON_E193_S21_LINE_TBL";
	private final String strSLSqlRecTyp1 = CanonConstants.SCHEMA_NAME+".CANON_E193_S21_SUBLINE_REC";
	private final String strSLSqlTableTyp1 = CanonConstants.SCHEMA_NAME+".CANON_E193_S21_SUBLINE_TBL";
	
	//private final String strHSqlRecTyp = "CANON.CANON.CANON_E193_HEADER_REC_TYP";
	//private final String strHSqlTableTyp = "CANON.CANON.CANON_E193_HEADER_TBL_TYP";
	
	private final String strHClassName = "com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketHeaderObj";
	private final String strLCalssName = "com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj";
	private final String strSLClassName = "com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketSubLinesObj";

	private static final long serialVersionUID = 1L;
	private String origEmail ="";
	private String origName = "";
	private String origPhNo = "";
	private String origType = "";
	private String custContact = "";
	private String custEmail = "";
	private String custPhNo = "";
	private String customerNo = "";
	private String customerName = "";
	private String invoiceNo = "";
	private String contractNo = "";
	private int orderNo = 0;
	private String orderType = "";
	private String catIdDesc = "";
	private String billingIssue = "";
	private String createdBy = "";
	private String region = "";
	private String source = "";
	private String sqlTypeName = "";
	
	
	public Canon_E193_CR_BillingIssue() {
		super();
	}
	
	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String newContractNo) {
		this.contractNo = newContractNo;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String newRegion) {
		this.region = newRegion;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String newSource) {
		this.source = newSource;
	}	
	
	public String getInvoiceNo() {
		return invoiceNo;
	}

		public void setInvoiceNo(String newInvoiceNo) {
		this.invoiceNo = newInvoiceNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

		public void setOrderNo(int newOrderNo) {
		this.orderNo = newOrderNo;
	}

	

	public String getBillingIssue() {
		return billingIssue;
	}

	public void setBillingIssue(String newBillingIssue) {
		this.billingIssue = newBillingIssue;
	}


	public String getCatIdDesc() {
		return catIdDesc;
	}

		public void setCatIdDesc(String newCatIdDesc) {
		this.catIdDesc = newCatIdDesc;
	}


	public String getCreatedBy() {
		return createdBy;
	}

		public void setCreatedBy(String newCreatedBy) {
		this.createdBy = newCreatedBy;
	}


	public String getCustContact() {
		return custContact;
	}

		public void setCustContact(String newCustContact) {
		this.custContact = newCustContact;
	}

	public String getCustEmail() {
		return custEmail;
	}

		public void setCustEmail(String newCustEmail) {
		this.custEmail = newCustEmail;
	}

	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String newCustomerName) {
		this.customerName = newCustomerName;
	}

	
	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String newCustomerNo) {
		this.customerNo = newCustomerNo;
	}

	public String getCustPhNo() {
		return custPhNo;
	}

    public void setCustPhNo(String newCustPhNo) {
		this.custPhNo = newCustPhNo;
	}

	

	
	public String getOrderType() {
		return orderType;
	}

	
	public void setOrderType(String newOrderType) {
		this.orderType = newOrderType;
	}

	
	public String getOrigEmail() {
		return origEmail;
	}

		public void setOrigEmail(String newOrigEmail) {
		this.origEmail = newOrigEmail;
	}

	

	public String getOrigName() {
		return origName;
	}

	
	public void setOrigName(String newOrigName) {
		this.origName = newOrigName;
	}

	

	public String getOrigPhNo() {
		return origPhNo;
	}

	
	public void setOrigPhNo(String newOrigPhNo) {
		this.origPhNo = newOrigPhNo;
	}

	

	public String getOrigType() {
		return origType;
	}

	
	public void setOrigType(String newOrigType) {
		this.origType = newOrigType;
	}

	
	public String getSqlTypeName() {
		return sqlTypeName;
	}

		public void setSqlTypeName(String newSqlTypeName) {
		this.sqlTypeName = newSqlTypeName;
	}

	public java.lang.String getSQLTypeName() throws SQLException {
		return sqlTypeName;
	}
	
	/**
	 * This method will return the list of headers.
	 * <p>
	 * @return int.
	 * @param headerObj Canon_E193_TicketHeaderObj.
	 * @param linesObj Canon_E193_TicketLinesObj.
	 * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
	 */
	
		
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
	    	System.out.println("in addBillHeaderLineSubLinesS21");
	    	boolean isInseart = true;
	    	//connUtil = new CanonCustAppDBUtil();
	    	conn = TransactionScope.getConnection();
	    //	conn = (Connection)(connUtil.getConnection());
	    	
	    	//System.out.println("Connection is established");
	    //	conn = (Connection)com.ibm.ws.rsadapter.jdbc.WSJdbcUtil.getNativeConnection( (com.ibm.ws.rsadapter.jdbc.WSJdbcConnection)conn );  
	    	
	    	 // Connection vendorConn = WSCallHelper.getNativeConnection(conn);
//System.out.println("hello conn from native connection");
	    	
	    	//System.out.println("conn is established" + conn);
	    	pCall = conn.prepareCall("{call CANON_E193_CREATE_TKT_PKG.CREATE_BILL_TICKET(?,?,?,?,?,?,?,?,?)}");
	    	//System.out.println("call is set");
	    	setSQLRecTypes(headerObj, lineObj, subLineObj);
	    	//System.out.println("call is set2");
	    	
	    	 nativeConn = TransactionScope.nativeConnection(conn);
	    	
	 	   	pCall.setArray(1, CanonCustAppUtil.getArray(headerObj, strHClassName, nativeConn, strHSqlRecTyp1, strHSqlTableTyp1));
	    	//System.out.println("call is set with 1st array" );
	    	pCall.setArray(2, CanonCustAppUtil.getArray(lineObj, strLCalssName, nativeConn, strLSqlRecTyp1, strLSqlTableTyp1));
	    	pCall.setArray(3, CanonCustAppUtil.getArray(subLineObj, strSLClassName, nativeConn, strSLSqlRecTyp1, strSLSqlTableTyp1));
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
	
		
		
		

		public void readSQL(SQLInput stream, String typeName) throws SQLException {
			setSqlTypeName(typeName);
			billingIssue = stream.readString();
			customerName = stream.readString();
			customerNo = stream.readString();
			invoiceNo = stream.readString();
			contractNo = stream.readString();
			orderNo = stream.readInt();
			orderType = stream.readString();
			origName = stream.readString();
			origPhNo = stream.readString();
			origEmail = stream.readString();
			origType = stream.readString();
			custContact = stream.readString();
			custPhNo = stream.readString();
			custEmail = stream.readString();
			createdBy = stream.readString();
			catIdDesc = stream.readString();
			
	    }

		public void writeSQL(java.sql.SQLOutput stream) throws SQLException {
			writeRecObjString(stream, getBillingIssue());
			writeRecObjString(stream, getCustomerName());
			writeRecObjString(stream, getCustomerNo());
			writeRecObjString(stream, getInvoiceNo());
			writeRecObjString(stream, getContractNo());
			writeRecObjInt(stream, getOrderNo());
			writeRecObjString(stream, getOrderType());
			writeRecObjString(stream, getOrigName());
			writeRecObjString(stream, getOrigPhNo());
			writeRecObjString(stream, getOrigEmail());
			writeRecObjString(stream, getOrigType());
			writeRecObjString(stream, getCustContact());
			writeRecObjString(stream, getCustPhNo());
			writeRecObjString(stream, getCustEmail());
			writeRecObjString(stream, getCreatedBy());
			writeRecObjString(stream, getCatIdDesc());
			}

		private void writeRecObjString(java.sql.SQLOutput stream, String value) throws SQLException {
			if (value != null) stream.writeString(value);
			else stream.writeObject(null);
		}

		private void writeRecObjInt(java.sql.SQLOutput stream, int value) throws SQLException {
			if (new Integer(value)!= null) stream.writeInt(value);
			else stream.writeObject(null);
		}

		/**
			 * toString methode: creates a String representation of the object
			 * @return the String representation
			 * @author info.vancauwenberge.tostring plugin

			 */
			public String toString() {
				StringBuffer buffer = new StringBuffer();
				buffer.append("Canon_E193_TicketHeaderObj[");
				buffer.append("serialVersionUID = ").append(serialVersionUID);
				buffer.append(", invoiceNo = ").append(invoiceNo);
				buffer.append(", contractNo = ").append(contractNo);
				buffer.append(", orderNo = ").append(orderNo);
				
				buffer.append(", billingIssue = ").append(billingIssue);
				buffer.append(", catIdDesc = ").append(catIdDesc);
				buffer.append(", createdBy = ").append(createdBy);
				buffer.append(", custContact = ").append(custContact);
				buffer.append(", custEmail = ").append(custEmail);
				buffer.append(", custPhNo = ").append(custPhNo);
				buffer.append(", customerName = ").append(customerName);
				buffer.append(", customerNo = ").append(customerNo);
				buffer.append(", orderType = ").append(orderType);
				buffer.append(", origEmail = ").append(origEmail);
				buffer.append(", origName = ").append(origName);
				buffer.append(", origPhNo = ").append(origPhNo);
				buffer.append(", origType = ").append(origType);
				buffer.append(", sqlTypeName = ").append(sqlTypeName);
				buffer.append("]");
				return buffer.toString();
			}	
	}
	
