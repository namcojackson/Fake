package com.canon.cusa.s21.batch.NFA.NFAB001501;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import business.db.AJE_INTFC_CTRLTMsg;
import business.db.JRNL_ENTRYTMsg;
import business.db.AJE_INV_ACCT_DISTTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INTFC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INV_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
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
 * This batch journalizes front transactions of OM.
 * Created based on NFAB001001
 *
 * Date         Company         Name            Create/Update                  Defect No
 * ---------------------------------------------------------------------------------------
 * 11/25/2015   CSAI            K.Uramori       Create based on NFAB001001      N/A
 * 02/10/2016   CSAI            K.Uramori       Deferred Revenue Recognition Transaction is not selected since the transaction is treated as processed by AJE_INTFC_CTRL.
 *                                              The reason is because the key was DS_INV_SLS_CR_PK. To avoid that set the AJE_INV_ACCT_DIST_PK of debit side to AJE_INTFC_PK.
 * 09/28/2017   CITS            Y.Fujii         Update                          QC#19408
 * 10/26/2017   CITS            T.Kikuhara      Update                          QC#21726
 * 11/17/2017   CITS            K.Ogino         Update                          QC#21980
 * 11/27/2017   CITS            T.Wada          Update                          QC#21258
 * 2019/08/10   Fujitsu         S.Takami        Update                          QC#51897
 * </pre>
 */
public class NFAB001501 extends S21BatchMain implements NFAB001501Constant, ZYPConstant, NFACommonJrnlEntryConstant {

    /** SQL Access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;
    
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
    /** Size of Array */
    private int cnclJrnlCount = 0;
    
    /** AJE Interface Control PK **/
    // private BigDecimal slsCrPk = BigDecimal.ZERO;
    
    /**  Journal Entry Common Module */
    private NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();

