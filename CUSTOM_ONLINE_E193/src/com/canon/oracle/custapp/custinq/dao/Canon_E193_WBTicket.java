package com.canon.oracle.custapp.custinq.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

import com.canon.oracle.custapp.custinq.beans.Canon_E193_WBTicketObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_WorkbenchObj;
import com.canon.oracle.custapp.util.CanonCustAppDBUtil;
import com.canon.oracle.custapp.util.CanonCustAppExceptionUtil;
import com.canon.oracle.custapp.util.CanonCustAppUtil;

/**
 * Canon_E193_WBTicket Retrieves owned tickets from database
 * Creation date:
 * @author: Dipti Shedji
 * <pre>
 * Flag  Date       By         Description
 * ----  ---------- ---------- ---------------------------------------------
 * 18-Dec-2006  Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 * 16-Sep-2013  Hema Doniparthi    ITG# 475337 
 * 29-Jan-2016  Mangala Shenoy     Modified for S21 changes
 * </pre>
 */
public class Canon_E193_WBTicket {

  /**
   * Canon_E193_WBTicket constructor.
   */
   public Canon_E193_WBTicket() {}

  /**
   * Get owned tickets from database.
   * Creation date:
   * @param iOrgId int
   * @param iResId int
   * @param isOwned java.lang.String
   * @param strOrderBy java.lang.String
   * @param strAscDescOrder java.lang.String
   * @param iPageNum int
   * @param iTotPageCount int
   * @return java.util.ArrayList
   */
   //Start changes for S21 by Mangala
   public ArrayList getOwnedTickets(String strDeptCode, String iResId, int iOrgId, String isOwned, String strOrderBy,
				String strAscDescOrder, int iPageNum, int iTotPageCount, String loggedInUser) throws CanonCustAppExceptionUtil {
	   CallableStatement cstmOwnedTkt = null;
	    ResultSet rsOwnedTkt = null;
	    ArrayList alOwnedTkt = new ArrayList();
	    CanonCustAppDBUtil connOwnedTkt = null;
	    Connection connOwnedTktConnection = null;

	    try {
	    	connOwnedTkt = new CanonCustAppDBUtil();
	    	connOwnedTktConnection = (Connection)(connOwnedTkt.getConnection());

			cstmOwnedTkt = connOwnedTktConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_wb_ticket_summary(?,?,?,?,?,?,?,?,?,?,?)}");
			//Start changes for S21 by Mangala
			cstmOwnedTkt.setString(1,iResId);
			//End changes for S21 by Mangala
			cstmOwnedTkt.setInt(2,iOrgId);
			cstmOwnedTkt.setString(3,isOwned);
			cstmOwnedTkt.setString(4,strOrderBy);
			cstmOwnedTkt.setString(5,strAscDescOrder);
			cstmOwnedTkt.setInt(6,iPageNum);
			cstmOwnedTkt.registerOutParameter(6,java.sql.Types.INTEGER);
			cstmOwnedTkt.setInt(7,iTotPageCount);
			cstmOwnedTkt.registerOutParameter(7,java.sql.Types.INTEGER);
			cstmOwnedTkt.setString(8, strDeptCode);
			cstmOwnedTkt.registerOutParameter(9,OracleTypes.CURSOR);
			cstmOwnedTkt.setString(10, loggedInUser);
			cstmOwnedTkt.setString(11, null);

			cstmOwnedTkt.execute();
			alOwnedTkt.add(new Integer(cstmOwnedTkt.getInt(6)));
			alOwnedTkt.add(new Integer(cstmOwnedTkt.getInt(7)));
			rsOwnedTkt = (ResultSet) cstmOwnedTkt.getObject(9);

	      while(rsOwnedTkt.next()) {
	        Canon_E193_WBTicketObj objOwnedTkt = new Canon_E193_WBTicketObj();
	        objOwnedTkt.setITicketId(rsOwnedTkt.getInt(1));
	        objOwnedTkt.setITicketNumber(rsOwnedTkt.getInt(2));
	        objOwnedTkt.setStrCatDesc(rsOwnedTkt.getString(3));
	        objOwnedTkt.setStrCrDate(formatDateString( rsOwnedTkt.getDate(4)));
	        objOwnedTkt.setIDaysOpen(rsOwnedTkt.getInt(5));
	        objOwnedTkt.setStrTicketStatus(rsOwnedTkt.getString(6));
	        objOwnedTkt.setStrTicketType(rsOwnedTkt.getString(7));
	        objOwnedTkt.setStrTicketAssignee(rsOwnedTkt.getString(8));
	        objOwnedTkt.setStrTicketAssigneeDept(rsOwnedTkt.getString(9));
	        objOwnedTkt.setStrUnassignedLinesFlag(rsOwnedTkt.getString(10));
	        objOwnedTkt.setStrAccountName(rsOwnedTkt.getString(11)); 
	        objOwnedTkt.setStrLastUpdateDate(formatDateString (rsOwnedTkt.getDate(12)));//Newly Added.
	        alOwnedTkt.add(objOwnedTkt);
	      }
	    }
	    catch (CanonCustAppExceptionUtil csExp) {
	    	System.err.println("Canon_E193_WBTicket getOwnedTickets() Cust App Exception is:" + csExp.toString());
	      throw (csExp);
	    }
	    catch (SQLException eSQLExp) {
	    	System.err.println("Canon_E193_WBTicket getOwnedTickets() SQL Exception is:" + eSQLExp.toString());
	      throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_WBTicket, Method:getOwnedTickets()"));
	    }
	    catch (Exception eExp) {
	    	System.err.println("Canon_E193_WBTicket getOwnedTickets() Exception is:" + eExp.toString());
	      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_WBTicket, Method:getOwnedTickets()"));
	    }
	    finally {
	      try
	      {
	        if(cstmOwnedTkt != null)
	          cstmOwnedTkt.close();
	        if(rsOwnedTkt != null)
	          rsOwnedTkt.close();
	        if(connOwnedTktConnection != null)
	          connOwnedTktConnection.close();
	        if(connOwnedTkt != null)
	          connOwnedTkt.releaseConnection();
	      }
	      catch (CanonCustAppExceptionUtil csExp) {
	        throw (csExp);
	      }
	      catch (SQLException eSQLExp) {
	        throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_WBTicket, Method:getOwnedTickets()"));
	      }
	      catch (Exception eExp) {
	        throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_WBTicket, Method:getOwnedTickets()"));
	      }
	    }
	    return alOwnedTkt;
  }

	/**
	 * Get owned assigned from database.
	 * Creation date:
	 * @param iOrgId int
	 * @param iResId int
	 * @param isOwned java.lang.String
	 * @param strOrderBy java.lang.String
	 * @param strAscDescOrder java.lang.String
	 * @param iPageNum int
	 * @param iTotPageCount int
	 * @return java.util.ArrayList
	 */
	public ArrayList getAssignedTickets(String strDeptCode, String iResId, int iOrgId, String isOwned, String strOrderBy,
				String strAscDescOrder, int iPageNum, int iTotPageCount, String loggedInUser) throws CanonCustAppExceptionUtil {	  
	
		return getTickets(strDeptCode, iResId, iOrgId, isOwned, strOrderBy,
				strAscDescOrder, iPageNum, iTotPageCount, loggedInUser, null,
				"getAssignedTickets()");
	}
	  
	public ArrayList getCreatedTickets(String strDeptCode, String iResId, int iOrgId, String isOwned, String strOrderBy,
				String strAscDescOrder, int iPageNum, int iTotPageCount, String loggedInUser, String numOfDays) throws CanonCustAppExceptionUtil {
		if(numOfDays != null && numOfDays.trim().isEmpty())
		{
			numOfDays= null;
		}		
		
		return getTickets(strDeptCode, iResId, iOrgId, isOwned, strOrderBy,
				strAscDescOrder, iPageNum, iTotPageCount, loggedInUser, numOfDays,
				"getCreatedTickets()");
	}
	  
	public ArrayList getTickets(String strDeptCode, String iResId, int iOrgId,
			String isOwned, String strOrderBy, String strAscDescOrder,
			int iPageNum, int iTotPageCount, String loggedInUser,
			String numOfDays, String fromMethod) throws CanonCustAppExceptionUtil 
	{	  
		CallableStatement cstmTkt = null;
		ResultSet rsTkt = null;
		ArrayList alTkt = new ArrayList();
		CanonCustAppDBUtil connTkt = null;
		Connection connTktConnection = null;
		try 
		{
			System.out.println("strDeptCode " + strDeptCode + " iResId " + iResId + " iOrgId " + iOrgId 
					+ " isOwned " + isOwned + " strOrderBy " + strOrderBy + " strAscDescOrder " + strAscDescOrder 
					+ " iPageNum " + iPageNum + " iTotPageCount " + iTotPageCount + " loggedInUser " + loggedInUser 
					+ " numOfDays " + numOfDays + " fromMethod " + fromMethod );
			connTkt = new CanonCustAppDBUtil();
			connTktConnection = (Connection)(connTkt.getConnection());
			cstmTkt = connTktConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_wb_ticket_summary(?,?,?,?,?,?,?,?,?,?,?)}"); /**/
			cstmTkt.setString(1,iResId);
			cstmTkt.setInt(2,iOrgId);
			cstmTkt.setString(3,isOwned);
			cstmTkt.setString(4,strOrderBy);
			cstmTkt.setString(5,strAscDescOrder);
			cstmTkt.setInt(6,iPageNum);
			cstmTkt.registerOutParameter(6,java.sql.Types.INTEGER);
			cstmTkt.setInt(7,iTotPageCount);
			cstmTkt.registerOutParameter(7,java.sql.Types.INTEGER);
			cstmTkt.setString(8, strDeptCode);
			cstmTkt.registerOutParameter(9,OracleTypes.CURSOR);
			cstmTkt.setString(10, loggedInUser);
			cstmTkt.setString(11, numOfDays);			
			
			cstmTkt.execute();
			
			alTkt.add(new Integer(cstmTkt.getInt(6)));
			alTkt.add(new Integer(cstmTkt.getInt(7)));
			rsTkt = (ResultSet) cstmTkt.getObject(9);

		    while(rsTkt.next()) 
		    {
		    	Canon_E193_WBTicketObj objAssignedTkt = new Canon_E193_WBTicketObj();
		    	objAssignedTkt.setStrAssignDate(formatDateString ( rsTkt.getDate(1)));
		    	objAssignedTkt.setIDaysOpen(rsTkt.getInt(2));
			    objAssignedTkt.setITicketId(rsTkt.getInt(3));
			    objAssignedTkt.setITicketNumber(rsTkt.getInt(4));
			    objAssignedTkt.setStrTicketType(rsTkt.getString(5));
			    objAssignedTkt.setStrCatDesc(rsTkt.getString(6));
			    objAssignedTkt.setILineId(rsTkt.getInt(7));
			    objAssignedTkt.setILineNumber(rsTkt.getInt(8));
			    objAssignedTkt.setStrLineCatDesc(rsTkt.getString(9));
			    objAssignedTkt.setStrLineStatus(rsTkt.getString(10));
			    objAssignedTkt.setILineSevLvl(rsTkt.getInt(11));
			    objAssignedTkt.setStrLineSeverity(rsTkt.getString(12));
			    objAssignedTkt.setStrTicketAssignee(rsTkt.getString(13));
			    objAssignedTkt.setStrTicketAssigneeDept(rsTkt.getString(14));
			    objAssignedTkt.setStrUnassignedLinesFlag(rsTkt.getString(15));
			    objAssignedTkt.setStrAccountName(rsTkt.getString(16));	
				objAssignedTkt.setStrProfileName(rsTkt.getString(17));	
				objAssignedTkt.setStrLastUpdateDate(formatDateString (rsTkt.getDate(18)));
		        alTkt.add(objAssignedTkt);
		    }
		}
		catch (CanonCustAppExceptionUtil csExp) {
			System.err.println("Canon_E193_WBTicket getTickets() Cust App Exception is:" + csExp.toString());
			throw (csExp);
		}
		catch (SQLException eSQLExp) {
			eSQLExp.printStackTrace();
			System.err.println("Canon_E193_WBTicket getTickets() SQL Exception is:" + eSQLExp.toString());
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_WBTicket, Method:getTickets() Calling Method " + fromMethod));
		}
		catch (Exception eExp) {
			eExp.printStackTrace();
			System.err.println("Canon_E193_WBTicket getTickets() Exception is:" + eExp.toString());
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_WBTicket, Method:getTickets() Calling Method " + fromMethod));
		}
		finally {
			try
			{
				if(cstmTkt != null)
					cstmTkt.close();
				if(rsTkt != null)
					rsTkt.close();
				if(connTktConnection != null)
					connTktConnection.close();
				if(connTkt != null)
					connTkt.releaseConnection();
			}
			catch (CanonCustAppExceptionUtil csExp) {
				throw (csExp);
			}
			catch (SQLException eSQLExp) {
				throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_WBTicket, Method:getTickets() Calling Method " + fromMethod));
			}
			catch (Exception eExp) {
				throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_WBTicket, Method:getTickets() Calling Method " + fromMethod));
			}
		}
		return alTkt;
	}
	
	public String getNoOfDaysDropDowns() {
		String alNumDays = "30-30 Days" + "|" + "90-90 Days" + "|" + "120-120 Days" + "|" + "365-1 Year" + "|" + "547-18 Months";
	    return alNumDays;
	}


  /**
   * Get ticket owners from database.
   * Creation date:
   * @param iOrgId int
   * @param iResId int
   * @return java.util.ArrayList
   * @Modified: ITG# 73987 : CBS Consolidation Changes -- strRegionCode added.
   */
  //Start changes for S21 by Mangala
 // public ArrayList getWBTktOwnerAssignee(String strDeptCode, int iResId, int iOrgId, String strRegionCode) throws CanonCustAppExceptionUtil {
  public ArrayList getWBTktOwnerAssignee(String strDeptCode, String iResId, int iOrgId, String strRegionCode) throws CanonCustAppExceptionUtil {
	//End changes for S21 by Mangala
    CallableStatement cstmWBTktOwner = null;
    ResultSet rsWBTktOwner = null;
    ArrayList alWBTktOwner = new ArrayList();

    //Get Connection
    CanonCustAppDBUtil connWBTktOwner = null;
    Connection connWBTktOwnerConnection = null;

    try {
    	connWBTktOwner = new CanonCustAppDBUtil();
    	connWBTktOwnerConnection = (Connection)(connWBTktOwner.getConnection());

		cstmWBTktOwner = connWBTktOwnerConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_wb_ticket_owner_asignee(?,?,?,?,?)}");
		//Start changes for S21 by Mangala
		//cstmWBTktOwner.setInt(1, iResId);
		cstmWBTktOwner.setString(1, iResId);
		//End changes for S21 by Mangala
		cstmWBTktOwner.setInt(2, iOrgId);
		cstmWBTktOwner.setString(3, strRegionCode);
		cstmWBTktOwner.setString(4, strDeptCode);
		cstmWBTktOwner.registerOutParameter(5, OracleTypes.CURSOR);

		cstmWBTktOwner.execute();

		rsWBTktOwner = (ResultSet) cstmWBTktOwner.getObject(5);

      	while(rsWBTktOwner.next()) {
			Canon_E193_WorkbenchObj objWBTktOwner = new Canon_E193_WorkbenchObj();
			//Start changes for S21 by Mangala
			//objWBTktOwner.setIAttribute1(rsWBTktOwner.getInt(1));
			objWBTktOwner.setIAttribute1(rsWBTktOwner.getString(1));
			//End changes for S21 by Mangala
			objWBTktOwner.setStrAttribute2(rsWBTktOwner.getString(2));
			alWBTktOwner.add(objWBTktOwner);
      }
    }
    catch (CanonCustAppExceptionUtil csExp) {
    	System.err.println("Canon_E193_WBTicket getWBTktOwnerAssignee() Cust App Exception is:" + csExp.toString());
      throw (csExp);
    }
    catch (SQLException eSQLExp) {
    	System.err.println("Canon_E193_WBTicket getWBTktOwnerAssignee() SQL Exception is:" + eSQLExp.toString());
      throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_WBTicket, Method:getWBTktOwner()"));
    }
    catch (Exception eExp) {
    	System.err.println("Canon_E193_WBTicket getWBTktOwnerAssignee() Exception is:" + eExp.toString());
      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_WBTicket, Method:getWBTktOwner()"));
    }
    finally {
      try
      {
        if(cstmWBTktOwner != null)
          cstmWBTktOwner.close();
        if(rsWBTktOwner != null)
          rsWBTktOwner.close();
        if(connWBTktOwnerConnection != null)
          connWBTktOwnerConnection.close();
        if(connWBTktOwner != null)
          connWBTktOwner.releaseConnection();
      }
      catch (CanonCustAppExceptionUtil csExp) {
        throw (csExp);
      }
      catch (SQLException eSQLExp) {
        throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_WBTicket, Method:getWBTktOwner()"));
      }
      catch (Exception eExp) {
        throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_WBTicket, Method:getWBTktOwner()"));
      }
    }
    return alWBTktOwner;
  }
  
  public ArrayList getWBTktCreatedBy(String strDeptCode, String iResId, int iOrgId, String strRegionCode) throws CanonCustAppExceptionUtil {
		//End changes for S21 by Mangala
	    CallableStatement cstmWBTktcreator = null;
	    ResultSet rsWBTktCreator = null;
	    ArrayList alWBTktCreator = new ArrayList();

	    //Get Connection
	    CanonCustAppDBUtil connWBTktCreator = null;
	    Connection connWBTktOwnerConnection = null;

	    try {
	    	System.out.println("Canon_E193_WBTicket getWBTktCreatedBy() strDeptCode : " + strDeptCode 
					+ " iResId : " + iResId  + " iOrgId : " + iOrgId
					+ " strRegionCode : " + strRegionCode ); 
	    	connWBTktCreator = new CanonCustAppDBUtil();
	    	connWBTktOwnerConnection = (Connection)(connWBTktCreator.getConnection());

			cstmWBTktcreator = connWBTktOwnerConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.SELECT_WB_TICKET_CREATED_BY(?,?,?,?,?)}");
			cstmWBTktcreator.setString(1, iResId);
			cstmWBTktcreator.setInt(2, iOrgId);
			cstmWBTktcreator.setString(3, strRegionCode);
			cstmWBTktcreator.setString(4, strDeptCode);
			cstmWBTktcreator.registerOutParameter(5, OracleTypes.CURSOR);

			cstmWBTktcreator.execute();

			rsWBTktCreator = (ResultSet) cstmWBTktcreator.getObject(5);

	      	while(rsWBTktCreator.next()) {
				Canon_E193_WorkbenchObj objWBTktOwner = new Canon_E193_WorkbenchObj();
				objWBTktOwner.setIAttribute1(rsWBTktCreator.getString(1));
				objWBTktOwner.setStrAttribute2(rsWBTktCreator.getString(2));
				alWBTktCreator.add(objWBTktOwner);
	      }
	    }
	    catch (CanonCustAppExceptionUtil csExp) {
	    	System.err.println("Canon_E193_WBTicket getWBTktCreatedBy() Cust App Exception is:" + csExp.toString());
	      throw (csExp);
	    }
	    catch (SQLException eSQLExp) {
	    	System.err.println("Canon_E193_WBTicket getWBTktCreatedBy() SQL Exception is:" + eSQLExp.toString());
	      throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_WBTicket, Method:getWBTktCreatedBy()"));
	    }
	    catch (Exception eExp) {
	    	System.err.println("Canon_E193_WBTicket getWBTktCreatedBy() Exception is:" + eExp.toString());
	      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_WBTicket, Method:getWBTktCreatedBy()"));
	    }
	    finally {
	      try
	      {
	        if(cstmWBTktcreator != null)
	          cstmWBTktcreator.close();
	        if(rsWBTktCreator != null)
	          rsWBTktCreator.close();
	        if(connWBTktOwnerConnection != null)
	          connWBTktOwnerConnection.close();
	        if(connWBTktCreator != null)
	          connWBTktCreator.releaseConnection();
	      }
	      catch (CanonCustAppExceptionUtil csExp) {
	        throw (csExp);
	      }
	      catch (SQLException eSQLExp) {
	        throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_WBTicket, Method:getWBTktCreatedBy()"));
	      }
	      catch (Exception eExp) {
	        throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_WBTicket, Method:getWBTktCreatedBy()"));
	      }
	    }
	    return alWBTktCreator;
	  }

  /**
   * Get departments from database.
   * Creation date:
   * @param iOrgId int
   * @param iResId int
   * @return java.util.ArrayList
   */
 //Start changes for S21 by Mangala
  //public ArrayList getWBTktDept(int iResId, int iOrgId, String strRegionCode) throws CanonCustAppExceptionUtil {
  public ArrayList getWBTktDept(String iResId, int iOrgId, String strRegionCode) throws CanonCustAppExceptionUtil {
	//End changes for S21 by Mangala
    CallableStatement cstmWBTktDept = null;
    ResultSet rsWBTktDept = null;
    ArrayList alWBTktDept = new ArrayList();

    //Get Connection
    CanonCustAppDBUtil connWBTktDept = null;
    Connection connWBTktDeptConnection = null;

    try {
    	connWBTktDept = new CanonCustAppDBUtil();
    	connWBTktDeptConnection = (Connection)(connWBTktDept.getConnection());

		cstmWBTktDept = connWBTktDeptConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_wb_ticket_assignee_dept(?,?,?,?)}");
		//Start changes for S21 by Mangala
		//cstmWBTktDept.setInt(1,iResId);
		cstmWBTktDept.setString(1,iResId);
		//End changes for S21 by Mangala
		cstmWBTktDept.setInt(2,iOrgId);

		/*ITG# 73987 - Begin */
		cstmWBTktDept.setString(3,strRegionCode);
		/*ITG# 73987 - End */

		cstmWBTktDept.registerOutParameter(4,OracleTypes.CURSOR);

		cstmWBTktDept.execute();

		rsWBTktDept = (ResultSet) cstmWBTktDept.getObject(4);

      	while(rsWBTktDept.next()) {
			Canon_E193_WorkbenchObj objWBTktDept = new Canon_E193_WorkbenchObj();
			objWBTktDept.setStrAttribute2(rsWBTktDept.getString(1));
			objWBTktDept.setStrAttribute3(rsWBTktDept.getString(2));
			alWBTktDept.add(objWBTktDept);
      }
    }
    catch (CanonCustAppExceptionUtil csExp) {
    	System.err.println("Canon_E193_WBTicket getWBTktDept() Cust App Exception is:" + csExp.toString());
      throw (csExp);
    }
    catch (SQLException eSQLExp) {
    	System.err.println("Canon_E193_WBTicket getWBTktDept() SQL Exception is:" + eSQLExp.toString());
      throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_WBTicket, Method:getWBTktDept()"));
    }
    catch (Exception eExp) {
    	System.err.println("Canon_E193_WBTicket getWBTktDept() Exception is:" + eExp.toString());
      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_WBTicket, Method:getWBTktDept()"));
    }
    finally {
      try
      {
        if(cstmWBTktDept != null) cstmWBTktDept.close();
        if(rsWBTktDept != null) rsWBTktDept.close();
        if(connWBTktDeptConnection != null) connWBTktDeptConnection.close();
        if(connWBTktDept != null) connWBTktDept.releaseConnection();
      }
      catch (CanonCustAppExceptionUtil csExp) {
        throw (csExp);
      }
      catch (SQLException eSQLExp) {
        throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_WBTicket, Method:getWBTktDept()"));
      }
      catch (Exception eExp) {
        throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_WBTicket, Method:getWBTktDept()"));
      }
    }
    return alWBTktDept;
  }

  /**
   * Get csr resources from database.
   * Creation date:
   * @param iOrgId int
   * @param iResId int
   * @return java.util.ArrayList
   */
  public ArrayList getWBTktRes(int iOrgId) throws CanonCustAppExceptionUtil {

    CallableStatement cstmtWBTktRes = null;
    ResultSet rsWBTktRes = null;
    ArrayList alWBTktRes = new ArrayList();

    //Get Connection
    CanonCustAppDBUtil connWBTktRes = new CanonCustAppDBUtil();
    Connection connWBTktResConnection = (Connection)(connWBTktRes.getConnection());

    try
    {
		cstmtWBTktRes = connWBTktResConnection.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_wb_ticket_all_res(?,?)}");

		cstmtWBTktRes.setInt(1,iOrgId);
		cstmtWBTktRes.registerOutParameter(2,OracleTypes.CURSOR);

		cstmtWBTktRes.execute();

		rsWBTktRes = (ResultSet) cstmtWBTktRes.getObject(2);

      while(rsWBTktRes.next()) {
        Canon_E193_WorkbenchObj objWBTktRes = new Canon_E193_WorkbenchObj();
        //Start changes for S21 by Mangala
        //objWBTktRes.setIAttribute1(rsWBTktRes.getInt(1));
        objWBTktRes.setIAttribute1(rsWBTktRes.getString(1));
      //End changes for S21 by Mangala
        objWBTktRes.setStrAttribute2(rsWBTktRes.getString(2));
        alWBTktRes.add(objWBTktRes);
      }
      cstmtWBTktRes.close();
      rsWBTktRes.close();
      connWBTktResConnection.close();
      connWBTktRes.releaseConnection();
    }
    catch (CanonCustAppExceptionUtil csExp) {
    	System.err.println("Canon_E193_WBTicket getWBTktRes() Cust App Exception is:" + csExp.toString());
      throw (csExp);
    }
    catch (SQLException eSQLExp) {
    	System.err.println("Canon_E193_WBTicket getWBTktRes() SQL Exception is:" + eSQLExp.toString());
      throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_WBTicket, Method:getWBTktRes()"));
    }
    catch (Exception eExp) {
    	System.err.println("Canon_E193_WBTicket getWBTktRes() Exception is:" + eExp.toString());
      throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_WBTicket, Method:getWBTktRes()"));
    }
    finally {
      try
      {
        if(cstmtWBTktRes != null)
          cstmtWBTktRes.close();
        if(rsWBTktRes != null)
          rsWBTktRes.close();
        if(connWBTktResConnection != null)
          connWBTktResConnection.close();
        if(connWBTktRes != null)
          connWBTktRes.releaseConnection();
      }
      catch (CanonCustAppExceptionUtil csExp) {
        throw (csExp);
      }
      catch (SQLException eSQLExp) {
        throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_WBTicket, Method:getWBTktRes()"));
      }
      catch (Exception eExp) {
        throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_WBTicket, Method:getWBTktRes()"));
      }
    }
    return alWBTktRes;
  }


