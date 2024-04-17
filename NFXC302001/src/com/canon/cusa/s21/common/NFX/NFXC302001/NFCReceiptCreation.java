/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC302001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDDBCICarrier;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_RCPT_DTLTMsg;
import business.db.AR_RCPT_IN_PROC_WRKTMsg;
import business.db.AR_RCPT_UN_APPLYTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.CUST_CR_PRFLTMsg;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import com.canon.cusa.s21.common.NFX.NFXC304001.NFCProcessStatus;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFCNumbering;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFXC3060Bean;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFCCurrencyConversion;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFXC3070Bean;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCDbConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CRAT_METH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * NFCReceiptCreation Receipt Creation
 *
 * Date         Company       Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/17/2009   Canon         M.Moriyama      Create          N/A
 * 11/09/2009   Canon         M.Moriyama      Update          DefID 0727,1504
 * 12/08/2009   Canon         K.Usui          Update          DefID 2371
 * 01/25/2010   Canon         M.Moriyama      Update          For TOC/PROD Code
 * 03/29/2010   Canon         K.Kimura        Update          DefID 5064
 * 04/03/2010   Canon         H.Tokunaga      Update          DefID 5423
 * 05/19/2010   Canon         I.Kondo         Update          DefID 6444 No.017
 * 06/02/2010   Canon         I.Kondo         Update          DefID 6836 No.074
 * 09/08/2010   Canon         I.Kondo         Update          DefID 7343 No.373
 * 09/09/2010   Canon         T.Tanaka        Update          DefID 8075
 * 09/22/2010   Canon         I.Kondo         Update          DefID 8104 No.397
 * 10/08/2010   Canon         T.Tanaka        Update          Merge R2->R3
 * 10/28/2010   Canon         T.Tanaka        Update          DefID M57
 * 01/22/2011   Canon         T.Tanaka        Update          DefID 1133 for Batch
 * 02/07/2011   Canon         K.Kimura        Update
 * 03/07/2011   Canon         Y.Suga          Update          DefID 1654
 * 05/05/2011   Canon         T.Tanaka        Update          DefID 2173
 * 07/12/2011   Canon         K.Kimura        Update          DefID 2378
 * 04/02/2015   Fujitsu       T.Yoshida       Update          for North America(CSA)
 * 10/05/2015   Fujitsu       T.Tanaka        Update          delete AR_TRX_CONSL_NUM,INV_CONSL_CD
 * 10/05/2015   Fujitsu       T.Tanaka        Update          delete call NMZC600001
 * 03/02/2016   Fujitsu       T.Tanaka        Update          Def#4999
 * 08/29/2016   Fujitsu       S.Fujita        Update          QC#12541
 * 02/20/2017   Hitachi       E.Kameishi      Update          QC#16802
 * 12/07/2017   Fujitsu       M.Ohno          Update          QC#21397
 * 12/11/2017   Fujitsu       M.Ohno          Update          QC#22262
 * 2018/06/11   Fujitsu       H.Ikeda         Update          QC#25692
 * 2018/06/26   Fujitsu       Y.Matsui        Update          QC#26788
 * 2018/07/27   Hitachi       E.Kameishi      Update          QC#27419
 * 2018/09/20   Fujitsu       T.Ogura         Update          QC#28097
 * 2020/07/30   CITS          R.Kurahashi     Update          QC#57436
 * 2022/03/15   Hitachi       R,Takau         Update          QC#55645
 *</pre>
 */
public class NFCReceiptCreation extends S21ApiCommonBase {

    /** RCPT_INTIME */
    private static final String RCPT_INTIME = "RCPT_INTIME";

    /** RCPT_INTMZN */
    private static final String RCPT_INTMZN = "RCPT_INTMZN";

    /** TRX_INTIME */
    private static final String TRX_INTIME = "TRX_INTIME";

    /** TRX_INTMZN */
    private static final String TRX_INTMZN = "TRX_INTMZN";

    /** FORMAT_DATE */
    private static final String FORMAT_DATE = "yyyyMMdd";

    /** AR_CASH_APP_SQ_NUM_ORIG */
    private static final String AR_CASH_APP_SQ_NUM_ORIG = "00001";

    /** Format of AR_RCPT_DTL.RCPT_DTL_NUM */
    private static final String RCPT_DTL_NUM_FORMAT = "%04d";

    /** gcc */
    private String gcc = null;

    /** agk */
    private String agk = null;

    /** rcvHdrNum */
    private String rcvHdrNum = null;

    /** afxc3060 */
    private NFCNumbering afxc3060 = null;

    /**
     * NFCReceiptCreation Constructor.
     */
    public NFCReceiptCreation() {
        super();
    }

    /**
     * <pre>
     * </pre>
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param applyGrpKey APPLY_GRP_KEY
     * @param opeDate String
     * @param rcvHdrNumParam String
     * @return NFXC3020Bean
     */
    public NFXC3020Bean execute(String glblCmpyCd, String applyGrpKey, String opeDate, String rcvHdrNumParam) {
        return this.execute(glblCmpyCd, applyGrpKey, opeDate, rcvHdrNumParam, null);
    }

    // START 2020/07/30 R.Kurahashi [QC#57436,ADD]
    /**
     * @param glblCmpyCd String
     * @param applyGrpKey String
     * @param opeDate String
     * @param rcvHdrNumParam String
     * @param arRcptTrxTpCd String
     * @return NFXC3020Bean
     */
    public NFXC3020Bean execute(String glblCmpyCd, String applyGrpKey, String opeDate, String rcvHdrNumParam, String arRcptTrxTpCd) {
        return this.execute(glblCmpyCd, applyGrpKey, opeDate, rcvHdrNumParam, arRcptTrxTpCd, null);
    }
    // END 2020/07/30 R.Kurahashi [QC#57436,ADD]
        
    // START 2020/07/30 R.Kurahashi [QC#57436,MOD]
    /**
     * @param glblCmpyCd String
     * @param applyGrpKey String
     * @param opeDate String
     * @param rcvHdrNumParam String
     * @param arRcptTrxTpCd String
     * @param crCardLastDigitNum String
     * @return NFXC3020Bean
     */
    //public NFXC3020Bean execute(String glblCmpyCd, String applyGrpKey, String opeDate, String rcvHdrNumParam, String arRcptTrxTpCd) {
    public NFXC3020Bean execute(String glblCmpyCd, String applyGrpKey, String opeDate, String rcvHdrNumParam, String arRcptTrxTpCd, String crCardLastDigitNum) {
    // END 2020/07/30 R.Kurahashi [QC#57436,MOD]

        NFXC3020Bean rtrnVal = new NFXC3020Bean();
        rtrnVal.setRtrnNo(NFCConst.CST_RTN_CD_ERR);

        int procCnt = 0;
        String preRcptHdrData = "";
        String trgtRcptHdrData = "";
        String rcptHdrNum = "";
        BigDecimal arTrxBalPk;
        int rcptDtlNo = 0;
        String rcptDtlNoStr = "";

        /* 1.1 Input Parameter Check */
        if (!checkInParamExecute(glblCmpyCd, applyGrpKey, opeDate, rcvHdrNumParam)) {
            return rtrnVal;

        }

        /* 1.2 AR_APPLY_INTFC_WRK */
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NFCDbConst.GLBL_CMPY_CD, this.gcc);
        queryParam.put(NFCDbConst.APPLY_GRP_KEY, this.agk);
        queryParam.put(NFCDbConst.RCV_HDR_NUM, this.rcvHdrNum);

        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        S21SsmExecutionParameter ssmEexcParam = null;
        PreparedStatement stmtRcptAccntWaitData = null;
        ResultSet rsTrgtData = null;

