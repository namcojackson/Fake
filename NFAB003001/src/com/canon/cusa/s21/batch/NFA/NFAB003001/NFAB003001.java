package com.canon.cusa.s21.batch.NFA.NFAB003001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

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
import business.db.JRNL_ENTRYTMsg;
import business.parts.MdseTpAcct;
import business.parts.NFAC000101PMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
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

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * This batch journalizes front transactions of REVALUATION and WRITE DOWN.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/14/2009   CSA             H.Naoi          Create          N/A
 * 05/14/2013   CSAI            K.Uramori       Update          Modifiaction for DS. Allow '@MC'.
 * 02/16/2016   CSAI            K.Uramori       Update          Modification for CSA
 * 2016/10/13   Hitachi         K.Kojima        Update          QC#14586
 * 2016/10/14   Hitachi         K.Kojima        Update          QC#14823
 * 2017/07/19   CITS            Y.Iwasaki       Update          QC#19954
 * 09/28/2017   CITS            Y.Fujii         Update          QC#19408
 * 04/23/2018   CITS            K.Ogino         Update          QC#25355
 * 2020/01/13   Fujitsu         Y.Matsui        Update          QC#55294
 * </pre>
 */
public class NFAB003001 extends S21BatchMain implements NFAB003001Constant, ZYPConstant, NFACommonJrnlEntryConstant {

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

    /** Merchandise Type Map */
    private Map<String, List<MdseTpAcct>> mtMap;
    
    /**  Journal Entry Common Module */
    private NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();
    
    /** Loan Inventory Account Code */
    private String loanAcct;

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");
        
