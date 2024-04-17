package com.canon.oracle.custapp.custinq.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

import com.canon.common.CanonCommonUtil;
import com.canon.oracle.custapp.util.CanonCustAppDBUtil;
import com.canon.oracle.custapp.util.CanonCustAppExceptionUtil;

/**
 * Canon_E193_SystemDAO Retrieves system variables from database
 * Creation date: (7/27/2005 1:20:15 PM)
 * @author:
 * Changes
 *
 * <pre>
 * Flag  Date       By         Description
 * ----  ---------- ---------- ---------------------------------------------
 * 18-Dec-2006  Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 * 29-Jan-2016  Mangala Shenoy	   Modified for S21 changes
 * </pre>
 */
public class Canon_E193_SystemDAO {
	CanonCommonUtil util;
	private   String clsName="Canon_E193_SystemDAO";
	/**
	 * Canon_E193_SystemDAO constructor comment.
	 */
	public Canon_E193_SystemDAO() {
		util = new CanonCommonUtil();
	}

	/**
	 * Get org id from database.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @param iRespId int
	 * @return int
	 */
	public int getOrgID(String iRespId) throws CanonCustAppExceptionUtil
  	{
		int iOrgId = 0;
	  	CallableStatement cstmtOrg = null;

		// Get Connection
		CanonCustAppDBUtil connOrg = new CanonCustAppDBUtil();
		Connection connOrgConnection = (Connection)(connOrg.getConnection());

		try
		{
			cstmtOrg = connOrgConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_org_id(?,?)}");
			cstmtOrg.setString(1, iRespId);
		  	cstmtOrg.registerOutParameter(2,java.sql.Types.INTEGER);
			cstmtOrg.execute();

			iOrgId= cstmtOrg.getInt(2);

			cstmtOrg.close();
			connOrgConnection.close();
			connOrg.releaseConnection();
		}

		catch (CanonCustAppExceptionUtil csExp) {
		  throw (csExp);
		}
		catch (SQLException eSQLExp) {
		  throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_SystemDAO, Method:getOrgID(int iRespId)"));
		}
		catch (Exception eExp) {
		  throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_SystemDAO, Method:getOrgID(int iRespId)"));
		}
		finally {
			try {
				if(cstmtOrg != null)
					cstmtOrg.close();
				if(connOrgConnection != null)
					connOrgConnection.close();
				if(connOrg != null)
					connOrg.releaseConnection();
			}
			catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			}
			catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_SystemDAO, Method:getOrgID(int iRespId)"));
			}
			catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_SystemDAO, Method:getOrgID(int iRespId)"));
			}
		}
		return iOrgId;
  	}


	/**
	 * Get org name from database.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @param iOrgId int
	 * @return java.lang.String
	 */
 	public String getOrgName(int iOrgId) throws CanonCustAppExceptionUtil
	{
		String strOrgName = "";

		CallableStatement cstmtOrg = null;

		// Get Connection
		CanonCustAppDBUtil connOrg = new CanonCustAppDBUtil();
		Connection connOrgConnection = (Connection)(connOrg.getConnection());

		try
		{
			cstmtOrg = connOrgConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_org_name(?,?)}");
			cstmtOrg.setInt(1, iOrgId);
		  	cstmtOrg.registerOutParameter(2,java.sql.Types.VARCHAR);
			cstmtOrg.execute();
			strOrgName = cstmtOrg.getString(2);

			cstmtOrg.close();
			connOrgConnection.close();
			connOrg.releaseConnection();

		}
		catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		}
		catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_SystemDAO, Method:getOrgName(int iOrgId)"));
		}
		catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_SystemDAO, Method:getOrgName(int iOrgId)"));
		}
		finally {
			try {
				if(cstmtOrg != null)
					cstmtOrg.close();
				if(connOrgConnection != null)
					connOrgConnection.close();
				if(connOrg != null)
					connOrg.releaseConnection();
      		}
			catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			}
			catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_SystemDAO, Method:getOrgName(int iOrgId)"));
			}
			catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_SystemDAO, Method:getOrgName(int iOrgId)"));
      		}
		}
		return strOrgName;
	}

	/**
     * Get names from database.
     * Creation date: (7/27/2005 5:01:24 PM)
     * @param iUserId int
     * @return java.util.ArrayList
     */
    public ArrayList getNames(String iUserId) throws CanonCustAppExceptionUtil
    {
		ArrayList alNames = new ArrayList();

		CallableStatement cstmtNames = null;

		// Get Connection
		CanonCustAppDBUtil connNames = new CanonCustAppDBUtil();
		Connection connNamesConnection = (Connection)(connNames.getConnection());

		try
		{
			cstmtNames = connNamesConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_emp_user_name(?,?,?)}");
			cstmtNames.setString(1, iUserId);
			cstmtNames.registerOutParameter(2,java.sql.Types.VARCHAR);
			cstmtNames.registerOutParameter(3,java.sql.Types.VARCHAR);
			cstmtNames.execute();

			alNames.add(cstmtNames.getString(2));
			alNames.add(cstmtNames.getString(3));

     		cstmtNames.close();
     		connNamesConnection.close();
     		connNames.releaseConnection();
		}
	    catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
	    }
	    catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_SystemDAO, Method:getNames(int iUserId)"));
	    }
	    catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_SystemDAO, Method:getNames(int iUserId)"));
	    }
	    finally {
			try {
				if(cstmtNames != null)
				  	cstmtNames.close();
				if(connNamesConnection != null)
				  	connNamesConnection.close();
				if(connNames != null)
				  	connNames.releaseConnection();
	      	}
	      	catch (CanonCustAppExceptionUtil csExp) {
	        	throw (csExp);
	      	}
	      	catch (SQLException eSQLExp) {
	        	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_SystemDAO, Method:getNames(int iUserId)"));
	      	}
	      	catch (Exception eExp) {
	        	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_SystemDAO, Method:getNames(int iUserId)"));
	      	}
	    }
	    return alNames;
	}

  /**
   * Get resource id from database.
   * Creation date: (7/27/2005 5:01:24 PM)
   * @param iUserId int
   * @return int
   */
    //Start changes for S21 by Mangala
	//public int getResourceId(int iUserId) throws CanonCustAppExceptionUtil {
    public String getResourceId(String iUserId) throws CanonCustAppExceptionUtil {
	//End Changes for S21 by Mangala
		CallableStatement cstmtRes = null;
		//Start Changes for S21 by Mangala
		//int iResource = -1;
		String iResource = "";
		//End Changes for S21 by Mangala
		//Get Connection
		CanonCustAppDBUtil connRes = new CanonCustAppDBUtil();
		Connection connResConnection = (Connection)(connRes.getConnection());

		try
		{
			cstmtRes = connResConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.get_resource_id(?,?)}");
			cstmtRes.setString(1,iUserId);
			//Start changes for S21 by Mangala
			//cstmtRes.registerOutParameter(2,java.sql.Types.INTEGER);
			cstmtRes.registerOutParameter(2,java.sql.Types.VARCHAR);
			//End Changes for S21 by Mangala
			cstmtRes.execute();
			//Start Changes for S21 by Mangala
			//iResource = cstmtRes.getInt(2);
			iResource = cstmtRes.getString(2);
			//End Changes for S21 by Mangala
			cstmtRes.close();
			connResConnection.close();
			connRes.releaseConnection();
		}
		catch (CanonCustAppExceptionUtil csExp) {
		  throw (csExp);
		}
		catch (SQLException eSQLExp) {
		  throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_SystemDAO, Method:getResourceId()"));
		}
		catch (Exception eExp) {
		  throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_SystemDAO, Method:getResourceId()"));
		}
		finally {
			try{
				if(cstmtRes != null)
				  cstmtRes.close();
				if(connResConnection != null)
				  connResConnection.close();
				if(connRes != null)
				  connRes.releaseConnection();
			}
			catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			}
			catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_SystemDAO, Method:getResourceId()"));
			}
			catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_SystemDAO, Method:getResourceId()"));
			}
		}
		return iResource;
	}

