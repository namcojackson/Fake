/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB090001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import business.db.DLY_AJE_RPT_INVTYTMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>    
 * Temp Cost Item Data Checklist Report
 *  
 * Date             Company         Name          Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 12/28/2009       CSAI            N.Sasaki      Create          N/A 
 * 05/17/2013       CSAI            K.Uramori     Update          Modification for DS
 * 07/29/2013       Fujitsu         T.Tanaka      Update          Def#1453  Mdse/Parts Inventry In-Out Report modify Logic
 * 02/23/2016       CSAI            K.Uramori     Update          Modification for CSA
 * 2018/02/19       Hitachi         J.Kim         Update          QC#20564
 * 2018/08/20       CITS            T.Hakodate    Update          QC#24951
 *</pre>
 */
public class NFAB090001 extends S21BatchMain implements NFAB090001Constant, ZYPConstant, NFACommonJrnlEntryConstant {

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Count */
    private int commitCount = 0;

    /** Termination Code */
    private TERM_CD termCd;

    /** Error message */
    private String errMsg = "";

    /** Process Date */
    private static String startDt = "";  // First date of this month

    /** Open Date */
    private static String openDt = "";   // Biggest date of previous month (Usually last date of the previous month)

    /** Process Date */
    private static String procDt = "";

    /** Array of TMsg */
    private EZDTMsg[] tMsgCreate;

    /** Size of Array */
    private int tMsgCreateCnt = 0;

    /** Open Amount */
    private BigDecimal invtyOpenAmt;

    /** End Amount */
    private BigDecimal invtyEndAmt;

    /** Revaluation Amount */
    private BigDecimal rvalAmt;

    /** Ship In List */
    private List<DLY_AJE_RPT_INVTYTMsg> shipInList;

    /** Ship Out List */
    private List<DLY_AJE_RPT_INVTYTMsg> shipOutList;

    ///** COA_PROD_CD List */
    //private List<String> coaProdCdList;
    
    // QC#24951 Mod start
    /** COA_PROD_CD List */
    private List<Map<String, Object>> coaProdCdList;
    // QC#24951 Mod end

    /** Journal Entry Common Module */
    private NFACommonJrnlEntry common = new NFACommonJrnlEntry();

    // START 2018/02/22 J.Kim [QC#20564,ADD]
    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;
    // END 2018/02/22 J.Kim [QC#20564,ADD]