/**
* Get owned tickets from database.
* Creation date:
* @param alTktId java.util.ArrayList
* @param strNewOwner String
* @param strPrevOwner String
* @return int
*/

public int changeOwner(int[] alTktId, String iNewOwner, String iPrevOwner, int iOrgId, String iUserId)
						 throws CanonCustAppExceptionUtil {

	CallableStatement cstmtWBChangeOwner = null;
	int iResult = 0;
	String strTableType = "CANON_E193_NUMBER_TBL_TYP";


    //Get Connection
    CanonCustAppDBUtil connWBChangeOwner = new CanonCustAppDBUtil();
    Connection connWBChangeOwnerConnection = (Connection)(connWBChangeOwner.getConnection());
    Connection nativeConn=null;
    try
    {
		cstmtWBChangeOwner = connWBChangeOwnerConnection.prepareCall("{call CANON_E193_CS_INSERT_SQLS_PKG.update_ticket_owner(?,?,?,?,?,?)}");
		
		nativeConn = TransactionScope.nativeConnection(connWBChangeOwnerConnection);
		
		System.out.println("alTktId !!!!!! " + Arrays.toString(alTktId) + "iNewOwner !!!!! " + iNewOwner + " iPrevOwner " + iPrevOwner
				+ " iOrgId " + iOrgId + " iUserId " + iUserId);
		

		cstmtWBChangeOwner.setArray(1,CanonCustAppUtil.getArray(alTktId,nativeConn,strTableType));
		cstmtWBChangeOwner.setString(2,iNewOwner);
		cstmtWBChangeOwner.setString(3,iPrevOwner);
		cstmtWBChangeOwner.setInt(4,iOrgId);
		cstmtWBChangeOwner.setString(5,iUserId);
		cstmtWBChangeOwner.registerOutParameter(6,java.sql.Types.INTEGER);

		cstmtWBChangeOwner.execute();
		iResult = cstmtWBChangeOwner.getInt(6);

	    connWBChangeOwnerConnection.close();
        connWBChangeOwner.releaseConnection();
    }
    catch (CanonCustAppExceptionUtil csExp) {
    	System.err.println("Canon_E193_WBTicket changeOwner() Cust App Exception is:" + csExp.toString());
		throw (csExp);
    }
    catch (SQLException eSQLExp) {
    	System.err.println("Canon_E193_WBTicket changeOwner() SQL Exception is:" + eSQLExp.toString());
		throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_WBTicket, Method:changeOwner()"));
    }
    catch (Exception eExp) {
    	System.err.println("Canon_E193_WBTicket changeOwner() Exception is:" + eExp.toString());
		throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_WBTicket, Method:changeOwner()"));
    }
    finally {
		try{
			if(cstmtWBChangeOwner != null)
			  cstmtWBChangeOwner.close();
			if(connWBChangeOwnerConnection != null)
			  connWBChangeOwnerConnection.close();
			if(connWBChangeOwner != null)
			  connWBChangeOwner.releaseConnection();
		}
		catch (CanonCustAppExceptionUtil csExp) {
			throw (csExp);
		}
		catch (SQLException eSQLExp) {
			throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_WBTicket, Method:changeOwner()"));
		}
		catch (Exception eExp) {
			throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_WBTicket, Method:changeOwner()"));
		}
    }
    return iResult;
}

public  String formatDateString(Date date){

	String str="";
      if(date!=null){
        try{
        java.util.Date d = date;
          str = (new SimpleDateFormat("dd-MMM-yyyy")) .format(d);
          if(str!=null)
        	  str= str.toUpperCase();
         }catch(Exception ex){
        	  ex.printStackTrace();
        } 
      }

   return str;
 }
}