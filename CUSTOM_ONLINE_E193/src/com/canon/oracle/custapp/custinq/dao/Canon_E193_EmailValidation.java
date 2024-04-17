package com.canon.oracle.custapp.custinq.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;




import com.canon.oracle.custapp.util.CanonCustAppDBUtil;
import com.canon.oracle.custapp.util.CanonCustAppExceptionUtil;

public class Canon_E193_EmailValidation {

	public Canon_E193_EmailValidation() {
		super();
	}
	
   public List<String> getEmanageUsername(String strOrigEmailAddress)  {
	   List<String> selectEmanageUser = new ArrayList<String>() ;
			//Get Connection
			CanonCustAppDBUtil connUtil = null;
			Connection conn = null; 
		    CallableStatement pCall = null;
		    ResultSet rs = null;  
		    //Canon_E193_EmailValidationObj objEmail = new Canon_E193_EmailValidationObj();
		      
		    try {
		    	//connUtil = new CanonCustAppDBUtil();
		    	
		    	System.out.println("in getEmanageUsername emailId = "+strOrigEmailAddress);
		    	
		    	 
		    	conn = TransactionScope.getConnection();
		    	
		    	pCall = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.SELECT_EMANAGE_USER(?, ?)}");
		    	
		    	pCall.setString(1, strOrigEmailAddress);
		    	pCall.registerOutParameter(2, OracleTypes.CURSOR);

		    	pCall.execute();
		    	
		    	rs = (ResultSet) pCall.getObject(2);
		    	//rs = ((OracleCallableStatement)pCall).getCursor(2);
		    	//System.out.println("Trail====== "+((OracleCallableStatement)pCall).getCursor(2));
		    	
		    	
		    	while(rs.next()) {
		  
		    	    //objEmail.setName1(rs.getString(1));
		    		
		    		//selectEmanageUser.add(objEmail);
		    		
		    		//System.out.println("[ getEmanageUsername ] returned email  email Id="+rs.getString(1));
		    		
		    		
		    		selectEmanageUser.add(rs.getString(1));
		    		
		    		
		    		
		    		//System.out.println("emanage:::"+selectEmanageUser);
		    		
		    		//System.out.println("email----"+rs.getString(1)+" name---"+ rs.getString(2));
		    		
		    		//selectEmanageUser = rs.getString(2);
		    		}
		    }catch(Exception e) {
		    	System.err.println(e);
		    }
		    finally {
		    	try {
		    		if(pCall != null) pCall.close();
		    		if(rs != null) rs.close();
		    		if(connUtil != null) connUtil.releaseConnection();
		    	}catch(Exception e) {
		    	    System.err.println(e.getMessage());
		       }   
		    }
			return selectEmanageUser;
		
	
	}
   public ArrayList getEmanageUser(String strOrigEmailAddress) throws CanonCustAppExceptionUtil {
		ArrayList alEmanageUser = new ArrayList();
		//Get Connection
		CanonCustAppDBUtil connSeverityUtil = null;
		Connection connSeverity = null;
	    CallableStatement pCall = null;
	    ResultSet rsUsers = null;
	    try {
	    	System.out.println("in getEmanageUser emailId = "+strOrigEmailAddress);
	    	connSeverityUtil = new CanonCustAppDBUtil();
	    	connSeverity = (Connection)(connSeverityUtil.getConnection());
	    	pCall = connSeverity.prepareCall("{call CANON_E193_CS_SQLS_PKG.SELECT_EMANAGE_USER(?,?)}");
	    	pCall.setString(1, strOrigEmailAddress);
	    	pCall.registerOutParameter(2, OracleTypes.CURSOR);

	    	pCall.execute();
	    	
	    	rsUsers = (ResultSet) pCall.getObject(2);
	    	//System.out.println("!!!!!!!!!!rsUsers = "+rsUsers);
	    	
	    	while(rsUsers.next()) {
	    		alEmanageUser.add(rsUsers.getString(1));
	    	}	    	
	    }catch(CanonCustAppExceptionUtil csExp) {
	    	throw (csExp);
	    }catch(SQLException eSQLExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_EmailValidation, Method:getEmanageUser()"));
	    }catch (Exception eExp) {
	    	throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_EmailValidation, Method:getEmanageUser()"));
	    }
	    finally {
	    	try {
	    		if(pCall != null) pCall.close();
	    		if(rsUsers != null) rsUsers.close();
	    		if(connSeverityUtil != null) connSeverityUtil.releaseConnection();
	    	}catch (CanonCustAppExceptionUtil csExp) {
	    		throw (csExp);
	    	}catch (SQLException eSQLExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_EmailValidation, Method:getEmanageUser()"));
	    	}catch (Exception eExp) {
	    		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_EmailValidation, Method:getEmanageUser()"));
	    	}
	    }
		return alEmanageUser;
	}
	
}
