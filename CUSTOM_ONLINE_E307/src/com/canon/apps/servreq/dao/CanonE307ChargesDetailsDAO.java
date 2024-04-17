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
import oracle.apps.jtf.base.resources.FrameworkException;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.ArrayDescriptor;

import java.sql.Timestamp;

import canon.apps.common.CanonConstants;

import com.canon.apps.servreq.beans.*;
import com.canon.common.CanonCommonUtil;

public class CanonE307ChargesDetailsDAO extends Object{
	public static final String GET_CHARGES_INFO = "{CALL CANON_E307_CHARGES_PKG.ESTIMATE_HDR_INFO(?,?)}";
	public static final String GET_ESTMT_TSK_INFO = "{CALL CANON_E307_CHARGES_PKG.ESTIMATE_TASK_DETAIL(?,?)}";
	public static final String GET_ESTMT_ITM_INFO = "{CALL CANON_E307_CHARGES_PKG.ESTIMATE_PRICE_DETAIL(?,?)}";
	public static final String GET_CHRG_HDR_INFO = "{CALL CANON_E307_CHARGES_PKG.CHARGE_HDR_INFO(?,?,?)}";
	public static final String GET_CHRG_DTL_INFO = "{CALL CANON_E307_CHARGES_PKG.CHARGE_DETAILS_INFO(?,?,?)}";
	public static final String GET_CHRG_ITEM_LOV = "{CALL CANON_E307_CHARGES_PKG.CHARGE_DETAILS_INFO(?,?)}";
	public static final String GET_TRANSACTION_DTLS = "{CALL CANON_E307_CHARGES_PKG.TRANSACTION_DETAILS(?,?,?,?)}";
	public static final String GET_CHNG_RSN = "{CALL CANON_E307_CHARGES_PKG.CHANGE_REASON_LOV(?)}";	
	public static final String GET_PRC_LST = "{CALL CANON_E307_CHARGES_PKG.PRICE_LIST_LOV(?,?,?,?,?)}";
	public static final String GET_BLNG_TPS = "{CALL CANON_E307_CHARGES_PKG.BILLING_TYPE_LOV(?)}";	
	
	private   String clsName="CanonE307ChargesDetailsDAO";
	CanonCommonUtil util;
	public CanonE307ChargesDetailsDAO() {
		util = new CanonCommonUtil();
	}
	