    /** QC#21258 */
    private String ajeZeroCratFlg = "";

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        new NFAB001501().executeBatch(NFAB001501.class.getSimpleName());

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
        
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // initialize
        jrnlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        ctrlMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];

        S21InfoLogOutput.println("initRoutine Method End");

    }

    @SuppressWarnings("unchecked")
    @Override
    protected void mainRoutine() {
        
        S21InfoLogOutput.println("mainRoutine Method Start");

        // List of interface control data whose JRNL_CPLY_FLG is "N".
        List<Map> intfcCtrlList = commonJrnlEntry.getAjeIntfcKeyListNotJrnlized(this.glblCmpyCd, AJE_INTFC_TP.OM);

        if (intfcCtrlList.size() > 0) {
                
            if (!removeAjeIntfcCtrlNotJrnlized(intfcCtrlList)) {
                rollback();
                throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
            }
        }

        // main process
        if (!doEntryToJournalEntry()) {
            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
        }
        
        // If AJE error occurs, journal entries for the entire invoice should be not generated.                
        if (!doCheckInterfaceControl()) {

            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
        } 
        
        // update JRNL_CRAT_DT on AJE_INV_ACCT_DIST for processed data without error
        if (!updateJrnlCratDt()) {
            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
        }
        
        commit();
        
        S21InfoLogOutput.println("mainRoutine Method End");

    }

    private boolean updateJrnlCratDt() {
        
        int cnt = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // get target request data
            stmt = getProcessedData();
            rs = stmt.executeQuery();
            
            EZDTMsg tmsgs[] = new EZDTMsg[BULK_INSERT_COUNT];
         
            BigDecimal pk;
            while (rs.next()) {
                pk = rs.getBigDecimal(AJE_INV_ACCT_DIST_PK);
                
                AJE_INV_ACCT_DISTTMsg tmsg = new AJE_INV_ACCT_DISTTMsg();
                
                ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmsg.ajeInvAcctDistPk, pk);
                
                tmsg = (AJE_INV_ACCT_DISTTMsg)S21FastTBLAccessor.findByKeyForUpdate(tmsg);
                
                ZYPEZDItemValueSetter.setValue(tmsg.jrnlCratDt, procDt);
                
                tmsgs[cnt] = tmsg;
                
                cnt = cnt + 1;
                
                if (cnt >= BULK_INSERT_COUNT) {
                    // bulk update
                    S21FastTBLAccessor.update(tmsgs);
                    
                    // initialize
                    tmsgs = new AJE_INV_ACCT_DISTTMsg[BULK_INSERT_COUNT];
                    cnt = 0;
                }
            }
            
            // update remaining data
            if (cnt != 0) {
                tmsgs = commonJrnlEntry.changeArraySize(tmsgs, cnt);
                S21FastTBLAccessor.update(tmsgs);
            }
            
        }catch (SQLException sqlEx) {
            errMsg = sqlEx.getMessage();
            return false;
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        } 
        
        return true;
    }
    
    private PreparedStatement getProcessedData() throws SQLException{
        PreparedStatement stmt = null;
        
        Map<String, String> sqlParam = new HashMap<String, String>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("flgY", FLG_ON_Y);
        sqlParam.put("ajeIntfcTpCd", AJE_INTFC_TP.OM);
        
        stmt = this.ssmLLClient.createPreparedStatement("getProcessedData", sqlParam);

        return stmt;
    }
    
    /**
     * <pre>
     *  Remove AJE Interface Control that is not journalized.
     *  @param List<Map> intfcCtrlList
     *  @return boolean true: OK  false: NG
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private boolean removeAjeIntfcCtrlNotJrnlized(List<Map> intfcCtrlList) {

        try {
            for (Map<String, Object> rec : intfcCtrlList) {
    
                AJE_INTFC_CTRLTMsg ajeIntfcCtrl = new AJE_INTFC_CTRLTMsg();
                String glblCmpyCdStr = (String) rec.get(GLBL_CMPY_CD);
                String ajeIntfcTpCdStr = (String) rec.get(AJE_INTFC_TP_CD);
                BigDecimal ajeIntfcPkNum = new BigDecimal(rec.get(AJE_INTFC_PK).toString());
    
                ZYPEZDItemValueSetter.setValue(ajeIntfcCtrl.glblCmpyCd, glblCmpyCdStr);
                ZYPEZDItemValueSetter.setValue(ajeIntfcCtrl.ajeIntfcTpCd, ajeIntfcTpCdStr);
                ZYPEZDItemValueSetter.setValue(ajeIntfcCtrl.ajeIntfcPk, ajeIntfcPkNum);
    
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
     *  Get Target Transactions in AJE OM Interface and Journalize.
     * </pre>
     * @param List<Map> Target AJE Pattern List
     * @return boolean
     */
    private Boolean doEntryToJournalEntry() {

        // ** Get Target Transactions in AJE OM Interface. **
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("ajeIntfcTpCd", AJE_INTFC_TP.OM);
        param.put("jrnlCpltFlg", FLG_ON_Y);
        param.put("errStsCd", PROC_STS.ERROR);
        param.put("debit", "D");
        param.put("credit", "C");
        param.put("flgN", FLG_OFF_N);
        param.put("flgY", FLG_ON_Y);

        // QC#21726 add Start
        String ajeFrtMdseCd = ZYPCodeDataUtil.getVarCharConstValue("AJE_FRT_MDSE_CD", this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(ajeFrtMdseCd)) {
            ajeFrtMdseCd = "";
        }
        param.put("ajeFrtMdseCd", ajeFrtMdseCd);
        param.put("freight", AJE_INV_ACCT_CLS.FREIGHT);
        // QC#21726 add End
        // QC#21980
        param.put("trxCd010", "010");
        param.put("trxRsnCdS","S%");
        // QC#21980 End

        // QC#21258
        ajeZeroCratFlg = ZYPCodeDataUtil.getVarCharConstValue("AJE_ZERO_CRAT_FLG_OM", this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(ajeZeroCratFlg)) {
            ajeZeroCratFlg = ZYPConstant.FLG_ON_Y;
        }

        return (Boolean) ssmBatchClient.queryObject("getDataNotJrnlized", param, new JrnlEntryHandler());
    }

    /**
     * <pre>
     *  Journal Entry Handler.
     *  Jourlize all transacntions with OM interface and update the result in AJE Interface Control Table.
     *  CoA Segment is derived from AJE Pattern or masters (Org, Merchandise or Customer).
     * </pre>
     */
    private final class JrnlEntryHandler extends S21SsmBooleanResultSetHandlerSupport {

        /** Error Message Id. **/
        private String errMsgId = NFACommonJrnlEntryConstant.DEF_ERROR_MSG_ID;
        /** Error Message text. **/
        private String errMsgTxt = NFACommonJrnlEntryConstant.DEF_ERROR_MSG_TXT;

        private JrnlEntryHandler() {
            // nothing
        }

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
                
                // Check Flag for CoA Segment Determination.
                boolean isError;
                
                while (rs.next()) {
                    // initialize
                    isError = false;
                    
                    JRNL_ENTRYTMsg jrnlEntry = new JRNL_ENTRYTMsg();
                    
                    try {
                            
                        BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(JRNL_ENTRY_SQ);
                        
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryPk, seqNum);
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajeIntfcTpCd, AJE_INTFC_TP.OM);
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajeIntfcPk, rs.getBigDecimal(AJE_INV_ACCT_DIST_PK));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.procDt, procDt);
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.glDt, rs.getString(GL_DT));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.glSendCpltFlg, FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajeId, rs.getString(AJE_ID));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.sysSrcCd, rs.getString(SYS_SRC_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.sysSrcNm, rs.getString(SYS_SRC_NM));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.trxCd, rs.getString(TRX_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.trxNm, rs.getString(TRX_NM));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.trxRsnCd, rs.getString(TRX_RSN_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.trxRsnNm, rs.getString(TRX_RSN_NM));
                        
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnIndTpCd_01, rs.getString(AJE_PTRN_IND_TP_CD_01));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnIndTpNm_01, rs.getString(AJE_PTRN_IND_TP_NM_01));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnActlCd_01, rs.getString(AJE_PTRN_ACTL_CD_01));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnActlNm_01, rs.getString(AJE_PTRN_ACTL_NM_01));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnIndTpCd_02, rs.getString(AJE_PTRN_IND_TP_CD_02));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnIndTpNm_02, rs.getString(AJE_PTRN_IND_TP_NM_02));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnActlCd_02, rs.getString(AJE_PTRN_ACTL_CD_02));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnActlNm_02, rs.getString(AJE_PTRN_ACTL_NM_02));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnIndTpCd_03, rs.getString(AJE_PTRN_IND_TP_CD_03));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnIndTpNm_03, rs.getString(AJE_PTRN_IND_TP_NM_03));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnActlCd_03, rs.getString(AJE_PTRN_ACTL_CD_03));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajePtrnActlNm_03, rs.getString(AJE_PTRN_ACTL_NM_03));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlSrcCd, rs.getString(JRNL_SRC_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlSrcNm, rs.getString(JRNL_SRC_NM));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlCatgCd, rs.getString(JRNL_CATG_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlCatgNm, rs.getString(JRNL_CATG_NM));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajeLineIdxNum, rs.getString(AJE_LINE_IDX_NUM));
                        
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajeItemCd, rs.getString(AJE_ITEM_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajeItemDescTxt, rs.getString(AJE_ITEM_DESC_TXT));
                        
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.drAjeLineIdxDescTxt, rs.getString(DR_AJE_LINE_IDX_DESC_TXT));
                        
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaCmpyCd, rs.getString(DR_COA_CMPY_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaBrCd, rs.getString(DR_COA_BR_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaCcCd, rs.getString(DR_COA_CC_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaAcctCd, rs.getString(DR_COA_ACCT_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaProdCd, rs.getString(DR_COA_PROD_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaChCd, rs.getString(DR_COA_CH_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaAfflCd, rs.getString(DR_COA_AFFL_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaProjCd, rs.getString(DR_COA_PROJ_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.drCoaExtnCd, rs.getString(DR_COA_EXTN_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaCmpyCd, rs.getString(CR_COA_CMPY_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaBrCd, rs.getString(CR_COA_BR_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaCcCd, rs.getString(CR_COA_CC_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaAcctCd, rs.getString(CR_COA_ACCT_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaProdCd, rs.getString(CR_COA_PROD_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaChCd, rs.getString(CR_COA_CH_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaAfflCd, rs.getString(CR_COA_AFFL_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaProjCd, rs.getString(CR_COA_PROJ_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.crCoaExtnCd, rs.getString(CR_COA_EXTN_CD));
                        
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlQty, rs.getBigDecimal(JRNL_QTY));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlDealDrAmt, rs.getBigDecimal(JRNL_DEAL_DR_AMT));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlDealCrAmt, rs.getBigDecimal(JRNL_DEAL_CR_AMT));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlFuncDrAmt, rs.getBigDecimal(JRNL_FUNC_DR_AMT));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlFuncCrAmt, rs.getBigDecimal(JRNL_FUNC_CR_AMT));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.dealCcyCd, rs.getString(DEAL_CCY_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.funcCcyCd, rs.getString(FUNC_CCY_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.orclCcyCd, rs.getString(ORCL_CCY_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.exchRate, rs.getBigDecimal(EXCH_RATE));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.acctArthTpCd, rs.getString(ACCT_ARTH_TP_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.coaProdCd, rs.getString(COA_RPOD_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.tocCd, rs.getString(TOC_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.billToCustCd, rs.getString(BILL_TO_CUST_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.shipToCustCd, rs.getString(SHIP_TO_CUST_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.sellToCustCd, rs.getString(SELL_TO_CUST_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajeInvNum, rs.getString(AJE_INV_NUM));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.cpoOrdNum, rs.getString(CPO_ORD_NUM));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.soNum, rs.getString(SO_NUM));
                        
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.dsOrdTpCd, rs.getString(DS_ORD_TP_CD));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.dsOrdRsnCd, rs.getString(DS_ORD_RSN_CD));
                        
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.dsAcctNum, rs.getString(DS_ACCT_NUM));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.serNum, rs.getString(SER_NUM));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.svcMachMstrPk, rs.getBigDecimal(SVC_MACH_MSTR_PK));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.dsContrPk, rs.getBigDecimal(DS_CONTR_PK));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.dsContrDtlPk, rs.getBigDecimal(DS_CONTR_DTL_PK));
                        
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryFirstRefTxt, rs.getString(DS_CONTR_NUM));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryScdRefTxt, rs.getString(AJE_INV_ACCT_DIST_PCT));
                        
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryFirstAttrbNm, DISP_DS_CONTR_NUM);
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryScdAttrbNm, DISP_AJE_INV_ACCT_DIST_PCT);
                        
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.orclRef10Txt, ORCL_REF_10_TXT_PREFIX + rs.getString(AJE_ID));
                        
                        // to store reference text
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryRefTxt, commonJrnlEntry.getJrnlEntryRefTxt(jrnlEntry));
                        
                        //---- start QC#16075 2016/12/12 If COA value can't be retrieved, default value is set and that is logged to comment text.
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.ajeIntfcCmntTxt, rs.getString("AJE_INTFC_CMNT_TXT"));
                        //---- end QC#16075 2016/12/12
                        
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryThirdRefTxt, rs.getString(CUST_ISS_PO_NUM));
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryThirdAttrbNm, DISP_CUST_ISS_PO_NUM);
                        String dsOrdPosnNum = rs.getString(DS_ORD_POSN_NUM);
                        BigDecimal dsCpoLineNum = rs.getBigDecimal(DS_CPO_LINE_NUM);
                        BigDecimal dsCpoLineSubNum = rs.getBigDecimal(DS_CPO_LINE_SUB_NUM);
                        String ordNum = "";
                        if (ZYPCommonFunc.hasValue(dsOrdPosnNum)) {
                            ordNum = dsOrdPosnNum;
                        }
                        if (ZYPCommonFunc.hasValue(dsCpoLineNum)) {
                            ordNum = ZYPCommonFunc.concatString(ordNum, ".", dsCpoLineNum.toPlainString());
                        }
                        if (ZYPCommonFunc.hasValue(dsCpoLineSubNum)) {
                            ordNum = ZYPCommonFunc.concatString(ordNum, ".", dsCpoLineSubNum.toPlainString());
                        }
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryFrthRefTxt, ordNum);
                        ZYPEZDItemValueSetter.setValue(jrnlEntry.jrnlEntryFrthAttrbNm, DISP_ORD_LINE_NUM);
                        // START 2019/08/10 S.Takami [QC#51897,ADD]
                        if (ZYPCommonFunc.hasValue(jrnlEntry.svcMachMstrPk)) {
                            setConfigId(jrnlEntry);
                        }
                        // END 2019/08/10 S.Takami [QC#51897,ADD]
                    
                    } catch (EZDAbendException exEz) {  // null exception
                        isError = true;
                        errMsgId = NFACommonJrnlEntryConstant.NULL_ERROR_MSG_ID;
                        errMsgTxt = exEz.getMessage();
                    
                    } catch (SQLException sqlEx) {
                        isError = true;
                        errMsgId = NFACommonJrnlEntryConstant.NFAM0035E;
                        errMsgTxt = sqlEx.getMessage();
                    }
    
                    insertIntoJrnlOrCtrl(isError, rs, jrnlEntry);
    
                } // while (rsAjeIntfcNotJrnlized.next())
    
                // process remaining data if exists
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
        private void insertIntoJrnlOrCtrl(boolean isError, ResultSet rs, JRNL_ENTRYTMsg jrnlEntry) throws SQLException, NFACommonJrnlEntry.JrnlCommonException {

            if (!isError) {
                // if amounts is $0, no need to create journal entry
            	// QC#21258
                //if (BigDecimal.ZERO.compareTo(jrnlEntry.jrnlFuncCrAmt.getValue()) != 0) {
                if (BigDecimal.ZERO.compareTo(jrnlEntry.jrnlFuncCrAmt.getValue()) != 0 || ZYPConstant.FLG_ON_Y.equals(ajeZeroCratFlg)) {
                    createJrnlEntry(jrnlEntry);
                } 
            }

                
            // Store the result in AJE_INTFC_CTRL.
            AJE_INTFC_CTRLTMsg ajeIntfcCtrl = new AJE_INTFC_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(ajeIntfcCtrl.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(ajeIntfcCtrl.ajeIntfcTpCd, AJE_INTFC_TP.OM);
            ZYPEZDItemValueSetter.setValue(ajeIntfcCtrl.ajeIntfcPk, rs.getBigDecimal(AJE_INV_ACCT_DIST_PK));
            ZYPEZDItemValueSetter.setValue(ajeIntfcCtrl.procDt, procDt);
            ZYPEZDItemValueSetter.setValue(ajeIntfcCtrl.ajePgmId, ajePgmId);
            ZYPEZDItemValueSetter.setValue(ajeIntfcCtrl.sysSrcCd, rs.getString(SYS_SRC_CD));
            ZYPEZDItemValueSetter.setValue(ajeIntfcCtrl.trxCd, rs.getString(TRX_CD));
            ZYPEZDItemValueSetter.setValue(ajeIntfcCtrl.trxRsnCd, rs.getString(TRX_RSN_CD));

            if (isError) {
                commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "jrnlCpltFlg", FLG_OFF_N);    
                commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "ajeErrMsgId", errMsgId);
                commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "ajeErrMsgTxt", errMsgTxt);

                errorCount += 1;
            } else {
                commonJrnlEntry.setFieldValue(ajeIntfcCtrl, "jrnlCpltFlg", FLG_ON_Y);
            }
            
            createAjeIntfcCtrl(ajeIntfcCtrl);
            
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

        // START 2019/08/10 S.Takami [QC#51897,ADD]
        private void setConfigId(JRNL_ENTRYTMsg jrnlEntry) {

            if (!ZYPCommonFunc.hasValue(jrnlEntry.glblCmpyCd) || !ZYPCommonFunc.hasValue(jrnlEntry.svcMachMstrPk)) {
                return;
            }
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, jrnlEntry.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, jrnlEntry.svcMachMstrPk);
            svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKey(svcMachMstrTMsg);
            if (svcMachMstrTMsg != null && ZYPCommonFunc.hasValue(svcMachMstrTMsg.svcConfigMstrPk)) {
                jrnlEntry.svcConfigMstrPk.setValue(svcMachMstrTMsg.svcConfigMstrPk.getValue()); 
            }
        }
        // END 2019/08/10 S.Takami [QC#51897,ADD]
    }


    /**
     * <pre>
     *  Get error transactions per invoice
     * </pre>
     * @return boolean
     */
    private Boolean doCheckInterfaceControl() {

        // ** Get Target Transactions in AJE Interface Control. **
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("ajeIntfcTpCd", AJE_INTFC_TP.OM);
        param.put("jrnlCpltFlg", FLG_OFF_N);

        return (Boolean) ssmBatchClient.queryObject("getErrorPerInv", param, new ErrorTrxHandler());
    }

    /**
     * <pre>
     *  If AJE error occurs, journal entries for the entire invoice should not be generated.
     * </pre>
     */
    private final class ErrorTrxHandler extends S21SsmBooleanResultSetHandlerSupport {

        private ErrorTrxHandler() {
        }

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            AJE_INTFC_CTRLTMsg ajeIntfcCtrlCond;
            JRNL_ENTRYTMsg jrnlEntryCond;
            
            // initialize
            EZDTMsg[] tmsgs = new EZDTMsg[BULK_INSERT_COUNT]; 
            EZDTMsg[] tmsgsJrnl = new EZDTMsg[BULK_INSERT_COUNT];
            ctrlMsgCount = 0;
            cnclJrnlCount = 0;
            
            BigDecimal intfcPk = BigDecimal.ZERO;
            
            try {
                while (rs.next()) {

                    // when it comes to new record for AJE_INTFC_CTRL
                    if (!rs.getBigDecimal(AJE_INTFC_PK).equals(intfcPk)) {
                        ajeIntfcCtrlCond = new AJE_INTFC_CTRLTMsg();
                        // AJE_INTFC_CTRLTMsg ajeIntfcCtrlUpd = new AJE_INTFC_CTRLTMsg();
                        
                        ZYPEZDItemValueSetter.setValue(ajeIntfcCtrlCond.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(ajeIntfcCtrlCond.ajeIntfcTpCd, AJE_INTFC_TP.OM);
                        ZYPEZDItemValueSetter.setValue(ajeIntfcCtrlCond.ajeIntfcPk, rs.getBigDecimal(AJE_INTFC_PK));
                        
                        ajeIntfcCtrlCond  = (AJE_INTFC_CTRLTMsg)S21FastTBLAccessor.findByKeyForUpdate(ajeIntfcCtrlCond);
                        
                        ZYPEZDItemValueSetter.setValue(ajeIntfcCtrlCond.jrnlCpltFlg, FLG_OFF_N);
                        
                        tmsgs[ctrlMsgCount] = ajeIntfcCtrlCond;
                        ctrlMsgCount += 1;
                        
                        // reset
                        intfcPk = rs.getBigDecimal(AJE_INTFC_PK);
                        
                        if (ctrlMsgCount >= BULK_INSERT_COUNT) {
                            S21FastTBLAccessor.update(tmsgs);
                            
                            // initialize
                            ctrlMsgCount = 0;
                            tmsgs = new EZDTMsg[BULK_INSERT_COUNT];
                        }
                    
                    }
                    
                    jrnlEntryCond = new JRNL_ENTRYTMsg();
                    
                    ZYPEZDItemValueSetter.setValue(jrnlEntryCond.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(jrnlEntryCond.jrnlEntryPk, rs.getBigDecimal(JRNL_ENTRY_PK));
                    
                    tmsgsJrnl[cnclJrnlCount] = jrnlEntryCond;
                    cnclJrnlCount += 1;
                    
                    if (cnclJrnlCount >= BULK_INSERT_COUNT) {
                        S21FastTBLAccessor.removePhysical(tmsgsJrnl);
                        
                        // initialize
                        cnclJrnlCount = 0;
                        tmsgsJrnl = new EZDTMsg[BULK_INSERT_COUNT];
                    }
                }

                // if any remaining, update and delete
                if (ctrlMsgCount != 0) {
                    S21FastTBLAccessor.update(tmsgs);   
                }
                if (cnclJrnlCount != 0) {
                    // resize the array
                    tmsgsJrnl = commonJrnlEntry.changeArraySize(tmsgsJrnl, cnclJrnlCount);
                    S21FastTBLAccessor.removePhysical(tmsgsJrnl);   
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
        setTermState(this.termCd, commitCount, errorCount, commitCount + errorCount);

        S21InfoLogOutput.println("termRoutine Method End");

    }

}
