package com.canon.apps.servreq.dao;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.ArrayDescriptor;

import java.sql.Timestamp;

import canon.apps.common.CanonConstants;

import com.canon.apps.servreq.beans.*;
import com.canon.common.CanonCommonUtil;


public class CanonE307DebriefDetailsDAO extends Object{
	
	public static final String GET_DEBRIEF_INFO = "{CALL CANON_E307_DEBRIEF_PKG.DEBRIEF_HDR_INFO(?,?)}";
	public static final String GET_LABOR_INFO = "{CALL CANON_E307_DEBRIEF_PKG.DEBRIEF_LABOR(?,?,?)}";
	public static final String GET_PARTS_INFO = "{CALL CANON_E307_DEBRIEF_PKG.DEBRIEF_PARTS(?,?)}";	
	//public static final String GET_LABOR_ITEM_LOV = "{CALL CANON_E307_DEBRIEF_PKG.DEBRIEF_PARTS(?,?)}";	
	public static final String GET_EXPENSE_INFO = "{CALL CANON_E307_DEBRIEF_PKG.DEBRIEF_EXPENSE(?,?)}";	
	public static final String GET_DEBRIEF_ITEM_LOV = "{CALL CANON_E307_DEBRIEF_PKG.ADD_DEBRIEF_LINE(?,?,?,?,?,?,?)}";	
	public static final String GET_DEBRIEF_LOV_INFO = "{CALL CANON_E307_DEBRIEF_PKG.DEBRIEF_LOV(?,?,?)}";
	public static final String GET_DEBRIEF_NOTES = "{CALL CANON_E307_DEBRIEF_PKG.DEBRIEF_NOTES(?,?)}";
	public static final String GET_CHECK_LIST = "{CALL CANON_E307_CREATE_SR_PKG.INSTALL_DTLS(?,?,?,?)}";
	public static final String GET_BILL_TP = "{?= call CANON_E307_DEBRIEF_PKG.GET_BILL_TP_CD(?)}";
	public static final String GET_SRVC_RDS = "{CALL CANON_E307_DEBRIEF_PKG.GET_SVC_MTR_READS(?,?,?,?,?)}";	
	public static final String GET_MTR_TP = "{CALL CANON_E307_DEBRIEF_PKG.GET_SVC_RDS_TP(?,?)}";
	public static final String GET_EXIST_SRVC_RDS = "{CALL CANON_E307_DEBRIEF_PKG.GET_SVC_PHYS_MTR_READ(?,?,?,?,?)}";	
	public static final String GET_ONLINE_VALID_FLG = "{?= call CANON_E307_DEBRIEF_PKG.GET_ONLINE_VALIDATION_FLG}";
	public static final String GET_CRCTN_RDS = "{CALL CANON_E307_DEBRIEF_PKG.GET_CRCTN_METER_INFO(?,?,?,?)}";
	public static final String GET_LAST_MTR_RD = "{?= call CANON_E307_DEBRIEF_PKG.GET_LATEST_MTR_READ(?,?)}";
	public static final String GET_OPEN_TASKS = "{CALL CANON_E307_DEBRIEF_PKG.GET_OPEN_TASK_LIST(?,?)}";
	public static final String GET_INVLD_SRVC_RDS = "{CALL CANON_E307_DEBRIEF_PKG.GET_SVC_INVLD_MTR_READ(?,?,?,?)}";	
	public static final String GET_SVC_INVLD_DISP_READ = "{CALL CANON_E307_DEBRIEF_PKG.GET_SVC_INVLD_DISP_READ(?,?,?,?)}";	
	public static final String GET_METER_ERROR_CODES = "{CALL CANON_E307_DEBRIEF_PKG.GET_METER_ERROR_CODES(?,?,?)}";
	public static final String GET_INVLD_PHYS_SRVC_RDS = "{CALL CANON_E307_DEBRIEF_PKG.GET_SVC_INVLD_MTR_RD_GRP(?,?,?)}";
	public static final String GET_INVL_MTR_GRP_SQ = "{?= call CANON_E307_DEBRIEF_PKG.GET_SVC_INV_GRP_SEQ(?,?,?)}";
	
	private   String clsName="CanonE307DebriefDetailsDAO";
	CanonCommonUtil util;
	public CanonE307DebriefDetailsDAO() {
		 util = new CanonCommonUtil();
	}

