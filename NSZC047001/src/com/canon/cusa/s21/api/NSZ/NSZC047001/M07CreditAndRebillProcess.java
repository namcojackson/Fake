package com.canon.cusa.s21.api.NSZ.NSZC047001;

import static com.canon.cusa.s21.api.NSZ.NSZC047001.constant.NSZC047001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHD_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsg;
import business.db.SVC_CONTR_BASE_BLLGTMsg;
import business.db.SVC_CONTR_BLLGTMsg;
import business.db.SVC_CONTR_BLLG_ALLOCTMsg;
import business.db.SVC_CONTR_MTR_BLLGTMsg;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsg;
import business.db.SVC_INVTMsg;
import business.db.SVC_INV_LINETMsg;
import business.db.SVC_INV_LINE_ADDL_CHRGTMsg;
import business.db.SVC_INV_LINE_ALLOCTMsg;
import business.db.SVC_INV_LINE_ALLOCTMsgArray;
import business.db.SVC_INV_LINE_MTRTMsg;
import business.db.SVC_INV_LINE_XS_MTRTMsg;
import business.db.SVC_INV_LINE_XS_MTRTMsgArray;
import business.parts.NSZC047007PMsg;
import business.parts.NSZC047007_xxBaseLineListPMsg;
import business.parts.NSZC047007_xxMtrLineListPMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CPLT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_PRC_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
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
 * 01/25/2016   Hitachi         T.Aoyagi        Update          QC#3623
 * 03/24/2016   Hitachi         T.Aoyagi        Update          QC#5901
 * 04/18/2016   Hitachi         T.Aoyagi        Update          QC#7056
 * 05/16/2016   Hitachi         T.Aoyagi        Update          QC#8183
 * 05/18/2017   Hitachi         K.Kitachi       Update          QC#18541
 * 2017/09/12   Hitachi         K.Kojima        Update          QC#20835
 * 10/03/2017   Hitachi         E.Kameishi      Update          QC#18636
 * 2017/10/13   Hitachi         K.Kim           Update          QC#21778
 * 2017/10/24   Hitachi         K.Kishimoto     Update          QC#22008
 * 2017/10/26   Hitachi         T.Tomita        Update          QC#21815
 * 2018/08/15   Hitachi         A.Kohinata      Update          QC#27329-3
 * 2018/10/29   Hitachi         K.Kojima        Update          QC#28999
 * 2018/10/30   Hitachi         K.Kojima        Update          QC#29000
 * </pre>
 */
public class M07CreditAndRebillProcess implements ZYPConstant {

    /** contrInfoList */
    private List<Map<String, Object>> contrInfoList;

    /** baseSvcContrBllgGrpSq */
    private BigDecimal baseSvcContrBllgGrpSq;

    /** usgSvcContrBllgGrpSq */
    private BigDecimal usgSvcContrBllgGrpSq;

    /** svcInvRebillTpCd */
    private String svcInvRebillTpCd = INV_TP.INVOICE;

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        String rebillTpCd = ZYPCodeDataUtil.getVarCharConstValue(SVC_INV_REBILL_TP_CD, pMsg.glblCmpyCd.getValue());
        if (hasValue(rebillTpCd)) {
            this.svcInvRebillTpCd = rebillTpCd;
        }

        checkParameter(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        creditAndRebillProcess(msgMap);
    }

    private void creditAndRebillProcess(S21ApiMessageMap msgMap) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        String svcCrRebilProcCd = pMsg.svcCrRebilProcCd.getValue();

        deleteSchdAndSmry(msgMap);
        if (FLG_ON_Y.equals(pMsg.delFlg.getValue())) {
            return;
        }

        // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
        // Create Credit Info
        // Base Charge
        this.baseSvcContrBllgGrpSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_GRP_SQ);
        for (int i = 0; i < pMsg.xxBaseLineList.getValidCount(); i++) {
            NSZC047007_xxBaseLineListPMsg linePMsg = pMsg.xxBaseLineList.no(i);
            this.contrInfoList = NSZC0470Query.getInstance().getContrInfo(pMsg.glblCmpyCd.getValue(), linePMsg.dsContrDtlPk_BL.getValue());
            createCreditInfoForBase(msgMap, linePMsg);
        }
        // Usage Charge
        BigDecimal preCrBllgMtrPk = null;
        for (int i = 0; i < pMsg.xxMtrLineList.getValidCount(); i++) {
            NSZC047007_xxMtrLineListPMsg linePMsg = pMsg.xxMtrLineList.no(i);
            BigDecimal crBllgMtrPk = linePMsg.dsContrBllgMtrPk_ML.getValue();
            if (hasValue(preCrBllgMtrPk) && preCrBllgMtrPk.compareTo(crBllgMtrPk) == 0) {
                continue;
            }
            preCrBllgMtrPk = crBllgMtrPk;

            this.usgSvcContrBllgGrpSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_GRP_SQ);
            this.contrInfoList = NSZC0470Query.getInstance().getContrInfo(pMsg.glblCmpyCd.getValue(), linePMsg.dsContrDtlPk_ML.getValue());
            createCreditInfoForUsg(msgMap, linePMsg);
        }
        // END 04/18/2016 T.Aoyagi [QC#7056, MOD]

        // Create Debit Info
        if (SVC_CR_REBIL_PROC.CREDIT_AND_REBILL.equals(svcCrRebilProcCd)) {

            // Base Charge
            for (int i = 0; i < pMsg.xxBaseLineList.getValidCount(); i++) {
                NSZC047007_xxBaseLineListPMsg linePMsg = pMsg.xxBaseLineList.no(i);
                // START 2016/03/24 T.Aoyagi [QC#5901, ADD]
                this.contrInfoList = NSZC0470Query.getInstance().getContrInfo(pMsg.glblCmpyCd.getValue(), linePMsg.dsContrDtlPk_BL.getValue());
                // END 2016/03/24 T.Aoyagi [QC#5901, ADD]
                createDebitInfoForBase(msgMap, linePMsg);
            }
            // Usage Charge
            BigDecimal preBllgMtrPk = null;
            for (int i = 0; i < pMsg.xxMtrLineList.getValidCount(); i++) {
                NSZC047007_xxMtrLineListPMsg linePMsg = pMsg.xxMtrLineList.no(i);
                BigDecimal bllgMtrPk = linePMsg.dsContrBllgMtrPk_ML.getValue();

                if (hasValue(preBllgMtrPk) && preBllgMtrPk.compareTo(bllgMtrPk) == 0) {
                    continue;
                }
                preBllgMtrPk = bllgMtrPk;

                this.usgSvcContrBllgGrpSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_GRP_SQ);
                // START 2016/03/24 T.Aoyagi [QC#5901, ADD]
                this.contrInfoList = NSZC0470Query.getInstance().getContrInfo(pMsg.glblCmpyCd.getValue(), linePMsg.dsContrDtlPk_ML.getValue());
                // END 2016/03/24 T.Aoyagi [QC#5901, ADD]
                createDebitInfoForUsg(msgMap, linePMsg);
            }
        }
    }

    private void checkParameter(S21ApiMessageMap msgMap) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.glblCmpyCd, ZZZM9007E, new String[]{"Global Company Code"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.slsDt, ZZZM9007E, new String[]{"Salse Date"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.svcInvNum, ZZZM9007E, new String[]{"Service Invoice Number"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.svcCrRebilPk, ZZZM9007E, new String[]{"Service Credit Rebill PK"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.svcCrRebilProcCd, ZZZM9007E, new String[]{"Service Credit Rebill Process Code"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.estFlg, ZZZM9007E, new String[]{"Estimate Flag"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.delFlg, ZZZM9007E, new String[]{"Delete Flag"});
    }

    private void deleteSchdAndSmry(S21ApiMessageMap msgMap) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        if (!FLG_ON_Y.equals(pMsg.delFlg.getValue()) && !hasValue(pMsg.svcCrRebilDtlPk)) {
            return;
        }
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal svcCrRebilPk = pMsg.svcCrRebilPk.getValue();
        BigDecimal svcCrRebilDtlPk = pMsg.svcCrRebilDtlPk.getValue();

        List<Map<String, BigDecimal>> schdMapList = NSZC0470Query.getInstance().getBllgSchdBySvcCrRebilPk(glblCmpyCd, svcCrRebilPk, svcCrRebilDtlPk);
        List<BigDecimal> schdPkList = NSZC047001CommonLogic.getSchdPkList(schdMapList);
        List<BigDecimal> schdSmryPkList = NSZC047001CommonLogic.getSchdSmryPkList(schdMapList);

        for (BigDecimal schdPk : schdPkList) {

            // START 04/18/2016 T.Aoyagi [QC#7056, DEL]
//            DS_CONTR_BLLG_SCHDTMsg schdTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, schdPk);
//            String baseChrgFlg = schdTMsg.baseChrgFlg.getValue();
//            // START 2016/03/24 T.Aoyagi [QC#5901, MOD]
//            String dsContrDtlTpCd = NSZC0470Query.getInstance().getDsContrDtlTp(pMsg.glblCmpyCd.getValue(), schdTMsg.dsContrDtlPk.getValue());
            // END 04/18/2016 T.Aoyagi [QC#7056, DEL]

            NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, schdPk);
            NSZC0470Query.getInstance().removeSchd(glblCmpyCd, schdPk);
            // START 2017/10/03 E.Kameishi [QC18636, ADD]
            NSZC0470Query.getInstance().removeSchdTestMtrSmry(glblCmpyCd, schdPk);
            // END 2017/10/03 E.Kameishi [QC18636, ADD]
            // START 04/18/2016 T.Aoyagi [QC#7056, DEL]
//            if (FLG_OFF_N.equals(baseChrgFlg) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) {
//            // END 2016/03/24 T.Aoyagi [QC#5901, MOD]
//                List<BigDecimal> childSchdPkList = NSZC0470Query.getInstance().getDeleteChildSchdList(glblCmpyCd, schdPk);
//                for (BigDecimal childSchdPk : childSchdPkList) {
//                    NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, childSchdPk);
//                    NSZC0470Query.getInstance().removeSchd(glblCmpyCd, childSchdPk);
//                }
//            }
            // END 04/18/2016 T.Aoyagi [QC#7056, DEL]
        }

        for (BigDecimal schdSmryPk : schdSmryPkList) {
            NSZC0470Query.getInstance().removeSchdSmry(glblCmpyCd, schdSmryPk);

            List<BigDecimal> schdMtrPkList = NSZC0470Query.getInstance().getDsContrBllgSchdMtrForCancel(glblCmpyCd, schdSmryPk);
            NSZC0470Query.getInstance().removeSchdMtr(glblCmpyCd, schdMtrPkList);
        }
    }

    // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
    private void createCreditInfoForBase(S21ApiMessageMap msgMap, NSZC047007_xxBaseLineListPMsg linePMsg) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String svcInvNum = pMsg.svcInvNum.getValue();
        BigDecimal svcInvLinePk = linePMsg.svcInvLinePk_BL.getValue();
        BigDecimal dsContrDtlPk = linePMsg.dsContrDtlPk_BL.getValue();
        String baseChrgFlg = FLG_ON_Y;

        SVC_INVTMsg svcInvTMsg = NSZC0470Query.getInstance().getSvcInvTMsg(glblCmpyCd, svcInvNum);
        SVC_INV_LINETMsg svcInvLineTMsg = NSZC0470Query.getInstance().getSvcInvLineTMsgByPk(glblCmpyCd, svcInvLinePk);

        if (DS_CONTR_DTL_TP.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
            Map<String, Object> origSchdInfo = getOrigSchdInfo(msgMap, null, baseChrgFlg);
            if (origSchdInfo != null) {
                // START 2017/05/18 K.Kitachi [QC#18541, MOD]
//                DS_CONTR_BLLG_SCHDTMsg aggLineSchdTMsg = createBllgSchdForAggLine(msgMap, dsContrDtlPk, svcInvTMsg, origSchdInfo, null, baseChrgFlg);
                DS_CONTR_BLLG_SCHDTMsg aggLineSchdTMsg = createBllgSchdForAggLine(msgMap, dsContrDtlPk, svcInvTMsg, origSchdInfo, null, baseChrgFlg, FLG_OFF_N);
                // END 2017/05/18 K.Kitachi [QC#18541, MOD]
                createContrBllgForAggLine(msgMap, dsContrDtlPk, svcInvTMsg, aggLineSchdTMsg, origSchdInfo, null, baseChrgFlg);
            }
            return;
        }
        // START 2017/09/12 K.Kojima [QC#20835,ADD]
        DS_CONTR_BLLG_SCHDTMsg origDsContrBllgSchd = null;
        List<Map<String, BigDecimal>> baseBllgSchdList = NSZC0470Query.getInstance().getBllgSchdBySvcInvNum(glblCmpyCd, dsContrDtlPk, svcInvNum, null, baseChrgFlg);
        if (!baseBllgSchdList.isEmpty()) {
            origDsContrBllgSchd = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, baseBllgSchdList.get(0).get("DS_CONTR_BLLG_SCHD_PK"));
        }
        if (origDsContrBllgSchd == null) {
            origDsContrBllgSchd = new DS_CONTR_BLLG_SCHDTMsg();
        }
        // END 2017/09/12 K.Kojima [QC#20835,ADD]
        // START 2017/05/18 K.Kitachi [QC#18541, MOD]
//        DS_CONTR_BLLG_SCHDTMsg schdTMsg = createBllgSchd(msgMap, svcInvTMsg, svcInvLineTMsg, null, baseChrgFlg);
        // START 2017/09/12 K.Kojima [QC#20835,MOD]
        // DS_CONTR_BLLG_SCHDTMsg schdTMsg = createBllgSchd(msgMap, svcInvTMsg, svcInvLineTMsg, null, baseChrgFlg, FLG_OFF_N);
        DS_CONTR_BLLG_SCHDTMsg schdTMsg = createBllgSchd(msgMap, svcInvTMsg, svcInvLineTMsg, null, baseChrgFlg, FLG_OFF_N, origDsContrBllgSchd);
        // END 2017/09/12 K.Kojima [QC#20835,MOD]
        // END 2017/05/18 K.Kitachi [QC#18541, MOD]
        SVC_CONTR_BLLGTMsg svcContrBllgTMsg = createContrBllg(msgMap, svcInvTMsg, svcInvLineTMsg, schdTMsg, null, baseChrgFlg);


        SVC_CONTR_BASE_BLLGTMsg svcContrBaseBllgTMsg = createContrBaseBllg(msgMap, svcInvTMsg, svcInvLineTMsg, svcContrBllgTMsg);
        SVC_INV_LINE_ALLOCTMsgArray svcInvLineAllocTMsgArray = NSZC0470Query.getInstance().getSvcInvLineAllocTMsgArray(glblCmpyCd, svcInvLineTMsg.svcInvLinePk.getValue());

        if (svcInvLineAllocTMsgArray != null) {
            for (int i = 0; i < svcInvLineAllocTMsgArray.getValidCount(); i++) {
                SVC_INV_LINE_ALLOCTMsg svcInvLineAllocTMsg = svcInvLineAllocTMsgArray.no(i);
                createContrBllgAlloc(msgMap, svcContrBllgTMsg, svcContrBaseBllgTMsg, null, null, svcInvLineAllocTMsg);
            }
        }

        // Additional charge for base
        List<BigDecimal> addlSvcInvLinePkList = NSZC0470Query.getInstance().getSvcInvLinePkForAddl(glblCmpyCd, svcInvLinePk);
        for (BigDecimal addlSvcInvLinePk : addlSvcInvLinePkList) {
            createCreditInfoForAddl(msgMap, addlSvcInvLinePk, svcContrBllgTMsg, svcContrBaseBllgTMsg, null, baseChrgFlg);
        }
    }

    private void createCreditInfoForUsg(S21ApiMessageMap msgMap, NSZC047007_xxMtrLineListPMsg linePMsg) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        String svcInvNum = pMsg.svcInvNum.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal svcInvLinePk = linePMsg.svcInvLinePk_ML.getValue();
        BigDecimal dsContrDtlPk = linePMsg.dsContrDtlPk_ML.getValue();
        BigDecimal bllgMtrPk = linePMsg.dsContrBllgMtrPk_ML.getValue();
        String baseChrgFlg = FLG_OFF_N;

        SVC_INVTMsg svcInvTMsg = NSZC0470Query.getInstance().getSvcInvTMsg(glblCmpyCd, svcInvNum);
        SVC_INV_LINETMsg svcInvLineTMsg = NSZC0470Query.getInstance().getSvcInvLineTMsgByPk(glblCmpyCd, svcInvLinePk);

        if (DS_CONTR_DTL_TP.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
            Map<String, Object> origSchdInfo = getOrigSchdInfo(msgMap, bllgMtrPk, baseChrgFlg);
            if (origSchdInfo != null) {
                // START 2017/05/18 K.Kitachi [QC#18541, MOD]
//                DS_CONTR_BLLG_SCHDTMsg aggLineSchdTMsg = createBllgSchdForAggLine(msgMap, dsContrDtlPk, svcInvTMsg, origSchdInfo, bllgMtrPk, baseChrgFlg);
                DS_CONTR_BLLG_SCHDTMsg aggLineSchdTMsg = createBllgSchdForAggLine(msgMap, dsContrDtlPk, svcInvTMsg, origSchdInfo, bllgMtrPk, baseChrgFlg, FLG_ON_Y);
                // END 2017/05/18 K.Kitachi [QC#18541, MOD]
                createContrBllgForAggLine(msgMap, dsContrDtlPk, svcInvTMsg, aggLineSchdTMsg, origSchdInfo, bllgMtrPk, baseChrgFlg);
            }
            return;
        }
        // START 2017/09/12 K.Kojima [QC#20835,ADD]
        DS_CONTR_BLLG_SCHDTMsg origDsContrBllgSchd = null;
        List<Map<String, BigDecimal>> usgBllgSchdList = NSZC0470Query.getInstance().getBllgSchdBySvcInvNum(glblCmpyCd, dsContrDtlPk, svcInvNum, bllgMtrPk, baseChrgFlg);
        if (!usgBllgSchdList.isEmpty()) {
            origDsContrBllgSchd = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, usgBllgSchdList.get(0).get("DS_CONTR_BLLG_SCHD_PK"));
        }
        if (origDsContrBllgSchd == null) {
            origDsContrBllgSchd = new DS_CONTR_BLLG_SCHDTMsg();
        }
        // END 2017/09/12 K.Kojima [QC#20835,ADD]

        SVC_INV_LINE_MTRTMsg svcInvLineMtrTMsg = NSZC0470Query.getInstance().getSvcInvLineMtrTMsg(glblCmpyCd, svcInvLinePk);
        SVC_INV_LINE_XS_MTRTMsgArray svcInvLineXsMtrTMsgArray = NSZC0470Query.getInstance().getSvcInvLineXsMtrTMsg(glblCmpyCd, svcInvLinePk);
        SVC_INV_LINE_ALLOCTMsgArray svcInvLineAllocTMsgArray = NSZC0470Query.getInstance().getSvcInvLineAllocTMsgArray(glblCmpyCd, svcInvLineTMsg.svcInvLinePk.getValue());

        // START 2017/05/18 K.Kitachi [QC#18541, MOD]