        try {
            ssmEexcParam = new S21SsmExecutionParameter();
            ssmEexcParam.setFetchSize(NFCConst.CST_SSM_CURSOR_PARAMETER_FETCH_SIZE);
            ssmEexcParam.setMaxRows(NFCConst.CST_SSM_CURSOR_PARAMETER_MAXROWS);
            stmtRcptAccntWaitData = ssmLLClient.createPreparedStatement("getRcptAccntWaitData", queryParam, ssmEexcParam);
            rsTrgtData = stmtRcptAccntWaitData.executeQuery();

            if (!rsTrgtData.next()) {
                rtrnVal.setProcCnt(0);
                rtrnVal.setRtrnNo(NFCConst.CST_RTN_CD_NORM);
                return rtrnVal;
            }

            /* */
            this.afxc3060 = new NFCNumbering();
            NFCProcessStatus afxc3040 = new NFCProcessStatus();
            String afxc3040Rtrn = "";
            String arCashApplyStsCd = "";

            /*
             */
            for (;;) {
                // START 2018/06/11 H.Ikeda [QC#25692,MOD]
                //trgtRcptHdrData = rsTrgtData.getBigDecimal(NFCDbConst.RCPT_IN_PROC_SQ_PK).toString() + rsTrgtData.getString(NFCDbConst.RCV_HDR_NUM);
                trgtRcptHdrData = rsTrgtData.getBigDecimal(NFCDbConst.RCPT_IN_PROC_SQ_PK).toString() + rsTrgtData.getString(NFCDbConst.RCV_HDR_NUM) + rsTrgtData.getString(NFCDbConst.RCPT_BAT_SQ_NUM);
                // END    2018/06/11 H.Ikeda [QC#25692,MOD]
                /* Recept Header */
                if (!preRcptHdrData.equals(trgtRcptHdrData)) {

                    // AR_CASH_APPLY_STS_CD is defined.
                    if (BigDecimal.ZERO.equals(rsTrgtData.getBigDecimal(NFCDbConst.DEAL_RCPT_RMNG_BAL_AMT))) {

                        arCashApplyStsCd = NFCConst.CST_AR_CASH_APPLY_STS_CD_APPLY;

                    } else if (rsTrgtData.getBigDecimal(NFCDbConst.DEAL_RCPT_AMT).equals(rsTrgtData.getBigDecimal(NFCDbConst.DEAL_RCPT_RMNG_BAL_AMT)) && BigDecimal.ZERO.equals(rsTrgtData.getBigDecimal(NFCDbConst.DEAL_APPLY_AMT))
                            && BigDecimal.ZERO.equals(rsTrgtData.getBigDecimal(NFCDbConst.DEAL_RF_AMT)) && BigDecimal.ZERO.equals(rsTrgtData.getBigDecimal(NFCDbConst.DEAL_VOID_AMT))) {

                        arCashApplyStsCd = NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY;

                    } else {

                        arCashApplyStsCd = NFCConst.CST_AR_CASH_APPLY_STS_CD_PARTIAL;

                    }
                    debugLog("AR_CASH_APPLY_STS_CD = " + arCashApplyStsCd);

                    if (EZDDBCICarrier.getUppgID().equals(NFCConst.AR_CASH_APPLY_BATCH_ID)) {
                        rcptHdrNum = ZYPNumbering.getUniqueID(this.gcc, NFCConst.CST_NUMBERING_KEY_RC_BAT);
                    } else {
                        rcptHdrNum = ZYPNumbering.getUniqueID(this.gcc, NFCConst.CST_NUMBERING_KEY_RC);
                    }
                    // START 2020/07/30 R.Kurahashi [QC#57436,MOD]
                    //if (!insertArRcpt(rcptHdrNum, arCashApplyStsCd, opeDate, rsTrgtData)) {
                    if (!insertArRcpt(rcptHdrNum, arCashApplyStsCd, opeDate, rsTrgtData, crCardLastDigitNum)) {
                    // END 2020/07/30 R.Kurahashi [QC#57436,MOD]
                        return rtrnVal;
                    }

                    NFXC3060Bean rtrn3060 = this.afxc3060.getNumber(NFCConst.CST_SEQ_ID_AR_TRX_BAL, "", 0);
                    if (NFCConst.CST_RTN_CD_ERR.equals(rtrn3060.getRtrnNo())) {
                        return rtrnVal;
                    } else {
                        arTrxBalPk = rtrn3060.getOraSqNo();
                    }

                    if (!insertArTrxBal(arTrxBalPk, rcptHdrNum, arCashApplyStsCd, opeDate, rsTrgtData)) {
                        return rtrnVal;
                    }

                    if (!insertArRcptUnApply(rcptHdrNum, rsTrgtData, opeDate)) {
                        return rtrnVal;
                    }

                    afxc3040Rtrn = afxc3040.setProcessStatus(this.gcc, rsTrgtData.getBigDecimal(NFCDbConst.RCPT_IN_PROC_SQ_PK), rsTrgtData.getString(NFCDbConst.RCV_HDR_NUM), NFCConst.CST_PROC_STS_CHK, "", "", rcptHdrNum);
                    if (NFCConst.CST_RTN_CD_ERR.equals(afxc3040Rtrn)) {
                        return rtrnVal;
                    }

// 10/05/2015 T.Tanaka Start
//                    if (!(S21StringUtil.isEmpty(rsTrgtData.getString(NFCDbConst.PAYER_CUST_CD))) && !(BigDecimal.ZERO.equals(rsTrgtData.getBigDecimal(NFCDbConst.DEAL_RCPT_AMT)))) {
//                        // Credit Balance is renewed.
//                        if (!updateCreditBalance(opeDate, rcptHdrNum, rsTrgtData)) {
//                            debugLog("updateCreditBalance error.");
//                            return rtrnVal;
//                        }
//                    }
// 10/05/2015 T.Tanaka Start

                    queryParam.clear();
                    queryParam.put(NFCDbConst.GLBL_CMPY_CD, this.gcc);
                    queryParam.put(NFCDbConst.RCPT_NUM, rcptHdrNum);
                    queryParam.put(NFCDbConst.AR_TRX_BAL_PK, arTrxBalPk);

                    /* AR_RCPT, AR_TRX_BAL */
                    if (!getEzInData(queryParam)) {
                        return rtrnVal;
                    }

                    /* AR_APPLY_INTC_WRK for Rcpt Header */
                    if (!updateArApplyIntfcWrkHdr(rcptHdrNum, queryParam, rsTrgtData)) {
                        return rtrnVal;
                    }

                    rcptDtlNo = 1;
                    preRcptHdrData = trgtRcptHdrData;

                } else {
                    rcptDtlNo++;
                }

                rcptDtlNoStr = String.format(RCPT_DTL_NUM_FORMAT, rcptDtlNo);

                /* */
                if (!insertArRcptDtl(rcptHdrNum, rcptDtlNoStr, rsTrgtData)) {
                    return rtrnVal;
                }

                /* */
                if (!updateArApplyIntfcWrkDtl(rsTrgtData.getBigDecimal(NFCDbConst.APPLY_GRP_SUB_PK), rcptDtlNoStr)) {
                    return rtrnVal;
                }

                /* */
                if (!updateArRcptInfProcWrk(rsTrgtData, NFCConst.CST_RCPT_STS_CD_NORM)) {
                    return rtrnVal;
                }
                procCnt++;

                if (!rsTrgtData.next()) {
                    break;
                }
            }
            rtrnVal.setProcCnt(procCnt);
            rtrnVal.setRtrnNo(NFCConst.CST_RTN_CD_NORM);

        } catch (SQLException sqlE) {
            sqlExceptionHandler(sqlE);

        } finally {
            /* */
            S21SsmLowLevelCodingClient.closeResource(stmtRcptAccntWaitData, rsTrgtData);

        }

