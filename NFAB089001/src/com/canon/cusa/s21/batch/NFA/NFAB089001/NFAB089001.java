/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB089001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import business.db.DLY_INVTY_RPT_SHIPTMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

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
 * 05/17/2013   CSAI            K.Uramori       Update          Modification for DS.
 * 02/23/2016   CSAI            K.Uramori       Update          Modification for CSA
 * </pre>
 */
public class NFAB089001 extends S21BatchMain implements NFAB089001Constant, ZYPConstant, NFACommonJrnlEntryConstant {
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

    /** Process Date */
    private static String procDt = "";
    
    /** Process Date : YYYYMMDD */
    private String procYrMth;

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

        new NFAB089001().executeBatch(NFAB089001.class.getSimpleName());

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
            procDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        }

        this.procYrMth = ZYPDateUtil.DateFormatter(procDt, "yyyyMMdd", "yyyyMM") + "%";
        
        // initialize
        tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];
        
        loanAcct = ZYPCodeDataUtil.getVarCharConstValue(LOAN_INVTY_ACCT_CD, this.glblCmpyCd);

        S21InfoLogOutput.println("initRoutine Method End");
    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        // Clear table
        if(!deleteThsMthTrx(procDt)){
            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
        }
        
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        
        queryParam.put("locStsCdInTransit", LOC_STS_CD_IN_TRANSIT);
        queryParam.put("ajeIntfcTpCdMdseInvty", AJE_INTFC_TP_CD_MDSE_INVTY);
        queryParam.put("ajeIntfcTpCdMdseInvtyRval", AJE_INTFC_TP_CD_MDSE_INVTY_RVAL);
        queryParam.put("ajeIntfcTpCdMdseInventoryReclass", AJE_INTFC_TP_CD_MDSE_INVTY_RECLASS);

        queryParam.put("assetCdF", ASSET_CD_F);
        queryParam.put("glDt", procYrMth);
        
        //---- start add 2016/02/23
        queryParam.put("flgY", FLG_ON_Y);
        //---- end 2016/02/23
        
        //---- start add 2016/04/11
        queryParam.put("loanAcct", this.loanAcct);
        //---- end 2016/04/11

        Boolean result = (Boolean) this.ssmBatchClient.queryObject("getResultNFAB089001", queryParam, new DBProc());

        if (result) {
            commit();
        } else {
            //S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
        }

        S21InfoLogOutput.println("mainRoutine Method End");
    }

    /** DB Process */
    private class DBProc extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
                