//        DS_CONTR_BLLG_SCHDTMsg schdTMsg = createBllgSchd(msgMap, svcInvTMsg, svcInvLineTMsg, svcInvLineMtrTMsg, baseChrgFlg);
        // START 2017/09/12 K.Kojima [QC#20835,MOD]
        // DS_CONTR_BLLG_SCHDTMsg schdTMsg = createBllgSchd(msgMap, svcInvTMsg, svcInvLineTMsg, svcInvLineMtrTMsg, baseChrgFlg, FLG_ON_Y);
        DS_CONTR_BLLG_SCHDTMsg schdTMsg = createBllgSchd(msgMap, svcInvTMsg, svcInvLineTMsg, svcInvLineMtrTMsg, baseChrgFlg, FLG_ON_Y, origDsContrBllgSchd);
        // END 2017/09/12 K.Kojima [QC#20835,MOD]
        // END 2017/05/18 K.Kitachi [QC#18541, MOD]
        SVC_CONTR_BLLGTMsg svcContrBllgTMsg = createContrBllg(msgMap, svcInvTMsg, svcInvLineTMsg, schdTMsg, null, baseChrgFlg);

        SVC_CONTR_MTR_BLLGTMsg svcContrMtrBllgTMsg = createSvcContrMtrBllg(msgMap, svcInvLineTMsg, svcInvLineMtrTMsg, svcContrBllgTMsg);

        if (DS_CONTR_CATG.FLEET.equals((String) this.contrInfoList.get(0).get("DS_CONTR_CATG_CD"))) {
            if (!DS_CONTR_DTL_TP.FLEET.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
                return;
            }
        }

        if (svcInvLineXsMtrTMsgArray != null) {
            for (int i = 0; i < svcInvLineXsMtrTMsgArray.getValidCount(); i++) {
                SVC_INV_LINE_XS_MTRTMsg svcInvLineXsMtrTMsg = svcInvLineXsMtrTMsgArray.no(i);
                createContrXsMtrBllg(msgMap, svcInvLineTMsg, svcInvLineXsMtrTMsg, svcContrBllgTMsg, svcContrMtrBllgTMsg);
            }
        }

        if (svcInvLineAllocTMsgArray != null) {
            for (int i = 0; i < svcInvLineAllocTMsgArray.getValidCount(); i++) {
                SVC_INV_LINE_ALLOCTMsg svcInvLineAllocTMsg = svcInvLineAllocTMsgArray.no(i);
                createContrBllgAlloc(msgMap, svcContrBllgTMsg, null, svcContrMtrBllgTMsg, null, svcInvLineAllocTMsg);
            }
        }
        // Add Start 10/24/2017 <QC#22008>
        if (DS_CONTR_CATG.FLEET.equals((String) this.contrInfoList.get(0).get("DS_CONTR_CATG_CD"))) {
            createCreditInfoForFleetMachine(msgMap, linePMsg, svcInvTMsg, schdTMsg, svcContrBllgTMsg, svcContrMtrBllgTMsg);
        }
        // Add End   10/24/2017 <QC#22008>

        // Additional charge for usage
        List<BigDecimal> addlSvcInvLinePkList = NSZC0470Query.getInstance().getSvcInvLinePkForAddl(glblCmpyCd, svcInvLinePk);
        for (BigDecimal addlSvcInvLinePk : addlSvcInvLinePkList) {
            createCreditInfoForAddl(msgMap, addlSvcInvLinePk, svcContrBllgTMsg, null, svcContrMtrBllgTMsg, baseChrgFlg);
        }
    }
    // END 04/18/2016 T.Aoyagi [QC#7056, MOD]

    private void createCreditInfoForAddl(S21ApiMessageMap msgMap, BigDecimal svcInvLinePk, SVC_CONTR_BLLGTMsg prntSvcContrBllgTMsg, SVC_CONTR_BASE_BLLGTMsg svcContrBaseBllgTMsg, SVC_CONTR_MTR_BLLGTMsg svcContrMtrBllgTMsg, String baseChrgFlg) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        String svcInvNum = pMsg.svcInvNum.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        SVC_INVTMsg svcInvTMsg = NSZC0470Query.getInstance().getSvcInvTMsg(glblCmpyCd, svcInvNum);
        SVC_INV_LINETMsg svcInvLineTMsg = NSZC0470Query.getInstance().getSvcInvLineTMsgByPk(glblCmpyCd, svcInvLinePk);
        SVC_INV_LINE_ALLOCTMsgArray svcInvLineAllocTMsgArray = NSZC0470Query.getInstance().getSvcInvLineAllocTMsgArray(glblCmpyCd, svcInvLineTMsg.svcInvLinePk.getValue());

        // SVC_CONTR_BLLG
        SVC_CONTR_BLLGTMsg svcContrBllgTMsg = createContrBllg(msgMap, svcInvTMsg, svcInvLineTMsg, null, prntSvcContrBllgTMsg, baseChrgFlg);
        BigDecimal svcContrBllgPk = svcContrBllgTMsg.svcContrBllgPk.getValue();
        BigDecimal svcContrBaseBllgPk = null;
        BigDecimal svcContrMtrBllgPk = null;
        if (svcContrBaseBllgTMsg != null) {
            svcContrBaseBllgPk = svcContrBaseBllgTMsg.svcContrBaseBllgPk.getValue();
        }
        if (svcContrMtrBllgTMsg != null) {
            svcContrMtrBllgPk = svcContrMtrBllgTMsg.svcContrMtrBllgPk.getValue();
        }
        SVC_INV_LINE_ADDL_CHRGTMsg svcInvLineAddlChrgTMsg = NSZC0470Query.getInstance().getSvcInvLineAddlChrg(glblCmpyCd, svcInvLinePk);

        // SVC_CONTR_ADDL_CHRG_BLLG
        SVC_CONTR_ADDL_CHRG_BLLGTMsg svcContrAddlChrgBllgTMsg = createContrAddlChrgBllg(msgMap, svcInvLineTMsg, svcInvLineAddlChrgTMsg, svcContrBllgPk, svcContrBaseBllgPk, svcContrMtrBllgPk);

        if (svcInvLineAllocTMsgArray != null) {
            for (int i = 0; i < svcInvLineAllocTMsgArray.getValidCount(); i++) {
                SVC_INV_LINE_ALLOCTMsg svcInvLineAllocTMsg = svcInvLineAllocTMsgArray.no(i);

                // SVC_CONTR_BLLG_ALLOC
                createContrBllgAlloc(msgMap, svcContrBllgTMsg, svcContrBaseBllgTMsg, svcContrMtrBllgTMsg, svcContrAddlChrgBllgTMsg, svcInvLineAllocTMsg);
            }
        }
    }

    // START 2017/05/18 K.Kitachi [QC#18541, MOD]
    // START 2017/09/12 K.Kojima [QC#20835,MOD]
    // private DS_CONTR_BLLG_SCHDTMsg createBllgSchd(S21ApiMessageMap msgMap, SVC_INVTMsg svcInvTMsg, SVC_INV_LINETMsg svcInvLineTMsg, SVC_INV_LINE_MTRTMsg svcInvLineMtrTMsg, String baseChrgFlg, String mtrEntryCpltFlg) {
    private DS_CONTR_BLLG_SCHDTMsg createBllgSchd(S21ApiMessageMap msgMap, SVC_INVTMsg svcInvTMsg, SVC_INV_LINETMsg svcInvLineTMsg, SVC_INV_LINE_MTRTMsg svcInvLineMtrTMsg, String baseChrgFlg, String mtrEntryCpltFlg, DS_CONTR_BLLG_SCHDTMsg origDsContrBllgSchd) {
    // END 2017/09/12 K.Kojima [QC#20835,MOD]
    // END 2017/05/18 K.Kitachi [QC#18541, MOD]

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();

        BigDecimal dsContrBllgSchdPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_SCHD_SQ);

        DS_CONTR_BLLG_SCHDTMsg inTMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        setValue(inTMsg.dsContrDtlPk, svcInvLineTMsg.dsContrDtlPk);
        inTMsg.dsContrBllgSchdSmryPk.clear();
        inTMsg.dsContrPrcEffPk.clear();
        // START 2017/09/12 K.Kojima [QC#20835,MOD]
        // inTMsg.dsContrPrcEffSqNum.clear();
        // inTMsg.dsContrBllgSchdSqNum.clear();
        // inTMsg.dsContrBllgSchdLvlNum.clear();
        setValue(inTMsg.dsContrPrcEffSqNum, origDsContrBllgSchd.dsContrPrcEffSqNum);
        setValue(inTMsg.dsContrBllgSchdSqNum, origDsContrBllgSchd.dsContrBllgSchdSqNum);
        setValue(inTMsg.dsContrBllgSchdLvlNum, origDsContrBllgSchd.dsContrBllgSchdLvlNum);
        // END 2017/09/12 K.Kojima [QC#20835,MOD]
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            setValue(inTMsg.baseChrgFlg, FLG_ON_Y);
            setValue(inTMsg.usgChrgFlg, FLG_OFF_N);
            // START 2017/10/13 K.Kim [QC#21778,MOD]
            // BigDecimal basePrcDealAmt = add(svcInvLineTMsg.invLineDealNetAmt.getValue(), svcInvLineTMsg.basePrcAdjDealAmt.getValue());
            // setValue(inTMsg.basePrcDealAmt, basePrcDealAmt);
            // BigDecimal basePrcFuncAmt = add(svcInvLineTMsg.invLineFuncNetAmt.getValue(), svcInvLineTMsg.basePrcAdjFuncAmt.getValue());
            // setValue(inTMsg.basePrcFuncAmt, basePrcFuncAmt);
            setValue(inTMsg.basePrcDealAmt, origDsContrBllgSchd.basePrcDealAmt.getValue());
            setValue(inTMsg.basePrcFuncAmt, origDsContrBllgSchd.basePrcFuncAmt.getValue());
            // END 2017/10/13 K.Kim [QC#21778,MOD]
            setValue(inTMsg.baseActlPrcDealAmt, svcInvLineTMsg.invLineDealNetAmt);
            setValue(inTMsg.baseActlPrcFuncAmt, svcInvLineTMsg.invLineFuncNetAmt);
        } else {
            setValue(inTMsg.baseChrgFlg, FLG_OFF_N);
            setValue(inTMsg.usgChrgFlg, FLG_ON_Y);
            BigDecimal readMtrCnt = subtract(svcInvLineMtrTMsg.totCopyQty.getValue(), svcInvLineMtrTMsg.prevTotCopyQty.getValue());
            setValue(inTMsg.readMtrCnt, readMtrCnt);
            setValue(inTMsg.bllgMtrCnt, svcInvLineMtrTMsg.bllgCopyQty);
            setValue(inTMsg.mtrChrgDealAmt, svcInvLineTMsg.invLineDealNetAmt);
            setValue(inTMsg.mtrChrgFuncAmt, svcInvLineTMsg.invLineFuncNetAmt);
            setValue(inTMsg.dsContrBllgMtrPk, svcInvLineMtrTMsg.dsContrBllgMtrPk);
            setValue(inTMsg.svcPhysMtrReadGrpSq, svcInvLineMtrTMsg.svcPhysMtrReadGrpSq);
        }
        inTMsg.skipRecovTpCd.clear();
        inTMsg.svcInvNum.clear();
        inTMsg.invDt.clear();
        setValue(inTMsg.nextBllgDt, pMsg.slsDt);
        setValue(inTMsg.bllgSchdFromDt, svcInvLineTMsg.bllgPerFromDt);
        setValue(inTMsg.bllgSchdThruDt, svcInvLineTMsg.bllgPerThruDt);
        inTMsg.bllgCycleCd.clear();
        setValue(inTMsg.bllgSchdPrrtFlg, FLG_OFF_N);
        setValue(inTMsg.dsContrTrmnFlg, FLG_OFF_N);
        setValue(inTMsg.ccyCd, svcInvTMsg.dealCcyCd);
        inTMsg.bllgSchdTrxSrcTpCd.clear();
        setValue(inTMsg.bllgStageFlg, FLG_ON_Y);
        setValue(inTMsg.invFlg, FLG_OFF_N);
        setValue(inTMsg.invTpCd, INV_TP.CREDIT_MEMO);
        setValue(inTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.BILLED);
        setValue(inTMsg.bllblFlg, FLG_ON_Y);
        inTMsg.rvsSchdDt.clear();
        // START 2017/05/18 K.Kitachi [QC#18541, MOD]
