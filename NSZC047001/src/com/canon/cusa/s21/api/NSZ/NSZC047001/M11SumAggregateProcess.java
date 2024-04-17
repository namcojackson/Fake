package com.canon.cusa.s21.api.NSZ.NSZC047001;

import static com.canon.cusa.s21.api.NSZ.NSZC047001.constant.NSZC047001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHD_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.parts.NSZC047011PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Contract Billing Schedule API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/28/2017   Hitachi         K.Kishimoto     Create          N/A
 * 10/25/2017   Hitachi         T.Tomita        Update          QC#21815
 * 2018/04/09   Hitachi         K.Kojima        Update          QC#24802
 * 2018/04/20   Hitachi         K.Kojima        Update          QC#25595
 * 2018/05/18   Hitachi         U.Kim           Update          QC#24854
 * 2018/07/17   Hitachi         K.Kishimoto     Update          QC#25959
 * 2019/03/07   Hitachi         K.Kitachi       Update          QC#30619
 *</pre>
 */
public class M11SumAggregateProcess implements ZYPConstant {

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        checkParameter(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        sumAggregateProcess(msgMap);
    }

    private void sumAggregateProcess(S21ApiMessageMap msgMap) {

        NSZC047011PMsg pMsg = (NSZC047011PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();

        // START 2018/04/09 K.Kojima [QC#24802,ADD]
        DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, dsContrDtlPk);
        dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKey(dsContrDtlTMsg);
        if (dsContrDtlTMsg == null) {
            return;
        }
        // END 2018/04/09 K.Kojima [QC#24802,ADD]
        // START 2018/07/17 [QC#25959,ADD]
        recovPrntSchdPk(glblCmpyCd, dsContrDtlPk);
        // END   2018/07/17 [QC#25959,ADD]
        // START 2018/04/09 K.Kojima [QC#24802,MOD]
        // List<Map<String, Object>> bllgSchdList = NSZC0470Query.getInstance().getSumAggBllgSchd(glblCmpyCd, dsContrDtlPk);
        List<Map<String, Object>> bllgSchdList = NSZC0470Query.getInstance().getSumAggBllgSchd(glblCmpyCd, dsContrDtlPk, dsContrDtlTMsg.dsContrPk.getValue());
        // END 2018/04/09 K.Kojima [QC#24802,MOD]
        for (Map<String, Object> bllgSchd : bllgSchdList) {
            BigDecimal bllgSchdPk = (BigDecimal) bllgSchd.get("DS_CONTR_BLLG_SCHD_PK");
            DS_CONTR_BLLG_SCHDTMsg bllgSchdTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, bllgSchdPk);
            if (bllgSchdTMsg == null) {
                continue;
            }
            // Add Start 2017/10/25 QC#21815
            setValue(bllgSchdTMsg.basePrcDealAmt, (BigDecimal) bllgSchd.get("BASE_PRC_DEAL_AMT"));
            // Add End 2017/10/25 QC#21815
            setValue(bllgSchdTMsg.baseActlPrcDealAmt, (BigDecimal) bllgSchd.get("TERM_AMT_RATE"));
            setValue(bllgSchdTMsg.basePrcAdjDealAmt, (BigDecimal) bllgSchd.get("ADJ_AMT_RATE"));
            S21ApiTBLAccessor.update(bllgSchdTMsg);
        }

