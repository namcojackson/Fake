package com.canon.cusa.s21.api.NSZ.NSZC047001;

import static com.canon.cusa.s21.api.NSZ.NSZC047001.constant.NSZC047001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.BLLG_CYCLETMsg;
import business.db.CCYTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHD_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsg;
import business.db.SVC_CONTR_BASE_BLLGTMsg;
import business.db.SVC_CONTR_BLLGTMsg;
import business.db.SVC_CONTR_BLLG_ALLOCTMsg;
import business.db.SVC_INVTMsg;
import business.db.SVC_INV_LINETMsg;
import business.db.SVC_INV_LINE_ALLOCTMsg;
import business.db.SVC_INV_LINE_ALLOCTMsgArray;
import business.parts.NSZC047006PMsg;
import business.parts.NWZC036101PMsg;
import business.parts.NWZC036101_taxCalculateOutputLinePMsg;

import com.canon.cusa.s21.common.NSX.NSXC003001.CalcCreditAddlChrgAmtForTerminateBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcCreditAmtForTerminateBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CallTaxCalcAPIForBaseBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcCreditAmtForTerminate;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CallTaxCalcAPIForBase;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001Exchange;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CPLT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_PRC_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MAN_TRMN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Contract Billing Schedule API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/14/2015   Hitachi         T.Aoyagi        Create          N/A
 * 01/21/2016   Hitachi         K.Kishimoto     Update          QC#3331
 * 01/28/2016   Hitachi         T.Aoyagi        Update          QC#3095
 * 02/02/2016   Hitachi         K.Kishimoto     Update          QC#4003
 * 2016/02/10   Hitachi         T.Aoyagi        Update          QC3690
 * 2016/02/19   Hitachi         T.Aoyagi        Update          QC3694
 * 2016/02/26   Hitachi         T.Aoyagi        Update          QC3419
 * 2016/03/15   Hitachi         T.Aoyagi        Update          QC#5492
 * 03/28/2016   Hitachi         K.Kishimoto     Update          QC#1003
 * 03/31/2016   Hitachi         K.Kishimoto     Update          QC#6343
 * 04/18/2016   Hitachi         T.Aoyagi        Update          QC#7056
 * 02/28/2017   Hitachi         K.Kishimoto     Update          QC#17809
 * 06/29/2017   Hitachi         A.Kohinata      Update          QC#18349
 * 07/21/2017   Hitachi         T.Tomita        Update          QC#20045
 * 08/28/2017   Hitachi         K.Kim           Update          QC#20760
 * 09/14/2017   Hitachi         K.Kim           Update          QC#20040
 * 09/27/2017   Hitachi         K.Kim           Update          QC#20383
 * 10/03/2017   Hitachi         E.Kameishi      Update          QC#18636
 * 2017/10/16   Hitachi         K.Kitachi       Update          QC#21683
 * 2017/10/30   Hitachi         K.Kitachi       Update          QC#21449
 * 2017/11/21   Hitachi         K.Kojima        Update          QC#22653
 * 2017/11/24   Hitachi         T.Tomita        Update          QC#22683
 * 2017/11/27   Hitachi         T.Tomita        Update          QC#21724
 * 2017/12/04   Hitachi         K.Kishimoto     Update          QC#22883
 * 2017/12/07   Hitachi         K.Kishimoto     Update          QC#22935
 * 2018/05/23   Hitachi         K.Kojima        Update          QC#23302
 * 2018/06/20   Hitachi         T.Tomita        Update          QC#26766
 * 07/12/2018   Hitachi         K.Kishimoto     Update          QC#25959
 * 2018/12/18   Hitachi         K.Kitachi       Update          QC#29659
 * 2019/02/14   Hitachi         K.Kitachi       Update          QC#30066
 * 2019/04/26   Hitachi         K.Fujimoto      Update          QC#31137/50058
 * 2019/05/17   Hitachi         K.Fujimoto      Update          QC#50338
 * 2019/08/14   Hitachi         T.Aoyagi        Update          QC#52451
 * 2019/08/14   Hitachi         T.Aoyagi        Update          QC#52451-2
 * 2019/09/24   Hitachi         K.Kim           Update          QC#53358
 * 2019/10/08   Hitachi         K.Kim           Update          QC#53962
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/03/14   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/04/27   CITS            T.Suzuki        Update          QC#59979
 * 2022/07/07   Hitachi         A.Kohinata      Update          QC#60167
 * 2023/09/04   CITS            T.Aizawa        Update          QC#60739
 * 2023/10/26   Hitachi         H.Iinuma        Update          QC#61675
 * </pre>
 */
public class M06TerminationProcess implements ZYPConstant {

    /** onBatchTp */
    private ONBATCH_TYPE onBatchTp;

    /** contrInfoList */
    private List<Map<String, Object>> contrInfoList;

    /** isTerminatedBeforeStartDate */
    private boolean isTerminatedBeforeStartDate;

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        this.onBatchTp = onBatchType;
        checkParameter(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        terminationProcess(msgMap);
    }

    private void terminationProcess(S21ApiMessageMap msgMap) {

        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        String dsContrDtlTp = NSZC0470Query.getInstance().getDsContrDtlTp(glblCmpyCd, pMsg.dsContrDtlPk.getValue());
        this.contrInfoList = NSZC0470Query.getInstance().getContrInfo(pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue());
        String trmnDt = pMsg.trmnDt.getValue();
        BigDecimal crAmt = pMsg.crAmt.getValue();
        BigDecimal xtrCrAmt = BigDecimal.ZERO;
        // START 2022/03/14 K.Kitachi [QC#59684, ADD]
        String manTrmnTpCd = pMsg.manTrmnTpCd.getValue();
        // END 2022/03/14 K.Kitachi [QC#59684, ADD]
        // START 2023/09/04 t.aizawa [QC#60739, ADD]
        this.isTerminatedBeforeStartDate = false;
        String contrEffFromDt = (String) this.contrInfoList.get(0).get("CONTR_EFF_FROM_DT");
        if (ZYPDateUtil.compare(trmnDt, contrEffFromDt) < 0) {
            trmnDt = contrEffFromDt;
            manTrmnTpCd = MAN_TRMN_TP.ALL_PERIOD;
            this.isTerminatedBeforeStartDate = true;
            setValue(pMsg.trmnDt, trmnDt);
            setValue(pMsg.manTrmnTpCd, manTrmnTpCd);
        }
        // END   2023/09/04 t.aizawa [QC#60739, ADD]

        // --------------------
        // Base Charge
        // --------------------
        // mod start 2017/06/29 QC#18349
        String baseChrgFlg = FLG_ON_Y;
        Map<String, Object> bllgSchdMap = NSZC0470Query.getInstance().getBllgSchdForTrmn(glblCmpyCd, dsContrDtlPk, null, baseChrgFlg, trmnDt);
        if (bllgSchdMap != null) {
            String bllgSchdThruDt = (String) bllgSchdMap.get("BLLG_SCHD_THRU_DT");
            String invFlg = (String) bllgSchdMap.get("INV_FLG");
            String svcInvNum = (String) bllgSchdMap.get("SVC_INV_NUM");
            boolean isBillWithEquip = false;


            // ADD START 2022/04/27 QC#59979
            if (ZYPConstant.FLG_OFF_N.equals(invFlg)) {
                String nextSchdThruDt = NSZC0470Query.getInstance().getInvedMaxBllgSchdThruDtForBase(glblCmpyCd, dsContrDtlPk, bllgSchdThruDt);
                if (hasValue(nextSchdThruDt)) {
                    bllgSchdMap = NSZC0470Query.getInstance().getBllgSchdForTrmn(glblCmpyCd, dsContrDtlPk, null, baseChrgFlg, nextSchdThruDt);
                    bllgSchdThruDt = (String) bllgSchdMap.get("BLLG_SCHD_THRU_DT");
                    invFlg = (String) bllgSchdMap.get("INV_FLG");
                    svcInvNum = (String) bllgSchdMap.get("SVC_INV_NUM");
                }
            }
            // ADD END 2022/04/27 QC#59979

            if (ZYPConstant.FLG_ON_Y.equals(invFlg)) {
                // MOD START 2022/05/09 QC#59979 [CCI-QC#3578, MOD]
                boolean isExistRefundSchd = true;
                // MOD END   2022/05/09 QC#59979 [CCI-QC#3578, MOD]
                if (!hasValue(svcInvNum)) {
                    // MOD START 2022/05/09 QC#59979 [CCI-QC#3578, MOD]
                    // isBillWithEquip = true;
                    BigDecimal count = NSZC0470Query.getInstance().countRefundBllgSchd(glblCmpyCd, dsContrDtlPk, trmnDt);
                    if (BigDecimal.ZERO.compareTo(count) == 0) {
                        isExistRefundSchd = false;
                        count = NSZC0470Query.getInstance().countUnbilledBllgSchd(glblCmpyCd, dsContrDtlPk, trmnDt);
                        if (BigDecimal.ZERO.compareTo(count) == 0) {
                            isBillWithEquip = true;
                        }
                    }
                    // MOD END   2022/05/09 QC#59979 [CCI-QC#3578, MOD]
                }

                if (ZYPConstant.FLG_ON_Y.equals(pMsg.supprCrFlg.getValue())) {
                    crAmt = BigDecimal.ZERO;
                } else if (isBillWithEquip) {
                    crAmt = BigDecimal.ZERO;
                // MOD START 2022/05/09 QC#59979 [CCI-QC#3578, MOD]
                } else if (!isExistRefundSchd) {
                    crAmt = BigDecimal.ZERO;
                // MOD END   2022/05/09 QC#59979 [CCI-QC#3578, MOD]
                } else if (!hasValue(crAmt)) {
                    crAmt = calcCreditAmtForTerminate(pMsg);
                }
                setValue(pMsg.crAmt, crAmt);

                String lastBilledDt = null;
                if (!isBillWithEquip) {
                    lastBilledDt = NSZC0470Query.getInstance().getInvedMaxBllgSchdThruDtForBase(glblCmpyCd, dsContrDtlPk, bllgSchdThruDt);
                }
                if (!hasValue(lastBilledDt)) {
                    lastBilledDt = bllgSchdThruDt;
                }

                deletePrcEffAndSchdInfo(msgMap, dsContrDtlTp, null, baseChrgFlg, lastBilledDt);
                updatePrcEffAndSchdInfoForBase(msgMap, lastBilledDt);

                if (hasValue(crAmt) && crAmt.compareTo(BigDecimal.ZERO) > 0) {
                    createCreditInfo(msgMap, bllgSchdMap, lastBilledDt, baseChrgFlg);
                }
            } else {
                // invFlg = "N"
                deletePrcEffAndSchdInfo(msgMap, dsContrDtlTp, null, baseChrgFlg, trmnDt);
                updatePrcEffAndSchdInfoForUnBilled(msgMap, dsContrDtlTp, bllgSchdMap, baseChrgFlg);
                // START 2019/10/08 [QC#53962, ADD]
                if (DS_CONTR_DTL_TP.ACCESSORIES.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
                    NSZC047001CommonLogic.recovAccSchdReln(msgMap, pMsg.glblCmpyCd.getValue(), (BigDecimal) this.contrInfoList.get(0).get("DS_CONTR_PK"));
                }
                // END 2019/10/08 [QC#53962, ADD]
            }

            // Add Start 2017/02/28 <QC#17809>
            List<Map<String, Object>> contrDtlList = NSZC0470Query.getInstance().getSumAggContrDtl(glblCmpyCd, dsContrDtlPk);
            for (Map<String, Object> ContrDtl : contrDtlList) {
                BigDecimal contrDtlPk = (BigDecimal) ContrDtl.get("DS_CONTR_DTL_PK");
                DS_CONTR_DTLTMsg contrDtlTMsg = NSZC0470Query.getInstance().getDsContrDtlTMsg(glblCmpyCd, contrDtlPk);
                if (contrDtlTMsg == null) {
                    continue;
                }
                setValue(contrDtlTMsg.basePrcTermDealAmtRate, (BigDecimal) ContrDtl.get("TERM_AMT_RATE"));
                S21ApiTBLAccessor.update(contrDtlTMsg);
            }
            // Add End 2017/02/28 <QC#17809>
        }
        // mod end 2017/06/29 QC#18349

        // --------------------
        // Usage
        // --------------------
        // START 2016/01/28 T.Aoyagi [CSA-QC#3095,MOVE]
        baseChrgFlg = FLG_OFF_N;
        // END   2016/01/28 T.Aoyagi [CSA-QC#3095,MOVE]
        List<BigDecimal> bllgMtrPkList = NSZC0470Query.getInstance().getBllgMtrPk(glblCmpyCd, dsContrDtlPk);
        for (BigDecimal bllgMtrPk : bllgMtrPkList) {

            String lastBilledDt = NSZC0470Query.getInstance().getInvedMaxBllgSchdThruDt(glblCmpyCd, dsContrDtlPk, bllgMtrPk, baseChrgFlg);
            String thruDt = lastBilledDt;
            // START 2019/09/24 [QC#53358, MOD]
            // if (!hasValue(thruDt) || thruDt.compareTo(trmnDt) <= 0) {
            if (!hasValue(thruDt) || thruDt.compareTo(trmnDt) < 0) {
            // END 2019/09/24 [QC#53358, MOD]
                thruDt = trmnDt;
                lastBilledDt = null;
            }
            bllgSchdMap = NSZC0470Query.getInstance().getBllgSchdForTrmn(glblCmpyCd, dsContrDtlPk, bllgMtrPk, baseChrgFlg, thruDt);
            if (bllgSchdMap == null) {
                continue;
            }

            if (hasValue(lastBilledDt)) {
                deletePrcEffAndSchdInfo(msgMap, dsContrDtlTp, bllgMtrPk, baseChrgFlg, lastBilledDt);

                bllgSchdMap = NSZC0470Query.getInstance().getBllgSchdForTrmn(glblCmpyCd, dsContrDtlPk, bllgMtrPk, baseChrgFlg, lastBilledDt);
                updatePrcEffAndSchdInfo(msgMap, bllgSchdMap, baseChrgFlg, xtrCrAmt, lastBilledDt);

            } else {
                deletePrcEffAndSchdInfo(msgMap, dsContrDtlTp, bllgMtrPk, baseChrgFlg, trmnDt);
                updatePrcEffAndSchdInfoForUnBilled(msgMap, dsContrDtlTp, bllgSchdMap, baseChrgFlg);
            }
            // START 2022/03/14 K.Kitachi [QC#59684, ADD]
            // START 2023/10/26 H.Iinuma [QC#61675, MOD]
            // if (MAN_TRMN_TP.ALL_PERIOD.equals(manTrmnTpCd)) {
            if (MAN_TRMN_TP.ALL_PERIOD.equals(manTrmnTpCd) || (hasValue(lastBilledDt) && ZYPDateUtil.compare(trmnDt, lastBilledDt) < 0)) {
            // END 2023/10/26 H.Iinuma [QC#61675, MOD]
                updateInvoicedFlag(msgMap, bllgMtrPk);
            }
            // END 2022/03/14 K.Kitachi [QC#59684, ADD]
        }
        // Add Start 03/28/2016 <QC#1003>
        if (bllgMtrPkList.size() > 0) {
            NSZC047001CommonLogic.mtrEntryStsUpd(msgMap, glblCmpyCd, pMsg.slsDt.getValue(), (BigDecimal) contrInfoList.get(0).get("DS_CONTR_PK"));
        }
        // Add End   03/28/2016 <QC#1003>
    }

