package com.canon.oracle.custapp.custinq.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_MainMenuObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_SubMenuObj;
import com.canon.oracle.custapp.util.CanonCustAppDBUtil;
import com.canon.oracle.custapp.util.CanonCustAppExceptionUtil;

/**
 * Canon_E193_RenderMenu Retrieves Main Menu and Sub Menus from database
 * Creation date: (7/27/2005 1:20:15 PM)
 * @author:
 */
public class Canon_E193_RenderMenu
{
	/**
	 * Canon_E193_RenderMenu constructor comment.
	 */
	public Canon_E193_RenderMenu() {}

	/**
	 * Get menus from database.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @param iRespId int
	 * @param iApplId int
	 * @return java.util.ArrayList
	 */
	public ArrayList getMenuInfo(String iRespId, int iApplId) throws CanonCustAppExceptionUtil {

		ArrayList alMainMenu = new ArrayList();

		CallableStatement cstmtMainMenu = null;
		ResultSet rsMainMenu = null;

		CallableStatement cstmtSubMenu = null;
		ResultSet rsSubMenu = null;

		//Get Connection
		CanonCustAppDBUtil connMenu = null;
		Connection connMenuConnection = null;

    	try {
      		connMenu = new CanonCustAppDBUtil(); 
			connMenuConnection = (Connection)(connMenu.getConnection());
			System.out.println("in getMenuInfo iRespId : " + iRespId + "iApplId : " + iApplId );

			S21UserProfileService upService = S21UserProfileServiceFactory.getInstance().getService();
			
			List<String> strList = new ArrayList<String>();
			/*System.out.println("iRespId : " + iRespId + " EXTNE193T030 " +upService.isFunctionGranted(iRespId, "EXTNE193T030"));
			System.out.println("iRespId : " + iRespId + " EXTNE193T040 " +upService.isFunctionGranted(iRespId, "EXTNE193T040"));
			System.out.println("iRespId : " + iRespId + " EXTNE193T050 " +upService.isFunctionGranted(iRespId, "EXTNE193T050"));*/
			
			StringBuilder functionsBuilder = new StringBuilder("");
			
			if (upService.isFunctionGranted(iRespId, "EXTNE193T030")) 
			{
				strList.add("EXTNE193T030");
			}
			if(upService.isFunctionGranted(iRespId, "EXTNE193T040"))
			{
				strList.add("EXTNE193T040");
			}
			if(upService.isFunctionGranted(iRespId, "EXTNE193T050"))
			{
				strList.add("EXTNE193T050");
			}
			
			//strList.add("EXTNE193T030"); //TODO Need to remove once the role set up has been done
					
			//System.out.println("List of functions available to that user : ");
			for(String str : strList)
			{
				functionsBuilder.append("'");
				functionsBuilder.append(str);
				functionsBuilder.append("'");
				functionsBuilder.append(",");
				//System.out.println(str);
			}
			
			String functionsStr =  functionsBuilder.toString();
			if(functionsStr != null && !"".equals(functionsStr.trim()))
			{
				functionsStr = functionsStr.substring(0, functionsStr.length() -1);
			}
			
			System.out.println("in getMenuInfo functionsStr " + functionsStr);
			
			cstmtMainMenu = connMenuConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_main_menu(?,?,?)}");
			cstmtMainMenu.setString(1, functionsStr);
			cstmtMainMenu.setInt(2, iApplId); //Note : We are not using in the Oracle function
			cstmtMainMenu.registerOutParameter(3, OracleTypes.CURSOR);

			cstmtMainMenu.execute();			
			rsMainMenu = (ResultSet) cstmtMainMenu.getObject(3);
      	//	rsMainMenu = ((OracleCallableStatement) cstmtMainMenu).getCursor(3);
			
			while(rsMainMenu.next()) {
				Canon_E193_MainMenuObj objMainMenu = new Canon_E193_MainMenuObj();
				objMainMenu.setPromptName(rsMainMenu.getString(1));
				String strMainMenuUrl = rsMainMenu.getString(2);
			//	System.out.println("strMainMenuUrl is " + strMainMenuUrl);
        		int i = strMainMenuUrl.indexOf("=");
        		if(i > 0) {
          			strMainMenuUrl = "canon_E193_cs" + strMainMenuUrl.substring(i+1,strMainMenuUrl.length()) + ".jsp";
          			//System.out.println("strMainMenuUrl in if is  " + strMainMenuUrl);
        		}
		        objMainMenu.setUrlName(strMainMenuUrl);
        		objMainMenu.setSubMenuID(rsMainMenu.getString(3));

				if(rsMainMenu.getString(3) !="") {
          			cstmtSubMenu = connMenuConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_sub_menu(?,?)}");
					cstmtSubMenu.setInt(1, rsMainMenu.getInt(3));
					cstmtSubMenu.registerOutParameter(2, OracleTypes.CURSOR);

					cstmtSubMenu.execute();
      			//	rsSubMenu = ((OracleCallableStatement)cstmtSubMenu).getCursor(2);
					rsSubMenu = (ResultSet) cstmtSubMenu.getObject(2);
          			ArrayList alSubMenu = new ArrayList();

					while(rsSubMenu.next()) {
						Canon_E193_SubMenuObj objSubMenu = new Canon_E193_SubMenuObj();
						objSubMenu.setPromptName(rsSubMenu.getString(1));						
						String strSubMenuUrl = rsSubMenu.getString(2);						
            			int j = strSubMenuUrl.indexOf("=");
            			if (j > 0) {
              				strSubMenuUrl = "canon_E193_cs" + strSubMenuUrl.substring(j+1,strSubMenuUrl.length()) + ".jsp";              				
            			}
            			objSubMenu.setUrlName(strSubMenuUrl);
            			alSubMenu.add(objSubMenu);
          			}
          			objMainMenu.setAlSubMenu(alSubMenu);
          			cstmtSubMenu.close();
          			rsSubMenu.close();
        		}
        		alMainMenu.add(objMainMenu);
      		}
			cstmtMainMenu.close();
			rsMainMenu.close();
			connMenuConnection.close();
			connMenu.releaseConnection();
		}
		catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		}
		catch (SQLException eSQLExp) {
		  	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_RenderMenu, Method:getMenuInfo(int iRespId, int iApplId)"));
		}
		catch (Exception eExp) {
		  	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_RenderMenu, Method:getMenuInfo(int iRespId, int iApplId)"));
		}
    	finally {
      		try {
				if(cstmtSubMenu != null) {
				  	cstmtSubMenu.close();
				}
				if(rsSubMenu != null) {
				  	rsSubMenu.close();
				}
				if(cstmtMainMenu != null) {
				  	cstmtMainMenu.close();
				}
				if(rsMainMenu != null) {
				  	rsMainMenu.close();
				}
				if(connMenuConnection != null) {
				  	connMenuConnection.close();
				}
				if(connMenu != null) {
				  	connMenu.releaseConnection();
        		}
      		}
      		catch (CanonCustAppExceptionUtil csExp) {
        		throw (csExp);
      		}
      		catch (SQLException eSQLExp) {
        		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_RenderMenu, Method:getMenuInfo(int iRespId, int iApplId)"));
      		}
      		catch (Exception eExp) {
        		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_RenderMenu, Method:getMenuInfo(int iRespId, int iApplId)"));
      		}
    	}
    	return alMainMenu;
  	}
}