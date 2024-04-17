/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLE.NLEB004001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.AJE_ASSET_TRX_INTFCTMsg;
import business.db.DS_ASSET_TRXTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Asset Transaction IF Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 05/21/2013   CSAI            Y.Imazu         Create          Modify for DS
 * 20/09/2013   CSAI            Y.Imazu         Update          Defect#2275
 * 03/16/2016   CSAI            K.Uramori       Update          Modification for CSA
 * 01/09/2016   Fujitsu         C.Tanaka        Update          QC#13360
 * 2017/03/01   Hitachi         J.Kim           Update          QC#17292
 * 10/26/2017   CITS            T.Tokutomi      Update          QC#22084
 * 2017/11/01   Hitachi         J.Kim           Update          QC#16345
 * 2018/01/18   Hitachi         J.Kim           Update          QC#17985
 *</pre>
 */

public class NLEB004001 extends S21BatchMain {

    /** Counter : Normal finished Records */
    private int counterNomalRec = 0;

    /** Counter : Error finished Records */
    private int counterErrorRec = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Process Date */
    private String procDt = null;

    /** Term Code */
    private TERM_CD termCd;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Error Message List */
    private List<String> errMsgList = new ArrayList<String>();

    /** Fatch size for SSM */
    private static final int FETCH_SIZE_MAX = 1000;

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {

        new NLEB004001().executeBatch(NLEB004001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;

        S21UserProfileService prof = S21UserProfileServiceFactory.getInstance().getService();
        glblCmpyCd = prof.getGlobalCompanyCode();

        if (S21StringUtil.isEmpty(glblCmpyCd)) {

            throw new S21AbendException(NLEB004001Constant.NASM0010E);
        }

        // QC#13360 ADD Start
        procDt = ZYPDateUtil.getBatProcDate(glblCmpyCd, NLEB004001Constant.PROGRAM_ID);
        if (!ZYPCommonFunc.hasValue(procDt)) {
            throw new S21AbendException(NLEB004001Constant.NLEM0001E, new String[] {NLEB004001Constant.BATCH_DATE });
        }
        // QC#13360 ADD End
    }

    @Override
    protected void mainRoutine() {

        // Insert AJE Asset Transaction IF
        insertAjeAssetTrxIntfc();

        if (!this.errMsgList.isEmpty()) {

            this.termCd = TERM_CD.WARNING_END;

            // Register Mail
            registerMail();

            // Commit
            commit();
        }
    }

    @Override
    protected void termRoutine() {

        // Set term code and total count.
        setTermState(this.termCd, counterNomalRec, counterErrorRec, (counterNomalRec + counterErrorRec));
    }

    /**
     * Insert AJE Asset Transaction Interface
     */
    private void insertAjeAssetTrxIntfc() {

        PreparedStatement stmtDsAssetTrx = null;
        ResultSet rsDsAssetTrx = null;

        try {

            S21SsmExecutionParameter excParam = new S21SsmExecutionParameter();
            excParam.setFetchSize(FETCH_SIZE_MAX);
            excParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            excParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            excParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            // Search target DS Asset Transaction data
            Map<String, Object> paramMsg = new HashMap<String, Object>();
            paramMsg.put(NLEB004001Constant.BIND_GLBL_CMPY_CD, glblCmpyCd);
            paramMsg.put(NLEB004001Constant.BIND_AJE_IF_CPLT_FLG, ZYPConstant.FLG_OFF_N);
            paramMsg.put(NLEB004001Constant.BIND_AJE_IF_REQ_FLG, ZYPConstant.FLG_ON_Y);
            // START 2017/03/01 J.Kim [QC#17292,ADD]
            paramMsg.put("rtlSwhDsblFlg", ZYPConstant.FLG_OFF_N);
            // END 2017/03/01 J.Kim [QC#17292,ADD]
            // START 2018/08/01 J.Kim [QC#26901,ADD]
            paramMsg.put("trxAdj", TRX.ADJUSTMENT);
            paramMsg.put("trxRsn", TRX_RSN.ASSET_INITIAL_COST_CHANGE);
            // END 2018/08/01 J.Kim [QC#26901,ADD]

            stmtDsAssetTrx = this.ssmLLClient.createPreparedStatement("getTargetDsAssetTrx", paramMsg, excParam);
            rsDsAssetTrx = stmtDsAssetTrx.executeQuery();

            // START 2016/11/01 J.Kim [QC#16345,DEL]
            //// QC#13360 ADD Start
            //PreparedStatement stmtDefCoa = null;
            //ResultSet rsDefCoa = null;
            // END 2016/11/01 J.Kim [QC#16345,DEL]
            // START 2016/11/01 J.Kim [QC#16345,DEL]
            //String assetCoaTrx = ZYPCodeDataUtil.getVarCharConstValue(NLEB004001Constant.ASSET_COA_TRX, glblCmpyCd);
            //if (!ZYPCommonFunc.hasValue(assetCoaTrx)) {
            //    throw new S21AbendException(NLEB004001Constant.NLEM0001E, new String[] {NLEB004001Constant.ASSET_COA_TRX });
            //}
            //String[] trxList = assetCoaTrx.split(NLEB004001Constant.STR_COMMA);
            //// QC#13360 ADD End
            // END 2016/11/01 J.Kim [QC#16345,DEL]

            dsAssetTrx: while (rsDsAssetTrx.next()) {

                // START 2016/11/01 J.Kim [QC#16345,DEL]
                //// QC#13360 ADD Start
                //StringBuffer trx = new StringBuffer();
                //trx.append(rsDsAssetTrx.getString(NLEB004001Constant.TRX_CD));
                //trx.append(NLEB004001Constant.STR_HYP);
                //trx.append(rsDsAssetTrx.getString(NLEB004001Constant.TRX_RSN_CD));
                //
                //boolean assetFlg = false;
                //for (String trxStr : trxList) {
                //    if (trxStr.equals(trx.toString())) {
                //        assetFlg = true;
                //    }
                //}
                //
                //String coaCmpyCd;
                //String coaBrCd;
                //String coaCcCd;
                //String coaAcctCd;
                //String coaProdCd;
                //String coaChCd;
                //String coaAfflCd;
                //String coaProjCd;
                //String coaExtnCd;
                //if (assetFlg) {
                //    // Get Asset COA
                //    coaCmpyCd = rsDsAssetTrx.getString(NLEB004001Constant.ASSET_COA_CMPY_CD);
                //    coaBrCd = rsDsAssetTrx.getString(NLEB004001Constant.ASSET_COA_BR_CD);
                //    coaCcCd = rsDsAssetTrx.getString(NLEB004001Constant.ASSET_COA_CC_CD);
                //    coaAcctCd = rsDsAssetTrx.getString(NLEB004001Constant.ASSET_COA_ACCT_CD);
                //    coaProdCd = rsDsAssetTrx.getString(NLEB004001Constant.ASSET_COA_PROD_CD);
                //    coaChCd = rsDsAssetTrx.getString(NLEB004001Constant.ASSET_COA_CH_CD);
                //    coaAfflCd = rsDsAssetTrx.getString(NLEB004001Constant.ASSET_COA_AFFL_CD);
                //    coaProjCd = rsDsAssetTrx.getString(NLEB004001Constant.ASSET_COA_PROJ_CD);
                //    coaExtnCd = rsDsAssetTrx.getString(NLEB004001Constant.ASSET_COA_EXTN_CD);
                //} else {
                //    // Get Expense COA
                //    coaCmpyCd = rsDsAssetTrx.getString(NLEB004001Constant.COA_CMPY_CD);
                //    coaBrCd = rsDsAssetTrx.getString(NLEB004001Constant.COA_BR_CD);
                //    coaCcCd = rsDsAssetTrx.getString(NLEB004001Constant.COA_CC_CD);
                //    coaAcctCd = rsDsAssetTrx.getString(NLEB004001Constant.COA_ACCT_CD);
                //    coaProdCd = rsDsAssetTrx.getString(NLEB004001Constant.COA_PROD_CD);
                //    coaChCd = rsDsAssetTrx.getString(NLEB004001Constant.COA_CH_CD);
                //    coaProjCd = rsDsAssetTrx.getString(NLEB004001Constant.COA_PROJ_CD);
                //    coaAfflCd = rsDsAssetTrx.getString(NLEB004001Constant.COA_AFFL_CD);
                //    coaExtnCd = rsDsAssetTrx.getString(NLEB004001Constant.COA_EXTN_CD);
                //}
                //
                //// Get Default COA
                //if (!ZYPCommonFunc.hasValue(coaCmpyCd) || !ZYPCommonFunc.hasValue(coaBrCd) || !ZYPCommonFunc.hasValue(coaCcCd) //
                //        || ZYPCommonFunc.hasValue(coaAcctCd) || !ZYPCommonFunc.hasValue(coaProdCd) || !ZYPCommonFunc.hasValue(coaChCd) // 
                //        || ZYPCommonFunc.hasValue(coaAfflCd) || !ZYPCommonFunc.hasValue(coaProjCd) || !ZYPCommonFunc.hasValue(coaExtnCd)) {
                //    Map<String, Object> param = new HashMap<String, Object>();
                //    param.put(NLEB004001Constant.BIND_GLBL_CMPY_CD, glblCmpyCd);
                //    param.put(NLEB004001Constant.BIND_NEW_CORE, ZYPCodeDataUtil.getVarCharConstValue(NLEB004001Constant.AJE_NEW_CORE_CD, glblCmpyCd));
                //    param.put(NLEB004001Constant.BIND_DEF_BR_NEW_CORE, ZYPCodeDataUtil.getVarCharConstValue(NLEB004001Constant.AJE_DEF_COA_BR_NONCORE, glblCmpyCd));
                //    // QC#22084 Modify Start.
                //    String[] coaDefValues = ZYPCodeDataUtil.getVarCharConstValue(NLEB004001Constant.AJE_COA_DEF_VALUES, glblCmpyCd).split(",");
                //    param.put(NLEB004001Constant.BIND_DEF_CH_NEW_CORE, coaDefValues[5]); // channel code
                //    param.put(NLEB004001Constant.BIND_DEF_AF_NEW_CORE, coaDefValues[6]); // affiliate code
                //    // QC#22084 Modify End.
                //    param.put(NLEB004001Constant.BIND_DEF_CC_NEW_CORE, ZYPCodeDataUtil.getVarCharConstValue(NLEB004001Constant.AJE_DEF_COA_CC_NONCORE, glblCmpyCd));
                //    param.put(NLEB004001Constant.BIND_DS_ASSET_MSTR_PK, rsDsAssetTrx.getBigDecimal(NLEB004001Constant.DS_ASSET_MSTR_PK));
                //    param.put(NLEB004001Constant.BIND_SLS_DT, procDt);
                //
                //    stmtDefCoa = this.ssmLLClient.createPreparedStatement("getDefultCoa", param, excParam);
                //    rsDefCoa = stmtDefCoa.executeQuery();
                //    while (rsDefCoa.next()) {
                //
                //        if (!ZYPCommonFunc.hasValue(coaCmpyCd)) {
                //            coaCmpyCd = glblCmpyCd;
                //        }
                //        if (!ZYPCommonFunc.hasValue(coaBrCd)) {
                //            if (assetFlg) {
                //                coaBrCd = rsDefCoa.getString(NLEB004001Constant.ASSET_COA_BR_CD);
                //            } else {
                //                coaBrCd = rsDefCoa.getString(NLEB004001Constant.COA_BR_CD);
                //            }
                //        }
                //        if (!ZYPCommonFunc.hasValue(coaCcCd)) {
                //            if (assetFlg) {
                //                coaCcCd = rsDefCoa.getString(NLEB004001Constant.ASSET_COA_CC_CD);
                //            } else {
                //                coaCcCd = rsDefCoa.getString(NLEB004001Constant.COA_CC_CD);
                //            }
                //        }
                //        if (!ZYPCommonFunc.hasValue(coaAcctCd)) {
                //            if (assetFlg) {
                //                coaAcctCd = rsDefCoa.getString(NLEB004001Constant.ASSET_COA_ACCT_CD);
                //            } else {
                //                coaAcctCd = rsDefCoa.getString(NLEB004001Constant.COA_ACCT_CD);
                //            }
                //        }
                //        if (!ZYPCommonFunc.hasValue(coaProdCd)) {
                //            coaProdCd = rsDefCoa.getString(NLEB004001Constant.COA_PROD_CD);
                //        }
                //        if (!ZYPCommonFunc.hasValue(coaChCd)) {
                //            coaChCd = rsDefCoa.getString(NLEB004001Constant.COA_CH_CD);
                //        }
                //        if (!ZYPCommonFunc.hasValue(coaAfflCd)) {
                //            coaAfflCd = rsDefCoa.getString(NLEB004001Constant.COA_AFFL_CD);
                //        }
                //        if (!ZYPCommonFunc.hasValue(coaProjCd)) {
                //            coaProjCd = rsDefCoa.getString(NLEB004001Constant.COA_PROJ_CD);
                //        }
                //        if (!ZYPCommonFunc.hasValue(coaExtnCd)) {
                //            coaExtnCd = rsDefCoa.getString(NLEB004001Constant.COA_EXTN_CD);
                //        }
                //
                //        break;
                //    }
                //}
                //// QC#13360 ADD End
                // END 2016/11/01 J.Kim [QC#16345,DEL]

                /*************************************************************
                 * 1. Insert AJE Asset Transaction Interface
                 ************************************************************/
                AJE_ASSET_TRX_INTFCTMsg insertMsg = new AJE_ASSET_TRX_INTFCTMsg();
                BigDecimal ajeAssetTrxIntfcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AJE_ASSET_TRX_INTFC_SQ);

                ZYPEZDItemValueSetter.setValue(insertMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(insertMsg.ajeAssetTrxIntfcPk, ajeAssetTrxIntfcPk);
                ZYPEZDItemValueSetter.setValue(insertMsg.glDt, rsDsAssetTrx.getString(NLEB004001Constant.ASSET_TRX_DT));
                ZYPEZDItemValueSetter.setValue(insertMsg.sysSrcCd, rsDsAssetTrx.getString(NLEB004001Constant.SYS_SRC_CD));
                ZYPEZDItemValueSetter.setValue(insertMsg.trxCd, rsDsAssetTrx.getString(NLEB004001Constant.TRX_CD));
                ZYPEZDItemValueSetter.setValue(insertMsg.trxRsnCd, rsDsAssetTrx.getString(NLEB004001Constant.TRX_RSN_CD));
                ZYPEZDItemValueSetter.setValue(insertMsg.assetTpCd, rsDsAssetTrx.getString(NLEB004001Constant.ASSET_TP_CD));
                ZYPEZDItemValueSetter.setValue(insertMsg.billToCustCd, rsDsAssetTrx.getString(NLEB004001Constant.BILL_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(insertMsg.mdseCd, rsDsAssetTrx.getString(NLEB004001Constant.MDSE_CD));
                ZYPEZDItemValueSetter.setValue(insertMsg.tocCd, rsDsAssetTrx.getString(NLEB004001Constant.SLS_REP_TOC_CD));
                // START 2018/01/18 J.Kim [QC#17985,MOD]
                // ZYPEZDItemValueSetter.setValue(insertMsg.origInvNum, rsDsAssetTrx.getString(NLEB004001Constant.INV_NUM));
                ZYPEZDItemValueSetter.setValue(insertMsg.ajeInvNum, rsDsAssetTrx.getString(NLEB004001Constant.AP_VND_INV_NUM));
                // END 2018/01/18 J.Kim [QC#17985,MOD]
                ZYPEZDItemValueSetter.setValue(insertMsg.invQty, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(insertMsg.serNum, rsDsAssetTrx.getString(NLEB004001Constant.SER_NUM));
                ZYPEZDItemValueSetter.setValue(insertMsg.origValAmt, rsDsAssetTrx.getBigDecimal(NLEB004001Constant.INIT_BOOK_AMT));
                // START 2016/11/01 J.Kim [QC#16345,DEL]
                //// QC#13360 MOD Start
                //ZYPEZDItemValueSetter.setValue(insertMsg.coaBrCd, coaBrCd);
                //ZYPEZDItemValueSetter.setValue(insertMsg.coaCcCd, coaCcCd);
                //ZYPEZDItemValueSetter.setValue(insertMsg.coaAcctCd, coaAcctCd);
                //ZYPEZDItemValueSetter.setValue(insertMsg.coaProdCd, coaProdCd);
                //ZYPEZDItemValueSetter.setValue(insertMsg.coaChCd, coaChCd);
                //ZYPEZDItemValueSetter.setValue(insertMsg.coaProjCd, coaProjCd);
                //// QC#13360 MOD End
                // END 2016/11/01 J.Kim [QC#16345,DEL]

                ZYPEZDItemValueSetter.setValue(insertMsg.origValAmt, rsDsAssetTrx.getBigDecimal(NLEB004001Constant.INIT_BOOK_AMT));
                // If current value amount is $0, prev value amount
                // will be set. This is done by SQL.
                ZYPEZDItemValueSetter.setValue(insertMsg.curValAmt, rsDsAssetTrx.getBigDecimal(NLEB004001Constant.CUR_VAL_AMT));
                ZYPEZDItemValueSetter.setValue(insertMsg.curDepcAmt, rsDsAssetTrx.getBigDecimal(NLEB004001Constant.DEPC_VAL_AMT));

                // CSA New
                ZYPEZDItemValueSetter.setValue(insertMsg.assetAdjAmt, rsDsAssetTrx.getBigDecimal("ASSET_ADJ_AMT"));
                ZYPEZDItemValueSetter.setValue(insertMsg.dsAssetTrxPk, rsDsAssetTrx.getBigDecimal("DS_ASSET_TRX_PK"));
                ZYPEZDItemValueSetter.setValue(insertMsg.sellToCustCd, rsDsAssetTrx.getString("SELL_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(insertMsg.shipToCustCd, rsDsAssetTrx.getString("SHIP_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(insertMsg.sldToCustLocCd, rsDsAssetTrx.getString("SLD_TO_CUST_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(insertMsg.shipToCustAcctCd, rsDsAssetTrx.getString("SHIP_TO_CUST_ACCT_CD"));
                ZYPEZDItemValueSetter.setValue(insertMsg.vndCd, rsDsAssetTrx.getString("VND_CD"));
                ZYPEZDItemValueSetter.setValue(insertMsg.cpoOrdNum, rsDsAssetTrx.getString("CPO_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(insertMsg.poOrdNum, rsDsAssetTrx.getString("PO_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(insertMsg.svcMachMstrPk, rsDsAssetTrx.getBigDecimal("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(insertMsg.rtrnWhCd, rsDsAssetTrx.getString("RTRN_WH_CD"));

                // START 2016/11/01 J.Kim [QC#16345,MOD]
                //// QC#13360 MOD Start
                //// ---- start 2016/07/11 add missing COA segments
                //ZYPEZDItemValueSetter.setValue(insertMsg.coaCmpyCd, coaCmpyCd);
                //ZYPEZDItemValueSetter.setValue(insertMsg.coaAfflCd, coaAfflCd);
                //ZYPEZDItemValueSetter.setValue(insertMsg.coaExtnCd, coaExtnCd);
                //// ---- end 2016/07/11
                //// QC#13360 MOD End
                ZYPEZDItemValueSetter.setValue(insertMsg.coaCmpyCd, rsDsAssetTrx.getString(NLEB004001Constant.COA_CMPY_CD));
                ZYPEZDItemValueSetter.setValue(insertMsg.coaBrCd, rsDsAssetTrx.getString(NLEB004001Constant.COA_BR_CD));
                ZYPEZDItemValueSetter.setValue(insertMsg.coaCcCd, rsDsAssetTrx.getString(NLEB004001Constant.COA_CC_CD));
                ZYPEZDItemValueSetter.setValue(insertMsg.coaAcctCd, rsDsAssetTrx.getString(NLEB004001Constant.COA_ACCT_CD));
                ZYPEZDItemValueSetter.setValue(insertMsg.coaProdCd, rsDsAssetTrx.getString(NLEB004001Constant.COA_PROD_CD));
                ZYPEZDItemValueSetter.setValue(insertMsg.coaChCd, rsDsAssetTrx.getString(NLEB004001Constant.COA_CH_CD));
                ZYPEZDItemValueSetter.setValue(insertMsg.coaProjCd, rsDsAssetTrx.getString(NLEB004001Constant.COA_PROJ_CD));
                ZYPEZDItemValueSetter.setValue(insertMsg.coaAfflCd, rsDsAssetTrx.getString(NLEB004001Constant.COA_AFFL_CD));
                ZYPEZDItemValueSetter.setValue(insertMsg.coaExtnCd, rsDsAssetTrx.getString(NLEB004001Constant.COA_EXTN_CD));
                // END 2016/11/01 J.Kim [QC#16345,MOD]

                // ---- start add 2016/06/24
                ZYPEZDItemValueSetter.setValue(insertMsg.prntDsAssetMstrPk, rsDsAssetTrx.getBigDecimal("PRNT_DS_ASSET_MSTR_PK"));
                // ---- end 2016/06/24

                // Insert AJE Asset Transaction Interface
                EZDTBLAccessor.insert(insertMsg);

                // Insert Error
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertMsg.getReturnCode())) {

                    setDBErrMsg(insertMsg.getTableName(), rsDsAssetTrx.getBigDecimal(NLEB004001Constant.DS_ASSET_TRX_PK), NLEB004001Constant.NASM0006E);
                    continue dsAssetTrx;
                }

                /*************************************************************
                 * 2. Update DS Asset Transaction
                 ************************************************************/
                DS_ASSET_TRXTMsg dsAssetTrxtMsg = new DS_ASSET_TRXTMsg();
                ZYPEZDItemValueSetter.setValue(dsAssetTrxtMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsAssetTrxtMsg.dsAssetTrxPk, rsDsAssetTrx.getBigDecimal(NLEB004001Constant.DS_ASSET_TRX_PK));

                DS_ASSET_TRXTMsg updateMsg = (DS_ASSET_TRXTMsg) EZDTBLAccessor.findByKeyForUpdateWait(dsAssetTrxtMsg);

                // Data does not exist
                if (updateMsg == null) {

                    setDBErrMsg(dsAssetTrxtMsg.getTableName(), rsDsAssetTrx.getBigDecimal(NLEB004001Constant.DS_ASSET_TRX_PK), NLEB004001Constant.NASM0012E);
                    continue dsAssetTrx;
                }

                // AJE Complete Flag
                ZYPEZDItemValueSetter.setValue(updateMsg.ajeIfCpltFlg, ZYPConstant.FLG_ON_Y);

                // Update DS Asset Transaction
                EZDTBLAccessor.updateSelectionField(updateMsg, new String[] {"ajeIfCpltFlg" });

                // Update Error
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateMsg.getReturnCode())) {

                    setDBErrMsg(updateMsg.getTableName(), rsDsAssetTrx.getBigDecimal(NLEB004001Constant.DS_ASSET_TRX_PK), NLEB004001Constant.NASM0007E);
                    continue dsAssetTrx;

                } else {

                    commit();
                    counterNomalRec++;
                }
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmtDsAssetTrx, rsDsAssetTrx);
        }
    }

    /**
     * Set DB Error Message
     * @param tableName String
     * @param dsAssetTrxPk BigDecimal
     * @param errMsgId String
     */
    private void setDBErrMsg(String tableName, BigDecimal dsAssetTrxPk, String errMsgId) {

        rollback();
        counterErrorRec++;

        String msgGlblCmpyCd = ZYPCommonFunc.concatString(NLEB004001Constant.GLBL_CMPY_CD, " : ", glblCmpyCd);
        String msgDsAssetTrxPk = ZYPCommonFunc.concatString(NLEB004001Constant.DS_ASSET_TRX_PK, " : ", dsAssetTrxPk.toString());
        String msgParam = ZYPCommonFunc.concatString(msgGlblCmpyCd, ", ", msgDsAssetTrxPk);
        String[] errMsg = new String[] {tableName, msgParam };

        S21InfoLogOutput.println(errMsgId, errMsg);
        this.errMsgList.add(S21MessageFunc.clspGetMessage(errMsgId, errMsg));
    }

    /**
     * Register Mail
     */
    private void registerMail() {

        /*************************************************************
         * 1. Get From
         ************************************************************/
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, NLEB004001Constant.MAIL_GROUP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;

        if (!addrFromList.isEmpty()) {

            from = addrFromList.get(0);
        }

        /*************************************************************
         * 2. Get To
         ************************************************************/
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, NLEB004001Constant.MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();

        if (addrToList.isEmpty()) {

            throw new S21AbendException(NLEB004001Constant.NWBM0092E);
        }

        /*************************************************************
         * 3. Create message for Body
         ************************************************************/
        StringBuilder msgBuf = new StringBuilder();
        msgBuf.append(NLEB004001Constant.MAIL_MSG_HEADER);
        msgBuf.append(NLEB004001Constant.LINE_FEED_CODE);

        for (int i = 0; i < errMsgList.size(); i++) {

            msgBuf.append((String) errMsgList.get(i));
            msgBuf.append(NLEB004001Constant.LINE_FEED_CODE);
        }

        String message = msgBuf.toString();

        /*************************************************************
         * 4. Create Subject and Body
         ************************************************************/
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, NLEB004001Constant.MAIL_TEMPLATE_ID);

        if (template == null) {

            throw new S21AbendException(NLEB004001Constant.NWBM0092E);
        }

        String currentTime = ZYPDateUtil.getCurrentSystemTime(NLEB004001Constant.DATE_TIME_PATTERN_FOR_MAIL);

        template.setTemplateParameter(NLEB004001Constant.MAIL_TEMPLATE_KEY_BATCH_ID, NLEB004001Constant.BUSINESS_ID);
        template.setTemplateParameter(NLEB004001Constant.MAIL_TEMPLATE_KEY_DATE, currentTime);
        template.setTemplateParameter(NLEB004001Constant.MAIL_TEMPLATE_KEY_MESSAGE, message);

        /*************************************************************
         * 5. Call Mail API
         ************************************************************/
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
    }
}
