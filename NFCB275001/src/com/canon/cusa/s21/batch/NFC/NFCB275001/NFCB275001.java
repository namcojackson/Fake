package com.canon.cusa.s21.batch.NFC.NFCB275001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import business.db.AJE_AR_RECLS_INTFCTMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INTFC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_RSN;

/**
 * <Pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </Pre>
 * 
 * <Pre>
 * This batch stores front transactions in interface table.
 * AR Reclass AJE Link File Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2019   Fujitsu         H.Nagashima     Create          N/A
 * </pre>
 */
public class NFCB275001 extends S21BatchMain implements NFCB275001Constant, ZYPConstant, NFACommonJrnlEntryConstant {

    /** Process Date */
    private String procDt = "";

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

    /** Array of TMsg */
    private EZDTMsg[] intfcMsgs;
    /** Array of TMsg */
    private EZDTMsg[] rmvMsgs;
    /** Array of TMsg */
    private EZDTMsg[] intfcWrkMsgs;

    /** Size of Array */
    private int intfcMsgCount = 0;
    /** Size of Array */
    private int rmvMsgCount = 0;
    /** Size of Array */
    private int intfcWrkMsgCount = 0;

    /**  Journal Entry Common Module */
    private NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();

    /**
     * @param args Argument from a batch shell. 
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        /* Initialize S21BatchMain */
        new NFCB275001().executeBatch(NFCB275001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }


    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        this.termCd = TERM_CD.NORMAL_END;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();

        procDt = S21BatchUtil.getInputParam1();

        // if it's set by the parameter, it should not be overwritten
        if (procDt == null || procDt.equals("")) {
            procDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        }