    private void checkParameter(S21ApiMessageMap msgMap) {

        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.glblCmpyCd, ZZZM9007E, new String[]{"Global Company Code"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.slsDt, ZZZM9007E, new String[]{"Salse Date"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.dsContrDtlPk, ZZZM9007E, new String[]{"DS Contract Detail PK"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.trmnDt, ZZZM9007E, new String[]{"Termination Date"});
    }

    private void deletePrcEffAndSchdInfo(S21ApiMessageMap msgMap, String dsContrDtlTp, BigDecimal dsContrBllgMtrPk, String baseChrgFlg, String thruDt) {

        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();

        String usgChrgFlg = FLG_ON_Y;
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            usgChrgFlg = FLG_OFF_N;
        }
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();

        List<Map<String, BigDecimal>> deleteBllgSchdList = NSZC0470Query.getInstance().getBllgSchdForDelete(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, thruDt, baseChrgFlg);
        List<BigDecimal> prcEffPkList = NSZC047001CommonLogic.getPrcEffPkList(deleteBllgSchdList);
        List<BigDecimal> schdSmryPkList = NSZC047001CommonLogic.getSchdSmryPkList(deleteBllgSchdList);
        List<BigDecimal> schdPkList = NSZC047001CommonLogic.getSchdPkList(deleteBllgSchdList);

        for (BigDecimal schdPk : schdPkList) {
            DS_CONTR_BLLG_SCHDTMsg schdTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, schdPk);
            if (thruDt.compareTo(schdTMsg.bllgSchdFromDt.getValue()) < 0) {
                // Add Start 01/21/2016 <QC#3331>
                NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, schdPk);
                // Add Start 01/21/2016 <QC#3331>
                NSZC0470Query.getInstance().removeSchd(glblCmpyCd, schdPk);
                // START 2017/10/03 E.Kameishi [QC18636, ADD]
                NSZC0470Query.getInstance().removeSchdTestMtrSmry(glblCmpyCd, schdPk);
                // END 2017/10/03 E.Kameishi [QC18636, ADD]

                if (FLG_ON_Y.equals(usgChrgFlg) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTp)) {
                    List<BigDecimal> prntSchdPkList = NSZC0470Query.getInstance().getDeleteChildSchdList(glblCmpyCd, schdPk);
                    // START 2017/10/30 K.Kitachi [QC#21449, ADD]
                    NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, prntSchdPkList);
                    // END 2017/10/30 K.Kitachi [QC#21449, ADD]
                    NSZC0470Query.getInstance().removeSchd(glblCmpyCd, prntSchdPkList);
                    // START 2017/10/03 E.Kameishi [QC18636, ADD]
                    NSZC0470Query.getInstance().removeSchdTestMtrSmry(glblCmpyCd, prntSchdPkList);
                    // END 2017/10/03 E.Kameishi [QC18636, ADD]
                }
            }
        }

        for (BigDecimal schdSmryPk : schdSmryPkList) {

            DS_CONTR_BLLG_SCHD_SMRYTMsg shcdSmryTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, schdSmryPk);
            if (thruDt.compareTo(shcdSmryTMsg.bllgSchdFromDt.getValue()) < 0) {
                NSZC0470Query.getInstance().removeSchdSmry(glblCmpyCd, schdSmryPk);

                if (FLG_ON_Y.equals(usgChrgFlg)) {
                    List<BigDecimal> schdMtrPkList = NSZC0470Query.getInstance().getDsContrBllgSchdMtrForCancel(glblCmpyCd, schdSmryPk);
                    NSZC0470Query.getInstance().removeSchdMtr(glblCmpyCd, schdMtrPkList);
                }
            }
        }

        for (BigDecimal prcEffPk : prcEffPkList) {

            DS_CONTR_PRC_EFFTMsg prcEffTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, prcEffPk);
            if (thruDt.compareTo(prcEffTMsg.contrPrcEffFromDt.getValue()) < 0) {
                NSZC0470Query.getInstance().removePrcEff(glblCmpyCd, prcEffPk);

                if (FLG_ON_Y.equals(usgChrgFlg)) {
                    List<BigDecimal> prcEffMtrPkList = NSZC0470Query.getInstance().getDeletePrcEffMtrList(glblCmpyCd, prcEffPk);
                    NSZC0470Query.getInstance().removePrcEffMtr(glblCmpyCd, prcEffMtrPkList);
                }
            }
        }
    }

    private void updatePrcEffAndSchdInfo(S21ApiMessageMap msgMap, Map<String, Object> bllgSchdMap, String baseChrgFlg, BigDecimal xtrCrAmt, String lastBilledDt) {

        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String thruDt = pMsg.trmnDt.getValue();
        BigDecimal schdSmryPk = (BigDecimal) bllgSchdMap.get("DS_CONTR_BLLG_SCHD_SMRY_PK");
        BigDecimal prcEffPk = (BigDecimal) bllgSchdMap.get("DS_CONTR_PRC_EFF_PK");
        // START 2017/08/28 K.Kim [QC#20760, ADD]
        String slsDt = pMsg.slsDt.getValue();
        // END 2017/08/28 K.Kim [QC#20760, ADD]

        if (FLG_ON_Y.equals(baseChrgFlg)) {
            updateSchd(msgMap, bllgSchdMap, xtrCrAmt);
        } else {
            thruDt = lastBilledDt;
        }

        // START 2016/02/02 K.Kishimoto [QC#4003, MOD]
        DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, schdSmryPk);
        if (schdSmryTMsg != null) {
            updateSchdSmry(msgMap, schdSmryTMsg, thruDt, baseChrgFlg);
        }

        DS_CONTR_PRC_EFFTMsg prcEffTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, prcEffPk);
        if (prcEffTMsg != null) {
            // START 2017/08/28 K.Kim [QC#20760, MOD]
            updatePrcEff(msgMap, prcEffTMsg, thruDt, baseChrgFlg, slsDt);
            // END 2017/08/28 K.Kim [QC#20760, MOD]
        }
        // END 2016/02/02 K.Kishimoto [QC#4003, MOD]
    }

    private void updateSchd(S21ApiMessageMap msgMap, Map<String, Object> bllgSchdMap, BigDecimal xtrCrAmt) {

        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String trmnDt = pMsg.trmnDt.getValue();
        BigDecimal dsContrBllgSchdPk = (BigDecimal) bllgSchdMap.get("DS_CONTR_BLLG_SCHD_PK");

        DS_CONTR_BLLG_SCHDTMsg schdTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, dsContrBllgSchdPk);
        if (schdTMsg != null) {
            BigDecimal baseActlPrcDealAmt = schdTMsg.baseActlPrcDealAmt.getValue();
            if (hasValue(xtrCrAmt)) {
                baseActlPrcDealAmt = baseActlPrcDealAmt.subtract(xtrCrAmt);
                // Add 03/31/2016 Start <QC#6343>
                if (BigDecimal.ZERO.compareTo(baseActlPrcDealAmt) > 0) {
                    baseActlPrcDealAmt = BigDecimal.ZERO;
                }
                // Add 03/31/2016 Start <QC#6343>
            }
            setValue(schdTMsg.baseActlPrcDealAmt, baseActlPrcDealAmt);
            setValue(schdTMsg.bllgSchdThruDt, trmnDt);

            S21ApiTBLAccessor.update(schdTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[]{"DS_CONTR_BLLG_SCHD"});
            }
        }
    }

    private void updateSchdSmry(S21ApiMessageMap msgMap, DS_CONTR_BLLG_SCHD_SMRYTMsg inTMsg, String thruDt, String baseChrgFlg) {

        String glblCmpyCd = inTMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = inTMsg.dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = inTMsg.dsContrBllgMtrPk.getValue();
        String bllgSchdFromDt = inTMsg.bllgSchdFromDt.getValue();

        Map<String, BigDecimal> smryBasePrcAmt = NSZC0470Query.getInstance().getSummaryBasePrcAmt(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, bllgSchdFromDt, thruDt, baseChrgFlg);

        if (smryBasePrcAmt != null) {
            if (FLG_ON_Y.equals(baseChrgFlg)) {
                setValue(inTMsg.basePrcDealAdjAmt, smryBasePrcAmt.get("BASE_PRC_ADJ_DEAL_AMT"));
                setValue(inTMsg.baseSubTotPrcDealAmt, smryBasePrcAmt.get("BASE_ACTL_PRC_DEAL_AMT"));
            }
            setValue(inTMsg.perSchdNum, smryBasePrcAmt.get("PER_SCHD_NUM"));
        }
        setValue(inTMsg.bllgSchdThruDt, thruDt);
        S21ApiTBLAccessor.update(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[]{"DS_CONTR_BLLG_SCHD_SMRY"});
        }
    }

    // START 2017/08/28 K.Kim [QC#20760, MOD]
    private void updatePrcEff(S21ApiMessageMap msgMap, DS_CONTR_PRC_EFFTMsg inTMsg, String thruDt, String baseChrgFlg, String slsDt) {
    // END 2017/08/28 K.Kim [QC#20760, MOD]

        if (FLG_ON_Y.equals(baseChrgFlg)) {
            String glblCmpyCd = inTMsg.glblCmpyCd.getValue();
            BigDecimal dsContrDtlPk = inTMsg.dsContrDtlPk.getValue();
            BigDecimal dsContrBllgMtrPk = inTMsg.dsContrBllgMtrPk.getValue();
            String bllgSchdFromDt = inTMsg.contrPrcEffFromDt.getValue();

            Map<String, BigDecimal> smryBasePrcAmt = NSZC0470Query.getInstance().getSummaryBasePrcAmt(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, bllgSchdFromDt, thruDt, baseChrgFlg);

            if (smryBasePrcAmt != null) {
                setValue(inTMsg.basePrcTermDealAmtRate, smryBasePrcAmt.get("BASE_ACTL_PRC_DEAL_AMT"));
            }
        }
        setValue(inTMsg.contrPrcEffThruDt, thruDt);
        // Mod Start 2017/11/27 QC#21724
        // START 2017/08/28 K.Kim [QC#20760, ADD]
        if (slsDt.compareTo(thruDt) > 0) {
            setValue(inTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.TERMINATED);
        }
        // END 2017/08/28 K.Kim [QC#20760, ADD]
        // Mod End 2017/11/27 QC#21724
        S21ApiTBLAccessor.update(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[]{"DS_CONTR_PRC_EFF"});
        }
    }

    private void updatePrcEffAndSchdInfoForUnBilled(S21ApiMessageMap msgMap, String dsContrDtlTp, Map<String, Object> bllgSchdMap, String baseChrgFlg) {

        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String trmnDt = pMsg.trmnDt.getValue();
        BigDecimal schdSmryPk = (BigDecimal) bllgSchdMap.get("DS_CONTR_BLLG_SCHD_SMRY_PK");
        BigDecimal prcEffPk = (BigDecimal) bllgSchdMap.get("DS_CONTR_PRC_EFF_PK");
        // START 2017/08/28 K.Kim [QC#20760, ADD]
        String slsDt = pMsg.slsDt.getValue();
        // END 2017/08/28 K.Kim [QC#20760, ADD]

        // START 2016/02/02 K.Kishimoto [QC#4003, MOD]
        DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, schdSmryPk);

        // START 2016/02/26 T.Aoyagi [QC3419, MOD]
        // get billing schedule of before update
        BigDecimal schdPk = (BigDecimal) bllgSchdMap.get("DS_CONTR_BLLG_SCHD_PK");
        DS_CONTR_BLLG_SCHDTMsg origSchdTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, schdPk);

        DS_CONTR_BLLG_SCHDTMsg schdTMsg = updateSchdForUnBilled(msgMap, dsContrDtlTp, bllgSchdMap, schdSmryTMsg, baseChrgFlg);
        if (schdSmryTMsg != null) {
            updateSchdSmryForUnBilled(msgMap, schdSmryTMsg, schdTMsg, trmnDt, baseChrgFlg, origSchdTMsg);
        }
        // END 2016/02/26 T.Aoyagi [QC3419, MOD]

        DS_CONTR_PRC_EFFTMsg prcEffTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, prcEffPk);
        if (prcEffTMsg != null) {
            // START 2017/08/28 K.Kim [QC#20760, MOD]
            updatePrcEff(msgMap, prcEffTMsg, trmnDt, baseChrgFlg, slsDt);
            // END 2017/08/28 K.Kim [QC#20760, MOD]
        }
        // END 2016/02/02 K.Kishimoto [QC#4003, MOD]
        // Add Start 2018/06/20 QC#26766
        NSZC047001CommonLogic.clearSvcContrBllg(msgMap, schdTMsg);
        // Add End 2018/06/20 QC#26766
    }

    private DS_CONTR_BLLG_SCHDTMsg updateSchdForUnBilled(S21ApiMessageMap msgMap, String dsContrDtlTp, Map<String, Object> bllgSchdMap, DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg, String baseChrgFlg) {

        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String trmnDt = pMsg.trmnDt.getValue();
        BigDecimal dsContrBllgSchdPk = (BigDecimal) bllgSchdMap.get("DS_CONTR_BLLG_SCHD_PK");

        DS_CONTR_BLLG_SCHDTMsg schdTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, dsContrBllgSchdPk);
        if (schdTMsg != null) {
            String fromDt = schdTMsg.bllgSchdFromDt.getValue();
            String thruDt = schdTMsg.bllgSchdThruDt.getValue();

            if (trmnDt.equals(thruDt)) {
                // START 2019/08/14 T.Aoyagi [QC#52451, ADD]
                setValue(schdTMsg.bllgStageFlg, FLG_OFF_N);
                setValue(schdTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.OPEN);
                setValue(schdTMsg.bllgCalcFlg, FLG_OFF_N);
                // START 2019/08/20 [QC#52451-3, ADD]
                if (FLG_OFF_N.equals(baseChrgFlg)) {
                    // Usage Charge
                    if (!DS_CONTR_CATG.REGULAR.equals((String) this.contrInfoList.get(0).get("DS_CONTR_CATG_CD"))) {
                        DS_CONTR_BLLG_SCHDTMsg prntBllgSchdTMsg = null;

                        // Get BllgSchd of Fleet line / Aggregate line.
                        if (DS_CONTR_DTL_TP.ACCESSORIES.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
                            DS_CONTR_BLLG_SCHDTMsg machSchdTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, schdTMsg.prntDsContrBllgSchdPk.getValue());
                            if (machSchdTMsg != null) {
                                prntBllgSchdTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, machSchdTMsg.prntDsContrBllgSchdPk.getValue());
                            }
                        } else {
                            prntBllgSchdTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, schdTMsg.prntDsContrBllgSchdPk.getValue());
                        }

                        // Clear status of Fleet line / Aggregate line.
                        if (prntBllgSchdTMsg != null) {
                            setValue(prntBllgSchdTMsg.bllgStageFlg, FLG_OFF_N);
                            setValue(prntBllgSchdTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.OPEN);
                            setValue(prntBllgSchdTMsg.bllgCalcFlg, FLG_OFF_N);
                            S21ApiTBLAccessor.update(prntBllgSchdTMsg);
                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prntBllgSchdTMsg.getReturnCode())) {
                                msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[]{"DS_CONTR_BLLG_SCHD"});
                            }
                            setValue(schdTMsg.nextBllgDt, prntBllgSchdTMsg.nextBllgDt.getValue());
                        }
                    }
                }
                // END 2019/08/20 [QC#52451-3, ADD]
                // START 2023/09/04 t.aizawa [QC#60739, ADD]
                if (isTerminatedBeforeStartDate) {
                    setValue(schdTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.CLOSE);
                    if (FLG_ON_Y.equals(baseChrgFlg)) {
                        setValue(schdTMsg.baseActlPrcDealAmt, BigDecimal.ZERO);
                    }
                }
                // END   2023/09/04 t.aizawa [QC#60739, ADD]
                S21ApiTBLAccessor.update(schdTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdTMsg.getReturnCode())) {
                    msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[]{"DS_CONTR_BLLG_SCHD"});
                }
                // END 2019/08/14 T.Aoyagi [QC#52451, ADD]
                return schdTMsg;
            }
            //Add Start 2017/12/07 <QC#22935>
            DS_CONTR_DTLTMsg dtlTmsg = NSZC0470Query.getInstance().getDsContrDtlTMsg(glblCmpyCd, schdTMsg.dsContrDtlPk.getValue());
            //Add End   2017/12/07 <QC#22935>
            // Mod Start 2018/06/20 QC#26766