//                DLY_INVTY_RPT_SHIPTMsg tMsgDelete = new DLY_INVTY_RPT_SHIPTMsg();
//                common.setFieldValue(tMsgDelete, "glblCmpyCd", glblCmpyCd);
//                common.setFieldValue(tMsgDelete, "glDt", procDt);
//                S21FastTBLAccessor.removeByPartialValue(tMsgDelete, new String[] {"glblCmpyCd", "glDt" });

                while (rs.next()) {

                    BigDecimal jrnlEntryPk = common.checkNull(rs.getBigDecimal(JRNL_ENTRY_PK));
                    String ajeIntfcTpCd = common.checkNull(rs.getString(AJE_INTFC_TP_CD));
                    BigDecimal ajeIntfcPk = common.checkNull(rs.getBigDecimal(AJE_INTFC_PK));
                    String glDt = common.checkNull(rs.getString(GL_DT));
                    String ajeItemCd = common.checkNull(rs.getString(AJE_ITEM_CD));
                    String drCoaAcctCd = common.checkNull(rs.getString(DR_COA_ACCT_CD));
                    String crCoaAcctCd = common.checkNull(rs.getString(CR_COA_ACCT_CD));
                    String coaProdCd = common.checkNull(rs.getString(COA_PROD_CD));
                    BigDecimal jrnlQty = common.checkNull(rs.getBigDecimal(JRNL_QTY));
                    String trxCd = common.checkNull(rs.getString(TRX_CD));
                    String trxRsnCd = common.checkNull(rs.getString(TRX_RSN_CD));
                    BigDecimal jrnlfuncDrAmt = common.checkNull(rs.getBigDecimal(JRNL_FUNC_DR_AMT));
                    BigDecimal jrnlfuncCrAmt = common.checkNull(rs.getBigDecimal(JRNL_FUNC_CR_AMT));
                    String invtyInOutStsCd = common.checkNull(rs.getString(D_OR_C));

                    DLY_INVTY_RPT_SHIPTMsg tMsg = new DLY_INVTY_RPT_SHIPTMsg();

                    common.setFieldValue(tMsg, "glblCmpyCd", glblCmpyCd);
                    common.setFieldValue(tMsg, "jrnlEntryPk", jrnlEntryPk);
                    common.setFieldValue(tMsg, "ajeIntfcTpCd", ajeIntfcTpCd);
                    common.setFieldValue(tMsg, "ajeIntfcPk", ajeIntfcPk);
                    common.setFieldValue(tMsg, "invtyInOutStsCd", invtyInOutStsCd);
                    
                    if (invtyInOutStsCd.equals(SHIP_IN_CD)) {
                        // Ship In (Debit)                        
                        common.setFieldValue(tMsg, "coaAcctCd", drCoaAcctCd);
                        common.setFieldValue(tMsg, "invtyShipAmt", jrnlfuncDrAmt);
                    } else if (invtyInOutStsCd.equals(SHIP_OUT_CD)) {
                        // Ship Out (Credit)
                        common.setFieldValue(tMsg, "coaAcctCd", crCoaAcctCd);
                        common.setFieldValue(tMsg, "invtyShipAmt", jrnlfuncCrAmt);
                    }

                    if (coaProdCd == null || coaProdCd.equals(BLANK)) {
                        coaProdCd = UNKNOWN;
                    }

                    common.setFieldValue(tMsg, "coaProdCd", coaProdCd);
                    common.setFieldValue(tMsg, "glDt", glDt);
                    common.setFieldValue(tMsg, "ajeItemCd", ajeItemCd);
                    common.setFieldValue(tMsg, "jrnlQty", jrnlQty);
                    common.setFieldValue(tMsg, "trxCd", trxCd);
                    common.setFieldValue(tMsg, "trxRsnCd", trxRsnCd);
                    
                    //-- start 2017/01/03 QC#16840 Unique key is added.
                    common.setFieldValue(tMsg, "drCrTpCd", rs.getString("DR_CR_TP_CD"));
                    //-- end 2017/01/03

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

    /** Get This Month Transaction */
    private boolean deleteThsMthTrx(String procDtStr) {
    
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("glDt", procYrMth);
    
        Boolean result = (Boolean) this.ssmBatchClient.queryObject("getThsMthTrx", queryParam, new getThsMthTrx());
          
        return result;
    }
    
    private final class getThsMthTrx extends S21SsmBooleanResultSetHandlerSupport {

        private getThsMthTrx() {
        }

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
                while (rs.next()) {
                    
                    if(rs.getBigDecimal(JRNL_ENTRY_PK) == null){
                        
                        return Boolean.FALSE;
                    } else {

                        DLY_INVTY_RPT_SHIPTMsg tMsgDelete = new DLY_INVTY_RPT_SHIPTMsg();

                        common.setFieldValue(tMsgDelete, "glblCmpyCd", glblCmpyCd);
                        common.setFieldValue(tMsgDelete, "jrnlEntryPk", rs.getBigDecimal(JRNL_ENTRY_PK));

                        S21FastTBLAccessor.removeByPartialValue(tMsgDelete, new String[] {"glblCmpyCd", "jrnlEntryPk" });
                    }
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
    
    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, 0, commitCount);

        S21InfoLogOutput.println("termRoutine Method End");
    }
}
