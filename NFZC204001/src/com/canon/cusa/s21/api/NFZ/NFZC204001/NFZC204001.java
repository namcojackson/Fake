/**<pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.api.NFZ.NFZC204001;

import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.NZZM0003E;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.AR_CUST_REF_STR;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.LF_RPT_ID;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.MAX_LATE_DAYS;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.MODE_APPLY;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.MODE_FLG_OFF_INIT;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.MODE_UNAPPLY;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.LATE_FEE_PRORATED_DAYS;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.ROUND_DOWN_DECIMAL_POINT;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.NFZM0001E;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.NFZM0002E;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.NFZM0003E;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.NFZM0005E;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.NFZM0006E;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.NFZM0008E;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.NFZM0009E;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.NFZM0010E;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.NFZM0012E;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.NFZM0013E;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.NFZM0015E;
import static com.canon.cusa.s21.api.NFZ.NFZC204001.constant.NFZC204001Constant.NFZM0016E;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_ADJTMsg;
import business.db.AR_GRACE_PERTMsg;
import business.db.AR_GRACE_PERTMsgArray;
import business.db.AR_LATE_FEE_TRX_DTLTMsg;
import business.db.AR_STMT_CTRLTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.PMT_TERMTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.parts.NFZC202001PMsg;
import business.parts.NFZC204001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFCCurrencyConversion;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFXC3070Bean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_STMT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DSPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PAYER_CUST_MODE;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;

/**
 * <pre>
 * Create Late Fee API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         M.Nakamura      Create          N/A.
 * 2016/03/18   Fujitsu         C.Naito         Update          QC#5655
 * 2016/03/24   Fujitsu         C.Naito         Update          QC#6006
 * 2016/03/30   Fujitsu         C.Naito         Update          QC#5752
 * 2016/09/02   Hitachi         K.Kojima        Update          QC#10786
 * 2017/06/09   Hitachi         J.Kim           Update          QC#18413
 * 2017/10/26   Fujitsu         K.Ishizuka      Update          QC#21977
 * 2017/12/19   Hitachi         E.Kameishi      Update          QC#22096
 * 2018/05/21   Fujitsu         S.Ohki          Update          QC#25981
 * 2018/06/05   Hitachi         Y.Takeno        Update          QC#26107
 * 2018/07/03   Hitachi         Y.Takeno        Update          QC#26600
 * 2019/02/14   Fujitsu         H.Ikeda         Update          QC#30301
 * 2019/02/19   Fujitsu         H.Ikeda         Update          QC#30038
 * 2019/05/10   Fujitsu         H.Ikeda         Update          QC#50140
 * 2019/07/20   Fujitsu         H.Ikeda         Update          QC#51787
 * 2023/02/02   Hitachi         S.Nakatani      Update          QC#60811
 * </pre>
 */
public class NFZC204001 extends S21ApiCommonBase {

    /** ssm Batch Client */
    private S21SsmBatchClient ssmClient;

    /** ssm LowLevel Client */
    private S21SsmLowLevelCodingClient ssmLowClient;

    /** NFC Currency Conversion */
    private NFCCurrencyConversion nfcCcyConv;

    /** Message Manager */
    private S21ApiMessageIdMgr msgIdMgr;

    // START 2019/05/10 H.Ikeda [QC#50140,ADD]
    /** Total Count */
    private int totalRecordCnt;

    /** Normal Count */
    private int normalRecordCnt;
    // END 2019/05/10 H.Ikeda [QC#50140,ADD]

    /** Constructor */
    public NFZC204001() {
        super();
        ssmClient = S21SsmBatchClient.getClient(this.getClass());
        ssmLowClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        nfcCcyConv = new NFCCurrencyConversion();
    }

