/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7290;

import static business.blap.NMAL7290.constant.NMAL7290Constant.CHKBOX_A;
import static business.blap.NMAL7290.constant.NMAL7290Constant.CHKBOX_B;
import static business.blap.NMAL7290.constant.NMAL7290Constant.CSV_EXT;
import static business.blap.NMAL7290.constant.NMAL7290Constant.CSV_FILE_NAME;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_AUTO;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_BIZ;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_CATG;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_CREATED_BY;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_CREATED_DATE;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_DEF;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_EFF_FROM;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_EFF_FROM_H;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_EFF_TO;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_EFF_TO_H;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_GRP_ACT;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_GRP_NM;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_GRP_NUM;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_ID;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_OVR;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_RULE_NM;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_SEQ;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_STS;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_TP;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_UPLOADED_BY;
import static business.blap.NMAL7290.constant.NMAL7290Constant.HEADER_UPLOADED_DATE;
import static business.blap.NMAL7290.constant.NMAL7290Constant.NZZM0000E;
import static business.blap.NMAL7290.constant.NMAL7290Constant.NZZM0001W;
import static business.blap.NMAL7290.constant.NMAL7290Constant.TBL_PRC_RULE_EVTR_TP;
import static business.blap.NMAL7290.constant.NMAL7290Constant.TBL_PRC_RULE_OP_TP;
import static business.blap.NMAL7290.constant.NMAL7290Constant.ZZZM9006E;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7290.common.NMAL7290CommonLogic;
import business.db.PRC_RULE_EVTR_TPTMsg;
import business.db.PRC_RULE_EVTR_TPTMsgArray;
import business.db.PRC_RULE_OP_TPTMsg;
import business.db.PRC_RULE_OP_TPTMsgArray;
import business.file.NMAL7290F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7290BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   Fujitsu         C.Tanaka        Create          N/A
 * 2016/09/27   Fujitsu         R.Nakamura      Update          QC#6931
 * 2016/11/08   Fujitsu         T.Murai         Update          QC#6599
 * 2018/08/31   Fujitsu         H.Kumagai       Update          QC#27892
 *</pre>
 */
