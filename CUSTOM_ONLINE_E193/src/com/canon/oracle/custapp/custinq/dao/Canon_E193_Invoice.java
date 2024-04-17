package com.canon.oracle.custapp.custinq.dao;

import java.sql.*;
import java.util.*;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;

import java.math.BigDecimal;

import com.canon.oracle.custapp.custinq.beans.*;
import com.canon.oracle.custapp.util.*;

import canon.apps.common.CanonConstants;
import oracle.apps.jtf.aom.transaction.TransactionScope;

/**
 * Canon_E193_Invoice Retrieves Invoices from database
 * Creation date:
 * @author:
 * 05-Feb-2013	  ITG#434465 	Modified by Hema to handle Non-Serialized accessories.
 */
public class Canon_E193_Invoice {

	private final String strSqlRecTyp = CanonConstants.SCHEMA_NAME+".CANON_E193_OKS_BILLING_REC_TYP";
	private final String strSqlTableTyp = CanonConstants.SCHEMA_NAME+".CANON_E193_OKS_BILLING_TBL_TYP";
	private final String strClassName = "com.canon.oracle.custapp.custinq.beans.Canon_E193_OKSBillingDtlsObj";

	/**
	 * Canon_E193_Invoice constructor.
	 */
	public Canon_E193_Invoice() {}

