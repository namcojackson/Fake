package com.canon.oracle.custapp.custinq.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.mail.Email;

import oracle.jdbc.OracleTypes;
import canon.apps.common.CanonEmailUtil;

import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketHeaderObj;
import com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj;
import com.canon.oracle.custapp.util.CanonCustAppDBUtil;
import com.canon.oracle.custapp.util.CanonCustAppExceptionUtil;

/**
 * Title:      Canon_E193_NonBillingIssue<br>
 * Description:   This is the main class of billing issues Functions<br>
 * <p>
 * CopyRight:  Canon<br>
 * Company:    Canon<br>
 * @author:    Subba<br>
 * @version:   1.0, (15-Sep-2005)<br>
 * @modified by:<br>
 * @version:<br>
 * @modification Detail:<br>
* <pre>
 * Flag  Date       By         Description
 * ----  ---------- ---------- ---------------------------------------------
 * 18-Dec-2006  Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 * 10-Apr-2009  Chandra Sekhar     ITG # 208045 - Canon Ball Changes
 * 29-Jan-2016  Mangala Shenoy	   Modified for S21 Changes
 * </pre>
 */

public class Canon_E193_NonBillingIssue {

   /**
    * Canon_E193_NonBillingIssue constructor.
    */
   public Canon_E193_NonBillingIssue() {
      super();
   }

   /**
    * This method will return the list of headers.
    * <p>
    * @return int.
    * @param headerObj Canon_E193_TicketHeaderObj.
    * @param linesObj Canon_E193_TicketLinesObj.
    * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
    */

