/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC034001;

import static com.canon.cusa.s21.api.NSZ.NSZC034001.constant.NSZC034001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.dbcommon.EZDTBLAccessor;
import business.db.AGGR_USG_RECALTMsg;
import business.db.AGGR_USG_RECALTMsgArray;
import business.db.AGGR_USG_RECAL_DTLTMsg;
import business.db.AGGR_USG_RECAL_DTLTMsgArray;
import business.db.AGGR_USG_RECAL_XS_MTRTMsg;
import business.db.AGGR_USG_RECAL_XS_MTRTMsgArray;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsg;
import business.db.SVC_CONTR_BLLG_ALLOCTMsg;
import business.db.SVC_CONTR_BLLG_ALLOCTMsgArray;
import business.db.SVC_CONTR_MTR_BLLGTMsg;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsg;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsgArray;
import business.parts.NSZC034001PMsg;
import business.parts.NWZC036101PMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.CalcAddlChrgInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001CalcAddlChrg;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001CalcDate;
import com.canon.cusa.s21.common.NSX.NSXC003001.CallTaxCalcAPIForUsageBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CallTaxCalcAPIForUsage;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001Exchange;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;

/**
 * <pre>
 * Aggregate Calculation API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/25   Hitachi         T.Kanasaka      Create          NA
 * 2016/01/08   Hitachi         T.Kanasaka      Update          QC2660,QC2661
 * 2016/01/25   Hitachi         T.Kanasaka      Update          QC3675
 * 2016/01/26   Hitachi         T.Kanasaka      Update          QC3678,QC3747
 * 2016/03/10   Hitachi         T.Kanasaka      Update          QC5207
 * 2016/03/11   Hitachi         T.Kanasaka      Update          QC5369
 * 2016/03/15   Hitachi         T.Kanasaka      Update          QC5475
 * 2016/03/24   Hitachi         T.Kanasaka      Update          QC6001
 * 2016/03/28   Hitachi         T.Kanasaka      Update          QC5879
 * 2016/04/14   Hitachi         K.Kishimoto     Update          QC5879
 * 2016/04/20   Hitachi         T.Kanasaka      Update          QC7240
 * 2016/04/27   Hitachi         K.Kishimoto     Update          QC7653
 * 2016/08/10   Hitachi         T.Kanasaka      Update          QC12465
 * 2016/10/31   Hitachi         K.Kishimoto     Update          QC15597
 * 2017/09/07   Hitachi         K.Kojima        Update          QC#18440
 * 2018/02/26   Hitachi         U.Kim           Update          QC#23618
 * 2018/04/05   Hitachi         K.Kitachi       Update          QC#23066
 * 2018/05/15   Hitachi         K.Kishimoto     Update          QC#23541
 * 2018/05/31   Hitachi         K.Kishimoto     Updatge         QC#23541
 * 2018/06/05   Hitachi         K.Kojima        Update          QC#21974
 * 2018/06/20   Hitachi         T.Tomita        Update          QC#26792
 * 2019/01/21   CITS            M.Naito         Update          QC#29083
 * 2019/01/23   CITS            M.Naito         Update          QC#29958
 * 2021/05/07   CITS            T.Wada          Update          QC#58805-4
 * </pre>
 */
