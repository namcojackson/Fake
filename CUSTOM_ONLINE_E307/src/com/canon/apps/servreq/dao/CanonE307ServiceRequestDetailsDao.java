package com.canon.apps.servreq.dao;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import canon.apps.common.CanonConstants;

import com.canon.apps.servreq.beans.CanonE307AttFileRec;
import com.canon.apps.servreq.beans.CanonE307BillCodeRec;
import com.canon.apps.servreq.beans.CanonE307CancelTaskRsnInfoRec;
import com.canon.apps.servreq.beans.CanonE307DebriefViewNotesRec;
import com.canon.apps.servreq.beans.CanonE307MachineCustSearchResultsRec;
import com.canon.apps.servreq.beans.CanonE307SRAuditLogRec;
import com.canon.apps.servreq.beans.CanonE307SRCntrDtlsRec;
import com.canon.apps.servreq.beans.CanonE307SRHistDownloadRec;
import com.canon.apps.servreq.beans.CanonE307SRHistHdrLvlInfoRec;
import com.canon.apps.servreq.beans.CanonE307SRHistoryNewRec;
import com.canon.apps.servreq.beans.CanonE307SRLovRec;
import com.canon.apps.servreq.beans.CanonE307SRTaskHistNewRec;
import com.canon.apps.servreq.beans.CanonE307SRViewTskDtlsRec;
import com.canon.apps.servreq.beans.CanonE307ServiceReqContractRec;
import com.canon.apps.servreq.beans.CanonE307ServiceReqInfoRec;
import com.canon.apps.servreq.beans.CanonE307SrHistLovRec;
import com.canon.apps.servreq.beans.CanonE307TaskCancelRsnRec;
import com.canon.apps.servreq.beans.CanonE307TaskDetailsRec;
import com.canon.apps.servreq.beans.CanonE307ServiceReqAssigneeRec;
import com.canon.apps.servreq.beans.CanonE307SRHistoryRec;
import com.canon.apps.servreq.beans.CanonE307TaskDtlInfoRec;
import com.canon.apps.servreq.beans.CanonE307TaskHistoryRec;
import com.canon.apps.servreq.beans.CanonE307TskTpeRec;
import com.canon.apps.servreq.util.CanonE307ServiceReqSumryAPIUtil;
import com.canon.common.CanonCommonUtil;


public class CanonE307ServiceRequestDetailsDao {

	CanonCommonUtil util;
	private   String clsName="CanonE307ServiceRequestDetailsDao";
	public CanonE307ServiceRequestDetailsDao() {
		util = new CanonCommonUtil();
	}
	public static final String GET_FSR_INFO = "{CALL CANON_E307_CREATE_SR_PKG.GET_CALL_SUMMARY(?,?,?,?,?,?,?,?)}";
	public static final String GET_RESOURCE_LOV = "{CALL CANON_E307_CREATE_SR_PKG.RESOURCE_LOV(?,?,?,?,?,?,?,?,?)}";
	public static final String GET_SR_HISTORY = "{CALL CANON_E307_CREATE_SR_PKG.GET_SR_HIST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";	
	public static final String GET_TASK_HISTORY = "{CALL CANON_E307_CREATE_SR_PKG.GET_TASK_HIST(?,?,?,?,?,?,?)}";		
	public static final String GET_SR_HISTORY_LOV = "{CALL CANON_E307_CREATE_SR_PKG.SR_HIST_LOV(?,?,?,?,?,?)}";
	public static final String GET_TASK_TYPE = "{CALL CANON_E307_CREATE_SR_PKG.TYPE_LOV(?)}";	
	public static final String GET_ATT_FILE = "{CALL CANON_E307_CALL_SUPPORT_PKG.GET_ATTACH_FILE_INFO(?,?,?,?,?)}";	
	public static final String GET_TASK_STS = "{CALL CANON_E307_CREATE_SR_PKG.GET_TASK_STATUS(?,?,?)}";	
	public static final String GET_UPLOAD_PATH = "{CALL CANON_E307_CALL_SUPPORT_PKG.GET_FILE_UPLOAD_PATH(?)}";	
	//public static final String GET_SCHEDULE_DATE = "{?= CALL CANON_E307_CREATE_SR_PKG.GET_SHCHEDULE_DATE(?)}";
	public static final String GET_TASK_DET = "{CALL CANON_E307_CREATE_SR_PKG.GET_TASK_DET(?,?,?,?,?,?,?,?,?)}";	
	public static final String GET_TECH_INFO = "{CALL CANON_E307_CREATE_SR_PKG.GET_TECH_INFO(?,?,?,?,?)}";
	public static final String GET_USER_ROLE = "{?= call CANON_E307_CALL_SUPPORT_PKG.GET_USER_EDIT_ROLE(?)}";
	public static final String GET_TASK_CNCL_RSN = "{call CANON_E307_CREATE_SR_PKG.TASK_CANCEL_RSN_LOV(?)}";
	public static final String GET_TASK_CNCL_INFO = "{call CANON_E307_CREATE_SR_PKG.GET_CANCEL_TASK_INFO(?,?)}";
	public static final String GET_SR_HISTORY_DWNLD = "{CALL CANON_E307_CREATE_SR_PKG.GET_SR_EXCEL_DWNLD(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	public static final String GET_CTR_DTLS = "{CALL CANON_E307_CREATE_SR_PKG.GET_CNTR_INFO_DWNLD(?,?,?,?)}";
	public static final String GET_CONTRACT_DTLS = "{CALL CANON_E307_CREATE_SR_PKG.GET_CONT_INFO_BY_CONTRPK(?,?,?,?,?,?)}";	
	public static final String GET_TASK_DTLS = "{CALL CANON_E307_CREATE_SR_PKG.GET_TASK_INFO(?,?)}";
	public static final String GET_SR_HISTORY_NEW = "{CALL CANON_E307_CREATE_SR_PKG.GET_SR_HIST_NEW(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";	
	public static final String GET_HDR_DET = "{CALL CANON_E307_CREATE_SR_PKG.GET_HDR_LVL_INFO(?,?)}";	
	public static final String GET_TASK_HIST_NEW = "{CALL CANON_E307_CREATE_SR_PKG.GET_TASK_HIST_NEW(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";	
//	public static final String GET_SR_TSK_HIST_DWNLD =  "{CALL CANON_E307_CREATE_SR_PKG.GET_TASK_HIST_NEW(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	public static final String GET_TASK_STS_LIST = "{CALL CANON_E307_CALL_SUPPORT_PKG.GET_TASK_STATUSES(?)}";	
	public static final String GET_SR_STS_LIST = "{CALL CANON_E307_CALL_SUPPORT_PKG.GET_SR_STATUSES(?)}";	
	public static final String GET_SR_TASK_DET = "{CALL CANON_E307_CREATE_SR_PKG.GET_TASK_DTL_INFO(?,?,?)}";
	public static final String GET_LST_TASK_UPD_FLG = "{?= call CANON_E307_CREATE_SR_PKG.GET_LAST_TSK_UPD_FLG(?,?)}";
	public static final String GET_IN_MTR_RD_FLG = "{?= call CANON_E307_CREATE_SR_PKG.GET_IN_READS_EXIST(?)}";
	public static final String GET_HOLIDAY_FLAG = "{?= call CANON_E307_CALL_SUPPORT_PKG.GET_HOLIDAY_FLAG(?)}";
	public static final String GET_FSR_REPORT_INFO = "{CALL CANON_E307_CALL_SUPPORT_PKG.GET_FSR_REPORT_INFO(?,?)}";	
	public static final String GET_DOWNLOAD_PATH = "{CALL CANON_E307_CALL_SUPPORT_PKG.GET_FILE_DOWNLOAD_PATH(?)}";	
	public static final String GET_AUDIT_INFO = "{CALL CANON_E307_CALL_SUPPORT_PKG.GET_AUDIT_LOG_INFO(?,?)}";	

	
	

	public Object[] getServiceRequestDetails(String strFSRNumber, String strTaskNumber){
		Object[] resObj = getServiceRequestDetails(strFSRNumber, strTaskNumber, "SUMMARY");
		return resObj;
	}

