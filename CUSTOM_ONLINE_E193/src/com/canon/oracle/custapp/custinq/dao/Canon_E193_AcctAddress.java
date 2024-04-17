package com.canon.oracle.custapp.custinq.dao;

import java.sql.*;
import java.util.ArrayList;



import oracle.jdbc.OracleTypes;

import com.canon.oracle.custapp.custinq.beans.*;
import com.canon.oracle.custapp.util.*;

/**
 * Canon_E193_AcctAddress Retrieves Account Addresses from database
 * Creation date: (7/27/2005 1:20:15 PM)
 * @author:
 */
public class Canon_E193_AcctAddress {
	
	/**
	 * Canon_E193_AcctAddress constructor.
	 */
	public Canon_E193_AcctAddress() {}

	/**
	 * Get addresses from database.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @param strAcctName java.util.String
	 * @param strAcctNum java.util.String
	 * @param strAddr1 java.util.String
	 * @param strAddr2 java.util.String
	 * @param strCity java.util.String
	 * @param strState java.util.String
	 * @param strZip java.util.String
	 * @param iOrgId int
	 * @return java.util.ArrayList
	 */
	public ArrayList getAddress(String strAcctName, String strAcctNum, String strAddr1, String strAddr2, 			
			String strCity, String strState, String strZip, int iOrgId, String strOrderName, String strOrderBy, 
			int iPageNo, int iTotalPageNo) throws CanonCustAppExceptionUtil {
		ArrayList alAddresses = new ArrayList();

		//Get Connection
		CanonCustAppDBUtil connAddrConnection = null;
		Connection connAddr = null;
		CallableStatement pCall = null;
		ResultSet rsAddr = null;
		Canon_E193_AcctAddressObj objAddr = null;
		try {
			System.out.println("in getAddress strAcctName " + strAcctName + " strAcctNum " + strAcctNum );
			connAddrConnection = new CanonCustAppDBUtil();
			connAddr = (Connection)(connAddrConnection.getConnection());
			pCall = connAddr.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_addr(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			pCall.setString(1, strAcctName);
			pCall.setString(2, strAcctNum);
			pCall.setString(3, strAddr1);
			pCall.setString(4, strAddr2);
			pCall.setString(5, strCity);
			pCall.setString(6, strState);
		 	pCall.setString(7, strZip);
		 	pCall.setInt(8, iOrgId);
		 	pCall.setString(9, strOrderName);
		 	pCall.setString(10, strOrderBy);
		 	pCall.setInt(11, iPageNo);
		 	pCall.registerOutParameter(11, OracleTypes.NUMBER);
		 	pCall.setInt(12, iTotalPageNo);
		 	pCall.registerOutParameter(12, OracleTypes.NUMBER);
		 	pCall.registerOutParameter(13, OracleTypes.CURSOR);

		 	pCall.execute();
		 	
		 	iPageNo = pCall.getInt(11);    	
		 	alAddresses.add(new Integer(iPageNo));
		 	
		 	iTotalPageNo = pCall.getInt(12);
		 	alAddresses.add(new Integer(iTotalPageNo));

		 	rsAddr = (ResultSet) pCall.getObject(13);

		 	while(rsAddr.next()) {
		 		objAddr = new  Canon_E193_AcctAddressObj();
		 		objAddr.setAcctName(rsAddr.getString(1));
		 		objAddr.setAcctNum(rsAddr.getString(2));
			  	objAddr.setAcctID(rsAddr.getString(3));
			  	objAddr.setLocID(rsAddr.getString(4));
			  	objAddr.setAddress1(rsAddr.getString(5));
			  	objAddr.setAddress2(rsAddr.getString(6));
			  	objAddr.setCity(rsAddr.getString(7));
			  	objAddr.setState(rsAddr.getString(8));
			  	objAddr.setPostalCode(rsAddr.getString(9));
			  	alAddresses.add(objAddr);
		 	}
		}catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		}catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctAddress, Method:getAddress()"));
		}catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctAddress, Method:getAddress()"));
		}
		finally {
			try {
				if(pCall != null) pCall.close();
				if(rsAddr != null) rsAddr.close();
				if(connAddrConnection != null) connAddrConnection.releaseConnection();
			}catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			}catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctAddress, Method:getAddress()"));
			}catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctAddress, Method:getAddress()"));
			}
		}
		return alAddresses;
  	}
}
