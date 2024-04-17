package com.canon.oracle.custapp.custinq.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

import org.apache.commons.mail.Email;

import canon.apps.common.CanonCustomProfile;
import canon.apps.common.CanonEmailUtil;

import com.canon.common.CanonCommonUtil;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_AccountDetails;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_ReasonCodeObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketHeaderObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj;
import com.canon.oracle.custapp.util.CanonCustAppDBUtil;
import com.canon.oracle.custapp.util.CanonCustAppExceptionUtil;

//import oracle.jdbc.driver.OracleTypes;
import java.util.Date;
import java.util.List;
/**
 * Title:      Canon_E193_QuickTicketDAO<br>
 * 
 * <pre>
 * Flag  Date       By         Description
 * ----  ---------- ---------- ---------------------------------------------
 * 29-Jan-2016  Mangala Shenoy	   Modified for S21 Changes.
 * </pre>
 */
public class Canon_E193_QuickTicketDAO {

	/**
	 * Canon_E193_Ticket constructor.
	 */
	public Canon_E193_QuickTicketDAO() {
		super();
	}
	
	public ArrayList getQuickTicketSNoAcctDetails(String strValueType, String strValue, 
			String strOrderName, String strOrderBy, int iPageNo, int iTotPageNo) throws CanonCustAppExceptionUtil {

		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
	    CallableStatement pCall = null;
	    ResultSet rs = null;
		
		System.out.println("Canon_E193_QuickTicketDAO getQuickTicketSNoAcctDetails strValue : " + strValue 
				+ " strValueType : " + strValueType  + " strOrderName : " + strOrderName
				+ " strOrderBy : " + strOrderBy + " iPageNo : " + iPageNo + " iTotPageNo : "+ iTotPageNo); 
	    ArrayList accountDetailsList = new ArrayList();
	    Canon_E193_AccountDetails accountDetails = null;
		try {
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());
	    	pCall = conn.prepareCall("{call CANON_E193_CSQUICK_SQLS_PKG.GET_SERIAL_ACCT_DETAILS(?,?)}");

	    	pCall.setString(1, strValue);
	    	pCall.registerOutParameter(2, OracleTypes.CURSOR);

	    	pCall.execute();

	    	rs = (ResultSet) pCall.getObject(2);
	    	//System.out.println("Canon_E193_QuickTicketDAO getQuickTicketSNoAcctDetails Resultset : " + rs);

	    	while(rs.next()) {
	    		accountDetails = new Canon_E193_AccountDetails();
				accountDetails.setAcctId(rs.getString("CUST_ACCOUNT_ID"));		
				accountDetails.setAcctName(rs.getString("PARTY_NAME"));
				accountDetails.setAcctNum(rs.getString("ACCOUNT_NUMBER"));
				accountDetails.setModel(rs.getString("MODEL"));
				accountDetails.setSerialNum(strValue);
				//System.out.println("Canon_E193_QuickTicketDAO getQuickTicketSNoAcctDetails accountDetails : " + accountDetails.toString());
				accountDetailsList.add(accountDetails);
	    	}

		} catch (CanonCustAppExceptionUtil csExp) {
			System.err.println("Canon_E193_QuickTicketDAO getQuickTicketSNoAcctDetails Cust App Exception is:" + csExp.toString());
			throw (csExp);
		} catch (SQLException eSQLExp) {
			System.err.println("Canon_E193_QuickTicketDAO getQuickTicketSNoAcctDetails SQL Exception is:" + eSQLExp.toString());
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQuickTicketSNoAcctDetails()"));
		} catch (Exception eExp) {
			System.err.println("Canon_E193_QuickTicketDAO getQuickTicketSNoAcctDetails Exception is:" + eExp.toString());
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQuickTicketSNoAcctDetails()"));
		} finally {
			try {
				if (rs != null) rs.close();
				if (pCall != null) pCall.close();
				if (connUtil != null) connUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQuickTicketSNoAcctDetails()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQuickTicketSNoAcctDetails()"));
			}
		}
		/*System.out.println("Canon_E193_QuickTicketDAO getSerialNoAccountDetails accountDetailsList : " 
				+ accountDetailsList);*/
		return accountDetailsList;
	}
	
	public ArrayList getMultipleAccountDetails(int iOrgId, String strValueType, String strValue 
			) throws CanonCustAppExceptionUtil {

		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
	    CallableStatement pCall = null;
	    ResultSet rs = null;
		
		System.out.println("Canon_E193_QuickTicketDAO getMultipleAccountDetails iOrgId : " + iOrgId + " strValue : " + strValue 
				+ " strValueType : " + strValueType ); 
	    ArrayList accountDetailsList = new ArrayList();
	    Canon_E193_AccountDetails accountDetails = null;
		try {
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());
	    	pCall = conn.prepareCall("{call canon_e193_csquick_sqls_pkg.get_account_details(?,?,?,?)}");

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
				//System.out.println("Canon_E193_QuickTicketDAO getMultipleAccountDetails accountDetails : " + accountDetails.toString());
				accountDetailsList.add(accountDetails);
	    	}

		} catch (CanonCustAppExceptionUtil csExp) {
			System.err.println("Canon_E193_QuickTicketDAO getMultipleAccountDetails Cust App Exception is:" + csExp.toString());
			throw (csExp);
		} catch (SQLException eSQLExp) {
			System.err.println("Canon_E193_QuickTicketDAO getMultipleAccountDetails SQL Exception is:" + eSQLExp.toString());
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getMultipleAccountDetails()"));
		} catch (Exception eExp) {
			System.err.println("Canon_E193_QuickTicketDAO getMultipleAccountDetails Exception is:" + eExp.toString());
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getMultipleAccountDetails()"));
		} finally {
			try {
				if (rs != null) rs.close();
				if (pCall != null) pCall.close();
				if (connUtil != null) connUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getMultipleAccountDetails()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getMultipleAccountDetails()"));
			}
		}
		/*System.out.println("Canon_E193_QuickTicketDAO getMultipleAccountDetails accountDetailsList : " 
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
			System.out.println("Canon_E193_QuickTicketDAO getAccountDetails iOrgId : " + iOrgId + " strValue : " + strValue 
					+ " strValueType : " + strValueType );
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());
	    	pCall = conn.prepareCall("{call canon_e193_csquick_sqls_pkg.get_account_details(?,?,?,?)}");

	    	pCall.setInt(1, iOrgId);
	    	pCall.setString(2, strValueType);
	    	pCall.setString(3, strValue);
	    	pCall.registerOutParameter(4, OracleTypes.CURSOR);

	    	pCall.execute();

	    	rs = (ResultSet) pCall.getObject(4);

	    	if(rs.next()) {
	    		AccInfo[0] = rs.getString(1);
				AccInfo[1] = rs.getString(2);
				AccInfo[2] = rs.getString(3);
				AccInfo[3] = rs.getString(4);
	    	}

		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getAccountDetails()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getAccountDetails()"));
		} finally {
			try {
				if (pCall != null) pCall.close();
				if (rs != null) rs.close();
				if (connUtil != null) connUtil.releaseConnection();
				if (conn != null) conn.close();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getAccountDetails()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getAccountDetails()"));
			}
		}
		return AccInfo;
	}
	//Start Changes for S21 by Mangala
 // public String getCustomerServiceGroup( int strResId) throws Exception
	 public String getCustomerServiceGroup( String strResId) throws Exception 
//End Changes for S21 by Mangala
  {
	 CallableStatement serviceGroupSt = null;
	 Connection conn = null;
     
     String resValue = "N";
     try
       {
    	 System.out.println("Canon_E193_QuickTicketDAO getCustomerServiceGroup strResId : " + strResId );
		//connInv = new CanonCustAppDBUtil();
		//connInvConnection = (Connection)(connInv.getConnection());
			try 
			{
				conn = (Connection)TransactionScope.getConnection();
			}
			catch (Exception eExp) 
			{
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getCustomerServiceGroup()"));
			}
		
		if (conn != null) {
		  serviceGroupSt = (CallableStatement) conn.prepareCall("BEGIN ? := canon_e193_csquick_sqls_pkg.get_cust_service_group(?); END;");
		  if (serviceGroupSt != null) {
			  // Set the parameters on the statement
			  serviceGroupSt.registerOutParameter(1, OracleTypes.VARCHAR);
			//Start Changes for S21 by Mangala
			  //serviceGroupSt.setInt(2,  strResId);
			  serviceGroupSt.setString(2,  strResId);
			//End Changes for S21 by Mangala
			  // Execute the statement
			  serviceGroupSt.execute();
			  resValue = serviceGroupSt.getString(1);
			  
			  return resValue;
		  }
		  else {
			  System.err.println("DBStatus.getCustomerServiceGroup Error ");
          	}
		} else {
			System.err.println("DBStatus.getCustomerServiceGroup - Unable to get data" );
        }
     } catch (Exception ex) {
		System.err.println("Exception occurred in getCustomerServiceGroup = "+ex);
    	 ex.printStackTrace();
		 throw (ex);
     } finally {
    	 if (serviceGroupSt != null) {
    		 try {
					serviceGroupSt.close();
					serviceGroupSt = null;
					if(conn != null) conn.close();
					TransactionScope.releaseConnection(conn);
				} 
				catch (Exception exp) 
				{
    			  System.err.println( " Exception while releasing the connection" + exp);
				  exp.printStackTrace();
				}
    	 }
    	 
    	} 
	  return resValue;
  }	
  	public ArrayList getQuickTicketReasonCodes(int strApplId, String strReasonCode) throws CanonCustAppExceptionUtil {
		//ArrayList alReasonCodes = new ArrayList();
		ArrayList alReasonDesc = new ArrayList();
		//Get Connection
		CanonCustAppDBUtil connReasonConnection = null;
		Connection connReason = null;
	    CallableStatement pCall = null;
	    ResultSet rsReason = null;
	    Canon_E193_ReasonCodeObj objReason = null;
	    try {
	    	System.out.println("Canon_E193_QuickTicketDAO getQuickTicketReasonCodes strApplId : " 
	    		+ strApplId + " strReasonCode " + strReasonCode );
	    	connReasonConnection = new CanonCustAppDBUtil();
	    	connReason = (Connection)(connReasonConnection.getConnection());
	    	pCall = connReason.prepareCall("{call CANON_E193_CSQUICK_SQLS_PKG.get_reason_codes(?)}");
	    	//pCall.setString(1, strReasonCode);
	    	pCall.registerOutParameter(1, OracleTypes.CURSOR);
	    	
	    	pCall.execute();	    	
	    	rsReason = (ResultSet) pCall.getObject(1);	    	
	    	while(rsReason.next()) {
	    		objReason = new Canon_E193_ReasonCodeObj();
	    		objReason.setReason(rsReason.getString(1));
	    		objReason.setDescription(rsReason.getString(2));
	    		alReasonDesc.add(objReason);
	    	}
	    }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQuickTicketReasonCodes()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQuickTicketReasonCodes()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
	    		if(rsReason != null) rsReason.close();
	    		if(connReasonConnection != null) connReasonConnection.releaseConnection();
				if(connReason != null) connReason.close();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQuickTicketReasonCodes()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQuickTicketReasonCodes()"));
	    	}
	    }
		return alReasonDesc;
	}
  	public String[] getQuickTicketCatId(String strRegion) throws CanonCustAppExceptionUtil {
		String[] code = new String[2];
		//Get Connection
		CanonCustAppDBUtil connCodeConnection = null;
		Connection connCode = null;
	    CallableStatement pCall = null;
	    ResultSet rsCode = null;
	    Canon_E193_ReasonCodeObj objReason = null;
	    try {
	    	System.out.println("Canon_E193_QuickTicketDAO getQuickTicketCatId strRegion : " 
		    		+ strRegion );
	    	connCodeConnection = new CanonCustAppDBUtil();
	    	connCode = (Connection)(connCodeConnection.getConnection());
	    	pCall = connCode.prepareCall("{call CANON_E193_CSQUICK_SQLS_PKG.get_cat_code(?,?)}");
	    	pCall.setString(1, strRegion);
	    	pCall.registerOutParameter(2, OracleTypes.CURSOR);
	    	
	    	pCall.execute();	    	
	    	rsCode= (ResultSet) pCall.getObject(2);	    	
	    	while(rsCode.next()) {
	    		code[0] = rsCode.getString("CAT_ID");
	    		code[1] = rsCode.getString("PARENT_CAT_ID");
	    	}
	    }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQuickTicketCatId()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQuickTicketCatId()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
	    		if(rsCode != null) rsCode.close();
	    		if(connCodeConnection != null) connCodeConnection.releaseConnection();
				if(connCode != null) connCode.close();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQuickTicketCatId()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQuickTicketCatId()"));
	    	}
	    }
		return code;
	}	
 public int getLineId(int ticketId) throws Exception 
  {
	 CallableStatement lineSt = null;
	 Connection conn = null;
     
     int lineId= -1;
     try
       {
    	 System.out.println("Canon_E193_QuickTicketDAO getLineId ticketId : " 
		    		+ ticketId );
			try 
			{
				conn = (Connection)TransactionScope.getConnection();
			}
			catch (Exception eExp) 
			{
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getLineId()"));
			}
		
		if (conn != null) {
		  lineSt = (CallableStatement) conn.prepareCall("BEGIN ? := canon_e193_csquick_sqls_pkg.get_line_id(?); END;");
		  if (lineSt != null) {
			  // Set the parameters on the statement
			  lineSt.registerOutParameter(1, OracleTypes.NUMBER);
			  lineSt.setInt(2,  ticketId);
			  
			  // Execute the statement
			  lineSt.execute();
			  lineId = lineSt.getInt(1);
			  
			  return lineId;
		  }
		  else {
			  System.err.println("DBStatus.getCustomerServiceGroup Error ");
          	}
		} else {
			System.err.println("DBStatus.getCustomerServiceGroup - Unable to get data" );
        }
     } catch (Exception ex) {
		System.err.println("Exception occured in getLineId   = "+ex);
    	 ex.printStackTrace();
		 throw (ex);
     } finally {
    	 if (lineSt != null) {
    		 try {
					lineSt.close();
					lineSt = null;
					if(conn != null) conn.close();
					TransactionScope.releaseConnection(conn);
				} 
				catch (Exception exp) 
				{
    			  System.out.println( " Exception while releasing the connection in getLineId");
				  exp.printStackTrace();
				}
    	 }
    	 
    	} 
	  return lineId;
  }
 //Start Changes for S21 by Mangala
//public int createQuickTicket(Canon_E193_TicketHeaderObj objHeaders, Canon_E193_TicketLinesObj objLines, int iOrgId, String iUserId, int iResourceId, String strRegionCode, String strCatId)throws CanonCustAppExceptionUtil {
public int createQuickTicket(Canon_E193_TicketHeaderObj objHeaders, Canon_E193_TicketLinesObj objLines, int iOrgId, 
		String iUserId, String iResourceId, String strRegionCode, String strCatId, String requestUrl)throws CanonCustAppExceptionUtil {
	//End Changes for S21 by Mangala
	ArrayList alHeader = new ArrayList();
	int iTicketId =0;
	Connection conn = null;
	CallableStatement pCall = null;
	CanonCustAppDBUtil connUtil = null;
	System.out.println("Inside createQuickTicket");
	try{
		
		Canon_E193_NonBillingIssue objNonBillDao = new Canon_E193_NonBillingIssue(); 
		Canon_E193_TicketLinesObj objTicketLines = null;
		Canon_E193_TicketHeaderObj objTicketHeader =null;
		ArrayList alAssignList = new ArrayList();
		ArrayList alLines = new ArrayList();
		int iLineId = -1;
		Canon_E193_Assignment objAssignDao = new Canon_E193_Assignment();
		int iCount = -1;
		int catId = -1;
		iTicketId = objNonBillDao.addNonBillHeaderLines(objHeaders, objLines);
		System.out.println("in createQuickTicket iTicketId : " + iTicketId + "orgId : " + iOrgId);
		if(iTicketId > 0) {
			alLines = getQTNonBillHeaderLines(iOrgId, iTicketId);
			System.out.println("in createQuickTicket len(alLines) : " + alLines.size());
		 	if(alLines != null && alLines.size() > 0) {
				if(!("".equals(strCatId) && "null".equals(strCatId))){
					catId = Integer.parseInt(strCatId);
				}
				
				connUtil = new CanonCustAppDBUtil();
				conn = (Connection)(connUtil.getConnection());				
				pCall = conn.prepareCall("{call canon_e193_cs_evolution_pkg.get_owner_details(?,?,?,?,?,?,?)}");
			    //Start Changes for S21 by Mangala
				//pCall.setInt(1, iResourceId);
				pCall.setString(1, iResourceId);
				//End Changes for S21 by Mangala
				pCall.setInt(2, iOrgId);
				pCall.setString(3, strRegionCode);
				pCall.setInt(4, catId);			
				//Start Changes for S21 by Mangala
				//pCall.registerOutParameter(5, OracleTypes.NUMBER);
				pCall.registerOutParameter(5, OracleTypes.VARCHAR);
				//End Changes for S21 by Mangala
				pCall.registerOutParameter(6, OracleTypes.VARCHAR);
				pCall.registerOutParameter(7, OracleTypes.VARCHAR);			
				try{
					pCall.execute();
			         }catch(Exception e) {
			        	 System.err.println("Exception occured in createQuickTicket" + e.getMessage());
			         }
				//Start Changes for S21 by Mangala
				//int resId = pCall.getInt(5);
				String resId = pCall.getString(5);
				//End Changes for S21 by Mangala
				String roleId = pCall.getString(6);
				String strDeptCode = pCall.getString(7);
				iCount= alLines.size();
				System.out.println("in createQuickTicket resId : " + resId + "roleId : " + roleId +" strDeptCode "+strDeptCode + " iCount " +iCount);
				//System.out.println("iCount : " + iCount);
				for(int i=0; i<alLines.size(); i++) {
					objTicketLines = (Canon_E193_TicketLinesObj)alLines.get(i);
					objLines = new Canon_E193_TicketLinesObj();
					objLines.setLineId(objTicketLines.getLineId());
				//	objLines.setDeptCode(objTicketLines.getDeptCode());
				//	objLines.setRoleId(objTicketLines.getRoleId());
				//	objLines.setResourceId(objTicketLines.getResourceId());
					objLines.setDeptCode(strDeptCode);
					objLines.setRoleId(roleId);
				/*	objLines.setResourceId(resId); */
					objLines.setResourceId(iResourceId);
					objLines.setCreatedBy(iUserId);
					//System.out.println("Line Id : "+ objTicketLines.getLineId()+ "Dept Code : " + objTicketLines.getDeptCode() + " objTicketLines.getRoleId() : "+objTicketLines.getRoleId()+" objTicketLines.getResourceId() : "+objTicketLines.getResourceId());
					//System.out.println("objLines = "+objLines);
					
					alAssignList.add(objLines);	
					iLineId = objTicketLines.getLineId();	
					//System.out.println("iLineId : " + iLineId);
					/*System.out.println("Ticker creation Asignee email Start");
					pCall = conn.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.GET_ASSIGNEE_NOTIF_DTLS(?,?,?,?,?,?,?)}");			
					pCall.setInt(1, iTicketId);
					pCall.setInt(2, iLineId);
					pCall.registerOutParameter(3, OracleTypes.VARCHAR);
					pCall.registerOutParameter(4, OracleTypes.VARCHAR);
					pCall.registerOutParameter(5, OracleTypes.VARCHAR);
					pCall.registerOutParameter(6, OracleTypes.VARCHAR);
					pCall.registerOutParameter(7, OracleTypes.VARCHAR);
					pCall.execute();
					String receiptEmail=pCall.getString(6);	
					String receiptEmails[]=null;
					if(receiptEmail==null || "".equalsIgnoreCase(receiptEmail))
						receiptEmail="test_consultant@cusa.canon.com";
					else
					{
							StringTokenizer receiptTokens = new StringTokenizer(
									receiptEmail, ",");
							receiptEmails = new String[receiptTokens
									.countTokens()];
							int emailCount = 0;
							while (receiptTokens.hasMoreTokens()) {
								receiptEmails[i] = receiptTokens.nextToken();
								emailCount++;
							}
							System.out.println("Email COunt:" + emailCount);
					}						
					String l_category=pCall.getString(3);
					if(l_category==null || "".equalsIgnoreCase(l_category))
						l_category=" ";
					String l_sub_category=pCall.getString(4);
					if(l_sub_category==null ||"".equalsIgnoreCase(l_sub_category))
						l_sub_category=" ";
					String l_created_by_name=pCall.getString(7);
					if(l_created_by_name==null ||"".equalsIgnoreCase(l_created_by_name))
						l_sub_category="Test User";
					String l_reason=pCall.getString(5);
					if(l_reason==null ||"".equalsIgnoreCase(l_reason))
						l_reason="Test Reason";
					
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
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
					String urlContent = requestUrl != null ? "\n Please login using the link provided:\n"+
							"\n "+requestUrl.split("e193")[0]+"e193/jsp/canon_E193_csEIHome.jsp" : "" ;
					
					String emailBody="This is an Auto Notification Email. Please refer to the information below to query or update"			            
					            +"Here is the ticket deatils, Ticket: "+iTicketId+" Category "+l_category +" and Sub category "+l_sub_category+" craeted by "+		 
					            l_created_by_name+" for Reason Code "+l_reason+" on "+dateFormat.format(date)+			            
					            urlContent +
					            "\n Thanks, \n Canon Customer Service";	
					 String emailSubject="Canon Solutions America Open Customer Care Inquiry Confirmation";
					Email email = CanonEmailUtil.createSimpleEmail(); 
					email.setSubject(subjectInit + emailSubject);
					if(receiptEmails==null)
					 email.setMsg(emailBody).addTo(receiptEmail);
					else
						email.setMsg(emailBody).addTo(receiptEmails);
					email.send();
					System.out.println("Ticker creation Asignee email End");*/
				}
				if(iCount > 0) {
					try{
						
					int assingnId = objAssignDao.addAssignments(alAssignList);

					System.out.println("in createQuickTicket assingnId : " + assingnId);
					}catch(Exception e) {
			        	 System.err.println(e.getMessage());
			         } 
				}
				try{
			objAssignDao.callWrapUp(iOrgId, iTicketId, 0, "ASSIGNED", iUserId);
				}catch(Exception e) {
		        	 System.err.println("Exception occured in createQuickTicket callWrapUp"+e.getMessage());
		         } 
			//int iLineId = getLineId(iTicketId);
		//	System.out.println("iLineId : " + iLineId+" iUserId : "+iUserId+" iOrgId : "+iOrgId);
				try{
			boolean status = updateQuickTicket(iTicketId, iOrgId, iLineId, null, "CLOSE", iUserId, "System generated message: Ticket Closed");
			System.out.println("in createQuickTicket status : " + status);
				}catch(Exception e) {
		        	 System.out.println("Exception occured in createQuickTicket  updateQuickTicket "+e.getMessage());
		         }
			}
		}
	}catch(CanonCustAppExceptionUtil csExp) {
         throw (csExp);
        }catch (Exception eExp) {
         throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:createQuickTicket()"));
       }
	   finally {
	    	try {
	    		if(pCall != null) pCall.close();
				if(conn!=null) conn.close();
	    		if(connUtil != null) connUtil.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:createQuickTicket()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:createQuickTicket()"));
	    	}
		}
	   return iTicketId;
	}
 public ArrayList getQTNonBillHeaderLines(int iOrgId, int iTicketId) throws CanonCustAppExceptionUtil {
      ArrayList hlList = new ArrayList();
      Canon_E193_TicketHeaderObj objNonBillH = null;
      Canon_E193_TicketLinesObj objNonBillL = null;
      //Get Connection
      CanonCustAppDBUtil connNonBillUtil = null;
      Connection connNonBill = null;
       CallableStatement pCall = null;
       ResultSet rsNonBill = null;
       try {
    	   System.out.println("in getQTNonBillHeaderLines iTicketId : " + iTicketId + "orgId : " + iOrgId);
         connNonBillUtil = new CanonCustAppDBUtil();
         connNonBill = (Connection)(connNonBillUtil.getConnection());

         pCall = connNonBill.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_ticket_summary(?,?,?,?)}");

         pCall.setInt(1, iOrgId);
         pCall.setInt(2, iTicketId);
         pCall.registerOutParameter(3, OracleTypes.CURSOR);
         pCall.registerOutParameter(4, OracleTypes.CURSOR);

         pCall.execute();

        // rsNonBill = ((OracleCallableStatement)pCall).getCursor(3);

        // rsNonBill = null;
         rsNonBill = (ResultSet) pCall.getObject(4);

         while(rsNonBill.next()) {
            objNonBillL = new Canon_E193_TicketLinesObj();
            objNonBillL.setLineId(rsNonBill.getInt(1));
            objNonBillL.setTicketId(rsNonBill.getInt(2));
            objNonBillL.setCatId(rsNonBill.getInt(3));
            objNonBillL.setLineNo(rsNonBill.getInt(4));
            objNonBillL.setLineStatus(rsNonBill.getString(5));
            objNonBillL.setSeverity(rsNonBill.getString(6));
            objNonBillL.setReasonCd(rsNonBill.getString(7));
            objNonBillL.setReason(rsNonBill.getString(8));
            objNonBillL.setJtfNotesFlag(rsNonBill.getString(9));
            objNonBillL.setAttribute1(rsNonBill.getString(10));
            objNonBillL.setAttribute2(rsNonBill.getString(11));
            objNonBillL.setAttribute3(rsNonBill.getString(12));
            objNonBillL.setAttribute4(rsNonBill.getString(13));
            objNonBillL.setAttribute5(rsNonBill.getString(14));
            objNonBillL.setCreatedBy(rsNonBill.getString(15));
            objNonBillL.setCreationDate(rsNonBill.getString(16));
            objNonBillL.setLastUpdatedDate(rsNonBill.getString(17));
            objNonBillL.setCatIdDesc(rsNonBill.getString(18));
            objNonBillL.setDeptCode(rsNonBill.getString(19));
            objNonBillL.setDeptName(rsNonBill.getString(20));
            objNonBillL.setRoleId(rsNonBill.getString(21));
            objNonBillL.setRoleCd(rsNonBill.getString(22));
            objNonBillL.setRoleName(rsNonBill.getString(23));
            //Start changes for S21 by Mangala
           // objNonBillL.setResourceId(rsNonBill.getInt(24));
            objNonBillL.setResourceId(rsNonBill.getString(24));
            //End Changes for S21 by Mangala
            objNonBillL.setResourceName(rsNonBill.getString(25));
            objNonBillL.setAttribute5(rsNonBill.getString(26));
            objNonBillL.setCatCode(rsNonBill.getString(27));
            hlList.add(objNonBillL);
         }

       }catch(CanonCustAppExceptionUtil csExp) {
         throw (csExp);
       }catch(SQLException eSQLExp) {
         throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQTNonBillHeaderLines()"));
       }catch (Exception eExp) {
         throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQTNonBillHeaderLines()"));
       }
       finally {
         try {
            if(pCall != null) pCall.close();
            if(rsNonBill != null) rsNonBill.close();
			if(connNonBill!=null) connNonBill.close();
            if(connNonBillUtil != null) connNonBillUtil.releaseConnection();
         }catch (CanonCustAppExceptionUtil csExp) {
            throw (csExp);
         }catch (SQLException eSQLExp) {
            throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQTNonBillHeaderLines()"));
         }catch (Exception eExp) {
            throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQTNonBillHeaderLines()"));
         }
       }
      return hlList;
   }	
	public boolean updateQuickTicket(int iTicketId, int iOrgId, int iLineId, String strSeverity, String strStatus, String iUpdatedBy, String strNotes)throws CanonCustAppExceptionUtil{
		boolean isTrue = false;
		//Get Connection
		CanonCustAppDBUtil connNonBillUtil = null;
		Connection connNonBill = null;
	    CallableStatement pCall = null;
	    try {
	    	System.out.println("in updateQuickTicket iTicketId : " + iTicketId + "iLineId : " + iLineId + " strStatus " + strStatus + " iUpdatedBy " + iUpdatedBy);
	    	connNonBillUtil = new CanonCustAppDBUtil();
	    	connNonBill = (Connection)(connNonBillUtil.getConnection());

	    	pCall = connNonBill.prepareCall("{call canon_e193_csquick_sqls_pkg.update_quick_ticket(?,?,?,?,?,?,?,?)}");

			pCall.setInt(1, iTicketId);
	    	pCall.setInt(2, iOrgId);
	    	pCall.setInt(3, iLineId);
	    	pCall.setString(4, strSeverity);
	    	pCall.setString(5, strStatus);
	    	pCall.setString(6, iUpdatedBy);
	    	pCall.setString(7, strNotes);

	    	pCall.registerOutParameter(8, OracleTypes.NUMBER);

	    	pCall.execute();

	    	int iErrorLC = pCall.getInt(8);			
			System.out.println("in updateQuickTicket iErrorLC: " + iErrorLC);
	    	if(iErrorLC == -1) isTrue = false;
	    	else isTrue = true;
	    	if(!isTrue) throw new CanonCustAppExceptionUtil(100001, "update assignment failed", "Class: Canon_E193_QuickTicketDAO, Method:updateQuickTicket()");
	    }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:updateQuickTicket()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:updateQuickTicket()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
				if(connNonBill!=null) connNonBill.close();
	    		if(connNonBillUtil != null) connNonBillUtil.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:updateQuickTicket()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:updateQuickTicket()"));
	    	}
	    }
		return isTrue;
	}
	public List getQTSearchTypes()throws CanonCustAppExceptionUtil{
		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
	    CallableStatement pCall = null;
		ResultSet rs = null;
		List searchTypes = new ArrayList();
	    try {
	    	System.out.println("in getQTSearchTypes");
	    	connUtil = new CanonCustAppDBUtil();
	    	conn = (Connection)(connUtil.getConnection());

	    	pCall = conn.prepareCall("{call canon_e193_csquick_sqls_pkg.get_qt_search_types(?)}");
			pCall.registerOutParameter(1, OracleTypes.CURSOR);

	    	pCall.execute();

	    	rs = (ResultSet) pCall.getObject(1);	
			while(rs.next()) {
				String strArray[] = new String[2];
				strArray[0]=(String)rs.getString("FLEX_VALUE");
				strArray[1]=(String)rs.getString("DESCRIPTION");
				searchTypes.add(strArray);
		 	}
			
		}catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQTSearchTypes()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:getQTSearchTypes()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
				if(rs != null) rs.close();
				if(conn!=null) conn.close();
	    		if(connUtil != null) connUtil.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:updateQuickTicket()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_QuickTicketDAO, Method:updateQuickTicket()"));
	    	}
		}
	 return searchTypes;
	}
	//Start Changes for S21 by Mangala
	//public int getResourceId(int userId){
	public String getResourceId(String userId){
	//End Changes for S21 by Mangala	
		CanonCustAppDBUtil connUtil = null;
		Connection connection = null;
		CallableStatement cstmt  = null;
		//Start Changes for S21 by Mangala
		//int resourceId = 0;
		String resourceId = "";
		//End Changes for S21 by Mangala
		try
		{
			System.out.println("in getResourceId userId " + userId);
		   String plsqlExp =  " Begin "
                           + " :1 := canon_e193_cs_evolution_pkg.get_resource_id(:2) ;  "
                           + " End;";
 
			  connUtil = new CanonCustAppDBUtil();
			  connection = (Connection)(connUtil.getConnection());	
			  cstmt = connection.prepareCall(plsqlExp);
			  cstmt.setString(2, userId);
			  cstmt.registerOutParameter(1,Types.INTEGER);
			  cstmt.execute();
			  //Start Changes for S21 by Mangala
			  //resourceId = cstmt.getInt(1);
			  resourceId = cstmt.getString(1);
			  //End Changes for S21 by Mangala
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
				if(cstmt != null)
				  cstmt.close();
				if(connection!=null) connection.close();
	    		if(connUtil != null) connUtil.releaseConnection();
			}
			catch (SQLException eSQLExp)
			{
			}
			catch (Exception eExp)
			{
			}
		}
		return resourceId;		
	}
}
