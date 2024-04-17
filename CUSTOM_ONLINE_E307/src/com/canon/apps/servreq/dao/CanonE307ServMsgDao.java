package com.canon.apps.servreq.dao;

import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.sql.Array;
import java.text.SimpleDateFormat;

import com.canon.apps.servreq.beans.CanonE307SRMsgsBean;
import com.canon.apps.servreq.beans.CanonE307ServMsgsBean;

public class CanonE307ServMsgDao {

    private  final String GET_REGION_LOV="{call CANON_E307_SERVMSG_ADMIN_PKG.GET_REGION_LOV(?,?,?,?,?)}";
    private  final String GET_BRANCH_LOV="{call CANON_E307_SERVMSG_ADMIN_PKG.GET_BRANCH_LOV(?,?,?,?,?,?)}";
    private  final String GET_PARTY_LOV= "{call CANON_E307_SERVMSG_ADMIN_PKG.GET_PARTY_LOV(?,?,?,?,?,?,?,?)}";
    private  final String GET_SERIAL_LOV="{call CANON_E307_SERVMSG_ADMIN_PKG.GET_SERIAL_LOV(?,?,?,?,?)}";
    private  final String GET_MODEL_LOV= "{call CANON_E307_SERVMSG_ADMIN_PKG.GET_MODEL_LOV(?,?,?,?,?)}";
	private  final String VALIDATE_DATA ="{call CANON_E307_SERVMSG_ADMIN_PKG.validate_data(?,?,?,?)}";
    private  final String CREATE_SERV_MSG = "{call CANON_E307_SERVMSG_ADMIN_PKG.create_service_message(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    private  final String SEARCH_SERV_MSGS = "{call CANON_E307_SERVMSG_ADMIN_PKG.search_service_message(?,?,?,?,?,?)}";
	private  final String UPDATE_SERV_MSG = "{call CANON_E307_SERVMSG_ADMIN_PKG.update_service_message(?,?,?,?,?,?,?,?,?,?)}";
    
    private  final String GET_PARTY_NUM_LOV= "{call CANON_E307_SERVMSG_ADMIN_PKG.GET_PARTY_NUMBER_LOV(?,?,?,?,?,?)}";
	private  final String GET_PARTY_SITE_LOV= "{call CANON_E307_SERVMSG_ADMIN_PKG.GET_PARTY_SITE_NUM_LOV(?,?,?,?,?)}";
	private  final String GET_ACCOUNT_NUM_LOV= "{call CANON_E307_SERVMSG_ADMIN_PKG.GET_ACCOUNT_NUMBER_LOV(?,?,?,?,?)}";
	private  final String GET_SVC_TEAM_LOV="{call CANON_E307_SERVMSG_ADMIN_PKG.GET_SVC_TEAM_LOV(?,?,?,?,?,?)}";
	
	private  final String CLS="CanonE307ServMsgDao";  
	
    public  String formatDateString(String frmFrmt, String toFrmt,String str) {
    	String MTHD="formatDateString";
		str=checkNull(str);
		str=str.trim();
		if (str.length()>0) {
			SimpleDateFormat sdf = new SimpleDateFormat(frmFrmt);
			try {
				java.util.Date d = sdf.parse(str);
				str = (new SimpleDateFormat(toFrmt)).format(d);
			} catch (Exception e1) {
				logMsg(true, CLS+MTHD, e1.getMessage().toString());	
			}
		}else{
			str=""; 
		} 
		return str; 
 	}
	
    
    public  String formatDateString(String toFrmt,Date d) {
        String str="";
		if (d!=null) {
			try {
				str = (new SimpleDateFormat(toFrmt)).format(d).toString();
			} catch (Exception ex) {
			}
		}else{
			str=""; 
		} 
		return str; 
 	}
    