//        setValue(inTMsg.mtrEntryCpltFlg, FLG_OFF_N);
        setValue(inTMsg.mtrEntryCpltFlg, mtrEntryCpltFlg);
        // END 2017/05/18 K.Kitachi [QC#18541, MOD]
        setValue(inTMsg.dsContrDtlTpCd, (String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"));
        BigDecimal prntDsContrBllgSchdPk = null;
        BigDecimal prntDsContrDtlPk = null;
        if (DS_CONTR_CATG.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_CATG_CD"))) {
            prntDsContrDtlPk = (BigDecimal) this.contrInfoList.get(0).get("PRNT_DS_CONTR_DTL_PK");
            if (!DS_CONTR_DTL_TP.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
                // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
                // START 2016/01/25 T.Aoyagi [CSA-QC3623, ADD]
                if (FLG_ON_Y.equals(baseChrgFlg)) {
                // END   2016/01/25 T.Aoyagi [CSA-QC3623, ADD]
                    // START 05/16/2016 T.Aoyagi [QC#8183, MOD]
                    prntDsContrBllgSchdPk = NSZC0470Query.getInstance().getPrntBllgSchdPkForRebil(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, null, inTMsg.nextBllgDt.getValue(), baseChrgFlg, INV_TP.CREDIT_MEMO, pMsg.svcCrRebilDtlPk.getValue());
                    // END 05/16/2016 T.Aoyagi [QC#8183, MOD]
                // START 2016/01/25 T.Aoyagi [CSA-QC3623, ADD]
                } else {
                    // START 05/16/2016 T.Aoyagi [QC#8183, MOD]
                    prntDsContrBllgSchdPk = NSZC0470Query.getInstance().getPrntBllgSchdPkForUsgRebil(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, svcInvLineMtrTMsg.dsContrBllgMtrPk.getValue(), inTMsg.nextBllgDt.getValue(), INV_TP.CREDIT_MEMO, pMsg.svcCrRebilDtlPk.getValue());
                    // END 05/16/2016 T.Aoyagi [QC#8183, MOD]
                }
                // END   2016/01/25 T.Aoyagi [CSA-QC3623, ADD]
                // END 04/18/2016 T.Aoyagi [QC#7056, MOD]
            }
        }
        setValue(inTMsg.prntDsContrBllgSchdPk, prntDsContrBllgSchdPk);
        setValue(inTMsg.bllgPerMthAot, svcInvLineTMsg.bllgPerMthAot);
        setValue(inTMsg.prntDsContrDtlPk, prntDsContrDtlPk);
        setValue(inTMsg.svcCrRebilPk, pMsg.svcCrRebilPk);
        setValue(inTMsg.svcCrRebilDtlPk, pMsg.svcCrRebilDtlPk);
        setValue(inTMsg.origDsContrBllgSchdPk, svcInvLineTMsg.dsContrBllgSchdPk);
        setValue(inTMsg.dsBllgSchdTpCd, DS_BLLG_SCHD_TP.CREDIT_CREDIT_AND_REBILL);
        setValue(inTMsg.basePrcAdjDealAmt, svcInvLineTMsg.basePrcAdjDealAmt);
        setValue(inTMsg.basePrcAdjFuncAmt, svcInvLineTMsg.basePrcAdjFuncAmt);
        setValue(inTMsg.dealTaxAmt, svcInvLineTMsg.invLineDealTaxAmt);
        setValue(inTMsg.funcTaxAmt, svcInvLineTMsg.invLineFuncTaxAmt);
        setValue(inTMsg.bllgCalcFlg, FLG_ON_Y);
        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_BLLG_SCHD"});
        }
        return inTMsg;
    }

    // Add Start 10/24/2017 <QC#22008>
    private void createCreditInfoForFleetMachine(S21ApiMessageMap msgMap, NSZC047007_xxMtrLineListPMsg linePMsg, SVC_INVTMsg orgSvcInvTMsg, DS_CONTR_BLLG_SCHDTMsg prntSchdTMsg, SVC_CONTR_BLLGTMsg prntSvcContrBllgTMsg, SVC_CONTR_MTR_BLLGTMsg prntSvcContrMtrBllgTMsg) {
        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal svcInvLinePk = linePMsg.svcInvLinePk_ML.getValue();
        BigDecimal svcContrBllgGrpSq = prntSvcContrBllgTMsg.svcContrBllgGrpSq.getValue();
        
        
        List<BigDecimal> orgSvcInvLineChildPkList = NSZC0470Query.getInstance().getOrgSvcINvLinePkFleetMachine(glblCmpyCd, svcInvLinePk);
        for (BigDecimal orgSvcInvLineChildPk : orgSvcInvLineChildPkList) {
            SVC_INV_LINETMsg orgSvcInvLine = NSZC0470Query.getInstance().getSvcInvLineTMsgByPk(glblCmpyCd, orgSvcInvLineChildPk);
            SVC_INV_LINE_MTRTMsg orgSvcInvLineMtrTMsg = NSZC0470Query.getInstance().getSvcInvLineMtrTMsg(glblCmpyCd, orgSvcInvLineChildPk);
            if (orgSvcInvLine == null || orgSvcInvLineMtrTMsg == null) {
                continue;
            }
            // Create Credit Info For Fleet Machine
            // DS_CONTR_BLLG_SCHD
            DS_CONTR_BLLG_SCHDTMsg fltMachDsContrBllgSchd = createDsContrBllgSchdForFleetMachine(msgMap, orgSvcInvTMsg, prntSchdTMsg, orgSvcInvLine, orgSvcInvLineMtrTMsg);
            // SVC_CONTR_BLLG
            SVC_CONTR_BLLGTMsg fltMachSvcContrBllg = createSvcContrBllgForFleetMachine(msgMap, orgSvcInvTMsg, orgSvcInvLine, orgSvcInvLineMtrTMsg, fltMachDsContrBllgSchd, svcContrBllgGrpSq);
            // SVC_CONTR_MTR_BLLG
            SVC_CONTR_MTR_BLLGTMsg fltMachSvcContrMtrBllg = createSvcContrMtrBllgForFleetMachine(msgMap, orgSvcInvTMsg, orgSvcInvLine, orgSvcInvLineMtrTMsg, fltMachSvcContrBllg);
        }

    }

    private DS_CONTR_BLLG_SCHDTMsg createDsContrBllgSchdForFleetMachine(S21ApiMessageMap msgMap, SVC_INVTMsg orgSvcInvTMsg, DS_CONTR_BLLG_SCHDTMsg prntSchdTMsg, SVC_INV_LINETMsg orgSvcInvLine, SVC_INV_LINE_MTRTMsg orgSvcInvLineMtrTMsg) {
        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal orgFltMachBllgSchdPk = orgSvcInvLine.dsContrBllgSchdPk.getValue();
        DS_CONTR_BLLG_SCHDTMsg orgFltMachBllgSchd = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, orgFltMachBllgSchdPk);

        BigDecimal dsContrBllgSchdPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_SCHD_SQ);

        DS_CONTR_BLLG_SCHDTMsg inTMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        setValue(inTMsg.dsContrDtlPk, orgSvcInvLine.dsContrDtlPk);
        inTMsg.dsContrBllgSchdSmryPk.clear();
        inTMsg.dsContrPrcEffPk.clear();
        inTMsg.dsContrPrcEffSqNum.clear();
        inTMsg.dsContrBllgSchdSqNum.clear();
        inTMsg.dsContrBllgSchdLvlNum.clear();
        setValue(inTMsg.baseChrgFlg, FLG_OFF_N);
        setValue(inTMsg.usgChrgFlg, FLG_ON_Y);
        inTMsg.skipRecovTpCd.clear();
        inTMsg.svcInvNum.clear();
        inTMsg.invDt.clear();
        setValue(inTMsg.nextBllgDt, pMsg.slsDt);
        setValue(inTMsg.bllgSchdFromDt, orgSvcInvLine.bllgPerFromDt);
        setValue(inTMsg.bllgSchdThruDt, orgSvcInvLine.bllgPerThruDt);
        BigDecimal readMtrCnt = subtract(orgSvcInvLineMtrTMsg.totCopyQty.getValue(), orgSvcInvLineMtrTMsg.prevTotCopyQty.getValue());
        setValue(inTMsg.readMtrCnt, readMtrCnt);
        setValue(inTMsg.bllgMtrCnt, orgSvcInvLineMtrTMsg.bllgCopyQty);
        setValue(inTMsg.mtrChrgDealAmt, orgSvcInvLine.invLineDealNetAmt);
        setValue(inTMsg.mtrChrgFuncAmt, orgSvcInvLine.invLineFuncNetAmt);
        setValue(inTMsg.ccyCd, orgSvcInvTMsg.dealCcyCd);
        setValue(inTMsg.bllgStageFlg, FLG_ON_Y);
        setValue(inTMsg.invFlg, FLG_OFF_N);
        setValue(inTMsg.invTpCd, INV_TP.CREDIT_MEMO);
        setValue(inTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.BILLED);
        setValue(inTMsg.dsContrBllgMtrPk, orgSvcInvLineMtrTMsg.dsContrBllgMtrPk);
        setValue(inTMsg.bllblFlg, FLG_ON_Y);
        setValue(inTMsg.svcPhysMtrReadGrpSq, orgSvcInvLineMtrTMsg.svcPhysMtrReadGrpSq);
        setValue(inTMsg.dsContrDtlTpCd, orgFltMachBllgSchd.dsContrDtlTpCd);
        setValue(inTMsg.prntDsContrBllgSchdPk, prntSchdTMsg.dsContrBllgSchdPk);
        setValue(inTMsg.bllgPerMthAot, orgSvcInvLine.bllgPerMthAot);
        setValue(inTMsg.prntDsContrDtlPk, prntSchdTMsg.dsContrDtlPk);
        setValue(inTMsg.svcCrRebilPk, pMsg.svcCrRebilPk);
        setValue(inTMsg.svcCrRebilDtlPk, pMsg.svcCrRebilDtlPk);
        setValue(inTMsg.dsBllgSchdTpCd, DS_BLLG_SCHD_TP.CREDIT_CREDIT_AND_REBILL);
        
        setValue(inTMsg.bllgSchdPrrtFlg, FLG_OFF_N);
        setValue(inTMsg.dsContrTrmnFlg, FLG_OFF_N);
        setValue(inTMsg.mtrEntryCpltFlg, FLG_ON_Y);
        setValue(inTMsg.origDsContrBllgSchdPk, orgSvcInvLine.dsContrBllgSchdPk);
        setValue(inTMsg.basePrcAdjDealAmt, orgSvcInvLine.basePrcAdjDealAmt);
        setValue(inTMsg.basePrcAdjFuncAmt, orgSvcInvLine.basePrcAdjFuncAmt);
        setValue(inTMsg.dealTaxAmt, orgSvcInvLine.invLineDealTaxAmt);
        setValue(inTMsg.funcTaxAmt, orgSvcInvLine.invLineFuncTaxAmt);
        setValue(inTMsg.bllgCalcFlg, FLG_ON_Y);
        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_BLLG_SCHD"});
        }
        return inTMsg;
    }

    private SVC_CONTR_BLLGTMsg createSvcContrBllgForFleetMachine(S21ApiMessageMap msgMap, SVC_INVTMsg orgSvcInvTMsg, SVC_INV_LINETMsg orgSvcInvLine, SVC_INV_LINE_MTRTMsg orgSvcInvLineMtrTMsg, DS_CONTR_BLLG_SCHDTMsg fltMachSchdTMsg, BigDecimal svcContrBllgGrpSq) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        BigDecimal svcContrBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_SQ);

        SVC_CONTR_BLLGTMsg inTMsg = new SVC_CONTR_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcContrBllgPk, svcContrBllgPk);
        setValue(inTMsg.dsContrPk, orgSvcInvTMsg.dsContrPk);
        setValue(inTMsg.dsContrDtlPk, orgSvcInvLine.dsContrDtlPk);
        setValue(inTMsg.bllgCpltStsCd, BLLG_CPLT_STS.SCHEDULED);
        setValue(inTMsg.bllgApvlReqFlg, FLG_ON_Y);
        setValue(inTMsg.bllgApvlCpltFlg, FLG_ON_Y);
        inTMsg.bllgApvlPsnCd.clear();
        setValue(inTMsg.mthEndCloFlg, FLG_OFF_N);
        inTMsg.contrCloDay.clear();
        setValue(inTMsg.svcContrBllgFromDt, orgSvcInvLine.bllgPerFromDt);
        setValue(inTMsg.svcContrBllgThruDt, orgSvcInvLine.bllgPerThruDt);
        inTMsg.baseBllgTmgCd.clear();
        inTMsg.baseBllgCycleMthAot.clear();
        inTMsg.baseBllgNextBllgDt.clear();
        inTMsg.baseBllgLastBllgDt.clear();
        inTMsg.baseBllgInvUpToDt.clear();
        inTMsg.mtrBllgTmgCd.clear();
        inTMsg.mtrBllgCycleMthAot.clear();
        setValue(inTMsg.mtrBllgNextBllgDt, pMsg.slsDt);
        inTMsg.mtrBllgLastBllgDt.clear();
        inTMsg.mtrBllgInvUpToDt.clear();
        setValue(inTMsg.ccyCd, orgSvcInvTMsg.dealCcyCd);
        setValue(inTMsg.svcMachMstrPk, orgSvcInvLine.svcMachMstrPk);
        inTMsg.contrCloDt.clear();
        setValue(inTMsg.baseChrgFlg, FLG_OFF_N);
        setValue(inTMsg.usgChrgFlg, FLG_ON_Y);
        inTMsg.sellToCustCd.clear();
        setValue(inTMsg.fleetCalcFlg, FLG_OFF_N);
        setValue(inTMsg.prntSvcContrBllgPk, orgSvcInvLine.svcContrBllgPk);
        setValue(inTMsg.dsContrCatgCd, orgSvcInvTMsg.dsContrCatgCd);
        inTMsg.ovrdNextBllgDt.clear();
        setValue(inTMsg.bllgReProcFlg, FLG_OFF_N);
        inTMsg.bllgReProcPsnCd.clear();
        inTMsg.prevSvcContrBllgPk.clear();
        inTMsg.lastMtrReadTs.clear();
        setValue(inTMsg.dsContrBllgSchdPk, fltMachSchdTMsg.dsContrBllgSchdPk);
        setValue(inTMsg.dsAcctNum, orgSvcInvTMsg.dsAcctNum);
        setValue(inTMsg.aggrCalcFlg, FLG_OFF_N);
        setValue(inTMsg.svcCrRebilPk, pMsg.svcCrRebilPk);
        setValue(inTMsg.svcCrRebilDtlPk, pMsg.svcCrRebilDtlPk);
        setValue(inTMsg.invTpCd, INV_TP.CREDIT_MEMO);
        setValue(inTMsg.dsContrBllgMtrPk, orgSvcInvLineMtrTMsg.dsContrBllgMtrPk);
        setValue(inTMsg.svcContrBllgGrpSq, svcContrBllgGrpSq);
        setValue(inTMsg.bllgCalcEstFlg, pMsg.estFlg);
        setValue(inTMsg.addlChrgFlg, FLG_OFF_N);
        inTMsg.addlChrgBllgNextBllgDt.clear();
        inTMsg.addlChrgInvUpToDt.clear();
        setValue(inTMsg.billToCustCd, orgSvcInvTMsg.billToCustCd);
        setValue(inTMsg.origSvcInvNum, pMsg.svcInvNum);
        setValue(inTMsg.prcAllocByMachQtyFlg, FLG_OFF_N);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"SVC_CONTR_BLLG"});
        }
        return inTMsg;
    }


    private SVC_CONTR_MTR_BLLGTMsg createSvcContrMtrBllgForFleetMachine(S21ApiMessageMap msgMap, SVC_INVTMsg orgSvcInvTMsg, SVC_INV_LINETMsg orgSvcInvLine, SVC_INV_LINE_MTRTMsg orgSvcInvLineMtrTMsg, SVC_CONTR_BLLGTMsg fltMachSvcContrBllgTMsg) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        BigDecimal svcContrMtrBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_MTR_BLLG_SQ);

        SVC_CONTR_MTR_BLLGTMsg inTMsg = new SVC_CONTR_MTR_BLLGTMsg();

        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcContrMtrBllgPk, svcContrMtrBllgPk);
        setValue(inTMsg.svcContrBllgPk, fltMachSvcContrBllgTMsg.svcContrBllgPk);
        setValue(inTMsg.dsContrDtlPk, orgSvcInvLine.dsContrDtlPk);
        setValue(inTMsg.dsContrBllgMtrPk, orgSvcInvLineMtrTMsg.dsContrBllgMtrPk);
        setValue(inTMsg.mtrChrgDealAmt, orgSvcInvLineMtrTMsg.mtrChrgDealAmt);
        setValue(inTMsg.mtrChrgFuncAmt, orgSvcInvLineMtrTMsg.mtrChrgFuncAmt);
        setValue(inTMsg.mtrChrgDiscDealAmt, BigDecimal.ZERO);
        setValue(inTMsg.mtrChrgDiscFuncAmt, BigDecimal.ZERO);
        setValue(inTMsg.mtrEstFlg, orgSvcInvLineMtrTMsg.mtrEstFlg);
        setValue(inTMsg.mtrCopyQty, orgSvcInvLineMtrTMsg.mtrCopyQty);
        setValue(inTMsg.mdseCd, orgSvcInvLineMtrTMsg.mdseCd);
        setValue(inTMsg.ccyCd, orgSvcInvLineMtrTMsg.ccyCd);
        setValue(inTMsg.mtrBllgFromDt, orgSvcInvLine.bllgPerFromDt);
        setValue(inTMsg.mtrBllgThruDt, orgSvcInvLine.bllgPerThruDt);
        setValue(inTMsg.bllgCopyQty, orgSvcInvLineMtrTMsg.bllgCopyQty);
        setValue(inTMsg.testCopyQty, orgSvcInvLineMtrTMsg.testCopyQty);
        setValue(inTMsg.totCopyQty, orgSvcInvLineMtrTMsg.totCopyQty);
        setValue(inTMsg.prevTotCopyQty, orgSvcInvLineMtrTMsg.prevTotCopyQty);
        setValue(inTMsg.prevPhysMtrReadGrpSq, orgSvcInvLineMtrTMsg.prevPhysMtrReadGrpSq);
        setValue(inTMsg.svcPhysMtrReadGrpSq, orgSvcInvLineMtrTMsg.svcPhysMtrReadGrpSq);
        setValue(inTMsg.mtrPerMthAot, orgSvcInvLine.bllgPerMthAot);
        setValue(inTMsg.freeCopyCnt, orgSvcInvLineMtrTMsg.freeCopyCnt);
        setValue(inTMsg.usgFreeCopyCnt, orgSvcInvLineMtrTMsg.usgFreeCopyCnt);
        setValue(inTMsg.xsChrgTpCd, orgSvcInvLineMtrTMsg.xsChrgTpCd);
        setValue(inTMsg.rollOverRatio, orgSvcInvLineMtrTMsg.rollOverRatio);
        setValue(inTMsg.rollOverCnt, orgSvcInvLineMtrTMsg.rollOverCnt);
        setValue(inTMsg.copyInclQty, orgSvcInvLineMtrTMsg.copyInclQty);
        setValue(inTMsg.dealTaxAmt, orgSvcInvLine.invLineDealTaxAmt);
        setValue(inTMsg.funcTaxAmt, orgSvcInvLine.invLineFuncTaxAmt);
        setValue(inTMsg.contrMtrMultRate, orgSvcInvLineMtrTMsg.contrMtrMultRate);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"SVC_CONTR_MTR_BLLG"});
        }
        return inTMsg;
    }
    // Add End   10/24/2017 <QC#22008>
    
    private SVC_CONTR_BLLGTMsg createContrBllg(S21ApiMessageMap msgMap, SVC_INVTMsg svcInvTMsg, SVC_INV_LINETMsg svcInvLineTMsg, DS_CONTR_BLLG_SCHDTMsg schdTMsg, SVC_CONTR_BLLGTMsg prntSvcContrBllgTMsg, String baseChrgFlg) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        BigDecimal svcContrBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_SQ);
        String svcInvChrgTpCd = svcInvLineTMsg.svcInvChrgTpCd.getValue();

        SVC_CONTR_BLLGTMsg inTMsg = new SVC_CONTR_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcContrBllgPk, svcContrBllgPk);
        setValue(inTMsg.dsContrPk, svcInvTMsg.dsContrPk);
        setValue(inTMsg.dsContrDtlPk, svcInvLineTMsg.dsContrDtlPk);
        setValue(inTMsg.bllgCpltStsCd, BLLG_CPLT_STS.SCHEDULED);
        setValue(inTMsg.bllgApvlReqFlg, FLG_ON_Y);
        setValue(inTMsg.bllgApvlCpltFlg, FLG_ON_Y);
        inTMsg.bllgApvlPsnCd.clear();
        setValue(inTMsg.mthEndCloFlg, FLG_OFF_N);
        inTMsg.contrCloDay.clear();
        setValue(inTMsg.svcContrBllgFromDt, svcInvLineTMsg.bllgPerFromDt);
        setValue(inTMsg.svcContrBllgThruDt, svcInvLineTMsg.bllgPerThruDt);
        inTMsg.baseBllgTmgCd.clear();
        inTMsg.baseBllgCycleMthAot.clear();
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            setValue(inTMsg.baseBllgNextBllgDt, pMsg.slsDt);
            setValue(inTMsg.baseChrgFlg, FLG_ON_Y);
            setValue(inTMsg.usgChrgFlg, FLG_OFF_N);
            setValue(inTMsg.svcContrBllgGrpSq, this.baseSvcContrBllgGrpSq);
        } else {
            setValue(inTMsg.mtrBllgNextBllgDt, pMsg.slsDt);
            setValue(inTMsg.baseChrgFlg, FLG_OFF_N);
            setValue(inTMsg.usgChrgFlg, FLG_ON_Y);
            setValue(inTMsg.svcContrBllgGrpSq, this.usgSvcContrBllgGrpSq);
        }
        inTMsg.baseBllgLastBllgDt.clear();
        inTMsg.baseBllgInvUpToDt.clear();
        inTMsg.mtrBllgTmgCd.clear();
        inTMsg.mtrBllgCycleMthAot.clear();
        inTMsg.mtrBllgLastBllgDt.clear();
        inTMsg.mtrBllgInvUpToDt.clear();
        setValue(inTMsg.ccyCd, svcInvTMsg.dealCcyCd);
        setValue(inTMsg.svcMachMstrPk, svcInvLineTMsg.svcMachMstrPk);
        inTMsg.contrCloDt.clear();
        inTMsg.sellToCustCd.clear();
        String fleetCalcFlg = FLG_OFF_N;
        if (DS_CONTR_DTL_TP.FLEET.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
            fleetCalcFlg = FLG_ON_Y;
        }
        setValue(inTMsg.fleetCalcFlg, fleetCalcFlg);
        if (schdTMsg != null) {
            setValue(inTMsg.dsContrBllgSchdPk, schdTMsg.dsContrBllgSchdPk);
            setValue(inTMsg.dsContrBllgMtrPk, schdTMsg.dsContrBllgMtrPk);
        } else {
            setValue(inTMsg.dsContrBllgMtrPk, prntSvcContrBllgTMsg.dsContrBllgMtrPk);
        }

        BigDecimal prntSvcContrBllgPk = null;
        if (prntSvcContrBllgTMsg != null) {
            prntSvcContrBllgPk = prntSvcContrBllgTMsg.svcContrBllgPk.getValue();
        }
        if (DS_CONTR_CATG.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_CATG_CD"))) {
            BigDecimal prntDsContrDtlPk = (BigDecimal) this.contrInfoList.get(0).get("PRNT_DS_CONTR_DTL_PK");
            // START 2018/10/29 K.Kojima [QC#28999,MOD]
            // if (!DS_CONTR_DTL_TP.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
            if (!DS_CONTR_DTL_TP.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))
                    && !SVC_INV_CHRG_TP.ADDITIONAL_CHARGE.equals(svcInvLineTMsg.svcInvChrgTpCd.getValue())) {
            // END 2018/10/29 K.Kojima [QC#28999,MOD]
                // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
                // START 2016/01/25 T.Aoyagi [CSA-QC3623, ADD]
                BigDecimal prntDsContrBllgSchdPk;
                if (FLG_ON_Y.equals(baseChrgFlg)) {
                // END   2016/01/25 T.Aoyagi [CSA-QC3623, ADD]
                    // START 05/16/2016 T.Aoyagi [QC#8183, MOD]
                    prntDsContrBllgSchdPk = NSZC0470Query.getInstance().getPrntBllgSchdPkForRebil(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, null, pMsg.slsDt.getValue(), baseChrgFlg, INV_TP.CREDIT_MEMO, pMsg.svcCrRebilDtlPk.getValue());
                    // END 05/16/2016 T.Aoyagi [QC#8183, MOD]
                // START 2016/01/25 T.Aoyagi [CSA-QC3623, ADD]
                } else {
                    // START 05/16/2016 T.Aoyagi [QC#8183, MOD]
                    prntDsContrBllgSchdPk = NSZC0470Query.getInstance().getPrntBllgSchdPkForUsgRebil(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, inTMsg.dsContrBllgMtrPk.getValue(), pMsg.slsDt.getValue(), INV_TP.CREDIT_MEMO, pMsg.svcCrRebilDtlPk.getValue());
                    // END 05/16/2016 T.Aoyagi [QC#8183, MOD]
                }
                // END   2016/01/25 T.Aoyagi [CSA-QC3623, ADD]
                // END 04/18/2016 T.Aoyagi [QC#7056, MOD]
                SVC_CONTR_BLLGTMsg contrBllgTMsg = NSZC0470Query.getInstance().getSvcContrBllgTMsg(pMsg.glblCmpyCd.getValue(), prntDsContrBllgSchdPk);
                if (contrBllgTMsg != null) {
                    prntSvcContrBllgPk = contrBllgTMsg.svcContrBllgPk.getValue();
                }
            }
        }

        setValue(inTMsg.prntSvcContrBllgPk, prntSvcContrBllgPk);
        setValue(inTMsg.dsContrCatgCd, svcInvTMsg.dsContrCatgCd);
        inTMsg.ovrdNextBllgDt.clear();
        setValue(inTMsg.bllgReProcFlg, FLG_OFF_N);
        inTMsg.bllgReProcPsnCd.clear();
        inTMsg.prevSvcContrBllgPk.clear();
        inTMsg.lastMtrReadTs.clear();
        setValue(inTMsg.dsAcctNum, svcInvTMsg.dsAcctNum);
        String aggrCalcFlg = FLG_OFF_N;
        if (DS_CONTR_DTL_TP.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
            aggrCalcFlg = FLG_ON_Y;
        }
        setValue(inTMsg.aggrCalcFlg, aggrCalcFlg);
        setValue(inTMsg.svcCrRebilPk, pMsg.svcCrRebilPk);
        setValue(inTMsg.svcCrRebilDtlPk, pMsg.svcCrRebilDtlPk);
        setValue(inTMsg.invTpCd, INV_TP.CREDIT_MEMO);
        setValue(inTMsg.bllgCalcEstFlg, pMsg.estFlg);
        String addlChrgFlg = FLG_OFF_N;
        String addlChrgBllgNextBllgDt = "";
        if (SVC_INV_CHRG_TP.ADDITIONAL_CHARGE.equals(svcInvChrgTpCd)) {
            addlChrgFlg = FLG_ON_Y;
            addlChrgBllgNextBllgDt = pMsg.slsDt.getValue();
        }
        setValue(inTMsg.addlChrgFlg, addlChrgFlg);
        setValue(inTMsg.addlChrgBllgNextBllgDt, addlChrgBllgNextBllgDt);
        inTMsg.addlChrgInvUpToDt.clear();
        setValue(inTMsg.billToCustCd, svcInvTMsg.billToCustCd);
        setValue(inTMsg.origSvcInvNum, pMsg.svcInvNum);
        setValue(inTMsg.prcAllocByMachQtyFlg, FLG_OFF_N);

        // START 2018/10/29 K.Kojima [QC#28999,ADD]
        setValue(inTMsg.dsContrAddlChrgPk, svcInvLineTMsg.dsContrAddlChrgPk);
        // END 2018/10/29 K.Kojima [QC#28999,ADD]

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"SVC_CONTR_BLLG"});
        }
        return inTMsg;
    }

    private SVC_CONTR_BASE_BLLGTMsg createContrBaseBllg(S21ApiMessageMap msgMap, SVC_INVTMsg svcInvTMsg, SVC_INV_LINETMsg svcInvLineTMsg, SVC_CONTR_BLLGTMsg svcContrBllgTMsg) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        BigDecimal svcContrBaseBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BASE_BLLG_SQ);

        SVC_CONTR_BASE_BLLGTMsg inTMsg = new SVC_CONTR_BASE_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcContrBaseBllgPk, svcContrBaseBllgPk);
        setValue(inTMsg.svcContrBllgPk, svcContrBllgTMsg.svcContrBllgPk);
        setValue(inTMsg.dsContrDtlPk, svcInvLineTMsg.dsContrDtlPk);
        setValue(inTMsg.baseDealAmt, svcInvLineTMsg.invLineDealNetAmt);
        setValue(inTMsg.baseFuncAmt, svcInvLineTMsg.invLineFuncNetAmt);
        setValue(inTMsg.baseDiscDealAmt, BigDecimal.ZERO);
        setValue(inTMsg.baseDiscFuncAmt, BigDecimal.ZERO);
        setValue(inTMsg.mdseCd, svcInvLineTMsg.svcPgmMdseCd);
        setValue(inTMsg.ccyCd, svcInvTMsg.dealCcyCd);
        setValue(inTMsg.baseBllgFromDt, svcInvLineTMsg.bllgPerFromDt);
        setValue(inTMsg.baseBllgThruDt, svcInvLineTMsg.bllgPerThruDt);
        setValue(inTMsg.basePerMthAot, svcInvLineTMsg.bllgPerMthAot);
        setValue(inTMsg.dealTaxAmt, svcInvLineTMsg.invLineDealTaxAmt);
        setValue(inTMsg.funcTaxAmt, svcInvLineTMsg.invLineFuncTaxAmt);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"SVC_CONTR_BASE_BLLG"});
        }
        return inTMsg;
    }

    private SVC_CONTR_MTR_BLLGTMsg createSvcContrMtrBllg(S21ApiMessageMap msgMap, SVC_INV_LINETMsg svcInvLineTMsg, SVC_INV_LINE_MTRTMsg svcInvLineMtrTMsg, SVC_CONTR_BLLGTMsg svcContrBllgTMsg) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        BigDecimal svcContrMtrBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_MTR_BLLG_SQ);

        SVC_CONTR_MTR_BLLGTMsg inTMsg = new SVC_CONTR_MTR_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcContrMtrBllgPk, svcContrMtrBllgPk);
        setValue(inTMsg.svcContrBllgPk, svcContrBllgTMsg.svcContrBllgPk);
        setValue(inTMsg.dsContrDtlPk, svcInvLineTMsg.dsContrDtlPk);
        setValue(inTMsg.dsContrBllgMtrPk, svcInvLineMtrTMsg.dsContrBllgMtrPk);
        inTMsg.dsContrBllgMtrId.clear();
        setValue(inTMsg.mtrChrgDealAmt, svcInvLineMtrTMsg.mtrChrgDealAmt);
        setValue(inTMsg.mtrChrgFuncAmt, svcInvLineMtrTMsg.mtrChrgFuncAmt);
        setValue(inTMsg.mtrChrgDiscDealAmt, BigDecimal.ZERO);
        setValue(inTMsg.mtrChrgDiscFuncAmt, BigDecimal.ZERO);
        setValue(inTMsg.mtrEstFlg, svcInvLineMtrTMsg.mtrEstFlg);
        setValue(inTMsg.mtrCopyQty, svcInvLineMtrTMsg.mtrCopyQty);
        setValue(inTMsg.mdseCd, svcInvLineMtrTMsg.mdseCd);
        setValue(inTMsg.ccyCd, svcInvLineMtrTMsg.ccyCd);
        setValue(inTMsg.mtrBllgFromDt, svcInvLineTMsg.bllgPerFromDt);
        setValue(inTMsg.mtrBllgThruDt, svcInvLineTMsg.bllgPerThruDt);
        setValue(inTMsg.bllgCopyQty, svcInvLineMtrTMsg.bllgCopyQty);
        setValue(inTMsg.testCopyQty, svcInvLineMtrTMsg.testCopyQty);
        setValue(inTMsg.totCopyQty, svcInvLineMtrTMsg.totCopyQty);
        setValue(inTMsg.prevTotCopyQty, svcInvLineMtrTMsg.prevTotCopyQty);
        setValue(inTMsg.prevPhysMtrReadGrpSq, svcInvLineMtrTMsg.prevPhysMtrReadGrpSq);
        setValue(inTMsg.svcPhysMtrReadGrpSq, svcInvLineMtrTMsg.svcPhysMtrReadGrpSq);
        setValue(inTMsg.mtrPerMthAot, svcInvLineTMsg.bllgPerMthAot);
        setValue(inTMsg.freeCopyCnt, svcInvLineMtrTMsg.freeCopyCnt);
        setValue(inTMsg.usgFreeCopyCnt, svcInvLineMtrTMsg.usgFreeCopyCnt);
        setValue(inTMsg.xsChrgTpCd, svcInvLineMtrTMsg.xsChrgTpCd);
        setValue(inTMsg.rollOverRatio, svcInvLineMtrTMsg.rollOverRatio);
        setValue(inTMsg.rollOverCnt, svcInvLineMtrTMsg.rollOverCnt);
        setValue(inTMsg.copyInclQty, svcInvLineMtrTMsg.copyInclQty);
        setValue(inTMsg.dealTaxAmt, svcInvLineTMsg.invLineDealTaxAmt);
        setValue(inTMsg.funcTaxAmt, svcInvLineTMsg.invLineFuncTaxAmt);
        setValue(inTMsg.contrMtrMultRate, svcInvLineMtrTMsg.contrMtrMultRate);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"SVC_CONTR_MTR_BLLG"});
        }
        return inTMsg;
    }

    private SVC_CONTR_XS_MTR_BLLGTMsg createContrXsMtrBllg(S21ApiMessageMap msgMap, SVC_INV_LINETMsg svcInvLineTMsg, SVC_INV_LINE_XS_MTRTMsg svcInvLineXsMtrTMsg, SVC_CONTR_BLLGTMsg svcContrBllgTMsg, SVC_CONTR_MTR_BLLGTMsg svcContrMtrBllgTMsg) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        BigDecimal svcContrXsMtrBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_XS_MTR_BLLG_SQ);

        SVC_CONTR_XS_MTR_BLLGTMsg inTMsg = new SVC_CONTR_XS_MTR_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcContrXsMtrBllgPk, svcContrXsMtrBllgPk);
        setValue(inTMsg.svcContrMtrBllgPk, svcContrMtrBllgTMsg.svcContrMtrBllgPk);
        setValue(inTMsg.svcContrBllgPk, svcContrBllgTMsg.svcContrBllgPk);
        setValue(inTMsg.dsContrDtlPk, svcInvLineTMsg.dsContrDtlPk);
        setValue(inTMsg.dsContrBllgMtrPk, svcInvLineXsMtrTMsg.dsContrBllgMtrPk);
        inTMsg.dsContrBllgMtrId.clear();
        setValue(inTMsg.contrXsCopyPk, svcInvLineXsMtrTMsg.contrXsCopyPk);
        setValue(inTMsg.xsMtrCopyQty, svcInvLineXsMtrTMsg.xsMtrCopyQty);
        setValue(inTMsg.xsMtrChrgDealAmt, svcInvLineXsMtrTMsg.xsMtrChrgDealAmt);
        setValue(inTMsg.xsMtrChrgFuncAmt, svcInvLineXsMtrTMsg.xsMtrChrgFuncAmt);
        setValue(inTMsg.xsMtrChrgDiscDealAmt, BigDecimal.ZERO);
        setValue(inTMsg.xsMtrChrgDiscFuncAmt, BigDecimal.ZERO);
        setValue(inTMsg.xsMtrAmtRate, svcInvLineXsMtrTMsg.xsMtrAmtRate);
        setValue(inTMsg.xsMtrFromCopyQty, svcInvLineXsMtrTMsg.xsMtrFromCopyQty);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"SVC_CONTR_XS_MTR_BLLG"});
        }
        return inTMsg;
    }

    private SVC_CONTR_ADDL_CHRG_BLLGTMsg createContrAddlChrgBllg(S21ApiMessageMap msgMap, SVC_INV_LINETMsg svcInvLineTMsg, SVC_INV_LINE_ADDL_CHRGTMsg svcInvLineAddlChrgTMsg, BigDecimal svcContrBllgPk, BigDecimal svcContrBaseBllgPk, BigDecimal svcContrMtrBllgPk) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        BigDecimal svcContrAddlChrgBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_ADDL_CHRG_BLLG_SQ);

        SVC_CONTR_ADDL_CHRG_BLLGTMsg inTMsg = new SVC_CONTR_ADDL_CHRG_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcContrAddlChrgBllgPk, svcContrAddlChrgBllgPk);
        setValue(inTMsg.svcContrBllgPk, svcContrBllgPk);
        setValue(inTMsg.addlChrgTpCd, svcInvLineAddlChrgTMsg.addlChrgTpCd);
        setValue(inTMsg.svcBillByTpCd, svcInvLineAddlChrgTMsg.svcBillByTpCd);
        setValue(inTMsg.addlDealPrcAmt, svcInvLineTMsg.invLineDealNetAmt);
        setValue(inTMsg.addlFuncPrcAmt, svcInvLineTMsg.invLineFuncNetAmt);
        setValue(inTMsg.addlChrgFlatDealPrcAmt, svcInvLineAddlChrgTMsg.addlChrgFlatDealPrcAmt);
        setValue(inTMsg.addlChrgFlatFuncPrcAmt, svcInvLineAddlChrgTMsg.addlChrgFlatFuncPrcAmt);
        setValue(inTMsg.addlChrgAplcPct, svcInvLineAddlChrgTMsg.addlChrgAplcPct);
        setValue(inTMsg.addlChrgInvTpCd, svcInvLineAddlChrgTMsg.addlChrgInvTpCd);
        setValue(inTMsg.printDtlFlg, svcInvLineAddlChrgTMsg.printDtlFlg);
        setValue(inTMsg.chrgMachCnt, svcInvLineAddlChrgTMsg.chrgMachCnt);
        setValue(inTMsg.addlChrgPerMthAot, svcInvLineTMsg.bllgPerMthAot);
        setValue(inTMsg.addlChrgFromDt, svcInvLineTMsg.bllgPerFromDt);
        setValue(inTMsg.addlChrgThruDt, svcInvLineTMsg.bllgPerThruDt);
        setValue(inTMsg.svcContrBaseBllgPk, svcContrBaseBllgPk);
        setValue(inTMsg.svcContrMtrBllgPk, svcContrMtrBllgPk);
        setValue(inTMsg.dealTaxAmt, svcInvLineTMsg.invLineDealTaxAmt);
        setValue(inTMsg.funcTaxAmt, svcInvLineTMsg.invLineFuncTaxAmt);
        // START 2018/10/29 K.Kojima [QC#28999,ADD]
        setValue(inTMsg.intgMdseCd, svcInvLineTMsg.intgMdseCd);
        setValue(inTMsg.chrgMachCnt, BigDecimal.ONE);
        // END 2018/10/29 K.Kojima [QC#28999,ADD]

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"SVC_CONTR_ADDL_CHRG_BLLG"});
        }
        return inTMsg;
    }

    private void createContrBllgAlloc(S21ApiMessageMap msgMap, SVC_CONTR_BLLGTMsg svcContrBllgTMsg, SVC_CONTR_BASE_BLLGTMsg svcContrBaseBllgTMsg, SVC_CONTR_MTR_BLLGTMsg svcContrMtrBllgTMsg, SVC_CONTR_ADDL_CHRG_BLLGTMsg svcContrAddlChrgBllgTMsg, SVC_INV_LINE_ALLOCTMsg svcInvLineAllocTMsg) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        BigDecimal svcContrBllgAllocPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_ALLOC_SQ);

        SVC_CONTR_BLLG_ALLOCTMsg inTMsg = new SVC_CONTR_BLLG_ALLOCTMsg();

        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcContrBllgAllocPk, svcContrBllgAllocPk);
        if (svcContrBaseBllgTMsg != null) {
            setValue(inTMsg.svcContrBaseBllgPk, svcContrBaseBllgTMsg.svcContrBaseBllgPk);
            setValue(inTMsg.contrPrcAllocTpCd, CONTR_PRC_ALLOC_TP.BASE);
        }
        if (svcContrMtrBllgTMsg != null) {
            setValue(inTMsg.svcContrMtrBllgPk, svcContrMtrBllgTMsg.svcContrMtrBllgPk);
            setValue(inTMsg.contrPrcAllocTpCd, CONTR_PRC_ALLOC_TP.USAGE);
        }
        if (svcContrAddlChrgBllgTMsg != null) {
            setValue(inTMsg.contrPrcAllocTpCd, CONTR_PRC_ALLOC_TP.ADDITIONAL_CHARGE);
            setValue(inTMsg.svcContrAddlChrgBllgPk, svcContrAddlChrgBllgTMsg.svcContrAddlChrgBllgPk);
        }
        setValue(inTMsg.intgMdseCd, svcInvLineAllocTMsg.intgMdseCd);
        setValue(inTMsg.tocCd, svcInvLineAllocTMsg.tocCd);
        setValue(inTMsg.slsAllocRate, svcInvLineAllocTMsg.slsAllocRate);
        setValue(inTMsg.dealGrsUnitPrcAmt, svcInvLineAllocTMsg.dealGrsUnitPrcAmt);
        setValue(inTMsg.dealDiscUnitPrcAmt, svcInvLineAllocTMsg.dealDiscUnitPrcAmt);
        setValue(inTMsg.dealNetUnitPrcAmt, svcInvLineAllocTMsg.dealNetUnitPrcAmt);
        setValue(inTMsg.dealGrsTotPrcAmt, svcInvLineAllocTMsg.dealGrsTotPrcAmt);
        setValue(inTMsg.invLineDealNetAmt, svcInvLineAllocTMsg.invLineDealNetAmt);
        setValue(inTMsg.invLineDealTaxAmt, svcInvLineAllocTMsg.invLineDealTaxAmt);
        setValue(inTMsg.funcGrsUnitPrcAmt, svcInvLineAllocTMsg.funcGrsUnitPrcAmt);
        setValue(inTMsg.funcDiscUnitPrcAmt, svcInvLineAllocTMsg.funcDiscUnitPrcAmt);
        setValue(inTMsg.funcNetUnitPrcAmt, svcInvLineAllocTMsg.funcNetUnitPrcAmt);
        setValue(inTMsg.funcGrsTotPrcAmt, svcInvLineAllocTMsg.funcGrsTotPrcAmt);
        setValue(inTMsg.invLineFuncNetAmt, svcInvLineAllocTMsg.invLineFuncNetAmt);
        setValue(inTMsg.invLineFuncTaxAmt, svcInvLineAllocTMsg.invLineFuncTaxAmt);
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
        setValue(inTMsg.ccyCd, svcInvLineAllocTMsg.ccyCd);
        setValue(inTMsg.trxCd, svcInvLineAllocTMsg.trxCd);
        setValue(inTMsg.trxRsnCd, svcInvLineAllocTMsg.trxRsnCd);
        setValue(inTMsg.dfrdAcctgRuleCd, svcInvLineAllocTMsg.dfrdAcctgRuleCd);
        setValue(inTMsg.dfrdAcctgRuleDurnAot, svcInvLineAllocTMsg.dfrdAcctgRuleDurnAot);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"SVC_CONTR_BLLG_ALLOC"});
        }
    }

    // START 04/18/2016 T.Aoyagi [QC#7056, ADD]
    // START 2017/05/18 K.Kitachi [QC#18541, MOD]
    private DS_CONTR_BLLG_SCHDTMsg createBllgSchdForAggLine(S21ApiMessageMap msgMap, BigDecimal dsContrDtlPk, SVC_INVTMsg svcInvTMsg, Map<String, Object> origSchdInfo, BigDecimal bllgMtrPk, String baseChrgFlg, String mtrEntryCpltFlg) {
    // END 2017/05/18 K.Kitachi [QC#18541, MOD]

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();

        BigDecimal dsContrBllgSchdPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_SCHD_SQ);

        DS_CONTR_BLLG_SCHDTMsg inTMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        setValue(inTMsg.dsContrDtlPk, dsContrDtlPk);
        inTMsg.dsContrBllgSchdSmryPk.clear();
        inTMsg.dsContrPrcEffPk.clear();
        // START 2017/09/12 K.Kojima [QC#20835,MOD]
        // inTMsg.dsContrPrcEffSqNum.clear();
        // inTMsg.dsContrBllgSchdSqNum.clear();
        // inTMsg.dsContrBllgSchdLvlNum.clear();
        setValue(inTMsg.dsContrPrcEffSqNum, (BigDecimal) origSchdInfo.get("DS_CONTR_PRC_EFF_SQ_NUM"));
        setValue(inTMsg.dsContrBllgSchdSqNum, (String) origSchdInfo.get("DS_CONTR_BLLG_SCHD_SQ_NUM"));
        setValue(inTMsg.dsContrBllgSchdLvlNum, (String) origSchdInfo.get("DS_CONTR_BLLG_SCHD_LVL_NUM"));
        // END 2017/09/12 K.Kojima [QC#20835,MOD]
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            setValue(inTMsg.baseChrgFlg, FLG_ON_Y);
            setValue(inTMsg.usgChrgFlg, FLG_OFF_N);
        } else {
            setValue(inTMsg.baseChrgFlg, FLG_OFF_N);
            setValue(inTMsg.usgChrgFlg, FLG_ON_Y);
            setValue(inTMsg.dsContrBllgMtrPk, (BigDecimal) origSchdInfo.get("DS_CONTR_BLLG_MTR_PK"));
            setValue(inTMsg.svcPhysMtrReadGrpSq, (BigDecimal) origSchdInfo.get("SVC_PHYS_MTR_READ_GRP_SQ"));
        }
        inTMsg.basePrcDealAmt.clear();
        inTMsg.basePrcFuncAmt.clear();
        inTMsg.baseActlPrcDealAmt.clear();
        inTMsg.baseActlPrcFuncAmt.clear();
        inTMsg.readMtrCnt.clear();
        inTMsg.bllgMtrCnt.clear();
        inTMsg.mtrChrgDealAmt.clear();
        inTMsg.mtrChrgFuncAmt.clear();

        inTMsg.skipRecovTpCd.clear();
        inTMsg.svcInvNum.clear();
        inTMsg.invDt.clear();
        setValue(inTMsg.nextBllgDt, pMsg.slsDt);
        setValue(inTMsg.bllgSchdFromDt, (String) origSchdInfo.get("BLLG_SCHD_FROM_DT"));
        setValue(inTMsg.bllgSchdThruDt, (String) origSchdInfo.get("BLLG_SCHD_THRU_DT"));
        inTMsg.bllgCycleCd.clear();
        setValue(inTMsg.bllgSchdPrrtFlg, FLG_OFF_N);
        setValue(inTMsg.dsContrTrmnFlg, FLG_OFF_N);
        setValue(inTMsg.ccyCd, svcInvTMsg.dealCcyCd);
        inTMsg.bllgSchdTrxSrcTpCd.clear();
        setValue(inTMsg.bllgStageFlg, FLG_ON_Y);
        setValue(inTMsg.invFlg, FLG_OFF_N);
        setValue(inTMsg.invTpCd, INV_TP.CREDIT_MEMO);
        setValue(inTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.BILLED);
        setValue(inTMsg.bllblFlg, FLG_ON_Y);
        inTMsg.rvsSchdDt.clear();
        // START 2017/05/18 K.Kitachi [QC#18541, MOD]