   public int addNonBillHeaderLines(Canon_E193_TicketHeaderObj headerObj, Canon_E193_TicketLinesObj linesObj) throws CanonCustAppExceptionUtil {
      int iTicketId = 0;
      //Get Connection
      CanonCustAppDBUtil connNonBillUtil = null;
      Connection connNonBill = null;
       CallableStatement pCall = null;
       try {
         boolean isInseart = true;
         connNonBillUtil = new CanonCustAppDBUtil();
         connNonBill = (Connection)(connNonBillUtil.getConnection());
         System.out.println("Inside addNonBillHeaderLines");
         pCall = connNonBill.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.Insert_NonBill(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

         pCall.setInt(1, headerObj.getCatId());
         pCall.setString(2, headerObj.getStatus());
         pCall.setString(3, headerObj.getRecurring());
         pCall.setString(4, headerObj.getBillingIssue());
         pCall.setInt(5, headerObj.getOrgId());
         pCall.setInt(6, headerObj.getCustAcctId());
         pCall.setString(7, headerObj.getCustomerName());
         pCall.setString(8, headerObj.getCustomerNo());
         pCall.setString(9, headerObj.getInvoiceNo());
         pCall.setString(10, headerObj.getContactNo());
         pCall.setString(11, headerObj.getContractModifier());
         pCall.setInt(12, headerObj.getOrderNo());
         pCall.setString(13, headerObj.getOrderType());
         pCall.setString(14, headerObj.getOrigName());
         pCall.setString(15, headerObj.getOrigPhNo());
         pCall.setString(16, headerObj.getOrigEmail());
         pCall.setString(17, headerObj.getOrigType());
         pCall.setString(18, headerObj.getCustContact());
         pCall.setString(19, headerObj.getCustPhNo());
         pCall.setString(20, headerObj.getCustEmail());
         pCall.setString(21, headerObj.getJtfNotesFlag());
         pCall.setString(22, headerObj.getAttribute1());
         pCall.setString(23, headerObj.getAttribute2());
         pCall.setString(24, headerObj.getAttribute3());
         pCall.setString(25, headerObj.getAttribute4());
         pCall.setString(26, headerObj.getAttribute5());

            /* ITG#: 73987 -- Added: Begin */
            int attrIndex = 27;
            pCall.setString(attrIndex++, headerObj.getAttribute6());
            pCall.setString(attrIndex++, headerObj.getAttribute7());
            pCall.setString(attrIndex++, headerObj.getAttribute8());
            pCall.setString(attrIndex++, headerObj.getAttribute9());
            pCall.setString(attrIndex++, headerObj.getAttribute10());
            pCall.setString(attrIndex++, headerObj.getAttribute11());
            pCall.setString(attrIndex++, headerObj.getAttribute12());
            pCall.setString(attrIndex++, headerObj.getAttribute13());
            pCall.setString(attrIndex++, headerObj.getAttribute14());
            pCall.setString(attrIndex++, headerObj.getAttribute15());


            /* ITG#: 73987 -- End */

            /* ITG#: 73987 -- Modified: Begin */
          //Start changes for S21 by Mangala
         //pCall.setInt(37, headerObj.getOwnerResId());
         //pCall.setInt(38, headerObj.getOwnerResId());
         pCall.setString(37, headerObj.getOwnerResId());
         pCall.setString(38, headerObj.getOwnerResId());
       //End changes for S21 by Mangala
         pCall.setString(39, headerObj.getOwnerDeptCode());
         pCall.setString(40, headerObj.getCreatedBy());
         pCall.setString(41, headerObj.getCreatedByRoleId());
         //Start changes for S21 by Mangala
         //pCall.setInt(42, headerObj.getCreatedByResId());
         pCall.setString(42, headerObj.getCreatedByResId());
       //End changes for S21 by Mangala
         pCall.setString(43, headerObj.getCreatedByDeptCode());
         //pCall.setString(44, headerObj.getsendEmailFlag());

         // setting lines
         pCall.setInt(44, linesObj.getCatId());
         pCall.setString(45, linesObj.getLineStatus());
         pCall.setString(46, linesObj.getSeverity());
         pCall.setString(47, linesObj.getReasonCd());
         pCall.setString(48, linesObj.getReason());
         pCall.setString(49, linesObj.getJtfNotesFlag());
         pCall.setString(50, linesObj.getAttribute1());
         pCall.setString(51, linesObj.getAttribute2());
         pCall.setString(52, linesObj.getAttribute3());
         pCall.setString(53, linesObj.getAttribute4());
         pCall.setString(54, linesObj.getAttribute5());
         pCall.setString(55, linesObj.getCreatedBy());
         pCall.setString(56, linesObj.getNotes());
         pCall.setString(57, headerObj.getsendEmailFlag());

         pCall.registerOutParameter(58, OracleTypes.NUMBER);
         pCall.registerOutParameter(59, OracleTypes.NUMBER);
         pCall.registerOutParameter(60, OracleTypes.NUMBER);
         //System.out.println("!!!!!!! Inside addNonBillHeaderLines Befor Execute");
         try{
         pCall.execute();
         }catch(Exception e) {
        	 System.err.println("in addNonBillHeaderLines " + e.getMessage());
         }
         //System.out.println("!!!!!!! Inside addNonBillHeaderLines After Execute");
         int iErrorHC = pCall.getInt(59);
         int iErrorLC = pCall.getInt(60);
         System.out.println("in addNonBillHeaderLines iErrorHC = "+iErrorHC + " iErrorLC " + iErrorLC);
         //System.out.println("!!!!!!! iErrorLC = "+iErrorLC);
         if(iErrorHC == -1 || iErrorLC == -1) isInseart = false;
         else iTicketId = pCall.getInt(58);        
         if(!isInseart) throw new CanonCustAppExceptionUtil(100001, "insert lines failed", "Class: Canon_E193_NonBillingIssue, Method:addNonBillHeaderLines()");

         /* ITG#: 73987 -- End */

       }catch(CanonCustAppExceptionUtil csExp) {
    	   System.err.println("Exception occurred in addNonBillHeaderLines " + csExp);
         throw (csExp);
       }catch(SQLException eSQLExp) {
         throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:addNonBillHeaderLines()"));
       }catch (Exception eExp) {
         throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:addNonBillHeaderLines()"));
       }
       finally {
         try {
            if(pCall != null) pCall.close();
            if(connNonBillUtil != null) connNonBillUtil.releaseConnection();
         }catch (CanonCustAppExceptionUtil csExp) {
            throw (csExp);
         }catch (SQLException eSQLExp) {
            throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:addNonBillHeaderLines()"));
         }catch (Exception eExp) {
            throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:addNonBillHeaderLines()"));
         }
       }
      return iTicketId;
   }

   /**
    * This method will insert the lines.
    * <p>
    * @return java.util.ArrayList.
    * @param linesObj Canon_E193_TicketLinesObj.
    * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
    */

   public boolean addNonBillLines(Canon_E193_TicketLinesObj linesObj) throws CanonCustAppExceptionUtil {
      boolean isInsert = true;
      //Get Connection
      CanonCustAppDBUtil connNonBillUtil = null;
      Connection connNonBill = null;
       CallableStatement pCall = null;
       try {
    	   System.out.println("Inside addNonBillLines ");
         connNonBillUtil = new CanonCustAppDBUtil();
         connNonBill = (Connection)(connNonBillUtil.getConnection());
         
         pCall = connNonBill.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.INSERT_NONBILL_LINE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

         pCall.setInt(1, linesObj.getTicketId());
         pCall.setString(2, null);
         pCall.setInt(3, linesObj.getOrgId());
         pCall.setInt(4, linesObj.getCatId());
         pCall.setString(5, linesObj.getLineStatus());
         pCall.setString(6, linesObj.getSeverity());
         pCall.setString(7, linesObj.getReasonCd());
         pCall.setString(8, linesObj.getReason());
         pCall.setString(9, linesObj.getJtfNotesFlag());
         pCall.setString(10, linesObj.getAttribute1());
         pCall.setString(11, linesObj.getAttribute2());
         pCall.setString(12, linesObj.getAttribute3());
         pCall.setString(13, linesObj.getAttribute4());
         pCall.setString(14, linesObj.getAttribute5());
         pCall.setString(15, linesObj.getCreatedBy());
         pCall.setString(16, linesObj.getNotes());

         pCall.registerOutParameter(17, OracleTypes.NUMBER);

         pCall.execute();

         int iErrorLC = pCall.getInt(17);
         if(iErrorLC == -1) {
            isInsert = false;
            throw new CanonCustAppExceptionUtil(100001, "insert lines failed", "Class: Canon_E193_NonBillingIssue, Method:addNonBillLines()");
         }

       }catch(CanonCustAppExceptionUtil csExp) {
         throw (csExp);
       }catch(SQLException eSQLExp) {
         throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:addNonBillLines()"));
       }catch (Exception eExp) {
         throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:addNonBillLines()"));
       }
       finally {
         try {
            if(pCall != null) pCall.close();
            if(connNonBillUtil != null) connNonBillUtil.releaseConnection();
         }catch (CanonCustAppExceptionUtil csExp) {
            throw (csExp);
         }catch (SQLException eSQLExp) {
            throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:addNonBillLines()"));
         }catch (Exception eExp) {
            throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:addNonBillLines()"));
         }
       }
      return isInsert;
   }

   /**
    * This method will return the details of the header and lines object.
    * <p>
    * @return java.util.ArrayList.
    * @param iOrgId int.
    * @param iTicketNo int.
    * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
    */
   public ArrayList getNonBillHeaderLines(int iOrgId, int iTicketId) throws CanonCustAppExceptionUtil {
      ArrayList hlList = new ArrayList();
      Canon_E193_TicketHeaderObj objNonBillH = null;
      Canon_E193_TicketLinesObj objNonBillL = null;
      //Get Connection
      CanonCustAppDBUtil connNonBillUtil = null;
      Connection connNonBill = null;
       CallableStatement pCall = null;
       ResultSet rsNonBill = null;
       try {
    	   System.out.println("Inside getNonBillHeaderLines iTicketId " + iTicketId);
         connNonBillUtil = new CanonCustAppDBUtil();
         connNonBill = (Connection)(connNonBillUtil.getConnection());

         pCall = connNonBill.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_ticket_summary(?,?,?,?)}");

         pCall.setInt(1, iOrgId);
         pCall.setInt(2, iTicketId);
         pCall.registerOutParameter(3, OracleTypes.CURSOR);
         pCall.registerOutParameter(4, OracleTypes.CURSOR);

         pCall.execute();

         rsNonBill = (ResultSet) pCall.getObject(3);

         while(rsNonBill.next()) {
            objNonBillH = new Canon_E193_TicketHeaderObj();
            objNonBillH.setTicketId(rsNonBill.getInt(1));
            objNonBillH.setTicketNo(rsNonBill.getString(2));
            objNonBillH.setCatId(rsNonBill.getInt(3));
            objNonBillH.setStatus(rsNonBill.getString(4));
            objNonBillH.setRecurring(rsNonBill.getString(5));
            objNonBillH.setBillingIssue(rsNonBill.getString(6));
            objNonBillH.setOrgId(rsNonBill.getInt(7));
            objNonBillH.setCustAcctId(rsNonBill.getInt(8));
            objNonBillH.setCustomerName(rsNonBill.getString(9));
            objNonBillH.setCustomerNo(rsNonBill.getString(10));
            objNonBillH.setInvoiceNo(rsNonBill.getString(11));
            objNonBillH.setContactNo(rsNonBill.getString(12));
            objNonBillH.setContractModifier(rsNonBill.getString(13));
            objNonBillH.setOrderNo(rsNonBill.getInt(14));
            objNonBillH.setOrderType(rsNonBill.getString(15));
            objNonBillH.setOrigName(rsNonBill.getString(16));
            objNonBillH.setOrigPhNo(rsNonBill.getString(17));
            objNonBillH.setOrigEmail(rsNonBill.getString(18));
            objNonBillH.setOrigType(rsNonBill.getString(19));
            objNonBillH.setCustContact(rsNonBill.getString(20));
            objNonBillH.setCustPhNo(rsNonBill.getString(21));
            objNonBillH.setCustEmail(rsNonBill.getString(22));
            objNonBillH.setJtfNotesFlag(rsNonBill.getString(23));
            objNonBillH.setAttribute1(rsNonBill.getString(24));
            objNonBillH.setAttribute2(rsNonBill.getString(25));
            objNonBillH.setAttribute3(rsNonBill.getString(26));
            objNonBillH.setAttribute4(rsNonBill.getString(27));
            objNonBillH.setAttribute5(rsNonBill.getString(28));


                /* ITG#: 73987 -- Added: Begin */

            int attrIndex = 29;
            objNonBillH.setAttribute6(rsNonBill.getString(attrIndex++));
            objNonBillH.setAttribute7(rsNonBill.getString(attrIndex++));
            objNonBillH.setAttribute8(rsNonBill.getString(attrIndex++));
            objNonBillH.setAttribute9(rsNonBill.getString(attrIndex++));
            objNonBillH.setAttribute10(rsNonBill.getString(attrIndex++));
            objNonBillH.setAttribute11(rsNonBill.getString(attrIndex++));
            objNonBillH.setAttribute12(rsNonBill.getString(attrIndex++));
            objNonBillH.setAttribute13(rsNonBill.getString(attrIndex++));
            objNonBillH.setAttribute14(rsNonBill.getString(attrIndex++));
            objNonBillH.setAttribute15(rsNonBill.getString(attrIndex++));

                /* ITG#: 73987 -- End */


                 /* ITG#: 73987 -- Modified: Begin */

            objNonBillH.setOwnerRoleId(rsNonBill.getString(39));
          //Start changes for S21 by Mangala
           // objNonBillH.setOwnerResId(rsNonBill.getInt(40));
            objNonBillH.setOwnerResId(rsNonBill.getString(40));
            //End changes for S21 by Mangala
            objNonBillH.setOwnerDeptCode(rsNonBill.getString(41));
            objNonBillH.setCreatedBy(rsNonBill.getString(42));
            objNonBillH.setCreatedByRoleId(rsNonBill.getString(43));
            //Start changes for S21 by Mangala
            //objNonBillH.setCreatedByResId(rsNonBill.getInt(44));
            objNonBillH.setCreatedByResId(rsNonBill.getString(44));
          //End changes for S21 by Mangala
            objNonBillH.setCreatedByDeptCode(rsNonBill.getString(45));
            objNonBillH.setCreationDate(rsNonBill.getString(46));
            objNonBillH.setLastUpdateDate(rsNonBill.getString(47));
            objNonBillH.setLastUpdatedBy(rsNonBill.getString(48));
			objNonBillH.setAggregateContractNum(rsNonBill.getString(49));
			objNonBillH.setConsolidateBillNum(rsNonBill.getString(50));
			
			objNonBillH.setsendEmailFlag(rsNonBill.getString(52));
			objNonBillH.setresolutionCode(rsNonBill.getString(53));
            objNonBillH.setCatIdDesc(rsNonBill.getString(54));
            objNonBillH.setCatCode(rsNonBill.getString(55));
            hlList.add(objNonBillH);
            //System.out.println("########## objNonBillH = "+objNonBillH);
            /* ITG#: 73987 -- End */
         }

         rsNonBill = null;
         rsNonBill = (ResultSet) pCall.getObject(4);

         while(rsNonBill.next()) {
            objNonBillL = new Canon_E193_TicketLinesObj();
            objNonBillL.setLineId(rsNonBill.getInt(1));
            objNonBillL.setTicketId(rsNonBill.getInt(2));
            objNonBillL.setCatId(rsNonBill.getInt(3));
            objNonBillL.setLineNo(rsNonBill.getInt(4));
            objNonBillL.setLineStatus(rsNonBill.getString(5));
            objNonBillL.setSeverity(rsNonBill.getString(6));
            objNonBillL.setReasonCd(rsNonBill.getString(7));
            objNonBillL.setReason(rsNonBill.getString(8));
            objNonBillL.setJtfNotesFlag(rsNonBill.getString(9));
            objNonBillL.setAttribute1(rsNonBill.getString(10));
            objNonBillL.setAttribute2(rsNonBill.getString(11));
            objNonBillL.setAttribute3(rsNonBill.getString(12));
            objNonBillL.setAttribute4(rsNonBill.getString(13));
            objNonBillL.setAttribute5(rsNonBill.getString(14));
            objNonBillL.setCreatedBy(rsNonBill.getString(15));
            objNonBillL.setCreationDate(rsNonBill.getString(16));
            objNonBillL.setLastUpdatedDate(rsNonBill.getString(17));
            objNonBillL.setCatIdDesc(rsNonBill.getString(18));
            objNonBillL.setDeptCode(rsNonBill.getString(19));
            objNonBillL.setDeptName(rsNonBill.getString(20));
            objNonBillL.setRoleId(rsNonBill.getString(21));
            objNonBillL.setRoleCd(rsNonBill.getString(22));
            objNonBillL.setRoleName(rsNonBill.getString(23));
            //Start Changes for S21 by Mangala
            //objNonBillL.setResourceId(rsNonBill.getInt(24));
            objNonBillL.setResourceId(rsNonBill.getString(24));
            //End Changes for S21 by Mangala
            objNonBillL.setResourceName(rsNonBill.getString(25));
            objNonBillL.setAttribute5(rsNonBill.getString(26));

            /** ITG # 208045 - Canon Ball Changes **/
            objNonBillL.setCatCode(rsNonBill.getString(27));
            /** ITG # 208045 - Canon Ball Changes **/
            //System.out.println("########## objNonBillL = "+objNonBillL);
            hlList.add(objNonBillL);
         }

       }catch(CanonCustAppExceptionUtil csExp) {
         throw (csExp);
       }catch(SQLException eSQLExp) {
         throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:getNonBillHeaderLines()"));
       }catch (Exception eExp) {
         throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:getNonBillHeaderLines()"));
       }
       finally {
         try {
            if(pCall != null) pCall.close();
            if(rsNonBill != null) rsNonBill.close();
            if(connNonBillUtil != null) connNonBillUtil.releaseConnection();
         }catch (CanonCustAppExceptionUtil csExp) {
            throw (csExp);
         }catch (SQLException eSQLExp) {
            throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:getNonBillHeaderLines()"));
         }catch (Exception eExp) {
            throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:getNonBillHeaderLines()"));
         }
       }
      return hlList;
   }

