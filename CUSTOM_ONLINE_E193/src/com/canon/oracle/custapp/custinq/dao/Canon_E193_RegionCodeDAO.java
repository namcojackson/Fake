package com.canon.oracle.custapp.custinq.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.canon.oracle.custapp.util.CanonCustAppDBUtil;
import com.canon.oracle.custapp.util.CanonCustAppExceptionUtil;


/**
 * Canon_E193_RegionCodeDAO Retrieves region code from database based on the invoice number
 * Creation date: (7/27/2005 1:20:15 PM)
 * @author:
 * Changes
 *
 * <pre>
 * Flag  Date       By         Description
 * ----  ---------- ---------- ---------------------------------------------
 * 12-Oct-2007  Kireet K Bollam    ITG#  : CBS Consolidation Changes
 * </pre>
 */
public class Canon_E193_RegionCodeDAO {

	/**
	 * Canon_E193_RegionCodeDAO constructor comment.
	 */
	public Canon_E193_RegionCodeDAO() {}

	public final static String EASTERNREGCODE = "EAST_REGION";

	/**
	 * Get regionCode from database.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @param iRespId int
	 * @return int
	 */
	public static String getRegionCode(String pInvoiceNumber) throws CanonCustAppExceptionUtil, SQLException
  	{
		String regionCodeRet = EASTERNREGCODE;
	  	CallableStatement cstmtOrg = null;

		// Get Connection
		CanonCustAppDBUtil connOrg = new CanonCustAppDBUtil();
		Connection connOrgConnection = (Connection)(connOrg.getConnection());

		try
		{
			cstmtOrg = connOrgConnection.prepareCall("{? = call CANON_E193_CS_SQLS_PKG.SELECT_REGION(?,?)}");
			int ind=1;
			cstmtOrg.registerOutParameter(ind++, java.sql.Types.VARCHAR);
		  	cstmtOrg.setString(ind++,"Y"); // p_billing_issue
		  	cstmtOrg.setString(ind++,pInvoiceNumber); // p_invoice_number
			cstmtOrg.execute();

			regionCodeRet= cstmtOrg.getString(1);

			cstmtOrg.close();
			connOrgConnection.close();
			connOrg.releaseConnection();
		}

		catch (SQLException eSQLExp) {
		  throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_RegionCodeDAO, Method:getRegionCode(String pInvoiceNumber)"));
		}
		catch (Exception eExp) {
		  throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_RegionCodeDAO, Method:getRegionCode(String pInvoiceNumber)"));
		}
		finally {
			try {
				if(cstmtOrg != null)
					cstmtOrg.close();
				if(connOrgConnection != null)
					connOrgConnection.close();
				if(connOrg != null)
					connOrg.releaseConnection();
			}
			catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			}
			catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_RegionCodeDAO, Method:getRegionCode(String pInvoiceNumber)"));
			}
			catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_RegionCodeDAO, Method:getRegionCode(String pInvoiceNumber)"));
			}
		}
		return regionCodeRet;
  	}

}