	/**
	 * Get Invoices from database.
	 * Creation date: (7/27/2005 5:01:24 PM).
	 * @param iAcctId int
	 * @param strInvType java.util.String.
	 * @param strLowInvDate java.util.String
	 * @param strHighInvDate java.util.String
	 * @param iLowIdx int
	 * @param iHighIdx int
	 * @param strOrderBy java.util.String
	 * @param iOrgId int
	 * @return java.util.ArrayList
	 */
	public ArrayList getInvoices(int iAcctId, String strInvType, String strLowInvDate, String strHighInvDate,
			int iOrgId, String strOrderName, String strOrderBy, int iPageNo, int iTotPageNo) throws CanonCustAppExceptionUtil {

		CallableStatement cstmtInv = null;
		ResultSet rsInv = null;
		ArrayList alInvoices = new ArrayList();

		//Get Connection
		CanonCustAppDBUtil connInv = null;
		Connection connInvConnection = null;

		try {
			System.out.println("in getInvoices iAcctId " + iAcctId + " strInvType " + strInvType + " strLowInvDate " + strLowInvDate 
					+ " strHighInvDate " + strHighInvDate + " strOrderName " + strOrderName + " strOrderBy " + strOrderBy 
					+ " iPageNo " +iPageNo + " iTotPageNo " + iTotPageNo);
			connInv = new CanonCustAppDBUtil();
			connInvConnection = (Connection)(connInv.getConnection());

			cstmtInv = connInvConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_inv(?,?,?,?,?,?,?,?,?,?)}");

			cstmtInv.setInt(1, iAcctId);
			cstmtInv.setString(2, strInvType);
			cstmtInv.setString(3, strLowInvDate);
			cstmtInv.setString(4, strHighInvDate);
			cstmtInv.setString(5, strOrderName);
			cstmtInv.setString(6, strOrderBy);
			cstmtInv.setInt(7, iOrgId);
			cstmtInv.setInt(8, iPageNo);
			cstmtInv.registerOutParameter(8, OracleTypes.NUMBER);
			cstmtInv.setInt(9, iTotPageNo);
			cstmtInv.registerOutParameter(9, OracleTypes.NUMBER);
			cstmtInv.registerOutParameter(10, OracleTypes.CURSOR);

			cstmtInv.execute();

			iPageNo = cstmtInv.getInt(8);
			alInvoices.add(new Integer(iPageNo));
			iTotPageNo = cstmtInv.getInt(9);
			alInvoices.add(new Integer(iTotPageNo));

			rsInv = (ResultSet) cstmtInv.getObject(10);

			while(rsInv.next()) {
				Canon_E193_InvoiceObj objInv = new  Canon_E193_InvoiceObj();
				objInv.setICustTrxId(rsInv.getLong(1));
				objInv.setStrTrxNum(rsInv.getString(2));
				objInv.setStrTrxDate(rsInv.getString(3));
				objInv.setStrTrxType(rsInv.getString(4));
				objInv.setStrContNum(rsInv.getString(5));
				objInv.setStrOrdrType(rsInv.getString(6));
				objInv.setStrSalesRep(rsInv.getString(7));
				objInv.setStrAttribute1(rsInv.getString(8));
				objInv.setConsolidateBillNum(rsInv.getString(9));
				alInvoices.add(objInv);
			}
			
			//System.out.println("iPageNo " +iPageNo + " iTotPageNo " + iTotPageNo + " getInvoices -----> "+ alInvoices);
		}catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		}catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoices()"));
		}catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoices()"));
		}
		finally {
			try {
				if(cstmtInv != null) cstmtInv.close();
				if(rsInv != null) rsInv.close();
				if(connInvConnection != null) connInvConnection.close();
				if(connInv != null) connInv.releaseConnection();
			}catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			}catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoices()"));
			}catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoices()"));
			}
		}
		//System.out.println(" alInvoices = "+ alInvoices.toString());
		return alInvoices;
	}

	/**
	 * Get Invoices from database.
	 * Creation date: (7/27/2005 5:01:24 PM)
	 * @param iAcctId int
	 * @param iOrgId int
	 * @param strInvoiceNo java.util.String
	 * @return Canon_E193_InvoiceObj
	 */
	public Canon_E193_InvoiceObj getInvoiceDetails(int iAcctId, int iOrgId, String strInvoiceNo) throws CanonCustAppExceptionUtil {
		CallableStatement cstmtInv = null;
		ResultSet rsInv = null;

		//Get Connection
		CanonCustAppDBUtil connInv = null;
		Connection connInvConnection = null;

		//Made Changes for ITG Ticket 42004/41734 by Sachin Mittal for Null pointer Excpetion for Unassigned Tickets
		Canon_E193_InvoiceObj objInv =  new  Canon_E193_InvoiceObj();

		try {
			System.out.println(" in getInvoiceDetails iAcctId " + iAcctId + " strInvoiceNo " + strInvoiceNo);
			connInv = new CanonCustAppDBUtil();
			connInvConnection = (Connection)(connInv.getConnection());

			cstmtInv = connInvConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_inv_details(?,?,?,?)}");
			//System.out.println(" : 1 : " + strInvoiceNo);
			cstmtInv.setInt(1, iAcctId);
			cstmtInv.setInt(2, iOrgId);
			cstmtInv.setString(3, strInvoiceNo);
			cstmtInv.registerOutParameter(4, OracleTypes.CURSOR);
            //System.out.println("Before execute the query : ");
			cstmtInv.execute();
			//System.out.println("After execute the query : ");
			rsInv = (ResultSet) cstmtInv.getObject(4);
          
		    if(rsInv.next()) {

				//Made Changes for ITG Ticket 42004/41734 by Sachin Mittal for Null pointer Excpetion for Unassigned Tickets
				//objInv = new  Canon_E193_InvoiceObj();
				objInv.setICustTrxId(rsInv.getLong(1));
				
				objInv.setStrTrxNum(rsInv.getString(2));
				
				objInv.setStrTrxDate(rsInv.getString(3));
				
				objInv.setStrTrxType(rsInv.getString(4));
				
				objInv.setStrContNum(rsInv.getString(5));
				
				objInv.setStrContNumMod(rsInv.getString(6));
				
				objInv.setStrOrdrNum(rsInv.getString(7));
				
				objInv.setStrOrdrType(rsInv.getString(8));
				
				objInv.setStrTicketNo(rsInv.getString(9));
				
				objInv.setStrPurchageOrder(rsInv.getString(10));
				//Basudev - Defect ID# 6001576 - Start.
				//objInv.setIBillToSiteUseId(rsInv.getInt(11));
				objInv.setIBillToSiteUseId(rsInv.getString(11).toString());
				
				//objInv.setIShipToSiteUseId(rsInv.getInt(12));
				objInv.setIShipToSiteUseId(rsInv.getString(12).toString());
				//Basudev - Defect ID# 6001576 - End.
				objInv.setStrDueDate(rsInv.getString(13));
				
				objInv.setIAmtDueOrig(rsInv.getDouble(14));
				
				objInv.setIAmtDueRemaining(rsInv.getDouble(15));
				
				objInv.setIAmtAppld(rsInv.getDouble(16));
				
				objInv.setIAmtAdjusted(rsInv.getDouble(17));
				
				objInv.setIAmtCredited(rsInv.getDouble(18));
				
				objInv.setITaxOrig(rsInv.getDouble(19));
				
				objInv.setIFreightOrig(rsInv.getDouble(20));
				
				objInv.setStrStatus(rsInv.getString(21));
				
				// setting incorrect billing header details
				objInv.setStrAttribute1(rsInv.getString(22));
				
				objInv.setStrAttribute2(rsInv.getString(23));
				
				objInv.setStrAttribute3(rsInv.getString(24));
				
				objInv.setStrAttribute4(rsInv.getString(25));
				
				objInv.setStrAttribute5(rsInv.getString(26));
				
				objInv.setStrAttribute6(rsInv.getString(27));
				
				objInv.setStrAttribute7(rsInv.getString(28));
				
				objInv.setAggContractSts(rsInv.getString(29));
				
			}
		}catch (CanonCustAppExceptionUtil csExp) {

			throw (csExp);
		}catch (SQLException eSQLExp) {

			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoiceDetails()"));
		}catch (Exception eExp) {

			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoiceDetails()"));
		}
		finally {
			try {
				if(cstmtInv != null) cstmtInv.close();
				if(rsInv != null) rsInv.close();
				if(connInvConnection != null) connInvConnection.close();
				if(connInv != null) connInv.releaseConnection();
			}catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			}catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoiceDetails()"));
			}catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoiceDetails()"));
			}
		}
		return objInv;
	}

  /**
   * Get Invoice count from database.
   * Creation date: (7/27/2005 5:01:24 PM)
   * @param iAcctId int
   * @param strInvType java.util.String
   * @param strLowInvDate java.util.String
   * @param strHighInvDate java.util.String
   * @param iOrgId int
   * @return int
   */
 /* Not currently using
  public int getInvoiceCount(int iAcctId, String strInvType, String strLowInvDate,
  		String strHighInvDate, int iOrgId) throws CanonCustAppExceptionUtil {

    CallableStatement cstmtInv = null;
    int iInvoiceCount = 0;

    //Get Connection
    CanonCustAppDBUtil connInv = new CanonCustAppDBUtil();
    Connection connInvConnection = (Connection)(connInv.getConnection());

    try
    {
		cstmtInv = connInvConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_inv_count(?,?,?,?,?,?)}");

		cstmtInv.setInt(1,iAcctId);
		cstmtInv.setString(2,strInvType);
		cstmtInv.setString(3,strLowInvDate);
		cstmtInv.setString(4,strHighInvDate);
		cstmtInv.setInt(5,iOrgId);
		cstmtInv.registerOutParameter(6,java.sql.Types.INTEGER);

		cstmtInv.execute();

		iInvoiceCount = cstmtInv.getInt(6);

		cstmtInv.close();
		connInvConnection.close();
		connInv.releaseConnection();
    }
    catch (CanonCustAppExceptionUtil csExp) {
      throw (csExp);
    }
    catch (SQLException eSQLExp) {
      throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoiceCount()"));
    }
    catch (Exception eExp) {
      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoiceCount()"));
    }
    finally {
      try
      {
        if(cstmtInv != null)
          cstmtInv.close();
        if(connInvConnection != null)
        	connInvConnection.close();
        if(connInv != null)
          connInv.releaseConnection();
      }
      catch (CanonCustAppExceptionUtil csExp) {
        throw (csExp);
      }
      catch (SQLException eSQLExp) {
        throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoiceCount()"));
      }
      catch (Exception eExp) {
        throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoiceCount()"));
      }
    }
    return iInvoiceCount;
  }

*/

  /**
   * Get Invoices lines from database.
   * Creation date: (9/13/2005 5:01:24 PM)
   * @param iAcctId int
   * @param strInvType java.util.String
   * @param strLowInvDate java.util.String
   * @param strHighInvDate java.util.String
   * @param iLowIdx int
   * @param iHighIdx int
   * @param strOrderBy java.util.String
   * @param iOrgId int
   * @return java.util.ArrayList
   */

  public ArrayList getInvoiceLines(int iOrgId, String strInvoiceNo) throws CanonCustAppExceptionUtil {
	    CallableStatement cstmtInvLines = null;
	    ResultSet rsInvLines = null;
	    ArrayList alInvLines = new ArrayList();
	    Canon_E193_InvoiceLinesObj objInvLines = null;

	    //Get Connection
	    CanonCustAppDBUtil connInvLines = new CanonCustAppDBUtil();
	    Connection connInvConnection = (Connection)(connInvLines.getConnection());

	    try {
	    	System.out.println(" in getInvoiceLines strInvoiceNo " + strInvoiceNo);
			cstmtInvLines = connInvConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_inv_lines(?,?,?,?)}");

			cstmtInvLines.setInt(1, iOrgId);
			cstmtInvLines.setString(2, strInvoiceNo);
			cstmtInvLines.registerOutParameter(3, OracleTypes.CURSOR);
			cstmtInvLines.registerOutParameter(4, OracleTypes.VARCHAR);

			cstmtInvLines.execute();
			String strStatus = cstmtInvLines.getString(4);
			if(strStatus != null) alInvLines.add(strStatus);

			rsInvLines = (ResultSet) cstmtInvLines.getObject(3);

	      while(rsInvLines.next()) {
	    	  objInvLines = new  Canon_E193_InvoiceLinesObj();
	    	  objInvLines.setCustTrxId(rsInvLines.getString(1));
	    	  objInvLines.setCustTrxLineId(rsInvLines.getString(2));
	    	  objInvLines.setLineNo(rsInvLines.getInt(3));
	    	  objInvLines.setItemNo(rsInvLines.getString(4));
	    	  objInvLines.setLineDesc(rsInvLines.getString(5));
	    	  objInvLines.setUnitPrice(rsInvLines.getDouble(6));
	    	  objInvLines.setQuantity(rsInvLines.getInt(7));
	    	  objInvLines.setUom(rsInvLines.getString(8));
	    	  objInvLines.setLineTotal(rsInvLines.getDouble(9));
	    	  alInvLines.add(objInvLines);
	      }
	      cstmtInvLines.close();
	      rsInvLines.close();
	      connInvConnection.close();
	      connInvLines.releaseConnection();
	    }
	    catch (CanonCustAppExceptionUtil csExp) {
	      throw (csExp);
	    }
	    catch (SQLException eSQLExp) {
	      throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoiceLines()"));
	    }
	    catch (Exception eExp) {
	      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoiceLines()"));
	    }
	    finally {
	      try
	      {
	        if(cstmtInvLines != null) cstmtInvLines.close();
	        if(rsInvLines != null) rsInvLines.close();
	        if(connInvConnection != null) connInvConnection.close();
	        if(connInvLines != null) connInvLines.releaseConnection();
	      }
	      catch (CanonCustAppExceptionUtil csExp) {
	        throw (csExp);
	      }
	      catch (SQLException eSQLExp) {
	        throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoiceLines()"));
	      }
	      catch (Exception eExp) {
	        throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoiceLines()"));
	      }
	    }
	    return alInvLines;
	  }

	/**
	 * Get Invoices lines from database.
	 * Creation date: (9/13/2005 5:01:24 PM)
	 * @param iOrgId int.
	 * @param strInvoiceNo java.util.String.
	 * @return java.util.ArrayList.
	 */
	public ArrayList getOksBillingDtls(int iOrgId, String strInvoiceNo) throws CanonCustAppExceptionUtil {
		ArrayList alInvLines = new ArrayList();

		Canon_E193_OKSBillingDtlsObj objBill = null;
		CallableStatement cStmt = null;
		//ResultSet rs = null;

		//Get Connection
		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
		System.out.println("in getOksBillingDtls strInvoiceNo : " + strInvoiceNo);
		try {
			connUtil = new CanonCustAppDBUtil();
			conn = (Connection)(connUtil.getConnection());
		
			cStmt = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.get_oks_billing_dtls(?,?,?)}");			
			cStmt.setInt(1, iOrgId);
			cStmt.setString(2, strInvoiceNo);			
			cStmt.registerOutParameter(3, OracleTypes.ARRAY, strSqlTableTyp);
			
			cStmt.execute();
			
			ARRAY objArray =  (ARRAY) cStmt.getObject(3);
		    //Object obj[] =(Object[]) ((ARRAY)cStmt.getObject(3)).getArray();
			if(objArray!=null){
			Object[] obj = (Object[])CanonCustAppUtil.getRecordObject(objArray, strClassName, conn, strSqlRecTyp);
			//System.out.println("Canon_E193_OKSBillingDtlsObj length:"+obj.length);
			
	    	for(int i=0; i<obj.length; i++) {
	    		objBill = (Canon_E193_OKSBillingDtlsObj)obj[i];
	    		alInvLines.add(objBill);
	    	}
	    	System.out.println("End getOksBillingDtls");
			}
		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getOksBillingDtls()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getOksBillingDtls()"));
		} finally {
			try {
				if (cStmt != null) cStmt.close();
				//if (rs != null) rs.close();
				if (connUtil != null) connUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getOksBillingDtls()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getOksBillingDtls()"));
			}
		}
		return alInvLines;
	}
	
	
	public ArrayList getOksAggregatePricingDtls(String strInvoiceNo, String strInvoiceType) throws CanonCustAppExceptionUtil {
		ArrayList aggInvLines = new ArrayList();

		Canon_E193_OKSBillingDtlsObj objBill = null;
		CallableStatement cStmt = null;

		//Get Connection
		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;
		System.out.println("in getOksAggregatePricingDtls strInvoiceNo : " + strInvoiceNo +" strInvoiceType " + strInvoiceType);
		try {
			connUtil = new CanonCustAppDBUtil();
			conn = (Connection)(connUtil.getConnection());
		
			cStmt = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.GET_OKS_AGG_PRICING_DTLS(?,?,?)}");			
			cStmt.setString(1, strInvoiceNo);
			cStmt.setString(2, strInvoiceType);
			cStmt.registerOutParameter(3, OracleTypes.ARRAY, strSqlTableTyp);
			
			cStmt.execute();
			
			ARRAY objArray =  (ARRAY) cStmt.getObject(3);
			if(objArray!=null){
				Object[] obj = (Object[])CanonCustAppUtil.getRecordObject(objArray, strClassName, conn, strSqlRecTyp);
				//System.out.println("Canon_E193_OKSBillingDtlsObj length:"+obj.length);
				
		    	for(int i=0; i<obj.length; i++) {
		    		objBill = (Canon_E193_OKSBillingDtlsObj)obj[i];
		    		aggInvLines.add(objBill);
		    	}
			}
		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getOksAggregatePricingDtls()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getOksAggregatePricingDtls()"));
		} finally {
			try {
				if (cStmt != null) cStmt.close();
				if (connUtil != null) connUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getOksAggregatePricingDtls()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getOksAggregatePricingDtls()"));
			}
		}
		return aggInvLines;
	}

	/**
	 * Get Invoices lines from database.
	 * Creation date: (9/13/2005 5:01:24 PM)
	 * @param iOrgId int.
	 * @param strFleetContract java.lang.String.
	 * @param strInvType java.lang.String.
	 * @param strType java.lang.String.
	 * @param strInvoiceNo java.lang.String.
	 * @param strSerialNo String[].
	 * @param strContId java.lang.String.
	 * @param strContNo java.lang.String.
	 * @param strInvoiceNo java.util.String.
	 * @return java.util.ArrayList.
	 */
	public ArrayList getOksBillingSerialNoDtls(int iOrgId, String strFleetContract, String strInvType, String strType,
			String strInvoiceNo, String[] strSerialNo, String strContId, String strContNo,int svcInvLinePk) throws CanonCustAppExceptionUtil {
		ArrayList alInvLines = new ArrayList();

		Canon_E193_OKSBillingDtlsObj objBill = null;
		CallableStatement cStmt = null;
		System.out.println("in getOksBillingSerialNoDtls iOrgId = "+iOrgId + " strFleetContract= "+strFleetContract+" strInvType= "+ strInvType+ " strType= "+strType
    		              +" strInvoiceNo= "+strInvoiceNo+ " strSerialNo= "+ Arrays.toString(strSerialNo)+ 
    		              " strContId= "+ strContId + " strContNo= "+strContNo+ " svcInvLinePk= "+svcInvLinePk);
		//Get Connection
		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;

		try
		{
			connUtil = new CanonCustAppDBUtil();
			conn = (Connection)(connUtil.getConnection());

			if(strSerialNo != null && strSerialNo.length > 0)
			{
				HashSet set = new HashSet();
				for(int k=0; k<strSerialNo.length; k++)
				{
					set.add(strSerialNo[k]);
				}

				Iterator ite = set.iterator();
				String strSerial = "";
				String strSerialNum ="";
				String strInvNum ="";
				String strProductNum ="";
				String strInvLinePKVal ="";
				String[] temp = new String[3];
				//System.out.println("length of Canon_E193_OKSBillingDtlsObj before execute 5");
				while (ite.hasNext()) 
				{
					//System.out.println("length of Canon_E193_OKSBillingDtlsObj before execute 1 ");						
					strSerialNum = ite.next().toString();
					//System.out.println("length of Canon_E193_OKSBillingDtlsObj before execute 2" + strSerialNum);
					temp = strSerialNum.split("~");
					//System.out.println("length of Canon_E193_OKSBillingDtlsObj before execute 3 temp = "+temp);
					if(temp!=null && temp.length>0 )
					{
						//System.out.println(" Temp is greater then 0 ");
						if ( temp.length > 1) 
						{
							//System.out.println(" Temp is greater then 1 "+temp.length);
							if(temp.length>0)
								strSerial = temp[0];
							if(temp.length>1)
								strInvNum = temp[1];
							if(temp.length>2)
								strProductNum = temp[2];
							if(temp.length>3)
								strInvLinePKVal = temp[3];
							//System.out.println("length of Canon_E193_OKSBillingDtlsObj before execute 4 ");	
						}
						else 
						{
							//System.out.println(" Temp is greater then 1 else  ");
							strSerial = temp[0];
							strInvNum = strInvoiceNo;
						}
					}
					else
					{
						//System.out.println(" Temp is greater then 0 else ");
					}
					//System.out.println("Value of strSerial = " + strSerial);
					//System.out.println("Value of strInvNum = " + strInvNum);
					//System.out.println("length of Canon_E193_OKSBillingDtlsObj before execute 6");
					System.out.println("strFleetContract in getOksBillingSerialNoDtls: " + strFleetContract + " strInvType : " 
								+ strInvType +" strType  :" + strType + " strInvLinePKVal " + strInvLinePKVal + " svcInvLinePk " + svcInvLinePk );
					//Made Changes for MW Project by Naveen Khandelwal to add one more parameter to PL/SQL procedure call
					
					if(strInvLinePKVal != null && svcInvLinePk != 0 && strInvLinePKVal.trim().equals(svcInvLinePk+""))
					{
						if(("USAGE".equalsIgnoreCase(strInvType) || "BASE".equalsIgnoreCase(strInvType)) && "READ".equalsIgnoreCase(strType))
							cStmt = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.get_oks_read_dtls(?,?,?,?,?,?,?,?)}");
						else if("USAGE".equalsIgnoreCase(strInvType) && "PRICE".equalsIgnoreCase(strType))
							cStmt = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.get_oks_pricing_dtls(?,?,?,?,?,?,?)}");
						else throw new SQLException("Invalid statement "+cStmt);
						
						//System.out.println("length of Canon_E193_OKSBillingDtlsObj before execute 7");
						cStmt.setInt(1, iOrgId);
						cStmt.setString(2, strFleetContract);
						//cStmt.setString(3, strInvoiceNo);
						cStmt.setString(3, strInvNum);
						//System.out.println(" Value of strSerial = " + strSerial);
						cStmt.setString(4, strSerial);
						//MW Project Changes
						cStmt.setString(5, strInvType);
						cStmt.registerOutParameter(6, OracleTypes.ARRAY, strSqlTableTyp);
						if(("USAGE".equalsIgnoreCase(strInvType) || "BASE".equalsIgnoreCase(strInvType)) && "READ".equalsIgnoreCase(strType))			
						{
							cStmt.setString(7, strProductNum);
							cStmt.setInt(8, svcInvLinePk);
						}
						else if("USAGE".equalsIgnoreCase(strInvType) && "PRICE".equalsIgnoreCase(strType))
							cStmt.setInt(7, svcInvLinePk);
						
						//System.out.println("length of Canon_E193_OKSBillingDtlsObj before execute");
						cStmt.execute();
						//System.out.println("length of Canon_E193_OKSBillingDtlsObj after execute");
						ARRAY objArray = (ARRAY) cStmt.getObject(6);
						//Object obj[] =(Object[]) ((ARRAY)cStmt.getObject(6)).getArray();
						//System.out.println("length of Canon_E193_OKSBillingDtlsObj after get array ");
						Object[] obj = (Object[])CanonCustAppUtil.getRecordObject(objArray, strClassName, conn, strSqlRecTyp);
				    	//System.out.println("length of Canon_E193_OKSBillingDtlsObj = "+obj.length);
						for(int j=0; j<obj.length; j++)
						{
				    		objBill = (Canon_E193_OKSBillingDtlsObj)obj[j];
				    		alInvLines.add(objBill);
				    	}
						//System.out.println("All invoices Canon_E193_OKSBillingDtlsObj = " + alInvLines);
					}
				}
			}

		} catch (CanonCustAppExceptionUtil csExp) {
			csExp.printStackTrace();
			throw (csExp);
		} catch (SQLException eSQLExp) {
			eSQLExp.printStackTrace();
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getOksBillingSerialNoDtls()"));
		} catch (Exception eExp) {
			eExp.printStackTrace();
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getOksBillingSerialNoDtls()"));
		} finally {
			try {
				if (cStmt != null) cStmt.close();
				if (connUtil != null) connUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getOksBillingSerialNoDtls()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getOksBillingSerialNoDtls()"));
			}
		}
		return alInvLines;
	}
	
	
	
	public ArrayList getOksBillingSerialNoDtlsDisplay(int iOrgId, String strFleetContract, String strInvType, String strType,
			String strInvoiceNo, String[] strSerialNo, String strContId, String strContNo) throws CanonCustAppExceptionUtil {
		ArrayList alInvLines = new ArrayList();

		Canon_E193_OKSBillingDtlsObj objBill = null;
		CallableStatement cStmt = null;
		System.out.println("in getOksBillingSerialNoDtlsDisplay iOrgId = "+iOrgId + " strFleetContract= "+strFleetContract+" strInvType= "+ strInvType+ " strType= "+strType
    		              +" strInvoiceNo= "+strInvoiceNo+ " strSerialNo= "+ Arrays.toString(strSerialNo)+ 
    		              " strContId= "+ strContId + " strContNo= "+strContNo);
		//Get Connection
		CanonCustAppDBUtil connUtil = null;
		Connection conn = null;

		try
		{
			connUtil = new CanonCustAppDBUtil();
			conn = (Connection)(connUtil.getConnection());

			if(strSerialNo != null && strSerialNo.length > 0)
			{
				HashSet set = new LinkedHashSet();
				for(int k=0; k<strSerialNo.length; k++)
				{
					set.add(strSerialNo[k]);
				}

				Iterator ite = set.iterator();
				String strSerial = "";
				String strSerialNum ="";
				String strInvNum ="";
				String strProductNum ="";
				String strInvLinePKVal ="";
				int invLinePk = 0;
				
				String[] temp = new String[3];
				//System.out.println("length of Canon_E193_OKSBillingDtlsObj before execute 5");
				while (ite.hasNext()) 
				{
					//System.out.println("length of Canon_E193_OKSBillingDtlsObj before execute 1 ");						
					strSerialNum = ite.next().toString();
					//System.out.println("length of Canon_E193_OKSBillingDtlsObj before execute 2" + strSerialNum);
					temp = strSerialNum.split("~");
					//System.out.println("length of Canon_E193_OKSBillingDtlsObj before execute 3 temp = "+temp);
					if(temp!=null && temp.length>0 )
					{
						//System.out.println(" Temp is greater then 0 ");
						if ( temp.length > 1) 
						{
							//System.out.println(" Temp is greater then 1 "+temp.length);
							if(temp.length>0)
								strSerial = temp[0];
							if(temp.length>1)
								strInvNum = temp[1];
							if(temp.length>2)
								strProductNum = temp[2];
							if(temp.length>3)
								strInvLinePKVal = temp[3];
							//System.out.println("length of Canon_E193_OKSBillingDtlsObj before execute 4 ");	
						}
						else 
						{
							//System.out.println(" Temp is greater then 1 else  ");
							strSerial = temp[0];
							strInvNum = strInvoiceNo; 
						}
					}
					else
					{
						//System.out.println(" Temp is greater then 0 else ");
					}
					//System.out.println("Value of strSerial = " + strSerial);
					//System.out.println("Value of strInvNum = " + strInvNum);
					//System.out.println("length of Canon_E193_OKSBillingDtlsObj before execute 6");
					/*System.out.println("in getOksBillingSerialNoDtlsDisplay strFleetContract " + strFleetContract + "strInvType : " + strInvType +"strType  :" + strType
								+ " strInvLinePKVal " + strInvLinePKVal);*/
					//Made Changes for MW Project by Naveen Khandelwal to add one more parameter to PL/SQL procedure call
					invLinePk = 0;
					if(strInvLinePKVal != null && !strInvLinePKVal.trim().isEmpty() && !strInvLinePKVal.trim().equals("null"))
					{
						invLinePk = Integer.parseInt(strInvLinePKVal);
					}
					
					if(("USAGE".equalsIgnoreCase(strInvType) || "BASE".equalsIgnoreCase(strInvType)) && "READ".equalsIgnoreCase(strType))
						cStmt = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.get_oks_read_dtls(?,?,?,?,?,?,?,?)}");
					else if("USAGE".equalsIgnoreCase(strInvType) && "PRICE".equalsIgnoreCase(strType))
						cStmt = conn.prepareCall("{call CANON_E193_CS_SQLS_PKG.get_oks_pricing_dtls(?,?,?,?,?,?,?)}");
					else throw new SQLException("Invalid statement "+cStmt);
					
					//System.out.println("length of Canon_E193_OKSBillingDtlsObj before execute 7");
					System.out.println("in getOksBillingSerialNoDtlsDisplay iOrgId : " + iOrgId + " strFleetContract : " + strFleetContract 
							+ " strInvNum : " + strInvNum + " strSerial : "+ strSerial + " strInvType : " + strInvType + 
							" strProductNum : " + strProductNum + " invLinePk : " + invLinePk); 
					
					
					cStmt.setInt(1, iOrgId);
					cStmt.setString(2, strFleetContract);
					//cStmt.setString(3, strInvoiceNo);
					cStmt.setString(3, strInvNum);
					
					cStmt.setString(4, strSerial);
					//MW Project Changes
					cStmt.setString(5, strInvType);
					cStmt.registerOutParameter(6, OracleTypes.ARRAY, strSqlTableTyp);
					if(("USAGE".equalsIgnoreCase(strInvType) || "BASE".equalsIgnoreCase(strInvType)) && "READ".equalsIgnoreCase(strType))			
					{
						cStmt.setString(7, strProductNum);
						cStmt.setInt(8, invLinePk);
					}
					else if("USAGE".equalsIgnoreCase(strInvType) && "PRICE".equalsIgnoreCase(strType))
						cStmt.setInt(7, invLinePk);
					
					//System.out.println("length of Canon_E193_OKSBillingDtlsObj before execute");
					cStmt.execute();
					//System.out.println("length of Canon_E193_OKSBillingDtlsObj after execute");
					ARRAY objArray = (ARRAY) cStmt.getObject(6);
					//Object obj[] =(Object[]) ((ARRAY)cStmt.getObject(6)).getArray();
					//System.out.println("length of Canon_E193_OKSBillingDtlsObj after get array ");
					Object[] obj = (Object[])CanonCustAppUtil.getRecordObject(objArray, strClassName, conn, strSqlRecTyp);
			    	//System.out.println("length of Canon_E193_OKSBillingDtlsObj = "+obj.length);
					for(int j=0; j<obj.length; j++)
					{
			    		objBill = (Canon_E193_OKSBillingDtlsObj)obj[j];
			    		alInvLines.add(objBill);
			    	}
					//System.out.println("All invoices Canon_E193_OKSBillingDtlsObj = " + alInvLines);
				}
			}

		} catch (CanonCustAppExceptionUtil csExp) {
			csExp.printStackTrace();
			throw (csExp);
		} catch (SQLException eSQLExp) {
			eSQLExp.printStackTrace();
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getOksBillingSerialNoDtls()"));
		} catch (Exception eExp) {
			eExp.printStackTrace();
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getOksBillingSerialNoDtls()"));
		} finally {
			try {
				if (cStmt != null) cStmt.close();
				if (connUtil != null) connUtil.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getOksBillingSerialNoDtls()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getOksBillingSerialNoDtls()"));
			}
		}
		return alInvLines;
	}

	/**
	 * Get Actual Invoice from 170 System.
	 * Creation date: (9/13/2005 5:01:24 PM)
	 * @param iOrgId int.
	 * @param strInvoiceNumber java.lang.String.
	 * @return java.lang.String
	 */
	public String getActualInvoice(int iOrgId, String strInvoiceNumber)
			throws CanonCustAppExceptionUtil {
		String strActualInvLink = "";
		String strStatus = "";

		//Get Connection
		CanonCustAppDBUtil connectionInvLink = null;
		Connection connInvLink = null;
		CallableStatement cstmtInvLink = null;

		try {
			System.out.println("in getActualInvoice strInvoiceNumber " + strInvoiceNumber);
			connectionInvLink = new CanonCustAppDBUtil();
			connInvLink = (Connection)(connectionInvLink.getConnection());

			cstmtInvLink = connInvLink.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_170_system_link(?,?,?,?)}");

			cstmtInvLink.setInt(1, iOrgId);
			cstmtInvLink.setString(2, strInvoiceNumber);
			cstmtInvLink.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmtInvLink.registerOutParameter(4, java.sql.Types.VARCHAR);
			cstmtInvLink.execute();

			strActualInvLink = cstmtInvLink.getString(3);
			strStatus = cstmtInvLink.getString(4);

			if(!("S".equalsIgnoreCase(strStatus)))
				strActualInvLink = "ERROR";

		} catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		} catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getActualInvoice()"));
		} catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getActualInvoice()"));
		} finally {
			try {
				if (cstmtInvLink != null) cstmtInvLink.close();
				if (connectionInvLink != null) connectionInvLink.releaseConnection();
			} catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			} catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getActualInvoice()"));
			} catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getActualInvoice()"));
			}
		}
		return strActualInvLink;
	}
	public ArrayList getInvoiceList(int iOrgId, String strInvType) throws CanonCustAppExceptionUtil {
		CallableStatement cstmtInv = null;
		ResultSet rsInv = null;
		ArrayList alInvoices = new ArrayList();
		//Get Connection
		CanonCustAppDBUtil connInv = null;
		Connection connInvConnection = null;
		try {
			System.out.println("in getInvoiceList strInvType " + strInvType);
			connInv = new CanonCustAppDBUtil();
			connInvConnection = (Connection)(connInv.getConnection());
			cstmtInv = connInvConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.get_inv_numbers(?,?,?)}");
			cstmtInv.setInt(1, iOrgId);
			cstmtInv.setString(2, strInvType);
			cstmtInv.registerOutParameter(3, OracleTypes.CURSOR);
			cstmtInv.execute();
			rsInv =  (ResultSet) cstmtInv.getObject(3);
			while(rsInv.next()) {
				Canon_E193_InvoiceObj objInv = new  Canon_E193_InvoiceObj();
				objInv.setStrTrxNum(rsInv.getString(1));
				alInvoices.add(objInv);
			}
		}catch (CanonCustAppExceptionUtil csExp) {
			System.err.println("!!!!!!! csExp =  "+csExp);
			throw (csExp);
		}catch (SQLException eSQLExp) {
			System.err.println("!!!!!!! eSQLExp  = "+eSQLExp);
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctInfo, Method:getInvoiceList()"));
		}catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctInfo, Method:getInvoiceList()"));
		}
		finally {
			try {
				if(cstmtInv != null) cstmtInv.close();
				if(rsInv != null) rsInv.close();
				if(connInvConnection != null) connInvConnection.close();
				if(connInv != null) connInv.releaseConnection();
			}catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			}catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctInfo, Method:getInvoiceList()"));
			}catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctInfo, Method:getInvoiceList()"));
			}
		}
		return alInvoices;
	}
	
	// this is for getting the cons bill number 
	public String[] getConsBillNumber(String trxNumber, int orgId) throws CanonCustAppExceptionUtil {
		CallableStatement cstmtInv = null;
		
		String cons_bill_dtls[] = new String[2];
		//Get Connection
		CanonCustAppDBUtil connInv = null;
		Connection connInvConnection = null;
		try {
			System.out.println("in getConsBillNumber trxNumber " + trxNumber);
			connInv = new CanonCustAppDBUtil();
			connInvConnection = (Connection)(connInv.getConnection());
			cstmtInv = connInvConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.get_cons_bill_number(?,?,?,?)}");
			cstmtInv.setString(1, trxNumber);
			cstmtInv.setBigDecimal(2, new BigDecimal(orgId));
			cstmtInv.registerOutParameter(3, OracleTypes.VARCHAR);
			cstmtInv.registerOutParameter(4, OracleTypes.VARCHAR);
			cstmtInv.execute();
			cons_bill_dtls[0] = cstmtInv.getString(3);
			cons_bill_dtls[1] = cstmtInv.getString(4);
		}catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		}catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctInfo, Method:getConsBillNumber()"));
		}catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctInfo, Method:getConsBillNumber()"));
		}
		finally {
			try {
				if(cstmtInv != null) cstmtInv.close();
				if(connInvConnection != null) connInvConnection.close();
				if(connInv != null) connInv.releaseConnection();
			}catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			}catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctInfo, Method:getConsBillNumber()"));
			}catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctInfo, Method:getConsBillNumber()"));
			}
		}
		return cons_bill_dtls;
	}
	
	// this is for counters display for invo  added third parameter called SvcinvlinePk
	public ArrayList getCounterReadList(String trxNumber, String serialNumber, int svcinvlinePk ) throws CanonCustAppExceptionUtil {
		CallableStatement cstmtInv = null;
		ResultSet rsInv = null;
		ArrayList counterRead = new ArrayList();
		//Get Connection
		CanonCustAppDBUtil connInv = null;
		Connection connInvConnection = null;
		try {
			System.out.println("in getCounterReadList trxNumber " + trxNumber + " serialNumber " 
					+ serialNumber + " svcinvlinePk " + svcinvlinePk);
			connInv = new CanonCustAppDBUtil();
			connInvConnection = (Connection)(connInv.getConnection());
			cstmtInv = connInvConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.get_counter_data(?,?,?,?)}"); // Added a "?"
			cstmtInv.setString(1, trxNumber);
			cstmtInv.setString(2, serialNumber);
			cstmtInv.setInt(3, svcinvlinePk); // Added(Set svcinvlinePk value)
			cstmtInv.registerOutParameter(4, OracleTypes.CURSOR); // Modified value 3 to 4
			cstmtInv.execute();
			rsInv = (ResultSet) cstmtInv.getObject(4); // Modified value 3 to 4
			while(rsInv.next()) {
				Canon_E193_OKSBillingDtlsObj objInv = new  Canon_E193_OKSBillingDtlsObj();
				objInv.setCustomerTrxId(rsInv.getLong("customer_trx_id"));
				objInv.setTrxNumber(rsInv.getString("inv_num"));
				objInv.setSerialNumber(rsInv.getString("serial_number"));
				objInv.setBillMeterType(rsInv.getString("meter_type")); //Fixing for the unit testing defect
				objInv.setStartReading(rsInv.getInt("start_total"));
				objInv.setEndReading(rsInv.getInt("end_total"));
				objInv.setTestCopies(rsInv.getInt("test_copies"));
				objInv.setBillingCounterName(rsInv.getString("counter_name")); //Fixing for the unit testing defect
				objInv.setTestCopyInd(rsInv.getString("test_copy"));
				objInv.setPhysMtrLbCd(rsInv.getString("physical_code"));
				objInv.setCounterDesc(rsInv.getString("counter_desc"));
				
				//objInv.setStartMeterReadDate(rsInv.getString("start_meter_read_date"));
				//objInv.setEndMeterReadDate(rsInv.getString("end_meter_read_date"));
				counterRead.add(objInv);
			}
		}catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		}catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctInfo, Method:getCounterReadList()"));
		}catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctInfo, Method:getCounterReadList()"));
		}
		finally {
			try {
				if(cstmtInv != null) cstmtInv.close();
				if(rsInv != null) rsInv.close();
				if(connInvConnection != null) connInvConnection.close();
				if(connInv != null) connInv.releaseConnection();
			}catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			}catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctInfo, Method:getCounterReadList()"));
			}catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctInfo, Method:getCounterReadList()"));
			}
		}
		return counterRead;
	}
	
	public ArrayList getContractBasePrices(String contractId, String serialNumber, String contractLineId, String strInstanceId) throws CanonCustAppExceptionUtil {
		CallableStatement cstmtInv = null;
		ResultSet rsInv = null;
		ArrayList basePrices = new ArrayList();
		//Get Connection
		CanonCustAppDBUtil connInv = null;
		Connection connInvConnection = null;
		try {
			//connInv = new CanonCustAppDBUtil();
			try 
			{
				connInvConnection = (Connection)TransactionScope.getConnection();
			}
			catch (Exception eExp) 
			{
				System.err.println(" Exception "+eExp);
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonCustAppDBUtil, Method:CanonCustAppDBUtil()"));
			}
			System.out.println("in getContractBasePrices " + contractId +" " +serialNumber +" "+ strInstanceId+" ,contractLineId:"+contractLineId);
			//connInvConnection = (Connection)(connInv.getConnection());
			cstmtInv = connInvConnection.prepareCall("{call canon_e193_cs_evolution_pkg.get_contract_base_rate(?,?,?,?,?,?,?,?)}");
			cstmtInv.setBigDecimal(1, new BigDecimal(contractId));
			cstmtInv.setString(2, serialNumber);
			cstmtInv.setString(3, strInstanceId);
			cstmtInv.setBigDecimal(4, new BigDecimal(contractLineId));
			cstmtInv.registerOutParameter(5, 2003, "JTF_VARCHAR2_TABLE_100");
			cstmtInv.registerOutParameter(6, 2003, "JTF_VARCHAR2_TABLE_100");
			cstmtInv.registerOutParameter(7, 2003, "JTF_VARCHAR2_TABLE_100");
			cstmtInv.registerOutParameter(8,  OracleTypes.NUMBER);
			cstmtInv.execute();
			//System.out.println("query executed in getContractBasePrices");
			
			Object[] readingArray = (Object[]) cstmtInv.getArray(5).getArray();
			Object[] uomPeriodsArray = (Object[]) cstmtInv.getArray(6).getArray();
			Object[] baseAmountArray = (Object[]) cstmtInv.getArray(7).getArray();
			
			basePrices.add(readingArray);
			basePrices.add(uomPeriodsArray);
			basePrices.add(baseAmountArray);
			
		/*}catch (CanonCustAppExceptionUtil csExp) {
			System.out.println( " Exception ex = "+csExp );
			throw (csExp);
		}catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctInfo, Method:getContractBasePrices()"));*/
		}catch (Exception eExp) {
			//System.err.println( "in getContractBasePrices Exception ex = "+eExp );
			System.err.println( "in getContractBasePrices Exception ex = " + eExp.toString()  + eExp.getMessage() );
			eExp.printStackTrace();
			
			//throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctInfo, Method:getContractBasePrices()"));
		}
		finally {
			try {
				if(cstmtInv != null) cstmtInv.close();
				if(rsInv != null) rsInv.close();
				if(connInvConnection != null) connInvConnection.close();
				if(connInv != null) connInv.releaseConnection();
				TransactionScope.releaseConnection(connInvConnection);
			}catch (CanonCustAppExceptionUtil csExp) {
				System.err.println( "in getContractBasePrices Exception while releasing the connection");
				throw (csExp);
			}catch (SQLException eSQLExp) {
				System.err.println( "in getContractBasePrices Exception while releasing the connection");
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_AcctInfo, Method:getContractBasePrices()"));
			}catch (Exception eExp) {
				System.err.println( "in getContractBasePrices Exception while releasing the connection");
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_AcctInfo, Method:getContractBasePrices()"));
			}
		}
		return basePrices;
	}
	
	public int getContractTier1CopyVolume( String contractLineId)
  {
	 CallableStatement tier1CopyVolume = null;
	 CanonCustAppDBUtil connInv = null;
	 Connection connInvConnection = null;
     
     int resValue = 0;
     try
       {
		connInv = new CanonCustAppDBUtil();
		connInvConnection = (Connection)(connInv.getConnection());
		
		if (connInvConnection != null) {
		  tier1CopyVolume = (CallableStatement) connInvConnection.prepareCall("BEGIN ? := canon_e193_cs_sqls_pkg.get_tier1_copy_volume_contract(?); END;");
		  if (tier1CopyVolume != null) {
			  // Set the parameters on the statement
			  tier1CopyVolume.registerOutParameter(1, OracleTypes.NUMBER);
			  tier1CopyVolume.setObject(2, contractLineId, OracleTypes.VARCHAR);
			  
			  // Execute the statement
			  tier1CopyVolume.execute();
			  resValue = (tier1CopyVolume.getBigDecimal(1)).intValue();
			  
			  return resValue;
		  }
		  else {
			  System.err.println("DBStatus.getContractTier1CopyVolume Error ");
          	}
		} else {
			System.err.println("DBStatus.getContractTier1CopyVolume - Unable to get data" );
        }
     } catch (Exception ex) {
    	 ex.printStackTrace();
     } finally {
    	 if (tier1CopyVolume != null) {
    		 try {
					tier1CopyVolume.close();
					tier1CopyVolume = null;
					if(connInvConnection != null) connInvConnection.close();
					if(connInv != null) connInv.releaseConnection();
				} 
				catch (Exception exp) 
				{
    			  exp.printStackTrace();
				}
    	 }
    	 
    	} 
	  return resValue;
  }
  
  public String displayBaseRateColumn( String contractLineId, String invoiceNum, String dateBilledFrom) throws Exception 
  {
	 CallableStatement dispayBaseRateSt = null;
	 CanonCustAppDBUtil connInv = null;
	 Connection connInvConnection = null;
     
     String resValue = "N";
     try
       {
		//connInv = new CanonCustAppDBUtil();
		//connInvConnection = (Connection)(connInv.getConnection());
			try 
			{
				connInvConnection = (Connection)TransactionScope.getConnection();
			}
			catch (Exception eExp) 
			{
				System.err.println("in displayBaseRateColumn Exception = "+eExp);
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: CanonCustAppDBUtil, Method:CanonCustAppDBUtil()"));
			}
		
		if (connInvConnection != null) {
		  dispayBaseRateSt = (CallableStatement) connInvConnection.prepareCall("BEGIN ? := canon_e193_cs_sqls_pkg.display_base_rate_col(?,?,?); END;");
		  if (dispayBaseRateSt != null) {
			  // Set the parameters on the statement
			  dispayBaseRateSt.registerOutParameter(1, OracleTypes.VARCHAR);
			  dispayBaseRateSt.setObject(2,  invoiceNum, OracleTypes.VARCHAR);
			  dispayBaseRateSt.setObject(3, dateBilledFrom, OracleTypes.VARCHAR);
			  dispayBaseRateSt.setObject(4, new BigDecimal(contractLineId), OracleTypes.NUMBER);
			  
			  // Execute the statement
			  dispayBaseRateSt.execute();
			  resValue = dispayBaseRateSt.getString(1);
			  
			  return resValue;
		  }
		  else {
			  System.err.println("DBStatus.displayBaseRateColumn Error ");
          	}
		} else {
			System.err.println("DBStatus.displayBaseRateColumn - Unable to get data" );
        }
     } catch (Exception ex) {
		System.err.println("in displayBaseRateColumn Exception = "+ex);
    	 ex.printStackTrace();
		 throw (ex);
     } finally {
    	 if (dispayBaseRateSt != null) {
    		 try {
					dispayBaseRateSt.close();
					dispayBaseRateSt = null;
					if(connInvConnection != null) connInvConnection.close();
					if(connInv != null) connInv.releaseConnection();
					TransactionScope.releaseConnection(connInvConnection);
				} 
				catch (Exception exp) 
				{
    			  System.err.println( "in displayBaseRateColumn Exception while releasing the connection");
				  exp.printStackTrace();
				}
    	 }
    	 
    	} 
	  return resValue;
  }
  
	public String getInvoiceStatus(String invoiceNum)
			throws Exception
	{
		CallableStatement cstmtInv = null;
		
		String tkt_number = "";
		//Get Connection
		CanonCustAppDBUtil connInv = null;
		Connection connInvConnection = null;
		try {
			System.out.println("in getInvoiceStatus invoiceNum" + tkt_number);
			connInv = new CanonCustAppDBUtil();
			connInvConnection = (Connection)(connInv.getConnection());
			cstmtInv = connInvConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.GET_INVOICE_STATUS(?,?)}");			
			cstmtInv.setString(1, invoiceNum);
			cstmtInv.registerOutParameter(2, OracleTypes.VARCHAR);
			cstmtInv.execute();
			tkt_number = cstmtInv.getString(2);
			System.out.println("in getInvoiceStatus tkt_number " + tkt_number);
			
		}catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		}catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoiceStatus()"));
		}catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoiceStatus()"));
		}
		finally {
			try {
				if(cstmtInv != null) cstmtInv.close();
				if(connInvConnection != null) connInvConnection.close();
				if(connInv != null) connInv.releaseConnection();
			}catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			}catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoiceStatus()"));
			}catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_Invoice, Method:getInvoiceStatus()"));
			}
		}
		return tkt_number;
	}
  
	
}
