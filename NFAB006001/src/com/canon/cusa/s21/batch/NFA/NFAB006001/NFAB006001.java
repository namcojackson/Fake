package com.canon.cusa.s21.batch.NFA.NFAB006001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import parts.common.EZDValidatorException;
import business.db.AJE_INTFC_CTRLTMsg;
import business.db.JRNL_ENTRYTMsg;
import business.parts.NFAC000101PMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
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
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_DEF_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.fujitsu.uji.ext.CommonCallFactory;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * This batch journalizes front transactions of LOAN DEPRECIATION.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/20/2009   CSAI            N.Sasaki        Create          N/A
 * 08/09/2011   CSAI            K.Uramori       Update          Set RCPT_NUM to SO_NUM
 * 10/15/2015   CSAI            K.Uramori       Update          CSA Modification
 * 09/28/2017   CITS            Y.Fujii         Update          QC#19408
 * 11/27/2017   CITS            T.Wada          Update          QC#21258
 * 11/07/2018   Hitachi         E.Kameishi      Update          QC#29066
 * </pre>
 */
public class NFAB006001 extends S21BatchMain implements NFAB006001Constant, ZYPConstant, NFACommonJrnlEntryConstant {
    /** SSM Batch Clinent */
    private S21SsmBatchClient ssmBatchClient;

    /** User Profile */
    private S21UserProfileService profile;

    /** AJE Program Id */
    private String ajePgmId;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Count */
    private int commitCount = 0;
    
    /** Error Count */
    private int errorCount = 0;

    /** Termination Code */
    private TERM_CD termCd;

    /** Error message */
    private String errMsg = "";

    /** Process Date */
    private static String procDt = "";

    /** Array of TMsg */
    private EZDTMsg[] jrnlMsgs;
    /** Array of TMsg */
    private EZDTMsg[] ctrlMsgs;
    /** Array of TMsg */
    private EZDTMsg[] rmvMsgs;

    /** Size of Array */
    private int jrnlMsgCount = 0;
    /** Size of Array */
    private int ctrlMsgCount = 0;
    /** Size of Array */
    private int rmvMsgCount = 0;

    /**  Journal Entry Common Module */
    private NFACommonJrnlEntry common = new NFACommonJrnlEntry();

    /** QC#21258 */
    private String ajeZeroCratFlg = "";
    
    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        new NFAB006001().executeBatch(NFAB006001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        this.termCd = TERM_CD.NORMAL_END;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();

        // Reference Item in AJE_INTFC_CTRL.
        this.ajePgmId = this.getClass().getSimpleName();

        procDt = S21BatchUtil.getInputParam1();
        
        // if it's set by the parameter, it should not be overwritten
        if (procDt == null || procDt.equals("")) {
            procDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        }

        // initialize
        jrnlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        ctrlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];

        // QC#21258
        ajeZeroCratFlg = ZYPCodeDataUtil.getVarCharConstValue("AJE_ZERO_CRAT_FLG_AR", this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(ajeZeroCratFlg)) {
            ajeZeroCratFlg = ZYPConstant.FLG_ON_Y;
        }