//        setValue(inTMsg.mtrEntryCpltFlg, FLG_OFF_N);
        setValue(inTMsg.mtrEntryCpltFlg, mtrEntryCpltFlg);
        // END 2017/05/18 K.Kitachi [QC#18541, MOD]
        setValue(inTMsg.dsContrDtlTpCd, DS_CONTR_DTL_TP.AGGREGATE);
        inTMsg.prntDsContrBllgSchdPk.clear();
        inTMsg.bllgPerMthAot.clear();
        inTMsg.prntDsContrDtlPk.clear();
        setValue(inTMsg.svcCrRebilPk, pMsg.svcCrRebilPk);
        setValue(inTMsg.svcCrRebilDtlPk, pMsg.svcCrRebilDtlPk);
        setValue(inTMsg.origDsContrBllgSchdPk, (BigDecimal) origSchdInfo.get("DS_CONTR_BLLG_SCHD_PK"));
        setValue(inTMsg.dsBllgSchdTpCd, DS_BLLG_SCHD_TP.CREDIT_CREDIT_AND_REBILL);
        inTMsg.basePrcAdjDealAmt.clear();
        inTMsg.basePrcAdjFuncAmt.clear();
        inTMsg.dealTaxAmt.clear();
        inTMsg.funcTaxAmt.clear();
        setValue(inTMsg.bllgCalcFlg, FLG_ON_Y);
        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_BLLG_SCHD"});
        }
        return inTMsg;
    }

    private SVC_CONTR_BLLGTMsg createContrBllgForAggLine(S21ApiMessageMap msgMap, BigDecimal dsContrDtlPk, SVC_INVTMsg svcInvTMsg, DS_CONTR_BLLG_SCHDTMsg schdTMsg, Map<String, Object> origSchdInfo, BigDecimal bllgMtrPk, String baseChrgFlg) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        BigDecimal svcContrBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_SQ);

        SVC_CONTR_BLLGTMsg inTMsg = new SVC_CONTR_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcContrBllgPk, svcContrBllgPk);
        setValue(inTMsg.dsContrPk, svcInvTMsg.dsContrPk);
        setValue(inTMsg.dsContrDtlPk, dsContrDtlPk);
        setValue(inTMsg.bllgCpltStsCd, BLLG_CPLT_STS.SCHEDULED);
        setValue(inTMsg.bllgApvlReqFlg, FLG_ON_Y);
        setValue(inTMsg.bllgApvlCpltFlg, FLG_ON_Y);
        inTMsg.bllgApvlPsnCd.clear();
        setValue(inTMsg.mthEndCloFlg, FLG_OFF_N);
        inTMsg.contrCloDay.clear();
        setValue(inTMsg.svcContrBllgFromDt, (String) origSchdInfo.get("BLLG_SCHD_FROM_DT"));
        setValue(inTMsg.svcContrBllgThruDt, (String) origSchdInfo.get("BLLG_SCHD_THRU_DT"));
        inTMsg.baseBllgTmgCd.clear();
        inTMsg.baseBllgCycleMthAot.clear();
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            setValue(inTMsg.baseBllgNextBllgDt, pMsg.slsDt);
            setValue(inTMsg.baseChrgFlg, FLG_ON_Y);
            setValue(inTMsg.usgChrgFlg, FLG_OFF_N);
            setValue(inTMsg.svcContrBllgGrpSq, this.baseSvcContrBllgGrpSq);
        } else {
            setValue(inTMsg.mtrBllgNextBllgDt, pMsg.slsDt);
            setValue(inTMsg.baseChrgFlg, FLG_OFF_N);
            setValue(inTMsg.usgChrgFlg, FLG_ON_Y);
            setValue(inTMsg.svcContrBllgGrpSq, this.usgSvcContrBllgGrpSq);
        }
        inTMsg.baseBllgLastBllgDt.clear();
        inTMsg.baseBllgInvUpToDt.clear();
        inTMsg.mtrBllgTmgCd.clear();
        inTMsg.mtrBllgCycleMthAot.clear();
        inTMsg.mtrBllgLastBllgDt.clear();
        inTMsg.mtrBllgInvUpToDt.clear();
        setValue(inTMsg.ccyCd, svcInvTMsg.dealCcyCd);
        inTMsg.svcMachMstrPk.clear();
        inTMsg.contrCloDt.clear();
        inTMsg.sellToCustCd.clear();
        setValue(inTMsg.fleetCalcFlg, FLG_OFF_N);
        if (schdTMsg != null) {
            setValue(inTMsg.dsContrBllgSchdPk, schdTMsg.dsContrBllgSchdPk);
            setValue(inTMsg.dsContrBllgMtrPk, schdTMsg.dsContrBllgMtrPk);
        }
        inTMsg.prntSvcContrBllgPk.clear();
        setValue(inTMsg.dsContrCatgCd, svcInvTMsg.dsContrCatgCd);
        inTMsg.ovrdNextBllgDt.clear();
        setValue(inTMsg.bllgReProcFlg, FLG_OFF_N);
        inTMsg.bllgReProcPsnCd.clear();
        inTMsg.prevSvcContrBllgPk.clear();
        inTMsg.lastMtrReadTs.clear();
        setValue(inTMsg.dsAcctNum, svcInvTMsg.dsAcctNum);
        setValue(inTMsg.aggrCalcFlg, FLG_ON_Y);
        setValue(inTMsg.svcCrRebilPk, pMsg.svcCrRebilPk);
        setValue(inTMsg.svcCrRebilDtlPk, pMsg.svcCrRebilDtlPk);
        setValue(inTMsg.invTpCd, INV_TP.CREDIT_MEMO);
        setValue(inTMsg.bllgCalcEstFlg, pMsg.estFlg);
        setValue(inTMsg.addlChrgFlg, FLG_OFF_N);
        inTMsg.addlChrgBllgNextBllgDt.clear();
        inTMsg.addlChrgInvUpToDt.clear();
        setValue(inTMsg.billToCustCd, svcInvTMsg.billToCustCd);
        setValue(inTMsg.origSvcInvNum, pMsg.svcInvNum);
        setValue(inTMsg.prcAllocByMachQtyFlg, FLG_OFF_N);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"SVC_CONTR_BLLG"});
        }
        return inTMsg;
    }

    private Map<String, Object> getOrigSchdInfo(S21ApiMessageMap msgMap, BigDecimal compareFromBllgMtrPk, String baseChrgFlg) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal svcInvLinePk = null;

        if (FLG_ON_Y.equals(baseChrgFlg)) {
            for (int i = 0; i < pMsg.xxBaseLineList.getValidCount(); i++) {
                if (hasValue(pMsg.xxBaseLineList.no(i).svcInvLinePk_BL)) {
                    svcInvLinePk = pMsg.xxBaseLineList.no(i).svcInvLinePk_BL.getValue();
                    break;
                }
            }
        } else {
            for (int i = 0; i < pMsg.xxMtrLineList.getValidCount(); i++) {
                BigDecimal compareToBllgMtrPk = pMsg.xxMtrLineList.no(i).dsContrBllgMtrPk_ML.getValue();
                if (hasValue(pMsg.xxMtrLineList.no(i).svcInvLinePk_ML)
                        && hasValue(compareToBllgMtrPk)) {

                    String compareFrom = NSZC0470Query.getInstance().getMtrLbCd(glblCmpyCd, compareFromBllgMtrPk);
                    String compareTo = NSZC0470Query.getInstance().getMtrLbCd(glblCmpyCd, compareToBllgMtrPk);

                    if (compareFrom.equals(compareTo)) {
                        svcInvLinePk = pMsg.xxMtrLineList.no(i).svcInvLinePk_ML.getValue();
                        break;
                    }
                }
            }
        }
        if (svcInvLinePk == null) {
            return null;
        }
        return NSZC0470Query.getInstance().getSchdPkForAggLine(glblCmpyCd, svcInvLinePk);
    }
    // END 04/18/2016 T.Aoyagi [QC#7056, ADD]

    private void createDebitInfoForBase(S21ApiMessageMap msgMap, NSZC047007_xxBaseLineListPMsg basePMsg) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String svcInvNum = pMsg.svcInvNum.getValue();
        String baseChrgFlg = FLG_ON_Y;

        // START 2016/03/24 T.Aoyagi [QC#5901, MOD]
        BigDecimal dsContrDtlPk = basePMsg.dsContrDtlPk_BL.getValue();
        BigDecimal schdSmryPk = null;
        BigDecimal schdPk = null;

        if (DS_CONTR_DTL_TP.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
            Map<String, Object> origSchdInfo = getOrigSchdInfo(msgMap, null, baseChrgFlg);
            if (origSchdInfo == null) {
                return;
            }
            schdSmryPk = (BigDecimal) origSchdInfo.get("DS_CONTR_BLLG_SCHD_SMRY_PK");
            schdPk = (BigDecimal) origSchdInfo.get("DS_CONTR_BLLG_SCHD_PK");
        } else {
            List<Map<String, BigDecimal>> baseBllgSchdList = NSZC0470Query.getInstance().getBllgSchdBySvcInvNum(glblCmpyCd, dsContrDtlPk, svcInvNum, null, baseChrgFlg);
            // END 2016/03/24 T.Aoyagi [QC#5901, MOD]
            if (baseBllgSchdList.isEmpty()) {
                return;
            }
            schdSmryPk = baseBllgSchdList.get(0).get("DS_CONTR_BLLG_SCHD_SMRY_PK");
            schdPk = baseBllgSchdList.get(0).get("DS_CONTR_BLLG_SCHD_PK");
        }
        // DS_CONTR_BLLG_SCHD_SMRY
        DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, schdSmryPk);
        DS_CONTR_BLLG_SCHD_SMRYTMsg drSchdSmryTMsg = createSchdSmry(msgMap, basePMsg, null, schdSmryTMsg, baseChrgFlg);

        // DS_CONTR_BLLG_SCHD
        DS_CONTR_BLLG_SCHDTMsg schdTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, schdPk);
        // START 2017/05/18 K.Kitachi [QC#18541, MOD]
