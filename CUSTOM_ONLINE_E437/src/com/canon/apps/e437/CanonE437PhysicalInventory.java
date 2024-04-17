/*
 * CanonE437PhysicalInventory.java
 *
 * Venkat Velagala
 */

package com.canon.apps.e437;

//import oracle.jdbc.driver.*;
import oracle.jdbc.OracleTypes;
import oracle.sql.*;
import oracle.apps.jtf.aom.transaction.TransactionScope;
import java.sql.*;
import java.util.*;
import java.sql.Date;
import java.sql.PreparedStatement; 
import java.text.* ;
import java.math.BigDecimal;
import java.lang.*;
import com.canon.apps.e437.CanonE410AttributeBean.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import business.parts.NWZC206001PMsg;
//import com.canon.cusa.s21.api.NWZ.NWZC206001.NWZC206001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;


public class CanonE437PhysicalInventory 
{
	public CanonE437PhysicalInventory() {
	}
	
	private void closeResources(ResultSet resultSet) {
		if(resultSet != null) {
			try {
				resultSet.close();
			}
			catch(Exception exception) {
				logErrorMessage( "Unable to close result set:  " + exception );
			}
		}
	}

	public void closeResources(Statement statement) {
		if(statement != null)	{
			try {
				statement.close();
			}
			catch(Exception exception) {
				logErrorMessage( "Unable to close statement:  " + exception );
			}
		}
	}
	
	private void logErrorMessage(String message) {
		System.err.println(message);
	}

	private void logMessage(String message) {
		System.out.println(message);
	}

