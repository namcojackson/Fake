package com.canon.cusa.s21.batch.NLC.NLCB801501;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

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
import business.db.AJE_INVTY_INTFCTMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_COST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_RSN;
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
 * Inventory Link File Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/13/2015   CSAI            K.Uramori       Create (Base is copied from NLCB801001)
 * 2016/08/30   Hitachi         J.Kim           Update          QC#13896
 * 2017/05/25   CITS            T.Ono           Update          RS#8316 (Base is copied from NFAB104001)
 * 2017/12/15   CITS            T.Wada          Update          QC#22849
 * 2019/02/19   Hitachi         Y.Takeno        Update          QC#29707
 * 2019/03/26   Fujitsu         S.Takami        Update          QC#30946
 * 2022/09/05   Hitachi         M.Kikushima     Update          QC#59131
 *</pre>
 */

public class NLCB801501 extends S21BatchMain implements NLCB801501Constant, ZYPConstant, NFACommonJrnlEntryConstant {

    /** SSM Batch Clinent */
    private S21SsmBatchClient ssmBatchClient;

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd;

    // START 2017/05/25 T.Ono [RS#8316,MOD]
//    /** Commit Count */
//    private int commitCount = 0;
    /** Total Insert Count Before Split */
    private int totalInsertCountBeforeSplit = 0;
    // END   2017/05/25 T.Ono [RS#8316,MOD]

    /** Termination Code */
    private TERM_CD termCd;

    /** Error message */
    private String errMsg = "";

    /** Process Date */
    private static String procDt = "";   // it is not in use for now
    
    /** Process Date : YYYYMMDD */
    private String procYrMth;

    /** Array of TMsg */
    private EZDTMsg[] intfcMsgs;
    /** Array of TMsg */
    private EZDTMsg[] rmvMsgs;

    /** Size of Array */
    private int intfcMsgCount = 0;
    /** Size of Array */
    private int rmvMsgCount = 0;

    /**  Journal Entry Common Module */
    private NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();
    
    /** Trx Code of Kit */
    private String trxKit;
    
    /** Trx Reason Code of Kit Out */
    private String trxRsnKitOut;
    
    /** Trx Reason Code of Kit In */
    private String trxRsnKitIn;
    
    /** Trx Reason Code of Reman Item Change */
    private String trxRsnRemanItemChng;
    
    /** Trx Reason Code of Reman Parts Usage */
    private String trxRsnRemanPrtUsg;
    
    /** First Prod Ctrl Code for PPS */
    private String frstProdCtrlPPS;
    
    /** Flip Amount Trx */
    private String flipAmtTrx;

    // START 2016/08/30 J.Kim [QC#13896,ADD]
    /** Trx Reason Cod of Sub Warehouse Transfer */
    private String trxRsnSwhTrnsf;
    // END 2016/08/30 J.Kim [QC#13896,ADD]

    private List<String> flipTrxList =  new ArrayList<String>();

    // START 2017/05/25 T.Ono [RS#8316,ADD]
    /** Array of TMsg for update  */
    private EZDTMsg[] updTMsgs;

    /** Array of TMsg for insert  */
    private EZDTMsg[] insTMsgs;

    /** Split Count Per Trx  */
    private int cntPerTrx = 0;

    /** Update Count  */
    private int updCnt = 0;

    /** Insert Count  */
    private int insCnt = 0;

    /** Previous Transaction's PK  */
    private BigDecimal prevTrxPk = BigDecimal.ZERO;

    /** Current Transaction's PK  */
    private BigDecimal currTrxPk = BigDecimal.ZERO;

    /** AJE Common Function  */
    private NFACommonJrnlEntry cmnFunc;
    
    /** Total Insert Count for Split Amount */
    private int totalInsertCountForSplitAmt = 0;

    /** Total Update Count for Split Amount */
    private int totalUpdateCountForSplitAmt = 0;

    /** Total Commit Count */
    private int totalCommitCount = 0;
    // END   2017/05/25 T.Ono [RS#8316,ADD]

    // START 2019/02/19 [QC#29707, ADD]
    /** Array of TMsg */
    private EZDTMsg[] updIntfcMsgs;

    /** Size of Array */
    private int updIntfcMsgCount = 0;
    // END   2019/02/19 [QC#29707, ADD]

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        /* Initialize S21BatchMain */
        new NLCB801501().executeBatch(NLCB801501.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        this.termCd = TERM_CD.NORMAL_END;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();

        // START 2017/05/25 T.Ono [RS#8316,MOD]
//        procDt = S21BatchUtil.getInputParam1();
        procDt = S21BatchUtil.getUserVariable1();
        // END   2017/05/25 T.Ono [RS#8316,MOD]
        
        // if it's set by the parameter, it should not be overwritten
        if (procDt == null || procDt.equals("")) {
            procDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        }

        this.procYrMth = ZYPDateUtil.DateFormatter(procDt, "yyyyMMdd", "yyyyMM") + "%";
        
        
        // getting varchar const values
        this.trxKit = ZYPCodeDataUtil.getVarCharConstValue(CONST_KIT_TRX, this.glblCmpyCd);
        this.trxRsnKitOut = ZYPCodeDataUtil.getVarCharConstValue(CONST_KIT_TRX_RSN_OUT, this.glblCmpyCd);
        this.trxRsnKitIn = ZYPCodeDataUtil.getVarCharConstValue(CONST_KIT_TRX_RSN_IN, this.glblCmpyCd);
        this.trxRsnRemanItemChng = ZYPCodeDataUtil.getVarCharConstValue(CONST_REMAN_ITM_CHNG_TRX_RSN, this.glblCmpyCd);
        this.trxRsnRemanPrtUsg = ZYPCodeDataUtil.getVarCharConstValue(CONST_REMAN_PRT_USG_TRX_RSN, this.glblCmpyCd);
        this.frstProdCtrlPPS = ZYPCodeDataUtil.getVarCharConstValue(CONST_PPS_FIRST_PROD_CTRL, this.glblCmpyCd);
        this.flipAmtTrx = ZYPCodeDataUtil.getVarCharConstValue(AJE_AMT_SIGN_FLIP_TRX, this.glblCmpyCd);
        // START 2016/08/30 J.Kim [QC#13896,ADD]
        this.trxRsnSwhTrnsf = ZYPCodeDataUtil.getVarCharConstValue(AJE_VAL_TRX_RSN_SWH_TRNSF, this.glblCmpyCd);
        // END 2016/08/30 J.Kim [QC#13896,ADD]

        initVariables();
        getListOfFlipAmtTrxs();
        
        S21InfoLogOutput.println("initRoutine Method End");

    }
    