//        DS_CONTR_BLLG_SCHDTMsg debitSchdTMsg = createSchd(msgMap, schdTMsg, drSchdSmryTMsg, baseChrgFlg);
        DS_CONTR_BLLG_SCHDTMsg debitSchdTMsg = createSchd(msgMap, schdTMsg, drSchdSmryTMsg, baseChrgFlg, FLG_OFF_N);
        // END 2017/05/18 K.Kitachi [QC#18541, MOD]

        // START 2016/03/24 T.Aoyagi [QC#5901, ADD]
        // mod start 2018/08/15 QC#27329-3
        //SVC_INVTMsg svcInvTMsg = NSZC0470Query.getInstance().getSvcInvTMsg(glblCmpyCd, svcInvNum);
        //createContrBllgForRebil(msgMap, svcInvTMsg, debitSchdTMsg, null, null,FLG_ON_Y);
        createContrBllgForRebil(msgMap, debitSchdTMsg, null, null,FLG_ON_Y, null);
        // mod end 2018/08/15 QC#27329-3
        // END 2016/03/24 T.Aoyagi [QC#5901, ADD]
    }

    private void createDebitInfoForUsg(S21ApiMessageMap msgMap, NSZC047007_xxMtrLineListPMsg mtrPMsg) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String svcInvNum = pMsg.svcInvNum.getValue();
        BigDecimal dsContrBllgMtrPk = mtrPMsg.dsContrBllgMtrPk_ML.getValue();
        String baseChrgFlg = FLG_OFF_N;

        // START 2016/03/24 T.Aoyagi [QC#5901, MOD]
        BigDecimal dsContrDtlPk = mtrPMsg.dsContrDtlPk_ML.getValue();
        BigDecimal schdSmryPk = null;
        BigDecimal schdPk = null;

        // add start 2018/08/15 QC#27329-3
        DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = NSZC0470Query.getInstance().getBllgMtrTMsg(pMsg.glblCmpyCd.getValue(), dsContrBllgMtrPk);
        if (dsContrBllgMtrTMsg == null) {
            return;
        }
        // add end 2018/08/15 QC#27329-3

        if (DS_CONTR_DTL_TP.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
            Map<String, Object> origSchdInfo = getOrigSchdInfo(msgMap, dsContrBllgMtrPk, baseChrgFlg);
            if (origSchdInfo == null) {
                return;
            }
            schdSmryPk = (BigDecimal) origSchdInfo.get("DS_CONTR_BLLG_SCHD_SMRY_PK");
            schdPk = (BigDecimal) origSchdInfo.get("DS_CONTR_BLLG_SCHD_PK");
        } else {
            List<Map<String, BigDecimal>> usgBllgSchdList = NSZC0470Query.getInstance().getBllgSchdBySvcInvNum(glblCmpyCd, dsContrDtlPk, svcInvNum, dsContrBllgMtrPk, baseChrgFlg);
            // END 2016/03/24 T.Aoyagi [QC#5901, MOD]
            if (usgBllgSchdList.isEmpty()) {
                return;
            }
            schdSmryPk = usgBllgSchdList.get(0).get("DS_CONTR_BLLG_SCHD_SMRY_PK");
            schdPk = usgBllgSchdList.get(0).get("DS_CONTR_BLLG_SCHD_PK");
        }

        // DS_CONTR_BLLG_SCHD_SMRY
        DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, schdSmryPk);
        DS_CONTR_BLLG_SCHD_SMRYTMsg drSchdSmryTMsg = createSchdSmry(msgMap, null, mtrPMsg, schdSmryTMsg, baseChrgFlg);

        Boolean isFirstLine = true;
        List<BigDecimal> schdMtrPkList = NSZC0470Query.getInstance().getSchdMtrInfo(glblCmpyCd, schdSmryPk, dsContrBllgMtrPk);
        // Mod Start 2017/10/26 QC#21815
        int tierCount = 1;
        for (BigDecimal schdMtrPk : schdMtrPkList) {
            DS_CONTR_BLLG_SCHD_MTRTMsg schdMtrTMsg = NSZC0470Query.getInstance().getSchdMtrTMsg(glblCmpyCd, schdMtrPk);
            createSchdMtr(msgMap, drSchdSmryTMsg, schdMtrTMsg, isFirstLine, tierCount);
            isFirstLine = false;
            tierCount++;
        }
        // Mod End 2017/10/26 QC#21815
        // DS_CONTR_BLLG_SCHD
        DS_CONTR_BLLG_SCHDTMsg schdTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, schdPk);
        // START 2017/05/18 K.Kitachi [QC#18541, MOD]
