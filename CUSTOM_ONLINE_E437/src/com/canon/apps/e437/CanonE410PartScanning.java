/*
 * CanonE410PartScanning.java
 *
 * Munazzam Shaik
 */

package com.canon.apps.e437;

import oracle.jdbc.driver.*;
import oracle.jdbc.OracleTypes;
import oracle.sql.*;
import oracle.apps.jtf.aom.transaction.TransactionScope;
import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement; 
import java.text.* ;
import java.math.BigDecimal;
import java.lang.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canon.apps.e437.CanonE410AttributeBean.*;
import com.canon.cusa.s21.framework.security.S21Authentication;
import com.canon.cusa.s21.framework.security.context.S21SecurityContext;
import com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class CanonE410PartScanning {
	private String User;
	public CanonE410PartScanning() {
	}
     	 
	 public static class tabDat
	{
		public String PickList;
		public String Att1;
		public String Att2;
		public String Att3;
		public int Att4;

		public tabDat()
		{
		PickList ="";
		Att1 = "";
		Att2 = "";
		Att3 = ""; 
		Att4 = -1; 

		}

	}
	
	private String userName;
	private int userId;
	private int respId;
	private int loginId;
	private String loc;
	private String subInv;
	private String showData;
	
	//session UserName
	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}
	
	//session UserId
	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}
	
	//session reponsibility Id
	public void setRespId(int respId){
		this.respId = respId;
	}

	public int getRespId(){
		return respId;
	}
	
	//session login Id
	public void setLoginId(int loginId){
		this.loginId = loginId;
	}

	public int getLoginId(){
		return loginId;
	}
	
	//session user security info from value set E410 USER SUBINV MAPPING attrib1
	public void setUserLoc(String loc){
		this.loc = loc;
	}

	public String getUserLoc(){
		return loc;
	}
	
	//session user security info from value set E410 USER SUBINV MAPPING attrib2
	public void setSubInv(String subInv){
		this.subInv = subInv;
	}

	public String getSubInv(){
		return subInv;
	}
	
	//session user security info from value set E410 USER SUBINV MAPPING attrib3
	public void setShowData(String showData){
		this.showData = showData;
	}

	public String getShowData(){
		return showData;
	}

	//public void setUser(String User){
	//	this.User = User;
	//}

	//public String getUser(){
	//	return User;
	//}	

	
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
	