        new NFAB003001().executeBatch(NFAB003001.class.getSimpleName());

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
            procDt = ZYPDateUtil.getBatProcDate();
        }

        // initialize
        jrnlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        ctrlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        
     // get COA Project Account Master
        mtMap = commonJrnlEntry.getMdseTpAcct(glblCmpyCd);
        
        // get loan account code
        loanAcct = ZYPCodeDataUtil.getVarCharConstValue(LOAN_INVTY_ACCT_CD, this.glblCmpyCd);

        S21InfoLogOutput.println("initRoutine Method End");

    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        List<Map> rtGetIntfcTrxPtrn = getIntfcTrxPtrn();

        if (rtGetIntfcTrxPtrn.size() == 0) {
            S21InfoLogOutput.println(NFAM0036I, new String[] {MSG_PARAM});
        } else {

            List<Map> rtGetAjeIntfcCtrlListNotJrnlized = commonJrnlEntry.getAjeIntfcKeyListNotJrnlized(this.glblCmpyCd, AJE_INTFC_TP_CD_VAL);

            Boolean checkRemoveProcess = Boolean.TRUE;
            if (rtGetAjeIntfcCtrlListNotJrnlized.size() > 0) {
                checkRemoveProcess = removeAjeIntfcCtrlNotJrnlized(rtGetAjeIntfcCtrlListNotJrnlized);

                if (checkRemoveProcess) {
                    commit();
                } else {
                    //S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg});
                    rollback();
                    throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
                }
            }

            if (checkRemoveProcess) {
                List<Map> rtGetAjePtrn = commonJrnlEntry.getAjePtrn(rtGetIntfcTrxPtrn, this.glblCmpyCd, AJE_INTFC_TP_CD_VAL);

                if (rtGetAjePtrn.size() == 0) {
                    S21InfoLogOutput.println(NFACommonJrnlEntryConstant.NFAM0037E);
                } else {

                    Boolean checkDoJournalProcess = doEntryToJournalEntry(rtGetAjePtrn);

                    if (checkDoJournalProcess != Boolean.TRUE) {
                        //S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg});
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
     *  Get All Transaction Patterns in AJE Merchandise Revaluation Interface.
     * </pre>
     * @param 
     */
    @SuppressWarnings("unchecked")
    private List<Map> getIntfcTrxPtrn() {

        // ** Get All Transaction Pattern in AJE Merchandise Revaluation Interface. **
        Map<String, String>queryParamToGetIntfcTrxPtrn = new HashMap<String, String>();
        queryParamToGetIntfcTrxPtrn.put("glblCmpyCd", this.glblCmpyCd);
        queryParamToGetIntfcTrxPtrn.put("ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
        queryParamToGetIntfcTrxPtrn.put("jrnlCpltFlg", FLG_ON_Y);

        List<Map> ssmResForGetIntfcTrxPtrn = (List<Map>) ssmBatchClient.queryObjectList("getIntfcTrxPtrn", queryParamToGetIntfcTrxPtrn);

        return ssmResForGetIntfcTrxPtrn;

    }

    /**
     * <pre>
     *  Remove AJE Interface Control that is not journalized.
     *  @return boolean true: OK  false: NG
     * </pre>
     */
    private boolean removeAjeIntfcCtrlNotJrnlized(List<Map> ssmResForGetAjeIntfcCtrlKeyNotJrnlized) {

        try {
            for (Map<String, Object> rsAjeIntfcCtrlListNotJrnlized : ssmResForGetAjeIntfcCtrlKeyNotJrnlized) {

                AJE_INTFC_CTRLTMsg ajeIntfcCtrl = new AJE_INTFC_CTRLTMsg();
                String glblCmpyCdStr = (String) rsAjeIntfcCtrlListNotJrnlized.get(GLBL_CMPY_CD);
                String ajeIntfcTpCdStr = (String) rsAjeIntfcCtrlListNotJrnlized.get(AJE_INTFC_TP_CD);
                BigDecimal ajeIntfcPkNum = new BigDecimal(rsAjeIntfcCtrlListNotJrnlized.get(AJE_INTFC_PK).toString());

                commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "glblCmpyCd", glblCmpyCdStr);
                commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "ajeIntfcTpCd", ajeIntfcTpCdStr);
                commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "ajeIntfcPk", ajeIntfcPkNum);

                removeAjeIntfcCtrlNotJrnlizedHelper(ajeIntfcCtrl);

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
     *  Get Target Transactions in AJE Merchandise Revaluation Interface and Journalize.
     * </pre>
     * @param List<Map> Target AJE Pattern List
     */
    @SuppressWarnings("unchecked")
    private Boolean doEntryToJournalEntry(List<Map> ssmResForGetAjePtrn) {

        // ** Get Target Transactions in AJE Merchandise Revaluation Interface. **        
        Map<String, String> queryParamToGetAjeMdseRvalIntfcNotJrnlized = new HashMap<String, String>();
        queryParamToGetAjeMdseRvalIntfcNotJrnlized.put("glblCmpyCd", this.glblCmpyCd);
        queryParamToGetAjeMdseRvalIntfcNotJrnlized.put("ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
        queryParamToGetAjeMdseRvalIntfcNotJrnlized.put("jrnlCpltFlg", FLG_ON_Y);
        queryParamToGetAjeMdseRvalIntfcNotJrnlized.put("prodCdZZ", COA_PROD_CD_VAL_ZZ);
        queryParamToGetAjeMdseRvalIntfcNotJrnlized.put("mdseOrPrtCd", MDSE_OR_PRT_CD_M_VAL);
        queryParamToGetAjeMdseRvalIntfcNotJrnlized.put("anyProdCd", "*");
        // QC#19954 : Added condition "LTST_REC_FLG=Y".
        queryParamToGetAjeMdseRvalIntfcNotJrnlized.put("ltstRecFlg", FLG_ON_Y);
        // START 2016/10/13 K.Kojima [QC#14586,ADD]
        queryParamToGetAjeMdseRvalIntfcNotJrnlized.put("coaProjAcctTp_WIP", COA_PROJ_ACCT_TP.WIP);
        // END 2016/10/13 K.Kojima [QC#14586,ADD]
        // START 2016/10/14 K.Kojima [QC#14823,ADD]
        queryParamToGetAjeMdseRvalIntfcNotJrnlized.put("coaProjAcctTp_CI", COA_PROJ_ACCT_TP.CUST_INTRNST);
        // END 2016/10/14 K.Kojima [QC#14823,ADD]

        Boolean checkEntryToJournalEntry = (Boolean) ssmBatchClient.queryObject("getAjeMdseRvalIntfcNotJrnlized", queryParamToGetAjeMdseRvalIntfcNotJrnlized, new JrnlEntryHandler(ssmResForGetAjePtrn));
        return checkEntryToJournalEntry;
    }

    /**
     * <pre>
     *  Journal Entry Handler.
     *  Jourlize all transacntions with Merchandise Revaluation and update the result in AJE Interface Control Table. 
     *  CoA Segment is derived from AJE Pattern or masters (Org, Merchandise or Customer).
     * </pre>
     */
    private final class JrnlEntryHandler extends S21SsmBooleanResultSetHandlerSupport {

        /** AJE Pattern List */
        private List<Map> ajePtrnList;
        /** Currency Info Getting Process Completion Flag*/
        boolean isComleted = false;
        /** Error Message Id **/
        private String errMsgId = NFACommonJrnlEntryConstant.DEF_ERROR_MSG_ID;
        /** Error Message text **/
        private String errMsgTxt = NFACommonJrnlEntryConstant.DEF_ERROR_MSG_TXT;

        private JrnlEntryHandler(List<Map> ssmResForGetAjePtrn) {
            this.ajePtrnList = ssmResForGetAjePtrn;
        }

        protected Boolean doProcessQueryResult(ResultSet rsAjeIntfcNotJrnlized) throws SQLException {

            try {
                // List to store JRNL_ENTRYTMsg to be inserted to JRNL_ENTRY
                List<JRNL_ENTRYTMsg> lstJrnlEntry = new ArrayList<JRNL_ENTRYTMsg>();

                while (rsAjeIntfcNotJrnlized.next()) {

                    // initialize
                    lstJrnlEntry.clear();

                    // Check Flag for CoA Segment Determination.
                    boolean checkCoaSegmentDetermination = true;

                    // ** AJE Pattern Indicator for Merchandise Revaluation. //
                    // ** AJE Id                                             //
                    // ** Indicator 1 :                                      //
                    // ** Indicator 2 :                                      //
                    // ** Indicator 3 :                                      //
                    String ajeId = commonJrnlEntry.generateAjeId(rsAjeIntfcNotJrnlized.getString(SYS_SRC_CD), rsAjeIntfcNotJrnlized.getString(TRX_CD), rsAjeIntfcNotJrnlized.getString(TRX_RSN_CD));

                    // Get AJE Pattern List by AJE Id and AJE Pattern Indicator type.//
                    List<NFAC000101PMsg> ajePtrnListByAjeIdAndIndTp = commonJrnlEntry.getAJEPtrnByAjeIdAndIndTp(ajeId, rsAjeIntfcNotJrnlized, ajePtrnList);

                    // if no pattern is found, set checkCoaSegmentDetermination false
                    if (ajePtrnListByAjeIdAndIndTp.isEmpty()) {
                        errMsgId = NFACommonJrnlEntryConstant.NFAM0037E;
                        errMsgTxt = NFACommonJrnlEntryConstant.NO_AJE_PTRN_ERROR_MSG_TXT;
                        checkCoaSegmentDetermination = false;
                    }

                    // Journalize by target and AJE Pattern Indicator type.
                    for (NFAC000101PMsg rsAjePtrnList : ajePtrnListByAjeIdAndIndTp) {

                        BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(JRNL_ENTRY_SQ);

                        JRNL_ENTRYTMsg jrnlEntry = new JRNL_ENTRYTMsg();

                        try {

                            commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlEntryPk", seqNum);

                            setPartOfJrnlEntryValues(jrnlEntry, rsAjeIntfcNotJrnlized, rsAjePtrnList);
                            
                            CoaSegment coa = CoaSegment.DEF;
                            
                         // for DR
                            coa = setValuesByCrDr("dr", jrnlEntry, rsAjeIntfcNotJrnlized, rsAjePtrnList);
                            if (coa != CoaSegment.DEF) {
                                errMsgId = NFACommonJrnlEntryConstant.COA_ERROR_MSG_ID;
                                errMsgTxt = NFACommonJrnlEntryConstant.COA_ERROR_MSG_TXT.replace("@", commonJrnlEntry.getCoAMessage(coa));
                                checkCoaSegmentDetermination = false;
                                break;
                            }

                            // for CR
                            coa = setValuesByCrDr("cr", jrnlEntry, rsAjeIntfcNotJrnlized, rsAjePtrnList);
                            if (coa != CoaSegment.DEF) {
                                errMsgId = NFACommonJrnlEntryConstant.COA_ERROR_MSG_ID;
                                errMsgTxt = NFACommonJrnlEntryConstant.COA_ERROR_MSG_TXT.replace("@", commonJrnlEntry.getCoAMessage(coa));
                                checkCoaSegmentDetermination = false;
                                break;
                            }

                            // ** CoA Currency ** //
                            commonJrnlEntry.setFieldValue(jrnlEntry, "dealCcyCd", rsAjeIntfcNotJrnlized.getString(COST_CCY_CD));
                            commonJrnlEntry.setFieldValue(jrnlEntry, "funcCcyCd", rsAjeIntfcNotJrnlized.getString(COST_CCY_CD));
                            commonJrnlEntry.setFieldValue(jrnlEntry, "orclCcyCd", rsAjeIntfcNotJrnlized.getString(ORCL_CCY_CD));
                            
                            // ** Exchange Rate ** //
                            commonJrnlEntry.setFieldValue(jrnlEntry, "exchRate", INITIAL_EXCH_RATE_VAL);
                            commonJrnlEntry.setFieldValue(jrnlEntry, "acctArthTpCd", rsAjeIntfcNotJrnlized.getString(ACCT_ARTH_TP_CD));

                            // ** Quantity ** //
                            commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlQty", rsAjeIntfcNotJrnlized.getBigDecimal(INVTY_QTY));

                            // ** Amount ** //
                            // Debit
                            commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlDealDrAmt", rsAjeIntfcNotJrnlized.getBigDecimal(TOT_RVAL_AMT));
                            commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlFuncDrAmt", rsAjeIntfcNotJrnlized.getBigDecimal(TOT_RVAL_AMT));

                            // Credit
                            commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlDealCrAmt", rsAjeIntfcNotJrnlized.getBigDecimal(TOT_RVAL_AMT));
                            commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlFuncCrAmt", rsAjeIntfcNotJrnlized.getBigDecimal(TOT_RVAL_AMT));

                            commonJrnlEntry.setFieldValue(jrnlEntry, "orclRef10Txt", ORCL_REF_10_TXT_PREFIX + rsAjePtrnList.ajeId.getValue());

                            jrnlEntry.orclAttrb11Txt.clear();

                            commonJrnlEntry.setFieldValue(jrnlEntry, "ajeIntfcCmntTxt", rsAjeIntfcNotJrnlized.getString(AJE_INTFC_CMNT_TXT));

                            // COA_PROD_CD
                            commonJrnlEntry.setFieldValue(jrnlEntry, "coaProdCd", rsAjeIntfcNotJrnlized.getString(COA_PROD_CD));
                            
                            //---- 2016/02/16 CSA mod new attribute fields
                            setValue(jrnlEntry.jrnlEntryFirstRefTxt,  rsAjeIntfcNotJrnlized.getString("RTL_WH_CD"));
                            setValue(jrnlEntry.jrnlEntryFirstAttrbNm,  "Warehouse Code");
                            setValue(jrnlEntry.jrnlEntryScdRefTxt,  rsAjeIntfcNotJrnlized.getString("RTL_SWH_CD"));
                            setValue(jrnlEntry.jrnlEntryScdAttrbNm,  "Sub Warehouse Code");
                            setValue(jrnlEntry.jrnlEntryThirdRefTxt,  rsAjeIntfcNotJrnlized.getString("MDSE_CST_UPD_TP_CD"));
                            setValue(jrnlEntry.jrnlEntryThirdAttrbNm,  "Cost Type");
                            setValue(jrnlEntry.jrnlEntryFrthRefTxt,  rsAjeIntfcNotJrnlized.getString("MDSE_INVTY_COST_PCT"));
                            setValue(jrnlEntry.jrnlEntryFrthAttrbNm,  "Cost %");
                            setValue(jrnlEntry.jrnlEntryFifthRefTxt,  rsAjeIntfcNotJrnlized.getString("LOC_STS_CD"));
                            setValue(jrnlEntry.jrnlEntryFifthAttrbNm,  "Location Status");
                            setValue(jrnlEntry.jrnlEntryRefTxt, commonJrnlEntry.getJrnlEntryRefTxt(jrnlEntry));
                            //---- end 2016/02/16
                            
                        } catch (EZDValidatorException exVal) {
                            checkCoaSegmentDetermination = false;
                            errMsgId = NFACommonJrnlEntryConstant.COA_ERROR_MSG_ID;
                            errMsgTxt = exVal.getMessage();
                            break;

                        } catch (EZDAbendException exEz) {  // null exception
                            checkCoaSegmentDetermination = false;
                            errMsgId = NFACommonJrnlEntryConstant.NULL_ERROR_MSG_ID;
                            errMsgTxt = exEz.getMessage();
                            break;

                        } catch (SQLException sqlEx) {
                            errMsg = sqlEx.getMessage();
                            return Boolean.FALSE;
                        }

                        //  Store into the list
                        if (checkCoaSegmentDetermination) {
                            lstJrnlEntry.add(jrnlEntry);
                        }

                    } // for (AJE_PTRNTMsg rsAjePtrnList : ajePtrnListByAjeIdAndIndTp)

                    insertIntoJrnlOrCtrl(checkCoaSegmentDetermination, rsAjeIntfcNotJrnlized, lstJrnlEntry);


                } // while (rsAjeIntfcNotJrnlized.next())

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
         * @param jrnlEntry TMsg of Journal Entry that is being generated
         * @param rsAjeIntfcNotJrnlized result set of interface table
         * @param rsAjePtrnList AJE pattern list
         * @throws SQLException SQL Exception
         * @throws EZDValidatorException
         */
        private CoaSegment setValuesByCrDr(String crOrDr, JRNL_ENTRYTMsg jrnlEntry, ResultSet rs, NFAC000101PMsg rsAjePtrnList)
        throws SQLException, EZDValidatorException {

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
            // In case of loan inventory, set fixed value.
            
            String ptrnVal;
            if ("dr".equals(crOrDr)) {
                ptrnVal= rsAjePtrnList.drAjeCoaAcctCd.getValue();
            } else {
                ptrnVal= rsAjePtrnList.crAjeCoaAcctCd.getValue();
            }
            // QC#25355
            if ("@MT-INV".equals(ptrnVal) && LOC_STS.TRIAL_USE.equalsIgnoreCase(rs.getString("LOC_STS_CD"))) {
                if ("dr".equals(crOrDr)) {
                    setValue(jrnlEntry.drCoaAcctCd, loanAcct);
                } else {
                    setValue(jrnlEntry.crCoaAcctCd, loanAcct);
                }
                // START 2016/10/13 K.Kojima [QC#14586,ADD] - 2020/01/13 Y.Matsui [QC#55294,MOD]
            } else if ("@MT-INV".equals(ptrnVal) && isInTransitWH(rs.getString("LOC_STS_CD"))) {
                if ("dr".equals(crOrDr)) {
                    setValue(jrnlEntry.drCoaAcctCd, rs.getString(COA_ACCT_CD_WIP));
                } else {
                    setValue(jrnlEntry.crCoaAcctCd, rs.getString(COA_ACCT_CD_WIP));
                }
                // END 2016/10/13 K.Kojima [QC#14586,ADD] - 2020/01/13 Y.Matsui [QC#55294,MOD]
                // START 2016/10/14 K.Kojima [QC#14823,ADD]
            } else if ("@MT-INV".equals(ptrnVal) && LOC_STS.WAITING_FOR_INSTALLATION.equalsIgnoreCase(rs.getString("LOC_STS_CD"))) {
                if ("dr".equals(crOrDr)) {
                    setValue(jrnlEntry.drCoaAcctCd, rs.getString(COA_ACCT_CD_CI));
                } else {
                    setValue(jrnlEntry.crCoaAcctCd, rs.getString(COA_ACCT_CD_CI));
                }
                // END 2016/10/14 K.Kojima [QC#14823,ADD]
            } else {
                if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.ACCT, crOrDr, rsAjePtrnList, rs, mtMap)) {
                    return CoaSegment.ACCT;
                }
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
         *  This is helper function of doProcessQueryResult to make the function smaller
         * </pre>
         * @param jrnlEntry TMsg of Journal Entry that is being generated
         * @param rsAjeIntfcNotJrnlized result set of interface table
         * @param rsAjePtrnList AJE pattern list
         * @throws SQLException SQL Exception
         */
        private void setPartOfJrnlEntryValues(JRNL_ENTRYTMsg jrnlEntry, ResultSet rsAjeIntfcNotJrnlized, NFAC000101PMsg rsAjePtrnList)
        throws SQLException {
            
            commonJrnlEntry.setFieldValue(jrnlEntry, "glblCmpyCd", rsAjeIntfcNotJrnlized.getString(GLBL_CMPY_CD));
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajeIntfcPk", rsAjeIntfcNotJrnlized.getBigDecimal(AJE_MDSE_RVAL_INTFC_PK));
            commonJrnlEntry.setFieldValue(jrnlEntry, "procDt", procDt);
            commonJrnlEntry.setFieldValue(jrnlEntry, "glDt", rsAjeIntfcNotJrnlized.getString(GL_DT));
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajeId", rsAjePtrnList.ajeId.getValue());
            commonJrnlEntry.setFieldValue(jrnlEntry, "sysSrcCd", rsAjeIntfcNotJrnlized.getString(SYS_SRC_CD));
            commonJrnlEntry.setFieldValue(jrnlEntry, "sysSrcNm", rsAjeIntfcNotJrnlized.getString(SYS_SRC_NM));
            commonJrnlEntry.setFieldValue(jrnlEntry, "trxCd", rsAjeIntfcNotJrnlized.getString(TRX_CD));
            commonJrnlEntry.setFieldValue(jrnlEntry, "trxNm", rsAjeIntfcNotJrnlized.getString(TRX_NM));
            commonJrnlEntry.setFieldValue(jrnlEntry, "trxRsnCd", rsAjeIntfcNotJrnlized.getString(TRX_RSN_CD));
            commonJrnlEntry.setFieldValue(jrnlEntry, "trxRsnNm", rsAjeIntfcNotJrnlized.getString(TRX_RSN_NM));
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajePtrnIndTpCd_01", rsAjePtrnList.ajePtrnIndTpCd_01.getValue());
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajePtrnIndTpNm_01", rsAjePtrnList.ajePtrnIndTpNm_01.getValue());
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajePtrnActlCd_01", rsAjePtrnList.ajePtrnActlCd_01.getValue());
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajePtrnActlNm_01", rsAjePtrnList.ajePtrnActlNm_01.getValue());
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajePtrnIndTpCd_02", rsAjePtrnList.ajePtrnIndTpCd_02.getValue());
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajePtrnIndTpNm_02", rsAjePtrnList.ajePtrnIndTpNm_02.getValue());
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajePtrnActlCd_02", rsAjePtrnList.ajePtrnActlCd_02.getValue());
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajePtrnActlNm_02", rsAjePtrnList.ajePtrnActlNm_02.getValue());
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajePtrnIndTpCd_03", rsAjePtrnList.ajePtrnIndTpCd_03.getValue());
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajePtrnIndTpNm_03", rsAjePtrnList.ajePtrnIndTpNm_03.getValue());
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajePtrnActlCd_03", rsAjePtrnList.ajePtrnActlCd_03.getValue());
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajePtrnActlNm_03", rsAjePtrnList.ajePtrnActlNm_03.getValue());
            commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlSrcCd", rsAjePtrnList.jrnlSrcCd.getValue());
            commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlSrcNm", rsAjePtrnList.jrnlSrcNm.getValue());
            commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlCatgCd", rsAjePtrnList.jrnlCatgCd.getValue());
            commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlCatgNm", rsAjePtrnList.jrnlCatgNm.getValue());
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajeLineIdxNum", rsAjePtrnList.ajeLineIdxNum.getValue());
            
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajeItemCd", rsAjeIntfcNotJrnlized.getString(MDSE_CD));
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajeItemDescTxt", rsAjeIntfcNotJrnlized.getString(MDSE_NM));
            
            commonJrnlEntry.setFieldValue(jrnlEntry, "glSendCpltFlg", FLG_OFF_N);

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
                    createJrnlEntry(jrnlEntryRec);
                }

            }

            // Store the result in AJE_INTFC_CTRL.
            AJE_INTFC_CTRLTMsg ajeIntfcCtrl = new AJE_INTFC_CTRLTMsg();
            commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "glblCmpyCd", rs.getString(GLBL_CMPY_CD));
            commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
            commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "ajeIntfcPk", rs.getBigDecimal(AJE_MDSE_RVAL_INTFC_PK));
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

            }
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

            } else {  // array may be not full
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

        // START 2020/01/13 Y.Matsui [QC#55294,ADD]
        private boolean isInTransitWH(String locStsCd) {
            return LOC_STS.IN_TRANSIT_WH.equals(locStsCd) || LOC_STS.WORK_IN_PROCESS_SUBCONTRACT.equals(locStsCd);
        }
        // END 2020/01/13 Y.Matsui [QC#55294,ADD]

    }

    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, errorCount, commitCount + errorCount);

        S21InfoLogOutput.println("termRoutine Method End");

    }

}

