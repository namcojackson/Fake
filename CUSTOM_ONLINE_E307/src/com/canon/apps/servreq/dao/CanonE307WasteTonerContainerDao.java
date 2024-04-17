package com.canon.apps.servreq.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.sql.Array;

import javax.servlet.http.HttpServletRequest;

import com.canon.apps.servreq.beans.CanonE307BillToCustCreditAuthBean;
import com.canon.apps.servreq.beans.CanonE307EOLRec;
import com.canon.apps.servreq.beans.CanonE307MachineCustSearchRec;
import com.canon.apps.servreq.beans.CanonE307MachineCustSearchResultsRec;
import com.canon.apps.servreq.beans.CanonE307SerialNumLov;
import com.canon.apps.servreq.beans.CanonE307UGWErrCodeRec;
import com.canon.apps.servreq.beans.CanonE307ServReqFutureCallRec;
import com.canon.apps.servreq.beans.CanonE307ServReqVendCallRec;
import com.canon.apps.servreq.beans.CanonE307WasteTonerRec;
import com.canon.common.CanonCommonUtil;













import canon.apps.common.CanonConstants;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
public class CanonE307WasteTonerContainerDao {
	
	public static final String GET_SERIAL_NUMBERS = "{CALL CANON_I163_WT_ORDER_ASCC_PKG.GET_SERIAL_NUMBERS(?,?)}";
	public static final String GET_CUSTOMERS = "{CALL CANON_I163_WT_ORDER_ASCC_PKG.GET_CUSTOMERS(?,?)}";
	public static final String GET_MACHINES = "{CALL CANON_I163_WT_ORDER_ASCC_PKG.MACHINE_INFO(?,?,?,?,?,?,?)}";
	public static final String STAGE_ORDER_DET = "{CALL CANON_I163_WT_ORDER_ASCC_PKG.STAGE_WT_ORDERS(?,?,?,?,?,?,?,?,?,?,?)}";

	CanonCommonUtil util;
	String SCH_NAME;
	
	private   String clsName="CanonE307WasteTonerContainerDao";
	public CanonE307WasteTonerContainerDao() {
		 util = new CanonCommonUtil();
		 SCH_NAME = CanonConstants.SCHEMA_NAME;
	}
	