//            BigDecimal beforeDays = new BigDecimal(NSZC047001CommonLogic.getDiffDays(thruDt, fromDt));
            BigDecimal afterDays = new BigDecimal(NSZC047001CommonLogic.getDiffDays(trmnDt, fromDt));
            if (FLG_ON_Y.equals(baseChrgFlg) && BLLG_TMG_TP.ADVANCE.equals(dtlTmsg.baseBllgTmgCd.getValue())) {
                // Base Charge (Advance)
//                BigDecimal rate = afterDays.divide(beforeDays, 2, BigDecimal.ROUND_HALF_UP);
                CCYTMsg ccyTMsg = NSZC0470Query.getInstance().getCcy(glblCmpyCd, (String) this.contrInfoList.get(0).get("CCY_CD"));
                int digit = 2;
                if (ccyTMsg != null && hasValue(ccyTMsg.aftDeclPntDigitNum)) {
                    digit = ccyTMsg.aftDeclPntDigitNum.getValueInt();
                }
                // Mod Start 2017/12/04 <QC#22883>
                BigDecimal origUnitAmt = schdTMsg.basePrcDealAmt.getValue(); 
                BigDecimal origAmt = schdTMsg.baseActlPrcDealAmt.getValue(); 
                BigDecimal year = new BigDecimal(DAYS_OF_YEAR);
                BLLG_CYCLETMsg bllgCycleTMsg = NSZC0470Query.getInstance().getBllgCycleTMsg(glblCmpyCd, schdTMsg.bllgCycleCd.getValue());
                BigDecimal prrtDivRate = bllgCycleTMsg.prrtDivRate.getValue();
                // START 2018/12/18 K.Kitachi [QC#29659, DEL]
//                BigDecimal bllgCycleMthAot = bllgCycleTMsg.bllgCycleMthAot.getValue();
                // END 2018/12/18 K.Kitachi [QC#29659, DEL]
                BigDecimal divRate = year.divide(prrtDivRate, digit, BigDecimal.ROUND_HALF_UP);
                // START 2018/12/18 K.Kitachi [QC#29659, MOD]
//                BigDecimal perDayDealAmt = origUnitAmt.multiply(bllgCycleMthAot).multiply(afterDays).divide(divRate, digit, BigDecimal.ROUND_HALF_UP);
                BigDecimal perDayDealAmt = origUnitAmt.multiply(afterDays).divide(divRate, digit, BigDecimal.ROUND_HALF_UP);
                // END 2018/12/18 K.Kitachi [QC#29659, MOD]
                BigDecimal baseActlPrcDealAmt = schdTMsg.baseActlPrcDealAmt.getValue();
                BigDecimal basePrcAdjDealAmt = schdTMsg.basePrcAdjDealAmt.getValue();
                if (origAmt.compareTo(perDayDealAmt) <= 0) {
                    baseActlPrcDealAmt = origAmt;
                    basePrcAdjDealAmt = origAmt.subtract(perDayDealAmt);
                } else{
                    baseActlPrcDealAmt = perDayDealAmt;
                    basePrcAdjDealAmt = basePrcAdjDealAmt.add(origAmt.subtract(perDayDealAmt));
                }
                // Mod End   2017/12/04 <QC#22883>

                setValue(schdTMsg.baseActlPrcDealAmt, baseActlPrcDealAmt);
                setValue(schdTMsg.basePrcAdjDealAmt, basePrcAdjDealAmt);
                setValue(schdTMsg.bllgStageFlg, FLG_OFF_N);
                setValue(schdTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.OPEN);
                setValue(schdTMsg.bllgCalcFlg, FLG_OFF_N);
            } else if (FLG_ON_Y.equals(baseChrgFlg) && BLLG_TMG_TP.ARREARS.equals(dtlTmsg.baseBllgTmgCd.getValue())) {
                // Base Charge (Arrears)
                // Add Start 2019/04/26 K.Fujimoto QC#31137/50058
                // Stub Calc.
                BigDecimal origUnitAmt = schdTMsg.basePrcDealAmt.getValue();
                BigDecimal origAmt = schdTMsg.baseActlPrcDealAmt.getValue();
                CCYTMsg ccyTMsg = NSZC0470Query.getInstance().getCcy(glblCmpyCd, (String) this.contrInfoList.get(0).get("CCY_CD"));
                int digit = 2;
                if (ccyTMsg != null && hasValue(ccyTMsg.aftDeclPntDigitNum)) {
                    digit = ccyTMsg.aftDeclPntDigitNum.getValueInt();
                }
                BigDecimal year = new BigDecimal(DAYS_OF_YEAR);
                BLLG_CYCLETMsg bllgCycleTMsg = NSZC0470Query.getInstance().getBllgCycleTMsg(glblCmpyCd, schdTMsg.bllgCycleCd.getValue());
                BigDecimal prrtDivRate = bllgCycleTMsg.prrtDivRate.getValue();
                BigDecimal divRate = year.divide(prrtDivRate, digit, BigDecimal.ROUND_HALF_UP);
                BigDecimal perDayDealAmt = origUnitAmt.multiply(afterDays).divide(divRate, digit, BigDecimal.ROUND_HALF_UP);
                BigDecimal baseActlPrcDealAmt = schdTMsg.baseActlPrcDealAmt.getValue();
                BigDecimal basePrcAdjDealAmt = schdTMsg.basePrcAdjDealAmt.getValue();
                if (origAmt.compareTo(perDayDealAmt) <= 0) {
                    baseActlPrcDealAmt = origAmt;
                    basePrcAdjDealAmt = origAmt.subtract(perDayDealAmt);
                } else {
                    baseActlPrcDealAmt = perDayDealAmt;
                    basePrcAdjDealAmt = basePrcAdjDealAmt.add(origAmt.subtract(perDayDealAmt));
                }
                setValue(schdTMsg.baseActlPrcDealAmt, baseActlPrcDealAmt);
                setValue(schdTMsg.basePrcAdjDealAmt, basePrcAdjDealAmt);
                setValue(schdTMsg.nextBllgDt, trmnDt);
                setValue(schdTMsg.bllgStageFlg, FLG_OFF_N);
                setValue(schdTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.OPEN);
                setValue(schdTMsg.bllgCalcFlg, FLG_OFF_N);
                // Add End   2019/04/26 K.Fujimoto QC#31137/50058
            } else if (FLG_OFF_N.equals(baseChrgFlg)) {
                // Usage Charge
                setValue(schdTMsg.nextBllgDt, trmnDt);
                // START 2019/08/14 T.Aoyagi [QC#52451-2, ADD]
                if (!DS_CONTR_CATG.REGULAR.equals((String) this.contrInfoList.get(0).get("DS_CONTR_CATG_CD"))) {
                    DS_CONTR_BLLG_SCHDTMsg prntBllgSchdTMsg = null;

                    // Get BllgSchd of Fleet line / Aggregate line.
                    if (DS_CONTR_DTL_TP.ACCESSORIES.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
                        DS_CONTR_BLLG_SCHDTMsg machSchdTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, schdTMsg.prntDsContrBllgSchdPk.getValue());
                        if (machSchdTMsg != null) {
                            prntBllgSchdTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, machSchdTMsg.prntDsContrBllgSchdPk.getValue());
                        }
                    } else {
                        prntBllgSchdTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, schdTMsg.prntDsContrBllgSchdPk.getValue());
                    }

                    // Clear status of Fleet line / Aggregate line.
                    if (prntBllgSchdTMsg != null) {
                        setValue(prntBllgSchdTMsg.bllgStageFlg, FLG_OFF_N);
                        setValue(prntBllgSchdTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.OPEN);
                        setValue(prntBllgSchdTMsg.bllgCalcFlg, FLG_OFF_N);
                        S21ApiTBLAccessor.update(prntBllgSchdTMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prntBllgSchdTMsg.getReturnCode())) {
                            msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[]{"DS_CONTR_BLLG_SCHD"});
                        }
                        setValue(schdTMsg.nextBllgDt, prntBllgSchdTMsg.nextBllgDt.getValue());
                    }
                }
                // START 2019/08/14 T.Aoyagi [QC#52451-2, ADD]
                setValue(schdTMsg.bllgStageFlg, FLG_OFF_N);
                setValue(schdTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.OPEN);
                setValue(schdTMsg.bllgCalcFlg, FLG_OFF_N);
                schdTMsg.readMtrCnt.clear();
                schdTMsg.bllgMtrCnt.clear();
                schdTMsg.mtrChrgDealAmt.clear();
                schdTMsg.mtrChrgFuncAmt.clear();
                schdTMsg.slsTaxRate.clear();
                schdTMsg.dealTaxAmt.clear();
                schdTMsg.funcTaxAmt.clear();
                setValue(schdTMsg.mtrEntryCpltFlg, FLG_OFF_N);
                schdTMsg.svcPhysMtrReadGrpSq.clear();
            }
            // Mod End 2018/06/20 QC#26766
            setValue(schdTMsg.dsContrBllgSchdSqNum, BigDecimal.ONE.toString());
            if (schdSmryTMsg != null) {
                int dsContrBllgSchdSqNum = Integer.parseInt(schdSmryTMsg.dsContrBllgSchdSqNum.getValue()) + 1;
                setValue(schdTMsg.dsContrBllgSchdLvlNum, String.valueOf(dsContrBllgSchdSqNum));
            }

            setValue(schdTMsg.bllgSchdThruDt, trmnDt);
            setValue(schdTMsg.bllgSchdPrrtFlg, FLG_ON_Y);
            setValue(schdTMsg.bllgPerMthAot, afterDays);
            // START 2022/02/04 K.Kitachi [QC#59684, ADD]
            if (MAN_TRMN_TP.ALL_PERIOD.equals(pMsg.manTrmnTpCd.getValue())) {
                setValue(schdTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.CLOSE);
            }
            // END 2022/02/04 K.Kitachi [QC#59684, ADD]
            // START 2023/09/04 t.aizawa [QC#60739, ADD]
            if (isTerminatedBeforeStartDate && FLG_ON_Y.equals(baseChrgFlg)) {
                setValue(schdTMsg.baseActlPrcDealAmt, BigDecimal.ZERO);
            }
            // END   2023/09/04 t.aizawa [QC#60739, ADD]

            if (FLG_OFF_N.equals(baseChrgFlg) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTp)) {
                updateSchdForUnBilledFleetChild(msgMap, schdTMsg);
            }
            S21ApiTBLAccessor.update(schdTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[]{"DS_CONTR_BLLG_SCHD"});
            }
        }
        return schdTMsg;
    }

    private void updateSchdForUnBilledFleetChild(S21ApiMessageMap msgMap, DS_CONTR_BLLG_SCHDTMsg prntSchdTMsg) {

        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String trmnDt = pMsg.trmnDt.getValue();
        BigDecimal prntDsContrBllgSchdPk = prntSchdTMsg.dsContrBllgSchdPk.getValue();
        List<BigDecimal> prntSchdPkList = NSZC0470Query.getInstance().getDeleteChildSchdList(glblCmpyCd, prntDsContrBllgSchdPk);
        for (BigDecimal schdPk : prntSchdPkList) {
            DS_CONTR_BLLG_SCHDTMsg schdTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, schdPk);
            if (schdTMsg == null) {
                continue;
            }
            String thruDt = schdTMsg.bllgSchdThruDt.getValue();

            if (trmnDt.equals(thruDt)) {
                continue;
            }

            // START 2017/10/16 K.Kitachi [QC#21683, ADD]
            setValue(schdTMsg.nextBllgDt, prntSchdTMsg.nextBllgDt);
            // END 2017/10/16 K.Kitachi [QC#21683, ADD]
            setValue(schdTMsg.bllgSchdThruDt, prntSchdTMsg.bllgSchdThruDt);
            setValue(schdTMsg.bllgSchdPrrtFlg, prntSchdTMsg.bllgSchdPrrtFlg);
            setValue(schdTMsg.bllgPerMthAot, prntSchdTMsg.bllgPerMthAot);

            S21ApiTBLAccessor.update(schdTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[]{"DS_CONTR_BLLG_SCHD"});
            }
        }
    }

    private void updateSchdSmryForUnBilled(S21ApiMessageMap msgMap, DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg, DS_CONTR_BLLG_SCHDTMsg schdTMsg, String trmnDt, String baseChrgFlg, DS_CONTR_BLLG_SCHDTMsg origSchdTMsg) {

        String glblCmpyCd = schdSmryTMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = schdSmryTMsg.dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = schdSmryTMsg.dsContrBllgMtrPk.getValue();
        String smryFromDt = schdSmryTMsg.bllgSchdFromDt.getValue();
        String smryThruDt = schdSmryTMsg.bllgSchdThruDt.getValue();
        String schdFromDt = schdTMsg.bllgSchdFromDt.getValue();
        // START 2016/02/26 T.Aoyagi [QC3419, ADD]
        String origSchdThruDt = "";
        if (origSchdTMsg != null) {
            origSchdThruDt = origSchdTMsg.bllgSchdThruDt.getValue();
        }
        // END 2016/02/26 T.Aoyagi [QC3419, ADD]

        boolean isAddSmrySchd = false;

        if (smryThruDt.equals(trmnDt)) {
            // no update
            return;
        }
        // START 2016/02/26 T.Aoyagi [QC3419, ADD]
        if (origSchdThruDt.equals(trmnDt)) {
            // not per days
            Map<String, BigDecimal> smryBasePrcAmt = NSZC0470Query.getInstance().getSummaryBasePrcAmt(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, smryFromDt, trmnDt, baseChrgFlg);
            if (smryBasePrcAmt != null) {
                if (FLG_ON_Y.equals(baseChrgFlg)) {
                    setValue(schdSmryTMsg.basePrcDealAdjAmt, smryBasePrcAmt.get("BASE_PRC_ADJ_DEAL_AMT"));
                    setValue(schdSmryTMsg.baseSubTotPrcDealAmt, smryBasePrcAmt.get("BASE_ACTL_PRC_DEAL_AMT"));
                }
                setValue(schdSmryTMsg.perSchdNum, smryBasePrcAmt.get("PER_SCHD_NUM"));
            }
            setValue(schdSmryTMsg.bllgSchdThruDt, trmnDt);
        // END 2016/02/26 T.Aoyagi [QC3419, ADD]
        } else if (smryFromDt.equals(schdFromDt)) {
            if (FLG_ON_Y.equals(baseChrgFlg)) {
                setValue(schdSmryTMsg.basePrcDealAdjAmt, schdTMsg.basePrcAdjDealAmt);
                setValue(schdSmryTMsg.baseSubTotPrcDealAmt, schdTMsg.baseActlPrcDealAmt);
            }
            int diffDays = NSZC047001CommonLogic.getDiffDays(trmnDt, smryFromDt);
            setValue(schdSmryTMsg.perSchdNum, new BigDecimal(diffDays));
            setValue(schdSmryTMsg.bllgSchdThruDt, trmnDt);
        } else if (smryFromDt.compareTo(schdFromDt) < 0) {

            String thruDt = ZYPDateUtil.addDays(schdFromDt, -1);
            Map<String, BigDecimal> smryBasePrcAmt = NSZC0470Query.getInstance().getSummaryBasePrcAmt(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, smryFromDt, thruDt, baseChrgFlg);
            if (smryBasePrcAmt != null) {
                if (FLG_ON_Y.equals(baseChrgFlg)) {
                    setValue(schdSmryTMsg.basePrcDealAdjAmt, smryBasePrcAmt.get("BASE_PRC_ADJ_DEAL_AMT"));
                    setValue(schdSmryTMsg.baseSubTotPrcDealAmt, smryBasePrcAmt.get("BASE_ACTL_PRC_DEAL_AMT"));
                }
                setValue(schdSmryTMsg.perSchdNum, smryBasePrcAmt.get("PER_SCHD_NUM"));
            }
            setValue(schdSmryTMsg.bllgSchdThruDt, thruDt);
            isAddSmrySchd = true;
        }

        S21ApiTBLAccessor.update(schdSmryTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdSmryTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[]{"DS_CONTR_BLLG_SCHD_SMRY"});
        }

        // for per days
        if (isAddSmrySchd) {
            DS_CONTR_BLLG_SCHD_SMRYTMsg perDaySchdSmryTMsg = createSchdSmry(msgMap, schdSmryTMsg, schdTMsg, baseChrgFlg);
            if (FLG_OFF_N.equals(baseChrgFlg)) {
                createSchdMtr(msgMap, schdSmryTMsg, perDaySchdSmryTMsg);
            }
            setValue(schdTMsg.dsContrBllgSchdSmryPk, perDaySchdSmryTMsg.dsContrBllgSchdSmryPk);
            S21ApiTBLAccessor.update(schdTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdSmryTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[]{"DS_CONTR_BLLG_SCHD"});
            }
        }
    }

    private DS_CONTR_BLLG_SCHD_SMRYTMsg createSchdSmry(S21ApiMessageMap msgMap, DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg, DS_CONTR_BLLG_SCHDTMsg schdTMsg, String baseChrgFlg) {

        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        DS_CONTR_BLLG_SCHD_SMRYTMsg inTMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();
        EZDMsg.copy(schdSmryTMsg, null, inTMsg, null);

        BigDecimal dsContrBllgSchdSmryPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_SCHD_SMRY_SQ);
        String dsContrBllgSchdSqNum = schdSmryTMsg.dsContrBllgSchdSqNum.getValue();
        int sqNum = Integer.parseInt(dsContrBllgSchdSqNum) + 1;
        int diffDays = NSZC047001CommonLogic.getDiffDays(schdTMsg.bllgSchdThruDt.getValue(), schdTMsg.bllgSchdFromDt.getValue());

        setValue(inTMsg.dsContrBllgSchdSmryPk, dsContrBllgSchdSmryPk);
        setValue(inTMsg.dsContrBllgSchdSqNum, String.valueOf(sqNum));
        setValue(inTMsg.perSchdNum, new BigDecimal(diffDays));
        setValue(inTMsg.perBllgCycleCd, BLLG_CYCLE.DAILY);
        setValue(inTMsg.bllgSchdFromDt, schdTMsg.bllgSchdFromDt);
        setValue(inTMsg.bllgSchdThruDt, schdTMsg.bllgSchdThruDt);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            setValue(inTMsg.basePrcDealAdjAmt, schdTMsg.basePrcAdjDealAmt);
            setValue(inTMsg.baseSubTotPrcDealAmt, schdTMsg.baseActlPrcDealAmt);
        } else {
            String bllgCycleCd = schdSmryTMsg.perBllgCycleCd.getValue();
            BLLG_CYCLETMsg bllgCycleTMsg = NSZC0470Query.getInstance().getBllgCycleTMsg(glblCmpyCd, bllgCycleCd);
            if (bllgCycleTMsg != null) {

            BigDecimal allowance = schdSmryTMsg.mlyCopyInclPrcQty.getValue();
            BigDecimal year = new BigDecimal(DAYS_OF_YEAR);
            BigDecimal prrtDivRate = bllgCycleTMsg.prrtDivRate.getValue();
            BigDecimal divRate = year.divide(prrtDivRate, 2, BigDecimal.ROUND_HALF_UP);
            BigDecimal perSchdNum = inTMsg.perSchdNum.getValue();
            BigDecimal xsMtrCopyQty = allowance.multiply(perSchdNum).divide(divRate, 0, BigDecimal.ROUND_HALF_UP);
            setValue(inTMsg.mlyCopyInclPrcQty, xsMtrCopyQty);
            }
        }
        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_BLLG_SCHD"});
        }
        return inTMsg;
    }

    private void createSchdMtr(S21ApiMessageMap msgMap, DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg, DS_CONTR_BLLG_SCHD_SMRYTMsg perDaySchdSmryTMsg) {

        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal schdSmryPk = schdSmryTMsg.dsContrBllgSchdSmryPk.getValue();

        List<BigDecimal> schdMtrPkList = NSZC0470Query.getInstance().getDsContrBllgSchdMtrForCancel(glblCmpyCd, schdSmryPk);

        for (BigDecimal schdMtrPk : schdMtrPkList) {

            DS_CONTR_BLLG_SCHD_MTRTMsg schdMtrTMsg = NSZC0470Query.getInstance().getSchdMtrTMsg(glblCmpyCd, schdMtrPk);

            DS_CONTR_BLLG_SCHD_MTRTMsg inTMsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();
            EZDMsg.copy(schdMtrTMsg, null, inTMsg, null);

            BigDecimal dsContrBllgSchdMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_SCHD_MTR_SQ);

            setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(inTMsg.dsContrBllgSchdMtrPk, dsContrBllgSchdMtrPk);
            setValue(inTMsg.dsContrBllgSchdSmryPk, perDaySchdSmryTMsg.dsContrBllgSchdSmryPk);

            String bllgCycleCd = schdSmryTMsg.perBllgCycleCd.getValue();
            BLLG_CYCLETMsg bllgCycleTMsg = NSZC0470Query.getInstance().getBllgCycleTMsg(glblCmpyCd, bllgCycleCd);
            if (bllgCycleTMsg == null) {
                return;
            }

            BigDecimal allowance = inTMsg.xsMtrCopyQty.getValue();
            BigDecimal year = new BigDecimal(DAYS_OF_YEAR);
            BigDecimal prrtDivRate = bllgCycleTMsg.prrtDivRate.getValue();
            BigDecimal divRate = year.divide(prrtDivRate, 2, BigDecimal.ROUND_HALF_UP);
            BigDecimal perSchdNum = perDaySchdSmryTMsg.perSchdNum.getValue();
            BigDecimal xsMtrCopyQty = allowance.multiply(perSchdNum).divide(divRate, 0, BigDecimal.ROUND_HALF_UP);
            setValue(inTMsg.xsMtrCopyQty, xsMtrCopyQty);

            S21ApiTBLAccessor.insert(inTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_BLLG_SCHD_MTR"});
            }
        }
    }

    private void createCreditInfo(S21ApiMessageMap msgMap, Map<String, Object> bllgSchdMap, String lastBilledDt, String baseChrgFlg) {

        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        // START 2019/05/17 [QC#50338, MOD]
        // String svcInvNum = NSZC0470Query.getInstance().getLastSvcInvNum(glblCmpyCd, dsContrDtlPk, null, baseChrgFlg);
        Map<String, Object> svcInvInfo = NSZC0470Query.getInstance().getLastSvcInvNum(glblCmpyCd, dsContrDtlPk, null, baseChrgFlg);
        // END 2019/05/17 [QC#50338, MOD]
        // START 2016/02/19 T.Aoyagi [QC3694, ADD]
        // START 2019/05/17 [QC#50338, MOD]
        // if (!hasValue(svcInvNum)) {
        if (svcInvInfo == null) {
        // END 2019/05/17 [QC#50338, MOD]
            msgMap.addXxMsgIdWithPrm(ZZZM9006E, new String[] {"Service Invoice Number" });
            return;
        }
        // END 2016/02/19 T.Aoyagi [QC3694, ADD]
        // START 2019/05/17 [QC#50338, MOD]
        // SVC_INVTMsg svcInvTMsg = NSZC0470Query.getInstance().getSvcInvTMsg(glblCmpyCd, svcInvNum);
        // SVC_INV_LINETMsg svcInvLineTMsg = NSZC0470Query.getInstance().getSvcInvLineTMsg(glblCmpyCd, svcInvNum);
        SVC_INVTMsg svcInvTMsg = NSZC0470Query.getInstance().getSvcInvTMsg(glblCmpyCd, (String) svcInvInfo.get("SVC_INV_NUM"));
        SVC_INV_LINETMsg svcInvLineTMsg = NSZC0470Query.getInstance().getSvcInvLineTMsg(glblCmpyCd, (String) svcInvInfo.get("SVC_INV_NUM"), (String) svcInvInfo.get("SVC_INV_LINE_NUM"));
        // END   2019/05/17 [QC#50338, MOD]
        SVC_INV_LINE_ALLOCTMsgArray svcInvLineAllocTMsgArray = NSZC0470Query.getInstance().getSvcInvLineAllocTMsgArray(glblCmpyCd, svcInvLineTMsg.svcInvLinePk.getValue());

        // DS_CONTR_BLLG_SCHD
        DS_CONTR_BLLG_SCHDTMsg schdTMsg = createBllgSchd(msgMap, bllgSchdMap, lastBilledDt, baseChrgFlg);
        // Add Start 2017/11/23 QC#22683
        updateTermAmtBllgSchdSmry(glblCmpyCd, schdTMsg.dsContrBllgSchdSmryPk.getValue());
        updateTermAmtPrcEff(glblCmpyCd, schdTMsg.dsContrPrcEffPk.getValue());
        // Add End 2017/11/23 QC#22683

        // SVC_CONTR_BLLG
        SVC_CONTR_BLLGTMsg svcContrBllgTMsg = createContrBllg(msgMap, svcInvTMsg, svcInvLineTMsg, schdTMsg, lastBilledDt);

        // SVC_CONTR_BASE_BLLG
        SVC_CONTR_BASE_BLLGTMsg svcContrBaseBllgTMsg = createContrBaseBllg(msgMap, svcInvLineTMsg, svcContrBllgTMsg, schdTMsg);

        // START 2018/05/23 K.Kojima [QC#23302,MOD]
        // if (svcInvLineAllocTMsgArray == null) {
        //     return;
        // }
        if (svcInvLineAllocTMsgArray != null) {
        // END 2018/05/23 K.Kojima [QC#23302,MOD]
        boolean isLastLine = false;
        BigDecimal xtrCrAmt = pMsg.crAmt.getValue();
        for (int i = 0; i < svcInvLineAllocTMsgArray.getValidCount(); i++) {

            SVC_INV_LINE_ALLOCTMsg svcInvLineAllocTMsg = svcInvLineAllocTMsgArray.no(i);

            if (i == svcInvLineAllocTMsgArray.getValidCount() - 1) {
                isLastLine = true;
            }

            // SVC_CONTR_BLLG_ALLOC
            xtrCrAmt = createContrBllgAlloc(msgMap, svcInvLineAllocTMsg, svcInvLineTMsg, svcContrBllgTMsg, svcContrBaseBllgTMsg, schdTMsg, xtrCrAmt, isLastLine);
        }
        // START 2018/05/23 K.Kojima [QC#23302,ADD]
        }
        // END 2018/05/23 K.Kojima [QC#23302,ADD]

        // START 2018/05/23 K.Kojima [QC#23302,ADD]
        // Additional Charge(Machine)
        // mod start 2022/07/07 QC#60167
        //createCreditInfoForDetailAdditionalCharge(msgMap, svcContrBllgTMsg, svcInvLineTMsg.svcMachMstrPk.getValue(), svcContrBaseBllgTMsg.svcContrBaseBllgPk.getValue());
        if (ZYPConstant.FLG_OFF_N.equals(pMsg.ovrdFlg.getValue())) {
            createCreditInfoForDetailAdditionalCharge(msgMap, svcContrBllgTMsg, svcInvLineTMsg.svcMachMstrPk.getValue(), svcContrBaseBllgTMsg.svcContrBaseBllgPk.getValue());
        }
        // mod end 2022/07/07 QC#60167
        // END 2018/05/23 K.Kojima [QC#23302,ADD]
    }

    private DS_CONTR_BLLG_SCHDTMsg createBllgSchd(S21ApiMessageMap msgMap, Map<String, Object> bllgSchdMap, String lastBilledDt, String baseChrgFlg) {

        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();

        BigDecimal dsContrBllgSchdPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_SCHD_SQ);
        String baseBllgCycleCd = (String) this.contrInfoList.get(0).get("BASE_BLLG_CYCLE_CD");

        BigDecimal bllgPerMthAot = null;
        BLLG_CYCLETMsg bllgCycleTMsg = NSZC0470Query.getInstance().getBllgCycleTMsg(pMsg.glblCmpyCd.getValue(), baseBllgCycleCd);
        if (bllgCycleTMsg != null) {
            bllgPerMthAot = bllgCycleTMsg.bllgCycleMthAot.getValue();
        }

        DS_CONTR_BLLG_SCHDTMsg inTMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        setValue(inTMsg.dsContrDtlPk, pMsg.dsContrDtlPk);
        setValue(inTMsg.dsContrBllgSchdSmryPk, (BigDecimal) bllgSchdMap.get("DS_CONTR_BLLG_SCHD_SMRY_PK"));
        // mod start 2017/06/29 QC#18349
        //inTMsg.dsContrPrcEffPk.clear();
        //inTMsg.dsContrPrcEffSqNum.clear();
        //setValue(inTMsg.dsContrBllgSchdSqNum, BigDecimal.ONE.toString());
        //setValue(inTMsg.dsContrBllgSchdLvlNum, (String) bllgSchdMap.get("DS_CONTR_BLLG_SCHD_SQ_NUM"));
        setValue(inTMsg.dsContrPrcEffPk, (BigDecimal) bllgSchdMap.get("DS_CONTR_PRC_EFF_PK"));
        setValue(inTMsg.dsContrPrcEffSqNum, (BigDecimal) bllgSchdMap.get("DS_CONTR_PRC_EFF_SQ_NUM"));
        inTMsg.dsContrBllgSchdSqNum.clear();
        setValue(inTMsg.dsContrBllgSchdLvlNum, (String) bllgSchdMap.get("DS_CONTR_BLLG_SCHD_LVL_NUM"));
        // mod end 2017/06/29 QC#18349

        setValue(inTMsg.baseChrgFlg, FLG_ON_Y);
        setValue(inTMsg.usgChrgFlg, FLG_OFF_N);
        setValue(inTMsg.skipRecovTpCd, SKIP_RECOV_TP.NONE);
        inTMsg.svcInvNum.clear();
        inTMsg.invDt.clear();
        setValue(inTMsg.nextBllgDt, pMsg.slsDt);
        setValue(inTMsg.bllgSchdFromDt, ZYPDateUtil.addDays(pMsg.trmnDt.getValue(), 1));
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        if (MAN_TRMN_TP.ALL_PERIOD.equals(pMsg.manTrmnTpCd.getValue())) {
            setValue(inTMsg.bllgSchdFromDt, pMsg.trmnDt);
        }
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        setValue(inTMsg.bllgSchdThruDt, lastBilledDt);
        setValue(inTMsg.bllgCycleCd, baseBllgCycleCd);
        setValue(inTMsg.bllgSchdPrrtFlg, FLG_ON_Y);
        setValue(inTMsg.basePrcDealAmt, (BigDecimal) bllgSchdMap.get("BASE_PRC_DEAL_AMT"));
        inTMsg.basePrcFuncAmt.clear();
        setValue(inTMsg.baseActlPrcDealAmt, pMsg.crAmt);
        inTMsg.baseActlPrcFuncAmt.clear();
        inTMsg.readMtrCnt.clear();
        inTMsg.bllgMtrCnt.clear();
        inTMsg.mtrChrgDealAmt.clear();
        inTMsg.mtrChrgFuncAmt.clear();
        setValue(inTMsg.dsContrTrmnFlg, FLG_OFF_N);
        setValue(inTMsg.ccyCd, (String) this.contrInfoList.get(0).get("CCY_CD"));
        inTMsg.bllgSchdTrxSrcTpCd.clear();
        // START 2017/09/14 K.Kim [QC#20040, MOD]
        // setValue(inTMsg.bllgStageFlg, FLG_OFF_N);
        setValue(inTMsg.bllgStageFlg, FLG_ON_Y);
        // END 2017/09/14 K.Kim [QC#20040, MOD]
        setValue(inTMsg.invFlg, FLG_OFF_N);
        setValue(inTMsg.invTpCd, INV_TP.CREDIT_MEMO);
        setValue(inTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.OPEN);
        inTMsg.dsContrBllgMtrPk.clear();
        setValue(inTMsg.bllblFlg, FLG_ON_Y);
        inTMsg.rvsSchdDt.clear();
        setValue(inTMsg.mtrEntryCpltFlg, FLG_OFF_N);
        inTMsg.svcPhysMtrReadGrpSq.clear();
        setValue(inTMsg.dsContrDtlTpCd, (String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"));
        BigDecimal prntDsContrBllgSchdPk = null;
        BigDecimal prntDsContrDtlPk = null;
        // START 07/12/2018 [QC#25959, MOD]
        Map<String, Object> prntBllgSchdInfo;
        String prntNextBllgDt = null;
        // START 2019/05/17 [QC#50338, MOD]
        // if (DS_CONTR_CATG.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_CATG_CD"))) {
        prntDsContrDtlPk = (BigDecimal) this.contrInfoList.get(0).get("PRNT_DS_CONTR_DTL_PK");
        if (hasValue(prntDsContrDtlPk)) {
            // if (!DS_CONTR_DTL_TP.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
            // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
            // mod start 2017/07/21 QC#20045
            // START 2019/02/14 K.Kitachi [QC#30066, MOD]
//            prntBllgSchdInfo = NSZC0470Query.getInstance().getPrntBllgSchdPk(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, null, inTMsg.bllgSchdFromDt.getValue(), inTMsg.bllgSchdThruDt.getValue(), baseChrgFlg, INV_TP.CREDIT_MEMO);
            prntBllgSchdInfo = NSZC0470Query.getInstance().getPrntBllgSchdPk(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, null, inTMsg.bllgSchdFromDt.getValue(), inTMsg.bllgSchdThruDt.getValue(), baseChrgFlg, INV_TP.CREDIT_MEMO, pMsg.dsContrDtlPk.getValue());
            // END 2019/02/14 K.Kitachi [QC#30066, MOD]
            if (prntBllgSchdInfo != null) {
                prntDsContrBllgSchdPk = (BigDecimal) prntBllgSchdInfo.get("DS_CONTR_BLLG_SCHD_PK");
                prntNextBllgDt = (String) prntBllgSchdInfo.get("NEXT_BLLG_DT");
                setValue(inTMsg.nextBllgDt, prntNextBllgDt);
            }
            // mod end   2017/07/21 QC#20045
            // END 04/18/2016 T.Aoyagi [QC#7056, MOD]
        }
        // END   2019/05/17 [QC#50338, MOD]
        // END   07/12/2018 [QC#25959, MOD]
        setValue(inTMsg.prntDsContrBllgSchdPk, prntDsContrBllgSchdPk);
        setValue(inTMsg.bllgPerMthAot, bllgPerMthAot);
        setValue(inTMsg.prntDsContrDtlPk, prntDsContrDtlPk);
        inTMsg.origDsContrBllgSchdPk.clear();
        inTMsg.svcCrRebilPk.clear();
        inTMsg.svcCrRebilDtlPk.clear();
        inTMsg.origDsContrBllgSchdPk.clear();
        setValue(inTMsg.dsBllgSchdTpCd, DS_BLLG_SCHD_TP.REGULAR);
        setValue(inTMsg.basePrcAdjDealAmt, BigDecimal.ZERO);
        inTMsg.basePrcAdjFuncAmt.clear();

        // START 2017/11/21 K.Kojima [QC#22653,MOD]
        // setValue(inTMsg.bllgCalcFlg, FLG_OFF_N);
        setValue(inTMsg.bllgCalcFlg, FLG_ON_Y);
        // END 2017/11/21 K.Kojima [QC#22653,MOD]
        // add start 2017/06/29 QC#18349
        setValue(inTMsg.slsTaxRate, (BigDecimal) bllgSchdMap.get("SLS_TAX_RATE"));
        // add end 2017/06/29 QC#18349
        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_BLLG_SCHD"});
        }
        return inTMsg;
    }

    private SVC_CONTR_BLLGTMsg createContrBllg(S21ApiMessageMap msgMap, SVC_INVTMsg svcInvTMsg, SVC_INV_LINETMsg svcInvLineTMsg, DS_CONTR_BLLG_SCHDTMsg schdTMsg, String lastBilledDt) {

        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();
        String baseChrgFlg = FLG_ON_Y;
        BigDecimal svcContrBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_SQ);
        BigDecimal svcContrBllgGrpSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_GRP_SQ);

        SVC_CONTR_BLLGTMsg inTMsg = new SVC_CONTR_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcContrBllgPk, svcContrBllgPk);
        setValue(inTMsg.dsContrPk, svcInvTMsg.dsContrPk);
        setValue(inTMsg.dsContrDtlPk, pMsg.dsContrDtlPk);
        setValue(inTMsg.bllgCpltStsCd, BLLG_CPLT_STS.SCHEDULED);
        setValue(inTMsg.bllgApvlReqFlg, FLG_ON_Y);
        setValue(inTMsg.bllgApvlCpltFlg, FLG_ON_Y);
        inTMsg.bllgApvlPsnCd.clear();
        setValue(inTMsg.mthEndCloFlg, FLG_OFF_N);
        inTMsg.contrCloDay.clear();
        setValue(inTMsg.svcContrBllgFromDt, ZYPDateUtil.addDays(pMsg.trmnDt.getValue(), 1));
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        if (MAN_TRMN_TP.ALL_PERIOD.equals(pMsg.manTrmnTpCd.getValue())) {
            setValue(inTMsg.svcContrBllgFromDt, pMsg.trmnDt);
        }
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        setValue(inTMsg.svcContrBllgThruDt, lastBilledDt);
        inTMsg.baseBllgTmgCd.clear();
        inTMsg.baseBllgCycleMthAot.clear();
        setValue(inTMsg.baseBllgNextBllgDt, pMsg.slsDt);
        inTMsg.baseBllgLastBllgDt.clear();
        inTMsg.baseBllgInvUpToDt.clear();
        inTMsg.mtrBllgTmgCd.clear();
        inTMsg.mtrBllgCycleMthAot.clear();
        inTMsg.mtrBllgNextBllgDt.clear();
        inTMsg.mtrBllgLastBllgDt.clear();
        inTMsg.mtrBllgInvUpToDt.clear();
        setValue(inTMsg.ccyCd, (String) this.contrInfoList.get(0).get("CCY_CD"));
        setValue(inTMsg.svcMachMstrPk, svcInvLineTMsg.svcMachMstrPk);
        inTMsg.contrCloDt.clear();
        setValue(inTMsg.baseChrgFlg, FLG_ON_Y);
        setValue(inTMsg.usgChrgFlg, FLG_OFF_N);
        inTMsg.sellToCustCd.clear();
        String fleetCalcFlg = FLG_OFF_N;
        if (DS_CONTR_DTL_TP.FLEET.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
            fleetCalcFlg = FLG_ON_Y;
        }
        setValue(inTMsg.fleetCalcFlg, fleetCalcFlg);
        BigDecimal prntSvcContrBllgPk = null;
        // START 07/12/2018 [QC#25959, MOD]
        Map<String, Object> prntBllgSchdInfo;
        BigDecimal prntDsContrBllgSchdPk = null;
        BigDecimal prntDsContrDtlPk = null;
        // START 2019/05/17 [QC#50338, MOD]
        // if (DS_CONTR_CATG.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_CATG_CD"))) {
        prntDsContrDtlPk = (BigDecimal) this.contrInfoList.get(0).get("PRNT_DS_CONTR_DTL_PK");
        if (hasValue(prntDsContrDtlPk)) {
            // if (!DS_CONTR_DTL_TP.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
            // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
            // mod start 2017/07/21 QC#20045
            // START 2019/02/14 K.Kitachi [QC#30066, MOD]
//            prntBllgSchdInfo = NSZC0470Query.getInstance().getPrntBllgSchdPk(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, null, pMsg.slsDt.getValue(), pMsg.slsDt.getValue(), baseChrgFlg, INV_TP.CREDIT_MEMO);
            prntBllgSchdInfo = NSZC0470Query.getInstance().getPrntBllgSchdPk(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, null,
                    inTMsg.svcContrBllgFromDt.getValue(), inTMsg.svcContrBllgThruDt.getValue(), baseChrgFlg, INV_TP.CREDIT_MEMO, pMsg.dsContrDtlPk.getValue());
            // END 2019/02/14 K.Kitachi [QC#30066, MOD]
            if (prntBllgSchdInfo != null) {
                prntDsContrBllgSchdPk = (BigDecimal) prntBllgSchdInfo.get("DS_CONTR_BLLG_SCHD_PK");
            }
            // mod end   2017/07/21 QC#20045
            // END 04/18/2016 T.Aoyagi [QC#7056, MOD]
            SVC_CONTR_BLLGTMsg contrBllgTMsg = NSZC0470Query.getInstance().getSvcContrBllgTMsg(pMsg.glblCmpyCd.getValue(), prntDsContrBllgSchdPk);
            if (contrBllgTMsg != null) {
                prntSvcContrBllgPk = contrBllgTMsg.svcContrBllgPk.getValue();
            }
        }
        // END   07/12/2018 [QC#25959, MOD]
        // END   2019/05/17 [QC#50338, MOD]
        setValue(inTMsg.prntSvcContrBllgPk, prntSvcContrBllgPk);
        setValue(inTMsg.dsContrCatgCd, svcInvTMsg.dsContrCatgCd);
        inTMsg.ovrdNextBllgDt.clear();
        setValue(inTMsg.bllgReProcFlg, FLG_OFF_N);
        inTMsg.bllgReProcPsnCd.clear();
        inTMsg.prevSvcContrBllgPk.clear();
        inTMsg.lastMtrReadTs.clear();
        setValue(inTMsg.dsContrBllgSchdPk, schdTMsg.dsContrBllgSchdPk);
        setValue(inTMsg.dsAcctNum, svcInvTMsg.dsAcctNum);
        setValue(inTMsg.aggrCalcFlg, FLG_OFF_N);
        // mod start 2022/07/07 QC#60167
        //setValue(inTMsg.svcCrRebilPk, svcInvTMsg.svcCrRebilPk);
        //setValue(inTMsg.svcCrRebilDtlPk, svcInvTMsg.svcCrRebilDtlPk);
        inTMsg.svcCrRebilPk.clear();
        inTMsg.svcCrRebilDtlPk.clear();
        // mod end 2022/07/07 QC#60167
        setValue(inTMsg.invTpCd, INV_TP.CREDIT_MEMO);
        inTMsg.dsContrBllgMtrPk.clear();
        setValue(inTMsg.svcContrBllgGrpSq, svcContrBllgGrpSq);
        setValue(inTMsg.bllgCalcEstFlg, FLG_OFF_N);
        setValue(inTMsg.addlChrgFlg, FLG_OFF_N);
        inTMsg.addlChrgBllgNextBllgDt.clear();
        inTMsg.addlChrgInvUpToDt.clear();
        setValue(inTMsg.billToCustCd, svcInvTMsg.billToCustCd);
        // mod start 2022/07/07 QC#60167
        //setValue(inTMsg.origSvcInvNum, svcInvTMsg.origSvcInvNum);
        inTMsg.origSvcInvNum.clear();
        // mod end 2022/07/07 QC#60167
        setValue(inTMsg.prcAllocByMachQtyFlg, FLG_OFF_N);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"SVC_CONTR_BLLG"});
        }
        return inTMsg;
    }

    private SVC_CONTR_BASE_BLLGTMsg createContrBaseBllg(S21ApiMessageMap msgMap, SVC_INV_LINETMsg svcInvLineTMsg, SVC_CONTR_BLLGTMsg svcContrBllgTMsg, DS_CONTR_BLLG_SCHDTMsg schdTMsg) {

        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String slsDt = pMsg.slsDt.getValue();
        BigDecimal svcContrBaseBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BASE_BLLG_SQ);
        String baseBllgCycleCd = (String) this.contrInfoList.get(0).get("BASE_BLLG_CYCLE_CD");
        BigDecimal basePerMthAot = null;
        BLLG_CYCLETMsg bllgCycleTMsg = NSZC0470Query.getInstance().getBllgCycleTMsg(pMsg.glblCmpyCd.getValue(), baseBllgCycleCd);
        if (bllgCycleTMsg != null) {
            basePerMthAot = bllgCycleTMsg.bllgCycleMthAot.getValue();
        }
        String ccyCd = (String) this.contrInfoList.get(0).get("CCY_CD");

        SVC_CONTR_BASE_BLLGTMsg inTMsg = new SVC_CONTR_BASE_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcContrBaseBllgPk, svcContrBaseBllgPk);
        setValue(inTMsg.svcContrBllgPk, svcContrBllgTMsg.svcContrBllgPk);
        setValue(inTMsg.dsContrDtlPk, pMsg.dsContrDtlPk);
        setValue(inTMsg.baseDealAmt, pMsg.crAmt);
        BigDecimal baseFuncAmt = NSXC003001Exchange.calcFuncFromDeal(glblCmpyCd, ccyCd, slsDt, pMsg.crAmt.getValue());
        setValue(inTMsg.baseFuncAmt, baseFuncAmt);
        setValue(inTMsg.baseDiscDealAmt, BigDecimal.ZERO);
        setValue(inTMsg.baseDiscFuncAmt, BigDecimal.ZERO);
        setValue(inTMsg.mdseCd, svcInvLineTMsg.mdseCd);
        setValue(inTMsg.ccyCd, svcContrBllgTMsg.ccyCd);
        setValue(inTMsg.baseBllgFromDt, svcContrBllgTMsg.svcContrBllgFromDt);
        setValue(inTMsg.baseBllgThruDt, svcContrBllgTMsg.svcContrBllgThruDt);
        setValue(inTMsg.basePerMthAot, basePerMthAot);
        BigDecimal funcTaxAmt = callTaxCalcAPIForBase(msgMap, svcInvLineTMsg, schdTMsg, baseFuncAmt);
        BigDecimal dealTaxAmt = NSXC003001Exchange.calcDealFromFunc(glblCmpyCd, ccyCd, slsDt, funcTaxAmt);
        setValue(inTMsg.dealTaxAmt, dealTaxAmt);
        setValue(inTMsg.funcTaxAmt, funcTaxAmt);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"SVC_CONTR_BASE_BLLG"});
        }
        return inTMsg;
    }

    private BigDecimal createContrBllgAlloc(S21ApiMessageMap msgMap, SVC_INV_LINE_ALLOCTMsg svcInvLineAllocTMsg, SVC_INV_LINETMsg svcInvLineTMsg, SVC_CONTR_BLLGTMsg svcContrBllgTMsg, SVC_CONTR_BASE_BLLGTMsg svcContrBaseBllgTMsg, DS_CONTR_BLLG_SCHDTMsg schdTMsg, BigDecimal xtrCrAmt, boolean isLastLine) {

        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String slsDt = pMsg.slsDt.getValue();
        BigDecimal crAmt = pMsg.crAmt.getValue();
        BigDecimal funcCrAmt = BigDecimal.ZERO;
        BigDecimal svcContrBllgAllocPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_ALLOC_SQ);

        String ccyCd = (String) this.contrInfoList.get(0).get("CCY_CD");
        CCYTMsg ccyTMsg = NSZC0470Query.getInstance().getCcy(glblCmpyCd, ccyCd);
        int digit = 2;
        if (ccyTMsg != null && hasValue(ccyTMsg.aftDeclPntDigitNum)) {
            digit = ccyTMsg.aftDeclPntDigitNum.getValueInt();
        }
        BigDecimal slsAllocRate = svcInvLineAllocTMsg.slsAllocRate.getValue();
        if (isLastLine) {
            crAmt = xtrCrAmt;
        } else {
            if (hasValue(slsAllocRate)) {
                BigDecimal rate = slsAllocRate.divide(RATE_100);
                crAmt = crAmt.multiply(rate).setScale(digit, BigDecimal.ROUND_HALF_UP);
                xtrCrAmt = xtrCrAmt.subtract(crAmt);
            }
            funcCrAmt = NSXC003001Exchange.calcFuncFromDeal(glblCmpyCd, ccyCd, slsDt, crAmt);
        }
        BigDecimal funcTaxAmt = callTaxCalcAPIForBase(msgMap, svcInvLineTMsg, schdTMsg, funcCrAmt);
        BigDecimal dealTaxAmt = NSXC003001Exchange.calcFuncFromDeal(glblCmpyCd, ccyCd, slsDt, funcTaxAmt);

        SVC_CONTR_BLLG_ALLOCTMsg inTMsg = new SVC_CONTR_BLLG_ALLOCTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcContrBllgAllocPk, svcContrBllgAllocPk);
        setValue(inTMsg.svcContrBaseBllgPk, svcContrBaseBllgTMsg.svcContrBaseBllgPk);
        inTMsg.svcContrMtrBllgPk.clear();
        setValue(inTMsg.contrPrcAllocTpCd, CONTR_PRC_ALLOC_TP.BASE);
        setValue(inTMsg.intgMdseCd, svcInvLineAllocTMsg.intgMdseCd);
        setValue(inTMsg.tocCd, svcInvLineAllocTMsg.tocCd);
        setValue(inTMsg.slsAllocRate, svcInvLineAllocTMsg.slsAllocRate);
        setValue(inTMsg.dealGrsUnitPrcAmt, crAmt);
        setValue(inTMsg.dealDiscUnitPrcAmt, BigDecimal.ZERO);
        setValue(inTMsg.dealNetUnitPrcAmt, crAmt);
        setValue(inTMsg.dealGrsTotPrcAmt, crAmt);
        setValue(inTMsg.invLineDealNetAmt, crAmt);
        setValue(inTMsg.invLineDealTaxAmt, dealTaxAmt);
        setValue(inTMsg.funcGrsUnitPrcAmt, funcCrAmt);
        setValue(inTMsg.funcDiscUnitPrcAmt, BigDecimal.ZERO);
        setValue(inTMsg.funcNetUnitPrcAmt, funcCrAmt);
        setValue(inTMsg.funcGrsTotPrcAmt, funcCrAmt);
        setValue(inTMsg.invLineFuncNetAmt, funcCrAmt);
        setValue(inTMsg.invLineFuncTaxAmt, funcTaxAmt);
        setValue(inTMsg.coaCmpyCd, svcInvLineAllocTMsg.coaCmpyCd);
        setValue(inTMsg.coaAfflCd, svcInvLineAllocTMsg.coaAfflCd);
        setValue(inTMsg.coaBrCd, svcInvLineAllocTMsg.coaBrCd);
        setValue(inTMsg.coaChCd, svcInvLineAllocTMsg.coaChCd);
        setValue(inTMsg.coaCcCd, svcInvLineAllocTMsg.coaCcCd);
        setValue(inTMsg.coaAcctCd, svcInvLineAllocTMsg.coaAcctCd);
        setValue(inTMsg.coaProdCd, svcInvLineAllocTMsg.coaProdCd);
        setValue(inTMsg.coaProjCd, svcInvLineAllocTMsg.coaProjCd);
        setValue(inTMsg.coaExtnCd, svcInvLineAllocTMsg.coaExtnCd);
        setValue(inTMsg.svcContrBllgPk, svcContrBllgTMsg.svcContrBllgPk);
        inTMsg.svcContrAddlChrgBllgPk.clear();
        setValue(inTMsg.ccyCd, svcInvLineAllocTMsg.ccyCd);
        setValue(inTMsg.trxCd, svcInvLineAllocTMsg.trxCd);
        setValue(inTMsg.trxRsnCd, svcInvLineAllocTMsg.trxRsnCd);
        setValue(inTMsg.dfrdAcctgRuleCd, svcInvLineAllocTMsg.dfrdAcctgRuleCd);
        setValue(inTMsg.dfrdAcctgRuleDurnAot, svcInvLineAllocTMsg.dfrdAcctgRuleDurnAot);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"SVC_CONTR_BASE_BLLG"});
        }
        return xtrCrAmt;
    }

    private BigDecimal callTaxCalcAPIForBase(S21ApiMessageMap msgMap, SVC_INV_LINETMsg svcInvLineTMsg, DS_CONTR_BLLG_SCHDTMsg schdTMsg, BigDecimal baseFuncAmt) {

        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();

        CallTaxCalcAPIForBaseBean inBean = new CallTaxCalcAPIForBaseBean();

        inBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        inBean.setSlsDt(pMsg.slsDt.getValue());
        // START 2017/09/27 K.Kim [QC#20383, MOD]
        // inBean.setXxProcMd(PROC_MODE_CR);
        inBean.setXxProcMd(PROC_MODE_QUOTATION);
        // END 2017/09/27 K.Kim [QC#20383, MOD]
        inBean.setInvTp(INV_TP.CREDIT_MEMO);
        inBean.setDsAcctNum((String) this.contrInfoList.get(0).get("DS_ACCT_NUM"));
        inBean.setBaseBillToCustCd((String) this.contrInfoList.get(0).get("BASE_BILL_TO_CUST_CD"));
        inBean.setDsContrDtlTpCd((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"));
        inBean.setSvcMachMstrPk(svcInvLineTMsg.svcMachMstrPk.getValue());
        inBean.setDsContrPk((BigDecimal) this.contrInfoList.get(0).get("DS_CONTR_PK"));
        inBean.setNextBllgDt(pMsg.slsDt.getValue());
        inBean.setDsContrBllgSchdPk(schdTMsg.dsContrBllgSchdPk.getValue());
        inBean.setSvcPgmMdseCd((String) this.contrInfoList.get(0).get("SVC_PGM_MDSE_CD"));
        inBean.setBaseFuncAmt(baseFuncAmt);
        inBean.setFuncTaxAmt(svcInvLineTMsg.invLineFuncTaxAmt.getValue());

        NWZC036101PMsg apiTaxPMsg = NSXC003001CallTaxCalcAPIForBase.callTaxCalcApi(inBean, this.onBatchTp);
        BigDecimal funcTaxAmt = BigDecimal.ZERO;
        if (apiTaxPMsg.taxCalculateOutputLine.getValidCount() > 0) {
            NWZC036101_taxCalculateOutputLinePMsg linePMsg = apiTaxPMsg.taxCalculateOutputLine.no(0);
            // START 2016/03/15 T.Aoyagi [QC#5492, MOD]
            funcTaxAmt = funcTaxAmt.add(nullToZero(linePMsg.taxAmt_01.getValue()));
            funcTaxAmt = funcTaxAmt.add(nullToZero(linePMsg.taxAmt_02.getValue()));
            funcTaxAmt = funcTaxAmt.add(nullToZero(linePMsg.taxAmt_03.getValue()));
            // END 2016/03/15 T.Aoyagi [QC#5492, MOD]
        }
        return funcTaxAmt;
    }
    // START 2016/03/15 T.Aoyagi [QC#5492, ADD]
    private BigDecimal nullToZero(BigDecimal val) {
        if (hasValue(val)) {
            return val;
        }
        return BigDecimal.ZERO;
    }
    // END 2016/03/15 T.Aoyagi [QC#5492, ADD]

    // add start 2017/06/29 QC#18349
    protected BigDecimal calcCreditAmtForTerminate(NSZC047006PMsg pMsg) {
        CalcCreditAmtForTerminateBean inBean = new CalcCreditAmtForTerminateBean();
        inBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        inBean.setDsContrDtlPk(pMsg.dsContrDtlPk.getValue());
        inBean.setTrmnDt(pMsg.trmnDt.getValue());
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        inBean.setManTrmnTpCd(pMsg.manTrmnTpCd.getValue());
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        NSXC003001CalcCreditAmtForTerminate.calcCreditAmtForTerminate(inBean);
        return inBean.getCreditAmt();
    }

    private void updatePrcEffAndSchdInfoForBase(S21ApiMessageMap msgMap, String lastBilledDt) {
        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        String baseChrgFlg = FLG_ON_Y;
        // START 2017/08/28 K.Kim [QC#20760, ADD]
        String slsDt = pMsg.slsDt.getValue();
        // END 2017/08/28 K.Kim [QC#20760, ADD]

        BigDecimal schdSmryPk = NSZC0470Query.getInstance().getBllgSchdSmryForUpdateBase(glblCmpyCd, dsContrDtlPk, lastBilledDt);
        if (hasValue(schdSmryPk)) {
            DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, schdSmryPk);
            if (schdSmryTMsg != null) {
                updateSchdSmry(msgMap, schdSmryTMsg, lastBilledDt, baseChrgFlg);
            }
        }

        BigDecimal prcEffPk = NSZC0470Query.getInstance().getPcrEffForUpdateBase(glblCmpyCd, dsContrDtlPk, lastBilledDt);
        if (hasValue(prcEffPk)) {
            DS_CONTR_PRC_EFFTMsg prcEffTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, prcEffPk);
            if (prcEffTMsg != null) {
                // START 2017/08/28 K.Kim [QC#20760, MOD]
                updatePrcEff(msgMap, prcEffTMsg, lastBilledDt, baseChrgFlg, slsDt);
                // END 2017/08/28 K.Kim [QC#20760, MOD]
            }
        }
    }
    // add end 2017/06/29 QC#18349
    // Add Start 2017/11/24 QC#22683
    private void updateTermAmtBllgSchdSmry(String glblCmpyCd, BigDecimal dsContrBllgSchdSmryPk) {
        Map<String, Object> termAmtMap = NSZC0470Query.getInstance().getSumBllgSchdSmry(glblCmpyCd, dsContrBllgSchdSmryPk);
        if (termAmtMap == null) {
            return;
        }
        DS_CONTR_BLLG_SCHD_SMRYTMsg inMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, dsContrBllgSchdSmryPk);
        if (inMsg == null) {
            return;
        }
        // Update Parameter
        setValue(inMsg.basePrcDealAmt, (BigDecimal) termAmtMap.get("BASE_PRC_DEAL_AMT"));
        setValue(inMsg.baseSubTotPrcDealAmt, (BigDecimal) termAmtMap.get("TERM_AMT_RATE"));
        setValue(inMsg.basePrcFuncAdjAmt, (BigDecimal) termAmtMap.get("ADJ_AMT_RATE"));
        S21ApiTBLAccessor.update(inMsg);
    }

    private void updateTermAmtPrcEff(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        Map<String, Object> termAmtMap = NSZC0470Query.getInstance().getSumPrcEff(glblCmpyCd, dsContrPrcEffPk);
        if (termAmtMap == null) {
            return;
        }
        DS_CONTR_PRC_EFFTMsg inMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, dsContrPrcEffPk);
        if (inMsg == null) {
            return;
        }
        // Update Parameter
        setValue(inMsg.basePrcDealAmt, (BigDecimal) termAmtMap.get("BASE_PRC_DEAL_AMT"));
        setValue(inMsg.basePrcTermDealAmtRate, (BigDecimal) termAmtMap.get("TERM_AMT_RATE"));
        S21ApiTBLAccessor.update(inMsg);
    }
    // Add End 2017/11/24 QC#22683

    // START 2018/05/23 K.Kojima [QC#23302,ADD]
    // mod start 2022/07/07 QC#60167
