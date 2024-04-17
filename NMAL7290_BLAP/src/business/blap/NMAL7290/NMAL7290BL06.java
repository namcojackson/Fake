/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7290;

import static business.blap.NMAL7290.constant.NMAL7290Constant.NMAM8330I;
import static business.blap.NMAL7290.constant.NMAL7290Constant.ZZM8100I;
import static business.blap.NMAL7290.constant.NMAL7290Constant.MSG_PRC_RULE_PRCD_GRP_NM;
import static business.blap.NMAL7290.constant.NMAL7290Constant.NMAM0010E;
import static business.blap.NMAL7290.constant.NMAL7290Constant.NMAM0043E;
import static business.blap.NMAL7290.constant.NMAL7290Constant.NMAM0176E;
import static business.blap.NMAL7290.constant.NMAL7290Constant.NMAM0177E;
import static business.blap.NMAL7290.constant.NMAL7290Constant.NMAM0208E;
import static business.blap.NMAL7290.constant.NMAL7290Constant.NMAM8307E;
import static business.blap.NMAL7290.constant.NMAL7290Constant.NZZM0003E;
import static business.blap.NMAL7290.constant.NMAL7290Constant.TBL_PRC_RULE_PRCD;
import static business.blap.NMAL7290.constant.NMAL7290Constant.TBL_PRC_RULE_PRCD_DTL;
import static business.blap.NMAL7290.constant.NMAL7290Constant.ZZZM9006E;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7290.constant.NMAL7290Constant;
import business.db.PRC_RULE_PRCDTMsg;
import business.db.PRC_RULE_PRCDTMsgArray;
import business.db.PRC_RULE_PRCD_DTLTMsg;
import business.db.PRC_RULE_PRCD_DTLTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_FUNC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_PRCD_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7290BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   Fujitsu         C.Tanaka        Create          N/A
 * 2016/08/15   SRAA            K.Aratani       Update          S21_NA#13311
 *</pre>
 */
