package com.canon.oracle.custapp.util;

/**
 * CanonCustAppDBUtil Contains connection, prepared statement, resultset utilities.
 * Creation date: (8/23/2005 6:05:38 PM).
 * @author:
 */

//import oracle.sql.*;
import oracle.jdbc.*;
import java.util.*;
import java.sql.*;
import oracle.apps.jtf.aom.transaction.TransactionScope;

public class CanonCustAppDBUtil
{
  public Connection conn = null;

 /**
  * CanonCustAppDBUtil constructor.
  */
  public CanonCustAppDBUtil() throws CanonCustAppExceptionUtil
  {
    try {
      conn = (Connection)TransactionScope.getConnection();
      System.out.println("connection is established");
    }
    catch (Exception eExp) {
      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonCustAppDBUtil, Method:CanonCustAppDBUtil()"));
    }
  }

  /**
   * Release Connection
   * Creation date: (7/27/2005 5:01:24 PM)
   * @return Connection
   */
   public Connection getConnection()
   {
		return conn;
   }

 /**
  * Added by OFF SHORE
  * CanonCustAppDBUtil Overloaded constructor.
  * Setting the OrgId for the current DB_SESSION
  * @param  strOrgId java.lang.String
  */
  public CanonCustAppDBUtil(String strOrgId)  throws CanonCustAppExceptionUtil
  {
	CallableStatement callStmt = null;
    try
    {
      conn     = (Connection)TransactionScope.getConnection();
	  callStmt =  conn.prepareCall("{call fnd_profile.PUT('ORG_ID',?) }");
	  callStmt.setInt(1, Integer.parseInt(strOrgId));
	  callStmt = getCallStmt(callStmt,"");
	  callStmt =  conn.prepareCall("{call dbms_application_info.set_client_info(?)  }");
	  callStmt.setInt(1, Integer.parseInt(strOrgId));
	  callStmt = getCallStmt(callStmt,"");
    }
    catch (Exception eExp)
    {
	  throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonCustAppDBUtil, Method:CanonCustAppDBUtil( ORG_ID )"));
    }
  }


  /**
   * Release Connection
   * Creation date: (7/27/2005 5:01:24 PM)
   * @return boolean
   */
  public boolean releaseConnection() throws CanonCustAppExceptionUtil
  {
    boolean OK = true;
    try{
      conn.close();
      OK = true;
    }
  //  catch (CanonCustAppExceptionUtil csExp) {
  //      OK = false;
  //      throw (csExp);
  //  }
    catch (SQLException eSQLExp) {
        OK = false;
        throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: CanonCustAppDBUtil, Method:releaseConnection()"));
    }
    catch (Exception eExp) {
        OK = false;
        throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonCustAppDBUtil, Method:releaseConnection()"));
    }
    return OK;
  }
	/**
	* Added by OFF SHORE
	* Static Function: Get a Callable Statement
	* Creation date: (09/03/2005 14:30 )
	* @param callStmt java.sql.CallableStatement;
	* @param strDummy java.lang.String
	* @return CallableStatement
	*/

  public static CallableStatement getCallStmt(CallableStatement callStmt,String strDummy) throws CanonCustAppExceptionUtil
  {
    try
    {
      callStmt.executeUpdate();
    }
    catch (SQLException eSQLExp) {
        throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: CanonCustAppDBUtil, Method:getCallStmt(CallableStatement,String)"));
    }
    catch (Exception eExp) {
        throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonCustAppDBUtil, Method:getCallStmt(CallableStatement,String)"));
    }
    return callStmt;
  }

  /**
   * Added by OFF SHORE
   * Get a Callable Statement
   * Creation date: (09/03/2005 14:50 )
   * @param callStmt java.sql.CallableStatement;
   * @return CallableStatement
   */
  public CallableStatement getCallStmt(CallableStatement callStmt) throws CanonCustAppExceptionUtil
  {
    try
    {
      callStmt.executeUpdate();
    }
    catch (SQLException eSQLExp) {
        throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: CanonCustAppDBUtil, Method:getCallStmt(CallableStatement)"));
    }
    catch (Exception eExp) {
        throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonCustAppDBUtil, Method:getCallStmt(CallableStatement)"));
    }
    return callStmt;
  }

  /**
   * Added by OFF SHORE
   * Get a Callable Statement
   * Creation date: (09/03/2005 14:50 )
   * @param callStmt java.sql.CallableStatement;
   * @return CallableStatement
   */
  public OracleCallableStatement getOracleCallStmt(OracleCallableStatement orclCallStmt)  throws CanonCustAppExceptionUtil
  {
    try
    {
      orclCallStmt.executeUpdate();
    }
    catch (SQLException eSQLExp) {
        throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: CanonCustAppDBUtil, Method:getOracleCallStmt(OracleCallableStatement)"));
    }
    catch (Exception eExp) {
        throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonCustAppDBUtil, Method:getOracleCallStmt(OracleCallableStatement)"));
    }
    return orclCallStmt;
  }