	public ArrayList getTechLOV(String pUserId, String pTechId, String pMode, String pFormMode) throws Exception {
		System.out.println ("E437 Inside getTechLOV");
		System.out.println ("parameters:pUserId["+pUserId+"]pTechId["+pTechId+"]pMode["+pMode+"]pFormMode["+pFormMode+"]");
		CallableStatement cstmt = null;
		ResultSet aresultset = null;
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		java.sql.Connection oracleconnection = null;
		
		//to get all the out parameters
		ArrayList list = new ArrayList();
		
		//to get refcursor out parameter
		ArrayList info = new ArrayList();	
						
		try {
			oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
			
			//Prepare Call:
			cstmt = oracleconnection.prepareCall("{call canon_e437_phyinvcnt_pkg.gettechdtls(?,?,?,?,?,?,?)}");
			
			//Set parameters:
			cstmt.setString(1, pUserId.toUpperCase());
			cstmt.setString(2, pTechId.toUpperCase());
			cstmt.setString(3, pMode.toUpperCase());
			cstmt.setString(4, pFormMode.toUpperCase());
			cstmt.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.registerOutParameter(6, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(7, OracleTypes.VARCHAR);
			
			//Execute Statement
			cstmt.execute();
			
			// x_return_code
			list.add(cstmt.getString(6));
			
			//x_return_message
			list.add(cstmt.getString(7));
			
			logErrorMessage("E437 Inside getTechLOV x_return_code: " +cstmt.getString(6) +" x_return_message: "+cstmt.getString(7));
			
			if (!(cstmt.getString(6).equals("E"))) {
				aresultset = (ResultSet)cstmt.getObject(5);

				while (aresultset.next()) {
					CanonE410AttributeBean rs = new CanonE410AttributeBean();
					rs.setAttribute1(aresultset.getString(1));
					rs.setAttribute2(aresultset.getString(2));
					rs.setAttribute3(aresultset.getString(3));
					rs.setAttribute4(aresultset.getString(4));
					info.add(rs);
				}
				list.add(info);
			}
			
		}
		catch(SQLException sqlExp) {
			logErrorMessage("SQLexception: " + sqlExp );
			throw sqlExp;
		}

		catch(Exception exception) {
			logErrorMessage("exception: " + exception);
		}
		finally {
			closeResources(cstmt);
			try {
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
					if(cstmt != null) cstmt.close();
			}
			catch (Exception eExp) {}
		}
	return list;
	}	

	

	public ArrayList getWhPhyInvData(String pOrgId, String pUserId, String pSubInv, String pFormMode) throws Exception {
		System.out.println ("E437 Inside getWhPhyInvData");
		System.out.println ("parameters:pOrgId["+pOrgId+"]pUserId["+pUserId+"]pSubInv["+pSubInv+"]pFormMode["+pFormMode+"]");
		CallableStatement cstmt = null;
		ResultSet aresultset = null;
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		java.sql.Connection oracleconnection = null;
		
		//to get all the out parameters
		ArrayList list = new ArrayList();
						
		try {
			oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
			
			//Prepare Call:
			cstmt = oracleconnection.prepareCall("{call canon_e437_phyinvcnt_pkg.getwhphyinvdtls(?,?,?,?,?,?,?,?,?,?)}");
			
			//Set parameters:
			cstmt.setString(1, pOrgId.toUpperCase());
			cstmt.setString(2, pUserId.toUpperCase());
			cstmt.setString(3, pSubInv.toUpperCase());
			cstmt.setString(4, pFormMode.toUpperCase());
			cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(6, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(7, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(8, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(9, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(10, OracleTypes.VARCHAR);
			
			//Execute Statement
			cstmt.execute();
			
			// x_return_code
			list.add(cstmt.getString(9));
			
			//x_return_message
			list.add(cstmt.getString(10));
			
			// x_emp_id
			list.add(cstmt.getString(5));

			// x_emp_user
			list.add(cstmt.getString(6));
			
			// x_phy_inv_id
			list.add(cstmt.getString(7));

			// x_phy_inv
			list.add(cstmt.getString(8));

			logErrorMessage("E437 Inside getWhPhyInvData x_return_code: " +cstmt.getString(9) +" x_return_message: "+cstmt.getString(10));
			
		}
		catch(SQLException sqlExp) {
			logErrorMessage("SQLexception: " + sqlExp );
			throw sqlExp;
		}

		catch(Exception exception) {
			logErrorMessage("exception: " + exception);
		}
		finally {
			closeResources(cstmt);
			try {
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
					if(cstmt != null) cstmt.close();
			}
			catch (Exception eExp) {}
		}
	return list;
	}	

	
	
	public ArrayList getTechPhyInvData(String pOrgId, String pUserId, String pTech, String pFormMode) throws Exception {
		System.out.println ("E437 Inside getTechPhyInvData");
		System.out.println ("parameters:pOrgId["+pOrgId+"]pUserId["+pUserId+"]pTech["+pTech+"]pFormMode["+pFormMode+"]");
		CallableStatement cstmt = null;
		ResultSet aresultset = null;
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		java.sql.Connection oracleconnection = null;
		
		//to get all the out parameters
		ArrayList list = new ArrayList();
		
		try {
			oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
			
			//Prepare Call:
			cstmt = oracleconnection.prepareCall("{call canon_e437_phyinvcnt_pkg.gettechphyinvdtls(?,?,?,?,?,?,?,?,?,?)}");
			
			//Set parameters:
			cstmt.setString(1, pOrgId.toUpperCase());
			cstmt.setString(2, pUserId.toUpperCase());
			cstmt.setString(3, pTech.toUpperCase());
			cstmt.setString(4, pFormMode.toUpperCase());
			cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(6, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(7, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(8, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(9, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(10, OracleTypes.VARCHAR);
			
			//Execute Statement
			cstmt.execute();
			
			// x_return_code
			list.add(cstmt.getString(9));
			
			//x_return_message
			list.add(cstmt.getString(10));
			
			// x_emp_id
			list.add(cstmt.getString(5));

			// x_emp_user
			list.add(cstmt.getString(6));
			
			// x_phy_inv_id
			list.add(cstmt.getString(7));

			// x_phy_inv
			list.add(cstmt.getString(8));
			
			logErrorMessage("E437 Inside getTechPhyInvData x_return_code: " +cstmt.getString(9) +" x_return_message: "+cstmt.getString(10));
			
		}
		catch(SQLException sqlExp) {
			logErrorMessage("SQLexception: " + sqlExp );
			throw sqlExp;
		}

		catch(Exception exception) {
			logErrorMessage("exception: " + exception);
		}
		finally {
			closeResources(cstmt);
			try {
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
					if(cstmt != null) cstmt.close();
			}
			catch (Exception eExp) {}
		}
	return list;
	}	

	

	public ArrayList getScannedData(String pOrgId, String pPhyInvId, String pEmpId, String pFormMode) throws Exception {
		System.out.println ("E437 Inside getScannedData");
		System.out.println ("parameters:pOrgId["+pOrgId+"]pPhyInvId["+pPhyInvId+"]pEmpId["+pEmpId+"]pFormMode["+pFormMode+"]");
		CallableStatement cstmt = null;
		ResultSet aresultset = null;
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		java.sql.Connection oracleconnection = null;
		
		//to get all the out parameters
		ArrayList list = new ArrayList();
		
		//to get refcursor out parameter
		ArrayList info = new ArrayList();	
						
		try {
			oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
			
			//Prepare Call:
			cstmt = oracleconnection.prepareCall("{call canon_e437_phyinvcnt_pkg.getscanneddata(?,?,?,?,?,?,?)}");
			
			//Set parameters:
			cstmt.setString(1, pOrgId.toUpperCase());
			cstmt.setString(2, pPhyInvId.toUpperCase());
			cstmt.setString(3, pEmpId.toUpperCase());
			cstmt.setString(4, pFormMode.toUpperCase());
			cstmt.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.registerOutParameter(6, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(7, OracleTypes.VARCHAR);
			
			//Execute Statement
			cstmt.execute();
			
			// x_return_code
			list.add(cstmt.getString(6));
			
			//x_return_message
			list.add(cstmt.getString(7));
			
			logErrorMessage("E437 Inside getScannedData x_return_code: " +cstmt.getString(6) +" x_return_message: "+cstmt.getString(7));
			
			if (!(cstmt.getString(6).equals("E"))) {
				aresultset = (ResultSet)cstmt.getObject(5);

				while (aresultset.next()) {
					CanonE410AttributeBean rs = new CanonE410AttributeBean();
					rs.setAttribute1(aresultset.getString(1));
					rs.setAttribute2(aresultset.getString(2));
					rs.setAttribute3(aresultset.getString(3));
					rs.setAttribute4(aresultset.getString(4));
					rs.setAttribute5(aresultset.getString(5));
					rs.setAttribute6(aresultset.getString(6));
					rs.setAttribute7(aresultset.getString(7));
					rs.setAttribute8(aresultset.getString(8));
					rs.setAttribute9(aresultset.getString(9));
					rs.setAttribute10(aresultset.getString(10));
					rs.setAttribute11(aresultset.getString(11));
					rs.setAttribute12(aresultset.getString(12));
					info.add(rs);
				}
				list.add(info);
			}
			
		}
		catch(SQLException sqlExp) {
			logErrorMessage("SQLexception: " + sqlExp );
			throw sqlExp;
		}

		catch(Exception exception) {
			logErrorMessage("exception: " + exception);
		}
		finally {
			closeResources(cstmt);
			try {
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
					if(cstmt != null) cstmt.close();
			}
			catch (Exception eExp) {}
		}
	return list;
	}	


	
	public ArrayList updateDBManual(String pOrgId, String pPhyInvId, String pTagId, String pTagQty, String pEmpId, String pUserId, String pSubInv, String pLocatorId, String pItemId, String pFormMode) throws Exception {
		System.out.println ("E437 Inside updateDBManual");
		System.out.println ("parameters:pOrgId["+pOrgId+"]pPhyInvId["+pPhyInvId+"]pTagId["+pTagId+"]pTagQty["+pTagQty+"]pEmpId["+pEmpId+"]pUserId["+pUserId+"]pSubInv["+pSubInv+"]pLocatorId["+pLocatorId+"]pItemId["+pItemId+"]pFormMode["+pFormMode+"]");
		CallableStatement cstmt = null;
		ResultSet aresultset = null;
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		java.sql.Connection oracleconnection = null;
		
		//to get all the out parameters
		ArrayList list = new ArrayList();
		
		//logErrorMessage("E437 Inside updateDBManual pTagId"+pTagId);
		//logErrorMessage("E437 Inside updateDBManual pTagQty"+pTagQty);
		//logErrorMessage("E437 Inside updateDBManual pEmpId"+pEmpId);
		//logErrorMessage("E437 Inside updateDBManual pUserId"+pUserId);
		//logErrorMessage("E437 Inside updateDBManual pSubInv"+pSubInv);
		//logErrorMessage("E437 Inside updateDBManual pLocatorId"+pLocatorId);
		//logErrorMessage("E437 Inside updateDBManual pFormMode"+pFormMode);
		
		try {
			oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
			
			//Prepare Call:
			cstmt = oracleconnection.prepareCall("{call canon_e437_phyinvcnt_pkg.updatedbmanual(?,?,?,?,?,?,?,?,?,?,?,?)}");
			
			//Set parameters:
			cstmt.setString(1, pOrgId);
			cstmt.setString(2, pPhyInvId);
			cstmt.setString(3, pTagId);
			cstmt.setString(4, pTagQty.toUpperCase());
			cstmt.setString(5, pEmpId.toUpperCase());
			cstmt.setString(6, pUserId.toUpperCase());
			cstmt.setString(7, pSubInv.toUpperCase());
			cstmt.setString(8, pLocatorId);
			cstmt.setString(9, pItemId);
			cstmt.setString(10, pFormMode.toUpperCase());
			cstmt.registerOutParameter(11, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(12, OracleTypes.VARCHAR);

			
			//Execute Statement
			cstmt.execute();
			
			// x_return_code
			list.add(cstmt.getString(11));
			
			//x_return_message
			list.add(cstmt.getString(12));
						
			logErrorMessage("E437 Inside updateDBManual x_return_code: " +cstmt.getString(11) +" x_return_message: "+cstmt.getString(12));
			
		}
		catch(SQLException sqlExp) {
			logErrorMessage("SQLexception: " + sqlExp );
			throw sqlExp;
		}

		catch(Exception exception) {
			logErrorMessage("exception: " + exception);
		}
		finally {
			closeResources(cstmt);
			try {
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
					if(cstmt != null) cstmt.close();
			}
			catch (Exception eExp) {}
		}
	return list;
	}	


	public ArrayList checkPendingCounts(String pOrgId, String pPhyInvId, String pFormMode) throws Exception {
		System.out.println ("E437 Inside checkPendingCounts");
		System.out.println ("parameters:pOrgId["+pOrgId+"]pPhyInvId["+pPhyInvId+"]pFormMode["+pFormMode+"]");
		CallableStatement cstmt = null;
		ResultSet aresultset = null;
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		java.sql.Connection oracleconnection = null;
		
		//to get all the out parameters
		ArrayList list = new ArrayList();
		
		try {
			oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
			
			//Prepare Call:
			cstmt = oracleconnection.prepareCall("{call canon_e437_phyinvcnt_pkg.checkPendingCounts(?,?,?,?,?,?)}");
			
			//Set parameters:
			cstmt.setString(1, pOrgId);
			cstmt.setString(2, pPhyInvId);
			cstmt.setString(3, pFormMode.toUpperCase());
			cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(6, OracleTypes.VARCHAR);

			
			//Execute Statement
			cstmt.execute();
			
			// x_return_code
			list.add(cstmt.getString(5));
			
			//x_return_message
			list.add(cstmt.getString(6));
			
			if (cstmt.getString(5).equals("S")) {						
				//x_cnt_msg
				list.add(cstmt.getString(4));
			}
			
			logErrorMessage("E437 Inside checkPendingCounts x_return_code: " +cstmt.getString(5) +" x_return_message: "+cstmt.getString(6));
			
		}
		catch(SQLException sqlExp) {
			logErrorMessage("SQLexception: " + sqlExp );
			throw sqlExp;
		}

		catch(Exception exception) {
			logErrorMessage("exception: " + exception);
		}
		finally {
			closeResources(cstmt);
			try {
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
					if(cstmt != null) cstmt.close();
			}
			catch (Exception eExp) {}
		}
	return list;
	}	


	public ArrayList assignZeroCounts(String pOrgId, String pPhyInvId, String pEmpId, String pUserId, String pFormMode) throws Exception {
		System.out.println ("E437 Inside assignZeroCounts");
		System.out.println ("parameters:pOrgId["+pOrgId+"]pPhyInvId["+pPhyInvId+"]pEmpId["+pEmpId+"]pUserId["+pUserId+"]pFormMode["+pFormMode+"]");
		CallableStatement cstmt = null;
		ResultSet aresultset = null;
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		java.sql.Connection oracleconnection = null;
		
		//to get all the out parameters
		ArrayList list = new ArrayList();
		
		try {
			oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
			
			//Prepare Call:
			cstmt = oracleconnection.prepareCall("{call canon_e437_phyinvcnt_pkg.assignZeroCounts(?,?,?,?,?,?,?)}");
			
			//Set parameters:
			cstmt.setString(1, pOrgId);
			cstmt.setString(2, pPhyInvId);
			cstmt.setString(3, pEmpId);
			cstmt.setString(4, pUserId);
			cstmt.setString(5, pFormMode.toUpperCase());
			cstmt.registerOutParameter(6, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(7, OracleTypes.VARCHAR);

			
			//Execute Statement
			cstmt.execute();
			
			// x_return_code
			list.add(cstmt.getString(6));
			
			//x_return_message
			list.add(cstmt.getString(7));
			
			logErrorMessage("E437 Inside assignZeroCounts x_return_code: " +cstmt.getString(6) +" x_return_message: "+cstmt.getString(7));
			
		}
		catch(SQLException sqlExp) {
			logErrorMessage("SQLexception: " + sqlExp );
			throw sqlExp;
		}

		catch(Exception exception) {
			logErrorMessage("exception: " + exception);
		}
		finally {
			closeResources(cstmt);
			try {
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
					if(cstmt != null) cstmt.close();
			}
			catch (Exception eExp) {}
		}
	return list;
	}	


	public ArrayList validateItem(String pOrgId, String pPhyInvId, String pEmpId, String pUserId, String pItemId, String pFormMode) throws Exception {
		System.out.println ("E437 Inside validateItem");
		System.out.println ("parameters:pOrgId["+pOrgId+"]pPhyInvId["+pPhyInvId+"]pUserId["+pUserId+"]pItemId["+pItemId+"]pFormMode["+pFormMode+"]");
		CallableStatement cstmt = null;
		ResultSet aresultset = null;
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		java.sql.Connection oracleconnection = null;
		PreparedStatement preparedStatement = null;
		
		//to get all the out parameters
		ArrayList list = new ArrayList();
		
		//to get refcursor out parameter
		ArrayList info = new ArrayList();	


		//UPC Check
		try {
			oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT AAA.MDSE_CD FROM ( ");
			sb.append(" SELECT '1' SORT_BY, MDSE_CD FROM MDSE M WHERE M.UPC_CD = ? AND M.GLBL_CMPY_CD = 'ADB' AND M.EZCANCELFLAG = '0' AND EXISTS(SELECT '1' FROM ORD_TAKE_MDSE OTM WHERE OTM.EZCANCELFLAG = '0' AND OTM.GLBL_CMPY_CD = 'ADB' AND OTM.MDSE_CD = M.MDSE_CD) ");
			sb.append(" UNION ALL ");
			sb.append(" SELECT '2' SORT_BY, MDSE_CD FROM MDSE M WHERE M.UPC_CD = ? AND M.GLBL_CMPY_CD = 'ADB' AND M.EZCANCELFLAG = '0' AND NOT EXISTS(SELECT '1' FROM ORD_TAKE_MDSE OTM WHERE OTM.EZCANCELFLAG = '0' AND OTM.GLBL_CMPY_CD = 'ADB' AND OTM.MDSE_CD = M.MDSE_CD) ");
			sb.append(" ) AAA ");
			sb.append(" ORDER BY AAA.SORT_BY DESC, AAA.MDSE_CD DESC ");
			preparedStatement = oracleconnection.prepareStatement(sb.toString());
			preparedStatement.setString(1, pItemId);
			preparedStatement.setString(2, pItemId);
			aresultset = preparedStatement.executeQuery();
			while ( aresultset.next() ) {
				pItemId = aresultset.getString(1);
			}
			aresultset.close();
			//stmt.close();
			//oracleconnection.close();
		}
		catch(SQLException sqlExp) {
			logErrorMessage("SQLexception: " + sqlExp );
			throw sqlExp;
		}
		catch(Exception exception) {
			logErrorMessage("exception: " + exception);
		}
		finally {
			closeResources(preparedStatement);
			try {
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
					if(preparedStatement != null) preparedStatement.close();
			}
			catch (Exception eExp) {}
		}
			
//        //NWZC206001 Supersede API
//        ArrayList<String> ret = null;
//        NWZC206001 api = new NWZC206001();
//        NWZC206001PMsg param = new NWZC206001PMsg();
//        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, "ADB");
//        ZYPEZDItemValueSetter.setValue(param.slsDt, ZYPDateUtil.getSalesDate("ADB"));
//        ZYPEZDItemValueSetter.setValue(param.mdseCd, pItemId);
//        ZYPEZDItemValueSetter.setValue(param.xxModeCd, "2");  //Latest Mode
//        ZYPEZDItemValueSetter.setValue(param.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(param.xxAvalPrchFlg, ZYPConstant.FLG_OFF_N);
//
//        api.execute(param, ONBATCH_TYPE.BATCH);
//
//        List<String> errList = S21ApiUtil.getXxMsgIdList(param);
//        if (errList.size() > 0) {
//            for (String xxMsgId : errList) {
//                System.out.println(S21MessageFunc.clspGetMessage(xxMsgId));
//                //if (xxMsgId.endsWith("E")) {
//                //    return null;
//                //}
//            }
//        }
//        //ret = new ArrayList<String>();
//
//        if (ZYPCommonFunc.hasValue(param.A.no(0).mdseCd)) {
//            pItemId = param.A.no(0).mdseCd.getValue();
//        }
        
		cstmt = null;
		aresultset = null;
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		oracleconnection = null;
		
		try {
			oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
			
			//Prepare Call:
			cstmt = oracleconnection.prepareCall("{call canon_e437_phyinvcnt_pkg.validateitem(?,?,?,?,?,?,?,?,?)}");
			
			//Set parameters:
			cstmt.setString(1, pOrgId.toUpperCase());
			cstmt.setString(2, pPhyInvId.toUpperCase());
			cstmt.setString(3, pEmpId.toUpperCase());
			cstmt.setString(4, pUserId.toUpperCase());
			cstmt.setString(5, pItemId.toUpperCase());
			cstmt.setString(6, pFormMode.toUpperCase());
			cstmt.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.registerOutParameter(8, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(9, OracleTypes.VARCHAR);
			
			//Execute Statement
			cstmt.execute();
			
			// x_return_code
			list.add(cstmt.getString(8));
			
			//x_return_message
			list.add(cstmt.getString(9));
			
			logErrorMessage("E437 Inside validateItem x_return_code: " +cstmt.getString(8) +" x_return_message: "+cstmt.getString(9));
			
			if (cstmt.getString(8).equals("M")) {
				aresultset = (ResultSet)cstmt.getObject(7);

				while (aresultset.next()) {
					CanonE410AttributeBean rs = new CanonE410AttributeBean();
					rs.setAttribute1(aresultset.getString(1));
					rs.setAttribute2(aresultset.getString(2));
					rs.setAttribute3(aresultset.getString(3));
					rs.setAttribute4(aresultset.getString(4));
					rs.setAttribute5(aresultset.getString(5));
					rs.setAttribute6(aresultset.getString(6));
					rs.setAttribute7(aresultset.getString(7));
					rs.setAttribute8(aresultset.getString(8));
					rs.setAttribute9(aresultset.getString(9));
					rs.setAttribute10(aresultset.getString(10));
					rs.setAttribute11(aresultset.getString(11));
					rs.setAttribute12(aresultset.getString(12));
					info.add(rs);
				}
				list.add(info);
			}
			
		}
		catch(SQLException sqlExp) {
			logErrorMessage("SQLexception: " + sqlExp );
			throw sqlExp;
		}

		catch(Exception exception) {
			logErrorMessage("exception: " + exception);
		}
		finally {
			closeResources(cstmt);
			try {
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
					if(cstmt != null) cstmt.close();
			}
			catch (Exception eExp) {}
		}
	return list;
	}	

	public ArrayList getScannedDataByPage(String pOrgId, String pPhyInvId, String pEmpId, String pPageNum, String pBeginItem, String pEndItem, String pFormMode) throws Exception {
		System.out.println ("E437 Inside getScannedDataByPage");
		System.out.println ("parameters:pOrgId["+pOrgId+"]pPhyInvId["+pPhyInvId+"]pEmpId["+pEmpId+"]pPageNum["+pPageNum+"]pBeginItem["+pBeginItem+"]pEndItem["+pEndItem+"]pFormMode["+pFormMode+"]");
		CallableStatement cstmt = null;
		ResultSet aresultset = null;
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		java.sql.Connection oracleconnection = null;
		
		//to get all the out parameters
		ArrayList list = new ArrayList();
		
		//to get refcursor out parameter
		ArrayList info = new ArrayList();	
						
		try {
			oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
			
			//Prepare Call:
			cstmt = oracleconnection.prepareCall("{call canon_e437_phyinvcnt_pkg.getScannedDataByPage(?,?,?,?,?,?,?,?,?,?)}");
			
			//Set parameters:
			cstmt.setString(1, pOrgId.toUpperCase());
			cstmt.setString(2, pPhyInvId.toUpperCase());
			cstmt.setString(3, pEmpId.toUpperCase());
			cstmt.setString(4, pPageNum.toUpperCase());
			cstmt.setString(5, pBeginItem.toUpperCase());
			cstmt.setString(6, pEndItem.toUpperCase());
			cstmt.setString(7, pFormMode.toUpperCase());
			cstmt.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.registerOutParameter(9, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(10, OracleTypes.VARCHAR);
			
			//Execute Statement
			cstmt.execute();
			
			// x_return_code
			list.add(cstmt.getString(9));
			
			//x_return_message
			list.add(cstmt.getString(10));
			
			logErrorMessage("E437 Inside getScannedDataByPage x_return_code: " +cstmt.getString(9) +" x_return_message: "+cstmt.getString(10));
			
			if (!(cstmt.getString(9).equals("E"))) {
				aresultset = (ResultSet)cstmt.getObject(8);

				while (aresultset.next()) {
					CanonE410AttributeBean rs = new CanonE410AttributeBean();
					rs.setAttribute1(aresultset.getString(1));
					rs.setAttribute2(aresultset.getString(2));
					rs.setAttribute3(aresultset.getString(3));
					rs.setAttribute4(aresultset.getString(4));
					rs.setAttribute5(aresultset.getString(5));
					rs.setAttribute6(aresultset.getString(6));
					rs.setAttribute7(aresultset.getString(7));
					rs.setAttribute8(aresultset.getString(8));
					rs.setAttribute9(aresultset.getString(9));
					rs.setAttribute10(aresultset.getString(10));
					rs.setAttribute11(aresultset.getString(11));
					rs.setAttribute12(aresultset.getString(12));
					info.add(rs);
				}
				list.add(info);
			}
			
		}
		catch(SQLException sqlExp) {
			logErrorMessage("SQLexception: " + sqlExp );
			throw sqlExp;
		}

		catch(Exception exception) {
			logErrorMessage("exception: " + exception);
		}
		finally {
			closeResources(cstmt);
			try {
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
					if(cstmt != null) cstmt.close();
			}
			catch (Exception eExp) {}
		}
	return list;
	}	
	
	//ITG 301386, Added New Function For Printing Physical Inventory Report
	public ArrayList printReport(String pOrgId, String pPhyInvId, String pUserId, String pFormMode) throws Exception {
		System.out.println ("E437 Inside printReport");
		System.out.println ("parameters:pOrgId["+pOrgId+"]pPhyInvId["+pPhyInvId+"]pUserId["+pUserId+"]pFormMode["+pFormMode+"]");
		CallableStatement cstmt = null;
		ResultSet aresultset = null;
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		java.sql.Connection oracleconnection = null;
		
		//to get all the out parameters
		ArrayList list = new ArrayList();
		
		try {
			oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
			
			//Prepare Call:
			cstmt = oracleconnection.prepareCall("{call canon_e437_phyinvcnt_pkg.printReport(?,?,?,?,?,?)}");
			
			//Set parameters:
			cstmt.setString(1, pOrgId);
			cstmt.setString(2, pPhyInvId);
			cstmt.setString(3, pUserId);
			cstmt.setString(4, pFormMode.toUpperCase());
			cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(6, OracleTypes.VARCHAR);

			
			//Execute Statement
			cstmt.execute();
			
			// x_return_code
			list.add(cstmt.getString(5));
			
			//x_return_message
			list.add(cstmt.getString(6));
			
			logErrorMessage("E437 Inside assignZeroCounts x_return_code: " +cstmt.getString(5) +" x_return_message: "+cstmt.getString(6));
			
		}
		catch(SQLException sqlExp) {
			logErrorMessage("SQLexception: " + sqlExp );
			throw sqlExp;
		}

		catch(Exception exception) {
			logErrorMessage("exception: " + exception);
		}
		finally {
			closeResources(cstmt);
			try {
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
					if(cstmt != null) cstmt.close();
			}
			catch (Exception eExp) {}
		}
	return list;
	}	
	
	
}