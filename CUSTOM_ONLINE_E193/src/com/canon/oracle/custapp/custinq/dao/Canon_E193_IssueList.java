package com.canon.oracle.custapp.custinq.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

import com.canon.oracle.custapp.custinq.beans.Canon_E193_IssueListObj;
import com.canon.oracle.custapp.util.CanonCustAppDBUtil;
import com.canon.oracle.custapp.util.CanonCustAppExceptionUtil;

/**
 * Title:       Canon_E193_IssueList<br>
 * Description: This is the main class of billing issues Functions<br>
 * <p>
 * CopyRight:   Canon<br>
 * Company:     Canon<br>
 * @author:     Subba<br>
 * @version:    1.0, (26-Sep-2005)<br>
 * @modified by:<br>
 * @version:<br>
 * @modification Detail:<br>
 * <pre>
 * Flag  Date         By                 Description
 * ----  ----------   ----------         ---------------------------------------------
 *       18-Dec-2006  Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 *       24-Mar-2008  Chandra Sekhar     ITG # 156613 - New Sub Categories
 * </pre>
 */

public class Canon_E193_IssueList {
    /**
     * Canon_E193_IssueList constructor.
     */

    public Canon_E193_IssueList() {
        super();
    }
    /**
     * This method will return the list of Billing Issues.
     * <p>
     * @return java.util.ArrayList.
     * @param iOrgId int.
     * @param strIssueType java.lang.String.
     * @param iCatId int.
     * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
     */
    public ArrayList getIssueList(int iOrgId, String strRegionCode, String strIssueType, int iCatId) throws CanonCustAppExceptionUtil {
        ArrayList alIssueList = new ArrayList();
        //Get Connection
        CanonCustAppDBUtil connIssueUtil = null;
        Connection connIssue = null;
        CallableStatement pCall = null;
        ResultSet rsIssue = null;
        Canon_E193_IssueListObj objIssue = null;
        System.out.println("in getIssueList iOrgId " + iOrgId + " strRegionCode " + strRegionCode + " strIssueType "
        		+ strIssueType + " iCatId " + iCatId);
        try {
            connIssueUtil = new CanonCustAppDBUtil();
            connIssue = (Connection)(connIssueUtil.getConnection());
            pCall = connIssue.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_issue_list(?,?,?,?,?)}");
            pCall.setInt(1, iOrgId);
            pCall.setString(2, strRegionCode);
            pCall.setString(3, strIssueType);
            pCall.setInt(4, iCatId);
            pCall.registerOutParameter(5, OracleTypes.CURSOR);

            pCall.execute();

            rsIssue = (ResultSet) pCall.getObject(5);

            while(rsIssue.next()) {
                objIssue = new Canon_E193_IssueListObj();
                objIssue.setCatId(rsIssue.getInt(1));
                objIssue.setParentCatId(rsIssue.getInt(2));
                objIssue.setCatCode(rsIssue.getString(3));
                objIssue.setCatDesc(rsIssue.getString(4));
                alIssueList.add(objIssue);
            }
        }catch(CanonCustAppExceptionUtil csExp) {
            throw (csExp);
        }catch(SQLException eSQLExp) {
            throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_IssueList, Method:getIssueList()"));
        }catch (Exception eExp) {
            throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_IssueList, Method:getIssueList()"));
        }
        finally {
            try {
                if(pCall != null) pCall.close();
                if(rsIssue != null) rsIssue.close();
                if(connIssueUtil != null) connIssueUtil.releaseConnection();
            }catch (CanonCustAppExceptionUtil csExp) {
                throw (csExp);
            }catch (SQLException eSQLExp) {
                throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_IssueList, Method:getIssueList()"));
            }catch (Exception eExp) {
                throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_IssueList, Method:getIssueList()"));
            }
        }
        return alIssueList;
    }

/** Added For ITG # 156613 By Chandra Sekhar - Start **/
    /**
     * This method will return the list of Categories.
     * <p>
     * @return java.util.ArrayList.
     * @param iOrgId int.
     * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
     */
    public ArrayList getCategories(String strRegCode, String strIssueType, String strUserProfile, String strAccessProfile) throws CanonCustAppExceptionUtil {
        ArrayList alCategory = new ArrayList();
        //Get Connection
        CanonCustAppDBUtil connCategoryUtil = null;
        Connection connCategory = null;
        CallableStatement pCall = null;
        ResultSet rsCategory = null;
        try {
        	System.out.println("in getCategories strRegCode" + strRegCode + " strIssueType " + strIssueType + 
        			" strUserProfile " + strUserProfile + " strAccessProfile " + strAccessProfile);
            connCategoryUtil = new CanonCustAppDBUtil();
            connCategory = (Connection)(connCategoryUtil.getConnection());
            pCall = connCategory.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_category(?,?,?,?,?)}");
            pCall.setString(1, strRegCode);
            pCall.setString(2, strIssueType);
            pCall.setString(3, strUserProfile);
            pCall.setString(4, strAccessProfile);
            pCall.registerOutParameter(5, OracleTypes.CURSOR);

            pCall.execute();
            
            rsCategory = (ResultSet) pCall.getObject(5);
            
            while(rsCategory.next()) {
                alCategory.add(rsCategory.getString(1));
            }           
        }catch(CanonCustAppExceptionUtil csExp) {
            throw (csExp);
        }catch(SQLException eSQLExp) {
            throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Severity, Method:getCategories()"));
        }catch (Exception eExp) {
            throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Severity, Method:getCategories()"));
        }
        finally {
            try {
                if(pCall != null) pCall.close();
                if(rsCategory != null) rsCategory.close();
                if(connCategoryUtil != null) connCategoryUtil.releaseConnection();
            }catch (CanonCustAppExceptionUtil csExp) {
                throw (csExp);
            }catch (SQLException eSQLExp) {
                throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Severity, Method:getCategories()"));
            }catch (Exception eExp) {
                throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Severity, Method:getCategories()"));
            }
        }
        return alCategory;
    }
/** Added For ITG # 156613 By Chandra Sekhar - End **/
}
