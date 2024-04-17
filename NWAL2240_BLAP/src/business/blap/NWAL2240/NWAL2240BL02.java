/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2240;

import static business.blap.NWAL2240.common.NWAL2240CommonLogic.setSvcPrvdPtyPulldown;
import static business.blap.NWAL2240.common.NWAL2240CommonLogic.copyCMsgToSMsg;
import static business.blap.NWAL2240.common.NWAL2240CommonLogic.copySMsgToCMsg;
import static business.blap.NWAL2240.common.NWAL2240CommonLogic.setAmPmPulldownList;
import static business.blap.NWAL2240.common.NWAL2240CommonLogic.setSvcIstlRulePulldown;
import static business.blap.NWAL2240.common.NWAL2240CommonLogic.getTimeZone;
import static business.blap.NWAL2240.common.NWAL2240CommonLogic.getChangeTime;
import static business.blap.NWAL2240.constant.NWAL2240Constant.BUTTON_DISP_FLG_ON;
import static business.blap.NWAL2240.constant.NWAL2240Constant.MESSAGE_KIND_ERROR;
import static business.blap.NWAL2240.constant.NWAL2240Constant.NWAM0142E;
import static business.blap.NWAL2240.constant.NWAL2240Constant.NWAM0181E;
import static business.blap.NWAL2240.constant.NWAL2240Constant.TAB_ENABLE_FLG_OFF;
import static business.blap.NWAL2240.constant.NWAL2240Constant.VALID_COUNT_ZERO;
import static business.blap.NWAL2240.constant.NWAL2240Constant.XX_EDT_MODE_CD_NO_EDIT;
import static business.blap.NWAL2240.constant.NWAL2240Constant.SVC_ISTL_RULE_NUM_NO_INSTALL;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.db.TECH_MSTRTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DELY_TRNSP_OPT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL2240BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         S.Ohki          Create          N/A
 * 2016/04/26   Hitachi         K.Kojima        Update          QC#5503
 * 2016/07/11   Fujitsu         H.Ikeda         Update          QC#5030
 * 2017/08/17   Fujitsu         S.Iidaka        Update          QC#20612
 * 2017/08/24   Fujitsu         S.Iidaka        Update          QC#20740
 * 2017/08/25   Fujitsu         S.Iidaka        Update          QC#20740-1
 * 2017/08/30   Fujitsu         S.Takami        Update          S21_NA#20842
 * 2018/01/09   Fujitsu         N.Sugiura       Update          QC#18460
 * 2023/09/05   Hitachi         K.Watanabe      Update          QC#53408
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 *</pre>
 */
