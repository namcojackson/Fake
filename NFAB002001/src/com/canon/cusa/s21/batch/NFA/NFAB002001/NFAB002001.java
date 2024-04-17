package com.canon.cusa.s21.batch.NFA.NFAB002001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_CRS_SVC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_FM_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_GENL_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_LINE_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_INBD_OTBD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SLD_PRT_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_SRC_TP;
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

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Inventory Journal Creation
 * // This batch journalizes front transactions of LOAN DEPRECIATION.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/20/2009   CSA             N.Sasaki        Create          N/A
 * 05/13/2013   CSAI            K.Uramori       Update          Modification for DS. Allow '@MC'.
 * 11/18/2015   CSAI            K.Uramori       Update          Modification for CSA
 * 01/27/2016   CSAI            K.Uramori       Update          at WH -> at MT
 * 04/15/2016   CSAI            K.Uramori       Update          QC#7161
 * 04/18/2016   CSAI            K.Uramori       Update          QC#7208
 * 05/26/2016   CSAI            K.Uramori       Update          QC#9122
 * 11/28/2016   Hitachi         E.Kameihsi      Update          QC#16133
 * 12/05/2016   Hitachi         E.Kameihsi      Update          QC#16148
 * 09/22/2017   Fujitsu         M.Ohno          Update          QC#17116(L3)
 * 09/28/2017   Fujitsu         M.Ohno          Update          QC#19408
 * 10/11/2017   CITS            T.Kikuhara      Update          QC#20771
 * 11/27/2017   CITS            T.Wada          Update          QC#21258
 * 12/04/2017   Hitachi         J.Kim           Update          QC#18127
 * 01/16/2018   Hitachi         E.Kameishi      Update          QC#23191
 * 02/08/2018   Hitachi         E.Kameishi      Update          QC#23191
 * 01/10/2019   Hitachi         E.Kameishi      Update          QC#29734
 * 02/19/2019   Hitachi         E.Kameishi      Update          QC#30262
 * 2019/08/10   Fujitsu         S.Takami        Update          QC#51897
 * 2019/09/05   Fujitsu         S.Takami        Update          QC#53266
 * </pre>
 */
public class NFAB002001 extends S21BatchMain implements NFAB002001Constant, ZYPConstant, NFACommonJrnlEntryConstant {

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
    private NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();
    
    /** Process Date */
    private Map<String, List<MdseTpAcct>> mtMap;
    
    /** First Prod Ctrl Code of PPS */
    private String frstProdCtrlPPS;
    
    /** newcore code */
    private String newcore;
    
    /** Default COA_BR_CD for newcore */
    private String nonNewCoreBr;
    
    /** Default COA_CC_CD for newcore */
    private String nonNewCoreCc;

    // START 2019/01/09 E.Kameishi [QC#29734,ADD]
    /** Default MT for Sold Parts */
    private List<String> sldPrtMdseTpList;
    // END 2019/01/09 E.Kameishi [QC#29734,ADD]

    /** QC#21258 */
    private String ajeZeroCratFlg = "";

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        new NFAB002001().executeBatch(NFAB002001.class.getSimpleName());

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
        
        this.frstProdCtrlPPS = ZYPCodeDataUtil.getVarCharConstValue(CONST_PPS_FIRST_PROD_CTRL, this.glblCmpyCd);
        this.newcore = ZYPCodeDataUtil.getVarCharConstValue("AJE_NEW_CORE_CD", this.glblCmpyCd);
        this.nonNewCoreBr = ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_BR_NONCORE", this.glblCmpyCd);
        this.nonNewCoreCc = ZYPCodeDataUtil.getVarCharConstValue("AJE_DEF_COA_CC_NONCORE", this.glblCmpyCd);

        // QC#21258
        ajeZeroCratFlg = ZYPCodeDataUtil.getVarCharConstValue("AJE_ZERO_CRAT_FLG_INVTY", this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(ajeZeroCratFlg)) {
            ajeZeroCratFlg = ZYPConstant.FLG_ON_Y;
        }
        // START 2019/01/09 E.Kameishi [QC#29734,ADD]
        String sldPrtMdseTp = ZYPCodeDataUtil.getVarCharConstValue("SLD_PRT_MDSE_TP_CD", this.glblCmpyCd);
        sldPrtMdseTpList = new ArrayList<String>();

