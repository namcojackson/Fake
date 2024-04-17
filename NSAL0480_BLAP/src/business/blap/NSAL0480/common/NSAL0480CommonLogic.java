/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0480.common;

import static business.blap.NSAL0480.constant.NSAL0480Constant.APL_ID;
import static business.blap.NSAL0480.constant.NSAL0480Constant.LIMIT_DL_ROWNUM;
import static business.blap.NSAL0480.constant.NSAL0480Constant.SCRN_ID;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.MDL_DESC_TXT;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.MDL_GRP_NM;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.MTR_GRP_NM;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.MTR_GRP_PK;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.SITE_SRVY_REQ_FLG;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.SRCH_OPT_NM;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.SRCH_OPT_PK;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.CUST_ISTL_FLG;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.SVC_DEINS_RULE_NUM;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.SVC_ISTL_REQ_FLG;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.SVC_ISTL_RULE_NM;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.SVC_ISTL_RULE_NUM;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.SVC_SEG_CD;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.SVC_SKILL_NM;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.SVC_SKILL_NUM;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.T_MDL_NM;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.XX_CRAT_DT;
import static business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM.XX_SCR_ITEM_10_TXT;

import static business.blap.NSAL0480.constant.NSAL0480Constant.TBL_NM.MDSE_ITEM_CLS_TP;
import static business.blap.NSAL0480.constant.NSAL0480Constant.TBL_NM.SVC_SEG;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDMsg;
import parts.common.EZDPStringItem;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NSAL0480.NSAL0480CMsg;
import business.blap.NSAL0480.NSAL0480Query;
import business.blap.NSAL0480.NSAL0480SMsg;
import business.blap.NSAL0480.NSAL0480_ASMsg;
import business.blap.NSAL0480.constant.NSAL0480Constant.COL_NM;
import business.blap.NSAL0480.constant.NSAL0480Constant.MDL_ACTV_FLG;
import business.blap.NSAL0480.constant.NSAL0480Constant.MSG_ID;
import business.blap.NSAL0480.constant.NSAL0480Constant.MSG_KIND;
import business.file.NSAL0480F00FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   Fujitsu         M.Yamada        Create          N/A
 * 2015/10/05   Hitachi         Y.Tsuchimoto    Update          NA#CSA
 * 2016/05/19   Hitachi         M.Gotou         Update          QC#8535
 * 2016/12/02   Hitachi         K.Kojima        Update          QC#14204
 *</pre>
 */