	public Object[] getBillingEstmtDtls(String strFsrNumber){
		 CallableStatement cstmt = null;
	     Connection connection = null;
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_CHARGES_INFO);
			  if (cstmt != null) {
				  // Set the parameters on the statement
				  cstmt.setString(1, strFsrNumber);
				  cstmt.registerOutParameter(2, OracleTypes.STRUCT, CanonConstants.SCHEMA_NAME+".CANON_E307_EST_HDR_REC");
	              connection.getTypeMap().put(CanonConstants.SCHEMA_NAME+".CANON_E307_EST_HDR_REC", CanonE307BillEstmtHdrRec.class);

	              // Execute the statement
	              util.logMsg(false,clsName+".getBillingEstmtDtls", strFsrNumber);
	              cstmt.execute();
	              util.logMsg(false,clsName+".getBillingEstmtDtls", "After Execute");
	               return new Object[]{cstmt.getObject(2)};
			  }
			  else {
				  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
	          	}
			} else {
				System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	        }
	     } catch (Exception ex) {
	    	 util.logMsg(true,clsName+".getBillingEstmtDtls", ex.getMessage());
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
	public ArrayList<CanonE307BillEstmtTskDtlsRec> getBllEstmtTskDtls(String strFsrNum){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     ArrayList<CanonE307BillEstmtTskDtlsRec> arList = new ArrayList<CanonE307BillEstmtTskDtlsRec>();
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_ESTMT_TSK_INFO);
			  if (cstmt != null) {
				  // Set the parameters on the statement
				  cstmt.setString(1, strFsrNum);
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_EST_TASK_REC", CanonE307BillEstmtTskDtlsRec.class);
	              cstmt.registerOutParameter(2, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_EST_TASK_TBL");
				  // Execute the statement
	              util.logMsg(false,clsName+".getBllEstmtTskDtls", "strFsrNum : "+strFsrNum);
	              cstmt.execute();
	              util.logMsg(false,clsName+".getBllEstmtTskDtls", "After Execute");
	              Object oo[] =(Object[]) ((Array) cstmt.getObject(2)).getArray();
				  if(oo!=null && oo.length>0){
					  System.out.println("Inside for "+oo.length);
					  for (int i = 0; i < oo.length; i++) {
						  CanonE307BillEstmtTskDtlsRec rec=  (CanonE307BillEstmtTskDtlsRec)oo[i] ;
						  arList.add(rec);		
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
	public ArrayList<CanonE307BillEstmtItmDtlsRec> getBllEstmtItmDtls(String strFsrNum){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     ArrayList<CanonE307BillEstmtItmDtlsRec> arList = new ArrayList<CanonE307BillEstmtItmDtlsRec>();
	     try
	       {
			connection = TransactionScope.getConnection();

			if (connection != null) {
				cstmt = connection.prepareCall(GET_ESTMT_ITM_INFO);
			  if (cstmt != null) {
				  // Set the parameters on the statement
				  cstmt.setString(1, strFsrNum);
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_EST_PRICE_REC", CanonE307BillEstmtItmDtlsRec.class);
	              cstmt.registerOutParameter(2, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_EST_PRICE_TBL");
				  // Execute the statement
	              util.logMsg(false,clsName+".getBllEstmtItmDtls Before Execute", " strFsrNum :"+strFsrNum);
	              cstmt.execute();
	              util.logMsg(false,clsName+".getBllEstmtItmDtls ", "After Execute");
	              Object oo[] =(Object[]) ((Array) cstmt.getObject(2)).getArray();
				  if(oo!=null && oo.length>0){
					  for (int i = 0; i < oo.length; i++) {
						  CanonE307BillEstmtItmDtlsRec rec=  (CanonE307BillEstmtItmDtlsRec)oo[i] ;
						  arList.add(rec);		
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
	public Object[] getChrgHdrDtls(String strFsrNumber, String strTskNum){
		 CallableStatement cstmt = null;
	     Connection connection = null;
	     ArrayList arList = null;
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_CHRG_HDR_INFO);
			  if (cstmt != null) {
				  // Set the parameters on the statement
				  cstmt.setString(1, strFsrNumber);
				  cstmt.setString(2, strTskNum);
				  cstmt.registerOutParameter(3, OracleTypes.STRUCT, CanonConstants.SCHEMA_NAME+".CANON_E307_CHG_HDR_REC");
	              connection.getTypeMap().put(CanonConstants.SCHEMA_NAME+".CANON_E307_CHG_HDR_REC", CanonE307ServReqChargesHdrRec.class);

	              // Execute the statement
	              util.logMsg(false,clsName+".getChrgHdrDtls Before Execute", strFsrNumber);
	              cstmt.execute();
	              util.logMsg(false,clsName+".getChrgHdrDtls", " After Execute");

	               return new Object[]{cstmt.getObject(3)};
			  }
			  else {
				  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
	          	}
			} else {
				System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	        }
	     } catch (Exception ex) {
	    	 util.logMsg(true,clsName+".getChrgHdrDtls", ex.getMessage());
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
	    			 util.logMsg(true,clsName+".getChrgHdrDtls", ex.getMessage());
	    		 }
	    	 }
	    	}
		  return null;		
	}	
	public ArrayList<CanonE307ServReqChrgDtlsRec> getChargeDetails(String strFsrNum, String strTskNum){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     ArrayList<CanonE307ServReqChrgDtlsRec> arList = new ArrayList<CanonE307ServReqChrgDtlsRec>();
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_CHRG_DTL_INFO);
			  if (cstmt != null) {
				  // Set the parameters on the statement
				  cstmt.setString(1, strFsrNum);
				  cstmt.setString(2, strTskNum);
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_CHG_DTL_REC", CanonE307ServReqChrgDtlsRec.class);
	              cstmt.registerOutParameter(3, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_CHG_DTL_TBL");
				  // Execute the statement
	              util.logMsg(false,clsName+".getChargeDetails before Execute", strFsrNum);
	              cstmt.execute();
	              util.logMsg(false,clsName+".getChargeDetails ","after execute");

	              Object oo[] =(Object[]) ((Array) cstmt.getObject(3)).getArray();
				  if(oo!=null && oo.length>0){
					  for (int i = 0; i < oo.length; i++) {
						  CanonE307ServReqChrgDtlsRec rec=  (CanonE307ServReqChrgDtlsRec)oo[i] ;
						  arList.add(rec);		
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
	    	 util.logMsg(true,clsName+".getChargeDetails ",ex.getMessage());
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
	public Object[] getChargeDetailsLov(String strItemCd, int start, int end){
		CallableStatement cstmt = null;
	     Connection connection = null;
	     try
	       {
			connection = TransactionScope.getConnection();
			List<Object> laborList = new ArrayList<Object>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_CHRG_ITEM_LOV);
			  if (cstmt != null) {
				  // Set the parameters on the statement
				  cstmt.setObject(1, strItemCd, OracleTypes.VARCHAR);
				  cstmt.setInt(2, start);
				  cstmt.setInt(3, end);
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_ITEM_LOV_REC", CanonE307DebriefItemLov.class);
	           //  ArrayDescriptor insDescriptor = ArrayDescriptor.createDescriptor("CANON.CANON_E307_DEBRIEF_NOTE_TBL", connection);
	              cstmt.registerOutParameter(4, OracleTypes.INTEGER);
	              cstmt.registerOutParameter(5, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_ITEM_LOV_TBL");
				  // Execute the statement
	              util.logMsg(false,clsName+".getChargeDetailsLov before Execute", strItemCd);
	              cstmt.execute();
	              util.logMsg(false,clsName+".getChargeDetailsLov ", "after Execute");
	              Object[] oo = (Object[]) ((Array)cstmt.getObject(5)).getArray();
				  if(oo!=null && oo.length>0){
					  for (int i = 0; i < oo.length; i++) {
						  laborList.add(oo[i]);			       
					  }	
					  return new Object[]{
							  	laborList
		                        ,cstmt.getObject(5)
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
	    	 util.logMsg(true,clsName+".getChargeDetailsLov ",ex.getMessage());
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
	public Object[] getTransactionDtls(String strFsrNumber, String strTaskNum, String strBillTpe){
		 CallableStatement cstmt = null;
	     Connection connection = null;
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_TRANSACTION_DTLS);
			  if (cstmt != null) {
				  // Set the parameters on the statement
				  cstmt.setString(1, strFsrNumber);
				  cstmt.setString(2, strTaskNum);
				  cstmt.setString(3, strBillTpe);
				  cstmt.registerOutParameter(4, OracleTypes.STRUCT, CanonConstants.SCHEMA_NAME+".CANON_E307_TRX_DTL_REC");
	              connection.getTypeMap().put(CanonConstants.SCHEMA_NAME+".CANON_E307_TRX_DTL_REC", CanonE307ServReqChrgTrnsactDtlsRec.class);

	              // Execute the statement
	              util.logMsg(false,clsName+".getTransactionDtls Before Execute", "strFsrNumber : "+strFsrNumber+"strTaskNum : "+strTaskNum+" strBillTpe : "+strBillTpe);
	              cstmt.execute();
	              util.logMsg(false,clsName+".getTransactionDtls","After Execute");

	               return new Object[]{cstmt.getObject(4)};
			  }
			  else {
				  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
	          	}
			} else {
				System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	        }
	     } catch (Exception ex) {
	    	 util.logMsg(true,clsName+".getTransactionDtls", ex.getMessage());
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
	    			 util.logMsg(true,clsName+".getTransactionDetails", ex.getMessage());
	    		 }
	    	 }
	    	}
		  return null;		
	}	
	public ArrayList<CanonE307ChargesChngRsnRec> getChangRsn(){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     ArrayList<CanonE307ChargesChngRsnRec> arList = new ArrayList<CanonE307ChargesChngRsnRec>();
	     try
	       {
			connection = TransactionScope.getConnection();

			if (connection != null) {
				cstmt = connection.prepareCall(GET_CHNG_RSN);
			  if (cstmt != null) {
				  // Set the parameters on the statement
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_REASON_CODE_REC", CanonE307ChargesChngRsnRec.class);
	              cstmt.registerOutParameter(1, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_CHNG_REASON_TBL");
				  // Execute the statement
	              util.logMsg(false,clsName+".getChangRsn","Before Execute");
	              cstmt.execute();
	              util.logMsg(false,clsName+".getChangRsn","After Execute");
	              Object oo[] =(Object[]) ((Array) cstmt.getObject(1)).getArray();
				  if(oo!=null && oo.length>0){
					  for (int i = 0; i < oo.length; i++) {
						  CanonE307ChargesChngRsnRec rec=  (CanonE307ChargesChngRsnRec)oo[i] ;
						  arList.add(rec);		
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
	    	 util.logMsg(true,clsName+".getChangRsn ",ex.getMessage());
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
	public Object[] getPriceList(int start, int end, String prsLstNm){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     ArrayList<CanonE307ChargesChngRsnRec> arList = new ArrayList<CanonE307ChargesChngRsnRec>();
	     try
	       {
			connection = TransactionScope.getConnection();
			List<Object> prcList = new ArrayList<Object>();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_PRC_LST);
			  if (cstmt != null) {
				  // Set the parameters on the statement
				  cstmt.setString(1, prsLstNm);
				  cstmt.setInt(2, start);
				  cstmt.setInt(3, end);
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_REASON_CODE_REC", CanonE307ChargesChngRsnRec.class);
	              cstmt.registerOutParameter(4, OracleTypes.INTEGER);
	              cstmt.registerOutParameter(5, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_CHNG_REASON_TBL");
				  // Execute the statement
	              util.logMsg(false,clsName+".getPriceList","Before Execute");
	              cstmt.execute();
	              util.logMsg(false,clsName+".getPriceList","After Execute");
	              
	              Object[] oo = (Object[]) ((Array)cstmt.getObject(5)).getArray();
				  if(oo!=null && oo.length>0){
					  for (int i = 0; i < oo.length; i++) {
						  prcList.add(oo[i]);			       
					  }	
					  return new Object[]{
							   prcList
		                      ,cstmt.getObject(4)
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
	    	 util.logMsg(true,clsName+".getPriceList ",ex.getMessage());
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
	public ArrayList<CanonE307ChargesChngRsnRec> getBlngTypes(){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     ArrayList<CanonE307ChargesChngRsnRec> arList = new ArrayList<CanonE307ChargesChngRsnRec>();
	     try
	       {
			connection = TransactionScope.getConnection();

			if (connection != null) {
				cstmt = connection.prepareCall(GET_BLNG_TPS);
			  if (cstmt != null) {
				  // Set the parameters on the statement
	              Map map = (Map) connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_REASON_CODE_REC", CanonE307ChargesChngRsnRec.class);
	              cstmt.registerOutParameter(1, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_CHNG_REASON_TBL");
				  // Execute the statement
	              util.logMsg(false,clsName+".getBlngTypes","Before Execute");
	              cstmt.execute();
	              util.logMsg(false,clsName+".getBlngTypes","After Execute");
	              Object oo[] =(Object[]) ((Array) cstmt.getObject(1)).getArray();
				  if(oo!=null && oo.length>0){
					  for (int i = 0; i < oo.length; i++) {
						  CanonE307ChargesChngRsnRec rec=  (CanonE307ChargesChngRsnRec)oo[i] ;
						  arList.add(rec);		
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
	    	 util.logMsg(true,clsName+".getBlngTypes ",ex.getMessage());
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
    public static void main(String[] args) throws SQLException{
    	CanonE307ChargesDetailsDAO dao = new CanonE307ChargesDetailsDAO();
        //dao.getTabs();
       ArrayList lst =  dao.getBllEstmtTskDtls("6284");
       if(lst!=null && lst.size()>0){
    	   System.out.println("Size : "+ lst.size());
       }
        
    }
}