public class NMAL7290BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7290CMsg bizMsg = (NMAL7290CMsg) cMsg;
            NMAL7290SMsg glblMsg = (NMAL7290SMsg) sMsg;

            if ("NMAL7290_INIT".equals(screenAplID)) {
                doProcess_NMAL7290_INIT(bizMsg, glblMsg);

            } else if ("NMAL7290Scrn00_Add_PrcRulePrcd".equals(screenAplID)) {
                doProcess_NMAL7290Scrn00_Add_PrcRulePrcd(bizMsg, glblMsg);

            } else if ("NMAL7290Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7290Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NMAL7290Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7290Scrn00_CMN_Clear(bizMsg, glblMsg);

            // QC#9694 2016/07/08 Mod Start
            } else if ("NMAL7290Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NMAL7290Scrn00_CMN_Delete(bizMsg, glblMsg);
            // QC#9694 2016/07/08 Mod End

            } else if ("NMAL7290Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7290Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL7290Scrn00_Del_PrcRulePrcd".equals(screenAplID)) {
                doProcess_NMAL7290Scrn00_Del_PrcRulePrcd(bizMsg, glblMsg);

            } else if ("NMAL7290Scrn00_Rmv_PrcRulePrcd".equals(screenAplID)) {
                doProcess_NMAL7290Scrn00_Rmv_PrcRulePrcd(bizMsg, glblMsg);

            } else if ("NMAL7290Scrn00_Search_PrcRule".equals(screenAplID)) {
                doProcess_NMAL7290Scrn00_Search_PrcRule(bizMsg, glblMsg);

            } else if ("NMAL7290Scrn00_Search_PrcRulePrcd".equals(screenAplID)) {
                doProcess_NMAL7290Scrn00_Search_PrcRulePrcd(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7290_INIT(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {
        // QC#8671 2016/05/25 Add Start
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        bizMsg.B.clear();
        bizMsg.B.setValidCount(0);
        glblMsg.X.clear();
        glblMsg.X.setValidCount(0);
        // QC#8671 2016/05/25 Add End
        NMAL7290CommonLogic.createPullDown(bizMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.actvFlg, ZYPConstant.FLG_ON_Y); // QC#9694 2016/07/08 Add
        ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode())); // QC#9694 2016/07/08 Add

//        if (ZYPCommonFunc.hasValue(bizMsg.prcRulePrcdGrpNum)) {
//            searchPrcRulePrcd(bizMsg, glblMsg, true);
//        }
        // QC#9694 2016/07/08 Mod Start
        if (ZYPCommonFunc.hasValue(bizMsg.prcRulePrcdPk)) {
            searchPrcRulePrcd(bizMsg, glblMsg, true);
        }
        bizMsg.xxWrnSkipFlg.setValue(ZYPConstant.FLG_OFF_0);
        // QC#9694 2016/07/08 Mod End
    }

    /**
     * Add_PrcRulePrcd Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7290Scrn00_Add_PrcRulePrcd(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.A, CHKBOX_A, ZYPConstant.CHKBOX_ON_Y);

        int lg = bizMsg.B.getValidCount();
        String actvFlg = null;
        String effFromDt = null;
        String effThruDt = null;
        NMAL7290_ACMsg aCMsg = null;
        NMAL7290_BCMsg bCMsg = null;

        for (int idx : checkList) {
            aCMsg = bizMsg.A.no(idx);
            bCMsg = bizMsg.B.no(lg);
            actvFlg = aCMsg.actvFlg_A.getValue();
            effFromDt = aCMsg.effFromDt_A.getValue();
            effThruDt = aCMsg.effThruDt_A.getValue();

            ZYPEZDItemValueSetter.setValue(bCMsg.prcRuleHdrPk_B, aCMsg.prcRuleHdrPk_A);
            ZYPEZDItemValueSetter.setValue(bCMsg.prcRuleNm_B, aCMsg.prcRuleNm_A);
            ZYPEZDItemValueSetter.setValue(bCMsg.prcRuleCondTpCd_B, aCMsg.prcRuleCondTpCd_A);
            ZYPEZDItemValueSetter.setValue(bCMsg.prcRuleCondTpDescTxt_B, aCMsg.prcRuleCondTpDescTxt_A);
            ZYPEZDItemValueSetter.setValue(bCMsg.lineBizTpCd_B, aCMsg.lineBizTpCd_A);
            ZYPEZDItemValueSetter.setValue(bCMsg.lineBizTpDescTxt_B, aCMsg.lineBizTpDescTxt_A);
            ZYPEZDItemValueSetter.setValue(bCMsg.prcRuleCatgCd_B, aCMsg.prcRuleCatgCd_A);
            ZYPEZDItemValueSetter.setValue(bCMsg.prcRuleCatgDescTxt_B, aCMsg.prcRuleCatgDescTxt_A);
            ZYPEZDItemValueSetter.setValue(bCMsg.applyAutoFlg_B, aCMsg.applyAutoFlg_A);
            ZYPEZDItemValueSetter.setValue(bCMsg.ovrdFlg_B, aCMsg.ovrdFlg_A);
            ZYPEZDItemValueSetter.setValue(bCMsg.actvFlg_B, actvFlg);
            ZYPEZDItemValueSetter.setValue(bCMsg.defRulePrcdNum_B, aCMsg.defRulePrcdNum_A);
            ZYPEZDItemValueSetter.setValue(bCMsg.effFromDt_B, effFromDt);
            ZYPEZDItemValueSetter.setValue(bCMsg.effThruDt_B, effThruDt);

            ZYPEZDItemValueSetter.setValue(bCMsg.xxScrItem8Txt_B, NMAL7290CommonLogic.getStatus(actvFlg, effFromDt, effThruDt));
            lg++;
        }

        bizMsg.B.setValidCount(lg);
        ZYPTableUtil.deleteRows(bizMsg.A, checkList);
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7290Scrn00_CMN_Download(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        PRC_RULE_OP_TPTMsg prcRuleOpTp = new PRC_RULE_OP_TPTMsg();
        prcRuleOpTp.setSQLID("001");
        prcRuleOpTp.setConditionValue("glblCmpyCd01", glblCmpyCd);
        PRC_RULE_OP_TPTMsgArray prcRuleOpTpArray = (PRC_RULE_OP_TPTMsgArray) EZDTBLAccessor.findByCondition(prcRuleOpTp);

        if (prcRuleOpTpArray.length() == 0) {
            bizMsg.setMessageInfo(ZZZM9006E, new String[] {TBL_PRC_RULE_OP_TP });
            return;
        }

        Map<String, String> opTpMap = new HashMap<String, String>();
        PRC_RULE_OP_TPTMsg opTpTMsg = null;
        for (int i = 0; i < prcRuleOpTpArray.length(); i++) {
            opTpTMsg = prcRuleOpTpArray.no(i);
            opTpMap.put(opTpTMsg.prcRuleOpTpCd.getValue(), opTpTMsg.prcRuleOpTpDescTxt.getValue());
        }

        PRC_RULE_EVTR_TPTMsg prcRuleEvtrTp = new PRC_RULE_EVTR_TPTMsg();
        prcRuleEvtrTp.setSQLID("001");
        prcRuleEvtrTp.setConditionValue("glblCmpyCd01", glblCmpyCd);
        PRC_RULE_EVTR_TPTMsgArray prcRuleEvtrTpArray = (PRC_RULE_EVTR_TPTMsgArray) EZDTBLAccessor.findByCondition(prcRuleEvtrTp);

        if (prcRuleEvtrTpArray.length() == 0) {
            bizMsg.setMessageInfo(ZZZM9006E, new String[] {TBL_PRC_RULE_EVTR_TP });
            return;
        }

        Map<String, String> evtrTpMap = new HashMap<String, String>();
        PRC_RULE_EVTR_TPTMsg evtrTptMsg = null;
        for (int i = 0; i < prcRuleEvtrTpArray.length(); i++) {
            evtrTptMsg = prcRuleEvtrTpArray.no(i);
            evtrTpMap.put(evtrTptMsg.prcRuleEvtrTpCd.getValue(), evtrTptMsg.prcRuleEvtrTpDescTxt.getValue());
        }

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV_EXT);

        NMAL7290F00FMsg fMsg = new NMAL7290F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        // Mod Start 2016/09/27 QC#6931
//        csvOutFile.writeHeader(new String[] {HEADER_GRP_NUM, HEADER_GRP_NM, HEADER_SEQ, HEADER_OP, HEADER_EVLV, HEADER_ID, HEADER_RULE_NM, HEADER_TP, HEADER_BIZ, HEADER_CATG, HEADER_AUTO, HEADER_OVR, HEADER_STS, HEADER_DEF,
//                HEADER_EFF_FROM, HEADER_EFF_TO });
        csvOutFile.writeHeader(new String[] {HEADER_GRP_NUM, HEADER_GRP_NM , HEADER_GRP_ACT, HEADER_EFF_FROM_H, HEADER_EFF_TO_H, HEADER_SEQ, HEADER_ID, HEADER_RULE_NM, HEADER_TP, HEADER_BIZ, HEADER_CATG, HEADER_AUTO, HEADER_OVR , //
                HEADER_STS, HEADER_DEF, HEADER_EFF_FROM, HEADER_EFF_TO, HEADER_CREATED_BY, HEADER_CREATED_DATE, HEADER_UPLOADED_BY, HEADER_UPLOADED_DATE });
        
        // Mod End 2016/09/27 QC#6931

        // Mod Start 2018/08/31 QC#27892
        BigDecimal prcRulePrcdPk = null;
        // Mod End 2018/08/31 QC#27892
        String prcRulePrcdGrpNm = "";
        // QC#9694 2016/07/08 Mod Start
        //if (ZYPCommonFunc.hasValue(bizMsg.prcRulePrcdGrpNum)) {
        //    prcRulePrcdGrpNum = bizMsg.prcRulePrcdGrpNum.getValue();
        //}
        if (ZYPCommonFunc.hasValue(bizMsg.prcRulePrcdPk)) {
            prcRulePrcdPk = bizMsg.prcRulePrcdPk.getValue();
        }
        // QC#9694 2016/07/08 Mod End
        if (ZYPCommonFunc.hasValue(bizMsg.prcRulePrcdGrpNm)) {
            prcRulePrcdGrpNm = bizMsg.prcRulePrcdGrpNm.getValue();
        }

        // Add Start 2016/09/27 QC#6931
        String prcPrcdActTpDescTxt = null;
        S21SsmEZDResult ssmResult = NMAL7290Query.getInstance().getAnyColmn("PRC_PRCD_ACT_TP_DESC_TXT", "PRC_PRCD_ACT_TP", "PRC_PRCD_ACT_TP_CD", bizMsg.prcPrcdActTpCd.getValue());
        if (ssmResult.isCodeNormal()) {
            prcPrcdActTpDescTxt = (String) ssmResult.getResultObject();
        }

        String headEffFromDt = NMAL7290CommonLogic.convertDateFormat(bizMsg.effFromDt.getValue());
        String headEffThruDt = NMAL7290CommonLogic.convertDateFormat(bizMsg.effThruDt.getValue());
        String headCratDt = NMAL7290CommonLogic.convertDateFormat(bizMsg.cratDt.getValue());
        String headLastUpdDt = NMAL7290CommonLogic.convertDateFormat(bizMsg.lastUpdDt.getValue());
        // Add End 2016/09/27 QC#6931

        NMAL7290_BCMsg bCMsg = null;
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            bCMsg = bizMsg.B.no(i);
            fMsg.clear();

            // Add Start 2016/09/27 QC#6931
            NMAL7290CommonLogic.setStringItem(fMsg.prcPrcdActTpDescTxt, prcPrcdActTpDescTxt);
            NMAL7290CommonLogic.setStringItem(fMsg.xxDtTxt_H1, headEffFromDt);
            NMAL7290CommonLogic.setStringItem(fMsg.xxDtTxt_H2, headEffThruDt);
            NMAL7290CommonLogic.setStringItem(fMsg.xxDtTxt_B1, NMAL7290CommonLogic.convertDateFormat(bizMsg.B.no(i).effFromDt_B.getValue()));
            NMAL7290CommonLogic.setStringItem(fMsg.xxDtTxt_B2, NMAL7290CommonLogic.convertDateFormat(bizMsg.B.no(i).effThruDt_B.getValue()));
            NMAL7290CommonLogic.setStringItem(fMsg.xxDtTxt_B3, headCratDt);
            NMAL7290CommonLogic.setStringItem(fMsg.xxDtTxt_B4, headLastUpdDt);
            NMAL7290CommonLogic.setStringItem(fMsg.xxFullNm_B1, bizMsg.xxFullNm_H1.getValue());
            NMAL7290CommonLogic.setStringItem(fMsg.xxFullNm_B2, bizMsg.xxFullNm_H2.getValue());
            // Add End 2016/09/27 QC#6931

            // Mod Start 2018/08/31 QC#27892
            fMsg.prcRulePrcdPk.setValue(prcRulePrcdPk);
            // Mod End 2018/08/31 QC#27892
            fMsg.prcRulePrcdGrpNm.setValue(prcRulePrcdGrpNm);
            // QC#9694 2016/07/08 Del Start
            //if (ZYPCommonFunc.hasValue(bCMsg.prcRuleOpTpCd_B)) {
            //    fMsg.prcRuleOpTpDescTxt_B.setValue(opTpMap.get(bCMsg.prcRuleOpTpCd_B.getValue()));
            //}
            //if (ZYPCommonFunc.hasValue(bCMsg.prcRuleEvtrTpCd_B)) {
            //    fMsg.prcRuleEvtrTpDescTxt_B.setValue(evtrTpMap.get(bCMsg.prcRuleEvtrTpCd_B.getValue()));
            //}
            // QC#9694 2016/07/08 Del Start
            EZDMsg.copy(bCMsg, null, fMsg, null);
            csvOutFile.write();
        }

        csvOutFile.close();

    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7290Scrn00_CMN_Clear(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {

        bizMsg.clear();
        glblMsg.clear();
        bizMsg.A.setValidCount(0);
        bizMsg.B.setValidCount(0);
        glblMsg.A.setValidCount(0);
        glblMsg.B.setValidCount(0);
        doProcess_NMAL7290_INIT(bizMsg, glblMsg);
    }

    // QC#9694 2016/07/08 Mod Start
    /**
     * CMN_Delete Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7290Scrn00_CMN_Delete(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {
        if (ZYPConstant.FLG_ON_1.equals(bizMsg.xxWrnSkipFlg.getValue())) {
            return;
        }
        doProcess_NMAL7290Scrn00_CMN_Clear(bizMsg, glblMsg);
    }
    // QC#9694 2016/07/08 Mod End

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7290Scrn00_CMN_Submit(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {

        glblMsg.X.clear();
        glblMsg.X.setValidCount(0);
        // QC#8671 2016/05/25 Mod Start
        // doProcess_NMAL7290_INIT(bizMsg, glblMsg);
        NMAL7290CommonLogic.createPullDown(bizMsg);

        // QC#9694 2016/07/08 Add Start
        // if (ZYPCommonFunc.hasValue(bizMsg.prcRulePrcdGrpNum)) {
        //     searchPrcRulePrcd(bizMsg, glblMsg, false);
        // }
        if (ZYPCommonFunc.hasValue(bizMsg.prcRulePrcdPk)) {
            searchPrcRulePrcd(bizMsg, glblMsg, false);
        }
        // QC#8671 2016/05/25 Mod End
        // QC#9694 2016/07/08 Mod END
    }

    /**
     * Del_PrcRulePrcd Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7290Scrn00_Del_PrcRulePrcd(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.B, CHKBOX_B, ZYPConstant.CHKBOX_ON_Y);

        int cnt = glblMsg.X.getValidCount();
        for (int idx : checkList) {
            if (ZYPCommonFunc.hasValue(bizMsg.B.no(idx).prcRulePrcdDtlPk_B)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.X.no(cnt).prcRulePrcdDtlPk_X, bizMsg.B.no(idx).prcRulePrcdDtlPk_B);
                cnt++;
            }
        }
        glblMsg.X.setValidCount(cnt);

        ZYPTableUtil.deleteRows(bizMsg.B, checkList);
    }

    /**
     * Rmv_PrcRulePrcd Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7290Scrn00_Rmv_PrcRulePrcd(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.B, CHKBOX_B, ZYPConstant.CHKBOX_ON_Y);

        int lg = bizMsg.A.getValidCount();
        NMAL7290_ACMsg aCMsg = null;
        NMAL7290_BCMsg bCMsg = null;

        for (int idx : checkList) {
            aCMsg = bizMsg.A.no(lg);
            bCMsg = bizMsg.B.no(idx);

            ZYPEZDItemValueSetter.setValue(aCMsg.prcRuleHdrPk_A, bCMsg.prcRuleHdrPk_B);
            ZYPEZDItemValueSetter.setValue(aCMsg.prcRuleNm_A, bCMsg.prcRuleNm_B);
            ZYPEZDItemValueSetter.setValue(aCMsg.prcRuleCondTpCd_A, bCMsg.prcRuleCondTpCd_B);
            ZYPEZDItemValueSetter.setValue(aCMsg.prcRuleCondTpDescTxt_A, bCMsg.prcRuleCondTpDescTxt_B);
            ZYPEZDItemValueSetter.setValue(aCMsg.lineBizTpCd_A, bCMsg.lineBizTpCd_B);
            ZYPEZDItemValueSetter.setValue(aCMsg.lineBizTpDescTxt_A, bCMsg.lineBizTpDescTxt_B);
            ZYPEZDItemValueSetter.setValue(aCMsg.prcRuleCatgCd_A, bCMsg.prcRuleCatgCd_B);
            ZYPEZDItemValueSetter.setValue(aCMsg.prcRuleCatgDescTxt_A, bCMsg.prcRuleCatgDescTxt_B);
            ZYPEZDItemValueSetter.setValue(aCMsg.applyAutoFlg_A, bCMsg.applyAutoFlg_B);
            ZYPEZDItemValueSetter.setValue(aCMsg.ovrdFlg_A, bCMsg.ovrdFlg_B);
            ZYPEZDItemValueSetter.setValue(aCMsg.actvFlg_A, bCMsg.actvFlg_B);
            ZYPEZDItemValueSetter.setValue(aCMsg.defRulePrcdNum_A, bCMsg.defRulePrcdNum_B);
            ZYPEZDItemValueSetter.setValue(aCMsg.effFromDt_A, bCMsg.effFromDt_B);
            ZYPEZDItemValueSetter.setValue(aCMsg.effThruDt_A, bCMsg.effThruDt_B);

            lg++;
        }

        bizMsg.A.setValidCount(lg);
        ZYPTableUtil.deleteRows(bizMsg.B, checkList);
    }

    /**
     * Search_PrcRule Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7290Scrn00_Search_PrcRule(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        searchPrcRule(bizMsg, glblMsg);
    }

    /**
     * Search_PrcRulePrcd Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7290Scrn00_Search_PrcRulePrcd(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {

        bizMsg.B.clear();
        bizMsg.B.setValidCount(0);
        // QC#8671 2016/05/25 Add Start
        glblMsg.X.clear();
        glblMsg.X.setValidCount(0);
        // QC#8671 2016/05/25 Add End

        searchPrcRulePrcd(bizMsg, glblMsg, true);
    }

    private void searchPrcRule(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg) {
        S21SsmEZDResult ssmResult = NMAL7290Query.getInstance().searchPrcRule(bizMsg, glblMsg);
        if (ssmResult.isCodeNormal()) {
            if (ssmResult.getQueryResultCount() > bizMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
            }
            // EZDMsg.copy(glblMsg.A, null, bizMsg.A, null);// QC#9694 2016/07/08 Del
        } else {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }
    }

    private void searchPrcRulePrcd(NMAL7290CMsg bizMsg, NMAL7290SMsg glblMsg, boolean serchFlg) {
        // QC#9694 2016/07/08 Mod Start
        bizMsg.xxWrnSkipFlg.setValue(ZYPConstant.FLG_OFF_0);
        S21SsmEZDResult ssmResultHdr = NMAL7290Query.getInstance().searchPrcRulePrcdHdr(bizMsg, glblMsg);
        if (ssmResultHdr.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRulePrcdPk_BK, bizMsg.prcRulePrcdPk);
        }else{
            if(serchFlg){
                bizMsg.setMessageInfo(NZZM0000E);
            }
            return;
        }
        
        // QC#9694 2016/07/08 Mod End
        S21SsmEZDResult ssmResult = NMAL7290Query.getInstance().searchPrcRulePrcd(bizMsg, glblMsg);
        if (ssmResult.isCodeNormal()) {
            if (ssmResult.getQueryResultCount() > bizMsg.B.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
            }
            // EZDMsg.copy(glblMsg.B, null, bizMsg.B, null);// QC#9694 2016/07/08 Del

            NMAL7290_BCMsg bCMsg = null;
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                bCMsg = bizMsg.B.no(i);
                ZYPEZDItemValueSetter.setValue(bCMsg.xxScrItem8Txt_B, NMAL7290CommonLogic.getStatus(bCMsg.actvFlg_B.getValue(), bCMsg.effFromDt_B.getValue(), bCMsg.effThruDt_B.getValue()));

                // START 2016/11/07 [QC#6599, ADD]
                    String cratNm = bCMsg.xxRecHistCratByNm_B.getValue();
                    String updNm = bCMsg.xxRecHistUpdByNm_B.getValue();
                    ZYPEZDItemValueSetter.setValue(bCMsg.xxRecHistCratByNm_B, ZYPRecHistUtil.getFullNameForRecHist(cratNm));
                    ZYPEZDItemValueSetter.setValue(bCMsg.xxRecHistUpdByNm_B, ZYPRecHistUtil.getFullNameForRecHist(updNm));
                // END 2016/11/07 [QC#6599, ADD]
            }

            // QC#9694 2016/07/08 Del Start
            //bCMsg = bizMsg.B.no(0);
            //ZYPEZDItemValueSetter.setValue(bizMsg.prcRulePrcdPk, bCMsg.prcRulePrcdPk_B);
            //ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime, bCMsg.ezUpTime_B);
            //ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone, bCMsg.ezUpTimeZone_B);
            //ZYPEZDItemValueSetter.setValue(bizMsg.prcRulePrcdGrpNum, bCMsg.prcRulePrcdGrpNum_B);
            //ZYPEZDItemValueSetter.setValue(bizMsg.prcRulePrcdGrpNm, bCMsg.prcRulePrcdGrpNm_B);

            //ZYPEZDItemValueSetter.setValue(glblMsg.prcRulePrcdGrpNum, bizMsg.prcRulePrcdGrpNum);
            //ZYPEZDItemValueSetter.setValue(glblMsg.prcRulePrcdGrpNm, bizMsg.prcRulePrcdGrpNm);
            // QC#9694 2016/07/08 Del End

        } else {
            // QC#8671 2016/05/25 Mod Start
            // bizMsg.setMessageInfo(NZZM0000E);
            if(serchFlg){
                bizMsg.setMessageInfo(NZZM0000E);
            }
            // QC#8671 2016/05/25 Mod End
            return;
        }
    }

}