public class NMAL7290BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7290CMsg bizMsg = (NMAL7290CMsg) cMsg;
            NMAL7290SMsg glblMsg = (NMAL7290SMsg) sMsg;

            if ("NMAL7290Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7290Scrn00_CMN_Submit(bizMsg, glblMsg);

            // QC#9694 2016/07/08 Mod Start
            } else if ("NMAL7290Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NMAL7290Scrn00_CMN_Delete(bizMsg, glblMsg);
            // QC#9694 2016/07/08 Mod End

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7290Scrn00_CMN_Submit(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {
        if (!errCheck(bizMsg, glblMsg)) {
            return;
        }

        if (!savePrcRulePrcd(bizMsg)) {
            return;
        }

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (!savePrcRulePrcdDtl(bizMsg, i)) {
                return;
            }
        }

        if (!deletePrcRulePrcdDtl(bizMsg, glblMsg)) {
            return;
        }
    }
    
    /**
     * CMN_Delete Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7290Scrn00_CMN_Delete(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {
        // Warning comment
        if (ZYPConstant.FLG_OFF_0.equals(bizMsg.xxWrnSkipFlg.getValue())) {
            bizMsg.setMessageInfo(NMAM8330I, new String[] {"Price Precedence Group" });
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_1);
            return;
        }

        // Delete
        PRC_RULE_PRCDTMsg prcRulePrcdTMsg = new PRC_RULE_PRCDTMsg();
        ZYPEZDItemValueSetter.setValue(prcRulePrcdTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(prcRulePrcdTMsg.prcRulePrcdPk, bizMsg.prcRulePrcdPk);

        prcRulePrcdTMsg = (PRC_RULE_PRCDTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prcRulePrcdTMsg);
        if (prcRulePrcdTMsg == null) {
            bizMsg.setMessageInfo(ZZZM9006E, new String[] {TBL_PRC_RULE_PRCD });
            return;
        }
        if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime.getValue(), bizMsg.ezUpTimeZone.getValue(), prcRulePrcdTMsg.ezUpTime.getValue(), prcRulePrcdTMsg.ezUpTimeZone.getValue())) {
            bizMsg.setMessageInfo(NZZM0003E);
            return;
        }

        EZDTBLAccessor.logicalRemove(prcRulePrcdTMsg);
        if (!RTNCD_NORMAL.equals(prcRulePrcdTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM0208E, new String[] {TBL_PRC_RULE_PRCD });
            return;
        }
        PRC_RULE_PRCD_DTLTMsg tMsg = new PRC_RULE_PRCD_DTLTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        tMsg.setConditionValue("prcRulePrcdPk01", bizMsg.prcRulePrcdPk.getValue());
        PRC_RULE_PRCD_DTLTMsgArray prcRulePrcdDtlArray = (PRC_RULE_PRCD_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(tMsg);

        PRC_RULE_PRCD_DTLTMsg prcRulePrcdDtlTMs;
        for (int i = 0; i < prcRulePrcdDtlArray.getValidCount(); i++) {
            prcRulePrcdDtlTMs = prcRulePrcdDtlArray.no(i);
            EZDTBLAccessor.logicalRemove(prcRulePrcdDtlTMs);
            if (!RTNCD_NORMAL.equals(prcRulePrcdTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0208E, new String[] {TBL_PRC_RULE_PRCD_DTL });
                return;
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_0);
        bizMsg.setMessageInfo(ZZM8100I);
        return;
    }

    private boolean errCheck(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        // QC#9694 2016/07/08 Mod Start
        // BigDecimal prcRulePrcdGrpNum = bizMsg.prcRulePrcdGrpNum.getValue();
        BigDecimal prcRulePrcdPk = bizMsg.prcRulePrcdPk.getValue();
        // QC#9694 2016/07/08 Mod End
        String prcRulePrcdGrpNm = bizMsg.prcRulePrcdGrpNm.getValue();

        // QC#9694 2016/07/08 Del Start
        //PRC_RULE_PRCDTMsg searchCond = new PRC_RULE_PRCDTMsg();
        //searchCond.setSQLID("001");
        //searchCond.setConditionValue("glblCmpyCd01", glblCmpyCd);
        //searchCond.setConditionValue("prcRulePrcdGrpNum01", prcRulePrcdGrpNum);
        //PRC_RULE_PRCDTMsgArray prcRulePrcdTMsgArray = (PRC_RULE_PRCDTMsgArray) EZDTBLAccessor.findByCondition(searchCond);
        //
        //if ((!ZYPCommonFunc.hasValue(bizMsg.prcRulePrcdPk) && prcRulePrcdTMsgArray.length() > 0) || (ZYPCommonFunc.hasValue(bizMsg.prcRulePrcdPk) && prcRulePrcdTMsgArray.length() > 1)) {
        //    bizMsg.prcRulePrcdGrpNum.setErrorInfo(1, NMAM0010E, new String[] {MSG_PRC_RULE_PRCD_GRP_NUM });
        //    return false;
        //}
        // QC#9694 2016/07/08 Del End

        PRC_RULE_PRCDTMsg searchCond2 = new PRC_RULE_PRCDTMsg();
        searchCond2.setSQLID("002");
        searchCond2.setConditionValue("glblCmpyCd01", glblCmpyCd);
        searchCond2.setConditionValue("prcRulePrcdGrpNm01", prcRulePrcdGrpNm);
        PRC_RULE_PRCDTMsgArray prcRulePrcdTMsgArray2 = (PRC_RULE_PRCDTMsgArray) EZDTBLAccessor.findByCondition(searchCond2);

        //if ((!ZYPCommonFunc.hasValue(bizMsg.prcRulePrcdPk) && prcRulePrcdTMsgArray2.length() > 0) || (ZYPCommonFunc.hasValue(bizMsg.prcRulePrcdPk) && prcRulePrcdTMsgArray2.length() > 1)) {
        if ((!ZYPCommonFunc.hasValue(bizMsg.prcRulePrcdPk_BK) && prcRulePrcdTMsgArray2.length() > 0) || (ZYPCommonFunc.hasValue(bizMsg.prcRulePrcdPk_BK) && prcRulePrcdTMsgArray2.length() > 1)) {
            bizMsg.prcRulePrcdGrpNm.setErrorInfo(1, NMAM0010E, new String[] {MSG_PRC_RULE_PRCD_GRP_NM });
            return false;
        }
        if (bizMsg.B.getValidCount() < 2) {
            bizMsg.setMessageInfo(NMAM8307E);
            return false;
        }
        // QC#9694 2016/07/08 Del Start
        //S21SortTarget sortTarget = new S21SortTarget(bizMsg.B, bizMsg.B.no(0).getBaseContents());
        //
        //S21SortKey sortKey = new S21SortKey();
        //sortKey.add("prcRuleOpTpCd_B", S21SortKey.ASC);
        //sortKey.add("prcRulePrcdSqNum_B", S21SortKey.ASC);
        //
        //S21EZDMsgArraySort.sortNullsLast(sortTarget, sortKey, 0, bizMsg.B.getValidCount());
        //
        //NMAL7290_BCMsg bCMsg = null;
        //NMAL7290_BCMsg bCMsgPrev = null;
        //int len = bizMsg.B.getValidCount();
        //for (int i = 0; i < len; i++) {
        //    bCMsg = bizMsg.B.no(i);

        //    if (PRC_RULE_OP_TP.OR.equals(bCMsg.prcRuleOpTpCd_B.getValue()) && !ZYPCommonFunc.hasValue(bCMsg.prcRuleEvtrTpCd_B)) {
        //        bCMsg.prcRuleEvtrTpCd_B.setErrorInfo(1, NMAM8308E);
        //        return false;
        //    } else if (!PRC_RULE_OP_TP.OR.equals(bCMsg.prcRuleOpTpCd_B.getValue()) && ZYPCommonFunc.hasValue(bCMsg.prcRuleEvtrTpCd_B)) {
        //        bCMsg.prcRuleEvtrTpCd_B.setErrorInfo(1, NMAM8309E);
        //        return false;
        //    }
        //
        //    if (i == len - 1 && ZYPCommonFunc.hasValue(bCMsg.prcRuleOpTpCd_B)) {
        //        bCMsg.prcRuleOpTpCd_B.setErrorInfo(1, NMAM8311E);
        //        return false;
        //    } else if (i != len - 1 && !ZYPCommonFunc.hasValue(bCMsg.prcRuleOpTpCd_B)) {
        //        bCMsg.prcRuleOpTpCd_B.setErrorInfo(1, NMAM0836E, new String[] {MSG_OP });
        //        return false;
        //    }
        //
        //    if (i > 0) {
        //        bCMsgPrev = bizMsg.B.no(i - 1);
        //        if (PRC_RULE_OP_TP.OR.equals(bCMsgPrev.prcRuleOpTpCd_B.getValue()) && bCMsg.prcRulePrcdSqNum_B.getValue().equals(bCMsgPrev.prcRulePrcdSqNum_B.getValue())) {
        //            bCMsg.prcRuleEvtrTpCd_B.setErrorInfo(1, NMAM8310E);
        //            return false;
        //        }
        //    }
        //}
        // QC#9694 2016/07/08 Del Start
        // QC#9694 2016/07/08 Add Start
        if (ZYPCommonFunc.hasValue(bizMsg.effThruDt)) {
            if (!ZYPCommonFunc.hasValue(bizMsg.effFromDt)
                    || ZYPDateUtil.compare(bizMsg.effFromDt.getValue(), bizMsg.effThruDt.getValue()) > 0) {
                bizMsg.effFromDt.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                return false;
            }
        }
        String prcRuleCatgCd = null;
        String prcRuleModTpCd = null;
        String prcRuleAdjLvlCd = null;
        String wkPrcRuleModTpCd = null;
        String wkPrcRuleAdjLvlCd = null;
        String prcFuncTpCd = null;
        NMAL7290_BCMsg bCMsg;
        NMAL7290_BCMsg bCMsg2;
        int cnt = 0;
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            bCMsg = bizMsg.B.no(i);

            if(PRC_PRCD_ACT_TP.ALL.equals(bizMsg.prcPrcdActTpCd.getValue())){
                for (int j = i + 1; j < bizMsg.B.getValidCount(); j++) {
                    bCMsg2 = bizMsg.B.no(j);
                    if (bCMsg.prcRulePrcdSqNum_B.getValue().equals(bCMsg2.prcRulePrcdSqNum_B.getValue())) {
                        bCMsg.xxChkBox_B.setErrorInfo(1, NMAL7290Constant.NMAM0072E, new String[] {"Seq #" });
                        return false;
                    }
                }
            }

            cnt = NMAL7290Query.getInstance().getCntPrcRuleHdr(bizMsg, bCMsg).intValue();
            if (cnt != 0) {
                bCMsg.xxChkBox_B.setErrorInfo(1, NMAL7290Constant.NMAM0834E, new String[] {"this Price Rule", "Price Precedence Group" });
                return false;
            }

            if (ZYPCommonFunc.hasValue(prcRuleCatgCd)) {
                if (!prcRuleCatgCd.equals(bCMsg.prcRuleCatgCd_B.getValue())) {
                    bCMsg.xxChkBox_B.setErrorInfo(1, NMAL7290Constant.NMAM8625E, new String[] {"Price Rule Category", "Precedence Group" });
                    return false;
                }
            } else {
                prcRuleCatgCd = bizMsg.B.no(i).prcRuleCatgCd_B.getValue();
            }

            S21SsmEZDResult ssmResult = NMAL7290Query.getInstance().getPrcRuleDtlData(bizMsg, bCMsg);
            if (!ssmResult.isCodeNormal()) {
                continue;
            }
            List<Map<?, ?>> list = (List<Map<?, ?>>) ssmResult.getResultObject();
            wkPrcRuleModTpCd = (String) list.get(0).get("PRC_RULE_MOD_TP_CD");
            wkPrcRuleAdjLvlCd = (String) list.get(0).get("PRC_RULE_ADJ_LVL_CD");
            if (ZYPCommonFunc.hasValue(prcRuleModTpCd)) {
                if (!prcRuleModTpCd.equals(wkPrcRuleModTpCd)) {
                    bCMsg.xxChkBox_B.setErrorInfo(1, NMAL7290Constant.NMAM8625E, new String[] {"Price Rule Modify", "Precedence Group" });
                    return false;
                }
            } else {
                prcRuleModTpCd = wkPrcRuleModTpCd;
            }
            if (ZYPCommonFunc.hasValue(prcRuleAdjLvlCd)) {
                //QC#13311
                if (!PRC_PRCD_ACT_TP.ALL.equals(bizMsg.prcPrcdActTpCd.getValue())) {
                    if (!prcRuleAdjLvlCd.equals(wkPrcRuleAdjLvlCd)) {
                        bCMsg.xxChkBox_B.setErrorInfo(1, NMAL7290Constant.NMAM8625E, new String[] {"Price Adjustment Level", "Precedence Group" });
                        return false;
                    }
                }
            } else {
                prcRuleAdjLvlCd = wkPrcRuleAdjLvlCd;
            }
            if (!PRC_PRCD_ACT_TP.ALL.equals(bizMsg.prcPrcdActTpCd.getValue())) {
                for (Map<?, ?> data : list) {
                    prcFuncTpCd = (String) data.get("PRC_FUNC_TP_CD");
                    if (PRC_FUNC_TP.GET_PROMOTION_API.equals(prcFuncTpCd)) {
                        bCMsg.xxChkBox_B.setErrorInfo(1, NMAL7290Constant.NMAM8626E);
                        return false;
                    }
                }
            }
        }
        // QC#9694 2016/07/08 Add End

        return true;
    }

    private boolean savePrcRulePrcd(NMAL7290CMsg bizMsg) {
        BigDecimal prcRulePrcdPk = bizMsg.prcRulePrcdPk.getValue();

        PRC_RULE_PRCDTMsg prcRulePrcdTMsg = new PRC_RULE_PRCDTMsg();
        ZYPEZDItemValueSetter.setValue(prcRulePrcdTMsg.glblCmpyCd, getGlobalCompanyCode());

        if (!ZYPCommonFunc.hasValue(prcRulePrcdPk)) {

            ZYPEZDItemValueSetter.setValue(prcRulePrcdTMsg.prcRulePrcdPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_RULE_PRCD_SQ));
            // ZYPEZDItemValueSetter.setValue(prcRulePrcdTMsg.prcRulePrcdGrpNum, bizMsg.prcRulePrcdGrpNum); // QC#9694 2016/07/08 Del
            ZYPEZDItemValueSetter.setValue(prcRulePrcdTMsg.prcRulePrcdGrpNm, bizMsg.prcRulePrcdGrpNm);
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRulePrcdPk, prcRulePrcdTMsg.prcRulePrcdPk);
            // QC#9694 2016/07/08 Mod Start
            ZYPEZDItemValueSetter.setValue(prcRulePrcdTMsg.prcPrcdActTpCd, bizMsg.prcPrcdActTpCd);
            ZYPEZDItemValueSetter.setValue(prcRulePrcdTMsg.actvFlg, getFlgVal(bizMsg.actvFlg.getValue()));
            ZYPEZDItemValueSetter.setValue(prcRulePrcdTMsg.effFromDt, bizMsg.effFromDt);
            ZYPEZDItemValueSetter.setValue(prcRulePrcdTMsg.effThruDt, bizMsg.effThruDt);
            // QC#9694 2016/07/08 Mod End

            S21FastTBLAccessor.insert(prcRulePrcdTMsg);
            if (!RTNCD_NORMAL.equals(prcRulePrcdTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0176E, new String[] {TBL_PRC_RULE_PRCD });
                return false;
            }
        } else {

            ZYPEZDItemValueSetter.setValue(prcRulePrcdTMsg.prcRulePrcdPk, prcRulePrcdPk);

            prcRulePrcdTMsg = (PRC_RULE_PRCDTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prcRulePrcdTMsg);
            if (prcRulePrcdTMsg == null) {
                bizMsg.setMessageInfo(ZZZM9006E, new String[] {TBL_PRC_RULE_PRCD });
                return false;
            }
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime.getValue(), bizMsg.ezUpTimeZone.getValue(), prcRulePrcdTMsg.ezUpTime.getValue(), prcRulePrcdTMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            //ZYPEZDItemValueSetter.setValue(prcRulePrcdTMsg.prcRulePrcdGrpNum, bizMsg.prcRulePrcdGrpNum);// QC#9694 2016/07/08 Del
            ZYPEZDItemValueSetter.setValue(prcRulePrcdTMsg.prcRulePrcdGrpNm, bizMsg.prcRulePrcdGrpNm);
            // QC#9694 2016/07/08 Mod Start
            ZYPEZDItemValueSetter.setValue(prcRulePrcdTMsg.prcPrcdActTpCd, bizMsg.prcPrcdActTpCd);
            ZYPEZDItemValueSetter.setValue(prcRulePrcdTMsg.actvFlg, getFlgVal(bizMsg.actvFlg.getValue()));
            ZYPEZDItemValueSetter.setValue(prcRulePrcdTMsg.effFromDt, bizMsg.effFromDt);
            ZYPEZDItemValueSetter.setValue(prcRulePrcdTMsg.effThruDt, bizMsg.effThruDt);
            // QC#9694 2016/07/08 Mod End

            S21FastTBLAccessor.update(prcRulePrcdTMsg);
            if (!RTNCD_NORMAL.equals(prcRulePrcdTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {TBL_PRC_RULE_PRCD });
                return false;
            }
        }

        return true;
    }

    private boolean savePrcRulePrcdDtl(NMAL7290CMsg bizMsg, int idx) {
        NMAL7290_BCMsg bCMsg = bizMsg.B.no(idx);
        BigDecimal prcRulePrcdDtlPk = bCMsg.prcRulePrcdDtlPk_B.getValue();

        PRC_RULE_PRCD_DTLTMsg prcRulePrcdDtlTMsg = new PRC_RULE_PRCD_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(prcRulePrcdDtlTMsg.glblCmpyCd, getGlobalCompanyCode());

        if (!ZYPCommonFunc.hasValue(prcRulePrcdDtlPk)) {

            ZYPEZDItemValueSetter.setValue(prcRulePrcdDtlTMsg.prcRulePrcdDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_RULE_PRCD_DTL_SQ));
            ZYPEZDItemValueSetter.setValue(prcRulePrcdDtlTMsg.prcRulePrcdPk, bizMsg.prcRulePrcdPk);
            ZYPEZDItemValueSetter.setValue(prcRulePrcdDtlTMsg.prcRuleHdrPk, bCMsg.prcRuleHdrPk_B);
            ZYPEZDItemValueSetter.setValue(prcRulePrcdDtlTMsg.prcRulePrcdSqNum, bCMsg.prcRulePrcdSqNum_B);
            //ZYPEZDItemValueSetter.setValue(prcRulePrcdDtlTMsg.prcRuleOpTpCd, bCMsg.prcRuleOpTpCd_B);     // QC#9694 2016/07/08 Del
            //ZYPEZDItemValueSetter.setValue(prcRulePrcdDtlTMsg.prcRuleEvtrTpCd, bCMsg.prcRuleEvtrTpCd_B); // QC#9694 2016/07/08 Del

            S21FastTBLAccessor.insert(prcRulePrcdDtlTMsg);
            if (!RTNCD_NORMAL.equals(prcRulePrcdDtlTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0176E, new String[] {TBL_PRC_RULE_PRCD_DTL });
                return false;
            }
        } else {

            ZYPEZDItemValueSetter.setValue(prcRulePrcdDtlTMsg.prcRulePrcdDtlPk, prcRulePrcdDtlPk);

            prcRulePrcdDtlTMsg = (PRC_RULE_PRCD_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prcRulePrcdDtlTMsg);
            if (prcRulePrcdDtlTMsg == null) {
                bizMsg.setMessageInfo(ZZZM9006E, new String[] {TBL_PRC_RULE_PRCD_DTL });
                return false;
            }

            ZYPEZDItemValueSetter.setValue(prcRulePrcdDtlTMsg.prcRulePrcdPk, bizMsg.prcRulePrcdPk);
            ZYPEZDItemValueSetter.setValue(prcRulePrcdDtlTMsg.prcRuleHdrPk, bCMsg.prcRuleHdrPk_B);
            ZYPEZDItemValueSetter.setValue(prcRulePrcdDtlTMsg.prcRulePrcdSqNum, bCMsg.prcRulePrcdSqNum_B);
            //ZYPEZDItemValueSetter.setValue(prcRulePrcdDtlTMsg.prcRuleOpTpCd, bCMsg.prcRuleOpTpCd_B);     // QC#9694 2016/07/08 Del
            //ZYPEZDItemValueSetter.setValue(prcRulePrcdDtlTMsg.prcRuleEvtrTpCd, bCMsg.prcRuleEvtrTpCd_B); // QC#9694 2016/07/08 Del

            S21FastTBLAccessor.update(prcRulePrcdDtlTMsg);
            if (!RTNCD_NORMAL.equals(prcRulePrcdDtlTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {TBL_PRC_RULE_PRCD_DTL });
                return false;
            }
        }

        return true;
    }

    private boolean deletePrcRulePrcdDtl(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {
        int len = glblMsg.X.getValidCount();
        if (len > 0) {
            PRC_RULE_PRCD_DTLTMsg[] prcRulePrcdDtlTMsgArr = new PRC_RULE_PRCD_DTLTMsg[len];
            int cnt = 0;

            for (int i = 0; i < glblMsg.X.getValidCount(); i++) {
                PRC_RULE_PRCD_DTLTMsg prcRulePrcdDtlTMsg = new PRC_RULE_PRCD_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(prcRulePrcdDtlTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(prcRulePrcdDtlTMsg.prcRulePrcdDtlPk, glblMsg.X.no(i).prcRulePrcdDtlPk_X);

                prcRulePrcdDtlTMsg = (PRC_RULE_PRCD_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prcRulePrcdDtlTMsg);

                if (!RTNCD_NORMAL.equals(prcRulePrcdDtlTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(ZZZM9006E, new String[] {TBL_PRC_RULE_PRCD });
                    return false;
                }
                prcRulePrcdDtlTMsgArr[cnt] = prcRulePrcdDtlTMsg;
                cnt++;
            }

            int result = S21FastTBLAccessor.removeLogical(prcRulePrcdDtlTMsgArr);
            if (result != len) {
                bizMsg.setMessageInfo(NMAM0208E, new String[] {TBL_PRC_RULE_PRCD });
                return false;
            }
        }

        return true;
    }

    private String getFlgVal(String flg) {
        if (ZYPConstant.FLG_ON_Y.equals(flg)) {
            return flg;
        }
        return ZYPConstant.FLG_OFF_N;
    }
}
