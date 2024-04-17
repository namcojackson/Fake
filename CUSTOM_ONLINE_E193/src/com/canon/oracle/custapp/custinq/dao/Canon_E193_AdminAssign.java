package com.canon.oracle.custapp.custinq.dao;

import java.sql.*;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

import com.canon.oracle.custapp.custinq.beans.*;
import com.canon.oracle.custapp.util.*;

/**
 * Canon_E193_AdminAssign Retrieves Amin information from database
 * Creation date:
 * @author:
 * <pre>
 * Flag  Date       By         Description
 * ----  ---------- ---------- ---------------------------------------------
 * 18-Dec-2006  Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 *                                 Added strRegionCode parameter in getCategories()
 * 								    and getGroup()
 * 29-Jan-2016  Mangala Shenoy     Modified for S21 changes
 * </pre>
 */
public class Canon_E193_AdminAssign {

  /**
   * Canon_E193_AdminAssign constructor.
   */
   public Canon_E193_AdminAssign() {}

  /**
   * Get Categories from database.
   * Creation date: (7/27/2005 5:01:24 PM)
   * @param iOrgId int
   * @return java.util.ArrayList
   */
  public ArrayList getCategories(int iOrgId, String strRegionCode) throws CanonCustAppExceptionUtil {

    CallableStatement cstmtCat = null;
    ResultSet rsCat = null;
    ArrayList alCategories = new ArrayList();

    //Get Connection
    CanonCustAppDBUtil connCat = new CanonCustAppDBUtil();
    Connection connCatConnection = (Connection)(connCat.getConnection());

    try
    {
		cstmtCat = connCatConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_admin_category(?,?,?)}");

		cstmtCat.setInt(1,iOrgId);
		cstmtCat.setString(2,strRegionCode);
		cstmtCat.registerOutParameter(3,OracleTypes.CURSOR);

		cstmtCat.execute();

		rsCat = (ResultSet) cstmtCat.getObject(3);

		while(rsCat.next()) {
			Canon_E193_AdminAssignObj objCat = new  Canon_E193_AdminAssignObj();
			objCat.setICatId(rsCat.getInt(1));
			objCat.setStrCatCode(rsCat.getString(2));
			objCat.setStrCatDesc(rsCat.getString(3));
			objCat.setStrParentCatCode(rsCat.getString(4));
			objCat.setICrmRoleId(rsCat.getString(5));
			objCat.setStrCrmRoleCode(rsCat.getString(6));
			objCat.setStrCrmRoleName(rsCat.getString(7));
			objCat.setStrCrmRoleTypeCode(rsCat.getString(8));
			objCat.setICrmResourceGroupId(rsCat.getInt(9));
			objCat.setStrCrmGroupName(rsCat.getString(10));
			objCat.setStrCrmGroupDesc(rsCat.getString(11));
			//Start Changes for S21 by Mangala
			//objCat.setICrmResourceId(rsCat.getInt(12));
			objCat.setICrmResourceId(rsCat.getString(12));
			//End Changes for S21 by Mangala
			objCat.setStrCrmResourceName(rsCat.getString(13));
        	alCategories.add(objCat);
      	}
		cstmtCat.close();
		rsCat.close();
		connCatConnection.close();
		connCat.releaseConnection();
    }
    catch (CanonCustAppExceptionUtil csExp) {
      throw (csExp);
    }
    catch (SQLException eSQLExp) {
      throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AdminAssign, Method:getCategories()"));
    }
    catch (Exception eExp) {
      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AdminAssign, Method:getCategories()"));
    }
    finally {
      try
      {
        if(cstmtCat != null)
          cstmtCat.close();
        if(rsCat != null)
          rsCat.close();
        if(connCatConnection != null)
          connCatConnection.close();
        if(connCat != null)
          connCat.releaseConnection();
      }
      catch (CanonCustAppExceptionUtil csExp) {
        throw (csExp);
      }
      catch (SQLException eSQLExp) {
        throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AdminAssign, Method:getCategories()"));
      }
      catch (Exception eExp) {
        throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AdminAssign, Method:getCategories()"));
      }
    }
    return alCategories;
  }

  /**
   * Get CRM Roles from database.
   * Creation date: (7/27/2005 5:01:24 PM)
   * @return java.util.ArrayList
   */
  public ArrayList getRoleResource(int iOrgId, String iGroupId) throws CanonCustAppExceptionUtil {

    CallableStatement cstmtRole = null;
    ResultSet rsRole = null;
    ArrayList alRole = new ArrayList();

    //Get Connection
    CanonCustAppDBUtil connRole = new CanonCustAppDBUtil();
    Connection connRoleConnection = (Connection)(connRole.getConnection());

    try
    {
    	System.out.println("Before calling CANON_E193_CS_SQLS_PKG.select_admin_role_resource " );
		cstmtRole = connRoleConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_admin_role_resource(?,?,?)}");
		cstmtRole.setInt(1, iOrgId);
		cstmtRole.setString(2, iGroupId);
		cstmtRole.registerOutParameter(3, OracleTypes.CURSOR);
		cstmtRole.execute();

		rsRole = (ResultSet) cstmtRole.getObject(3);

	    while(rsRole.next()) {
			Canon_E193_AdminCrmObj objRole = new Canon_E193_AdminCrmObj();
			//Start changes for S21 by Mangala
			//objRole.setIAttribute1(rsRole.getInt(1));
			objRole.setIAttribute1(rsRole.getString(1));
			//System.out.println("objRole.setIAttribute1(rsRole.getString(1)) :  "+objRole.getIAttribute1() );
			//End changes for S21 by Mangala
			objRole.setStrAttribute2(rsRole.getString(2));
			objRole.setStrAttribute3(rsRole.getString(3));
			objRole.setStrAttribute4(rsRole.getString(4));
			//Start changes for S21 by Mangala
			//objRole.setIAttribute11(rsRole.getInt(5));
			objRole.setIAttribute11(rsRole.getString(5));
			//End changes for S21 by Mangala
			objRole.setStrAttribute5(rsRole.getString(6));
			alRole.add(objRole);
		}

		cstmtRole.close();
		rsRole.close();
		connRoleConnection.close();
		connRole.releaseConnection();
    }
    catch (CanonCustAppExceptionUtil csExp) {
      throw (csExp);
    }
    catch (SQLException eSQLExp) {
      throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AdminAssign, Method:getRole()"));
    }
    catch (Exception eExp) {
      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AdminAssign, Method:getRole()"));
    }
    finally {
      try
      {
        if(cstmtRole != null)
          cstmtRole.close();
        if(rsRole != null)
          rsRole.close();
        if(connRoleConnection != null)
        	connRoleConnection.close();
        if(connRole != null)
          connRole.releaseConnection();
      }
      catch (CanonCustAppExceptionUtil csExp) {
        throw (csExp);
      }
      catch (SQLException eSQLExp) {
        throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AdminAssign, Method:getRole()"));
      }
      catch (Exception eExp) {
        throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AdminAssign, Method:getRole()"));
      }
    }
    return alRole;
  }

  /**
   * Get CRM Groups from database.
   * Creation date: (7/27/2005 5:01:24 PM)
   * @return java.util.ArrayList
   */
  public ArrayList getGroup(int iOrgId, String strRegionCode) throws CanonCustAppExceptionUtil {

    CallableStatement cstmtGroup = null;
    ResultSet rsGroup = null;
    ArrayList alGroup = new ArrayList();

    //Get Connection
    CanonCustAppDBUtil connGroup = new CanonCustAppDBUtil();
    Connection connGroupConnection = (Connection)(connGroup.getConnection());

    try
    {
		cstmtGroup = connGroupConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_admin_group(?,?,?)}");
		cstmtGroup.setInt(1,iOrgId);
		cstmtGroup.setString(2,strRegionCode);
		cstmtGroup.registerOutParameter(3, OracleTypes.CURSOR);
		cstmtGroup.execute();

		rsGroup = (ResultSet) cstmtGroup.getObject(3);

	    while(rsGroup.next()) {
			Canon_E193_AdminCrmObj objGroup = new Canon_E193_AdminCrmObj();
			//Start changes for S21 by Mangala
			//objGroup.setIAttribute1(rsGroup.getInt(1));
			objGroup.setIAttribute1(rsGroup.getString(1));
			//End changes for S21 by Mangala
			objGroup.setStrAttribute2(rsGroup.getString(2));
			objGroup.setStrAttribute3(rsGroup.getString(3));
			alGroup.add(objGroup);
		}

		cstmtGroup.close();
		rsGroup.close();
		connGroupConnection.close();
		connGroup.releaseConnection();
    }
    catch (CanonCustAppExceptionUtil csExp) {
      throw (csExp);
    }
    catch (SQLException eSQLExp) {
      throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AdminAssign, Method:getGroup()"));
    }
    catch (Exception eExp) {
      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AdminAssign, Method:getGroup()"));
    }
    finally {
      try
      {
        if(cstmtGroup != null)
          cstmtGroup.close();
        if(rsGroup != null)
          rsGroup.close();
        if(connGroupConnection != null)
        	connGroupConnection.close();
        if(connGroup != null)
          connGroup.releaseConnection();
      }
      catch (CanonCustAppExceptionUtil csExp) {
        throw (csExp);
      }
      catch (SQLException eSQLExp) {
        throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AdminAssign, Method:getGroup()"));
      }
      catch (Exception eExp) {
        throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AdminAssign, Method:getGroup()"));
      }
    }
    return alGroup;
  }

  /**
   * Update CRM categories in database.
   * Creation date: (7/27/2005 5:01:24 PM)
   * @return int
   */
  public int setCategories(int iCatId, String iRoleId, String strRoleCode, String strRoleName, String strRoleTypeCode,
		  //Start Changes for S21 by Mangala
  		//int iGroupId, String strGroupName, String strGroupDesc, int iResId, String strResName) throws CanonCustAppExceptionUtil {
		int iGroupId, String strGroupName, String strGroupDesc, String iResId, String strResName) throws CanonCustAppExceptionUtil {
	//End Changes for S21 by Mangala
    CallableStatement cstmtUpdtCat = null;
    int result;

    System.out.println("In SetCategories:= "+" iCatId = "+iCatId +" , "+" ~iRoleId =  "+ iRoleId +" , "+
    		" strRoleCode = "+strRoleCode +" , "+" ~RoleName = " + strRoleName+" , "+ 
    		" strRoleTypeCode = "+strRoleTypeCode+" , "+" iGroupId = "+iGroupId +" , "+
    		" ~ strGroupDesc =  " + strGroupDesc+" , "+" strGroupName = "+strGroupName+
    		" , "+" iResourceId = "+ iResId+" , "+"~ strResourceName= " +strResName);	
    //Get Connection
    CanonCustAppDBUtil connUpdtCat = new CanonCustAppDBUtil();
    Connection connUpdtCatConnection = (Connection)(connUpdtCat.getConnection());

    try
    {
		cstmtUpdtCat = connUpdtCatConnection.prepareCall("{call CANON_E193_CS_INSERT_SQLS_PKG.UPDATE_CATEGORY(?,?,?,?,?,?,?,?,?,?,?)}");
		cstmtUpdtCat.setInt(1, iCatId);
		cstmtUpdtCat.setString(2, iRoleId.toString());
		//System.out.println(" In setCategories iRoleId= "+ iRoleId);
		cstmtUpdtCat.setString(3, strRoleCode.toString());
		//System.out.println(" In setCategories strRoleCode= "+ strRoleCode);
		cstmtUpdtCat.setString(4, strRoleName);
		//System.out.println(" In setCategories strRoleName= "+ strRoleName);
		cstmtUpdtCat.setString(5, strRoleTypeCode);
		//System.out.println(" In setCategories strRoleName= "+ strRoleName);
		cstmtUpdtCat.setInt(6, iGroupId);
		cstmtUpdtCat.setString(7, strGroupName);
		cstmtUpdtCat.setString(8, strGroupDesc);
		//Start changes for S21 by Mangala
		//cstmtUpdtCat.setInt(9, iResId);
		cstmtUpdtCat.setString(9, iResId);
		//End Changes for S21 by Mangala
		cstmtUpdtCat.setString(10, strResName);
		cstmtUpdtCat.registerOutParameter(11, java.sql.Types.INTEGER);
		//System.out.println(" Before execute the Query");
		cstmtUpdtCat.execute();

		result = cstmtUpdtCat.getInt(11);
		//System.out.println(" After execute the Query, Result=" +result);
		cstmtUpdtCat.close();
		connUpdtCatConnection.close();
		connUpdtCat.releaseConnection();
    }
    catch (CanonCustAppExceptionUtil csExp) {
      throw (csExp);
    }
    catch (SQLException eSQLExp) {
      throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AdminAssign, Method:setCategories()"));
    }
    catch (Exception eExp) {
      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AdminAssign, Method:setCategories()"));
    }
    finally {
      try
      {
        if(cstmtUpdtCat != null)
          cstmtUpdtCat.close();
        if(connUpdtCatConnection != null)
        	connUpdtCatConnection.close();
        if(connUpdtCat != null)
          connUpdtCat.releaseConnection();
      }
      catch (CanonCustAppExceptionUtil csExp) {
        throw (csExp);
      }
      catch (SQLException eSQLExp) {
        throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AdminAssign, Method:setCategories()"));
      }
      catch (Exception eExp) {
        throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AdminAssign, Method:setCategories()"));
      }
    }
    return result;
  }
}