//    private void createCreditInfoForDetailAdditionalCharge(S21ApiMessageMap msgMap, SVC_CONTR_BLLGTMsg prntSvcContrBllgTMsg, BigDecimal svcMachMstrPk, BigDecimal svcContrBaseBllgPk) {
//        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();
//        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
//        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
//        String trmnDt = pMsg.trmnDt.getValue();
//
//        List<Map<String, Object>> dsContrAddlChrgInfoList = NSZC0470Query.getInstance().getDsContrAddlChrgPkForTerminate(glblCmpyCd, dsContrDtlPk, trmnDt);
//        for (Map<String, Object> dsContrAddlChrgInfo : dsContrAddlChrgInfoList) {
//            BigDecimal dsContrAddlChrgPk = (BigDecimal) dsContrAddlChrgInfo.get("DS_CONTR_ADDL_CHRG_PK");
//            BigDecimal svcInvLinePk = (BigDecimal) dsContrAddlChrgInfo.get("SVC_INV_LINE_PK");
//
//            Map<String, Object> svcInvLineInfo = NSZC0470Query.getInstance().getSvcInvLineInfoForAdditionalChargeTerminate(glblCmpyCd, svcInvLinePk);
//            SVC_INV_LINE_ALLOCTMsgArray svcInvLineAllocTMsgArray = NSZC0470Query.getInstance().getSvcInvLineAllocTMsgArray(glblCmpyCd, svcInvLinePk);
//
//            SVC_CONTR_BLLGTMsg svcContrBllgTMsg = createContrBllgForAddlChrg(msgMap, svcInvLineInfo, prntSvcContrBllgTMsg, dsContrAddlChrgPk, svcMachMstrPk);
//            SVC_CONTR_ADDL_CHRG_BLLGTMsg svcContrAddlChrgBllgTMsg = createContrAddlChrgBllg(msgMap, svcInvLineInfo, svcContrBllgTMsg, dsContrAddlChrgPk, svcContrBaseBllgPk);
//
//            if (svcInvLineAllocTMsgArray == null) {
//                return;
//            }
//            boolean isLastLine = false;
//            BigDecimal xtrCrAmt = svcContrAddlChrgBllgTMsg.addlDealPrcAmt.getValue();
//            for (int i = 0; i < svcInvLineAllocTMsgArray.getValidCount(); i++) {
//                SVC_INV_LINE_ALLOCTMsg svcInvLineAllocTMsg = svcInvLineAllocTMsgArray.no(i);
//                if (i == svcInvLineAllocTMsgArray.getValidCount() - 1) {
//                    isLastLine = true;
//                }
//                xtrCrAmt = createContrBllgAllocForAddlChrg(msgMap, svcInvLineAllocTMsg, svcContrAddlChrgBllgTMsg, xtrCrAmt, isLastLine);
//            }
//        }
//    }

    private void createCreditInfoForDetailAdditionalCharge(S21ApiMessageMap msgMap, SVC_CONTR_BLLGTMsg prntSvcContrBllgTMsg, BigDecimal svcMachMstrPk, BigDecimal svcContrBaseBllgPk) {
        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        String trmnDt = pMsg.trmnDt.getValue();
        Map<String, Object> svcInvInfo = NSZC0470Query.getInstance().getSvcInvForAddlChrgTerminate(glblCmpyCd, dsContrDtlPk, trmnDt);
        if (svcInvInfo == null) {
            return;
        }
        BigDecimal svcInvLinePk = (BigDecimal) svcInvInfo.get("SVC_INV_LINE_PK");
        BigDecimal dsContrAddlChrgPk = (BigDecimal) svcInvInfo.get("DS_CONTR_ADDL_CHRG_PK");
        String bllgPerFromDt = (String) svcInvInfo.get("BLLG_PER_FROM_DT");
        String bllgPerThruDt = (String) svcInvInfo.get("BLLG_PER_THRU_DT");

        Map<String, Object> svcInvLineInfo = NSZC0470Query.getInstance().getSvcInvLineInfoForAdditionalChargeTerminate(glblCmpyCd, svcInvLinePk);
        SVC_INV_LINE_ALLOCTMsgArray svcInvLineAllocTMsgArray = NSZC0470Query.getInstance().getSvcInvLineAllocTMsgArray(glblCmpyCd, svcInvLinePk);

        SVC_CONTR_BLLGTMsg svcContrBllgTMsg = createContrBllgForAddlChrg(msgMap, svcInvLineInfo, prntSvcContrBllgTMsg, dsContrAddlChrgPk, svcMachMstrPk);
        SVC_CONTR_ADDL_CHRG_BLLGTMsg svcContrAddlChrgBllgTMsg = createContrAddlChrgBllg(msgMap, svcInvLineInfo, svcContrBllgTMsg, dsContrAddlChrgPk, svcContrBaseBllgPk, bllgPerFromDt, bllgPerThruDt);

        if (svcInvLineAllocTMsgArray == null) {
            return;
        }
        boolean isLastLine = false;
        BigDecimal xtrCrAmt = svcContrAddlChrgBllgTMsg.addlDealPrcAmt.getValue();
        for (int i = 0; i < svcInvLineAllocTMsgArray.getValidCount(); i++) {
            SVC_INV_LINE_ALLOCTMsg svcInvLineAllocTMsg = svcInvLineAllocTMsgArray.no(i);
            if (i == svcInvLineAllocTMsgArray.getValidCount() - 1) {
                isLastLine = true;
            }
            xtrCrAmt = createContrBllgAllocForAddlChrg(msgMap, svcInvLineAllocTMsg, svcContrAddlChrgBllgTMsg, xtrCrAmt, isLastLine);
        }
    }
    // mod end 2022/07/07 QC#60167

    private SVC_CONTR_BLLGTMsg createContrBllgForAddlChrg(S21ApiMessageMap msgMap, Map<String, Object> svcInvLineInfo, SVC_CONTR_BLLGTMsg prntSvcContrBllgTMsg, BigDecimal dsContrAddlChrgPk, BigDecimal svcMachMstrPk) {
        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();
        BigDecimal svcContrBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_SQ);

        SVC_CONTR_BLLGTMsg inTMsg = new SVC_CONTR_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcContrBllgPk, svcContrBllgPk);
        setValue(inTMsg.dsContrPk, (BigDecimal) svcInvLineInfo.get("DS_CONTR_PK"));
        setValue(inTMsg.dsContrDtlPk, pMsg.dsContrDtlPk);
        setValue(inTMsg.bllgCpltStsCd, BLLG_CPLT_STS.SCHEDULED);
        setValue(inTMsg.bllgApvlReqFlg, FLG_ON_Y);
        setValue(inTMsg.bllgApvlCpltFlg, FLG_ON_Y);
        inTMsg.bllgApvlPsnCd.clear();
        setValue(inTMsg.mthEndCloFlg, FLG_OFF_N);
        inTMsg.contrCloDay.clear();
        setValue(inTMsg.svcContrBllgFromDt, prntSvcContrBllgTMsg.svcContrBllgFromDt);
        setValue(inTMsg.svcContrBllgThruDt, prntSvcContrBllgTMsg.svcContrBllgThruDt);
        inTMsg.baseBllgTmgCd.clear();
        inTMsg.baseBllgCycleMthAot.clear();
        inTMsg.baseBllgNextBllgDt.clear();
        inTMsg.baseBllgLastBllgDt.clear();
        inTMsg.baseBllgInvUpToDt.clear();
        inTMsg.mtrBllgTmgCd.clear();
        inTMsg.mtrBllgCycleMthAot.clear();
        inTMsg.mtrBllgNextBllgDt.clear();
        inTMsg.mtrBllgLastBllgDt.clear();
        inTMsg.mtrBllgInvUpToDt.clear();
        setValue(inTMsg.ccyCd, (String) this.contrInfoList.get(0).get("CCY_CD"));
        setValue(inTMsg.svcMachMstrPk, svcMachMstrPk);
        inTMsg.contrCloDt.clear();
        setValue(inTMsg.baseChrgFlg, FLG_OFF_N);
        setValue(inTMsg.usgChrgFlg, FLG_OFF_N);
        inTMsg.sellToCustCd.clear();
        String fleetCalcFlg = FLG_OFF_N;
        if (DS_CONTR_DTL_TP.FLEET.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
            fleetCalcFlg = FLG_ON_Y;
        }
        setValue(inTMsg.fleetCalcFlg, fleetCalcFlg);
        setValue(inTMsg.prntSvcContrBllgPk, prntSvcContrBllgTMsg.svcContrBllgPk);
        setValue(inTMsg.dsContrCatgCd, (String) svcInvLineInfo.get("DS_CONTR_CATG_CD"));
        inTMsg.ovrdNextBllgDt.clear();
        setValue(inTMsg.bllgReProcFlg, FLG_OFF_N);
        inTMsg.bllgReProcPsnCd.clear();
        inTMsg.prevSvcContrBllgPk.clear();
        inTMsg.lastMtrReadTs.clear();
        setValue(inTMsg.dsContrBllgSchdPk, prntSvcContrBllgTMsg.dsContrBllgSchdPk);
        setValue(inTMsg.dsAcctNum, (String) svcInvLineInfo.get("DS_ACCT_NUM"));
        setValue(inTMsg.aggrCalcFlg, FLG_OFF_N);
        // mod start 2022/07/07 QC#60167
        //setValue(inTMsg.origSvcInvNum, (String) svcInvLineInfo.get("ORIG_SVC_INV_NUM"));
        inTMsg.origSvcInvNum.clear();
        // mod end 2022/07/07 QC#60167
        inTMsg.svcCrRebilPk.clear();
        inTMsg.svcCrRebilDtlPk.clear();
        setValue(inTMsg.invTpCd, INV_TP.CREDIT_MEMO);
        inTMsg.dsContrBllgMtrPk.clear();
        setValue(inTMsg.svcContrBllgGrpSq, prntSvcContrBllgTMsg.svcContrBllgGrpSq);
        setValue(inTMsg.bllgCalcEstFlg, FLG_OFF_N);
        setValue(inTMsg.addlChrgFlg, FLG_ON_Y);
        setValue(inTMsg.addlChrgBllgNextBllgDt, pMsg.slsDt);
        inTMsg.addlChrgInvUpToDt.clear();
        setValue(inTMsg.billToCustCd, (String) svcInvLineInfo.get("BILL_TO_CUST_CD"));
        setValue(inTMsg.prcAllocByMachQtyFlg, FLG_OFF_N);
        setValue(inTMsg.dsContrAddlChrgPk, dsContrAddlChrgPk);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[] {"SVC_CONTR_BLLG" });
        }
        return inTMsg;
    }

    // mod start 2022/07/07 QC#60167
    //private SVC_CONTR_ADDL_CHRG_BLLGTMsg createContrAddlChrgBllg(S21ApiMessageMap msgMap, Map<String, Object> svcInvLineInfo, SVC_CONTR_BLLGTMsg svcContrBllgTMsg, BigDecimal dsContrAddlChrgPk, BigDecimal svcContrBaseBllgPk) {
    private SVC_CONTR_ADDL_CHRG_BLLGTMsg createContrAddlChrgBllg(S21ApiMessageMap msgMap, Map<String, Object> svcInvLineInfo, SVC_CONTR_BLLGTMsg svcContrBllgTMsg, BigDecimal dsContrAddlChrgPk, BigDecimal svcContrBaseBllgPk,
            String bllgPerFromDt, String bllgPerThruDt) {
    // mod end 2022/07/07 QC#60167
        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String trmnDt = pMsg.trmnDt.getValue();
        String slsDt = pMsg.slsDt.getValue();
        String ccyCd = (String) this.contrInfoList.get(0).get("CCY_CD");
        BigDecimal svcContrAddlChrgBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_ADDL_CHRG_BLLG_SQ);

        // START 2022/02/04 K.Kitachi [QC#59684, MOD]
