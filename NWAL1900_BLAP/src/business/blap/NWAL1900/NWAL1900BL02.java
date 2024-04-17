package business.blap.NWAL1900;

import static business.blap.NWAL1900.constant.NWAL1900Constant.FLG_NO;
import static business.blap.NWAL1900.constant.NWAL1900Constant.FLG_YES;
import static business.blap.NWAL1900.constant.NWAL1900Constant.NZZM0000E;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1900.common.NWAL1900CommonLogic;
import business.blap.NWAL1900.constant.NWAL1900Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/13   Fujitsu         H.Kumagai       Create          N/A
 * 2018/11/27   Fujitsu         M.Ishii         Update          QC#29361
 * </pre>
 */
public class NWAL1900BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1900CMsg bizMsg = (NWAL1900CMsg) cMsg;
            NWAL1900SMsg glblMsg = (NWAL1900SMsg) sMsg;

            if ("NWAL1900_INIT".equals(screenAplID)) {
                doProcess_NWAL1900_INIT(bizMsg, glblMsg);
            } else if ("NWAL1900Scrn00_PriceSearch".equals(screenAplID)) {
                doProcess_NWAL1900Scrn00_PriceSearch(bizMsg, glblMsg);
            } else if ("NWAL1900Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWAL1900Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NWAL1900Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWAL1900Scrn00_PagePrev(bizMsg, glblMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do Process (Init)
     * @param bizMsg NWAL1900CMsg
     */
    private void doProcess_NWAL1900_INIT(NWAL1900CMsg bizMsg, NWAL1900SMsg glblMsg) {
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        NWAL1900CommonLogic.createPullDownList(getGlobalCompanyCode(), bizMsg);
        NWAL1900CommonLogic.setTempTable(getGlobalCompanyCode(), bizMsg, glblMsg);
        // 2018/11/27 QC#29361 Add Start
        String trxTpCd = NWAL1900CommonLogic.getTrxRuleTp(getGlobalCompanyCode(), bizMsg);
        if(ZYPCommonFunc.hasValue(trxTpCd)){
            ZYPEZDItemValueSetter.setValue(bizMsg.prcGrpTrxTpCd_H1, trxTpCd);
        }
        // 2018/11/27 QC#29361 Add End

        // 2018/11/27 QC#29361 Mod Start
//        search(bizMsg, glblMsg);
        search(bizMsg, glblMsg, NWAL1900CommonLogic.chkMnlAdj(bizMsg, glblMsg));
        // 2018/11/27 QC#29361 Mod End
    }

    private void doProcess_NWAL1900Scrn00_PriceSearch(NWAL1900CMsg bizMsg, NWAL1900SMsg glblMsg) {
        ZYPTableUtil.clear(glblMsg.A);
        // 2018/11/27 QC#29361 Mod Start
//        search(bizMsg, glblMsg);
        search(bizMsg, glblMsg, NWAL1900CommonLogic.chkMnlAdj(bizMsg, glblMsg));
        // 2018/11/27 QC#29361 Mod Start
    }

    /**
     * <pre>
     * Process of screen event NWAL1900Scrn00_PageNext
     * </pre>
     * @param bizMsg Business Component Interface Message
     * @param sharedMsg Global area information
     */
    private void doProcess_NWAL1900Scrn00_PageNext(NWAL1900CMsg bizMsg, NWAL1900SMsg sharedMsg) {

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWAL1900CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, sharedMsg.A);
    }

    /**
     * <pre>
     * Process of screen event NWAL1900Scrn00_PagePrev
     * </pre>
     * @param bizMsg Business Component Interface Message
     * @param sharedMsg Global area information
     */
    private void doProcess_NWAL1900Scrn00_PagePrev(NWAL1900CMsg bizMsg, NWAL1900SMsg sharedMsg) {

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NWAL1900CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, sharedMsg.A);
    }
    // 2018/11/27 QC#29361 Mod Start
