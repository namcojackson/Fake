/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC035001;

import static com.canon.cusa.s21.api.NSZ.NSZC035001.constant.NSZC035001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMessageEnv;

import business.db.BLLG_SCHD_TEST_MTR_SMRYTMsg;
import business.db.CALC_MTR_SCHD_RELNTMsg;
import business.db.CALC_MTR_SCHD_RELNTMsgArray;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.FLEET_READ_ROLL_UPTMsg;
import business.db.FLEET_READ_ROLL_UPTMsgArray;
import business.db.FLEET_READ_ROLL_UP_DTLTMsg;
import business.db.FLEET_READ_ROLL_UP_DTLTMsgArray;
import business.db.SVC_CR_REBIL_MTR_READTMsg;
import business.db.SVC_CR_REBIL_MTR_READTMsgArray;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.parts.NSZC035001PMsg;

import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001MtrCntTwoPntEst;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001SvcPhysMtrRead;
import com.canon.cusa.s21.common.NSX.NSXC003001.SvcPhysMtrReadInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FLEET_CALC_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TEST_COPY_CLS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Fleet Calculation API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Hitachi         T.Kanasaka      Create          NA
 * 2016/01/18   Hitachi         T.Kanasaka      Update          QC3069
 * 2016/01/21   Hitachi         T.Kanasaka      Update          QC3455
 * 2016/01/22   Hitachi         T.Kanasaka      Update          QC3475
 * 2016/02/04   Hitachi         T.Kanasaka      Update          QC4116
 * 2016/02/05   Hitachi         T.Kanasaka      Update          QC4139
 * 2016/03/11   Hitachi         T.Kanasaka      Update          QC5374
 * 2016/04/26   Hitachi         T.Kanasaka      Update          QC7578
 * 2016/05/09   Hitachi         T.Kanasaka      Update          QC7764
 * 2016/10/04   Hitachi         T.Kanasaka      Update          QC12274
 * 2017/02/15   Hitachi         T.Mizuki        Update          QC17489
 * 2017/08/24   Hitachi         K.Kitachi       Update          QC#20746
 * 2017/09/20   Hitachi         T.Kanasaka      Update          QC#15134
 * 2017/10/10   Hitachi         E.Kameishi      Update          QC#18636
 * 2017/10/18   Hitachi         E.Kameishi      Update          QC#18636
 * 2017/10/25   Hitachi         K.Kojima        Update          QC#21562
 * 2017/12/27   Hitachi         K.Kojima        Update          QC#23276
 * 2018/01/17   Hitachi         K.Kishimoto     Update          QC#23486
 * 2018/06/07   Hitachi         K.Kitachi       Update          QC#20750
 * 2019/08/23   Hitachi         A.Kohinata      Update          QC#52934
 * 2019/09/04   Hitachi         T.Aoyagi        Update          QC#53124
 * </pre>
 */