//        DS_CONTR_BLLG_SCHDTMsg prntSchdTmsg = createSchd(msgMap, schdTMsg, drSchdSmryTMsg, baseChrgFlg);
        DS_CONTR_BLLG_SCHDTMsg prntSchdTmsg = createSchd(msgMap, schdTMsg, drSchdSmryTMsg, baseChrgFlg, FLG_ON_Y);
        // END 2017/05/18 K.Kitachi [QC#18541, MOD]
        // START 2016/03/24 T.Aoyagi [QC#5901, ADD]
        // mod start 2018/08/15 QC#27329-3
        //SVC_INVTMsg svcInvTMsg = NSZC0470Query.getInstance().getSvcInvTMsg(glblCmpyCd, svcInvNum);
        //SVC_CONTR_BLLGTMsg svcContrBllgTMsg = createContrBllgForRebil(msgMap, svcInvTMsg, prntSchdTmsg, null, null, FLG_OFF_N);
        SVC_CONTR_BLLGTMsg svcContrBllgTMsg = createContrBllgForRebil(msgMap, prntSchdTmsg, null, null, FLG_OFF_N, dsContrBllgMtrTMsg);
        // mod end 2018/08/15 QC#27329-3
        // END 2016/03/24 T.Aoyagi [QC#5901, ADD]
        if (DS_CONTR_DTL_TP.FLEET.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
            List<Map<String, Object>> fleetMachList = NSZC0470Query.getInstance().getFleetMachContrInfoRebil(pMsg.glblCmpyCd.getValue(), schdTMsg.dsContrBllgSchdPk.getValue());
            for (Map<String, Object> fleetMachInfo : fleetMachList) {
                // START 2016/03/24 T.Aoyagi [QC#5901, MOD]
                // START 2017/05/18 K.Kitachi [QC#18541, MOD]
//                DS_CONTR_BLLG_SCHDTMsg childSchdTmsg = createSchdForFleet(msgMap, prntSchdTmsg, fleetMachInfo);
                DS_CONTR_BLLG_SCHDTMsg childSchdTmsg = createSchdForFleet(msgMap, prntSchdTmsg, fleetMachInfo, FLG_ON_Y);
                // END 2017/05/18 K.Kitachi [QC#18541, MOD]
                // mod start 2018/08/15 QC#27329-3
                //createContrBllgForRebil(msgMap, svcInvTMsg, childSchdTmsg, svcContrBllgTMsg, fleetMachInfo, FLG_OFF_N);
                createContrBllgForRebil(msgMap, childSchdTmsg, svcContrBllgTMsg, fleetMachInfo, FLG_OFF_N, dsContrBllgMtrTMsg);
                // mod end 2018/08/15 QC#27329-3
                // END 2016/03/24 T.Aoyagi [QC#5901, MOD]
            }
        }
    }

    private DS_CONTR_BLLG_SCHD_SMRYTMsg createSchdSmry(S21ApiMessageMap msgMap, NSZC047007_xxBaseLineListPMsg basePMsg, NSZC047007_xxMtrLineListPMsg mtrPMsg, DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg, String baseChrgFlg) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        BigDecimal dsContrBllgSchdSmryPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_SCHD_SMRY_SQ);
        DS_CONTR_BLLG_SCHD_SMRYTMsg inTMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();

        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdSmryPk, dsContrBllgSchdSmryPk);
        setValue(inTMsg.dsContrDtlPk, schdSmryTMsg.dsContrDtlPk);
        inTMsg.dsContrPrcEffPk.clear();
        inTMsg.dsContrPrcEffSqNum.clear();
        setValue(inTMsg.dsContrBllgMtrPk, schdSmryTMsg.dsContrBllgMtrPk);
        setValue(inTMsg.dsContrBllgSchdSqNum, schdSmryTMsg.dsContrBllgSchdSqNum);
        setValue(inTMsg.perSchdNum, schdSmryTMsg.perSchdNum);
        setValue(inTMsg.perBllgCycleCd, schdSmryTMsg.perBllgCycleCd);
        setValue(inTMsg.bllgSchdFromDt, schdSmryTMsg.bllgSchdFromDt);
        setValue(inTMsg.bllgSchdThruDt, schdSmryTMsg.bllgSchdThruDt);
        setValue(inTMsg.bllgCycleCd, schdSmryTMsg.bllgCycleCd);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            BigDecimal basePrcDealAmt = basePMsg.basePrcDealAmt_BL.getValue();
            if (!hasValue(basePrcDealAmt)) {
                // START 2018/10/30 K.Kojima [QC#29000,MOD]
                // basePrcDealAmt = schdSmryTMsg.basePrcDealAmt.getValue();
                if (schdSmryTMsg.perBllgCycleCd.getValue().equals(BLLG_CYCLE.DAILY)) {
                    basePrcDealAmt = schdSmryTMsg.baseSubTotPrcDealAmt.getValue();
                } else {
                    basePrcDealAmt = schdSmryTMsg.basePrcDealAmt.getValue();
                }
                // END 2018/10/30 K.Kojima [QC#29000,MOD]
            }
            setValue(inTMsg.basePrcDealAmt, basePrcDealAmt);
            setValue(inTMsg.basePrcFuncAmt, schdSmryTMsg.basePrcFuncAmt);
            setValue(inTMsg.basePrcDealAdjAmt, BigDecimal.ZERO);
            inTMsg.basePrcFuncAdjAmt.clear();
            setValue(inTMsg.baseSubTotPrcDealAmt, BigDecimal.ZERO);
            inTMsg.baseSubTotPrcFuncAmt.clear();
            setValue(inTMsg.baseChrgFlg, FLG_ON_Y);
            setValue(inTMsg.usgChrgFlg, FLG_OFF_N);
        } else {
            setValue(inTMsg.mlyCopyInclPrcQty, mtrPMsg.xsMtrCopyQty_ML);
            setValue(inTMsg.dsContrBllgSchdTrmnDt, schdSmryTMsg.dsContrBllgSchdTrmnDt);
            setValue(inTMsg.baseChrgFlg, FLG_OFF_N);
            setValue(inTMsg.usgChrgFlg, FLG_ON_Y);
        }
        setValue(inTMsg.ccyCd, schdSmryTMsg.ccyCd);
        setValue(inTMsg.origBllgSchdSmryNum, schdSmryTMsg.dsContrBllgSchdSmryPk);
        setValue(inTMsg.prntDsContrDtlPk, schdSmryTMsg.prntDsContrDtlPk);
        setValue(inTMsg.dsBllgSchdTpCd, DS_BLLG_SCHD_TP.REBILL_CREDIT_AND_REBILL);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_BLLG_SCHD_SMRY"});
        }
        return inTMsg;
    }

    // Mod Start 2017/10/26 QC#21815
    private void createSchdMtr(S21ApiMessageMap msgMap, DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg, DS_CONTR_BLLG_SCHD_MTRTMsg schdMtrTMsg, Boolean isFirstLine, int tierCnt) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();

        BigDecimal dsContrBllgSchdMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_SCHD_MTR_SQ);
        NSZC047007_xxMtrLineListPMsg mtrPMsg = getPrmAllowance(msgMap, schdMtrTMsg.contrXsCopyPk.getValue());

        DS_CONTR_BLLG_SCHD_MTRTMsg inTMsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();

        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdMtrPk, dsContrBllgSchdMtrPk);
        setValue(inTMsg.dsContrBllgSchdSmryPk, schdSmryTMsg.dsContrBllgSchdSmryPk);

        if (mtrPMsg != null) {
            setValue(inTMsg.contrXsCopyPk, mtrPMsg.contrXsCopyPk_ML);
            setValue(inTMsg.dsContrBllgMtrPk, mtrPMsg.dsContrBllgMtrPk_ML);
            // START 2016/03/24 T.Aoyagi [QC#5901, MOD]
            BigDecimal xsMtrCopyQty = mtrPMsg.xsMtrCopyQty_ML.getValue();
            if (!hasValue(xsMtrCopyQty)) {
                xsMtrCopyQty = schdMtrTMsg.xsMtrCopyQty.getValue();
            }
            // Add Start 2017/10/26 QC#21815
            if (isAggLine(pMsg.glblCmpyCd.getValue(), mtrPMsg.dsContrDtlPk_ML.getValue())) {
                xsMtrCopyQty = NSZC0470Query.getInstance().getNewXsCopyQtyForAggLine(pMsg.glblCmpyCd.getValue(), pMsg.svcCrRebilDtlPk.getValue(), mtrPMsg.dsContrDtlPk_ML.getValue(), mtrPMsg.dsContrBllgMtrPk_ML.getValue(), tierCnt);
            }
            // Add End 2017/10/26 QC#21815
            setValue(inTMsg.xsMtrCopyQty, xsMtrCopyQty);
            BigDecimal xsMtrAmtRate = mtrPMsg.xsMtrAmtRate_ML.getValue();
            if (!hasValue(xsMtrAmtRate)) {
                xsMtrAmtRate = schdMtrTMsg.xsMtrAmtRate.getValue();
            }
            setValue(inTMsg.xsMtrAmtRate, xsMtrAmtRate);
            // END 2016/03/24 T.Aoyagi [QC#5901, MOD]
            if (isFirstLine) {
                setValue(inTMsg.xsMtrFirstFlg, FLG_ON_Y);
            } else {
                setValue(inTMsg.xsMtrFirstFlg, FLG_OFF_N);
            }
        } else {
            setValue(inTMsg.contrXsCopyPk, schdMtrTMsg.contrXsCopyPk);
            setValue(inTMsg.dsContrBllgMtrPk, schdMtrTMsg.dsContrBllgMtrPk);
            setValue(inTMsg.xsMtrCopyQty, schdMtrTMsg.xsMtrCopyQty);
            setValue(inTMsg.xsMtrAmtRate, schdMtrTMsg.xsMtrAmtRate);
            setValue(inTMsg.xsMtrFirstFlg, schdMtrTMsg.xsMtrFirstFlg);
        }

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_BLLG_SCHD_MTR"});
        }
    }
    // Mod End 2017/10/26 QC#21815

    // START 2017/05/18 K.Kitachi [QC#18541, MOD]
    private DS_CONTR_BLLG_SCHDTMsg createSchd(S21ApiMessageMap msgMap, DS_CONTR_BLLG_SCHDTMsg schdTMsg, DS_CONTR_BLLG_SCHD_SMRYTMsg schdSmryTMsg, String baseChrgFlg, String mtrEntryCpltFlg) {
    // END 2017/05/18 K.Kitachi [QC#18541, MOD]

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        BigDecimal dsContrBllgSchdPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_SCHD_SQ);
        DS_CONTR_BLLG_SCHDTMsg inTMsg = new DS_CONTR_BLLG_SCHDTMsg();

        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        setValue(inTMsg.dsContrDtlPk, schdTMsg.dsContrDtlPk);
        setValue(inTMsg.dsContrBllgSchdSmryPk, schdSmryTMsg.dsContrBllgSchdSmryPk);
        inTMsg.dsContrPrcEffPk.clear();
        // START 2017/09/12 K.Kojima [QC#20835,MOD]
        // inTMsg.dsContrPrcEffSqNum.clear();
        // setValue(inTMsg.dsContrBllgSchdSqNum, "1");
        // setValue(inTMsg.dsContrBllgSchdLvlNum, schdSmryTMsg.dsContrBllgSchdSqNum);
        setValue(inTMsg.dsContrPrcEffSqNum, schdTMsg.dsContrPrcEffSqNum);
        setValue(inTMsg.dsContrBllgSchdSqNum, schdTMsg.dsContrBllgSchdSqNum);
        setValue(inTMsg.dsContrBllgSchdLvlNum, schdTMsg.dsContrBllgSchdLvlNum);
        // END 2017/09/12 K.Kojima [QC#20835,MOD]
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            setValue(inTMsg.baseChrgFlg, FLG_ON_Y);
            setValue(inTMsg.usgChrgFlg, FLG_OFF_N);
        } else {
            setValue(inTMsg.baseChrgFlg, FLG_OFF_N);
            setValue(inTMsg.usgChrgFlg, FLG_ON_Y);
        }
        setValue(inTMsg.skipRecovTpCd, schdTMsg.skipRecovTpCd);
        inTMsg.svcInvNum.clear();
        inTMsg.invDt.clear();
        setValue(inTMsg.nextBllgDt, pMsg.slsDt);
        setValue(inTMsg.bllgSchdFromDt, schdTMsg.bllgSchdFromDt);
        setValue(inTMsg.bllgSchdThruDt, schdTMsg.bllgSchdThruDt);
        setValue(inTMsg.bllgCycleCd, schdTMsg.bllgCycleCd);
        setValue(inTMsg.bllgSchdPrrtFlg, schdTMsg.bllgSchdPrrtFlg);
        setValue(inTMsg.basePrcDealAmt, schdSmryTMsg.basePrcDealAmt);
        inTMsg.basePrcFuncAmt.clear();
        // START 2016/03/24 T.Aoyagi [QC#5901, MOD]
        setValue(inTMsg.baseActlPrcDealAmt, schdSmryTMsg.basePrcDealAmt);
        // END 2016/03/24 T.Aoyagi [QC#5901, MOD]
        inTMsg.baseActlPrcFuncAmt.clear();
        inTMsg.readMtrCnt.clear();
        inTMsg.bllgMtrCnt.clear();
        inTMsg.mtrChrgDealAmt.clear();
        inTMsg.mtrChrgFuncAmt.clear();
        setValue(inTMsg.dsContrTrmnFlg, FLG_OFF_N);
        setValue(inTMsg.ccyCd, schdTMsg.ccyCd);
        setValue(inTMsg.bllgSchdTrxSrcTpCd, schdTMsg.bllgSchdTrxSrcTpCd);
        setValue(inTMsg.bllgStageFlg, FLG_OFF_N);
        setValue(inTMsg.invFlg, FLG_OFF_N);
        setValue(inTMsg.invTpCd, this.svcInvRebillTpCd);
        setValue(inTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.OPEN);
        setValue(inTMsg.dsContrBllgMtrPk, schdTMsg.dsContrBllgMtrPk);
        setValue(inTMsg.bllblFlg, FLG_ON_Y);
        inTMsg.rvsSchdDt.clear();
        // START 2017/05/18 K.Kitachi [QC#18541, MOD]