/** ITG# 74988 - Begin */

    /**
     * Get user profiles from database.
     * Creation date: (11/30/2006)
     * @param iUserId int
     * @return java.util.ArrayList
     */
    public ArrayList getUserProfiles(String iUserId) throws CanonCustAppExceptionUtil
    {
		ArrayList alProfiles = new ArrayList();

		CallableStatement cstmtNames = null;

		// Get Connection
		CanonCustAppDBUtil connNames = new CanonCustAppDBUtil();
		Connection connNamesConnection = (Connection)(connNames.getConnection());

		try
		{
			cstmtNames = connNamesConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_user_profiles(?,?,?,?)}");
			cstmtNames.setString(1, iUserId);
			cstmtNames.registerOutParameter(2,java.sql.Types.VARCHAR);
			cstmtNames.registerOutParameter(3,java.sql.Types.VARCHAR);
			// ITG# 73987 - CBS Con Kireet Kumar Bollam: Begin
			cstmtNames.registerOutParameter(4,java.sql.Types.VARCHAR);
			// ITG# 73987 - CBS Con Kireet Kumar Bollam: End
			cstmtNames.execute();

			alProfiles.add(cstmtNames.getString(2));
			alProfiles.add(cstmtNames.getString(3));
			// ITG# 73987 - CBS Con Kireet Kumar Bollam: Begin
			alProfiles.add(cstmtNames.getString(4));
			// ITG# 73987 - CBS Con Kireet Kumar Bollam: End

     		cstmtNames.close();
     		connNamesConnection.close();
     		connNames.releaseConnection();
		}
	    catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
	    }
	    catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_SystemDAO, Method:getUserProfiles(int iUserId)"));
	    }
	    catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_SystemDAO, Method:getUserProfiles(int iUserId)"));
	    }
	    finally {
			try {
				if(cstmtNames != null)
				  	cstmtNames.close();
				if(connNamesConnection != null)
				  	connNamesConnection.close();
				if(connNames != null)
				  	connNames.releaseConnection();
	      	}
	      	catch (CanonCustAppExceptionUtil csExp) {
	        	throw (csExp);
	      	}
	      	catch (SQLException eSQLExp) {
	        	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_SystemDAO, Method:getUserProfiles(int iUserId)"));
	      	}
	      	catch (Exception eExp) {
	        	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_SystemDAO, Method:getUserProfiles(int iUserId)"));
	      	}
	    }
	    return alProfiles;
	}
    
    public String getUploadFilePath(){
		
		String res="";
		ResultSet rs = null;
		Connection connection = null;
		CallableStatement cstmt = null;
		try {
			connection = TransactionScope.getConnection();
			cstmt = (CallableStatement)connection.prepareCall("{CALL CANON_E307_CALL_SUPPORT_PKG.GET_FILE_UPLOAD_PATH(?)}");
			cstmt.registerOutParameter(1, OracleTypes.VARCHAR);
		    cstmt.execute();
			res= util.checkNull(cstmt.getString(1));
		} catch (Exception ex1) {
			util.logMsg(true,clsName+".getUploadFilePath ", ex1.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
				if (connection != null)
					TransactionScope.releaseConnection(connection);
			} catch (Exception ex2) {
				util.logMsg(true,clsName+".getUploadFilePath", ex2.getMessage());
			}
		}
		return res;
	}

/** ITG# 74988 - End */

}