        if (sldPrtMdseTp != null) {
            String[] varsldPrtMdseTpList = sldPrtMdseTp.split(",");
            sldPrtMdseTpList = Arrays.asList(varsldPrtMdseTpList);
        }
        // END 2019/01/09 E.Kameishi [QC#29734,ADD]

        S21InfoLogOutput.println("initRoutine Method End");
    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        List<Map> listTrxPtrn = getTrxPtrnInvtyIntFc();

        if (listTrxPtrn.size() == 0) {
            S21InfoLogOutput.println(NFAM0036I, new String[] {MSG_PARAM });
        } else {

            List<Map> listNotJrnlized = commonJrnlEntry.getAjeIntfcKeyListNotJrnlized(this.glblCmpyCd, AJE_INTFC_TP_CD_VAL);

            if (listNotJrnlized.size() > 0) {
                
                if (removeIntFcNotJrnlized(listNotJrnlized)) {
                    commit();
                } else {
                    rollback();
                    throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
                }
            }

            List<Map> listAjePtrn = commonJrnlEntry.getAjePtrn(listTrxPtrn, this.glblCmpyCd, AJE_INTFC_TP_CD_VAL);

            if (listAjePtrn.size() == 0) {
                S21InfoLogOutput.println(NFACommonJrnlEntryConstant.NFAM0037E);
            } else {

                if (!doEntryToJournalEntry(listAjePtrn)) {
                    rollback();
                    throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
                } else {
                    commit();
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
    private List<Map> getTrxPtrnInvtyIntFc() {

        // ** Get All Transaction Pattern in AJE Loan Depreciation
        // Interface. **
        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
        map.put("jrnlCpltFlg", FLG_ON_Y);

        List<Map> listTrxPtrn = (List<Map>) ssmBatchClient.queryObjectList("getIntfcTrxPtrn", map);

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
     *  Get Target Transactions in AJE Loan Depreciation Interface and Journalize.
     * </pre>
     * 
     * @param List<Map> Target AJE Pattern List
     */
    @SuppressWarnings("unchecked")
    private Boolean doEntryToJournalEntry(List<Map> ajePtrnList) {

        // ** Get Target Transactions in AJE Loan Depreciation
        // Interface. **
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
        map.put("jrnlCpltFlg", FLG_ON_Y);
        map.put("batchProcDt", procDt);
        //---- start 2016/04/15 QC#7161
        map.put("poAcctTpAcrl", PO_ACCT_TP.ACCRUAL);
        //---- end 2016/04/15
        map.put("flgY", FLG_ON_Y);
        //---- start add 2016/03/28
        map.put("pps", frstProdCtrlPPS);
        map.put("newcore", this.newcore);
        map.put("defBrNewcore", this.nonNewCoreBr);
        map.put("defCcNewcore", this.nonNewCoreCc);
        //---- end 2016/03/28
        //---- start 2016/04/18 QC#7208
        map.put("rentalShip", TRX.RENTAL_SHIPMENT);
        map.put("rental", ASSET_TP.RENTAL_ASSET);
        map.put("fm", ASSET_TP.EMSD_ASSET);
        //---- end 2016/04/18
        //---- start 2016/04/18 QC#9122
        map.put("adjustment", TRX.ADJUSTMENT);
        //----end 2016/05/26
        //START 2016/11/28 E.Kameishi [QC#16133,ADD]
        map.put("accrual", COA_PROJ_ACCT_TP.ACCRUAL);
        //END 2016/11/28 E.Kameishi [QC#16133,ADD]
        //START 2016/12/05 E.Kameishi [QC#16148,ADD]
        map.put("bsd", DS_CONTR_CLS.BSD);
        // QC#20771 UPD START
        map.put("fmIndTp", AJE_FM_IND_TP.FM);
        // QC#20771 UPD END
        map.put("nonFm", AJE_FM_IND_TP.NON_FM);
        map.put("crossSvc", SVC_CALL_SRC_TP.CROSS_SERVICE);
        map.put("cs", AJE_CRS_SVC_TP.CS);
        map.put("nonCs", AJE_CRS_SVC_TP.NON_CS);
        //END 2016/12/05 E.Kameishi [QC#16148,ADD]
        //---- start 2017/09/22 QC#17116
        map.put("flt", DS_CONTR_CATG.FLEET);
        //---- end 2017/09/22 QC#17116
        // START 2017/12/04 J.Kim [QC#18127,ADD]
        map.put("exch", ASSET_INBD_OTBD_TP.EXCH);
        map.put("nonExch", ASSET_INBD_OTBD_TP.NON_EXCH);
        map.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.SERVICE_EXCHANGE_ORDER_VALUE_SET);
        // END 2017/12/04 J.Kim [QC#18127,ADD]
        // START 2017/12/15 J.Kim [QC#22911,ADD]
        map.put("configInd", AJE_GENL_IND_TP.CONFIG_INDICATOR);
        map.put("none", AJE_GENL_IND_TP.NONE);
        // END 2017/12/15 J.Kim [QC#22911,ADD]
        // START 2018/01/16 E.Kameishi [QC#23191,ADD]
        map.put("fmAccts", DS_ACCT_CLS.FM_ACCTS);
        map.put("emsd",  LINE_BIZ_ROLE_TP.EMSD);
        // END 2018/01/16 E.Kameishi [QC#23191,ADD]
        // START 2019/01/09 E.Kameishi [QC#29734,ADD]
        map.put("sldPrtMdseTp", this.sldPrtMdseTpList);
        map.put("sldPrt", SLD_PRT_IND_TP.SOLD_PARTS);
        map.put("nonSldPrt", SLD_PRT_IND_TP.NON_SOLD_PARTS);
        // END 2019/01/09 E.Kameishi [QC#29734,ADD]
        // START 2019/02/19 E.Kameishi [QC#30262,ADD]
        map.put("flgY", ZYPConstant.FLG_ON_Y);
        // END 2019/02/19 E.Kameishi [QC#30262,ADD]
        // START 2019/09/05 S.Takami [QC#53266,ADD]
        map.put("lineBizEss", LINE_BIZ_TP.ESS);
        map.put("lineBizLfs", LINE_BIZ_TP.LFS);
        map.put("lineBizPps", LINE_BIZ_TP.PPS);
        // END 2019/09/05 S.Takami [QC#53266,ADD]

        Boolean checkJournalEntry = (Boolean) ssmBatchClient.queryObject("getAjeInvtyIntfcNotJrnlized", map, new JrnlEntryHandler(ajePtrnList));
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
            if (this.funcCcyCd == null  && this.isComleted == false) {
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
                        commonJrnlEntry.setFieldValue(tMsgJrnEnt, "dealCcyCd", rs.getString(CCY_CD));
                        commonJrnlEntry.setFieldValue(tMsgJrnEnt, "funcCcyCd", funcCcyCd);
                        commonJrnlEntry.setFieldValue(tMsgJrnEnt, "orclCcyCd", rs.getString(ORCL_CCY_CD));

                        /** Exchange Rate */
                        commonJrnlEntry.setFieldValue(tMsgJrnEnt, "exchRate", INITIAL_EXCH_RATE_VAL);
                        commonJrnlEntry.setFieldValue(tMsgJrnEnt, "acctArthTpCd", rs.getString(ACCT_ARTH_TP_CD));

                        /** Quantity */
                        
                        BigDecimal jrnlQty = rs.getBigDecimal(INVTY_TRX_QTY);
                        
                        // Flip Flag
                        if (FLG_ON_Y.equals(pMsg.jrnlQtyFlipFlg.getValue())) {
                            jrnlQty = jrnlQty.negate();
                        }
                        
                        commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlQty", jrnlQty);

                        /** Amount */
                        BigDecimal jrnlAmt = getJournalAmount(pMsg, rs);
                        
                        // Debit
                        commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlDealDrAmt", jrnlAmt);
                        commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlFuncDrAmt", jrnlAmt);

                        // Credit
                        commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlDealCrAmt", jrnlAmt);
                        commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlFuncCrAmt", jrnlAmt);
                        
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

        private BigDecimal getJournalAmount(NFAC000101PMsg pMsg, ResultSet rs) throws SQLException{
            
         // AJE_LINE_IDX_IND_TP_CD will be needed
            String ajeLineIdxIndTpCd = pMsg.ajeLineIndTpCd.getValue();
            BigDecimal jrnlAmt = BigDecimal.ZERO;
            String flipFlg = pMsg.jrnlAmtFlipFlg.getValue();
            
            //---- start mod 2016/03/16 use AJE_LINE_IND_TP class
            if (ajeLineIdxIndTpCd != null && AJE_LINE_IND_TP.ACCRUAL_22.equals(ajeLineIdxIndTpCd)) {
              //---- start 2016/07/27 QC#12416 PRCH_PRC_AMT holds unit amount.
                // accrual
                jrnlAmt = rs.getBigDecimal(PRCH_PRC_AMT).multiply(rs.getBigDecimal(INVTY_TRX_QTY));
              //---- end 2016/07/27 QC#12416
                
            } else if (ajeLineIdxIndTpCd != null && AJE_LINE_IND_TP.VARIANCE_25.equals(ajeLineIdxIndTpCd)) {
                if (TRX.PURCHASE_STOCK_IN.equals(pMsg.trxCd.getValue())){
                    //---- start 2016/07/27 QC#12416 PPV to be calculated by unit price
                    // PPV:  (Std Cost(Unit) - Purchase Price(Unit)) * Qty
                    jrnlAmt = (rs.getBigDecimal(UNIT_COST_AMT).add(rs.getBigDecimal(PRCH_PRC_AMT).negate())).multiply(rs.getBigDecimal(INVTY_TRX_QTY));
                    //---- end 2016/07/27 QC#12416
                } if (TRX.MOVEMENT.equals(pMsg.trxCd.getValue())){
                    // Showroom Variance: WIP(Std Cost of Ship From) - Std Cost of Destination
                    jrnlAmt = rs.getBigDecimal(SHIP_FROM_AMT).add(rs.getBigDecimal(SHIP_COST_AMT).negate());
                }
            //---- start 2016/05/24  remove  
            //} else if (ajeLineIdxIndTpCd != null && AJE_LINE_IND_TP.SHIP_FROM_COST_26.equals(ajeLineIdxIndTpCd)) {
            //    jrnlAmt = rs.getBigDecimal(SHIP_FROM_AMT).add(rs.getBigDecimal(SHIP_COST_AMT).negate());
            //---- end 2016/05/24    
            } else if (ajeLineIdxIndTpCd != null && AJE_LINE_IND_TP.REMAN_WIP_27.equals(ajeLineIdxIndTpCd)) {
                jrnlAmt = rs.getBigDecimal(RMNF_WIP_AMT);
                
            } else {
                // Ship Cost Amount: SHIP_COST_AMT
                jrnlAmt = rs.getBigDecimal(SHIP_COST_AMT);
            }
            //---- end 2016/03/16
            
            // If flag is ON, flip the sign of amount
            if (FLG_ON_Y.equals(flipFlg)) {
                jrnlAmt = jrnlAmt.negate();
            }
            
            return jrnlAmt;
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
                    
                    //---- start add 2016/02/01 when amount is zero, not to create journal entry
                    // QC#21258
                	//if (BigDecimal.ZERO.compareTo(jrnlEntryRec.jrnlDealDrAmt.getValue()) == 0) {
                	if (BigDecimal.ZERO.compareTo(jrnlEntryRec.jrnlDealDrAmt.getValue()) == 0 && !ZYPConstant.FLG_ON_Y.equals(ajeZeroCratFlg)) {
                        continue;
                    }
                    //----end 2016/02/01
                    
                    createJrnlEntry(jrnlEntryRec);
                }

            }

            // Store the result in AJE_INTFC_CTRL.
            AJE_INTFC_CTRLTMsg ajeIntfcCtrl = new AJE_INTFC_CTRLTMsg();
            commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "glblCmpyCd", rs.getString(GLBL_CMPY_CD));
            commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
            commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "ajeIntfcPk", rs.getBigDecimal(AJE_INVTY_INTFC_PK));
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
         *  This is helper function of doProcessQueryResult to make the function smaller
         * </pre>
         * @param tMsgJrnEnt TMsg of Journal Entry that is being generated
         * @param rs result set of OM interface table
         * @param pMsg AJE pattern list
         * @throws SQLException SQL Exception
         */
        private void setPartOfJrnlEntryValues(JRNL_ENTRYTMsg tMsgJrnEnt, ResultSet rs, NFAC000101PMsg pMsg)
        throws SQLException {

            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "glblCmpyCd", rs.getString(GLBL_CMPY_CD));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajeIntfcPk", rs.getBigDecimal(AJE_INVTY_INTFC_PK));
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

            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "billToCustCd", rs.getString(BILL_TO_CUST_CD));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "shipToCustCd", rs.getString(SHIP_TO_CUST_CD));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "sellToCustCd", rs.getString(SELL_TO_CUST_CD));

            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "cpoOrdNum", rs.getString(CPO_ORD_NUM));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "soNum", rs.getString(SO_NUM));

            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "vndCd", rs.getString(VND_CD));
            
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "orclRef10Txt", ORCL_REF_10_TXT_PREFIX + pMsg.ajeId.getValue());
            tMsgJrnEnt.orclAttrb11Txt.clear();
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajeIntfcCmntTxt", rs.getString(AJE_INTFC_CMNT_TXT));
            // COA_PROD_CD);
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "coaProdCd", rs.getString(ITEM_PROD_CD));
            // TOC_CD
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "tocCd", rs.getString(TOC_CD));
            // AJE_INV_NUM
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "ajeInvNum", rs.getString(INV_NUM));
            
            // CSA new
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "dsAcctNum", rs.getString(DS_ACCT_NUM));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "serNum", rs.getString(SER_NUM));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "svcMachMstrPk", rs.getBigDecimal(SVC_MACH_MSTR_PK));
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "invtyTrxPk", rs.getBigDecimal(INVTY_TRX_PK));
            
            // 2017/09/28 S21_NA#19408 Mod Start
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryFirstRefTxt", rs.getString(RTL_WH_CD));
//            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryFirstAttrbNm", RTL_WH_CD);
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryFirstAttrbNm", RTL_WH_CD_ATTRB_NM);
            
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryScdRefTxt", rs.getString(SLS_REP_CR_PCT));
//            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryScdAttrbNm", SLS_REP_CR_PCT);
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryScdAttrbNm", SLS_REP_CR_PCT_ATTRB_NM);

            // START 2018/02/08 E.Kameishi [QC#23191,MOD]
            if (ZYPCommonFunc.hasValue(rs.getString(PO_ORD_NUM))) {
                commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryThirdRefTxt", rs.getString(PO_ORD_NUM));
//                commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryThirdAttrbNm", PO_ORD_NUM);
                commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryThirdAttrbNm", PO_ORD_NUM_ATTRB_NM);
            } else {
                commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryThirdRefTxt", rs.getString(SLS_REP_TOC_CD));
                commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryThirdAttrbNm", EMSD_REP_ATTRB_NM);
            }
            // END 2018/02/08 E.Kameishi [QC#23191,MOD]

            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryFrthRefTxt", rs.getString(PO_ORD_DTL_LINE_NUM));
//            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryFrthAttrbNm", PO_ORD_DTL_LINE_NUM);
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryFrthAttrbNm", PO_ORD_DTL_LINE_NUM_ATTRB_NM);
            
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryFifthRefTxt", rs.getString(INVTY_TRX_SLP_NUM));
//            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryFifthAttrbNm", INVTY_TRX_SLP_NUM);
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "jrnlEntryFifthAttrbNm", INVTY_TRX_SLP_NUM_ATTRB_NM);
            // 2017/09/28 S21_NA#19408 Mod End
            
            //---- start 2016/06/09 new field
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "poOrdNum", rs.getString(PO_ORD_NUM));
            //---- end 2016/06/09

            // START 2019/08/10 S.Takami [QC#51897,ADD]
            commonJrnlEntry.setFieldValue(tMsgJrnEnt, "svcConfigMstrPk", rs.getBigDecimal(SVC_CONFIG_MSTR_PK));
            // END 2019/08/10 S.Takami [QC#51897,ADD]
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
    }

    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, errorCount, commitCount + errorCount);

        S21InfoLogOutput.println("termRoutine Method End");
    }

}
