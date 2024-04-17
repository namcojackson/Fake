/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7270;

import static business.blap.NMAL7270.common.NMAL7270CommonLogic.toStr;
import static business.blap.NMAL7270.common.NMAL7270CommonLogic.formatAmount;
import static business.blap.NMAL7270.constant.NMAL7270Constant.CSV_DOWNLOAD_HEADER_ADJ_DTL;
import static business.blap.NMAL7270.constant.NMAL7270Constant.CSV_DOWNLOAD_HEADER_TRX_COND;
import static business.blap.NMAL7270.constant.NMAL7270Constant.CSV_FILE_EXTENSION;
import static business.blap.NMAL7270.constant.NMAL7270Constant.CSV_FILE_NM_ADJ_DTL;
import static business.blap.NMAL7270.constant.NMAL7270Constant.CSV_FILE_NM_TRX_COND;
import static business.blap.NMAL7270.constant.NMAL7270Constant.MODE_OPENWIN_PRC;
import static business.blap.NMAL7270.constant.NMAL7270Constant.MODE_SEARCH;
import static business.blap.NMAL7270.constant.NMAL7270Constant.MODE_SUBMIT;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NMAM0050E;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NMAM0163E;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NUM_100;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NZZM0000E;
import static business.blap.NMAL7270.constant.NMAL7270Constant.NZZM0002I;
import static business.blap.NMAL7270.constant.NMAL7270Constant.PERCENT_DIGIT;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7270.common.NMAL7270CommonLogic;
import business.blap.NMAL7270.constant.NMAL7270Constant;
import business.db.PRC_RULE_ATTRBTMsg;
import business.db.PRC_RULE_ATTRBTMsgArray;
import business.db.PRC_RULE_CATGTMsg;
import business.file.NMAL7270F00FMsg;
import business.file.NMAL7270F01FMsg;

import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceMasterUtil;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ADJ_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ADJ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_MOD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_TRX_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7270BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 * 2016/04/06   Fujitsu         Y.Kanefusa      Update          QC#6397
 * 2016/07/08   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 * 2016/08/24   Fujitsu         R.Nakamura      Update          S21_NA#3934
 * 2016/11/08   Fujitsu         T.Murai         Update          S21_NA#6599
 * 2017/09/01   Fujitsu         R.Nakamura      Update          S21_NA#20729-2
 * 2018/09/12   Fujitsu         M.Ohno          Upadte          QC#9700
 *</pre>
 */