public class NWAL2240BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2240CMsg bizMsg = (NWAL2240CMsg) cMsg;
            NWAL2240SMsg glblMsg = (NWAL2240SMsg) sMsg;

            if ("NWAL2240_INIT".equals(screenAplID)) {
                doProcess_NWAL2240_INIT(bizMsg, glblMsg);

            } else if ("NWAL2240Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWAL2240Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NWAL2240Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWAL2240Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NWAL2240Scrn00_Add_Row".equals(screenAplID)) {
                doProcess_NWAL2240Scrn00_Add_Row(bizMsg, glblMsg);

            } else if ("NWAL2240Scrn00_Delete_Row".equals(screenAplID)) {
                doProcess_NWAL2240Scrn00_Delete_Row(bizMsg, glblMsg);

            } else if ("NWAL2240Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL2240Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NWAL2240Scrn00_Search_Order".equals(screenAplID)) {
                doProcess_NWAL2240Scrn00_Search_Order(bizMsg, glblMsg);

            } else if ("NWAL2240Scrn00_Search_TechNm".equals(screenAplID)) {
                doProcess_NWAL2240Scrn00_Search_TechNm(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL2240Scrn00_Search_TechNm(NWAL2240CMsg bizMsg, NWAL2240SMsg sMsg) {

        TECH_MSTRTMsg tMsg = NWAL2240Query.getInstance().getTechMstr(bizMsg);
        if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.techNm)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.techNm_DI, tMsg.techNm);
        } else {
            bizMsg.istlTechCd_DI.setErrorInfo(1, NWAM0181E, new String[] {"Install Technician" });
        }
    }

    private void doProcess_NWAL2240_INIT(NWAL2240CMsg bizMsg, NWAL2240SMsg sMsg) {

        initSearch(bizMsg, sMsg);
    }

    private void doProcess_NWAL2240Scrn00_CMN_Reset(NWAL2240CMsg bizMsg, NWAL2240SMsg sMsg) {

        initSearch(bizMsg, sMsg);
    }

    private void initSearch(NWAL2240CMsg bizMsg, NWAL2240SMsg sMsg) {

        createPulldown(bizMsg, sMsg);

        if (!ZYPCommonFunc.hasValue(bizMsg.ordSrcRefNum_H0)) {
            return;
        }

        searchOrder(bizMsg, sMsg);
    }

    private void createPulldown(NWAL2240CMsg bizMsg, NWAL2240SMsg sMsg) {

        // Configuration Category
        bizMsg.configCatgCd_L0.clear();
        bizMsg.configCatgDescTxt_L0.clear();
        ZYPCodeDataUtil.createPulldownList(CONFIG_CATG.class, bizMsg.configCatgCd_L0, bizMsg.configCatgDescTxt_L0);

        // Service Install Rule
        bizMsg.svcIstlRuleNum_DI.clear();
        bizMsg.svcIstlRuleNum_L0.clear();
        bizMsg.svcIstlRuleNm_L0.clear();
        setSvcIstlRulePulldown(bizMsg, getGlobalCompanyCode());

        // Install Division
        bizMsg.istlDivCd_DI.clear();
        bizMsg.istlDivCd_L0.clear();
        bizMsg.lineBizTpDescTxt_L0.clear();
        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.istlDivCd_L0, bizMsg.lineBizTpDescTxt_L0);

        // 2017/08/17 QC#20612 Add Start
        // Transport Option
        bizMsg.delyTrnspOptCd_SS.clear();
        bizMsg.delyTrnspOptCd_L0.clear();
        bizMsg.delyTrnspOptNm_L0.clear();
        ZYPCodeDataUtil.createPulldownList(DELY_TRNSP_OPT.class, bizMsg.delyTrnspOptCd_L0, bizMsg.delyTrnspOptNm_L0);
        // 2017/08/17 QC#20612 Add End

        // AM/PM
        bizMsg.xxTpCd.clear();
        bizMsg.xxTpNm.clear();
        setAmPmPulldownList(bizMsg);

        // Contact Parson
        bizMsg.ctacPsnTpCd_L0.clear();
        bizMsg.ctacTpNm_L0.clear();
        ZYPCodeDataUtil.createPulldownList(CTAC_TP.class, bizMsg.ctacPsnTpCd_L0, bizMsg.ctacTpNm_L0);

        // 2019/11/09 QC#53993 Add Start
        // To Be Installed By
        bizMsg.istlBySvcPrvdPtyCd_DI.clear();
        bizMsg.svcPrvdPtyDescTxt_L0.clear();
        bizMsg.istlBySvcPrvdPtyCd_L0.clear();
        setSvcPrvdPtyPulldown(bizMsg, true, this.getGlobalCompanyCode());
        // 2019/11/09 QC#53993 Add End

        // 2019/11/09 QC#53993 Add Start
        // Service Provided By
        bizMsg.svcBySvcPrvdPtyCd_DI.clear();
        bizMsg.svcPrvdPtyDescTxt_L1.clear();
        bizMsg.svcBySvcPrvdPtyCd_L0.clear();
        setSvcPrvdPtyPulldown(bizMsg, false, this.getGlobalCompanyCode());
        // 2019/11/09 QC#53993 Add End
    }

    /**
     * Set Tab Protect Flg
     * @param bizMsg NWAL2240CMsg
     */
    private void setTabProtectFlg(NWAL2240CMsg bizMsg) {

        S21SsmEZDResult ssmResult = null;

        ssmResult = NWAL2240Query.getInstance().getCountImptOrderByStatus(bizMsg);
        BigDecimal count = (BigDecimal) ssmResult.getResultObject();

        if (count.compareTo(BigDecimal.ZERO) != 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_DI, TAB_ENABLE_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_SS, TAB_ENABLE_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_CO, TAB_ENABLE_FLG_OFF);
            return;
        }

        // START 2016/04/26 K.Kojima [QC#5503,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.xxEdtModeFlg) && XX_EDT_MODE_CD_NO_EDIT.equals(bizMsg.xxEdtModeFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_DI, TAB_ENABLE_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_SS, TAB_ENABLE_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_CO, TAB_ENABLE_FLG_OFF);
            return;
        }
        // END 2016/04/26 K.Kojima [QC#5503,ADD]
    }

    private void doProcess_NWAL2240Scrn00_Search_Order(NWAL2240CMsg bizMsg, NWAL2240SMsg sMsg) {

        searchOrder(bizMsg, sMsg);
    }

    private void searchOrder(NWAL2240CMsg bizMsg, NWAL2240SMsg sMsg) {

        clearAll(bizMsg, sMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());

        if (!setHeaderItem(bizMsg, sMsg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_DI, TAB_ENABLE_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_SS, TAB_ENABLE_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_CO, TAB_ENABLE_FLG_OFF);
            return;
        }

        // ///////////////////////////////////////
        // Delivery & Install Tab
        // Header Area
        // Delivery Area
        setDelivItem(bizMsg);

        // Install Area
        setIstlItemItem(bizMsg);

        // ///////////////////////////////////////
        // Survey
        setSurveyInfo(bizMsg);

        // ///////////////////////////////////////
        // Contact
        setCtacList(bizMsg, sMsg);

        // Set Tab Enable Flag
        setTabProtectFlg(bizMsg);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg, BUTTON_DISP_FLG_ON);
    }

    private void doProcess_NWAL2240Scrn00_CMN_Clear(NWAL2240CMsg cMsg, NWAL2240SMsg sMsg) {

        cMsg.A.setValidCount(VALID_COUNT_ZERO);
        cMsg.B.setValidCount(VALID_COUNT_ZERO);
        cMsg.C.setValidCount(VALID_COUNT_ZERO);

        createPulldown(cMsg, sMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg_DI, TAB_ENABLE_FLG_OFF);
        ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg_SS, TAB_ENABLE_FLG_OFF);
        ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg_CO, TAB_ENABLE_FLG_OFF);
    }

    private void doProcess_NWAL2240Scrn00_CMN_Submit(NWAL2240CMsg bizMsg, NWAL2240SMsg sMsg) {
        if (bizMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            return;
        }

        searchOrder(bizMsg, sMsg);
    }

    /**
     * Set Header Area Item
     * @param bizMsg NWAL2240CMsg
     * @param sMsg NWAL2240SMsg
     * @return result(false: error)
     */
    private boolean setHeaderItem(NWAL2240CMsg bizMsg, NWAL2240SMsg sMsg) {
        // Configuration Number is not empty
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {

            S21SsmEZDResult ssmResult = NWAL2240Query.getInstance().searchWithConfNum(bizMsg);

            if (ssmResult.isCodeNotFound()) {
                bizMsg.setMessageInfo(NWAM0142E);
                return false;
            }

            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();

            // DS_CPO_CONFIG_PK
            ZYPEZDItemValueSetter.setValue(bizMsg.dsImptOrdConfigPk_H0, (BigDecimal) resultMap.get("DS_CPO_CONFIG_PK"));
            // Category
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgDescTxt_H0, (String) resultMap.get("DS_ORD_CATG_DESC_TXT"));
            // Reason Code
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpDescTxt_H0, (String) resultMap.get("DS_ORD_TP_DESC_TXT"));
            // Ship To Cust Acct
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd_H0, (String) resultMap.get("SHIP_TO_CUST_ACCT_CD"));
            // Location Code
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustLocCd_H0, (String) resultMap.get("SHIP_TO_CUST_LOC_CD"));
            // Name
            // 2017/08/30 S21_NA#20842 Mod Start
