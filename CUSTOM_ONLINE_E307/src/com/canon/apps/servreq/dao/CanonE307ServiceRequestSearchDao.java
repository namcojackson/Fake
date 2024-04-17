package com.canon.apps.servreq.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.sql.Array;

import com.canon.apps.servreq.beans.CanonE307BillToCustCreditAuthBean;
import com.canon.apps.servreq.beans.CanonE307EOLRec;
import com.canon.apps.servreq.beans.CanonE307HardHoldInfoRec;
import com.canon.apps.servreq.beans.CanonE307MachineCustSearchRec;
import com.canon.apps.servreq.beans.CanonE307MachineCustSearchResultsRec;
import com.canon.apps.servreq.beans.CanonE307SRLovRec;
import com.canon.apps.servreq.beans.CanonE307ServiceReqProbUtilRec;
import com.canon.apps.servreq.beans.CanonE307UGWErrCodeRec;
import com.canon.apps.servreq.beans.CanonE307ServReqFutureCallRec;
import com.canon.apps.servreq.beans.CanonE307ServReqVendCallRec;
import com.canon.common.CanonCommonUtil;







import canon.apps.common.CanonConstants;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
public class CanonE307ServiceRequestSearchDao {
	
	public static final String GET_BRANCh_INFO = "{CALL CANON_E307_DSPTCH_PKG.BRANCH_CODE_LOV(?)}";
	public static final String GET_SERIAL_INFO = "{CALL CANON_E307_DSPTCH_PKG.GET_SRL_INFO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	public static final String GET_UGW_INFO = "{CALL CANON_E307_DSPTCH_PKG.GET_UGW_DETAILS(?,?,?,?,?,?,?,?,?,?)}";
	public static final String GET_FUTURE_CALLS = "{CALL CANON_E307_DSPTCH_PKG.GET_FUTURE_SR_DTLS(?,?,?,?,?,?,?,?,?,?)}";
	public static final String GET_VENDOR_CALLS = "{CALL CANON_E307_DSPTCH_PKG.GET_VENDOR_SR_DTLS(?,?,?,?,?,?,?,?,?,?,?)}";
	public static final String GET_MACHINE_COUNT = "{CALL CANON_E307_DSPTCH_PKG.GET_MACHINE_COUNT(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
//	public static final String GET_MACHINE_EOL = "{?= call CANON_E307_DSPTCH_PKG.GET_MACHINE_EOL(?)}";
	public static final String GET_MACHINE_EOL = "{call CANON_E307_DSPTCH_PKG.GET_MACHINE_EOL(?,?,?,?,?)}";
	public static final String GET_MACH_EOL_DTLS = "{CALL CANON_E307_DSPTCH_PKG.GET_MACHINE_EOL_DTLS(?,?)}";	
	public static final String GET_EOL_SERV_DT =  "{?= call CANON_E307_DSPTCH_PKG.GET_EOL_SERV_DT(?)}";
	public static final String GET_SERV_MSGS = "{CALL CANON_E307_DSPTCH_PKG.GET_SERVICE_MESSAGES(?,?,?)}";
	public static final String GET_UGW_LOCKOUT = "{CALL CANON_E307_DSPTCH_PKG.CHECK_UGW_LOCKOUT(?,?,?)}";
	public static final String GET_EOL_INFO = "{CALL CANON_E307_DSPTCH_PKG.GET_EOL_SERV_INFO(?,?,?,?)}";
	public static final String GET_EOL_FLG =  "{?= call CANON_E307_DSPTCH_PKG.GET_EOL_FLG(?,?)}";	
	public static final String GET_SRVC_EOL_DTLS = "{CALL CANON_E307_DSPTCH_PKG.GET_EOL_SRV_MSGS(?,?,?)}";
	public static final String GET_HARD_HOLD_FLG =  "{?= call CANON_E307_DSPTCH_PKG.GET_HARD_HOLD_FLG(?,?)}";	
	public static final String GET_HARD_HOLD_INFO =  "{CALL CANON_E307_DSPTCH_PKG.GET_COLLECTION_MANAGER(?,?,?)}";
	public static final String UPDATE_NOTIF_FLG =  "{CALL CANON_E307_DSPTCH_PKG.UPDATE_EMAIL_NOTIF_FLG(?,?,?,?,?,?)}";
	public static final String GET_TASK_TYPES =  "{CALL CANON_E307_DSPTCH_PKG.GET_TASK_TYPES(?)}";
	
	
	CanonCommonUtil util;
	String SCH_NAME;
	
	private   String clsName="CanonE307ServiceRequestSearchDao";
	public CanonE307ServiceRequestSearchDao() {
		 util = new CanonCommonUtil();
		 SCH_NAME = CanonConstants.SCHEMA_NAME;
	}
	