	public Object[] getDebriefDetails(String strTaskNumber){
		 CallableStatement cstmt = null;
	     Connection connection = null;
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_DEBRIEF_INFO);
			  if (cstmt != null) {
				  // Set the parameters on the statement
				  cstmt.setString(1, strTaskNumber);
				  cstmt.registerOutParameter(2, OracleTypes.STRUCT, CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_DATA_REC");
	              connection.getTypeMap().put(CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_DATA_REC", CanonE307DebriefDetailsRec.class);

	              // Execute the statement
	              util.logMsg(false,clsName+".getDebriefDetails Before Execute", strTaskNumber);
	              cstmt.execute();
	              util.logMsg(false,clsName+".getDebriefDetails", "After Execute");
	               return new Object[]{cstmt.getObject(2)};
			  }
			  else {
				  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
	          	}
			} else {
				System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	        }
	     } catch (Exception ex) {
	    	 util.logMsg(true,clsName+".getDebriefDetails", ex.getMessage());
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			  exp.printStackTrace();
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 util.logMsg(true,clsName+".getDebriefDetails", ex.getMessage());
	    		 }
	    	 }
	    	}
		  return null;		
	}	
	public Object[] getDebriefLaborInfo(String strTaskNumber){
		 CallableStatement cstmt = null;
	     Connection connection = null;
	     try
	       {
			connection = TransactionScope.getConnection();
			List<Object> laborList = new ArrayList<Object>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_LABOR_INFO);
			  if (cstmt != null) {
				  // Set the parameters on the statement
				  cstmt.setString(1, strTaskNumber);
				  cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_LABOR_REC", CanonE307DebriefLaborDetailsRec.class);
	              cstmt.registerOutParameter(3, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_LABOR_TBL");
				  // Execute the statement
	              util.logMsg(false,clsName+".getDebriefLaborInfo Before Execute", "strTaskNumber : "+ strTaskNumber);
	              cstmt.execute();
	              util.logMsg(false,clsName+".getDebriefLaborInfo", "After Execute");
				  Object[] oo = (Object[]) ((Array)cstmt.getObject(3)).getArray();
				  if(oo!=null && oo.length>0){
					  for (int i = 0; i < oo.length; i++) {
						  laborList.add(oo[i]);			       
					  }	
				  }
					  return new Object[]{
							  laborList,
		                     cstmt.getObject(2)
		                    };
			  }
			  else {
				  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
				  
	          	}
			} else {
				System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	        }
	     } catch (Exception ex) {
	    	 ex.printStackTrace();
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			  exp.printStackTrace();
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 ex.printStackTrace();
	    		 }
	    	 }
	    	}
		  return null;		
	}
	public List getDebriefPartsInfo(String strTaskNumber){
		 CallableStatement cstmt = null;
	     Connection connection = null;
	     try
	       {
			connection = TransactionScope.getConnection();
			List<Object> partsList = new ArrayList<Object>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_PARTS_INFO);
			  if (cstmt != null) {
				  // Set the parameters on the statement
				  cstmt.setString(1, strTaskNumber);
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_PART_REC", CanonE307DebriefPartsRec.class);
	              cstmt.registerOutParameter(2, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_PART_TBL");
				  // Execute the statement
	              util.logMsg(false,clsName+".getDebriefPartsInfo Before Execute", "strTaskNumber : "+strTaskNumber);
	              cstmt.execute();
	              util.logMsg(false,clsName+".getDebriefPartsInfo", "After Execute");
				  Object[] oo = (Object[]) ((Array)cstmt.getObject(2)).getArray();
				  if(oo!=null && oo.length>0){
					  for (int i = 0; i < oo.length; i++) {
						  partsList.add(oo[i]);			       
					  }	
					  return partsList;
				  }
			  }
			  else {
				  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
	          	}
			} else {
				System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	        }
	     } catch (Exception ex) {
	    	 ex.printStackTrace();
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			  exp.printStackTrace();
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 ex.printStackTrace();
	    		 }
	    	 }
	    	}
		  return null;		
	}	
	public Object[] getItemDetailsLov(String strItmTpe, String strItemCd, int start, int end, String strTaskNumber){
		CallableStatement cstmt = null;
	     Connection connection = null;
	     try
	       {
	    //	strItemNumber = "011ZZ003";
			connection = TransactionScope.getConnection();
			List<Object> laborList = new ArrayList<Object>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_DEBRIEF_ITEM_LOV);
			  if (cstmt != null) {
				  // Set the parameters on the statement
				  cstmt.setObject(1, strItmTpe, OracleTypes.VARCHAR);
				  cstmt.setObject(2, strItemCd, OracleTypes.VARCHAR);
				  cstmt.setInt(3, start);
				  cstmt.setInt(4, end);
				  cstmt.setObject(5, strTaskNumber, OracleTypes.VARCHAR);
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_ITEM_LOV_REC", CanonE307DebriefItemLov.class);
	           //  ArrayDescriptor insDescriptor = ArrayDescriptor.createDescriptor("CANON.CANON_E307_DEBRIEF_NOTE_TBL", connection);
	              cstmt.registerOutParameter(6, OracleTypes.INTEGER);
	              cstmt.registerOutParameter(7, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_ITEM_LOV_TBL");
				  // Execute the statement
	              util.logMsg(false,clsName+".getItemDetailsLov Before Execute", "strItmTpe: "+ strItmTpe+" strItemCd : "+strItemCd);
	              cstmt.execute();
	              util.logMsg(false,clsName+".getItemDetailsLov", "After Execute");
	              Object[] oo = (Object[]) ((Array)cstmt.getObject(7)).getArray();
				  if(oo!=null && oo.length>0){
					  for (int i = 0; i < oo.length; i++) {
						  laborList.add(oo[i]);			       
					  }	
					  return new Object[]{
							  	laborList
		                        ,cstmt.getObject(6)
		                        };
				  }
			  }
			  else {
				  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
	          	}
			} else {
				System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	        }
	     } catch (Exception ex) {
	    	 ex.printStackTrace();
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			  exp.printStackTrace();
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 ex.printStackTrace();
	    		 }
	    	 }
	    	}
		  return null;		
	} 
	
	public double getLbrDuration(String strStrtDt, String strStrtTm, String strEndDt, String strEndTm, String strtAmPm, String endAmPm){
		double retFlag=0.0;
		Connection connection = null;
		CallableStatement cstmt  = null;
		ResultSet rs = null;
		System.out.println("strStrtDt : "+ strStrtDt + "strStrtTm : "+ strStrtTm+" strEndDt : "+strEndDt+" strEndTm : "+strEndTm+" strtAmPm: "+strtAmPm+" endAmPm : "+ endAmPm);
		try
		{
		   String plsqlExp =  " Begin \n "
				   + " :1 := CANON_E307_UTILS.date_diff_format2(:2,:3,:4,:5,:6,:7) ; \n "
				   + " End;";
		    connection = TransactionScope.getConnection();
			  cstmt = connection.prepareCall(plsqlExp);
			  cstmt.setString(2, strStrtDt);
			  cstmt.setString(3, strStrtTm);
			  cstmt.setString(4, strEndDt);
			  cstmt.setString(5, strEndTm);
			  cstmt.setString(6, strtAmPm);
			  cstmt.setString(7, endAmPm);
			  cstmt.registerOutParameter(1,Types.DOUBLE);
			  cstmt.execute();
			  retFlag = cstmt.getDouble(1);
		}
 		catch(SQLException eSQLExp)
		{
		  System.out.println("SQL Exception is:" + eSQLExp.toString());
		}
		catch (Exception eExp)
		{
		  System.out.println("Exception is:" + eExp.toString());
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
	public List<Object> getDebriefExpenseInfo(String strTaskNumber){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     try
	       {
	    	connection = TransactionScope.getConnection();
			List<Object> list = new ArrayList<Object>();	
			if (connection != null) {
				cstmt =  connection.prepareCall(GET_EXPENSE_INFO);
			  if (cstmt != null) {
				  // Set the parameters on the statement
				  cstmt.setString(1, strTaskNumber);
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_EXPENSE_REC", CanonE307DebriefExpensesRec.class);
	              cstmt.registerOutParameter(2, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_EXPENSE_TBL");
	              util.logMsg(false,clsName+".getDebriefExpenseInfo Before Execute", "strTaskNumber: "+ strTaskNumber);
				  // Execute the statement
	              cstmt.execute();
	              util.logMsg(false,clsName+".getDebriefExpenseInfo", "After Execute");
	              Object[] oo = (Object[]) ((Array)cstmt.getObject(2)).getArray();
				  if(oo!=null && oo.length>0){
					  for (int i = 0; i < oo.length; i++) {
						   list.add(oo[i]);			       
					  }	
					  return list;
				  }
			  }
			  else {
				  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
	          	}
			} else {
				System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	        }
	     } catch (Exception ex) {
	    	 ex.printStackTrace();
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			  exp.printStackTrace();
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 ex.printStackTrace();
	    		 }
	    	 }
	    	}
		  return null;		
	}
	public ArrayList<CanonE307ServReqDebriefLovRec> getDebriefLovVal(String strVal, String searchVal){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     ArrayList<CanonE307ServReqDebriefLovRec> arList = new ArrayList<CanonE307ServReqDebriefLovRec>();
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_DEBRIEF_LOV_INFO);
			  if (cstmt != null) {
				  // Set the parameters on the statement
				  cstmt.setString(1, strVal);
				  cstmt.setString(2, searchVal);
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_LOV_VAL_REC", CanonE307ServReqDebriefLovRec.class);
	              cstmt.registerOutParameter(3, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_LOV_VAL_TBL");
				  // Execute the statement
	              util.logMsg(false,clsName+".getDebriefLovVal", "strVal: "+strVal+" searchVal : "+searchVal);
	              cstmt.execute();
	              util.logMsg(false,clsName+".getDebriefLovVal", "After Execute");
	              Object oo[] =(Object[]) ((Array) cstmt.getObject(3)).getArray();
				  if(oo!=null && oo.length>0){
					  for (int i = 0; i < oo.length; i++) {
						  CanonE307ServReqDebriefLovRec rec=  (CanonE307ServReqDebriefLovRec)oo[i] ;
						  arList.add( rec);		
					  }	
					  return arList;
				  }
			  }
			  else {
				  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
	          	}
			} else {
				System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	        }
	     } catch (Exception ex) {
	    	 ex.printStackTrace();
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			  exp.printStackTrace();
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 ex.printStackTrace();
	    		 }
	    	 }
	    	}
		  return null;		
	}
	public ArrayList<CanonE307DebriefNotesDetailsRec> getDebriefNotesInfo(String strTaskNumber){
		 CallableStatement cstmt = null;
	     Connection connection = null;
	     try
	       {
			connection = TransactionScope.getConnection();
			ArrayList<CanonE307DebriefNotesDetailsRec> noteList = new ArrayList<CanonE307DebriefNotesDetailsRec>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_DEBRIEF_NOTES);
			  if (cstmt != null) {
				  // Set the parameters on the statement
				  cstmt.setString(1, strTaskNumber);
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_NOTE_REC", CanonE307DebriefNotesDetailsRec.class);
	              cstmt.registerOutParameter(2, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_NOTE_TBL");
				  // Execute the statement
	              util.logMsg(false,clsName+".getDebriefNotesInfo", "Before Execute");
	              cstmt.execute();
	              util.logMsg(false,clsName+".getDebriefNotesInfo", "After Execute");
				  Object[] oo = (Object[]) ((Array)cstmt.getObject(2)).getArray();
				  if(oo!=null && oo.length>0){
					  for (int i = 0; i < oo.length; i++) {
						  CanonE307DebriefNotesDetailsRec rec=  (CanonE307DebriefNotesDetailsRec)oo[i] ;
						  noteList.add( rec);		
					  }	
					  return noteList;
				  }
			  }
			  else {
				  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
	          	}
			} else {
				System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	        }
	     } catch (Exception ex) {
	    	 ex.printStackTrace();
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			  exp.printStackTrace();
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 ex.printStackTrace();
	    		 }
	    	 }
	    	}
		  return null;		
	}	
	public String checkInstallCall(String strTskNum){
		String retFlag="";
		Connection connection = null;
		CallableStatement cstmt  = null;
		ResultSet rs = null;
		try
		{
		   String plsqlExp =  " Begin \n "
				   + " :1 := CANON_E307_CREATE_SR_PKG.INSTALL_CALL_CHECK(:2) ; \n "
				   + " End;";
		    connection = TransactionScope.getConnection();

			  cstmt = connection.prepareCall(plsqlExp);
			  cstmt.setString(2, strTskNum);
			  cstmt.registerOutParameter(1,Types.VARCHAR);
			  cstmt.execute();
			  retFlag = cstmt.getString(1);
		}
 		catch(SQLException eSQLExp)
		{
		  System.out.println("SQL Exception is:" + eSQLExp.toString());
		}
		catch (Exception eExp)
		{
		  System.out.println("Exception is:" + eExp.toString());
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
	public ArrayList<CanonE307DebriefInstallRec> getCheckListInfo(String strSrlNum, String strTaskNum, String strStatus){
		 CallableStatement cstmt = null;
	     Connection connection = null;
	     try
	       {
	    	 util.logMsg(false,clsName+".getCheckListInfo ", "strSrlNum : "+strSrlNum+" strTaskNum: "+strTaskNum+" strStatus: "+strStatus);
	  			connection = TransactionScope.getConnection();
				ArrayList<CanonE307DebriefInstallRec> checkList = new ArrayList<CanonE307DebriefInstallRec>();	
				if (connection != null) {
					cstmt = connection.prepareCall(GET_CHECK_LIST);
				  if (cstmt != null) {
					  // Set the parameters on the statement
					  cstmt.setString(1, strSrlNum);
					  cstmt.setString(2, strTaskNum);
					  cstmt.setString(3, strStatus);
		              Map map = (Map) connection.getTypeMap();
		              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_INSTALL_REC", CanonE307DebriefInstallRec.class);
		              cstmt.registerOutParameter(4, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_INSTALL_TBL");
					  // Execute the statement
		              util.logMsg(false,clsName+".getCheckListInfo Before Execute", "strSrlNum : "+strSrlNum);
		              cstmt.execute();
		              util.logMsg(false,clsName+".getCheckListInfo", "After Execute");
					  Object[] oo = (Object[]) ((Array)cstmt.getObject(4)).getArray();
					  if(oo!=null && oo.length>0){
						  util.logMsg(false,clsName+".getCheckListInfo", "Checklist Length "+ oo.length);
						  for (int i = 0; i < oo.length; i++) {
							  CanonE307DebriefInstallRec rec=  (CanonE307DebriefInstallRec)oo[i] ;
							  checkList.add( rec);		
						  }	
						  return checkList;
					  }
				  }
				  else {
					  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
		          	}
				} else {
					System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
		        }	    		 
	     } catch (Exception ex) {
	    	 ex.printStackTrace();
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			  exp.printStackTrace();
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 ex.printStackTrace();
	    		 }
	    	 }
	    	}
		  return null;		
	}	
    public String getBillTpCd(String strMdseCd){
        util.logMsg(false, clsName+".getBillTpCd", "strMdseCd : " + strMdseCd);	        
        CallableStatement statement = null;
        Connection connection = null;
        String billTpCd = "";
        try {
        	 connection = TransactionScope.getConnection();
             if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_BILL_TP);
                if (statement != null) {
                    statement.setObject(2, strMdseCd, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
                    statement.execute();
                    billTpCd =  statement.getString(1);
                    util.logMsg(false, clsName+".getBillTpCd", "billTpCd : " + billTpCd);
                    
                    return billTpCd;
                }else{
                	 util.logMsg(true,clsName+".getBillTpCd", " Statement is Null");
                } 
             }else{
            	 util.logMsg(true,clsName+".getBillTpCd", " Connection is Null");
             }
        } 
        catch(SQLException eSQLExp)
		{
  		  System.out.println("SQL Exception is:" + eSQLExp.toString());
  		}
  		catch (Exception eExp)
  		{
  		  System.out.println("Exception is:" + eExp.toString());
  			}
  			finally
  			{
  				try
  				{
  					if(statement != null)
  						statement.close();
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
        return billTpCd;
    }
	public ArrayList<CanonE307SvcMtrRdsRec> getSvcMtrDet(String strFsrNum, String strTaskNum, String strTaskSts, String strSvcMachMstrPk){
		 CallableStatement cstmt = null;
	     Connection connection = null;
	     System.out.println("strFsrNum: "+ strFsrNum+" strTaskNum: "+strTaskNum+" strTaskSts: "+strTaskSts +" strSvcMachMstrPk: "+strSvcMachMstrPk);
	     try
	       {
	  			connection = TransactionScope.getConnection();
				ArrayList<CanonE307SvcMtrRdsRec> checkList = new ArrayList<CanonE307SvcMtrRdsRec>();	
				if (connection != null) {
					cstmt = connection.prepareCall(GET_SRVC_RDS);
				  if (cstmt != null) {
					  // Set the parameters on the statement
					  cstmt.setString(1, strFsrNum);
					  cstmt.setString(2, strTaskNum);
					  cstmt.setString(3, strTaskSts);
					  cstmt.setString(4, strSvcMachMstrPk);
		              Map map = (Map) connection.getTypeMap();
		              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_SVC_READS_REC", CanonE307SvcMtrRdsRec.class);
		              cstmt.registerOutParameter(5, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_SVC_READS_TBL");
					  // Execute the statement
		              util.logMsg(false,clsName+".getSvcMtrDet Before Execute", "strSrlNum : "+strFsrNum);
		              cstmt.execute();
		              util.logMsg(false,clsName+".getSvcMtrDet", "After Execute");
					  Object[] oo = (Object[]) ((Array)cstmt.getObject(5)).getArray();
					  if(oo!=null && oo.length>0){
						  util.logMsg(false,clsName+".getSvcMtrDet", "Inside Object");
						  for (int i = 0; i < oo.length; i++) {
							  CanonE307SvcMtrRdsRec rec=  (CanonE307SvcMtrRdsRec)oo[i] ;
							  checkList.add( rec);		
						  }	
						  return checkList;
					  }
				  }
				  else {
					  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
		          	}
				} else {
					System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
		        }	    		 
	     } catch (Exception ex) {
	    	 ex.printStackTrace();
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			  exp.printStackTrace();
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 ex.printStackTrace();
	    		 }
	    	 }
	    	}
		  return null;		
	}	
	public ArrayList<CanonE307MtrRdTpeRec> getMtrRdType(String strInOutMtr){
		 CallableStatement cstmt = null;
	     Connection connection = null;
	     try
	       {
			connection = TransactionScope.getConnection();
			ArrayList<CanonE307MtrRdTpeRec> rdTpLst = new ArrayList<CanonE307MtrRdTpeRec>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_MTR_TP);
			  if (cstmt != null) {
				  // Set the parameters on the statement
				  cstmt.setString(1, strInOutMtr);
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_TYPE_LOV_REC", CanonE307MtrRdTpeRec.class);
	              cstmt.registerOutParameter(2, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_TYPE_LOV_TBL");
				  // Execute the statement
	              util.logMsg(false,clsName+".getMtrRdType", "Before Execute");
	              cstmt.execute();
	              util.logMsg(false,clsName+".getMtrRdType", "After Execute");
				  Object[] oo = (Object[]) ((Array)cstmt.getObject(2)).getArray();
				  if(oo!=null && oo.length>0){
					  for (int i = 0; i < oo.length; i++) {
						  CanonE307MtrRdTpeRec rec=  (CanonE307MtrRdTpeRec)oo[i] ;
						  rdTpLst.add( rec);		
					  }	
					  return rdTpLst;
				  }
			  }
			  else {
				  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
	          	}
			} else {
				System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	        }
	     } catch (Exception ex) {
	    	 ex.printStackTrace();
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			  exp.printStackTrace();
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 ex.printStackTrace();
	    		 }
	    	 }
	    	}
		  return null;		
	}
	public ArrayList<CanonE307SvcMtrRdsRec> getExistSvcMtrDet(String strFsrNum, String strTaskNum, String strSvcMachMstrPk, String mtrTp){
		 CallableStatement cstmt = null;
	     Connection connection = null;
	     System.out.println("strFsrNum: "+ strFsrNum+" strTaskNum: "+strTaskNum+" strSvcMachMstrPk: "+strSvcMachMstrPk+" mtrTp: "+mtrTp);
	     try
	       {
	  			connection = TransactionScope.getConnection();
				ArrayList<CanonE307SvcMtrRdsRec> checkList = new ArrayList<CanonE307SvcMtrRdsRec>();	
				if (connection != null) {
					cstmt = connection.prepareCall(GET_EXIST_SRVC_RDS);
				  if (cstmt != null) {
					  // Set the parameters on the statement
					  cstmt.setString(1, strFsrNum);
					  cstmt.setString(2, strTaskNum);
					  cstmt.setString(3, strSvcMachMstrPk);
					  cstmt.setString(4, mtrTp);
		              Map map = (Map) connection.getTypeMap();
		              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_SVC_READS_REC", CanonE307SvcMtrRdsRec.class);
		              cstmt.registerOutParameter(5, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_SVC_READS_TBL");
					  // Execute the statement
		              util.logMsg(false,clsName+".getExistSvcMtrDet Before Execute", "strSrlNum : "+strFsrNum);
		              cstmt.execute();
		              util.logMsg(false,clsName+".getExistSvcMtrDet", "After Execute");
					  Object[] oo = (Object[]) ((Array)cstmt.getObject(5)).getArray();
					  if(oo!=null && oo.length>0){
						  //util.logMsg(false,clsName+".getExistSvcMtrDet", "Inside Object");
						  for (int i = 0; i < oo.length; i++) {
							  CanonE307SvcMtrRdsRec rec=  (CanonE307SvcMtrRdsRec)oo[i] ;
							  checkList.add( rec);		
						  }	
						  return checkList;
					  }
				  }
				  else {
					  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
		          	}
				} else {
					System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
		        }	    		 
	     } catch (Exception ex) {
	    	 ex.printStackTrace();
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			  exp.printStackTrace();
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 ex.printStackTrace();
	    		 }
	    	 }
	    	}
		  return null;		
	}
    public String getOnlineValidFlg(){
        
        CallableStatement statement = null;
        Connection connection = null;
        String retFlg = "";
        try {
        	 connection = TransactionScope.getConnection();
             if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_ONLINE_VALID_FLG);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
                    util.logMsg(false, clsName+".getOnlineValidFlg", "Beofe execute");
                    statement.execute();
                    retFlg =  statement.getString(1);
                    util.logMsg(false, clsName+".getOnlineValidFlg", "retFlg : " + retFlg);
                    
                    return retFlg;
                }else{
                	 util.logMsg(true,clsName+".getOnlineValidFlg", " Statement is Null");
                } 
             }else{
            	 util.logMsg(true,clsName+".getOnlineValidFlg", " Connection is Null");
             }
        } 
        catch(SQLException eSQLExp)
		{
  		  System.out.println("SQL Exception is:" + eSQLExp.toString());
  		}
  		catch (Exception eExp)
  		{
  		  System.out.println("Exception is:" + eExp.toString());
  			}
  			finally
  			{
  				try
  				{
  					if(statement != null)
  						statement.close();
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
        return retFlg;
    }
	public ArrayList<CanonE307SvcMtrRdsRec> getCrctnSvcMtrDet(String strSvcMachMstrPk, String fsrNum, String svcTaskNum){
		 CallableStatement cstmt = null;
	     Connection connection = null;

	     util.logMsg(false,clsName+".getCrctnSvcMtrDet", "strSvcMachMstrPk: "+strSvcMachMstrPk);
	     try
	       {
	  			connection = TransactionScope.getConnection();
				ArrayList<CanonE307SvcMtrRdsRec> checkList = new ArrayList<CanonE307SvcMtrRdsRec>();	
				if (connection != null) {
					cstmt = connection.prepareCall(GET_CRCTN_RDS);
				  if (cstmt != null) {
					  // Set the parameters on the statement
					  cstmt.setString(1, fsrNum);
					  cstmt.setString(2, svcTaskNum);
					  cstmt.setString(3, strSvcMachMstrPk);
		              Map map = (Map) connection.getTypeMap();
		              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_SVC_READS_REC", CanonE307SvcMtrRdsRec.class);
		              cstmt.registerOutParameter(4, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_SVC_READS_TBL");
					  // Execute the statement
		              util.logMsg(false,clsName+".getCrctnSvcMtrDet Before Execute", "strSvcMachMstrPk : "+strSvcMachMstrPk+" fsrNum: "+fsrNum+" svcTaskNum: "+svcTaskNum);
		              cstmt.execute();
		              util.logMsg(false,clsName+".getCrctnSvcMtrDet", "After Execute");
					  Object[] oo = (Object[]) ((Array)cstmt.getObject(4)).getArray();
					  if(oo!=null && oo.length>0){
						 // util.logMsg(false,clsName+".getExistSvcMtrDet", "Inside Object");
						  for (int i = 0; i < oo.length; i++) {
							  CanonE307SvcMtrRdsRec rec=  (CanonE307SvcMtrRdsRec)oo[i] ;
							  checkList.add( rec);		
						  }	
						  return checkList;
					  }
				  }
				  else {
					  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
		          	}
				} else {
					System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
		        }	    		 
	     } catch (Exception ex) {
	    	 ex.printStackTrace();
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			  exp.printStackTrace();
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 ex.printStackTrace();
	    		 }
	    	 }
	    	}
		  return null;		
	}
    public String getMtrLatestRead(String strSvcMachMstrPk, String strPhyMtrPk){
        util.logMsg(false, clsName+".getMtrLatestRead", "strSvcMachMstrPk : " + strSvcMachMstrPk+" strPhyMtrPk: "+strPhyMtrPk);	        
        CallableStatement statement = null;
        Connection connection = null;
        String lastMtrRd = "";
        try {
        	 connection = TransactionScope.getConnection();
             if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_LAST_MTR_RD);
                if (statement != null) {
                    statement.setObject(2, strSvcMachMstrPk, OracleTypes.VARCHAR);
                    statement.setObject(3, strPhyMtrPk, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
                    statement.execute();
                    lastMtrRd =  statement.getString(1);
                    util.logMsg(false, clsName+".getMtrLatestRead", "lastMtrRd : " + lastMtrRd);	 
                    return lastMtrRd;
                }else{
                	 util.logMsg(true,clsName+".getMtrLatestRead", " Statement is Null");
                } 
             }else{
            	 util.logMsg(true,clsName+".getMtrLatestRead", " Connection is Null");
             }
        } 
        catch(SQLException eSQLExp)
		{
  		  System.out.println("SQL Exception is:" + eSQLExp.toString());
  		}
  		catch (Exception eExp)
  		{
  		  System.out.println("Exception is:" + eExp.toString());
  			}
  			finally
  			{
  				try
  				{
  					if(statement != null)
  						statement.close();
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
        return lastMtrRd;
    }	
	public ArrayList getOpenTasksLst(String strFsrNum){
		
	    String MTHD=".getOpenTasksLst";
	    System.out.println("Inside getOpenTasksLst(): " + strFsrNum);
	    ArrayList tskList = new ArrayList();
		ResultSet rs = null;
		Connection conn = null;
		CallableStatement cstmt = null;

		try {
			conn = TransactionScope.getConnection();
			cstmt = (CallableStatement)conn.prepareCall(GET_OPEN_TASKS);
			cstmt.setString(1,strFsrNum);
			cstmt.registerOutParameter(2,OracleTypes.CURSOR);
			System.out.println("Before execute");
			cstmt.execute();
			System.out.println("After execute");
			rs = (ResultSet) cstmt.getObject(2);
		
			while (rs.next()) {
				String svcTaskNum = (rs.getString("SVC_TASK_NUM"));
				tskList.add(svcTaskNum);
			}
		} catch (Exception e1) {
			util.logMsg(false,clsName+".getOpenTasksLst Before Execute", strFsrNum);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
				if (conn != null)
					TransactionScope.releaseConnection(conn);
			} catch (Exception exception2) {
				exception2.printStackTrace();
			}
		}
		return tskList;
	}   
	public ArrayList<CanonE307SvcMtrRdsRec> getInvalidSvcMtrDet(String invaldData, String svcMachMstrPk, String meterReadDate){
		 CallableStatement cstmt = null;
	     Connection connection = null; 
	     System.out.println("getInvalidSvcMtrDet invaldData: "+ invaldData+" strSvcMachMstrPk: "+svcMachMstrPk+" meterReadDate: "+meterReadDate);
	     try
	       {
	  			connection = TransactionScope.getConnection();
				ArrayList<CanonE307SvcMtrRdsRec> checkList = new ArrayList<CanonE307SvcMtrRdsRec>();	
				if (connection != null) {
					cstmt = connection.prepareCall(GET_INVLD_SRVC_RDS);
				  if (cstmt != null) {
					  // Set the parameters on the statement
					  cstmt.setString(1, invaldData);
					  cstmt.setString(2, svcMachMstrPk);
					  cstmt.setString(3, meterReadDate);
		              Map map = (Map) connection.getTypeMap();
		              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_SVC_READS_REC", CanonE307SvcMtrRdsRec.class);
		              cstmt.registerOutParameter(4, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_SVC_READS_TBL");
					  // Execute the statement
		              util.logMsg(false,clsName+".getInvalidSvcMtrDet Before Execute", "svcMachMstrPk : "+svcMachMstrPk);
		              cstmt.execute();
		              util.logMsg(false,clsName+".getInvalidSvcMtrDet", "After Execute");
					  Object[] oo = (Object[]) ((Array)cstmt.getObject(4)).getArray();
					  if(oo!=null && oo.length>0){
						  util.logMsg(false,clsName+".getExistSvcMtrDet", "Inside Object");
						  for (int i = 0; i < oo.length; i++) {
							  CanonE307SvcMtrRdsRec rec=  (CanonE307SvcMtrRdsRec)oo[i] ;
							  checkList.add( rec);		
						  }	
						  return checkList;
					  }
				  }
				  else {
					  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
		          	}
				} else {
					System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
		        }	    		 
	     } catch (Exception ex) {
	    	 ex.printStackTrace();
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			  exp.printStackTrace();
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 ex.printStackTrace();
	    		 }
	    	 }
	    	}
		  return null;		
	}
	public ArrayList<CanonE307SvcMtrRdsRec> getInvalidSvcDispDet(String fsrNum, String svcTaskNum, String svcMachMstrPk){
		 CallableStatement cstmt = null;
	     Connection connection = null; 
	     System.out.println("getInvalidSvcDispDet fsrNum: "+ fsrNum+" svcTaskNum: "+svcTaskNum+" svcMachMstrPk: "+svcMachMstrPk);
	     try
	       {
	  			connection = TransactionScope.getConnection();
				ArrayList<CanonE307SvcMtrRdsRec> checkList = new ArrayList<CanonE307SvcMtrRdsRec>();	
				if (connection != null) {
					cstmt = connection.prepareCall(GET_SVC_INVLD_DISP_READ);
				  if (cstmt != null) {
					  // Set the parameters on the statement
					  cstmt.setString(1, fsrNum);
					  cstmt.setString(2, svcTaskNum);
					  cstmt.setString(3, svcMachMstrPk);
		              Map map = (Map) connection.getTypeMap();
		              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_SVC_READS_REC", CanonE307SvcMtrRdsRec.class);
		              cstmt.registerOutParameter(4, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_SVC_READS_TBL");
					  // Execute the statement
		              util.logMsg(false,clsName+".getInvalidSvcDispDet Before Execute", "svcMachMstrPk : "+svcMachMstrPk);
		              cstmt.execute();
		              util.logMsg(false,clsName+".getInvalidSvcDispDet", "After Execute");
					  Object[] oo = (Object[]) ((Array)cstmt.getObject(4)).getArray();
					  if(oo!=null && oo.length>0){
						 // util.logMsg(false,clsName+".getInvalidSvcDispDet", "Inside Object");
						  for (int i = 0; i < oo.length; i++) {
							  CanonE307SvcMtrRdsRec rec=  (CanonE307SvcMtrRdsRec)oo[i] ;
							  checkList.add( rec);		
						  }	
						  return checkList;
					  }
				  }
				  else {
					  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
		          	}
				} else {
					System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
		        }	    		 
	     } catch (Exception ex) {
	    	 ex.printStackTrace();
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			  exp.printStackTrace();
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 ex.printStackTrace();
	    		 }
	    	 }
	    	}
		  return null;		
	}
	public ArrayList getMeterErrorCodes(String strSource,String errorCode){
		
	    String MTHD=".getMeterErrorCodes";
	    System.out.println("Inside getMeterErrorCodes(): " + strSource+" errorCode: "+errorCode);
	    ArrayList errList = new ArrayList();
		ResultSet rs = null;
		Connection conn = null;
		CallableStatement cstmt = null;

		try {
			conn = TransactionScope.getConnection();
			cstmt = (CallableStatement)conn.prepareCall(GET_METER_ERROR_CODES);
			cstmt.setString(1,strSource);
			cstmt.setString(2,errorCode);
			cstmt.registerOutParameter(3,OracleTypes.CURSOR);
			System.out.println("Before execute");
			cstmt.execute();
			System.out.println("After execute");
			rs = (ResultSet) cstmt.getObject(3);
		
			while (rs.next()) {
				CanonE307MeterErrorCodesBean errBean = new CanonE307MeterErrorCodesBean();
				//System.out.println("Error Code: "+rs.getString("ERROR_CODE"));
				errBean.setStrMtrErrCd(rs.getString("ERROR_CODE"));
				errBean.setStrMtrErrMsg(rs.getString("ERROR_MSG"));
				errBean.setStrMtrSoftWarnFlg(rs.getString("WARN_FLG"));
				errBean.setStrLowerRdFlg(rs.getString("LOWER_READ_FLG"));
				errList.add(errBean);
			}
		} catch (Exception e1) {
			util.logMsg(false,clsName+".getOpenTasksLst Before Execute", strSource);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
				if (conn != null)
					TransactionScope.releaseConnection(conn);
			} catch (Exception exception2) {
				exception2.printStackTrace();
			}
		}
		return errList;
	} 
	public ArrayList<CanonE307SvcMtrRdsRec> getInvalidSvcMtrSeq(String physMtrReadGrpSeq, String svcMachMstrPk){
		 CallableStatement cstmt = null;
	     Connection connection = null; 
	     System.out.println("getInvalidAllSvcMtrDet invaldData: "+ physMtrReadGrpSeq+" physMtrReadGrpSeq: "+svcMachMstrPk);
	     try
	       {
	  			connection = TransactionScope.getConnection();
				ArrayList<CanonE307SvcMtrRdsRec> checkList = new ArrayList<CanonE307SvcMtrRdsRec>();	
				if (connection != null) {
					cstmt = connection.prepareCall(GET_INVLD_PHYS_SRVC_RDS);
				  if (cstmt != null) {
					  // Set the parameters on the statement
					  cstmt.setString(1, physMtrReadGrpSeq);
					  cstmt.setString(2, svcMachMstrPk);
		              Map map = (Map) connection.getTypeMap();
		              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_SVC_READS_REC", CanonE307SvcMtrRdsRec.class);
		              cstmt.registerOutParameter(3, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_SVC_READS_TBL");
					  // Execute the statement
		              util.logMsg(false,clsName+".getInvalidAllSvcMtrDet Before Execute", "svcMachMstrPk : "+svcMachMstrPk);
		              cstmt.execute();
		              util.logMsg(false,clsName+".getInvalidAllSvcMtrDet", "After Execute");
					  Object[] oo = (Object[]) ((Array)cstmt.getObject(3)).getArray();
					  if(oo!=null && oo.length>0){
						  util.logMsg(false,clsName+".getExistSvcMtrDet", "Inside Object");
						  for (int i = 0; i < oo.length; i++) {
							  CanonE307SvcMtrRdsRec rec=  (CanonE307SvcMtrRdsRec)oo[i] ;
							  checkList.add( rec);		
						  }	
						  return checkList;
					  }
				  }
				  else {
					  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
		          	}
				} else {
					System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
		        }	    		 
	     } catch (Exception ex) {
	    	 ex.printStackTrace();
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			  exp.printStackTrace();
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 ex.printStackTrace();
	    		 }
	    	 }
	    	}
		  return null;		
	}	
	   public String getMtrInvGrpSeq(String invaldData, String svcMachMstrPk, String meterReadDate){
	        util.logMsg(false, clsName+".getMtrInvGrpSeq", "invaldData : " + invaldData+" svcMachMstrPk: "+svcMachMstrPk+" meterReadDate: "+meterReadDate);	        
	        CallableStatement statement = null;
	        Connection connection = null;
	        String invaldGrpSq = "";
	        String invaldGrpSeq = "";
	        try {
	        	 connection = TransactionScope.getConnection();
	             if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(GET_INVL_MTR_GRP_SQ);
	                if (statement != null) {
	                    statement.setObject(2, invaldData, OracleTypes.VARCHAR);
	                    statement.setObject(3, svcMachMstrPk, OracleTypes.VARCHAR);
	                    statement.setObject(4, meterReadDate, OracleTypes.VARCHAR);
	                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
	                    statement.execute();
	                    invaldGrpSeq =  statement.getString(1);
	                    util.logMsg(false, clsName+".getMtrInvGrpSeq", "invaldGrpSeq : " + invaldGrpSeq);	 
	                    
	                    String[] arrOfStr = invaldGrpSeq.split(",", -1); 
	                    for (String aseq : arrOfStr) {
	                        System.out.println("invaldGrpSq: "+  invaldGrpSq+" aseq: "+aseq);
	            	            if("null".equals(invaldGrpSq) || "".equals(invaldGrpSq)){
	            	            	invaldGrpSq =aseq;
	            	            }else{
	            	            	// System.out.println("invaldGrpSq: "+  invaldGrpSq+ "aseq: "+aseq);
	            	            	if(invaldGrpSq.contains(aseq)){
	            	            		//Do Nothing
	            	            	}else{
	            	            		invaldGrpSq=invaldGrpSq+","+aseq;
	            	            	}
	            	            }
	                        }
	                    System.out.println("Final invaldGrpSq: "+  invaldGrpSq);
	                    
   
	                    
	                    return invaldGrpSq;
	                }else{
	                	 util.logMsg(true,clsName+".getMtrInvGrpSeq", " Statement is Null");
	                } 
	             }else{
	            	 util.logMsg(true,clsName+".getMtrInvGrpSeq", " Connection is Null");
	             }
	        } 
	        catch(SQLException eSQLExp)
			{
	  		  System.out.println("SQL Exception is:" + eSQLExp.toString());
	  		}
	  		catch (Exception eExp)
	  		{
	  		  System.out.println("Exception is:" + eExp.toString());
	  			}
	  			finally
	  			{
	  				try
	  				{
	  					if(statement != null)
	  						statement.close();
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
	        return invaldGrpSq;
	    }	
}