        return rtrnVal;

    }

    /**
     * <pre>
     * </pre>
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param applyGrpKey APPLY_GRP_KEY
     * @param opeDate String
     * @param rcvHdrNumParam String
     */
    private boolean checkInParamExecute(String glblCmpyCd, String applyGrpKey, String opeDate, String rcvHdrNumParam) {

        if (S21StringUtil.isEmpty(glblCmpyCd)) {
            return false;
        } else {
            this.gcc = glblCmpyCd;
        }

        if (S21StringUtil.isEmpty(applyGrpKey)) {
            return false;
        } else {
            this.agk = applyGrpKey;
        }

        if (S21StringUtil.isEmpty(opeDate)) {
            return false;
        }

        if (S21StringUtil.isEmpty(rcvHdrNumParam)) {
            return false;
        } else {
            this.rcvHdrNum = rcvHdrNumParam;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(opeDate);
        } catch (Exception e) {
            return false;
        }

        return true;

    }

    // START 2020/07/30 R.Kurahashi [QC#57436,MOD]
    /**
     * insertArRcpt
     * 
     * <pre>
     * AR_RCPT record is registered. 
     * </pre>
     * @param rcptHdrNum
     * @param arCashApplyStsCd
     * @param cashAppDt
     * @param trgtRsltSet
     * @param crCardLastDigitNum
     * @return True:Success / False:Filure
     * @throws SQLException SQLException
     */
    //private boolean insertArRcpt(String rcptHdrNum, String arCashApplyStsCd, String cashAppDt, ResultSet trgtRsltSet) throws SQLException {
    private boolean insertArRcpt(String rcptHdrNum, String arCashApplyStsCd, String cashAppDt, ResultSet trgtRsltSet, String crCardLastDigitNum) throws SQLException {
    // END 2020/07/30 R.Kurahashi [QC#57436,MOD]
        AR_RCPTTMsg arRcptTmsg = new AR_RCPTTMsg();
        // START 2017/01/20 E.Kameishi [QC#16802,MOD]
        /* */
        // GLBL_CMPY_CD
        setValue(arRcptTmsg.glblCmpyCd, this.gcc);
        // RCPT_NUM
        setValue(arRcptTmsg.rcptNum, rcptHdrNum);
        // RCPT_BAT_NUM
        setValue(arRcptTmsg.rcptBatNum, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RCPT_BAT_NUM)));
        // RCPT_BAT_SQ_NUM
        setValue(arRcptTmsg.rcptBatSqNum, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RCPT_BAT_SQ_NUM)));
        // AR_RCPT_TRX_TP_CD
        setValue(arRcptTmsg.arRcptTrxTpCd, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.AR_RCPT_TRX_TP_CD)));
        // AR_RCPT_TP_CD
        setValue(arRcptTmsg.arRcptTpCd, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.AR_RCPT_TP_CD)));
        // DEAL_CCY_CD
        setValue(arRcptTmsg.dealCcyCd, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.DEAL_CCY_CD)));
        // DEAL_RCPT_AMT
        setValue(arRcptTmsg.dealRcptAmt, trgtRsltSet.getBigDecimal(NFCDbConst.DEAL_RCPT_AMT));
        // DEAL_APPLY_AMT
        setValue(arRcptTmsg.dealApplyAmt, trgtRsltSet.getBigDecimal(NFCDbConst.DEAL_APPLY_AMT));
        // DEAL_APPLY_ADJ_AMT
        setValue(arRcptTmsg.dealApplyAdjAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        // DEAL_RF_AMT
        setValue(arRcptTmsg.dealRfAmt, trgtRsltSet.getBigDecimal(NFCDbConst.DEAL_RF_AMT));
        // DEAL_VOID_AMT
        setValue(arRcptTmsg.dealVoidAmt, trgtRsltSet.getBigDecimal(NFCDbConst.DEAL_VOID_AMT));
        // DEAL_RCPT_RMNG_BAL_AMT
        setValue(arRcptTmsg.dealRcptRmngBalAmt, trgtRsltSet.getBigDecimal(NFCDbConst.DEAL_RCPT_RMNG_BAL_AMT));
        // EXCH_RATE
        setValue(arRcptTmsg.exchRate, trgtRsltSet.getBigDecimal(NFCDbConst.EXCH_RATE));
        // FUNC_CCY_CD
        setValue(arRcptTmsg.funcCcyCd, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.FUNC_CCY_CD)));
        // FUNC_RCPT_AMT
        setValue(arRcptTmsg.funcRcptAmt, trgtRsltSet.getBigDecimal(NFCDbConst.FUNC_RCPT_AMT));
        // FUNC_APPLY_AMT
        setValue(arRcptTmsg.funcApplyAmt, trgtRsltSet.getBigDecimal(NFCDbConst.FUNC_APPLY_AMT));
        // FUNC_APPLY_ADJ_AMT
        setValue(arRcptTmsg.funcApplyAdjAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        // FUNC_RF_AMT
        setValue(arRcptTmsg.funcRfAmt, trgtRsltSet.getBigDecimal(NFCDbConst.FUNC_RF_AMT));
        // FUNC_VOID_AMT
        setValue(arRcptTmsg.funcVoidAmt, trgtRsltSet.getBigDecimal(NFCDbConst.FUNC_VOID_AMT));
        // FUNC_RVAL_VAR_AMT
        setValue(arRcptTmsg.funcRvalVarAmt, NFCConst.CST_DB_INIT_VAL_NUM);
        // FUNC_RCPT_RMNG_BAL_AMT
        setValue(arRcptTmsg.funcRcptRmngBalAmt, trgtRsltSet.getBigDecimal(NFCDbConst.FUNC_RCPT_RMNG_BAL_AMT));
        // RCPT_DT
        setValue(arRcptTmsg.rcptDt, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RCPT_DT)));
        // GL_DT
        setValue(arRcptTmsg.glDt, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RCPT_GL_DT)));
        // RCPT_CHK_NUM
        setValue(arRcptTmsg.rcptChkNum, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RCPT_CHK_NUM)));
        // RCPT_CHK_DT
        setValue(arRcptTmsg.rcptChkDt, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RCPT_CHK_DT)));
        // PAYER_CUST_CD
        setValue(arRcptTmsg.payerCustCd, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.PAYER_CUST_CD)));
        // TOC_CD
        setValue(arRcptTmsg.tocCd, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.TOC_CD)));
        // COA_PROD_CD
        setValue(arRcptTmsg.coaProdCd, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.COA_PROD_CD)));