	public ArrayList getSerialNumbers(String strSerialNum){
		Connection connection = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		ArrayList<CanonE307SerialNumLov> alRepData = new ArrayList<CanonE307SerialNumLov>();
		String srvMsg = "";
		try {
			util.logMsg(true,clsName+".getSerialNumbers", " strSerialNum: "+ strSerialNum);
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_SERIAL_NUMBERS);
				if (cstmt !=null){
					cstmt.setString(1, strSerialNum);
					cstmt.registerOutParameter(2, OracleTypes.CURSOR);
					util.logMsg(true,clsName+".getSerialNumbers", " Before execute0.."+ strSerialNum);
					cstmt.execute();
					util.logMsg(true,clsName+".getSerialNumbers", " After execute0.."+ strSerialNum);
					rs = (ResultSet) cstmt.getObject(2);
					while (rs.next()) {
						 CanonE307SerialNumLov beanObj = new CanonE307SerialNumLov();
						 
						 beanObj.setStrSerialNumber(rs.getString("SER_NUM"));
						 beanObj.setStrModel(rs.getString("MODEL"));
						 beanObj.setStrMachMstrPk(rs.getString("SVC_MACH_MSTR_PK"));
						 beanObj.setStrEquipNumber(rs.getString("CUST_MACH_CTRL_NUM"));
						 beanObj.setStrAddress(rs.getString("ADDRESS"));
						 beanObj.setStrShipToAcctNum(rs.getString("SHIP_TO_ACCT_NO"));
						 beanObj.setStrCustName(rs.getString("SHIP_TO_CUST_NAME"));
						 
						 alRepData.add(beanObj);
					}
				}else{
					util.logMsg(true,clsName+".getSerialNumbers", " CallableStatement is Null");
				}
			}else{
				util.logMsg(true,clsName+".getSerialNumbers", " Connection is Null");
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
		return alRepData;
	}	    	
	public ArrayList getCustomers(String strCustName){
		Connection connection = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		ArrayList<CanonE307SerialNumLov> alRepData = new ArrayList<CanonE307SerialNumLov>();
		String srvMsg = "";
		try {
			util.logMsg(true,clsName+".getCustomers", " strCustName: "+ strCustName);
			connection = TransactionScope.getConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(GET_CUSTOMERS);
				if (cstmt !=null){
					cstmt.setString(1, strCustName);
					cstmt.registerOutParameter(2, OracleTypes.CURSOR);
					util.logMsg(true,clsName+".getCustomers", " Before execute0.."+ strCustName);
					cstmt.execute();
					util.logMsg(true,clsName+".getCustomers", " After execute0.."+ strCustName);
					rs = (ResultSet) cstmt.getObject(2);
					while (rs.next()) {
						util.logMsg(true,clsName+".getCustomers", " Inside RS ");
						 CanonE307SerialNumLov beanObj = new CanonE307SerialNumLov();
						 
						 beanObj.setStrShipToAcctNum(rs.getString("ACCOUNT_NUMBER"));
						 beanObj.setStrCustName(rs.getString("CUSTOMER_NAME"));
						 
						 alRepData.add(beanObj);
					}
				}else{
					util.logMsg(true,clsName+".getCustomers", " CallableStatement is Null");
				}
			}else{
				util.logMsg(true,clsName+".getCustomers", " Connection is Null");
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
		return alRepData;
	}	    	
	public Object[] getMachineSearchResults(String serialNumber, String custNm, String location, int start, int end){
	     CallableStatement cstmt = null;
	     Connection connection = null;
	     Object[] resObj = new Object[2];
	     try
	       {
			connection = TransactionScope.getConnection();
			List<Object> list = new ArrayList<Object>();	
			if (connection != null) {
				cstmt = connection.prepareCall(GET_MACHINES);
			  if (cstmt != null) {
				  cstmt.setString(1,serialNumber);
				  cstmt.setString(2,custNm);
				  cstmt.setString(3,location);
				  cstmt.setInt(4,start);
				  cstmt.setInt(5,end);
	              cstmt.registerOutParameter(6, OracleTypes.ARRAY, CanonConstants.SCHEMA_NAME+".CANON_E307_WASTE_TONER_TBL");
				  cstmt.registerOutParameter(7, OracleTypes.NUMBER);
				  // Execute the statement
	              Map map = connection.getTypeMap();
	              map.put(CanonConstants.SCHEMA_NAME+".CANON_E307_WASTE_TONER_REC",CanonE307WasteTonerRec.class);
	              util.logMsg(true,clsName+".getMachineSearchResults before execute", "Serial Number : "+ serialNumber);
	              cstmt.execute();

	              int totRecords = cstmt.getInt(7);
	              util.logMsg(false,clsName+".getMachineSearchResults before execute","");
	              resObj[0]=totRecords;
	              Object oo[] =(Object[]) ((Array)cstmt.getObject(6)).getArray();
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
	public String[] submitOrders(HttpServletRequest req){
		String[] resp = new String[2];
		
		  CallableStatement cstmt = null;
		  Connection connection = null;
	
		     try
		       {
				connection = TransactionScope.getConnection();
				String serialNumbers[] = req.getParameterValues("wtCheckBox");

		    		System.out.println("Selected serial numbers to create orders:");
		    		for(int k=0;k<serialNumbers.length; k++)
		    		{
		    			System.out.print("-"+serialNumbers[k] );
		    		}

		    	int l=0;
		    	for(l=0;l<serialNumbers.length; l++)
				{
		    		String serNum =req.getParameter("serialNumber"+l);
		    		String model =req.getParameter("model"+l);
		    		String customer =req.getParameter("customer"+l);
		    		String location =req.getParameter("location"+l);
		    		String contactNm =req.getParameter("contactNm"+l);
		    		String contactPhone = req.getParameter("contactPhn"+l);
		    		String contactExt = req.getParameter("phnExtn"+l);
		    		String email =req.getParameter("email"+l);
		    		String shelfStock =req.getParameter("shelfStock"+l);
		    		String qty =req.getParameter("qty"+l);
		    		
		    		System.out.println(" serNum: "+ serNum+ "model: "+ model+" customer: "+ customer + " location: "+ location+ " qty: "+ qty);
		     		System.out.println(" contactNm: "+ contactNm+ "contactPhone: "+ contactPhone+" contactExt: "+ contactExt + " email: "+ email+" shelfStock: "+shelfStock);

				if (connection != null) {
					cstmt = connection.prepareCall(STAGE_ORDER_DET);
				  if (cstmt != null) {
					  cstmt.setString(1,serNum);
					  cstmt.setString(2,model);
					  cstmt.setString(3,customer);
					  cstmt.setString(4,contactNm);
					  cstmt.setString(5,contactPhone);
					  cstmt.setString(6,contactExt);
					  cstmt.setString(7,email);
					  cstmt.setString(8,shelfStock);
					  cstmt.setString(9,qty);
					  
		              cstmt.registerOutParameter(10, OracleTypes.VARCHAR);
					  cstmt.registerOutParameter(11, OracleTypes.VARCHAR);
					  
					  System.out.println("Before execute ");
					  cstmt.execute();

					  System.out.println("After execute "+ cstmt.getString(10));
					  if("S".equals(cstmt.getString(10))){
						  resp[0] = "S";
						  resp[1] = "Successfully submitted orders.";
						  
					  }else{
						  resp[0] = "E";
						  resp[1] = "Problem submitting order";
					  }
				  }
				
				  else {
					  System.err.println("DBStatus.UNABLE_TO_CREATE_DATA ");
		          	}
				} else {
					System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
		        }
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
		
		
		return resp;
	}
}
