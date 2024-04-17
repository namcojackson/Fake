package com.canon.cusa.s21.batch.NFA.NFAB008001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import parts.common.EZDValidatorException;
import business.db.AJE_INTFC_CTRLTMsg;
import business.db.JRNL_ENTRYTMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFAC000101PMsg;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_ASSET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_LINE_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
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

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * This batch journalizes front transactions of RENTAL DEPRECIATION.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/14/2009   CSA             H.Naoi          Create          N/A
 * 05/20/2013   CSAI            K.Uramori       Update          Modification for DS.
 * 03/15/2016   CSAI            K.Uramori       Update          Modification for CSA
 * 03/28/2016   CSAI            K.Uramori       Update          QC#6101
 * 09/28/2017   CITS            Y.Fujii         Update          QC#19408
 * 2017/11/09   Hitachi         J.Kim           Update          QC#16345
 * 11/27/2017   CITS            T.Wada          Update          QC#21258
 * 2019/08/10   Fujitsu         S.Takami        Update          QC#51897
 * </pre>
 */
public class NFAB008001 extends S21BatchMain implements NFAB008001Constant, ZYPConstant, NFACommonJrnlEntryConstant {

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
    
    /** */
    private String assetAdjPtrn = "";
    /** */
    private String newcore = "";
    /** */
    private String nonNewCoreBr = "";
    /** */
    private String nonNewCoreCc = "";
    /** */
    private List<String> assetAdjPtrnList = new ArrayList<String>();

    /**  Journal Entry Common Module */
    private NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();

    /** QC#21258 */
    private String ajeZeroCratFlg = "";

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        new NFAB008001().executeBatch(NFAB008001.class.getSimpleName());

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
        
        assetAdjPtrn = ZYPCodeDataUtil.getVarCharConstValue("AJE_ASSET_ADJ_PTRN", this.glblCmpyCd);
        divPtrn(assetAdjPtrn);
        newcore = ZYPCodeDataUtil.getVarCharConstValue("AJE_NEW_CORE_CD", this.glblCmpyCd);
        nonNewCoreBr = ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_BR_NONCORE", this.glblCmpyCd);
        nonNewCoreCc = ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_CC_NONCORE", this.glblCmpyCd);

        // QC#21258
        ajeZeroCratFlg = ZYPCodeDataUtil.getVarCharConstValue("AJE_ZERO_CRAT_FLG_ASSET", this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(ajeZeroCratFlg)) {
            ajeZeroCratFlg = ZYPConstant.FLG_ON_Y;
        }