//Def#4971
//        // CR_ANLST_PSN_CD
//        String payerCustCd = trgtRsltSet.getString(NFCDbConst.PAYER_CUST_CD);
//        final CUST_CR_PRFLTMsg custCrPrflTMsg = findCustCrPrfl(payerCustCd, this.gcc);
//        String crAnlistPsnCd = custCrPrflTMsg.crMgrPsnCd.getValue();
//        arRcptTmsg.crAnlstPsnCd.setValue(crAnlistPsnCd);

        setValue(arRcptTmsg.crAnlstPsnCd, NFCCmnMethod.convertDbString(trgtRsltSet.getString("CLT_PSN_CD")));

        // AR_BANK_ACCT_CD
        setValue(arRcptTmsg.arBankAcctCd, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.AR_BANK_ACCT_CD)));
        // AR_BANK_CMSN_FEE_TP_CD
        setValue(arRcptTmsg.arBankCmsnFeeTpCd, NFCConst.CST_DB_INIT_VAL_STR);
        // RCPT_FIRST_CMNT_TXT
        setValue(arRcptTmsg.rcptFirstCmntTxt, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RCPT_FIRST_CMNT_TXT)));
        // RCPT_SCD_CMNT_TXT
        setValue(arRcptTmsg.rcptScdCmntTxt, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RCPT_SCD_CMNT_TXT)));
        // AR_CASH_APPLY_STS_CD
        setValue(arRcptTmsg.arCashApplyStsCd, arCashApplyStsCd);
        // VOID_FLG
        setValue(arRcptTmsg.voidFlg, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.VOID_FLG)));
        // AR_RCPT_VOID_RSN_CD
        setValue(arRcptTmsg.arRcptVoidRsnCd, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.AR_RCPT_VOID_RSN_CD)));
        // VOID_DT
        setValue(arRcptTmsg.voidDt, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.VOID_DT)));
        // VOID_FIRST_CMNT_TXT
        setValue(arRcptTmsg.voidFirstCmntTxt, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.VOID_FIRST_CMNT_TXT)));
        // VOID_SCD_CMNT_TXT
        setValue(arRcptTmsg.voidScdCmntTxt, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.VOID_SCD_CMNT_TXT)));
        // VOID_GL_DT
        setValue(arRcptTmsg.voidGlDt, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.VOID_GL_DT)));
        // AR_RCPT_RF_RSN_CD
        setValue(arRcptTmsg.arRcptRfRsnCd, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.AR_RCPT_RF_RSN_CD)));
        // RF_DT
        setValue(arRcptTmsg.rfDt, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RF_DT)));
        // FIRST_RF_CMNT_TXT
        setValue(arRcptTmsg.firstRfCmntTxt, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.FIRST_RF_CMNT_TXT)));
        // SCD_RF_CMNT_TXT
        setValue(arRcptTmsg.scdRfCmntTxt, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.SCD_RF_CMNT_TXT)));
        // RF_GL_DT
        setValue(arRcptTmsg.rfGlDt, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RF_GL_DT)));
        // RF_EXCH_RATE
        setValue(arRcptTmsg.rfExchRate, trgtRsltSet.getBigDecimal(NFCDbConst.RF_EXCH_RATE));
        // RCV_TS
        setValue(arRcptTmsg.rcvTs, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RCV_TS)));
        // RCPT_IN_PROC_SQ_PK
        setValue(arRcptTmsg.rcptInProcSqPk, trgtRsltSet.getBigDecimal(NFCDbConst.RCPT_IN_PROC_SQ_PK));
        // AR_EDI_SEND_BANK_CD
        setValue(arRcptTmsg.arEdiSendBankCd, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.AR_EDI_SEND_BANK_CD)));
        // EDI_RCV_CUST_NM
        setValue(arRcptTmsg.ediRcvCustNm, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.EDI_RCV_CUST_NM)));
        // CRAT_METH_TP_CD
        setValue(arRcptTmsg.cratMethTpCd, NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.CRAT_METH_TP_CD)));
        // CASH_APP_DT
        setValue(arRcptTmsg.cashAppDt, cashAppDt);
        // EXPT_FLG
        setValue(arRcptTmsg.exptFlg, NFCCmnMethod.convertDbString(trgtRsltSet.getString("EXPT_FLG")));

        // EXPT_FIRST_BANK_CHRG_CCY_CD
        setValue(arRcptTmsg.exptFirstBankChrgCcyCd, trgtRsltSet.getString(NFCDbConst.DEAL_CCY_CD));
        // DEAL_FIRST_EXPT_CHRG_AMT
        setValue(arRcptTmsg.dealFirstExptChrgAmt, BigDecimal.ZERO);
        // FUNC_FIRST_EXPT_CHRG_AMT
        setValue(arRcptTmsg.funcFirstExptChrgAmt, BigDecimal.ZERO);
        // EXPT_SCD_BANK_CHRG_CCY_CD
        setValue(arRcptTmsg.exptScdBankChrgCcyCd, trgtRsltSet.getString(NFCDbConst.DEAL_CCY_CD));
        // DEAL_SCD_EXPT_CHRG_AMT
        setValue(arRcptTmsg.dealScdExptChrgAmt, BigDecimal.ZERO);
        // FUNC_SCD_EXPT_CHRG_AMT
        setValue(arRcptTmsg.funcScdExptChrgAmt, BigDecimal.ZERO);
        // DEAL_NET_RCPT_AMT
        setValue(arRcptTmsg.dealNetRcptAmt, trgtRsltSet.getBigDecimal(NFCDbConst.DEAL_RCPT_AMT));
        // FUNC_NET_RCPT_AMT
        setValue(arRcptTmsg.funcNetRcptAmt, trgtRsltSet.getBigDecimal(NFCDbConst.FUNC_RCPT_AMT));
        // FGN_EXCH_LOSS_GAIN_AMT
        setValue(arRcptTmsg.fgnExchLossGainAmt, BigDecimal.ZERO);

        String rcvFuncId = trgtRsltSet.getString(NFCDbConst.RCV_FUNC_ID);
        // for Paymentech
        if (rcvFuncId.equals("NFC0060001") || rcvFuncId.equals("NFC0040001") || rcvFuncId.equals("NFCI0260")) {
            if (ZYPCommonFunc.hasValue(trgtRsltSet.getString(NFCDbConst.AR_CUST_REF_NUM))) {
                setValue(arRcptTmsg.pmtcOrdNum, trgtRsltSet.getString(NFCDbConst.AR_CUST_REF_NUM));
            }
            if (ZYPCommonFunc.hasValue(trgtRsltSet.getString(NFCDbConst.UPLD_AR_CUST_REF_NUM))) {
                setValue(arRcptTmsg.upldArCustRefNum, trgtRsltSet.getString(NFCDbConst.UPLD_AR_CUST_REF_NUM));
            }
            if (ZYPCommonFunc.hasValue(trgtRsltSet.getString(NFCDbConst.INV_NUM))) {
                setValue(arRcptTmsg.pmtcInvNum, trgtRsltSet.getString(NFCDbConst.INV_NUM));
            }
        }
        // for EDI and other
        setValue(arRcptTmsg.rcvFuncId, trgtRsltSet.getString(NFCDbConst.RCV_FUNC_ID));
        setValue(arRcptTmsg.lastUpdUserId, trgtRsltSet.getString("EZUPUSERID"));

        // for North America(CSA) 2015/04/02 Add Start
        setValue(arRcptTmsg.arBatRcptNm, trgtRsltSet.getString(NFCDbConst.AR_BAT_RCPT_NM));
        setValue(arRcptTmsg.arOrgRcptNum, trgtRsltSet.getString(NFCDbConst.CUST_RCPT_NUM));
        setValue(arRcptTmsg.arRcptSrcCd, trgtRsltSet.getString(NFCDbConst.AR_RCPT_SRC_CD));
        // START 2018/09/20 T.Ogura [QC#28097,MOD]