//        setValue(inTMsg.mtrEntryCpltFlg, FLG_OFF_N);
        setValue(inTMsg.mtrEntryCpltFlg, mtrEntryCpltFlg);
        // END 2017/05/18 K.Kitachi [QC#18541, MOD]
        setValue(inTMsg.svcPhysMtrReadGrpSq, schdTMsg.svcPhysMtrReadGrpSq);
        setValue(inTMsg.dsContrDtlTpCd, schdTMsg.dsContrDtlTpCd);
        // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
        BigDecimal prntDsContrBllgSchdPk = null;
        if (DS_CONTR_CATG.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_CATG_CD"))) {
            BigDecimal prntDsContrDtlPk = (BigDecimal) this.contrInfoList.get(0).get("PRNT_DS_CONTR_DTL_PK");
            if (!DS_CONTR_DTL_TP.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
                if (FLG_ON_Y.equals(baseChrgFlg)) {
                    // START 05/16/2016 T.Aoyagi [QC#8183, MOD]
                    prntDsContrBllgSchdPk = NSZC0470Query.getInstance().getPrntBllgSchdPkForRebil(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, null, inTMsg.nextBllgDt.getValue(), baseChrgFlg, INV_TP.INVOICE, pMsg.svcCrRebilDtlPk.getValue());
                    // END 05/16/2016 T.Aoyagi [QC#8183, MOD]
                } else {
                    // START 05/16/2016 T.Aoyagi [QC#8183, MOD]
                    prntDsContrBllgSchdPk = NSZC0470Query.getInstance().getPrntBllgSchdPkForUsgRebil(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, schdTMsg.dsContrBllgMtrPk.getValue(), inTMsg.nextBllgDt.getValue(), INV_TP.INVOICE, pMsg.svcCrRebilDtlPk.getValue());
                    // END 05/16/2016 T.Aoyagi [QC#8183, MOD]
                }
            }
        }
        setValue(inTMsg.prntDsContrBllgSchdPk, prntDsContrBllgSchdPk);
        // END 04/18/2016 T.Aoyagi [QC#7056, MOD]
        setValue(inTMsg.bllgPerMthAot, schdTMsg.bllgPerMthAot);
        setValue(inTMsg.prntDsContrDtlPk, schdTMsg.prntDsContrDtlPk);
        setValue(inTMsg.svcCrRebilPk, pMsg.svcCrRebilPk);
        setValue(inTMsg.svcCrRebilDtlPk, pMsg.svcCrRebilDtlPk);
        setValue(inTMsg.origDsContrBllgSchdPk, schdTMsg.dsContrBllgSchdPk);
        setValue(inTMsg.dsBllgSchdTpCd, DS_BLLG_SCHD_TP.REBILL_CREDIT_AND_REBILL);
        inTMsg.basePrcAdjDealAmt.clear();
        inTMsg.basePrcAdjFuncAmt.clear();
        setValue(inTMsg.bllgCalcFlg, FLG_OFF_N);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_BLLG_SCHD"});
        }
        return inTMsg;
    }

    // START 2017/05/18 K.Kitachi [QC#18541, MOD]
    private DS_CONTR_BLLG_SCHDTMsg createSchdForFleet(S21ApiMessageMap msgMap, DS_CONTR_BLLG_SCHDTMsg schdTMsg, Map<String, Object> fleetMachInfo, String mtrEntryCpltFlg) {
    // END 2017/05/18 K.Kitachi [QC#18541, MOD]

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        BigDecimal dsContrBllgSchdPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_SCHD_SQ);
        DS_CONTR_BLLG_SCHDTMsg inTMsg = new DS_CONTR_BLLG_SCHDTMsg();

        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
        setValue(inTMsg.dsContrDtlPk, (BigDecimal) fleetMachInfo.get("DS_CONTR_DTL_PK"));
        // END 04/18/2016 T.Aoyagi [QC#7056, MOD]
        inTMsg.dsContrBllgSchdSmryPk.clear();
        inTMsg.dsContrPrcEffPk.clear();
        inTMsg.dsContrPrcEffSqNum.clear();
        inTMsg.dsContrBllgSchdSqNum.clear();
        inTMsg.dsContrBllgSchdLvlNum.clear();
        setValue(inTMsg.baseChrgFlg, FLG_OFF_N);
        setValue(inTMsg.usgChrgFlg, FLG_ON_Y);
        setValue(inTMsg.skipRecovTpCd, schdTMsg.skipRecovTpCd);
        inTMsg.svcInvNum.clear();
        inTMsg.invDt.clear();
        setValue(inTMsg.nextBllgDt, pMsg.slsDt);
        setValue(inTMsg.bllgSchdFromDt, schdTMsg.bllgSchdFromDt);
        setValue(inTMsg.bllgSchdThruDt, schdTMsg.bllgSchdThruDt);
        setValue(inTMsg.bllgCycleCd, schdTMsg.bllgCycleCd);
        setValue(inTMsg.bllgSchdPrrtFlg, schdTMsg.bllgSchdPrrtFlg);
        inTMsg.basePrcDealAmt.clear();
        inTMsg.basePrcFuncAmt.clear();
        inTMsg.baseActlPrcDealAmt.clear();
        inTMsg.baseActlPrcFuncAmt.clear();
        inTMsg.readMtrCnt.clear();
        inTMsg.bllgMtrCnt.clear();
        inTMsg.mtrChrgDealAmt.clear();
        inTMsg.mtrChrgFuncAmt.clear();
        setValue(inTMsg.dsContrTrmnFlg, FLG_OFF_N);
        setValue(inTMsg.ccyCd, schdTMsg.ccyCd);
        setValue(inTMsg.bllgSchdTrxSrcTpCd, schdTMsg.bllgSchdTrxSrcTpCd);
        setValue(inTMsg.bllgStageFlg, FLG_OFF_N);
        setValue(inTMsg.invFlg, FLG_OFF_N);
        setValue(inTMsg.invTpCd, this.svcInvRebillTpCd);
        setValue(inTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.OPEN);
        setValue(inTMsg.dsContrBllgMtrPk, (BigDecimal) fleetMachInfo.get("DS_CONTR_BLLG_MTR_PK"));
        setValue(inTMsg.bllblFlg, FLG_ON_Y);
        inTMsg.rvsSchdDt.clear();
        // START 2017/05/18 K.Kitachi [QC#18541, MOD]