//        BigDecimal dealAmt = getAddlDealPrcAmt(glblCmpyCd, dsContrAddlChrgPk, trmnDt);
        // mod start 2022/07/07 QC#60167
        //BigDecimal dealAmt = getAddlDealPrcAmt(glblCmpyCd, dsContrAddlChrgPk, trmnDt, pMsg.manTrmnTpCd.getValue());
        BigDecimal dealAmt = getAddlDealPrcAmt(glblCmpyCd, pMsg.dsContrDtlPk.getValue(), trmnDt, pMsg.manTrmnTpCd.getValue());
        // mod end 2022/07/07 QC#60167
        // END 2022/02/04 K.Kitachi [QC#59684, MOD]
        BigDecimal funcAmt = NSXC003001Exchange.calcFuncFromDeal(glblCmpyCd, ccyCd, slsDt, dealAmt);

        SVC_CONTR_ADDL_CHRG_BLLGTMsg inTMsg = new SVC_CONTR_ADDL_CHRG_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcContrAddlChrgBllgPk, svcContrAddlChrgBllgPk);
        setValue(inTMsg.svcContrBllgPk, svcContrBllgTMsg.svcContrBllgPk);
        setValue(inTMsg.addlChrgTpCd, (String) svcInvLineInfo.get("ADDL_CHRG_TP_CD"));
        setValue(inTMsg.svcBillByTpCd, (String) svcInvLineInfo.get("SVC_BILL_BY_TP_CD"));
        setValue(inTMsg.addlDealPrcAmt, dealAmt);
        setValue(inTMsg.addlFuncPrcAmt, funcAmt);
        setValue(inTMsg.addlChrgFlatDealPrcAmt, (BigDecimal) svcInvLineInfo.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"));
        setValue(inTMsg.addlChrgFlatFuncPrcAmt, (BigDecimal) svcInvLineInfo.get("ADDL_CHRG_FLAT_FUNC_PRC_AMT"));
        setValue(inTMsg.addlChrgAplcPct, (BigDecimal) svcInvLineInfo.get("ADDL_CHRG_APLC_PCT"));
        setValue(inTMsg.addlChrgInvTpCd, (String) svcInvLineInfo.get("ADDL_CHRG_INV_TP_CD"));
        setValue(inTMsg.printDtlFlg, (String) svcInvLineInfo.get("PRINT_DTL_FLG"));
        setValue(inTMsg.intgMdseCd, NSZC0470Query.getInstance().getIntgMdseCdForAddlChrgTpV(pMsg.glblCmpyCd.getValue(), inTMsg.addlChrgTpCd.getValue()));
        setValue(inTMsg.chrgMachCnt, BigDecimal.ONE);
        inTMsg.addlChrgPerMthAot.clear();
        // mod start 2022/07/07 QC#60167
        //String bllgPerFromDt = (String) svcInvLineInfo.get("BLLG_PER_FROM_DT");
        //if (pMsg.trmnDt.getValue().compareTo(bllgPerFromDt) < 0) {
        //    setValue(inTMsg.addlChrgFromDt, bllgPerFromDt);
        //} else {
        //    setValue(inTMsg.addlChrgFromDt, ZYPDateUtil.addDays(pMsg.trmnDt.getValue(), 1));
        //}
        //// START 2022/02/04 K.Kitachi [QC#59684, ADD]
        //if (MAN_TRMN_TP.ALL_PERIOD.equals(pMsg.manTrmnTpCd.getValue())) {
        //    setValue(inTMsg.addlChrgFromDt, pMsg.trmnDt);
        //}
        //// END 2022/02/04 K.Kitachi [QC#59684, ADD]
        //setValue(inTMsg.addlChrgThruDt, (String) svcInvLineInfo.get("BLLG_PER_THRU_DT"));
        setValue(inTMsg.addlChrgFromDt, svcContrBllgTMsg.svcContrBllgFromDt);
        setValue(inTMsg.addlChrgThruDt, svcContrBllgTMsg.svcContrBllgThruDt);
        if (ZYPDateUtil.compare(svcContrBllgTMsg.svcContrBllgFromDt.getValue(), bllgPerFromDt) < 0) {
            setValue(inTMsg.addlChrgFromDt, bllgPerFromDt);
        }
        if (ZYPDateUtil.compare(svcContrBllgTMsg.svcContrBllgThruDt.getValue(), bllgPerThruDt) > 0) {
            setValue(inTMsg.addlChrgThruDt, bllgPerThruDt);
        }
        // mod end 2022/07/07 QC#60167
        setValue(inTMsg.svcContrBaseBllgPk, svcContrBaseBllgPk);
        inTMsg.svcContrMtrBllgPk.clear();
        setValue(inTMsg.slsTaxRate, BigDecimal.ZERO);
        setValue(inTMsg.dealTaxAmt, BigDecimal.ZERO);
        setValue(inTMsg.funcTaxAmt, BigDecimal.ZERO);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[] {"SVC_CONTR_BLLG" });
        }
        return inTMsg;
    }

    // START 2022/02/04 K.Kitachi [QC#59684, MOD]