//        setValue(arRcptTmsg.arRcptStsCd, AR_RCPT_STS.CLEARED);
        if (AR_CASH_APPLY_STS.UNAPPLIED.equals(arCashApplyStsCd)) {
            setValue(arRcptTmsg.arRcptStsCd, AR_RCPT_STS.UNAPPLIED);
        } else if (AR_CASH_APPLY_STS.PARTIAL.equals(arCashApplyStsCd)) {
            setValue(arRcptTmsg.arRcptStsCd, AR_RCPT_STS.PARTIAL_APPLIED);
        } else if (AR_CASH_APPLY_STS.APPLIED.equals(arCashApplyStsCd)) {
            setValue(arRcptTmsg.arRcptStsCd, AR_RCPT_STS.APPLIED);
        } else {
            setValue(arRcptTmsg.arRcptStsCd, AR_RCPT_STS.NEW);
        }
        // END   2018/09/20 T.Ogura [QC#28097,MOD]
        setValue(arRcptTmsg.arRcptNoteTxt, NFCConst.CST_DB_INIT_VAL_STR);
        // START 2018/07/27 E.Kameishi [QC#27419, MOD]
        String arRcptSrcCd = trgtRsltSet.getString(NFCDbConst.AR_RCPT_SRC_CD);
        if (AR_RCPT_SRC.CREDIT_CARD_PAYMENT.equals(arRcptSrcCd)) {
            setValue(arRcptTmsg.arRcptRefTxt, trgtRsltSet.getString(NFCDbConst.CR_CARD_LAST_DIGIT_NUM));
        // START 2020/07/30 R.Kurahashi [QC#57436,ADD]
        } else if (ZYPCommonFunc.hasValue(crCardLastDigitNum)) {
            setValue(arRcptTmsg.arRcptRefTxt, S21StringUtil.concatStrings("****", crCardLastDigitNum));
        // END 2020/07/30 R.Kurahashi [QC#57436,ADD]
        } else {
            setValue(arRcptTmsg.arRcptRefTxt, NFCConst.CST_DB_INIT_VAL_STR);
        }
        // END 2018/07/27 E.Kameishi [QC#27419, MOD]
        // START 2023/03/15 R.Takau [QC#55645,MOD]
//        setValue(arRcptTmsg.arRcptCmntTxt, NFCConst.CST_DB_INIT_VAL_STR);
        if (AR_RCPT_SRC.CHECK_BY_PHONE.equals(arRcptSrcCd)) {
            setValue(arRcptTmsg.arRcptCmntTxt, "AUTOPAY ECHECK PAYMENT " + ZYPDateUtil.DateFormatter(cashAppDt, "yyyyMMdd", "MM/dd/yyyy"));
        } else {
            setValue(arRcptTmsg.arRcptCmntTxt, NFCConst.CST_DB_INIT_VAL_STR);
        }
        // END 2023/03/15 R.Takau   [QC#55645,MOD]
        setValue(arRcptTmsg.arRcptRvrsRsnCd, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arRcptTmsg.arRcptRvrsCmntTxt, NFCConst.CST_DB_INIT_VAL_STR);
        // 2017/12/08 QC#21397 mod start
//        setValue(arRcptTmsg.arRcptRemDt, trgtRsltSet.getString(NFCDbConst.RCV_DT));
        setValue(arRcptTmsg.arRcptRemDt, trgtRsltSet.getString(NFCDbConst.RCPT_DT));
        // 2017/12/08 QC#21397 mod end
        // START 2018/06/26 Y.Matsui [QC#26788,MOD]
//        setValue(arRcptTmsg.crCardApvlCd, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arRcptTmsg.crCardApvlCd, trgtRsltSet.getString(NFCDbConst.CR_CARD_APVL_CD));
        // END   2018/06/26 Y.Matsui [QC#26788,MOD]
        setValue(arRcptTmsg.pmtSvcOrdNum, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arRcptTmsg.arRcptActvTpCd, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arRcptTmsg.arRcptPaidByNm, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arRcptTmsg.arRcptMiscCmntTxt, NFCConst.CST_DB_INIT_VAL_STR);
        setValue(arRcptTmsg.remDsBankAcctPk, trgtRsltSet.getBigDecimal(NFCDbConst.REM_DS_BANK_ACCT_PK));
        setValue(arRcptTmsg.custDsBankAcctPk, trgtRsltSet.getBigDecimal(NFCDbConst.CUST_DS_BANK_ACCT_PK));
        setValue(arRcptTmsg.ajeCratCpltFlg, ZYPConstant.FLG_OFF_N);
        // for North America(CSA) 2015/04/02 Add END
        // END 2017/01/20 E.Kameishi [QC#16802,MOD]
        setValue(arRcptTmsg.locNum, trgtRsltSet.getString(NFCDbConst.LOC_NUM));

        /* */
        S21FastTBLAccessor.insert(arRcptTmsg);

        if (NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(arRcptTmsg.getReturnCode())) {
            /* */

            return true;
        }
        /* */

        return false;
    }

    /**
     * Retrieving CUST_CR_PRFL, and returns it.
     * @param payerCode Payer Code
     * @return {@link CUST_CR_PRFLTMsg}
     */
    private static CUST_CR_PRFLTMsg findCustCrPrfl(final String payerCode, String glblCmpyCd) {

        if (S21StringUtil.isEmpty(payerCode)) {
            return null;
        }

        final CUST_CR_PRFLTMsg inCustCrPrflTMsg = new CUST_CR_PRFLTMsg();
        ZYPEZDItemValueSetter.setValue(inCustCrPrflTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inCustCrPrflTMsg.billToCustCd, payerCode);

        S21SsmEZDResult ssmResult = NFXC302001Query.getInstance().findCustCrPrfl(inCustCrPrflTMsg);
        return (CUST_CR_PRFLTMsg) ssmResult.getResultObject();
    }

    /**
     * insertArTrxBal
     * 
     * <pre>
     * AR_TRX_BAL record is registered. 
     * </pre>
     * @param arTrxBalPk
     * @param arTrxNum
     * @param arCashApplyStsCd
     * @param cashAppDt
     * @param trgtRsltSet
     * @return True:Success / False:Filure
     * @throws SQLException SQLException
     */
    private boolean insertArTrxBal(BigDecimal arTrxBalPk, String arTrxNum, String arCashApplyStsCd, String cashAppDt, ResultSet trgtRsltSet) throws SQLException {

        AR_TRX_BALTMsg arTrxBalTmsg = new AR_TRX_BALTMsg();

        /* */
        // GLBL_CMPY_CD
        arTrxBalTmsg.glblCmpyCd.setValue(this.gcc);
        // AR_TRX_BAL_PK
        arTrxBalTmsg.arTrxBalPk.setValue(arTrxBalPk);
        // AR_TRX_NUM
        arTrxBalTmsg.arTrxNum.setValue(arTrxNum);
        // AR_TRX_TP_CD
        arTrxBalTmsg.arTrxTpCd.setValue(AR_TRX_TP.RECEIPT);
        // ATT_ADJ_NUM
        arTrxBalTmsg.attAdjNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        // DEAL_CCY_CD
        arTrxBalTmsg.dealCcyCd.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.DEAL_CCY_CD)));
        // DEAL_ORIG_GRS_AMT
        arTrxBalTmsg.dealOrigGrsAmt.setValue(trgtRsltSet.getBigDecimal(NFCDbConst.DEAL_RCPT_AMT));
        // DEAL_APPLY_GRS_AMT
        arTrxBalTmsg.dealApplyGrsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // DEAL_APPLY_CASH_DISC_AMT
        arTrxBalTmsg.dealApplyCashDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // DEAL_APPLY_CR_AMT
        arTrxBalTmsg.dealApplyCrAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // DEAL_APPLY_ADJ_AMT
        arTrxBalTmsg.dealApplyAdjAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // DEAL_APPLY_ADJ_RSVD_AMT
        arTrxBalTmsg.dealApplyAdjRsvdAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // DEAL_RMNG_BAL_GRS_AMT
        arTrxBalTmsg.dealRmngBalGrsAmt.setValue(trgtRsltSet.getBigDecimal(NFCDbConst.DEAL_RCPT_AMT));
        // DEAL_NET_SLS_AMT
        arTrxBalTmsg.dealNetSlsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // DEAL_FRT_AMT
        arTrxBalTmsg.dealFrtAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // DEAL_TAX_AMT
        arTrxBalTmsg.dealTaxAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // DEAL_INV_DISC_AMT
        arTrxBalTmsg.dealInvDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // DEAL_PRMO_DISC_AMT
        arTrxBalTmsg.dealPrmoDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // DEAL_RCPT_VOID_AMT
        arTrxBalTmsg.dealRcptVoidAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // EXCH_RATE
        arTrxBalTmsg.exchRate.setValue(trgtRsltSet.getBigDecimal(NFCDbConst.EXCH_RATE));
        // FUNC_CCY_CD
        arTrxBalTmsg.funcCcyCd.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.FUNC_CCY_CD)));
        // FUNC_ORIG_GRS_AMT
        arTrxBalTmsg.funcOrigGrsAmt.setValue(trgtRsltSet.getBigDecimal(NFCDbConst.FUNC_RCPT_AMT));
        // FUNC_APPLY_GRS_AMT
        arTrxBalTmsg.funcApplyGrsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // FUNC_APPLY_CASH_DISC_AMT
        arTrxBalTmsg.funcApplyCashDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // FUNC_APPLY_CR_AMT
        arTrxBalTmsg.funcApplyCrAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // FUNC_APPLY_ADJ_AMT
        arTrxBalTmsg.funcApplyAdjAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // FUNC_APPLY_ADJ_RSVD_AMT
        arTrxBalTmsg.funcApplyAdjRsvdAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // FUNC_RVAL_VAR_AMT
        arTrxBalTmsg.funcRvalVarAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // FUNC_RMNG_BAL_GRS_AMT
        arTrxBalTmsg.funcRmngBalGrsAmt.setValue(trgtRsltSet.getBigDecimal(NFCDbConst.FUNC_RCPT_AMT));
        // FUNC_NET_SLS_AMT
        arTrxBalTmsg.funcNetSlsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // FUNC_FRT_AMT
        arTrxBalTmsg.funcFrtAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // FUNC_TAX_AMT
        arTrxBalTmsg.funcTaxAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // FUNC_INV_DISC_AMT
        arTrxBalTmsg.funcInvDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // FUNC_PRMO_DISC_AMT
        arTrxBalTmsg.funcPrmoDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // FUNC_RCPT_VOID_AMT
        arTrxBalTmsg.funcRcptVoidAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // CASH_DISC_TERM_CD
        arTrxBalTmsg.cashDiscTermCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        // CASH_DISC_PCT
        arTrxBalTmsg.cashDiscPct.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        // AR_CASH_APPLY_STS_CD
        arTrxBalTmsg.arCashApplyStsCd.setValue(arCashApplyStsCd);
        // AR_TRX_DT
        arTrxBalTmsg.arTrxDt.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RCPT_DT)));
        // INV_DUE_DT
        arTrxBalTmsg.invDueDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        arTrxBalTmsg.glDt.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RCPT_GL_DT)));
        // CASH_APP_DT
        arTrxBalTmsg.cashAppDt.setValue(cashAppDt);
        // 2017/12/11 QC#22262 mod start
        // BILL_TO_CUST_CD
