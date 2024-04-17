package com.canon.cusa.s21.batch.NFA.NFAB016001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import parts.common.EZDValidatorException;
import business.db.AJE_INTFC_CTRLTMsg;
import business.db.CM_ACRL_WRITE_OFFTMsg;
import business.db.JRNL_ENTRYTMsg;
import business.parts.MdseTpAcct;
import business.parts.NFAC000101PMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_CM_INTFC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INTFC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_STK_IN_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_ACCTG_RULE;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * AP Journal Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/04/05   Hitachi         K.Kim           Create          QC#57935
 *</pre>
 */
public class NFAB016001 extends S21BatchMain implements NFAB016001Constant, ZYPConstant, NFACommonJrnlEntryConstant {

    /** SSM Batch Client */
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

    /** Journal Entry Common Module */
    private NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();

    /** Process Date */
    private Map<String, List<MdseTpAcct>> mtMap;

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        new NFAB016001().executeBatch(NFAB016001.class.getSimpleName());

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

        procDt = ZYPDateUtil.getBatProcDate();

        // initialize
        jrnlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        ctrlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];

        // get COA Project Account Master
        mtMap = commonJrnlEntry.getMdseTpAcct(glblCmpyCd);

        S21InfoLogOutput.println("initRoutine Method End");
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        List<Map> listTrxPtrn = getTrxPtrnCmIntFc();

        if (listTrxPtrn.size() == 0) {
            S21InfoLogOutput.println(NFAM0036I, new String[] {MSG_PARAM });
        } else {

            List<Map> listNotJrnlized = commonJrnlEntry.getAjeIntfcKeyListNotJrnlized(this.glblCmpyCd, AJE_INTFC_TP.COST_MANAGEMENT);

            if (listNotJrnlized.size() > 0) {

                if (removeIntFcNotJrnlized(listNotJrnlized)) {
                    commit();
                } else {
                    rollback();
                    throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg });
                }
            }

            List<Map> listAjePtrn = commonJrnlEntry.getAjePtrn(listTrxPtrn, this.glblCmpyCd, AJE_INTFC_TP.COST_MANAGEMENT);

            if (listAjePtrn.size() == 0) {
                S21InfoLogOutput.println(NFACommonJrnlEntryConstant.NFAM0037E);
            } else {

                if (!doEntryToJournalEntry(listAjePtrn)) {
                    rollback();
                    throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg });
                }

                commit();
            }
        }
        S21InfoLogOutput.println("mainRoutine Method End");
    }

    /**
     * <pre>
     *  Get All Transaction Patterns in AJE Cost Management Interface.
     * </pre>
     * @param
     */
    @SuppressWarnings("unchecked")
    private List<Map> getTrxPtrnCmIntFc() {

        // ** Get All Transaction Pattern in AJE Loan Depreciation
        // Interface. **
        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("ajeIntfcTpCd", AJE_INTFC_TP.COST_MANAGEMENT);
        map.put("jrnlCpltFlg", FLG_ON_Y);

        List<Map> listTrxPtrn = (List<Map>) ssmBatchClient.queryObjectList("getCmTrxPtrn", map);

        return listTrxPtrn;
    }

    /**
     * <pre>
     *  Remove AJE Interface Control that is not journalized.
     *  @return boolean true: OK  false: NG
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private boolean removeIntFcNotJrnlized(List<Map> listNotJrnlized) {

        try {
            for (Map<String, Object> map : listNotJrnlized) {

                AJE_INTFC_CTRLTMsg tMsgIntFctrl = new AJE_INTFC_CTRLTMsg();

                String glblCmpyCdStr = (String) map.get(GLBL_CMPY_CD);
                String ajeIntfcTpCdStr = (String) map.get(AJE_INTFC_TP_CD);
                BigDecimal intFcPk = new BigDecimal(map.get(AJE_INTFC_PK).toString());

                commonJrnlEntry.setFieldValue(tMsgIntFctrl, "glblCmpyCd", glblCmpyCdStr);
                commonJrnlEntry.setFieldValue(tMsgIntFctrl, "ajeIntfcTpCd", ajeIntfcTpCdStr);
                commonJrnlEntry.setFieldValue(tMsgIntFctrl, "ajeIntfcPk", intFcPk);

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
     *  @return JrnlCommonException JrnlCommonException
     * </pre>
     */
    private void removeAjeIntfcCtrlNotJrnlizedHelper(EZDTMsg rmvRec) throws NFACommonJrnlEntry.JrnlCommonException {

        if (rmvRec != null) {
            rmvMsgs[rmvMsgCount] = rmvRec;
            rmvMsgCount += 1;
        } else {
            rmvMsgs = commonJrnlEntry.changeArraySize(rmvMsgs, rmvMsgCount);
        }

        // per 10000 lines
        if (rmvMsgCount >= BULK_INSERT_COUNT || rmvRec == null) {

            int retCnt = S21FastTBLAccessor.removePhysical(rmvMsgs);

            // if passed records' count and return count don't match,
            // error
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
     *  Get Target Transactions in AJE Cost Management Interface and Journalize.
     * </pre>
     * @param List<Map> Target AJE Pattern List
     */
    @SuppressWarnings("unchecked")
    private Boolean doEntryToJournalEntry(List<Map> ajePtrnList) {

        // ** Get Target Transactions in AJE Cost Management Interface. **
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("ajeIntfcTpCd", AJE_INTFC_TP.COST_MANAGEMENT);
        map.put("jrnlCpltFlg", FLG_ON_Y);
        map.put("flgY", FLG_ON_Y);
        map.put("accrual", COA_PROJ_ACCT_TP.ACCRUAL);
        map.put("immediate", DFRD_ACCTG_RULE.IMMEDIATE);
        map.put("stkIn", AJE_STK_IN_IND_TP.STK_IN);
        map.put("nonStkIn", AJE_STK_IN_IND_TP.NON_STK_IN);

        Boolean checkJournalEntry = (Boolean) ssmBatchClient.queryObject("getAjeCmIntfcNotJrnlized", map, new JrnlEntryHandler(ajePtrnList));
        return checkJournalEntry;
    }

    /**
     * <pre>
     *  Journal Entry Handler.
     *  Journalize all transactions with Cost Management and update the result in AJE Interface Control Table. 
     *  CoA Segment is derived from AJE Pattern or masters (Merchandise).
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private final class JrnlEntryHandler extends S21SsmBooleanResultSetHandlerSupport {

        /** AJE Pattern List */
        private List<Map> ajePtrnList;

        /** Functional Currency Code */
        private String funcCcyCd;

        /** Oracle Currency Code */
        private String orclCcyCd;

        /** Accounting Arithmetic Type Code */
        private String acctArthTpCd;

        /** Currency Info Getting Process Completion Flag */
        boolean isComleted = false;

        /** Error Message Id **/
        private String errMsgId = NFACommonJrnlEntryConstant.DEF_ERROR_MSG_ID;

        /** Error Message text **/
        private String errMsgTxt = NFACommonJrnlEntryConstant.DEF_ERROR_MSG_TXT;

        private JrnlEntryHandler(List<Map> ajePtrnList) {
            // AJE Pattern
            this.ajePtrnList = ajePtrnList;
        }

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
                // List to store JRNL_ENTRYTMsg to be inserted to JRNL_ENTRY
                List<JRNL_ENTRYTMsg> lstJrnlEntry = new ArrayList<JRNL_ENTRYTMsg>();

                // Get Currency Info.
                // This process is invoked only one time in this loop.
                if (this.funcCcyCd == null && this.isComleted == false) {
                    Map currencyInfo = (Map) commonJrnlEntry.getCcy(glblCmpyCd);
                    // ** CoA Currency ** //
                    this.funcCcyCd = currencyInfo.get("FUNC_CCY_CD").toString();
                    this.orclCcyCd = currencyInfo.get("ORCL_CCY_CD").toString();
                    this.acctArthTpCd = currencyInfo.get("ACCT_ARTH_TP_CD").toString();
                    if (this.funcCcyCd != null && this.orclCcyCd != null && this.acctArthTpCd != null) {
                        isComleted = true;
                    } else {
                        S21InfoLogOutput.println(NFAM0048E);
                    }
                }

                while (rs.next()) {
                    // initialize
                    lstJrnlEntry.clear();

                    // Check Flag for CoA Segment Determination.
                    boolean checkCoaSegmentDetermination = true;

                    // AJE ID
                    String ajeId = commonJrnlEntry.generateAjeId(rs.getString(SYS_SRC_CD), rs.getString(TRX_CD), rs.getString(TRX_RSN_CD));

                    // Refine AJE Pattern List by AJE ID and Indicator
                    List<NFAC000101PMsg> pMsgList = commonJrnlEntry.getAJEPtrnByAjeIdAndIndTp(ajeId, rs, ajePtrnList);

                    // if no pattern is found, set noError false
                    if (pMsgList.isEmpty()) {
                        errMsgId = NFACommonJrnlEntryConstant.NFAM0037E;
                        errMsgTxt = NFACommonJrnlEntryConstant.NO_AJE_PTRN_ERROR_MSG_TXT;
                        checkCoaSegmentDetermination = false;
                    }

                    for (NFAC000101PMsg pMsg : pMsgList) {

                        BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(JRNL_ENTRY_SQ);

                        JRNL_ENTRYTMsg tMsgJrnEnt = new JRNL_ENTRYTMsg();

                        try {
                            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryPk", seqNum);

                            setPartOfJrnlEntryValues(tMsgJrnEnt, rs, pMsg);
                            CoaSegment coa = CoaSegment.DEF;

                            // for DR
                            coa = setValuesByCrDr("dr", tMsgJrnEnt, rs, pMsg);
                            if (coa != CoaSegment.DEF) {
                                errMsgId = NFACommonJrnlEntryConstant.COA_ERROR_MSG_ID;
                                errMsgTxt = NFACommonJrnlEntryConstant.COA_ERROR_MSG_TXT.replace("@", commonJrnlEntry.getCoAMessage(coa));
                                checkCoaSegmentDetermination = false;
                                break;
                            }

                            // for CR
                            coa = setValuesByCrDr("cr", tMsgJrnEnt, rs, pMsg);
                            if (coa != CoaSegment.DEF) {
                                errMsgId = NFACommonJrnlEntryConstant.COA_ERROR_MSG_ID;
                                errMsgTxt = NFACommonJrnlEntryConstant.COA_ERROR_MSG_TXT.replace("@", commonJrnlEntry.getCoAMessage(coa));
                                checkCoaSegmentDetermination = false;
                                break;
                            }

                            /** CoA Currency */
                            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "dealCcyCd", funcCcyCd);
                            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "funcCcyCd", funcCcyCd);
                            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "orclCcyCd", orclCcyCd);

                            /** Exchange Rate */
                            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "exchRate", INITIAL_EXCH_RATE_VAL);
                            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "acctArthTpCd", acctArthTpCd);

                            /** Quantity */
                            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlQty", rs.getBigDecimal(WRITE_OFF_QTY));

                            /** Amount */
                            // Debit
                            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlDealDrAmt", rs.getBigDecimal(CM_AJE_TOT_COST_AMT));
                            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlFuncDrAmt", rs.getBigDecimal(CM_AJE_TOT_COST_AMT));

                            // Credit
                            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlDealCrAmt", rs.getBigDecimal(CM_AJE_TOT_COST_AMT));
                            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlFuncCrAmt", rs.getBigDecimal(CM_AJE_TOT_COST_AMT));

                            // set reference text
                            String refTxt = commonJrnlEntry.getJrnlEntryRefTxt(tMsgJrnEnt);
                            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryRefTxt", refTxt);

                        } catch (EZDValidatorException exVal) {
                            checkCoaSegmentDetermination = false;
                            errMsgId = NFACommonJrnlEntryConstant.COA_ERROR_MSG_ID;
                            errMsgTxt = exVal.getMessage();
                            break;

                        } catch (EZDAbendException exEz) {
                            // null exception
                            checkCoaSegmentDetermination = false;
                            errMsgId = NFACommonJrnlEntryConstant.NULL_ERROR_MSG_ID;
                            errMsgTxt = exEz.getMessage();
                            break;

                        } catch (SQLException sqlEx) {
                            errMsg = sqlEx.getMessage();
                            return Boolean.FALSE;
                        }

                        // Store into the list
                        if (checkCoaSegmentDetermination) {
                            lstJrnlEntry.add(tMsgJrnEnt);
                        }
                    } // for

                    insertIntoJrnlOrCtrl(checkCoaSegmentDetermination, rs, lstJrnlEntry);

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
         *  Set Dr or Cr values of COA segment depends on parameter
         * </pre>
         * @param crOrDr Cr or Dr
         * @param jrnlEntry TMsg of Journal Entry that is being
         * generated
         * @param rs result set of interface table
         * @param rsAjePtrnList AJE pattern list
         * @throws SQLException SQL Exception
         * @throws EZDValidatorException
         */
        private CoaSegment setValuesByCrDr(String crOrDr, JRNL_ENTRYTMsg jrnlEntry, ResultSet rs, NFAC000101PMsg rsAjePtrnList) throws SQLException, EZDValidatorException {

            /** CoA Company */
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.CMPY, crOrDr, rsAjePtrnList, rs, mtMap)) {
                return CoaSegment.CMPY;
            }

            /** CoA Branch */
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.BR, crOrDr, rsAjePtrnList, rs, mtMap)) {
                return CoaSegment.BR;
            }

            /** CoA Cos tCenter */
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.CC, crOrDr, rsAjePtrnList, rs, mtMap)) {
                return CoaSegment.CC;
            }

            /** CoA Account */
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.ACCT, crOrDr, rsAjePtrnList, rs, mtMap)) {
                return CoaSegment.ACCT;
            }

            /** CoA Prod */
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.PROD, crOrDr, rsAjePtrnList, rs, mtMap)) {
                return CoaSegment.PROD;
            }

            /** CoA Channel */
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.CH, crOrDr, rsAjePtrnList, rs, mtMap)) {
                return CoaSegment.CH;
            }

            /** CoA Affiliate */
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.AFFL, crOrDr, rsAjePtrnList, rs, mtMap)) {
                return CoaSegment.AFFL;
            }

            /** CoA Project */
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.PROJ, crOrDr, rsAjePtrnList, rs, mtMap)) {
                return CoaSegment.PROJ;
            }

            /** CoA Extension */
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.EXTN, crOrDr, rsAjePtrnList, rs, mtMap)) {
                return CoaSegment.EXTN;
            }

            return CoaSegment.DEF;

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

            // CoA Segment Setting is complete or not.
            if (checkCoaSegmentDetermination) {
                // insert into jrnl entry
                for (JRNL_ENTRYTMsg jrnlEntryRec : lstJrnlEntry) {
                    createJrnlEntry(jrnlEntryRec);
                }
            }

            // Store the result in AJE_INTFC_CTRL.
            AJE_INTFC_CTRLTMsg ajeIntfcCtrl = new AJE_INTFC_CTRLTMsg();
            commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "glblCmpyCd", rs.getString(GLBL_CMPY_CD));
            commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "ajeIntfcTpCd", AJE_INTFC_TP.COST_MANAGEMENT);
            commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "ajeIntfcPk", rs.getBigDecimal(AJE_CM_INTFC_PK));
            commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "procDt", procDt);
            commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "ajePgmId", ajePgmId);
            commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "sysSrcCd", rs.getString(SYS_SRC_CD));
            commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "trxCd", rs.getString(TRX_CD));
            commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "trxRsnCd", rs.getString(TRX_RSN_CD));

            if (!checkCoaSegmentDetermination) {

                commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "jrnlCpltFlg", FLG_OFF_N);
                commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "ajeErrMsgId", errMsgId);
                commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "ajeErrMsgTxt", errMsgTxt);

                errorCount += 1;

                createAjeIntfcCtrl(ajeIntfcCtrl);

            } else {
                commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "jrnlCpltFlg", FLG_ON_Y);

                createAjeIntfcCtrl(ajeIntfcCtrl);

                updateAjeCmIntfcTpCd(rs.getString(GLBL_CMPY_CD), rs.getBigDecimal(CM_ACRL_WRITE_OFF_PK));
            }
        }

        /**
         * <pre>
         *  This is helper function of doProcessQueryResult to make the function smaller
         * </pre>
         * @param tMsgJrnEnt TMsg of Journal Entry that is being
         * generated
         * @param rs result set of OM interface table
         * @param pMsg AJE pattern list
         * @throws SQLException SQL Exception
         */
        private void setPartOfJrnlEntryValues(JRNL_ENTRYTMsg tMsgJrnEnt, ResultSet rs, NFAC000101PMsg pMsg) throws SQLException {

            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "glblCmpyCd", rs.getString(GLBL_CMPY_CD));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajeIntfcTpCd", AJE_INTFC_TP.COST_MANAGEMENT);
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajeIntfcPk", rs.getBigDecimal(AJE_CM_INTFC_PK));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "procDt", procDt);
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "glDt", rs.getString(GL_DT));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajeId", pMsg.ajeId.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "sysSrcCd", rs.getString(SYS_SRC_CD));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "sysSrcNm", rs.getString(SYS_SRC_NM));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "trxCd", rs.getString(TRX_CD));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "trxNm", rs.getString(TRX_NM));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "trxRsnCd", rs.getString(TRX_RSN_CD));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "trxRsnNm", rs.getString(TRX_RSN_NM));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajePtrnIndTpCd_01", pMsg.ajePtrnIndTpCd_01.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajePtrnIndTpNm_01", pMsg.ajePtrnIndTpNm_01.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajePtrnActlCd_01", pMsg.ajePtrnActlCd_01.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajePtrnActlNm_01", pMsg.ajePtrnActlNm_01.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajePtrnIndTpCd_02", pMsg.ajePtrnIndTpCd_02.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajePtrnIndTpNm_02", pMsg.ajePtrnIndTpNm_02.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajePtrnActlCd_02", pMsg.ajePtrnActlCd_02.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajePtrnActlNm_02", pMsg.ajePtrnActlNm_02.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajePtrnIndTpCd_03", pMsg.ajePtrnIndTpCd_03.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajePtrnIndTpNm_03", pMsg.ajePtrnIndTpNm_03.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajePtrnActlCd_03", pMsg.ajePtrnActlCd_03.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajePtrnActlNm_03", pMsg.ajePtrnActlNm_03.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlSrcCd", pMsg.jrnlSrcCd.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlSrcNm", pMsg.jrnlSrcNm.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlCatgCd", pMsg.jrnlCatgCd.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlCatgNm", pMsg.jrnlCatgNm.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajeLineIdxNum", pMsg.ajeLineIdxNum.getValue());
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajeItemCd", rs.getString(MDSE_CD));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajeItemDescTxt", rs.getString(MDSE_NM));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "glSendCpltFlg", FLG_OFF_N);
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "vndCd", rs.getString(VND_CD));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "poOrdNum", rs.getString(PO_NUM));

            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "orclRef10Txt", ORCL_REF_10_TXT_PREFIX + pMsg.ajeId.getValue());

            // First Reference Text : PO Line#
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryFirstRefTxt", rs.getString(PO_ORD_DTL_LINE_NUM));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryFirstAttrbNm", PO_ORD_DTL_LINE_NUM_ATTRB_NM);

            // Inventory Transaction
            List<String> invtyTrxPkList = getInvtyTrxPkList(rs.getString(GLBL_CMPY_CD), rs.getString(PO_NUM), rs.getString(PO_ORD_DTL_LINE_NUM));

            if (!invtyTrxPkList.isEmpty()) {
                if (invtyTrxPkList.size() == 1) {
                    commonJrnlEntry.setFieldValue(tMsgJrnEnt, "invtyTrxPk", new BigDecimal(invtyTrxPkList.get(0)));
                } else {
                    // Second Reference Text : Inventory Transaction
                    // If there is no AP invoice & multiple Inventory Transaction
                    commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryScdRefTxt", getStrInvtyTrxPkList(invtyTrxPkList));
                    commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryScdAttrbNm", INVTY_TRX_SLP_NUM_ATTRB_NM);
                }
            }

            // Second Reference Text : AP Invoice
            if (hasValue(rs.getString(AP_VND_INV_NUM))) {
                commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryScdRefTxt", rs.getString(AP_VND_INV_NUM));
                commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryScdAttrbNm", AP_INV_NUM_ATTRB_NM);
                tMsgJrnEnt.invtyTrxPk.clear();
            }

            // Third Reference Text : Accrual Write Off PK
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryThirdRefTxt", rs.getString(CM_ACRL_WRITE_OFF_PK));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryThirdAttrbNm", CM_ACRL_WRITE_OFF_PK_ATTRB_NM);

        }

        /**
         * <pre>
         *  getInvtyTrxPkList
         * </pre>
         * @param String glblCmpyCd
         * @param String poNum
         * @param String poOrdDtlLineNum
         */
        private List<String> getInvtyTrxPkList(String glblCmpyCd, String poNum, String poOrdDtlLineNum) {

            Map<String, String> map = new HashMap<String, String>();
            map.put("glblCmpyCd", glblCmpyCd);
            map.put("poNum", poNum);
            map.put("poOrdDtlLineNum", poOrdDtlLineNum);

            List<String> invtyTrxPkList = (List<String>) ssmBatchClient.queryObjectList("getInvtyTrxPkList", map);

            return invtyTrxPkList;
        }

        /**
         * <pre>
         *  getStrInvtyTrxPkList
         * </pre>
         * @param List<String> invtyTrxPkList
         */
        private String getStrInvtyTrxPkList(List<String> invtyTrxPkList) {

            String strInvtyTrxPkList = S21StringUtil.toStringList(invtyTrxPkList.toArray(new String[invtyTrxPkList.size()]));

            if (strInvtyTrxPkList.length() > 100) {
                strInvtyTrxPkList = strInvtyTrxPkList.substring(0, 100);
                strInvtyTrxPkList = strInvtyTrxPkList.substring(0, strInvtyTrxPkList.lastIndexOf(","));
            }

            return strInvtyTrxPkList;
        }

        /**
         * <pre>
         *  Create AJE Interface Control.
         * </pre>
         * @param EZDTMsg AJE Interface Control
         * @throws JrnlCommonException JrnlCommonException
         */
        private void createAjeIntfcCtrl(EZDTMsg ajeIntfcCtrl) throws NFACommonJrnlEntry.JrnlCommonException {

            if (ajeIntfcCtrl != null) {
                ctrlMsgCount += 1;
                ctrlMsgs[ctrlMsgCount - 1] = ajeIntfcCtrl;
            } else {
                ctrlMsgs = commonJrnlEntry.changeArraySize(ctrlMsgs, ctrlMsgCount);
            }

            // per 10000 lines
            if (ctrlMsgCount >= BULK_INSERT_COUNT || ajeIntfcCtrl == null) {

                // insert
                int retCnt = S21FastTBLAccessor.insert(ctrlMsgs);

                // if passed records' count and return count don't match, error
                if (retCnt != ctrlMsgCount) {

                    throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
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

            } else { // array may be not full
                jrnlMsgs = commonJrnlEntry.changeArraySize(jrnlMsgs, jrnlMsgCount);
            }

            // per 10000 lines
            if (jrnlMsgCount >= BULK_INSERT_COUNT || jrnlEntry == null) {

                int retCnt = S21FastTBLAccessor.insert(jrnlMsgs);

                // if passed records' count and return count don't match, error
                if (retCnt != jrnlMsgCount) {
                    throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
                }
                commitCount += jrnlMsgCount;
                // initialize
                jrnlMsgCount = 0;
                jrnlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
            }

        }
    }

    private void updateAjeCmIntfcTpCd(String glblCmpyCd, BigDecimal cmAcrlWriteOffPk) {

        CM_ACRL_WRITE_OFFTMsg tmsg = new CM_ACRL_WRITE_OFFTMsg();

        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.cmAcrlWriteOffPk, cmAcrlWriteOffPk);

        tmsg = (CM_ACRL_WRITE_OFFTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

        ZYPEZDItemValueSetter.setValue(tmsg.ajeCmIntfcTpCd, AJE_CM_INTFC_TP.JOURNALIZED);
        S21FastTBLAccessor.update(tmsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            throw new S21AbendException(NFAM0208E, new String[] {"CM_ACRL_WRITE_OFF", cmAcrlWriteOffPk.toString() });
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