        // START 2018/05/18 U.Kim [QC#24854, MOD]
        // List<Map<String, Object>> bllgSchdSmryList = NSZC0470Query.getInstance().getSumAggBllgSchdSmry(glblCmpyCd, dsContrDtlPk);
        List<Map<String, Object>> bllgSchdSmryList = NSZC0470Query.getInstance().getSumAggBllgSchdSmry(glblCmpyCd, dsContrDtlPk, pMsg.slsDt.getValue());
        // END 2018/05/18 U.Kim [QC#24854, MOD]
        for (Map<String, Object> bllgSchdSmry : bllgSchdSmryList) {
            BigDecimal bllgSchdSmryPk = (BigDecimal) bllgSchdSmry.get("DS_CONTR_BLLG_SCHD_SMRY_PK");
            DS_CONTR_BLLG_SCHD_SMRYTMsg bllgSchdSmryTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, bllgSchdSmryPk);
            if (bllgSchdSmryTMsg == null) {
                continue;
            }
            // Add Start 2017/10/25 QC#21815
            setValue(bllgSchdSmryTMsg.basePrcDealAmt, (BigDecimal) bllgSchdSmry.get("BASE_PRC_DEAL_AMT"));
            // Add End 2017/10/25 QC#21815
            setValue(bllgSchdSmryTMsg.baseSubTotPrcDealAmt, (BigDecimal) bllgSchdSmry.get("TERM_AMT_RATE"));
            setValue(bllgSchdSmryTMsg.basePrcDealAdjAmt, (BigDecimal) bllgSchdSmry.get("ADJ_AMT_RATE"));
            S21ApiTBLAccessor.update(bllgSchdSmryTMsg);
        }

        // START 2018/05/18 U.Kim [QC#24854, MOD]
        // List<Map<String, Object>> prcEffList = NSZC0470Query.getInstance().getSumAggPrcEff(glblCmpyCd, dsContrDtlPk);
        List<Map<String, Object>> prcEffList = NSZC0470Query.getInstance().getSumAggPrcEff(glblCmpyCd, dsContrDtlPk, pMsg.slsDt.getValue());
        // END 2018/05/18 U.Kim [QC#24854, MOD]
        for (Map<String, Object> prcEff : prcEffList) {
            BigDecimal prcEffPk = (BigDecimal) prcEff.get("DS_CONTR_PRC_EFF_PK");
            DS_CONTR_PRC_EFFTMsg prcEffTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, prcEffPk);
            if (prcEffTMsg == null) {
                continue;
            }
            // Add Start 2017/10/25 QC#21815
            setValue(prcEffTMsg.basePrcDealAmt, (BigDecimal) prcEff.get("BASE_PRC_DEAL_AMT"));
            // Add End 2017/10/25 QC#21815
            setValue(prcEffTMsg.basePrcTermDealAmtRate, (BigDecimal) prcEff.get("TERM_AMT_RATE"));
            S21ApiTBLAccessor.update(prcEffTMsg);
        }

        List<Map<String, Object>> contrDtlList = NSZC0470Query.getInstance().getSumAggContrDtl(glblCmpyCd, dsContrDtlPk);
        for (Map<String, Object> ContrDtl : contrDtlList) {
            BigDecimal contrDtlPk = (BigDecimal) ContrDtl.get("DS_CONTR_DTL_PK");
            DS_CONTR_DTLTMsg contrDtlTMsg = NSZC0470Query.getInstance().getDsContrDtlTMsg(glblCmpyCd, contrDtlPk);
            if (contrDtlTMsg == null) {
                continue;
            }
            // Add Start 2017/10/25 QC#21815
            BigDecimal basePrcDealAmt = NSZC0470Query.getInstance().getCurPeBasePrcDealAmt(glblCmpyCd, contrDtlPk, pMsg.slsDt.getValue());
            if (hasValue(basePrcDealAmt)) {
                setValue(contrDtlTMsg.basePrcDealAmt, basePrcDealAmt);
            }
            // Add End 2017/10/25 QC#21815
            setValue(contrDtlTMsg.basePrcTermDealAmtRate, (BigDecimal) ContrDtl.get("TERM_AMT_RATE"));
            S21ApiTBLAccessor.update(contrDtlTMsg);
        }

        // START 2018/04/20 K.Kojima [QC#25595,ADD]
        List<Map<String, Object>> sumBllgSchdMtrList = NSZC0470Query.getInstance().getSumBllgSchdMtr(glblCmpyCd, dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlPk);
        for (Map<String, Object> sumBllgSchdMtr : sumBllgSchdMtrList) {
            BigDecimal dsContrBllgSchdMtrPk = (BigDecimal) sumBllgSchdMtr.get("DS_CONTR_BLLG_SCHD_MTR_PK");
            BigDecimal newXsMtrCopyQty = (BigDecimal) sumBllgSchdMtr.get("NEW_XS_MTR_COPY_QTY");
            DS_CONTR_BLLG_SCHD_MTRTMsg dsContrBllgSchdMtrTMsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();
            setValue(dsContrBllgSchdMtrTMsg.glblCmpyCd, glblCmpyCd);
            setValue(dsContrBllgSchdMtrTMsg.dsContrBllgSchdMtrPk, dsContrBllgSchdMtrPk);
            dsContrBllgSchdMtrTMsg = (DS_CONTR_BLLG_SCHD_MTRTMsg) S21ApiTBLAccessor.findByKey(dsContrBllgSchdMtrTMsg);
            if (dsContrBllgSchdMtrTMsg == null) {
                continue;
            }
            setValue(dsContrBllgSchdMtrTMsg.xsMtrCopyQty, newXsMtrCopyQty);
            setValue(dsContrBllgSchdMtrTMsg.xsMtrAdjCopyQty, BigDecimal.ZERO);
            S21ApiTBLAccessor.update(dsContrBllgSchdMtrTMsg);
        }
        // END 2018/04/20 K.Kojima [QC#25595,ADD]
    }

    private void checkParameter(S21ApiMessageMap msgMap) {

        NSZC047011PMsg pMsg = (NSZC047011PMsg) msgMap.getPmsg();
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.glblCmpyCd, ZZZM9007E, new String[] {"Global Company Code" });
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.slsDt, ZZZM9007E, new String[] {"Salse Date" });
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.dsContrDtlPk, ZZZM9007E, new String[] {"DS Contract Detail PK" });
    }
    // START 2018/07/17 [QC#25959,ADD]
    private void recovPrntSchdPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        List<Map<String, Object>> recovBaseSchdList = NSZC0470Query.getInstance().getRecovPrntPkForAggBase(glblCmpyCd, dsContrDtlPk);
        for (Map<String, Object> recovBaseSchd : recovBaseSchdList) {
            DS_CONTR_BLLG_SCHDTMsg updBllgSchd = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, (BigDecimal) recovBaseSchd.get("DS_CONTR_BLLG_SCHD_PK"));
            if (updBllgSchd != null) {
                setValue(updBllgSchd.prntDsContrBllgSchdPk, (BigDecimal) recovBaseSchd.get("PRNT_SCHD_PK"));
                // START 2019/03/07 K.Kitachi [QC#30619, ADD]
                S21ApiTBLAccessor.update(updBllgSchd);
                // END 2019/03/07 K.Kitachi [QC#30619, ADD]
            }
        }
        List<Map<String, Object>> recovUsgSchdList = NSZC0470Query.getInstance().getRecovPrntPkForAggUsg(glblCmpyCd, dsContrDtlPk);
        for (Map<String, Object> recovUsgSchd : recovUsgSchdList) {
            DS_CONTR_BLLG_SCHDTMsg updBllgSchd = NSZC0470Query.getInstance().getDsContrBllgSchdTMsg(glblCmpyCd, (BigDecimal) recovUsgSchd.get("DS_CONTR_BLLG_SCHD_PK"));
            if (updBllgSchd != null) {
                setValue(updBllgSchd.prntDsContrBllgSchdPk, (BigDecimal) recovUsgSchd.get("PRNT_SCHD_PK"));
                // START 2019/03/07 K.Kitachi [QC#30619, ADD]
                S21ApiTBLAccessor.update(updBllgSchd);
                // END 2019/03/07 K.Kitachi [QC#30619, ADD]
            }
        }
    }
    // END   2018/07/17 [QC#25959,ADD]
}
