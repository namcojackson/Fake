package com.canon.cusa.s21.batch.NFA.NFAB312001;

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
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INTFC_TP;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Accrual Journal Creation Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/06/14   Hitachi         S.Nakatani      Create          QC#61088
 * 2024/01/22   CITS            M.Kobayashi     Update          QC#62449
 * </pre>
 */
public class NFAB312001 extends S21BatchMain implements NFAB312001Constant, ZYPConstant, NFACommonJrnlEntryConstant {
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

    /** Sales Date */
    private String slsDt = "";

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

    /** Journal Entry Common Module */
    private NFACommonJrnlEntry common = new NFACommonJrnlEntry();

    /** Aje Zero Create Flg */
    private String ajeZeroCratFlg = "";

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        new NFAB312001().executeBatch(NFAB312001.class.getSimpleName());

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

        // Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            errorCount++;
            setTermState(TERM_CD.ABNORMAL_END, commitCount, errorCount, commitCount + errorCount);
            throw new S21AbendException("ZZXM0020E", new String[] {"Sales Date" });
        }

        // initialize
        jrnlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        ctrlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];

        ajeZeroCratFlg = ZYPCodeDataUtil.getVarCharConstValue("AJE_ZERO_CRAT_FLG_OM", this.glblCmpyCd);
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
            S21InfoLogOutput.println(NFAM0036I, new String[] {"Accrual Jounal Creation" });
        } else {

            List<Map> listNotJrnlized = common.getAjeIntfcKeyListNotJrnlized(this.glblCmpyCd, AJE_INTFC_TP.ACCRUAL);

            Boolean checkRemoveProcess = Boolean.TRUE;
            if (listNotJrnlized.size() > 0) {
                checkRemoveProcess = removeIntFcNotJrnlized(listNotJrnlized);

                if (checkRemoveProcess) {
                    commit();
                } else {
                    S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
                    rollback();
                    errorCount++;
                    setTermState(TERM_CD.ABNORMAL_END, commitCount, errorCount, commitCount + errorCount);
                    throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg });
                }
            }

            if (checkRemoveProcess) {
                List<Map> listAjePtrn = common.getAjePtrn(listTrxPtrn, this.glblCmpyCd, AJE_INTFC_TP.ACCRUAL);

                if (listAjePtrn.size() == 0) {
                    S21InfoLogOutput.println(NFACommonJrnlEntryConstant.NFAM0037E);
                } else {
                    Boolean checkJournalProc = doEntryToJournalEntry(listAjePtrn);

                    if (checkJournalProc != Boolean.TRUE) {
                        S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
                        rollback();
                        errorCount++;
                        setTermState(TERM_CD.ABNORMAL_END, commitCount, errorCount, commitCount + errorCount);
                        throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg });
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
     * @param
     */
    private List<Map> getTrxPtrn() {

        // ** Get All Transaction Pattern in AJE Loan Depreciation Interface. **
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

                setValue(tMsgIntFctrl.glblCmpyCd, glblCmpyCdStr);
                setValue(tMsgIntFctrl.ajeIntfcTpCd, ajeIntfcTpCdStr);
                setValue(tMsgIntFctrl.ajeIntfcPk, ajeIntfcPkNum);

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

        // per 1000 lines
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
     * @param List<Map> Target AJE Pattern List
     */
    private Boolean doEntryToJournalEntry(List<Map> ajePtrnList) {

        // ** Get Target Transactions in AJE Loan Depreciation Interface. **
        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
        map.put("jrnlCpltFlg", FLG_ON_Y);

        // START 2024/01/22 [QC#62449, Del]
        // last month
        //Calendar cal = Calendar.getInstance();

        //cal.set(Integer.valueOf(ZYPDateUtil.DateFormatter(slsDt, "yyyyMMdd", "yyyy")), Integer.valueOf(ZYPDateUtil.DateFormatter(slsDt, "yyyyMMdd", "MM")) - 1, Integer.valueOf(ZYPDateUtil.DateFormatter(slsDt, "yyyyMMdd", "dd")));

        // minus one month (send previous month fixed data)
        //cal.add(Calendar.MONTH, -1);

        // format
        //String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        //if (month != null && month.length() == 1) {
        //    month = "0" + month;
        //}
        //map.put("lastMonth", String.valueOf(cal.get(Calendar.YEAR)) + month);
        // END 2024/01/22 [QC#62449, Del]

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

                    for (NFAC000101PMsg pMsg : pMsgList) {

                        BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(JRNL_ENTRY_SQ);

                        JRNL_ENTRYTMsg tMsgJrnEnt = new JRNL_ENTRYTMsg();
                        try {

                            // From Interface
                            setValue(tMsgJrnEnt.jrnlEntryPk, seqNum);

                            setPartOfJrnlEntryValues(tMsgJrnEnt, rs, pMsg);

                            CoaSegment coa = CoaSegment.DEF;

                            // for CR
                            coa = setValuesByCr(tMsgJrnEnt, rs, pMsg);
                            if (coa != CoaSegment.DEF) {
                                errMsgId = NFACommonJrnlEntryConstant.COA_ERROR_MSG_ID;
                                errMsgTxt = NFACommonJrnlEntryConstant.COA_ERROR_MSG_TXT.replace("@", common.getCoAMessage(coa));
                                notError = false;
                                break;
                            }

                            setValue(tMsgJrnEnt.jrnlEntryRefTxt, common.getJrnlEntryRefTxt(tMsgJrnEnt));

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

                    }

                    insertIntoJrnlOrCtrl(notError, rs, lstJrnlEntry);

                }

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
         * @param checkCoaSegmentDetermination True: OK False: NG
         * @param rs ResultSet of transaction
         * @param errMsgId error message Id if error exists
         * @param errMsgTxt error message text if error exists
         * @param lstJrnlEntry list of journal entry to be inserted
         * @throws SQLException SQL Exception
         */
        private void insertIntoJrnlOrCtrl(boolean checkCoaSegmentDetermination, ResultSet rs, List<JRNL_ENTRYTMsg> lstJrnlEntry) throws SQLException, NFACommonJrnlEntry.JrnlCommonException {

            boolean isJrnlEntryCommit = false;
            boolean isAjeIntfcCtrlCommit = false;
            
            // CoA Segment Setting is complete or not.
            if (checkCoaSegmentDetermination) {
                // insert into jrnl entry
                for (JRNL_ENTRYTMsg jrnlEntryRec : lstJrnlEntry) {

                    if (BigDecimal.ZERO.compareTo(jrnlEntryRec.jrnlDealDrAmt.getValue()) == 0 && !ZYPConstant.FLG_ON_Y.equals(ajeZeroCratFlg)) {
                        continue;
                    }
                    isJrnlEntryCommit = createJrnlEntry(jrnlEntryRec);
                }

            }

            // Store the result in AJE_INTFC_CTRL.
            AJE_INTFC_CTRLTMsg ajeIntfcCtrl = new AJE_INTFC_CTRLTMsg();
            setValue(ajeIntfcCtrl.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
            setValue(ajeIntfcCtrl.ajeIntfcTpCd, AJE_INTFC_TP.ACCRUAL);
            setValue(ajeIntfcCtrl.ajeIntfcPk, rs.getBigDecimal(AJE_INTFC_PK));
            setValue(ajeIntfcCtrl.procDt, rs.getString(GL_DT));
            setValue(ajeIntfcCtrl.sysSrcCd, rs.getString(SYS_SRC_CD));
            setValue(ajeIntfcCtrl.trxCd, rs.getString(TRX_CD));
            setValue(ajeIntfcCtrl.trxRsnCd, rs.getString(TRX_RSN_CD));
            setValue(ajeIntfcCtrl.ajePgmId, ajePgmId);

            if (!checkCoaSegmentDetermination) {

                setValue(ajeIntfcCtrl.jrnlCpltFlg, FLG_OFF_N);
                setValue(ajeIntfcCtrl.ajeErrMsgId, errMsgId);
                setValue(ajeIntfcCtrl.ajeErrMsgTxt, errMsgTxt);

                errorCount += 1;

                isAjeIntfcCtrlCommit = createAjeIntfcCtrl(ajeIntfcCtrl);

            } else {
                setValue(ajeIntfcCtrl.jrnlCpltFlg, FLG_ON_Y);

                isAjeIntfcCtrlCommit = createAjeIntfcCtrl(ajeIntfcCtrl);
            }
            
            // 1000 lines
            if(isJrnlEntryCommit && isAjeIntfcCtrlCommit){
                commit();
            }
        }

        /**
         * <pre>
         *  Set Cr values of COA segment depends on parameter
         * </pre>
         * @param jrnlEntry TMsg of Journal Entry that is being
         * generated
         * @param rs result set of interface table
         * @param rsAjePtrnList AJE pattern list
         * @throws SQLException SQL Exception
         * @throws EZDValidatorException
         */
        private CoaSegment setValuesByCr(JRNL_ENTRYTMsg jrnlEntry, ResultSet rs, NFAC000101PMsg rsAjePtrnList) throws SQLException, EZDValidatorException {

            // CoA Company
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.CMPY, CR, rsAjePtrnList, rs, null)) {
                return CoaSegment.CMPY;
            }

            // CoA Branch
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.BR, CR, rsAjePtrnList, rs, null)) {
                return CoaSegment.BR;
            }

            // CoA Cos tCenter
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.CC, CR, rsAjePtrnList, rs, null)) {
                return CoaSegment.CC;
            }

            // CoA Account
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.ACCT, CR, rsAjePtrnList, rs, null)) {
                return CoaSegment.ACCT;
            }

            // CoA Prod
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.PROD, CR, rsAjePtrnList, rs, null)) {
                return CoaSegment.PROD;
            }

            // CoA Channel
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.CH, CR, rsAjePtrnList, rs, null)) {
                return CoaSegment.CH;
            }

            // CoA Affiliate
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.AFFL, CR, rsAjePtrnList, rs, null)) {
                return CoaSegment.AFFL;
            }

            // CoA Project
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.PROJ, CR, rsAjePtrnList, rs, null)) {
                return CoaSegment.PROJ;
            }
            
            // CoA Extension
            if (!common.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.EXTN, CR, rsAjePtrnList, rs, null)) {
                return CoaSegment.EXTN;
            }

            return CoaSegment.DEF;

        }

        /**
         * <pre>
         *  This is helper function of doProcessQueryResult to make the function smaller
         * </pre>
         * @param tMsgJrnEnt TMsg of Journal Entry that is being
         * generated
         * @param rs result set of interface table
         * @param pMsg AJE pattern list
         * @throws SQLException SQL Exception
         */
        private void setPartOfJrnlEntryValues(JRNL_ENTRYTMsg tMsgJrnEnt, ResultSet rs, NFAC000101PMsg pMsg) throws SQLException {
            setValue(tMsgJrnEnt.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
            setValue(tMsgJrnEnt.ajeIntfcTpCd, AJE_INTFC_TP.ACCRUAL);
            setValue(tMsgJrnEnt.ajeIntfcPk, rs.getBigDecimal(AJE_INTFC_PK));
            setValue(tMsgJrnEnt.procDt, rs.getString(GL_DT));
            setValue(tMsgJrnEnt.glDt, rs.getString(GL_DT));
            setValue(tMsgJrnEnt.glSendCpltFlg, FLG_OFF_N);
            setValue(tMsgJrnEnt.ajeId, pMsg.ajeId.getValue());
            setValue(tMsgJrnEnt.sysSrcCd, rs.getString(SYS_SRC_CD));
            setValue(tMsgJrnEnt.sysSrcNm, rs.getString(SYS_SRC_NM));
            setValue(tMsgJrnEnt.trxCd, rs.getString(TRX_CD));
            setValue(tMsgJrnEnt.trxNm, rs.getString(TRX_NM));
            setValue(tMsgJrnEnt.trxRsnCd, rs.getString(TRX_RSN_CD));
            setValue(tMsgJrnEnt.trxRsnNm, rs.getString(TRX_RSN_NM));
            setValue(tMsgJrnEnt.ajePtrnIndTpCd_01, pMsg.ajePtrnIndTpCd_01.getValue());
            setValue(tMsgJrnEnt.ajePtrnIndTpNm_01, pMsg.ajePtrnIndTpNm_01.getValue());
            setValue(tMsgJrnEnt.ajePtrnActlCd_01, pMsg.ajePtrnActlCd_01.getValue());
            setValue(tMsgJrnEnt.ajePtrnActlNm_01, pMsg.ajePtrnActlNm_01.getValue());
            setValue(tMsgJrnEnt.ajePtrnIndTpCd_02, pMsg.ajePtrnIndTpCd_02.getValue());
            setValue(tMsgJrnEnt.ajePtrnIndTpNm_02, pMsg.ajePtrnIndTpNm_02.getValue());
            setValue(tMsgJrnEnt.ajePtrnActlCd_02, pMsg.ajePtrnActlCd_02.getValue());
            setValue(tMsgJrnEnt.ajePtrnActlNm_02, pMsg.ajePtrnActlNm_02.getValue());
            setValue(tMsgJrnEnt.ajePtrnIndTpCd_03, pMsg.ajePtrnIndTpCd_03.getValue());
            setValue(tMsgJrnEnt.ajePtrnIndTpNm_03, pMsg.ajePtrnIndTpNm_03.getValue());
            setValue(tMsgJrnEnt.ajePtrnActlCd_03, pMsg.ajePtrnActlCd_03.getValue());
            setValue(tMsgJrnEnt.ajePtrnActlNm_03, pMsg.ajePtrnActlNm_03.getValue());
            setValue(tMsgJrnEnt.jrnlSrcCd, pMsg.jrnlSrcCd.getValue());
            setValue(tMsgJrnEnt.jrnlSrcNm, pMsg.jrnlSrcNm.getValue());
            setValue(tMsgJrnEnt.jrnlCatgCd, pMsg.jrnlCatgCd.getValue());
            setValue(tMsgJrnEnt.jrnlCatgNm, pMsg.jrnlCatgNm.getValue());
            setValue(tMsgJrnEnt.ajeLineIdxNum, pMsg.ajeLineIdxNum.getValue());
            setValue(tMsgJrnEnt.ajeItemCd, rs.getString(AJE_ITEM_CD));
            setValue(tMsgJrnEnt.ajeItemDescTxt, rs.getString(AJE_ITEM_DESC_TXT));
            setValue(tMsgJrnEnt.drAjeLineIdxDescTxt, pMsg.drAjeLineIdxDescTxt.getValue());

            // DR
            setValue(tMsgJrnEnt.drCoaCmpyCd, "ADB");
            setValue(tMsgJrnEnt.drCoaBrCd, "000");
            setValue(tMsgJrnEnt.drCoaCcCd, "000000");
            setValue(tMsgJrnEnt.drCoaAcctCd, "11519101");
            setValue(tMsgJrnEnt.drCoaProdCd, "ZZ");
            setValue(tMsgJrnEnt.drCoaChCd, "000");
            setValue(tMsgJrnEnt.drCoaAfflCd, "000");
            setValue(tMsgJrnEnt.drCoaProjCd, "00");
            setValue(tMsgJrnEnt.drCoaExtnCd, "000");

            // Quantity
            BigDecimal multi = BigDecimal.ONE;
            setValue(tMsgJrnEnt.jrnlQty, multi);

            // Amount
            String flipFlg = pMsg.jrnlAmtFlipFlg.getValue();
            if (FLG_ON_Y.equals(flipFlg)) {
                multi = multi.negate();
            }
            setValue(tMsgJrnEnt.jrnlDealDrAmt, rs.getBigDecimal(JRNL_DEAL_AMT).multiply(multi));
            setValue(tMsgJrnEnt.jrnlDealCrAmt, rs.getBigDecimal(JRNL_DEAL_AMT).multiply(multi));
            setValue(tMsgJrnEnt.jrnlFuncDrAmt, rs.getBigDecimal(JRNL_FUNC_AMT).multiply(multi));
            setValue(tMsgJrnEnt.jrnlFuncCrAmt, rs.getBigDecimal(JRNL_FUNC_AMT).multiply(multi));

            setValue(tMsgJrnEnt.dealCcyCd, rs.getString(DEAL_CCY_CD));
            setValue(tMsgJrnEnt.funcCcyCd, rs.getString(FUNC_CCY_CD));
            setValue(tMsgJrnEnt.orclCcyCd, rs.getString(ORCL_CCY_CD));
            setValue(tMsgJrnEnt.exchRate, rs.getBigDecimal(EXCH_RATE));
            setValue(tMsgJrnEnt.acctArthTpCd, rs.getString(ACCT_ARTH_TP_CD));
            setValue(tMsgJrnEnt.tocCd, rs.getString(TOC_CD));
            setValue(tMsgJrnEnt.orclRef10Txt, ORCL_REF_10_TXT_PREFIX + pMsg.ajeId.getValue());
            setValue(tMsgJrnEnt.dsAcctNum, rs.getString(DS_ACCT_NUM));
            setValue(tMsgJrnEnt.serNum, rs.getString(SER_NUM));
            setValue(tMsgJrnEnt.svcMachMstrPk, rs.getBigDecimal(SVC_MACH_MSTR_PK));
            setValue(tMsgJrnEnt.dsContrPk, rs.getBigDecimal(DS_CONTR_PK));
            setValue(tMsgJrnEnt.dsContrDtlPk, rs.getBigDecimal(DS_CONTR_DTL_PK));
            setValue(tMsgJrnEnt.jrnlEntryFirstRefTxt, rs.getString(DS_CONTR_NUM));
            setValue(tMsgJrnEnt.jrnlEntryScdRefTxt, rs.getString(SLS_REP_CR_PCT));
            setValue(tMsgJrnEnt.jrnlEntryFirstAttrbNm, "Contract Number");
            setValue(tMsgJrnEnt.jrnlEntryScdAttrbNm, "Service AllocationRatio");
            setValue(tMsgJrnEnt.svcConfigMstrPk, rs.getBigDecimal(SVC_CONFIG_MSTR_PK));
        }

        /**
         * <pre>
         *  Create AJE Interface Control.
         * </pre>
         * @param EZDTMsg AJE Interface Control
         * @return boolean
         * @throws JrnlCommonException JrnlCommonException
         */
        private boolean createAjeIntfcCtrl(EZDTMsg ajeIntfcCtrl) throws NFACommonJrnlEntry.JrnlCommonException {

            boolean isAjeIntfcCtrlCommit = false;
            if (ajeIntfcCtrl != null) {
                ctrlMsgCount += 1;
                ctrlMsgs[ctrlMsgCount - 1] = ajeIntfcCtrl;
            } else {
                ctrlMsgs = common.changeArraySize(ctrlMsgs, ctrlMsgCount);
            }

            // per 1000 lines
            if (ctrlMsgCount >= BULK_INSERT_COUNT || ajeIntfcCtrl == null) {

                // insert
                int retCnt = S21FastTBLAccessor.insert(ctrlMsgs);

                // if passed records' count and return count don't match, error
                if (retCnt != ctrlMsgCount) {
                    throw common.new JrnlCommonException(ZZBM0074E);
                }

                isAjeIntfcCtrlCommit = true;

                // initialize
                ctrlMsgCount = 0;
                ctrlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
            }
            return isAjeIntfcCtrlCommit;
        }

        /**
         * <pre>
         *  Create Journal Entry.
         * </pre>
         * @param EZDTMsg Journal Entry
         * @return boolean
         * @throws JrnlCommonException JrnlCommonException
         */
        private boolean createJrnlEntry(EZDTMsg jrnlEntry) throws NFACommonJrnlEntry.JrnlCommonException {

            boolean isJrnlEntryCommit = false;
            if (jrnlEntry != null) {
                jrnlMsgs[jrnlMsgCount] = jrnlEntry;
                jrnlMsgCount += 1;

            } else { // array may be not full
                jrnlMsgs = common.changeArraySize(jrnlMsgs, jrnlMsgCount);
            }

            // per 1000 lines
            if (jrnlMsgCount >= BULK_INSERT_COUNT || jrnlEntry == null) {

                int retCnt = S21FastTBLAccessor.insert(jrnlMsgs);
                
                // if passed records' count and return count don't
                // match, error
                if (retCnt != jrnlMsgCount) {
                    throw common.new JrnlCommonException(ZZBM0074E);
                }

                isJrnlEntryCommit = true;

                commitCount += jrnlMsgCount;

                // initialize
                jrnlMsgCount = 0;
                jrnlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
            }
            return isJrnlEntryCommit;

        }
    }

    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, errorCount, commitCount + errorCount);

        S21InfoLogOutput.println("termRoutine Method End");
    }

}