//        setValue(inTMsg.mtrEntryCpltFlg, FLG_OFF_N);
        setValue(inTMsg.mtrEntryCpltFlg, mtrEntryCpltFlg);
        // END 2017/05/18 K.Kitachi [QC#18541, MOD]
        // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
        setValue(inTMsg.svcPhysMtrReadGrpSq, (BigDecimal) fleetMachInfo.get("SVC_PHYS_MTR_READ_GRP_SQ"));
        // END 04/18/2016 T.Aoyagi [QC#7056, MOD]
        setValue(inTMsg.dsContrDtlTpCd, (String) fleetMachInfo.get("DS_CONTR_DTL_TP_CD"));
        setValue(inTMsg.prntDsContrBllgSchdPk, schdTMsg.dsContrBllgSchdPk);
        setValue(inTMsg.bllgPerMthAot, schdTMsg.bllgPerMthAot);
        // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
        setValue(inTMsg.prntDsContrDtlPk, schdTMsg.dsContrDtlPk);
        // END 04/18/2016 T.Aoyagi [QC#7056, MOD]
        setValue(inTMsg.svcCrRebilPk, pMsg.svcCrRebilPk);
        setValue(inTMsg.svcCrRebilDtlPk, pMsg.svcCrRebilDtlPk);
        setValue(inTMsg.origDsContrBllgSchdPk, (BigDecimal) fleetMachInfo.get("DS_CONTR_BLLG_SCHD_PK"));
        setValue(inTMsg.dsBllgSchdTpCd, DS_BLLG_SCHD_TP.REBILL_CREDIT_AND_REBILL);
        inTMsg.basePrcAdjDealAmt.clear();
        inTMsg.basePrcAdjFuncAmt.clear();
        setValue(inTMsg.bllgCalcFlg, FLG_OFF_N);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"DS_CONTR_BLLG_SCHD"});
        }
        return inTMsg;
    }

    // START 2016/03/24 T.Aoyagi [QC#5901, ADD]
    // mod start 2018/08/15 QC#27329-3
    //private SVC_CONTR_BLLGTMsg createContrBllgForRebil(S21ApiMessageMap msgMap, SVC_INVTMsg svcInvTMsg, DS_CONTR_BLLG_SCHDTMsg schdTMsg, SVC_CONTR_BLLGTMsg prntSvcContrBllgTMsg, Map<String, Object> fleetMachInfo, String baseChrgFlg) {
    private SVC_CONTR_BLLGTMsg createContrBllgForRebil(S21ApiMessageMap msgMap, DS_CONTR_BLLG_SCHDTMsg schdTMsg, SVC_CONTR_BLLGTMsg prntSvcContrBllgTMsg, Map<String, Object> fleetMachInfo, String baseChrgFlg,
            DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg) {
    // mod end 2018/08/15 QC#27329-3

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();
        BigDecimal svcContrBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_SQ);

        SVC_CONTR_BLLGTMsg inTMsg = new SVC_CONTR_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcContrBllgPk, svcContrBllgPk);
        // mod start 2018/08/15 QC#27329-3
        //setValue(inTMsg.dsContrPk, svcInvTMsg.dsContrPk);
        setValue(inTMsg.dsContrPk, (BigDecimal) this.contrInfoList.get(0).get("DS_CONTR_PK"));
        // mod end 2018/08/15 QC#27329-3
        setValue(inTMsg.dsContrDtlPk, schdTMsg.dsContrDtlPk);
        setValue(inTMsg.bllgCpltStsCd, BLLG_CPLT_STS.CREATED);
        setValue(inTMsg.bllgApvlReqFlg, FLG_OFF_N);
        setValue(inTMsg.bllgApvlCpltFlg, FLG_ON_Y);
        inTMsg.bllgApvlPsnCd.clear();
        if (MONTH_END_99.equals((String) this.contrInfoList.get(0).get("CONTR_CLO_DAY"))) {
            setValue(inTMsg.mthEndCloFlg, FLG_ON_Y);
        } else {
            setValue(inTMsg.mthEndCloFlg, FLG_OFF_N);
        }
        setValue(inTMsg.contrCloDay, (String) this.contrInfoList.get(0).get("CONTR_CLO_DAY"));
        setValue(inTMsg.svcContrBllgFromDt, schdTMsg.bllgSchdFromDt);
        setValue(inTMsg.svcContrBllgThruDt, schdTMsg.bllgSchdThruDt);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            setValue(inTMsg.baseBllgTmgCd, (String) this.contrInfoList.get(0).get("BASE_BLLG_TMG_CD"));
            setValue(inTMsg.baseBllgCycleMthAot, schdTMsg.bllgPerMthAot);
            setValue(inTMsg.baseBllgNextBllgDt, pMsg.slsDt);
            setValue(inTMsg.baseBllgLastBllgDt, (String) this.contrInfoList.get(0).get("BASE_BLLG_LAST_BLLG_DT"));
            setValue(inTMsg.baseChrgFlg, FLG_ON_Y);
            setValue(inTMsg.usgChrgFlg, FLG_OFF_N);
            setValue(inTMsg.svcContrBllgGrpSq, this.baseSvcContrBllgGrpSq);
            // add start 2018/08/15 QC#27329-3
            setValue(inTMsg.billToCustCd, (String) this.contrInfoList.get(0).get("BASE_BILL_TO_CUST_CD"));
            // add end 2018/08/15 QC#27329-3
        } else {
            setValue(inTMsg.mtrBllgTmgCd, (String) this.contrInfoList.get(0).get("MTR_BLLG_TMG_CD"));
            setValue(inTMsg.mtrBllgCycleMthAot, schdTMsg.bllgPerMthAot);
            setValue(inTMsg.mtrBllgNextBllgDt, pMsg.slsDt);
            setValue(inTMsg.mtrBllgLastBllgDt, (String) this.contrInfoList.get(0).get("MTR_BLLG_LAST_BLLG_DT"));
            setValue(inTMsg.baseChrgFlg, FLG_OFF_N);
            setValue(inTMsg.usgChrgFlg, FLG_ON_Y);
            setValue(inTMsg.svcContrBllgGrpSq, this.usgSvcContrBllgGrpSq);
            // add start 2018/08/15 QC#27329-3
            setValue(inTMsg.billToCustCd, dsContrBllgMtrTMsg.bllgMtrBillToCustCd);
            // add end 2018/08/15 QC#27329-3
        }
        inTMsg.baseBllgInvUpToDt.clear();
        inTMsg.mtrBllgInvUpToDt.clear();
        // mod start 2018/08/15 QC#27329-3
        //setValue(inTMsg.ccyCd, svcInvTMsg.dealCcyCd);
        setValue(inTMsg.ccyCd, (String) this.contrInfoList.get(0).get("CCY_CD"));
        // mod end 2018/08/15 QC#27329-3
        setValue(inTMsg.svcMachMstrPk, (BigDecimal) this.contrInfoList.get(0).get("SVC_MACH_MSTR_PK"));
        if (fleetMachInfo != null) {
            setValue(inTMsg.svcMachMstrPk, (BigDecimal) fleetMachInfo.get("SVC_MACH_MSTR_PK"));
        }
        setValue(inTMsg.contrCloDt, (String) this.contrInfoList.get(0).get("CONTR_CLO_DT"));
        inTMsg.sellToCustCd.clear();
        String fleetCalcFlg = FLG_OFF_N;
        if (DS_CONTR_DTL_TP.FLEET.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
            fleetCalcFlg = FLG_ON_Y;
        }
        setValue(inTMsg.fleetCalcFlg, fleetCalcFlg);

        BigDecimal prntSvcContrBllgPk = null;
        if (prntSvcContrBllgTMsg != null) {
            prntSvcContrBllgPk = prntSvcContrBllgTMsg.svcContrBllgPk.getValue();
        }
        if (DS_CONTR_CATG.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_CATG_CD"))) {
            BigDecimal prntDsContrDtlPk = (BigDecimal) this.contrInfoList.get(0).get("PRNT_DS_CONTR_DTL_PK");
            if (!DS_CONTR_DTL_TP.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
                BigDecimal prntDsContrBllgSchdPk;
                // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
                if (FLG_ON_Y.equals(baseChrgFlg)) {
                    // START 05/16/2016 T.Aoyagi [QC#8183, MOD]
                    prntDsContrBllgSchdPk = NSZC0470Query.getInstance().getPrntBllgSchdPkForRebil(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, null, pMsg.slsDt.getValue(), baseChrgFlg, INV_TP.INVOICE, pMsg.svcCrRebilDtlPk.getValue());
                    // END 05/16/2016 T.Aoyagi [QC#8183, MOD]
                } else {
                    // START 05/16/2016 T.Aoyagi [QC#8183, MOD]
                    prntDsContrBllgSchdPk = NSZC0470Query.getInstance().getPrntBllgSchdPkForUsgRebil(pMsg.glblCmpyCd.getValue(), prntDsContrDtlPk, schdTMsg.dsContrBllgMtrPk.getValue(), pMsg.slsDt.getValue(), INV_TP.INVOICE, pMsg.svcCrRebilDtlPk.getValue());
                    // END 05/16/2016 T.Aoyagi [QC#8183, MOD]
                }
                // END 04/18/2016 T.Aoyagi [QC#7056, MOD]
                SVC_CONTR_BLLGTMsg contrBllgTMsg = NSZC0470Query.getInstance().getSvcContrBllgTMsg(pMsg.glblCmpyCd.getValue(), prntDsContrBllgSchdPk);
                if (contrBllgTMsg != null) {
                    prntSvcContrBllgPk = contrBllgTMsg.svcContrBllgPk.getValue();
                    setValue(inTMsg.svcContrBllgGrpSq, contrBllgTMsg.svcContrBllgGrpSq.getValue());
                }
            }
        }

        setValue(inTMsg.prntSvcContrBllgPk, prntSvcContrBllgPk);
        // mod start 2018/08/15 QC#27329-3
        //setValue(inTMsg.dsContrCatgCd, svcInvTMsg.dsContrCatgCd);
        setValue(inTMsg.dsContrCatgCd, (String) this.contrInfoList.get(0).get("DS_CONTR_CATG_CD"));
        // mod end 2018/08/15 QC#27329-3
        setValue(inTMsg.ovrdNextBllgDt, schdTMsg.rvsSchdDt);
        setValue(inTMsg.bllgReProcFlg, FLG_OFF_N);
        inTMsg.bllgReProcPsnCd.clear();
        inTMsg.prevSvcContrBllgPk.clear();
        inTMsg.lastMtrReadTs.clear();
        setValue(inTMsg.dsContrBllgSchdPk, schdTMsg.dsContrBllgSchdPk);
        // mod start 2018/08/15 QC#27329-3
        //setValue(inTMsg.dsAcctNum, svcInvTMsg.dsAcctNum);
        setValue(inTMsg.dsAcctNum, (String) this.contrInfoList.get(0).get("DS_ACCT_NUM"));
        // mod end 2018/08/15 QC#27329-3
        String aggrCalcFlg = FLG_OFF_N;
        if (DS_CONTR_DTL_TP.AGGREGATE.equals((String) this.contrInfoList.get(0).get("DS_CONTR_DTL_TP_CD"))) {
            aggrCalcFlg = FLG_ON_Y;
        }
        setValue(inTMsg.aggrCalcFlg, aggrCalcFlg);
        setValue(inTMsg.svcCrRebilPk, pMsg.svcCrRebilPk);
        setValue(inTMsg.svcCrRebilDtlPk, pMsg.svcCrRebilDtlPk);
        setValue(inTMsg.invTpCd, INV_TP.INVOICE);
        setValue(inTMsg.dsContrBllgMtrPk, schdTMsg.dsContrBllgMtrPk);
        setValue(inTMsg.bllgCalcEstFlg, pMsg.estFlg);
        setValue(inTMsg.addlChrgFlg, FLG_OFF_N);
        inTMsg.addlChrgBllgNextBllgDt.clear();
        inTMsg.addlChrgInvUpToDt.clear();
        // del start 2018/08/15 QC#27329-3
        //setValue(inTMsg.billToCustCd, svcInvTMsg.billToCustCd);
        // del end 2018/08/15 QC#27329-3
        setValue(inTMsg.origSvcInvNum, pMsg.svcInvNum);
        setValue(inTMsg.prcAllocByMachQtyFlg, FLG_OFF_N);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0032E, new String[]{"SVC_CONTR_BLLG"});
        }
        return inTMsg;
    }
    // END 2016/03/24 T.Aoyagi [QC#5901, ADD]

    // START 2017/10/13 K.Kim [QC#21778,DEL]
//    private BigDecimal add(BigDecimal amt1, BigDecimal amt2) {
//
//        if (!hasValue(amt1)) {
//            amt1 = BigDecimal.ZERO;
//        }
//        if (!hasValue(amt2)) {
//            amt2 = BigDecimal.ZERO;
//        }
//        return amt1.add(amt2);
//    }
    // END 2017/10/13 K.Kim [QC#21778,DEL]

    private BigDecimal subtract(BigDecimal amt1, BigDecimal amt2) {

        if (!hasValue(amt1)) {
            amt1 = BigDecimal.ZERO;
        }
        if (!hasValue(amt2)) {
            amt2 = BigDecimal.ZERO;
        }
        return amt1.subtract(amt2);
    }

      // START 04/18/2016 T.Aoyagi [QC#7056, DEL]
//    private NSZC047007_xxBaseLineListPMsg getBasePMsg(NSZC047007PMsg pMsg, BigDecimal svcInvLinePk) {
//
//        for (int i = 0; i < pMsg.xxBaseLineList.getValidCount(); i++) {
//            BigDecimal prmSvcInvLinePk = pMsg.xxBaseLineList.no(i).svcInvLinePk_BL.getValue();
//            if (svcInvLinePk.compareTo(prmSvcInvLinePk) == 0) {
//                return pMsg.xxBaseLineList.no(i);
//            }
//        }
//        return null;
//    }
//
//    private NSZC047007_xxMtrLineListPMsg getUsgPMsg(NSZC047007PMsg pMsg, BigDecimal svcInvLinePk) {
//
//        for (int i = 0; i < pMsg.xxMtrLineList.getValidCount(); i++) {
//            BigDecimal prmSvcInvLinePk = pMsg.xxMtrLineList.no(i).svcInvLinePk_ML.getValue();
//            if (svcInvLinePk.compareTo(prmSvcInvLinePk) == 0) {
//                return pMsg.xxMtrLineList.no(i);
//            }
//        }
//        return null;
//    }
    // END 04/18/2016 T.Aoyagi [QC#7056, DEL]

    private NSZC047007_xxMtrLineListPMsg getPrmAllowance(S21ApiMessageMap msgMap, BigDecimal contrXsCopyPk) {

        NSZC047007PMsg pMsg = (NSZC047007PMsg) msgMap.getPmsg();

        for (int i = 0; i < pMsg.xxMtrLineList.getValidCount(); i++) {
            BigDecimal prmContrXsCopyPk = pMsg.xxMtrLineList.no(i).contrXsCopyPk_ML.getValue();
            if (contrXsCopyPk.equals(prmContrXsCopyPk)) {
                return pMsg.xxMtrLineList.no(i);
            }
        }
        return null;
    }
    // Add Start 2017/10/26 QC#21815
    private boolean isAggLine(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        if (!hasValue(dsContrDtlPk)) {
            return false;
        }
        DS_CONTR_DTLTMsg dsContrDtlTMsg = NSZC0470Query.getInstance().getDsContrDtlTMsg(glblCmpyCd, dsContrDtlPk);
        if (dsContrDtlTMsg == null) {
            return false;
        }
        if (!DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
            return false;
        }
        return true;
    }
    // Add End 2017/10/26 QC#21815
}