//            ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm_H0, (String) resultMap.get("SHIP_TO_CUST_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm_H0, (String) resultMap.get("SHIP_TO_CUST_ACCT_NM"));
            // 2017/08/30 S21_NA#20842 Mod End
            // Address Line 1
            ZYPEZDItemValueSetter.setValue(bizMsg.firstLineAddr_H0, (String) resultMap.get("SHIP_TO_FIRST_LINE_ADDR"));
            // Address Line 2
            ZYPEZDItemValueSetter.setValue(bizMsg.scdLineAddr_H0, (String) resultMap.get("SHIP_TO_SCD_LINE_ADDR"));
            // 2018/01/09 QC#18460 Add Start
            // Country Code
            ZYPEZDItemValueSetter.setValue(bizMsg.ctryCd_H0, (String) resultMap.get("SHIP_TO_CTRY_CD"));
            // Post Code
            ZYPEZDItemValueSetter.setValue(bizMsg.postCd_H0, (String) resultMap.get("SHIP_TO_POST_CD"));
            // 2018/01/09 QC#18460 Add End

        } else {

            S21SsmEZDResult ssmResult = NWAL2240Query.getInstance().searchWithOrdNum(bizMsg);

            if (ssmResult.isCodeNotFound()) {
                bizMsg.setMessageInfo(NWAM0142E);
                return false;
            }

            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();

            // Category
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgDescTxt_H0, (String) resultMap.get("DS_ORD_CATG_DESC_TXT"));
            // Reason Code
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpDescTxt_H0, (String) resultMap.get("DS_ORD_TP_DESC_TXT"));
            // Ship To Cust Acct
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd_H0, (String) resultMap.get("SELL_TO_CUST_CD"));
            // Location Code
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustLocCd_H0, (String) resultMap.get("SHIP_TO_CUST_CD"));
            // Name
            // 2017/08/30 S21_NA#20842 Mod Start
