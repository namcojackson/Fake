/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB091001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.MTH_INVTY_RPT_RVALTMsg;
import business.db.INVTY_DTL_RPT_SMRY_AMTTMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * This batch copy the data from S21_ORG to TOC_MTH_SNAP_SHOT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/28/2009   CSA             N.Sasaki        Create          N/A
 * 05/17/2013   CSAI            K.Uramori       Update          Modification for DS
 * 02/23/2016   CSAI            K.Uramori       Update          Modification for CSA
 * 11/20/2017   CITS            K.Ogino         Update             QC#22290
 * 12/07/2017   Hitachi         Y.Takeno        Update          QC#20644
 * 02/06/2018   Hitachi         Y.Takeno        Update          QC#23971
 * </pre>
 */
public class NFAB091001 extends S21BatchMain implements NFAB091001Constant, ZYPConstant, NFACommonJrnlEntryConstant {

    /** Date format */
    private SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDD);

    /** SSM Batch Clinent */
    private S21SsmBatchClient ssmBatchClient;

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
    
    /** Last date of the previous month */
    private static String startDt = "";

    /** Process Date */
    private static String procDt = "";

    /** First Date of the month */
    //private static String firstDt = "";

    /** Array of TMsg */
    private EZDTMsg[] tMsgCreate;

    /** Size of Array */
    private int tMsgCnt = 0;

    /** Journal Entry Common Module */
    private NFACommonJrnlEntry common = new NFACommonJrnlEntry();
    
    /** Loan Inventory Account Code */
    private String loanAcct;

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        // Get parameter (PROC_DT)
        procDt = S21BatchUtil.getInputParam1();

        new NFAB091001().executeBatch(NFAB091001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();

        if (procDt == null || procDt.equals(BLANK)) {
            procDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        }

        // Get the first date of the month of process date
        // firstDt = getFirstDateOfMonth(procDt);
        //---- start del 2016/02/23 revaluation will be generated everyday.
        //firstDt = getFirstDateOfNextMonth(procDt);
        //---- end 2016/02/23

        // initialize
        tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];

        loanAcct = ZYPCodeDataUtil.getVarCharConstValue(LOAN_INVTY_ACCT_CD, this.glblCmpyCd);
        
        S21InfoLogOutput.println("initRoutine Method End");
    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");
        
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("trxCdCostCalculation", TRX.COST_CALCULATION);
        queryParam.put("trxRsnCdRevaluation", TRX_RSN_CD_REVALUATION);
        queryParam.put("trxRsnCdWriteDown", TRX_RSN_CD_WRITE_DOWN);
        
        //---- start add 2016/02/23
        // queryParam.put("glDt", firstDt);
        queryParam.put("flgY", FLG_ON_Y);
        queryParam.put("glYearMonth", this.procDt.substring(0, 6).concat("%"));
        //---- end 2016/02/23
        
        //---- start add 2016/04/11
        queryParam.put("loanAcct", this.loanAcct);
        //---- end 2016/04/11
        
        Boolean result = (Boolean) this.ssmBatchClient.queryObject("getResultNFAB091001", queryParam, new DBProc());

        if (result) {
            commit();
        } else {
            //S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
        }

        // update COA_PROD_CD in INVTY_DTL_RPT_SMRY_AMT
        if (updateCoaProdCd() == Boolean.FALSE) {
            rollback();
            // not get it abend. not much impact
        } else {
            commit();
        }
        
        S21InfoLogOutput.println("mainRoutine Method End");
    }

    private boolean updateCoaProdCd() {
        
        // Get the last date of the previous month
        if(!getLastDateOfPrevMonth(getFirstDateOfMonth(procDt))){
            return Boolean.FALSE;
        }
        
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("lastDate", startDt);
        // QC#22290 Start
        queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
        // QC#22290 End

        Boolean result = (Boolean) this.ssmBatchClient.queryObject("getCoaProdCdChange", queryParam, new DBProcForProdChange());
        
        return result;

    }
    
    /** Get Previous Month of Last Date */
    private boolean getLastDateOfPrevMonth(String procDtStr) {
    
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("dateProc", procDtStr);
    
        Boolean result = (Boolean) this.ssmBatchClient.queryObject("getMaxPrevDt", queryParam, new GetPrevDate(procDtStr));
    
        if (!result) {
            S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
        }
          
        return result;
    }
    
    private final class GetPrevDate extends S21SsmBooleanResultSetHandlerSupport {

        private GetPrevDate(String procDtStr) {
            startDt = procDtStr;
        }

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
                while (rs.next()) {
                    startDt = rs.getString(DT_PREV);
                }
                
                if(startDt == null){
                    startDt = procDt;
                }
            } catch (EZDAbendException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (SQLException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }
    }
    
    private String getFirstDateOfMonth(String dateStr) {

        if (dateStr != null && dateStr.length() == 8) {
            String date = dateStr.substring(0, 6);
            return date + "01";
        } else {
            return BLANK;
        }
    }
    
    /** DB Process fro COA_PROD_CD update*/
    private class DBProcForProdChange extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {

                while (rs.next()) {

                    INVTY_DTL_RPT_SMRY_AMTTMsg tMsg = new INVTY_DTL_RPT_SMRY_AMTTMsg();

                    common.setFieldValue(tMsg, "glblCmpyCd", glblCmpyCd);
                    common.setFieldValue(tMsg, "invtySnapShotDt", startDt);
                    common.setFieldValue(tMsg, "mdseCd", common.checkNull(rs.getString(MDSE_CD)));
                    
                    tMsg = (INVTY_DTL_RPT_SMRY_AMTTMsg)EZDTBLAccessor.findByKey(tMsg);

                    if (tMsg != null) {
                        common.setFieldValue(tMsg, "coaProdCd", common.checkNull(rs.getString(COA_PROD_CD)));
                        
                        EZDTBLAccessor.update(tMsg);
                    }
                } // while

            } catch (EZDAbendException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (SQLException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } 

            return Boolean.TRUE;
        }
    }
       
    private boolean removeOldData() {
        
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("glYearMonth", this.procDt.substring(0, 6).concat("%"));
        
        List<MTH_INVTY_RPT_RVALTMsg> list = (List<MTH_INVTY_RPT_RVALTMsg>) this.ssmBatchClient.queryObjectList("getOldData", queryParam);
        
        if (list.size() == 0) {
            return true;
        }
        
        int rtn = S21FastTBLAccessor.removePhysical(list.toArray(new MTH_INVTY_RPT_RVALTMsg[list.size()]));
        
        if (rtn != list.size()) {
            return false;
        }
        
        return true;
    }
    
    /** DB Process */
    private class DBProc extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
              //---- start mod 2016/02/23 revaluation will be generated everyday. So this month data to be refreshed.
                // Clear table
                /*MTH_INVTY_RPT_RVALTMsg tMsgDelete = new MTH_INVTY_RPT_RVALTMsg();
                common.setFieldValue(tMsgDelete, "glblCmpyCd", glblCmpyCd);
                common.setFieldValue(tMsgDelete, "glDt", firstDt);
                //---- end 2016/02/23
                S21FastTBLAccessor.removeByPartialValue(tMsgDelete, new String[] {"glblCmpyCd", "glDt" });
                */
                if (!removeOldData()) {
                    return false;
                }
                //---- end 2016/02/23

                while (rs.next()) {

                    BigDecimal jrnlEntryPk = common.checkNull(rs.getBigDecimal(JRNL_ENTRY_PK));
                    String ajeIntfcTpCd = common.checkNull(rs.getString(AJE_INTFC_TP_CD));
                    BigDecimal ajeIntfcPk = common.checkNull(rs.getBigDecimal(AJE_INTFC_PK));
                    String glDt = common.checkNull(rs.getString(GL_DT));
                    String ajeItemCd = common.checkNull(rs.getString(AJE_ITEM_CD));
                    String coaProdCd = common.checkNull(rs.getString(COA_PROD_CD));
                    BigDecimal jrnlQty = common.checkNull(rs.getBigDecimal(JRNL_QTY));
                    String trxCd = common.checkNull(rs.getString(TRX_CD));
                    String trxRsnCd = common.checkNull(rs.getString(TRX_RSN_CD));
                    BigDecimal jrnlfuncDrAmt = common.checkNull(rs.getBigDecimal(JRNL_FUNC_DR_AMT));
                    String coaAcctCd = common.checkNull(rs.getString(COA_ACCT_CD));

                    if (coaProdCd == null || coaProdCd.equals(BLANK)) {
                        coaProdCd = UNKNOWN;
                    }

                    // START 2018/02/06 [QC#23971, MOD]
                    // START 2017/12/07 [QC#20644, ADD]
                    // String drCoaAcctCd = common.checkNull(rs.getString(DR_COA_ACCT_CD));
                    // if (drCoaAcctCd != null && drCoaAcctCd.equals(coaAcctCd)) {
                    //     jrnlfuncDrAmt = jrnlfuncDrAmt.multiply(BigDecimal.ONE.negate());
                    // }
                    String crCoaAcctCd = common.checkNull(rs.getString(CR_COA_ACCT_CD));
                    if (crCoaAcctCd != null && crCoaAcctCd.equals(coaAcctCd)) {
                        jrnlfuncDrAmt = jrnlfuncDrAmt.multiply(BigDecimal.ONE.negate());
                    }
                    // END   2017/12/07 [QC#20644, ADD]
                    // END   2018/02/06 [QC#23971, MOD]

                    MTH_INVTY_RPT_RVALTMsg tMsg = new MTH_INVTY_RPT_RVALTMsg();

                    common.setFieldValue(tMsg, "glblCmpyCd", glblCmpyCd);
                    common.setFieldValue(tMsg, "jrnlEntryPk", jrnlEntryPk);
                    common.setFieldValue(tMsg, "ajeIntfcTpCd", ajeIntfcTpCd);
                    common.setFieldValue(tMsg, "ajeIntfcPk", ajeIntfcPk);
                    common.setFieldValue(tMsg, "glDt", glDt);
                    common.setFieldValue(tMsg, "ajeItemCd", ajeItemCd);
                    common.setFieldValue(tMsg, "coaProdCd", coaProdCd);
                    common.setFieldValue(tMsg, "jrnlQty", jrnlQty);
                    common.setFieldValue(tMsg, "trxCd", trxCd);
                    common.setFieldValue(tMsg, "trxRsnCd", trxRsnCd);
                    common.setFieldValue(tMsg, "rvalAmt", jrnlfuncDrAmt);
                    common.setFieldValue(tMsg, "coaAcctCd", coaAcctCd);

                    createRecord(tMsg);
                } // while

                if (tMsgCnt != 0) {
                    createRecord(null);
                }

            } catch (EZDAbendException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (SQLException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (NFACommonJrnlEntry.JrnlCommonException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            }

            return Boolean.TRUE;
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
                tMsgCreate[tMsgCnt] = tMsg;
                tMsgCnt += 1;

            } else { // array may be not full
                tMsgCreate = common.changeArraySize(tMsgCreate, tMsgCnt);
            }

            // per 10000 lines
            if (tMsgCnt >= BULK_INSERT_COUNT || tMsg == null) {

                int retCnt = S21FastTBLAccessor.insert(tMsgCreate);

                // if passed records' count and return count don't
                // match, error
                if (retCnt != tMsgCnt) {
                    throw common.new JrnlCommonException(ZZBM0074E);
                }
                commitCount += tMsgCnt;
                // initialize
                tMsgCnt = 0;
                tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];
            }
        }
    }

    /*
    private String getFirstDateOfNextMonth(String procDtStr) {

        try {
            // Date String Validation
            if (procDtStr.length() != 8) {
                S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, "Invalid Date format" });
                throw new S21AbendException("Invalid Date format");
            }
            // Date Validation
            dateFormat.applyPattern(YYYYMMDD);
            dateFormat.setLenient(false);
            dateFormat.parse(procDtStr);

            Calendar cal = Calendar.getInstance();

            cal.set(Calendar.YEAR, Integer.parseInt(procDtStr.substring(0, 4)));
            cal.set(Calendar.MONTH, Integer.parseInt(procDtStr.substring(4, 6)) - 1);
            cal.set(Calendar.DATE, 1);

            cal.add(Calendar.MONTH, 1);

            return dateFormat.format(cal.getTime());
        } catch (ParseException e) {
            S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, e.getMessage() });
            throw new S21AbendException("Error occured during the procedure: " + e.getMessage());
        }
    }
    */

    // private String getFirstDateOfMonth(String dateStr) {
    //
    // if (dateStr != null && dateStr.length() == 8) {
    // String date = dateStr.substring(0, 6);
    // return date + "01";
    // } else {
    // return BLANK;
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