  /**
   * Get a prepared statment
   * Creation date: (7/27/2005 5:01:24 PM)
   * @param strQuery java.lang.String
   * @return PreparedStatement
   */
  public PreparedStatement getPreparedStmt(String strQuery) throws CanonCustAppExceptionUtil
  {
    PreparedStatement pstmt = null;

    try {
      pstmt = conn.prepareStatement(strQuery);
    }
   // catch (CanonCustAppExceptionUtil csExp) {
  //    throw (csExp);
  //  }
    catch (SQLException eSQLExp) {
      throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: CanonCustAppDBUtil, Method:getPreparedStmt(String strQuery)"));
    }
    catch (Exception eExp) {
      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonCustAppDBUtil, Method:getPreparedStmt(String strQuery)"));
    }
    return pstmt;
  }

  /**
   * Get a result set for a given PreparedStatement
   * Creation date: (7/27/2005 5:01:24 PM)
   * @param pstmt PreparedStatement
   * @return ResultSet
   */
  public ResultSet getResultSet(PreparedStatement pstmt) throws CanonCustAppExceptionUtil
  {
    ResultSet rs = null;

    try {
      rs = pstmt.executeQuery();
    }
   // catch (CanonCustAppExceptionUtil csExp) {
   //   throw (csExp);
   // }
    catch (SQLException eSQLExp) {
      throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: CanonCustAppDBUtil, Method:getResultSet(PreparedStatement pstmt)"));
    }
    catch (Exception eExp) {
      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonCustAppDBUtil, Method:getResultSet(PreparedStatement pstmt)"));
    }
    return rs;
  }

  /**
   * Get a result set using Statement
   * Creation date: (7/27/2005 5:01:24 PM)
   * @param strQuery java.lang.String
   * @return ResultSet
   */
  public ResultSet getResultSet(String strQuery) throws CanonCustAppExceptionUtil
  {
  Statement stmt = null;
  ResultSet rs = null;

    try {
    stmt = conn.createStatement();
      rs = stmt.executeQuery(strQuery);
    }
   // catch (CanonCustAppExceptionUtil csExp) {
   //   throw (csExp);
   // }
    catch (SQLException eSQLExp) {
      throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: CanonCustAppDBUtil, Method:getResultSet(String strQuery)"));
    }
    catch (Exception eExp) {
      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonCustAppDBUtil, Method:getResultSet(String strQuery)"));
    }
    return rs;
  }

  /**
   * Added by OFF SHORE
   * Get a result set by using Statement
   * Creation date: (7/27/2005 5:01:24 PM)
   * @param strQuery java.lang.String
   * @param stObj    java.lang.String
   * @return ResultSet
   */
  public ResultSet getResultSet(String strQuery, Statement stObj) throws CanonCustAppExceptionUtil
  {
    ResultSet resSet   = null;
    try
    {
      resSet = stObj.executeQuery(strQuery);
    }
    catch (SQLException eSQLExp) {
      throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: CanonCustAppDBUtil, Method:getResultSet(String,Statement )"));
    }
    catch (Exception eExp) {
      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonCustAppDBUtil, Method:getResultSet(String,Statement )"));
    }

    return resSet;
  }

  /**
   * Execute Update query.
   * Creation date: (7/27/2005 5:01:24 PM)
   * @param strQuery java.lang.String
   * @return int
   */
  public int executeUpdate(String strQuery) throws CanonCustAppExceptionUtil
  {
    Statement stmt = null;
    int iCount = 0;

    try {
    stmt = conn.createStatement();
      iCount = stmt.executeUpdate(strQuery);
    }
    //catch (CanonCustAppExceptionUtil csExp) {
   //   throw (csExp);
   // }
    catch (SQLException eSQLExp) {
      throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: CanonCustAppDBUtil, Method:executeUpdate(String strQuery)"));
    }
    catch (Exception eExp) {
      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonCustAppDBUtil, Method:executeUpdate(String strQuery)"));
    }
    return iCount;
  }

  /**
   * Get a callable statment
   * Creation date: (7/27/2005 5:01:24 PM)
   * @param strQuery java.lang.String
   * @return PreparedStatement
   */
  public CallableStatement getCallableStmt(String strProcName) throws CanonCustAppExceptionUtil
  {
    CallableStatement cstmt = null;

    try {
      cstmt = conn.prepareCall("{call strProcName}");
    }
    catch (SQLException eSQLExp) {
      throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: CanonCustAppDBUtil, Method:getCallableStmt(String strProcName)"));
    }
    catch (Exception eExp) {
      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonCustAppDBUtil, Method:getCallableStmt(String strProcName)"));
    }
    return cstmt;
  }

}