        S21InfoLogOutput.println("initRoutine Method End");

    }

    private void divPtrn(String str) {
        if (str == null || str.equals("")) {
            return;
        }
        
        StringTokenizer st = new StringTokenizer(str, ",");
        
        while (st.hasMoreTokens()) {
            assetAdjPtrnList.add(st.nextToken());
        }

    }
    
    private boolean isAssetAdj(String ptrn) {
        for (String adjPtrn : assetAdjPtrnList) {
            
            if (adjPtrn.equals(ptrn)) {
                return true;
            }
        }
        
        return false;
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
     *  Get All Transaction Patterns in AJE Rental Depreciation Interface.
     * </pre>
     * @param 
     */
    @SuppressWarnings("unchecked")
    private List<Map> getIntfcTrxPtrn() {

        // ** Get All Transaction Pattern in AJE Rental Depreciation Interface. **
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
     *  @throws JrnlCommonException JrnlCommonException
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
     *  Get Target Transactions in AJE Rental Depreciation Interface and Journalize.
     * </pre>
     * @param List<Map> Target AJE Pattern List
     */
    @SuppressWarnings("unchecked")
    private Boolean doEntryToJournalEntry(List<Map> ssmResForGetAjePtrn) {

        // ** Get Target Transactions in AJE Rental Depreciation Interface. **        
        Map<String, String> queryParamToGetAjeRentalDepcIntfcNotJrnlized = new HashMap<String, String>();
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("glblCmpyCd", this.glblCmpyCd);
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("jrnlCpltFlg", FLG_ON_Y);
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("invtyIndTp", INV_INVTY_IND_TP_VAL_C);
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("prodCdZ", COA_PROD_CD_VAL_Z);
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("afflCd000", COA_AFFL_CD_VAL_000);
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("prodCdZZ", COA_PROD_CD_VAL_ZZ);
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("proj0000", COA_PROJ_CD_DEFAULT);

        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("mdseOrPrtCd", MDSE_OR_PRT_CD_M_VAL);
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("anyProdCd", ANY_PROD_CD);     
        
        // CSA New
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("newcore", this.newcore);
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("defBrNewcore", this.nonNewCoreBr);
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("defCcNewcore", this.nonNewCoreCc);
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("flgY", FLG_ON_Y);
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("batProcDt", this.procDt);
        // START 2016/11/10 J.Kim [QC#16345,ADD]
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("assetTpE", ASSET_TP.EMSD_ASSET);
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("assetTpEmsdPo", AJE_ASSET_TP.EMSD_PO);
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("assetTpEmsdOrd", AJE_ASSET_TP.EMSD_ORD);
        queryParamToGetAjeRentalDepcIntfcNotJrnlized.put("assetTpRe", AJE_ASSET_TP.RE);
        // END 2016/11/10 J.Kim [QC#16345,ADD]

        Boolean checkEntryToJournalEntry = (Boolean) ssmBatchClient.queryObject("getAjeIntfcNotJrnlized", queryParamToGetAjeRentalDepcIntfcNotJrnlized, new JrnlEntryHandler(ssmResForGetAjePtrn));
        return checkEntryToJournalEntry;
    }

    /**
     * <pre>
     *  Journal Entry Handler.
     *  Jourlize all transacntions with Rental Depreciation and update the result in AJE Interface Control Table. 
     *  CoA Segment is derived from AJE Pattern or masters (Org, Merchandise or Customer).
     * </pre>
     */
    private final class JrnlEntryHandler extends S21SsmBooleanResultSetHandlerSupport {

        /** AJE Pattern List */
        private List<Map> ajePtrnList;
        /** Funcional Currency Code */
        private String funcCcyCd;
        /** Oracle Currency Code */
        private String orclCcyCd;
        /** Accounting Arithmeric Type Code */
        private String acctArthTpCd;
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

                while (rsAjeIntfcNotJrnlized.next()) {

                    // initialize
                    lstJrnlEntry.clear();

                    // Check Flag for CoA Segment Determination.
                    boolean checkCoaSegmentDetermination = true;

                    // ** AJE Pattern Indicator for Rental Depreciation. //
                    // ** AJE Id                                         //
                    // ** Indicator 1 : Trial Machine Indicator.         //
                    // ** Indicator 2 :                                  //
                    // ** Indicator 3 :                                  //
                    String ajeId = commonJrnlEntry.generateAjeId(rsAjeIntfcNotJrnlized.getString(SYS_SRC_CD), rsAjeIntfcNotJrnlized.getString(TRX_CD), rsAjeIntfcNotJrnlized.getString(TRX_RSN_CD));

                    // Get AJE Pattern List by AJE Id and AJE Pattern Indicator type.//
                    List<NFAC000101PMsg> ajePtrnListByAjeIdAndIndTp = commonJrnlEntry.getAJEPtrnByAjeIdAndIndTp(ajeId, rsAjeIntfcNotJrnlized, ajePtrnList);

                    // if no pattern is found, set checkCoaSegmentDetermination false
                    if (ajePtrnListByAjeIdAndIndTp.isEmpty()) {
                        errMsgId = NFACommonJrnlEntryConstant.NFAM0037E;
                        errMsgTxt = NFACommonJrnlEntryConstant.NO_AJE_PTRN_ERROR_MSG_TXT;
                        checkCoaSegmentDetermination = false;
                    }

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

                            commonJrnlEntry.setFieldValue(jrnlEntry, "ajeItemCd", rsAjeIntfcNotJrnlized.getString(MDSE_CD));
                            commonJrnlEntry.setFieldValue(jrnlEntry, "ajeItemDescTxt", rsAjeIntfcNotJrnlized.getString(MDSE_NM));

                            // ** CoA Currency ** //
                            commonJrnlEntry.setFieldValue(jrnlEntry, "dealCcyCd", this.funcCcyCd);
                            commonJrnlEntry.setFieldValue(jrnlEntry, "funcCcyCd", this.funcCcyCd);
                            commonJrnlEntry.setFieldValue(jrnlEntry, "orclCcyCd", this.orclCcyCd);

                            // ** Exchange Rate ** //
                            commonJrnlEntry.setFieldValue(jrnlEntry, "exchRate", INITIAL_EXCH_RATE_VAL);
                            commonJrnlEntry.setFieldValue(jrnlEntry, "acctArthTpCd", this.acctArthTpCd);

                            // ** Quantity ** //
                            //---- start 2016/05/24 Flip quantity by the flag in AJE pattern
                            String flipFlg = rsAjePtrnList.jrnlQtyFlipFlg.getValue();
                            
                            BigDecimal multi = BigDecimal.ONE;
                            
                            if (FLG_ON_Y.equals(flipFlg)) {
                                multi = multi.negate();
                            }
                            
                            commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlQty", rsAjeIntfcNotJrnlized.getBigDecimal(INV_QTY).multiply(multi));
                            //---- end 2016/05/24

                            // additional info
                            commonJrnlEntry.setFieldValue(jrnlEntry, "billToCustCd", rsAjeIntfcNotJrnlized.getString(BILL_TO_CUST_CD));
                            
                            if (setAmtOfJrnlEntryValues(ajeId, jrnlEntry, rsAjeIntfcNotJrnlized, rsAjePtrnList) == Boolean.FALSE) {
                                continue;  // go to next aje pattern
                            }
                            
                            commonJrnlEntry.setFieldValue(jrnlEntry, "orclRef10Txt", ORCL_REF_10_TXT_PREFIX + rsAjePtrnList.ajeId.getValue());
                            jrnlEntry.orclAttrb11Txt.clear();

                            commonJrnlEntry.setFieldValue(jrnlEntry, "ajeIntfcCmntTxt", rsAjeIntfcNotJrnlized.getString(AJE_INTFC_CMNT_TXT));

                            // COA_PROD_CD
                            commonJrnlEntry.setFieldValue(jrnlEntry, "coaProdCd", rsAjeIntfcNotJrnlized.getString("ITEM_PROD_CD"));
                            // TOC_CD
                            commonJrnlEntry.setFieldValue(jrnlEntry, "tocCd", rsAjeIntfcNotJrnlized.getString(TOC_CD));
                            // INV_NUM
                            commonJrnlEntry.setFieldValue(jrnlEntry, "ajeInvNum", rsAjeIntfcNotJrnlized.getString(INV_NUM));
                            
                            // new references
                            setValue(jrnlEntry.vndCd, rsAjeIntfcNotJrnlized.getString("VND_CD"));
                            setValue(jrnlEntry.dsAcctNum, rsAjeIntfcNotJrnlized.getString("SELL_TO_CUST_CD"));
                            setValue(jrnlEntry.shipToCustCd, rsAjeIntfcNotJrnlized.getString("SHIP_TO_CUST_CD"));
                            setValue(jrnlEntry.serNum, rsAjeIntfcNotJrnlized.getString("SER_NUM"));
                            setValue(jrnlEntry.svcMachMstrPk, rsAjeIntfcNotJrnlized.getBigDecimal("SVC_MACH_MSTR_PK"));
                            setValue(jrnlEntry.cpoOrdNum, rsAjeIntfcNotJrnlized.getString("CPO_ORD_NUM"));
                            
                            setValue(jrnlEntry.jrnlEntryFirstRefTxt, rsAjeIntfcNotJrnlized.getString("SLD_TO_CUST_LOC_CD"));
                            setValue(jrnlEntry.jrnlEntryFirstAttrbNm, "Sold To Location");
                            setValue(jrnlEntry.jrnlEntryScdRefTxt, rsAjeIntfcNotJrnlized.getString("SHIP_TO_CUST_ACCT_CD"));
                            setValue(jrnlEntry.jrnlEntryScdAttrbNm, "Ship To Customer Account");
                            setValue(jrnlEntry.jrnlEntryThirdRefTxt, rsAjeIntfcNotJrnlized.getString("PO_ORD_NUM"));
                            setValue(jrnlEntry.jrnlEntryThirdAttrbNm, "PO Number");
                            setValue(jrnlEntry.jrnlEntryFrthRefTxt, rsAjeIntfcNotJrnlized.getString("RTRN_WH_CD"));
                            setValue(jrnlEntry.jrnlEntryFrthAttrbNm, "Return WH Code");
                            setValue(jrnlEntry.jrnlEntryFifthRefTxt, rsAjeIntfcNotJrnlized.getBigDecimal("DS_ASSET_TRX_PK").toString());
                            //setValue(jrnlEntry.jrnlEntryFifthAttrbNm, "DS_ASSET_TRX_PK");
                            
                            setValue(jrnlEntry.prntDsAssetMstrPk, rsAjeIntfcNotJrnlized.getBigDecimal("PRNT_DS_ASSET_MSTR_PK"));
                            
                            setValue(jrnlEntry.jrnlEntryRefTxt, commonJrnlEntry.getJrnlEntryRefTxt(jrnlEntry));

                            // START 2019/08/10 S.Takami [QC#51897,ADD]
                            setValue(jrnlEntry.svcConfigMstrPk, rsAjeIntfcNotJrnlized.getBigDecimal(SVC_CONFIG_MSTR_PK));
                            // END 2019/08/10 S.Takami [QC#51897,ADD]
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

                        // Store into the list
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
                    
                    //---- start add 2016/07/19 when amount is zero, not to create journal entry
                	// QC#21258
//                    if (BigDecimal.ZERO.compareTo(jrnlEntryRec.jrnlDealDrAmt.getValue()) == 0) {
                        if (BigDecimal.ZERO.compareTo(jrnlEntryRec.jrnlDealDrAmt.getValue()) == 0 && !ZYPConstant.FLG_ON_Y.equals(ajeZeroCratFlg)) {
                        continue;
                    }
                    //----end 2016/07/19
                    
                    createJrnlEntry(jrnlEntryRec);
                }

            }

            // Store the result in AJE_INTFC_CTRL.
            AJE_INTFC_CTRLTMsg ajeIntfcCtrl = new AJE_INTFC_CTRLTMsg();
            commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "glblCmpyCd", rs.getString(GLBL_CMPY_CD));
            commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
            commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "ajeIntfcPk", rs.getBigDecimal(AJE_ASSET_TRX_INTFC_PK));
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
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.CMPY, crOrDr, rsAjePtrnList, rs, null)) {    
                return CoaSegment.CMPY;
            }
            
            /** CoA Branch */
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.BR, crOrDr, rsAjePtrnList, rs, null)) {
                return CoaSegment.BR;
            }
            
            /** CoA Cos tCenter */
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.CC, crOrDr, rsAjePtrnList, rs, null)) {
                return CoaSegment.CC;
            }
            
            /** CoA Account */
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.ACCT, crOrDr, rsAjePtrnList, rs, null)) {
                return CoaSegment.ACCT;
            }
            
            /** CoA Prod */
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.PROD, crOrDr, rsAjePtrnList, rs, null)) {
                return CoaSegment.PROD;
            }
            
            /** CoA Channel */
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.CH, crOrDr, rsAjePtrnList, rs, null)) {
                return CoaSegment.CH;
            }
            
            /** CoA Affiliate */
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.AFFL, crOrDr, rsAjePtrnList, rs, null)) {
                return CoaSegment.AFFL;
            }
            
            /** CoA Project */
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.PROJ, crOrDr, rsAjePtrnList, rs, null)) {
                return CoaSegment.PROJ;
            }
            
            /** CoA Extension */
            if (!commonJrnlEntry.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.EXTN, crOrDr, rsAjePtrnList, rs, null)) {
                return CoaSegment.EXTN;
            }
            
            return CoaSegment.DEF;        }
        
        /**
         * <pre>
         *  This is helper function of doProcessQueryResult to make the function smaller
         * </pre>
         * @param ajeId AJE ID
         * @param jrnlEntry TMsg of Journal Entry that is being generated
         * @param rsAjeIntfcNotJrnlized result set of OM interface table
         * @param rsAjePtrnList AJE pattern list
         * @return boolean true: generate journal record  false: not generate journal record 
         * @throws SQLException SQL Exception
         */
        private boolean setAmtOfJrnlEntryValues(String ajeId, JRNL_ENTRYTMsg jrnlEntry, ResultSet rs, NFAC000101PMsg rsAjePtrnList)
        throws SQLException {

        	// Accumulate Depreciation line
            if (AJE_LINE_IND_TP.ACCUM_DEPR_23.equals(rsAjePtrnList.ajeLineIndTpCd.getValue())) {
                
                // Original - Current Book Value
                BigDecimal accumAmt = rs.getBigDecimal(ORIG_VAL_AMT).subtract(rs.getBigDecimal(CUR_VAL_AMT));
                
                // if depc amt is 0, don't have to make journal data
                if (BigDecimal.ZERO.compareTo(accumAmt) == 0) {
                    return false;
                }
                
                // Debit
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlDealDrAmt", accumAmt);
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlFuncDrAmt", accumAmt);

                // Credit
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlDealCrAmt", accumAmt);
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlFuncCrAmt", accumAmt);
                
             // Cost Of Sales Line
            } else if (AJE_LINE_IND_TP.COST_OF_SALES_24.equals(rsAjePtrnList.ajeLineIndTpCd.getValue())) {
                // Debit
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlDealDrAmt", rs.getBigDecimal(CUR_VAL_AMT));
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlFuncDrAmt", rs.getBigDecimal(CUR_VAL_AMT));

                // Credit
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlDealCrAmt", rs.getBigDecimal(CUR_VAL_AMT));
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlFuncCrAmt", rs.getBigDecimal(CUR_VAL_AMT));
            
            // Depreciation
            } else if (TRX.DEPRECIATION.equals(rs.getString("TRX_CD"))){
            	// Debit
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlDealDrAmt", rs.getBigDecimal(CUR_DEPC_AMT));
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlFuncDrAmt", rs.getBigDecimal(CUR_DEPC_AMT));

                // Credit
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlDealCrAmt", rs.getBigDecimal(CUR_DEPC_AMT));
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlFuncCrAmt", rs.getBigDecimal(CUR_DEPC_AMT));
                
            // Adjustment
            //---- start 2016/03/28 QC#6101 Adjustment needs to be identified by AJE_LINE_IND_TP
            //} else if (isAssetAdj(rsAjePtrnList.trxCd.getValue().concat("-").concat(rsAjePtrnList.trxRsnCd.getValue()))) {
            } else if (AJE_LINE_IND_TP.ADJ_29.equals(rsAjePtrnList.ajeLineIndTpCd.getValue())) {
            //---- end 2016/03/28
             // Debit
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlDealDrAmt", rs.getBigDecimal(ASSET_ADJ_AMT));
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlFuncDrAmt", rs.getBigDecimal(ASSET_ADJ_AMT));

                // Credit
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlDealCrAmt", rs.getBigDecimal(ASSET_ADJ_AMT));
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlFuncCrAmt", rs.getBigDecimal(ASSET_ADJ_AMT));
                
            // Others
            } else {
             // Debit
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlDealDrAmt", rs.getBigDecimal(ORIG_VAL_AMT));
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlFuncDrAmt", rs.getBigDecimal(ORIG_VAL_AMT));

                // Credit
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlDealCrAmt", rs.getBigDecimal(ORIG_VAL_AMT));
                commonJrnlEntry.setFieldValue(jrnlEntry, "jrnlFuncCrAmt", rs.getBigDecimal(ORIG_VAL_AMT));
            }
            
            
            //---- start 2016/05/24 Flip amount by the flag in AJE pattern
            String flipFlg = rsAjePtrnList.jrnlAmtFlipFlg.getValue();
            
            BigDecimal multi = BigDecimal.ONE;
            
            if (FLG_ON_Y.equals(flipFlg)) {
                multi = multi.negate();
            }
            
            // Debit
            setValue(jrnlEntry.jrnlDealDrAmt, jrnlEntry.jrnlDealDrAmt.getValue().multiply(multi));
            setValue(jrnlEntry.jrnlFuncDrAmt, jrnlEntry.jrnlFuncDrAmt.getValue().multiply(multi));

            // Credit
            setValue(jrnlEntry.jrnlDealCrAmt, jrnlEntry.jrnlDealCrAmt.getValue().multiply(multi));
            setValue(jrnlEntry.jrnlFuncDrAmt, jrnlEntry.jrnlFuncDrAmt.getValue().multiply(multi));
            
            //---- end 2016/05/24
            
           return true;
            
        }
            
        /**
         * <pre>
         *  This is helper function of doProcessQueryResult to make the function smaller
         * </pre>
         * @param jrnlEntry TMsg of Journal Entry that is being generated
         * @param rsAjeIntfcNotJrnlized result set of OM interface table
         * @param rsAjePtrnList AJE pattern list
         * @throws SQLException SQL Exception
         */
        private void setPartOfJrnlEntryValues(JRNL_ENTRYTMsg jrnlEntry, ResultSet rsAjeIntfcNotJrnlized, NFAC000101PMsg rsAjePtrnList)
        throws SQLException {
            
            commonJrnlEntry.setFieldValue(jrnlEntry, "glblCmpyCd", rsAjeIntfcNotJrnlized.getString(GLBL_CMPY_CD));
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
            commonJrnlEntry.setFieldValue(jrnlEntry, "ajeIntfcPk", rsAjeIntfcNotJrnlized.getBigDecimal(AJE_ASSET_TRX_INTFC_PK));
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

            commonJrnlEntry.setFieldValue(jrnlEntry, "glSendCpltFlg", FLG_OFF_N);
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
              ctrlMsgs[ctrlMsgCount - 1 ] = ajeIntfcCtrl;
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
    }
    
    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, errorCount, commitCount + errorCount);

        S21InfoLogOutput.println("termRoutine Method End");

    }

}