//	public boolean validUsrVs(String invCode,String picList) throws Exception
//	{
//	StringBuffer qry = new StringBuffer(20);
//	PreparedStatement statement = null;
//	ResultSet resultSet = null;
//	Connection conn = null;
//	ArrayList list = new ArrayList();
//	int d=0;
//	boolean x=false;
//	conn = TransactionScope.getConnection();
//    //TODO
//	//logErrorMessage( "Inside validUsrVs");
//	qry.append(" SELECT count(*) \n");
//		qry.append("   FROM mtl_material_transactions_temp mmtt,csp_picklist_lines cpll,csp_picklist_headers cplh \n");
//		qry.append(" 		,mtl_item_locations mil ,mtl_system_items msi\n");
//		qry.append("  WHERE 1=1 AND mmtt.transaction_temp_id = cpll.transaction_temp_id \n");
//		qry.append("    AND cpll.picklist_header_id = cplh.picklist_header_id \n");
//		qry.append("    AND mmtt.organization_id = cplh.organization_id \n");
//		qry.append("    AND mmtt.locator_id = mil.inventory_location_id \n");
//		qry.append("    AND mmtt.inventory_item_id = msi.inventory_item_id \n");
//		qry.append("    AND mmtt.organization_id = msi.organization_id \n");
//		qry.append("    AND mmtt.attribute15 IS NULL \n");
//		qry.append("    AND cplh.picklist_status <> 3 \n");
//		qry.append("    AND cplh.picklist_number = ? \n");
//		qry.append("    AND upper(mmtt.subinventory_code) = ? \n");
//	
//	try {
//			if(conn != null) 
//			{
//				statement = conn.prepareStatement(qry.toString());
//				statement.setString(1,picList.toString());
//				statement.setString(2,invCode.toUpperCase());
//				//logMessage("qry @@@ \n"+qry.toString());
//				if(statement != null) 
//				{
//				 //logMessage("@@@ before execute");
//				 resultSet = statement.executeQuery();
//				// logMessage("@@@ after execute");	
//				 while(resultSet.next()) 
//				 {
//				 CanonE410AttributeBean rs = new CanonE410AttributeBean();
//				 d = resultSet.getInt(1);
//				 //logMessage("@@@ d -> "+d);
//				 }
//				 
//				 if (d>0)
//				 {
//				 x=true;
//				 } else {x=false;}
//				 
//				}
//				else 
//				{
//					logErrorMessage("dbStatus: DBStatus.UNABLE_TO_CREATE_STATEMENT");
//				}
//			}
//			else {
//				logErrorMessage("dbStatus: UNABLE_TO_GET_CONNECTION");
//			}
//		}
//		catch(SQLException sqlExp) {
//			logErrorMessage("SQLexception: " + sqlExp );
//			throw sqlExp;
//		}
//		catch(Exception exception) {
//			logErrorMessage("exception: " + exception);
//		}
//		finally {
//			closeResources(resultSet);
//			closeResources(statement);
//			try {
//				if(conn != null)
//					TransactionScope.releaseConnection(conn);
//			}
//			catch (Exception eExp) {
//			}
//		}
//		return x;
//	
//
//	}
	
	public String[] upd(String pickListNum,int userId,String userName,String Organization,String pickpack,String userOrgId,String userSubInv) throws Exception 
	{
        System.out.println ("Inside Upd *** Picklist# "+pickListNum+"***userSubInv: "+userSubInv+"***userOrgId: "+userOrgId);
		CallableStatement cstmtUpd = null;
		CallableStatement cstmtPackList = null;
		CallableStatement cstmtAssignDel = null;
		ResultSet rsInv = null;
		ArrayList alInvoices = new ArrayList();
		//CanonE410AttributeBean newObj = new CanonE410AttributeBean();
		String uName = userName;
		//String[] strOrg = getUserLoc(uName);
		//Get Connection
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		java.sql.Connection oracleconnection = null;
		String errorStatus = null;
		String errorCode = null;
		
		String delErrStatus = null;
		String delErrCode = null;
		
		String errStatus=null;
		String errCode = null;
		
		String[] status = new String[2];
		String[] stat = new String[2];
		String[] delstat = new String[2];
		String strPickPack = "N";
		String succMsg = "";
		try 
		{
			oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
			cstmtUpd = oracleconnection.prepareCall("{call canon_e410_mwa_pick_confirm.Pick_confirm(?,?,?,?,?,?)}");
			cstmtUpd.setString(1, pickListNum);
			cstmtUpd.setInt(2, Integer.parseInt(pickListNum));			
			cstmtUpd.setInt(3, Integer.parseInt(Organization));
			cstmtUpd.setInt(4, userId);
			cstmtUpd.registerOutParameter(5, OracleTypes.VARCHAR);
			cstmtUpd.registerOutParameter(6, OracleTypes.VARCHAR);

			cstmtUpd.execute();

			status[0] = cstmtUpd.getString(5);
			//System.out.println ("@@@ "+errorStatus);
			status[1] = cstmtUpd.getString(6);
			
			errorStatus=status[0];
						
			//System.out.println ("@@@ Status "+status[0]+status[1]);
			System.out.println ("@@@ errorStatus "+errorStatus);
				
				if ("S".equals(errorStatus))
				{
				//logErrorMessage("Inside errorstatus for packlist "  );
				cstmtPackList = oracleconnection.prepareCall("{call canon_e410_pick_pack_pkg.main(?,?,?,?)}");
				cstmtPackList.setString(1, pickListNum);
				cstmtPackList.setInt(2, Integer.parseInt(Organization));
				cstmtPackList.registerOutParameter(3, OracleTypes.VARCHAR);
				cstmtPackList.registerOutParameter(4, OracleTypes.VARCHAR);
				
				cstmtPackList.execute();
				 stat[0] = cstmtPackList.getString(3);
			     //System.out.println ("@@@ packlist "+stat[0]);
			     stat[1] = cstmtPackList.getString(4);
				 //System.out.println ("@@@ packlist "+stat[1]);
				  if ("S".equals(stat[0]))
				    {
					succMsg = "Pick/pack successful ";
					stat[1] = succMsg;
					}
				}
				//--    Itg# 453052
				strPickPack = pickpack;
				//select from Menu pick confirm and assign del
				if ("Y".equals(strPickPack))
				{
				 //If Pack list creation success
				  if ("S".equals(stat[0]))
				    {
					 cstmtAssignDel = oracleconnection.prepareCall("{call canon_e410_ship_process_pkg.auto_assign_pick_loc_del(?,?,?,?)}");
					 cstmtAssignDel.setString(1, pickListNum);
					 cstmtAssignDel.setString(2, userName);
					 cstmtAssignDel.registerOutParameter(3, OracleTypes.VARCHAR);
					 cstmtAssignDel.registerOutParameter(4, OracleTypes.VARCHAR);
					
					 cstmtAssignDel.execute();
					 delstat[0] = cstmtAssignDel.getString(3);
					 // System.out.println ("@@@ Del Status "+delstat[0]);
					 delstat[1] = cstmtAssignDel.getString(4);
					 // System.out.println ("@@@ Del msg "+delstat[1]);
					
					if ("S".equals(delstat[0]))
				    {
					succMsg = succMsg+" and assigned to delivery";
					stat[0] = delstat[0];
					stat[1] = succMsg;
					// System.out.println ("@@@ stat msg "+stat[0]);
					// System.out.println ("@@@ stat msg "+stat[1]);
					} else {
							succMsg = succMsg+" "+delstat[1];														
							stat[0] = delstat[0];
							stat[1] = succMsg;
							// System.out.println ("@@@ stat2 msg "+stat[0]);
							// System.out.println ("@@@ stat2 msg "+stat[1]);
							}
					}
						
				
				} //pickpack="Y"
		}
		 catch(SQLException sqlExp) 
			{
			logErrorMessage("SQLexception: " + sqlExp );
			throw sqlExp;
		    }
		
		catch(Exception exception) 
			{
			logErrorMessage("exception: " + exception);
			}
		
		finally {
			
			closeResources(cstmtUpd);
			try 
			{
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
					if(cstmtUpd != null) cstmtUpd.close();
			}
			catch (Exception eExp) {}
		}
	return stat;
	}
	
	public String getAliasItem(String item,String orgId) throws Exception 
	{
       // System.out.println ("Inside getAliasItem "+item+" orgId "+orgId);
		CallableStatement cstmt = null;
		String aliasItem = "";	
		
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		java.sql.Connection oracleconnection = null;
		
		try 
		{
			oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
			cstmt = oracleconnection.prepareCall("{? = call canon_e410_util_pkg.derive_item(?,?)}");
			cstmt.registerOutParameter(1, OracleTypes.VARCHAR);
			cstmt.setString(2, item);
			cstmt.setInt(3, Integer.parseInt(orgId));			

			cstmt.execute();

			aliasItem = cstmt.getString(1);
			//System.out.println ("@@@ aliasItem "+aliasItem);

		}
		 catch(SQLException sqlExp) 
			{
			logErrorMessage("SQLexception: " + sqlExp );
			throw sqlExp;
		    }
		
		catch(Exception exception) 
			{
			logErrorMessage("exception: " + exception);
			}
		
		finally {
			
			closeResources(cstmt);
			try 
			{
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
					if(cstmt != null) cstmt.close();
			}
			catch (Exception eExp) {}
		}
	return aliasItem;
	}
	
	public String[] getUserLoc(String userName) throws Exception
	{
	StringBuffer qry = new StringBuffer(20);
	Statement statement = null;
	ResultSet resultSet = null;
	//oracle.jdbc.driver.OracleConnection oracleconnection = null;
	java.sql.Connection oracleconnection = null;
	String c[] = new String[8];
	oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
	
	//TODO
	qry.append(" SELECT ood.organization_id \n");
	qry.append("       ,ffv.attribute2 \n");
	qry.append("       ,ffv.flex_value \n");
	qry.append("       ,ffv.attribute3 \n");
	qry.append("       ,ffv.attribute1 \n");
	qry.append("       ,ffv.attribute4 \n");
	qry.append("       ,ffv.attribute5 \n");
	qry.append("       ,ffv.attribute6  \n");
	qry.append(" FROM   fnd_flex_value_sets ffvs \n");
	qry.append("       ,fnd_flex_values ffv \n");
	qry.append("       ,mtl_parameters ood \n");
	qry.append(" WHERE  ffvs.flex_value_set_name = 'CANON_E410_USER_SUBINV_MAPPING' \n");
	qry.append(" AND    ffvs.flex_value_set_id = ffv.flex_value_set_id \n");
	qry.append(" AND    ood.organization_code(+) = UPPER (ffv.attribute1) \n");
	qry.append(" AND    ffv.enabled_flag = 'Y' \n");

	
	if(userName != null && !("".equals(userName))) 
		{
			qry.append(" AND    UPPER (ffv.flex_value) = UPPER ('");
			qry.append((userName).toString());
			qry.append("')\n");
		}
	
		
		try {
			if(oracleconnection != null) 
			{
				statement = oracleconnection.createStatement();
				if(statement != null) 
				{
				 
				 resultSet = statement.executeQuery(qry.toString());
				 
				while(resultSet.next()) {
				 CanonE410AttributeBean rs = new CanonE410AttributeBean();
				 c[0]= resultSet.getString(1);
				 c[1]= resultSet.getString(2);
				 c[2]= resultSet.getString(3);
				 c[3]= resultSet.getString(4);
				 c[4]= resultSet.getString(5);
				 c[5]= resultSet.getString(6);
				 c[6]= resultSet.getString(7);
				 c[7]= resultSet.getString(8);
				 }
				}
				else 
				{
					logErrorMessage("dbStatus: DBStatus.UNABLE_TO_CREATE_STATEMENT");
				}
			}
			else {
				logErrorMessage("dbStatus: UNABLE_TO_GET_CONNECTION");
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
			closeResources(resultSet);
			closeResources(statement);
			try {
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
			}
			catch (Exception eExp) {
			}
		}
		return c;
	}

//	public ArrayList getpicklistResults(String picklistnum,String userName,String OrgId,String SubInv) throws Exception {
//		StringBuffer qry = new StringBuffer(20);
//		Statement statement = null;
//		ResultSet resultSet = null;
//		oracle.jdbc.driver.OracleConnection oracleconnection = null;
//		ArrayList list = new ArrayList();
//		boolean x=true;
//		//logMessage ("Inside Search *** Picklist# "+picklistnum);
//		//CanonE410AttributeBean newObj = new CanonE410AttributeBean();
//		//String userName = getUserName();
//		//System.out.println("GETPICKLIST RESULTS "+userName);
//		String[] userOrg = new String[3];
//		//TODO
//		oracleconnection = (OracleConnection)TransactionScope.getConnection();
//		qry.append(" SELECT cplh.picklist_number, msi.segment1 item,SUBSTR (msi.description, 1, 20) itemdescription \n");
//		qry.append("   		,mmtt.transaction_quantity,mil.segment1 LOCATOR, '0' picked, cplh.organization_id \n");	
//		qry.append("   FROM mtl_material_transactions_temp mmtt,csp_picklist_lines cpll,csp_picklist_headers cplh \n");
//		qry.append(" 		,mtl_item_locations mil ,mtl_system_items msi\n");
//		qry.append("  WHERE 1=1 AND mmtt.transaction_temp_id = cpll.transaction_temp_id \n");
//		qry.append("    AND cpll.picklist_header_id = cplh.picklist_header_id \n");
//		qry.append("    AND mmtt.organization_id = cplh.organization_id \n");
//		qry.append("    AND mmtt.locator_id = mil.inventory_location_id(+) \n");
//		qry.append("    AND mmtt.inventory_item_id = msi.inventory_item_id \n");
//		qry.append("    AND mmtt.organization_id = msi.organization_id \n");
//		qry.append("    AND mmtt.attribute15 IS NULL \n");
//		qry.append("    AND cplh.picklist_status <> 3 \n");
//				
//		
//		//logMessage("@@@ picklistnum "+picklistnum);
//		//System.out.println("User getUser-> "+getUser());
//		if(picklistnum != null && !("".equals(picklistnum))) 
//		{
//			qry.append("    AND cplh.picklist_number = '");
//			//qry.append(Integer.parseInt(picklistnum));
//			qry.append((picklistnum).toString());
//			qry.append("'\n");
//		}
//		
//		if (OrgId!=null && !("".equals(OrgId)))
//				//&& ((userOrg[1]==null) || ("".equals(userOrg[1])))
//			{
//			qry.append("    AND cplh.organization_id = ");
//			qry.append( OrgId);
//			qry.append("\n");
//			}
//			
//		qry.append("ORDER BY mil.segment1 \n");
//		
//				
//		if (SubInv!=null && !("".equals(SubInv)))
//			{	
//				if (!validUsrVs(SubInv,picklistnum))
//				{x=false;}
//			}
//			
//		// logMessage("@@@ Inside getResults "+qry.toString());
//		
//		try 
//		{
//		 if (x)
//		 {
//			if(oracleconnection != null) 
//			{
//				statement = oracleconnection.createStatement();
//				if(statement != null) 
//				{	resultSet = statement.executeQuery(qry.toString());
//					while(resultSet.next()) 
//					{	CanonE410AttributeBean rs = new CanonE410AttributeBean();
//						rs.setAttribute1(resultSet.getString(1));
//						rs.setAttribute2(resultSet.getString(2));
//						rs.setAttribute3(resultSet.getString(3));
//						rs.setAttribute4(resultSet.getString(4));
//						rs.setAttribute5(resultSet.getString(5));
//						rs.setAttribute6(resultSet.getString(6));
//						rs.setAttribute7(resultSet.getString(7));
//										
//						list.add(rs);	
//					}//while ends
//					
//					
//				}
//				else {logErrorMessage("dbStatus: DBStatus.UNABLE_TO_CREATE_STATEMENT");}
//			}
//			else {logErrorMessage("dbStatus: UNABLE_TO_GET_CONNECTION");}
//		 }
//		}
//		catch(SQLException sqlExp) 
//		{
//			logErrorMessage("SQLexception: " + sqlExp );
//			throw sqlExp;
//		}
//		catch(Exception exception) 
//		{
//			logErrorMessage("exception: " + exception);
//		}
//		finally 
//		{
//			closeResources(resultSet);
//			closeResources(statement);
//			try 
//			{
//				if(oracleconnection != null)
//					TransactionScope.releaseConnection(oracleconnection);
//			}
//			catch (Exception eExp) {
//			}
//		}
//		return list;
//	}
		
	public ArrayList validatePT(String pickNum,String user,String oraSession,String webSession) throws Exception
	{
		//System.out.println ("Inside validTech *** ");
		CallableStatement cstmt = null;
		ResultSet aresultset = null;
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		java.sql.Connection oracleconnection = null;
		
		//to get all the out parameters
		ArrayList list = new ArrayList();
		
		//to get refcursor out parameter
		ArrayList info = new ArrayList();	
		
		String[] errInfo = new String[2];	
				
		try
		{
			oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
			//Prepare Call:
			cstmt = oracleconnection.prepareCall("{call canon_e410_del_verify_pkg.validate_pt(?,?,?,?,?,?,?,?,?)}");
			//Set parameters:
			cstmt.setString(1, pickNum.toUpperCase());
			cstmt.setString(2, user.toUpperCase());
			cstmt.setString(3, oraSession);
			cstmt.setString(4, webSession);
			cstmt.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.registerOutParameter(6, OracleTypes.INTEGER);
			cstmt.registerOutParameter(7, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(8, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(9, OracleTypes.VARCHAR);
			
			//Execute Statement
			cstmt.execute();
			
			// x_return_status
			list.add(cstmt.getString(8));
			//x_error_message
			list.add(cstmt.getString(9));
			// Total Pick Lists
			list.add(cstmt.getString(6));
			//Last Picklist
			list.add(cstmt.getString(7));
			
			//x_return_status
			errInfo[0] = cstmt.getString(8);
			//x_error_message
			errInfo[1] = cstmt.getString(9);
			
			//logErrorMessage("POX errInfo0: " +errInfo[0] +" errInfo1: "+errInfo[1]);
			
			if (!(errInfo[0].equals("E")))
			{
				aresultset = (ResultSet)cstmt.getObject(5);

				while (aresultset.next())
				{
              	CanonE410AttributeBean rs = new CanonE410AttributeBean();
				rs.setAttribute1(aresultset.getString(1));
				rs.setAttribute2(aresultset.getString(2));
				rs.setAttribute3(aresultset.getString(3));
				rs.setAttribute4(aresultset.getString(4));
				rs.setAttribute5(aresultset.getString(5));
				rs.setAttribute6(aresultset.getString(6));
				rs.setAttribute7(aresultset.getString(7));
				info.add(rs);
				
			/*	logErrorMessage("POX refcursor: " +aresultset.getString(1) 
								+" errInfo1: "+aresultset.getString(2) 
								+" errInfo1: "+aresultset.getString(3) 
								+" errInfo1: "+aresultset.getString(4) 
								+" errInfo1: "+aresultset.getString(5) 
								+" errInfo1: "+aresultset.getString(6) 
								+" errInfo1: "+aresultset.getString(7) 
								+" errInfo1: "+aresultset.getString(8) 
								+" errInfo1: "+aresultset.getString(9) 			
								+" errInfo1: "+aresultset.getString(10) 		
								+" errInfo1: "+aresultset.getString(11) 		
									);*/
				}
				//System.out.println(info.size());
				list.add(info);
			}
		}
		 catch(SQLException sqlExp)
			{
			logErrorMessage("SQLexception: " + sqlExp );
			throw sqlExp;
		    }

		catch(Exception exception)
			{
			logErrorMessage("exception: " + exception);
			}

		finally {

			closeResources(cstmt);
			try
			{
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
					if(cstmt != null) cstmt.close();
			}
			catch (Exception eExp) {}
		}

	return list;
	}
	
	public String[] printMoveOrderReport(String strMONum,String strPrinterName,String strUserName,String strTrackNum) throws Exception
	{
		//System.out.println ("Inside Print Manifest Report");
		CallableStatement cstmtPrint = null;
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		java.sql.Connection oracleconnection = null;
		String[] UpdateInfo = new String[2];
		String org = null;
		try
		{
			oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
			//Prepare Call:
			cstmtPrint = oracleconnection.prepareCall("{call canon_e410_util_pkg.print_mo_report(?,?,?,?,?,?)}");
			//Set parameters:

			cstmtPrint.setString(1, strMONum);
			cstmtPrint.setString(2, strPrinterName);
			cstmtPrint.setString(3, strUserName);
			cstmtPrint.setString(4, strTrackNum);
		
			cstmtPrint.registerOutParameter(5, OracleTypes.VARCHAR);
			cstmtPrint.registerOutParameter(6, OracleTypes.VARCHAR);
			
			//Execute Statement
			//System.out.println ("Inside before execute **** ");
			cstmtPrint.execute();
			//System.out.println ("Inside After execute **** ");
			
			//Capture output
			UpdateInfo[0] = cstmtPrint.getString(5);
			UpdateInfo[1] = cstmtPrint.getString(6);
			

			//logErrorMessage("Print Manifest report UpdaetInfo[0] "+UpdateInfo[0]+"Will Call UpdateInfo[1] "+UpdateInfo[1]);
		}
		 catch(SQLException sqlExp)
			{
			logErrorMessage("SQLexception: " + sqlExp );
			throw sqlExp;
		    }

		catch(Exception exception)
			{
			logErrorMessage("exception: " + exception);
			}

		finally {

			closeResources(cstmtPrint);
			try
			{
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
					if(cstmtPrint != null) cstmtPrint.close();
			}
			catch (Exception eExp) {}
		}

	return UpdateInfo;
	}


	public String[] processPickLists(String user,String oraSession,String webSession) throws Exception
	{
		//System.out.println ("Inside validTech *** ");
		CallableStatement cstmt = null;
		ResultSet aresultset = null;
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		java.sql.Connection oracleconnection = null;
		
		//to get all the out parameters
		ArrayList list = new ArrayList();
			
		String[] errInfo = new String[3];	
				
		try
		{
			oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
			//Prepare Call:
			cstmt = oracleconnection.prepareCall("{call canon_e410_del_verify_pkg.process_picklists(?,?,?,?,?,?)}");
			//Set parameters:
			cstmt.setString(1, user);
			cstmt.setString(2, oraSession);
			cstmt.setString(3, webSession);
			cstmt.registerOutParameter(4, OracleTypes.INTEGER);
			cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(6, OracleTypes.VARCHAR);
			
			//Execute Statement
			cstmt.execute();
			
			//x_return_status
			errInfo[0] = cstmt.getString(5);
			//x_error_message
			errInfo[1] = cstmt.getString(6);
			//total count
			errInfo[2] = cstmt.getString(4);
			
			//logErrorMessage("POX errInfo0: " +errInfo[0] +" errInfo1: "+errInfo[1]);
		
		}
		 catch(SQLException sqlExp)
			{
			logErrorMessage("SQLexception: " + sqlExp );
			throw sqlExp;
		    }

		catch(Exception exception)
			{
			logErrorMessage("exception: " + exception);
			}

		finally {

			closeResources(cstmt);
			try
			{
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
					if(cstmt != null) cstmt.close();
			}
			catch (Exception eExp) {}
		}

	return errInfo;
	}
	
public ArrayList getRMOResults(String strRMONum,String userName,String OrgId,String SubInv) throws Exception {
		StringBuffer qry = new StringBuffer(20);
		Statement statement = null;
		ResultSet resultSet = null;
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		java.sql.Connection oracleconnection = null;
		ArrayList list = new ArrayList();
		boolean x=true;
		
		String[] userOrg = new String[3];
		//TODO
		oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
		qry.append(" SELECT DISTINCT mtrh.request_number ,rmo.item_number ,substr(rmo.description,1,20),NVL (mtrl.quantity, 0) - NVL (cpl.quantity_received, 0) mo_qty, (SELECT segment1 FROM mtl_item_locations WHERE inventory_location_id = to_locator_id AND ROWNUM < 2) loc \n");
		qry.append("   		,mtrl.line_id ,mtrl.organization_id  ,mtrl.line_number \n");	
		qry.append("   		,NVL ((LENGTH (csp.attribute1) - LENGTH (REPLACE (csp.attribute1, ',', ''))),'-1')+ 1  box_ct \n");	
		qry.append("   		,csp.packlist_number,rmo.mo_line_id,rmo.inventory_item_id,source_subinventory    \n");	
		qry.append("   FROM mtl_txn_request_headers mtrh ,mtl_txn_request_lines mtrl \n");
		qry.append(" 		,mtl_material_transactions mmt,canon_e572_rmo_xfer_tbl rmo,csp_packlist_headers csp \n");
		qry.append("        ,csp_packlist_lines cpl \n");
		qry.append("  WHERE 1=1 \n");
		qry.append("    AND mtrh.header_id = mtrl.header_id \n");
		qry.append("    AND mtrl.line_id = mmt.move_order_line_id \n");
		qry.append("    AND mtrl.line_id = rmo.mo_line_id \n");
		qry.append("    AND mmt.subinventory_code = 'INTRANSIT' \n");
		qry.append("    AND csp.packlist_header_id = rmo.packlist_header_id \n");
		qry.append("    AND csp.packlist_status = 2 \n");		
		qry.append("    AND csp.packlist_header_id = cpl.packlist_header_id \n");		
		qry.append("    AND cpl.line_id = mtrl.line_id \n");		
		qry.append("    AND cpl.packlist_line_status <> 3 \n");	    		
				
		
		//logMessage("@@@ picklistnum "+picklistnum);
		//System.out.println("User getUser-> "+getUser());
		if(strRMONum != null && !("".equals(strRMONum))) 
		{
			qry.append("    AND mtrh.request_number = '");			
			qry.append((strRMONum).toString());
			qry.append("'\n");
		}
		
		if (OrgId!=null && !("".equals(OrgId)))
				//&& ((userOrg[1]==null) || ("".equals(userOrg[1])))
			{
			qry.append("    AND mtrl.organization_id = ");
			qry.append( OrgId);
			qry.append("\n");
			}
			
		qry.append("ORDER BY mtrl.line_number \n");	
				
		logMessage("@@@ Qry \n"+qry.toString());
		try 
		{
		 if (x)
		 {
			if(oracleconnection != null) 
			{
				statement = oracleconnection.createStatement();
				if(statement != null) 
				{	resultSet = statement.executeQuery(qry.toString());
					while(resultSet.next()) 
					{	CanonE410AttributeBean rs = new CanonE410AttributeBean();
						rs.setAttribute1(resultSet.getString(1));
						rs.setAttribute2(resultSet.getString(2));
						rs.setAttribute3(resultSet.getString(3));
						rs.setAttribute4(resultSet.getString(4));
						rs.setAttribute5(resultSet.getString(5));
						rs.setAttribute6(resultSet.getString(6));
						rs.setAttribute7(resultSet.getString(7));
						rs.setAttribute8(resultSet.getString(8));
						rs.setAttribute9(resultSet.getString(9));
						rs.setAttribute10(resultSet.getString(10));
						rs.setAttribute11(resultSet.getString(11));
						rs.setAttribute12(resultSet.getString(12));
						rs.setAttribute13(resultSet.getString(13));
										
						list.add(rs);	
					}//while ends
					
					
				}
				else {logErrorMessage("dbStatus: DBStatus.UNABLE_TO_CREATE_STATEMENT");}
			}
			else {logErrorMessage("dbStatus: UNABLE_TO_GET_CONNECTION");}
		 }
		}
		catch(SQLException sqlExp) 
		{
			logErrorMessage("SQLexception: " + sqlExp );
			throw sqlExp;
		}
		catch(Exception exception) 
		{
			logErrorMessage("exception: " + exception);
		}
		finally 
		{
			closeResources(resultSet);
			closeResources(statement);
			try 
			{
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
			}
			catch (Exception eExp) {
			}
		}
		return list;
	}	
	
		public String[] rcvMO(String moNum,ArrayList listItems,String usrId,String strUserName,String strSourceSubInv){
		 
		String status=null;
		boolean commit_flag=true;
		ResultSet rs = null;
		Connection aconnection = null;	
		java.sql.CallableStatement stmt = null;
		int itemCnt = 0;
		String[] returnInfo	= new String[3];
		logMessage("Inside Assign Items Java...........");
		try{
				if(listItems != null){
				
						itemCnt = listItems.size();
						String[] LineIdArray    = new String[itemCnt];
						String[] LineQtyArray   = new String[itemCnt];
						
				
					try{
					
					   aconnection = TransactionScope.getConnection();
						logMessage("after connection...........");
						ArrayDescriptor oracleArrayDesc =	ArrayDescriptor.createDescriptor("CANON_E410_NUMBER_T",aconnection);
						logMessage("after descriptor...........");
						for(int i=0; i < itemCnt ; i++){
						  
							CanonE410AttributeBean  newItem = (CanonE410AttributeBean)listItems.get(i) ;
							LineIdArray[i] = newItem.getAttribute1()==null?"":newItem.getAttribute1();							
							LineQtyArray[i] = newItem.getAttribute2()==null?"":newItem.getAttribute2();
							
						}
						logMessage("after for loop...........");
						
						for(int i=0; i < itemCnt ; i++){
						   logMessage(" LineIdArray["+i+"]"+LineIdArray[i]);
							logMessage(" LineQtyArray["+i+"]"+LineQtyArray[i]);
							
						}
						
					   ARRAY oraLineId  = new ARRAY (oracleArrayDesc, aconnection, LineIdArray);
					   ARRAY oraLineQty = new ARRAY (oracleArrayDesc, aconnection, LineQtyArray);
					   
									
				      logMessage("after ARRAY...........");
										  
					  StringBuffer sqlStmtBuffer = new StringBuffer("{call canon_e410_util_pkg.receive_mo(?,?,?,?,?,?,?,?)}");
					  String sqlStmt = sqlStmtBuffer.toString();
					  stmt = aconnection.prepareCall(sqlStmt);
					  stmt.setString(1,moNum);
					  stmt.setObject(2,oraLineId);					  
					  stmt.setObject(3,oraLineQty);
					  stmt.setInt(4,Integer.parseInt(usrId));
					  stmt.setString(5,strUserName);
					  stmt.setString(6,strSourceSubInv);
					  
					  stmt.registerOutParameter(7, OracleTypes.VARCHAR);
					  stmt.registerOutParameter(8, OracleTypes.VARCHAR);
					  
					  //TransactionScope.begin(Thread.currentThread());
					  logMessage("before execute...........");					  
					  stmt.execute();
					  logMessage("after execute...........");
					  returnInfo[0] = stmt.getString(7);
					  returnInfo[1] = stmt.getString(8);
					  logMessage(">>>chk="+returnInfo[0]+" err:"+returnInfo[1]);
					  // commit_flag = true;

					}catch (Exception e){
						// commit_flag=false;
						returnInfo[0] ="E";
						returnInfo[1]=e.toString();						
						logErrorMessage("CanonE410PartScanning.rcvMO Exception Occured Level2 Error:"+e);
					}
					finally {										
						stmt.close();
						if(aconnection != null)
							TransactionScope.releaseConnection(aconnection);	
					}				
				}
		}catch(Exception e1) {
		     returnInfo[0] = "E";
			  returnInfo[1]=e1.toString(); ////
			  logErrorMessage("CanonE410PartScanning.rcvMO Exception Occured Level1 Error:"+e1);
		 }
		return returnInfo;
	}
	
	public String getMenuGoBack(int respId) throws Exception {
		StringBuffer qry = new StringBuffer(20);
		Statement statement = null;
		ResultSet resultSet = null;
		//oracle.jdbc.driver.OracleConnection oracleconnection = null;
		java.sql.Connection oracleconnection = null;
		String list = "Y";		
		//TODO
		oracleconnection = (java.sql.Connection)TransactionScope.getConnection();
		qry.append(" SELECT NVL((SELECT 'N' FROM fnd_flex_value_sets ffvs,fnd_flex_values_vl ffvv,fnd_responsibility_vl frv \n");
		qry.append("   		WHERE ffvs.flex_value_set_id = ffvv.flex_value_set_id \n");	
		qry.append("   		AND ffvs.flex_value_set_name = 'CANON_E410_MENU_RESP_CONTROL' \n");	
		qry.append("   		AND ffvv.flex_value = frv.responsibility_name   \n");	
		qry.append("   AND frv.responsibility_id = " );
		qry.append(respId );		
		qry.append(" ),'Y') a FROM DUAL \n");
			
		// logMessage("@@@ Qry \n"+qry.toString());
		try 
		{
		if(oracleconnection != null) 
			{
				statement = oracleconnection.createStatement();
				if(statement != null) 
				{	resultSet = statement.executeQuery(qry.toString());
				  while(resultSet.next()) 
					{
					//canonE410AttributeBean rs = new CanonE410AttributeBean();
					// rs.setAttribute1(resultSet.getString(1));
					  list = resultSet.getString(1);
					}
				}
				else {logErrorMessage("dbStatus: DBStatus.UNABLE_TO_CREATE_STATEMENT");}
			}
			else {logErrorMessage("dbStatus: UNABLE_TO_GET_CONNECTION");}			
		}
		finally 
		{
			closeResources(resultSet);
			closeResources(statement);
			try 
			{
				if(oracleconnection != null)
					TransactionScope.releaseConnection(oracleconnection);
			}
			catch (Exception eExp) {
			}
		}
		return list;
	}
    public static String[] getUserNameAndFullNameS21(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	S21SecurityContext context = S21SecurityContextHolder.getContext();
    	S21Authentication s21Authentication=context.getAuthentication();
        String userName = s21Authentication.getIdentityDetails().getUserName();
        String userID = s21Authentication.getIdentityDetails().getUID();
        //String fullName = s21Authentication.getIdentityDetails().get();
        return new String[]{userName, userID};
    }

	public static boolean isAuthReadableUser(String userId) throws Exception{
        boolean isAuthReadableUser =false;
        try {
            S21UserProfileService upService = S21UserProfileServiceFactory.getInstance().getService();
            if (upService.isFunctionGranted(userId, "EXTNE437T010")) {
            	isAuthReadableUser = true;
            }
        }
    catch (Exception _ex) {
             throw new Exception(" Exception while getting approval role(EXTNE437T010): CanonE410PartScanning.java: "+_ex);
         }
        return isAuthReadableUser;
    }
	public static boolean isAuthEditableUser(String userId) throws Exception{
        boolean isAuthEditableUser =false;
        try {
            S21UserProfileService upService = S21UserProfileServiceFactory.getInstance().getService();
            if (upService.isFunctionGranted(userId, "EXTNE437T020")) {
            	isAuthEditableUser = true;
            }
        }
    catch (Exception _ex) {
             throw new Exception(" Exception while getting approval role(EXTNE437T020): CanonE410PartScanning.java: "+_ex);
         }
        return isAuthEditableUser;
    }
    
}