    /**
     * @param args String
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        // Get parameter (PROC_DT)
        procDt = S21BatchUtil.getInputParam1();
        new NFAB090001().executeBatch(NFAB090001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        this.termCd = TERM_CD.NORMAL_END;
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        this.coaProdCdList = new ArrayList<Map<String,Object>>();
        // START 2018/02/22 J.Kim [QC#20564,ADD]
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // END 2018/02/22 J.Kim [QC#20564,ADD]

        resetFields();

        if (procDt == null || procDt.equals(BLANK)) {
            procDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        }
        startDt = procDt.substring(0, 6) + "01";

        // // get a previous date of process date
        // startDt = getPrevDateStr(procDt);

        // initialize
        tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];

        S21InfoLogOutput.println("initRoutine Method End");
    }

    private void resetFields() {
        this.invtyOpenAmt = new BigDecimal("0");
        this.invtyEndAmt = new BigDecimal("0");
        this.rvalAmt = new BigDecimal("0");

        this.shipInList = new ArrayList<DLY_AJE_RPT_INVTYTMsg>();
        this.shipOutList = new ArrayList<DLY_AJE_RPT_INVTYTMsg>();
    }

    @Override
    protected void mainRoutine() {

        // get a previous date of process date
        String firstDt = procDt.substring(0,6) + "01";
        if (!getPrevDateStr(firstDt)) {
            return;
        }

        // Get COA_PROD_CD(s)
        if (!getCoaProdCds()) {
            return;
        }

        // Delete the record of process date
        deleteAllRecords(procDt);

        // Create data by COA_PROC_CD
        for (int i = 0; i < coaProdCdList.size(); i++) {

            // QC#24951 add start
            //String coaProdCd = (String) coaProdCdList.get(i);
            String coaProdCd = (String)coaProdCdList.get(i).get(COA_PROD_CD);
            String coaAcctCd = (String)coaProdCdList.get(i).get(COA_ACCT_CD);
            // QC#24951 add end

            // Open amount
            // QC#24951 mod start. Add coaAcctCd.
            if (!getOpenEndAmount(openDt, OPEN, coaProdCd , coaAcctCd)) {  // last date of previoud month
                return;
            }
            // End amount
            if (!getOpenEndAmount(procDt, END, coaProdCd , coaAcctCd)) {
                return;
            }
            // QC#24951 mod end
            
            // Revaluation
            // QC#24951 mod start. Add coaAcctCd.
            if (!getRvalAmount(coaProdCd, coaAcctCd)) {
                return;
            }
            // QC#24951 mod end

            // Ship In
            // QC#24951 mod start. Add coaAcctCd.
            int shipInCount = getShipInOut(INVTY_IN_OUT_STS_CD_SHIP_IN, coaProdCd, coaAcctCd);

            // Ship Out
            int shipOutCount = getShipInOut(INVTY_IN_OUT_STS_CD_SHIP_OUT, coaProdCd, coaAcctCd);
            // QC#24951 mod end
            
            if (shipInCount == ERROR || shipOutCount == ERROR) {
                return;
            }
            int maxRowCount = Math.max(shipInCount, shipOutCount);
            // At least one record required
            if (maxRowCount == 0) {
                maxRowCount = 1;
            }
            // Set work table for report
            // QC#24951 mod start. Add coaAcctCd.
            if (!setWorkTable(maxRowCount, coaProdCd, coaAcctCd)) {
                // QC#24951 mod end
                S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
                rollback();
                return;
            }
        }

        // Create data by any COA_PROD_CD
        if (!createAll()) {
            S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
            rollback();
            return;
        }
        
        commit();
        //rollback();

        //---- start del 2016/02/23. Stop creating reports
        // Generate Printing Document
        /*genPrintingDoc(reportIdAll);
        genPrintingDoc(reportIdByProd);
        */
        //---- end 2016/02/23
    }

    private void deleteAllRecords(String procDt) {
        // Delete the record of process date
        DLY_AJE_RPT_INVTYTMsg tMsgDelete = new DLY_AJE_RPT_INVTYTMsg();
        common.setFieldValue(tMsgDelete, "glblCmpyCd", glblCmpyCd);
        common.setFieldValue(tMsgDelete, "glDt", procDt);
        S21FastTBLAccessor.removeByPartialValue(tMsgDelete, new String[] {"glblCmpyCd", "glDt" });
    }

    /**
     * createAll
     * @return boolean
     */
    private boolean createAll() {

        // Open amount
        // QC#24951 mod start. Add coaAcctCd.
        if (!getOpenEndAmount(openDt, OPEN, null, null)) {
            return false;
        }
        // End amount
        if (!getOpenEndAmount(procDt, END, null, null)) {
            return false;
        }
        // Revaluation
        if (!getRvalAmount(null, null)) {
            return false;
        }

        // Ship In
        int shipInCount = getShipInOut(INVTY_IN_OUT_STS_CD_SHIP_IN, null, null);
        // Ship Out
        int shipOutCount = getShipInOut(INVTY_IN_OUT_STS_CD_SHIP_OUT, null, null);
        // QC#24951 mod end.

        if (shipInCount == ERROR || shipOutCount == ERROR) {
            return false;
        }
        int maxRowCount = Math.max(shipInCount, shipOutCount);
        // At least one record required
        if (maxRowCount == 0) {
            maxRowCount = 1;
        }
        // Set work table for report
        if (!setWorkTable(maxRowCount, null, null)) {
            return false;
        }
        // QC#24951 mod end.
        return true;
    }

    private boolean getCoaProdCds() {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        queryParam.put("dateStart", openDt);
        queryParam.put("dateEnd", procDt);
        queryParam.put("parts", MDSE_CATG.PARTS);

        // START 2018/02/22 J.Kim [QC#20564,MOD]
        //Boolean result = (Boolean) this.ssmBatchClient.queryObject("getCoaProdCds", queryParam, new GetCoaProdCds());
        //if (!result) {
        //    S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
        //}

        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getCoaProdCds", queryParam, execParam());
            rs = stmt.executeQuery();

            result = checkCoaProdCds(rs);
            if (!result) {
                S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        // END 2018/02/22 J.Kim [QC#20564,MOD]

        return result;
    }

    /** Get set of COA_PROD_CD */
    private boolean checkCoaProdCds(ResultSet rs) throws SQLException {

        try {
            while (rs.next()) {

                String coaProdCd = rs.getString(COA_PROD_CD);
                String accountCd = rs.getString(COA_ACCT_CD);

                if (coaProdCd != null && coaProdCd.equals(BLANK)) {
                    coaProdCd = "UNKNOWN";
                }

                Map<String, Object> result = new HashMap<String, Object>();
                result.put(COA_PROD_CD, coaProdCd);
                result.put(COA_ACCT_CD, accountCd);

                coaProdCdList.add(result);
            }
        } catch (EZDAbendException ex) {
            errMsg = ex.getMessage();
            return false;
        } catch (SQLException ex) {
            errMsg = ex.getMessage();
            return false;
        }
        return true;
    }

    private boolean getOpenEndAmount(String invtySnapShotDt, int openOrEnd, String coaProdCd , String coaAcctCd) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        queryParam.put("invtySnapShotDt", invtySnapShotDt);
        queryParam.put("coaProdCd", coaProdCd);
        queryParam.put("parts", MDSE_CATG.PARTS);
        //QC#24951 ADD START
        queryParam.put("coaAcctCd", coaAcctCd);
        //QC#24951 ADD END

        // START 2018/02/22 J.Kim [QC#20564,MOD]
        // Boolean result = (Boolean) this.ssmBatchClient.queryObject("getOpenEndAmount", queryParam, new GetOpenEndAmount(openOrEnd));
        // if (!result) {
        //    S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
        // }

        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getOpenEndAmount", queryParam, execParam());
            rs = stmt.executeQuery();

            result = checkOpenEndAmount(rs, openOrEnd);
            if (!result) {
                S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        // END 2018/02/22 J.Kim [QC#20564,MOD]

        return result;
    }

    /**
     * Get Open/End Amount
     * @param rs ResultSet
     * @param openOrEnd
     * @return boolean
     */
    private boolean checkOpenEndAmount(ResultSet rs, int openOrEnd) {

        /** Open/End Status */
        int openEndSts = 0;

        try {
            openEndSts = openOrEnd;
            while (rs.next()) {
                BigDecimal amount = rs.getBigDecimal(DLY_MDSE_SMRY_AMT_SUM);

                if (openEndSts == OPEN) {
                    invtyOpenAmt = amount;
                } else if (openEndSts == END) {
                    invtyEndAmt = amount;
                }
            }
        } catch (EZDAbendException ex) {
            errMsg = ex.getMessage();
            return false;
        } catch (SQLException ex) {
            errMsg = ex.getMessage();
            return false;
        }
        return true;
    }

    /**
     * getRvalAmount
     * @param coaProdCd
     * @param coaAcctCd
     * @return
     */
    private boolean getRvalAmount(String coaProdCd, String coaAcctCd) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        queryParam.put("dateStart", startDt);
        queryParam.put("dateEnd", procDt);
        queryParam.put("coaProdCd", coaProdCd);
        queryParam.put("parts", MDSE_CATG.PARTS);
        //QC#24951 ADD START
        queryParam.put("coaAcctCd", coaAcctCd);
        //QC#24951 ADD END

        // START 2018/02/22 J.Kim [QC#20564,MOD]
        // Boolean result = (Boolean) this.ssmBatchClient.queryObject("getRvalAmount", queryParam, new GetRvalAmount());
        // if (!result) {
        //    S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
        // }

        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getRvalAmount", queryParam, execParam());
            rs = stmt.executeQuery();

            result = checkRvalAmount(rs);
            if (!result) {
                S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        // END 2018/02/22 J.Kim [QC#20564,MOD]

        return result;
    }

    /**
     * Get Revaluation Amount
     * @param rs ResultSet
     * @return boolean
     */
    private boolean checkRvalAmount(ResultSet rs) {

        try {
            while (rs.next()) {
                BigDecimal amount = rs.getBigDecimal(RVAL_AMT_SUM);
                rvalAmt = amount;
            }
        } catch (EZDAbendException ex) {
            errMsg = ex.getMessage();
            return false;
        } catch (SQLException ex) {
            errMsg = ex.getMessage();
            return false;
        }
        return true;
    }

    private int getShipInOut(String invtyInOutStsCd, String coaProdCd , String coaAcctCd) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        queryParam.put("invtyInOutStsCd", invtyInOutStsCd);
        queryParam.put("coaProdCd", coaProdCd);
        queryParam.put("dateStart", startDt);
        queryParam.put("dateEnd", procDt);
        queryParam.put("parts", MDSE_CATG.PARTS);
        //QC#24951 ADD START
        queryParam.put("coaAcctCd", coaAcctCd);
        //QC#24951 ADD END

        // START 2018/02/22 J.Kim [QC#20564,MOD]
        // Integer resultShipIn = (Integer) this.ssmBatchClient.queryObject("getShipInOut", queryParam, new GetShipInOut(invtyInOutStsCd));
        // int recordCount = resultShipIn.intValue();
        // if (recordCount == ERROR) {
        //    S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
        // }

        PreparedStatement stmt = null;
        ResultSet rs = null;
        int recordCount = 0;

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getShipInOut", queryParam, execParam());
            rs = stmt.executeQuery();

            recordCount = checkShipInOut(rs, invtyInOutStsCd);
            if (recordCount == ERROR) {
                S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        // END 2018/02/22 J.Kim [QC#20564,MOD]

        return recordCount;
    }

    /** Get number of record in Ship-In/Out */
    private int checkShipInOut(ResultSet rs, String invtyInOutStsCd) {

        int recCount = 0;
        try {
            while (rs.next()) {
                String trxCd = rs.getString(TRX_CD);
                String trxRsnCd = rs.getString(TRX_RSN_CD);
                BigDecimal invtyShipAmt = rs.getBigDecimal(INVTY_SHIP_AMT);

                if (invtyInOutStsCd.equals(INVTY_IN_OUT_STS_CD_SHIP_IN)) {
                    DLY_AJE_RPT_INVTYTMsg tempMsg = new DLY_AJE_RPT_INVTYTMsg();

                    common.setFieldValue(tempMsg, "drTrxCd", trxCd);
                    common.setFieldValue(tempMsg, "drTrxRsnCd", trxRsnCd);
                    common.setFieldValue(tempMsg, "shipInAmt", invtyShipAmt);
                    // Set Ship In
                    shipInList.add(tempMsg);
                } else if (invtyInOutStsCd.equals(INVTY_IN_OUT_STS_CD_SHIP_OUT)) {
                    DLY_AJE_RPT_INVTYTMsg tempMsg = new DLY_AJE_RPT_INVTYTMsg();

                    common.setFieldValue(tempMsg, "crTrxCd", trxCd);
                    common.setFieldValue(tempMsg, "crTrxRsnCd", trxRsnCd);
                    common.setFieldValue(tempMsg, "shipOutAmt", invtyShipAmt);
                    // Set Ship Out
                    shipOutList.add(tempMsg);
                }
                recCount++;
            }
            // If no result, at least one row need to be created
            if (recCount == 0 && invtyInOutStsCd.equals(INVTY_IN_OUT_STS_CD_SHIP_IN)) {
                DLY_AJE_RPT_INVTYTMsg tempMsgIn = new DLY_AJE_RPT_INVTYTMsg();
                common.setFieldValue(tempMsgIn, "drTrxCd", BLANK);
                common.setFieldValue(tempMsgIn, "drTrxRsnCd", BLANK);
                common.setFieldValue(tempMsgIn, "shipInAmt", new BigDecimal("0"));
                shipInList.add(tempMsgIn);
            } else if (recCount == 0 && invtyInOutStsCd.equals(INVTY_IN_OUT_STS_CD_SHIP_OUT)) {
                DLY_AJE_RPT_INVTYTMsg tempMsgOut = new DLY_AJE_RPT_INVTYTMsg();
                common.setFieldValue(tempMsgOut, "crTrxCd", BLANK);
                common.setFieldValue(tempMsgOut, "crTrxRsnCd", BLANK);
                common.setFieldValue(tempMsgOut, "shipOutAmt", new BigDecimal("0"));
                shipOutList.add(tempMsgOut);
            }
        } catch (EZDAbendException ex) {
            errMsg = ex.getMessage();
            return ERROR;
        } catch (SQLException ex) {
            errMsg = ex.getMessage();
            return ERROR;
        }
        return recCount;
    }

    /**
     * setWorkTable
     * @param maxRowCount
     * @param coaProdCd
     * @param coaAcctCd
     * @return
     */
    private boolean setWorkTable(int maxRowCount, String coaProdCd , String coaAcctCd) {

        try {

            for (int i = 0; i < maxRowCount; i++) {

                BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(DLY_AJE_RPT_INVTY_SQ);

                DLY_AJE_RPT_INVTYTMsg tMsg = new DLY_AJE_RPT_INVTYTMsg();

                BigDecimal shipInAmt = BigDecimal.ZERO;
                BigDecimal shipOutAmt = BigDecimal.ZERO;
                
                common.setFieldValue(tMsg, "glblCmpyCd", glblCmpyCd);
                common.setFieldValue(tMsg, "dlyAjeRptInvtyPk", seqNum);
                common.setFieldValue(tMsg, "glDt", procDt);
                if (coaProdCd != null) {
                    common.setFieldValue(tMsg, "coaProdCd", coaProdCd);
                }
                
                // QC#24951 mod start. Add coaAcctCd.
                if (coaAcctCd != null) {
                    common.setFieldValue(tMsg, "coaAcctCd", coaAcctCd);
                }
                // QC#24951 mod end.
                
                if (i == 0) {
                    common.setFieldValue(tMsg, "invtyOpenAmt", invtyOpenAmt);
                    common.setFieldValue(tMsg, "invtyEndAmt", invtyEndAmt);
                    common.setFieldValue(tMsg, "rvalAmt", rvalAmt);
                }

                if (shipInList != null && i < shipInList.size()) {
                    DLY_AJE_RPT_INVTYTMsg tempMsg = (DLY_AJE_RPT_INVTYTMsg) shipInList.get(i);

                    common.setFieldValue(tMsg, "drTrxCd", tempMsg.drTrxCd.getValue());
                    common.setFieldValue(tMsg, "drTrxRsnCd", tempMsg.drTrxRsnCd.getValue());
                    common.setFieldValue(tMsg, "shipInAmt", tempMsg.shipInAmt.getValue());
                    shipInAmt = tempMsg.shipInAmt.getValue();
                }

                if (shipOutList != null && i < shipOutList.size()) {
                    DLY_AJE_RPT_INVTYTMsg tempMsg = (DLY_AJE_RPT_INVTYTMsg) shipOutList.get(i);

                    common.setFieldValue(tMsg, "crTrxCd", tempMsg.crTrxCd.getValue());
                    common.setFieldValue(tMsg, "crTrxRsnCd", tempMsg.crTrxRsnCd.getValue());
                    common.setFieldValue(tMsg, "shipOutAmt", tempMsg.shipOutAmt.getValue());
                    shipOutAmt = tempMsg.shipOutAmt.getValue();
                }

                //---- start mod 201602/23  Calculate variance and set to the table
                BigDecimal var = (invtyOpenAmt.add(rvalAmt).add(shipInAmt).add(shipOutAmt.negate())).add(invtyEndAmt.negate());
                setValue(tMsg.varAmt, var);
                //---- end 2016/02/23
                
                createRecord(tMsg);
            }

            if (tMsgCreateCnt != 0) {
                createRecord(null);
            }

        } catch (EZDAbendException ex) {
            errMsg = ex.getMessage();
            return false;
        } catch (NFACommonJrnlEntry.JrnlCommonException ex) {
            errMsg = ex.getMessage();
            return false;
        }
        resetFields();

        return true;
    }

    /**
     * <pre>
     *  Create New Record
     * </pre>
     * 
     * @param EZDTMsg tMsg
     * @throws JrnlCommonException JrnlCommonException
     */
    private void createRecord(EZDTMsg tMsg) throws NFACommonJrnlEntry.JrnlCommonException {

        if (tMsg != null) {
            tMsgCreate[tMsgCreateCnt] = tMsg;
            tMsgCreateCnt += 1;

        } else { // array may be not full
            tMsgCreate = common.changeArraySize(tMsgCreate, tMsgCreateCnt);
        }

        // per 10000 lines
        if (tMsgCreateCnt >= BULK_INSERT_COUNT || tMsg == null) {

            int retCnt = S21FastTBLAccessor.insert(tMsgCreate);

            // if passed records' count and return count don't
            // match, error
            if (retCnt != tMsgCreateCnt) {
                throw common.new JrnlCommonException(ZZBM0074E);
            }
            commitCount += tMsgCreateCnt;
            // initialize
            tMsgCreateCnt = 0;
            tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];
        }
    }

    /** Get Previous of Process Date */
    private boolean getPrevDateStr(String procDtStr) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("dateProc", procDtStr);
        queryParam.put("parts", MDSE_CATG.PARTS);

        // START 2018/02/22 J.Kim [QC#20564,MOD]
        //Boolean result = (Boolean) this.ssmBatchClient.queryObject("getMaxPrevDt", queryParam, new GetPrevDate(procDtStr));
        //if (!result) {
        //    S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
        //}

        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getMaxPrevDt", queryParam, execParam());
            rs = stmt.executeQuery();

            result = checkPrevDate(rs, procDtStr);
            if (!result) {
                S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        // END 2018/02/22 J.Kim [QC#20564,MOD]

        return result;
    }

    private boolean checkPrevDate(ResultSet rs, String procDtStr) {

        try {
            openDt = procDtStr;
            while (rs.next()) {
                openDt = rs.getString(DT_PREV);
            }

            if (openDt == null) {
                openDt = procDt;
            }
        } catch (EZDAbendException ex) {
            errMsg = ex.getMessage();
            return false;
        } catch (SQLException ex) {
            errMsg = ex.getMessage();
            return false;
        }
        return true;
    }

    // START 2018/02/22 J.Kim [QC#20564,ADD]
    /**
     * execParam
     * @return S21SsmExecutionParameter
     */
    private S21SsmExecutionParameter execParam() {
        // Execute parameter
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }
    // END 2018/02/22 J.Kim [QC#20564,ADD]

    // private String getPrevDateStr(String procDtStr) {
    //
    // try {
    // Calendar cal = Calendar.getInstance();
    // cal.setTime(dateFormat.parse(procDtStr));
    // cal.add(Calendar.DATE, -1);
    //
    // return dateFormat.format(cal.getTime());
    // } catch (ParseException e) {
    // S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM,
    // e.getMessage() });
    // throw new S21AbendException("Error occured during the
    // procedure: " + e.getMessage());
    // }
    // }

    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, 0, commitCount);

        S21InfoLogOutput.println("termRoutine Method End");
    }
}