//        arTrxBalTmsg.billToCustCd.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.PAYER_CUST_CD)));
        arTrxBalTmsg.billToCustCd.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.BILL_TO_CUST_CD)));
        // SELL_TO_CUST_CD
        // arTrxBalTmsg.sellToCustCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        arTrxBalTmsg.sellToCustCd.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.PAYER_CUST_CD)));
        // PAYER_CUST_CD
//        arTrxBalTmsg.payerCustCd.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.PAYER_CUST_CD)));
        arTrxBalTmsg.payerCustCd.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.BILL_TO_CUST_CD)));
        // 2017/12/11 QC#22262 mod end
        // TOC_CD
        arTrxBalTmsg.tocCd.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.TOC_CD)));
        // COA_PROD_CD
        arTrxBalTmsg.coaProdCd.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.COA_PROD_CD)));
        // GRP_INV_NUM
        arTrxBalTmsg.grpInvNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        // CPO_ORD_NUM
        arTrxBalTmsg.cpoOrdNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        // CUST_ISS_PO_NUM
        arTrxBalTmsg.custIssPoNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);

        // UPPER_CUST_ISS_PO_NUM
        arTrxBalTmsg.upperCustIssPoNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);

        // AR_CUST_REF_NUM
        arTrxBalTmsg.arCustRefNum.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RCPT_CHK_NUM)));
        // PMT_TERM_CD
        arTrxBalTmsg.pmtTermCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        // EXPT_FLG
        arTrxBalTmsg.exptFlg.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString("EXPT_FLG")));

//10/06/2015 T.Tanaka Start
//        // for North America(CSA) 2015/04/02 Add Start
//        arTrxBalTmsg.arTrxConslNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
//        arTrxBalTmsg.invConslTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
//        // for North America(CSA) 2015/04/02 Add END
//10/06/2015 T.Tanaka End

        // Def#4999
        arTrxBalTmsg.arAutoPurgeOfsFlg.setValue(ZYPConstant.FLG_OFF_N);
        // START 2016/08/29 S.Fujita [QC#12541,ADD]
        // BILL_TO_CUST_ACCT_CD
        arTrxBalTmsg.billToCustAcctCd.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.PAYER_CUST_CD)));
        // END   2016/08/29 S.Fujita [QC#12541,ADD]
        /* */
        S21FastTBLAccessor.insert(arTrxBalTmsg);

        if (S21FastTBLAccessor.RTNCD_NORMAL.equals(arTrxBalTmsg.getReturnCode())) {
            /* */

            return true;
        }
        /* */

        return false;
    }

    /**
     * <pre>
     * </pre>
     * @throws SQLException SQLException
     */
    private boolean insertArRcptUnApply(String rcptNum, ResultSet trgtRsltSet, String cashAppDt) throws SQLException {

        /* */
        NFXC3060Bean rtrn3060 = this.afxc3060.getNumber(NFCConst.CST_SEQ_ID_AR_CASH_APP, "", 0);
        if (NFCConst.CST_RTN_CD_ERR.equals(rtrn3060.getRtrnNo())) {
            return false;
        }

        // AR_UN_APPLY_TP_CD is defined.
        String arUnApplyTpsCd = "";

        if (S21StringUtil.isEmpty(trgtRsltSet.getString(NFCDbConst.PAYER_CUST_CD))) {
            arUnApplyTpsCd = AR_CASH_APPLY_STS.UNAPPLIED;
        } else {
            arUnApplyTpsCd = AR_CASH_APPLY_STS.APPLIED;
        }

        AR_RCPT_UN_APPLYTMsg arRcptUnApplyTmsg = new AR_RCPT_UN_APPLYTMsg();

        /* */
        // GLBL_CMPY_CD
        arRcptUnApplyTmsg.glblCmpyCd.setValue(this.gcc);
        // AR_CASH_APP_PK
        arRcptUnApplyTmsg.arCashAppPk.setValue(rtrn3060.getOraSqNo());
        // AR_CASH_APP_SQ_NUM
        arRcptUnApplyTmsg.arCashAppSqNum.setValue(AR_CASH_APP_SQ_NUM_ORIG);
        // RCPT_NUM
        arRcptUnApplyTmsg.rcptNum.setValue(rcptNum);
        // DEAL_UN_APPLY_AMT
        arRcptUnApplyTmsg.dealUnApplyAmt.setValue(trgtRsltSet.getBigDecimal(NFCDbConst.DEAL_RCPT_AMT));
        // FUNC_UN_APPLY_AMT
        arRcptUnApplyTmsg.funcUnApplyAmt.setValue(trgtRsltSet.getBigDecimal(NFCDbConst.FUNC_RCPT_AMT));
        // AR_UN_APPLY_TP_CD
        arRcptUnApplyTmsg.arUnApplyTpCd.setValue(arUnApplyTpsCd);
        arRcptUnApplyTmsg.glDt.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RCPT_GL_DT)));
        // CASH_APP_DT
        arRcptUnApplyTmsg.cashAppDt.setValue(cashAppDt);
        // AR_UN_APPLY_STS_CD
        arRcptUnApplyTmsg.arUnApplyStsCd.setValue(NFCConst.CST_AR_UN_APPLY_STS_CD_NEW);
        // AJE_CRAT_CPLT_FLG
        arRcptUnApplyTmsg.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_OFF);

        /* */
        S21FastTBLAccessor.insert(arRcptUnApplyTmsg);

        if (S21FastTBLAccessor.RTNCD_NORMAL.equals(arRcptUnApplyTmsg.getReturnCode())) {
            /* */

            return true;
        }
        /* */

        return false;
    }

    /**
     * updateCreditBalance
     * 
     * <pre>
     * Credit Balance is renewed.
     * </pre>
     * @param opeDate
     * @param rcptNum
     * @param trgtRsltSet
     * @throws SQLException SQLException
     */