        // initialize
        intfcMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        intfcWrkMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];

        S21InfoLogOutput.println("initRoutine Method End");

    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        List<Map> targetData = getAjeArReclsIntfcListNotJrnlized();

        if (targetData.size() > 0) {

            if (removeAjeArReclsIntfcNotJrnlized(targetData)) {
                commit();
            } else {
                rollback();
                throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
            }

        }

        if (!doEntryToAjeArReclsIntfc()) {
            commitCount = 0;
            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
        } else {
            commit();
        }

        S21InfoLogOutput.println("mainRoutine Method End");

    }

    /**
     * <pre>
     *  Get AJE Interface Key List that is not journalized from AJE Interface Control.
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private List<Map> getAjeArReclsIntfcListNotJrnlized() {

        // ** Get All Transaction Pattern in AJE AR Reclass Interface. **
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("ajeIntfcTpCd", AJE_INTFC_TP.AR_RECLASS);
        queryParam.put("jrnlCpltFlg", FLG_ON_Y);
        queryParam.put("trxRsnCdRcls", TRX_RSN.AR_RECLASS);
        queryParam.put("trxRsnCdRvsl", TRX_RSN.AR_RECLASS_REVERSAL);
        queryParam.put("procDt", procDt);

        List<Map> ssmRes = (List<Map>) ssmBatchClient.queryObjectList("getAjeArReclsIntfcListNotJrnlized", queryParam);

        return ssmRes;
    }

    /**
     * <pre>
     *  Remove AJE AR Reclass Interface that is not journalized.
     * </pre>
     * @param List<Map> AJE Interface Key List that is not journalized.
     * @return boolean true: OK  false: NG
     */
    private boolean removeAjeArReclsIntfcNotJrnlized(List<Map> ssmResForGetAjeIntfcCtrlKeyNotJrnlized) {

        try {
            for (Map<String, Object> rsAjeIntfcCtrlListNotJrnlized : ssmResForGetAjeIntfcCtrlKeyNotJrnlized) {

                AJE_AR_RECLS_INTFCTMsg ajeArReclsIntfc = new AJE_AR_RECLS_INTFCTMsg();
                String glblCmpyCdStr = (String) rsAjeIntfcCtrlListNotJrnlized.get(GLBL_CMPY_CD);
                BigDecimal ajeArReclsIntfcPkNum = new BigDecimal(rsAjeIntfcCtrlListNotJrnlized.get(AJE_AR_RECLS_INTFC_PK).toString());

                commonJrnlEntry.setFieldValue(ajeArReclsIntfc, "glblCmpyCd", glblCmpyCdStr);
                commonJrnlEntry.setFieldValue(ajeArReclsIntfc, "ajeArReclsIntfcPk", ajeArReclsIntfcPkNum);

                removeAjeIntfcNotJrnlizedHelper(ajeArReclsIntfc);

            }

            if (rmvMsgCount != 0) {
                removeAjeIntfcNotJrnlizedHelper(null);
            }
        } catch (NFACommonJrnlEntry.JrnlCommonException ex) {
            errMsg = ex.getMessage();
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * <pre>
     *  Remove AJE Interface Control per a certain amount of records.
     *  @param rmvRec TMsg to be removed
     *  @throws JrnlCommonException JrnlCommonException
     * </pre>
     */
    private void removeAjeIntfcNotJrnlizedHelper(EZDTMsg rmvRec) throws NFACommonJrnlEntry.JrnlCommonException {

        if (rmvRec != null) {
            rmvMsgs[rmvMsgCount] = rmvRec;
            rmvMsgCount += 1;
        } else {
            rmvMsgs = commonJrnlEntry.changeArraySize(rmvMsgs, rmvMsgCount);
        }

        // per 10000 lines
        if (rmvMsgCount >= BULK_INSERT_COUNT || rmvRec == null) {

            int retCnt = S21FastTBLAccessor.removePhysical(rmvMsgs);

            // if passed records' count and return count don't match, error
            if (retCnt != rmvMsgCount) {
                throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
            }
            // initialize
            rmvMsgCount = 0;
            rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        }
    }

    /**
     * <pre>
     *  Store Target Transactions in AJE AR Reclass Interface.
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private boolean doEntryToAjeArReclsIntfc() {

        // ** Get Target Transactions in AR_TRX_BAL. **        
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("ajeIntfcTpCd", AJE_INTFC_TP.AR_RECLASS);
        queryParam.put("jrnlCpltFlg", FLG_ON_Y);
        queryParam.put("trxRsnCdRcls", TRX_RSN.AR_RECLASS);
        queryParam.put("trxRsnCdRvsl", TRX_RSN.AR_RECLASS_REVERSAL);
        queryParam.put("procDt", procDt);
        queryParam.put("flgY", FLG_ON_Y);
        queryParam.put("rptCondConstValRsUnid", PARAM_VAL_RPT_COND_CONST_VAL_RS_UNID);
        queryParam.put("rptCondConstValRsUnapl", PARAM_VAL_RPT_COND_CONST_VAL_RS_UNAPL);
        queryParam.put("rptCondConstValRsCash", PARAM_VAL_RPT_COND_CONST_VAL_RS_CASH);
        queryParam.put("rptCondConstValRsOacc", PARAM_VAL_RPT_COND_CONST_VAL_RS_OACC);
        queryParam.put("coaAcctCdDef", PARAM_VAL_COA_ACCT_CD_DEF);
        queryParam.put("coaAfflCdDef", PARAM_VAL_COA_AFFL_CD_DEF);
        queryParam.put("coaCcCdDef", PARAM_VAL_COA_CC_CD_DEF);
        queryParam.put("rptCondConstGrpId", PARAM_VAL_RPT_COND_CONST_GRP_ID);
        queryParam.put("rptCondConstCdRcptIdentified", PARAM_VAL_RPT_COND_CONST_CD_RCPT_IDENTIFIED);

        Boolean rtn = (Boolean) ssmBatchClient.queryObject("getArReclsNotJrnlized", queryParam, new AjeArReclsIntfcWrkHandler());
        if (rtn) {
            rtn = (Boolean) ssmBatchClient.queryObject("getArReclsIntfc", queryParam, new AjeArReclsIntfcHandler());
        }

        return rtn;
    }

    /**
     * <pre>
     *  AJE AR Reclass.
     *  Store Target Transactions in AJE AR Reclass Interface Work.
     * </pre>
     */
    private class AjeArReclsIntfcWrkHandler extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rsArReclsNotJrnlized) throws SQLException {

            Boolean rtn = doProcessLoop(rsArReclsNotJrnlized);

            return rtn;
        }

        protected boolean doProcessLoop(ResultSet rsArReclsNotJrnlized) {
            try {

                while (rsArReclsNotJrnlized.next()) {

                    AJE_AR_RECLS_INTFCTMsg ajeArReclsIntfc = new AJE_AR_RECLS_INTFCTMsg();
                    setValue(ajeArReclsIntfc.glblCmpyCd, rsArReclsNotJrnlized.getString(GLBL_CMPY_CD));

                    BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_AR_RECLS_INTFC_SQ);
                    setValue(ajeArReclsIntfc.ajeArReclsIntfcPk, seqNum);

                    setValue(ajeArReclsIntfc.arTrxBalPk, rsArReclsNotJrnlized.getBigDecimal(AR_TRX_BAL_PK));
                    setValue(ajeArReclsIntfc.arTrxNum, rsArReclsNotJrnlized.getString(AR_TRX_NUM));
                    setValue(ajeArReclsIntfc.arTrxTpCd, rsArReclsNotJrnlized.getString(AR_TRX_TP_CD));
                    setValue(ajeArReclsIntfc.procDt, rsArReclsNotJrnlized.getString(PROC_DT));

                    setValue(ajeArReclsIntfc.dealRmngBalGrsAmt, rsArReclsNotJrnlized.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT));
                    setValue(ajeArReclsIntfc.funcRmngBalGrsAmt, rsArReclsNotJrnlized.getBigDecimal(FUNC_RMNG_BAL_GRS_AMT));
                    setValue(ajeArReclsIntfc.dealCcyCd, rsArReclsNotJrnlized.getString(DEAL_CCY_CD));
                    setValue(ajeArReclsIntfc.funcCcyCd, rsArReclsNotJrnlized.getString(FUNC_CCY_CD));
                    setValue(ajeArReclsIntfc.sysSrcCd, SYS_SRC.S21_ACCOUNTING_AR);
                    setValue(ajeArReclsIntfc.coaAcctCd, rsArReclsNotJrnlized.getString(COA_ACCT_CD));
                    setValue(ajeArReclsIntfc.coaAfflCd, rsArReclsNotJrnlized.getString(COA_AFFL_CD));
                    setValue(ajeArReclsIntfc.billToCustAcctCd, rsArReclsNotJrnlized.getString(BILL_TO_CUST_ACCT_CD));

                    createAjeArReclsIntfcWrk(ajeArReclsIntfc);
                }

                if (intfcWrkMsgCount != 0) {
                    createAjeArReclsIntfcWrk(null);
                }

            } catch (EZDAbendException exEz) {  // null exception
                errMsg = exEz.getMessage();
                return Boolean.FALSE;
            } catch (S21AbendException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (SQLException sqlEx) {
                errMsg = sqlEx.getMessage();
                return Boolean.FALSE;
            } catch (NFACommonJrnlEntry.JrnlCommonException exJ) {
                errMsg = exJ.getMessage();
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }

        /**
         * @param EZDTMsg AJE AR Reclassn Interface.
         * @throws JrnlCommonException JrnlCommonException
         */
        protected void createAjeArReclsIntfcWrk(EZDTMsg ajeArReclsIntfc) throws NFACommonJrnlEntry.JrnlCommonException {

            if (ajeArReclsIntfc != null) {
                intfcWrkMsgs[intfcWrkMsgCount] = ajeArReclsIntfc;
                intfcWrkMsgCount += 1;

            } else {  // array may be not full
                intfcWrkMsgs = commonJrnlEntry.changeArraySize(intfcWrkMsgs, intfcWrkMsgCount);
            }

            // per 10000 lines
            if (intfcWrkMsgCount >= BULK_INSERT_COUNT || ajeArReclsIntfc == null) {

                int retCnt = S21FastTBLAccessor.insert(intfcWrkMsgs);

                // if passed records' count and return count don't match, error
                if (retCnt != intfcWrkMsgCount) {
                    throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
                }

                // initialize
                intfcWrkMsgCount = 0;
                intfcWrkMsgs = new EZDTMsg[BULK_INSERT_COUNT];
            }
        }
    }

    /**
     * <pre>
     *  AJE AR Reclass .
     *  Store Target Transactions in AJE AR Reclass Interface.
     * </pre>
     */
    private class AjeArReclsIntfcHandler extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rsArReclsNotJrnlized) throws SQLException {

            Boolean rtn = doProcessLoop(rsArReclsNotJrnlized);

            return rtn;
        }

        protected boolean doProcessLoop(ResultSet rs) {
            try {

                while (rs.next()) {

                    for (int i = 0; i <= 1; i++) {
                        AJE_AR_RECLS_INTFCTMsg ajeArReclsIntfc = new AJE_AR_RECLS_INTFCTMsg();

                        setValue(ajeArReclsIntfc.glblCmpyCd, glblCmpyCd);

                        BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_AR_RECLS_INTFC_SQ);
                        setValue(ajeArReclsIntfc.ajeArReclsIntfcPk, seqNum);

                        setValue(ajeArReclsIntfc.arTrxNum, "*");
                        setValue(ajeArReclsIntfc.procDt, procDt);
                        setValue(ajeArReclsIntfc.dealRmngBalGrsAmt, rs.getBigDecimal(DEAL_RMNG_BAL_GRS_AMT));
                        setValue(ajeArReclsIntfc.funcRmngBalGrsAmt, rs.getBigDecimal(FUNC_RMNG_BAL_GRS_AMT));
                        setValue(ajeArReclsIntfc.dealCcyCd, rs.getString(DEAL_CCY_CD));
                        setValue(ajeArReclsIntfc.funcCcyCd, rs.getString(FUNC_CCY_CD));
                        setValue(ajeArReclsIntfc.sysSrcCd, SYS_SRC.S21_ACCOUNTING_AR);
                        setValue(ajeArReclsIntfc.coaAcctCd, rs.getString(COA_ACCT_CD));
                        setValue(ajeArReclsIntfc.coaAfflCd, rs.getString(COA_AFFL_CD));
                        setValue(ajeArReclsIntfc.trxCd, TRX.AR_RECLASS);
                        if (i == 0) {
                            setValue(ajeArReclsIntfc.glDt, procDt);
                            setValue(ajeArReclsIntfc.trxRsnCd, TRX_RSN.AR_RECLASS);
                        } else {
                            setValue(ajeArReclsIntfc.glDt, addMonths(procDt, 1).substring(0, 6) + "01" );
                            setValue(ajeArReclsIntfc.trxRsnCd, TRX_RSN.AR_RECLASS_REVERSAL);
                        }

                        createAjeArReclsIntfc(ajeArReclsIntfc);
                    }
                }

                if (intfcMsgCount != 0) {
                    createAjeArReclsIntfc(null);
                }

            } catch (EZDAbendException exEz) {  // null exception
                errMsg = exEz.getMessage();
                return Boolean.FALSE;
            } catch (S21AbendException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (SQLException sqlEx) {
                errMsg = sqlEx.getMessage();
                return Boolean.FALSE;
            } catch (NFACommonJrnlEntry.JrnlCommonException exJ) {
                errMsg = exJ.getMessage();
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }

        /**
         * @param EZDTMsg AJE AR Reclass Interface.
         * @throws JrnlCommonException JrnlCommonException
         */
        protected void createAjeArReclsIntfc(EZDTMsg ajeArReclsIntfc) throws NFACommonJrnlEntry.JrnlCommonException {

            if (ajeArReclsIntfc != null) {
                intfcMsgs[intfcMsgCount] = ajeArReclsIntfc;
                intfcMsgCount += 1;

            } else {  // array may be not full
                intfcMsgs = commonJrnlEntry.changeArraySize(intfcMsgs, intfcMsgCount);
            }

            // per 10000 lines
            if (intfcMsgCount >= BULK_INSERT_COUNT || ajeArReclsIntfc == null) {

                int retCnt = S21FastTBLAccessor.insert(intfcMsgs);

                // if passed records' count and return count don't match, error
                if (retCnt != intfcMsgCount) {
                    throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
                }
                commitCount += intfcMsgCount;
                // initialize
                intfcMsgCount = 0;
                intfcMsgs = new EZDTMsg[BULK_INSERT_COUNT];
            }
        }
    }

    
    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, 0, commitCount);

        S21InfoLogOutput.println("termRoutine Method End");

    }

    /**
     * addMonths
     * @param String date
     * @param int addMth
     * @return String
     */
    private String addMonths(String date, int addMth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)) - 1, 01);

        calendar.add(Calendar.MONTH, addMth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String addMthYrMth = dateFormat.format(calendar.getTime());

        return addMthYrMth;
    }

}