//    private BigDecimal getAddlDealPrcAmt(String glblCmpyCd, BigDecimal dsContrAddlChrgPk, String trmnDt) {
    // mod start 2022/07/07 QC#60167
    //private BigDecimal getAddlDealPrcAmt(String glblCmpyCd, BigDecimal dsContrAddlChrgPk, String trmnDt, String manTrmnTpCd) {
    private BigDecimal getAddlDealPrcAmt(String glblCmpyCd, BigDecimal dsContrDtlPk, String trmnDt, String manTrmnTpCd) {
    // mod end 2022/07/07 QC#60167
    // END 2022/02/04 K.Kitachi [QC#59684, MOD]
        CalcCreditAddlChrgAmtForTerminateBean inBean = new CalcCreditAddlChrgAmtForTerminateBean();
        inBean.setGlblCmpyCd(glblCmpyCd);
        // mod start 2022/07/07 QC#60167
        //inBean.setDsContrAddlChrgPk(dsContrAddlChrgPk);
        inBean.setDsContrDtlPk(dsContrDtlPk);
        // mod end 2022/07/07 QC#60167
        inBean.setTrmnDt(trmnDt);
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        inBean.setManTrmnTpCd(manTrmnTpCd);
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        NSXC003001CalcCreditAmtForTerminate.calcCreditAddlChrgAmtForTerminate(inBean);
        return inBean.getCreditAmt();
    }

    private BigDecimal createContrBllgAllocForAddlChrg(S21ApiMessageMap msgMap, SVC_INV_LINE_ALLOCTMsg svcInvLineAllocTMsg, SVC_CONTR_ADDL_CHRG_BLLGTMsg svcContrAddlChrgBllgTMsg, BigDecimal xtrCrAmt, boolean isLastLine) {
        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String slsDt = pMsg.slsDt.getValue();

        BigDecimal crAmt = svcContrAddlChrgBllgTMsg.addlDealPrcAmt.getValue();
        BigDecimal funcCrAmt = BigDecimal.ZERO;
        BigDecimal svcContrBllgAllocPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_ALLOC_SQ);

        String ccyCd = (String) this.contrInfoList.get(0).get("CCY_CD");
        CCYTMsg ccyTMsg = NSZC0470Query.getInstance().getCcy(glblCmpyCd, ccyCd);
        int digit = 2;
        if (ccyTMsg != null && hasValue(ccyTMsg.aftDeclPntDigitNum)) {
            digit = ccyTMsg.aftDeclPntDigitNum.getValueInt();
        }
        BigDecimal slsAllocRate = svcInvLineAllocTMsg.slsAllocRate.getValue();
        if (isLastLine) {
            crAmt = xtrCrAmt;
        } else {
            if (hasValue(slsAllocRate)) {
                BigDecimal rate = slsAllocRate.divide(RATE_100);
                crAmt = crAmt.multiply(rate).setScale(digit, BigDecimal.ROUND_HALF_UP);
                xtrCrAmt = xtrCrAmt.subtract(crAmt);
            }
            funcCrAmt = NSXC003001Exchange.calcFuncFromDeal(glblCmpyCd, ccyCd, slsDt, crAmt);
        }

        SVC_CONTR_BLLG_ALLOCTMsg inTMsg = new SVC_CONTR_BLLG_ALLOCTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcContrBllgAllocPk, svcContrBllgAllocPk);
        inTMsg.svcContrBaseBllgPk.clear();
        inTMsg.svcContrMtrBllgPk.clear();
        setValue(inTMsg.contrPrcAllocTpCd, CONTR_PRC_ALLOC_TP.ADDITIONAL_CHARGE);
        setValue(inTMsg.intgMdseCd, svcInvLineAllocTMsg.intgMdseCd);
        setValue(inTMsg.tocCd, svcInvLineAllocTMsg.tocCd);
        setValue(inTMsg.slsAllocRate, svcInvLineAllocTMsg.slsAllocRate);
        setValue(inTMsg.dealGrsUnitPrcAmt, crAmt);
        setValue(inTMsg.dealDiscUnitPrcAmt, BigDecimal.ZERO);
        setValue(inTMsg.dealNetUnitPrcAmt, crAmt);
        setValue(inTMsg.dealGrsTotPrcAmt, crAmt);
        setValue(inTMsg.invLineDealNetAmt, crAmt);
        setValue(inTMsg.invLineDealTaxAmt, BigDecimal.ZERO);
        setValue(inTMsg.funcGrsUnitPrcAmt, funcCrAmt);
        setValue(inTMsg.funcDiscUnitPrcAmt, BigDecimal.ZERO);
        setValue(inTMsg.funcNetUnitPrcAmt, funcCrAmt);
        setValue(inTMsg.funcGrsTotPrcAmt, funcCrAmt);
        setValue(inTMsg.invLineFuncNetAmt, funcCrAmt);
        setValue(inTMsg.invLineFuncTaxAmt, BigDecimal.ZERO);
        setValue(inTMsg.coaCmpyCd, svcInvLineAllocTMsg.coaCmpyCd);
        setValue(inTMsg.coaAfflCd, svcInvLineAllocTMsg.coaAfflCd);
        setValue(inTMsg.coaBrCd, svcInvLineAllocTMsg.coaBrCd);
        setValue(inTMsg.coaChCd, svcInvLineAllocTMsg.coaChCd);
        setValue(inTMsg.coaCcCd, svcInvLineAllocTMsg.coaCcCd);
        setValue(inTMsg.coaAcctCd, svcInvLineAllocTMsg.coaAcctCd);
        setValue(inTMsg.coaProdCd, svcInvLineAllocTMsg.coaProdCd);
        setValue(inTMsg.coaProjCd, svcInvLineAllocTMsg.coaProjCd);
        setValue(inTMsg.coaExtnCd, svcInvLineAllocTMsg.coaExtnCd);
        setValue(inTMsg.svcContrBllgPk, svcContrAddlChrgBllgTMsg.svcContrBllgPk);
        setValue(inTMsg.svcContrAddlChrgBllgPk, svcContrAddlChrgBllgTMsg.svcContrAddlChrgBllgPk);
        setValue(inTMsg.ccyCd, svcInvLineAllocTMsg.ccyCd);
        setValue(inTMsg.trxCd, svcInvLineAllocTMsg.trxCd);
        setValue(inTMsg.trxRsnCd, svcInvLineAllocTMsg.trxRsnCd);
        setValue(inTMsg.dfrdAcctgRuleCd, svcInvLineAllocTMsg.dfrdAcctgRuleCd);
        setValue(inTMsg.dfrdAcctgRuleDurnAot, svcInvLineAllocTMsg.dfrdAcctgRuleDurnAot);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[] {"SVC_CONTR_BASE_BLLG" });
        }
        return xtrCrAmt;
    }

    // END 2018/05/23 K.Kojima [QC#23302,ADD]

    // START 2022/03/14 K.Kitachi [QC#59684, ADD]
    private void updateInvoicedFlag(S21ApiMessageMap msgMap, BigDecimal dsContrBllgMtrPk) {

        NSZC047006PMsg pMsg = (NSZC047006PMsg) msgMap.getPmsg();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        // START 2023/10/05 H.Iinuma [QC#61675, ADD]
        String trmnDt = pMsg.trmnDt.getValue();
        // END 2023/10/05 H.Iinuma [QC#61675, ADD]

        // START 2023/10/05 H.Iinuma [QC#61675, MOD]
        // List<BigDecimal> schdPkList = NSZC0470Query.getInstance().getInvoicedSchdPkByBllgMtrPk(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk);
        List<BigDecimal> schdPkList = NSZC0470Query.getInstance().getInvoicedSchdPkByBllgMtrPk(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, trmnDt);
        // END 2023/10/05 H.Iinuma [QC#61675, MOD]

        for (BigDecimal schdPk : schdPkList) {
            DS_CONTR_BLLG_SCHDTMsg updTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdTMsgForUpdate(glblCmpyCd, schdPk);
            if (updTMsg == null) {
                msgMap.addXxMsgId(NSZM0828E);
                return;
            }
            setValue(updTMsg.invFlg, FLG_OFF_N);
            S21ApiTBLAccessor.update(updTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0828E);
                return;
            }
        }

    }
    // END 2022/03/14 K.Kitachi [QC#59684, ADD]
}