//10/06/2015 T.Tanaka Start
//    private boolean updateCreditBalance(String opeDate, String rcptNum, ResultSet trgtRsltSet) throws SQLException {
//
//        /* The registration data is set in PMsg. */
//        // GLBL_CMPY_CD
//        NMZC600001PMsg pmsg = new NMZC600001PMsg();
//        pmsg.glblCmpyCd.setValue(this.gcc);
//        // BILL_TO_CUST_CD
//        pmsg.billToCustCd.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.PAYER_CUST_CD)));
//        // IN_PROC_AMT
//        pmsg.inProcAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
//        // INV_AMT
//        pmsg.invAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
//        // INV_DT
//        pmsg.invDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
//        // RCPT_AMT
//        // change deal to func.
//        String dealCcyCd = trgtRsltSet.getString(NFCDbConst.DEAL_CCY_CD);
//        BigDecimal dealAmt = trgtRsltSet.getBigDecimal(NFCDbConst.DEAL_RCPT_AMT).negate();
//        String glDt;
//        if (null != trgtRsltSet.getString(NFCDbConst.CASH_APP_GL_DT)) {
//            glDt = trgtRsltSet.getString(NFCDbConst.CASH_APP_GL_DT);
//        } else {
//            glDt = trgtRsltSet.getString(NFCDbConst.RCPT_GL_DT);
//        }
//        BigDecimal funcAmt = this.getFuncAmt(this.gcc, dealCcyCd, dealAmt, glDt);
//
//        if (null == funcAmt) {
//            debugLog("NFCCurrencyConversion - Failure");
//            return false;
//        }
//
//        pmsg.rcptAmt.setValue(funcAmt);
//        // RCPT_DT
//        pmsg.rcptDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
//        // UPD_KEY_NUM
//        pmsg.updKeyNum.setValue(rcptNum);
//        // SLS_DT
//        pmsg.slsDt.setValue(opeDate);
//
//        /* NMZC600001 is executed. */
//        NMZC600001 api = new NMZC600001();
//        api.execute(pmsg, ONBATCH_TYPE.ONLINE);
//
//        List<String> errList = S21ApiUtil.getXxMsgIdList(pmsg);
//        if (errList.size() == 0) {
//            debugLog("updateCreditBalance  end - Success");
//
//            return true;
//        } else {
//            debugLog("updateCreditBalance  end - Failure");
//
//            return false;
//        }
//    }
//10/06/2015 T.Tanaka End

    /**
     * When an error occurred, this method return null.
     * @param glblCmpyCd String
     * @param dealCcyCd String
     * @param dealAmt BigDecimal
     * @param glDt String
     * @return BigDecimal
     */
    private BigDecimal getFuncAmt(String glblCmpyCd, String dealCcyCd, BigDecimal dealAmt, String glDt) {

        NFCCurrencyConversion afcCryCon = new NFCCurrencyConversion();
        NFXC3070Bean resultBean = afcCryCon.convertCurrency(glblCmpyCd, dealCcyCd, dealAmt, glDt, null);
        if (!NFCConst.CST_RTN_CD_NORM.equals(resultBean.getRtrnCd())) {
            return null;
        } else {
            return resultBean.getFuncAmt();
        }
    }

    /**
     * <pre>
     * </pre>
     */
    private Boolean getEzInData(Map<String, Object> paramMap) {

        /* AR_RCPT */
        AR_RCPTTMsg rcptTmsg = new AR_RCPTTMsg();

        rcptTmsg.glblCmpyCd.setValue(paramMap.get(NFCDbConst.GLBL_CMPY_CD).toString());
        rcptTmsg.rcptNum.setValue(paramMap.get(NFCDbConst.RCPT_NUM).toString());

        rcptTmsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKey(rcptTmsg);
        if (EZDTBLAccessor.RTNCD_NORMAL.equals(rcptTmsg.getReturnCode())) {
            paramMap.put(RCPT_INTIME, rcptTmsg.ezInTime.getValue());
            paramMap.put(RCPT_INTMZN, rcptTmsg.ezInTimeZone.getValue());
        } else {
            return false;
        }

        /* AR_TRX_BAL */
        AR_TRX_BALTMsg trxTmsg = new AR_TRX_BALTMsg();

        trxTmsg.glblCmpyCd.setValue(paramMap.get(NFCDbConst.GLBL_CMPY_CD).toString());

        BigDecimal arTrxBalPk = new BigDecimal(paramMap.get(NFCDbConst.AR_TRX_BAL_PK).toString());
        trxTmsg.arTrxBalPk.setValue(arTrxBalPk);

        trxTmsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKey(trxTmsg);
        if (EZDTBLAccessor.RTNCD_NORMAL.equals(trxTmsg.getReturnCode())) {
            paramMap.put(TRX_INTIME, trxTmsg.ezInTime.getValue());
            paramMap.put(TRX_INTMZN, trxTmsg.ezInTimeZone.getValue());
        } else {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * </pre>
     * @param paramMap
     * @throws SQLException SQLException
     */
    private boolean updateArApplyIntfcWrkHdr(String rcptHdrNum, Map<String, Object> paramMap, ResultSet trgtRsltSet) throws SQLException {

        AR_APPLY_INTFC_WRKTMsg cndTMsg = new AR_APPLY_INTFC_WRKTMsg();
        AR_APPLY_INTFC_WRKTMsg updTMsg = new AR_APPLY_INTFC_WRKTMsg();

        /* */
        // GLBL_CMPY_CD(PK)
        cndTMsg.glblCmpyCd.setValue(this.gcc);
        // APPLY_GRP_KEY(PK)
        cndTMsg.applyGrpKey.setValue(this.agk);
        // RCPT_IN_PROC_SQ_PK
        cndTMsg.rcptInProcSqPk.setValue(trgtRsltSet.getBigDecimal(NFCDbConst.RCPT_IN_PROC_SQ_PK));
        // RCV_HDR_NUM
        cndTMsg.rcvHdrNum.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RCV_HDR_NUM)));
        /* */
        String[] cndFields = new String[] {NFCDbConst.GLBL_CMPY_CD_J, NFCDbConst.APPLY_GRP_KEY_J, NFCDbConst.RCPT_IN_PROC_SQ_PK_J, NFCDbConst.RCV_HDR_NUM_J };

        /* */
        // RCPT_NUM
        updTMsg.rcptNum.setValue(rcptHdrNum);
        // RCPT_HDR_LAST_UPD_TS
        updTMsg.rcptHdrLastUpdTs.setValue(paramMap.get(RCPT_INTIME).toString());
        // RCPT_HD_TM_ZN
        updTMsg.rcptHdrTz.setValue(paramMap.get(RCPT_INTMZN).toString());
        // RCPT_TRX_BAL_LAST_UPD_TS
        updTMsg.rcptTrxBalLastUpdTs.setValue(paramMap.get(TRX_INTIME).toString());
        // RCPT_TRX_BAL_TM_ZN
        updTMsg.rcptTrxBalTz.setValue(paramMap.get(TRX_INTMZN).toString());
        // RCPT_TRX_BAL_PK
        BigDecimal arTrxBalPk = new BigDecimal(paramMap.get(NFCDbConst.AR_TRX_BAL_PK).toString());
        updTMsg.rcptTrxBalPk.setValue(arTrxBalPk);

        /* */
        String[] updFields = new String[] {NFCDbConst.RCPT_NUM_J, NFCDbConst.RCPT_HDR_LAST_UPD_TS_J, NFCDbConst.RCPT_HDR_TZ_J, NFCDbConst.RCPT_TRX_BAL_LAST_UPD_TS_J, NFCDbConst.RCPT_TRX_BAL_TZ_J, NFCDbConst.RCPT_TRX_BAL_PK_J };

        /* */
        S21FastTBLAccessor.updateByPartialValue(cndTMsg, cndFields, updTMsg, updFields);
        if (S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            /* */

            return true;
        }
        /* */

        return false;
    }

    /**
     * <pre>
     * </pre>
     * @throws SQLException SQLException
     */
    private boolean insertArRcptDtl(String rcptHdrNum, String rcptDtlNum, ResultSet trgtRsltSet) throws SQLException {
        debugLog("insertArRcptDtl  start");

        AR_RCPT_DTLTMsg arRcptDtlTmsg = new AR_RCPT_DTLTMsg();

        /* */
        // GLBL_CMPY_CD
        arRcptDtlTmsg.glblCmpyCd.setValue(this.gcc);
        // RCPT_NUM
        arRcptDtlTmsg.rcptNum.setValue(rcptHdrNum);
        // RCPT_DTL_NUM
        arRcptDtlTmsg.rcptDtlNum.setValue(rcptDtlNum);
        // INV_NUM
        arRcptDtlTmsg.invNum.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.INV_NUM)));

        if ((AR_CRAT_METH_TP.CSV_UPLOAD.equals(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.CRAT_METH_TP_CD)))) && (S21StringUtil.isEmpty(arRcptDtlTmsg.invNum.getValue()))) {

            // AR_CUST_REF_NUM
            arRcptDtlTmsg.arCustRefNum.setValue(ZYPCodeDataUtil.getVarCharConstValue(NFCConst.CST_VAR_CHAR_CONST_NAME_AR_AUTO_RCPT_REF_NUM, this.gcc));

        } else {

            // AR_CUST_REF_NUM
            arRcptDtlTmsg.arCustRefNum.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.AR_CUST_REF_NUM)));

        }

        // AR_CUST_REF_TP_CD
        arRcptDtlTmsg.arCustRefTpCd.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.AR_CUST_REF_TP_CD)));
        // DEAL_RCPT_DTL_AMT
        arRcptDtlTmsg.dealRcptDtlAmt.setValue(trgtRsltSet.getBigDecimal(NFCDbConst.DEAL_RCPT_DTL_AMT));
        // FUNC_RCPT_DTL_AMT
        arRcptDtlTmsg.funcRcptDtlAmt.setValue(trgtRsltSet.getBigDecimal(NFCDbConst.FUNC_RCPT_DTL_AMT));
        // RCV_TRX_TP_CD
        arRcptDtlTmsg.rcvTrxTpCd.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RCV_TRX_TP_CD)));
        // RCV_TRX_NUM
        arRcptDtlTmsg.rcvTrxNum.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.RCV_TRX_NUM)));
        // AUTO_CRAT_FLG
        arRcptDtlTmsg.autoCratFlg.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.AUTO_CRAT_FLG)));
        // DUP_ERR_CD
        arRcptDtlTmsg.dupErrCd.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.DUP_ERR_CD)));
        // GRP_INV_FLG
        arRcptDtlTmsg.grpInvFlg.setValue(NFCCmnMethod.convertDbString(trgtRsltSet.getString(NFCDbConst.GRP_INV_FLG)));

        /* */
        S21FastTBLAccessor.insert(arRcptDtlTmsg);

        if (S21FastTBLAccessor.RTNCD_NORMAL.equals(arRcptDtlTmsg.getReturnCode())) {
            /* */

            return true;
        }
        /* */

        return false;
    }

    /**
     * <pre>
     * </pre>
     */
    private boolean updateArApplyIntfcWrkDtl(BigDecimal applyGrpSubPk, String rcptDtlNum) {

        AR_APPLY_INTFC_WRKTMsg cndTMsg = new AR_APPLY_INTFC_WRKTMsg();
        AR_APPLY_INTFC_WRKTMsg updTMsg = new AR_APPLY_INTFC_WRKTMsg();

        /* Condition */
        cndTMsg.glblCmpyCd.setValue(this.gcc);
        cndTMsg.applyGrpKey.setValue(this.agk);
        cndTMsg.applyGrpSubPk.setValue(applyGrpSubPk);
        String[] cndList = new String[] {"glblCmpyCd", "applyGrpKey", "applyGrpSubPk" };

        /* Update */
        updTMsg.rcptDtlNum.setValue(rcptDtlNum);
        String[] updList = new String[] {"rcptDtlNum" };

        S21FastTBLAccessor.updateByPartialValue(cndTMsg, cndList, updTMsg, updList);
        if (S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * </pre>
     * @throws SQLException SQLException
     */
    private boolean updateArRcptInfProcWrk(ResultSet trgtRsltSet, String rcptStsCd) throws SQLException {

        AR_RCPT_IN_PROC_WRKTMsg cndTMsg = new AR_RCPT_IN_PROC_WRKTMsg();
        AR_RCPT_IN_PROC_WRKTMsg updTMsg = new AR_RCPT_IN_PROC_WRKTMsg();

        /* Condition */
        cndTMsg.glblCmpyCd.setValue(this.gcc);
        cndTMsg.rcptInProcSqPk.setValue(trgtRsltSet.getBigDecimal(NFCDbConst.RCPT_IN_PROC_SQ_PK));
        cndTMsg.rcvHdrNum.setValue(trgtRsltSet.getString(NFCDbConst.RCV_HDR_NUM));
        cndTMsg.rcvDtlNum.setValue(trgtRsltSet.getString(NFCDbConst.RCV_DTL_NUM));
        String[] cndList = new String[] {"glblCmpyCd", "rcptInProcSqPk", "rcvHdrNum", "rcvDtlNum" };

        /* Update */
        updTMsg.rcptStsCd.setValue(rcptStsCd);
        String[] updList = new String[] {"rcptStsCd" };

        S21FastTBLAccessor.updateByPartialValue(cndTMsg, cndList, updTMsg, updList);
        if (S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            return true;
        }
        return false;
    }

    /**
     */
    private void debugLog(String logmsg) {
        // protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