	public Object[] getServiceRequestDetails(String strFSRNumber, String strTaskNumber, String strSource){
	     CallableStatement cstmt = null;
	     Connection connection = null;
	     Object[] resObj = new Object[5];
	     try
	       {
	    //	 strFSRNumber="50001635";
			connection = TransactionScope.getConnection();
			List<Object> listMInfo = new ArrayList<Object>();
			List<Object> listSRInfo = new ArrayList<Object>();	
			List<Object> listVNotes = new ArrayList<Object>();	
			List<Object> listCont = new ArrayList<Object>();	
			List<Object> listTaskInfo = new ArrayList<Object>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_FSR_INFO);
			  if (cstmt != null) {
				  cstmt.setString(1, strFSRNumber);
				  cstmt.setString(2, strTaskNumber);
	              cstmt.registerOutParameter(3, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_MAC_SER_TBL");
	              cstmt.registerOutParameter(4, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_SR_INFO_TBL");
	              cstmt.registerOutParameter(5, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_CONTRACT_TBL");
	              cstmt.registerOutParameter(6, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_TASK_INFO_TBL");
	              cstmt.registerOutParameter(7, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_NOTE_TBL");
	              cstmt.setString(8, strSource);
	              
	              Map map = connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_MAC_SER_REC", CanonE307MachineCustSearchResultsRec.class);
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_SR_INFO_REC", CanonE307ServiceReqInfoRec.class);
	          //    map.put("S21_CSA_EXTN5.CANON_E307_CUST_HANDLING_REC", CanonE307CustomerHandlingRec.class);
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_CONTRACT_REC", CanonE307ServiceReqContractRec.class);
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_TASK_INFO_REC", CanonE307TaskDetailsRec.class);
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_NOTE_REC", CanonE307DebriefViewNotesRec.class);
	              
	              //Execute the statement
	              util.logMsg(true,clsName+".getServiceRequestDetails Before Execute", strFSRNumber);
	              cstmt.execute();
	              util.logMsg(true,clsName+".getServiceRequestDetails ", "After Execute");

	              Object ooMachInfo[] =(Object[]) ((Array)cstmt.getObject(3)).getArray();
	              Object ooSrInfo[] =(Object[]) ((Array)cstmt.getObject(4)).getArray();
	              Object ooContract[] =(Object[]) ((Array)cstmt.getObject(5)).getArray();
	              Object ooTaskInfo[] =(Object[]) ((Array)cstmt.getObject(6)).getArray();
	              Object ooNoteInfo[] =(Object[]) ((Array)cstmt.getObject(7)).getArray();

				  if(ooMachInfo!=null && ooMachInfo.length>0){
					  for (int i = 0; i < ooMachInfo.length; i++) {
						  listMInfo.add(ooMachInfo[i]);			       
					  }	
					  CanonE307MachineCustSearchResultsRec mcsrObj = (CanonE307MachineCustSearchResultsRec) listMInfo.get(0); 
					  resObj[0]=mcsrObj;
				  }
				 if(ooSrInfo!=null && ooSrInfo.length>0){
					  for (int i = 0; i < ooSrInfo.length; i++) {
						  listSRInfo.add(ooSrInfo[i]);			       
					  }
					  CanonE307ServiceReqInfoRec servObj = (CanonE307ServiceReqInfoRec) listSRInfo.get(0); 
					  resObj[1]=servObj;	
				 }
				  if(ooContract!=null && ooContract.length>0){
					  for (int i = 0; i < ooContract.length; i++) {
						  listCont.add(ooContract[i]);			       
					  }	
					  CanonE307ServiceReqContractRec contractObj = (CanonE307ServiceReqContractRec) listCont.get(0); 
					  resObj[2]=contractObj;	
				  }
				  if(ooTaskInfo!=null && ooTaskInfo.length>0){
					  for (int i = 0; i < ooTaskInfo.length; i++) {
						  listTaskInfo.add(ooTaskInfo[i]);			       
					  }
					  resObj[3]=listTaskInfo;
				  }
				  if(ooNoteInfo!=null && ooNoteInfo.length>0){
					  for (int i = 0; i < ooNoteInfo.length; i++) {
						  listVNotes.add(ooNoteInfo[i]);			       
					  }	
					  resObj[4]=listVNotes;
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
     return resObj;
	}
	public  Object[] getAssigneeDet(String strAsgnName, int start, int end, String strSortBy, String strSortOrder, String strSource, String strBranch){
		 CallableStatement cstmt = null;
	     Connection connection = null;
	     
	     Object[] resObj = new Object[2];
	     
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_RESOURCE_LOV);
			  if (cstmt != null) {
				  cstmt.setString(1,strAsgnName);
				  cstmt.setString(2, strSource);
	              cstmt.setInt(3, start);
	              cstmt.setInt(4, end);
	              cstmt.setString(5, strSortBy);
	              cstmt.setString(6, strSortOrder);
	              cstmt.setString(7, strBranch);
				 
	              cstmt.registerOutParameter(8, OracleTypes.NUMBER);
	              cstmt.registerOutParameter(9, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_RES_LOV_TBL");
	              
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_RES_LOV_REC", CanonE307ServiceReqAssigneeRec.class);
	              cstmt.execute();
	              int totRecords = cstmt.getInt(8);

	              resObj[0]=totRecords;
	              
	              Object[] oo =(Object[]) ((Array)cstmt.getObject(9)).getArray();
        		  List<CanonE307ServiceReqAssigneeRec> assignList = new ArrayList<CanonE307ServiceReqAssigneeRec>();
        		 	             
        		  if(oo!=null && oo.length>0){
					  for (int k = 0; k < oo.length; k++) {
						  assignList.add((CanonE307ServiceReqAssigneeRec)oo[k]);		
				         }	
      		       }  
        		  resObj[1]=assignList; 
              
			  }else {
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
		  return resObj;		
		}
	public  Object[] getSrHistoryNew(String strSerial, String strTagNum, String strSolName, String strModel, String strAcctNum, String strCustName, int start, int end, String strSortBy, String strSortOrder, String strSRNum, String strTskNum, String strCreatedBy, String strCreationDt, String strSrSts, String strTaskSts, String strTasktype){
		 CallableStatement cstmt = null;
	     Connection connection = null;
         List<CanonE307SRHistoryNewRec> srList = new ArrayList<CanonE307SRHistoryNewRec>();	 
         System.out.println("Inside SR history strSortBy.."+ strSortBy+" strSortOrder: "+strSortOrder+" start: "+start+" end: "+end);
         Object[] histObj = new Object[2];
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_SR_HISTORY_NEW);
			  if (cstmt != null) {
				  cstmt.setString(1, strSRNum);
				  cstmt.setString(2, strTskNum);
				  cstmt.setString(3, strSerial);
				  cstmt.setString(4, strTagNum);
	              cstmt.setString(5, strSolName);
	              cstmt.setString(6, strModel);
	              cstmt.setString(7, strAcctNum);
	              cstmt.setString(8, strCustName);
	              cstmt.setInt(9, start);
	              cstmt.setInt(10, end);
	              cstmt.setString(11,strSortBy);
	              cstmt.setString(12, strSortOrder);
	              cstmt.setString(13, strCreatedBy);
	              cstmt.setString(14, strCreationDt);
	              cstmt.setString(15, strSrSts);
	              cstmt.setString(16, strTaskSts);
	              cstmt.setString(17, strTasktype);
	              cstmt.registerOutParameter(18, OracleTypes.NUMBER);
	              cstmt.registerOutParameter(19, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_SR_HIST_NEW_TBL");
	              
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_SR_HIST_NEW_REC", CanonE307SRHistoryNewRec.class);
	              util.logMsg(false,clsName+".getSrHistory Before Excecute", "strSerial : "+ strSerial+" strTagNum : "+strTagNum+" strCreatedBy : "+strCreatedBy+"strCreationDt : "+strCreationDt);		          
	              cstmt.execute();
	              util.logMsg(false,clsName+".getSrHistory", "After Excecute");		          
	              int totRecords = cstmt.getInt(18);
	              util.logMsg(false,clsName+".getSrHistory", "totRecords: "+ totRecords);	
	              histObj[0]=totRecords;
	              Object[] oo =(Object[]) ((Array)cstmt.getObject(19)).getArray();

	              if(oo!=null && oo.length>0){
	            	  util.logMsg(false,clsName+".getSrHistory", " oo.length: "+  oo.length);	
					  for (int k = 0; k < oo.length; k++) {
						  srList.add((CanonE307SRHistoryNewRec)oo[k]);		
				         }	
		              histObj[1]=srList; 
     		       }  
			  }else {
				  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
	          }
		  } else {
			  System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	      }
	     } catch (Exception ex) {
	    	 ex.printStackTrace();
	    	 System.err.println("Inside catch ");	    	 
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
		return histObj;		
	}	
	public  Object[] getTaskHistory(String strSRNum, int start, int end, String strSortBy, String strSortOrder){
		CallableStatement cstmt = null;
	    Connection connection = null;
        List<CanonE307TaskHistoryRec> srList = new ArrayList<CanonE307TaskHistoryRec>();	 
        Object[] histObj = new Object[2];
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_TASK_HISTORY);
			  if (cstmt != null) {
				  cstmt.setString(1, strSRNum);
	              cstmt.setInt(2, start);
	              cstmt.setInt(3, end);
	              cstmt.setString(4,strSortBy);
	              cstmt.setString(5, strSortOrder);
	              cstmt.registerOutParameter(6, OracleTypes.NUMBER);
	              cstmt.registerOutParameter(7, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_TSK_HIST_TBL");
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_TSK_HIST_REC", CanonE307TaskHistoryRec.class);
	              util.logMsg(true,clsName+".getTaskHistory Before Excecute", "strSRNum : "+ strSRNum);	
	              cstmt.execute();
	              util.logMsg(true,clsName+".getTaskHistory", "After execute");	              
	              int totRecords = cstmt.getInt(6);
	              histObj[0]=totRecords;
	              Object[] oo =(Object[]) ((Array)cstmt.getObject(7)).getArray();

	              if(oo!=null && oo.length>0){
					  for (int k = 0; k < oo.length; k++) {
						  srList.add((CanonE307TaskHistoryRec)oo[k]);		
				      }	
    		       }  
	              histObj[1]=srList; 
			  }else {
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
		  return histObj;		
		}	
	public  Object[] getSrHistLov(String strSource, String strVal, int start, int end, String strSortBy, String strSortOrder){
		CallableStatement cstmt = null;
	    Connection connection = null;
        List srList = new ArrayList();	 
        Object[] histLovObj = new Object[2];
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_SR_HISTORY_LOV);
			  if (cstmt != null) {
				  cstmt.setString(1, strSource);
				  cstmt.setString(2, strVal);
	              cstmt.setInt(3, start);
	              cstmt.setInt(4, end);
	          //    cstmt.setString(5, strSortBy);
	          //    cstmt.setString(6, strSortOrder);
	              cstmt.registerOutParameter(5, OracleTypes.NUMBER);
	              cstmt.registerOutParameter(6, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_LOV_VAL_TBL");
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_LOV_VAL_REC", CanonE307SrHistLovRec.class);
	              util.logMsg(true,clsName+".getSrHistLov Before Excecute", "strSource : "+ strSource);	              
	              cstmt.execute();
	              util.logMsg(true,clsName+".getSrHistLov", "After Execute");
	              int totRecords = cstmt.getInt(5);
	              histLovObj[0]=totRecords;
	              Object[] oo =(Object[]) ((Array)cstmt.getObject(6)).getArray();

	              if(oo!=null && oo.length>0){
					  for (int k = 0; k < oo.length; k++) {
						  srList.add((CanonE307SrHistLovRec)oo[k]);		
				         }	
    		       }  
	              histLovObj[1]=srList; 
			  }else {
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
		  return histLovObj;		
		}
	public List<Object> getTaskType(){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     try
	       {
			connection = TransactionScope.getConnection();
			List<Object> list = new ArrayList<Object>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_TASK_TYPE);
			  if (cstmt != null) {
				  // Set the parameters on the statement
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_TYPE_LOV_REC", CanonE307TskTpeRec.class);
	              cstmt.registerOutParameter(1, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_TYPE_LOV_TBL");
				  // Execute the statement
	              cstmt.execute();
	              
	              Object oo[] =(Object[]) ((Array) cstmt.getObject(1)).getArray();
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
	public ArrayList<CanonE307AttFileRec> getAttFileIds(String strFsrNumber, String strSerNum, String strFileNm){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     try
	       {
			connection = TransactionScope.getConnection();
			ArrayList<CanonE307AttFileRec> list = new ArrayList<CanonE307AttFileRec>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_ATT_FILE);
			  if (cstmt != null) {
				      cstmt.setString(1, strFsrNumber);
					  cstmt.setString(2, strSerNum);
					  cstmt.setString(3, strFileNm);
					  cstmt.setString(4, "Y");
				  // Set the parameters on the statement
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_ATT_FILE_REC", CanonE307AttFileRec.class);
	              cstmt.registerOutParameter(5, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_ATT_FILE_TBL");
				  // Execute the statement
	              util.logMsg(true,clsName+".getAttFileIds Before Execute", "strFsrNumber: "+ strFsrNumber+" strSerNum : "+strSerNum);	              
	              cstmt.execute();
	              util.logMsg(true,clsName+".getAttFileIds", "After Execute");
	              Object oo[] =(Object[]) ((Array) cstmt.getObject(5)).getArray();
				  if(oo!=null && oo.length>0){
					  for (int i = 0; i < oo.length; i++) {
						   list.add((CanonE307AttFileRec)oo[i]);
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
	public List getTaskStsByTrnstn(String strSts, String userId){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     try
	       {
			connection = TransactionScope.getConnection();
			List<Object> list = new ArrayList<Object>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_TASK_STS);
			  if (cstmt != null) {
				  // Set the parameters on the statement
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_TYPE_LOV_REC", CanonE307TskTpeRec.class);
	              cstmt.setString(1, strSts);
	              cstmt.setString(2, userId);
	              cstmt.registerOutParameter(3, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_TYPE_LOV_TBL");
				  // Execute the statement
	              util.logMsg(true,clsName+".getTaskStsByTrnstn before execute", "strSts : "+ strSts);
	              cstmt.execute();
	              
			//	  Object[] oo = (Object[]) cstmt.getARRAY(1).getArray();
	              Object oo[] =(Object[]) ((Array) cstmt.getObject(3)).getArray();
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
	  public String getUploadFilePath(){
			
			String res="";
			ResultSet rs = null;
			Connection connection = null;
			CallableStatement cstmt = null;
			try {
				connection = TransactionScope.getConnection();
				cstmt = (CallableStatement)connection.prepareCall(GET_UPLOAD_PATH);
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
/*	   public String getScheduleDate(String strFsr) throws Exception{
	        CallableStatement statement = null;
	        Connection connection = null;
	        String scheduleDate = "";
	        try {
	        	connection = TransactionScope.getConnection();
	 		   String plsqlExp =  " Begin \n "
                       + " :1 := CANON_E307_CREATE_SR_PKG.GET_TASK_DET(:2) ; \n "
                       + " End;";
	            if (connection != null) {
	                	statement = (CallableStatement) connection.prepareCall(plsqlExp);
	                	statement.setString(2, strFsr);
	                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
	                    System.out.println("Before Execute"+strFsr);
	                    statement.execute();
	                    System.out.println("After Execute");
	                    scheduleDate =  statement.getString(1);
	                    util.logMsg(false,clsName+".getScheduleDate ", " scheduleDate : "+ scheduleDate);
	                    return scheduleDate;
	            } else {
	            	util.logMsg(true,clsName+".getScheduleDate ", "DBStatus.UNABLE_TO_GET_CONNECTION");
	            }
	        } catch (Exception ex) {
	        	util.logMsg(true,clsName+".getScheduleDate ", ex.getMessage());
			} finally {
				try {
					if (statement != null)
						statement.close();
					if (connection != null)
						TransactionScope.releaseConnection(connection);
				} catch (Exception ex2) {
					util.logMsg(true,clsName+".getScheduleDate", ex2.getMessage());
				}
			}
	        return scheduleDate;
	    }*/
		public String[] getTskDtls(String strFsrNum, String taskNum){
			String[] retFlag=new String[7];
			Connection connection = null;
			CallableStatement cstmt  = null;
			try
			{
				  connection = TransactionScope.getConnection();
				  if(connection!=null)
				  cstmt = connection.prepareCall(GET_TASK_DET);
				  cstmt.setString(1, strFsrNum);
				  cstmt.setString(2, taskNum);
				  cstmt.registerOutParameter(3,Types.VARCHAR);
				  cstmt.registerOutParameter(4,Types.VARCHAR);
				  cstmt.registerOutParameter(5,Types.VARCHAR);
				  cstmt.registerOutParameter(6,Types.VARCHAR);
				  cstmt.registerOutParameter(7,Types.VARCHAR);
				  cstmt.registerOutParameter(8,Types.VARCHAR);
				  cstmt.registerOutParameter(9,Types.VARCHAR);
				  cstmt.execute();
				  
	              util.logMsg(false,clsName+".getTskDtls After Execute", " strFsrNum : "+ strFsrNum+" Task Status: "+cstmt.getString(3)+"taskNum : "+taskNum);

				  retFlag[0]= util.checkStrNull(cstmt.getString(3));
				  retFlag[1]= util.checkStrNull(cstmt.getString(4));
				  retFlag[2]= util.checkStrNull(cstmt.getString(5));
				  retFlag[3]= util.checkStrNull(cstmt.getString(6));
				  retFlag[4]= util.checkStrNull(cstmt.getString(7));
				  retFlag[5]= util.checkStrNull(cstmt.getString(8));
				  retFlag[6]= util.checkStrNull(cstmt.getString(9));
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
		public String[] getTechDtls(String strmachMstrPk){
			String[] retFlag=new String[4];
			Connection connection = null;
			CallableStatement cstmt  = null;
			try
			{
				  connection = TransactionScope.getConnection();
				  if(connection!=null)
				  cstmt = connection.prepareCall(GET_TECH_INFO);
				  cstmt.setString(1, strmachMstrPk);
				  cstmt.registerOutParameter(2,Types.VARCHAR);
				  cstmt.registerOutParameter(3,Types.VARCHAR);
				  cstmt.registerOutParameter(4,Types.VARCHAR);
				  cstmt.registerOutParameter(5,Types.VARCHAR);
				  cstmt.execute();
				  
	              util.logMsg(true,clsName+".getTechDtls After Execute", " strSerial : "+ strmachMstrPk);

				  retFlag[0]= util.checkNull(cstmt.getString(2));
				  retFlag[1]= util.checkNull(cstmt.getString(3));
				  retFlag[2]= util.checkNull(cstmt.getString(4));
				  retFlag[3]= util.checkNull(cstmt.getString(5));
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
	    public String getUserEditRole(String struserName){
	        util.logMsg(false, clsName+".getUserEditRole", "struserName : " + struserName);	        
	        CallableStatement statement = null;
	        Connection connection = null;
	        String roleNm = "";
	        try {
	        	 connection = TransactionScope.getConnection();
	             if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(GET_USER_ROLE);
	                if (statement != null) {
	                    statement.setObject(2, struserName, OracleTypes.VARCHAR);
	                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
	                    statement.execute();
	                    roleNm =  statement.getString(1);
	                    util.logMsg(false, clsName+".getUserEditRole", "roleNm : " + roleNm);	 
	                    return roleNm;
	                }else{
	                	 util.logMsg(true,clsName+".getUserEditRole", " Statement is Null");
	                } 
	             }else{
	            	 util.logMsg(true,clsName+".getUserEditRole", " Connection is Null");
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
	        return roleNm;
	    }			
	    public  ArrayList<CanonE307TaskCancelRsnRec> getTaskCancelRsns(){
			ArrayList<CanonE307TaskCancelRsnRec> arList = new ArrayList<CanonE307TaskCancelRsnRec>();
			ResultSet rs = null;
			Connection connection = null;
			CallableStatement cstmt = null;
	
			try {
				connection = TransactionScope.getConnection();
				cstmt = (CallableStatement)connection.prepareCall(GET_TASK_CNCL_RSN);
				cstmt.registerOutParameter(1, OracleTypes.ARRAY,CanonConstants.SCHEMA_NAME+".CANON_E307_TYPE_LOV_TBL");
				Map map = (Map) connection.getTypeMap();
	            map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_TYPE_LOV_REC", CanonE307TaskCancelRsnRec.class);
	            util.logMsg(false,clsName+".getTaskCancelRsns ", "before execute");
				cstmt.execute();
				util.logMsg(false,clsName+".getTaskCancelRsns ", "after execute");
				Object oo[] =(Object[]) ((Array) cstmt.getObject(1)).getArray();
				   if(oo!=null && oo.length>0){
						  for (int k = 0; k < oo.length; k++) {
							  CanonE307TaskCancelRsnRec rec=  (CanonE307TaskCancelRsnRec)oo[k] ;
							  arList.add( rec);		
					       }	
	     		   }  
				} catch (Exception ex1) {
					util.logMsg(true,clsName+".getTaskCancelRsns", ex1.getMessage());
				} finally {
					try {
						if (rs != null)
							rs.close();
						if (cstmt != null)
							cstmt.close();
						if (connection != null)
							TransactionScope.releaseConnection(connection);
					} catch (Exception ex2) {
						util.logMsg(true,clsName+".getTaskCancelRsns", ex2.getMessage());
					}
				}
				return arList;
		}	
	    
	    public  ArrayList<CanonE307CancelTaskRsnInfoRec> getTaskCancelRsnsInfo(String fsrNumber){
			ArrayList<CanonE307CancelTaskRsnInfoRec> arList = new ArrayList<CanonE307CancelTaskRsnInfoRec>();
			ResultSet rs = null;
			Connection connection = null;
			CallableStatement cstmt = null;
	
			try {
				connection = TransactionScope.getConnection();
				cstmt = (CallableStatement)connection.prepareCall(GET_TASK_CNCL_INFO);
				Map map = (Map) connection.getTypeMap();
	            map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_TASK_RSN_INFO_REC", CanonE307CancelTaskRsnInfoRec.class);
	            util.logMsg(false,clsName+".getTaskCancelRsnsInfo ", "before execute");
	            cstmt.setObject(1, fsrNumber, OracleTypes.VARCHAR);
				cstmt.registerOutParameter(2, OracleTypes.ARRAY,CanonConstants.SCHEMA_NAME+".CANON_E307_TASK_RSN_INFO_TBL");	
				cstmt.execute();
				util.logMsg(false,clsName+".getTaskCancelRsnsInfo ", "after execute");
				Object oo[] =(Object[]) ((Array) cstmt.getObject(2)).getArray();
				   if(oo!=null && oo.length>0){
						  for (int k = 0; k < oo.length; k++) {
							  CanonE307CancelTaskRsnInfoRec rec=  (CanonE307CancelTaskRsnInfoRec)oo[k] ;
							  arList.add( rec);		
					       }	
	     		   }  
				} catch (Exception ex1) {
					util.logMsg(true,clsName+".getTaskCancelRsnsInfo ", ex1.getMessage());
				} finally {
					try {
						if (rs != null)
							rs.close();
						if (cstmt != null)
							cstmt.close();
						if (connection != null)
							TransactionScope.releaseConnection(connection);
					} catch (Exception ex2) {
						util.logMsg(true,clsName+".getTaskCancelRsnsInfo ", ex2.getMessage());
					}
				}
				return arList;
		}	
		public  Object[] getSrHistoryDownload1(String strSerial, String strTagNum, String strSolName, String strModel, String strAcctNum, String strCustName, String strSortBy, String strSortOrder, String strSRNum, String strTskNum, String strCreatedBy, String strCreationDt){
			 CallableStatement cstmt = null;
		     Connection connection = null;
	         List<CanonE307SRHistDownloadRec> srList = new ArrayList<CanonE307SRHistDownloadRec>();	 
	         Object[] histObj = new Object[2];
		     try
		       {
				connection = TransactionScope.getConnection();
				if (connection != null) {
					cstmt = connection.prepareCall(GET_SR_HISTORY_DWNLD);
				  if (cstmt != null) {
					  cstmt.setString(1, strSRNum);
					  cstmt.setString(2, strTskNum);
					  cstmt.setString(3, strSerial);
					  cstmt.setString(4, strTagNum);
		              cstmt.setString(5, strSolName);
		              cstmt.setString(6, strModel);
		              cstmt.setString(7, strAcctNum);
		              cstmt.setString(8, strCustName);
		              cstmt.setString(9,strSortBy);
		              cstmt.setString(10, strSortOrder);
		              cstmt.setString(11, strCreatedBy);
		              cstmt.setString(12, strCreationDt);
		              cstmt.registerOutParameter(13, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_SR_HIST_DWNLD_TBL");
		              
		              Map map = (Map) connection.getTypeMap();
		              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_SR_HIST_DWNLD_REC", CanonE307SRHistDownloadRec.class);
		              util.logMsg(false,clsName+".getSrHistoryDownload Before Excecute", "strSerial : "+ strSerial+" strTagNum : "+strTagNum+" strCreatedBy : "+strCreatedBy+"strCreationDt : "+strCreationDt);		          
		              cstmt.execute();
		              util.logMsg(false,clsName+".getSrHistoryDownload", "After Excecute");		          

		              Object[] oo =(Object[]) ((Array)cstmt.getObject(13)).getArray();

		              if(oo!=null && oo.length>0){
						  for (int k = 0; k < oo.length; k++) {
							  srList.add((CanonE307SRHistDownloadRec)oo[k]);		
					         }	
			              histObj[0]=srList; 
	     		       }  
				  }else {
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
			return histObj;		
		}		
		public List getSrHistoryDownload(String strSerial, String strTagNum, String strSolName, String strModel, String strAcctNum, String strCustName, String strSortBy, String strSortOrder, String strSRNum, String strTskNum, String strCreatedBy, String strCreationDt){
			 CallableStatement cstmt = null;
		     Connection connection = null;
			 ResultSet rs = null;
	         List<CanonE307SRHistDownloadRec> srList = new ArrayList<CanonE307SRHistDownloadRec>();	 
//	         Object[] histObj = new Object[2];
	         CanonE307SRHistDownloadRec dwnldObj = new CanonE307SRHistDownloadRec();
		     try
		       {
		       System.out.println("Inside download..");	 
				connection = TransactionScope.getConnection();
				if (connection != null) {
					cstmt = connection.prepareCall(GET_SR_HISTORY_DWNLD);
				  if (cstmt != null) {
					  cstmt.setString(1, strSRNum);
					  cstmt.setString(2, strTskNum);
					  cstmt.setString(3, strSerial);
					  cstmt.setString(4, strTagNum);
		              cstmt.setString(5, strSolName);
		              cstmt.setString(6, strModel);
		              cstmt.setString(7, strAcctNum);
		              cstmt.setString(8, strCustName);
		              cstmt.setString(9,strSortBy);
		              cstmt.setString(10, strSortOrder);
		              cstmt.setString(11, strCreatedBy);
		              cstmt.setString(12, strCreationDt);
		              cstmt.registerOutParameter(13, OracleTypes.CURSOR);
		              
		              util.logMsg(false,clsName+".getSrHistoryDownload 1 Before Excecute", "strSerial : "+ strSerial+" strTagNum : "+strTagNum+" strCreatedBy : "+strCreatedBy+"strCreationDt : "+strCreationDt);		          
		              cstmt.execute();
		              util.logMsg(false,clsName+".getSrHistoryDownload 1", "After Execute");		          

		              rs = (ResultSet) cstmt.getObject(13);	
		              System.out.println("After RS");	 
		              while (rs.next()) {
		            	  CanonE307ServiceReqSumryAPIUtil utilObj = new CanonE307ServiceReqSumryAPIUtil();		            	  
		            	  dwnldObj = new CanonE307SRHistDownloadRec();
		            	  String fsrNum = rs.getString("FSR");
		            	  String svcTaskNum = rs.getString("TASK_NUMBER");
		            	  String tskCrtnDt = rs.getString("TASK_CREATION_DATE");
		            	  String tskEndDt = rs.getString("ACTUAL_END_DATE");
		            	  String postalCd = rs.getString("POSTAL_CODE");
		            	  String cntryCd =  rs.getString("COUNTRY_CODE");
		            	 // System.out.println("postalCd: "+postalCd+" cntryCd: "+cntryCd+" tskCrtnDt: "+tskCrtnDt);
		            	  String tskConvDt = util.checkNull(utilObj.getTmZone(postalCd, cntryCd, tskCrtnDt));
		            	  tskEndDt = util.checkNull(utilObj.getTmZone(postalCd, cntryCd, tskEndDt));
		            	//  System.out.println("tskConvDt: "+ tskConvDt +" postalCd: "+postalCd+" cntryCd: "+cntryCd+" tskEndDt: "+tskEndDt);
		            	  dwnldObj.setStrFSR(rs.getString("FSR"));
		            	  dwnldObj.setStrTaskNumber(rs.getString("TASK_NUMBER"));
		            	  dwnldObj.setStrTskCrtnDt(tskConvDt);
		            	  dwnldObj.setStrSerNum(rs.getString("SER_NUM"));
		            	  dwnldObj.setStrTskTpe(rs.getString("TASK_TYPE"));
		            	  dwnldObj.setStrActualEndDt(tskEndDt);
		            	  dwnldObj.setStrAsgnNm(rs.getString("ASSIGNEE_NAME"));
		            	  dwnldObj.setStrTotRd(rs.getString("TOTAL_READ"));
		            	  dwnldObj.setStrBWRd(rs.getString("BW_READ"));
		            	  dwnldObj.setStrClrRd(rs.getString("COLOR_READ"));
		            	  dwnldObj.setStrRspTm(rs.getString("RESP_TM"));
		            	  dwnldObj.setStrRstrTm(rs.getString("RESTR_TM"));
		            	  dwnldObj.setStrInstlCompNm(rs.getString("INST_COMP_NM"));
		            	  dwnldObj.setStrPblmTpCd(rs.getString("SVC_PBLM_TP_CD"));
		            	  dwnldObj.setStrTskSts(rs.getString("TASK_STS"));
		            	  dwnldObj.setStrMachMngr(rs.getString("MACHINE_MANAGER"));
		            	  dwnldObj.setStrMaterialChrg(rs.getString("MATERIAL_CHARGE"));
		            	  dwnldObj.setStrLabrChrg(rs.getString("LABOR_CHARGE"));
		            	  dwnldObj.setStrTechNotes(rs.getString("TECH_NOTES"));
		            	  dwnldObj.setStrModNum(rs.getString("MOD_PERFORM"));
		            	  dwnldObj.setStrContNumbr(rs.getString("CONTRACT_NUMBER"));
		            	  dwnldObj.setStrContSts(rs.getString("CONTRACT_STATUS"));
		            	  dwnldObj.setStrAveMonthlyVol(rs.getString("AVE_MONTHLY_VOL"));
		            	  dwnldObj.setStrCntrFlg(rs.getString("COUNTER_FLG"));
		            	  String cntrFlg = rs.getString("COUNTER_FLG");
		            	  
		            	  if("Y".equals(cntrFlg)){
		            		  //Counter List
		            		  String svcmachMstrPk = rs.getString("SVC_MACH_MSTR_PK");
		            	//	  System.out.println("svcmachMstrPk: "+ svcmachMstrPk);
		            		  dwnldObj.setCounterLst(getCntrDtlsDwnld(svcmachMstrPk, fsrNum, svcTaskNum, connection));
		            		//  System.out.println("After setCounterLst: ");
		            	  }
		            	  util.logMsg(false,clsName+".getSrHistoryDownload fsrNum: ", fsrNum+" cntrFlg: "+cntrFlg);	
		            	  srList.add(dwnldObj);
		              }
		            //  histObj[0]=srList;
				  }else {
					  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
		          }
			  } else {
				  System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
			  }
		     } catch (Exception ex) {
		    	 ex.printStackTrace();
				  util.logMsg(true,clsName+".getSrHistoryDownload : ", ex.toString());	
		     } finally {
		    	 if (cstmt != null) {
		    		 try {
		    			 cstmt.close();
		    			 cstmt = null;
		    		 } catch (Exception exp) {
		    			 exp.printStackTrace();
		    		 }
		    	 }
				 if (rs != null){
		    		 try {
		    			 rs.close();
		    			 rs = null;
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
     return srList;	
	}
		public List getCntrDtlsDwnld(String strSvcMchMstrPk, String fsrNum, String svcTaskNum, Connection connection){
			 CallableStatement cstmt = null;
		 //    Connection connection = null;
			 ResultSet rs = null;
			 CanonE307SRCntrDtlsRec ctrObj = new CanonE307SRCntrDtlsRec();
			 List<CanonE307SRCntrDtlsRec> ctrLst = new ArrayList<CanonE307SRCntrDtlsRec>();	 
			 util.logMsg(false,clsName+".getCntrDtlsDwnld Before Excecute", "strSvcMchMstrPk : "+ strSvcMchMstrPk+" fsrNum: "+fsrNum+" svcTaskNum: "+svcTaskNum);	
		     try
		       {
				if (connection != null) {
					cstmt = connection.prepareCall(GET_CTR_DTLS);
				  if (cstmt != null) {
		              cstmt.setString(1, strSvcMchMstrPk);
		              cstmt.setString(2, fsrNum);
		              cstmt.setString(3, svcTaskNum);
		              cstmt.registerOutParameter(4, OracleTypes.CURSOR);
		              
		              util.logMsg(false,clsName+".getCntrDtlsDwnld Before Excecute", "strSvcMchMstrPk : "+ strSvcMchMstrPk);		          
		              cstmt.execute();
		              util.logMsg(false,clsName+".getCntrDtlsDwnld ", "After Execute");		          

		              rs = (ResultSet) cstmt.getObject(4);	
		              while (rs.next()) {
		            	  ctrObj = new CanonE307SRCntrDtlsRec();
		            	  String mtrNm = rs.getString("MDL_MTR_LB_NOTE_TXT");
		            	  ctrObj.setCtrRd(rs.getDouble("READ_MTR_CNT"));
		            	  ctrObj.setCtrName(rs.getString("MDL_MTR_LB_NOTE_TXT"));
		            	  ctrObj.setReadDate(rs.getString("MTR_READ_DT"));
		            	  ctrObj.setAvgMonthlyCpVol(rs.getDouble("AVG_MLY_COPY_VOL_CNT"));
		            	  util.logMsg(false,clsName+".getCntrDtlsDwnld ", "mtrNm: "+ mtrNm);		
	            	  ctrLst.add(ctrObj);
		              }
		            //  histObj[0]=srList;
				  }else {
					  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
		          }
			  } else {
				  System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
			  }
		     } catch (Exception ex) {
		    	 ex.printStackTrace();
				  util.logMsg(true,clsName+".getCntrDtlsDwnld fsrNum: ", ex.toString());	
		     } finally {
		    	 if (cstmt != null) {
		    		 try {
		    			 cstmt.close();
		    			 cstmt = null;
		    		 } catch (Exception exp) {
		    			 exp.printStackTrace();
		    		 }
		    	 }
				 if (rs != null){
		    		 try {
		    			 rs.close();
		    			 rs = null;
		    		 } catch (Exception exp) {
		    			 exp.printStackTrace();
		    		 }					 
		    	 }
		}	
    return ctrLst;	
	}	
	public Object[] getContDetails(String svcMachMstrPk, String acctNum, String dsContrPk){
		ResultSet rs = null;
		Connection connection = null;
		CallableStatement cstmt = null;
		CanonE307ServiceReqContractRec contObj=null;
		String srvcHrs = "";
		Object[] contObjs = new Object[2];
		try {
			util.logMsg(false,clsName+".getContDetails ", "Inside getContDetails");
			connection = TransactionScope.getConnection();
			cstmt = (CallableStatement)connection.prepareCall(GET_CONTRACT_DTLS);
			cstmt.setString(1, svcMachMstrPk);
			cstmt.setString(2, acctNum);
			cstmt.setString(3, dsContrPk);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, OracleTypes.ARRAY,CanonConstants.SCHEMA_NAME+".CANON_E307_CONTRACT_TBL");
			cstmt.registerOutParameter(6, Types.VARCHAR);
			Map map = (Map) connection.getTypeMap();
            map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_CONTRACT_REC", CanonE307ServiceReqContractRec.class);
            util.logMsg(false,clsName+".getContDetails ", "before execute");
			cstmt.execute();
			util.logMsg(false,clsName+".getContDetails ", "after execute");
			srvcHrs = cstmt.getString(6);
			Object oo[] =(Object[]) ((Array) cstmt.getObject(5)).getArray();
			   if(oo!=null && oo.length>0){
				   util.logMsg(false,clsName+".getContDetails ", "oo.length: "+ oo.length);
					  for (int k = 0; k < oo.length; k++) {
						  contObj =  (CanonE307ServiceReqContractRec)oo[k] ;
				       }	
     		   }
			   contObjs[0] = srvcHrs;
			   contObjs[1] = contObj;
			   
			} catch (Exception ex1) {
				util.logMsg(true,clsName+".getContDetails", ex1.getMessage());
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (cstmt != null)
						cstmt.close();
					if (connection != null)
						TransactionScope.releaseConnection(connection);
				} catch (Exception ex2) {
					util.logMsg(true,clsName+".getContDetails", ex2.getMessage());
				}
			}
			return contObjs;		
	}
	public  List getTaskDet(String strServReqNum){
		 CallableStatement cstmt = null;
	     Connection connection = null;
	     System.out.println(" strServReqNum in getTaskDet : "+strServReqNum);
	     List<CanonE307SRViewTskDtlsRec> tskList = new ArrayList<CanonE307SRViewTskDtlsRec>();
	     
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_TASK_DTLS);
			  if (cstmt != null) {
				  cstmt.setString(1,strServReqNum);
	              cstmt.registerOutParameter(2, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_SRVIEW_TSK_DTLS_TBL");
	              
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_SRVIEW_TSK_DTLS_REC", CanonE307SRViewTskDtlsRec.class);
	              System.out.println(" getTaskDet before execute : ");    
	              cstmt.execute();

	              Object[] oo =(Object[]) ((Array)cstmt.getObject(2)).getArray();
	              System.out.println(" getTaskDet after execute : ");          
	              if(oo!=null && oo.length>0){
	            	  util.logMsg(false,clsName+".getTaskDet", " oo.length: "+  oo.length);	
					  for (int k = 0; k < oo.length; k++) {
						  tskList.add((CanonE307SRViewTskDtlsRec)oo[k]);		
				         }	
     		       }  

			  }else {
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
		  return tskList;		
		}	
	public  Object[] getSrHistory(String strSerial, String strTagNum, String strSolName, String strModel, String strAcctNum, String strCustName, int start, int end, String strSortBy, String strSortOrder, String strSRNum, String strTskNum, String strCreatedBy, String strCreationDt, String taskType){
		 CallableStatement cstmt = null;
	     Connection connection = null;
        List<CanonE307SRHistoryRec> srList = new ArrayList<CanonE307SRHistoryRec>();	 
        Object[] histObj = new Object[2];
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_SR_HISTORY);
			  if (cstmt != null) {
				  cstmt.setString(1, strSRNum);
				  cstmt.setString(2, strTskNum);
				  cstmt.setString(3, strSerial);
				  cstmt.setString(4, strTagNum);
	              cstmt.setString(5, strSolName);
	              cstmt.setString(6, strModel);
	              cstmt.setString(7, strAcctNum);
	              cstmt.setString(8, strCustName);
	              cstmt.setInt(9, start);
	              cstmt.setInt(10, end);
	              cstmt.setString(11,strSortBy);
	              cstmt.setString(12, strSortOrder);
	              cstmt.setString(13, strCreatedBy);
	              cstmt.setString(14, strCreationDt);
	              cstmt.setString(15, taskType);
	              cstmt.registerOutParameter(16, OracleTypes.NUMBER);
	              cstmt.registerOutParameter(17, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_SR_HIST_TBL");
	              
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_SR_HIST_REC", CanonE307SRHistoryRec.class);
	              util.logMsg(true,clsName+".getSrHistory Before Excecute", "strSerial : "+ strSerial+" strTagNum : "+strTagNum+" strCreatedBy : "+strCreatedBy+"strCreationDt : "+strCreationDt+" taskType: "+taskType);		          
	              cstmt.execute();
	              util.logMsg(true,clsName+".getSrHistory", "After Excecute");		          
	              int totRecords = cstmt.getInt(15);

	              histObj[0]=totRecords;
	              Object[] oo =(Object[]) ((Array)cstmt.getObject(16)).getArray();

	              if(oo!=null && oo.length>0){
					  for (int k = 0; k < oo.length; k++) {
						  srList.add((CanonE307SRHistoryRec)oo[k]);		
				         }	
		              histObj[1]=srList; 
    		       }  
			  }else {
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
		return histObj;		
	}	
	public  CanonE307SRHistHdrLvlInfoRec getHdrLvlInfo(String strFsrNum){
		CallableStatement cstmt = null;
	    Connection connection = null;
	    CanonE307SRHistHdrLvlInfoRec hdrInfoObj = new CanonE307SRHistHdrLvlInfoRec();
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_HDR_DET);
			  if (cstmt != null) {
				  cstmt.setString(1, strFsrNum);
	              cstmt.registerOutParameter(2, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_SR_HDR_INFO_TBL");
	              
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_SR_HDR_INFO_REC", CanonE307SRHistHdrLvlInfoRec.class);
	              util.logMsg(true,clsName+".getHdrLvlInfo Before Excecute", "strFsrNum1 : "+ strFsrNum);		          
	              cstmt.execute();
	              util.logMsg(true,clsName+".getHdrLvlInfo", "After Excecute");		          
	              Object[] oo =(Object[]) ((Array)cstmt.getObject(2)).getArray();

	              if(oo!=null && oo.length>0){
					  for (int k = 0; k < oo.length; k++) {
						  hdrInfoObj = (CanonE307SRHistHdrLvlInfoRec)oo[k];		
				      }	
   		       }  
			  }else {
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
		return hdrInfoObj;		
	}	
	public  Object[] getTaskHistoryNew(String strSerial, String strTagNum, String strSolName, String strModel, String strAcctNum, String strCustName, int start, int end, String strSortBy, String strSortOrder, String strSRNum, String strTskNum, String strCreatedBy, String strCreationDt, String strSrSts, String strtskSts, String strTaskType){
		 CallableStatement cstmt = null;
	     Connection connection = null;
        List<CanonE307SRTaskHistNewRec> srList = new ArrayList<CanonE307SRTaskHistNewRec>();	 
        System.out.println("Inside Task history New..");
        Object[] histObj = new Object[2];
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_TASK_HIST_NEW);
			  if (cstmt != null) {
				  cstmt.setString(1, strSRNum);
				  cstmt.setString(2, strTskNum);
				  cstmt.setString(3, strSerial);
				  cstmt.setString(4, strTagNum);
	              cstmt.setString(5, strSolName);
	              cstmt.setString(6, strModel);
	              cstmt.setString(7, strAcctNum);
	              cstmt.setString(8, strCustName);
	              cstmt.setInt(9, start);
	              cstmt.setInt(10, end);
	              cstmt.setString(11,strSortBy);
	              cstmt.setString(12, strSortOrder);
	              cstmt.setString(13, strCreatedBy);
	              cstmt.setString(14, strCreationDt);
	              cstmt.setString(15, strSrSts);
	              cstmt.setString(16, strtskSts);
	              cstmt.setString(17, strTaskType);
	              cstmt.registerOutParameter(18, OracleTypes.NUMBER);
	              cstmt.registerOutParameter(19, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_TASK_HIST_NEW_TBL");
	             // cstmt.registerOutParameter(20, OracleTypes.CURSOR);
	              
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_TASK_HIST_NEW_REC", CanonE307SRTaskHistNewRec.class);
	              util.logMsg(false,clsName+".getTaskHistory Before Excecute", "strSerial : "+ strSerial+" strTagNum : "+strTagNum+" strCreatedBy : "+strCreatedBy+"strCreationDt : "+strCreationDt+" strTskNum: "+strTskNum+" strTaskType: "+strTaskType);		          
	              cstmt.execute();
	              util.logMsg(false,clsName+".getTaskHistory", "After Excecute");		          
	              int totRecords = cstmt.getInt(18);

	              histObj[0]=totRecords;
	              Object[] oo =(Object[]) ((Array)cstmt.getObject(19)).getArray();
	              util.logMsg(true,clsName+".getTaskHistory", "Before oo");
	              if(oo!=null && oo.length>0){
	            	  util.logMsg(false,clsName+".getTaskHistory", "oo.length: "+ oo.length);	
					  for (int k = 0; k < oo.length; k++) {
						  srList.add((CanonE307SRTaskHistNewRec)oo[k]);		
				         }	
		              histObj[1]=srList; 
    		       }  
			  }else {
				  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
	          }
		  } else {
			  System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	      }
	     } catch (Exception ex) {
	    	 ex.printStackTrace();
	    	 System.err.println("Inside catch ");	    	 
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
	     util.logMsg(false,clsName+".getTaskHistory", "Before return");
		return histObj;		
	}	
	public List getTaskSts(){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     try
	       {
			connection = TransactionScope.getConnection();
			List<Object> list = new ArrayList<Object>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_TASK_STS_LIST);
			  if (cstmt != null) {
				  // Set the parameters on the statement
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_LOV_VAL_REC", CanonE307SRLovRec.class);
	              cstmt.registerOutParameter(1, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_LOV_VAL_TBL");
				  // Execute the statement
	              util.logMsg(false,clsName+".getTaskSts before execute", "");
	              cstmt.execute();
	              util.logMsg(false,clsName+".getTaskSts after execute", "");
	              Object oo[] =(Object[]) ((Array) cstmt.getObject(1)).getArray();
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
	public List getSRSts(){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     try
	       {
			connection = TransactionScope.getConnection();
			List<Object> list = new ArrayList<Object>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_SR_STS_LIST);
			  if (cstmt != null) {
				  // Set the parameters on the statement
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_LOV_VAL_REC", CanonE307SRLovRec.class);
	              cstmt.registerOutParameter(1, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_LOV_VAL_TBL");
				  // Execute the statement
	              util.logMsg(false,clsName+".getTaskSts before execute", "");
	              cstmt.execute();
	              util.logMsg(false,clsName+".getTaskSts after execute", "");
	              Object oo[] =(Object[]) ((Array) cstmt.getObject(1)).getArray();
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
    public  String getTaskDtlInfo(String fsrNumber, String lastUpdateDate){
		ArrayList<CanonE307TaskDtlInfoRec> arList = new ArrayList<CanonE307TaskDtlInfoRec>();
		String res="";
		ResultSet rs = null;
		Connection connection = null;
		CallableStatement cstmt = null;
		 util.logMsg(false,clsName+".getTaskDtlInfo ", "fsrNumber: "+fsrNumber+" lastUpdateDate: "+lastUpdateDate);
		try {
			connection = TransactionScope.getConnection();
			cstmt = (CallableStatement)connection.prepareCall(GET_SR_TASK_DET);
			Map map = (Map) connection.getTypeMap();
            map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_TASK_DTL_REC", CanonE307TaskDtlInfoRec.class);
            util.logMsg(false,clsName+".getTaskDtlInfo ", "before execute");
            cstmt.setObject(1, fsrNumber, OracleTypes.VARCHAR);
            cstmt.setObject(2, lastUpdateDate, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(3, OracleTypes.ARRAY,CanonConstants.SCHEMA_NAME+".CANON_E307_TASK_DTL_TBL");	
			cstmt.execute();
			util.logMsg(false,clsName+".getTaskDtlInfo ", "after execute");
			Object oo[] =(Object[]) ((Array) cstmt.getObject(3)).getArray();
			   if(oo!=null && oo.length>0){
					  for (int k = 0; k < oo.length; k++) {
						  util.logMsg(true,clsName+".getTaskDtlInfo ", "Inside for");
						  CanonE307TaskDtlInfoRec rec=  (CanonE307TaskDtlInfoRec)oo[k] ;
						  arList.add( rec);		
				       }	
     		   }
			   
			    res = listToJson(arList, new CanonE307TaskDtlInfoRec());
			   
			   
			} catch (Exception ex1) {
				util.logMsg(true,clsName+".getTaskDtlInfo ", ex1.getMessage());
				res="{ \"data\" : []}";
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (cstmt != null)
						cstmt.close();
					if (connection != null)
						TransactionScope.releaseConnection(connection);
				} catch (Exception ex2) {
					util.logMsg(true,clsName+".getTaskDtlInfo ", ex2.getMessage());
				}
			}
			return res;
	}		

		
   public String listToJson(ArrayList pojoList, Object model) {
		StringBuffer jsonString = new StringBuffer();
		jsonString.append("{ \"data\" : [");
		if(pojoList!=null && pojoList.size()>0){
			for (int j = 0; j < pojoList.size(); j++) {
				model = pojoList.get(j);
				String jsonObj= getJavaBeanToJsonString(model);
				if (j > 0)
					jsonString.append(" ,");
				jsonString.append(jsonObj);
			}
		}
		jsonString.append("]}");

		return jsonString.toString();
	}
	
	
	public String getJavaBeanToJsonString(Object model){
		String res="";
		try {
			String col=":";
			String slshCm="\",";
			String slsh="\"";
			
			if(model instanceof CanonE307TaskDtlInfoRec){ 
				CanonE307TaskDtlInfoRec t= (CanonE307TaskDtlInfoRec) model;
				 res+="\"strSvcTskNum\""+col+"\""+checkNull(t.getStrSvcTskNum())+slshCm;
				 res+="\"strTskStsCd\""+col+"\""+checkNull(t.getStrTskStsCd())+slshCm;
				 res+="\"strTskSTsNm\""+col+"\""+checkNull(t.getStrTskSTsNm())+slshCm;
				 res+="\"strAssigneeCd\""+col+"\""+checkNull(t.getStrAssigneeCd())+slshCm;
				 res+="\"strAssignee\""+col+"\""+checkNull(t.getStrAssignee())+slshCm;
				 res+="\"strAssigneeTpCd\""+col+"\""+checkNull(t.getStrAssigneeTpCd())+slshCm;
				 res+="\"strLastUpdateDate\""+col+"\""+checkNull(t.getStrLastUpdateDate())+slsh;
			} 
		
			
			res="{"+res+"}";
			
		} catch (Exception e) {
			res="{}";
			 util.logMsg(true,clsName+".getTaskDtlInfo ", "ERROR:"+e.getMessage().toString());
		}
		return res;
	}
	
	public String checkNull(String str) {
		if (str != null) {
			return str;
		} else {
			return "";
		}
	}
    public String getTsklstUpdFlag(String strFsrNum, String stsLastUpdDt){
        util.logMsg(false, clsName+".getTsklstUpdFlag", "strFsrNum : " + strFsrNum+" stsLastUpdDt: "+stsLastUpdDt);	        
        CallableStatement statement = null;
        Connection connection = null;
        String tskUpdFlg = "";
        try {
        	 connection = TransactionScope.getConnection();
             if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_LST_TASK_UPD_FLG);
                if (statement != null) {
                    statement.setObject(2, strFsrNum, OracleTypes.VARCHAR);
                    statement.setObject(3, stsLastUpdDt, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
                    statement.execute();
                    tskUpdFlg =  statement.getString(1);
                    util.logMsg(false, clsName+".getTsklstUpdFlag", "tskUpdFlg : " + tskUpdFlg);	 
                    return tskUpdFlg;
                }else{
                	 util.logMsg(true,clsName+".getTsklstUpdFlag", " Statement is Null");
                } 
             }else{
            	 util.logMsg(true,clsName+".getTsklstUpdFlag", " Connection is Null");
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
        return tskUpdFlg;
    }
    
    public String getInMtrRdsExistFlag(String strSvcTaskNum){
        util.logMsg(false, clsName+".getInMtrRdsExistFlag", "strSvcTaskNum : " + strSvcTaskNum);	        
        CallableStatement statement = null;
        Connection connection = null;
        String mtrRdFlg = "";
        try {
        	 connection = TransactionScope.getConnection();
             if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_IN_MTR_RD_FLG);
                if (statement != null) {
                    statement.setObject(2, strSvcTaskNum, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
                    statement.execute();
                    mtrRdFlg =  statement.getString(1);
                    util.logMsg(false, clsName+".getInMtrRdsExistFlag", "tskUpdFlg : " + mtrRdFlg);	 
                    return mtrRdFlg;
                }else{
                	 util.logMsg(true,clsName+".getInMtrRdsExistFlag", " Statement is Null");
                } 
             }else{
            	 util.logMsg(true,clsName+".getInMtrRdsExistFlag", " Connection is Null");
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
        return mtrRdFlg;
    }	
      public String getHolidayFlag(String strDate){
	        util.logMsg(false, clsName+".getHolidayFlag", "strDate : " + strDate);	        
	        CallableStatement statement = null;
	        Connection connection = null;
	        String holidayFlg = "";
	        try {
	        	 connection = TransactionScope.getConnection();
	             if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(GET_HOLIDAY_FLAG);
	                if (statement != null) {
	                    statement.setObject(2, strDate, OracleTypes.VARCHAR);
	                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
	                    statement.execute();
	                    holidayFlg =  statement.getString(1);
	                    util.logMsg(false, clsName+".getHolidayFlag", "holidayFlg : " + holidayFlg);	 
	                    return holidayFlg;
	                }else{
	                	 util.logMsg(true,clsName+".getHolidayFlag", " Statement is Null");
	                } 
	             }else{
	            	 util.logMsg(true,clsName+".getHolidayFlag", " Connection is Null");
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
	        return holidayFlg;
	    }
      
  	public ArrayList<CanonE307AttFileRec> getFSRReportInfo(String strFsrNumber){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     util.logMsg(true,clsName+".getFSRReportInfo", "Inside strFsrNumber: "+ strFsrNumber);
	     try
	       {
			connection = TransactionScope.getConnection();
			ArrayList<CanonE307AttFileRec> list = new ArrayList<CanonE307AttFileRec>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_FSR_REPORT_INFO);
			  if (cstmt != null) {
				      cstmt.setString(1, strFsrNumber);
	
				  // Set the parameters on the statement
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_ATT_FILE_REC", CanonE307AttFileRec.class);
	              cstmt.registerOutParameter(2, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_ATT_FILE_TBL");
				  // Execute the statement
	              util.logMsg(true,clsName+".getFSRReportInfo Before Execute", "strFsrNumber: "+ strFsrNumber);	              
	              cstmt.execute();
	              util.logMsg(true,clsName+".getFSRReportInfo", "After Execute");
	              Object oo[] =(Object[]) ((Array) cstmt.getObject(2)).getArray();
				  if(oo!=null && oo.length>0){
					  for (int i = 0; i < oo.length; i++) {
						   list.add((CanonE307AttFileRec)oo[i]);
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
	  public String getDownloadFilePath(){
			
			String res="";
			ResultSet rs = null;
			Connection connection = null;
			CallableStatement cstmt = null;
			try {
				connection = TransactionScope.getConnection();
				cstmt = (CallableStatement)connection.prepareCall(GET_DOWNLOAD_PATH);
				cstmt.registerOutParameter(1, OracleTypes.VARCHAR);
			    cstmt.execute();
				res= util.checkNull(cstmt.getString(1));
			} catch (Exception ex1) {
				util.logMsg(true,clsName+".getDownloadFilePath ", ex1.getMessage());
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
		public List getAuditLogInfo(String fsrNum){
			 CallableStatement cstmt = null;
		     Connection connection = null;
			 ResultSet rs = null;
			 CanonE307SRAuditLogRec auditObj = new CanonE307SRAuditLogRec();
			 List<CanonE307SRAuditLogRec> auditLst = new ArrayList<CanonE307SRAuditLogRec>();	 
			// util.logMsg(false,clsName+".getAuditLogInfo Before Excecute", "fsrNum: "+fsrNum);	
		     try
		       {
		    	 connection = TransactionScope.getConnection();
				if (connection != null) {
					cstmt = (CallableStatement)connection.prepareCall(GET_AUDIT_INFO);
				  if (cstmt != null) {
		              cstmt.setString(1, fsrNum);
		              cstmt.registerOutParameter(2, OracleTypes.CURSOR);
		              
		              util.logMsg(false,clsName+".getAuditLogInfo Before Excecute", "fsrNum : "+ fsrNum);		          
		              cstmt.execute();
		              util.logMsg(false,clsName+".getAuditLogInfo ", "After Execute");		          

		              rs = (ResultSet) cstmt.getObject(2);	
		              while (rs.next()) {
		            	  auditObj = new CanonE307SRAuditLogRec();
		            	  auditObj.setStrFSR(rs.getString("FSR"));
		            	  auditObj.setStrTask(rs.getString("TASK"));
		            	  auditObj.setStrTime(rs.getString("TIME"));
		            	  auditObj.setStrUpdate(rs.getString("UPDATE"));
		            	  auditObj.setStrOldValue(rs.getString("OLD_VALUE"));
		            	  auditObj.setStrNewValue(rs.getString("NEW_VALUE"));
		            	  auditObj.setStrUserId(rs.getString("USER_ID"));
		            	  

		            	  util.logMsg(false,clsName+".getAuditLogInfo ", "FSR: "+ rs.getString("FSR"));		
		            	  auditLst.add(auditObj);
		              }
		            //  histObj[0]=srList;
				  }else {
					  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
		          }
			  } else {
				  System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
			  }
		     } catch (Exception ex) {
		    	 ex.printStackTrace();
				  util.logMsg(true,clsName+".getAuditLogInfo fsrNum: ", ex.toString());	
		     } finally {
		    	 if (cstmt != null) {
		    		 try {
		    			 cstmt.close();
		    			 cstmt = null;
		    		 } catch (Exception exp) {
		    			 exp.printStackTrace();
		    		 }
		    	 }
				 if (rs != null){
		    		 try {
		    			 rs.close();
		    			 rs = null;
		    		 } catch (Exception exp) {
		    			 exp.printStackTrace();
		    		 }					 
		    	 }
		}	
   return auditLst;	
	}		  
	
}    
	


 