	public Object[] getMachineSearchResults(CanonE307MachineCustSearchRec custRec, int start, int end){
	     CallableStatement cstmt = null;
	     Connection connection = null;
	     Object[] resObj = new Object[2];
	     try
	       {
			connection = TransactionScope.getConnection();
			List<Object> list = new ArrayList<Object>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_SERIAL_INFO);
			  if (cstmt != null) {
				  cstmt.setString(1,util.checkStrNull(custRec.getStrSerialTagNumber()));
				  cstmt.setString(2,util.checkStrNull(custRec.getStrSolutionName()));
				  cstmt.setString(3,util.checkStrNull(custRec.getStrAccountNumber()));
				  cstmt.setString(4,util.checkStrNull(custRec.getStrCustomerName()));
				  cstmt.setString(5,util.checkStrNull(custRec.getStrAddress()));
				  cstmt.setString(6,util.checkStrNull(custRec.getStrCity()));
				  cstmt.setString(7,util.checkStrNull(custRec.getStrState()));
				  cstmt.setString(8,util.checkStrNull(custRec.getStrPostalCode()));
				  cstmt.setString(9,util.checkStrNull(custRec.getStrPhone1()));
			//	  cstmt.setString(10,custRec.getStrPhone2());
			//	  cstmt.setString(11,custRec.getStrPhone3());
				  cstmt.setInt(10,start);
				  cstmt.setInt(11,end);
				  cstmt.setString(12,util.checkStrNull(custRec.getStrSortBy()));
				  cstmt.setString(13,util.checkStrNull(custRec.getStrSortOrder()));
				  cstmt.registerOutParameter(14, OracleTypes.NUMBER);
	              cstmt.registerOutParameter(15, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_MAC_SER_TBL");
				  // Execute the statement
	              Map map = connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_MAC_SER_REC",CanonE307MachineCustSearchResultsRec.class);
	              util.logMsg(false,clsName+".getMachineSearchResults before execute", "Serial Number : "+ custRec.getStrSerialTagNumber()+" start: "+start+" end: "+end);
	              util.logMsg(false,clsName+".getMachineSearchResults before execute",  custRec.toString());
	              
	              cstmt.execute();
				//  Object[] oo = (Object[]) cstmt.getARRAY(2).getArray();
	              int totRecords = cstmt.getInt(14);
	              util.logMsg(false,clsName+".getMachineSearchResults before execute","");
	              resObj[0]=totRecords;
	              Object oo[] =(Object[]) ((Array)cstmt.getObject(15)).getArray();
				  if(oo!=null && oo.length>0){
					  System.out.println("length : "+oo.length);
					  for (int i = 0; i < oo.length; i++) {
						   list.add(oo[i]);			       
					  }	
					  resObj[1]=list; 
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
	public Object[] getUGWErrCodeDtls(String strCustNm, String strSrlTagNum, String strModel, String strBranch, int start, int end, String strSortby, String strSortOrder){
	     CallableStatement cstmt = null;
	     Connection connection = null;
	     Object[] resObj = new Object[2];
	     try
	       {
			connection = TransactionScope.getConnection();
			List<Object> list = new ArrayList<Object>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_UGW_INFO);
			  if (cstmt != null) {
				  cstmt.setString(1,strCustNm);
				  cstmt.setString(2,strSrlTagNum);
				  cstmt.setString(3,strModel);
				  cstmt.setString(4,strBranch);
				  cstmt.setInt(5,start);
				  cstmt.setInt(6,end);
				  cstmt.setString(7,strSortby);
				  cstmt.setString(8,strSortOrder);
				  cstmt.registerOutParameter(9, OracleTypes.NUMBER);
	              cstmt.registerOutParameter(10, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_UGW_TBL");
				  // Execute the statement
	              Map map = connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_UGW_REC",CanonE307UGWErrCodeRec.class);
	              util.logMsg(false,clsName+".getUGWErrCodeDtls before execute", "strCustNm : "+ strCustNm);
	              cstmt.execute();
	              util.logMsg(false,clsName+".getUGWErrCodeDtls", "After Execute");	              
	              int totRecords = cstmt.getInt(9);
	              resObj[0]=totRecords;

	              Object oo[] =(Object[]) ((Array)cstmt.getObject(10)).getArray();
	              
				  if(oo!=null && oo.length>0){
					  for (int i = 0; i < oo.length; i++) {
						   list.add(oo[i]);			       
					  }	
					  resObj[1]=list; 
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
	
	public Object[] getFutureCalls(int start, int end, String strSortby, String strSortOrder, String strFtrBrnch, String strFtrDt, String strCustomer){
	     CallableStatement cstmt = null;
	     Connection connection = null;
	     Object[] resObj = new Object[2];
	     String strSerial="";
	     try
	       {
			connection = TransactionScope.getConnection();
			List<Object> list = new ArrayList<Object>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_FUTURE_CALLS);
			  if (cstmt != null) {
				  cstmt.setString(1, strSerial);
				  cstmt.setString(2, util.checkStrNull(strFtrDt));
				  cstmt.setString(3, util.checkStrNull(strFtrBrnch));
				  cstmt.setString(4, util.checkStrNull(strCustomer));
				  cstmt.setInt(5,start);
				  cstmt.setInt(6,end);
				  cstmt.setString(7,strSortby);
				  cstmt.setString(8,strSortOrder);
				  cstmt.registerOutParameter(9, OracleTypes.NUMBER);
	              cstmt.registerOutParameter(10, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_SR_DETL_TBL");
				  // Execute the statement
	              Map map = connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_SR_DETL_REC", CanonE307ServReqFutureCallRec.class);
	              util.logMsg(false,clsName+".getFutureCalls Before Execute", "strFtrBrnch: "+strFtrBrnch+" strCustomer : "+ strCustomer);
	              cstmt.execute();
	              util.logMsg(false,clsName+".getFutureCalls", "After Execute");
	              int totRecords = cstmt.getInt(9);
	              resObj[0]=totRecords;
	              Object ooF[] =(Object[]) ((Array)cstmt.getObject(10)).getArray();
	              
				  if(ooF!=null && ooF.length>0){
					  for (int i = 0; i < ooF.length; i++) {
						   list.add(ooF[i]);			       
					  }	
					  resObj[1]=list; 
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
	
	public Object[] getThirdPartyCalls(int start, int end, String strSortby, String strSortOrder, String strCustNm, String strSts, String strAssignee){
	     CallableStatement cstmt = null;
	     Connection connection = null;
	     Object[] resObj = new Object[2];
	     String strVendSerNum="";
	     String strVendDt="";
	     
	     try
	       {
			connection = TransactionScope.getConnection();
			List<Object> list = new ArrayList<Object>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_VENDOR_CALLS);
			  if (cstmt != null) {
				  cstmt.setString(1, strVendSerNum);
				  cstmt.setString(2, strVendDt);
				  cstmt.setString(3,  util.checkStrNull(strCustNm));
				  cstmt.setString(4,  util.checkStrNull(strSts));
				  cstmt.setString(5,  util.checkStrNull(strAssignee));
				  cstmt.setInt(6,start);
				  cstmt.setInt(7,end);
				  cstmt.setString(8,strSortby);
				  cstmt.setString(9,strSortOrder);
				  cstmt.registerOutParameter(10, OracleTypes.NUMBER);
	              cstmt.registerOutParameter(11, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_SR_DETL_TBL");
				  // Execute the statement
	              Map map = connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_SR_DETL_REC", CanonE307ServReqVendCallRec.class);
	              util.logMsg(false,clsName+".getThirdPartyCalls Before Execute", "strCustNm : "+ strCustNm+" strAssignee : "+strAssignee);
	              cstmt.execute();
	              util.logMsg(false,clsName+".getThirdPartyCalls", "After Execute");
	              int totRecords = cstmt.getInt(10);
	              resObj[0]=totRecords;
	              Object ooF[] =(Object[]) ((Array)cstmt.getObject(11)).getArray();
	              
				  if(ooF!=null && ooF.length>0){
					  for (int i = 0; i < ooF.length; i++) {
						   list.add(ooF[i]);			       
					  }	
					  resObj[1]=list; 
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
	public String[] getRecCount(String strSerialNum, String strSolNm, String strAcctNum, String strCustNm, String strAddress, String strCity, String strState, String strPostalCd, String strPhNum){
		util.logMsg(false,clsName+".getRecCount ", " strSerialNum : "+ strSerialNum+" strAcctNum : "+strAcctNum);
		String[] retFlag=new String[7];
		Connection connection = null;
		CallableStatement cstmt  = null;
		ResultSet rs = null;
		try
		{
			  connection = TransactionScope.getConnection();
			  if(connection!=null)
			  cstmt = connection.prepareCall(GET_MACHINE_COUNT);
			  cstmt.setString(1, strSerialNum);
			  cstmt.setString(2, strSolNm);
			  cstmt.setString(3, strAcctNum);
			  cstmt.setString(4, strCustNm);
			  cstmt.setString(5, strAddress);
			  cstmt.setString(6, strCity);
			  cstmt.setString(7, strState);
			  cstmt.setString(8, strPostalCd);
			  cstmt.setString(9, strPhNum);			  
			  cstmt.registerOutParameter(1,Types.VARCHAR);
			  cstmt.registerOutParameter(10,Types.INTEGER);
			  cstmt.registerOutParameter(11,Types.VARCHAR);
			  cstmt.registerOutParameter(12,Types.VARCHAR);
			  cstmt.registerOutParameter(13,Types.VARCHAR);
			  util.logMsg(false,clsName+".getRecCount Before Execute", " strSerialNum : "+ strSerialNum+" strAcctNum : "+strAcctNum);
			  cstmt.execute();
              util.logMsg(false,clsName+".getRecCount After Execute", " strSerialNum : "+ strSerialNum+" strAcctNum : "+strAcctNum);
			  int count = cstmt.getInt(10);
			  System.out.println("count:  "+ count);
			  if(count==1){
				  retFlag[0]= util.checkNull(cstmt.getString(1));
				  retFlag[1]= util.checkNull(cstmt.getString(11));
				  retFlag[2]= util.checkNull(cstmt.getString(12));
				  util.logMsg(false,clsName+".getRecCount ", "  retFlag[0] : "+  retFlag[0]+"retFlag[1]: "+retFlag[1]);	
	//			  retFlag[3] = geMachineEolDt(retFlag[0]);
				  retFlag[3] = getServiceMsgs(retFlag[0], retFlag[1]);
				  retFlag[4] = geEOLFlg(retFlag[0], retFlag[1]); 
				  retFlag[5] = geHardHoldFlg(retFlag[0], retFlag[1]);
				  retFlag[6]=  util.checkNull(cstmt.getString(13));
				  util.logMsg(false,clsName+".getRecCount ", "  retFlag[3] : "+  retFlag[3]+ " retFlag[4]: " + retFlag[4]+" retFlag[5]: "+retFlag[5]);
			  }
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
	
	public int getSrCount(String strSrNum, String strTskNum, String strCreatedBy, String strCreationDt, String strSrSts, String strTskSts, String strtaskType){
		int count=0;
		Connection connection = null;
		CallableStatement cstmt  = null;
		try
		{
		  String plsqlExp =  " Begin \n "
				   + " :1 := CANON_E307_DSPTCH_PKG.GET_SR_COUNT(:2,:3,:4,:5,:6,:7,:8) ; \n "
				   + " End;";
			  connection = TransactionScope.getConnection();
			  if(connection!=null)
				  cstmt = connection.prepareCall(plsqlExp);
			  cstmt.setString(2, strSrNum);
			  cstmt.setString(3, strTskNum);
			  cstmt.setString(4, strCreatedBy);
			  cstmt.setString(5, strCreationDt);
			  cstmt.setString(6, strSrSts);
			  cstmt.setString(7, strTskSts);
			  cstmt.setString(8, strtaskType);
			  cstmt.registerOutParameter(1,Types.INTEGER);
			  cstmt.execute();
              util.logMsg(false,clsName+".getSrCount After Execute", " strSrNum : "+ strSrNum+" strTskNum : "+strTskNum+" strtaskType: "+strtaskType);
			  count = cstmt.getInt(1);
			  util.logMsg(false,clsName+".getSrCount After Execute", " count : "+ count+" strSrSts : "+strSrSts+" strTskSts: "+strTskSts);
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
		return count;		
	}	
	public ArrayList<CanonE307SRLovRec> getTaskTypeLovVal(){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     ArrayList<CanonE307SRLovRec> arList = new ArrayList<CanonE307SRLovRec>();
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_TASK_TYPES);
			  if (cstmt != null) {
				  // Set the parameters on the statement
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_LOV_VAL_REC", CanonE307SRLovRec.class);
	              cstmt.registerOutParameter(1, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_LOV_VAL_TBL");
				  // Execute the statement
	              util.logMsg(false,clsName+".getTaskTypeLovVal", "Before execute");
	              cstmt.execute();
	              util.logMsg(false,clsName+".getDebriefLovVal", "After Execute");
	              Object oo[] =(Object[]) ((Array) cstmt.getObject(1)).getArray();
				  if(oo!=null && oo.length>0){
					  for (int i = 0; i < oo.length; i++) {
						  CanonE307SRLovRec rec=  (CanonE307SRLovRec)oo[i] ;
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
 /*   public String geMachineEol(String strSerialNumber){
        util.logMsg(false, clsName+".geMachineEol", "strSerialNumber : " + strSerialNumber);	        
        CallableStatement statement = null;
        Connection connection = null;
        String eolFlg = "";
        try {
        	 connection = TransactionScope.getConnection();
             if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_MACHINE_EOL);
                if (statement != null) {
                    statement.setObject(2, strSerialNumber, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
                    statement.execute();
                    eolFlg =  statement.getString(1);
                    util.logMsg(false, clsName+".geMachineEol", "eolFlg : " + eolFlg);	 
                    return eolFlg;
                }else{
                	 util.logMsg(true,clsName+".geMachineEol", " Statement is Null");
                } 
             }else{
            	 util.logMsg(true,clsName+".geMachineEol", " Connection is Null");
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
        return eolFlg;
    }	*/
/*	public String[] geMachineEol(String strSerialNum){
		String[] retFlag=new String[4];
		Connection connection = null;
		CallableStatement cstmt  = null;
		ResultSet rs = null;
		try
		{
			  connection = TransactionScope.getConnection();
			  if(connection!=null)
			  cstmt = connection.prepareCall(GET_MACHINE_EOL);
			  cstmt.setString(1, strSerialNum);
			  cstmt.registerOutParameter(2,Types.VARCHAR);
			  cstmt.registerOutParameter(3,Types.VARCHAR);
			  cstmt.registerOutParameter(4,Types.VARCHAR);
			  cstmt.registerOutParameter(5,Types.VARCHAR);
              util.logMsg(false,clsName+".geMachineEol Before Execute", " strSerialNum : "+ strSerialNum);			  
			  cstmt.execute();
        	  retFlag[0]= util.checkNull(cstmt.getString(2)); // Creation Call Flg
			  retFlag[1]= util.checkNull(cstmt.getString(3)); // EOL Tp Nm
			  retFlag[2]= util.checkNull(cstmt.getString(4)); //EOL End Dt
			  retFlag[3]= util.checkNull(cstmt.getString(5)); // Disp Comments
			  util.logMsg(false,clsName+".geMachineEol", " retFlag[0] : "+ retFlag[0]+" retFlag[1]: "+retFlag[1]+" retFlag[2]: "+retFlag[2]+" retFlag[3]: "+retFlag[3]);
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
	}		*/
	public List getMachineEOLDtls(String fsrNumber){
	     CallableStatement cstmt = null;
	     Connection connection = null;
	     List<Object> list = new ArrayList<Object>();
	     try
	       {
			connection = TransactionScope.getConnection();
				
			if (connection != null) {
				cstmt = connection.prepareCall(GET_MACH_EOL_DTLS);
			  if (cstmt != null) {
				  cstmt.setString(1, fsrNumber);
	              cstmt.registerOutParameter(2, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_EOL_TBL");
				  // Execute the statement
	              Map map = connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_EOL_REC", CanonE307EOLRec.class);
	              util.logMsg(false,clsName+".getMachineEOLDtls Before Execute", "fsrNum : "+ fsrNumber);
	              cstmt.execute();
	              util.logMsg(false,clsName+".getMachineEOLDtls", "After Execute");
	              Object ooF[] =(Object[]) ((Array)cstmt.getObject(2)).getArray();
	              
				  if(ooF!=null && ooF.length>0){
					  util.logMsg(false,clsName+".getMachineEOLDtls","Length: "+ooF.length);
					  for (int i = 0; i < ooF.length; i++) {
						   list.add(ooF[i]);		
						  // util.logMsg(false,clsName+".getMachineEOLDtls","log "+((CanonE307EOLRec)ooF[i]).toString());
					  }	
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
	     util.logMsg(false,clsName+".getMachineEOLDtls", "Before returning list");
	     return list;
	}   
	public String geMachineEolDt(String strSerialNumber){
        util.logMsg(false, clsName+".geMachineEolDt", "strSerialNumber : " + strSerialNumber);	        
        CallableStatement statement = null;
        Connection connection = null;
        String eolEndDt = "";
        try {
        	 connection = TransactionScope.getConnection();
             if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_EOL_SERV_DT);
                if (statement != null) {
                    statement.setObject(2, strSerialNumber, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
                    util.logMsg(false, clsName+".geMachineEolDt", "Before execute " );	                    
                    statement.execute();
                    util.logMsg(false, clsName+".geMachineEolDt", "After Execute " );	
                    eolEndDt =  statement.getString(1);
                    util.logMsg(false, clsName+".geMachineEolDt", "eolEndDt : " + eolEndDt);	 
                    return eolEndDt;
                }else{
                	 util.logMsg(true,clsName+".geMachineEolDt", " Statement is Null");
                } 
             }else{
            	 util.logMsg(true,clsName+".geMachineEolDt", " Connection is Null");
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
        return eolEndDt;
    }		
    static oracle.sql.ARRAY canonMachineSearchListToArray(List list, Connection connection) throws SQLException{
        ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor(CanonConstants.CSA_COMPANY_CODE+".CANON_E307_SEARCH_SER_TBL", connection);
        CanonE307MachineCustSearchRec[] searchArray = (CanonE307MachineCustSearchRec[]) list.toArray(new CanonE307MachineCustSearchRec[0]);
        oracle.sql.ARRAY array = new oracle.sql.ARRAY(arraydesc, connection, searchArray);
        return array;
    }
	public String getServiceMsgs(String strSerialNum, String svcMachMstrPk){
		Connection connection = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		ArrayList alRepData = new ArrayList();
		String srvMsg = "";
		try {

			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_SERV_MSGS);
				if (cstmt !=null){
					cstmt.setString(1, strSerialNum);
					cstmt.registerOutParameter(2, OracleTypes.CURSOR);
					cstmt.setString(3, svcMachMstrPk);
					util.logMsg(false,clsName+".getServiceMsgs", " Before execute0.."+ strSerialNum);
					cstmt.execute();
					util.logMsg(false,clsName+".getServiceMsgs", " After execute0.."+ strSerialNum);
					rs = (ResultSet) cstmt.getObject(2);
					while (rs.next()) {
						 util.logMsg(false,clsName+".getServiceMsgs", "Srvc Message: "+ rs.getString("service_message"));						 
						 if("".equals(srvMsg)){
							 srvMsg = "<p>"+rs.getString("SERVICE_MESSAGE")+"</p>";
						 }else{
							// srvMsg = srvMsg+" \n "+ rs.getString("SERVICE_MESSAGE");
							 srvMsg = srvMsg+"<br><p>"+ rs.getString("SERVICE_MESSAGE")+"</p>";
						 }
					}
					 util.logMsg(false,clsName+".getServiceMsgs", "Srvc Message final: "+ srvMsg);
				}else{
					util.logMsg(true,clsName+".getServiceMsgs", " CallableStatement is Null");
				}
			}else{
				util.logMsg(true,clsName+".getServiceMsgs", " Connection is Null");
			}
			
		} catch (Exception exception1) {
			exception1.printStackTrace();
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (rs != null)
					rs.close();
				if (connection != null)
					TransactionScope.releaseConnection(connection);
			} catch (Exception exception2) {
				exception2.printStackTrace();
			}
		}
		return srvMsg;
	}	
	public String getUGWLockoutInfo(String strSerialNum, String strUserId){
		util.logMsg(false,clsName+".getUGWLockoutInfo..", " strSerialNum: "+ strSerialNum+" strUserId: "+strUserId);
		Connection connection = null;
		CallableStatement cstmt = null;
		String retVal = "N";
		try {

			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_UGW_LOCKOUT);
				if (cstmt !=null){
					cstmt.setString(1, strSerialNum);
					cstmt.setString(2, strUserId);
					cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
					cstmt.execute();
					retVal = cstmt.getString(3);
					util.logMsg(false,clsName+".getUGWLockoutInfo", " After execute0 retVal: " + retVal);					
				}else{
					util.logMsg(true,clsName+".getUGWLockoutInfo", " CallableStatement is Null");
				}
			}else{
				util.logMsg(true,clsName+".getUGWLockoutInfo", " Connection is Null");
			}
			
		} catch (Exception exception1) {
			exception1.printStackTrace();
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (connection != null)
					TransactionScope.releaseConnection(connection);
			} catch (Exception exception2) {
				exception2.printStackTrace();
			}
		}
		return retVal;
	}	    
	public String[] getEOLInfo(String strSerialNum){
		util.logMsg(false,clsName+".Inside getEOLInfo ", " strSerialNum : "+ strSerialNum);
		String[] retFlag=new String[3];
		Connection connection = null;
		CallableStatement cstmt  = null;
		ResultSet rs = null;
		try
		{
			  connection = TransactionScope.getConnection();
			  if(connection!=null)
			  cstmt = connection.prepareCall(GET_EOL_INFO);
			  cstmt.setString(1, strSerialNum);
			  cstmt.registerOutParameter(2,Types.VARCHAR);
			  cstmt.registerOutParameter(3,Types.VARCHAR);
			  cstmt.registerOutParameter(4,Types.VARCHAR);
			  util.logMsg(false,clsName+".getEOLInfo Before Execute", " strSerialNum : "+ strSerialNum);
			  cstmt.execute();
              util.logMsg(false,clsName+".getEOLInfo After Execute", " strSerialNum : "+ strSerialNum);
              retFlag[0] = cstmt.getString(2)==null?"":cstmt.getString(2);
              retFlag[1] = cstmt.getString(3)==null?"":cstmt.getString(3);
              retFlag[2] = cstmt.getString(4);
			  System.out.println("srFlg:  "+retFlag[0]+" dispCmnts: "+ retFlag[1]+" endDt: "+retFlag[2]);

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
	public String geEOLFlg(String strSerialNumber, String svcMachMstrPk){
        util.logMsg(false, clsName+".geEOLFlg", "strSerialNumber : " + strSerialNumber);	        
        CallableStatement statement = null;
        Connection connection = null;
        String eolFlg = "";
        try {
        	 connection = TransactionScope.getConnection();
             if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_EOL_FLG);
                if (statement != null) {
                    statement.setObject(2, strSerialNumber, OracleTypes.VARCHAR);
                    statement.setObject(3, svcMachMstrPk);
                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
                    statement.execute();
                    eolFlg =  statement.getString(1);
                    util.logMsg(false, clsName+".geEOLFlg", "eolFlg : " + eolFlg);	 
                    return eolFlg;
                }else{
                	 util.logMsg(true,clsName+".geMachineEolDt", " Statement is Null");
                } 
             }else{
            	 util.logMsg(true,clsName+".geMachineEolDt", " Connection is Null");
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
        return eolFlg;
    }	
	public List getSrvcEOLDtls(String strSerialNumber, String svcMachMstrPk){
	     CallableStatement cstmt = null;
	     Connection connection = null;
	     List<Object> list = new ArrayList<Object>();
	     try
	       {
			connection = TransactionScope.getConnection();
				
			if (connection != null) {
				cstmt = connection.prepareCall(GET_SRVC_EOL_DTLS);
			  if (cstmt != null) {
				  cstmt.setString(1, strSerialNumber);
	              cstmt.registerOutParameter(2, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_EOL_TBL");
	              cstmt.setString(3, svcMachMstrPk);
				  // Execute the statement
	              Map map = connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_EOL_REC", CanonE307EOLRec.class);
	              util.logMsg(false,clsName+".getSrvcEOLDtls Before Execute", "strSerialNumber : "+ strSerialNumber+" svcMachMstrPk: "+svcMachMstrPk);
	              cstmt.execute();
	              util.logMsg(false,clsName+".getSrvcEOLDtls", "After Execute");
	              Object ooF[] =(Object[]) ((Array)cstmt.getObject(2)).getArray();
	              
				  if(ooF!=null && ooF.length>0){
					  util.logMsg(false,clsName+".getSrvcEOLDtls","Length: "+ooF.length);
					  for (int i = 0; i < ooF.length; i++) {
						   list.add(ooF[i]);		
						  util.logMsg(false,clsName+".getMachineEOLDtls","log "+((CanonE307EOLRec)ooF[i]).toString());
					  }	
				  }			  }
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
	     util.logMsg(false,clsName+".getMachineEOLDtls", "Before returning list");
	     return list;
	}  
	public String geHardHoldFlg(String strSerialNumber, String svcMachMstrPk){
        util.logMsg(false, clsName+".geHardHoldFlg", "strSerialNumber : " + strSerialNumber);	        
        CallableStatement statement = null;
        Connection connection = null;
        String hardHoldFlg = "";
        try {
        	 connection = TransactionScope.getConnection();
             if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_HARD_HOLD_FLG);
                if (statement != null) {
                    statement.setObject(2, strSerialNumber, OracleTypes.VARCHAR);
                    statement.setObject(3, svcMachMstrPk);
                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
                    statement.execute();
                    hardHoldFlg =  statement.getString(1);
                    util.logMsg(false, clsName+".geHardHoldFlg", "hardHoldFlg : " + hardHoldFlg);	
                 //   if("Y".equals(hardHoldFlg)){
                    	//String retValue = geHardHoldNotifInfo(strSerialNumber);
                  //   }
                    return hardHoldFlg;
                }else{
                	 util.logMsg(true,clsName+".geHardHoldFlg", " Statement is Null");
                } 
             }else{
            	 util.logMsg(true,clsName+".geHardHoldFlg", " Connection is Null");
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
        return hardHoldFlg;
    }
/*	public String geHardHoldNotifInfo(String strSerialNumber){
	     CallableStatement cstmt = null;
	     Connection connection = null;
	     List<Object> list = new ArrayList<Object>();
	     String retVal ="";
	     try
	       {
			connection = TransactionScope.getConnection();
				
			if (connection != null) {
				cstmt = connection.prepareCall(GET_HARD_HOLD_INFO);
			  if (cstmt != null) {
				  cstmt.setString(1, strSerialNumber);
	              cstmt.registerOutParameter(2, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_HARD_HOLD_INFO_TBL");
				  // Execute the statement
	              Map map = connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_HARD_HOLD_INFO_REC", CanonE307HardHoldInfoRec.class);
	              util.logMsg(false,clsName+".geHardHoldNotifInfo Before Execute", "strSerialNumber : "+ strSerialNumber);
	              cstmt.execute();
	              util.logMsg(false,clsName+".geHardHoldNotifInfo", "After Execute");
	              
	              Object[] oo =(Object[]) ((Array)cstmt.getObject(2)).getArray();
	              
	              util.logMsg(false,clsName+".geHardHoldNotifInfo", "After Object..");
	              
				  if(oo!=null && oo.length>0){
					  util.logMsg(false,clsName+".geHardHoldNotifInfo","Length: "+oo.length);
					  for (int i = 0; i < oo.length; i++) {
						   list.add(oo[i]);		
						  util.logMsg(false,clsName+".geHardHoldNotifInfo","log "+((CanonE307HardHoldInfoRec)oo[i]).toString());
					  }	
				  }
				  if(list!=null && list.size()>0){
					  CanonE307HardHoldInfoRec hhBean = (CanonE307HardHoldInfoRec) list.get(0);
					  System.out.println("hhBean: "+ hhBean.toString());
					  String collectnEmailAddr = hhBean.getStrCEmailAddress()==null?"":hhBean.getStrCEmailAddress();
					  	if(collectnEmailAddr!=null && collectnEmailAddr.length()>0){
					  		
							try {
								
								StringBuffer  sb= new StringBuffer();
								sb.append("The following customer called and attempted to create Service Call but is on a Hard Credit Hold. <br>");
								sb.append("The Call was not created. Please call the customer. <br>");
								sb.append("Customer Name : "+ hhBean.getStrCustomerName()==null?"":hhBean.getStrCustomerName() +"<br>");
								sb.append("Address	      : " + hhBean.getStrCustAddress()==null?"":hhBean.getStrCustAddress() +"<br>");
								sb.append("Contact Name  : " + hhBean.getStrContactName()==null?"":hhBean.getStrContactName() +"<br>");
								sb.append("Contact#      : " + hhBean.getStrContTelNum()==null?"":hhBean.getStrContTelNum() +"<br>");
								sb.append("Model	      : "+ hhBean.getStrModel()==null?"":hhBean.getStrModel() +"<br>");
								sb.append("Serial        : "+ strSerialNumber+"<br>");
								
								System.out.println("eMail Body: "+sb.toString());
								
								String emailSubject = "Service Call is not created - " + hhBean.getStrCustomerName();
								
								retVal = send(emailSubject,"hdoniparthi_consultant@cusa.canon.com",sb.toString());
								if("S".equals(retVal)){
									retVal = updateNotifFlg(strSerialNumber, collectnEmailAddr, "");
									System.out.println("Update Flag retVal: " + retVal);
								}
								//send(emailSubject,"3126223223@tmomail.net",sb.toString());	
							} catch (Exception e) {
								System.out.println(e.getMessage().toString());
							}
					  	}
					  	String dutyManager = hhBean.getStrDutyMngr()==null?"":hhBean.getStrDutyMngr();
					  	if(dutyManager!=null && dutyManager.length()>0){
					  		StringBuffer  sb= new StringBuffer();
					  		sb.append(hhBean.getStrCustomerName()+" Attempted to place a service call, but they are on a credit Hard Hold.  No call was created for them.  Please contact the customer.<br>");
					  		
					  		sb.append("Customer Name : "+ hhBean.getStrCustomerName()==null?"":hhBean.getStrCustomerName() +"<br>");
							sb.append("Address	      : " + hhBean.getStrCustAddress()==null?"":hhBean.getStrCustAddress() +"<br>");
							sb.append("Contact Name  : " + hhBean.getStrContactName()==null?"":hhBean.getStrContactName() +"<br>");
							sb.append("Contact#      : " + hhBean.getStrContTelNum()==null?"":hhBean.getStrContTelNum() +"<br>");
							sb.append("Model	      : "+ hhBean.getStrModel() ==null?"":hhBean.getStrModel() +"<br>");
							
							String emailSubject = "Service Call is not created - " + hhBean.getStrCustomerName();
							
							retVal = send("","hdoniparthi_consultant@cusa.canon.com",sb.toString());
							if("S".equals(retVal)){
								retVal = updateNotifFlg(strSerialNumber, "", hhBean.getStrEmailAddress());
								System.out.println("Update Flag retVal: " + retVal);
							}
					  	}
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
	     util.logMsg(false,clsName+".geHardHoldInfo", "Before returning list");
	     return retVal;
	}*/
		public String updateNotifFlg (String strSerialNum, String strColctnMngrEmail, String strDutyMngrEmail, String machMngrEmail){
			util.logMsg(false,clsName+".updateNotifFlg ", " strSerialNum : "+ strSerialNum+" strDutyMngrEmail : "+strDutyMngrEmail+" strColctnMngrEmail: "+strColctnMngrEmail);
			String retFlag="";
			Connection connection = null;
			CallableStatement cstmt  = null;
			ResultSet rs = null;
			try
			{
				  connection = TransactionScope.getConnection();
				  if(connection!=null)
				  cstmt = connection.prepareCall(UPDATE_NOTIF_FLG);
				  cstmt.setString(1, strSerialNum);
				  cstmt.setString(2, strColctnMngrEmail);
				  cstmt.setString(3, strDutyMngrEmail);
				  cstmt.registerOutParameter(4,Types.VARCHAR);
				  cstmt.registerOutParameter(5,Types.VARCHAR);
				  cstmt.setString(6, machMngrEmail);
				  util.logMsg(false,clsName+".updateNotifFlg Before Execute", " strSerialNum : "+ strSerialNum);
				  cstmt.execute();
	              util.logMsg(false,clsName+".updateNotifFlg After Execute", " strSerialNum : "+ strSerialNum);
				  retFlag = cstmt.getString(4);
				  String retMsg = cstmt.getString(5);
				  System.out.println("retFlag:  "+ retFlag+" retMsg: "+retMsg);
	
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

		  public CanonE307HardHoldInfoRec getNotigMngrDtls(String strSerialNumber, String svcMachMstrPk){
				
				ArrayList<String> arList = new ArrayList<String>();
				ResultSet rs = null;
				Connection connection = null;
				CallableStatement cstmt = null;
				String retVal ="";
				List<Object> list = new ArrayList<Object>();
				CanonE307HardHoldInfoRec hhBean = new CanonE307HardHoldInfoRec();
				try {
					connection = TransactionScope.getConnection();
					cstmt = (CallableStatement)connection.prepareCall(GET_HARD_HOLD_INFO);
					cstmt.setString(1,strSerialNumber);
					cstmt.registerOutParameter(2, OracleTypes.ARRAY,SCH_NAME+".CANON_E307_HARD_HOLD_INFO_TBL");
					cstmt.setString(3,svcMachMstrPk);
					Map map = (Map) connection.getTypeMap();
		            map.put(SCH_NAME+".CANON_E307_HARD_HOLD_INFO_REC", CanonE307HardHoldInfoRec.class);
		            util.logMsg(false,clsName+".geHardHoldNotifInfo","Before Execute");
					cstmt.execute();
					 util.logMsg(false,clsName+".geHardHoldNotifInfo","After Execute");
					Object oo[] =(Object[]) ((Array) cstmt.getObject(2)).getArray();
					 util.logMsg(false,clsName+".geHardHoldNotifInfo","After Array..");
		     		   if(oo!=null && oo.length>0){
		     			  util.logMsg(false,clsName+".geHardHoldNotifInfo","oo.length: "+oo.length);
							  for (int k = 0; k < oo.length; k++) {
								  CanonE307HardHoldInfoRec rec=  (CanonE307HardHoldInfoRec)oo[k] ;
								  util.logMsg(false,clsName+".geHardHoldNotifInfo","log "+((CanonE307HardHoldInfoRec)oo[k]).toString());
								  list.add(oo[k]);
								  //arList.add(oo[i]);	
						       }
							  if(list!=null && list.size()>0){
								  //System.out.println("list.size(): "+ list.size());
								  hhBean = (CanonE307HardHoldInfoRec) list.get(0);
								 // System.out.println("hhBean: "+ hhBean.toString());
							  }
		     		   }
								 
		     		//   util.logMsg(false, clsName+".getProbDetails", arList.toString());
		     		   
				} catch (Exception ex1) {
					util.logMsg(true,clsName+".getProbDetails", ex1.getMessage());
				} finally {
					try {
						if (rs != null)
							rs.close();
						if (cstmt != null)
							cstmt.close();
						if (connection != null)
							TransactionScope.releaseConnection(connection);
					} catch (Exception ex2) {
						util.logMsg(true,clsName+".getProbDetails", ex2.getMessage());
					}
				}
				return hhBean;
			}

}
