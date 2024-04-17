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
import java.util.List;
import java.util.Map;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import canon.apps.common.CanonConstants;

import com.canon.apps.servreq.beans.CanonE307BillCodeRec;
import com.canon.apps.servreq.beans.CanonE307BillToCustCreditAuthBean;
import com.canon.apps.servreq.beans.CanonE307DebriefDetailsRec;
import com.canon.apps.servreq.beans.CanonE307DebriefNoteTypeRec;
import com.canon.apps.servreq.beans.CanonE307MachineCustSearchResultsRec;
import com.canon.apps.servreq.beans.CanonE307NoteTypeRec;
import com.canon.apps.servreq.beans.CanonE307ServiceReqCallAvdRec;
import com.canon.apps.servreq.beans.CanonE307ServiceReqCallInfoRec;
import com.canon.apps.servreq.beans.CanonE307ServiceReqContractRec;
import com.canon.apps.servreq.beans.CanonE307ServiceReqCustomerRec;
import com.canon.apps.servreq.beans.CanonE307ServiceReqErrorCodesRec;
import com.canon.apps.servreq.beans.CanonE307ServiceReqLocRec;
import com.canon.apps.servreq.beans.CanonE307ServiceReqProbRec;
import com.canon.apps.servreq.beans.CanonE307ServiceReqProbUtilRec;
import com.canon.apps.servreq.beans.CanonE307ServiceReqRmdDtlsRec;
import com.canon.common.CanonCommonUtil;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetRspTime;

public class CanonE307ServiceReqCreateDao {
 

	private  String clsName="CanonE307ServiceReqCreateDao";
	private  final String GET_XML = "{CALL CANON_E307_CREATE_SR_PKG.LOOKUP_BY_XML_MESSAGE(?,?)}";
	public   final String GET_PROBLEM ="{CALL CANON_E307_CREATE_SR_PKG.PROBLEM_CODE_LOV(?,?,?,?,?)}";
	public   final String GET_REMEDY_DETAILS ="{CALL CANON_E307_CREATE_SR_PKG.REMEDY_DETAILS(?,?,?)}";
	public   final String GET_CALL_DETAILS ="{call CANON_E307_CREATE_SR_PKG.GET_CALL_DETAILS(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	public   final String GET_OPEN_SR_NUM ="{CALL CANON_E307_CREATE_SR_PKG.GET_OPEN_SR_NUM(?,?)}";
	public   final String GET_OPEN_SR_NUMS ="{CALL CANON_E307_CREATE_SR_PKG.GET_OPEN_SR_NUMS(?,?,?)}";	
	public   final String GET_CALL_AVOIDANCE ="{CALL CANON_E307_CREATE_SR_PKG.GET_CALL_AVOIDANCE(?)}";
	public   final String GET_COPY_FSR ="{call CANON_E307_CREATE_SR_PKG.COPY_FSR(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	public   final String GET_CUST_INFO = "{CALL CANON_E307_CREATE_SR_PKG.GET_CREDIT_CUST_INFO(?,?,?,?,?,?,?,?)}";
	public   final String GET_BLLBL_FLG = "{?= call CANON_E307_CREATE_SR_PKG.GET_AFTER_HOURS_BILL_FLAG(?)}";
	public   final String GET_HOUR_VAL ="{CALL CANON_E307_CREATE_SR_PKG.GET_HOUR_VAL(?)}";
	public   final String GET_MIN_VAL ="{CALL CANON_E307_CREATE_SR_PKG.GET_MIN_VAL(?)}";
	public   final String GET_NOTE_TYPE ="{CALL CANON_E307_CREATE_SR_PKG.GET_NOTE_TYPES(?)}";
	public   final String GET_BILL_CODE ="{CALL CANON_E307_CREATE_SR_PKG.GET_BILL_CODES(?)}";	
	public   final String GET_CALL_TYPE ="{CALL CANON_E307_CREATE_SR_PKG.GLOBAL_LEVEL_RECALL(?,?,?,?,?)}";
	public   final String GET_CR_TYPE = "{?= call CANON_E307_CREATE_SR_PKG.GET_CARD_TYPE(?)}";
	public   final String GET_COV_TM ="{CALL CANON_E307_CREATE_SR_PKG.GET_COVERAGE_TIME(?,?,?)}";
	public   final String GET_TASK_UPD_FLG = "{?= call CANON_E307_CREATE_SR_PKG.GET_TASK_UPDATE_FLG(?)}";
	public   final String GET_EXCLUSION_PARTS ="{CALL CANON_E307_CREATE_SR_PKG.GET_EXCLUSION_PARTS(?,?,?)}";
	public static final String GET_CROSS_SRVC_FLG = "{?= call CANON_E307_DSPTCH_PKG.GET_CROSS_SRVC_FLG(?,?)}";
	
	CanonCommonUtil util;
	