public class NSZC034001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Global Company Code
     */
    private String glblCmpyCd = null;

    /**
     * Standard Currency After Decimal Point
     */
    private int stdCcyAftDeclPntDigitNum = 0;

    /**
     * ONBATCH_TYPE
     */
    private ONBATCH_TYPE onBatTp;

    /**
     * Constructor
     */
    public NSZC034001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsgList List<NSZC034001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC034001PMsg> pMsgList, ONBATCH_TYPE onBatchType) {
        for (NSZC034001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatTp);
        }
    }

    /**
     * execute
     * @param pMsg NSZC034001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC034001PMsg pMsg, ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        execute(msgMap, onBatTp);
        msgMap.flush();
    }

    private void execute(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NSZC034001PMsg pMsg = (NSZC034001PMsg) msgMap.getPmsg();

        this.glblCmpyCd = pMsg.glblCmpyCd.getValue();
        this.onBatTp = onBatchType;

        checkParameter(msgMap);
        if (hasErrMsg(msgMap)) {
            return;
        }

        setCcyCd(msgMap);
        if (hasErrMsg(msgMap)) {
            return;
        }

        doProcess(msgMap);
    }

    private void checkParameter(S21ApiMessageMap msgMap) {
        NSZC034001PMsg pMsg = (NSZC034001PMsg) msgMap.getPmsg();
        if (!hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZM0001E);
        }
        if (!hasValue(pMsg.slsDt)) {
            msgMap.addXxMsgId(NSZM0002E);
        }
        if (!hasValue(pMsg.dsContrNum)) {
            msgMap.addXxMsgId(NSZM0271E);
        }
        if (!hasValue(pMsg.bllgDt)) {
            msgMap.addXxMsgId(NSZM0817E);
        }
    }

    private boolean hasErrMsg(S21ApiMessageMap msgMap) {
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return true;
        }
        return false;
    }

    private void setCcyCd(S21ApiMessageMap msgMap) {

        GLBL_CMPYTMsg inMsg = new GLBL_CMPYTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        GLBL_CMPYTMsg outMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            msgMap.addXxMsgId(NSZM0380E);
            return;
        }

        Integer scale = NSXC003001Exchange.getStdCcyDigit(this.glblCmpyCd);
        this.stdCcyAftDeclPntDigitNum = scale;
    }

    private void doProcess(S21ApiMessageMap msgMap) {

        // get target
        NSZC034001PMsg pMsg = (NSZC034001PMsg) msgMap.getPmsg();
        List<Map<String, Object>> contrBllgInfoMapList = getContrBllgInfo(pMsg.dsContrNum.getValue(), pMsg.bllgDt.getValue());
        if (contrBllgInfoMapList == null || contrBllgInfoMapList.size() == 0) {
            msgMap.addXxMsgId(NSZM0639E);
            return;
        }

        for (Map<String, Object> contrBllgInfoMap : contrBllgInfoMapList) {
            BigDecimal svcContrBllgGrpSq = (BigDecimal) contrBllgInfoMap.get("SVC_CONTR_BLLG_GRP_SQ");

            // remove AGGR_USG_RECAL, AGGR_USG_RECAL_DTL, AGGR_USG_RECAL_XS_MTR
            removeAggrUsgRecal(svcContrBllgGrpSq);

            // insert AGGR_USG_RECAL
            BigDecimal aggrUsgRecalPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AGGR_USG_RECAL_SQ);
            insertAggrUsgRecal(msgMap, contrBllgInfoMap, aggrUsgRecalPk, svcContrBllgGrpSq);
            if (hasErrMsg(msgMap)) {
                return;
            }

            // recalc
            recalc(msgMap, contrBllgInfoMap, aggrUsgRecalPk, svcContrBllgGrpSq);
            if (hasErrMsg(msgMap)) {
                return;
            }

            // update SVC_CONTR_MTR_BLLG, DS_CONTR_BLLG_SCHD, SVC_CONTR_XS_MTR_BLLG, SVC_CONTR_BLLG_ALLOC
            updateSvcContrBllg(msgMap, aggrUsgRecalPk);
            if (hasErrMsg(msgMap)) {
                return;
            }
        }

        // START 2018/04/05 K.Kitachi [QC#23066, ADD]
        // update DS_CONTR_BLLG_SCHD (Aggregate Line)
        updateDsContrBllgSchdForAggLine(msgMap, pMsg.dsContrNum.getValue(), pMsg.bllgDt.getValue());
        if (hasErrMsg(msgMap)) {
            return;
        }
        // END 2018/04/05 K.Kitachi [QC#23066, ADD]
    }

    private void removeAggrUsgRecal(BigDecimal svcContrBllgGrpSq) {

        // get remove data
        List<AGGR_USG_RECALTMsg> delAggrUsgRecalList = new ArrayList<AGGR_USG_RECALTMsg>();
        List<AGGR_USG_RECAL_DTLTMsg> delAggrUsgRecalDtlList = new ArrayList<AGGR_USG_RECAL_DTLTMsg>();
        List<AGGR_USG_RECAL_XS_MTRTMsg> delAggrUsgRecalXsMtrList = new ArrayList<AGGR_USG_RECAL_XS_MTRTMsg>();

        AGGR_USG_RECALTMsgArray aggrUsgRecalTMsgArray = getAggrUsgRecal(svcContrBllgGrpSq);
        for (int i = 0; i < aggrUsgRecalTMsgArray.getValidCount(); i++) {
            AGGR_USG_RECALTMsg aggrUsgRecalTMsg = aggrUsgRecalTMsgArray.no(i);
            delAggrUsgRecalList.add(aggrUsgRecalTMsg);

            AGGR_USG_RECAL_DTLTMsgArray aggrUsgRecalDtlTMsgArray = getAggrUsgRecalDtl(aggrUsgRecalTMsg.aggrUsgRecalPk.getValue());
            for (int j = 0; j < aggrUsgRecalDtlTMsgArray.getValidCount(); j++) {
                AGGR_USG_RECAL_DTLTMsg aggrUsgRecalDtlTMsg = aggrUsgRecalDtlTMsgArray.no(j);
                delAggrUsgRecalDtlList.add(aggrUsgRecalDtlTMsg);

                AGGR_USG_RECAL_XS_MTRTMsgArray aggrUsgRecalXsMtrTMsgArray = getAggrUsgRecalXsMtr(aggrUsgRecalDtlTMsg.aggrUsgRecalDtlPk.getValue());
                for (int k = 0; k < aggrUsgRecalXsMtrTMsgArray.getValidCount(); k++) {
                    AGGR_USG_RECAL_XS_MTRTMsg aggrUsgRecalXsMtrTMsg = aggrUsgRecalXsMtrTMsgArray.no(k);
                    delAggrUsgRecalXsMtrList.add(aggrUsgRecalXsMtrTMsg);
                }
            }
        }

        // remove AGGR_USG_RECAL, AGGR_USG_RECAL_DTL, AGGR_USG_RECAL_XS_MTR
        if (delAggrUsgRecalList.size() > 0) {
            S21ApiTBLAccessor.remove(delAggrUsgRecalList.toArray(new AGGR_USG_RECALTMsg[delAggrUsgRecalList.size()]));
        }
        if (delAggrUsgRecalDtlList.size() > 0) {
            S21ApiTBLAccessor.remove(delAggrUsgRecalDtlList.toArray(new AGGR_USG_RECAL_DTLTMsg[delAggrUsgRecalDtlList.size()]));
        }
        if (delAggrUsgRecalXsMtrList.size() > 0) {
            S21ApiTBLAccessor.remove(delAggrUsgRecalXsMtrList.toArray(new AGGR_USG_RECAL_XS_MTRTMsg[delAggrUsgRecalXsMtrList.size()]));
        }
    }

    private void insertAggrUsgRecal(S21ApiMessageMap msgMap, Map<String, Object> contrBllgInfoMap, BigDecimal aggrUsgRecalPk, BigDecimal svcContrBllgGrpSq) {
        NSZC034001PMsg pMsg = (NSZC034001PMsg) msgMap.getPmsg();

        AGGR_USG_RECALTMsg aggrUsgRecalTMsg = new AGGR_USG_RECALTMsg();
        setValue(aggrUsgRecalTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(aggrUsgRecalTMsg.aggrUsgRecalPk, aggrUsgRecalPk);
        setValue(aggrUsgRecalTMsg.dsContrPk, (BigDecimal) contrBllgInfoMap.get("DS_CONTR_PK"));
        setValue(aggrUsgRecalTMsg.dsContrNum, pMsg.dsContrNum.getValue());
        setValue(aggrUsgRecalTMsg.svcContrBllgGrpSq, svcContrBllgGrpSq);
        setValue(aggrUsgRecalTMsg.bllgFromDt, (String) contrBllgInfoMap.get("SVC_CONTR_BLLG_FROM_DT"));
        setValue(aggrUsgRecalTMsg.bllgThruDt, (String) contrBllgInfoMap.get("SVC_CONTR_BLLG_THRU_DT"));
        S21ApiTBLAccessor.insert(aggrUsgRecalTMsg);
        String rtnCd = aggrUsgRecalTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0818E);
            return;
        }
    }

    private void recalc(S21ApiMessageMap msgMap, Map<String, Object> contrBllgInfoMap, BigDecimal aggrUsgRecalPk, BigDecimal svcContrBllgGrpSq) {
        NSZC034001PMsg pMsg = (NSZC034001PMsg) msgMap.getPmsg();

        Map<String, Map<String, BigDecimal>> bllgMtrSumMap = new HashMap<String, Map<String, BigDecimal>>();
        BigDecimal sumCopiesMade = BigDecimal.ZERO;
        BigDecimal sumTotalCopy = BigDecimal.ZERO;
        BigDecimal sumAllowance = BigDecimal.ZERO;
        BigDecimal sumBillableCopy = BigDecimal.ZERO;
        // Add Start 05/15/2018 <QC#23541>
        AGGR_USG_RECALTMsg aggrUsgRecalTMsgParam = new AGGR_USG_RECALTMsg();
        setValue(aggrUsgRecalTMsgParam.glblCmpyCd, this.glblCmpyCd);
        setValue(aggrUsgRecalTMsgParam.aggrUsgRecalPk, aggrUsgRecalPk);
        AGGR_USG_RECALTMsg aggrUsgRecalTMsg = (AGGR_USG_RECALTMsg) S21ApiTBLAccessor.findByKey(aggrUsgRecalTMsgParam);
        BigDecimal dsContrPk = aggrUsgRecalTMsg.dsContrPk.getValue();
        BigDecimal sumMinAmtRate = BigDecimal.ZERO;
        BigDecimal sumMinCnt = BigDecimal.ZERO;
        BigDecimal aggLineDtlPk = BigDecimal.ZERO;
        List<AggTierInfo> aggTierInfoList = new ArrayList<AggTierInfo>();
        boolean isMinAmout = false;
        boolean aggTierCreateFlg = true;
        String xsChrgTpcd = "";
        // Add End   05/15/2018 <QC#23541>
        List<Map<String, Object>> recalcInfoList = getRecalcInfo(svcContrBllgGrpSq);
        for (Map<String, Object> recalcInfoMap : recalcInfoList) {
            String bllgMtrLbCd = (String) recalcInfoMap.get("BLLG_MTR_LB_CD");
        // Add Start 05/15/2018 <QC#23541>
            xsChrgTpcd = (String) recalcInfoMap.get("XS_CHRG_TP_CD");
        // Add End   05/15/2018 <QC#23541>
            Map<String, BigDecimal> sumMap = null;
            if (bllgMtrSumMap.containsKey(bllgMtrLbCd)) {
                sumMap = bllgMtrSumMap.get(bllgMtrLbCd);
                sumCopiesMade = sumMap.get(MAP_KEY_SUM_COPIES_MADE);
                sumTotalCopy = sumMap.get(MAP_KEY_SUM_TOTAL_COPY);
                sumAllowance = sumMap.get(MAP_KEY_SUM_ALLOWANCE);
                sumBillableCopy = sumMap.get(MAP_KEY_SUM_BILLABLE_COPY);
                // Add Start 05/15/2018 <QC#23541>
                sumMinAmtRate = sumMap.get(MAP_KEY_SUM_MIN_AMT_RATE);
                sumMinCnt = sumMap.get(MAP_KEY_SUM_MIN_CNT);
                // Add End   05/15/2018 <QC#23541>
            } else {
        // Mod Start 05/15/2018 <QC#23541>
                sumMap = new HashMap<String, BigDecimal>(SIZE_6);
        // Mod End   05/15/2018 <QC#23541>
                bllgMtrSumMap.put(bllgMtrLbCd, sumMap);
                sumCopiesMade = BigDecimal.ZERO;
                sumTotalCopy = BigDecimal.ZERO;
                sumAllowance = BigDecimal.ZERO;
                sumBillableCopy = BigDecimal.ZERO;
                // Add Start 05/15/2018 <QC#23541>
                sumMinAmtRate = BigDecimal.ZERO;
                sumMinCnt = BigDecimal.ZERO;
                // Add End   05/15/2018 <QC#23541>
            }

            // sum
            // Mod Start 2016/10/31 <QC#15597>
            BigDecimal testCopyQty = (BigDecimal) recalcInfoMap.get("TEST_COPY_QTY");
            // START 2017/09/07 K.Kojima [QC#18440,MOD]
            // sumCopiesMade = sumCopiesMade.add((BigDecimal) recalcInfoMap.get("MTR_COPY_QTY")).subtract(testCopyQty);
            // sumTotalCopy = sumTotalCopy.add((BigDecimal) recalcInfoMap.get("MTR_COPY_QTY")).subtract(testCopyQty);
            sumCopiesMade = sumCopiesMade.add((BigDecimal) recalcInfoMap.get("MTR_COPY_QTY"));
            sumTotalCopy = sumTotalCopy.add((BigDecimal) recalcInfoMap.get("MTR_COPY_QTY"));
            // END 2017/09/07 K.Kojima [QC#18440,MOD]
            // Mod End   2016/10/31 <QC#15597>
            // Add Start 05/15/2018 <QC#23541>
            aggLineDtlPk = (BigDecimal) recalcInfoMap.get("PRNT_DS_CONTR_DTL_PK");
            BigDecimal bllgMinAmtRate = (BigDecimal) recalcInfoMap.get("BLLG_MIN_AMT_RATE");
            // START 2019/01/23 M.Naito [QC#29958,MOD]
            if (hasValue(bllgMinAmtRate) && BigDecimal.ZERO.compareTo(bllgMinAmtRate) < 0) {
//                sumMinAmtRate = sumMinAmtRate.add(getBllgMinAmtRate(recalcInfoMap, bllgMinAmtRate));
                sumMinAmtRate = sumMinAmtRate.add(getBllgMin(recalcInfoMap, bllgMinAmtRate, ccyScale));
            }
            BigDecimal bllgMinCnt = (BigDecimal) recalcInfoMap.get("BLLG_MIN_CNT");
            if (hasValue(bllgMinCnt) && BigDecimal.ZERO.compareTo(bllgMinCnt) < 0) {
//                sumMinCnt = sumMinCnt.add(getBllgMinAmtRate(recalcInfoMap, bllgMinCnt));
                sumMinCnt = sumMinCnt.add(getBllgMin(recalcInfoMap, bllgMinCnt, 0));
            }
            // END 2019/01/23 M.Naito [QC#29958,MOD]
            List<Map<String, Object>> topSchdMtrList = getTopSchdMtrList((BigDecimal) recalcInfoMap.get("DS_CONTR_BLLG_SCHD_SMRY_PK"));
            int tierCnt = 0;
            for (Map<String, Object> topSchdMtr:topSchdMtrList) {
                if (aggTierCreateFlg == true) {
                    AggTierInfo aggTier = new AggTierInfo();
                    aggTier.setXsMtrCopyQty((BigDecimal) topSchdMtr.get("XS_MTR_COPY_QTY"));
                    aggTier.setXsMtrAmtRate((BigDecimal) topSchdMtr.get("XS_MTR_AMT_RATE"));
                    aggTier.setBllblCopyQty(BigDecimal.ZERO);
                    aggTier.setRemainBllblCopyQty(BigDecimal.ZERO);
                    // Add Start 05/31/2018 <QC#23541>
                    aggTier.setRemainChrgAmt(BigDecimal.ZERO);
                    // Add End   05/31/2018 <QC#23541>
                    aggTier.setExecChrg(false);
                    aggTierInfoList.add(aggTier);
                } else {
                    AggTierInfo aggTier = aggTierInfoList.get(tierCnt);
                    aggTier.addXsMtrCopyQty((BigDecimal) topSchdMtr.get("XS_MTR_COPY_QTY"));
                }
                tierCnt++;
            }
            aggTierCreateFlg = false;
            // Add End   05/15/2018 <QC#23541>
            sumAllowance = sumAllowance.add((BigDecimal) recalcInfoMap.get("COPY_INCL_QTY"));
            sumBillableCopy = sumBillableCopy.add((BigDecimal) recalcInfoMap.get("BLLG_COPY_QTY"));
            sumMap.put(MAP_KEY_SUM_COPIES_MADE, sumCopiesMade);
            sumMap.put(MAP_KEY_SUM_TOTAL_COPY, sumTotalCopy);
            sumMap.put(MAP_KEY_SUM_ALLOWANCE, sumAllowance);
            sumMap.put(MAP_KEY_SUM_BILLABLE_COPY, sumBillableCopy);
            // Add Start 05/15/2018 <QC#23541>
            sumMap.put(MAP_KEY_SUM_MIN_AMT_RATE, sumMinAmtRate);
            sumMap.put(MAP_KEY_SUM_MIN_CNT, sumMinCnt);
            // Add End   05/15/2018 <QC#23541>

            // insert AGGR_USG_RECAL_DTL
            BigDecimal aggrUsgRecalDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AGGR_USG_RECAL_DTL_SQ);
            insertAggrUsgRecalDtl(msgMap, recalcInfoMap, aggrUsgRecalPk, aggrUsgRecalDtlPk, bllgMtrLbCd);
            if (hasErrMsg(msgMap)) {
                return;
            }

            BigDecimal svcContrMtrBllgPk = (BigDecimal) recalcInfoMap.get("SVC_CONTR_MTR_BLLG_PK");
            SVC_CONTR_XS_MTR_BLLGTMsgArray svcContrXsMtrBllgTMsgArray = getSvcContrXsMtrBllg(svcContrMtrBllgPk);
            for (int i = 0; i < svcContrXsMtrBllgTMsgArray.getValidCount(); i++) {
                SVC_CONTR_XS_MTR_BLLGTMsg svcContrXsMtrBllgTMsg = svcContrXsMtrBllgTMsgArray.no(i);

                // insert AGGR_USG_RECAL_XS_MTR
                BigDecimal aggrUsgRecalXsMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AGGR_USG_RECAL_XS_MTR_SQ);
                insertAggrUsgRecalXsMtr(msgMap, svcContrXsMtrBllgTMsg, aggrUsgRecalDtlPk, aggrUsgRecalXsMtrPk);
            }
        }

        String rtnCd = null;
        String prcAllocByMachQtyFlg = (String) contrBllgInfoMap.get("PRC_ALLOC_BY_MACH_QTY_FLG");
        Set<Map.Entry<String, Map<String, BigDecimal>>> msgSet = bllgMtrSumMap.entrySet();
        Iterator<Map.Entry<String, Map<String, BigDecimal>>> msgSetIt = msgSet.iterator();
        while (msgSetIt.hasNext()) {
            Map.Entry<String, Map<String, BigDecimal>> entry = msgSetIt.next();
            String bllgMtrLbCd = entry.getKey();
            Map<String, BigDecimal> sumMap = entry.getValue();
            sumCopiesMade = sumMap.get(MAP_KEY_SUM_COPIES_MADE);
            sumTotalCopy = sumMap.get(MAP_KEY_SUM_TOTAL_COPY);
            sumAllowance = sumMap.get(MAP_KEY_SUM_ALLOWANCE);
            sumBillableCopy = sumMap.get(MAP_KEY_SUM_BILLABLE_COPY);
            BigDecimal sumRoundedBilledClick = BigDecimal.ZERO;
            BigDecimal sumAllocatedBilledClick = BigDecimal.ZERO;

            // START 2018/06/07 K.Kojima [QC#21974,ADD]
            BigDecimal bllgRollorverRatio = (BigDecimal) recalcInfoList.get(0).get("BLLG_ROLL_OVER_RATIO");
            // END 2018/06/07 K.Kojima [QC#21974,ADD]

            // Add Start 05/15/2018 <QC#23541>
            BigDecimal prevFreeCopyCnt = BigDecimal.ZERO;
            BigDecimal prevRolloverCnt = BigDecimal.ZERO;         
            Map<String, Object> freeCopyFromAggrRecalMap = getFreeCopyFromAggrRecal(dsContrPk, bllgMtrLbCd, aggrUsgRecalTMsg.bllgFromDt.getValue());
            // START 2019/01/21 M.Naito [QC#29083,ADD]
            Map<String, Object> cumCopyInfoMap = getFreeCopyFromAggrBllgMtr(bllgMtrLbCd, aggLineDtlPk);
            BigDecimal cumCopyCnt = (BigDecimal) cumCopyInfoMap.get("CUM_COPY_CNT");
            BigDecimal cumCopyFreq = (BigDecimal) cumCopyInfoMap.get("CUM_COPY_FREQ_MTH_AOT");
            String cumCopyStDt = (String) cumCopyInfoMap.get("CUM_COPY_START_DT");
            String cumCopyEdDt = (String) cumCopyInfoMap.get("CUM_COPY_END_DT");
            String bllgFromDt = aggrUsgRecalTMsg.bllgFromDt.getValue();
            String bllgThruDt = aggrUsgRecalTMsg.bllgThruDt.getValue();
            if (cumCopyCnt != null) {
                if (checkFromToDt(bllgThruDt, cumCopyStDt, cumCopyEdDt)) {
                    if (freeCopyFromAggrRecalMap != null && checkCumCopyReset(cumCopyFreq, cumCopyStDt, bllgFromDt, bllgThruDt)) {
                        freeCopyFromAggrRecalMap.put("BLLG_FREE_COPY_CNT", cumCopyCnt);
                        freeCopyFromAggrRecalMap.put("ROLL_OVER_CNT", null);
                    }
                } else {
                    if (checkCumCopyPerDays(bllgFromDt, bllgThruDt, aggLineDtlPk, aggrUsgRecalTMsg.aggrUsgRecalPk.getValue(), bllgMtrLbCd, cumCopyStDt)) {
                        String cumCopyCycleEndDt = addDays(NSXC001001CalcDate.addMonths(cumCopyStDt, cumCopyFreq.intValue()), -1);
                        BigDecimal cumCopyDaysAot = new BigDecimal(ZYPDateUtil.getDiffDays(cumCopyCycleEndDt, cumCopyStDt) + 1);
                        BigDecimal cumAllowancePerDays = cumCopyCnt.divide(cumCopyDaysAot, 0, BigDecimal.ROUND_UP);
                        BigDecimal mtrBllgDaysAot = new BigDecimal(ZYPDateUtil.getDiffDays(bllgThruDt, bllgFromDt) + 1);
                        prevFreeCopyCnt = cumAllowancePerDays.multiply(mtrBllgDaysAot);
                        cumCopyInfoMap.put("BLLG_FREE_COPY_CNT", prevFreeCopyCnt);
                        cumCopyInfoMap.put("ROLL_OVER_CNT", null);
                        freeCopyFromAggrRecalMap = cumCopyInfoMap;
                    }
                }
            }
            // END 2019/01/21 M.Naito [QC#29083,ADD]
            // START 2018/06/07 K.Kojima [QC#21974,ADD]
            Map<String, Object> tempFreeCopyFromAggrRecalMap = getFreeCopyFromAggrRecalUsed(aggLineDtlPk, bllgMtrLbCd, aggrUsgRecalTMsg.svcContrBllgGrpSq.getValue(), aggrUsgRecalTMsg.bllgFromDt.getValue());
            if (tempFreeCopyFromAggrRecalMap != null && !tempFreeCopyFromAggrRecalMap.isEmpty()) {
                bllgRollorverRatio = (BigDecimal) tempFreeCopyFromAggrRecalMap.get("ROLL_OVER_RATIO");
                if (freeCopyFromAggrRecalMap == null || freeCopyFromAggrRecalMap.isEmpty()) {
                    freeCopyFromAggrRecalMap = tempFreeCopyFromAggrRecalMap;
                }
            }
            // END 2018/06/07 K.Kojima [QC#21974,ADD]
            if (freeCopyFromAggrRecalMap == null || freeCopyFromAggrRecalMap.isEmpty()) {
                freeCopyFromAggrRecalMap = getFreeCopyFromAggrBllgMtr(bllgMtrLbCd, aggLineDtlPk);
            }
            if (freeCopyFromAggrRecalMap != null && !freeCopyFromAggrRecalMap.isEmpty()) {
                prevFreeCopyCnt = (BigDecimal)freeCopyFromAggrRecalMap.get("BLLG_FREE_COPY_CNT");
                prevRolloverCnt = (BigDecimal)freeCopyFromAggrRecalMap.get("ROLL_OVER_CNT");
            }
            // START 2018/06/07 K.Kojima [QC#21974,DEL]
            // BigDecimal bllgRollorverRatio = (BigDecimal) recalcInfoList.get(0).get("BLLG_ROLL_OVER_RATIO");
            // END 2018/06/07 K.Kojima [QC#21974,DEL]
            BigDecimal bllgMinAmtRate = sumMap.get(MAP_KEY_SUM_MIN_AMT_RATE);
            BigDecimal bllgMinCnt = sumMap.get(MAP_KEY_SUM_MIN_CNT);
            BigDecimal usgFreeCopyCnt = BigDecimal.ZERO;
            BigDecimal remainingRolloverCnt = BigDecimal.ZERO;
            BigDecimal remainingFreeCopyCnt = null;
            BigDecimal remainingBllgCopyCnt = BigDecimal.ZERO;
            BigDecimal AggAmount = BigDecimal.ZERO;
            if (hasValue(prevFreeCopyCnt) && BigDecimal.ZERO.compareTo(prevFreeCopyCnt) < 0) {
                // Free Copy
                if (sumAllowance.compareTo(sumCopiesMade) > 0) {
                    remainingBllgCopyCnt = BigDecimal.ZERO;
                    usgFreeCopyCnt = BigDecimal.ZERO;
                    remainingFreeCopyCnt = prevFreeCopyCnt;
                } else if (prevFreeCopyCnt.add(sumAllowance).compareTo(sumCopiesMade) >= 0) {
                    remainingBllgCopyCnt = BigDecimal.ZERO;
                    usgFreeCopyCnt = sumCopiesMade.subtract(sumAllowance);
                    remainingFreeCopyCnt = prevFreeCopyCnt.subtract(usgFreeCopyCnt);
                } else {
                    remainingBllgCopyCnt = sumCopiesMade.subtract(prevFreeCopyCnt);
                    usgFreeCopyCnt = prevFreeCopyCnt;
                    remainingFreeCopyCnt = BigDecimal.ZERO;
                }
                AggAmount = calcAggAmt(xsChrgTpcd, aggTierInfoList, sumAllowance, remainingBllgCopyCnt);
            } else if (hasValue(bllgRollorverRatio) && BigDecimal.ZERO.compareTo(bllgRollorverRatio) < 0) {
                // Roll Over
                if (!hasValue(prevRolloverCnt)) {
                    prevRolloverCnt = BigDecimal.ZERO;
                }
                remainingRolloverCnt = BigDecimal.ZERO;
                remainingBllgCopyCnt = sumCopiesMade.subtract(prevRolloverCnt);

                // START 2021/05/08 [QC#58805 MOD]
                BigDecimal curBllgCopyCnt = BigDecimal.ZERO;
                curBllgCopyCnt = sumAllowance.subtract(sumCopiesMade);
                if (curBllgCopyCnt.compareTo(BigDecimal.ZERO) > 0) {
                    remainingRolloverCnt = (curBllgCopyCnt.multiply(bllgRollorverRatio.divide(BIGDECIMAL_100)).setScale(0, BigDecimal.ROUND_HALF_UP)).add(prevRolloverCnt);
                } else {
                    remainingRolloverCnt = curBllgCopyCnt.setScale(0, BigDecimal.ROUND_HALF_UP).add(prevRolloverCnt);
                }
                if  (remainingRolloverCnt.compareTo(BigDecimal.ZERO) < 0) {
                    remainingRolloverCnt = BigDecimal.ZERO;
                }

//                if (sumAllowance.compareTo(remainingBllgCopyCnt) > 0) {
//                    remainingRolloverCnt = sumAllowance.subtract(remainingBllgCopyCnt).multiply(bllgRollorverRatio.divide(BIGDECIMAL_100)).setScale(0, BigDecimal.ROUND_HALF_UP);
//                }
                // END 2021/05/08 [QC#58805 MOD]

                AggAmount = calcAggAmt(xsChrgTpcd, aggTierInfoList, sumAllowance, remainingBllgCopyCnt);
            } else if (hasValue(bllgMinAmtRate) && BigDecimal.ZERO.compareTo(bllgMinAmtRate) < 0) {
                // Min Amt Rate
                remainingBllgCopyCnt = sumCopiesMade;
                AggAmount = calcAggAmt(xsChrgTpcd, aggTierInfoList, sumAllowance, remainingBllgCopyCnt);
                if (AggAmount.compareTo(bllgMinAmtRate) < 0) {
                    AggAmount = bllgMinAmtRate;
                    isMinAmout = true;
                }
            } else if (hasValue(bllgMinCnt) && BigDecimal.ZERO.compareTo(bllgMinCnt) < 0) {
                // Min count
                if (sumCopiesMade.compareTo(bllgMinCnt) < 0) {
                    remainingBllgCopyCnt = bllgMinCnt;
                } else {
                    remainingBllgCopyCnt = sumCopiesMade;
                }
                AggAmount =  calcAggAmt(xsChrgTpcd, aggTierInfoList, sumAllowance, remainingBllgCopyCnt);
            } else {
                remainingBllgCopyCnt = sumCopiesMade;
                AggAmount =  calcAggAmt(xsChrgTpcd, aggTierInfoList, sumAllowance, remainingBllgCopyCnt);
            }
            setValue(aggrUsgRecalTMsg.bllgMtrLbCd, bllgMtrLbCd);
            setValue(aggrUsgRecalTMsg.bllgFreeCopyCnt, remainingFreeCopyCnt);
            setValue(aggrUsgRecalTMsg.rollOverCnt, remainingRolloverCnt);
            // START 2018/06/05 K.Kojima [QC#21974,ADD]
            setValue(aggrUsgRecalTMsg.usgFreeCopyCnt, usgFreeCopyCnt);
            if (bllgRollorverRatio == null) {
                setValue(aggrUsgRecalTMsg.rollOverRatio, BigDecimal.ZERO);
            } else {
                setValue(aggrUsgRecalTMsg.rollOverRatio, bllgRollorverRatio);
            }
            // END 2018/06/05 K.Kojima [QC#21974,ADD]
            S21ApiTBLAccessor.update(aggrUsgRecalTMsg);

            // Add End   05/15/2018 <QC#23541>

            // allocate
            // Mod Start 05/15/2018 <QC#23541>
            List<AggTierInfo> aggTierInfoListAsc = new ArrayList<AggTierInfo>();
            int aggTierSize = aggTierInfoList.size();
            for (int i = aggTierSize - 1; i >= 0; i--) {
                aggTierInfoListAsc.add(aggTierInfoList.get(i));
            }
            AGGR_USG_RECAL_DTLTMsgArray aggrUsgRecalDtlTMsgArray = getAggrUsgRecalDtlForAlloc(aggrUsgRecalPk, bllgMtrLbCd);
            int recalDtlCnt = aggrUsgRecalDtlTMsgArray.getValidCount();
            for (int i = 0; i < recalDtlCnt; i++) {
                BigDecimal allocateRatio = null;
                BigDecimal allocatedBilledClick = BigDecimal.ZERO;
                BigDecimal roundedBilledClick = BigDecimal.ZERO;
                AGGR_USG_RECAL_DTLTMsg aggrUsgRecalDtlTMsg = aggrUsgRecalDtlTMsgArray.no(i);
                BigDecimal aggrUsgRecalDtlPk = aggrUsgRecalDtlTMsg.aggrUsgRecalDtlPk.getValue();

                // START 2016/03/11 T.Kanasaka [QC5369, MOD]
                // START 2016/08/10 T.Kanasaka [QC12465, MOD]
//                if (ZYPConstant.FLG_ON_Y.equals(prcAllocByMachQtyFlg)) {
                if (!ZYPConstant.FLG_ON_Y.equals(prcAllocByMachQtyFlg)) {
                    // END 2016/08/10 T.Kanasaka [QC12465, MOD]
                    if (BigDecimal.ZERO.compareTo(sumBillableCopy) == 0) {
                        allocateRatio = BigDecimal.ZERO;
                    } else {
                        // Mod Start 2018/06/20 QC#26792
                        allocateRatio = aggrUsgRecalDtlTMsg.bllblCopyQty.getValue().divide(sumBillableCopy, RATIO_SCALE, HALF_UP);
                        // Mod End 2018/06/20 QC#26792
                    }
                } else {
                    if (BigDecimal.ZERO.compareTo(sumTotalCopy) == 0) {
                        allocateRatio = BigDecimal.ZERO;
                    } else {
                        // Mod Start 2018/06/20 QC#26792
                        allocateRatio = aggrUsgRecalDtlTMsg.totCopyQty.getValue().divide(sumTotalCopy, RATIO_SCALE, HALF_UP);
                        // Mod End 2018/06/20 QC#26792
                    }
                }
                // Xs Level Allocate
                // get Excess Info
                List<Map<String, Object>> excessInfoList = getExcessInfoAsc(aggrUsgRecalDtlTMsg.dsContrBllgSchdPk.getValue());
                if (excessInfoList == null || excessInfoList.size() == 0) {
                    continue;
                }

                BigDecimal chrgQty = BigDecimal.ZERO;
                BigDecimal machChrgQty = BigDecimal.ZERO;
                AGGR_USG_RECAL_XS_MTRTMsgArray aggrUsgRecalXsMtrTMsgArray = getAggrUsgRecalXsMtrForRead(aggrUsgRecalDtlPk);
                if (aggrUsgRecalXsMtrTMsgArray.getValidCount() == 0) {
                    // START 2019/01/21 M.Naito [QC#29083,MOD]
//                    for (int j = 0; j < aggTierSize; i++) {
                    for (int j = 0; j < aggTierSize; j++) {
                    // END 2019/01/21 M.Naito [QC#29083,MOD]
                        if (aggTierInfoListAsc.get(j).getBllblCopyQty().compareTo(BigDecimal.ZERO) == 0) {
                            break;
                        }
                        Map<String, Object> excessInfoMap = excessInfoList.get(j);
                        if (i != recalDtlCnt - 1) {
                            chrgQty = aggTierInfoListAsc.get(j).getBllblCopyQty().multiply(allocateRatio).setScale(LENGTH_ALLOC_BLLBL_COPY_QTY, HALF_UP);
                            allocatedBilledClick = allocatedBilledClick.add(chrgQty);
                            chrgQty = chrgQty.setScale(0, HALF_UP);
                            aggTierInfoListAsc.get(j).subtractRemainBllblCopyQty(chrgQty);
                        } else {
                            chrgQty = aggTierInfoListAsc.get(j).getBllblCopyQty().multiply(allocateRatio).setScale(LENGTH_ALLOC_BLLBL_COPY_QTY, HALF_UP);
                            allocatedBilledClick = allocatedBilledClick.add(chrgQty);
                            chrgQty = aggTierInfoListAsc.get(j).getRemainBllblCopyQty();
                        }
                        machChrgQty = machChrgQty.add(chrgQty);
                        AGGR_USG_RECAL_XS_MTRTMsg aggrUsgRecalXsMtrTMsg = new AGGR_USG_RECAL_XS_MTRTMsg();
                        BigDecimal aggrUsgRecalXsMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AGGR_USG_RECAL_XS_MTR_SQ);
                        setValue(aggrUsgRecalXsMtrTMsg.glblCmpyCd, this.glblCmpyCd);
                        setValue(aggrUsgRecalXsMtrTMsg.aggrUsgRecalXsMtrPk, aggrUsgRecalXsMtrPk);
                        setValue(aggrUsgRecalXsMtrTMsg.aggrUsgRecalDtlPk, aggrUsgRecalDtlPk);
                        setValue(aggrUsgRecalXsMtrTMsg.contrXsCopyPk, (BigDecimal) excessInfoMap.get("CONTR_XS_COPY_PK"));
                        setValue(aggrUsgRecalXsMtrTMsg.xsFromMtrCopyQty, (BigDecimal) excessInfoMap.get("XS_MTR_COPY_QTY"));
                        setValue(aggrUsgRecalXsMtrTMsg.xsMtrAmtRate, (BigDecimal) excessInfoMap.get("XS_MTR_AMT_RATE"));
                        setValue(aggrUsgRecalXsMtrTMsg.bllblCopyQty, BigDecimal.ZERO);
                        setValue(aggrUsgRecalXsMtrTMsg.allocBllblCopyQty, chrgQty);
                        setValue(aggrUsgRecalXsMtrTMsg.dealOrigPrcAmt, BigDecimal.ZERO);
                        setValue(aggrUsgRecalXsMtrTMsg.dealRecalPrcAmt, BigDecimal.ZERO);
                        setValue(aggrUsgRecalXsMtrTMsg.funcOrigPrcAmt, BigDecimal.ZERO);
                        setValue(aggrUsgRecalXsMtrTMsg.funcRecalPrcAmt, BigDecimal.ZERO);
                        setValue(aggrUsgRecalXsMtrTMsg.ccyCd, (String) excessInfoMap.get("CCY_CD"));
                        S21ApiTBLAccessor.insert(aggrUsgRecalXsMtrTMsg);
                        rtnCd = aggrUsgRecalXsMtrTMsg.getReturnCode();
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                            msgMap.addXxMsgId(NSZM0821E);
                            return;
                        }
                    }
                } else {
                    if (XS_CHRG_TP.POINT.equals((String) xsChrgTpcd)) {
                        int k =0;
                        for (AggTierInfo aggTierInfo : aggTierInfoListAsc) {
                            if (aggTierInfo.isExecChrg() == true) {
                                break;
                            }
                            k++;
                        }
                        if (i != recalDtlCnt - 1) {
                            chrgQty = aggTierInfoListAsc.get(k).getBllblCopyQty().multiply(allocateRatio).setScale(LENGTH_ALLOC_BLLBL_COPY_QTY, HALF_UP);
                            allocatedBilledClick = allocatedBilledClick.add(chrgQty);
                            chrgQty = chrgQty.setScale(0, HALF_UP);
                            aggTierInfoListAsc.get(k).subtractRemainBllblCopyQty(chrgQty);
                        } else {
                            chrgQty = aggTierInfoListAsc.get(k).getBllblCopyQty().multiply(allocateRatio).setScale(LENGTH_ALLOC_BLLBL_COPY_QTY, HALF_UP);
                            allocatedBilledClick = allocatedBilledClick.add(chrgQty);
                            chrgQty = aggTierInfoListAsc.get(k).getRemainBllblCopyQty();
                        }
                        machChrgQty = machChrgQty.add(chrgQty);
                        Map<String, Object> excessInfoMap = excessInfoList.get(k);
                        AGGR_USG_RECAL_XS_MTRTMsg aggrUsgRecalXsMtrTMsg = aggrUsgRecalXsMtrTMsgArray.no(0);
                        setValue(aggrUsgRecalXsMtrTMsg.contrXsCopyPk, (BigDecimal) excessInfoMap.get("CONTR_XS_COPY_PK"));
                        setValue(aggrUsgRecalXsMtrTMsg.xsFromMtrCopyQty, (BigDecimal) excessInfoMap.get("XS_MTR_COPY_QTY"));
                        setValue(aggrUsgRecalXsMtrTMsg.xsMtrAmtRate, (BigDecimal) excessInfoMap.get("XS_MTR_AMT_RATE"));
                        setValue(aggrUsgRecalXsMtrTMsg.allocBllblCopyQty, chrgQty);
                        setValue(aggrUsgRecalXsMtrTMsg.dealRecalPrcAmt, BigDecimal.ZERO);
                        setValue(aggrUsgRecalXsMtrTMsg.funcRecalPrcAmt, BigDecimal.ZERO);
                        S21ApiTBLAccessor.update(aggrUsgRecalXsMtrTMsg);
                        rtnCd = aggrUsgRecalXsMtrTMsg.getReturnCode();
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                            msgMap.addXxMsgId(NSZM0822E);
                            return;
                        }
                    } else {
                        for (int j = 0; j < aggTierSize; j++) {
                            // update AGGR_USG_RECAL_XS_MTR
                            if (i != recalDtlCnt - 1) {
                                chrgQty = aggTierInfoListAsc.get(j).getBllblCopyQty().multiply(allocateRatio).setScale(LENGTH_ALLOC_BLLBL_COPY_QTY, HALF_UP);
                                allocatedBilledClick = allocatedBilledClick.add(chrgQty);
                                chrgQty = chrgQty.setScale(0, HALF_UP);
                                aggTierInfoListAsc.get(j).subtractRemainBllblCopyQty(chrgQty);
                            } else {
                                chrgQty = aggTierInfoListAsc.get(j).getBllblCopyQty().multiply(allocateRatio).setScale(LENGTH_ALLOC_BLLBL_COPY_QTY, HALF_UP);
                                allocatedBilledClick = allocatedBilledClick.add(chrgQty);
                                chrgQty = aggTierInfoListAsc.get(j).getRemainBllblCopyQty();
                            }
                            machChrgQty = machChrgQty.add(chrgQty);
                            Map<String, Object> excessInfoMap = excessInfoList.get(j);
                            if (j < aggrUsgRecalXsMtrTMsgArray.getValidCount()) {
                                AGGR_USG_RECAL_XS_MTRTMsg aggrUsgRecalXsMtrTMsg = aggrUsgRecalXsMtrTMsgArray.no(j);
                                setValue(aggrUsgRecalXsMtrTMsg.contrXsCopyPk, (BigDecimal) excessInfoMap.get("CONTR_XS_COPY_PK"));
                                setValue(aggrUsgRecalXsMtrTMsg.xsFromMtrCopyQty, (BigDecimal) excessInfoMap.get("XS_MTR_COPY_QTY"));
                                setValue(aggrUsgRecalXsMtrTMsg.xsMtrAmtRate, (BigDecimal) excessInfoMap.get("XS_MTR_AMT_RATE"));
                                setValue(aggrUsgRecalXsMtrTMsg.allocBllblCopyQty, chrgQty);
                                setValue(aggrUsgRecalXsMtrTMsg.dealRecalPrcAmt, BigDecimal.ZERO);
                                setValue(aggrUsgRecalXsMtrTMsg.funcRecalPrcAmt, BigDecimal.ZERO);
                                S21ApiTBLAccessor.update(aggrUsgRecalXsMtrTMsg);
                                rtnCd = aggrUsgRecalXsMtrTMsg.getReturnCode();
                                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                                    msgMap.addXxMsgId(NSZM0822E);
                                    return;
                                }
                            } else if (aggTierInfoListAsc.get(j).isExecChrg() == true) {
                                AGGR_USG_RECAL_XS_MTRTMsg aggrUsgRecalXsMtrTMsg = new AGGR_USG_RECAL_XS_MTRTMsg();
                                BigDecimal aggrUsgRecalXsMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AGGR_USG_RECAL_XS_MTR_SQ);
                                setValue(aggrUsgRecalXsMtrTMsg.glblCmpyCd, this.glblCmpyCd);
                                setValue(aggrUsgRecalXsMtrTMsg.aggrUsgRecalXsMtrPk, aggrUsgRecalXsMtrPk);
                                setValue(aggrUsgRecalXsMtrTMsg.aggrUsgRecalDtlPk, aggrUsgRecalDtlPk);
                                setValue(aggrUsgRecalXsMtrTMsg.contrXsCopyPk, (BigDecimal) excessInfoMap.get("CONTR_XS_COPY_PK"));
                                setValue(aggrUsgRecalXsMtrTMsg.xsFromMtrCopyQty, (BigDecimal) excessInfoMap.get("XS_MTR_COPY_QTY"));
                                setValue(aggrUsgRecalXsMtrTMsg.xsMtrAmtRate, (BigDecimal) excessInfoMap.get("XS_MTR_AMT_RATE"));
                                setValue(aggrUsgRecalXsMtrTMsg.bllblCopyQty, BigDecimal.ZERO);
                                setValue(aggrUsgRecalXsMtrTMsg.allocBllblCopyQty, chrgQty);
                                setValue(aggrUsgRecalXsMtrTMsg.dealOrigPrcAmt, BigDecimal.ZERO);
                                setValue(aggrUsgRecalXsMtrTMsg.dealRecalPrcAmt, BigDecimal.ZERO);
                                setValue(aggrUsgRecalXsMtrTMsg.funcOrigPrcAmt, BigDecimal.ZERO);
                                setValue(aggrUsgRecalXsMtrTMsg.funcRecalPrcAmt, BigDecimal.ZERO);
                                setValue(aggrUsgRecalXsMtrTMsg.ccyCd, (String) excessInfoMap.get("CCY_CD"));
                                S21ApiTBLAccessor.insert(aggrUsgRecalXsMtrTMsg);
                                rtnCd = aggrUsgRecalXsMtrTMsg.getReturnCode();
                                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                                    msgMap.addXxMsgId(NSZM0821E);
                                    return;
                                }
                            }
                        }
                    }
                }
                roundedBilledClick = machChrgQty;
                sumRoundedBilledClick = sumRoundedBilledClick.add(roundedBilledClick);
                sumAllocatedBilledClick = sumAllocatedBilledClick.add(allocatedBilledClick);

                // update AGGR_USG_RECAL_DTL
                // START 2018/02/26 U.Kim [QC#23618, MOD]
                // setValue(aggrUsgRecalDtlTMsg.rndBllblCopyQty, roundedBilledClick);
                if (roundedBilledClick.compareTo(BigDecimal.ZERO) < 0) {
                    setValue(aggrUsgRecalDtlTMsg.rndBllblCopyQty, BigDecimal.ZERO);
                } else {
                    setValue(aggrUsgRecalDtlTMsg.rndBllblCopyQty, roundedBilledClick);
                }
                // END 2018/02/26 U.Kim [QC#23618, MOD]
                BigDecimal aggrAdjCopyQty = roundedBilledClick.subtract(aggrUsgRecalDtlTMsg.bllblCopyQty.getValue()).setScale(0, HALF_UP);
                setValue(aggrUsgRecalDtlTMsg.aggrAdjCopyQty, aggrAdjCopyQty);
                setValue(aggrUsgRecalDtlTMsg.allocBllblCopyQty, allocatedBilledClick);
                S21ApiTBLAccessor.update(aggrUsgRecalDtlTMsg);
                rtnCd = aggrUsgRecalDtlTMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    msgMap.addXxMsgId(NSZM0820E);
                    return;
                }
            }
            // Mod End   05/15/2018 <QC#23541>

            // Del Start 05/15/2018 <QC#23541>
            // adjust
//            BigDecimal adjustedBilledClick = sumAllocatedBilledClick.subtract(sumRoundedBilledClick).setScale(0, HALF_UP);
//            if (adjustedBilledClick.compareTo(BigDecimal.ZERO) != 0) {
//                aggrUsgRecalDtlTMsgArray = getAggrUsgRecalDtlForAdjust(aggrUsgRecalPk, bllgMtrLbCd);
//                if (aggrUsgRecalDtlTMsgArray.getValidCount() > 0) {
//                    AGGR_USG_RECAL_DTLTMsg aggrUsgRecalDtlTMsg = aggrUsgRecalDtlTMsgArray.no(0);
//                    BigDecimal aggrAdjCopyQty = aggrUsgRecalDtlTMsg.rndBllblCopyQty.getValue().add(adjustedBilledClick);
//
//                    // update AGGR_USG_RECAL_DTL
//                    setValue(aggrUsgRecalDtlTMsg.aggrAdjCopyQty, aggrAdjCopyQty);
//                    S21ApiTBLAccessor.update(aggrUsgRecalDtlTMsg);
//                    rtnCd = aggrUsgRecalDtlTMsg.getReturnCode();
//                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
//                        msgMap.addXxMsgId(NSZM0820E);
//                        return;
//                    }
//                }
//            }
            // Del End   05/15/2018 <QC#23541>

            // Mod Start 05/15/2018 <QC#23541>
            // update AGGR_USG_RECAL_XS_MTR
            aggrUsgRecalDtlTMsgArray = getAggrUsgRecalDtlForAlloc(aggrUsgRecalPk, bllgMtrLbCd);
            BigDecimal remainAggAmount = AggAmount;
            for (int i = 0; i < aggrUsgRecalDtlTMsgArray.getValidCount(); i++) {
                AGGR_USG_RECAL_DTLTMsg aggrUsgRecalDtlTMsg = aggrUsgRecalDtlTMsgArray.no(i);
                BigDecimal aggrUsgRecalDtlPk = nullToZero(aggrUsgRecalDtlTMsg.aggrUsgRecalDtlPk.getValue());
                BigDecimal aggrAdjCopyQty = nullToZero(aggrUsgRecalDtlTMsg.aggrAdjCopyQty.getValue());
                BigDecimal copyAlwncMtrCnt = nullToZero(aggrUsgRecalDtlTMsg.copyAlwncMtrCnt.getValue());
                BigDecimal remainCopyQty = aggrAdjCopyQty.add(copyAlwncMtrCnt);
                BigDecimal sumDealAmt = BigDecimal.ZERO;
                BigDecimal sumFuncAmt = BigDecimal.ZERO;

                // get Excess Info
                List<Map<String, Object>> excessInfoList = getExcessInfo(aggrUsgRecalDtlTMsg.dsContrBllgSchdPk.getValue());
                if (excessInfoList == null || excessInfoList.size() == 0) {
                    continue;
                }

                BigDecimal allocRatio = BigDecimal.ZERO;
                if (!ZYPConstant.FLG_ON_Y.equals(prcAllocByMachQtyFlg)) {
                    if (BigDecimal.ZERO.compareTo(sumBillableCopy) == 0) {
                        allocRatio = BigDecimal.ZERO;
                    } else {
                        // Mod Start 2018/06/20 QC#26792
                        allocRatio = aggrUsgRecalDtlTMsg.bllblCopyQty.getValue().divide(sumBillableCopy, RATIO_SCALE, HALF_UP);
                        // Mod End 2018/06/20 QC#26792
                    }
                } else {
                    if (BigDecimal.ZERO.compareTo(sumTotalCopy) == 0) {
                        allocRatio = BigDecimal.ZERO;
                    } else {
                        // Mod Start 2018/06/20 QC#26792
                        allocRatio = aggrUsgRecalDtlTMsg.totCopyQty.getValue().divide(sumTotalCopy, RATIO_SCALE, HALF_UP);
                        // Mod End 2018/06/20 QC#26792
                    }
                }
                
                if (isMinAmout == true) {
                    String ccyCd = (String) excessInfoList.get(0).get("CCY_CD");
                    BigDecimal xsMtrCopyQty = nullToZero((BigDecimal) excessInfoList.get(0).get("XS_MTR_COPY_QTY"));
                    BigDecimal dealAmt = BigDecimal.ZERO;
                    if (i != recalDtlCnt - 1) {
                        dealAmt = AggAmount.multiply(allocRatio).setScale(ccyScale, HALF_UP);
                        remainAggAmount = remainAggAmount.subtract(dealAmt).setScale(ccyScale, HALF_UP);
                    } else {
                        dealAmt = remainAggAmount;
                    }
                    BigDecimal funcAmt =  NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, ccyCd, pMsg.slsDt.getValue(), dealAmt);
                    sumDealAmt = sumDealAmt.add(dealAmt);
                    sumFuncAmt = sumDealAmt.add(funcAmt);
                    AGGR_USG_RECAL_XS_MTRTMsgArray aggrUsgRecalXsMtrTMsgArray = getAggrUsgRecalXsMtrForCharge(aggrUsgRecalDtlPk, xsMtrCopyQty);
                    if (aggrUsgRecalXsMtrTMsgArray.getValidCount() != 0) {
                        // update AGGR_USG_RECAL_XS_MTR
                        AGGR_USG_RECAL_XS_MTRTMsg aggrUsgRecalXsMtrTMsg = aggrUsgRecalXsMtrTMsgArray.no(0);
                        setValue(aggrUsgRecalXsMtrTMsg.dealRecalPrcAmt, dealAmt);
                        setValue(aggrUsgRecalXsMtrTMsg.funcRecalPrcAmt, funcAmt);
                        S21ApiTBLAccessor.update(aggrUsgRecalXsMtrTMsg);
                        rtnCd = aggrUsgRecalXsMtrTMsg.getReturnCode();
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                            msgMap.addXxMsgId(NSZM0822E);
                            return;
                        }
                    }
                } else {
                    // Mod Start 05/31/2018 <QC#23541>
                    int tierCnt = 0;
                    for (Map<String, Object> excessInfoMap : excessInfoList) {
                        String ccyCd = (String) excessInfoMap.get("CCY_CD");
                        BigDecimal xsMtrCopyQty = nullToZero((BigDecimal) excessInfoMap.get("XS_MTR_COPY_QTY"));
                        BigDecimal xsMtrAmtRate = nullToZero((BigDecimal) excessInfoMap.get("XS_MTR_AMT_RATE"));
                        BigDecimal chrgQty = BigDecimal.ZERO;
                        BigDecimal dealAmt = BigDecimal.ZERO;
                        BigDecimal funcAmt = BigDecimal.ZERO;
                        int dealScale = NSXC003001Exchange.getDealCcyDigit(this.glblCmpyCd, ccyCd);

                        AGGR_USG_RECAL_XS_MTRTMsgArray aggrUsgRecalXsMtrTMsgArray = getAggrUsgRecalXsMtrForCharge(aggrUsgRecalDtlPk, xsMtrCopyQty);
                        if (aggrUsgRecalXsMtrTMsgArray.getValidCount() != 0) {
                            // update AGGR_USG_RECAL_XS_MTR
                            AGGR_USG_RECAL_XS_MTRTMsg aggrUsgRecalXsMtrTMsg = aggrUsgRecalXsMtrTMsgArray.no(0);
                            chrgQty = aggrUsgRecalXsMtrTMsg.allocBllblCopyQty.getValue();
                            if (i != recalDtlCnt - 1) {
                                dealAmt = chrgQty.multiply(xsMtrAmtRate).setScale(dealScale, HALF_UP);
                                aggTierInfoList.get(tierCnt).subtractRemainChrgAmt(dealAmt);
                               
                            } else {
                                dealAmt = aggTierInfoList.get(tierCnt).getRemainChrgAmt();
                                aggTierInfoList.get(tierCnt).setRemainChrgAmt(BigDecimal.ZERO);
                            }
                            if (dealAmt.compareTo(BigDecimal.ZERO) != 0) {
                                funcAmt = NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, ccyCd, pMsg.slsDt.getValue(), dealAmt);
                            }
                            sumDealAmt = sumDealAmt.add(dealAmt);
                            sumFuncAmt = sumFuncAmt.add(funcAmt);
                            setValue(aggrUsgRecalXsMtrTMsg.allocBllblCopyQty, chrgQty);
                            setValue(aggrUsgRecalXsMtrTMsg.dealRecalPrcAmt, dealAmt);
                            setValue(aggrUsgRecalXsMtrTMsg.funcRecalPrcAmt, funcAmt);
                            S21ApiTBLAccessor.update(aggrUsgRecalXsMtrTMsg);
                            rtnCd = aggrUsgRecalXsMtrTMsg.getReturnCode();
                            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                                msgMap.addXxMsgId(NSZM0822E);
                                return;
                            }
                        }
                        tierCnt++;
                    }
                    // Mod End   05/31/2018 <QC#23541>
                }
                // Mod End 05/15/2018 <QC#23541>

                // update AGGR_USG_RECAL_DTL
                setValue(aggrUsgRecalDtlTMsg.mtrChrgDealAmt, sumDealAmt);
                setValue(aggrUsgRecalDtlTMsg.mtrChrgFuncAmt, sumFuncAmt);
                S21ApiTBLAccessor.update(aggrUsgRecalDtlTMsg);
                rtnCd = aggrUsgRecalDtlTMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    msgMap.addXxMsgId(NSZM0820E);
                    return;
                }
            }
        }
    }

    private void insertAggrUsgRecalDtl(S21ApiMessageMap msgMap, Map<String, Object> recalcInfoMap, BigDecimal aggrUsgRecalPk, BigDecimal aggrUsgRecalDtlPk, String bllgMtrLbCd) {

        // insert AGGR_USG_RECAL_DTL
        AGGR_USG_RECAL_DTLTMsg aggrUsgRecalDtlTMsg = new AGGR_USG_RECAL_DTLTMsg();
        setValue(aggrUsgRecalDtlTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(aggrUsgRecalDtlTMsg.aggrUsgRecalDtlPk, aggrUsgRecalDtlPk);
        setValue(aggrUsgRecalDtlTMsg.aggrUsgRecalPk, aggrUsgRecalPk);
        setValue(aggrUsgRecalDtlTMsg.svcMachMstrPk, (BigDecimal) recalcInfoMap.get("SVC_MACH_MSTR_PK"));
        setValue(aggrUsgRecalDtlTMsg.serNum, (String) recalcInfoMap.get("SER_NUM"));
        setValue(aggrUsgRecalDtlTMsg.dsContrBllgSchdPk, (BigDecimal) recalcInfoMap.get("DS_CONTR_BLLG_SCHD_PK"));
        setValue(aggrUsgRecalDtlTMsg.bllgFromDt, (String) recalcInfoMap.get("MTR_BLLG_FROM_DT"));
        setValue(aggrUsgRecalDtlTMsg.bllgThruDt, (String) recalcInfoMap.get("MTR_BLLG_THRU_DT"));
        setValue(aggrUsgRecalDtlTMsg.dsContrBllgMtrPk, (BigDecimal) recalcInfoMap.get("DS_CONTR_BLLG_MTR_PK"));
        setValue(aggrUsgRecalDtlTMsg.bllgMtrLbCd, bllgMtrLbCd);
        setValue(aggrUsgRecalDtlTMsg.startReadMtrCnt, (BigDecimal) recalcInfoMap.get("PREV_TOT_COPY_QTY"));
        setValue(aggrUsgRecalDtlTMsg.curReadMtrCnt, (BigDecimal) recalcInfoMap.get("TOT_COPY_QTY"));
        setValue(aggrUsgRecalDtlTMsg.copyAlwncMtrCnt, (BigDecimal) recalcInfoMap.get("COPY_INCL_QTY"));
        // Mod Start 2016/10/31 <QC#15597>
        BigDecimal mtrCopyQty = (BigDecimal) recalcInfoMap.get("MTR_COPY_QTY");
        BigDecimal testCopyQty = (BigDecimal) recalcInfoMap.get("TEST_COPY_QTY");
        setValue(aggrUsgRecalDtlTMsg.actlCopyQty, mtrCopyQty.subtract(testCopyQty));
        setValue(aggrUsgRecalDtlTMsg.totCopyQty, mtrCopyQty.subtract(testCopyQty));
        // Mod End   2016/10/31 <QC#15597>
        setValue(aggrUsgRecalDtlTMsg.bllblCopyQty, (BigDecimal) recalcInfoMap.get("BLLG_COPY_QTY"));
        setValue(aggrUsgRecalDtlTMsg.aggrAdjCopyQty, BigDecimal.ZERO);
        setValue(aggrUsgRecalDtlTMsg.mtrChrgDealAmt, BigDecimal.ZERO);
        setValue(aggrUsgRecalDtlTMsg.mtrChrgFuncAmt, BigDecimal.ZERO);
        S21ApiTBLAccessor.insert(aggrUsgRecalDtlTMsg);
        String rtnCd = aggrUsgRecalDtlTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0819E);
            return;
        }
    }

    private void insertAggrUsgRecalXsMtr(S21ApiMessageMap msgMap, SVC_CONTR_XS_MTR_BLLGTMsg svcContrXsMtrBllgTMsg, BigDecimal aggrUsgRecalDtlPk, BigDecimal aggrUsgRecalXsMtrPk) {
        // insert AGGR_USG_RECAL_XS_MTR
        AGGR_USG_RECAL_XS_MTRTMsg aggrUsgRecalXsMtrTMsg = new AGGR_USG_RECAL_XS_MTRTMsg();
        setValue(aggrUsgRecalXsMtrTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(aggrUsgRecalXsMtrTMsg.aggrUsgRecalXsMtrPk, aggrUsgRecalXsMtrPk);
        setValue(aggrUsgRecalXsMtrTMsg.aggrUsgRecalDtlPk, aggrUsgRecalDtlPk);
        setValue(aggrUsgRecalXsMtrTMsg.contrXsCopyPk, svcContrXsMtrBllgTMsg.contrXsCopyPk);
        setValue(aggrUsgRecalXsMtrTMsg.xsFromMtrCopyQty, svcContrXsMtrBllgTMsg.xsMtrFromCopyQty);
        setValue(aggrUsgRecalXsMtrTMsg.xsMtrAmtRate, svcContrXsMtrBllgTMsg.xsMtrAmtRate);
        setValue(aggrUsgRecalXsMtrTMsg.bllblCopyQty, svcContrXsMtrBllgTMsg.xsMtrCopyQty);
        setValue(aggrUsgRecalXsMtrTMsg.dealOrigPrcAmt, svcContrXsMtrBllgTMsg.xsMtrChrgDealAmt);
        setValue(aggrUsgRecalXsMtrTMsg.funcOrigPrcAmt, svcContrXsMtrBllgTMsg.xsMtrChrgFuncAmt);
        setValue(aggrUsgRecalXsMtrTMsg.ccyCd, svcContrXsMtrBllgTMsg.ccyCd);
        S21ApiTBLAccessor.insert(aggrUsgRecalXsMtrTMsg);
        String rtnCd = aggrUsgRecalXsMtrTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0821E);
            return;
        }
    }

    private void updateSvcContrBllg(S21ApiMessageMap msgMap, BigDecimal aggrUsgRecalPk) {
        NSZC034001PMsg pMsg = (NSZC034001PMsg) msgMap.getPmsg();

        List<Map<String, Object>> svcContrBllgInfoList = getSvcContrBllgInfo(aggrUsgRecalPk);
        for (Map<String, Object> svcContrBllgInfoMap : svcContrBllgInfoList) {

            // call Tax Calculation API
            CallTaxCalcAPIForUsageBean apiBean = new CallTaxCalcAPIForUsageBean();
            // START 2016/03/10 T.Kanasaka [QC5207, MOD]
            setTaxCalcParam(msgMap, svcContrBllgInfoMap, apiBean, null);
            // END 2016/03/10 T.Kanasaka [QC5207, MOD]
            NWZC036101PMsg taxApiPMsg = NSXC003001CallTaxCalcAPIForUsage.callTaxCalcApi(apiBean, this.onBatTp);

            // err check
            List<String> errList = S21ApiUtil.getXxMsgIdList(taxApiPMsg);
            if (!errList.isEmpty()) {
                for (String xxMsgId : errList) {
                    msgMap.addXxMsgId(xxMsgId);
                }
                return;
            }

            String ccyCd = (String) svcContrBllgInfoMap.get("CCY_CD");
//            BigDecimal stateTaxRate = nullToZero(taxApiPMsg.taxCalculateOutputLine.no(0).taxPct_01.getValue());
//            BigDecimal countyTaxRate = nullToZero(taxApiPMsg.taxCalculateOutputLine.no(0).taxPct_02.getValue());
//            BigDecimal cityTaxRate = nullToZero(taxApiPMsg.taxCalculateOutputLine.no(0).taxPct_03.getValue());
            BigDecimal funcStateTaxAmt = nullToZero(taxApiPMsg.taxCalculateOutputLine.no(0).taxAmt_01.getValue());
            BigDecimal funcCountyTaxAmt = nullToZero(taxApiPMsg.taxCalculateOutputLine.no(0).taxAmt_02.getValue());
            BigDecimal funcCityTaxAmt = nullToZero(taxApiPMsg.taxCalculateOutputLine.no(0).taxAmt_03.getValue());
            BigDecimal dealStateTaxAmt = NSXC003001Exchange.calcDealFromFunc(this.glblCmpyCd, ccyCd, pMsg.slsDt.getValue(), funcStateTaxAmt);
            BigDecimal dealCountyTaxAmt = NSXC003001Exchange.calcDealFromFunc(this.glblCmpyCd, ccyCd, pMsg.slsDt.getValue(), funcCountyTaxAmt);
            BigDecimal dealCityTaxAmt = NSXC003001Exchange.calcDealFromFunc(this.glblCmpyCd, ccyCd, pMsg.slsDt.getValue(), funcCityTaxAmt);
            BigDecimal slsTaxRate = nullToZero(taxApiPMsg.taxCalculateOutputLine.no(0).xxTaxCalcLineTaxPct.getValue());
//            String taxAreaId = taxApiPMsg.taxCalculateOutputLine.no(0).taxAreaId.getValue();
            BigDecimal totalFuncTaxAmt = funcStateTaxAmt.add(funcCountyTaxAmt.add(funcCityTaxAmt));
            BigDecimal totalDealTaxAmt = dealStateTaxAmt.add(dealCountyTaxAmt.add(dealCityTaxAmt));

            // get SVC_CONTR_MTR_BLLG
            SVC_CONTR_MTR_BLLGTMsg svcContrMtrBllgTMsgParam = new SVC_CONTR_MTR_BLLGTMsg();
            setValue(svcContrMtrBllgTMsgParam.glblCmpyCd, this.glblCmpyCd);
            setValue(svcContrMtrBllgTMsgParam.svcContrMtrBllgPk, (BigDecimal) svcContrBllgInfoMap.get("SVC_CONTR_MTR_BLLG_PK"));
            SVC_CONTR_MTR_BLLGTMsg svcContrMtrBllgTMsg = (SVC_CONTR_MTR_BLLGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(svcContrMtrBllgTMsgParam);
            if (svcContrMtrBllgTMsg == null) {
                msgMap.addXxMsgId(NSZM0825E);
                return;
            }

            // update SVC_CONTR_MTR_BLLG
            setValue(svcContrMtrBllgTMsg.mtrChrgDealAmt, (BigDecimal) svcContrBllgInfoMap.get("MTR_CHRG_DEAL_AMT"));
            setValue(svcContrMtrBllgTMsg.mtrChrgFuncAmt, (BigDecimal) svcContrBllgInfoMap.get("MTR_CHRG_FUNC_AMT"));
            setValue(svcContrMtrBllgTMsg.slsTaxRate, slsTaxRate);
            setValue(svcContrMtrBllgTMsg.dealTaxAmt, totalDealTaxAmt);
            setValue(svcContrMtrBllgTMsg.funcTaxAmt, totalFuncTaxAmt);
            setValue(svcContrMtrBllgTMsg.bllgCopyQty, (BigDecimal) svcContrBllgInfoMap.get("RND_BLLBL_COPY_QTY"));
            setValue(svcContrMtrBllgTMsg.aggrAdjCopyQty, (BigDecimal) svcContrBllgInfoMap.get("AGGR_ADJ_COPY_QTY"));
            S21ApiTBLAccessor.update(svcContrMtrBllgTMsg);
            String rtnCd = svcContrMtrBllgTMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                msgMap.addXxMsgId(NSZM0826E);
                return;
            }

            // get DS_CONTR_BLLG_SCHD
            DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsgParam = new DS_CONTR_BLLG_SCHDTMsg();
            setValue(dsContrBllgSchdTMsgParam.glblCmpyCd, this.glblCmpyCd);
            setValue(dsContrBllgSchdTMsgParam.dsContrBllgSchdPk, (BigDecimal) svcContrBllgInfoMap.get("DS_CONTR_BLLG_SCHD_PK"));
            DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg = (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrBllgSchdTMsgParam);
            if (dsContrBllgSchdTMsg == null) {
                msgMap.addXxMsgId(NSZM0827E);
                return;
            }

            // update DS_CONTR_BLLG_SCHD
            setValue(dsContrBllgSchdTMsg.mtrChrgDealAmt, (BigDecimal) svcContrBllgInfoMap.get("MTR_CHRG_DEAL_AMT"));
            setValue(dsContrBllgSchdTMsg.mtrChrgFuncAmt, (BigDecimal) svcContrBllgInfoMap.get("MTR_CHRG_FUNC_AMT"));
            setValue(dsContrBllgSchdTMsg.slsTaxRate, slsTaxRate);
            setValue(dsContrBllgSchdTMsg.dealTaxAmt, totalDealTaxAmt);
            setValue(dsContrBllgSchdTMsg.funcTaxAmt, totalFuncTaxAmt);
            setValue(dsContrBllgSchdTMsg.bllgMtrCnt, (BigDecimal) svcContrBllgInfoMap.get("RND_BLLBL_COPY_QTY"));
            // START 2016/03/15 T.Kanasaka [QC5475, MOD]
            setValue(dsContrBllgSchdTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.BILLED);
            // END 2016/03/15 T.Kanasaka [QC5475, MOD]
            S21ApiTBLAccessor.update(dsContrBllgSchdTMsg);
            rtnCd = dsContrBllgSchdTMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                msgMap.addXxMsgId(NSZM0828E);
                return;
            }

            // get SVC_CONTR_XS_MTR_BLLG
            List<SVC_CONTR_XS_MTR_BLLGTMsg> delSvcContrXsMtrBllgList = new ArrayList<SVC_CONTR_XS_MTR_BLLGTMsg>();
            SVC_CONTR_XS_MTR_BLLGTMsgArray svcContrXsMtrBllgTMsgArray = getSvcContrXsMtrBllg((BigDecimal) svcContrBllgInfoMap.get("SVC_CONTR_MTR_BLLG_PK"));
            for (int i = 0; i < svcContrXsMtrBllgTMsgArray.getValidCount(); i++) {
                SVC_CONTR_XS_MTR_BLLGTMsg svcContrXsMtrBllgTMsg = svcContrXsMtrBllgTMsgArray.no(i);
                delSvcContrXsMtrBllgList.add(svcContrXsMtrBllgTMsg);
            }

            // remove SVC_CONTR_XS_MTR_BLLG
            if (delSvcContrXsMtrBllgList.size() > 0) {
                S21ApiTBLAccessor.remove(delSvcContrXsMtrBllgList.toArray(new SVC_CONTR_XS_MTR_BLLGTMsg[delSvcContrXsMtrBllgList.size()]));
            }

            // get AGGR_USG_RECAL_XS_MTR
            AGGR_USG_RECAL_XS_MTRTMsgArray aggrUsgRecalXsMtrTMsgArray = getAggrUsgRecalXsMtrForRead((BigDecimal) svcContrBllgInfoMap.get("AGGR_USG_RECAL_DTL_PK"));
            for (int i = 0; i < aggrUsgRecalXsMtrTMsgArray.getValidCount(); i++) {
                AGGR_USG_RECAL_XS_MTRTMsg aggrUsgRecalXsMtrTMsg = aggrUsgRecalXsMtrTMsgArray.no(i);

                // insert SVC_CONTR_XS_MTR_BLLG
                BigDecimal svcContrXsMtrBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_XS_MTR_BLLG_SQ);
                SVC_CONTR_XS_MTR_BLLGTMsg svcContrXsMtrBllgTMsg = new SVC_CONTR_XS_MTR_BLLGTMsg();
                setValue(svcContrXsMtrBllgTMsg.glblCmpyCd, this.glblCmpyCd);
                setValue(svcContrXsMtrBllgTMsg.svcContrXsMtrBllgPk, svcContrXsMtrBllgPk);
                setValue(svcContrXsMtrBllgTMsg.svcContrMtrBllgPk, (BigDecimal) svcContrBllgInfoMap.get("SVC_CONTR_MTR_BLLG_PK"));
                setValue(svcContrXsMtrBllgTMsg.svcContrBllgPk, (BigDecimal) svcContrBllgInfoMap.get("SVC_CONTR_BLLG_PK"));
                setValue(svcContrXsMtrBllgTMsg.dsContrDtlPk, (BigDecimal) svcContrBllgInfoMap.get("DS_CONTR_DTL_PK"));
                setValue(svcContrXsMtrBllgTMsg.dsContrBllgMtrPk, (BigDecimal) svcContrBllgInfoMap.get("DS_CONTR_BLLG_MTR_PK"));
                setValue(svcContrXsMtrBllgTMsg.ccyCd, (String) svcContrBllgInfoMap.get("CCY_CD"));
                setValue(svcContrXsMtrBllgTMsg.contrXsCopyPk, aggrUsgRecalXsMtrTMsg.contrXsCopyPk);
                setValue(svcContrXsMtrBllgTMsg.xsMtrCopyQty, nullToZero(aggrUsgRecalXsMtrTMsg.allocBllblCopyQty.getValue()).setScale(0, HALF_UP));
                setValue(svcContrXsMtrBllgTMsg.xsMtrChrgDealAmt, aggrUsgRecalXsMtrTMsg.dealRecalPrcAmt);
                setValue(svcContrXsMtrBllgTMsg.xsMtrChrgFuncAmt, aggrUsgRecalXsMtrTMsg.funcRecalPrcAmt);
                setValue(svcContrXsMtrBllgTMsg.xsMtrChrgDiscDealAmt, BigDecimal.ZERO);
                setValue(svcContrXsMtrBllgTMsg.xsMtrChrgDiscFuncAmt, BigDecimal.ZERO);
                setValue(svcContrXsMtrBllgTMsg.xsMtrAmtRate, aggrUsgRecalXsMtrTMsg.xsMtrAmtRate);
                setValue(svcContrXsMtrBllgTMsg.xsMtrFromCopyQty, aggrUsgRecalXsMtrTMsg.xsFromMtrCopyQty);
                S21ApiTBLAccessor.insert(svcContrXsMtrBllgTMsg);
                rtnCd = svcContrXsMtrBllgTMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    msgMap.addXxMsgId(NSZM0823E);
                    return;
                }
            }

            // get SVC_CONTR_BLLG_ALLOC
            int adjIndex = -1;
            BigDecimal maxDealGrsUnitPrcAmt = BigDecimal.ZERO;
            BigDecimal sumDealGrsUnitPrcAmt = BigDecimal.ZERO;
            BigDecimal sumFuncGrsUnitPrcAmt = BigDecimal.ZERO;
            SVC_CONTR_BLLG_ALLOCTMsgArray svcContrBllgAllocTMsgArray = getSvcContrBllgAlloc(svcContrMtrBllgTMsg.svcContrMtrBllgPk.getValue());
            for (int i = 0; i < svcContrBllgAllocTMsgArray.getValidCount(); i++) {
                SVC_CONTR_BLLG_ALLOCTMsg svcContrBllgAllocTMsg = svcContrBllgAllocTMsgArray.no(i);

                int dealScale = NSXC003001Exchange.getDealCcyDigit(this.glblCmpyCd, svcContrBllgAllocTMsg.ccyCd.getValue());
                BigDecimal slsAllocRate = nullToZero(svcContrBllgAllocTMsg.slsAllocRate.getValue());
                BigDecimal dealGrsUnitPrcAmt = nullToZero(svcContrMtrBllgTMsg.mtrChrgDealAmt.getValue()).multiply(slsAllocRate).divide(RATE_CHANGE, dealScale, HALF_UP);
                BigDecimal dealDiscUnitPrcAmt = BigDecimal.ZERO;
                BigDecimal funcGrsUnitPrcAmt = nullToZero(svcContrMtrBllgTMsg.mtrChrgFuncAmt.getValue()).multiply(slsAllocRate).divide(RATE_CHANGE, this.stdCcyAftDeclPntDigitNum, HALF_UP);
                BigDecimal funcDiscUnitPrcAmt = BigDecimal.ZERO;

                if (maxDealGrsUnitPrcAmt.compareTo(dealGrsUnitPrcAmt) < 0) {
                    maxDealGrsUnitPrcAmt = dealGrsUnitPrcAmt;
                    adjIndex = i;
                }
                sumDealGrsUnitPrcAmt = sumDealGrsUnitPrcAmt.add(dealGrsUnitPrcAmt);
                sumFuncGrsUnitPrcAmt = sumFuncGrsUnitPrcAmt.add(funcGrsUnitPrcAmt);

                // update SVC_CONTR_BLLG_ALLOC
                setValue(svcContrBllgAllocTMsg.dealGrsUnitPrcAmt, dealGrsUnitPrcAmt);
                setValue(svcContrBllgAllocTMsg.dealDiscUnitPrcAmt, dealDiscUnitPrcAmt);
                setValue(svcContrBllgAllocTMsg.dealNetUnitPrcAmt, dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt));
                setValue(svcContrBllgAllocTMsg.dealGrsTotPrcAmt, svcContrBllgAllocTMsg.dealNetUnitPrcAmt);
                setValue(svcContrBllgAllocTMsg.invLineDealNetAmt, svcContrBllgAllocTMsg.dealNetUnitPrcAmt);
                setValue(svcContrBllgAllocTMsg.invLineDealTaxAmt, BigDecimal.ZERO);
                setValue(svcContrBllgAllocTMsg.funcGrsUnitPrcAmt, funcGrsUnitPrcAmt);
                setValue(svcContrBllgAllocTMsg.funcDiscUnitPrcAmt, funcDiscUnitPrcAmt);
                setValue(svcContrBllgAllocTMsg.funcNetUnitPrcAmt, funcGrsUnitPrcAmt.subtract(funcDiscUnitPrcAmt));
                setValue(svcContrBllgAllocTMsg.funcGrsTotPrcAmt, svcContrBllgAllocTMsg.funcNetUnitPrcAmt);
                setValue(svcContrBllgAllocTMsg.invLineFuncNetAmt, svcContrBllgAllocTMsg.funcNetUnitPrcAmt);
                setValue(svcContrBllgAllocTMsg.invLineFuncTaxAmt, BigDecimal.ZERO);
                S21ApiTBLAccessor.update(svcContrBllgAllocTMsg);
                rtnCd = svcContrBllgAllocTMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    msgMap.addXxMsgId(NSZM0824E);
                    return;
                }
            }

            // adjust
            if (adjIndex >= 0) {
                BigDecimal mtrChrgDealAmt = nullToZero(svcContrMtrBllgTMsg.mtrChrgDealAmt.getValue());
                BigDecimal mtrChrgFuncAmt = nullToZero(svcContrMtrBllgTMsg.mtrChrgFuncAmt.getValue());
                BigDecimal adjDealAmt = mtrChrgDealAmt.subtract(sumDealGrsUnitPrcAmt);
                BigDecimal adjFuncAmt = mtrChrgFuncAmt.subtract(sumFuncGrsUnitPrcAmt);
                if (adjDealAmt.compareTo(BigDecimal.ZERO) != 0 || adjFuncAmt.compareTo(BigDecimal.ZERO) != 0) {
                    SVC_CONTR_BLLG_ALLOCTMsg svcContrBllgAllocTMsg = svcContrBllgAllocTMsgArray.no(adjIndex);

                    BigDecimal dealGrsUnitPrcAmt = svcContrBllgAllocTMsg.dealGrsUnitPrcAmt.getValue().add(adjDealAmt);
                    BigDecimal dealDiscUnitPrcAmt = BigDecimal.ZERO;
                    BigDecimal funcGrsUnitPrcAmt = svcContrBllgAllocTMsg.funcGrsUnitPrcAmt.getValue().add(adjFuncAmt);
                    BigDecimal funcDiscUnitPrcAmt = BigDecimal.ZERO;

                    // update SVC_CONTR_BLLG_ALLOC
                    setValue(svcContrBllgAllocTMsg.dealGrsUnitPrcAmt, dealGrsUnitPrcAmt);
                    setValue(svcContrBllgAllocTMsg.dealDiscUnitPrcAmt, dealDiscUnitPrcAmt);
                    setValue(svcContrBllgAllocTMsg.dealNetUnitPrcAmt, dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt));
                    setValue(svcContrBllgAllocTMsg.dealGrsTotPrcAmt, svcContrBllgAllocTMsg.dealNetUnitPrcAmt);
                    setValue(svcContrBllgAllocTMsg.invLineDealNetAmt, svcContrBllgAllocTMsg.dealNetUnitPrcAmt);
                    setValue(svcContrBllgAllocTMsg.invLineDealTaxAmt, BigDecimal.ZERO);
                    setValue(svcContrBllgAllocTMsg.funcGrsUnitPrcAmt, funcGrsUnitPrcAmt);
                    setValue(svcContrBllgAllocTMsg.funcDiscUnitPrcAmt, funcDiscUnitPrcAmt);
                    setValue(svcContrBllgAllocTMsg.funcNetUnitPrcAmt, funcGrsUnitPrcAmt.subtract(funcDiscUnitPrcAmt));
                    setValue(svcContrBllgAllocTMsg.funcGrsTotPrcAmt, svcContrBllgAllocTMsg.funcNetUnitPrcAmt);
                    setValue(svcContrBllgAllocTMsg.invLineFuncNetAmt, svcContrBllgAllocTMsg.funcNetUnitPrcAmt);
                    setValue(svcContrBllgAllocTMsg.invLineFuncTaxAmt, BigDecimal.ZERO);
                    S21ApiTBLAccessor.update(svcContrBllgAllocTMsg);
                    rtnCd = svcContrBllgAllocTMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        msgMap.addXxMsgId(NSZM0824E);
                        return;
                    }
                }
            }

            // START 2016/03/10 T.Kanasaka [QC5207, ADD]
            updateSvcContrAddlChrgBllg(msgMap, svcContrBllgInfoMap);
            // END 2016/03/10 T.Kanasaka [QC5207, ADD]
        }
    }

    // START 2016/03/10 T.Kanasaka [QC5207, ADD]
    private void updateSvcContrAddlChrgBllg(S21ApiMessageMap msgMap, Map<String, Object> svcContrBllgInfoMap) {
        NSZC034001PMsg pMsg = (NSZC034001PMsg) msgMap.getPmsg();
        BigDecimal svcContrBllgPk = (BigDecimal) svcContrBllgInfoMap.get("SVC_CONTR_BLLG_PK");
        BigDecimal mtrChrgDealAmt = (BigDecimal) svcContrBllgInfoMap.get("MTR_CHRG_DEAL_AMT");
        String ccyCd = (String) svcContrBllgInfoMap.get("CCY_CD");
        int dealScale = NSXC003001Exchange.getDealCcyDigit(this.glblCmpyCd, ccyCd);

        List<Map<String, Object>> addlChrgInfoMapList = getAddlChrgInfo(svcContrBllgPk);
        if (addlChrgInfoMapList == null || addlChrgInfoMapList.size() == 0) {
            return;
        }

        for (Map<String, Object> addlChrgInfoMap : addlChrgInfoMapList) {
            BigDecimal svcContrAddlChrgBllgPk = (BigDecimal) addlChrgInfoMap.get("SVC_CONTR_ADDL_CHRG_BLLG_PK");
            BigDecimal addlChrgAplcPct = (BigDecimal) addlChrgInfoMap.get("ADDL_CHRG_APLC_PCT");
            // START 2017/12/25 K.Kojima [QC#18562,MOD]
            // BigDecimal addlDealPrcAmt = mtrChrgDealAmt.multiply(addlChrgAplcPct).divide(RATE_CHANGE, dealScale, HALF_UP);
            CalcAddlChrgInfo calcAddlChrgInfo = new CalcAddlChrgInfo();
            calcAddlChrgInfo.setBasePrcAmt(mtrChrgDealAmt);
            calcAddlChrgInfo.setFlatRateAmt(BigDecimal.ZERO);
            calcAddlChrgInfo.setAplcPct(addlChrgAplcPct);
            calcAddlChrgInfo.setAddlChrgFromDt((String) addlChrgInfoMap.get("ADDL_CHRG_FROM_DT"));
            calcAddlChrgInfo.setAddlChrgThueDt((String) addlChrgInfoMap.get("ADDL_CHRG_THRU_DT"));
            calcAddlChrgInfo.setBllgFromDt((String) addlChrgInfoMap.get("SVC_CONTR_BLLG_FROM_DT"));
            calcAddlChrgInfo.setBllgThruDt((String) addlChrgInfoMap.get("SVC_CONTR_BLLG_THRU_DT"));
            calcAddlChrgInfo.setAftDeclPntDigitNum(new BigDecimal(dealScale));
            NSXC001001CalcAddlChrg calcAddlChrg = new NSXC001001CalcAddlChrg();
            calcAddlChrg.calcAddrChrg(calcAddlChrgInfo);
            BigDecimal addlDealPrcAmt = calcAddlChrgInfo.getAddlChrgAmt();
            // END 2017/12/25 K.Kojima [QC#18562,MOD]
            BigDecimal addlFuncPrcAmt = nullToZero(NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, ccyCd, pMsg.slsDt.getValue(), addlDealPrcAmt));

            // call Tax Calculation API
            CallTaxCalcAPIForUsageBean apiBean = new CallTaxCalcAPIForUsageBean();
            setTaxCalcParam(msgMap, svcContrBllgInfoMap, apiBean, addlFuncPrcAmt);
            NWZC036101PMsg taxApiPMsg = NSXC003001CallTaxCalcAPIForUsage.callTaxCalcApi(apiBean, this.onBatTp);

            // err check
            List<String> errList = S21ApiUtil.getXxMsgIdList(taxApiPMsg);
            if (!errList.isEmpty()) {
                for (String xxMsgId : errList) {
                    msgMap.addXxMsgId(xxMsgId);
                }
                return;
            }
            BigDecimal funcStateTaxAmt = nullToZero(taxApiPMsg.taxCalculateOutputLine.no(0).taxAmt_01.getValue());
            BigDecimal funcCountyTaxAmt = nullToZero(taxApiPMsg.taxCalculateOutputLine.no(0).taxAmt_02.getValue());
            BigDecimal funcCityTaxAmt = nullToZero(taxApiPMsg.taxCalculateOutputLine.no(0).taxAmt_03.getValue());
            BigDecimal dealStateTaxAmt = NSXC003001Exchange.calcDealFromFunc(this.glblCmpyCd, ccyCd, pMsg.slsDt.getValue(), funcStateTaxAmt);
            BigDecimal dealCountyTaxAmt = NSXC003001Exchange.calcDealFromFunc(this.glblCmpyCd, ccyCd, pMsg.slsDt.getValue(), funcCountyTaxAmt);
            BigDecimal dealCityTaxAmt = NSXC003001Exchange.calcDealFromFunc(this.glblCmpyCd, ccyCd, pMsg.slsDt.getValue(), funcCityTaxAmt);
            BigDecimal slsTaxRate = nullToZero(taxApiPMsg.taxCalculateOutputLine.no(0).xxTaxCalcLineTaxPct.getValue());
            BigDecimal totalFuncTaxAmt = funcStateTaxAmt.add(funcCountyTaxAmt.add(funcCityTaxAmt));
            BigDecimal totalDealTaxAmt = dealStateTaxAmt.add(dealCountyTaxAmt.add(dealCityTaxAmt));

            // get SVC_CONTR_ADDL_CHRG_BLLG
            SVC_CONTR_ADDL_CHRG_BLLGTMsg svcContrAddlChrgBllgTMsgParam = new SVC_CONTR_ADDL_CHRG_BLLGTMsg();
            setValue(svcContrAddlChrgBllgTMsgParam.glblCmpyCd, this.glblCmpyCd);
            setValue(svcContrAddlChrgBllgTMsgParam.svcContrAddlChrgBllgPk, svcContrAddlChrgBllgPk);
            SVC_CONTR_ADDL_CHRG_BLLGTMsg svcContrAddlChrgBllgTMsg = (SVC_CONTR_ADDL_CHRG_BLLGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(svcContrAddlChrgBllgTMsgParam);
            if (svcContrAddlChrgBllgTMsg == null) {
                msgMap.addXxMsgId(NSZM0870E);
                return;
            }

            // update SVC_CONTR_ADDL_CHRG_BLLG
            setValue(svcContrAddlChrgBllgTMsg.addlDealPrcAmt, addlDealPrcAmt);
            setValue(svcContrAddlChrgBllgTMsg.addlFuncPrcAmt, addlFuncPrcAmt);
            setValue(svcContrAddlChrgBllgTMsg.slsTaxRate, slsTaxRate);
            setValue(svcContrAddlChrgBllgTMsg.dealTaxAmt, totalDealTaxAmt);
            setValue(svcContrAddlChrgBllgTMsg.funcTaxAmt, totalFuncTaxAmt);
            S21ApiTBLAccessor.update(svcContrAddlChrgBllgTMsg);
            String rtnCd = svcContrAddlChrgBllgTMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                msgMap.addXxMsgId(NSZM0871E);
                return;
            }

            // get SVC_CONTR_BLLG_ALLOC
            int adjIndex = -1;
            BigDecimal maxDealGrsUnitPrcAmt = BigDecimal.ZERO;
            BigDecimal sumDealGrsUnitPrcAmt = BigDecimal.ZERO;
            BigDecimal sumFuncGrsUnitPrcAmt = BigDecimal.ZERO;
            SVC_CONTR_BLLG_ALLOCTMsgArray svcContrBllgAllocTMsgArray = getSvcContrBllgAllocForAddl(svcContrAddlChrgBllgPk);
            for (int i = 0; i < svcContrBllgAllocTMsgArray.getValidCount(); i++) {
                SVC_CONTR_BLLG_ALLOCTMsg svcContrBllgAllocTMsg = svcContrBllgAllocTMsgArray.no(i);

                int scale = NSXC003001Exchange.getDealCcyDigit(this.glblCmpyCd, svcContrBllgAllocTMsg.ccyCd.getValue());
                BigDecimal slsAllocRate = nullToZero(svcContrBllgAllocTMsg.slsAllocRate.getValue());
                BigDecimal dealGrsUnitPrcAmt = addlDealPrcAmt.multiply(slsAllocRate).divide(RATE_CHANGE, scale, HALF_UP);
                BigDecimal dealDiscUnitPrcAmt = BigDecimal.ZERO;
                BigDecimal funcGrsUnitPrcAmt = addlFuncPrcAmt.multiply(slsAllocRate).divide(RATE_CHANGE, this.stdCcyAftDeclPntDigitNum, HALF_UP);
                BigDecimal funcDiscUnitPrcAmt = BigDecimal.ZERO;

                if (maxDealGrsUnitPrcAmt.compareTo(dealGrsUnitPrcAmt) < 0) {
                    maxDealGrsUnitPrcAmt = dealGrsUnitPrcAmt;
                    adjIndex = i;
                }
                sumDealGrsUnitPrcAmt = sumDealGrsUnitPrcAmt.add(dealGrsUnitPrcAmt);
                sumFuncGrsUnitPrcAmt = sumFuncGrsUnitPrcAmt.add(funcGrsUnitPrcAmt);

                // update SVC_CONTR_BLLG_ALLOC
                setValue(svcContrBllgAllocTMsg.dealGrsUnitPrcAmt, dealGrsUnitPrcAmt);
                setValue(svcContrBllgAllocTMsg.dealDiscUnitPrcAmt, dealDiscUnitPrcAmt);
                setValue(svcContrBllgAllocTMsg.dealNetUnitPrcAmt, dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt));
                setValue(svcContrBllgAllocTMsg.dealGrsTotPrcAmt, svcContrBllgAllocTMsg.dealNetUnitPrcAmt);
                setValue(svcContrBllgAllocTMsg.invLineDealNetAmt, svcContrBllgAllocTMsg.dealNetUnitPrcAmt);
                setValue(svcContrBllgAllocTMsg.invLineDealTaxAmt, BigDecimal.ZERO);
                setValue(svcContrBllgAllocTMsg.funcGrsUnitPrcAmt, funcGrsUnitPrcAmt);
                setValue(svcContrBllgAllocTMsg.funcDiscUnitPrcAmt, funcDiscUnitPrcAmt);
                setValue(svcContrBllgAllocTMsg.funcNetUnitPrcAmt, funcGrsUnitPrcAmt.subtract(funcDiscUnitPrcAmt));
                setValue(svcContrBllgAllocTMsg.funcGrsTotPrcAmt, svcContrBllgAllocTMsg.funcNetUnitPrcAmt);
                setValue(svcContrBllgAllocTMsg.invLineFuncNetAmt, svcContrBllgAllocTMsg.funcNetUnitPrcAmt);
                setValue(svcContrBllgAllocTMsg.invLineFuncTaxAmt, BigDecimal.ZERO);
                S21ApiTBLAccessor.update(svcContrBllgAllocTMsg);
                rtnCd = svcContrBllgAllocTMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    msgMap.addXxMsgId(NSZM0824E);
                    return;
                }
            }

            // adjust
            if (adjIndex >= 0) {
                BigDecimal adjDealAmt = addlDealPrcAmt.subtract(sumDealGrsUnitPrcAmt);
                BigDecimal adjFuncAmt = addlFuncPrcAmt.subtract(sumFuncGrsUnitPrcAmt);
                if (adjDealAmt.compareTo(BigDecimal.ZERO) != 0 || adjFuncAmt.compareTo(BigDecimal.ZERO) != 0) {
                    SVC_CONTR_BLLG_ALLOCTMsg svcContrBllgAllocTMsg = svcContrBllgAllocTMsgArray.no(adjIndex);

                    BigDecimal dealGrsUnitPrcAmt = svcContrBllgAllocTMsg.dealGrsUnitPrcAmt.getValue().add(adjDealAmt);
                    BigDecimal dealDiscUnitPrcAmt = BigDecimal.ZERO;
                    BigDecimal funcGrsUnitPrcAmt = svcContrBllgAllocTMsg.funcGrsUnitPrcAmt.getValue().add(adjFuncAmt);
                    BigDecimal funcDiscUnitPrcAmt = BigDecimal.ZERO;

                    // update SVC_CONTR_BLLG_ALLOC
                    setValue(svcContrBllgAllocTMsg.dealGrsUnitPrcAmt, dealGrsUnitPrcAmt);
                    setValue(svcContrBllgAllocTMsg.dealDiscUnitPrcAmt, dealDiscUnitPrcAmt);
                    setValue(svcContrBllgAllocTMsg.dealNetUnitPrcAmt, dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt));
                    setValue(svcContrBllgAllocTMsg.dealGrsTotPrcAmt, svcContrBllgAllocTMsg.dealNetUnitPrcAmt);
                    setValue(svcContrBllgAllocTMsg.invLineDealNetAmt, svcContrBllgAllocTMsg.dealNetUnitPrcAmt);
                    setValue(svcContrBllgAllocTMsg.invLineDealTaxAmt, BigDecimal.ZERO);
                    setValue(svcContrBllgAllocTMsg.funcGrsUnitPrcAmt, funcGrsUnitPrcAmt);
                    setValue(svcContrBllgAllocTMsg.funcDiscUnitPrcAmt, funcDiscUnitPrcAmt);
                    setValue(svcContrBllgAllocTMsg.funcNetUnitPrcAmt, funcGrsUnitPrcAmt.subtract(funcDiscUnitPrcAmt));
                    setValue(svcContrBllgAllocTMsg.funcGrsTotPrcAmt, svcContrBllgAllocTMsg.funcNetUnitPrcAmt);
                    setValue(svcContrBllgAllocTMsg.invLineFuncNetAmt, svcContrBllgAllocTMsg.funcNetUnitPrcAmt);
                    setValue(svcContrBllgAllocTMsg.invLineFuncTaxAmt, BigDecimal.ZERO);
                    S21ApiTBLAccessor.update(svcContrBllgAllocTMsg);
                    rtnCd = svcContrBllgAllocTMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        msgMap.addXxMsgId(NSZM0824E);
                        return;
                    }
                }
            }
        }
    }
    // END 2016/03/10 T.Kanasaka [QC5207, ADD]

    // START 2016/03/10 T.Kanasaka [QC5207, MOD]
    // START 2016/03/24 T.Kanasaka [QC6001, MOD]
    // START 2016/03/28 T.Kanasaka [QC5879, MOD]
    private void setTaxCalcParam(S21ApiMessageMap msgMap, Map<String, Object> svcContrBllgInfoMap, CallTaxCalcAPIForUsageBean apiBean, BigDecimal funcAmt) {
        NSZC034001PMsg pMsg = (NSZC034001PMsg) msgMap.getPmsg();
        apiBean.setGlblCmpyCd(this.glblCmpyCd);
        apiBean.setSlsDt(pMsg.slsDt.getValue());
        apiBean.setXxProcMd("Q");
        apiBean.setInvTp((String) svcContrBllgInfoMap.get("INV_TP_CD"));
        apiBean.setDsAcctNum((String) svcContrBllgInfoMap.get("DS_ACCT_NUM"));
        apiBean.setBaseBillToCustCd((String) svcContrBllgInfoMap.get("BILL_TO_CUST_CD"));
        apiBean.setDsContrDtlTpCd((String) svcContrBllgInfoMap.get("DS_CONTR_DTL_TP_CD"));
        apiBean.setSvcMachMstrPk((BigDecimal) svcContrBllgInfoMap.get("SVC_MACH_MSTR_PK"));
        apiBean.setDsContrPk((BigDecimal) svcContrBllgInfoMap.get("DS_CONTR_PK"));
        apiBean.setNextBllgDt((String) svcContrBllgInfoMap.get("NEXT_BLLG_DT"));
        apiBean.setDsContrBllgSchdPk((BigDecimal) svcContrBllgInfoMap.get("DS_CONTR_BLLG_SCHD_PK"));
        apiBean.setSvcPgmMdseCd((String) svcContrBllgInfoMap.get("INTG_MDSE_CD"));
        apiBean.setMdseCdForSvcAllocTp((String) svcContrBllgInfoMap.get("SVC_PGM_MDSE_CD"));
        if (funcAmt == null) {
            apiBean.setBaseFuncAmt((BigDecimal) svcContrBllgInfoMap.get("MTR_CHRG_FUNC_AMT"));
        } else {
            apiBean.setBaseFuncAmt(funcAmt);
        }
    }
    // END 2016/03/28 T.Kanasaka [QC5879, MOD]
    // END 2016/03/24 T.Kanasaka [QC6001, MOD]
    // END 2016/03/10 T.Kanasaka [QC5207, MOD]

    private List<Map<String, Object>> getContrBllgInfo(String dsContrNum, String bllgDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_5);
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("dsContrNum", dsContrNum);
        ssmPrm.put("dsContrCatgCd", DS_CONTR_CATG.AGGREGATE);
        ssmPrm.put("bllgDt", bllgDt);
        // Add Start 04/27/2016 <QC#7563>
        ssmPrm.put("invTpCd", INV_TP.INVOICE);
        // Add End   04/27/2016 <QC#7563>
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getContrBllgInfo", ssmPrm);
    }

    private AGGR_USG_RECALTMsgArray getAggrUsgRecal(BigDecimal svcContrBllgGrpSq) {
        AGGR_USG_RECALTMsg tMsg = new AGGR_USG_RECALTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("svcContrBllgGrpSq01", svcContrBllgGrpSq);
        return (AGGR_USG_RECALTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    private AGGR_USG_RECAL_DTLTMsgArray getAggrUsgRecalDtl(BigDecimal aggrUsgRecalPk) {
        AGGR_USG_RECAL_DTLTMsg tMsg = new AGGR_USG_RECAL_DTLTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("aggrUsgRecalPk01", aggrUsgRecalPk);
        return (AGGR_USG_RECAL_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    private AGGR_USG_RECAL_DTLTMsgArray getAggrUsgRecalDtlForAlloc(BigDecimal aggrUsgRecalPk, String bllgMtrLbCd) {
        AGGR_USG_RECAL_DTLTMsg tMsg = new AGGR_USG_RECAL_DTLTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("aggrUsgRecalPk01", aggrUsgRecalPk);
        tMsg.setConditionValue("bllgMtrLbCd01", bllgMtrLbCd);
        return (AGGR_USG_RECAL_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    private AGGR_USG_RECAL_DTLTMsgArray getAggrUsgRecalDtlForAdjust(BigDecimal aggrUsgRecalPk, String bllgMtrLbCd) {
        AGGR_USG_RECAL_DTLTMsg tMsg = new AGGR_USG_RECAL_DTLTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("aggrUsgRecalPk01", aggrUsgRecalPk);
        tMsg.setConditionValue("bllgMtrLbCd01", bllgMtrLbCd);
        return (AGGR_USG_RECAL_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    private AGGR_USG_RECAL_XS_MTRTMsgArray getAggrUsgRecalXsMtr(BigDecimal aggrUsgRecalDtlPk) {
        AGGR_USG_RECAL_XS_MTRTMsg tMsg = new AGGR_USG_RECAL_XS_MTRTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("aggrUsgRecalDtlPk01", aggrUsgRecalDtlPk);
        return (AGGR_USG_RECAL_XS_MTRTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    private AGGR_USG_RECAL_XS_MTRTMsgArray getAggrUsgRecalXsMtrForCharge(BigDecimal aggrUsgRecalDtlPk, BigDecimal xsFromMtrCopyQty) {
        AGGR_USG_RECAL_XS_MTRTMsg tMsg = new AGGR_USG_RECAL_XS_MTRTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("aggrUsgRecalDtlPk01", aggrUsgRecalDtlPk);
        tMsg.setConditionValue("xsFromMtrCopyQty01", xsFromMtrCopyQty);
        return (AGGR_USG_RECAL_XS_MTRTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    private AGGR_USG_RECAL_XS_MTRTMsgArray getAggrUsgRecalXsMtrForRead(BigDecimal aggrUsgRecalDtlPk) {
        AGGR_USG_RECAL_XS_MTRTMsg tMsg = new AGGR_USG_RECAL_XS_MTRTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("aggrUsgRecalDtlPk01", aggrUsgRecalDtlPk);
        return (AGGR_USG_RECAL_XS_MTRTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    private List<Map<String, Object>> getRecalcInfo(BigDecimal svcContrBllgGrpSq) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_2);
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("svcContrBllgGrpSq", svcContrBllgGrpSq);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRecalcInfo", ssmPrm);
    }

    private SVC_CONTR_XS_MTR_BLLGTMsgArray getSvcContrXsMtrBllg(BigDecimal svcContrMtrBllgPk) {
        SVC_CONTR_XS_MTR_BLLGTMsg tMsg = new SVC_CONTR_XS_MTR_BLLGTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("svcContrMtrBllgPk01", svcContrMtrBllgPk);
        return (SVC_CONTR_XS_MTR_BLLGTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    private List<Map<String, Object>> getExcessInfo(BigDecimal dsContrBllgSchdPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_2);
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("dsContrBllgSchdPk", dsContrBllgSchdPk);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getExcessInfo", ssmPrm);
    }

    // Add Start 05/15/2018 <QC#23541>
    private List<Map<String, Object>> getExcessInfoAsc(BigDecimal dsContrBllgSchdPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_2);
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("dsContrBllgSchdPk", dsContrBllgSchdPk);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getExcessInfoAsc", ssmPrm);
    }
    // Add End   05/15/2018 <QC#23541>

    private List<Map<String, Object>> getSvcContrBllgInfo(BigDecimal aggrUsgRecalPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_2);
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("aggrUsgRecalPk", aggrUsgRecalPk);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcContrBllgInfo", ssmPrm);
    }

    private SVC_CONTR_BLLG_ALLOCTMsgArray getSvcContrBllgAlloc(BigDecimal svcContrMtrBllgPk) {
        SVC_CONTR_BLLG_ALLOCTMsg tMsg = new SVC_CONTR_BLLG_ALLOCTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("svcContrMtrBllgPk01", svcContrMtrBllgPk);
        return (SVC_CONTR_BLLG_ALLOCTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    // START 2016/03/10 T.Kanasaka [QC5207, ADD]
    private List<Map<String, Object>> getAddlChrgInfo(BigDecimal svcContrBllgPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_2);
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("svcContrBllgPk", svcContrBllgPk);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getAddlChrgInfo", ssmPrm);
    }

    private SVC_CONTR_BLLG_ALLOCTMsgArray getSvcContrBllgAllocForAddl(BigDecimal svcContrAddlChrgBllgPk) {
        SVC_CONTR_BLLG_ALLOCTMsg tMsg = new SVC_CONTR_BLLG_ALLOCTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("svcContrAddlChrgBllgPk01", svcContrAddlChrgBllgPk);
        return (SVC_CONTR_BLLG_ALLOCTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }
    // END 2016/03/10 T.Kanasaka [QC5207, ADD]

    // START 2018/04/05 K.Kitachi [QC#23066, ADD]
    private void updateDsContrBllgSchdForAggLine(S21ApiMessageMap msgMap, String dsContrNum, String bllgDt) {
        List<Map<String, Object>> childSummryInfoList = getChildSummryInfoList(dsContrNum, bllgDt);
        for (Map<String, Object> childSummryInfo : childSummryInfoList) {
            BigDecimal dsContrBllgSchdPk = (BigDecimal) childSummryInfo.get("DS_CONTR_BLLG_SCHD_PK");
            if (!hasValue(dsContrBllgSchdPk)) {
                return;
            }

            // get DS_CONTR_BLLG_SCHD
            DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsgParam = new DS_CONTR_BLLG_SCHDTMsg();
            setValue(dsContrBllgSchdTMsgParam.glblCmpyCd, this.glblCmpyCd);
            setValue(dsContrBllgSchdTMsgParam.dsContrBllgSchdPk, dsContrBllgSchdPk);
            DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg = (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrBllgSchdTMsgParam);
            if (dsContrBllgSchdTMsg == null) {
                msgMap.addXxMsgId(NSZM0827E);
                return;
            }

            // update DS_CONTR_BLLG_SCHD
            setValue(dsContrBllgSchdTMsg.mtrChrgDealAmt, (BigDecimal) childSummryInfo.get("MTR_CHRG_DEAL_AMT"));
            setValue(dsContrBllgSchdTMsg.mtrChrgFuncAmt, (BigDecimal) childSummryInfo.get("MTR_CHRG_FUNC_AMT"));
            setValue(dsContrBllgSchdTMsg.slsTaxRate, (BigDecimal) childSummryInfo.get("SLS_TAX_RATE"));
            setValue(dsContrBllgSchdTMsg.dealTaxAmt, (BigDecimal) childSummryInfo.get("DEAL_TAX_AMT"));
            setValue(dsContrBllgSchdTMsg.funcTaxAmt, (BigDecimal) childSummryInfo.get("FUNC_TAX_AMT"));
            S21ApiTBLAccessor.update(dsContrBllgSchdTMsg);
            String rtnCd = dsContrBllgSchdTMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                msgMap.addXxMsgId(NSZM0828E);
                return;
            }
        }
    }

    private List<Map<String, Object>> getChildSummryInfoList(String dsContrNum, String bllgDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_6);
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("dsContrNum", dsContrNum);
        ssmPrm.put("dsContrCatgCd", DS_CONTR_CATG.AGGREGATE);
        ssmPrm.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        ssmPrm.put("invTpCd", INV_TP.INVOICE);
        ssmPrm.put("bllgDt", bllgDt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getChildSummryInfo", ssmPrm);
    }
    // END 2018/04/05 K.Kitachi [QC#23066, ADD]

    // Add Start 05/15/2018 <QC#23541>
    private BigDecimal getBllgMinCnt(Map<String, Object> rsltSchdMap, BigDecimal bllgMinCnt) {
        return getBllgMin(rsltSchdMap, bllgMinCnt, 0);
    }

    private BigDecimal getBllgMinAmtRate(Map<String, Object> rsltSchdMap, BigDecimal bllgMinAmtRate) {
        return getBllgMin(rsltSchdMap, bllgMinAmtRate, ccyScale);
    }

    private BigDecimal getBllgMin(Map<String, Object> rsltSchdMap, BigDecimal bllgMin, int scale) {
        BigDecimal prrtDivRate = (BigDecimal) rsltSchdMap.get("PRRT_DIV_RATE");
        String perBllgCycleCd = (String) rsltSchdMap.get("PER_BLLG_CYCLE_CD");
        BigDecimal perSchdNum = (BigDecimal) rsltSchdMap.get("PER_SCHD_NUM");

        if (!ZYPCommonFunc.hasValue(prrtDivRate) || !ZYPCommonFunc.hasValue(perBllgCycleCd) || !ZYPCommonFunc.hasValue(perSchdNum)) {
            return bllgMin;
        }
        if (!BLLG_CYCLE.DAILY.equals(perBllgCycleCd)) {
            return bllgMin;
        }

        return bllgMin.multiply(perSchdNum).divide(DAYS_OF_YEAR.divide(prrtDivRate, 2, BigDecimal.ROUND_HALF_UP), scale, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal nullToZero(BigDecimal num) {
        if (num == null) {
            return BigDecimal.ZERO;
        }
        return num;
    }

    private List<Map<String, Object>> getTopSchdMtrList(BigDecimal dsContrBllgSchdSmryPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_6);
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("dsContrBllgSchdSmryPk", dsContrBllgSchdSmryPk);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getTopSchdMtrList", ssmPrm);
    }

    private Map<String, Object> getFreeCopyFromAggrRecal(BigDecimal dsContrPk, String bllgMtrLbCd, String curFromDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_6);
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("bllgMtrLbCd", bllgMtrLbCd);
        ssmPrm.put("curFromDt", curFromDt);
        return (Map<String, Object>) ssmBatchClient.queryObject("getFreeCopyFromAggrRecal", ssmPrm);
    }

    // START 2018/06/07 K.Kojima [QC#21974,ADD]
    private Map<String, Object> getFreeCopyFromAggrRecalUsed(BigDecimal aggLineDtlPk, String bllgMtrLbCd, BigDecimal svcContrBllgGrpSq, String curFromDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_6);
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("aggLineDtlPk", aggLineDtlPk);
        ssmPrm.put("bllgMtrLbCd", bllgMtrLbCd);
        ssmPrm.put("svcContrBllgGrpSq", svcContrBllgGrpSq);
        ssmPrm.put("curFromDt", curFromDt);
        ssmPrm.put("invTpInvoice", INV_TP.INVOICE);

        BigDecimal countCreditRebill = (BigDecimal) ssmBatchClient.queryObject("countCreditRebill", ssmPrm);
        if (countCreditRebill == null || countCreditRebill.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        BigDecimal bllgFreeCopyCnt = null;
        BigDecimal rollOverRatio = null;
        Map<String, Object> freeCopyRollOverRatio = (Map<String, Object>) ssmBatchClient.queryObject("getFreeCopyRollOverRatioFromAggrRecalUsed", ssmPrm);
        if (freeCopyRollOverRatio != null) {
            bllgFreeCopyCnt = (BigDecimal) freeCopyRollOverRatio.get("BLLG_FREE_COPY_CNT");
            rollOverRatio = (BigDecimal) freeCopyRollOverRatio.get("ROLL_OVER_RATIO");
        }
        if (bllgFreeCopyCnt == null) {
            bllgFreeCopyCnt = BigDecimal.ZERO;
        }
        if (rollOverRatio == null) {
            rollOverRatio = BigDecimal.ZERO;
        }
        BigDecimal rollOverCnt = (BigDecimal) ssmBatchClient.queryObject("getRollOverCntFromAggrRecalUsed", ssmPrm);
        if (rollOverCnt == null) {
            rollOverCnt = BigDecimal.ZERO;
        }

        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("BLLG_FREE_COPY_CNT", bllgFreeCopyCnt);
        returnMap.put("ROLL_OVER_RATIO", rollOverRatio);
        returnMap.put("ROLL_OVER_CNT", rollOverCnt);
        return returnMap;
    }

    // END 2018/06/07 K.Kojima [QC#21974,ADD]

    private Map<String, Object> getFreeCopyFromAggrBllgMtr(String bllgMtrLbCd, BigDecimal aggLineDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_6);
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("bllgMtrLbCd", bllgMtrLbCd);
        ssmPrm.put("aggLineDtlPk", aggLineDtlPk);
        return (Map<String, Object>) ssmBatchClient.queryObject("getFreeCopyFromAggrBllgMtr", ssmPrm);
    }

    private BigDecimal calcAggAmt(String xsChrgTpcd, List<AggTierInfo> aggTierInfoList, BigDecimal sumAllowance, BigDecimal remainingBllgCopyCnt) {
        BigDecimal bllgAmt = BigDecimal.ZERO;
        BigDecimal xsMtrAmtRate = BigDecimal.ZERO;
        if (XS_CHRG_TP.POINT.equals((String) xsChrgTpcd)) {
            AggTierInfo pointChrgTierInfo = null;
            for (AggTierInfo aggTierInfo : aggTierInfoList) {
                xsMtrAmtRate = aggTierInfo.getXsMtrAmtRate();
                pointChrgTierInfo = aggTierInfo;
                if (remainingBllgCopyCnt.compareTo(aggTierInfo.getXsMtrCopyQty()) > 0) {
                    break;
                }
            }
            pointChrgTierInfo.setExecChrg(true);
            remainingBllgCopyCnt = remainingBllgCopyCnt.subtract(sumAllowance);
            if (BigDecimal.ZERO.compareTo(remainingBllgCopyCnt) >= 0) {
                bllgAmt = setAmtScale(BigDecimal.ZERO);
                // Add Start 05/31/2018 <QC#23541>
                pointChrgTierInfo.setRemainChrgAmt(bllgAmt);
                // Add End   05/31/2018 <QC#23541>
                pointChrgTierInfo.setBllblCopyQty(BigDecimal.ZERO);
                pointChrgTierInfo.setRemainBllblCopyQty(BigDecimal.ZERO);
            } else {
                BigDecimal chrgCopyCnt = remainingBllgCopyCnt;
                pointChrgTierInfo.setBllblCopyQty(chrgCopyCnt);
                pointChrgTierInfo.setRemainBllblCopyQty(chrgCopyCnt);
                bllgAmt = xsMtrAmtRate.multiply(chrgCopyCnt);
                bllgAmt = setAmtScale(bllgAmt);
                // Add Start 05/31/2018 <QC#23541>
                pointChrgTierInfo.setRemainChrgAmt(bllgAmt);
                // Add End   05/31/2018 <QC#23541>
            }
        } else {
            for (AggTierInfo aggTierInfo : aggTierInfoList) {
                if (remainingBllgCopyCnt.compareTo(aggTierInfo.getXsMtrCopyQty()) > 0) {
                    aggTierInfo.setExecChrg(true);
                    xsMtrAmtRate = (BigDecimal) aggTierInfo.getXsMtrAmtRate();
                    BigDecimal chrgCopyCnt = remainingBllgCopyCnt.subtract(aggTierInfo.getXsMtrCopyQty());
                    chrgCopyCnt = chrgCopyCnt.setScale(0, BigDecimal.ROUND_HALF_UP);
                    aggTierInfo.addBllblCopyQty(chrgCopyCnt);
                    aggTierInfo.setRemainBllblCopyQty(aggTierInfo.getBllblCopyQty());
                    BigDecimal chrgXsAmt = xsMtrAmtRate.multiply(chrgCopyCnt);
                    remainingBllgCopyCnt = remainingBllgCopyCnt.subtract(chrgCopyCnt);
                    bllgAmt = bllgAmt.add(chrgXsAmt);
                    bllgAmt = setAmtScale(bllgAmt);
                    // Add Start 05/31/2018 <QC#23541>
                    aggTierInfo.setRemainChrgAmt(setAmtScale(chrgXsAmt));
                    // Add End   05/31/2018 <QC#23541>
                }
            }
        }
        return bllgAmt;
    }

    private BigDecimal setAmtScale(BigDecimal amt) {
        if (!hasValue(amt)) {
            return BigDecimal.ZERO;
        }
        return amt.setScale(ccyScale, BigDecimal.ROUND_HALF_UP);
    }
    // Add End   05/15/2018 <QC#23541>

    // START 2019/01/21 M.Naito [QC#29083,ADD]
    private String addDays(String date, int numberOfDays) {
        if (!hasValue(date)) {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat(S21CalendarUtilConstants.TYPE_YYYYMMDD);
        Date dt = null;
        try {
            dt = format.parse(date);
        } catch (ParseException e) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.DATE, numberOfDays);
        return format.format(cal.getTime());
    }

    private boolean checkFromToDt(String targetDt, String FromDt, String ToDt) {
        if (ZYPDateUtil.compare(targetDt, FromDt) >= 0 && ZYPDateUtil.compare(targetDt, ToDt) <= 0) {
            return true;
        }
        return false;
    }

    private boolean checkCumCopyReset(BigDecimal cumCopyFreq, String cumCopyStDt, String bllgFromDt, String bllgThruDt) {
        int cumCopyCnt = NSXC001001CalcDate.diffMonths(cumCopyStDt, bllgThruDt) / cumCopyFreq.intValue();
        String cumCopyResetDt = NSXC001001CalcDate.addMonths(cumCopyStDt, cumCopyCnt * cumCopyFreq.intValue());

        if (checkFromToDt(cumCopyResetDt, bllgFromDt, bllgThruDt)) {
            return true;
        }
        return false;
    }

    private boolean checkCumCopyPerDays(String bllgFromDt, String bllgThruDt, BigDecimal dsContrDtlPk, BigDecimal aggrUsgRecalPk, String bllgMtrLbCd, String cumCopyStDt) {
        DS_CONTR_DTLTMsg dsContrDtlTmsg = new DS_CONTR_DTLTMsg();
        setValue(dsContrDtlTmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(dsContrDtlTmsg.dsContrDtlPk, dsContrDtlPk);
        dsContrDtlTmsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKey(dsContrDtlTmsg);

        if (ZYPDateUtil.compare(bllgFromDt, dsContrDtlTmsg.contrEffFromDt.getValue()) == 0) {
            Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_6);
            ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
            ssmPrm.put("aggrUsgRecalPk", aggrUsgRecalPk);
            ssmPrm.put("bllgMtrLbCd", bllgMtrLbCd);
            BigDecimal countBllgSchdPrrt = (BigDecimal) ssmBatchClient.queryObject("countBllgSchdPrrt", ssmPrm);

            if (countBllgSchdPrrt != null && countBllgSchdPrrt.compareTo(BigDecimal.ZERO) > 0) {
                if (ZYPDateUtil.compare(addDays(bllgThruDt, 1), cumCopyStDt) == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    // END 2019/01/21 M.Naito [QC#29083,ADD]
}