    public   String checkNull(String str){
		 if (str != null) {
		    return str;
		}else {
			return "";
		}
    }
    public void logMsg(boolean error,String cls,  String str ){
 	   String e="[ERROR]";
 	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
 	   PrintStream out = System.err;
 	   
 	  if(!error  ){
 		 e ="[INFO]";
 		 out = System.out;
 	  }	 
 	 out = System.out; // setting System.out to all Log msgs
 	 
 	  out.println("["+sdf.format(Calendar.getInstance().getTime())+"] : "+e+"["+cls +"]"+ str  );
    }
    
	public ArrayList getModelLOV(String model, int start, int end){
		
	    String MTHD=".getModelLOV";
	    System.out.println("Inside getModelLOV(): " + model);
	    ArrayList beanList = new ArrayList();
		ResultSet rs = null;
		Connection conn = null;
		CallableStatement cstmt = null;
		
		CanonE307SRMsgsBean bean = null;
		try {
			conn = TransactionScope.getConnection();
			cstmt = (CallableStatement)conn.prepareCall(GET_MODEL_LOV);
			cstmt.setString(1,model);
			cstmt.setInt(2,start);
			cstmt.setInt(3,end);
			cstmt.registerOutParameter(4,OracleTypes.NUMBER);
			cstmt.registerOutParameter(5,OracleTypes.CURSOR);
			System.out.println("Before execute");
			cstmt.execute();
			System.out.println("After execute");
			rs = (ResultSet) cstmt.getObject(5);
		
			
			int totalNoOfRecords = cstmt.getInt(4);
			System.out.println("totalNoOfRecords" + totalNoOfRecords);
			beanList.add(totalNoOfRecords+"");
			while (rs.next()) {
				
				bean = new CanonE307SRMsgsBean();
				bean.model = (rs.getString("model"));
				bean.modelDesc = (rs.getString("description"));
				bean.totalRec = totalNoOfRecords;
					beanList.add(bean);
				
			}
		} catch (Exception e1) {
			logMsg(true, CLS+MTHD, e1.getMessage().toString());
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
		return beanList;
		
	}   
	public ArrayList getSerialLOV(String serialNum, int start, int end){
		
	    String MTHD=".getSerialLOV";
	    System.out.println("Inside getSerialLOV(): " + serialNum);
	    ArrayList beanList = new ArrayList();
		ResultSet rs = null;
		Connection conn = null;
		CallableStatement cstmt = null;
		
		CanonE307SRMsgsBean bean = null;
		try {
			conn = TransactionScope.getConnection();
			cstmt = (CallableStatement)conn.prepareCall(GET_SERIAL_LOV);
			cstmt.setString(1,serialNum);
			cstmt.setInt(2,start);
			cstmt.setInt(3,end);
			cstmt.registerOutParameter(4,OracleTypes.NUMBER);
			cstmt.registerOutParameter(5,OracleTypes.CURSOR);
			System.out.println("Before execute");
			cstmt.execute();
			System.out.println("After execute");
			rs = (ResultSet) cstmt.getObject(5);
			
			int totalNoOfRecords = cstmt.getInt(4);
			System.out.println("totalNoOfRecords" + totalNoOfRecords);
			beanList.add(totalNoOfRecords+"");
			while (rs.next()) {
				
				bean = new CanonE307SRMsgsBean();
				bean.serialNumber = (rs.getString("SERIAL_NUMBER"));
				bean.extReference = (rs.getString("EXTERNAL_REFERENCE"));
				bean.partyName = (rs.getString("PARTY_NAME"));
				bean.address = (rs.getString("ADDRESS"));
				bean.totalRec = totalNoOfRecords;
					beanList.add(bean);
				
			}
		} catch (Exception e1) {
			logMsg(true, CLS+MTHD, e1.getMessage().toString());
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
		return beanList;
		
	} 
	
    public ArrayList getRegionLOV(String region, int start, int end){
    	
	    String MTHD=".getRegionLOV";
	    System.out.println("Inside getRegionLOV: " + region);
	    ArrayList beanList = new ArrayList();
		ResultSet rs = null;
		Connection conn = null;
		CallableStatement cstmt = null;
		
		CanonE307SRMsgsBean bean = null;
		try {
			conn = TransactionScope.getConnection();
			cstmt = (CallableStatement)conn.prepareCall(GET_REGION_LOV);
			cstmt.setString(1,region);
			cstmt.setInt(2,start);
			cstmt.setInt(3,end);
			cstmt.registerOutParameter(4,OracleTypes.NUMBER);
			cstmt.registerOutParameter(5,OracleTypes.CURSOR);
			System.out.println("Before Execute: " );
			cstmt.execute();
			System.out.println("After Execute");
			rs = (ResultSet) cstmt.getObject(5);
			
			int totalNoOfRecords = cstmt.getInt(4);
			System.out.println("totalNoOfRecords" + totalNoOfRecords);
			beanList.add(totalNoOfRecords+"");
			while (rs.next()) {
				
				bean = new CanonE307SRMsgsBean();
				bean.region = (rs.getString("REGION"));
				bean.totalRec = totalNoOfRecords;
				beanList.add(bean);
				
			}
		} catch (Exception e1) {
			logMsg(true, CLS+MTHD, e1.getMessage().toString());
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
		return beanList;
		
	}
    
  	public ArrayList getBranchLOV(String branch, String branchDesc, int start, int end){
  		
	    String MTHD=".getBranchLOV";
	 
		ArrayList beanList = new ArrayList();
		ResultSet rs = null;
		Connection conn = null;
		CallableStatement cstmt = null;
		
		CanonE307SRMsgsBean bean = null;
		try {
			conn = TransactionScope.getConnection();
			cstmt = (CallableStatement)conn.prepareCall(GET_BRANCH_LOV);
			cstmt.setString(1,branch);
			cstmt.setString(2,branchDesc);
			cstmt.setInt(3,start);
			cstmt.setInt(4,end);
			cstmt.registerOutParameter(5,OracleTypes.NUMBER);
			cstmt.registerOutParameter(6,OracleTypes.CURSOR);
			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(6);
			
			int totalNoOfRecords = cstmt.getInt(5);
			System.out.println("totalNoOfRecords" + totalNoOfRecords);
			beanList.add(totalNoOfRecords+"");
			while (rs.next()) {
				
				bean = new CanonE307SRMsgsBean();
				bean.branch = (rs.getString("BRANCH"));
				bean.branchDesc = (rs.getString("BRANCH_DESC"));
				bean.totalRec = totalNoOfRecords;
					beanList.add(bean);
				
			}
		} catch (Exception e1) {
			logMsg(true, CLS+MTHD, e1.getMessage().toString());
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
		return beanList;
		
	}
  	public ArrayList getSvcTeamLOV(String branch, String svcTeamDesc, int start, int end){
  		
	    String MTHD=".getSvcTeamLOV";
	 
		ArrayList beanList = new ArrayList();
		ResultSet rs = null;
		Connection conn = null;
		CallableStatement cstmt = null;
		
		CanonE307SRMsgsBean bean = null;
		try {
			conn = TransactionScope.getConnection();
			cstmt = (CallableStatement)conn.prepareCall(GET_SVC_TEAM_LOV);
			cstmt.setString(1,branch);
			cstmt.setString(2,svcTeamDesc);
			cstmt.setInt(3,start);
			cstmt.setInt(4,end);
			cstmt.registerOutParameter(5,OracleTypes.NUMBER);
			cstmt.registerOutParameter(6,OracleTypes.CURSOR);
			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(6);
			
			int totalNoOfRecords = cstmt.getInt(5);
			System.out.println("totalNoOfRecords" + totalNoOfRecords);
			beanList.add(totalNoOfRecords+"");
			while (rs.next()) {
				System.out.println("Inside RS" );
				bean = new CanonE307SRMsgsBean();
				bean.branch = (rs.getString("BRANCH"));
				bean.branchDesc = (rs.getString("BRANCH_DESC"));
				bean.svcTeam = (rs.getString("TEAM_DESC"));
				bean.totalRec = totalNoOfRecords;
					beanList.add(bean);
				
			}
		} catch (Exception e1) {
			logMsg(true, CLS+MTHD, e1.getMessage().toString());
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
		return beanList;
		
	}  	
  	
	public ArrayList getPartyNumberLOV(String partyName, String partyNum, int start, int end){
		
	    String MTHD=".getPartyNumberLOV";
	    System.out.println("Inside getPartyNumberLOV partyName: " + partyName+" partyNum: "+partyNum);
		ArrayList beanList = new ArrayList();
		ResultSet rs = null;
		Connection conn = null;
		CallableStatement cstmt = null;
		
		CanonE307SRMsgsBean bean = null;
		try {
			conn = TransactionScope.getConnection();
			cstmt = (CallableStatement)conn.prepareCall(GET_PARTY_NUM_LOV);
			cstmt.setString(1,partyName);
			cstmt.setString(2,partyNum);
			cstmt.setInt(3,start);
			cstmt.setInt(4,end);
			cstmt.registerOutParameter(5,OracleTypes.NUMBER);
			cstmt.registerOutParameter(6,OracleTypes.CURSOR);
			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(6);
			
			int totalNoOfRecords = cstmt.getInt(5);
			System.out.println("totalNoOfRecords" + totalNoOfRecords);
			beanList.add(totalNoOfRecords+"");
			while (rs.next()) {
				
				bean = new CanonE307SRMsgsBean();
				bean.partyId = (rs.getString("PARTY_ID"));
				bean.partyName = (rs.getString("PARTY_NAME"));
				bean.partyNumber = (rs.getString("PARTY_NUMBER"));
				bean.totalRec = totalNoOfRecords;
					beanList.add(bean);
				
			}
		} catch (Exception e1) {
			logMsg(true, CLS+MTHD, e1.getMessage().toString());
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
		return beanList;
	}  
	public ArrayList getPartySiteLOV(String partySiteNum, int start, int end){
		
	    String MTHD=".getPartySiteLOV";
	 
	    System.out.println("getPartySiteLOV: " + partySiteNum);
	    ArrayList beanList = new ArrayList();
		ResultSet rs = null;
		Connection conn = null;
		CallableStatement cstmt = null;
		
		CanonE307SRMsgsBean bean = null;
		try {
			conn = TransactionScope.getConnection();
			cstmt = (CallableStatement)conn.prepareCall(GET_PARTY_SITE_LOV);
			cstmt.setString(1,partySiteNum);
			cstmt.setInt(2,start);
			cstmt.setInt(3,end);
			cstmt.registerOutParameter(4,OracleTypes.NUMBER);
			cstmt.registerOutParameter(5,OracleTypes.CURSOR);
			System.out.println("Before execute ");
			cstmt.execute();
			System.out.println("After execute ");			
			rs = (ResultSet) cstmt.getObject(5);
			
			int totalNoOfRecords = cstmt.getInt(4);
			System.out.println("totalNoOfRecords" + totalNoOfRecords);
			beanList.add(totalNoOfRecords+"");
			while (rs.next()) {
				bean = new CanonE307SRMsgsBean();
				bean.partyId = (rs.getString("PARTY_ID"));
				bean.partyName = (rs.getString("PARTY_NAME"));
				bean.partyNumber = (rs.getString("PARTY_NUMBER"));
				bean.partySiteNumber = (rs.getString("PARTY_SITE_NUMBER"));
				bean.totalRec = totalNoOfRecords;
				beanList.add(bean);
				
			}
		} catch (Exception e1) {
			logMsg(true, CLS+MTHD, e1.getMessage().toString());
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
		return beanList;
	}	
	public ArrayList getAccountNumberLOV(String accountNum, int start, int end){
		
	    String MTHD=".getAccountNumberLOV";
	    System.out.println("Inside getAccountNumberLOV(): "+ accountNum);
		ArrayList beanList = new ArrayList();
		ResultSet rs = null;
		Connection conn = null;
		CallableStatement cstmt = null;
		
		CanonE307SRMsgsBean bean = null;
		try {
			conn = TransactionScope.getConnection();
			cstmt = (CallableStatement)conn.prepareCall(GET_ACCOUNT_NUM_LOV);
			cstmt.setString(1,accountNum);
			cstmt.setInt(2,start);
			cstmt.setInt(3,end);
			cstmt.registerOutParameter(4,OracleTypes.NUMBER);
			cstmt.registerOutParameter(5,OracleTypes.CURSOR);
			System.out.println("Before execute");
			cstmt.execute();
			System.out.println("After execute");
			rs = (ResultSet) cstmt.getObject(5);
			
			int totalNoOfRecords = cstmt.getInt(4);
			System.out.println("totalNoOfRecords" + totalNoOfRecords);
			beanList.add(totalNoOfRecords+"");
			while (rs.next()) {
				bean = new CanonE307SRMsgsBean();
				bean.partyId = (rs.getString("PARTY_ID"));
				bean.partyName = (rs.getString("PARTY_NAME"));
				bean.partyNumber = (rs.getString("PARTY_NUMBER"));
				bean.accountNumber = (rs.getString("ACCOUNT_NUMBER"));
				bean.totalRec = totalNoOfRecords;
				beanList.add(bean);
				
			}
		} catch (Exception e1) {
			logMsg(true, CLS+MTHD, e1.getMessage().toString());
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
		return beanList;
	}	
	public String createServMsgs(java.util.ArrayList msgList, String userId){
		
		
		ArrayList alRepData = new ArrayList();		
	
	    String MTHD=".createServMsgs";
	 		
		ResultSet rs = null;
		Connection conn = null;
		CallableStatement cstmt = null;
		
		int size = msgList.size();
		
		String valResult[] = new String[size] ;
		
		String result;
		
		boolean isInValid = false;
		int LineNumber;
		
		try {
			conn = TransactionScope.getConnection();
			cstmt = (CallableStatement)conn.prepareCall(CREATE_SERV_MSG);
			//conn.setAutoCommit(false);
			
			if(msgList!=null && msgList.size()>0){
				for(int i=0;i<msgList.size();i++){
					CanonE307ServMsgsBean objInsert =(CanonE307ServMsgsBean)msgList.get(i);	
					System.out.println("level ius" + objInsert.getFieldName()+" Field Value: "+ objInsert.getFieldValue());
					LineNumber=objInsert.getLineNumber()+1;
					if(!(objInsert.getFieldName().equals(""))){
					String retVal = validateData(objInsert.getFieldName(), objInsert.getFieldValue());
					
						System.out.println("Return Message from Validate Method:" + retVal);					
							if(retVal.equals("F")){
							valResult[i] = "Row Number-"+LineNumber + "-  Invalid value";
							isInValid = true;
						   }
							else{
								
								valResult[i] = "Row Number-"+LineNumber+"-Valid value ";
							}
					}
					else	
						valResult[i] = "Row Number-"+LineNumber+"-Value Valid";
				}
			if(!isInValid){
			for(int i=0;i<msgList.size();i++){			
				System.out.println("is valid" + valResult[i]);
				CanonE307ServMsgsBean objInsert =(CanonE307ServMsgsBean)msgList.get(i);	
				
			if(!(objInsert.getFieldName().equals(""))){
				cstmt.setString(1,objInsert.getRegion());
				cstmt.setString(2,objInsert.getBranch());
				cstmt.setString(3,objInsert.getPostal());
				cstmt.setString(4,objInsert.getPartyName());
				cstmt.setString(5,objInsert.getPartyNumber());
				cstmt.setString(6,objInsert.getPartySiteNumber());
				cstmt.setString(7,objInsert.getAccountNumber());
				cstmt.setString(8,objInsert.getModel());
				cstmt.setString(9,objInsert.getSerialNumber());	
				cstmt.setString(10,objInsert.getStartDate());
				cstmt.setString(11,objInsert.getEndDate());	
				cstmt.setString(12,objInsert.getStartTime());
				cstmt.setString(13,objInsert.getEndTime());
				cstmt.setString(14,objInsert.getIsFullHour());
				cstmt.setString(15,objInsert.getServMsg());
				cstmt.setString(16,userId);
				cstmt.registerOutParameter(17, OracleTypes.VARCHAR);
				cstmt.registerOutParameter(18, OracleTypes.VARCHAR);
				cstmt.setString(19,objInsert.getSvcTeam());
				cstmt.execute();

				valResult[i]   =  cstmt.getString(17) + "-" + cstmt.getString(18); 	
				System.out.println("result is" + valResult[i]);				
			}		
			
			}
	/*		if(result == "E"){
				System.out.println("Inside rollback..");
					conn.rollback();
				}else{
				System.out.println("Inside commit..");
					conn.commit();
				}*/
				
			}				
			
			}
							
			} catch (Exception e1) {
			logMsg(true, CLS+MTHD, e1.getMessage().toString());
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
		
		   //return valResult;
	       //result=Arrays.toString(valResult);
			//return result;
			StringBuffer builder = new StringBuffer();
			for(int i=0;i<valResult.length;i++)
			{
				 if(i!=0)
				 {				
				 builder.append(",");
				 builder.append(valResult[i]);
				 }
				 else{
					 
					 builder.append(valResult[i]);
				 }
				 
			}
			String resultstr = builder.toString();
            System.out.println("Response from [createServMsgs] method"+resultstr);
			return resultstr;
		
	}	

	 public String validateData(String field, String value){
			
		    String MTHD=".validateData";
		 
			String retVal = "";		
			ResultSet rs = null;
			Connection conn = null;
			CallableStatement cstmt = null;
			try {
				conn = TransactionScope.getConnection();
				cstmt = (CallableStatement)conn.prepareCall(VALIDATE_DATA);
				cstmt.setString(1,field);
				cstmt.setString(2,value);
				cstmt.registerOutParameter(3,OracleTypes.VARCHAR);
				cstmt.registerOutParameter(4,OracleTypes.VARCHAR);
				System.out.println("Before Execute field: "+field+" value: "+value);
				cstmt.execute();
				System.out.println("After Execute");
				retVal = cstmt.getString(3);
				//retVal[1] = cstmt.getString(4);
				System.out.println("retVal: "+ retVal);
				System.out.println("retVal[0] is" + cstmt.getString(3));
				System.out.println("retVal[1] is " + cstmt.getString(4));
							
			} catch (Exception e1) {
				logMsg(true, CLS+MTHD, e1.getMessage().toString());
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
			return retVal;
			
		}
		public ArrayList searchServMsgs(String field, String value, int start, int end){
			
		    String MTHD=".searchServMsgs";
		    System.out.println("searchServMsgs field: " + field+" value: "+value);
			ArrayList beanList = new ArrayList();
			ResultSet rs = null;
			Connection conn = null;
			CallableStatement cstmt = null;
			
			CanonE307SRMsgsBean bean = null;
			try {
				conn = TransactionScope.getConnection();
				cstmt = (CallableStatement)conn.prepareCall(SEARCH_SERV_MSGS);
				cstmt.setString(1,field);
				cstmt.setString(2,value);
				cstmt.setInt(3,start);
				cstmt.setInt(4,end);
				cstmt.registerOutParameter(5,OracleTypes.NUMBER);
				cstmt.registerOutParameter(6,OracleTypes.CURSOR);
				System.out.println("Before Execute");
				cstmt.execute();
				System.out.println("After Execute");				
				rs = (ResultSet) cstmt.getObject(6);
				
				int totalNoOfRecords = cstmt.getInt(5);
				System.out.println("totalNoOfRecords" + totalNoOfRecords);
				beanList.add(totalNoOfRecords+"");
				
				String start_time = "";
				String end_time = "";

				while (rs.next()) {
					System.out.println("Inside while");
					bean = new CanonE307SRMsgsBean();
					bean.setServMsgId(rs.getString("SERV_MSG_ID"));
					bean.setRegion(rs.getString("REGION"));
					bean.setBranch(rs.getString("BRANCH"));
					bean.setPostal(rs.getString("POSTAL_CODE"));
					bean.setPartyName(rs.getString("PARTY_NAME"));
					bean.setPartyNumber(rs.getString("PARTY_NUMBER"));
					bean.setAccountNumber(rs.getString("ACCOUNT_NUMBER"));
					bean.setPartySiteNumber(rs.getString("PARTY_SITE_NUM"));				
					bean.setModel(rs.getString("MODEL_NUM"));
					bean.setSerialNumber(rs.getString("SERIAL_NUMBER"));
					bean.setStartDate(rs.getString("START_DATE"));
					bean.setEndDate(rs.getString("END_DATE"));
				
					if(rs.getString("START_TIME") != null){
						start_time = (rs.getString("START_TIME")).replaceAll(":", "");
					}
					if(rs.getString("END_TIME") != null){
						end_time = (rs.getString("END_TIME")).replaceAll(":", "");
					}
					bean.setStartTime(start_time);				
					bean.setEndTime(end_time);
					bean.setIsFullHour(rs.getString("IS_FULLHOUR"));
					bean.setServMsg(rs.getString("SERVICE_MESSAGE"));
					bean.setCreatedBy(rs.getString("CREATED_BY"));
					bean.setLastUpdatedBy(rs.getString("LAST_UPDATED_BY"));
					bean.setSvcTeam(rs.getString("SVC_TEAM"));
									 
					bean.totalRec = totalNoOfRecords;
						
					beanList.add(bean);
					System.out.println("size is" + beanList.size());
					
				}
				} catch (Exception e1) {
				logMsg(true, CLS+MTHD, e1.getMessage().toString());
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
			return beanList;
			
		}	
		public String[] updateServMsgs(java.util.ArrayList msgList, String userId){
			
			System.out.println("updateServMsgs" + userId);
			ArrayList alRepData = new ArrayList();		
		
		    String MTHD=".updateServMsgs";
		 		
			ResultSet rs = null;
			Connection conn = null;
			CallableStatement cstmt = null;
			
			int size = msgList.size();
			
			String valResult[] = new String[size] ;
			
			
			try {
				conn = TransactionScope.getConnection();
				cstmt = (CallableStatement)conn.prepareCall(UPDATE_SERV_MSG);
				//conn.setAutoCommit(false);
				
				if(msgList!=null && msgList.size()>0){			
				for(int i=0;i<msgList.size();i++){
					System.out.println("is valid" + valResult[i]);
					CanonE307ServMsgsBean objInsert =(CanonE307ServMsgsBean)msgList.get(i);	
					
					cstmt.setInt(1,objInsert.getServMsgId());
					cstmt.setString(2,objInsert.getStartDate());
					cstmt.setString(3,objInsert.getEndDate());
					cstmt.setString(4,objInsert.getStartTime());
					cstmt.setString(5,objInsert.getEndTime());
					cstmt.setString(6,objInsert.getIsFullHour());
					cstmt.setString(7,objInsert.getServMsg());
					cstmt.setString(8,userId);
					cstmt.registerOutParameter(9, OracleTypes.VARCHAR);
					cstmt.registerOutParameter(10, OracleTypes.VARCHAR);
					cstmt.execute();

				valResult[i]   =  cstmt.getString(9) + "-" + cstmt.getString(10); 				
				
				System.out.println("result is" + valResult[i]);
				}
		/*		if(result == "E"){
					System.out.println("Inside rollback..");
						conn.rollback();
					}else{
					System.out.println("Inside commit..");
						conn.commit();
					}*/
					
				}			
				} catch (Exception e1) {
				logMsg(true, CLS+MTHD, e1.getMessage().toString());
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
			return valResult;
			
		}		
}