    private void initVariables() {
        intfcMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];

        intfcMsgCount = 0;
        rmvMsgCount = 0;

        // START 2017/05/25 T.Ono [RS#8316,ADD]
        updTMsgs = new AJE_INVTY_INTFCTMsg[BULK_UPD_CNT];
        insTMsgs = new AJE_INVTY_INTFCTMsg[BULK_UPD_CNT];

        cmnFunc = new NFACommonJrnlEntry();
        // END   2017/05/25 T.Ono [RS#8316,ADD]

        // START 2019/02/19 [QC#29707, ADD]
        updIntfcMsgs = new AJE_INVTY_INTFCTMsg[BULK_UPD_CNT];
        updIntfcMsgCount = 0;
        // END   2019/02/19 [QC#29707, ADD]
    }

    //---- start 2016/06/08
    private void getListOfFlipAmtTrxs() {
        
        StringTokenizer st = new StringTokenizer(flipAmtTrx, ",");
        
        while (st.hasMoreTokens()) {
            flipTrxList.add(st.nextToken());
        }
        
    }
    
    private BigDecimal getSignOfAmount(ResultSet rs) throws SQLException {
        BigDecimal qty = rs.getBigDecimal(INVTY_TRX_QTY);
        String trx = "";
        
        if (rs.getString(TRX_CD) != null && !rs.getString(TRX_CD).equals("")) {
            trx = rs.getString(TRX_CD).concat("-").concat(rs.getString(TRX_RSN_CD));
        } else {
            return BigDecimal.ONE;
        }
        
        if (flipTrxList.contains(trx)) {
            // positive
            if (qty.compareTo(BigDecimal.ZERO) >= 0) {
                return BigDecimal.ONE.negate();
            } 
        }
        
        return BigDecimal.ONE;
    }
    //---- end 2016/06/08
    
    @Override
    @SuppressWarnings("unchecked")
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        List<Map> map = getAjeInvtyIntfcListNotJrnlized();

        if (map.size() > 0) {
            if (!removeNotJrnlized(map)){
                // error
                rollback();
                throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
            }
        }

        if (!doEntryToAjeInvtyIntfc()) {
            // error
            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
        // START 2017/05/25 T.Ono [RS#8316,DEL]
//        } else {
//            commit();
        // END   2017/05/25 T.Ono [RS#8316,DEL]
        }

        // START 2019/02/19 [QC#29707, ADD]
        if (!updateAjeInvtyIntfcRMACredit()) {
            // error
            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
        }
        // END   2019/02/19 [QC#29707, ADD]

        // START 2017/05/25 T.Ono [RS#8316,ADD]
        // get target data
        List<InvtySlsCrDist> resultList = getTargetData();

        if (resultList == null) {
         // abend
            setTermState(TERM_CD.ABNORMAL_END, 0, 1, 0);
            throw new S21AbendException("ZZZM9006E", new String[] {"Standard Currency"});
        }

        // initialize variables
        int index = 0;

        //main process
        for (InvtySlsCrDist row : resultList) {
            currTrxPk = row.getAjeInvtyIntfcPk();

         // initialize
            if (prevTrxPk.equals(BigDecimal.ZERO)) {
                prevTrxPk = row.getAjeInvtyIntfcPk();
            }

            // when INVTY_TRX_PK breaks
            if (!prevTrxPk.equals(currTrxPk)) {
                // finalize one inventory trx
                if (!completeOneTrx(index - 1, resultList, false)) {
                    rollback();
                    setTermState(TERM_CD.ABNORMAL_END, 0, 1, 0);
                    throw new S21AbendException("NFDM0003E", new String[] {errMsg});
                }
            }

            cntPerTrx++;

            index++;

        }

         // finalize one inventory trx
        if (index > 1) {
            if (!completeOneTrx(index - 1, resultList, true)) {
                rollback();
                setTermState(TERM_CD.ABNORMAL_END, 0, 1, 0);
                throw new S21AbendException("NFDM0003E", new String[] {errMsg});
            }
        }

        // process remaining records
        if (insCnt != 0) {
            insert(null, true);
        }
        
        // update SLS_CR_SPL_CPLT_FLG to "Y" for the remaining current existing data (They were Not target)
        if (!updateFlgOnly()) {
            rollback();
            setTermState(TERM_CD.ABNORMAL_END, 0, 1, 0);
            throw new S21AbendException("NFDM0003E", new String[] {errMsg});
        }

        commit();
        totalCommitCount = totalInsertCountBeforeSplit + totalInsertCountForSplitAmt;
        S21InfoLogOutput.println("Insert record count before split : " + Integer.toString(totalInsertCountBeforeSplit));
        S21InfoLogOutput.println("Update record count for split amount : " + Integer.toString(totalUpdateCountForSplitAmt));
        S21InfoLogOutput.println("Insert record count for split amount : " + Integer.toString(totalInsertCountForSplitAmt));
        
        // END   2017/05/25 T.Ono [RS#8316,ADD]
        
        S21InfoLogOutput.println("mainRoutine Method End");
    }

    /**
     * <pre>
     *  Get AJE Interface Key List that is not journalized from AJE Interface Control.
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private List<Map> getAjeInvtyIntfcListNotJrnlized() {

        // ** Get All Transaction Pattern in AJE Loan Interface. **
        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
        map.put("jrnlCpltFlg", FLG_ON_Y);
        map.put("thisMonth", procYrMth);

        List<Map> listNotJrnlized = (List<Map>) ssmBatchClient.queryObjectList("getAjeInvtyIntfcListNotJrnlized", map);

        return listNotJrnlized;
    }

    /**
     * <pre>
     *  Remove AJE Inventory Interface that is not journalized.
     * </pre>
     * 
     * @param List<Map> AJE Interface Key List that is not
     * journalized.
     * @return true: OK  false: NG
     */
    @SuppressWarnings("unchecked")
    private boolean removeNotJrnlized(List<Map> listNotJrnlized) {
        try {
            for (Map<String, Object> mapNotJorlized : listNotJrnlized) {

                AJE_INVTY_INTFCTMsg tMsgInvty = new AJE_INVTY_INTFCTMsg();
                String glblCmpyCdStr = (String) mapNotJorlized.get(GLBL_CMPY_CD);
                BigDecimal ajeLoanDepcIntfcPkNum = new BigDecimal(mapNotJorlized.get(AJE_INVTY_INTFC_PK).toString());

                commonJrnlEntry.setFieldValue(tMsgInvty, "glblCmpyCd", glblCmpyCdStr);
                commonJrnlEntry.setFieldValue(tMsgInvty, "ajeInvtyIntfcPk", ajeLoanDepcIntfcPkNum);

                removeAjeIntfcNotJrnlizedHelper(tMsgInvty);
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
     *  Store Target Transactions in AJE Loan Depreciation Interface.
     * </pre>
     */
    private boolean doEntryToAjeInvtyIntfc() {

        // ** Get Target Transactions in INVTY_TRX. **
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
        map.put("jrnlCpltFlg", FLG_ON_Y);
        map.put("trxPrcsStkIn", TRX.PURCHASE_STOCK_IN);
        map.put("thisMonth", procYrMth);
        
        map.put("trxKit", trxKit);
        map.put("trxRsnKitOut", trxRsnKitOut);
        map.put("trxRsnKitIn", trxRsnKitIn);
        map.put("kitOrdTp", SCE_ORD_TP_KIT);
        map.put("unkitOrdTp", SCE_ORD_TP_UN_KIT);
        map.put("trxReman", TRX.REMANUFACTURING);
        map.put("trxRsnRemanItmChng", trxRsnRemanItemChng);
        map.put("pps", frstProdCtrlPPS);
        map.put("flgN", FLG_OFF_N);
        map.put("flgY", FLG_ON_Y);
        map.put("trxRsnRemanPrtUsg", trxRsnRemanPrtUsg);
        map.put("sales", TRX.SALES);
        // START 2016/08/30 J.Kim [QC#13896,ADD]
        map.put("stdCost", MDSE_COST_TP.STANDARD_COST);
        map.put("assetRecov", MDSE_COST_TP.ASSET_RECOVERY);
        map.put("ltstRecFlg", ZYPConstant.FLG_ON_Y);
        // END 2016/08/30 J.Kim [QC#13896,ADD]

        // QC#22849
        map.put("trxRemnf", TRX.REMANUFACTURING);
        map.put("remnfItemChgStkOut", TRX_RSN.REMAN_ITEM_CHANGE_STOCK_OUT);
        map.put("remnfItemChgStkOutAcsry", TRX_RSN.REMAN_ITEM_CHANGE_STOCK_OUT_ACSRY);
        map.put("remnfItemChgStkIn", TRX_RSN.REMAN_ITEM_CHANGE_STOCK_IN);
        map.put("remnfItemChgStkInAcsry", TRX_RSN.REMAN_ITEM_CHANGE_STOCK_IN_ACSRY);
        // START 2018/09/13 J.Kim [QC#23502,ADD]
        map.put("sysSrcSv", SYS_SRC.S21_SERVICE);
        // END 2018/09/13 J.Kim [QC#23502,ADD]
        // START 2022/09/05 M.Kikushima [QC#59131, ADD]
        map.put("trxAdj", TRX.ADJUSTMENT);
        map.put("trxRsnKitStkIn", TRX_RSN.KITTING_ITEM_CHANGE_STOCK_IN);
        // END 2022/09/05 M.Kikushima [QC#59131, ADD]

        Boolean success = (Boolean) ssmBatchClient.queryObject("getInvtyTrxNotJrnlized", map, new AjeInvtyIntfcHandler());

        return success;
    }
    
    /**
     * <pre>
     *  AJE Inventory Interfaces.
     *  Store Target Transactions in AJE Inventory Interface.
     * </pre>
     */
    private class AjeInvtyIntfcHandler extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
                while (rs.next()) {

                    BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INVTY_INTFC_SQ);

                    AJE_INVTY_INTFCTMsg tMsgInvty = new AJE_INVTY_INTFCTMsg();

                    commonJrnlEntry.setFieldValue(tMsgInvty, "glblCmpyCd", rs.getString(GLBL_CMPY_CD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "ajeInvtyIntfcPk", seqNum);
                    commonJrnlEntry.setFieldValue(tMsgInvty, "glDt", rs.getString(INVTY_TRX_DT));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "invtyTrxPk", rs.getBigDecimal(INVTY_TRX_PK));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "sysSrcCd", rs.getString(SYS_SRC_CD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "trxCd", rs.getString(TRX_CD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "trxRsnCd", rs.getString(TRX_RSN_CD));

                    commonJrnlEntry.setFieldValue(tMsgInvty, "invInvtyIndTpCd", INV_INVTY_IND_TP_CD);

                    commonJrnlEntry.setFieldValue(tMsgInvty, "billToCustCd", rs.getString(BILL_TO_CUST_CD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "sellToCustCd", rs.getString(SELL_TO_CUST_CD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "shipToCustCd", rs.getString(SHIP_TO_CUST_CD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "mdseCd", rs.getString(MDSE_CD));

                    commonJrnlEntry.setFieldValue(tMsgInvty, "mdseOrPrtCd", MDSE_OR_PRT_CD_M_VAL);

                    commonJrnlEntry.setFieldValue(tMsgInvty, "invtyLocCd", rs.getString(INVTY_LOC_CD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "tocCd", rs.getString(TOC_CD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "vndCd", rs.getString(VND_CD));

                    commonJrnlEntry.setFieldValue(tMsgInvty, "ccyCd", rs.getString(CCY_CD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "invtyTrxQty", rs.getBigDecimal(INVTY_TRX_QTY));
                    
                    commonJrnlEntry.setFieldValue(tMsgInvty, "unitCostAmt", rs.getBigDecimal(UNIT_COST_AMT));

                    commonJrnlEntry.setFieldValue(tMsgInvty, "depcAmt", ZERO_AMT);

                    // START 2016/08/30 J.Kim [QC#13896,MOD]
                    if (TRX.MOVEMENT.equals(rs.getString(TRX_CD)) && trxRsnSwhTrnsf.equals(rs.getString(TRX_RSN_CD))) {
                        commonJrnlEntry.setFieldValue(tMsgInvty, "unitCostAmt", rs.getBigDecimal(TRNSF_SHIP_COST_AMT));
                        commonJrnlEntry.setFieldValue(tMsgInvty, "shipCostAmt", rs.getBigDecimal(TRNSF_SHIP_COST_AMT).multiply(rs.getBigDecimal(INVTY_TRX_QTY).abs()));
                    } else {
                        //---- start add 2016/06/08 when the trx contains both in and out, need amount to be flipped.
                        BigDecimal multVal = getSignOfAmount(rs);
                        commonJrnlEntry.setFieldValue(tMsgInvty, "shipCostAmt", rs.getBigDecimal(SHIP_COST_AMT).multiply(multVal));
                        //---- end 2016/06/08
                    }
                    // END 2016/08/30 J.Kim [QC#13896,MOD]
                    
                    commonJrnlEntry.setFieldValue(tMsgInvty, "coaAcctCd", rs.getString(COA_ACCT_CD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "coaProdCd", rs.getString(COA_PROD_CD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "expProjCd", rs.getString(EXP_PROJ_CD));
                    
                    commonJrnlEntry.setFieldValue(tMsgInvty, "coaBrCd", rs.getString(COA_BR_CD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "coaCcCd", rs.getString(COA_CC_CD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "coaChCd", rs.getString(COA_CH_CD));
                    
                    commonJrnlEntry.setFieldValue(tMsgInvty, "cpoOrdNum", rs.getString(CPO_ORD_NUM));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "soNum", rs.getString(SO_NUM));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "invNum", rs.getString(INV_NUM));
  
                    
                    // new fields for CSA 
                    commonJrnlEntry.setFieldValue(tMsgInvty, "dsAcctNum", rs.getString(SELL_TO_CUST_CD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "invtyTrxSlpNum", rs.getString(INVTY_TRX_SLP_NUM));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "invtyTrxRefNum", rs.getString(INVTY_TRX_REF_NUM));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "invtyOrdNum", rs.getString(INVTY_ORD_NUM));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "invtyOrdLineNum", rs.getString(INVTY_ORD_LINE_NUM));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "rwsNum", rs.getString(RWS_NUM));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "wrkOrdNum", rs.getString(WRK_ORD_NUM));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "cpoDtlLineNum", rs.getString(CPO_DTL_LINE_NUM));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "cpoDtlLineSubNum", rs.getString(CPO_DTL_LINE_SUB_NUM));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "drInvtyOrdCmpyCd", rs.getString(LINK_CMPY));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "drInvtyOrdBrCd", rs.getString(LINK_BR));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "drInvtyOrdCcCd", rs.getString(LINK_CC));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "drInvtyOrdAcctCd", rs.getString(LINK_ACCT));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "drInvtyOrdProdCd", rs.getString(LINK_PROD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "drInvtyOrdChCd", rs.getString(LINK_CH));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "drInvtyOrdAfflCd", rs.getString(LINK_AFFL));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "drInvtyOrdProjCd", rs.getString(LINK_PROJ));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "drInvtyOrdExtnCd", rs.getString(LINK_EXTN));
                    
                    commonJrnlEntry.setFieldValue(tMsgInvty, "crInvtyOrdCmpyCd", rs.getString(LINK_CMPY));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "crInvtyOrdBrCd", rs.getString(LINK_BR));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "crInvtyOrdCcCd", rs.getString(LINK_CC));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "crInvtyOrdAcctCd", rs.getString(LINK_ACCT));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "crInvtyOrdProdCd", rs.getString(LINK_PROD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "crInvtyOrdChCd", rs.getString(LINK_CH));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "crInvtyOrdAfflCd", rs.getString(LINK_AFFL));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "crInvtyOrdProjCd", rs.getString(LINK_PROJ));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "crInvtyOrdExtnCd", rs.getString(LINK_EXTN));
                    
                    commonJrnlEntry.setFieldValue(tMsgInvty, "prchPrcAmt", rs.getBigDecimal(PRCH_PRC_AMT));
                    
                    // START 2016/08/30 J.Kim [QC#13896,MOD]
                    if (TRX.MOVEMENT.equals(rs.getString(TRX_CD)) && trxRsnSwhTrnsf.equals(rs.getString(TRX_RSN_CD))) {
                        commonJrnlEntry.setFieldValue(tMsgInvty, "shipFromUnitCostAmt", rs.getBigDecimal(UNIT_COST_AMT));
                        commonJrnlEntry.setFieldValue(tMsgInvty, "shipFromAmt", rs.getBigDecimal(SHIP_COST_AMT));
                    } else {
                        commonJrnlEntry.setFieldValue(tMsgInvty, "shipFromUnitCostAmt", rs.getBigDecimal(SHIP_FROM_UNIT_COST_AMT));
                        commonJrnlEntry.setFieldValue(tMsgInvty, "shipFromAmt", rs.getBigDecimal(SHIP_FROM_AMT));
                    }
                    // END 2016/08/30 J.Kim [QC#13896,MOD]
                    commonJrnlEntry.setFieldValue(tMsgInvty, "rmnfWipAmt", rs.getBigDecimal(RMNF_WIP_AMT));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "svcMachMstrPk", rs.getBigDecimal(SVC_MACH_MSTR_PK));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "machMdseCd", rs.getString(MACH_MDSE_CD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "finBrCd", rs.getString(FIN_BR_CD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "serNum", rs.getString(SER_NUM));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "rtlWhCd", rs.getString(RTL_WH_CD));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "origRtlWhCd", rs.getString(ORIG_RTL_WH_CD));
                    
                    commonJrnlEntry.setFieldValue(tMsgInvty, "rmnfWipCpltFlg", FLG_OFF_N);
                    
                    if (rs.getString(CPO_ORD_NUM) == null) {  // if CPO Ord Number is not there, sales credit split won't happen.
                        commonJrnlEntry.setFieldValue(tMsgInvty, "slsCrSplCpltFlg", FLG_ON_Y);
                    } else {
                        commonJrnlEntry.setFieldValue(tMsgInvty, "slsCrSplCpltFlg", FLG_OFF_N);
                    }
                    
                    // added more new fields 2016/01/15
                    commonJrnlEntry.setFieldValue(tMsgInvty, "poOrdNum", rs.getString(PO_ORD_NUM));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "poOrdDtlLineNum", rs.getString(PO_ORD_DTL_LINE_NUM));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "rmaNum", rs.getString(RMA_NUM));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "rmaLineNum", rs.getString(RMA_LINE_NUM));
                    commonJrnlEntry.setFieldValue(tMsgInvty, "rmaLineSubNum", rs.getString(RMA_LINE_SUB_NUM));

                    // Added 2016/01/15
                    if (BigDecimal.ZERO.compareTo(rs.getBigDecimal(RMNF_WIP_AMT)) != 0 && rs.getBigDecimal(RMNF_WIP_AMT) != null) {
                        
                        // update RMNF_WIP_CPLT_FLG
                        AJE_INVTY_INTFCTMsg cond = new AJE_INVTY_INTFCTMsg();
                        setValue(cond.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
                        setValue(cond.rmnfWipCpltFlg, FLG_OFF_N);
                        setValue(cond.invtyTrxSlpNum, rs.getString(INVTY_TRX_SLP_NUM));
                        setValue(cond.trxCd, TRX.REMANUFACTURING);
                        setValue(cond.trxRsnCd, trxRsnRemanPrtUsg);
      
                        AJE_INVTY_INTFCTMsg updt = new AJE_INVTY_INTFCTMsg();
                        setValue(updt.rmnfWipCpltFlg, FLG_ON_Y);
                        
                        int rtn = S21FastTBLAccessor.updateByPartialValue(cond, new String[] {"glblCmpyCd", "rmnfWipCpltFlg", "invtyTrxSlpNum", "trxCd", "trxRsnCd"}
                                                                                          , updt, new String[] {"rmnfWipCpltFlg"});
                        
                        if (rtn < 1) {
                            errMsg = UPDT_ERROR;
                            return Boolean.FALSE;
                        }
                    }
                    
                    createIntfc(tMsgInvty);

                }

                if (intfcMsgCount != 0) {
                    createIntfc(null);
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

    }
    
    /**
     * @param EZDTMsg AJE Loan Depreciation Interface.
     * @throws JrnlCommonException JrnlCommonException
     */
    protected void createIntfc(EZDTMsg tMsgInvty) throws NFACommonJrnlEntry.JrnlCommonException {

        if (tMsgInvty != null) {
            intfcMsgs[intfcMsgCount] = tMsgInvty;
            intfcMsgCount += 1;

        } else {  // array may be not full
            intfcMsgs = commonJrnlEntry.changeArraySize(intfcMsgs, intfcMsgCount);
        }

        // per 10000 lines
        if (intfcMsgCount >= BULK_INSERT_COUNT || tMsgInvty == null) {

            int retCnt = S21FastTBLAccessor.insert(intfcMsgs);

            // if passed records' count and return count don't match, error
            if (retCnt != intfcMsgCount) {
                throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
            }
            totalInsertCountBeforeSplit += intfcMsgCount;
            // initialize
            intfcMsgCount = 0;
            intfcMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        }
    }


    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, totalCommitCount, 0, totalCommitCount);

        S21InfoLogOutput.println("termRoutine Method End");
    }

    // START 2017/05/25 T.Ono [RS#8316,ADD]
    @SuppressWarnings("unchecked")
    private List<InvtySlsCrDist> getTargetData() {

        List<InvtySlsCrDist> resultList = new ArrayList<InvtySlsCrDist>();

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("flgY", FLG_ON_Y);
        queryParam.put("flgN", FLG_OFF_N);

        BigDecimal decimalPoint = getStdCcyInfo();

        if (decimalPoint == null) {
            return null;
        }

        queryParam.put("decPnt", decimalPoint);

        resultList = (List<InvtySlsCrDist>) ssmBatchClient.queryObjectList("getTargetData", queryParam);

        return resultList;

    }

    @SuppressWarnings("unchecked")
    private BigDecimal getStdCcyInfo() {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);

        Map rsltMap = (Map) ssmBatchClient.queryObject("getStdCcyInfo", queryParam);

        if (rsltMap != null) {
            return (BigDecimal) rsltMap.get("AFT_DECL_PNT_DIGIT_NUM");
        } else {
            return null;
        }

    }

    private boolean completeOneTrx(int lastIndex, List<InvtySlsCrDist> list, boolean isLast) {
        BigDecimal accmAmt = BigDecimal.ZERO;
        BigDecimal accmAmtFrom = BigDecimal.ZERO;
        Boolean isLastPerTrx = false;

        // for first record  -- update
        AJE_INVTY_INTFCTMsg updMsg = new AJE_INVTY_INTFCTMsg();

        InvtySlsCrDist firstLine = list.get(lastIndex - cntPerTrx + 1);

        // If the allocation is 100%, this is the last line.
        if (cntPerTrx == 1) {
            isLastPerTrx = true;
        } else {
            accmAmt = accmAmt.add(firstLine.getDividedShipCostAmt());
            accmAmtFrom = accmAmtFrom.add(firstLine.getDividedShipFromCostAmt());
        }

        if (!setTMsg(firstLine, updMsg, false, isLastPerTrx, accmAmt, accmAmtFrom)) {
            return false;
        }

        if (!update(updMsg, isLast)) {
            return false;
        }

        // For second and third record -- insert
        for (int i = 1; i < cntPerTrx; i++) {
            AJE_INVTY_INTFCTMsg insMsg = new AJE_INVTY_INTFCTMsg();

            InvtySlsCrDist currLine = list.get(lastIndex - cntPerTrx + (1 + i));

            // other than last record
            if (!((i + 1) == cntPerTrx)) {
                accmAmt = accmAmt.add(currLine.getDividedShipCostAmt());
                accmAmtFrom = accmAmtFrom.add(currLine.getDividedShipFromCostAmt());
            }

            if (!setTMsg(currLine, insMsg, true, (i + 1) == cntPerTrx, accmAmt, accmAmtFrom)) {
                return false;
            }

            if (!insert(insMsg, isLast)) {
                return false;
            }
        }

        // initialize variables
        cntPerTrx = 0;
        prevTrxPk = currTrxPk;

        return true;

    }

    private boolean setTMsg(InvtySlsCrDist data, AJE_INVTY_INTFCTMsg tmsg, boolean isInsert, boolean isLast, BigDecimal accmAmt, BigDecimal accmAmtFrom) {

        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, data.getGlblCmpyCd());
        if (isInsert) {
            BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal("AJE_INVTY_INTFC_SQ");
            ZYPEZDItemValueSetter.setValue(tmsg.ajeInvtyIntfcPk, seqNum);
        } else { // update
            ZYPEZDItemValueSetter.setValue(tmsg.ajeInvtyIntfcPk, data.getAjeInvtyIntfcPk());

            // retrieve target data
            tmsg  = (AJE_INVTY_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

            if (tmsg == null) {
                errMsg = RETRIEVE_ERROR;
                return false;
            }
        }
        ZYPEZDItemValueSetter.setValue(tmsg.glDt, data.getGlDt());
        ZYPEZDItemValueSetter.setValue(tmsg.invtyTrxPk, data.getInvtyTrxPk());
        ZYPEZDItemValueSetter.setValue(tmsg.sysSrcCd, data.getSysSrcCd());
        ZYPEZDItemValueSetter.setValue(tmsg.trxCd, data.getTrxCd());
        ZYPEZDItemValueSetter.setValue(tmsg.trxRsnCd, data.getTrxRsnCd());
        ZYPEZDItemValueSetter.setValue(tmsg.invInvtyIndTpCd, data.getInvInvtyIndTpCd());
        ZYPEZDItemValueSetter.setValue(tmsg.billToCustCd, data.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(tmsg.sellToCustCd, data.getSellToCustCd());
        ZYPEZDItemValueSetter.setValue(tmsg.shipToCustCd, data.getShipToCustCd());
        ZYPEZDItemValueSetter.setValue(tmsg.invNum, data.getInvNum());
        ZYPEZDItemValueSetter.setValue(tmsg.cpoOrdNum, data.getCpoOrdNum());
        ZYPEZDItemValueSetter.setValue(tmsg.soNum, data.getSoNum());
        ZYPEZDItemValueSetter.setValue(tmsg.mdseCd, data.getMdseCd());
        ZYPEZDItemValueSetter.setValue(tmsg.mdseOrPrtCd, data.getMdseOrPrtCd());
        ZYPEZDItemValueSetter.setValue(tmsg.invtyLocCd, data.getInvtyLocCd());
        // DS_CPO_SLS_CR.SLS_REP_TOC_CD
        ZYPEZDItemValueSetter.setValue(tmsg.tocCd, data.getTocCd());

        ZYPEZDItemValueSetter.setValue(tmsg.vndCd, data.getVndCd());
        ZYPEZDItemValueSetter.setValue(tmsg.ccyCd, data.getCcyCd());

        if (isLast) {  // Only when last line, quantity to be set.
            ZYPEZDItemValueSetter.setValue(tmsg.invtyTrxQty, data.getInvtyTrxQty());
        } else {
            ZYPEZDItemValueSetter.setValue(tmsg.invtyTrxQty, BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(tmsg.unitCostAmt, data.getUnitCostAmt());
        if (isLast) {
            // original amount - total of other lines
            ZYPEZDItemValueSetter.setValue(tmsg.shipCostAmt, data.getShipCostAmt().add(accmAmt.negate()));
        } else {
            ZYPEZDItemValueSetter.setValue(tmsg.shipCostAmt, data.getDividedShipCostAmt());
        }
        ZYPEZDItemValueSetter.setValue(tmsg.depcAmt, data.getDepcAmt());
        ZYPEZDItemValueSetter.setValue(tmsg.coaAcctCd, data.getCoaAcctCd());
        ZYPEZDItemValueSetter.setValue(tmsg.coaProdCd, data.getCoaProdCd());
        ZYPEZDItemValueSetter.setValue(tmsg.expProjCd, data.getExpProjCd());
        ZYPEZDItemValueSetter.setValue(tmsg.coaBrCd, data.getCoaBrCd());
        ZYPEZDItemValueSetter.setValue(tmsg.coaCcCd, data.getCoaCcCd());
        ZYPEZDItemValueSetter.setValue(tmsg.coaChCd, data.getCoaChCd());
        ZYPEZDItemValueSetter.setValue(tmsg.ajeIntfcCmntTxt, data.getAjeIntfcCmntTxt());

        ZYPEZDItemValueSetter.setValue(tmsg.dsAcctNum, data.getDsAcctNum());
        ZYPEZDItemValueSetter.setValue(tmsg.invtyTrxSlpNum, data.getInvtyTrxSlpNum());
        ZYPEZDItemValueSetter.setValue(tmsg.invtyTrxRefNum, data.getInvtyTrxRefNum());
        ZYPEZDItemValueSetter.setValue(tmsg.invtyOrdNum, data.getInvtyOrdNum());
        ZYPEZDItemValueSetter.setValue(tmsg.invtyOrdLineNum, data.getInvtyOrdLineNum());
        ZYPEZDItemValueSetter.setValue(tmsg.rwsNum, data.getRwsNum());
        ZYPEZDItemValueSetter.setValue(tmsg.wrkOrdNum, data.getWrkOrdNum());
        ZYPEZDItemValueSetter.setValue(tmsg.cpoDtlLineNum, data.getCpoDtlLineNum());
        ZYPEZDItemValueSetter.setValue(tmsg.cpoDtlLineSubNum, data.getCpoDtlLineSubNum());
        ZYPEZDItemValueSetter.setValue(tmsg.dsCpoSlsCrPk, data.getDsCpoSlsCrPk());
        ZYPEZDItemValueSetter.setValue(tmsg.slsRepCrPct, data.getSlsRepCrPct());

        ZYPEZDItemValueSetter.setValue(tmsg.drInvtyOrdCmpyCd, data.getDrInvtyOrdCmpyCd());
        ZYPEZDItemValueSetter.setValue(tmsg.drInvtyOrdBrCd, data.getDrInvtyOrdBrCd());
        ZYPEZDItemValueSetter.setValue(tmsg.drInvtyOrdCcCd, data.getDrInvtyOrdCcCd());
        ZYPEZDItemValueSetter.setValue(tmsg.drInvtyOrdAcctCd, data.getDrInvtyOrdAcctCd());
        ZYPEZDItemValueSetter.setValue(tmsg.drInvtyOrdProdCd, data.getDrInvtyOrdProdCd());
        ZYPEZDItemValueSetter.setValue(tmsg.drInvtyOrdChCd, data.getDrInvtyOrdChCd());
        ZYPEZDItemValueSetter.setValue(tmsg.drInvtyOrdAfflCd, data.getDrInvtyOrdAfflCd());
        ZYPEZDItemValueSetter.setValue(tmsg.drInvtyOrdProjCd, data.getDrInvtyOrdProjCd());
        ZYPEZDItemValueSetter.setValue(tmsg.drInvtyOrdExtnCd, data.getDrInvtyOrdExtnCd());

        ZYPEZDItemValueSetter.setValue(tmsg.crInvtyOrdCmpyCd, data.getCrInvtyOrdCmpyCd());
        ZYPEZDItemValueSetter.setValue(tmsg.crInvtyOrdBrCd, data.getCrInvtyOrdBrCd());
        ZYPEZDItemValueSetter.setValue(tmsg.crInvtyOrdCcCd, data.getCrInvtyOrdCcCd());
        ZYPEZDItemValueSetter.setValue(tmsg.crInvtyOrdAcctCd, data.getCrInvtyOrdAcctCd());
        ZYPEZDItemValueSetter.setValue(tmsg.crInvtyOrdProdCd, data.getCrInvtyOrdProdCd());
        ZYPEZDItemValueSetter.setValue(tmsg.crInvtyOrdChCd, data.getCrInvtyOrdChCd());
        ZYPEZDItemValueSetter.setValue(tmsg.crInvtyOrdAfflCd, data.getCrInvtyOrdAfflCd());
        ZYPEZDItemValueSetter.setValue(tmsg.crInvtyOrdProjCd, data.getCrInvtyOrdProjCd());
        ZYPEZDItemValueSetter.setValue(tmsg.crInvtyOrdExtnCd, data.getCrInvtyOrdExtnCd());

        ZYPEZDItemValueSetter.setValue(tmsg.prchPrcAmt, data.getPrchPrcAmt());
        ZYPEZDItemValueSetter.setValue(tmsg.shipFromUnitCostAmt, data.getShipFromUnitCostAmt());
        
        if (isLast) {
            // original amount - total of other lines
            ZYPEZDItemValueSetter.setValue(tmsg.shipFromAmt, data.getShipFromAmt().add(accmAmtFrom.negate()));
        } else {
            ZYPEZDItemValueSetter.setValue(tmsg.shipFromAmt, data.getDividedShipFromCostAmt());
        }

        ZYPEZDItemValueSetter.setValue(tmsg.rmnfWipAmt, data.getRmnfWipAmt());
        ZYPEZDItemValueSetter.setValue(tmsg.svcMachMstrPk, data.getSvcMachMstrPk());
        ZYPEZDItemValueSetter.setValue(tmsg.machMdseCd, data.getMachMdseCd());
        ZYPEZDItemValueSetter.setValue(tmsg.finBrCd, data.getFinBrCd());
        ZYPEZDItemValueSetter.setValue(tmsg.serNum, data.getSerNum());
        ZYPEZDItemValueSetter.setValue(tmsg.rtlWhCd, data.getRtlWhCd());
        ZYPEZDItemValueSetter.setValue(tmsg.origRtlWhCd, data.getOrigRtlWhCd());
        ZYPEZDItemValueSetter.setValue(tmsg.rmnfWipCpltFlg, data.getRmnfWipCpltFlg());
        ZYPEZDItemValueSetter.setValue(tmsg.poOrdNum, data.getPoOrdNum());
        ZYPEZDItemValueSetter.setValue(tmsg.poOrdDtlLineNum, data.getPoOrdDtlLineNum());
        // START 2016/09/07 T.Tsuchida [QC#14260,ADD]
        ZYPEZDItemValueSetter.setValue(tmsg.rmaNum, data.getRmaNum());
        ZYPEZDItemValueSetter.setValue(tmsg.rmaLineNum, data.getRmaLineNum());
        ZYPEZDItemValueSetter.setValue(tmsg.rmaLineSubNum, data.getRmaLineSubNum());
        // END 2016/09/07 T.Tsuchida [QC#14260,ADD]

        ZYPEZDItemValueSetter.setValue(tmsg.slsCrSplCpltFlg, FLG_ON_Y);

        return true;
    }

    private boolean updateFlgOnly() {
        AJE_INVTY_INTFCTMsg tmsgCond = new AJE_INVTY_INTFCTMsg();
        AJE_INVTY_INTFCTMsg tmsgUpd = new AJE_INVTY_INTFCTMsg();

        // update the records with flag = "N" to "Y"
        ZYPEZDItemValueSetter.setValue(tmsgCond.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsgCond.ezCancelFlag, "0");
        ZYPEZDItemValueSetter.setValue(tmsgCond.slsCrSplCpltFlg, FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(tmsgUpd.slsCrSplCpltFlg, FLG_ON_Y);

        int rtnCd = S21FastTBLAccessor.updateByPartialValue(tmsgCond, new String[]{"glblCmpyCd", "slsCrSplCpltFlg"}
                                                            , tmsgUpd, new String[]{"slsCrSplCpltFlg"});

        if (rtnCd < 0) {
            errMsg = UPDATE_ERROR;
            return false;
        } else {
            return true;
        }

    }

    private boolean update(EZDTMsg tmsg, boolean isLast) {
        updTMsgs[updCnt] = tmsg;

        updCnt++;
        totalUpdateCountForSplitAmt++;

        if (updCnt >= BULK_UPD_CNT || isLast) {

            if (isLast) {
                // the array may not be full
                updTMsgs = cmnFunc.changeArraySize(updTMsgs, updCnt);
            }

            int retCnt = S21FastTBLAccessor.update(updTMsgs);

            // if passed records' count and return count don't match, error
            if (retCnt != updTMsgs.length) {
                errMsg = UPDATE_ERROR;
                return false;
            }
            // initialize
            updCnt = 0;
            updTMsgs = new EZDTMsg[BULK_UPD_CNT];

        }

        return true;
    }

    private boolean insert(EZDTMsg tmsg, boolean isLast) {
        
        if (tmsg != null) {
            insTMsgs[insCnt] = tmsg;
    
            insCnt++;
            totalInsertCountForSplitAmt++;
        }

        if (insCnt >= BULK_UPD_CNT || isLast) {

            if (isLast) {
                // the array may not be full
                insTMsgs = cmnFunc.changeArraySize(insTMsgs, insCnt);
            }

            int retCnt = S21FastTBLAccessor.insert(insTMsgs);

            // if passed records' count and return count don't match, error
            if (retCnt != insTMsgs.length) {
                errMsg = INSERT_ERROR;
                return false;
            }
            // initialize
            insCnt = 0;
            insTMsgs = new EZDTMsg[BULK_UPD_CNT];

        }

        return true;
    }
    // END   2017/05/25 T.Ono [RS#8316,ADD]

    // START 2019/02/19 [QC#29707, ADD]
    private boolean updateAjeInvtyIntfcRMACredit() {

        // ** Get Target Records in INVTY_TRX_INTFC. **
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
        map.put("jrnlCpltFlg", ZYPConstant.FLG_ON_Y);
        // START 2019/03/26 [QC#30946,ADD]
        map.put("invTpCr", INV_TP.CREDIT_MEMO);
        // END 2019/03/26 [QC#30946,ADD]

        Boolean success = (Boolean) ssmBatchClient.queryObject("getInvtyTrxIntfcRMACredit", map, new AjeInvtyIntfcUpdateHandler());
        return success;
    }

    /**
     * <pre>
     *  AjeInvtyIntfcUpdateHandler
     *  Store Target Transactions in AJE Inventory Interface.
     * </pre>
     */
    private class AjeInvtyIntfcUpdateHandler extends S21SsmBooleanResultSetHandlerSupport {
        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
                while (rs.next()) {
                    AJE_INVTY_INTFCTMsg tMsgInvty = new AJE_INVTY_INTFCTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsgInvty.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
                    ZYPEZDItemValueSetter.setValue(tMsgInvty.ajeInvtyIntfcPk, rs.getBigDecimal("AJE_INVTY_INTFC_PK"));
                    tMsgInvty = (AJE_INVTY_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsgInvty);
                    if (tMsgInvty == null) {
                        errMsg = UPDATE_ERROR;
                        return false;
                    }

                    if(!ZYPCommonFunc.hasValue(rs.getString("INV_NUM"))) {
                        continue;
                    }

                    // update Invoice#
                    ZYPEZDItemValueSetter.setValue(tMsgInvty.invNum, rs.getString("INV_NUM"));
                    updateIntfc(tMsgInvty, false);
                }

                if (updIntfcMsgCount != 0) {
                    updateIntfc(null, true);
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
            }
            return Boolean.TRUE;
        }

        private boolean updateIntfc(AJE_INVTY_INTFCTMsg tMsg, boolean isLast) {
            if (tMsg != null) {
                updIntfcMsgs[updIntfcMsgCount] = tMsg;
                updIntfcMsgCount++;
            }

            if (updIntfcMsgCount >= BULK_UPD_CNT || isLast) {
                if (isLast) {
                    // the array may not be full
                    updIntfcMsgs = cmnFunc.changeArraySize(updIntfcMsgs, updIntfcMsgCount);
                }

                int retCnt = S21FastTBLAccessor.update(updIntfcMsgs);

                // if passed records' count and return count don't match, error
                if (retCnt != updIntfcMsgs.length) {
                    errMsg = UPDATE_ERROR;
                    return false;
                }
                // initialize
                updIntfcMsgCount = 0;
                updIntfcMsgs = new EZDTMsg[BULK_UPD_CNT];
            }

            return true;
        }
    }
    // END   2019/02/19 [QC#29707, ADD]
}
