package com.canon.oracle.custapp.custinq.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;


import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

import com.canon.oracle.custapp.custinq.beans.Canon_E193_AccountDetails;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_AcctInfoObj;
import com.canon.oracle.custapp.util.CanonCustAppDBUtil;
import com.canon.oracle.custapp.util.CanonCustAppExceptionUtil;

/**
 * Canon_E193_AcctInfo Retrieves Account Information from database Creation
 * date: (7/27/2005 1:20:15 PM)
 *
 * @author:
 */
public class Canon_E193_AcctInfo {

	/**
	 * Canon_E193_AcctInfo constructor.
	 */
	public Canon_E193_AcctInfo() {
	}

	/**
	 * Get account info from database. Creation date: (7/27/2005 5:01:24 PM)
	 *
	 * @param strAcctName java.util.String
	 * @param strAcctNum java.util.String
	 * @param iAcctId int
	 * @param iOrgId int
	 * @return com.canon.oracle.custapp.custinq.beans.Canon_E193_AcctInfoObj
	 */
	public Canon_E193_AcctInfoObj getAcctInfo(String strAcctName, String strAcctNum, int iAcctId, int iOrgId) throws CanonCustAppExceptionUtil {
		//Get Connection
		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
	    CallableStatement pCall = null;
		Canon_E193_AcctInfoObj objAcctInfo = new Canon_E193_AcctInfoObj();

		try {
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());
	    	pCall = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_acct_info(?,?,?,?,?)}");

	    	pCall.setInt(1, iOrgId);
	    	pCall.setInt(2, iAcctId);
	    	pCall.registerOutParameter(3, OracleTypes.VARCHAR);
	    	pCall.registerOutParameter(4, OracleTypes.INTEGER);
	    	pCall.registerOutParameter(5, OracleTypes.VARCHAR);

	    	pCall.execute();

	    	String strOpenCIFlag = pCall.getString(3);
	    	int iOpenCINinetyDays = pCall.getInt(4);
	    	String strPORequiredFlag = pCall.getString(5);