public class NSAL0480CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * Sets the pagenation.
     * @param xxPageShowOfNum the xx page show of num
     * @param xxPageShowToNum the xx page show to num
     * @param xxPageShowFromNum the xx page show from num
     * @param pageRecs the page records
     * @param totalRecs the total records
     */
    public static void setPagenation(EZDCBigDecimalItem xxPageShowOfNum, EZDCBigDecimalItem xxPageShowToNum, int xxPageShowFromNum, int pageRecs, int totalRecs) {

        if (xxPageShowOfNum == null //
                || xxPageShowToNum == null) {
            return;
        }
        if (pageRecs == 0 || totalRecs == 0) {
            return;
        }

        xxPageShowToNum.setValue(xxPageShowFromNum + pageRecs - 1);
        xxPageShowOfNum.setValue(totalRecs);

    }

    /**
     * createPullDown
     * @param cMsg NSAL0480CMsg
     * @param srchOptUsrId String
     */
    public static void createPullDown(NSAL0480CMsg cMsg, String srchOptUsrId) {
        createSavedSearchOptionsPullDown(cMsg, srchOptUsrId);

        createMeterGroupPullDown(cMsg);
        createServiceSkillPullDown(cMsg);
        createInstallationRulePullDown(cMsg);
        createDeinstallationRulePullDown(cMsg);
        createServiceSegmentPullDown(cMsg);
        createSupplyClassPullDown(cMsg);
        createActiveStatusPullDown(cMsg);

    }

    private static void createActiveStatusPullDown(NSAL0480CMsg cMsg) {

        EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        ZYPPulldownValueSetter.insertFirstData(//
                ZYPConstant.FLG_OFF_N //
                , cMsg.mdlActvFlg_L //
                , converter.convLabel2i18nLabel("", MDL_ACTV_FLG.N.getmdlActvFlgNm()) //
                , cMsg.xxScrItem10Txt_L);
        ZYPPulldownValueSetter.insertFirstData(//
                ZYPConstant.FLG_ON_Y //
                , cMsg.mdlActvFlg_L //
                , converter.convLabel2i18nLabel("", MDL_ACTV_FLG.Y.getmdlActvFlgNm()) //
                , cMsg.xxScrItem10Txt_L);
    }

    private static void createSupplyClassPullDown(NSAL0480CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_CLS_TP.toString(), cMsg.mdseItemClsTpCd_L, cMsg.mdseItemClsTpDescTxt_L);
    }

    private static void createServiceSegmentPullDown(NSAL0480CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(SVC_SEG.toString(), cMsg.svcSegCd_L, cMsg.xxScrItem54Txt_L, ":");
    }

    private static void createDeinstallationRulePullDown(NSAL0480CMsg cMsg) {
        S21SsmEZDResult ssmResult = NSAL0480Query.getInstance().getInstallationRuleList(ZYPConstant.FLG_OFF_N);
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

        for (int i = 0; i < resultList.size() && i < cMsg.svcIstlRuleNum_LN.length(); i++) {
            Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
            cMsg.svcIstlRuleNum_LN.no(i).setValue((String) resultMap.get(SVC_ISTL_RULE_NUM.toString()));
            cMsg.svcIstlRuleNm_LN.no(i).setValue((String) resultMap.get(SVC_ISTL_RULE_NM.toString()));
        }
    }

    private static void createInstallationRulePullDown(NSAL0480CMsg cMsg) {
        S21SsmEZDResult ssmResult = NSAL0480Query.getInstance().getInstallationRuleList(ZYPConstant.FLG_ON_Y);
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

        for (int i = 0; i < resultList.size() && i < cMsg.svcIstlRuleNum_LY.length(); i++) {
            Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
            cMsg.svcIstlRuleNum_LY.no(i).setValue((String) resultMap.get(SVC_ISTL_RULE_NUM.toString()));
            cMsg.svcIstlRuleNm_LY.no(i).setValue((String) resultMap.get(SVC_ISTL_RULE_NM.toString()));
        }
    }

    private static void createServiceSkillPullDown(NSAL0480CMsg cMsg) {
        S21SsmEZDResult ssmResult = NSAL0480Query.getInstance().getServiceSkillList();
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

        for (int i = 0; i < resultList.size() && i < cMsg.svcSkillNum_L.length(); i++) {
            Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
            cMsg.svcSkillNum_L.no(i).setValue((String) resultMap.get(SVC_SKILL_NUM.toString()));
            cMsg.svcSkillNm_L.no(i).setValue((String) resultMap.get(SVC_SKILL_NM.toString()));
        }

    }

    private static void createMeterGroupPullDown(NSAL0480CMsg cMsg) {
        S21SsmEZDResult ssmResult = NSAL0480Query.getInstance().getMeterGroupList();
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        for (int i = 0; i < resultList.size() && i < cMsg.mtrGrpPk_L.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            cMsg.mtrGrpPk_L.no(i).setValue((BigDecimal) resultMap.get(MTR_GRP_PK.toString()));
            // START 2016/05/19 M.Gotou [QC#8535, MOD]
            cMsg.mtrGrpNm_L.no(i).setValue((String) resultMap.get(MTR_GRP_NM.toString()));
            // END 2016/05/19 M.Gotou [QC#8535, MOD]
        }
    }

    private static void createSavedSearchOptionsPullDown(NSAL0480CMsg cMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NSAL0480Query.getInstance().getSavedSearchOptionList(srchOptUsrId);
        if (!ssmResult.isCodeNormal()) {
            cMsg.srchOptPk_L.clear();
            cMsg.srchOptNm_L.clear();
            return;
        }

        cMsg.srchOptPk_L.clear();
        cMsg.srchOptNm_L.clear();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < cMsg.srchOptPk_L.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            cMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get(SRCH_OPT_PK.toString()));
            cMsg.srchOptNm_L.no(i).setValue((String) resultMap.get(SRCH_OPT_NM.toString()));
        }

    }

    /**
     * getSearchData
     * @param bizMsg NSAL0480CMsg
     * @param glblMsg NSAL0480SMsg
     */
    public static void getSearchData(NSAL0480CMsg bizMsg, NSAL0480SMsg glblMsg) {
        S21SsmEZDResult ssmResult = NSAL0480Query.getInstance().getSearchData(bizMsg, glblMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(MSG_ID.NZZM0000E.toString());
        } else if (!ssmResult.isCodeNormal()) {
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

        glblMsg.A.clear();
        glblMsg.A.setValidCount(0);
        for (int i = 0; i < resultList.size() && i < glblMsg.A.length() - 1; i++) {
            Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
            NSAL0480_ASMsg asMsg = glblMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(asMsg.t_MdlNm_A, (String) resultMap.get(T_MDL_NM.toString()));
            ZYPEZDItemValueSetter.setValue(asMsg.mdlDescTxt_A, (String) resultMap.get(MDL_DESC_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(asMsg.mdlGrpNm_A, (String) resultMap.get(MDL_GRP_NM.toString()));
            ZYPEZDItemValueSetter.setValue(asMsg.svcSegCd_A, (String) resultMap.get(SVC_SEG_CD.toString()));
            ZYPEZDItemValueSetter.setValue(asMsg.xxScrItem10Txt_A, (String) resultMap.get(XX_SCR_ITEM_10_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(asMsg.xxCratDt_A, (String) resultMap.get(XX_CRAT_DT.toString()));
            // START 2016/05/19 M.Gotou [QC#8535, MOD]
            ZYPEZDItemValueSetter.setValue(asMsg.mtrGrpNm_A, (String) resultMap.get(MTR_GRP_NM.toString()));
            // END 2016/05/19 M.Gotou [QC#8535, MOD]
            ZYPEZDItemValueSetter.setValue(asMsg.svcSkillNm_A, (String) resultMap.get(SVC_SKILL_NM.toString()));
            // 2015/10/05 CSA Y.Tsuchimoto Add Start
            ZYPEZDItemValueSetter.setValue(asMsg.svcIstlRuleNum_A, (String) resultMap.get(SVC_ISTL_RULE_NUM.toString()));
            ZYPEZDItemValueSetter.setValue(asMsg.svcDeinsRuleNum_A, (String) resultMap.get(SVC_DEINS_RULE_NUM.toString()));
            ZYPEZDItemValueSetter.setValue(asMsg.custIstlFlg_A, (String) resultMap.get(CUST_ISTL_FLG.toString()));
            ZYPEZDItemValueSetter.setValue(asMsg.svcIstlReqFlg_A, (String) resultMap.get(SVC_ISTL_REQ_FLG.toString()));
            ZYPEZDItemValueSetter.setValue(asMsg.siteSrvyReqFlg_A, (String) resultMap.get(SITE_SRVY_REQ_FLG.toString()));
            // 2015/10/05 CSA Y.Tsuchimoto Add End
            // START 2016/12/02 K.Kojima [QC#14204,ADD]
            ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistCratTs_A, (String) resultMap.get("XX_REC_HIST_CRAT_TS_A"));
            ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistCratByNm_A, (String) resultMap.get("XX_REC_HIST_CRAT_BY_NM_A"));
            ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistUpdTs_A, (String) resultMap.get("XX_REC_HIST_UPD_TS_A"));
            ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistUpdByNm_A, (String) resultMap.get("XX_REC_HIST_UPD_BY_NM_A"));
            ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistTblNm_A, (String) resultMap.get("XX_REC_HIST_TBL_NM_A"));
            // END 2016/12/02 K.Kojima [QC#14204,ADD]

            glblMsg.A.setValidCount(i + 1);
        }
        if (resultList.size() >= glblMsg.A.length()) {
            bizMsg.setMessageInfo(MSG_ID.NZZM0001W.toString(), null);
        }

    }

    /**
     * copyGlblMsgToBizMsg
     * @param bizMsg NSAL0480CMsg
     * @param glblMsg NSAL0480SMsg
     */
    public static void copyGlblMsgToBizMsg(NSAL0480CMsg bizMsg, NSAL0480SMsg glblMsg) {
        int ixS = (bizMsg.xxPageShowFromNum.getValueInt() - 1);
        int i = 0;
        if (ixS >= 0) {
            for (; i < bizMsg.A.length() && ixS < glblMsg.A.getValidCount(); i++) {
                EZDMsg.copy(glblMsg.A.no(ixS++), null, bizMsg.A.no(i), null);
            }
        }
        bizMsg.A.setValidCount(i);
    }

    /**
     * preSetToPageOne
     * @param xxPageShowFromNum EZDCBigDecimalItem
     */
    public static void preSetToPageOne(EZDCBigDecimalItem xxPageShowFromNum) {
        if (xxPageShowFromNum == null) {
            return;
        }
        xxPageShowFromNum.setValue(BigDecimal.ONE);
    }

    /**
     * writeCsvFile
     * @param cMsg NSAL0480CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFile(NSAL0480CMsg cMsg, ResultSet rs) throws SQLException {

        NSAL0480F00FMsg fMsg = new NSAL0480F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        //write header
        writeCsvFileHeader(csvOutFile);

        if (!rs.next()) {
            cMsg.setMessageInfo(MSG_ID.NSAM0194I.toString(), null);
            csvOutFile.close();
            return;
        }

        //write contents
        do {
            if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(MSG_ID.NZZM0001W.toString(), null);
                break;
            }
            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.t_MdlNm, rs.getString(COL_NM.T_MDL_NM.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.mdlDescTxt, rs.getString(COL_NM.MDL_DESC_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.mdlGrpNm, rs.getString(COL_NM.MDL_GRP_NM.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.svcSegCd, rs.getString(COL_NM.SVC_SEG_CD.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt, rs.getString(COL_NM.XX_SCR_ITEM_10_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxSrDtTxt //
                    , ZYPDateUtil.convertFormat(rs.getString(COL_NM.XX_CRAT_DT.toString()), "yyyyMMdd", "MMddyyyy", '/'));
            // START 2016/05/19 M.Gotou [QC#8535, MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.mtrGrpNm, rs.getString(COL_NM.MTR_GRP_NM.toString()));
            // END 2016/05/19 M.Gotou [QC#8535, MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.svcSkillNm, rs.getString(COL_NM.SVC_SKILL_NM.toString()));
            // 2015/10/05 CSA Y.Tsuchimoto Add Start
            ZYPEZDItemValueSetter.setValue(fMsg.svcIstlRuleNm_IN, rs.getString(COL_NM.SVC_ISTL_RULE_NM_IN.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.svcIstlRuleNm_DE, rs.getString(COL_NM.SVC_ISTL_RULE_NM_DE.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.custIstlFlg, rs.getString(COL_NM.CUST_ISTL_FLG.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.svcIstlReqFlg, rs.getString(COL_NM.SVC_ISTL_REQ_FLG.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.siteSrvyReqFlg, rs.getString(COL_NM.SITE_SRVY_REQ_FLG.toString()));
            // 2015/10/05 CSA Y.Tsuchimoto Add END
            ZYPEZDItemValueSetter.setValue(fMsg.rcllIntvlDaysAot, rs.getBigDecimal(COL_NM.RCLL_INTVL_DAYS_AOT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.rcllCopyVolCnt, rs.getBigDecimal(COL_NM.RCLL_COPY_VOL_CNT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.rcllGlblIntvlDaysAot, rs.getBigDecimal(COL_NM.RCLL_GLBL_INTVL_DAYS_AOT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.rcllGlblCopyVolCnt, rs.getBigDecimal(COL_NM.RCLL_GLBL_COPY_VOL_CNT.toString()));
            // RSP_TM_UP_MN_AOT is registered as "hours", so it does not need convert.
            ZYPEZDItemValueSetter.setValue(fMsg.rspTmUpMnAot, rs.getBigDecimal(COL_NM.RSP_TM_UP_MN_AOT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.xsVisitCnt, rs.getBigDecimal(COL_NM.XS_VISIT_CNT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.phoneFixFlg, rs.getString(COL_NM.PHONE_FIX_FLG.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.phoneFixIntvlDaysAot, rs.getBigDecimal(COL_NM.PHONE_FIX_INTVL_DAYS_AOT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.afterHourAllwFlg, rs.getString(COL_NM.AFTER_HOUR_ALLW_FLG.toString()));
            // 2015/10/05 CSA Y.Tsuchimoto Add Start
            ZYPEZDItemValueSetter.setValue(fMsg.machInFldInacMthAot, rs.getBigDecimal(COL_NM.MACH_IN_FLD_INAC_MTH_AOT.toString()));
            // 2015/10/05 CSA Y.Tsuchimoto Add End
            ZYPEZDItemValueSetter.setValue(fMsg.copyVolDaysAot, rs.getBigDecimal(COL_NM.COPY_VOL_DAYS_AOT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.maxCopyPerDayTotCnt, rs.getBigDecimal(COL_NM.MAX_COPY_PER_DAY_TOT_CNT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.maxCopyPerDayBlackCnt, rs.getBigDecimal(COL_NM.MAX_COPY_PER_DAY_BLACK_CNT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.maxCopyTestCnt, rs.getBigDecimal(COL_NM.MAX_COPY_TEST_CNT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.mdlSpeedBlackRate, rs.getBigDecimal(COL_NM.MDL_SPEED_BLACK_RATE.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.mdlSpeedColorRate, rs.getBigDecimal(COL_NM.MDL_SPEED_COLOR_RATE.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.dsMdlPaperSizeNm, rs.getString(COL_NM.DS_MDL_PAPER_SIZE_NM.toString()));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    private static void writeCsvFileHeader(ZYPCSVOutFile csvOutFile) {

        final String[] csvHeader = new String[] {//
        "Model Name" //
                , "Model Description" //
                , "Model Group" //
                , "Service Segment" //
                , "Active Status" //
                , "Create Date" //
                , "Meter Group" //
                , "Service Skills" //
                // 2015/10/05 CSA Y.Tsuchimoto Add Start
                , "Installation Rules" //
                , "De-installation Rules" //
                , "Customer Installable" //
                , "Install Required for Review" //
                , "Site Survey Required" //
                // 2015/10/05 CSA Y.Tsuchimoto Add ENd
                , "Local Recall Rules (Days)" //
                , "Local Recall Rules (Copies)" //
                , "Global Recall Rules (Days)" //
                , "Global Recall Rules (Copies)" //
                , "Response Time Target (Hours)" //
                , "Excessive Calls" //
                , "Phone Fix Enabled" //
                , "Phone Fix Period (Days)" //
                , "After Hours Allowed" //
                // 2015/10/05 CSA Y.Tsuchimoto Add Start
                , "MIF Inactive(Months)" //
                // 2015/10/05 CSA Y.Tsuchimoto Add End
                , "Average Daily Copy Volume (ADCV)" //
                , "Max Copies Per Day - Total" //
                , "Max Copies Per Day - BW" //
                , "Max Test Copies" //
                , "Speed - BW (Copies Per Minute)" //
                , "Speed - Color (Copies Per Minute)" //
                , "Paper Size" };
        csvOutFile.writeHeader(csvHeader);
    }

    /**
     * callNszc0330forSearchOption
     * @param cMsg NSAL0480CMsg
     * @param sMsg NSAL0480SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forSearchOption(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, srchOptUsrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, APL_ID);

        if (!callNszc0330(cMsg, pMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(cMsg.t_MdlNm_H, pMsg.srchOptTxt_01.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.mdlDescTxt_H, pMsg.srchOptTxt_02.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.mdlGrpNm_H, pMsg.srchOptTxt_03.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.svcSegCd_H, pMsg.srchOptTxt_04.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.mdlActvFlg_H, pMsg.srchOptTxt_05.getValue());
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_06.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxCratDt_H, pMsg.srchOptTxt_06.getValue());
        } else {
            cMsg.xxCratDt_H.clear();
        }
        if (ZYPCommonFunc.isNumberCheck(pMsg.srchOptTxt_07.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.mtrGrpPk_H, new BigDecimal(pMsg.srchOptTxt_07.getValue()));
        } else {
            cMsg.mtrGrpPk_H.clear();
        }
        ZYPEZDItemValueSetter.setValue(cMsg.svcSkillNum_H, pMsg.srchOptTxt_08.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.svcIstlRuleNum_HY, pMsg.srchOptTxt_09.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.svcIstlReqFlg_HY, pMsg.srchOptTxt_10.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.svcIstlReqFlg_HN, pMsg.srchOptTxt_11.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.svcIstlRuleNum_HN, pMsg.srchOptTxt_12.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.t_ItemCd_H, pMsg.srchOptTxt_13.getValue()); // Mdse Cd
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd_H, pMsg.srchOptTxt_14.getValue()); // Supply Mdse Cd
        ZYPEZDItemValueSetter.setValue(cMsg.imgSplyOemCd_H, pMsg.srchOptTxt_15.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.mdseItemClsTpCd_H, pMsg.srchOptTxt_16.getValue());
        // 2015/10/05 CSA Y.Tsuchimoto Add Start
        ZYPEZDItemValueSetter.setValue(cMsg.custIstlFlg_HY, pMsg.srchOptTxt_17.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.custIstlFlg_HN, pMsg.srchOptTxt_18.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.siteSrvyReqFlg_HY, pMsg.srchOptTxt_19.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.siteSrvyReqFlg_HN, pMsg.srchOptTxt_20.getValue());
        // 2015/10/05 CSA Y.Tsuchimoto Add End
    }

    /**
     * callNszc0330forSaveSearch
     * @param cMsg NSAL0480CMsg
     * @param sMsg NSAL0480SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forSaveSearch(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S) //
                || isSameSaveSearchName(cMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        }
        if (ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, cMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, APL_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, srchOptUsrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.t_MdlNm_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.mdlDescTxt_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.mdlGrpNm_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.svcSegCd_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.mdlActvFlg_H.getValue());
        ZYPEZDItemValueSetter.setValue((EZDPStringItem) pMsg.srchOptTxt_06 //
                , cMsg.xxCratDt_H.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.mtrGrpPk_H)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.mtrGrpPk_H.getValue().toString());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.svcSkillNum_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.svcIstlRuleNum_HY.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.svcIstlReqFlg_HY.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, cMsg.svcIstlReqFlg_HN.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, cMsg.svcIstlRuleNum_HN.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.t_ItemCd_H.getValue()); // Mdse Cd
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.mdseCd_H.getValue()); // Supply Mdse Cd
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.imgSplyOemCd_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.mdseItemClsTpCd_H.getValue());
        // 2015/10/05 CSA Y.Tsuchimoto Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, cMsg.custIstlFlg_HY.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, cMsg.custIstlFlg_HN.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, cMsg.siteSrvyReqFlg_HY.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, cMsg.siteSrvyReqFlg_HN.getValue());
        // 2015/10/05 CSA Y.Tsuchimoto Add End

        if (callNszc0330(cMsg, pMsg)) {
            createSavedSearchOptionsPullDown(cMsg, srchOptUsrId);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_S, pMsg.srchOptPk);
            cMsg.srchOptNm_S.clear();
            cMsg.setMessageInfo(MSG_ID.ZZZM9003I.toString() //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Save Search") });
        }
    }

    private static boolean isSameSaveSearchName(NSAL0480CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * isExistSaveSearchName
     * @param cMsg NSAL0480CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NSAL0480CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S) //
                        && cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param cMsg NSAL0480CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NSAL0480CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {
                return;
            }
            if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_L.no(i));
            }
        }
        return;
    }

    /**
     * callNszc0330forDeleteSearch
     * @param cMsg NSAL0480CMsg
     * @param sMsg NSAL0480SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forDeleteSearch(NSAL0480CMsg cMsg, NSAL0480SMsg sMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, APL_ID);

        if (callNszc0330(cMsg, pMsg)) {
            createSavedSearchOptionsPullDown(cMsg, srchOptUsrId);
            cMsg.srchOptNm_S.clear();
            cMsg.setMessageInfo(MSG_ID.ZZZM9003I.toString() //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Delete Search") });
        }
    }

    private static boolean callNszc0330(NSAL0480CMsg cMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    cMsg.setMessageInfo(msgId);
                    if (msgId.endsWith(MSG_KIND.ERROR.getMsgKind())) {
                        cMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        cMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