//    private void search(NWAL1900CMsg bizMsg, NWAL1900SMsg glblMsg) {
    private void search(NWAL1900CMsg bizMsg, NWAL1900SMsg glblMsg, boolean manualAdj) {
    // 2018/11/27 QC#29361 Mod End
        int cnt = 0;

        ZYPTableUtil.clear(bizMsg.A);

        S21SsmEZDResult rslt = NWAL1900Query.getInstance().getPrcRuleInfo(getGlobalCompanyCode(), bizMsg);
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
        cnt = glblMsg.A.getValidCount();
        for (int i = 0; i < rsltList.size(); i++) {
            Map<String, Object> resultMap = rsltList.get(i);
            NWAL1900_ASMsg aMsg = glblMsg.A.no(cnt);
            ZYPEZDItemValueSetter.setValue(aMsg.prcRuleHdrPk_A, (BigDecimal) resultMap.get("PRC_RULE_HDR_PK"));
            ZYPEZDItemValueSetter.setValue(aMsg.prcRuleNm_A, (String) resultMap.get("PRC_RULE_NM"));
            ZYPEZDItemValueSetter.setValue(aMsg.prcRuleCondTpCd_A, (String) resultMap.get("PRC_RULE_COND_TP_CD"));
            ZYPEZDItemValueSetter.setValue(aMsg.prcRuleCondTpNm_A, (String) resultMap.get("PRC_RULE_COND_TP_NM"));
            if (resultMap.get("APPLY_AUTO_FLG").equals(ZYPConstant.FLG_ON_Y)) {
                ZYPEZDItemValueSetter.setValue(aMsg.xxCmntTxt_A1, (String) FLG_YES);
            } else {
                ZYPEZDItemValueSetter.setValue(aMsg.xxCmntTxt_A1, (String) FLG_NO);
            }
//            ZYPEZDItemValueSetter.setValue(aMsg.applyAutoFlg_A, (String) resultMap.get("APPLY_AUTO_FLG"));
            if (resultMap.get("OVRD_FLG").equals(ZYPConstant.FLG_ON_Y)) {
                ZYPEZDItemValueSetter.setValue(aMsg.xxCmntTxt_A2, (String) FLG_YES);
            } else {
                ZYPEZDItemValueSetter.setValue(aMsg.xxCmntTxt_A2, (String) FLG_NO);
            }
//            ZYPEZDItemValueSetter.setValue(aMsg.ovrdFlg_A, (String) resultMap.get("OVRD_FLG"));
            ZYPEZDItemValueSetter.setValue(aMsg.defRulePrcdNum_A, (BigDecimal) resultMap.get("DEF_RULE_PRCD_NUM"));
            ZYPEZDItemValueSetter.setValue(aMsg.effFromDt_A, (String) resultMap.get("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(aMsg.effThruDt_A, (String) resultMap.get("EFF_THRU_DT"));
            glblMsg.A.setValidCount(++cnt);
        }

        // Matching
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1900_BCMsg row = bizMsg.B.no(i);
            for (int j = 0; j < glblMsg.A.getValidCount(); j++) {
                NWAL1900_ASMsg aMsg = glblMsg.A.no(j);
                if (!ZYPCommonFunc.hasValue(aMsg.prcRuleHdrPk_A.getValue())) {
                    continue;
                }
                if (aMsg.prcRuleHdrPk_A.getValue().compareTo(row.prcRuleHdrPk_B.getValue()) == 0) {
                    if (ZYPConstant.FLG_ON_Y.equals(row.prcRuleApplyFlg_B.getValue())) {
                        ZYPEZDItemValueSetter.setValue(aMsg.xxCmntTxt_A3, NWAL1900Constant.APPLIED);
                    } else {
                        ZYPEZDItemValueSetter.setValue(aMsg.xxCmntTxt_A3, NWAL1900Constant.QUALIFIED);
                    }
                    ZYPEZDItemValueSetter.setValue(aMsg.prcRulePrcdPk_A, row.prcRulePrcdPk_B);
                    ZYPEZDItemValueSetter.setValue(aMsg.prcAdjDtlPk_A, row.prcAdjDtlPk_B);
                    if (ZYPCommonFunc.hasValue(aMsg.prcRulePrcdPk_A)) {
                        String prcRulePrcdActNm = NWAL1900Query.getInstance().getPrcPrcdActNm(getGlobalCompanyCode(), aMsg.prcRulePrcdPk_A.getValue());
                        ZYPEZDItemValueSetter.setValue(aMsg.prcPrcdActTpNm_A, prcRulePrcdActNm);
                    }
                    break;
                }
            }
        }
        if (glblMsg.A.getValidCount() == 0) {
            // 2018/11/27 QC#29361 Mod Start
//            bizMsg.setMessageInfo(NZZM0000E);
//            bizMsg.xxPageShowFromNum.clear();
//            bizMsg.xxPageShowToNum.clear();
//            bizMsg.xxPageShowOfNum.clear();
            if (manualAdj) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).prcRuleNm_A, NWAL1900Constant.MANUAL_ADJUSTMENT);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).xxCmntTxt_A3, NWAL1900Constant.APPLIED);
                bizMsg.A.setValidCount(1);
                bizMsg.xxPageShowFromNum.setValue(1);
                bizMsg.xxPageShowToNum.setValue(1);
                bizMsg.xxPageShowOfNum.setValue(1);
            } else {
                bizMsg.setMessageInfo(NZZM0000E);
                bizMsg.xxPageShowFromNum.clear();
                bizMsg.xxPageShowToNum.clear();
                bizMsg.xxPageShowOfNum.clear();
            }
            // 2018/11/27 QC#29361 Mod End
            return;
        }
       // Search results copy
        int i = 0;
        int vCount = 0;
        // 2018/11/27 QC#29361 Mod Start
//        for (; i < glblMsg.A.getValidCount(); i++) {
//            if (i == bizMsg.A.length()) {
//                break;
//            }
//            EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
//            bizMsg.A.setValidCount(++vCount);
//        }
        if (manualAdj) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).prcRuleNm_A, NWAL1900Constant.MANUAL_ADJUSTMENT);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).xxCmntTxt_A3, NWAL1900Constant.APPLIED);
            vCount++;
            for (; i < glblMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i + 1), null);
                bizMsg.A.setValidCount(++vCount);
            }
        } else {
            for (; i < glblMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
                bizMsg.A.setValidCount(++vCount);
            }
        }
        // 2018/11/27 QC#29361 Mod End
        bizMsg.xxPageShowFromNum.setValue(1);
        bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        // 2018/11/27 QC#29361 Mod Start
//        bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());
        if (manualAdj) {
            bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount() + 1);
        } else {
            bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());
        }
        // 2018/11/27 QC#29361 Mod End
    }
}