   /**
    * This method will return the line object.
    * <p>
    * @return canon_E193_TicketLinesObj.
    * @param iOrgId int.
    * @param iTicketId int.
    * @param iLineId int.
    * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
    */
   public Canon_E193_TicketLinesObj getNonBillLine(int iOrgId, int iTicketId, int iLineId) throws CanonCustAppExceptionUtil {
      //Get Connection
      CanonCustAppDBUtil connNonBillUtil = null;
      Connection connNonBill = null;
       CallableStatement pCall = null;
       ResultSet rsNonBill = null;
       Canon_E193_TicketLinesObj objLine = new Canon_E193_TicketLinesObj();
       System.out.println("in getNonBillLine iOrgId = " + iOrgId + "iTicketId = " + iTicketId + "iLineId = " + iLineId);
       try {
         connNonBillUtil = new CanonCustAppDBUtil();
         connNonBill = (Connection)(connNonBillUtil.getConnection());
         pCall = connNonBill.prepareCall("{call CANON_E193_CS_SQLS_PKG.select_ticket_line(?,?,?,?)}");
         //System.out.println("call is prepared");
         pCall.setInt(1, iOrgId);
         pCall.setInt(2, iTicketId);
         pCall.setInt(3, iLineId);
         pCall.registerOutParameter(4, OracleTypes.CURSOR);
         //System.out.println("call is before execute");
         pCall.execute();
         //System.out.println("call is after execute");
         rsNonBill = (ResultSet) pCall.getObject(4);
         //System.out.println("call is after getting resultset" + rsNonBill);
         while(rsNonBill.next()) {
        	// System.out.println("call is after getting resultset" + rsNonBill.getString(6) + rsNonBill.getString(7));
            objLine.setLineId(rsNonBill.getInt(1));
            objLine.setCatId(rsNonBill.getInt(2));
            objLine.setParentCatId(rsNonBill.getInt(3));
            objLine.setCatIdDesc(rsNonBill.getString(4));
            objLine.setReasonCd(rsNonBill.getString(5));
            objLine.setReason(rsNonBill.getString(6));
            objLine.setSeverity(rsNonBill.getString(7));
            objLine.setNoteId(rsNonBill.getInt(8));
            objLine.setNotes(rsNonBill.getString(9));
         }
         
       }catch(CanonCustAppExceptionUtil csExp) {
         throw (csExp);
       }catch(SQLException eSQLExp) {
         throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:getNonBillLine()"));
       }catch (Exception eExp) {
         throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:getNonBillLine()"));
       }
       finally {
         try {
            if(pCall != null) pCall.close();
            if(rsNonBill != null) rsNonBill.close();
            if(connNonBillUtil != null) connNonBillUtil.releaseConnection();
         }catch (CanonCustAppExceptionUtil csExp) {
            throw (csExp);
         }catch (SQLException eSQLExp) {
            throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:getNonBillLine()"));
         }catch (Exception eExp) {
            throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:getNonBillLine()"));
         }
       }
      return objLine;
   }