public class NSZC035001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Global Company Code
     */
    private String glblCmpyCd = null;

    /**
     * Mode
     */
    private String xxModeCd = null;

    /**
     * ONBATCH_TYPE
     */
    private ONBATCH_TYPE onBatTp;

    /**
     * svcInvTpCd
     */
    private String svcInvTpCd = INV_TP.INVOICE;

    /**
     * Continuable Error (To update Fleet Read Roll Up Table)
     */
    private boolean isContinuableError = false;

    // add start 2019/09/04 QC#53124
    /**
     * Contract LOB List
     */
    private List<String> contrLobList = new ArrayList<String>();

    /**
     * Is Exclude Test Copy
     */
    private boolean isExclTestCopy = false;
    // add end 2019/09/04 QC#53124

    /**
     * Constructor
     */
    public NSZC035001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsgList List<NSZC035001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC035001PMsg> pMsgList, ONBATCH_TYPE onBatchType) {
        for (NSZC035001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatTp);
        }
    }

    /**
     * execute
     * @param pMsg NSZC035001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC035001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        execute(msgMap, onBatTp);
        msgMap.flush();
    }

    private void execute(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NSZC035001PMsg pMsg = (NSZC035001PMsg) msgMap.getPmsg();

        // 1. input check
        checkParameter(msgMap);
        if (hasErrMsg(msgMap)) {
            return;
        }

        this.glblCmpyCd = pMsg.glblCmpyCd.getValue();
        this.xxModeCd = pMsg.xxModeCd.getValue();
        this.onBatTp = onBatchType;
        this.isContinuableError = false;

        if (MODE_REBILL.equals(pMsg.xxModeCd.getValue())) {
            String rebillTpCd = ZYPCodeDataUtil.getVarCharConstValue(SVC_INV_REBILL_TP_CD, this.glblCmpyCd);
            if (hasValue(rebillTpCd)) {
                this.svcInvTpCd = rebillTpCd;
            }
        }
        // add start 2019/09/04 QC#53124
        String contrLob = ZYPCodeDataUtil.getVarCharConstValue(CONTR_LOB_EXCL_TEST_COPY, this.glblCmpyCd);
        if (hasValue(contrLob)) {
            this.contrLobList = Arrays.asList(contrLob.split(","));
        }
        // add end 2019/09/04 QC#53124
        doProcess(msgMap);
    }

    private void checkParameter(S21ApiMessageMap msgMap) {

        NSZC035001PMsg pMsg = (NSZC035001PMsg) msgMap.getPmsg();

        if (!hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZM0001E);
        }

        if (!MODE_NORMAL.equals(pMsg.xxModeCd.getValue()) && !MODE_REBILL.equals(pMsg.xxModeCd.getValue())) {
            msgMap.addXxMsgId(NSZM0175E);
        }

        if (!hasValue(pMsg.dsContrNum)) {
            msgMap.addXxMsgId(NSZM0271E);
        }

        if (!hasValue(pMsg.prntDsContrBllgSchdPk)) {
            msgMap.addXxMsgId(NSZM0831E);
        }

        if (!hasValue(pMsg.bllgDt)) {
            msgMap.addXxMsgId(NSZM0817E);
        }

        if (MODE_REBILL.equals(pMsg.xxModeCd.getValue())) {
            if (!hasValue(pMsg.svcCrRebilPk)) {
                msgMap.addXxMsgId(NSZM0832E);
            }

            if (!hasValue(pMsg.svcCrRebilDtlPk)) {
                msgMap.addXxMsgId(NSZM0833E);
            }
        }
    }

    private boolean hasErrMsg(S21ApiMessageMap msgMap) {
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return true;
        }
        return false;
    }

    // START 2016/05/09 T.Kanasaka [QC7764, MOD]
    private void doProcess(S21ApiMessageMap msgMap) {

        NSZC035001PMsg pMsg = (NSZC035001PMsg) msgMap.getPmsg();
        String dsContrNum = pMsg.dsContrNum.getValue();
        String bllgDt = pMsg.bllgDt.getValue();
        BigDecimal svcCrRebilDtlPk = pMsg.svcCrRebilDtlPk.getValue();
        BigDecimal prntDsContrBllgSchdPk = pMsg.prntDsContrBllgSchdPk.getValue();

        // 2. remove FLEET_READ_ROLL_UP, FLEET_READ_ROLL_UP_DTL
        removeFleetReadRollUp(prntDsContrBllgSchdPk);

        // 3. get target Billing Schedule
        List<Map<String, Object>> contrBllgSchdInfoList = getContrBllgSchdInfo(dsContrNum, prntDsContrBllgSchdPk, bllgDt, svcCrRebilDtlPk);
        if (contrBllgSchdInfoList == null || contrBllgSchdInfoList.size() == 0) {
            msgMap.addXxMsgId(NSZM0834E);
            return;
        }

        // add start 2019/09/04 QC#53124
        if (MODE_NORMAL.equals(pMsg.xxModeCd.getValue())) {
            String svcLineBizCd = (String) contrBllgSchdInfoList.get(0).get("SVC_LINE_BIZ_CD");
            if (this.contrLobList.contains(svcLineBizCd)) {
                this.isExclTestCopy = true;
            }
        }
        // add end 2019/09/04 QC#53124
        BigDecimal dsContrPk = (BigDecimal) contrBllgSchdInfoList.get(0).get("DS_CONTR_PK");
        BigDecimal prntDsContrDtlPk = (BigDecimal) contrBllgSchdInfoList.get(0).get("PRNT_DS_CONTR_DTL_PK");
        BigDecimal fleetReadRollUpPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FLEET_READ_ROLL_UP_SQ);
        Map<String, Object> bllgMtrLbInfo = new HashMap<String, Object>();
        BigDecimal sumPriReadMtrCnt = BigDecimal.ZERO;
        BigDecimal sumCurReadMtrCnt = BigDecimal.ZERO;
        // START 2017/09/20 T.Kanasaka [QC#15134,ADD]
        BigDecimal sumAftResetMtrCnt = BigDecimal.ZERO;
        // END 2017/09/20 T.Kanasaka [QC#15134,ADD]
        String bllgMtrLbCd = null;

        for (Map<String, Object> contrBllgSchdInfoMap : contrBllgSchdInfoList) {
            BigDecimal dsContrDtlPk = (BigDecimal) contrBllgSchdInfoMap.get("DS_CONTR_DTL_PK");
            BigDecimal dsContrBllgMtrPk = (BigDecimal) contrBllgSchdInfoMap.get("DS_CONTR_BLLG_MTR_PK");
            String bllgSchdFromDt = (String) contrBllgSchdInfoMap.get("BLLG_SCHD_FROM_DT");

            // 4. get target Physical Meter
            List<Map<String, Object>> physMtrInfoList = getPhysMtrInfo(dsContrDtlPk, dsContrBllgMtrPk);

            // 5. get previous Billing Schedule
            String prevSchdDt = ZYPDateUtil.addDays(bllgSchdFromDt, -1);
            Map<String, Object> prevContrBllgSchdInfoMap = getPrevContrBllgSchdInfo(dsContrNum, dsContrBllgMtrPk, prevSchdDt);

            if (MODE_REBILL.equals(pMsg.xxModeCd.getValue())) {
                if (!physMtrInfoList.isEmpty()) {
                    bllgMtrLbCd = (String) physMtrInfoList.get(0).get("BLLG_MTR_LB_CD");
                }
                doRebillProcess(msgMap, dsContrDtlPk, fleetReadRollUpPk, contrBllgSchdInfoMap, physMtrInfoList, bllgMtrLbInfo, prevContrBllgSchdInfoMap);
                continue;
            }

            if (prevContrBllgSchdInfoMap != null) {
                String prevBllgSchdThruDt = (String) prevContrBllgSchdInfoMap.get("BLLG_SCHD_THRU_DT");
                BigDecimal prevSvcCrRebilDtlPk = (BigDecimal) prevContrBllgSchdInfoMap.get("SVC_CR_REBIL_DTL_PK");
                BigDecimal prevSvcPhysMtrReadGrpSq = (BigDecimal) prevContrBllgSchdInfoMap.get("SVC_PHYS_MTR_READ_GRP_SQ");
                String prevDsBllgSchdStsCd = (String) prevContrBllgSchdInfoMap.get("DS_BLLG_SCHD_STS_CD");
                String prevFleetReadRollUpErrMsg = null;

                // 6. get previous Fleet Roll Up
                Map<String, Object> prevFleetReadRollUpInfoMap = getPrevFleetReadRollUpInfo(prntDsContrDtlPk, dsContrBllgMtrPk, prevBllgSchdThruDt, prevSvcCrRebilDtlPk);
                if (!checkPrevFleetReadRollUpInfo(prevFleetReadRollUpInfoMap, prevDsBllgSchdStsCd)) {
                    this.isContinuableError = true;
                    prevFleetReadRollUpErrMsg = EZDMessageEnv.getMessage(NSZM0839E);
                }

                // 7.1 get previous meter count (previous exists)
                for (Map<String, Object> physMtrInfoMap : physMtrInfoList) {
                    BigDecimal svcPhysMtrPk = (BigDecimal) physMtrInfoMap.get("SVC_PHYS_MTR_PK");
                    BigDecimal priReadMtrCnt = null;
                    // START 2016/10/04 T.Kanasaka [QC#12274, ADD]
                    String priMtrReadDt = null;
                    // END 2016/10/04 T.Kanasaka [QC#12274, ADD]
                    String vldMsgTxt = null;

                    // START 2017/09/20 T.Kanasaka [QC#15134,ADD]
                    SvcPhysMtrReadInfoBean priMtrReadInfo = null;
                    SvcPhysMtrReadInfoBean curMtrReadInfo = null;
                    // END 2017/09/20 T.Kanasaka [QC#15134,ADD]

                    if (bllgMtrLbCd == null) {
                        bllgMtrLbCd = (String) physMtrInfoMap.get("BLLG_MTR_LB_CD");
                    }

                    if (prevFleetReadRollUpErrMsg != null) {
                        priReadMtrCnt = BigDecimal.ZERO;
                        vldMsgTxt = prevFleetReadRollUpErrMsg;
                    }

                    if (vldMsgTxt == null) {
                        if (prevFleetReadRollUpInfoMap != null) {
                            // 7.1.1 get previous Fleet Roll Up Detail
                            // Mod Start 2018/01/17 <QC#23486>
                            FLEET_READ_ROLL_UP_DTLTMsgArray fleetReadRollUpDtlTMsgArray = getFleetReadRollUpDtlForPrev((BigDecimal) prevFleetReadRollUpInfoMap.get("FLEET_READ_ROLL_UP_PK"), (BigDecimal) contrBllgSchdInfoMap.get("SVC_MACH_MSTR_PK"), svcPhysMtrPk);
                            // Mod End   2018/01/17 <QC#23486>
                            if (fleetReadRollUpDtlTMsgArray.getValidCount() > 0) {
                                priReadMtrCnt = fleetReadRollUpDtlTMsgArray.no(0).curReadMtrCnt.getValue();
                            }
                            // START 2016/10/04 T.Kanasaka [QC#12274, ADD]
                            FLEET_READ_ROLL_UPTMsg fleetReadRollUpTMsg = getFleetReadRollUpByKey((BigDecimal) prevFleetReadRollUpInfoMap.get("FLEET_READ_ROLL_UP_PK"));
                            if (fleetReadRollUpTMsg != null) {
                                priMtrReadDt = fleetReadRollUpTMsg.mtrReadDt.getValue();
                            }
                            // END 2016/10/04 T.Kanasaka [QC#12274, ADD]

                            // START 2017/09/20 T.Kanasaka [QC#15134,ADD]
                            priMtrReadInfo = getMtrReadInfoByRollUpDtl(fleetReadRollUpDtlTMsgArray.no(0));
                            // END 2017/09/20 T.Kanasaka [QC#15134,ADD]
                        } else {
                            // 7.1.2 read previous meter count
                            if (priReadMtrCnt == null) {
                                // 7.1.2.2 read previous meter count (Normal)
                                SVC_PHYS_MTR_READTMsg svcPhysMtrReadTMsg = NSXC003001SvcPhysMtrRead.getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(this.glblCmpyCd, svcPhysMtrPk, prevSvcPhysMtrReadGrpSq);
                                if (svcPhysMtrReadTMsg != null) {
                                    priReadMtrCnt = svcPhysMtrReadTMsg.readMtrCnt.getValue();
                                    // START 2016/10/04 T.Kanasaka [QC#12274, ADD]
                                    priMtrReadDt = svcPhysMtrReadTMsg.mtrReadDt.getValue();
                                    // END 2016/10/04 T.Kanasaka [QC#12274, ADD]

                                    // START 2017/09/20 T.Kanasaka [QC#15134,ADD]
                                    priMtrReadInfo = getMtrReadInfoBySvcPhysMtrRead(svcPhysMtrReadTMsg);
                                    // END 2017/09/20 T.Kanasaka [QC#15134,ADD]
                                }
                            }
                        }
                    }

                    if (priReadMtrCnt == null) {
                        // error
                        priReadMtrCnt = BigDecimal.ZERO;
                        this.isContinuableError = true;
                        vldMsgTxt = EZDMessageEnv.getMessage(NSZM0840E);
                    }
                    BigDecimal contrMtrMultRate = nullToZero((BigDecimal) physMtrInfoMap.get("CONTR_MTR_MULT_RATE"));
                    BigDecimal priBllgMtrCnt = priReadMtrCnt.multiply(contrMtrMultRate).setScale(0, HALF_UP);
                    sumPriReadMtrCnt = sumPriReadMtrCnt.add(priBllgMtrCnt);

                    // 8. Roll Up (Detail)
                    // START 2016/10/04 T.Kanasaka [QC#12274, MOD]
//                    BigDecimal curBllgMtrCnt = insertFleetReadRollUpDtl(msgMap, fleetReadRollUpPk, contrBllgSchdInfoMap, physMtrInfoMap, priReadMtrCnt, null, prevBllgSchdThruDt, bllgMtrLbInfo, vldMsgTxt);
                    BigDecimal curBllgMtrCnt = insertFleetReadRollUpDtl(msgMap, fleetReadRollUpPk, contrBllgSchdInfoMap, physMtrInfoMap, priReadMtrCnt, null, prevBllgSchdThruDt, bllgMtrLbInfo, vldMsgTxt, priMtrReadDt, null);
                    // END 2016/10/04 T.Kanasaka [QC#12274, MOD]
                    if (hasErrMsg(msgMap)) {
                        return;
                    }

                    // START 2017/09/20 T.Kanasaka [QC#15134,ADD]
                    BigDecimal rollOverExchCnt = BigDecimal.ZERO;
                    SVC_PHYS_MTR_READTMsg curSvcPhysMtrReadTMsg = NSXC003001SvcPhysMtrRead.getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(this.glblCmpyCd, (BigDecimal) physMtrInfoMap.get("SVC_PHYS_MTR_PK"), (BigDecimal) contrBllgSchdInfoMap.get("SVC_PHYS_MTR_READ_GRP_SQ"));
                    if (curSvcPhysMtrReadTMsg != null) {
                        if (priMtrReadInfo != null) {
                            curMtrReadInfo = getMtrReadInfoBySvcPhysMtrRead(curSvcPhysMtrReadTMsg);
                            NSXC003001MtrCntTwoPntEst nsxc003001MtrEst = new NSXC003001MtrCntTwoPntEst();
                            rollOverExchCnt = nsxc003001MtrEst.getRollOverExchCnt(this.glblCmpyCd, priMtrReadInfo, curMtrReadInfo);
                            if (rollOverExchCnt.compareTo(BigDecimal.ZERO) > 0) {
                                insertCalcMtrSchdReln(msgMap, (BigDecimal) contrBllgSchdInfoMap.get("DS_CONTR_BLLG_SCHD_PK"), priMtrReadInfo, curMtrReadInfo);
                                if (hasErrMsg(msgMap)) {
                                    return;
                                }
                            }
                        }
                    }
                    sumAftResetMtrCnt = sumAftResetMtrCnt.add(curBllgMtrCnt.multiply(contrMtrMultRate).setScale(0, HALF_UP));
                    curBllgMtrCnt = curBllgMtrCnt.add(rollOverExchCnt);
                    // END 2017/09/20 T.Kanasaka [QC#15134,ADD]

                    curBllgMtrCnt = curBllgMtrCnt.multiply(contrMtrMultRate).setScale(0, HALF_UP);
                    sumCurReadMtrCnt = sumCurReadMtrCnt.add(curBllgMtrCnt);

                    // START 2018/06/07 K.Kitachi [QC#20750, ADD]
                    updateBllgSchdTestMtrSmry((BigDecimal) contrBllgSchdInfoMap.get("DS_CONTR_BLLG_SCHD_PK"), svcPhysMtrPk, priMtrReadInfo, curMtrReadInfo, rollOverExchCnt, contrMtrMultRate);
                    // END 2018/06/07 K.Kitachi [QC#20750, ADD]
                }
            } else {
                // 7.2 get previous meter count (previous not exists)
                for (Map<String, Object> physMtrInfoMap : physMtrInfoList) {
                    BigDecimal svcPhysMtrPk = (BigDecimal) physMtrInfoMap.get("SVC_PHYS_MTR_PK");
                    BigDecimal priReadMtrCnt = null;
                    String vldMsgTxt = null;
                    // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
                    String priMtrReadDt = null;
                    // END 2016/10/06 T.Kanasaka [QC#12274, ADD]

                    if (bllgMtrLbCd == null) {
                        bllgMtrLbCd = (String) physMtrInfoMap.get("BLLG_MTR_LB_CD");
                    }

                    // START 2017/09/20 T.Kanasaka [QC#15134,ADD]
                    SvcPhysMtrReadInfoBean priMtrReadInfo = null;
                    SvcPhysMtrReadInfoBean curMtrReadInfo = null;
                    // END 2017/09/20 T.Kanasaka [QC#15134,ADD]

                    if (priReadMtrCnt == null) {
                        // 7.2.2 read start meter count
                        SVC_PHYS_MTR_READTMsg svcPhysMtrReadTMsg = null;
                        BigDecimal svcPhysMtrReadGrpSq = NSXC003001SvcPhysMtrRead.getStartMeterSvcPhysMtrReadGrpSq(this.glblCmpyCd, dsContrDtlPk, svcPhysMtrPk);
                        if (svcPhysMtrReadGrpSq != null) {
                            svcPhysMtrReadTMsg = NSXC003001SvcPhysMtrRead.getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(this.glblCmpyCd, svcPhysMtrPk, svcPhysMtrReadGrpSq);
                        }

                        if (svcPhysMtrReadTMsg != null) {
                            priReadMtrCnt = svcPhysMtrReadTMsg.readMtrCnt.getValue();
                            // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
                            priMtrReadDt = ZYPDateUtil.addDays(svcPhysMtrReadTMsg.mtrReadDt.getValue(), -1);
                            // END 2016/10/06 T.Kanasaka [QC#12274, ADD]

                            // START 2017/09/20 T.Kanasaka [QC#15134,ADD]
                            priMtrReadInfo = getMtrReadInfoBySvcPhysMtrRead(svcPhysMtrReadTMsg);
                            // END 2017/09/20 T.Kanasaka [QC#15134,ADD]
                        }
                    }

                    if (priReadMtrCnt == null) {
                        // error
                        priReadMtrCnt = BigDecimal.ZERO;
                        this.isContinuableError = true;
                        vldMsgTxt = EZDMessageEnv.getMessage(NSZM0840E);
                    }
                    BigDecimal contrMtrMultRate = nullToZero((BigDecimal) physMtrInfoMap.get("CONTR_MTR_MULT_RATE"));
                    BigDecimal priBllgMtrCnt = priReadMtrCnt.multiply(contrMtrMultRate).setScale(0, HALF_UP);
                    sumPriReadMtrCnt = sumPriReadMtrCnt.add(priBllgMtrCnt);

                    // 8. Roll Up (Detail)
                    // START 2016/10/04 T.Kanasaka [QC#12274, MOD]
//                    BigDecimal curBllgMtrCnt = insertFleetReadRollUpDtl(msgMap, fleetReadRollUpPk, contrBllgSchdInfoMap, physMtrInfoMap, priReadMtrCnt, null, null, bllgMtrLbInfo, vldMsgTxt);
                    BigDecimal curBllgMtrCnt = insertFleetReadRollUpDtl(msgMap, fleetReadRollUpPk, contrBllgSchdInfoMap, physMtrInfoMap, priReadMtrCnt, null, null, bllgMtrLbInfo, vldMsgTxt, priMtrReadDt, null);
                    // END 2016/10/04 T.Kanasaka [QC#12274, MOD]
                    if (hasErrMsg(msgMap)) {
                        return;
                    }

                    // START 2017/09/20 T.Kanasaka [QC#15134,ADD]
                    BigDecimal rollOverExchCnt = BigDecimal.ZERO;
                    SVC_PHYS_MTR_READTMsg curSvcPhysMtrReadTMsg = NSXC003001SvcPhysMtrRead.getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(this.glblCmpyCd, (BigDecimal) physMtrInfoMap.get("SVC_PHYS_MTR_PK"), (BigDecimal) contrBllgSchdInfoMap.get("SVC_PHYS_MTR_READ_GRP_SQ"));
                    if (curSvcPhysMtrReadTMsg != null) {
                        if (priMtrReadInfo != null) {
                            curMtrReadInfo = getMtrReadInfoBySvcPhysMtrRead(curSvcPhysMtrReadTMsg);
                            NSXC003001MtrCntTwoPntEst nsxc003001MtrEst = new NSXC003001MtrCntTwoPntEst();
                            rollOverExchCnt = nsxc003001MtrEst.getRollOverExchCnt(this.glblCmpyCd, priMtrReadInfo, curMtrReadInfo);
                            if (rollOverExchCnt.compareTo(BigDecimal.ZERO) > 0) {
                                insertCalcMtrSchdReln(msgMap, (BigDecimal) contrBllgSchdInfoMap.get("DS_CONTR_BLLG_SCHD_PK"), priMtrReadInfo, curMtrReadInfo);
                                if (hasErrMsg(msgMap)) {
                                    return;
                                }
                            }
                        }
                    }
                    sumAftResetMtrCnt = sumAftResetMtrCnt.add(curBllgMtrCnt.multiply(contrMtrMultRate).setScale(0, HALF_UP));
                    curBllgMtrCnt = curBllgMtrCnt.add(rollOverExchCnt);
                    // END 2017/09/20 T.Kanasaka [QC#15134,ADD]

                    curBllgMtrCnt = curBllgMtrCnt.multiply(contrMtrMultRate).setScale(0, HALF_UP);
                    sumCurReadMtrCnt = sumCurReadMtrCnt.add(curBllgMtrCnt);

                    // START 2018/06/07 K.Kitachi [QC#20750, ADD]
                    updateBllgSchdTestMtrSmry((BigDecimal) contrBllgSchdInfoMap.get("DS_CONTR_BLLG_SCHD_PK"), svcPhysMtrPk, priMtrReadInfo, curMtrReadInfo, rollOverExchCnt, contrMtrMultRate);
                    // END 2018/06/07 K.Kitachi [QC#20750, ADD]
                }
            }
        }

        if (!MODE_REBILL.equals(pMsg.xxModeCd.getValue())) {
            // set Sum Meter Count
            bllgMtrLbInfo.put(MAP_KEY_SUM_PRI_READ_MTR_CNT, sumPriReadMtrCnt);
            bllgMtrLbInfo.put(MAP_KEY_SUM_CUR_READ_MTR_CNT, sumCurReadMtrCnt);
            // START 2017/09/20 T.Kanasaka [QC#15134,ADD]
            if (sumAftResetMtrCnt.compareTo(sumCurReadMtrCnt) != 0) {
                bllgMtrLbInfo.put(MAP_KEY_SUM_AFT_RESET_MTR_CNT, sumAftResetMtrCnt);
            }
            // END 2017/09/20 T.Kanasaka [QC#15134,ADD]
        }

        // get DS_CONTR_BLLG_SCHD
        DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg = getDsContrBllgSchd(prntDsContrBllgSchdPk);
        if (dsContrBllgSchdTMsg == null) {
            msgMap.addXxMsgId(NSZM0834E);
            return;
        }

        // 9. insert FLEET_READ_ROLL_UP
        FLEET_READ_ROLL_UPTMsg fleetReadRollUpTMsg = insertFleetReadRollUp(msgMap, fleetReadRollUpPk, dsContrPk, prntDsContrDtlPk, bllgMtrLbInfo, bllgMtrLbCd, dsContrBllgSchdTMsg);
        if (hasErrMsg(msgMap)) {
            return;
        }

        // output error info
        if (this.isContinuableError) {
            updateErrInfoToFleetReadRollUp(msgMap, fleetReadRollUpTMsg);
            // START 2017/08/24 K.Kitachi [QC#20746, ADD]
            // 10. update DS_CONTR_BLLG_SCHD
            updateDsContrBllgSchd(msgMap, dsContrBllgSchdTMsg, ZYPConstant.FLG_OFF_N);
            if (hasErrMsg(msgMap)) {
                return;
            }
            // END 2017/08/24 K.Kitachi [QC#20746, ADD]
            // START 2017/12/27 K.Kojima [QC#23276,ADD]
            msgMap.addXxMsgId(NSZM0841E);
            // END 2017/12/27 K.Kojima [QC#23276,ADD]
        } else {
            // normal end
            // 10. update DS_CONTR_BLLG_SCHD
            // START 2017/08/24 K.Kitachi [QC#20746, MOD]
//            updateDsContrBllgSchd(msgMap, dsContrBllgSchdTMsg);
            updateDsContrBllgSchd(msgMap, dsContrBllgSchdTMsg, ZYPConstant.FLG_ON_Y);
            // END 2017/08/24 K.Kitachi [QC#20746, MOD]
            if (hasErrMsg(msgMap)) {
                return;
            }
        }

        setValue(pMsg.fleetReadRollUpPk, fleetReadRollUpPk);
    }
    // END 2016/05/09 T.Kanasaka [QC7764, MOD]

    private void removeFleetReadRollUp(BigDecimal prntDsContrBllgSchdPk) {

        // get remove data
        List<FLEET_READ_ROLL_UPTMsg> delFleetReadRollUpList = new ArrayList<FLEET_READ_ROLL_UPTMsg>();
        List<FLEET_READ_ROLL_UP_DTLTMsg> delFleetReadRollUpDtlList = new ArrayList<FLEET_READ_ROLL_UP_DTLTMsg>();
        // START 2017/09/20 T.Kanasaka [QC#15134,ADD]
        List<CALC_MTR_SCHD_RELNTMsg> delCalcMtrSchdRelnList = new ArrayList<CALC_MTR_SCHD_RELNTMsg>();
        // END 2017/09/20 T.Kanasaka [QC#15134,ADD]

        FLEET_READ_ROLL_UPTMsgArray fleetReadRollUpTMsgArray = getFleetReadRollUp(prntDsContrBllgSchdPk);
        for (int i = 0; i < fleetReadRollUpTMsgArray.getValidCount(); i++) {
            FLEET_READ_ROLL_UPTMsg fleetReadRollUpTMsg = fleetReadRollUpTMsgArray.no(i);
            delFleetReadRollUpList.add(fleetReadRollUpTMsg);

            FLEET_READ_ROLL_UP_DTLTMsgArray fleetReadRollUpDtlTMsgArray = getFleetReadRollUpDtl(fleetReadRollUpTMsg.fleetReadRollUpPk.getValue());
            for (int j = 0; j < fleetReadRollUpDtlTMsgArray.getValidCount(); j++) {
                FLEET_READ_ROLL_UP_DTLTMsg fleetReadRollUpDtlTMsg = fleetReadRollUpDtlTMsgArray.no(j);
                delFleetReadRollUpDtlList.add(fleetReadRollUpDtlTMsg);

                // START 2017/09/20 T.Kanasaka [QC#15134,ADD]
                CALC_MTR_SCHD_RELNTMsgArray calcMtrSchdRelnTMsgArray = getCalcMtrSchdReln(fleetReadRollUpDtlTMsg.dsContrBllgSchdPk.getValue());
                for (int k = 0; k < calcMtrSchdRelnTMsgArray.getValidCount(); k++) {
                    CALC_MTR_SCHD_RELNTMsg calcMtrSchdRelnTMsg = calcMtrSchdRelnTMsgArray.no(k);
                    delCalcMtrSchdRelnList.add(calcMtrSchdRelnTMsg);
                }
                // END 2017/09/20 T.Kanasaka [QC#15134,ADD]
            }
        }

        // remove FLEET_READ_ROLL_UP, FLEET_READ_ROLL_UP_DTL
        if (delFleetReadRollUpList.size() > 0) {
            // START 2017/08/24 K.Kitachi [QC#20746, MOD]
//            S21ApiTBLAccessor.remove(delFleetReadRollUpList.toArray(new FLEET_READ_ROLL_UPTMsg[delFleetReadRollUpList.size()]));
            S21ApiTBLAccessor.logicalRemove(delFleetReadRollUpList.toArray(new FLEET_READ_ROLL_UPTMsg[delFleetReadRollUpList.size()]));
            // END 2017/08/24 K.Kitachi [QC#20746, MOD]
        }
        if (delFleetReadRollUpDtlList.size() > 0) {
            // START 2017/08/24 K.Kitachi [QC#20746, MOD]
//            S21ApiTBLAccessor.remove(delFleetReadRollUpDtlList.toArray(new FLEET_READ_ROLL_UP_DTLTMsg[delFleetReadRollUpDtlList.size()]));
            S21ApiTBLAccessor.logicalRemove(delFleetReadRollUpDtlList.toArray(new FLEET_READ_ROLL_UP_DTLTMsg[delFleetReadRollUpDtlList.size()]));
            // END 2017/08/24 K.Kitachi [QC#20746, MOD]
        }
        // START 2017/09/20 T.Kanasaka [QC#15134,ADD]
        if (delCalcMtrSchdRelnList.size() > 0) {
            S21ApiTBLAccessor.logicalRemove(delCalcMtrSchdRelnList.toArray(new CALC_MTR_SCHD_RELNTMsg[delCalcMtrSchdRelnList.size()]));
        }
        // END 2017/09/20 T.Kanasaka [QC#15134,ADD]
    }

    // START 2016/05/09 T.Kanasaka [QC7764, ADD]
    private void doRebillProcess(S21ApiMessageMap msgMap, BigDecimal dsContrDtlPk, BigDecimal fleetReadRollUpPk, Map<String, Object> contrBllgSchdInfoMap, List<Map<String, Object>> physMtrInfoList, Map<String, Object> bllgMtrLbInfo,
            Map<String, Object> prevContrBllgSchdInfoMap) {

        NSZC035001PMsg pMsg = (NSZC035001PMsg) msgMap.getPmsg();

        for (Map<String, Object> physMtrInfoMap : physMtrInfoList) {
            BigDecimal priReadMtrCnt = BigDecimal.ZERO;
            BigDecimal curReadMtrCnt = BigDecimal.ZERO;
            // START 2017/10/10 E.Kameishi [QC#18636, ADD]
            BigDecimal curTestMtrCnt = BigDecimal.ZERO;
            // END 2017/10/10 E.Kameishi [QC#18636, ADD]
            // START 2018/06/07 K.Kitachi [QC#20750, ADD]
            SvcPhysMtrReadInfoBean prevSvcPhysMtrReadInfoBean = null;
            SvcPhysMtrReadInfoBean currSvcPhysMtrReadInfoBean = null;
            BigDecimal rollOverExchCnt = BigDecimal.ZERO;
            // END 2018/06/07 K.Kitachi [QC#20750, ADD]
            String mdlMtrLbCd = (String) physMtrInfoMap.get("MDL_MTR_LB_CD");

            SVC_CR_REBIL_MTR_READTMsgArray svcCrRebilMtrReadTMsgArray = getSvcCrRebilMtrRead(pMsg.svcCrRebilDtlPk.getValue(), dsContrDtlPk, mdlMtrLbCd);
            if (svcCrRebilMtrReadTMsgArray.getValidCount() > 0) {
                SVC_CR_REBIL_MTR_READTMsg svcCrRebilMtrReadTMsg = svcCrRebilMtrReadTMsgArray.no(0);

                if (hasValue(svcCrRebilMtrReadTMsg.newStartReadMtrCnt)) {
                    priReadMtrCnt = svcCrRebilMtrReadTMsg.newStartReadMtrCnt.getValue();
                } else {
                    priReadMtrCnt = svcCrRebilMtrReadTMsg.oldStartReadMtrCnt.getValue();
                }

                if (hasValue(svcCrRebilMtrReadTMsg.newEndReadMtrCnt)) {
                    curReadMtrCnt = svcCrRebilMtrReadTMsg.newEndReadMtrCnt.getValue();
                } else {
                    curReadMtrCnt = svcCrRebilMtrReadTMsg.oldEndReadMtrCnt.getValue();
                }
                // START 2017/10/10 E.Kameishi [QC#18636, ADD]
                if (hasValue(svcCrRebilMtrReadTMsg.newTestMtrCnt)) {
                    curTestMtrCnt = svcCrRebilMtrReadTMsg.newTestMtrCnt.getValue();
                } else {
                    curTestMtrCnt = svcCrRebilMtrReadTMsg.oldTestMtrCnt.getValue();
                }
                // END 2017/10/10 E.Kameishi [QC#18636, ADD]
                // set bllgMtrLbInfo
                String mtrReadDt = (String) bllgMtrLbInfo.get(MAP_KEY_MTR_READ_DT);
                if (mtrReadDt == null || mtrReadDt.compareTo(svcCrRebilMtrReadTMsg.endMtrReadDt.getValue()) < 0) {
                    mtrReadDt = svcCrRebilMtrReadTMsg.endMtrReadDt.getValue();
                    bllgMtrLbInfo.put(MAP_KEY_MTR_READ_DT, mtrReadDt);
                }
                // START 2018/06/07 K.Kitachi [QC#20750, ADD]
                prevSvcPhysMtrReadInfoBean = getPrevMtrReadBean(svcCrRebilMtrReadTMsg);
                currSvcPhysMtrReadInfoBean = getCurrMtrReadBean(svcCrRebilMtrReadTMsg);
                NSXC003001MtrCntTwoPntEst nsxc003001MtrEst = new NSXC003001MtrCntTwoPntEst();
                // mod start 2019/08/23 QC#52934
                //rollOverExchCnt = nsxc003001MtrEst.getRollOverExchCnt(this.glblCmpyCd, prevSvcPhysMtrReadInfoBean, currSvcPhysMtrReadInfoBean);
                BigDecimal exclSvcPhysMtrReadPk = null;
                if (hasValue(svcCrRebilMtrReadTMsg.newEndReadMtrCnt) || hasValue(svcCrRebilMtrReadTMsg.newTestMtrCnt)) {
                    exclSvcPhysMtrReadPk = svcCrRebilMtrReadTMsg.endSvcPhysMtrReadPk.getValue();
                }
                rollOverExchCnt = nsxc003001MtrEst.getRollOverExchCntForCrRebil(this.glblCmpyCd, prevSvcPhysMtrReadInfoBean, currSvcPhysMtrReadInfoBean, exclSvcPhysMtrReadPk);
                // mod end 2019/08/23 QC#52934
                // END 2018/06/07 K.Kitachi [QC#20750, ADD]
            }

            BigDecimal sumPriReadMtrCnt = (BigDecimal) bllgMtrLbInfo.get(MAP_KEY_SUM_PRI_READ_MTR_CNT);
            if (sumPriReadMtrCnt == null) {
                sumPriReadMtrCnt = BigDecimal.ZERO;
            }
            BigDecimal contrMtrMultRate = nullToZero((BigDecimal) physMtrInfoMap.get("CONTR_MTR_MULT_RATE"));
            BigDecimal priBllgMtrCnt = priReadMtrCnt.multiply(contrMtrMultRate).setScale(0, HALF_UP);
            sumPriReadMtrCnt = sumPriReadMtrCnt.add(priBllgMtrCnt);
            bllgMtrLbInfo.put(MAP_KEY_SUM_PRI_READ_MTR_CNT, sumPriReadMtrCnt);

            BigDecimal sumCurReadMtrCnt = (BigDecimal) bllgMtrLbInfo.get(MAP_KEY_SUM_CUR_READ_MTR_CNT);
            if (sumCurReadMtrCnt == null) {
                sumCurReadMtrCnt = BigDecimal.ZERO;
            }
            BigDecimal curBllgMtrCnt = curReadMtrCnt.multiply(contrMtrMultRate).setScale(0, HALF_UP);
            sumCurReadMtrCnt = sumCurReadMtrCnt.add(curBllgMtrCnt);
            bllgMtrLbInfo.put(MAP_KEY_SUM_CUR_READ_MTR_CNT, sumCurReadMtrCnt);

            String prevBllgSchdThruDt = null;
            if (prevContrBllgSchdInfoMap != null) {
                prevBllgSchdThruDt = (String) prevContrBllgSchdInfoMap.get("BLLG_SCHD_THRU_DT");
            }

            // START 2016/10/04 T.Kanasaka [QC#12274, MOD]
//            insertFleetReadRollUpDtl(msgMap, fleetReadRollUpPk, contrBllgSchdInfoMap, physMtrInfoMap, priReadMtrCnt, curReadMtrCnt, prevBllgSchdThruDt, bllgMtrLbInfo, null);
            insertFleetReadRollUpDtl(msgMap, fleetReadRollUpPk, contrBllgSchdInfoMap, physMtrInfoMap, priReadMtrCnt, curReadMtrCnt, prevBllgSchdThruDt, bllgMtrLbInfo, null, null, curTestMtrCnt);
            // END 2016/10/04 T.Kanasaka [QC#12274, MOD]
            // START 2017/10/10 E.Kameishi [QC#18636, ADD]
            insertBllgSchdTestMtrSmryForRebill(contrBllgSchdInfoMap, physMtrInfoMap, curTestMtrCnt);
            // END 2017/10/10 E.Kameishi [QC#18636, ADD]
            // START 2018/06/07 K.Kitachi [QC#20750, ADD]
            updateBllgSchdTestMtrSmry((BigDecimal) contrBllgSchdInfoMap.get("DS_CONTR_BLLG_SCHD_PK"), (BigDecimal) physMtrInfoMap.get("SVC_PHYS_MTR_PK"), prevSvcPhysMtrReadInfoBean, currSvcPhysMtrReadInfoBean, rollOverExchCnt, contrMtrMultRate);
            // END 2018/06/07 K.Kitachi [QC#20750, ADD]
            if (hasErrMsg(msgMap)) {
                return;
            }
        }
    }
    // END 2016/05/09 T.Kanasaka [QC7764, ADD]

    // START 2016/05/09 T.Kanasaka [QC7764, DEL]
//    private BigDecimal getPrevReadMtrCntForCrRebil(BigDecimal svcCrRebilDtlPk, BigDecimal dsContrDtlPk, String physMtrLbCd) {
//        BigDecimal curReadMtrCnt = null;
//        SVC_CR_REBIL_MTR_READTMsgArray svcCrRebilMtrReadTMsgArray = getSvcCrRebilMtrRead(svcCrRebilDtlPk, dsContrDtlPk, physMtrLbCd);
//        if (svcCrRebilMtrReadTMsgArray.getValidCount() > 0) {
//            SVC_CR_REBIL_MTR_READTMsg svcCrRebilMtrReadTMsg = svcCrRebilMtrReadTMsgArray.no(0);
//            if (hasValue(svcCrRebilMtrReadTMsg.newEndReadMtrCnt)) {
//                curReadMtrCnt = svcCrRebilMtrReadTMsg.newEndReadMtrCnt.getValue();
//            } else {
//                curReadMtrCnt = svcCrRebilMtrReadTMsg.oldEndReadMtrCnt.getValue();
//            }
//        }
//        return curReadMtrCnt;
//    }
//
//    // START 2016/04/26 T.Kanasaka [QC7578, MOD]
//    private BigDecimal getReadMtrCntForCrRebil(BigDecimal svcCrRebilDtlPk, BigDecimal dsContrDtlPk, String physMtrLbCd, Map<String, Object> bllgMtrLbInfo) {
//        BigDecimal curReadMtrCnt = null;
//        SVC_CR_REBIL_MTR_READTMsgArray svcCrRebilMtrReadTMsgArray = getSvcCrRebilMtrRead(svcCrRebilDtlPk, dsContrDtlPk, physMtrLbCd);
//        if (svcCrRebilMtrReadTMsgArray.getValidCount() > 0) {
//            SVC_CR_REBIL_MTR_READTMsg svcCrRebilMtrReadTMsg = svcCrRebilMtrReadTMsgArray.no(0);
//            if (hasValue(svcCrRebilMtrReadTMsg.newEndReadMtrCnt)) {
//                curReadMtrCnt = svcCrRebilMtrReadTMsg.newEndReadMtrCnt.getValue();
//            } else {
//                curReadMtrCnt = svcCrRebilMtrReadTMsg.oldEndReadMtrCnt.getValue();
//            }
//
//            // set bllgMtrLbInfo
//            String mtrReadDt = (String) bllgMtrLbInfo.get(MAP_KEY_MTR_READ_DT);
//            if (mtrReadDt == null || mtrReadDt.compareTo(svcCrRebilMtrReadTMsg.endMtrReadDt.getValue()) < 0) {
//                mtrReadDt = svcCrRebilMtrReadTMsg.endMtrReadDt.getValue();
//                bllgMtrLbInfo.put(MAP_KEY_MTR_READ_DT, mtrReadDt);
//            }
//        }
//        return curReadMtrCnt;
//    }
//    // END 2016/04/26 T.Kanasaka [QC7578, MOD]
    // END 2016/05/09 T.Kanasaka [QC7764, DEL]

    private boolean checkPrevFleetReadRollUpInfo(Map<String, Object> prevFleetReadRollUpInfoMap, String dsBllgSchdStsCd) {
        if (DS_BLLG_SCHD_STS.OPEN.equals(dsBllgSchdStsCd)) {
            if (prevFleetReadRollUpInfoMap == null) {
                return false;
            } else {
                String fleetCalcProcCd = (String) prevFleetReadRollUpInfoMap.get("FLEET_CALC_PROC_CD");
                if ((MODE_NORMAL.equals(this.xxModeCd) && FLEET_CALC_PROC.INCOMPLETE.equals(fleetCalcProcCd)) || (MODE_REBILL.equals(this.xxModeCd) && FLEET_CALC_PROC.REBILL_INCOMPLETE.equals(fleetCalcProcCd))) {
                    return false;
                }
            }
        }
        return true;
    }

    // START 2016/05/09 T.Kanasaka [QC7764, DEL]
//    private boolean isReBilling(BigDecimal svcCrRebilDtlPk, String dsBllgSchdStsCd) {
//        if (svcCrRebilDtlPk != null && DS_BLLG_SCHD_STS.OPEN.equals(dsBllgSchdStsCd)) {
//            return true;
//        }
//        return false;
//    }
    // END 2016/05/09 T.Kanasaka [QC7764, DEL]

    // START 2016/10/04 T.Kanasaka [QC#12274, MOD]
    // START 2016/05/09 T.Kanasaka [QC7764, MOD]
    private BigDecimal insertFleetReadRollUpDtl(S21ApiMessageMap msgMap, BigDecimal fleetReadRollUpPk, Map<String, Object> contrBllgSchdInfoMap, Map<String, Object> physMtrInfoMap, BigDecimal priReadMtrCnt, BigDecimal curReadMtrCnt,
            String priBllgPerUpToDt, Map<String, Object> bllgMtrLbInfo, String vldMsgTxt, String priMtrReadDt, BigDecimal testMtrCnt) {
        BigDecimal svcPhysMtrReadGrpSq = (BigDecimal) contrBllgSchdInfoMap.get("SVC_PHYS_MTR_READ_GRP_SQ");
        BigDecimal svcPhysMtrPk = (BigDecimal) physMtrInfoMap.get("SVC_PHYS_MTR_PK");

        String curMtrReadDt = null;
        BigDecimal testCopyQty = BigDecimal.ZERO;
        // START 2017/10/18 E.Kameishi [QC#18636, ADD]
        if (testMtrCnt != null){
            // add start 2019/09/04 QC#53124
            if (this.isExclTestCopy) {
                testMtrCnt = BigDecimal.ZERO;
            }
            // add end 2019/09/04 QC#53124
            testCopyQty = testMtrCnt;
            // START 2017/10/19 K.Ochiai [QC#18636, ADD]
            BigDecimal sumTestMtrCnt = (BigDecimal) bllgMtrLbInfo.get(MAP_KEY_SUM_TEST_MTR_CNT);
            if (sumTestMtrCnt == null) {
                sumTestMtrCnt = BigDecimal.ZERO;
            }
            BigDecimal contrMtrMultRate = nullToZero((BigDecimal) physMtrInfoMap.get("CONTR_MTR_MULT_RATE"));
            BigDecimal testBllgMtrCnt = testCopyQty.multiply(contrMtrMultRate).setScale(0, HALF_UP);
            sumTestMtrCnt = sumTestMtrCnt.add(testBllgMtrCnt);
            bllgMtrLbInfo.put(MAP_KEY_SUM_TEST_MTR_CNT, sumTestMtrCnt);
            // END 2017/10/19 K.Ochiai [QC#18636, ADD]
        }
        // START 2017/10/18 E.Kameishi [QC#18636, ADD]

        if (curReadMtrCnt == null) {
            // 8.2
            SVC_PHYS_MTR_READTMsg svcPhysMtrReadTMsg = NSXC003001SvcPhysMtrRead.getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(this.glblCmpyCd, svcPhysMtrPk, svcPhysMtrReadGrpSq);
            if (svcPhysMtrReadTMsg != null) {
                curReadMtrCnt = svcPhysMtrReadTMsg.readMtrCnt.getValue();

                // set bllgMtrLbInfo
                curMtrReadDt = (String) bllgMtrLbInfo.get(MAP_KEY_MTR_READ_DT);
                if (curMtrReadDt == null || curMtrReadDt.compareTo(svcPhysMtrReadTMsg.mtrReadDt.getValue()) < 0) {
                    curMtrReadDt = svcPhysMtrReadTMsg.mtrReadDt.getValue();
                    bllgMtrLbInfo.put(MAP_KEY_MTR_READ_DT, curMtrReadDt);
                }
            }

            if (priMtrReadDt == null) {
                priMtrReadDt = ZYPDateUtil.addDays((String) contrBllgSchdInfoMap.get("BLLG_SCHD_FROM_DT"), -1);
            }

            // START 2017/10/10 E.Kameishi [QC#18636, MOD]
            // get Test Meter Count
            testCopyQty = getTestMtrCnt(contrBllgSchdInfoMap,svcPhysMtrPk, priMtrReadDt, curMtrReadDt);
            // END 2017/10/10 E.Kameishi [QC#18636, MOD]
            // add start 2019/09/04 QC#53124
            if (this.isExclTestCopy) {
                testCopyQty = BigDecimal.ZERO;
            }
            // add end 2019/09/04 QC#53124
            BigDecimal sumTestMtrCnt = (BigDecimal) bllgMtrLbInfo.get(MAP_KEY_SUM_TEST_MTR_CNT);
            if (sumTestMtrCnt == null) {
                sumTestMtrCnt = BigDecimal.ZERO;
            }
            BigDecimal contrMtrMultRate = nullToZero((BigDecimal) physMtrInfoMap.get("CONTR_MTR_MULT_RATE"));
            BigDecimal testBllgMtrCnt = testCopyQty.multiply(contrMtrMultRate).setScale(0, HALF_UP);
            sumTestMtrCnt = sumTestMtrCnt.add(testBllgMtrCnt);
            bllgMtrLbInfo.put(MAP_KEY_SUM_TEST_MTR_CNT, sumTestMtrCnt);
        }

        if (curReadMtrCnt == null) {
            // error
            curReadMtrCnt = BigDecimal.ZERO;
            this.isContinuableError = true;
            vldMsgTxt = EZDMessageEnv.getMessage(NSZM0840E);
        }

        // 8.3 insert FLEET_READ_ROLL_UP_DTL
        BigDecimal fleetReadRollUpDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FLEET_READ_ROLL_UP_DTL_SQ);
        FLEET_READ_ROLL_UP_DTLTMsg fleetReadRollUpDtlTMsg = new FLEET_READ_ROLL_UP_DTLTMsg();
        setValue(fleetReadRollUpDtlTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(fleetReadRollUpDtlTMsg.fleetReadRollUpDtlPk, fleetReadRollUpDtlPk);
        setValue(fleetReadRollUpDtlTMsg.fleetReadRollUpPk, fleetReadRollUpPk);
        setValue(fleetReadRollUpDtlTMsg.svcMachMstrPk, (BigDecimal) contrBllgSchdInfoMap.get("SVC_MACH_MSTR_PK"));
        setValue(fleetReadRollUpDtlTMsg.serNum, (String) contrBllgSchdInfoMap.get("SER_NUM"));
        setValue(fleetReadRollUpDtlTMsg.dsContrBllgSchdPk, (BigDecimal) contrBllgSchdInfoMap.get("DS_CONTR_BLLG_SCHD_PK"));
        setValue(fleetReadRollUpDtlTMsg.machMtrLbCd, (String) physMtrInfoMap.get("MTR_LB_CD"));
        setValue(fleetReadRollUpDtlTMsg.fleetMtrLbCd, (String) physMtrInfoMap.get("FLEET_MTR_LB_CD"));
        setValue(fleetReadRollUpDtlTMsg.curBllgPerFromDt, (String) contrBllgSchdInfoMap.get("BLLG_SCHD_FROM_DT"));
        setValue(fleetReadRollUpDtlTMsg.curBllgPerThruDt, (String) contrBllgSchdInfoMap.get("BLLG_SCHD_THRU_DT"));
        setValue(fleetReadRollUpDtlTMsg.priBllgPerUpToDt, priBllgPerUpToDt);
        setValue(fleetReadRollUpDtlTMsg.curReadMtrCnt, curReadMtrCnt);
        setValue(fleetReadRollUpDtlTMsg.priReadMtrCnt, priReadMtrCnt);
        if (this.isContinuableError) {
            if (MODE_NORMAL.equals(this.xxModeCd)) {
                setValue(fleetReadRollUpDtlTMsg.fleetCalcProcCd, FLEET_CALC_PROC.INCOMPLETE);
            } else if (MODE_REBILL.equals(this.xxModeCd)) {
                setValue(fleetReadRollUpDtlTMsg.fleetCalcProcCd, FLEET_CALC_PROC.REBILL_INCOMPLETE);
            }
            setValue(fleetReadRollUpDtlTMsg.vldMsgTxt, vldMsgTxt);
        } else {
            if (MODE_NORMAL.equals(this.xxModeCd)) {
                setValue(fleetReadRollUpDtlTMsg.fleetCalcProcCd, FLEET_CALC_PROC.COMPLETE);
            } else if (MODE_REBILL.equals(this.xxModeCd)) {
                setValue(fleetReadRollUpDtlTMsg.fleetCalcProcCd, FLEET_CALC_PROC.REBILL_COMPLETE);
            }
        }
        setValue(fleetReadRollUpDtlTMsg.svcPhysMtrPk, svcPhysMtrPk);
        setValue(fleetReadRollUpDtlTMsg.testCopyQty, testCopyQty);
        S21ApiTBLAccessor.insert(fleetReadRollUpDtlTMsg);
        String rtnCd = fleetReadRollUpDtlTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0837E);
            return curReadMtrCnt;
        }

        // set bllgMtrLbInfo
        String bllgFromDt = (String) bllgMtrLbInfo.get(MAP_KEY_BLLG_FROM_DT);
        if (bllgFromDt == null || bllgFromDt.compareTo(fleetReadRollUpDtlTMsg.curBllgPerFromDt.getValue()) > 0) {
            bllgFromDt = fleetReadRollUpDtlTMsg.curBllgPerFromDt.getValue();
            bllgMtrLbInfo.put(MAP_KEY_BLLG_FROM_DT, bllgFromDt);
        }
        String bllgThruDt = (String) bllgMtrLbInfo.get(MAP_KEY_BLLG_THRU_DT);
        if (bllgThruDt == null || bllgThruDt.compareTo(fleetReadRollUpDtlTMsg.curBllgPerThruDt.getValue()) < 0) {
            bllgThruDt = fleetReadRollUpDtlTMsg.curBllgPerThruDt.getValue();
            bllgMtrLbInfo.put(MAP_KEY_BLLG_THRU_DT, bllgThruDt);
        }

        return curReadMtrCnt;
    }
    // END 2016/05/09 T.Kanasaka [QC7764, MOD]
    // END 2016/10/04 T.Kanasaka [QC#12274, MOD]

    private FLEET_READ_ROLL_UPTMsg insertFleetReadRollUp(S21ApiMessageMap msgMap, BigDecimal fleetReadRollUpPk, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, Map<String, Object> bllgMtrLbInfo,
            String bllgMtrLbCd, DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg) {
        NSZC035001PMsg pMsg = (NSZC035001PMsg) msgMap.getPmsg();

        // insert FLEET_READ_ROLL_UP
        FLEET_READ_ROLL_UPTMsg fleetReadRollUpTMsg = new FLEET_READ_ROLL_UPTMsg();
        setValue(fleetReadRollUpTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(fleetReadRollUpTMsg.fleetReadRollUpPk, fleetReadRollUpPk);
        setValue(fleetReadRollUpTMsg.dsContrPk, dsContrPk);
        setValue(fleetReadRollUpTMsg.dsContrNum, pMsg.dsContrNum);
        setValue(fleetReadRollUpTMsg.dsContrDtlPk, dsContrDtlPk);
        setValue(fleetReadRollUpTMsg.prntDsContrBllgSchdPk, pMsg.prntDsContrBllgSchdPk);
        setValue(fleetReadRollUpTMsg.svcCrRebilPk, pMsg.svcCrRebilPk);
        setValue(fleetReadRollUpTMsg.svcCrRebilDtlPk, pMsg.svcCrRebilDtlPk);
        setValue(fleetReadRollUpTMsg.bllgMtrLbCd, bllgMtrLbCd);
        setValue(fleetReadRollUpTMsg.bllgFromDt, (String) bllgMtrLbInfo.get(MAP_KEY_BLLG_FROM_DT));
        setValue(fleetReadRollUpTMsg.bllgThruDt, (String) bllgMtrLbInfo.get(MAP_KEY_BLLG_THRU_DT));
        setValue(fleetReadRollUpTMsg.priFleetReadMtrCnt, (BigDecimal) bllgMtrLbInfo.get(MAP_KEY_SUM_PRI_READ_MTR_CNT));
        setValue(fleetReadRollUpTMsg.curFleetReadMtrCnt, (BigDecimal) bllgMtrLbInfo.get(MAP_KEY_SUM_CUR_READ_MTR_CNT));
        setValue(fleetReadRollUpTMsg.mtrReadDt, (String) bllgMtrLbInfo.get(MAP_KEY_MTR_READ_DT));
        if (MODE_NORMAL.equals(this.xxModeCd)) {
            setValue(fleetReadRollUpTMsg.fleetCalcProcCd, FLEET_CALC_PROC.COMPLETE);
        } else if (MODE_REBILL.equals(this.xxModeCd)) {
            setValue(fleetReadRollUpTMsg.fleetCalcProcCd, FLEET_CALC_PROC.REBILL_COMPLETE);
        }
        setValue(fleetReadRollUpTMsg.dsContrBllgMtrPk, dsContrBllgSchdTMsg.dsContrBllgMtrPk);
        // START 2016/10/04 T.Kanasaka [QC#12274, ADD]
        setValue(fleetReadRollUpTMsg.testCopyQty, nullToZero((BigDecimal) bllgMtrLbInfo.get(MAP_KEY_SUM_TEST_MTR_CNT)));
        // END 2016/10/04 T.Kanasaka [QC#12274, ADD]
        // START 2017/09/20 T.Kanasaka [QC#15134,ADD]
        if (bllgMtrLbInfo.containsKey(MAP_KEY_SUM_AFT_RESET_MTR_CNT)) {
            setValue(fleetReadRollUpTMsg.aftResetMtrCnt, (BigDecimal) bllgMtrLbInfo.get(MAP_KEY_SUM_AFT_RESET_MTR_CNT));
        }
        // END 2017/09/20 T.Kanasaka [QC#15134,ADD]
        S21ApiTBLAccessor.insert(fleetReadRollUpTMsg);
        String rtnCd = fleetReadRollUpTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0835E);
            return null;
        }

        return fleetReadRollUpTMsg;
    }

    private void updateErrInfoToFleetReadRollUp(S21ApiMessageMap msgMap, FLEET_READ_ROLL_UPTMsg fleetReadRollUpTMsg) {
        if (MODE_NORMAL.equals(this.xxModeCd)) {
            setValue(fleetReadRollUpTMsg.fleetCalcProcCd, FLEET_CALC_PROC.INCOMPLETE);
        } else if (MODE_REBILL.equals(this.xxModeCd)) {
            setValue(fleetReadRollUpTMsg.fleetCalcProcCd, FLEET_CALC_PROC.REBILL_INCOMPLETE);
        }
        setValue(fleetReadRollUpTMsg.vldMsgTxt, EZDMessageEnv.getMessage(NSZM0841E));
        S21ApiTBLAccessor.update(fleetReadRollUpTMsg);
        String rtnCd = fleetReadRollUpTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0836E);
            return;
        }
    }

    // START 2017/08/24 K.Kitachi [QC#20746, MOD]
//    private void updateDsContrBllgSchd(S21ApiMessageMap msgMap, DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg) {
    private void updateDsContrBllgSchd(S21ApiMessageMap msgMap, DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg, String mtrEntryCpltFlg) {
//        setValue(dsContrBllgSchdTMsg.mtrEntryCpltFlg, ZYPConstant.FLG_ON_Y);
        setValue(dsContrBllgSchdTMsg.mtrEntryCpltFlg, mtrEntryCpltFlg);
        S21ApiTBLAccessor.update(dsContrBllgSchdTMsg);
        String rtnCd = dsContrBllgSchdTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0838E);
            return;
        }
    }
    // END 2017/08/24 K.Kitachi [QC#20746, MOD]

    private FLEET_READ_ROLL_UPTMsgArray getFleetReadRollUp(BigDecimal prntDsContrBllgSchdPk) {
        FLEET_READ_ROLL_UPTMsg tMsg = new FLEET_READ_ROLL_UPTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("prntDsContrBllgSchdPk01", prntDsContrBllgSchdPk);
        return (FLEET_READ_ROLL_UPTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    private FLEET_READ_ROLL_UP_DTLTMsgArray getFleetReadRollUpDtl(BigDecimal fleetReadRollUpPk) {
        FLEET_READ_ROLL_UP_DTLTMsg tMsg = new FLEET_READ_ROLL_UP_DTLTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("fleetReadRollUpPk01", fleetReadRollUpPk);
        return (FLEET_READ_ROLL_UP_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    // Mod Start 2018/01/17 <QC#23486>
    private FLEET_READ_ROLL_UP_DTLTMsgArray getFleetReadRollUpDtlForPrev(BigDecimal fleetReadRollUpPk, BigDecimal svcMachMstrPk, BigDecimal svcPhysMtrPk) {
        FLEET_READ_ROLL_UP_DTLTMsg tMsg = new FLEET_READ_ROLL_UP_DTLTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("fleetReadRollUpPk01", fleetReadRollUpPk);
        tMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        tMsg.setConditionValue("svcPhysMtrPk01", svcPhysMtrPk);
        return (FLEET_READ_ROLL_UP_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }
    // Mod End   2018/01/17 <QC#23486>

    private SVC_CR_REBIL_MTR_READTMsgArray getSvcCrRebilMtrRead(BigDecimal svcCrRebilDtlPk, BigDecimal dsContrDtlPk, String physMtrLbCd) {
        SVC_CR_REBIL_MTR_READTMsg tMsg = new SVC_CR_REBIL_MTR_READTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("svcCrRebilDtlPk01", svcCrRebilDtlPk);
        tMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        tMsg.setConditionValue("physMtrLbCd01", physMtrLbCd);
        return (SVC_CR_REBIL_MTR_READTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    private List<Map<String, Object>> getContrBllgSchdInfo(String dsContrNum, BigDecimal prntDsContrBllgSchdPk, String bllgDt, BigDecimal svcCrRebilDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_9);
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("dsContrNum", dsContrNum);
        ssmPrm.put("dsContrCatgCd", DS_CONTR_CATG.FLEET);
        // START 2016/03/11 T.Kanasaka [QC5374, DEL]
//        ssmPrm.put("dsBllgSchdStsCd", DS_BLLG_SCHD_STS.OPEN);
        // END 2016/03/11 T.Kanasaka [QC5374, DEL]
        if (hasValue(svcCrRebilDtlPk)) {
            ssmPrm.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        } else {
            ssmPrm.put("dsBllgSchdTpCd", DS_BLLG_SCHD_TP.REGULAR);
        }
        ssmPrm.put("invTpCd", svcInvTpCd);
        ssmPrm.put("prntDsContrBllgSchdPk", prntDsContrBllgSchdPk);
        ssmPrm.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.FLEET);
        ssmPrm.put("bllgDt", bllgDt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getContrBllgSchdInfo", ssmPrm);
    }

    private List<Map<String, Object>> getPhysMtrInfo(BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_3);
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPhysMtrInfo", ssmPrm);
    }

    private Map<String, Object> getPrevContrBllgSchdInfo(String dsContrNum, BigDecimal dsContrBllgMtrPk, String bllgDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_5);
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("dsContrNum", dsContrNum);
        ssmPrm.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmPrm.put("bllgDt", bllgDt);
        ssmPrm.put("invTpCd", svcInvTpCd);
        return (Map<String, Object>) ssmBatchClient.queryObject("getPrevContrBllgSchdInfo", ssmPrm);
    }

    private Map<String, Object> getPrevFleetReadRollUpInfo(BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String bllgDt, BigDecimal svcCrRebilDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_5);
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmPrm.put("bllgDt", bllgDt);
        if (hasValue(svcCrRebilDtlPk)) {
            ssmPrm.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        } else {
            ssmPrm.put("dsBllgSchdTpCd", DS_BLLG_SCHD_TP.REGULAR);
        }
        return (Map<String, Object>) ssmBatchClient.queryObject("getPrevFleetReadRollUpInfo", ssmPrm);
    }

    private DS_CONTR_BLLG_SCHDTMsg getDsContrBllgSchd(BigDecimal dsContrBllgSchdPk) {
        DS_CONTR_BLLG_SCHDTMsg param = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(param.glblCmpyCd, this.glblCmpyCd);
        setValue(param.dsContrBllgSchdPk, dsContrBllgSchdPk);
        return (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(param);
    }

    private BigDecimal nullToZero(BigDecimal num) {
        if (num == null) {
            return BigDecimal.ZERO;
        }
        return num;
    }

    // START 2016/10/04 T.Kanasaka [QC#12274, ADD]
    private BigDecimal getTestMtrCnt(Map<String, Object> contrBllgSchdInfoMap, BigDecimal svcPhysMtrPk, String priMtrReadDt, String curMtrReadDt) {
        BigDecimal testMtrCnt = BigDecimal.ZERO;
        // START 2017/10/10 E.Kameishi [QC#18636, ADD]
        BigDecimal dsContrDtlPk = (BigDecimal) contrBllgSchdInfoMap.get("DS_CONTR_DTL_PK");
        BigDecimal dsContrBllgMtrPk = (BigDecimal) contrBllgSchdInfoMap.get("DS_CONTR_BLLG_MTR_PK");
        BigDecimal svcMachMstrPk = (BigDecimal) contrBllgSchdInfoMap.get("SVC_MACH_MSTR_PK");
        // END 2017/10/10 E.Kameishi [QC#18636, ADD]

        if (curMtrReadDt == null) {
            return testMtrCnt;
        }

        Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_9);
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("svcPhysMtrPk", svcPhysMtrPk);
        ssmPrm.put("priMtrReadDt", priMtrReadDt);
        ssmPrm.put("curMtrReadDt", curMtrReadDt);
        ssmPrm.put("testCopyClsCd_In", DS_TEST_COPY_CLS.TEST_COPY_IN);
        ssmPrm.put("testCopyClsCd_Out", DS_TEST_COPY_CLS.TEST_COPY_OUT);
        List<Map<String, Object>> testMtrReadList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getTestMtrCnt", ssmPrm);

        String prevFsrNum = null;
        String prevFsrVisitNum = null;
        // mod start 2017/02/15 CSA QC17489
        String prevSvcTaskNum = null;
        // START 2017/10/10 E.Kameishi [QC#18636, ADD]
        Map<BigDecimal, BigDecimal> sumMap = new HashMap<BigDecimal, BigDecimal>();
        // END 2017/10/10 E.Kameishi [QC#18636, ADD]

        for (int i = 0; i < testMtrReadList.size(); i++) {
            Map<String, Object> testMtrReadInfo = testMtrReadList.get(i);
            String fsrNum = (String) testMtrReadInfo.get("FSR_NUM");
            String fsrVisitNum = (String) testMtrReadInfo.get("FSR_VISIT_NUM");
            String svcTaskNum = (String) testMtrReadInfo.get("SVC_TASK_NUM");
            if (!hasValue(fsrNum) || !hasValue(fsrVisitNum)) {
                if(svcTaskNum.equals(prevSvcTaskNum)) {
                    continue;
                }
            } else if (fsrNum.equals(prevFsrNum) && fsrVisitNum.equals(prevFsrVisitNum)) {
                continue;
            }
            // mod end 2017/02/15 CSA QC17489
            String testCopyInOutCd = (String) testMtrReadInfo.get("DS_TEST_COPY_CLS_CD");
            if (DS_TEST_COPY_CLS.TEST_COPY_OUT.equals(testCopyInOutCd) && i < testMtrReadList.size() - 1) {
                String nextTestCopyInOutCd = (String) (testMtrReadList.get(i + 1)).get("DS_TEST_COPY_CLS_CD");
                if (DS_TEST_COPY_CLS.TEST_COPY_IN.equals(nextTestCopyInOutCd)) {
                    // START 2017/10/25 K.Kojima [QC#21562,ADD]
                    if (!checkTestCopyDtAndStartReadDt(dsContrDtlPk, dsContrBllgMtrPk, (BigDecimal) testMtrReadList.get(i + 1).get("SVC_PHYS_MTR_PK"),
                            (String) testMtrReadInfo.get("MTR_READ_DT"), (BigDecimal) testMtrReadList.get(i + 1).get("READ_MTR_CNT"))) {
                        continue;
                    }
                    // END 2017/10/25 K.Kojima [QC#21562,ADD]
                    testMtrCnt = testMtrCnt.add((BigDecimal) testMtrReadInfo.get("TEST_MTR_CNT"));
                    // START 2017/10/10 E.Kameishi [QC#18636, ADD]
                    BigDecimal testPhysCopyCnt = (BigDecimal) testMtrReadInfo.get("TEST_MTR_CNT");
                    if (sumMap.containsKey(svcPhysMtrPk)) {
                        BigDecimal sumTestPhysCopyCnt = sumMap.get(svcPhysMtrPk).add(testPhysCopyCnt);
                        sumMap.put(svcPhysMtrPk, sumTestPhysCopyCnt);
                    } else {
                        sumMap.put(svcPhysMtrPk, testPhysCopyCnt);
                    }
                    // END 2017/10/10 E.Kameishi [QC#18636, ADD]
                    prevFsrNum = fsrNum;
                    prevFsrVisitNum = fsrVisitNum;
                    // mod start 2017/02/15 CSA QC17489
                    prevSvcTaskNum = svcTaskNum;
                    // mod end 2017/02/15 CSA QC17489
                }
            }
        }
        // START 2017/10/10 E.Kameishi [QC#18636, ADD]
        insertBllgSchdTestMtrSmry(contrBllgSchdInfoMap, sumMap);
        // END 2017/10/10 E.Kameishi [QC#18636, ADD]

        return testMtrCnt;
    }

    private FLEET_READ_ROLL_UPTMsg getFleetReadRollUpByKey(BigDecimal fleetReadRollUpPk) {
        FLEET_READ_ROLL_UPTMsg param = new FLEET_READ_ROLL_UPTMsg();
        setValue(param.glblCmpyCd, this.glblCmpyCd);
        setValue(param.fleetReadRollUpPk, fleetReadRollUpPk);
        return (FLEET_READ_ROLL_UPTMsg) S21ApiTBLAccessor.findByKey(param);
    }
    // END 2016/10/04 T.Kanasaka [QC#12274, ADD]

    // START 2017/09/20 T.Kanasaka [QC#15134,ADD]
    private SvcPhysMtrReadInfoBean getMtrReadInfoByRollUpDtl(FLEET_READ_ROLL_UP_DTLTMsg tMsg) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>(SIZE_3);
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("dsContrBllgSchdPk", tMsg.dsContrBllgSchdPk);
        ssmPrm.put("svcPhysMtrPk", tMsg.svcPhysMtrPk);
        Map<String, Object> mtrReadInfo = (Map<String, Object>) ssmBatchClient.queryObject("getMtrReadInfo", ssmPrm);
        if (mtrReadInfo != null) {
            SvcPhysMtrReadInfoBean mtrReadBean = new SvcPhysMtrReadInfoBean();
            mtrReadBean.setSvcPhysMtrReadPk((BigDecimal) mtrReadInfo.get("SVC_PHYS_MTR_READ_PK"));
            mtrReadBean.setSvcPhysMtrPk((BigDecimal) mtrReadInfo.get("SVC_PHYS_MTR_PK"));
            mtrReadBean.setMtrReadDt((String) mtrReadInfo.get("MTR_READ_DT"));
            mtrReadBean.setReadMtrCnt((BigDecimal) mtrReadInfo.get("READ_MTR_CNT"));
            return mtrReadBean;
        }
        return null;
    }

    private SvcPhysMtrReadInfoBean getMtrReadInfoBySvcPhysMtrRead(SVC_PHYS_MTR_READTMsg tMsg) {
        SvcPhysMtrReadInfoBean mtrReadBean = new SvcPhysMtrReadInfoBean();
        mtrReadBean.setSvcPhysMtrReadPk(tMsg.svcPhysMtrReadPk.getValue());
        mtrReadBean.setSvcPhysMtrPk(tMsg.svcPhysMtrPk.getValue());
        mtrReadBean.setMtrReadDt(tMsg.mtrReadDt.getValue());
        mtrReadBean.setReadMtrCnt(tMsg.readMtrCnt.getValue());
        return mtrReadBean;
    }

    private void insertCalcMtrSchdReln(S21ApiMessageMap msgMap, BigDecimal dsContrBllgSchdPk, SvcPhysMtrReadInfoBean priMtrReadInfo, SvcPhysMtrReadInfoBean curMtrReadInfo) {
        NSXC003001MtrCntTwoPntEst nsxc003001MtrEst = new NSXC003001MtrCntTwoPntEst();
        List<Map<String, Object>> rollOverExchList = nsxc003001MtrEst.getRollOverExchList(this.glblCmpyCd, priMtrReadInfo, curMtrReadInfo);
        if (rollOverExchList == null) {
            return;
        }

        for (Map<String, Object> rollOverExchMap : rollOverExchList) {
            // insert CALC_MTR_SCHD_RELN
            CALC_MTR_SCHD_RELNTMsg calcMtrSchdRelnTMsg = new CALC_MTR_SCHD_RELNTMsg();
            setValue(calcMtrSchdRelnTMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(calcMtrSchdRelnTMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
            setValue(calcMtrSchdRelnTMsg.svcPhysMtrReadPk, (BigDecimal) rollOverExchMap.get("SVC_PHYS_MTR_READ_PK"));
            setValue(calcMtrSchdRelnTMsg.cntrResetTpCd, (String) rollOverExchMap.get("CNTR_RESET_TP_CD"));
            S21ApiTBLAccessor.create(calcMtrSchdRelnTMsg);
            String rtnCd = calcMtrSchdRelnTMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                msgMap.addXxMsgId(NSZM1301E);
                return;
            }
        }
    }

    private CALC_MTR_SCHD_RELNTMsgArray getCalcMtrSchdReln(BigDecimal dsContrBllgSchdPk) {
        CALC_MTR_SCHD_RELNTMsg tMsg = new CALC_MTR_SCHD_RELNTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("dsContrBllgSchdPk01", dsContrBllgSchdPk);
        return (CALC_MTR_SCHD_RELNTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }
    // END 2017/09/20 T.Kanasaka [QC#15134,ADD]

    // START 2017/10/10 E.Kameishi [QC#18636, ADD]
    private void insertBllgSchdTestMtrSmry(Map<String, Object> contrBllgSchdInfoMap, Map<BigDecimal, BigDecimal> sumMap) {
        if (sumMap.size() == 0) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("dsContrDtlPk", (BigDecimal) contrBllgSchdInfoMap.get("DS_CONTR_DTL_PK"));
            param.put("dsContrBllgMtrPk", (BigDecimal) contrBllgSchdInfoMap.get("DS_CONTR_BLLG_MTR_PK"));
            List<Map<String, Object>> svcPhysMtrPkList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcPhysMtrPk", param);

            for (int i = 0; i < svcPhysMtrPkList.size(); i++) {
                Map<String, Object> svcPhysMtrPkInfo = svcPhysMtrPkList.get(i);
                BLLG_SCHD_TEST_MTR_SMRYTMsg inTmsg = new BLLG_SCHD_TEST_MTR_SMRYTMsg();
                setValue(inTmsg.glblCmpyCd, this.glblCmpyCd);
                setValue(inTmsg.dsContrBllgSchdPk, (BigDecimal) contrBllgSchdInfoMap.get("DS_CONTR_BLLG_SCHD_PK"));
                setValue(inTmsg.svcPhysMtrPk, (BigDecimal) svcPhysMtrPkInfo.get("SVC_PHYS_MTR_PK"));
                BLLG_SCHD_TEST_MTR_SMRYTMsg outTmsg = (BLLG_SCHD_TEST_MTR_SMRYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTmsg);

                if (outTmsg != null) {
                    setValue(outTmsg.testMtrCnt, BigDecimal.ZERO);
                    S21ApiTBLAccessor.update(outTmsg);
                } else {
                    setValue(inTmsg.testMtrCnt, BigDecimal.ZERO);
                    S21ApiTBLAccessor.create(inTmsg);
                }
            }
        } else {
            for (BigDecimal svcPhysMtrPk : sumMap.keySet()) {
                BLLG_SCHD_TEST_MTR_SMRYTMsg inTmsg = new BLLG_SCHD_TEST_MTR_SMRYTMsg();
                setValue(inTmsg.glblCmpyCd, this.glblCmpyCd);
                setValue(inTmsg.dsContrBllgSchdPk, (BigDecimal) contrBllgSchdInfoMap.get("DS_CONTR_BLLG_SCHD_PK"));
                setValue(inTmsg.svcPhysMtrPk, svcPhysMtrPk);
                BLLG_SCHD_TEST_MTR_SMRYTMsg outTmsg = (BLLG_SCHD_TEST_MTR_SMRYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTmsg);

                if (outTmsg != null) {
                    setValue(outTmsg.testMtrCnt, sumMap.get(svcPhysMtrPk));
                    // add start 2019/09/04 QC#53124
                    if (this.isExclTestCopy) {
                        setValue(outTmsg.testMtrCnt, BigDecimal.ZERO);
                    }
                    // add end 2019/09/04 QC#53124
                    S21ApiTBLAccessor.update(outTmsg);
                } else {
                    setValue(inTmsg.testMtrCnt, sumMap.get(svcPhysMtrPk));
                    // add start 2019/09/04 QC#53124
                    if (this.isExclTestCopy) {
                        setValue(inTmsg.testMtrCnt, BigDecimal.ZERO);
                    }
                    // add end 2019/09/04 QC#53124
                    S21ApiTBLAccessor.create(inTmsg);
                }
            }
        }
    }

    private void insertBllgSchdTestMtrSmryForRebill(Map<String, Object> contrBllgSchdInfoMap, Map<String, Object> physMtrInfoMap, BigDecimal curTestMtrCnt) {
        BLLG_SCHD_TEST_MTR_SMRYTMsg inTmsg = new BLLG_SCHD_TEST_MTR_SMRYTMsg();
        setValue(inTmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inTmsg.dsContrBllgSchdPk, (BigDecimal) contrBllgSchdInfoMap.get("DS_CONTR_BLLG_SCHD_PK"));
        setValue(inTmsg.svcPhysMtrPk, (BigDecimal) physMtrInfoMap.get("SVC_PHYS_MTR_PK"));

        BLLG_SCHD_TEST_MTR_SMRYTMsg outTmsg = (BLLG_SCHD_TEST_MTR_SMRYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTmsg);

        if (outTmsg != null) {
            setValue(outTmsg.testMtrCnt, curTestMtrCnt);
            S21ApiTBLAccessor.update(outTmsg);
        } else {
            setValue(inTmsg.testMtrCnt, curTestMtrCnt);
            S21ApiTBLAccessor.create(inTmsg);
        }
    }
    // END 2017/10/10 E.Kameishi [QC#18636, ADD]

    // START 2017/10/25 K.Kojima [QC#21562,ADD]
    private boolean checkTestCopyDtAndStartReadDt(BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal svcPhysMtrPk, String mtrReadDt, BigDecimal readMtrCnt) {
        List<Map<String, Object>> startMtrReadList = getStartCopyQty(dsContrDtlPk, dsContrBllgMtrPk, mtrReadDt);
        boolean isCalcTrgt = true;
        if (startMtrReadList != null && startMtrReadList.size() > 0) {
            for (Map<String, Object> startMtrReadInfo : startMtrReadList) {
                if (svcPhysMtrPk.compareTo((BigDecimal) startMtrReadInfo.get("SVC_PHYS_MTR_PK")) == 0) {
                    if (readMtrCnt.compareTo((BigDecimal) startMtrReadInfo.get("READ_MTR_CNT")) < 0) {
                        isCalcTrgt = false;
                    }
                    break;
                }
            }
        }
        return isCalcTrgt;
    }

    private List<Map<String, Object>> getStartCopyQty(BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String mtrReadDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        param.put("mtrReadDt", mtrReadDt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getStartCopyQty", param);
    }
    // END 2017/10/25 K.Kojima [QC#21562,ADD]

    // START 2018/06/07 K.Kitachi [QC#20750, ADD]
    private SvcPhysMtrReadInfoBean getPrevMtrReadBean(SVC_CR_REBIL_MTR_READTMsg svcCrRebilMtrReadTMsg) {
        SvcPhysMtrReadInfoBean mtrReadBean = new SvcPhysMtrReadInfoBean();
        mtrReadBean.setSvcPhysMtrReadPk(svcCrRebilMtrReadTMsg.startSvcPhysMtrReadPk.getValue());
        mtrReadBean.setSvcPhysMtrPk(svcCrRebilMtrReadTMsg.svcPhysMtrPk.getValue());
        mtrReadBean.setMtrReadDt(svcCrRebilMtrReadTMsg.startMtrReadDt.getValue());
        if (hasValue(svcCrRebilMtrReadTMsg.newStartReadMtrCnt)) {
            mtrReadBean.setReadMtrCnt(svcCrRebilMtrReadTMsg.newStartReadMtrCnt.getValue());
        } else {
            mtrReadBean.setReadMtrCnt(svcCrRebilMtrReadTMsg.oldStartReadMtrCnt.getValue());
        }
        return mtrReadBean;
    }

    private SvcPhysMtrReadInfoBean getCurrMtrReadBean(SVC_CR_REBIL_MTR_READTMsg svcCrRebilMtrReadTMsg) {
        SvcPhysMtrReadInfoBean mtrReadBean = new SvcPhysMtrReadInfoBean();
        mtrReadBean.setSvcPhysMtrReadPk(svcCrRebilMtrReadTMsg.endSvcPhysMtrReadPk.getValue());
        mtrReadBean.setSvcPhysMtrPk(svcCrRebilMtrReadTMsg.svcPhysMtrPk.getValue());
        mtrReadBean.setMtrReadDt(svcCrRebilMtrReadTMsg.endMtrReadDt.getValue());
        if (hasValue(svcCrRebilMtrReadTMsg.newEndReadMtrCnt)) {
            mtrReadBean.setReadMtrCnt(svcCrRebilMtrReadTMsg.newEndReadMtrCnt.getValue());
        } else {
            mtrReadBean.setReadMtrCnt(svcCrRebilMtrReadTMsg.oldEndReadMtrCnt.getValue());
        }
        return mtrReadBean;
    }

    private void updateBllgSchdTestMtrSmry(BigDecimal dsContrBllgSchdPk, BigDecimal svcPhysMtrPk, SvcPhysMtrReadInfoBean prevMtrReadBean, SvcPhysMtrReadInfoBean currMtrReadBean, BigDecimal rollOverExchCnt, BigDecimal contrMtrMultRate) {
        BLLG_SCHD_TEST_MTR_SMRYTMsg inTmsg = new BLLG_SCHD_TEST_MTR_SMRYTMsg();
        setValue(inTmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inTmsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        setValue(inTmsg.svcPhysMtrPk, svcPhysMtrPk);
        BLLG_SCHD_TEST_MTR_SMRYTMsg outTmsg = (BLLG_SCHD_TEST_MTR_SMRYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTmsg);
        if (outTmsg == null) {
            return;
        }
        if (prevMtrReadBean != null) {
            setValue(outTmsg.startReadMtrCnt, prevMtrReadBean.getReadMtrCnt());
        }
        if (currMtrReadBean != null) {
            setValue(outTmsg.endReadMtrCnt, currMtrReadBean.getReadMtrCnt());
        }
        if (prevMtrReadBean != null && currMtrReadBean != null) {
            // mod start 2019/09/04 QC#53124
            //BigDecimal totCopyQty = currMtrReadBean.getReadMtrCnt().subtract(prevMtrReadBean.getReadMtrCnt()).subtract(outTmsg.testMtrCnt.getValue());
            BigDecimal totCopyQty = BigDecimal.ZERO;
            if (this.isExclTestCopy) {
                totCopyQty = currMtrReadBean.getReadMtrCnt().subtract(prevMtrReadBean.getReadMtrCnt()).subtract(BigDecimal.ZERO);
            } else {
                totCopyQty = currMtrReadBean.getReadMtrCnt().subtract(prevMtrReadBean.getReadMtrCnt()).subtract(outTmsg.testMtrCnt.getValue());
            }
            // mod end 2019/09/04 QC#53124
            if (rollOverExchCnt.compareTo(BigDecimal.ZERO) > 0) {
                totCopyQty = totCopyQty.add(rollOverExchCnt);
            }
            setValue(outTmsg.totCopyQty, totCopyQty);
        }
        setValue(outTmsg.contrMtrMultRate, contrMtrMultRate);
        S21ApiTBLAccessor.update(outTmsg);
    }
    // END 2018/06/07 K.Kitachi [QC#20750, ADD]
}