public class NMAL7270BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7270CMsg bizMsg = (NMAL7270CMsg) cMsg;

            // Mod Start 2016/08/24 QC#3934
            if ("NMAL7270_INIT".equals(screenAplID)) {
                doProcess_NMAL7270_INIT(bizMsg);

            } else if ("NMAL7270Scrn00_Add_PrcAdjDtl".equals(screenAplID)) {
                doProcess_NMAL7270Scrn00_Add_PrcAdjDtl(bizMsg);

            } else if ("NMAL7270Scrn00_Add_TrxCond".equals(screenAplID)) {
                doProcess_NMAL7270Scrn00_Add_TrxCond(bizMsg);

            } else if ("NMAL7270Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7270Scrn00_CMN_Submit(bizMsg);

            } else if ("NMAL7270Scrn00_Download_PrcAdjDtl".equals(screenAplID)) {
                doProcess_NMAL7270Scrn00_Download_PrcAdjDtl(bizMsg);

            } else if ("NMAL7270Scrn00_Download_TrxCond".equals(screenAplID)) {
                doProcess_NMAL7270Scrn00_Download_TrxCond(bizMsg);

            } else if ("NMAL7270Scrn00_OnChange_Category".equals(screenAplID)) {
                doProcess_NMAL7270Scrn00_OnChange_Category(bizMsg);

            } else if ("NMAL7270Scrn00_OpenWin_CondGrpRules".equals(screenAplID)) {
                doProcess_NMAL7270Scrn00_OpenWin_CondGrpRules(bizMsg);

            } else if ("NMAL7270Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL7270Scrn00_Search(bizMsg);

            } else if ("NMAL7270Scrn00_OnBlur_Setting_Name".equals(screenAplID)) {
                doProcess_NMAL7270Scrn00_OnBlur_Setting_Name(bizMsg);

            } else {
                return;
            }
            // Mod Start 2016/08/24 QC#3934
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7270_INIT(NMAL7270CMsg bizMsg) {

        // Create Pull Down.
        createPullDown(bizMsg);

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode()); // QC#9694 2016/06/28 Add 
        ZYPEZDItemValueSetter.setValue(bizMsg.slsDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));

        if (ZYPCommonFunc.hasValue(bizMsg.prcRuleHdrPk_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleHdrPk_H1, bizMsg.prcRuleHdrPk_BK);
            doProcess_NMAL7270Scrn00_Search(bizMsg);
        } else {
            // Initial Data Setup
            ZYPEZDItemValueSetter.setValue(bizMsg.applyAutoFlg_H1, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(bizMsg.actvFlg_H1, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_H1, bizMsg.slsDt);

            ZYPTableUtil.clear(bizMsg.A);
            ZYPTableUtil.clear(bizMsg.B);
        }
    }

    /**
     * Add_PrcAdjDtl Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7270Scrn00_Add_PrcAdjDtl(NMAL7270CMsg bizMsg) {
        if (bizMsg.B.getValidCount() >= bizMsg.B.length()) {
            bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(bizMsg.B.length()) });
        }

        int curIdx = bizMsg.B.getValidCount();

        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(curIdx).prcRuleAttrbCd_B1, bizMsg.prcRuleAttrbCd_LB.no(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(curIdx).effFromDt_B1, bizMsg.slsDt);

        bizMsg.B.setValidCount(curIdx + 1);

    }

    /**
     * Add_TrxCond Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7270Scrn00_Add_TrxCond(NMAL7270CMsg bizMsg) {
        if (bizMsg.A.getValidCount() >= bizMsg.A.length()) {
            bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(bizMsg.A.length()) });
        }

        int curIdx = bizMsg.A.getValidCount();

        if (curIdx != 0) {
            BigDecimal nowMax = bizMsg.A.no(curIdx - 1).prcRuleCondNum_A1.getValue();
            if (nowMax.intValue() >= bizMsg.A.length()) {
                bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(bizMsg.A.length()) });
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(curIdx).prcRuleCondNum_A1, nowMax.add(BigDecimal.ONE));
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(curIdx).prcRuleCondNum_A1, BigDecimal.ONE);
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(curIdx).prcRuleTrxCatgCd_A1, PRC_RULE_TRX_CATG.ORDER);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(curIdx).prcRuleAttrbCd_A1, bizMsg.prcRuleAttrbCd_LA.no(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(curIdx).effFromDt_A1, bizMsg.slsDt);

        bizMsg.A.setValidCount(curIdx + 1);
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7270Scrn00_CMN_Submit(NMAL7270CMsg bizMsg) {
        search(bizMsg, MODE_SEARCH);
    }

    /**
     * Download_PrcAdjDtl Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7270Scrn00_Download_PrcAdjDtl(NMAL7270CMsg bizMsg) {

        bizMsg.xxFileData.clear();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NM_ADJ_DTL), CSV_FILE_EXTENSION);
        NMAL7270F01FMsg fMsg = new NMAL7270F01FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER_ADJ_DTL);
        S21SsmEZDResult ssmResult = null;

        // Add Start 2016/09/27 QC#6931
        String lineBizTpDescTxt = null;
        String prcRuleCatgDescTxt = null;
        ssmResult = NMAL7270Query.getInstance().getAnyColmn("LINE_BIZ_TP_DESC_TXT", "LINE_BIZ_TP", "LINE_BIZ_TP_CD", bizMsg.lineBizTpCd_H1.getValue());
        if (ssmResult.isCodeNormal()) {
            lineBizTpDescTxt = (String) ssmResult.getResultObject();
        }

        ssmResult = NMAL7270Query.getInstance().getAnyColmn("PRC_RULE_CATG_DESC_TXT", "PRC_RULE_CATG", "PRC_RULE_CATG_CD", bizMsg.prcRuleCatgCd_H1.getValue());
        if (ssmResult.isCodeNormal()) {
            prcRuleCatgDescTxt = (String) ssmResult.getResultObject();
        }

        String headEffFromDt = NMAL7270CommonLogic.convertDateFormat(bizMsg.effFromDt_H1.getValue());
        String headEffThruDt = NMAL7270CommonLogic.convertDateFormat(bizMsg.effThruDt_H1.getValue());
        String headCratDt = NMAL7270CommonLogic.convertDateFormat(bizMsg.cratDt_H1.getValue());
        String headLastUpdDt = NMAL7270CommonLogic.convertDateFormat(bizMsg.lastUpdDt_H1.getValue());
        // Add End 2016/09/27 QC#6931

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {

            // Add Start 2016/09/27 QC#6931
            EZDMsg.copy(bizMsg, null, fMsg, null);

            NMAL7270CommonLogic.setStringItem(fMsg.lineBizTpDescTxt_H1, lineBizTpDescTxt);
            NMAL7270CommonLogic.setStringItem(fMsg.prcRuleCatgDescTxt_H1, prcRuleCatgDescTxt);
            NMAL7270CommonLogic.setStringItem(fMsg.xxDtTxt_H1, headEffFromDt);
            NMAL7270CommonLogic.setStringItem(fMsg.xxDtTxt_H2, headEffThruDt);
            NMAL7270CommonLogic.setStringItem(fMsg.xxDtTxt_H3, headCratDt);
            NMAL7270CommonLogic.setStringItem(fMsg.xxDtTxt_H4, headLastUpdDt);
            NMAL7270CommonLogic.setStringItem(fMsg.xxDtTxt_B1, NMAL7270CommonLogic.convertDateFormat(bizMsg.B.no(i).effFromDt_B1.getValue()));
            NMAL7270CommonLogic.setStringItem(fMsg.xxDtTxt_B2, NMAL7270CommonLogic.convertDateFormat(bizMsg.B.no(i).effThruDt_B1.getValue()));
            NMAL7270CommonLogic.setStringItem(fMsg.xxDtTxt_B3, NMAL7270CommonLogic.convertDateFormat(bizMsg.B.no(i).cratDt_B1.getValue()));
            NMAL7270CommonLogic.setStringItem(fMsg.xxDtTxt_B4, NMAL7270CommonLogic.convertDateFormat(bizMsg.B.no(i).lastUpdDt_B1.getValue()));
            // Add End 2016/09/27 QC#6931

            EZDMsg.copy(bizMsg.B.no(i), null, fMsg, null);

            // Value
            if (PRC_RULE_ATTRB.FORMULA.equals(bizMsg.B.no(i).prcRuleAttrbCd_B1.getValue())) {
                ZYPEZDItemValueSetter.setValue(fMsg.prcRuleDtlTxt_B1, toStr(bizMsg.B.no(i).prcFmlaPk_B1.getValue()));
            } else if (PRC_RULE_ATTRB.PERCENT.equals(bizMsg.B.no(i).prcRuleAttrbCd_B1.getValue())) {
                ZYPEZDItemValueSetter.setValue(fMsg.prcRuleDtlTxt_B1, toStr(bizMsg.B.no(i).prcRuleDtlRate_B1.getValue()));
            } else if (PRC_RULE_ATTRB.VALUE.equals(bizMsg.B.no(i).prcRuleAttrbCd_B1.getValue())) {
                ZYPEZDItemValueSetter.setValue(fMsg.prcRuleDtlTxt_B1, bizMsg.B.no(i).prcRuleDtlTxt_B1);
            }

            ssmResult = NMAL7270Query.getInstance().getAnyColmn("PRC_RULE_MOD_TP_DESC_TXT", "PRC_RULE_MOD_TP", "PRC_RULE_MOD_TP_CD", bizMsg.B.no(i).prcRuleModTpCd_B1.getValue());
            if (ssmResult.isCodeNormal()) {
                ZYPEZDItemValueSetter.setValue(fMsg.prcRuleModTpDescTxt_B1, (String) ssmResult.getResultObject());
            } else {
                fMsg.prcRuleModTpDescTxt_B1.clear();
            }

            ssmResult = NMAL7270Query.getInstance().getAnyColmn("PRC_RULE_ADJ_TP_DESC_TXT", "PRC_RULE_ADJ_TP", "PRC_RULE_ADJ_TP_CD", bizMsg.B.no(i).prcRuleAdjTpCd_B1.getValue());
            if (ssmResult.isCodeNormal()) {
                ZYPEZDItemValueSetter.setValue(fMsg.prcRuleAdjTpDescTxt_B1, (String) ssmResult.getResultObject());
            } else {
                fMsg.prcRuleAdjTpDescTxt_B1.clear();
            }

            ssmResult = NMAL7270Query.getInstance().getAnyColmn("PRC_RULE_ATTRB_DESC_TXT", "PRC_RULE_ATTRB", "PRC_RULE_ATTRB_CD", bizMsg.B.no(i).prcRuleAttrbCd_B1.getValue());
            if (ssmResult.isCodeNormal()) {
                ZYPEZDItemValueSetter.setValue(fMsg.prcRuleAttrbDescTxt_B1, (String) ssmResult.getResultObject());
            } else {
                fMsg.prcRuleAttrbDescTxt_B1.clear();
            }

            ssmResult = NMAL7270Query.getInstance().getAnyColmn("PRC_RULE_ADJ_LVL_DESC_TXT", "PRC_RULE_ADJ_LVL", "PRC_RULE_ADJ_LVL_CD", bizMsg.B.no(i).prcRuleAdjLvlCd_B1.getValue());
            if (ssmResult.isCodeNormal()) {
                ZYPEZDItemValueSetter.setValue(fMsg.prcRuleAdjLvlDescTxt_B1, (String) ssmResult.getResultObject());
            } else {
                fMsg.prcRuleAdjLvlDescTxt_B1.clear();
            }

            csvOutFile.write();
        }

        csvOutFile.close();
    }

    /**
     * Download_TrxCond Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7270Scrn00_Download_TrxCond(NMAL7270CMsg bizMsg) {
        bizMsg.xxFileData.clear();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NM_TRX_COND), CSV_FILE_EXTENSION);
        NMAL7270F00FMsg fMsg = new NMAL7270F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER_TRX_COND);

        S21SsmEZDResult ssmResult = null;

        // Add Start 2016/09/27 QC#6931
        String lineBizTpDescTxt = null;
        String prcRuleCatgDescTxt = null;
        ssmResult = NMAL7270Query.getInstance().getAnyColmn("LINE_BIZ_TP_DESC_TXT", "LINE_BIZ_TP", "LINE_BIZ_TP_CD", bizMsg.lineBizTpCd_H1.getValue());
        if (ssmResult.isCodeNormal()) {
            lineBizTpDescTxt = (String) ssmResult.getResultObject();
        }

        ssmResult = NMAL7270Query.getInstance().getAnyColmn("PRC_RULE_CATG_DESC_TXT", "PRC_RULE_CATG", "PRC_RULE_CATG_CD", bizMsg.prcRuleCatgCd_H1.getValue());
        if (ssmResult.isCodeNormal()) {
            prcRuleCatgDescTxt = (String) ssmResult.getResultObject();
        }

        String headEffFromDt = NMAL7270CommonLogic.convertDateFormat(bizMsg.effFromDt_H1.getValue());
        String headEffThruDt = NMAL7270CommonLogic.convertDateFormat(bizMsg.effThruDt_H1.getValue());
        String headCratDt = NMAL7270CommonLogic.convertDateFormat(bizMsg.cratDt_H1.getValue());
        String headLastUpdDt = NMAL7270CommonLogic.convertDateFormat(bizMsg.lastUpdDt_H1.getValue());
        // Add End 2016/09/27 QC#6931

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            // Add Start 2016/09/27 QC#6931
            EZDMsg.copy(bizMsg, null, fMsg, null);

            NMAL7270CommonLogic.setStringItem(fMsg.lineBizTpDescTxt_H1, lineBizTpDescTxt);
            NMAL7270CommonLogic.setStringItem(fMsg.prcRuleCatgDescTxt_H1, prcRuleCatgDescTxt);
            NMAL7270CommonLogic.setStringItem(fMsg.xxDtTxt_H1, headEffFromDt);
            NMAL7270CommonLogic.setStringItem(fMsg.xxDtTxt_H2, headEffThruDt);
            NMAL7270CommonLogic.setStringItem(fMsg.xxDtTxt_H3, headCratDt);
            NMAL7270CommonLogic.setStringItem(fMsg.xxDtTxt_H4, headLastUpdDt);
            NMAL7270CommonLogic.setStringItem(fMsg.xxDtTxt_A1, NMAL7270CommonLogic.convertDateFormat(bizMsg.A.no(i).effFromDt_A1.getValue()));
            NMAL7270CommonLogic.setStringItem(fMsg.xxDtTxt_A2, NMAL7270CommonLogic.convertDateFormat(bizMsg.A.no(i).effThruDt_A1.getValue()));
            NMAL7270CommonLogic.setStringItem(fMsg.xxDtTxt_A3, NMAL7270CommonLogic.convertDateFormat(bizMsg.A.no(i).cratDt_A1.getValue()));
            NMAL7270CommonLogic.setStringItem(fMsg.xxDtTxt_A4, NMAL7270CommonLogic.convertDateFormat(bizMsg.A.no(i).lastUpdDt_A1.getValue()));
            // Add End 2016/09/27 QC#6931

            if (NMXC105001PriceMasterUtil.getRuleAttrbNm(bizMsg.A.no(i).prcRuleAttrbCd_A1.getValue(), bizMsg.A.no(i).prcRuleCondFromTxt_A1, bizMsg.A.no(i).xxRecNmTxt_A1, "Value From")) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxRecNmTxt_A1, bizMsg.A.no(i).xxRecNmTxt_A1);
            }

            EZDMsg.copy(bizMsg.A.no(i), null, fMsg, null);

            ssmResult = NMAL7270Query.getInstance().getAnyColmn("PRC_RULE_TRX_CATG_DESC_TXT", "PRC_RULE_TRX_CATG", "PRC_RULE_TRX_CATG_CD", bizMsg.A.no(i).prcRuleTrxCatgCd_A1.getValue());
            if (ssmResult.isCodeNormal()) {
                ZYPEZDItemValueSetter.setValue(fMsg.prcRuleTrxCatgDescTxt_A1, (String) ssmResult.getResultObject());
            } else {
                fMsg.prcRuleTrxCatgDescTxt_A1.clear();
            }

            ssmResult = NMAL7270Query.getInstance().getAnyColmn("PRC_RULE_ATTRB_DESC_TXT", "PRC_RULE_ATTRB", "PRC_RULE_ATTRB_CD", bizMsg.A.no(i).prcRuleAttrbCd_A1.getValue());
            if (ssmResult.isCodeNormal()) {
                ZYPEZDItemValueSetter.setValue(fMsg.prcRuleAttrbDescTxt_A1, (String) ssmResult.getResultObject());
            } else {
                fMsg.prcRuleAttrbDescTxt_A1.clear();
            }

            csvOutFile.write();
        }

        csvOutFile.close();
    }

    /**
     * OnChange_Category Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7270Scrn00_OnChange_Category(NMAL7270CMsg bizMsg) {
        PRC_RULE_CATGTMsg inTMsg = new PRC_RULE_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inTMsg.prcRuleCatgCd, bizMsg.prcRuleCatgCd_H1);
        PRC_RULE_CATGTMsg outTMsg = (PRC_RULE_CATGTMsg) ZYPCodeDataUtil.findByKey(inTMsg);
        if (outTMsg == null) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {getGlobalCompanyCode(), "Price Rule Category" });
            return;
        }

        ZYPEZDItemValueSetter.setValue(inTMsg.defRulePrcdNum, outTMsg.defRulePrcdNum);
    }

    /**
     * @param bizMsg
     */
    private void doProcess_NMAL7270Scrn00_OpenWin_CondGrpRules(NMAL7270CMsg bizMsg) {
        // search
        search(bizMsg, MODE_OPENWIN_PRC);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7270Scrn00_Search(NMAL7270CMsg bizMsg) {
        // search
        search(bizMsg, MODE_SEARCH);
    }

    // Mod Start 2016/08/24 QC#3934
    /**
     * OnBlur_Setting_Name Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7270Scrn00_OnBlur_Setting_Name(NMAL7270CMsg bizMsg) {

        int idx = bizMsg.xxCellIdx.getValueInt();
        NMXC105001PriceMasterUtil.getRuleAttrbNm(bizMsg.A.no(idx).prcRuleAttrbCd_A1.getValue(), bizMsg.A.no(idx).prcRuleCondFromTxt_A1, bizMsg.A.no(idx).xxRecNmTxt_A1, "Value From");
    }
    // Mod End 2016/08/24 QC#3934

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param mode String
     */
    private void search(NMAL7270CMsg bizMsg, String mode) {

        ZYPTableUtil.clear(bizMsg.A);
        if (MODE_SEARCH.equals(mode)) {
            ZYPTableUtil.clear(bizMsg.B);
        }

        S21SsmEZDResult ssmResult = NMAL7270Query.getInstance().search(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }
        ssmResult = NMAL7270Query.getInstance().searchTrxCond(bizMsg);
        if (S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                // Mod Start 2017/09/01 QC#20729-2
//                if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(bizMsg.A.no(i).prcRuleAttrbCd_A1.getValue())) {
//                    NMXC105001PriceMasterUtil.getRuleAttrbMdlNm(bizMsg.A.no(i).prcRuleAttrbCd_A1.getValue(), bizMsg.A.no(i).prcRuleCondFromTxt_A1, bizMsg.A.no(i).xxRecNmTxt_A1, "Value From");
//                } else {
//                    NMXC105001PriceMasterUtil.getRuleAttrbNm(bizMsg.A.no(i).prcRuleAttrbCd_A1.getValue(), bizMsg.A.no(i).prcRuleCondFromTxt_A1, bizMsg.A.no(i).xxRecNmTxt_A1, "Value From");
//                }
                if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(bizMsg.A.no(i).prcRuleAttrbCd_A1.getValue())) {
                    EZDCStringItem setValueItem = bizMsg.A.no(i).prcRuleCondFromTxt_A1;
                    if (ZYPCommonFunc.hasValue(setValueItem)) {
                        NMXC105001PriceMasterUtil.getMoldelIdName(getGlobalCompanyCode(), setValueItem.getValue(), true, setValueItem);
                    }
                }
                NMXC105001PriceMasterUtil.getRuleAttrbNm(bizMsg.A.no(i).prcRuleAttrbCd_A1.getValue(), bizMsg.A.no(i).prcRuleCondFromTxt_A1, bizMsg.A.no(i).xxRecNmTxt_A1, "Value From");
                // Mod End 2017/09/01 QC#20729-2

                // START 2016/11/07 [QC#6599, ADD]
                String cratNm = bizMsg.A.no(i).xxRecHistCratByNm_A1.getValue();
                String updNm = bizMsg.A.no(i).xxRecHistUpdByNm_A1.getValue();
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(cratNm));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(updNm));
                // END 2016/11/07 [QC#6599, ADD]
                
            }
        }

        if (MODE_SEARCH.equals(mode)) {
            ssmResult = NMAL7270Query.getInstance().searchPrcDtl(bizMsg);
            BigDecimal rate = null;
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).prcRuleDtlRate_B1)) {
                    rate = bizMsg.B.no(i).prcRuleDtlRate_B1.getValue().multiply(NUM_100);
                    ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).prcRuleDtlRate_B1, rate.setScale(PERCENT_DIGIT, BigDecimal.ROUND_HALF_DOWN));
                }
                // START 2016/11/07 [QC#6599, ADD]
                    String cratNm = bizMsg.B.no(i).xxRecHistCratByNm_B1.getValue();
                    String updNm = bizMsg.B.no(i).xxRecHistUpdByNm_B1.getValue();
                    ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).xxRecHistCratByNm_B1, ZYPRecHistUtil.getFullNameForRecHist(cratNm));
                    ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).xxRecHistUpdByNm_B1, ZYPRecHistUtil.getFullNameForRecHist(updNm));
                // END   2016/11/07 [QC#6599, ADD]

                // 2018/06/07 QC#26099 Add Start
                    ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).prcRuleDtlTxt_B1, formatAmount(bizMsg.B.no(i).prcRuleDtlTxt_B1));
                // 2018/06/07 QC#26099 Add End
            }
        }

        // QC#9694 2016/07/08 Add Start
        if (MODE_SEARCH.equals(mode) || MODE_SUBMIT.equals(mode)) {
            if(NMAL7270CommonLogic.checkPrcPrcdGrp(bizMsg)){
                bizMsg.prcRulePrcdGrpNm_H1.setErrorInfo(2, NMAL7270Constant.NMAM8627W);
                return;
            }
        }
        // QC#9694 2016/07/08 Add End
        if (MODE_SEARCH.equals(mode)) {
            bizMsg.setMessageInfo(NZZM0002I);
        }
    }

    private void createPullDown(NMAL7270CMsg bizMsg) {
        // Pull down
        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.lineBizTpCd_L1, bizMsg.lineBizTpDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_RULE_CATG.class, bizMsg.prcRuleCatgCd_L1, bizMsg.prcRuleCatgDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_RULE_TRX_CATG.class, bizMsg.prcRuleTrxCatgCd_L1, bizMsg.prcRuleTrxCatgDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_RULE_MOD_TP.class, bizMsg.prcRuleModTpCd_L1, bizMsg.prcRuleModTpDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_RULE_ADJ_TP.class, bizMsg.prcRuleAdjTpCd_L1, bizMsg.prcRuleAdjTpDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_RULE_ADJ_LVL.class, bizMsg.prcRuleAdjLvlCd_L1, bizMsg.prcRuleAdjLvlDescTxt_L1);
        // 2018/09/12 QC#9700 add start
        ZYPCodeDataUtil.createPulldownList(PRC_GRP_TRX_TP.class, bizMsg.prcGrpTrxTpCd_L1, bizMsg.prcGrpTrxTpDescTxt_L1);
        // 2018/09/12 QC#9700 add end

        PRC_RULE_ATTRBTMsg inTrxTMsg = new PRC_RULE_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(inTrxTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inTrxTMsg.ezCancelFlag, BigDecimal.ZERO.toString());
        ZYPEZDItemValueSetter.setValue(inTrxTMsg.prcRuleDispFlg, ZYPConstant.FLG_ON_Y);
        PRC_RULE_ATTRBTMsgArray trxTMsgArray = (PRC_RULE_ATTRBTMsgArray) ZYPCodeDataUtil.findByCondition(inTrxTMsg);

        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "prcRuleAttrbCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "prcRuleAttrbDescTxt");
        ZYPPulldownValueSetter.set(trxTMsgArray, tMsgKeys, bizMsg.prcRuleAttrbCd_LA, bizMsg.prcRuleAttrbDescTxt_LA);

        PRC_RULE_ATTRBTMsg inDtlTMsg = new PRC_RULE_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(inDtlTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inDtlTMsg.ezCancelFlag, BigDecimal.ZERO.toString());
        ZYPEZDItemValueSetter.setValue(inDtlTMsg.modFldFlg, ZYPConstant.FLG_ON_Y);
        PRC_RULE_ATTRBTMsgArray dtlTMsgArray = (PRC_RULE_ATTRBTMsgArray) ZYPCodeDataUtil.findByCondition(inDtlTMsg);

        ZYPPulldownValueSetter.set(dtlTMsgArray, tMsgKeys, bizMsg.prcRuleAttrbCd_LB, bizMsg.prcRuleAttrbDescTxt_LB);
    }
}