	String SCH_NAME="";
  public CanonE307ServiceReqCreateDao(){
	 util = new CanonCommonUtil();
	 SCH_NAME = CanonConstants.SCHEMA_NAME;
  }
  
  
  public   ArrayList<String> getProbDetails( String attr, String mdl, String type, String desc ){
		
		ArrayList<String> arList = new ArrayList<String>();
		ResultSet rs = null;
		Connection connection = null;
		CallableStatement cstmt = null;

		try {
			connection = TransactionScope.getConnection();
			cstmt = (CallableStatement)connection.prepareCall(GET_PROBLEM);
			cstmt.setString(1,attr);
			cstmt.setString(2,mdl);
			cstmt.setString(3,type);
			cstmt.setString(4,desc);
			cstmt.registerOutParameter(5, OracleTypes.ARRAY,SCH_NAME+".CANON_E307_LOV_TBL");
			Map map = (Map) connection.getTypeMap();
            map.put(SCH_NAME+".CANON_E307_LOV_REC", CanonE307ServiceReqProbUtilRec.class);
			cstmt.execute();
			Object oo[] =(Object[]) ((Array) cstmt.getObject(5)).getArray();
     		    
     		   if(oo!=null && oo.length>0){
					  for (int k = 0; k < oo.length; k++) {
						  CanonE307ServiceReqProbUtilRec rec=  (CanonE307ServiceReqProbUtilRec)oo[k] ;
						  arList.add( util.checkNull(rec.getProbValue()) );	
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
		return arList;
	}
  
  
    public   ArrayList<CanonE307ServiceReqCallAvdRec> getCallAvoidanceDtls(){
		
		ArrayList<CanonE307ServiceReqCallAvdRec> arList = new ArrayList<CanonE307ServiceReqCallAvdRec>();
		ResultSet rs = null;
		Connection connection = null;
		CallableStatement cstmt = null;

		try {
			connection = TransactionScope.getConnection();
			cstmt = (CallableStatement)connection.prepareCall(GET_CALL_AVOIDANCE);
			cstmt.registerOutParameter(1, OracleTypes.ARRAY,SCH_NAME+".CANON_E307_CALL_AVOID_TBL");
			Map map = (Map) connection.getTypeMap();
            map.put(SCH_NAME+".CANON_E307_CALL_AVOID_REC", CanonE307ServiceReqCallAvdRec.class);
			 cstmt.execute();
				
			  Object oo[] =(Object[]) ((Array) cstmt.getObject(1)).getArray();
			 
     		   if(oo!=null && oo.length>0){
					  for (int k = 0; k < oo.length; k++) {
						  CanonE307ServiceReqCallAvdRec rec=  (CanonE307ServiceReqCallAvdRec)oo[k] ;
						  arList.add( rec);		
				       }	
     		   }  
	   		   
		} catch (Exception ex1) {
			util.logMsg(true,clsName+".getCallAvoidanceDtls", ex1.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
				if (connection != null)
					TransactionScope.releaseConnection(connection);
			} catch (Exception ex2) {
				util.logMsg(true,clsName+".getCallAvoidanceDtls", ex2.getMessage());
			}
		}
		return arList;
	}
  
  public  ArrayList<CanonE307ServiceReqRmdDtlsRec> getRmdyDetails( String mdl, String pbCd){
		
		ArrayList<CanonE307ServiceReqRmdDtlsRec> arList = new ArrayList<CanonE307ServiceReqRmdDtlsRec>();
		ResultSet rs = null;
		Connection connection = null;
		CallableStatement cstmt = null;

		try {
			connection = TransactionScope.getConnection();
			cstmt = (CallableStatement)connection.prepareCall(GET_REMEDY_DETAILS);
			cstmt.setString(1,mdl);
			cstmt.setString(2,pbCd);
			cstmt.registerOutParameter(3, OracleTypes.ARRAY,SCH_NAME+".CANON_E307_REMEDY_TBL");
			Map map = (Map) connection.getTypeMap();
            map.put(SCH_NAME+".CANON_E307_REMEDY_REC", CanonE307ServiceReqRmdDtlsRec.class);
			cstmt.execute();
			Object oo[] =(Object[]) ((Array) cstmt.getObject(3)).getArray();
			   if(oo!=null && oo.length>0){
					  for (int k = 0; k < oo.length; k++) {
						  CanonE307ServiceReqRmdDtlsRec rec=  (CanonE307ServiceReqRmdDtlsRec)oo[k] ;
						  arList.add( rec);		
				       }	
     		   }  
	   		   
		} catch (Exception ex1) {
			util.logMsg(true,clsName+".getRmdyDetails", ex1.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
				if (connection != null)
					TransactionScope.releaseConnection(connection);
			} catch (Exception ex2) {
				util.logMsg(true,clsName+".getRmdyDetails", ex2.getMessage());
			}
		}
		return arList;
	}


  
  public String getOpenSR( String ser, String svcMachMstrPk){
		String res="";
		ResultSet rs = null;
		Connection connection = null;
		CallableStatement cstmt = null;
		try {
			connection = TransactionScope.getConnection();
			cstmt = (CallableStatement)connection.prepareCall(GET_OPEN_SR_NUMS);
			cstmt.setString(1,ser);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.setString(3, svcMachMstrPk);
		    cstmt.execute();
		    rs = (ResultSet) cstmt.getObject(2);
		    while (rs.next()) {
		    	res = res + rs.getString("FSR_NUM")+", ";
		    }
		} catch (Exception ex1) {
			util.logMsg(true,clsName+".getOpenSR", ex1.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
				if (connection != null)
					TransactionScope.releaseConnection(connection);
			} catch (Exception ex2) {
				util.logMsg(true,clsName+".getOpenSR", ex2.getMessage());
			}
		}
		util.logMsg(false,clsName+".getOpenSR res: ", res);
		return res;
	}
  
  
  public  Object[] getCallDetails( String srNum, String mdl, String dsContrPk){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     
	     Object[] resObj = new Object[11]; 
	   //  util.logMsg(false,clsName+".getCallDetails", " Getting Call details");
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = (CallableStatement) connection.prepareCall(GET_CALL_DETAILS);
			  if (cstmt != null) {
				
				  cstmt.setString(1,srNum);
	              cstmt.setString(2, mdl);
				  cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
				  cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
				  cstmt.registerOutParameter(5, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_MAC_SER_TBL");
	              cstmt.registerOutParameter(6, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_UGW_ERR_CODE_TBL");
	              cstmt.registerOutParameter(7, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_PROB_CODE_TBL");
	              cstmt.registerOutParameter(8, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_CALL_INFO_TBL");
	              cstmt.registerOutParameter(9, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_CONTRACT_TBL");
	              cstmt.registerOutParameter(10, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_CUST_LOC_TBL");
	              cstmt.registerOutParameter(11, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_CUST_LOC_TBL");
	              cstmt.registerOutParameter(12, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_DEBRIEF_NOTE_TBL");
	              cstmt.registerOutParameter(13, OracleTypes.VARCHAR);
	              cstmt.setString(14, dsContrPk);

	              Map map = (Map) connection.getTypeMap();
	              map.put(SCH_NAME+".CANON_E307_MAC_SER_REC", CanonE307MachineCustSearchResultsRec.class); 
	              map.put(SCH_NAME+".CANON_E307_UGW_ERR_CODE_REC", CanonE307ServiceReqErrorCodesRec.class);
	              map.put(SCH_NAME+".CANON_E307_PROB_CODE_REC", CanonE307ServiceReqProbRec.class);
	              map.put(SCH_NAME+".CANON_E307_CALL_INFO_REC", CanonE307ServiceReqCallInfoRec.class);
	              map.put(SCH_NAME+".CANON_E307_CONTRACT_REC", CanonE307ServiceReqContractRec.class);
	              map.put(SCH_NAME+".CANON_E307_CUST_LOC_REC", CanonE307ServiceReqLocRec.class);
	              map.put(SCH_NAME+".CANON_E307_CUST_LOC_REC", CanonE307ServiceReqLocRec.class);
	              map.put(SCH_NAME+".CANON_E307_DEBRIEF_NOTE_REC", CanonE307DebriefNoteTypeRec.class);
	              util.logMsg(false,clsName+".getCallDetails", "Before execute : ");
	              cstmt.execute();
	              
	              for (int i=3; i<=13;i++){
	            	  if(i==3 || i==4 || i==13){
	            		 resObj[i-3]= util.checkNull(cstmt.getString(i));
	            		 util.logMsg(false,clsName+".getCallDetails", "SLA & Vip flg && Call Dur for Mdl : "+i+"-"+cstmt.getString(i));
	            	  }else{
	            		  util.logMsg(false,clsName+".In else getCallDetails", "After execute : "+i);
	            		  Object oo[] =(Object[]) ((Array) cstmt.getObject(i)).getArray();
	            		  List<Object> list = new ArrayList<Object>();
	            		    
	            		   if(oo!=null && oo.length>0){
	    					  for (int k = 0; k < oo.length; k++) {
	    						   list.add(oo[k]);		
	    				         }	
	    					  util.logMsg(false,clsName+".getCallDetails", "After execute : "+i);
	    				    if(i==5){	
	    				    	 CanonE307MachineCustSearchResultsRec mcsr = (CanonE307MachineCustSearchResultsRec) list.get(0); 
	    				    	 resObj[i-3]=mcsr;
	    				    }else if(i==6){
	    				    	//error codes
	    				    	 resObj[i-3]=list;
	    				    }else if(i==7){
	    				    	CanonE307ServiceReqProbRec pbRec= (CanonE307ServiceReqProbRec) list.get(0);
	    				    	resObj[i-3]=pbRec;
	    				    }else if(i==8){
	    				    	CanonE307ServiceReqCallInfoRec ciRec = (CanonE307ServiceReqCallInfoRec) list.get(0);
	    				    	resObj[i-3]=ciRec;
	    				    }else if(i==9){
	    				    	CanonE307ServiceReqContractRec ctrRec =(CanonE307ServiceReqContractRec) list.get(0);
	    				    	resObj[i-3]=ctrRec;
	    				    }else if(i==10 || i==11){
	    				    	CanonE307ServiceReqLocRec locRec = (CanonE307ServiceReqLocRec) list.get(0);
	    				    	resObj[i-3]=locRec;
	    				    }else if(i==12){
	    				    	 resObj[i-3]=list;
	    				    }
	    				  }
	            		  
	            	  }
	              }
	              printRes(resObj); 
	        }else {
	        	util.logMsg(true,clsName+".getCallDetails", " CallableStatement IS NULL");
			}
			} else {
				util.logMsg(true,clsName+".getCallDetails", " Connection IS NULL");
		    }
			
	     } catch (Exception ex) {
	    	 util.logMsg(true,clsName+".getCallDetails", ex.getMessage());
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			 util.logMsg(true,clsName+".getCallDetails", exp.getMessage());
	    			  
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 util.logMsg(true,clsName+".getCallDetails", ex.getMessage());
	    		 }
	    	 }
	    	}
		  return resObj;		
	}	
  
  

	public  Object[] getCustomer(String custName, String accNum , int start, int end,String sortBy, String sortOrder){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     
	     Object[] resObj = new Object[10];
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = (CallableStatement) connection.prepareCall("{call CANON_E307_CREATE_SR_PKG.CUST_NAME_LOV(?,?,?,?,?,?,?,?)}");
			  if (cstmt != null) {
				
				  
				  cstmt.setString(1,custName);
				  cstmt.setString(2,accNum);
	              cstmt.setInt(3, start);
	              cstmt.setInt(4, end);
	              cstmt.setString(5, sortBy);
	              cstmt.setString(6, sortOrder);
				 
	              cstmt.registerOutParameter(7, OracleTypes.NUMBER);
	              cstmt.registerOutParameter(8, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_CUST_NAME_LOV_TBL");
	              
	              Map map = (Map) connection.getTypeMap();
	              map.put(SCH_NAME+".CANON_E307_CUST_NAME_LOV_REC", CanonE307ServiceReqCustomerRec.class);
	              
	              cstmt.execute();
	              
	              int totRecords = cstmt.getInt(7);
	              resObj[0]=totRecords;
	              
	              Object oo[] =(Object[]) ((Array) cstmt.getObject(8)).getArray();
        		  List<CanonE307ServiceReqCustomerRec> list = new ArrayList<CanonE307ServiceReqCustomerRec>();
        		  util.logMsg(false, clsName+".getCustomer", "Total Records :" + totRecords);
	             
        		  if(oo!=null && oo.length>0){
					  for (int k = 0; k < oo.length; k++) {
						   list.add((CanonE307ServiceReqCustomerRec)oo[k]);		
				         }	
      		       }  
      		   
        		  resObj[1]=list; 
              
			  }else {
        	  util.logMsg(true,clsName+".getCustomer", " CallableStatement IS NULL");
	          }
		  } else {
				util.logMsg(true,clsName+".getCustomer", " Connection IS NULL");
	      }
			
	     } catch (Exception ex) {
	    	 util.logMsg(true,clsName+".getCustomer", ex.getMessage());
	    	 
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			 util.logMsg(true,clsName+".getCustomer", exp.getMessage());
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 util.logMsg(true,clsName+".getCustomer", ex.getMessage());
	    		 }
	    	 }
	    	}
		  return resObj;		
	}	
	
  
	public  Object[] getCustLocation(String locType,String custAcctNum, String addr, int start, int end,String sortBy, String sortOrder){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     
	     Object[] resObj = new Object[10];
	     
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = (CallableStatement) connection.prepareCall("{call CANON_E307_CREATE_SR_PKG.CUST_ADDR_LOV(?,?,?,?,?,?,?,?,?)}");
			  if (cstmt != null) {
				
				  cstmt.setString(1,locType);
				  cstmt.setString(2,custAcctNum);
				  cstmt.setString(3,addr);
	              cstmt.setInt(4, start);
	              cstmt.setInt(5, end);
	              cstmt.setString(6, sortBy);
	              cstmt.setString(7, sortOrder);
				 
	              cstmt.registerOutParameter(8, OracleTypes.NUMBER);
	              cstmt.registerOutParameter(9, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_CUST_LOC_TBL");
	              
	              Map map = (Map) connection.getTypeMap();
	              map.put(SCH_NAME+".CANON_E307_CUST_LOC_REC", CanonE307ServiceReqLocRec.class);
	              
	              cstmt.execute();
	              
	              int totRecords = cstmt.getInt(8);
	              util.logMsg(false, clsName+".getCustLocation", " Total Records :" + totRecords);
	              resObj[0]=totRecords;
	              
	              Object oo[] =(Object[]) ((Array) cstmt.getObject(9)).getArray();
        		  List<CanonE307ServiceReqLocRec> list = new ArrayList<CanonE307ServiceReqLocRec>();
        		    
        		   if(oo!=null && oo.length>0){
					  for (int k = 0; k < oo.length; k++) {
						 System.out.println( oo[k].toString());
						   list.add((CanonE307ServiceReqLocRec)oo[k]);		
				         }	
        		   }  
        		
        		   util.logMsg(false, clsName, list.toString());
        		  resObj[1]=list; 
            }else {
            	util.logMsg(true,clsName+".getCustLocation", " CallableStatement IS NULL");
				  
	        }
			} else {
				util.logMsg(true,clsName+".getCustLocation", " Connection IS NULL");
	        }
			
	     } catch (Exception ex) {
	    	 util.logMsg(true,clsName+".getCustLocation", ex.getMessage());
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			 util.logMsg(true,clsName+".getCustLocation", exp.getMessage());
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 util.logMsg(true,clsName+".getCustLocation", ex.getMessage());
	    		 }
	    	 }
	    	}
		  return resObj;		
	}	
	
	public static void printRes( Object[] resObj){
		
	      
		  for (int k = 0; k < resObj.length; k++) {
			   
			  if(resObj[k]!=null) {
						  if(resObj[k] instanceof String){
							  System.out.println(k + " : " +resObj[k]);
						  }else if( resObj[k] instanceof List){
							  
							  List l = (ArrayList) resObj[k] ;
							      for(int j=0;j<l.size();j++)
							       System.out.println( k+" : "+ ( l.get(j) ) .toString() );
						            }
					  
                         else{
							  
							  System.out.println(k + " : "+ resObj[k]);
						    } 
						  
					  
				  }	 else{
					  
					  System.out.println(k + " : "+ resObj[k]);
				  } 
		  }	
}

  public  String getXml(String xmlInput) {

 		Connection connection = null;
 		CallableStatement cstmt = null;
 		String xmlOut = "";
 		util.logMsg(false, clsName+".getXml ","[ XML INPUT ]/n"+ xmlInput);
 		
 		try { 
 			connection = TransactionScope.getConnection();
 			cstmt = connection.prepareCall(GET_XML);
 			cstmt.setString(1, xmlInput);
 			cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
 			cstmt.execute();
 			xmlOut = ((CallableStatement) cstmt).getString(2);
 		} catch (Exception e1) {
 			util.logMsg(true, clsName+".getXml", e1.getMessage());
 		} finally {
 			try {
 				if (cstmt != null)
 					cstmt.close();
 				if (connection != null)
 					TransactionScope.releaseConnection(connection);
 			} catch (Exception e2) {
 				util.logMsg(true, clsName+".getXml", e2.getMessage());
 			}
 		}
 		return xmlOut;
 	}
  public  Object[] getCallDetailsByFsr(String fsrNumber, String dsContrPk){
	    CallableStatement cstmt = null;
	     Connection connection = null;
	     System.out.println("Inside getCallDetailsByFsr: "+ fsrNumber+" dsContrPk: "+dsContrPk);
	     Object[] resObj = new Object[10];
	     util.logMsg(false,clsName+".getCallDetails", " Getting C");
	     try
	       {
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = (CallableStatement) connection.prepareCall(GET_COPY_FSR);
			  if (cstmt != null) {
				
				  cstmt.setString(1,fsrNumber);
				  cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
				  cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
				  cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
				  cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
				  cstmt.registerOutParameter(6, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_MAC_SER_TBL");
	              cstmt.registerOutParameter(7, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_UGW_ERR_CODE_TBL");
	              cstmt.registerOutParameter(8, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_PROB_CODE_TBL");
	              cstmt.registerOutParameter(9, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_CALL_INFO_TBL");
	              cstmt.registerOutParameter(10, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_CONTRACT_TBL");
	              cstmt.registerOutParameter(11, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_CUST_LOC_TBL");
	              cstmt.registerOutParameter(12, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_CUST_LOC_TBL");
	              cstmt.registerOutParameter(13, OracleTypes.ARRAY, SCH_NAME+".CANON_E307_DEBRIEF_NOTE_TBL");
	              cstmt.setString(14, dsContrPk);
	              
	              Map map = (Map) connection.getTypeMap();
	              map.put(SCH_NAME+".CANON_E307_MAC_SER_REC", CanonE307MachineCustSearchResultsRec.class); 
	              map.put(SCH_NAME+".CANON_E307_UGW_ERR_CODE_REC", CanonE307ServiceReqErrorCodesRec.class);
	              map.put(SCH_NAME+".CANON_E307_PROB_CODE_REC", CanonE307ServiceReqProbRec.class);
	              map.put(SCH_NAME+".CANON_E307_CALL_INFO_REC", CanonE307ServiceReqCallInfoRec.class);
	              map.put(SCH_NAME+".CANON_E307_CONTRACT_REC", CanonE307ServiceReqContractRec.class);
	              map.put(SCH_NAME+".CANON_E307_CUST_LOC_REC", CanonE307ServiceReqLocRec.class);
	              map.put(SCH_NAME+".CANON_E307_CUST_LOC_REC", CanonE307ServiceReqLocRec.class);
	              map.put(SCH_NAME+".CANON_E307_DEBRIEF_NOTE_REC", CanonE307DebriefNoteTypeRec.class);
	              
	              System.out.println("Before execute..");
	              cstmt.execute();
	              System.out.println("After execute");
	              for (int i=2; i<=13;i++){
	            	  if(i==2 || i==3){
	            		  System.out.println("iVal if: "+ i);  
	            		  resObj[i-2]= util.checkNull(cstmt.getString(i));
	            		 util.logMsg(false,clsName+".getCallDetailsByFsr", "SLA & Vip flg : "+i+"-"+cstmt.getString(i));
	            	  }else if(i==4 || i==5){
	            		  // do nothing . this param is for future use
	            		  System.out.println("iVal else if: "+ i);  
	            	  }else{
	            		  System.out.println("iVal else: "+ i);  
	            		  Object oo[] =(Object[]) ((Array) cstmt.getObject(i)).getArray();
	            		  List<Object> list = new ArrayList<Object>();
	            		    
	            		   if(oo!=null && oo.length>0){
	    					  for (int k = 0; k < oo.length; k++) {
	    						   list.add(oo[k]);		
	    				         }	
	    				    if(i==6){	
	    				    	 CanonE307MachineCustSearchResultsRec mcsr = (CanonE307MachineCustSearchResultsRec) list.get(0); 
	    				    	 resObj[i-4]=mcsr;
	    				    }else if(i==7){
	    				    	//error codes
	    				    	 resObj[i-7]=list;
	    				    }else if(i==8){
	    				    	CanonE307ServiceReqProbRec pbRec= (CanonE307ServiceReqProbRec) list.get(0);
	    				    	resObj[i-4]=pbRec;
	    				    }else if(i==9){
	    				    	CanonE307ServiceReqCallInfoRec ciRec = (CanonE307ServiceReqCallInfoRec) list.get(0);
	    				    	resObj[i-4]=ciRec;
	    				    }else if(i==10){
	    				    	CanonE307ServiceReqContractRec ctrRec =(CanonE307ServiceReqContractRec) list.get(0);
	    				    	resObj[i-4]=ctrRec;
	    				    }else if(i==11 || i==12){
	    				    	CanonE307ServiceReqLocRec locRec = (CanonE307ServiceReqLocRec) list.get(0);
	    				    	resObj[i-4]=locRec;
	    				    }else if(i==13){
	    				    	 resObj[i-4]=list;
	    				    }
	    				  }
	            		  
	            	  }
	              }
	              
	              util.logMsg(false,clsName+".getCallDetailsByFsr", "getCallDetailsByFsr OUT PARAMETERS  "); 
	              printRes(resObj);
	        }else {
	        	util.logMsg(true,clsName+".getCallDetailsByFsr", " CallableStatement IS NULL");
			}
			} else {
				util.logMsg(true,clsName+".getCallDetailsByFsr", " Connection IS NULL");
		    }
			
	     } catch (Exception ex) {
	    	 util.logMsg(true,clsName+".getCallDetailsByFsr", ex.getMessage());
	     } finally {
	    	 if (cstmt != null) {
	    		 try {
	    			 cstmt.close();
	    			 cstmt = null;
	    		 } catch (Exception exp) {
	    			 util.logMsg(true,clsName+".getCallDetailsByFsr", exp.getMessage());
	    			  
	    		 }
	    	 }
	    	 if (connection != null) {
	    		 try {
	    			 TransactionScope.releaseConnection(connection);
	    		 } catch (Exception ex) {
	    			 util.logMsg(true,clsName+".getCallDetails", ex.getMessage());
	    		 }
	    	 }
	    	}
		  return resObj;		
	} 
 
	public CanonE307BillToCustCreditAuthBean getCustInfo(String strSerialNum, String svcMachMstrPk){
		Connection connection = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		CanonE307BillToCustCreditAuthBean custObj = new CanonE307BillToCustCreditAuthBean();
		try {
			
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_CUST_INFO);
				if (cstmt !=null){
					cstmt.setString(1, strSerialNum);
					cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
					cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
					cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
					cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
					cstmt.registerOutParameter(6, OracleTypes.VARCHAR);		
					cstmt.registerOutParameter(7, OracleTypes.CURSOR);
					cstmt.setString(8, svcMachMstrPk);
			
					cstmt.execute();
					util.logMsg(false,clsName+".getCustInfo", " After execute0");
					rs = (ResultSet) cstmt.getObject(7);
					custObj.setStrMrchntId((String)cstmt.getString(2));
					custObj.setStrCustEmail((String)cstmt.getString(3));
					custObj.setStrPhoneNum((String)cstmt.getString(4));
					custObj.setStrPhnExtn((String)cstmt.getString(5));
					custObj.setStrContactNm((String)cstmt.getString(6));
					while (rs.next()) {
						custObj.setStrCustCd(rs.getString("CUST_CODE"));
						custObj.setStrCustNm(rs.getString("CUST_NAME"));
						custObj.setStrAddress1(rs.getString("ADDRESS"));
						custObj.setStrAddress2(rs.getString("ADDRESS2"));
						custObj.setStrCity(rs.getString("CITY"));
						custObj.setStrState(rs.getString("STATE"));
						custObj.setStrPostlCd(rs.getString("POSTAL_CODE"));
						custObj.setStrCountry(rs.getString("COUNTRY"));
					}
				}else{
					util.logMsg(true,clsName+".getCustInfo", " CallableStatement is Null");
				}
			}else{
				util.logMsg(true,clsName+".getCustInfo", " Connection is Null");
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
		return custObj;
	}	
	public String getRspTmMnAot(String strMachMstrPk, String machDownFlg, String mdlNm) throws ParseException{
		 //util.logMsg(false, clsName+".getRspTmMnAot", "strMachMstrPk : " + strMachMstrPk+ "machDownFlg : "+machDownFlg + "mdlNm : "+mdlNm);
		 BigDecimal svcMachMstrPk= new BigDecimal(strMachMstrPk);
		 String glblCmpyCd="ADB";
		 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmmss");
		 SimpleDateFormat sdfCurHr = new SimpleDateFormat("HH");
		 SimpleDateFormat sdfCurMn = new SimpleDateFormat("mm");
		 String svcTaskRcvDt=sdfDt.format(Calendar.getInstance().getTime());
    	 BigDecimal respTm = NSXC001001GetRspTime.getRspTmMnAot(glblCmpyCd, svcMachMstrPk, svcTaskRcvDt, machDownFlg, mdlNm);
		 String strRespTm = getRespTmMn(respTm.toString());
		 return strRespTm;
	  } 
	  public String getRespTmMn(String respTm){
		String retFlag="";
		String strTmUom = "MIN";
		String strFormat="FORMAT1";
		Connection connection = null;
		CallableStatement cstmt  = null;
		ResultSet rs = null;
		try
		{
		   String plsqlExp =  " Begin \n "
				   + " :1 := CANON_E307_UTILS.FORMAT_TIME(:2,:3,:4) ; \n "
				   + " End;";
		    connection = TransactionScope.getConnection();
		
			  cstmt = connection.prepareCall(plsqlExp);
			  cstmt.setString(2, respTm);
			  cstmt.setString(3, strTmUom);
			  cstmt.setString(4, strFormat);
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
	    public String getBllblFlg(String billTpCd) throws Exception{
	        util.logMsg(false, clsName+".getBllblFlg", "billTpCd : " + billTpCd);	        
	        CallableStatement statement = null;
	        Connection connection = null;
	        String bllblFlg = "";
	        try {
	        	 connection = TransactionScope.getConnection();
	             if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(GET_BLLBL_FLG);
	                if (statement != null) {
	                    statement.setObject(2, billTpCd, OracleTypes.VARCHAR);
	                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
	                    statement.execute();
	                    bllblFlg =  statement.getString(1);
	                    util.logMsg(false, clsName+".getBllblFlg", "bllblFlg : " + bllblFlg);	 
	                    return bllblFlg;
	                }else{
	                	 util.logMsg(true,clsName+".getCustInfo", " Statement is Null");
	                } 
	             }else{
	            	 util.logMsg(true,clsName+".getCustInfo", " Connection is Null");
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
	        return bllblFlg;
	    }
	    public  ArrayList<String> getHourVal(){
			ArrayList<String> arList = new ArrayList<String>();
			ResultSet rs = null;
			Connection connection = null;
			CallableStatement cstmt = null;

			try {
				connection = TransactionScope.getConnection();
				cstmt = (CallableStatement)connection.prepareCall(GET_HOUR_VAL);
				cstmt.registerOutParameter(1, OracleTypes.ARRAY,SCH_NAME+".CANON_E307_LOV_TBL");
				Map map = (Map) connection.getTypeMap();
	            map.put(SCH_NAME+".CANON_E307_LOV_REC", CanonE307ServiceReqProbUtilRec.class);
				cstmt.execute();
				Object oo[] =(Object[]) ((Array) cstmt.getObject(1)).getArray();
	     		   if(oo!=null && oo.length>0){
						  for (int k = 0; k < oo.length; k++) {
							  CanonE307ServiceReqProbUtilRec rec=  (CanonE307ServiceReqProbUtilRec)oo[k] ;
							  arList.add( util.checkNull(rec.getProbValue()) );	
					       }	
	     		   }  
		   	   
	     		//   util.logMsg(false, clsName+".getProbDetails", arList.toString());
	     		   
			} catch (Exception ex1) {
				util.logMsg(true,clsName+".getHourVal", ex1.getMessage());
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (cstmt != null)
						cstmt.close();
					if (connection != null)
						TransactionScope.releaseConnection(connection);
				} catch (Exception ex2) {
					util.logMsg(true,clsName+".getHourVal", ex2.getMessage());
				}
			}
			return arList;
		}
	    public  ArrayList<String> getMnVal(){
			ArrayList<String> arList = new ArrayList<String>();
			ResultSet rs = null;
			Connection connection = null;
			CallableStatement cstmt = null;

			try {
				connection = TransactionScope.getConnection();
				cstmt = (CallableStatement)connection.prepareCall(GET_MIN_VAL);
				cstmt.registerOutParameter(1, OracleTypes.ARRAY,SCH_NAME+".CANON_E307_LOV_TBL");
				Map map = (Map) connection.getTypeMap();
	            map.put(SCH_NAME+".CANON_E307_LOV_REC", CanonE307ServiceReqProbUtilRec.class);
				cstmt.execute();
				Object oo[] =(Object[]) ((Array) cstmt.getObject(1)).getArray();
	     		   if(oo!=null && oo.length>0){
						  for (int k = 0; k < oo.length; k++) {
							  CanonE307ServiceReqProbUtilRec rec=  (CanonE307ServiceReqProbUtilRec)oo[k] ;
							  arList.add( util.checkNull(rec.getProbValue()) );	
					       }	
	     		   }  
		   	   
	     		//   util.logMsg(false, clsName+".getProbDetails", arList.toString());
	     		   
			} catch (Exception ex1) {
				util.logMsg(true,clsName+".getHourVal", ex1.getMessage());
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (cstmt != null)
						cstmt.close();
					if (connection != null)
						TransactionScope.releaseConnection(connection);
				} catch (Exception ex2) {
					util.logMsg(true,clsName+".getHourVal", ex2.getMessage());
				}
			}
			return arList;
		}
	    public  ArrayList<CanonE307NoteTypeRec> getNoteTypes(){
			
			ArrayList<CanonE307NoteTypeRec> arList = new ArrayList<CanonE307NoteTypeRec>();
			ResultSet rs = null;
			Connection connection = null;
			CallableStatement cstmt = null;

			try {
				connection = TransactionScope.getConnection();
				cstmt = (CallableStatement)connection.prepareCall(GET_NOTE_TYPE);
				cstmt.registerOutParameter(1, OracleTypes.ARRAY,SCH_NAME+".CANON_E307_TYPE_LOV_TBL");
				Map map = (Map) connection.getTypeMap();
	            map.put(SCH_NAME+".CANON_E307_TYPE_LOV_REC", CanonE307NoteTypeRec.class);
	            util.logMsg(false,clsName+".getNoteTypes ", "before execute");
				cstmt.execute();
				util.logMsg(false,clsName+".getNoteTypes ", "after execute");
				Object oo[] =(Object[]) ((Array) cstmt.getObject(1)).getArray();
				   if(oo!=null && oo.length>0){
						  for (int k = 0; k < oo.length; k++) {
							  CanonE307NoteTypeRec rec=  (CanonE307NoteTypeRec)oo[k] ;
							  arList.add( rec);		
					       }	
	     		   }  
				} catch (Exception ex1) {
					util.logMsg(true,clsName+".getNoteTypes", ex1.getMessage());
				} finally {
					try {
						if (rs != null)
							rs.close();
						if (cstmt != null)
							cstmt.close();
						if (connection != null)
							TransactionScope.releaseConnection(connection);
					} catch (Exception ex2) {
						util.logMsg(true,clsName+".getNoteTypes", ex2.getMessage());
					}
				}
				return arList;
		}
	    public String[] getAHCallType( String strSerNum, String strModel){
			
			String[] ret=new String[2];
			ResultSet rs = null;
			Connection connection = null;
			CallableStatement cstmt = null;
			try {
				connection = TransactionScope.getConnection();
				cstmt = (CallableStatement)connection.prepareCall(GET_CALL_TYPE);
				cstmt.setString(1, strSerNum);
				cstmt.setString(2, strModel);
				cstmt.setString(3, "AHS");
				
				cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
				cstmt.registerOutParameter(5, OracleTypes.VARCHAR);				
			    cstmt.execute();
			    ret[0]= util.checkNull(cstmt.getString(4));
			    ret[1]= util.checkNull(cstmt.getString(5));
			} catch (Exception ex1) {
				util.logMsg(true,clsName+".getAHCallType", ex1.getMessage());
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (cstmt != null)
						cstmt.close();
					if (connection != null)
						TransactionScope.releaseConnection(connection);
				} catch (Exception ex2) {
					util.logMsg(true,clsName+".getAHCallType", ex2.getMessage());
				}
			}
			return ret;
		}
	    public String getCRCardTp(String strTypeVal){
	        util.logMsg(false, clsName+".getCRCardTp", "strTypeVal : " + strTypeVal);	        
	        CallableStatement statement = null;
	        Connection connection = null;
	        String crTpDesc = "";
	        try {
	        	 connection = TransactionScope.getConnection();
	             if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(GET_CR_TYPE);
	                if (statement != null) {
	                    statement.setObject(2, strTypeVal, OracleTypes.VARCHAR);
	                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
	                    statement.execute();
	                    crTpDesc =  statement.getString(1);
	                    util.logMsg(false, clsName+".getCRCardTp", "crTpDesc : " + crTpDesc);	 
	                    return crTpDesc;
	                }else{
	                	 util.logMsg(true,clsName+".getCRCardTp", " Statement is Null");
	                } 
	             }else{
	            	 util.logMsg(true,clsName+".getCRCardTp", " Connection is Null");
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
	        return crTpDesc;
	    }	
	    public  ArrayList<CanonE307BillCodeRec> getBillCodes(){
			ArrayList<CanonE307BillCodeRec> arList = new ArrayList<CanonE307BillCodeRec>();
			ResultSet rs = null;
			Connection connection = null;
			CallableStatement cstmt = null;

			try {
				connection = TransactionScope.getConnection();
				cstmt = (CallableStatement)connection.prepareCall(GET_BILL_CODE);
				cstmt.registerOutParameter(1, OracleTypes.ARRAY,SCH_NAME+".CANON_E307_TYPE_LOV_TBL");
				Map map = (Map) connection.getTypeMap();
	            map.put(SCH_NAME+".CANON_E307_TYPE_LOV_REC", CanonE307BillCodeRec.class);
	            util.logMsg(false,clsName+".getBillCodes ", "before execute");
				cstmt.execute();
				util.logMsg(false,clsName+".getBillCodes ", "after execute");
				Object oo[] =(Object[]) ((Array) cstmt.getObject(1)).getArray();
				   if(oo!=null && oo.length>0){
						  for (int k = 0; k < oo.length; k++) {
							  CanonE307BillCodeRec rec=  (CanonE307BillCodeRec)oo[k] ;
							  arList.add( rec);		
					       }	
	     		   }  
				} catch (Exception ex1) {
					util.logMsg(true,clsName+".getBillCodes", ex1.getMessage());
				} finally {
					try {
						if (rs != null)
							rs.close();
						if (cstmt != null)
							cstmt.close();
						if (connection != null)
							TransactionScope.releaseConnection(connection);
					} catch (Exception ex2) {
						util.logMsg(true,clsName+".getBillCodes", ex2.getMessage());
					}
				}
				return arList;
		}	
	    public String getCallType( String strSerNum, String strModel){
			
			String retVal="";
			ResultSet rs = null;
			Connection connection = null;
			CallableStatement cstmt = null;
			try {
				connection = TransactionScope.getConnection();
				cstmt = (CallableStatement)connection.prepareCall(GET_CALL_TYPE);
				cstmt.setString(1, strSerNum);
				cstmt.setString(2, strModel);
				cstmt.setString(3, "REGULAR");
				
				cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
				cstmt.registerOutParameter(5, OracleTypes.VARCHAR);				
			    cstmt.execute();
			    //ret[0]= util.checkNull(cstmt.getString(4));
			    retVal = util.checkNull(cstmt.getString(5));
			} catch (Exception ex1) {
				util.logMsg(true,clsName+".getCallType", ex1.getMessage());
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (cstmt != null)
						cstmt.close();
					if (connection != null)
						TransactionScope.releaseConnection(connection);
				} catch (Exception ex2) {
					util.logMsg(true,clsName+".getCallType", ex2.getMessage());
				}
			}
			return retVal;
		}	
	    public String getTskUpdFlag(String strTskStsVal){
	        util.logMsg(false, clsName+".getTskUpdFlag", "strTskStsVal : " + strTskStsVal);	        
	        CallableStatement statement = null;
	        Connection connection = null;
	        String tskUpdFlg = "";
	        try {
	        	 connection = TransactionScope.getConnection();
	             if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(GET_TASK_UPD_FLG);
	                if (statement != null) {
	                    statement.setObject(2, strTskStsVal, OracleTypes.VARCHAR);
	                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
	                    statement.execute();
	                    tskUpdFlg =  statement.getString(1);
	                    util.logMsg(false, clsName+".getTskUpdFlag", "tskUpdFlg : " + tskUpdFlg);	 
	                    return tskUpdFlg;
	                }else{
	                	 util.logMsg(true,clsName+".getTskUpdFlag", " Statement is Null");
	                } 
	             }else{
	            	 util.logMsg(true,clsName+".getTskUpdFlag", " Connection is Null");
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
	    public  ArrayList<String> getExclusionParts(String machMstrPk, String salesDate){
			ArrayList<String> arList = new ArrayList<String>();
			ResultSet rs = null;
			Connection connection = null;
			CallableStatement cstmt = null;
			util.logMsg(false,clsName+".getExclusion parts: ", "machMstrPk: "+machMstrPk+ " salesDate: "+ salesDate);
			try {
				connection = TransactionScope.getConnection();
				cstmt = (CallableStatement)connection.prepareCall(GET_EXCLUSION_PARTS);
				cstmt.setString(1, salesDate);
				cstmt.setString(2, machMstrPk);
				cstmt.registerOutParameter(3, OracleTypes.ARRAY,SCH_NAME+".CANON_E307_LOV_TBL");
				Map map = (Map) connection.getTypeMap();
	            map.put(SCH_NAME+".CANON_E307_LOV_REC", CanonE307ServiceReqProbUtilRec.class);
	            System.out.println("Before execute getExclusionParts");
				cstmt.execute();
				System.out.println("After execute getExclusionParts");
				Object oo[] =(Object[]) ((Array) cstmt.getObject(3)).getArray();
	     		   if(oo!=null && oo.length>0){
						  for (int k = 0; k < oo.length; k++) {
							  CanonE307ServiceReqProbUtilRec rec=  (CanonE307ServiceReqProbUtilRec)oo[k] ;
							  if(k==0){
								  	arList.add( util.checkNull(rec.getProbValue()) );	
						  		}else{
						  			arList.add(", "+ util.checkNull(rec.getProbValue()) );	
						  		}
					       }	
	     		   }  
	     		//   util.logMsg(false, clsName+".getProbDetails", arList.toString());
	     		   
			} catch (Exception ex1) {
				util.logMsg(true,clsName+".getExclusion parts: ", ex1.getMessage());
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (cstmt != null)
						cstmt.close();
					if (connection != null)
						TransactionScope.releaseConnection(connection);
				} catch (Exception ex2) {
					util.logMsg(true,clsName+".getExclusion parts: ", ex2.getMessage());
				}
			}
			return arList;
		}
	    public String getCrossSrvcFlg(String strSerNum, String strSvcMachMstrPk){
	        util.logMsg(false, clsName+".getCrossSrvcFlg", "strSerNum : " + strSerNum+" strSvcMachMstrPk: "+strSvcMachMstrPk);	        
	        CallableStatement statement = null;
	        Connection connection = null;
	        String crossSrvcFlg = "";
	        try {
	        	 connection = TransactionScope.getConnection();
	             if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(GET_CROSS_SRVC_FLG);
	                if (statement != null) {
	                    statement.setObject(2, strSerNum, OracleTypes.VARCHAR);
	                    statement.setObject(3, strSvcMachMstrPk, OracleTypes.VARCHAR);
	                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
	                    statement.execute();
	                    crossSrvcFlg =  statement.getString(1);
	                    util.logMsg(false, clsName+".getCrossSrvcFlg", "tskUpdFlg : " + crossSrvcFlg);	 
	                    return crossSrvcFlg;
	                }else{
	                	 util.logMsg(true,clsName+".getCrossSrvcFlg", " Statement is Null");
	                } 
	             }else{
	            	 util.logMsg(true,clsName+".getCrossSrvcFlg", " Connection is Null");
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
	        return crossSrvcFlg;
	    }	    
	public static void main(String a[]){
	  
	/*  ArrayList<String> al = getProbDetails("returnattribute:problemType;modelname:ADVC9075");
	  
	  System.out.println(" size  : "+al.size());
	  System.out.println(al);*/
	  
	}

}