//            ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm_H0, (String) resultMap.get("SHIP_TO_CUST_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm_H0, (String) resultMap.get("SHIP_TO_CUST_ACCT_NM"));
            // 2017/08/30 S21_NA#20842 Mod End
            // Address Line 1
            ZYPEZDItemValueSetter.setValue(bizMsg.firstLineAddr_H0, (String) resultMap.get("SHIP_TO_FIRST_LINE_ADDR"));
            // Address Line 2
            ZYPEZDItemValueSetter.setValue(bizMsg.scdLineAddr_H0, (String) resultMap.get("SHIP_TO_SCD_LINE_ADDR"));
            // 2018/01/09 QC#18460 Add Start
            // Country Code
            ZYPEZDItemValueSetter.setValue(bizMsg.ctryCd_H0, (String) resultMap.get("SHIP_TO_CTRY_CD"));
            // Post Code
            ZYPEZDItemValueSetter.setValue(bizMsg.postCd_H0, (String) resultMap.get("SHIP_TO_POST_CD"));
            // 2018/01/09 QC#18460 Add End
        }

        // 2018/01/09 QC#18460 Add Start
        // Time Zone
        ZYPEZDItemValueSetter.setValue(bizMsg.tmZoneCd_H0, getTimeZone(bizMsg));
        // 2018/01/09 QC#18460 Add End

        // GET LOC_NUM
        S21SsmEZDResult ssmResult = NWAL2240Query.getInstance().getLocNum(bizMsg);

        if (ssmResult.isCodeNormal()) {
            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
            // Category
            ZYPEZDItemValueSetter.setValue(bizMsg.locNum_H0, (String) resultMap.get("LOC_NUM"));
        }

        return true;
    }

    /**
     * Set Derivery Area Item
     * @param bizMsg NWAL2240CMsg
     */
    private void setDelivItem(NWAL2240CMsg bizMsg) {

        Map<String, Object> resultMap = null;
        String cpoSrcTpCd = ""; // QC#20740

        // Configuration Number is not empty
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {

            S21SsmEZDResult delvDtl = NWAL2240Query.getInstance().getDeliveryDtlWithConfNum(bizMsg);

            if (delvDtl.isCodeNormal()) {
                resultMap = (Map<String, Object>) delvDtl.getResultObject();
            }

        } else {

            S21SsmEZDResult delvDtl = NWAL2240Query.getInstance().getDeliveryDtlWithOrdNum(bizMsg);

            if (delvDtl.isCodeNormal()) {
                resultMap = (Map<String, Object>) delvDtl.getResultObject();
            }
        }

        if (resultMap != null) {
            // DS_CPO_DELY_INFO_PK
            ZYPEZDItemValueSetter.setValue(bizMsg.dsImptOrdDelyInfoPk_DI, (BigDecimal) resultMap.get("DS_IMPT_ORD_DELY_INFO_PK"));
            // Delivery Date
//            ZYPEZDItemValueSetter.setValue(bizMsg.rddDt_DI, (String) resultMap.get("RDD_DT")); // 2017/08/25 S21_NA#20740-1 Del
            // 2018/01/09 QC#18460 Mod Start
            // Hours of Operation From
            // ZYPEZDItemValueSetter.setValue(bizMsg.opsFromHourMn_DI, (String) resultMap.get("OPS_FROM_HOUR_MN"));
            ZYPEZDItemValueSetter.setValue(bizMsg.opsFromHourMn_DI, getChangeTime(bizMsg, (String) resultMap.get("OPS_FROM_HOUR_MN")));
            // Hours of Operation To
            // ZYPEZDItemValueSetter.setValue(bizMsg.opsToHourMn_DI, (String) resultMap.get("OPS_TO_HOUR_MN"));
            ZYPEZDItemValueSetter.setValue(bizMsg.opsToHourMn_DI, getChangeTime(bizMsg, (String) resultMap.get("OPS_TO_HOUR_MN")));
            // 2018/01/09 QC#18460 Mod End
            // Loading Dock
            if (ZYPCommonFunc.hasValue((String) resultMap.get("LOAD_DOCK_AVAL_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalFlg_DI, (String) resultMap.get("LOAD_DOCK_AVAL_FLG"));
            }
            // Stairs
            if (ZYPCommonFunc.hasValue((String) resultMap.get("STAIR_CRAW_REQ_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.stairCrawReqFlg_DI, (String) resultMap.get("STAIR_CRAW_REQ_FLG"));
            }
            // Number of Stairs
            ZYPEZDItemValueSetter.setValue(bizMsg.stairCrawNum_DI, (String) resultMap.get("STAIR_CRAW_NUM"));
            // Elevator
            if (ZYPCommonFunc.hasValue((String) resultMap.get("ELEV_AVAL_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalFlg_DI, (String) resultMap.get("ELEV_AVAL_FLG"));
            }
            // Delivery Instructions
            ZYPEZDItemValueSetter.setValue(bizMsg.delyAddlCmntTxt_DI, (String) resultMap.get("DELY_ADDL_CMNT_TXT"));
            // EZD update Time
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_DI, (String) resultMap.get("EZUPTIME"));
            // EZD Update TimeZone
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_DI, (String) resultMap.get("EZUPTIMEZONE"));

            // 2017/08/24 QC#20740 Add Start
            // CPO Sorce Type
            cpoSrcTpCd = (String) resultMap.get("CPO_SRC_TP_CD");
            // 2017/08/24 QC#20740 Add Start
        }

// 2017/08/25 S21_NA#20740-1 Del Start
//        // 2017/08/24 QC#20740 Mod
//        if (ZYPCommonFunc.hasValue(cpoSrcTpCd) && !CPO_SRC_TP.DEAL_CONFIGURATOR.equals(cpoSrcTpCd) && !ZYPCommonFunc.hasValue(bizMsg.rddDt_DI)) {
//            S21SsmEZDResult dsImptOrd = NWAL2240Query.getInstance().getDsImptOrd(bizMsg);
//
//            if (dsImptOrd.isCodeNormal()) {
//                // Delivery Date
//                Map<String, Object> map = (Map<String, Object>) dsImptOrd.getResultObject();
//
//                ZYPEZDItemValueSetter.setValue(bizMsg.rddDt_DI, (String) map.get("RDD_DT"));
//            }
//        }
// 2017/08/25 S21_NA#20740-1 Del End
    }

    /**
     * Set Install Area Item
     * @param bizMsg NWAL2240CMsg
     */
    private void setIstlItemItem(NWAL2240CMsg bizMsg) {

        Map<String, Object> resultMap = null;
        // Configuration Number is not empty
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {

            S21SsmEZDResult istlDtl = NWAL2240Query.getInstance().getIstlDtlWithConfNum(bizMsg);

            if (istlDtl.isCodeNotFound()) {

                S21SsmEZDResult defultInstType = NWAL2240Query.getInstance().getDefaultInstTypeWithConfNum(bizMsg);
                if (defultInstType.isCodeNormal()) {

                    Map<String, Object> defaultInstTypeMap = (Map<String, Object>) defultInstType.getResultObject();

                    // Install Type
                    // START 2023/12/12 K.Watanabe [QC#61300, MOD]
                    //ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, (String) defaultInstTypeMap.get("SVC_ISTL_RULE_NUM"));
                    if (S21StringUtil.isEquals(bizMsg.configCatgCd_H0.getValue(), CONFIG_CATG.OUTBOUND)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, (String) defaultInstTypeMap.get("SVC_ISTL_RULE_NUM"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, (String) defaultInstTypeMap.get("SVC_DEINS_RULE_NUM"));
                        setDeinsItemItem(bizMsg);
                    }
                    // END   2023/12/12 K.Watanabe [QC#61300, MOD]
                }

                // START 2023/09/05 K.Watanabe [QC#53408, ADD]
                S21SsmEZDResult defaultAccessoryInstType = NWAL2240Query.getInstance().getDefaultAccessoryInstTypeWithConfNum(bizMsg);
                if (defaultAccessoryInstType.isCodeNormal()) {

                    Map<String, Object> defaultAccessoryInstTypeMap = (Map<String, Object>) defaultAccessoryInstType.getResultObject();

                    if (ZYPCommonFunc.hasValue((String) defaultAccessoryInstTypeMap.get("SVC_ISTL_RULE_NUM"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, (String) defaultAccessoryInstTypeMap.get("SVC_ISTL_RULE_NUM"));
                    } else { 
                        ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, SVC_ISTL_RULE_NUM_NO_INSTALL);
                    }
                }
                // END 2023/09/05 K.Watanabe [QC#53408, ADD]

                return;

            } else {

                resultMap = (Map<String, Object>) istlDtl.getResultObject();
            }

        } else {

            S21SsmEZDResult istlDtl = NWAL2240Query.getInstance().getIstlDtlWithOrdNum(bizMsg);
            if (istlDtl.isCodeNormal()) {
                resultMap = (Map<String, Object>) istlDtl.getResultObject();
            } else {
                return;
            }
        }

        if (resultMap != null) {
            // DS_CPO_ISTL_INFO_PK
            ZYPEZDItemValueSetter.setValue(bizMsg.dsImptOrdIstlInfoPk_DI, (BigDecimal) resultMap.get("DS_IMPT_ORD_ISTL_INFO_PK"));
            // Install Type
            ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, (String) resultMap.get("SVC_ISTL_RULE_NUM"));
            // Install Division
            ZYPEZDItemValueSetter.setValue(bizMsg.istlDivCd_DI, (String) resultMap.get("ISTL_DIV_CD"));
            // Install Technician Code
            ZYPEZDItemValueSetter.setValue(bizMsg.istlTechCd_DI, (String) resultMap.get("ISTL_TECH_CD"));
            // Install Technician Name
            ZYPEZDItemValueSetter.setValue(bizMsg.techNm_DI, (String) resultMap.get("TECH_NM"));
            // Install Date
            // ZYPEZDItemValueSetter.setValue(bizMsg.rqstIstlDt_DI, (String) resultMap.get("RQST_ISTL_DT")); // 2018/01/09 QC#18460 Del
            // 2018/01/09 QC#18460 Mod Start
            // Install Time
            // ZYPEZDItemValueSetter.setValue(bizMsg.rqstIstlTm_DI, (String) resultMap.get("RQST_ISTL_TM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.rqstIstlTm_DI, getChangeTime(bizMsg, (String) resultMap.get("RQST_ISTL_TM")));
            // 2018/01/09 QC#18460 Mod End
            // Install Instructions
            ZYPEZDItemValueSetter.setValue(bizMsg.istlAddlCmntTxt_DI, (String) resultMap.get("ISTL_ADDL_CMNT_TXT"));
            // EZD update Time
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_IS, (String) resultMap.get("EZUPTIME"));
            // EZD Update TimeZone
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_IS, (String) resultMap.get("EZUPTIMEZONE"));

            // 2019/11/09 QC#53993 Add Start
            ZYPEZDItemValueSetter.setValue(bizMsg.istlBySvcPrvdPtyCd_DI, (String) resultMap.get("ISTL_BY_SVC_PRVD_PTY_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.svcBySvcPrvdPtyCd_DI, (String) resultMap.get("SVC_BY_SVC_PRVD_PTY_CD"));
            // 2019/11/09 QC#53993 Add End
        }
    }

    // START 2023/12/12 K.Watanabe [QC#61300, ADD]
    /**
     * Set Deinstall Area Item
     * @param bizMsg NWAL2240CMsg
     */
    private void setDeinsItemItem(NWAL2240CMsg bizMsg) {
        if (S21StringUtil.isEquals(bizMsg.configCatgCd_H0.getValue(), CONFIG_CATG.INBOUND)) {
            S21SsmEZDResult deinstallInfo = NWAL2240Query.getInstance().getDeinstallInfo(bizMsg);
            if (deinstallInfo.isCodeNormal()) {
                Map<String, Object> deinstallInfoMap = (Map<String, Object>) deinstallInfo.getResultObject();
                String svcBySvcPrvdPtyCd = (String) deinstallInfoMap.get("SVC_BY_SVC_PRVD_PTY_CD");
                String svcDeinsRuleNum = (String) deinstallInfoMap.get("SVC_DEINS_RULE_NUM");
                if (!ZYPCommonFunc.hasValue(bizMsg.istlBySvcPrvdPtyCd_DI) && ZYPCommonFunc.hasValue(svcBySvcPrvdPtyCd)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.istlBySvcPrvdPtyCd_DI, svcBySvcPrvdPtyCd);
                }
                if (ZYPCommonFunc.hasValue(svcDeinsRuleNum)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, svcDeinsRuleNum);
                } else {
                    BigDecimal dsCpoConfigPk = (BigDecimal) deinstallInfoMap.get("DS_IMPT_ORD_CONFIG_PK");
                    BigDecimal svcMachMstrPk = (BigDecimal) deinstallInfoMap.get("SVC_MACH_MSTR_PK");
                    S21SsmEZDResult svcDeinsRuleForMdse = NWAL2240Query.getInstance().getMdlSvcDeinsRuleNum(bizMsg.glblCmpyCd.getValue(), dsCpoConfigPk, svcMachMstrPk);
                    if (svcDeinsRuleForMdse.isCodeNotFound()) {
                        S21SsmEZDResult svcDeinsRuleForRtrnDtl = NWAL2240Query.getInstance().getMdseSvcDeinsRuleNum(bizMsg.glblCmpyCd.getValue(), dsCpoConfigPk);
                        if (svcDeinsRuleForRtrnDtl.isCodeNormal()) {
                            Map<String, Object> svcDeinsRuleForRtrnDtlMap = (Map<String, Object>) svcDeinsRuleForRtrnDtl.getResultObject();
                            String svcDeinsRuleNumForRtrnDtl = (String) svcDeinsRuleForRtrnDtlMap.get("SVC_DEINS_RULE_NUM");
                            ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, svcDeinsRuleNumForRtrnDtl);
                        }
                    }
                }
            }
        }
    }
    // END   2023/12/12 K.Watanabe [QC#61300, ADD]

    /**
     * Set Site Survey Info
     * @param bizMsg NWAL2240CMsg
     */
    private void setSurveyInfo(NWAL2240CMsg bizMsg) {

        S21SsmEZDResult ssmResult = null;

        // Configuration Number is not empty
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {
            ssmResult = NWAL2240Query.getInstance().getSiteSurveybyConfNum(bizMsg);

            if (CONFIG_CATG.OUTBOUND.equals(bizMsg.configCatgCd_H0.getValue())) {
                NWAL2240Query.getInstance().getMaterialInfobyConfNum(bizMsg);
            } else {
                NWAL2240Query.getInstance().getMaterialRtnInfobyConfNum(bizMsg);
            }

        } else {
            ssmResult = NWAL2240Query.getInstance().getSiteSurveybyOrdNum(bizMsg);

            NWAL2240Query.getInstance().getMaterialInfobyOrdNum(bizMsg);
        }

        if (ssmResult.isCodeNormal()) {

            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();

            // Survey Key
            ZYPEZDItemValueSetter.setValue(bizMsg.dsImptOrdSiteSrvyPk_SS, (BigDecimal) resultMap.get("DS_IMPT_ORD_SITE_SRVY_PK"));

            // ///////////////////
            // Company Information

            // Company Name
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm_SS, (String) resultMap.get("SHIP_TO_LOC_NM"));
            // Customer Number
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustLocCd_SS, (String) resultMap.get("SHIP_TO_CUST_LOC_CD"));
            // Street
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_SS, (String) resultMap.get("SHIP_TO_FIRST_LINE_ADDR"));
            // City
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_SS, (String) resultMap.get("SHIP_TO_CTY_ADDR"));
            // Apt. or Building
            ZYPEZDItemValueSetter.setValue(bizMsg.cmpyInfoAptBldgNm_SS, (String) resultMap.get("CMPY_INFO_APT_BLDG_NM"));
            // State
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_SS, (String) resultMap.get("SHIP_TO_ST_CD"));
            // Floor
            ZYPEZDItemValueSetter.setValue(bizMsg.cmpyInfoFlNm_SS, (String) resultMap.get("CMPY_INFO_FL_NM"));
            // Postal
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_SS, (String) resultMap.get("SHIP_TO_POST_CD"));
            // Department
            ZYPEZDItemValueSetter.setValue(bizMsg.cmpyInfoDeptNm_SS, (String) resultMap.get("CMPY_INFO_DEPT_NM"));
            // Floor Protection Req
            if (ZYPCommonFunc.hasValue((String) resultMap.get("ELEV_PROT_REQ_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.elevProtReqFlg_SS, (String) resultMap.get("ELEV_PROT_REQ_FLG"));
            }

            // ///////////////////
            // Additional Comments
            ZYPEZDItemValueSetter.setValue(bizMsg.siteSrvyAddlCmntTxt_SS, (String) resultMap.get("SITE_SRVY_ADDL_CMNT_TXT"));

            // ///////////////////
            // Site Information

            // No of Steps Outside
            ZYPEZDItemValueSetter.setValue(bizMsg.otsdStepNum_SS, (String) resultMap.get("OTSD_STEP_NUM"));
            // No of Steps Inside
            ZYPEZDItemValueSetter.setValue(bizMsg.insdStepNum_SS, (String) resultMap.get("INSD_STEP_NUM"));
            // Step Crawler required
            if (ZYPCommonFunc.hasValue((String) resultMap.get("STAIR_CRAW_REQ_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.stairCrawReqFlg_SS, (String) resultMap.get("STAIR_CRAW_REQ_FLG"));
            }
            // No of Flights
            ZYPEZDItemValueSetter.setValue(bizMsg.flgtStairNum_SS, (String) resultMap.get("FLGT_STAIR_NUM"));
            // Width of stairs and landings
            ZYPEZDItemValueSetter.setValue(bizMsg.stairAndLdgWdt_SS, (BigDecimal) resultMap.get("STAIR_AND_LDG_WDT"));
            // Loading Dock Available
            if (ZYPCommonFunc.hasValue((String) resultMap.get("LOAD_DOCK_AVAL_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalFlg_SS, (String) resultMap.get("LOAD_DOCK_AVAL_FLG"));
            }
            // Dock Height
            ZYPEZDItemValueSetter.setValue(bizMsg.loadDockHgt_SS, (BigDecimal) resultMap.get("LOAD_DOCK_HGT"));
            // 2018/01/09 QC#18460 Mod Start
            // Delivery Hours From
            // ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalFromHourMn_SS, (String) resultMap.get("LOAD_DOCK_AVAL_FROM_HOUR_MN"));
            ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalFromHourMn_SS, getChangeTime(bizMsg, (String) resultMap.get("LOAD_DOCK_AVAL_FROM_HOUR_MN")));
            // Delivery Hours To
            // ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalToHourMn_SS, (String) resultMap.get("LOAD_DOCK_AVAL_TO_HOUR_MN"));
            ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalToHourMn_SS, getChangeTime(bizMsg, (String) resultMap.get("LOAD_DOCK_AVAL_TO_HOUR_MN")));
            // 2018/01/09 QC#18460 Mod End
            // Tractor Trailer Accessible
            if (ZYPCommonFunc.hasValue((String) resultMap.get("TRCTR_AND_TRAIL_ACCS_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.trctrAndTrailAccsFlg_SS, (String) resultMap.get("TRCTR_AND_TRAIL_ACCS_FLG"));
            }
            // 2018/01/09 QC#18460 Mod Start
            // Timestop
            // ZYPEZDItemValueSetter.setValue(bizMsg.carrDelyTmHourMn_SS, (String) resultMap.get("CARR_DELY_TM_HOUR_MN"));
            ZYPEZDItemValueSetter.setValue(bizMsg.carrDelyTmHourMn_SS, getChangeTime(bizMsg, (String) resultMap.get("CARR_DELY_TM_HOUR_MN")));
            // 2018/01/09 QC#18460 Mod End
            // Timestop
            ZYPEZDItemValueSetter.setValue(bizMsg.delyTrnspOptCd_SS, (String) resultMap.get("DELY_TRNSP_OPT_CD"));

            // ///////////////////
            // Elevator Information & Dimensions
            // Elevator Available
            if (ZYPCommonFunc.hasValue((String) resultMap.get("ELEV_AVAL_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalFlg_SS, (String) resultMap.get("ELEV_AVAL_FLG"));
            }
            // 2018/01/09 QC#18460 Mod Start
            // Elevator Hoiurs From
            // ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalFromHourMn_SS, (String) resultMap.get("ELEV_AVAL_FROM_HOUR_MN"));
            ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalFromHourMn_SS, getChangeTime(bizMsg, (String) resultMap.get("ELEV_AVAL_FROM_HOUR_MN")));
            // Elevator Hoiurs To
            // ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalToHourMn_SS, (String) resultMap.get("ELEV_AVAL_TO_HOUR_MN"));
            ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalToHourMn_SS, getChangeTime(bizMsg, (String) resultMap.get("ELEV_AVAL_TO_HOUR_MN")));
            // 2018/01/09 QC#18460 Mod End
            // Elevetor Appinted Needed
            if (ZYPCommonFunc.hasValue((String) resultMap.get("ELEV_APPT_REQ_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.elevApptReqFlg_SS, (String) resultMap.get("ELEV_APPT_REQ_FLG"));
            }
            // Elevator Contact Parson Name
            ZYPEZDItemValueSetter.setValue(bizMsg.elevCtacPsnNm_SS, (String) resultMap.get("ELEV_CTAC_PSN_NM"));
            // Elevator Contact Phone
            ZYPEZDItemValueSetter.setValue(bizMsg.elevCtacTelNum_SS, (String) resultMap.get("ELEV_CTAC_TEL_NUM"));

            // Building Entrance(Height)
            ZYPEZDItemValueSetter.setValue(bizMsg.bldgEntDoorHgt_SS, (BigDecimal) resultMap.get("BLDG_ENT_DOOR_HGT"));
            // Building Entrance(Width)
            ZYPEZDItemValueSetter.setValue(bizMsg.bldgEntDoorWdt_SS, (BigDecimal) resultMap.get("BLDG_ENT_DOOR_WDT"));
            // Building Entrance(Corridor Width)
            ZYPEZDItemValueSetter.setValue(bizMsg.crdrWdt_SS, (BigDecimal) resultMap.get("CRDR_WDT"));
            // Building Entrance(Door Width)
            ZYPEZDItemValueSetter.setValue(bizMsg.doorWdt_SS, (BigDecimal) resultMap.get("DOOR_WDT"));

            // Elevator(Width)
            ZYPEZDItemValueSetter.setValue(bizMsg.elevWdt_SS, (BigDecimal) resultMap.get("ELEV_WDT"));
            // Elevator(Depth)
            ZYPEZDItemValueSetter.setValue(bizMsg.elevDepthNum_SS, (BigDecimal) resultMap.get("ELEV_DEPTH_NUM"));
            // Elevator(Capacity)
            ZYPEZDItemValueSetter.setValue(bizMsg.elevCapWt_SS, (BigDecimal) resultMap.get("ELEV_CAP_WT"));
            // Door Opening(Height)
            ZYPEZDItemValueSetter.setValue(bizMsg.elevDoorHgt_SS, (BigDecimal) resultMap.get("ELEV_DOOR_HGT"));
            // Door Opening(Height)
            ZYPEZDItemValueSetter.setValue(bizMsg.elevDoorWdt_SS, (BigDecimal) resultMap.get("ELEV_DOOR_WDT"));

            // EZD update Time
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_SS, (String) resultMap.get("EZUPTIME"));
            // EZD Update TimeZone
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_SS, (String) resultMap.get("EZUPTIMEZONE"));
        }
    }

    /**
     * Set Contact List
     * @param bizMsg NWAL2240CMsg
     * @param sMsg NWAL2240SMsg
     */
    private void setCtacList(NWAL2240CMsg bizMsg, NWAL2240SMsg sMsg) {

        // Configuration Number is not empty
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {
            NWAL2240Query.getInstance().getCtacListbyConfNum(bizMsg, sMsg);
        } else {
            NWAL2240Query.getInstance().getCtacListbyOrdNum(bizMsg, sMsg);
        }

        for (int i = 0; i < bizMsg.C.length(); i++) {
            if (i < sMsg.C.getValidCount()) {
                EZDMsg.copy(sMsg.C.no(i), null, bizMsg.C.no(i), null);
            } else {
                break;
            }
        }
        bizMsg.C.setValidCount(sMsg.C.getValidCount());
    }

    /**
     * Add Contact Row
     * @param cMsg NWAL2240CMsg
     * @param sMsg NWAL2240SMsg
     */
    private void doProcess_NWAL2240Scrn00_Add_Row(NWAL2240CMsg bizMsg, NWAL2240SMsg sMsg) {

        copyCMsgToSMsg(bizMsg, sMsg);
        int addRowIndex = sMsg.C.getValidCount();

        sMsg.C.setValidCount(addRowIndex + 1);

        ZYPEZDItemValueSetter.setValue(sMsg.C.no(addRowIndex).ctacPsnTpCd_C0, bizMsg.ctacPsnTpCd_L0.no(0));

        copySMsgToCMsg(bizMsg, sMsg);
    }

    /**
     * Delete Contact Row
     * @param cMsg NWAL2240CMsg
     * @param sMsg NWAL2240SMsg
     */
    private void doProcess_NWAL2240Scrn00_Delete_Row(NWAL2240CMsg bizMsg, NWAL2240SMsg sMsg) {

        copyCMsgToSMsg(bizMsg, sMsg);

        List<Integer> chkYList = ZYPTableUtil.getSelectedRows(bizMsg.C, "delFlg_C0", ZYPConstant.FLG_ON_Y);

        for (int chkYRow : chkYList) {
            if (ZYPCommonFunc.hasValue(bizMsg.C.no(chkYRow).dsImptOrdCtacPsnPk_C0)) {
                EZDMsg.copy(sMsg.C.no(chkYRow), "C0", sMsg.D.no(sMsg.D.getValidCount()), "D0");
                sMsg.D.setValidCount(sMsg.D.getValidCount() + 1);
            }
        }

        ZYPTableUtil.deleteRows(sMsg.C, chkYList);

        copySMsgToCMsg(bizMsg, sMsg);
    }

    /**
     * Delete Contact Row
     * @param cMsg NWAL2240CMsg
     * @param sMsg NWAL2240SMsg
     */
    private void clearAll(NWAL2240CMsg bizMsg, NWAL2240SMsg sMsg) {
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.C);
        ZYPTableUtil.clear(sMsg.D);

        bizMsg.dsImptOrdSiteSrvyPk_SS.clear();
        bizMsg.shipToLocNm_SS.clear();
        bizMsg.shipToCustLocCd_SS.clear();
        bizMsg.shipToFirstLineAddr_SS.clear();
        bizMsg.shipToCtyAddr_SS.clear();
        bizMsg.cmpyInfoAptBldgNm_SS.clear();
        bizMsg.shipToStCd_SS.clear();
        bizMsg.cmpyInfoFlNm_SS.clear();
        bizMsg.shipToPostCd_SS.clear();
        bizMsg.cmpyInfoDeptNm_SS.clear();
        bizMsg.elevProtReqFlg_SS.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.siteSrvyAddlCmntTxt_SS.clear();
        bizMsg.otsdStepNum_SS.clear();
        bizMsg.insdStepNum_SS.clear();
        bizMsg.stairCrawReqFlg_SS.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.flgtStairNum_SS.clear();
        bizMsg.stairAndLdgWdt_SS.clear();
        bizMsg.loadDockAvalFlg_SS.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.loadDockHgt_SS.clear();
        bizMsg.loadDockAvalFromHourMn_SS.clear();
        bizMsg.loadDockAvalToHourMn_SS.clear();
        bizMsg.trctrAndTrailAccsFlg_SS.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.carrDelyTmHourMn_SS.clear();
        bizMsg.delyTrnspOptCd_SS.clear();
        bizMsg.elevAvalFlg_SS.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.elevApptReqFlg_SS.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.elevAvalFromHourMn_SS.clear();
        bizMsg.elevAvalToHourMn_SS.clear();
        bizMsg.elevCtacPsnNm_SS.clear();
        bizMsg.elevCtacTelNum_SS.clear();
        bizMsg.bldgEntDoorHgt_SS.clear();
        bizMsg.bldgEntDoorWdt_SS.clear();
        bizMsg.crdrWdt_SS.clear();
        bizMsg.doorWdt_SS.clear();
        bizMsg.elevWdt_SS.clear();
        bizMsg.elevDepthNum_SS.clear();
        bizMsg.elevCapWt_SS.clear();
        bizMsg.elevDoorHgt_SS.clear();
        bizMsg.elevDoorWdt_SS.clear();
        bizMsg.ezUpTime_SS.clear();
        bizMsg.ezUpTimeZone_SS.clear();
        // 2016/07/11 QC#5030 Mod Start
        //ZYPEZDItemValueSetter.setValue(bizMsg.rqstIstlTm_AP, bizMsg.xxTpCd.no(0));
        //ZYPEZDItemValueSetter.setValue(bizMsg.opsFromHourMn_AP, bizMsg.xxTpCd.no(0));
        //ZYPEZDItemValueSetter.setValue(bizMsg.opsToHourMn_AP, bizMsg.xxTpCd.no(0));
        //ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalFromHourMn_AP, bizMsg.xxTpCd.no(0));
        //ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalToHourMn_AP, bizMsg.xxTpCd.no(0));
        //ZYPEZDItemValueSetter.setValue(bizMsg.carrDelyTmHourMn_AP, bizMsg.xxTpCd.no(0));
        //ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalFromHourMn_AP, bizMsg.xxTpCd.no(0));
        //ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalToHourMn_AP, bizMsg.xxTpCd.no(0));
        bizMsg.rqstIstlTm_AP.clear();
        bizMsg.opsFromHourMn_AP.clear();
        bizMsg.opsToHourMn_AP.clear();
        bizMsg.loadDockAvalFromHourMn_AP.clear();
        bizMsg.loadDockAvalToHourMn_AP.clear();
        bizMsg.carrDelyTmHourMn_AP.clear();
        bizMsg.elevAvalFromHourMn_AP.clear();
        bizMsg.elevAvalToHourMn_AP.clear();
        // 2016/07/11 QC#5030 Mod End
        bizMsg.dsImptOrdIstlInfoPk_DI.clear();
        bizMsg.svcIstlRuleNum_DI.clear();
        bizMsg.istlDivCd_DI.clear();
        bizMsg.istlTechCd_DI.clear();
        bizMsg.techNm_DI.clear();
        // bizMsg.rqstIstlDt_DI.clear();  // 2018/01/09 QC#18460 Del
        bizMsg.rqstIstlTm_DI.clear();
        bizMsg.istlAddlCmntTxt_DI.clear();
        bizMsg.ezUpTime_IS.clear();
        bizMsg.ezUpTimeZone_IS.clear();
        bizMsg.dsImptOrdDelyInfoPk_DI.clear();
//        bizMsg.rddDt_DI.clear(); // 2017/08/25 S21_NA#20740-1 Del
        bizMsg.opsFromHourMn_DI.clear();
        bizMsg.opsToHourMn_DI.clear();
        bizMsg.loadDockAvalFlg_DI.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.stairCrawReqFlg_DI.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.stairCrawNum_DI.clear();
        bizMsg.elevAvalFlg_DI.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.delyAddlCmntTxt_DI.clear();
        bizMsg.ezUpTime_DI.clear();
        bizMsg.ezUpTimeZone_DI.clear();
        bizMsg.dsOrdCatgDescTxt_H0.clear();
        bizMsg.dsOrdTpDescTxt_H0.clear();
        bizMsg.shipToCustAcctCd_H0.clear();
        bizMsg.shipToCustLocCd_H0.clear();
        // 2017/08/30 S21_NA#20842 Mod Start
//        bizMsg.shipToLocNm_H0.clear();
        bizMsg.shipToCustAcctNm_H0.clear();
        // 2017/08/30 S21_NA#20842 Mod End
        bizMsg.firstLineAddr_H0.clear();
        bizMsg.scdLineAddr_H0.clear();
        bizMsg.dsImptOrdConfigPk_H0.clear();
        bizMsg.dsOrdCatgDescTxt_H0.clear();
        bizMsg.dsOrdTpDescTxt_H0.clear();
        bizMsg.shipToCustAcctCd_H0.clear();
        bizMsg.shipToCustLocCd_H0.clear();
        // 2017/08/30 S21_NA#20842 Mod End
//        bizMsg.shipToLocNm_H0.clear();
        bizMsg.shipToCustAcctNm_H0.clear();
        // 2017/08/30 S21_NA#20842 Mod End
        bizMsg.firstLineAddr_H0.clear();
        bizMsg.scdLineAddr_H0.clear();
        // 2018/01/09 QC#18460 Add Start
        bizMsg.tmZoneCd_H0.clear();
        bizMsg.ctryCd_H0.clear();
        bizMsg.postCd_H0.clear();
        // 2018/01/09 QC#18460 Add End
        bizMsg.xxSvcFromHourMnTxt_OP.clear();
        bizMsg.xxSvcToHourMnTxt_OP.clear();
        bizMsg.xxSvcFromHourMnTxt_RQ.clear();
        bizMsg.xxSvcFromHourMnTxt_LD.clear();
        bizMsg.xxSvcToHourMnTxt_LD.clear();
        bizMsg.xxSvcFromHourMnTxt_CD.clear();
        bizMsg.xxSvcFromHourMnTxt_EA.clear();
        bizMsg.xxSvcToHourMnTxt_EA.clear();
        bizMsg.xxRsltFlg_DI.clear();
        bizMsg.xxRsltFlg_SS.clear();
        bizMsg.xxRsltFlg_CO.clear();
    }
}