        S21InfoLogOutput.println("initRoutine Method End");
    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        List<Map> listTrxPtrn = getTrxPtrn();

        
        if (listTrxPtrn.size() == 0) {
            S21InfoLogOutput.println(NFAM0036I, new String[] {MSG_PARAM });
        } else {

            List<Map> listNotJrnlized = common.getAjeIntfcKeyListNotJrnlized(this.glblCmpyCd, AJE_INTFC_TP_CD_VAL);

            Boolean checkRemoveProcess = Boolean.TRUE;
            if (listNotJrnlized.size() > 0) {
                checkRemoveProcess = removeIntFcNotJrnlized(listNotJrnlized);

                if (checkRemoveProcess) {
                    commit();
                } else {
                    S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg});
                    rollback();
                    throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
                }
            }

            if (checkRemoveProcess) {
                List<Map> listAjePtrn = common.getAjePtrn(listTrxPtrn, this.glblCmpyCd, AJE_INTFC_TP_CD_VAL);

                if (listAjePtrn.size() == 0) {
                    S21InfoLogOutput.println(NFACommonJrnlEntryConstant.NFAM0037E);
                } else {
                    Boolean checkJournalProc = doEntryToJournalEntry(listAjePtrn);

                    if (checkJournalProc != Boolean.TRUE) {
                        S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
                        rollback();
                        throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
                    } else {
                        commit();
                    }
                }
            }
        }
        S21InfoLogOutput.println("mainRoutine Method End");
    }

    /**
     * <pre>
     *  Get All Transaction Patterns in AJE Loan Depreciation Interface.
     * </pre>
     * 
     * @param
     */
    @SuppressWarnings("unchecked")
    private List<Map> getTrxPtrn() {

        // ** Get All Transaction Pattern in AJE Loan Depreciation
        // Interface. **
        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
        map.put("jrnlCpltFlg", FLG_ON_Y);

        List<Map> listTrxPtrn = (List<Map>) ssmBatchClient.queryObjectList("getTrxPtrn", map);

        return listTrxPtrn;
    }

    /**
     * <pre>
     *  Remove AJE Interface Control that is not journalized.
     *  @return boolean true: OK  false: NG
     * </pre>
     */
    private boolean removeIntFcNotJrnlized(List<Map> listNotJrnlized) {
        try {
        for (Map<String, Object> map : listNotJrnlized) {

            AJE_INTFC_CTRLTMsg tMsgIntFctrl = new AJE_INTFC_CTRLTMsg();

            String glblCmpyCdStr = (String) map.get(GLBL_CMPY_CD);
            String ajeIntfcTpCdStr = (String) map.get(AJE_INTFC_TP_CD);
            BigDecimal ajeIntfcPkNum = new BigDecimal(map.get(AJE_INTFC_PK).toString());

            common.setFieldValue(tMsgIntFctrl, "glblCmpyCd", glblCmpyCdStr);
            common.setFieldValue(tMsgIntFctrl, "ajeIntfcTpCd", ajeIntfcTpCdStr);
            common.setFieldValue(tMsgIntFctrl, "ajeIntfcPk", ajeIntfcPkNum);

            removeAjeIntfcCtrlNotJrnlizedHelper(tMsgIntFctrl);
        }

        if (rmvMsgCount != 0) {
            removeAjeIntfcCtrlNotJrnlizedHelper(null);
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
    private void removeAjeIntfcCtrlNotJrnlizedHelper(EZDTMsg rmvRec) throws NFACommonJrnlEntry.JrnlCommonException {

        if (rmvRec != null) {
            rmvMsgs[rmvMsgCount] = rmvRec;
            rmvMsgCount += 1;
        } else {
            rmvMsgs = common.changeArraySize(rmvMsgs, rmvMsgCount);
        }

        // per 10000 lines
        if (rmvMsgCount >= BULK_INSERT_COUNT || rmvRec == null) {

            int retCnt = S21FastTBLAccessor.removePhysical(rmvMsgs);

            // if passed records' count and return count don't match, error
            if (retCnt != rmvMsgCount) {
                throw common.new JrnlCommonException(ZZBM0074E);
            }
            // initialize
            rmvMsgCount = 0;
            rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        }
    }

    /**
     * <pre>
     *  Get Target Transactions in AJE Loan Depreciation Interface and Journalize.
     * </pre>
     * 
     * @param List<Map> Target AJE Pattern List
     */
    @SuppressWarnings("unchecked")
    private Boolean doEntryToJournalEntry(List<Map> ajePtrnList) {

        // ** Get Target Transactions in AJE Loan Depreciation
        // Interface. **
        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
        map.put("jrnlCpltFlg", FLG_ON_Y);
        map.put("thisMonth", ZYPDateUtil.DateFormatter(procDt, "yyyyMMdd", "yyyyMM"));
        // START 2018/11/07 E.Kameishi [QC#29066,ADD]
        map.put("ded", AR_TRX_TP.DEDUCTION);
        map.put("lf", "LF");
        map.put("non-lf", "NON-LF");
        // END 2018/11/07 E.Kameishi [QC#29066,ADD]
        
        // last month
        Calendar cal = Calendar.getInstance();

        cal.set(Integer.valueOf(ZYPDateUtil.DateFormatter(procDt, "yyyyMMdd", "yyyy")),
                Integer.valueOf(ZYPDateUtil.DateFormatter(procDt, "yyyyMMdd", "MM")) - 1,
                Integer.valueOf(ZYPDateUtil.DateFormatter(procDt, "yyyyMMdd", "dd")));

        // minus one month (send previous month fixed data)
        cal.add(Calendar.MONTH, -1);

        // format
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if (month != null && month.length() == 1) {
            month = "0" + month;
        }
        map.put("lastMonth", String.valueOf(cal.get(Calendar.YEAR)) + month);
        
        //---- start add QC#12217 2016/07/20
        map.put("refund", CM_DEF_ACCT.REFUND_COA);
        //---- end QC#12217 2016/07/20
        
        Boolean checkJournalEntry = (Boolean) ssmBatchClient.queryObject("getAjeArIntfcNotJrnlized", map, new JrnlEntryHandler(ajePtrnList));
        return checkJournalEntry;
    }

    /**
     * <pre>
     *  Journal Entry Handler.
     *  Jourlize all transacntions with Loan Depreciation and update the result in AJE Interface Control Table. 
     *  CoA Segment is derived from AJE Pattern or masters (Org, Merchandise or Customer).
     * </pre>
     */
    private final class JrnlEntryHandler extends S21SsmBooleanResultSetHandlerSupport {

        /** AJE Pattern List */
        private List<Map> ajePtrnList;

        /** Error Message Id **/
        private String errMsgId = NFACommonJrnlEntryConstant.DEF_ERROR_MSG_ID;
        /** Error Message text **/
        private String errMsgTxt = NFACommonJrnlEntryConstant.DEF_ERROR_MSG_TXT;

        private JrnlEntryHandler(List<Map> ajePtrnList) {
            // AJE Pattern
            this.ajePtrnList = ajePtrnList;

            System.out.println("***************** JrnlEntryHandler ***********************");
        }

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
            // List to store JRNL_ENTRYTMsg to be inserted to JRNL_ENTRY
            List<JRNL_ENTRYTMsg> lstJrnlEntry = new ArrayList<JRNL_ENTRYTMsg>();

            while (rs.next()) {

                // initialize
                lstJrnlEntry.clear();

                // Check Flag Segment
                boolean notError = true;
                List<NFAC000101PMsg> pMsgList = new ArrayList<NFAC000101PMsg>();
                
                // if GL_DT is null, that means GL_DT is too old
                // checkCoaSegmentDetermination false
                if (rs.getString(GL_DT) == null) {
                    errMsgId = NFAM0076E;
                    errMsgTxt = NFAM0076E_MSG;  // GL_DT is not in open journalizable period.
                    notError = false;
                } else {
                
                    // AJE ID
                    String ajeId = common.generateAjeId(rs.getString(SYS_SRC_CD), rs.getString(TRX_CD), rs.getString(TRX_RSN_CD));
    
                    // Refine AJE Pattern List by AJE ID and Indicator
                    pMsgList = common.getAJEPtrnByAjeIdAndIndTp(ajeId, rs, ajePtrnList);
    
                    // if no pattern is found, set
                    // checkCoaSegmentDetermination false
                    if (pMsgList.isEmpty()) {
                        errMsgId = NFACommonJrnlEntryConstant.NFAM0037E;
                        errMsgTxt = NFACommonJrnlEntryConstant.NO_AJE_PTRN_ERROR_MSG_TXT;
                        notError = false;
                    }
                }

                for (NFAC000101PMsg pMsg : pMsgList) {

                    BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(JRNL_ENTRY_SQ);

                    JRNL_ENTRYTMsg tMsgJrnEnt = new JRNL_ENTRYTMsg();
                    try {

                        // From Interface
                        common.setFieldValue(tMsgJrnEnt, "jrnlEntryPk", seqNum);

                        setPartOfJrnlEntryValues(tMsgJrnEnt, rs, pMsg);
                        CoaSegment coa = CoaSegment.DEF;
                        
                        // for DR
                        coa = setValuesByCrDr("dr", tMsgJrnEnt, rs, pMsg);
                        if (coa != CoaSegment.DEF) {
                        	errMsgId = NFACommonJrnlEntryConstant.COA_ERROR_MSG_ID;
                            errMsgTxt = NFACommonJrnlEntryConstant.COA_ERROR_MSG_TXT.replace("@", common.getCoAMessage(coa));
                        	notError = false;
                            break;
                        }

                        // for CR
                        coa = setValuesByCrDr("cr", tMsgJrnEnt, rs, pMsg);
                        if (coa != CoaSegment.DEF) {
                        	errMsgId = NFACommonJrnlEntryConstant.COA_ERROR_MSG_ID;
                            errMsgTxt = NFACommonJrnlEntryConstant.COA_ERROR_MSG_TXT.replace("@", common.getCoAMessage(coa));
                        	notError = false;
                            break;
                        }

                        /** CoA Currency */
                        common.setFieldValue(tMsgJrnEnt, "dealCcyCd", rs.getString(DEAL_CCY_CD));
                        common.setFieldValue(tMsgJrnEnt, "funcCcyCd", rs.getString(FUNC_CCY_CD));
                        common.setFieldValue(tMsgJrnEnt, "orclCcyCd", rs.getString(ORCL_CCY_CD));

                        /** Exchange Rate */
                        common.setFieldValue(tMsgJrnEnt, "exchRate", rs.getBigDecimal(EXCH_RATE));
                        common.setFieldValue(tMsgJrnEnt, "acctArthTpCd", rs.getString(ACCT_ARTH_TP_CD));

                        /** Quantity */
                        common.setFieldValue(tMsgJrnEnt, "jrnlQty", AR_QTY);

                        /** Amount */
                        //---- start 2016/05/24 Flip amount by the flag in AJE pattern
                        String flipFlg = pMsg.jrnlAmtFlipFlg.getValue();
                        
                        BigDecimal multi = BigDecimal.ONE;
                        
                        if (FLG_ON_Y.equals(flipFlg)) {
                            multi = multi.negate();
                        }
                        
                        common.setFieldValue(tMsgJrnEnt, "jrnlDealDrAmt", rs.getBigDecimal(DEAL_ACCT_AMT).multiply(multi));
                        common.setFieldValue(tMsgJrnEnt, "jrnlFuncDrAmt", rs.getBigDecimal(FUNC_ACCT_AMT).multiply(multi));

                        common.setFieldValue(tMsgJrnEnt, "jrnlDealCrAmt", rs.getBigDecimal(DEAL_ACCT_AMT).multiply(multi));
                        common.setFieldValue(tMsgJrnEnt, "jrnlFuncCrAmt", rs.getBigDecimal(FUNC_ACCT_AMT).multiply(multi));
                        //---- end 2016/05/24

                        // additional info
                        
                        common.setFieldValue(tMsgJrnEnt, "billToCustCd", rs.getString(BILL_TO_CUST_CD));
                        
                        
                        common.setFieldValue(tMsgJrnEnt, "orclRef10Txt", ORCL_REF_10_TXT_PREFIX + pMsg.ajeId.getValue());
                        tMsgJrnEnt.orclAttrb11Txt.clear();

                        common.setFieldValue(tMsgJrnEnt, "ajeIntfcCmntTxt", rs.getString(AJE_INTFC_CMNT_TXT));

                        // COA_PROD_CD
                        common.setFieldValue(tMsgJrnEnt, "coaProdCd", rs.getString(COA_PROD_CD));
                        // TOC_CD
                        common.setFieldValue(tMsgJrnEnt, "tocCd", rs.getString(TOC_CD));
                        // AJE_INV_NUM
                        common.setFieldValue(tMsgJrnEnt, "ajeInvNum", rs.getString(AR_TRX_NUM));
                         
                        
                      //---- start add new fields for CSA 2016/01/19
                        setValue(tMsgJrnEnt.rcptChkNum, rs.getString(RCPT_CHK_NUM));
                        setValue(tMsgJrnEnt.dsAcctNum, rs.getString(DS_ACCT_NUM));
                        setValue(tMsgJrnEnt.rcptNum, rs.getString(RCPT_NUM));
                        setValue(tMsgJrnEnt.arTrxNum, rs.getString(AR_TRX_NUM));
                        
                        setValue(tMsgJrnEnt.jrnlEntryFirstRefTxt, rs.getString(DS_INV_TP_CD));
                        setValue(tMsgJrnEnt.jrnlEntryFirstAttrbNm, DISP_DS_INV_TP_CD);
                        
                        setValue(tMsgJrnEnt.jrnlEntryScdRefTxt, rs.getString(DR_AR_RCPT_SRC_CD));
                        setValue(tMsgJrnEnt.jrnlEntryScdAttrbNm, DISP_DR_AR_RCPT_SRC_CD);
                        
                        setValue(tMsgJrnEnt.jrnlEntryThirdRefTxt, rs.getString(CR_AR_RCPT_SRC_CD));
                        setValue(tMsgJrnEnt.jrnlEntryThirdAttrbNm, DISP_CR_AR_RCPT_SRC_CD);
                        
                        setValue(tMsgJrnEnt.jrnlEntryFrthRefTxt, rs.getString(AR_TRX_NUM));
                        setValue(tMsgJrnEnt.jrnlEntryFrthAttrbNm, DISP_AR_TRX_NUM);
                        
                        setValue(tMsgJrnEnt.jrnlEntryFifthRefTxt, rs.getString(AR_ADJ_NUM));
                        setValue(tMsgJrnEnt.jrnlEntryFifthAttrbNm, DISP_AR_ADJ_NUM);
                        
                        setValue(tMsgJrnEnt.jrnlEntryRefTxt, common.getJrnlEntryRefTxt(tMsgJrnEnt));
                        //---- end 2016/01/19
                        
                        
                    } catch (EZDValidatorException exVal) {
                        notError = false;
                        errMsgId = NFACommonJrnlEntryConstant.COA_ERROR_MSG_ID;
                        errMsgTxt = exVal.getMessage();
                        break;

                    } catch (EZDAbendException exEz) {
                        // null exception
                        notError = false;
                        errMsgId = NFACommonJrnlEntryConstant.NULL_ERROR_MSG_ID;
                        errMsgTxt = exEz.getMessage();
                        break;

                    } catch (SQLException sqlEx) {
                        errMsg = sqlEx.getMessage();
                        return Boolean.FALSE;
                    }

                    // Store into the list
                    if (notError) {
                        lstJrnlEntry.add(tMsgJrnEnt);
                    }

                } // for

                insertIntoJrnlOrCtrl(notError, rs, lstJrnlEntry);

            } // while

            if (jrnlMsgCount != 0) {
                createJrnlEntry(null);
            }
            if (ctrlMsgCount != 0) {
                createAjeIntfcCtrl(null);
            }

            } catch (NFACommonJrnlEntry.JrnlCommonException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }

        /**
         * <pre>
         *  Create Journal Entry or AJE Interface control.
         * </pre>
         * @param checkCoaSegmentDetermination True: OK  False: NG
         * @param rs ResultSet of transaction
         * @param errMsgId  error message Id if error exists
         * @param errMsgTxt error message text if error exists
         * @param lstJrnlEntry list of journal entry to be inserted
         * @throws SQLException SQL Exception
         */
        private void insertIntoJrnlOrCtrl(boolean checkCoaSegmentDetermination, ResultSet rs,
                List<JRNL_ENTRYTMsg> lstJrnlEntry) throws SQLException, NFACommonJrnlEntry.JrnlCommonException {

            // CoA Segment Setting is complete or not.
            if (checkCoaSegmentDetermination) {
                // insert into jrnl entry
                for (JRNL_ENTRYTMsg jrnlEntryRec : lstJrnlEntry) {
                    
                   //---- start add 2016/05/24 when amount is zero, not to create journal entry
                	// QC#21258
                    //if (BigDecimal.ZERO.compareTo(jrnlEntryRec.jrnlDealDrAmt.getValue()) == 0) {
                    if (BigDecimal.ZERO.compareTo(jrnlEntryRec.jrnlDealDrAmt.getValue()) == 0 && !ZYPConstant.FLG_ON_Y.equals(ajeZeroCratFlg)) {
                        continue;
                    }
                    //----end 2016/05/24
                    
                    createJrnlEntry(jrnlEntryRec);
                }

            }

            // Store the result in AJE_INTFC_CTRL.
            AJE_INTFC_CTRLTMsg ajeIntfcCtrl = new AJE_INTFC_CTRLTMsg();
            common.setFieldValue(ajeIntfcCtrl, "glblCmpyCd", rs.getString(GLBL_CMPY_CD));
            common.setFieldValue(ajeIntfcCtrl, "ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
            common.setFieldValue(ajeIntfcCtrl, "ajeIntfcPk", rs.getBigDecimal(AJE_INTFC_PK));
            common.setFieldValue(ajeIntfcCtrl, "procDt", procDt);
            common.setFieldValue(ajeIntfcCtrl, "ajePgmId", ajePgmId);
            common.setFieldValue(ajeIntfcCtrl, "sysSrcCd", rs.getString(SYS_SRC_CD));
            common.setFieldValue(ajeIntfcCtrl, "trxCd", rs.getString(TRX_CD));
            common.setFieldValue(ajeIntfcCtrl, "trxRsnCd", rs.getString(TRX_RSN_CD));
            
            if (!checkCoaSegmentDetermination) {

                common.setFieldValue(ajeIntfcCtrl, "jrnlCpltFlg", FLG_OFF_N);
                common.setFieldValue(ajeIntfcCtrl, "ajeErrMsgId", errMsgId);
                common.setFieldValue(ajeIntfcCtrl, "ajeErrMsgTxt", errMsgTxt);

                errorCount += 1;
                
                createAjeIntfcCtrl(ajeIntfcCtrl);

            } else {
                common.setFieldValue(ajeIntfcCtrl, "jrnlCpltFlg", FLG_ON_Y);

                createAjeIntfcCtrl(ajeIntfcCtrl);
            }
        }

        /**
         * <pre>
         *  Set Dr or Cr values of COA segment depends on parameter
         * </pre>
         * @param crOrDr Cr or Dr
         * @param jrnlEntry TMsg of Journal Entry that is being generated
         * @param rs result set of interface table
         * @param rsAjePtrnList AJE pattern list
         * @throws SQLException SQL Exception
         * @throws EZDValidatorException
         */
        private CoaSegment setValuesByCrDr(String crOrDr, JRNL_ENTRYTMsg jrnlEntry, ResultSet rs, NFAC000101PMsg rsAjePtrnList)
        throws SQLException, EZDValidatorException {
            
            /** CoA Company */
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.CMPY, crOrDr, rsAjePtrnList, rs, null)) {	
            	return CoaSegment.CMPY;
            }
            
            /** CoA Branch */
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.BR, crOrDr, rsAjePtrnList, rs, null)) {
            	return CoaSegment.BR;
            }
            
            /** CoA Cos tCenter */
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.CC, crOrDr, rsAjePtrnList, rs, null)) {
            	return CoaSegment.CC;
            }
            
            /** CoA Account */
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.ACCT, crOrDr, rsAjePtrnList, rs, null)) {
            	return CoaSegment.ACCT;
            }
            
            /** CoA Prod */
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.PROD, crOrDr, rsAjePtrnList, rs, null)) {
            	return CoaSegment.PROD;
            }
            
            /** CoA Channel */
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.CH, crOrDr, rsAjePtrnList, rs, null)) {
            	return CoaSegment.CH;
            }
            
            /** CoA Affiliate */
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.AFFL, crOrDr, rsAjePtrnList, rs, null)) {
            	return CoaSegment.AFFL;
            }
            
            /** CoA Project */
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.PROJ, crOrDr, rsAjePtrnList, rs, null)) {
            	return CoaSegment.PROJ;
            }
            
            /** CoA Extension */
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.EXTN, crOrDr, rsAjePtrnList, rs, null)) {
            	return CoaSegment.EXTN;
            }
            
            return CoaSegment.DEF;

        }

        /**
         * <pre>
         *  This is helper function of doProcessQueryResult to make the function smaller
         * </pre>
         * @param tMsgJrnEnt TMsg of Journal Entry that is being generated
         * @param rs result set of interface table
         * @param pMsg AJE pattern list
         * @throws SQLException SQL Exception
         */
        private void setPartOfJrnlEntryValues(JRNL_ENTRYTMsg tMsgJrnEnt, ResultSet rs, NFAC000101PMsg pMsg)
        throws SQLException {
            common.setFieldValue(tMsgJrnEnt, "glblCmpyCd", rs.getString(GLBL_CMPY_CD));
            common.setFieldValue(tMsgJrnEnt, "ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
            common.setFieldValue(tMsgJrnEnt, "ajeIntfcPk", rs.getBigDecimal(AJE_INTFC_PK));
            common.setFieldValue(tMsgJrnEnt, "procDt", procDt);
            common.setFieldValue(tMsgJrnEnt, "glDt", rs.getString(GL_DT));
            common.setFieldValue(tMsgJrnEnt, "ajeId", pMsg.ajeId.getValue());
            common.setFieldValue(tMsgJrnEnt, "sysSrcCd", rs.getString(SYS_SRC_CD));
            common.setFieldValue(tMsgJrnEnt, "sysSrcNm", rs.getString(SYS_SRC_NM));
            common.setFieldValue(tMsgJrnEnt, "trxCd", rs.getString(TRX_CD));
            common.setFieldValue(tMsgJrnEnt, "trxNm", rs.getString(TRX_NM));
            common.setFieldValue(tMsgJrnEnt, "trxRsnCd", rs.getString(TRX_RSN_CD));
            common.setFieldValue(tMsgJrnEnt, "trxRsnNm", rs.getString(TRX_RSN_NM));
            common.setFieldValue(tMsgJrnEnt, "ajePtrnIndTpCd_01", pMsg.ajePtrnIndTpCd_01.getValue());
            common.setFieldValue(tMsgJrnEnt, "ajePtrnIndTpNm_01", pMsg.ajePtrnIndTpNm_01.getValue());
            common.setFieldValue(tMsgJrnEnt, "ajePtrnActlCd_01", pMsg.ajePtrnActlCd_01.getValue());
            common.setFieldValue(tMsgJrnEnt, "ajePtrnActlNm_01", pMsg.ajePtrnActlNm_01.getValue());
            common.setFieldValue(tMsgJrnEnt, "ajePtrnIndTpCd_02", pMsg.ajePtrnIndTpCd_02.getValue());
            common.setFieldValue(tMsgJrnEnt, "ajePtrnIndTpNm_02", pMsg.ajePtrnIndTpNm_02.getValue());
            common.setFieldValue(tMsgJrnEnt, "ajePtrnActlCd_02", pMsg.ajePtrnActlCd_02.getValue());
            common.setFieldValue(tMsgJrnEnt, "ajePtrnActlNm_02", pMsg.ajePtrnActlNm_02.getValue());
            common.setFieldValue(tMsgJrnEnt, "ajePtrnIndTpCd_03", pMsg.ajePtrnIndTpCd_03.getValue());
            common.setFieldValue(tMsgJrnEnt, "ajePtrnIndTpNm_03", pMsg.ajePtrnIndTpNm_03.getValue());
            common.setFieldValue(tMsgJrnEnt, "ajePtrnActlCd_03", pMsg.ajePtrnActlCd_03.getValue());
            common.setFieldValue(tMsgJrnEnt, "ajePtrnActlNm_03", pMsg.ajePtrnActlNm_03.getValue());
            common.setFieldValue(tMsgJrnEnt, "jrnlSrcCd", pMsg.jrnlSrcCd.getValue());
            common.setFieldValue(tMsgJrnEnt, "jrnlSrcNm", pMsg.jrnlSrcNm.getValue());
            common.setFieldValue(tMsgJrnEnt, "jrnlCatgCd", pMsg.jrnlCatgCd.getValue());
            common.setFieldValue(tMsgJrnEnt, "jrnlCatgNm", pMsg.jrnlCatgNm.getValue());
            common.setFieldValue(tMsgJrnEnt, "ajeLineIdxNum", pMsg.ajeLineIdxNum.getValue());
            common.setFieldValue(tMsgJrnEnt, "glSendCpltFlg", getGlSendCpltFlg(rs.getString(TRX_CD), rs.getString(TRX_RSN_CD)));
        }

        /**
         * <pre>
         *  Returns "Y" for A/R reclass transactions 
         * </pre>
         * 
         * @param trxCd Transaction Code
         * @param trxRsnCd Transaction Reason Code
         * @return Y/N
         */
        private String getGlSendCpltFlg(String trxCd, String trxRsnCd) {

            if ((ZYPCommonFunc.hasValue(trxCd) && trxCd.equals(TRX_CD_AR_RECLASS)) && (ZYPCommonFunc.hasValue(trxRsnCd) && trxRsnCd.equals(TRX_RSN_CD_AR_RECLASS))) {
                return FLG_ON_Y;
            } else {
                return FLG_OFF_N;
            }
        }
        
        /**
         * <pre>
         *  Create AJE Interface Control.
         * </pre>
         * @param EZDTMsg AJE Interface Control
         * @throws JrnlCommonException JrnlCommonException
         */
        private void createAjeIntfcCtrl(EZDTMsg ajeIntfcCtrl)
        throws NFACommonJrnlEntry.JrnlCommonException {

            if (ajeIntfcCtrl != null) {
              ctrlMsgCount += 1;
              ctrlMsgs[ctrlMsgCount - 1] = ajeIntfcCtrl;
            } else {
                ctrlMsgs = common.changeArraySize(ctrlMsgs, ctrlMsgCount);
            }

            // per 10000 lines
            if (ctrlMsgCount >= BULK_INSERT_COUNT || ajeIntfcCtrl == null) {

                // insert
                int retCnt = S21FastTBLAccessor.insert(ctrlMsgs);

                // if passed records' count and return count don't match, error
                if (retCnt != ctrlMsgCount) {

                    throw common.new JrnlCommonException(ZZBM0074E);
                }

                // initialize
                ctrlMsgCount = 0;
                ctrlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
            }

        }

        /**
         * <pre>
         *  Create Journal Entry.
         * </pre>
         * @param EZDTMsg Journal Entry
         * @throws JrnlCommonException JrnlCommonException
         */
        private void createJrnlEntry(EZDTMsg jrnlEntry) throws NFACommonJrnlEntry.JrnlCommonException {

            if (jrnlEntry != null) {
                jrnlMsgs[jrnlMsgCount] = jrnlEntry;
                jrnlMsgCount += 1;

            } else {  // array may be not full
                jrnlMsgs = common.changeArraySize(jrnlMsgs, jrnlMsgCount);
            }

            // per 10000 lines
            if (jrnlMsgCount >= BULK_INSERT_COUNT || jrnlEntry == null) {

                int retCnt = S21FastTBLAccessor.insert(jrnlMsgs);

                // if passed records' count and return count don't match, error
                if (retCnt != jrnlMsgCount) {
                    throw common.new JrnlCommonException(ZZBM0074E);
                }
                commitCount += jrnlMsgCount;
                // initialize
                jrnlMsgCount = 0;
                jrnlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
            }

        }

        // This function is no longer required since TOC_CD is added into AJE_AR_INTFC
        /*private HashMap getBrCcProdCodeMap(ResultSet rs) throws SQLException {
            String trxCd = rs.getString(TRX_CD);
            String trxRsnCd = rs.getString(TRX_RSN_CD);
            HashMap<String, String> hMp = new HashMap<String, String>();

            // 20100114 PROD_CD is removed from S21_ORG
            
            // Transaction code from 110 to 180 in order to determine the right table

            // Array contains the list of transaction code(s) such as {"120", "150"}
            // this case is not in use ad of 2009/11 because @TOC doesn't exist in AJE Pattern
            for (String trxCode : AR_RCPT_TRX_CD_ARR) {
                // If the transaction code found in the array the values based on AR_RCPT will be used
                if (trxCd.equals(trxCode)) {

                    hMp.put(COA_BR_CD, rs.getString(COA_BR_RCPT));
                    hMp.put(COA_CC_CD, rs.getString(COA_CC_RCPT));
                    hMp.put(COA_CH_CD, rs.getString(COA_CH_RCPT));
                    hMp.put(BR_DPC_PTRN, rs.getString(BR_DPC_PTRN_RCPT));
                    hMp.put(CC_DPC_PTRN, rs.getString(CC_DPC_PTRN_RCPT));
                    hMp.put(CH_DPC_PTRN, rs.getString(CH_DPC_PTRN_RCPT));
                    return hMp;

                }
            }

            // Array contains the list of transaction code(s) such as {}
            for (String trxCode : AR_ADJ_TRX_CD_ARR) {
                // If the transaction code found in the array the values based on AR_ADJ will be used
                if (trxCd.equals(trxCode)) {
                    hMp.put(COA_BR_CD, rs.getString(COA_BR_ADJ));
                    hMp.put(COA_CC_CD, rs.getString(COA_CC_ADJ));
                    hMp.put(COA_CH_CD, rs.getString(COA_CH_ADJ));
                    return hMp;

                }
            }

            // Array contains the list of transaction code(s) such as {"130", "140", "160", "170", "180"}
            for (String trxCode : AR_TRX_BAL_TRX_CD_ARR) {
                // If the transaction code found in the array the values based on AR_TRX_BAL will be used
                if (trxCd.equals(trxCode)) {
                    hMp.put(COA_BR_CD, rs.getString(COA_BR_TRX));
                    hMp.put(COA_CC_CD, rs.getString(COA_CC_TRX));
                    hMp.put(COA_CH_CD, rs.getString(COA_CH_TRX));
                    hMp.put(BR_DPC_PTRN, rs.getString(BR_DPC_PTRN_TRX));
                    hMp.put(CC_DPC_PTRN, rs.getString(CC_DPC_PTRN_TRX));
                    hMp.put(CH_DPC_PTRN, rs.getString(CH_DPC_PTRN_TRX));
                    
                    // special case
                    // In case 180-02, codes should be set from AR_RCPT
                    if (trxCode.equals(TRX_CD_VAL_180) && trxRsnCd.equals(TRX_RSN_CD_VAL_02)) {
                        hMp.put(COA_BR_CD, rs.getString(COA_BR_RCPT));
                        hMp.put(COA_CC_CD, rs.getString(COA_CC_RCPT));
                        hMp.put(COA_CH_CD, rs.getString(COA_CH_RCPT));
                        hMp.put(BR_DPC_PTRN, rs.getString(BR_DPC_PTRN_RCPT));
                        hMp.put(CC_DPC_PTRN, rs.getString(CC_DPC_PTRN_RCPT));
                        hMp.put(CH_DPC_PTRN, rs.getString(CH_DPC_PTRN_RCPT));
                    }
                    
                    return hMp;
                }
            }
            return null;
        } */
    }
    

    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, errorCount, commitCount + errorCount);

        S21InfoLogOutput.println("termRoutine Method End");
    }

}