			objAcctInfo.setAcctName(strAcctName);
			objAcctInfo.setAcctNum(strAcctNum);
			objAcctInfo.setAcctId(iAcctId);
			objAcctInfo.setOpenCIFlag(strOpenCIFlag);
			objAcctInfo.setOpenCIForNinetyDays(iOpenCINinetyDays);
			objAcctInfo.setPORequiredFlag(strPORequiredFlag);

		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctInfo, Method:getAcctInfo()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctInfo, Method:getAcctInfo()"));
		} finally {
			try {
				if (pCall != null) pCall.close();
				if (connUtil != null) connUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctInfo, Method:getAcctInfo()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctInfo, Method:getAcctInfo()"));
			}
		}
		return objAcctInfo;
	}
	
	public ArrayList getSerialNoAccountDetails(String strValueType, String strValue, 
			String strOrderName, String strOrderBy, int iPageNo, int iTotPageNo) throws CanonCustAppExceptionUtil {

		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
	    CallableStatement pCall = null;
	    ResultSet rs = null;
		
		System.out.println("Canon_E193_AcctInfo getSerialNoAccountDetails strValue : " + strValue 
				+ " strValueType : " + strValueType  + " strOrderName : " + strOrderName
				+ " strOrderBy : " + strOrderBy + " iPageNo : " + iPageNo + " iTotPageNo : "+ iTotPageNo); 
	    ArrayList accountDetailsList = new ArrayList();
	    Canon_E193_AccountDetails accountDetails = null;
		try {
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());
	    	pCall = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.GET_SERIAL_ACCT_DETAILS(?,?)}");

	    	pCall.setString(1, strValue);
	    	pCall.registerOutParameter(2, OracleTypes.CURSOR);

	    	pCall.execute();

	    	rs = (ResultSet) pCall.getObject(2);
	    	//System.out.println("Canon_E193_AcctInfo getSerialNoAccountDetails Resultset : " + rs);

	    	while(rs.next()) {
	    		accountDetails = new Canon_E193_AccountDetails();
				accountDetails.setAcctId(rs.getString("CUST_ACCOUNT_ID"));		
				accountDetails.setAcctName(rs.getString("PARTY_NAME"));
				accountDetails.setAcctNum(rs.getString("ACCOUNT_NUMBER"));
				accountDetails.setModel(rs.getString("MODEL"));
				accountDetails.setSerialNum(strValue);
				//System.out.println("Canon_E193_AcctInfo getSerialNoAccountDetails accountDetails : " + accountDetails.toString());
				accountDetailsList.add(accountDetails);
	    	}

		} catch (CanonCustAppExceptionUtil csExp) {
			System.err.println("Canon_E193_AcctInfo getSerialNoAccountDetails Cust App Exception is:" + csExp.toString());
			throw (csExp);
		} catch (SQLException eSQLExp) {
			System.err.println("Canon_E193_AcctInfo getSerialNoAccountDetails SQL Exception is:" + eSQLExp.toString());
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctInfo, Method:getSerialNoAccountDetails()"));
		} catch (Exception eExp) {
			System.err.println("Canon_E193_AcctInfo getSerialNoAccountDetails Exception is:" + eExp.toString());
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctInfo, Method:getSerialNoAccountDetails()"));
		} finally {
			try {
				if (rs != null) rs.close();
				if (pCall != null) pCall.close();
				if (connUtil != null) connUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctInfo, Method:getSerialNoAccountDetails()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctInfo, Method:getSerialNoAccountDetails()"));
			}
		}
		/*System.out.println("Canon_E193_AcctInfo getSerialNoAccountDetails accountDetailsList : " 
				+ accountDetailsList);*/
		return accountDetailsList;
	}
	
		
	public ArrayList getMultipleAccountDetails(int iOrgId, String strValueType, String strValue 
			) throws CanonCustAppExceptionUtil {

		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
	    CallableStatement pCall = null;
	    ResultSet rs = null;
		
		System.out.println("Canon_E193_AcctInfo getMultipleAccountDetails iOrgId : " + iOrgId + " strValue : " + strValue 
				+ " strValueType : " + strValueType ); 
	    ArrayList accountDetailsList = new ArrayList();
	    Canon_E193_AccountDetails accountDetails = null;
		try {
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());
	    	pCall = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_account_details(?,?,?,?)}");

	    	pCall.setInt(1, iOrgId);
	    	pCall.setString(2, strValueType);
	    	pCall.setString(3, strValue);
	    	pCall.registerOutParameter(4, OracleTypes.CURSOR);

	    	pCall.execute();

	    	rs = (ResultSet) pCall.getObject(4);

	    	while(rs.next()) {
	    		accountDetails = new Canon_E193_AccountDetails();
				accountDetails.setAcctId(rs.getString("CUST_ACCOUNT_ID"));		
				accountDetails.setAcctName(rs.getString("PARTY_NAME"));
				accountDetails.setAcctNum(rs.getString("ACCOUNT_NUMBER"));
				
				accountDetails.setPoNum(strValue);
				//System.out.println("Canon_E193_AcctInfo getMultipleAccountDetails accountDetails : " + accountDetails.toString());
				accountDetailsList.add(accountDetails);
	    	}

		} catch (CanonCustAppExceptionUtil csExp) {
			System.err.println("Canon_E193_AcctInfo getMultipleAccountDetails Cust App Exception is:" + csExp.toString());
			throw (csExp);
		} catch (SQLException eSQLExp) {
			System.err.println("Canon_E193_AcctInfo getMultipleAccountDetails SQL Exception is:" + eSQLExp.toString());
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctInfo, Method:getMultipleAccountDetails()"));
		} catch (Exception eExp) {
			System.err.println("Canon_E193_AcctInfo getMultipleAccountDetails Exception is:" + eExp.toString());
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctInfo, Method:getMultipleAccountDetails()"));
		} finally {
			try {
				if (rs != null) rs.close();
				if (pCall != null) pCall.close();
				if (connUtil != null) connUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctInfo, Method:getMultipleAccountDetails()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctInfo, Method:getMultipleAccountDetails()"));
			}
		}
		/*System.out.println("Canon_E193_AcctInfo getMultipleAccountDetails accountDetailsList : " 
				+ accountDetailsList);*/
		return accountDetailsList;
	}
		

	public static String[] getAccountDetails(String strValue, String strValueType, int iOrgId) throws CanonCustAppExceptionUtil {

		//Get Connection
		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
	    CallableStatement pCall = null;
	    ResultSet rs = null;

		String AccInfo[] = new String[4];

		try {
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());
	    	pCall = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_account_details(?,?,?,?)}");

	    	pCall.setInt(1, iOrgId);
	    	pCall.setString(2, strValueType);
	    	pCall.setString(3, strValue);
	    	pCall.registerOutParameter(4, OracleTypes.CURSOR);

	    	pCall.execute();

	    	rs = (ResultSet) pCall.getObject(4);
	    //	rs = ((OracleCallableStatement)pCall).getCursor(4);

	    	if(rs.next()) {
	    		AccInfo[0] = rs.getString(1);
				AccInfo[1] = rs.getString(2);
				AccInfo[2] = rs.getString(3);
				AccInfo[3] = rs.getString(4);
	    	}

		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctInfo, Method:getAccountDetails()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctInfo, Method:getAccountDetails()"));
		} finally {
			try {
				if (rs != null) rs.close();
				if (pCall != null) pCall.close();
				if (connUtil != null) connUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctInfo, Method:getAccountDetails()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctInfo, Method:getAccountDetails()"));
			}
		}
		return AccInfo;
	}
	
	public String getBillableFlag(String strInTpe){
		String retFlag="";
		Connection connection = null;
		CallableStatement cstmt  = null;
		ResultSet rs = null;
		System.out.println("strInTpe : "+ strInTpe);
		try
		{
		   String plsqlExp =  " Begin \n "
				   + " :1 := CANON_E193_CS_SQLS_PKG.GET_BILL_FLAG(:2) ; \n "
				   + " End;";
		    connection =  (Connection)TransactionScope.getConnection();
		     cstmt = connection.prepareCall(plsqlExp);
			  cstmt.setString(2, strInTpe);
			  cstmt.registerOutParameter(1,Types.VARCHAR);
			  cstmt.execute();
			  retFlag = cstmt.getString(1);
		}
 		catch(SQLException eSQLExp)
		{
		  System.err.println("SQL Exception is:" + eSQLExp.toString());
		}
		catch (Exception eExp)
		{
		  System.err.println("Exception is:" + eExp.toString());
		}
		finally
		{
			try
			{
				if(rs != null)
				  rs.close();
				if(cstmt != null)
				  cstmt.close();
				if(connection != null)
					TransactionScope.releaseConnection(connection);
			}
			catch (SQLException eSQLExp)
			{
			}
			catch (Exception eExp)
			{
			}
		}
		return retFlag;		
	}
}