   /**
    * This method will update the line record.
    * <p>
    * @return boolean.
    * @param iOrgId int.
    * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
    */
   public int updateLines(Canon_E193_TicketLinesObj lineObj) throws CanonCustAppExceptionUtil {
      //Get Connection
      int isUpdated = 0;
      CanonCustAppDBUtil connNonBillUtil = null;
      Connection connNonBill = null;
       CallableStatement pCall = null;
       ResultSet rsNonBill = null;
       try {
    	   System.out.println("in updateLines ");
         connNonBillUtil = new CanonCustAppDBUtil();
         connNonBill = (Connection)(connNonBillUtil.getConnection());
         pCall = connNonBill.prepareCall("{call CANON_E193_CS_EVOLUTION_PKG.UPDATE_NONBILL_LINE(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
         pCall.setInt(1, lineObj.getTicketId());
         pCall.setInt(2, lineObj.getOrgId());
         pCall.setInt(3, lineObj.getLineId());
         pCall.setString(4, lineObj.getSeverity());
         pCall.setString(5, lineObj.getReasonCd());
         pCall.setString(6, lineObj.getReason());
         pCall.setString(7, lineObj.getAttribute1());
         pCall.setString(8, lineObj.getAttribute2());
         pCall.setString(9, lineObj.getAttribute3());
         pCall.setString(10, lineObj.getAttribute4());
         pCall.setString(11, lineObj.getAttribute5());
         pCall.setString(12, lineObj.getCreatedBy());
         pCall.setString(13, lineObj.getNotes());

         pCall.registerOutParameter(14, OracleTypes.NUMBER);

         pCall.execute();

         isUpdated = pCall.getInt(14);
         if(isUpdated == -1) throw new CanonCustAppExceptionUtil(100001, "update line failed", "Class: Canon_E193_NonBillingIssue, Method:getNonBillLine()");

       }catch(CanonCustAppExceptionUtil csExp) {
         throw (csExp);
       }catch(SQLException eSQLExp) {
         throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:getNonBillLine()"));
       }catch (Exception eExp) {
         throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:getNonBillLine()"));
       }
       finally {
         try {
            if(pCall != null) pCall.close();
            if(rsNonBill != null) rsNonBill.close();
            if(connNonBillUtil != null) connNonBillUtil.releaseConnection();
         }catch (CanonCustAppExceptionUtil csExp) {
            throw (csExp);
         }catch (SQLException eSQLExp) {
            throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:getNonBillLine()"));
         }catch (Exception eExp) {
            throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:getNonBillLine()"));
         }
       }
      return isUpdated;
   }

   /** ITG # 208045 - Canon Ball Changes **/
   /**
    * This method will return the Dff Details.
    * <p>
    * @return int.
    * @param sourceContext.
    * @exception com.canon.oracle.custapp.util.CanonCustAppExceptionUtil.
    */

   public int getDffCount(String sourceContext) throws CanonCustAppExceptionUtil {
      int iDffCount = 0;
       //Get Connection
       CanonCustAppDBUtil connDffUtil = null;
       Connection connDff = null;
       CallableStatement pCall = null;

       try {
    	   System.out.println("in getDffCount ");
         connDffUtil = new CanonCustAppDBUtil();
         connDff = (Connection)(connDffUtil.getConnection());

         pCall = connDff.prepareCall("{call CANON_E193_CS_SQLS_PKG.get_dff_count(?,?)}");

         pCall.setString(1, sourceContext);
         pCall.registerOutParameter(2, OracleTypes.NUMBER);

         pCall.execute();

         iDffCount = pCall.getInt(2);

       }catch(CanonCustAppExceptionUtil csExp) {
         throw (csExp);
       }catch(SQLException eSQLExp) {
         throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:getDffCount()"));
       }catch (Exception eExp) {
         throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:getDffCount()"));
       }
       finally {
         try {
            if(pCall != null) pCall.close();
            if(connDffUtil != null) connDffUtil.releaseConnection();
         }catch (CanonCustAppExceptionUtil csExp) {
            throw (csExp);
         }catch (SQLException eSQLExp) {
            throw (new CanonCustAppExceptionUtil(100001, eSQLExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:getDffCount()"));
         }catch (Exception eExp) {
            throw (new CanonCustAppExceptionUtil(100001, eExp.toString(), "Class: Canon_E193_NonBillingIssue, Method:getDffCount()"));
         }
       }
      return iDffCount;
   }
   /** ITG # 208045 - Canon Ball Changes **/

}