    /**
     * Create Late Fee API.
     * @param pMsgList List<NFZC204001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NFZC204001PMsg> pMsgList,  final ONBATCH_TYPE onBatchType) {
        for (NFZC204001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * Create Late Fee API.
     * @param pMsg NFZC204001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NFZC204001PMsg pMsg,  final ONBATCH_TYPE onBatchType) {
        try {
            doProcess(pMsg, onBatchType);
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        }
    }

    private void doProcess(NFZC204001PMsg pMsg, final ONBATCH_TYPE onBatchType) throws SQLException {
        S21EIPPrintingService service = new S21EIPPrintingService();
        String sysTimeStamp = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");
        PreparedStatement stmtBillTo = null;
        ResultSet rsltSetBillTo = null;
        try {
            this.msgIdMgr = new S21ApiMessageIdMgr();
            // START 2019/05/10 H.Ikeda [QC#50140,ADD]
            this.normalRecordCnt = 0;
            //List<AR_LATE_FEE_TRX_DTLTMsg> arLateFeeTrxDtlTMsgList = new ArrayList<AR_LATE_FEE_TRX_DTLTMsg>();
            // END   2019/05/10 H.Ikeda [QC#50140,ADD]

            // Parameter Check.
            if (!checkParam(pMsg)) {
                return;
            }

            NFZC204001Bean bean = new NFZC204001Bean();

            // Get AR_SUB_SYS_ID
            bean.setArSubSysId(ZYPCodeDataUtil.getVarCharConstValue("AR_SUB_SYS_ID", pMsg.glblCmpyCd.getValue()));

            // Get Account Date.
            bean.setArAcctDt(pMsg.slsDt.getValue());

            // Get Currency Code
            GLBL_CMPYTMsg glblCmpyTMsg = (GLBL_CMPYTMsg) ZYPCodeDataUtil.findByCode(GLBL_CMPY.class, pMsg.glblCmpyCd.getValue(), pMsg.glblCmpyCd.getValue());
            if (glblCmpyTMsg == null) {
                this.msgIdMgr.addXxMsgId(NFZM0015E, pMsg);
                return;
            }
            bean.setCcyCd(glblCmpyTMsg.stdCcyCd.getValue());

            // Get AR Daily Interest Rate
            // Start 2023/2/02 S.Nakatani [QC#60811, MOD]
//            bean.setArDlyIntRate(ZYPCodeDataUtil.getNumConstValue("AR_DLY_INT_RATE", pMsg.glblCmpyCd.getValue()));
            BigDecimal arDlyIntRate = ZYPCodeDataUtil.getNumConstValue("AR_DLY_INT_RATE", pMsg.glblCmpyCd.getValue());

            // Get Late Fee Prorated Days
            BigDecimal lateFeeProratedDays = ZYPCodeDataUtil.getNumConstValue(LATE_FEE_PRORATED_DAYS, pMsg.glblCmpyCd.getValue());
            
            // Get Round down Decimal Point
            int roundDownDecimalPoint = ZYPCodeDataUtil.getNumConstValue(ROUND_DOWN_DECIMAL_POINT, pMsg.glblCmpyCd.getValue()).intValue();
            // End 2023/2/02 S.Nakatani [QC#60811, MOD]
            
            // Get AR Adjustment Type Code
            bean.setArAdjTpCd(ZYPCodeDataUtil.getVarCharConstValue("AR_LATE_FEE_AR_ADJ_TP", pMsg.glblCmpyCd.getValue()));
            // Get AR Adjustment Type Name
            bean.setArAdjTpNm(ZYPCodeDataUtil.getName(AR_ADJ_TP.class.getSimpleName(), pMsg.glblCmpyCd.getValue(), bean.getArAdjTpCd()));

            //* Get DS Invoice Type Code
            bean.setDsInvTpCd(ZYPCodeDataUtil.getVarCharConstValue("AR_LATE_FEE_DS_INV_TP_CD", pMsg.glblCmpyCd.getValue()));

            // Get Payment Term Info.
            if (!setPmtTermInfo(pMsg, bean)) {
                return;
            }

            // START 2019/05/10 H.Ikeda [QC#50140, MOD]
            // Get AR Statement Issue Cycle Code.
            //Map<String, String> arStmtIssCycleMap = getArStmtIssCycleCd(pMsg);
            Map<String, Object> arStmtIssCycleMap = getArStmtIssCycleCd(pMsg);
            // END   2019/05/10 H.Ikeda [QC#50140, MOD]
            if (arStmtIssCycleMap == null) {
                // Not Target.
                return;
            }

            String arStmtIssCycleCd = (String) arStmtIssCycleMap.get("AR_STMT_ISS_CYCLE_CD");
            String lateFeeCalcFlgStmtCtrl = (String) arStmtIssCycleMap.get("LATE_FEE_CALC_FLG");
            if (arStmtIssCycleCd == null || lateFeeCalcFlgStmtCtrl == null) {
                this.msgIdMgr.addXxMsgId(NFZM0016E, pMsg);
                return;
            }

            // Get AR Statement Issue Day.
            String arStmtIssDay = getArStmtIssDay(pMsg, arStmtIssCycleCd);
            if (arStmtIssDay == null) {
                this.msgIdMgr.addXxMsgId(NFZM0010E, pMsg);
                return;
            }

            // Get Grace Per Code
            AR_GRACE_PERTMsg arGracePerTMsg = getArGracePerCd(pMsg);
            if (arGracePerTMsg == null) {
                this.msgIdMgr.addXxMsgId(NFZM0012E, pMsg);
                return;
            }
            bean.setArGracePerTMsg(arGracePerTMsg);

            // Get Target Customer.
            stmtBillTo = getTrgtBillTo(pMsg, arStmtIssDay);
            rsltSetBillTo = stmtBillTo.executeQuery();
            boolean isExistsReport = false;
            boolean isFirst = true;

            while (rsltSetBillTo.next()) {

                // Clear Total Late Fee.
                bean.setTotLateFeeAmt(BigDecimal.ZERO);

                bean.setRsltSetBillTo(rsltSetBillTo);

                // START 2018/06/05 [QC#26107, ADD]
                bean.getArLateFeeTrxPkList().clear();
                // END   2018/06/05 [QC#26107, ADD]

                isFirst = true;

                // Get Invoice Data.
                // [QC#6006] INSERT start
                String thisBillToCustCd = rsltSetBillTo.getString("BILL_TO_CUST_CD");
                // [QC#6006] INSERT end

                PreparedStatement stmtInv = null;
                ResultSet rsltSetInv = null;
                try {

                    stmtInv = getTrgtInv(pMsg, thisBillToCustCd, arGracePerTMsg.netDueGracePerDaysAot.getValue());
                    rsltSetInv = stmtInv.executeQuery();

                    // START 2019/05/10 H.Ikeda [QC#50140,ADD]
                    rsltSetInv.last();
                    int rowCnt = rsltSetInv.getRow();
                    rsltSetInv.beforeFirst();
                    this.totalRecordCnt = this.totalRecordCnt + rowCnt;
                    ZYPEZDItemValueSetter.setValue(pMsg.batProcTotRecCnt, BigDecimal.valueOf(this.totalRecordCnt));
                    // END   2019/05/10 H.Ikeda [QC#50140,ADD]
                    while (rsltSetInv.next()) {
                        // [QC#6006] INSERT start
                        // LateFeeLimit NotOver data is not target
                        // data
                        String arTrxNum = rsltSetInv.getString("AR_TRX_NUM");
                        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
                        if (getNotTrgtInv(arTrxNum, thisBillToCustCd, glblCmpyCd)) {
                            continue;
                        }
                        // [QC#6006] INSERT end

                        bean.setRsltSetInv(rsltSetInv);
                        if (isFirst) {
                            bean.setOrigBillToCustCd(rsltSetInv.getString("BILL_TO_CUST_CD"));
                            bean.setOrigSellToCustCd(rsltSetInv.getString("SELL_TO_CUST_CD"));
                            bean.setOrigPayerCustCd(rsltSetInv.getString("PAYER_CUST_CD"));
                            bean.setOrigBillToCustAcctCd(rsltSetInv.getString("BILL_TO_CUST_ACCT_CD"));
                            bean.setOrigTocCd(rsltSetInv.getString("TOC_CD"));
                            bean.setOrigCoaProdCd(rsltSetInv.getString("COA_PROD_CD"));
                            bean.setOrigSlsRepTocCd(rsltSetInv.getString("SLS_REP_TOC_CD"));
                            // Start 2023/2/02 S.Nakatani [QC#60811, ADD]
                            if (ZYPCommonFunc.hasValue(rsltSetInv.getBigDecimal("MLY_LATE_FEE_RATE"))) {
                                BigDecimal mlyLateFeeRatePercent = rsltSetInv.getBigDecimal("MLY_LATE_FEE_RATE").divide(BigDecimal.valueOf(100));
                                bean.setArDlyIntRate(mlyLateFeeRatePercent.divide(lateFeeProratedDays, roundDownDecimalPoint, RoundingMode.DOWN));
                            } else {
                                bean.setArDlyIntRate(arDlyIntRate);
                            }
                            // End 2023/2/02 S.Nakatani [QC#60811, ADD]
                            isFirst = false;
                        }

                        String mode = checkCreateMode(pMsg, rsltSetBillTo, rsltSetInv, lateFeeCalcFlgStmtCtrl);
                        String arLastStmtDt = rsltSetInv.getString("AR_LAST_STMT_DT");
                        if (MODE_UNAPPLY.equals(mode) || MODE_FLG_OFF_INIT.equals(mode)) {
                            // START 2019/05/10 H.Ikeda [QC#50140, MOD]
                            // Create Late Fee Detail.
                            if (!createArLateFeeTrxDtl(mode, pMsg, bean)) {
                            //if (!createArLateFeeTrxDtl(mode, pMsg, bean, arLateFeeTrxDtlTMsgList)) {
                            // END   2019/05/10 H.Ikeda [QC#50140, MOD]
                                this.msgIdMgr.addXxMsgId(NFZM0013E, pMsg);
                                return;
                            }
                        } else {
                            BigDecimal curRmngGrsAmt = rsltSetInv.getBigDecimal("DEAL_ORIG_GRS_AMT");
                            String firstDay = arLastStmtDt;
                            if (!ZYPCommonFunc.hasValue(firstDay)) {
                                firstDay = rsltSetInv.getString("INV_DUE_DT");
                            }

                            // Get Cash Apply Data.
                            List<Map<String, Object>> cashApplyList = getCashApply(pMsg, rsltSetInv);
                            String lastApplyDt = (String) cashApplyList.get(cashApplyList.size() - 1).get("CASH_APP_DT");
                            String graceDate = ZYPDateUtil.addDays(rsltSetInv.getString("INV_DUE_DT"), arGracePerTMsg.netDueGracePerDaysAot.getValueInt());
                            for (Map<String, Object> rsltMap : cashApplyList) {

                                if (ZYPDateUtil.compare(graceDate, ((String) rsltMap.get("CASH_APP_DT"))) >= 0) {
                                    curRmngGrsAmt = curRmngGrsAmt.subtract((BigDecimal) rsltMap.get("FUNC_APPLY_AMT"));
                                    continue;
                                }
                                // START 2019/02/14 [QC#30301, ADD]
                                // START 2019/07/20 [QC#51787, MOD]
                                //if (ZYPDateUtil.compare(firstDay, graceDate) > 0) {
                                if (ZYPDateUtil.compare(firstDay, graceDate) > 0 && ZYPDateUtil.compare(firstDay, (String) rsltMap.get("CASH_APP_DT")) > 0) {
                                // END   2019/07/20 [QC#51787, MOD]
                                    curRmngGrsAmt = curRmngGrsAmt.subtract((BigDecimal) rsltMap.get("FUNC_APPLY_AMT"));
                                    continue;
                                }
                                // END   2019/02/14 [QC#30301, ADD]
                                // START 2019/05/10 H.Ikeda [QC#50140, MOD]
                                // Create Late Fee Detail.
                                if (!createArLateFeeTrxDtlCashApp(pMsg, bean, (String) rsltMap.get("CASH_APP_DT"), firstDay, lastApplyDt, curRmngGrsAmt)) {
                                //if (!createArLateFeeTrxDtlCashApp(pMsg, bean, (String) rsltMap.get("CASH_APP_DT"), firstDay, lastApplyDt, curRmngGrsAmt, arLateFeeTrxDtlTMsgList)) {
                                // END   2019/05/10 H.Ikeda [QC#50140, MOD]
                                    this.msgIdMgr.addXxMsgId(NFZM0013E, pMsg);
                                    return;
                                }
                                if (ZYPDateUtil.compare(firstDay, ((String) rsltMap.get("CASH_APP_DT"))) <= 0) {
                                    firstDay = (String) rsltMap.get("CASH_APP_DT");
                                }
                                curRmngGrsAmt = curRmngGrsAmt.subtract((BigDecimal) rsltMap.get("FUNC_APPLY_AMT"));
                            }
                            if (curRmngGrsAmt.compareTo(BigDecimal.ZERO) > 0) {
                                // START 2019/05/10 H.Ikeda [QC#50140, MOD]
                                // Create Late Fee Detail.
                                if (!createArLateFeeTrxDtlCashApp(pMsg, bean, pMsg.arStmtDt.getValue(), firstDay, lastApplyDt, curRmngGrsAmt)) {
                                //if (!createArLateFeeTrxDtlCashApp(pMsg, bean, pMsg.arStmtDt.getValue(), firstDay, lastApplyDt, curRmngGrsAmt, arLateFeeTrxDtlTMsgList)) {
                                // END   2019/05/10 H.Ikeda [QC#50140, MOD]
                                    this.msgIdMgr.addXxMsgId(NFZM0013E, pMsg);
                                    return;
                                }
                            }
                        }
                        // START 2019/05/10 H.Ikeda [QC#50140, ADD]
                        this.normalRecordCnt++;
                        // END   2019/05/10 H.Ikeda [QC#50140, ADD]
                    }
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(stmtInv, rsltSetInv);
                }

                if (bean.getTotLateFeeAmt().compareTo(nvl(rsltSetBillTo.getBigDecimal("LATE_FEE_AMT"))) <= 0) {
                    // Not Create Late Fee Record.
                    continue;
                }

                if (isFirst) {
                    // No Invoice data.
                    continue;
                }

                // Deal -> Func Conversion.
                NFXC3070Bean rsltBean = nfcCcyConv.convertCurrency(pMsg.glblCmpyCd.getValue(), bean.getCcyCd(), bean.getTotLateFeeAmt(), pMsg.arStmtDt.getValue(), null);

                // Create Deduction.
                BigDecimal arTrxBalPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_TRX_BAL_SQ);
                String arAdjNum = ZYPNumbering.getUniqueID("AD#");
                if (!createArTrx(pMsg, bean, rsltBean, arAdjNum, arTrxBalPk)) {
                    this.msgIdMgr.addXxMsgId(NFZM0013E, pMsg);
                    return;
                }
                if (!createArAdj(pMsg, bean, rsltBean, arTrxBalPk, arAdjNum)) {
                    this.msgIdMgr.addXxMsgId(NFZM0013E, pMsg);
                    return;
                }
                // START 2017/12/19 E.Kameishi [QC#22096, ADD]
                //Update Credit Profile
                NFZC202001 crPrflUpdApi = new NFZC202001();
                NFZC202001PMsg nfzc2020Pmsg = new NFZC202001PMsg();
                
                ZYPEZDItemValueSetter.setValue(nfzc2020Pmsg.xxModeCd, NFZC202001.MODE_BILL_TO_CUST);
                ZYPEZDItemValueSetter.setValue(nfzc2020Pmsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(nfzc2020Pmsg.billToCustCd, bean.getOrigBillToCustCd());
                ZYPEZDItemValueSetter.setValue(nfzc2020Pmsg.procDt, pMsg.slsDt);

                crPrflUpdApi.execute(nfzc2020Pmsg, onBatchType);
                if (nfzc2020Pmsg.xxMsgIdList.getValidCount() > 0) {
                    for (int i = 0; i < nfzc2020Pmsg.xxMsgIdList.getValidCount(); i++) {
                        this.msgIdMgr.addXxMsgId(nfzc2020Pmsg.xxMsgIdList.no(i).xxMsgId.getValue(), pMsg);
                    }
                    return;
                }
                // END 2017/12/19 E.Kameishi [QC#22096, ADD]
                // Create EIP Report Request.
                // START 2018/06/05 [QC#26107, MOD]
                // createEIPReportRquest(service, pMsg, thisBillToCustCd, sysTimeStamp);
                long rqstPk = createEIPReportRquest(service, pMsg, thisBillToCustCd, sysTimeStamp);
                if (!updateArLateFeeTrxDtl(pMsg, bean.getArLateFeeTrxPkList(), arTrxBalPk, rqstPk)) {
                    this.msgIdMgr.addXxMsgId(NFZM0013E, pMsg);
                    return;
                }
                bean.getArLateFeeTrxPkList().clear();
                // END   2018/06/05 [QC#26107, MOD]

                // Clear Total Late Fee.
                bean.setTotLateFeeAmt(BigDecimal.ZERO);

                isExistsReport = true;
            }

//            // START 2019/05/10 H.Ikeda [QC#50140,ADD]
//            if (arLateFeeTrxDtlTMsgList.size() > 0) {
//                int insCnt = S21FastTBLAccessor.insert(arLateFeeTrxDtlTMsgList.toArray(new AR_LATE_FEE_TRX_DTLTMsg[arLateFeeTrxDtlTMsgList.size()]));
//                if (insCnt != arLateFeeTrxDtlTMsgList.size()) {
//                    this.msgIdMgr.addXxMsgId(NFZM0013E, pMsg);
//                    return;
//                }
//                arLateFeeTrxDtlTMsgList.clear();
//            }
//            // END   2019/05/10 H.Ikeda [QC#50140,ADD]

            // START 2019/05/10 H.Ikeda [QC#50140, ADD]
            // Update AR_STMT_CTRL.
            if (!updateArStmtCtrl(pMsg, arStmtIssCycleMap)) {
                return;
            }
            // END   2019/05/10 H.Ikeda [QC#50140, ADD]

            if (isExistsReport) {
                //********************************
                // If success, activate Report Processing.(Print)
                //********************************
                long processPk = service.activateAsyncReportJob();
                S21InfoLogOutput.println("||||||||||||| Process Pk Print: " + processPk + " |||||||||||||||||||");
            }

        } catch (SQLException e) {
            throw e;

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtBillTo, rsltSetBillTo);
            // START 2019/05/10 H.Ikeda [QC#50140,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.batProcNormRecCnt, BigDecimal.valueOf(this.normalRecordCnt));
            // END   2019/05/10 H.Ikeda [QC#50140,ADD]
            super.updateMessage(pMsg, this.msgIdMgr);
        }
    }

    private boolean checkParam(NFZC204001PMsg pMsg) {

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            this.msgIdMgr.addXxMsgId(NFZM0001E, pMsg);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            this.msgIdMgr.addXxMsgId(NFZM0002E, pMsg);
            return false;
        } else {
            if (!ZYPDateUtil.isValidDate(pMsg.slsDt.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
                msgIdMgr.addXxMsgId(NFZM0005E, pMsg);
                return false;
            }
        }

        if (!ZYPCommonFunc.hasValue(pMsg.arStmtDt)) {
            this.msgIdMgr.addXxMsgId(NFZM0003E, pMsg);
            return false;
        } else {
            if (!ZYPDateUtil.isValidDate(pMsg.arStmtDt.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
                this.msgIdMgr.addXxMsgId(NFZM0006E, pMsg);
                return false;
            }
        }
        return true;
    }

    // START 2019/05/10 H.Ikeda [QC#50140, MOD]
    //private Map<String, String> getArStmtIssCycleCd(NFZC204001PMsg pMsg) {
    private Map<String, Object> getArStmtIssCycleCd(NFZC204001PMsg pMsg) {
    // END   2019/05/10 H.Ikeda [QC#50140, MOD]
        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("arStmtDt", pMsg.arStmtDt.getValue());
        // [QC#5655] INSERT start
        ssmParam.put("arStmtStsCd", AR_STMT_STS.PENDING);
        // [QC#5655] INSERT end
        // START 2019/05/10 H.Ikeda [QC#50140, MOD]
        //return (Map<String, String>) ssmClient.queryObject("getArStmtIssCycleCd", ssmParam);
        return (Map<String, Object>) ssmClient.queryObject("getArStmtIssCycleCd", ssmParam);
        // END   2019/05/10 H.Ikeda [QC#50140, MOD]
    }

    private String getArStmtIssDay(NFZC204001PMsg pMsg, String arStmtIssCycleCd) {
        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("arStmtIssCycleCd", arStmtIssCycleCd);
        ssmParam.put("actvFlgY", ZYPConstant.FLG_ON_Y);

        return (String) ssmClient.queryObject("getArStmtIssDay", ssmParam);
    }

    private PreparedStatement getTrgtBillTo(NFZC204001PMsg pMsg, String arStmtIssDay) throws SQLException {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("arStmtIssDay", arStmtIssDay);
        // START 2017/06/09 J.Kim [QC#18413,ADD]
        ssmParam.put("unapply", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmParam.put("partial", AR_CASH_APPLY_STS.PARTIAL);
        // END 2017/06/09 J.Kim [QC#18413,ADD]

        // START 2019/02/19 H.Ikeda [QC#30038,ADD]
        ssmParam.put("payerCustModeCdStmtTo", PAYER_CUST_MODE.STMT_TO);
        ssmParam.put("payerCustModeCdSoldTo", PAYER_CUST_MODE.SOLD_TO);
        // END   2019/02/19 H.Ikeda [QC#30038,ADD]
        
        // START 2019/07/20 H.Ikeda [QC#51787,ADD]
        ssmParam.put("applied", AR_CASH_APPLY_STS.APPLIED);
        ssmParam.put("month", getMonth(pMsg));
        // END   2019/07/20 H.Ikeda [QC#51787,ADD]
        return ssmLowClient.createPreparedStatement("getTrgtBillTo", ssmParam);
    }

    // START 2019/07/20 H.Ikeda [QC#51787,ADD]
    private String getMonth(NFZC204001PMsg pMsg) {
        String rtnStr = "";
        if (ZYPCommonFunc.hasValue(pMsg.arStmtDt) && 5 < pMsg.arStmtDt.getValue().length()) {
            rtnStr = pMsg.arStmtDt.getValue().substring(0, 6);
        }else {
            rtnStr = "noData";
        }
        return rtnStr;
    }
    // END   2019/07/20 H.Ikeda [QC#51787,ADD]
    
    private AR_GRACE_PERTMsg getArGracePerCd(NFZC204001PMsg pMsg) {
        AR_GRACE_PERTMsg inTMsg = new AR_GRACE_PERTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);

        AR_GRACE_PERTMsgArray outTMsgArray = (AR_GRACE_PERTMsgArray) S21CodeTableAccessor.findAll(inTMsg);

        if (outTMsgArray == null || outTMsgArray.length() == 0) {
            return null;
        }

        return outTMsgArray.no(0);
    }

    private PreparedStatement getTrgtInv(NFZC204001PMsg pMsg, String billToCustCd, BigDecimal netDueGracePerDaysAot) throws SQLException {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("billToCustCd", billToCustCd);
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("arCashApplyStsVoid", AR_CASH_APPLY_STS.VOID);
        ssmParam.put("arTrxTpCdInv", AR_TRX_TP.INVOICE);
        ssmParam.put("exclDt", ZYPDateUtil.addDays(pMsg.arStmtDt.getValue(), netDueGracePerDaysAot.negate().intValue()));
        ssmParam.put("graceDays", netDueGracePerDaysAot);
        // START 2016/09/02 K.Kojima [QC#10786,ADD]
        ssmParam.put("cltDsptStsCdApproved", CLT_DSPT_STS.APPROVED);
        // END 2016/09/02 K.Kojima [QC#10786,ADD]

        // START 2019/02/19 H.Ikeda [QC#30038,ADD]
        ssmParam.put("payerCustModeCdStmtTo", PAYER_CUST_MODE.STMT_TO);
        ssmParam.put("payerCustModeCdSoldTo", PAYER_CUST_MODE.SOLD_TO);
        // END   2019/02/19 H.Ikeda [QC#30038,ADD]

        return ssmLowClient.createPreparedStatement("getTrgtInv", ssmParam);
    }

    // [QC#6006] INSERT start
    private boolean getNotTrgtInv(String arTrxNum, String thisBillToCustCd, String glblCmpyCd) throws SQLException {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustCd", thisBillToCustCd);
        ssmParam.put("arTrxNum", arTrxNum);
        ssmParam.put("flag_Y", ZYPConstant.FLG_ON_Y);

        Map<String, Object> reslt = (Map<String, Object>) ssmClient.queryObject("getNotTrgtInv", ssmParam);
        if (reslt == null || reslt.size() <= 0) {
            return false;
        } else {
            // it's NOT TargetInv data. (LateFeeAmt is not over Customer LateFeeMinimum)
            return true;
        }
    }
    // [QC#6006] INSERT end

    private String checkCreateMode(NFZC204001PMsg pMsg, ResultSet rsltSetBillTo, ResultSet rsltSetInv, String lateFeeCalcFlgStmtCtrl) throws SQLException {

        if (ZYPConstant.FLG_OFF_N.equals(lateFeeCalcFlgStmtCtrl)
                || ZYPConstant.FLG_OFF_N.equals(rsltSetBillTo.getString("LATE_FEE_FLG"))) {
            /**
             * AR_STMT_CTRL.LATE_FEE_CALC_FLG = N [today NOT calc LateFee] or 
             * BILL_TO_CUST.LATE_FEE_FLG = N   [this Cust NOT calc LateFee]
             * -> Not Calc LateFee.
             */
            return MODE_FLG_OFF_INIT;
        }

        if ((ZYPConstant.FLG_ON_Y.equals(lateFeeCalcFlgStmtCtrl) 
                && ZYPConstant.FLG_ON_Y.equals(rsltSetBillTo.getString("LATE_FEE_FLG")) 
                && ZYPConstant.FLG_ON_Y.equals(rsltSetInv.getString("DSPT_EX_FLG")))) {
            /**
             * AR_STMT_CTRL.LATE_FEE_CALC_FLG = Y      [today calc LateFee] and 
             * BILL_TO_CUST.LATE_FEE_FLG = Y        [this Cust calc LateFee] and
             * CLT_DSPT_TRX.DEAL_CLT_DSPT_AMT = hasAmt [this Cust now Dispute!]
             * -> Not Calc LateFee.
             */
            return MODE_FLG_OFF_INIT;
        }

        if (rsltSetInv.getBigDecimal("DEAL_ORIG_GRS_AMT").compareTo(rsltSetInv.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT")) == 0) {
            /**
             * AR_TRX_BAL.DEAL_ORIG_GRS_AMT = DEAL_RMNG_BAL_GRS_AMT 
             * [this Inv OriginalAmoun = Balance ...has not Apply] 
             */
            return MODE_UNAPPLY;
        } else if (BigDecimal.ZERO.compareTo(rsltSetInv.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT")) == 0) {
            /**
             * AR_TRX_BAL.DEAL_RMNG_BAL_GRS_AMT = 0 
             * [this Inv Balance = 0 ...already Apply] 
             */
            return MODE_APPLY;
        }
        /**
         * Balance <> Original Amount & Balance <> 0
         * [this Inv OriginalAmoun != Balance !=0 ...has Apply but remain Balance] 
         */
        return MODE_APPLY;
    }

    private List<Map<String, Object>> getCashApply(NFZC204001PMsg pMsg, ResultSet rsltSetInv) throws SQLException {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("arTrxNum", rsltSetInv.getString("AR_TRX_NUM"));
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getCashApply", ssmParam);
    }

    // START 2019/05/10 H.Ikeda [QC#50140, MOD]
    private boolean createArLateFeeTrxDtl(String mode, NFZC204001PMsg pMsg, NFZC204001Bean bean) throws SQLException {
    //private boolean createArLateFeeTrxDtl(String mode, NFZC204001PMsg pMsg, NFZC204001Bean bean, List<AR_LATE_FEE_TRX_DTLTMsg> arLateFeeTrxDtlTMsgList) throws SQLException {
    // END   2019/05/10 H.Ikeda [QC#50140, MOD]
        ResultSet rsltSetInv = bean.getRsltSetInv();
        ResultSet rsltSetBillTo = bean.getRsltSetBillTo();
        BigDecimal arDlyIntRate = bean.getArDlyIntRate();
        AR_GRACE_PERTMsg arGracePerTMsg = bean.getArGracePerTMsg();
        BigDecimal totLateFeeAmt = bean.getTotLateFeeAmt();

        AR_LATE_FEE_TRX_DTLTMsg tMsg = new AR_LATE_FEE_TRX_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arLateFeeTrxDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_LATE_FEE_TRX_DTL_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.arStmtDt, pMsg.arStmtDt);
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustAcctCd, rsltSetInv.getString("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCd, rsltSetInv.getString("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, rsltSetInv.getString("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.arTrxNum, rsltSetInv.getString("AR_TRX_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.arLastStmtDt, rsltSetInv.getString("AR_LAST_STMT_DT"));
        tMsg.lastCashAppDt.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.invDt, rsltSetInv.getString("AR_TRX_DT"));
        ZYPEZDItemValueSetter.setValue(tMsg.invDueDt, rsltSetInv.getString("INV_DUE_DT"));
        ZYPEZDItemValueSetter.setValue(tMsg.arGracePerCd, arGracePerTMsg.arGracePerCd);
        ZYPEZDItemValueSetter.setValue(tMsg.billToLateFeeFlg, rsltSetBillTo.getString("LATE_FEE_FLG"));
        if (ZYPConstant.FLG_ON_Y.equals(rsltSetInv.getString("DSPT_EX_FLG"))) {
            ZYPEZDItemValueSetter.setValue(tMsg.dealCltDsptAmt, rsltSetInv.getBigDecimal("DEAL_CLT_DSPT_AMT"));
        } else {
            tMsg.dealCltDsptAmt.clear();
        }
        ZYPEZDItemValueSetter.setValue(tMsg.arDlyIntRate, arDlyIntRate);
        ZYPEZDItemValueSetter.setValue(tMsg.dealRmngBalGrsAmt, rsltSetInv.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT"));

        if (MODE_UNAPPLY.equals(mode)) {
            tMsg.cashAppDt.clear();
            ZYPEZDItemValueSetter.setValue(tMsg.pmtLateDaysAot, getLateDays(pMsg.arStmtDt.getValue(), rsltSetInv.getString("AR_LAST_STMT_DT"), rsltSetInv.getString("INV_DUE_DT")));
            ZYPEZDItemValueSetter.setValue(tMsg.arLateFeeAmt, getLateFeeAmt(tMsg.dealRmngBalGrsAmt.getValue(), tMsg.pmtLateDaysAot.getValue(), arDlyIntRate));
        } else if (MODE_FLG_OFF_INIT.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(tMsg.cashAppDt, rsltSetInv.getString("CASH_APP_DT"));
            tMsg.pmtLateDaysAot.clear();
            ZYPEZDItemValueSetter.setValue(tMsg.arLateFeeAmt, BigDecimal.ZERO);
        }

        // START 2019/05/10 H.Ikeda [QC#50140, MOD]
//        arLateFeeTrxDtlTMsgList.add(tMsg);
//        if (arLateFeeTrxDtlTMsgList.size() >= COMMIT_CNT) {
//            int insCnt = S21FastTBLAccessor.insert(arLateFeeTrxDtlTMsgList.toArray(new AR_LATE_FEE_TRX_DTLTMsg[arLateFeeTrxDtlTMsgList.size()]));
//            if (insCnt != arLateFeeTrxDtlTMsgList.size()) {
//                return false;
//            }
//            arLateFeeTrxDtlTMsgList.clear();
//        }
        S21ApiTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return false;
        }
        // END  2019/05/10 H.Ikeda [QC#50140, MOD]

        totLateFeeAmt = totLateFeeAmt.add(tMsg.arLateFeeAmt.getValue());
        bean.setTotLateFeeAmt(totLateFeeAmt);

        // START 2018/06/05 [QC#26107, ADD]
        bean.getArLateFeeTrxPkList().add(tMsg.arLateFeeTrxDtlPk.getValue());
        // END   2018/06/05 [QC#26107, ADD]

        return true;
    }

    // START 2019/05/10 H.Ikeda [QC#50140, MOD]
    private boolean createArLateFeeTrxDtlCashApp(NFZC204001PMsg pMsg, NFZC204001Bean bean, String applyDt, String firstDay, String lastApplyDt, BigDecimal curRmngGrsAmt) throws SQLException {
    //private boolean createArLateFeeTrxDtlCashApp(NFZC204001PMsg pMsg, NFZC204001Bean bean, String applyDt, String firstDay, String lastApplyDt, BigDecimal curRmngGrsAmt, List<AR_LATE_FEE_TRX_DTLTMsg> arLateFeeTrxDtlTMsgList) throws SQLException {
    // END   2019/05/10 H.Ikeda [QC#50140, MOD]

        ResultSet rsltSetInv = bean.getRsltSetInv();
        ResultSet rsltSetBillTo = bean.getRsltSetBillTo();
        BigDecimal arDlyIntRate = bean.getArDlyIntRate();
        AR_GRACE_PERTMsg arGracePerTMsg = bean.getArGracePerTMsg();
        BigDecimal totLateFeeAmt = bean.getTotLateFeeAmt();

        AR_LATE_FEE_TRX_DTLTMsg tMsg = new AR_LATE_FEE_TRX_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arLateFeeTrxDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_LATE_FEE_TRX_DTL_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.arStmtDt, pMsg.arStmtDt);
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustAcctCd, rsltSetInv.getString("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCd, rsltSetInv.getString("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, rsltSetInv.getString("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.arTrxNum, rsltSetInv.getString("AR_TRX_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.arLastStmtDt, rsltSetInv.getString("AR_LAST_STMT_DT"));
        ZYPEZDItemValueSetter.setValue(tMsg.invDt, rsltSetInv.getString("AR_TRX_DT"));
        ZYPEZDItemValueSetter.setValue(tMsg.invDueDt, rsltSetInv.getString("INV_DUE_DT"));
        ZYPEZDItemValueSetter.setValue(tMsg.arGracePerCd, arGracePerTMsg.arGracePerCd);
        ZYPEZDItemValueSetter.setValue(tMsg.billToLateFeeFlg, rsltSetBillTo.getString("LATE_FEE_FLG"));
        if (ZYPConstant.FLG_ON_Y.equals(rsltSetInv.getString("DSPT_EX_FLG"))) {
            ZYPEZDItemValueSetter.setValue(tMsg.dealCltDsptAmt, rsltSetInv.getBigDecimal("DEAL_CLT_DSPT_AMT"));
        } else {
            tMsg.dealCltDsptAmt.clear();
        }
        ZYPEZDItemValueSetter.setValue(tMsg.arDlyIntRate, arDlyIntRate);

        ZYPEZDItemValueSetter.setValue(tMsg.cashAppDt, rsltSetInv.getString("CASH_APP_DT"));
        ZYPEZDItemValueSetter.setValue(tMsg.lastCashAppDt, lastApplyDt);
        ZYPEZDItemValueSetter.setValue(tMsg.pmtLateDaysAot, getLateDays(applyDt, firstDay));
        ZYPEZDItemValueSetter.setValue(tMsg.dealRmngBalGrsAmt, curRmngGrsAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.arLateFeeAmt, getLateFeeAmt(tMsg.dealRmngBalGrsAmt.getValue(), tMsg.pmtLateDaysAot.getValue(), arDlyIntRate));

        // START 2019/05/10 H.Ikeda [QC#50140, MOD]
//        arLateFeeTrxDtlTMsgList.add(tMsg);
//        if (arLateFeeTrxDtlTMsgList.size() >= COMMIT_CNT) {
//            int insCnt = S21FastTBLAccessor.insert(arLateFeeTrxDtlTMsgList.toArray(new AR_LATE_FEE_TRX_DTLTMsg[arLateFeeTrxDtlTMsgList.size()]));
//            if (insCnt != arLateFeeTrxDtlTMsgList.size()) {
//                return false;
//            }
//            arLateFeeTrxDtlTMsgList.clear();
//        }
        S21ApiTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return false;
        }
        // END  2019/05/10 H.Ikeda [QC#50140, MOD]
        
        totLateFeeAmt = totLateFeeAmt.add(tMsg.arLateFeeAmt.getValue());
        bean.setTotLateFeeAmt(totLateFeeAmt);

        // START 2018/06/05 [QC#26107, ADD]
        bean.getArLateFeeTrxPkList().add(tMsg.arLateFeeTrxDtlPk.getValue());
        // END   2018/06/05 [QC#26107, ADD]

        return true;
    }

    private BigDecimal getLateDays(String arStmtDt, String lastArStmtDt, String invDueDt) {
        if (ZYPCommonFunc.hasValue(lastArStmtDt)) {
            return changeNumIfNumOver(new BigDecimal(ZYPDateUtil.getDiffDays(arStmtDt, lastArStmtDt)));//S21_NA MOD START QC#21977
        }
        
        return changeNumIfNumOver(new BigDecimal(ZYPDateUtil.getDiffDays(arStmtDt, invDueDt)));//S21_NA MOD START QC#21977
    }

    private BigDecimal getLateDays(String arStmtDt, String lastArStmtDt) {
        return changeNumIfNumOver(new BigDecimal(ZYPDateUtil.getDiffDays(arStmtDt, lastArStmtDt)));//S21_NA MOD START QC#21977
    }
    
    //S21_NA ADD START QC#21977
    private BigDecimal changeNumIfNumOver(BigDecimal num) {
        BigDecimal maxNum = new BigDecimal(MAX_LATE_DAYS);
        if (maxNum.compareTo(num) < 0) {
            return maxNum;
        }
        return num;
    }
    //S21_NA END START QC#21977

    private BigDecimal getLateFeeAmt(BigDecimal rmngBalGrsAmt, BigDecimal pmtLateDaysAot, BigDecimal arDlyIntRate) {
        return (rmngBalGrsAmt.multiply(pmtLateDaysAot).multiply(arDlyIntRate)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private boolean createArAdj(NFZC204001PMsg pMsg, NFZC204001Bean bean, NFXC3070Bean rsltBean, BigDecimal arTrxBalPk, String arAdjNum) throws SQLException {
        AR_ADJTMsg tMsg = new AR_ADJTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arAdjNum, arAdjNum);
        ZYPEZDItemValueSetter.setValue(tMsg.arAdjTrxTpCd, AR_ADJ_TRX_TP.DEDUCTION);
        ZYPEZDItemValueSetter.setValue(tMsg.arAdjTpCd, bean.getArAdjTpCd());
        ZYPEZDItemValueSetter.setValue(tMsg.dealCcyCd, bean.getCcyCd());
        ZYPEZDItemValueSetter.setValue(tMsg.dealArAdjAmt, bean.getTotLateFeeAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.exchRate, rsltBean.getExchRate());
        ZYPEZDItemValueSetter.setValue(tMsg.funcCcyCd, rsltBean.getFuncCcyCd());
        ZYPEZDItemValueSetter.setValue(tMsg.funcArAdjAmt, rsltBean.getFuncAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.origArTrxBalPk, arTrxBalPk);
        //S21_NA MOD START QC#25981
//        ZYPEZDItemValueSetter.setValue(tMsg.adjDt, pMsg.arStmtDt);
//        ZYPEZDItemValueSetter.setValue(tMsg.glDt, pMsg.arStmtDt);
        ZYPEZDItemValueSetter.setValue(tMsg.adjDt, bean.getArAcctDt());
        ZYPEZDItemValueSetter.setValue(tMsg.glDt, bean.getArAcctDt());
        //S21_NA MOD END QC#25981
        tMsg.adjCmntTxt.clear();
        tMsg.adjApvlCmntTxt.clear();
        tMsg.apvlByPsnCd.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.arAdjStsCd, AR_ADJ_STS.APPROVED);
        ZYPEZDItemValueSetter.setValue(tMsg.tocCd, bean.getOrigTocCd());
        ZYPEZDItemValueSetter.setValue(tMsg.coaProdCd, bean.getOrigCoaProdCd());
        ZYPEZDItemValueSetter.setValue(tMsg.ajeCratCpltFlg, ZYPConstant.FLG_OFF_N);
        tMsg.ajeCratVrsnNum.clear();
        tMsg.arWrtOffNoteCd.clear();
        tMsg.arWrtOffNoteTxt.clear();

        S21ApiTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return false;
        }

        return true;
    }
    private boolean createArTrx(NFZC204001PMsg pMsg, NFZC204001Bean bean, NFXC3070Bean rsltBean, String arAdjNum, BigDecimal arTrxBalPk) throws SQLException {

        AR_TRX_BALTMsg tMsg = new AR_TRX_BALTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arTrxBalPk, arTrxBalPk);
        ZYPEZDItemValueSetter.setValue(tMsg.arTrxNum, ZYPNumbering.getUniqueID("DD#"));
        ZYPEZDItemValueSetter.setValue(tMsg.arTrxTpCd, AR_TRX_TP.DEDUCTION);
        ZYPEZDItemValueSetter.setValue(tMsg.attAdjNum, arAdjNum);
        ZYPEZDItemValueSetter.setValue(tMsg.dealCcyCd, bean.getCcyCd());
        ZYPEZDItemValueSetter.setValue(tMsg.dealOrigGrsAmt, bean.getTotLateFeeAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.dealApplyGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.dealApplyCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.dealApplyCrAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.dealRmngBalGrsAmt, bean.getTotLateFeeAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.dealNetSlsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.dealFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.dealTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.dealInvDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.dealPrmoDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.dealRcptVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.exchRate, rsltBean.getExchRate());
        ZYPEZDItemValueSetter.setValue(tMsg.funcCcyCd, rsltBean.getFuncCcyCd());
        ZYPEZDItemValueSetter.setValue(tMsg.funcOrigGrsAmt, rsltBean.getFuncAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.funcApplyGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.funcApplyCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.funcApplyCrAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.funcApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.funcApplyAdjRsvdAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.funcRvalVarAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.funcRmngBalGrsAmt, rsltBean.getFuncAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.funcNetSlsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.funcFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.funcTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.funcInvDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.funcPrmoDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.funcRcptVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCashDiscCd, bean.getPmtTermCashDiscCd());
        ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCd, bean.getPmtTermCd());
        tMsg.cashDiscTermCd.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.cashDiscPct, BigDecimal.ZERO);
        tMsg.invPmtMethCd.clear();
        tMsg.invPmtCondCd.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.UNAPPLIED);
        ZYPEZDItemValueSetter.setValue(tMsg.arTrxDt, bean.getArAcctDt());
        ZYPEZDItemValueSetter.setValue(tMsg.invDueDt, ZYPDateUtil.addDays(bean.getArAcctDt(), bean.getPmtTermAot().intValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.glDt, bean.getArAcctDt());
        ZYPEZDItemValueSetter.setValue(tMsg.cashAppDt, bean.getArAcctDt());
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, bean.getOrigBillToCustCd());
        ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCd, bean.getOrigSellToCustCd());
        ZYPEZDItemValueSetter.setValue(tMsg.payerCustCd, bean.getOrigPayerCustCd());
        ZYPEZDItemValueSetter.setValue(tMsg.tocCd, bean.getOrigTocCd());
        ZYPEZDItemValueSetter.setValue(tMsg.coaProdCd, bean.getOrigCoaProdCd());
        tMsg.grpInvNum.clear();
        tMsg.cpoOrdNum.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.custIssPoNum, bean.getArAdjTpNm());
        ZYPEZDItemValueSetter.setValue(tMsg.arCustRefNum, AR_CUST_REF_STR + pMsg.arStmtDt.getValue() + bean.getOrigBillToCustCd());
        ZYPEZDItemValueSetter.setValue(tMsg.exptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.upperCustIssPoNum, bean.getArAdjTpNm());
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustAcctCd, bean.getOrigBillToCustAcctCd());
        tMsg.srcSysDocNum.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.sysSrcCd, bean.getArSubSysId());
        tMsg.origInvNum.clear();
        tMsg.svcInvPk.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.dsInvTpCd, bean.getDsInvTpCd());
        ZYPEZDItemValueSetter.setValue(tMsg.slsRepTocCd, bean.getOrigSlsRepTocCd());
        tMsg.custCareTktNum.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.arAutoPurgeOfsFlg, ZYPConstant.FLG_OFF_N);
        tMsg.crRebilRsnCatgCd.clear();
        tMsg.arTrxBillFromDt.clear();
        tMsg.arTrxBillThruDt.clear();

        S21ApiTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    private boolean setPmtTermInfo(NFZC204001PMsg pMsg, NFZC204001Bean bean) {

        String pmtTermCashDiscCd = ZYPCodeDataUtil.getVarCharConstValue("AR_LATE_FEE_PMT_TERM", pMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(pmtTermCashDiscCd)) {
            return false;
        }

        PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = new PMT_TERM_CASH_DISCTMsg();
        ZYPEZDItemValueSetter.setValue(pmtTermCashDiscTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmtTermCashDiscTMsg.pmtTermCashDiscCd, pmtTermCashDiscCd);
        PMT_TERM_CASH_DISCTMsg outPmtTermCashDiscTMsg = (PMT_TERM_CASH_DISCTMsg) S21ApiTBLAccessor.findByKey(pmtTermCashDiscTMsg);
        if (outPmtTermCashDiscTMsg == null) {
            this.msgIdMgr.addXxMsgId(NFZM0008E, pMsg);
            return false;
        }

        PMT_TERMTMsg pmtTermTMsg = new PMT_TERMTMsg();
        ZYPEZDItemValueSetter.setValue(pmtTermTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmtTermTMsg.pmtTermCd, outPmtTermCashDiscTMsg.pmtTermCd);
        PMT_TERMTMsg outPmtTermTMsg = (PMT_TERMTMsg) S21ApiTBLAccessor.findByKey(pmtTermTMsg);
        if (outPmtTermTMsg == null) {
            this.msgIdMgr.addXxMsgId(NFZM0009E, pMsg);
            return false;
        }

        bean.setPmtTermCashDiscCd(pmtTermCashDiscCd);
        bean.setPmtTermCd(outPmtTermCashDiscTMsg.pmtTermCd.getValue());
        bean.setPmtTermAot(outPmtTermTMsg.pmtTermAot.getValue());

        return true;
    }

    private long createEIPReportRquest(S21EIPPrintingService service, NFZC204001PMsg pMsg, String billToCustCd, String sysTimeStamp) {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String titleBase = makeTitleBase(billToCustCd, sysTimeStamp);

        //********************************
        // Set Report Basic Info
        //********************************
        S21ReportRequestBean request = new S21ReportRequestBean(LF_RPT_ID);
        request.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
        request.setRptArcFlg(true);
        String rptTtlNm = "Print Late Fee " + titleBase;
        request.setRptTtlNm(rptTtlNm);

        //********************************
        // Set Report Input Parameter
        //********************************
        S21InputParameter inputParam = request.getInputParamBeanInstance();
        inputParam.addReportParameter("INTL_LANG_VAL_COL_NM", inputParam.getSystemDefaultLanguage());
        inputParam.addReportParameter("EZCANCELFLAG", "0");
        inputParam.addReportParameter("GLBL_CMPY_CD", glblCmpyCd);
        inputParam.addReportParameter("BILL_TO_CUST_CD", billToCustCd);
        inputParam.addReportParameter("AR_STMT_DT", pMsg.arStmtDt.getValue());
        request.setInputParamBean(inputParam);

        //********************************
        // Create Request
        //********************************
        long requestPk = service.createReportByAsync(request);
        S21InfoLogOutput.println("||||||||||||| Request PK for print: " + requestPk + " |||||||||||||||||||");

        // START 2018/06/05 [QC#26107, ADD]
        return requestPk;
        // END   2018/06/05 [QC#26107, ADD]
    }

    private String makeTitleBase(String billToCustCd, String sysTimeStamp) {
        StringBuilder rptTtl = new StringBuilder();
        rptTtl.append("BilltoCustomer ");
        rptTtl.append(billToCustCd);
        rptTtl.append(" Time ");
        rptTtl.append(sysTimeStamp);
        return rptTtl.toString();
    }

    private BigDecimal nvl(BigDecimal val) {
        if (!ZYPCommonFunc.hasValue(val)) {
            return BigDecimal.ZERO;
        }
        return val;
    }

    // START 2018/06/05 [QC#26107, MOD]
    private boolean updateArLateFeeTrxDtl(NFZC204001PMsg pMsg, List<BigDecimal> arLateFeeTrxPkList, BigDecimal arTrxBalPk, long rqstPk) {

        for (BigDecimal arLateFeeTrxPk : arLateFeeTrxPkList) {
            AR_LATE_FEE_TRX_DTLTMsg dtlTMsg = new AR_LATE_FEE_TRX_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dtlTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dtlTMsg.arLateFeeTrxDtlPk, arLateFeeTrxPk);
            dtlTMsg = (AR_LATE_FEE_TRX_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dtlTMsg);
            if (dtlTMsg == null) {
                return false;
            }

            ZYPEZDItemValueSetter.setValue(dtlTMsg.eipRptRqstPk, BigDecimal.valueOf(rqstPk));
            // START 2018/07/03 [QC#26600, MOD]
            // ZYPEZDItemValueSetter.setValue(dtlTMsg.dedctArTrxBalPk, arTrxBalPk);
            ZYPEZDItemValueSetter.setValue(dtlTMsg.arTrxBalPk, arTrxBalPk);
            // END   2018/07/03 [QC#26600, MOD]

            S21ApiTBLAccessor.update(dtlTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dtlTMsg.getReturnCode())) {
                return false;
            }
        }

        return true;
    }
    // END   2018/06/05 [QC#26107, MOD]

    // START 2019/05/10 H.Ikeda [QC#50140,ADD]
    private boolean updateArStmtCtrl(NFZC204001PMsg pMsg, Map<String, Object> arStmtCtrlMap) {

        // Get update Target AR_STMT_CTRL data
        BigDecimal arStmtCtrlPk = (BigDecimal) arStmtCtrlMap.get("AR_STMT_CTRL_PK");

        AR_STMT_CTRLTMsg updTMsg = new AR_STMT_CTRLTMsg();
        updTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        updTMsg.arStmtCtrlPk.setValue(arStmtCtrlPk);
        updTMsg = (AR_STMT_CTRLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(updTMsg);
        if (updTMsg == null) {
            // error: This data has been updated by another user.
            this.msgIdMgr.addXxMsgId(NZZM0003E, pMsg);
            return false;
        }

        // Set update data
        ZYPEZDItemValueSetter.setValue(updTMsg.lateFeeFnlzFlg, ZYPConstant.FLG_ON_Y);

        // update
        EZDTBLAccessor.update(updTMsg);
        if(!EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())){
            // error: DB update error.
            this.msgIdMgr.addXxMsgId(NFZM0013E, pMsg);
            return false;
        }

        return true;
    }
    // END   2019/05/10 H.Ikeda [QC#50140,ADD]
}
