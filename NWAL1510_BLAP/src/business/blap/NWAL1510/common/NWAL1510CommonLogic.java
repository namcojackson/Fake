/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1510.common;

import static business.blap.NWAL1510.constant.NWAL1510Constant.AM_CD;
import static business.blap.NWAL1510.constant.NWAL1510Constant.AM_PM_PULLDOWN_CD;
import static business.blap.NWAL1510.constant.NWAL1510Constant.AM_PM_PULLDOWN_NM;
import static business.blap.NWAL1510.constant.NWAL1510Constant.BUSINESS_ID;
import static business.blap.NWAL1510.constant.NWAL1510Constant.CTAC_PT_TP_CD_FAX;
import static business.blap.NWAL1510.constant.NWAL1510Constant.CTAC_PT_TP_CD_MAIL;
import static business.blap.NWAL1510.constant.NWAL1510Constant.CTAC_PT_TP_CD_PHONE;
import static business.blap.NWAL1510.constant.NWAL1510Constant.DELY_TP_CD_INSTALL;
import static business.blap.NWAL1510.constant.NWAL1510Constant.MESSAGE_KIND_ERROR;
import static business.blap.NWAL1510.constant.NWAL1510Constant.MESSAGE_NAME_SCHD_CMNT;
import static business.blap.NWAL1510.constant.NWAL1510Constant.MODE_DELETE;
import static business.blap.NWAL1510.constant.NWAL1510Constant.MODE_MODIFY;
import static business.blap.NWAL1510.constant.NWAL1510Constant.MODE_NEW;
import static business.blap.NWAL1510.constant.NWAL1510Constant.NMZM0347E;
import static business.blap.NWAL1510.constant.NWAL1510Constant.NMZM0348E;
import static business.blap.NWAL1510.constant.NWAL1510Constant.NMZM0349E;
import static business.blap.NWAL1510.constant.NWAL1510Constant.NOON;
import static business.blap.NWAL1510.constant.NWAL1510Constant.NWAM0269E;
import static business.blap.NWAL1510.constant.NWAL1510Constant.NWAM0325E;
import static business.blap.NWAL1510.constant.NWAL1510Constant.NWAM0960E;
import static business.blap.NWAL1510.constant.NWAL1510Constant.NZZM0002I;
import static business.blap.NWAL1510.constant.NWAL1510Constant.PM_CD;
import static business.blap.NWAL1510.constant.NWAL1510Constant.SVC_ISTL_RULE_NUM_NO_INSTALL;
import static business.blap.NWAL1510.constant.NWAL1510Constant.SVC_ISTL_RULE_NUM_NO_DEINSTALL;
import static business.blap.NWAL1510.constant.NWAL1510Constant.TAB_CONTACT;
import static business.blap.NWAL1510.constant.NWAL1510Constant.TAB_INSTALL;
import static business.blap.NWAL1510.constant.NWAL1510Constant.TAB_SURVEY;
import static business.blap.NWAL1510.constant.NWAL1510Constant.TIME_SUBSTRING_FROM;
import static business.blap.NWAL1510.constant.NWAL1510Constant.TIME_SUBSTRING_TO;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NWAL1510.NWAL1510CMsg;
import business.blap.NWAL1510.NWAL1510Query;
import business.blap.NWAL1510.NWAL1510SMsg;
import business.blap.NWAL1510.NWAL1510_CCMsg;
import business.blap.NWAL1510.NWAL1510_CSMsg;
import business.db.SVC_PRVD_PTYTMsg;
import business.parts.NMZC002001PMsg;
import business.parts.NWZC183001PMsg;
import business.parts.NWZC183001_cpoCtacPsnInfoListPMsg;
import business.parts.NWZC183001_cpoDelyInfoListPMsg;
import business.parts.NWZC183001_cpoInstInfoListPMsg;
import business.parts.NWZC183001_siteSrvyInfoListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.api.NWZ.NWZC183001.NWZC183001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * D&I/Contact/Site Survey Common Business Logic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/27   Fujitsu         S.Ohki          Create          N/A
 * 2015/11/25   Fujitsu         T.Ishii         Update          S21_NA#879
 * 2016/02/09   Fujitsu         S.Ohki          Update          S21_NA#1622
 * 2016/02/09   Fujitsu         M.Yamada        Update          S21_NA#7626
 * 2016/07/28   Fujitsu         H.Ikeda         Update          S21_NA#5030
 * 2016/08/02   Fujitsu         M.Hara          Update          S21_NA#7704, S21_NA#7306
 * 2016/09/20   Fujitsu         R.Nakamura      Update          QC#13738
 * 2016/10/21   Fujitsu         M.Ohno          Update          S21_NA#15354
 * 2017/02/09   Fujitsu         M.Ohno          Update          S21_NA#17500
 * 2017/02/27   Fujitsu         T.Yoshida       Update          S21_NA#16035
 * 2017/03/01   Fujitsu         S.Ohki          Update          S21_NA#17807
 * 2017/08/08   Fujitsu         H.Nagashima     Update          S21_NA#16452
 * 2017/08/29   Fujitsu         M.Ohno          Update          S21_NA#20803
 * 2017/09/13   Fujitsu         S.Takami        Update          S21_NA#21069
 * 2018/1/18    CITS            T.Hakodate      Update          S21_NA#18460(Sol#087)
 * 2018/07/18   Fujitsu         K.Ishizuka      Update          S21_NA#26188
 * 2018/08/02   Fujitsu         K.Ishizuka      Update          S21_NA#26188-2
 * 2019/01/16   Fujitsu         S.Kosaka        Update          QC#29642
 * 2019/10/25   Fujitsu         Mz.Takahashi    Update          S21_NA#53993
 * 2019/12/26   Fujitsu         S.Kosaka        Update          QC#54725
 * 2020/07/17   Fujitsu         T.Ogura         Update          QC#57340
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 *</pre>
 */
public class NWAL1510CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * Set Service Install Rule
     * @param bizMsg NWAL1510CMsg
     * @param glblCmpyCd Global Company Code
     */
    public static void setSvcIstlRulePulldown(NWAL1510CMsg bizMsg, String glblCmpyCd) {
        S21SsmEZDResult result = NWAL1510Query.getInstance().getSvcIstlRuleList(bizMsg, glblCmpyCd);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            for (Map<String, Object> map : list) {
                if (map != null && map.get("SVC_ISTL_RULE_NUM") != null) {
                    bizMsg.svcIstlRuleNum_L0.no(i).setValue((String) map.get("SVC_ISTL_RULE_NUM"));
                    bizMsg.svcIstlRuleNm_L0.no(i).setValue((String) map.get("SVC_ISTL_RULE_NM"));
                    i++;
                }
            }
        }
    }

    /**
     * Set AmPm Pulldown List
     * @param bizMsg NWAL1510CMsg
     */
    public static void setAmPmPulldownList(NWAL1510CMsg bizMsg) {
        for (int idx = 0; idx < AM_PM_PULLDOWN_CD.length; idx++) {
            bizMsg.xxTpCd.no(idx).setValue(AM_PM_PULLDOWN_CD[idx]);
            bizMsg.xxTpNm.no(idx).setValue(AM_PM_PULLDOWN_NM[idx]);
        }
    }

    // S21_NA#7306
    /**
     * setCtacPsnTpPulldown
     * @param bizMsg NWAL1510CMsg
     * @param glblCmpyCd String
     */
    public static void setCtacPsnTpPulldown(NWAL1510CMsg bizMsg, String glblCmpyCd) {
        S21SsmEZDResult result = NWAL1510Query.getInstance().getCtacPsnTpPulldown(bizMsg, glblCmpyCd);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            for (Map<String, Object> map : list) {
                bizMsg.ctacPsnTpCd_L0.no(i).setValue((String) map.get("CTAC_TP_CD"));
                bizMsg.ctacTpNm_L0.no(i).setValue((String) map.get("CTAC_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).ctacCustRefTpCd_L, (String) map.get("CTAC_CUST_REF_TP_CD"));  // QC#16452 add
                i++;
            }
        }
    }

    /**
     * From CMsg to SMsg
     * @param cMsg NWAL1510CMsg
     * @param sMsg NWAL1510SMsg
     */
    public static void copyCMsgToSMsg(NWAL1510CMsg cMsg, NWAL1510SMsg sMsg) {
        int idx = 0;
        for (; idx < cMsg.C.length(); idx++) {
            if (idx < cMsg.C.getValidCount()) {
                EZDMsg.copy(cMsg.C.get(idx), null, sMsg.C.get(idx), null);
            } else {
                break;
            }
        }
        sMsg.C.setValidCount(idx);
    }

    /**
     * From SMsg to CMsg
     * @param cMsg NWAL1510CMsg
     * @param sMsg NWAL1510SMsg
     */
    public static void copySMsgToCMsg(NWAL1510CMsg cMsg, NWAL1510SMsg sMsg) {
        int idx = 0;
        for (; idx < cMsg.C.length(); idx++) {
            if (idx < sMsg.C.getValidCount()) {
                EZDMsg.copy(sMsg.C.no(idx), null, cMsg.C.no(idx), null);
            } else {
                break;
            }
        }
        cMsg.C.setValidCount(idx);
    }

    /**
     * Change time in 24-hour notation
     * @param time String
     * @param apCode String
     * @return changeTime String
     */
    public static String changeTime24h(String time, String apCode) {
        if (!ZYPCommonFunc.hasValue(time)) {
            return time;
        }

        if (AM_CD.equals(apCode)) {
            return time;
        }

        return String.valueOf(Integer.parseInt(time.substring(0, 2)) + 12) + time.substring(2);
    }

    /**
     * Forcibly Update Of Multiple Configuration
     * @param time String
     * @param apCode String
     * @return changeTime String
     */
    @SuppressWarnings("unchecked")
    public static void forciblyUpdateOfMultiConfig(NWAL1510CMsg bizMsg, NWAL1510SMsg sMsg, String glblCmpyCd, BigDecimal dsCpoConfigPk) {

        BigDecimal dsCpoDelyInfoPk = null;
        BigDecimal dsCpoIstlInfoPk = null;
        BigDecimal dsSiteSrvyPk = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // if (bizMsg.H.getValidCount() > 1) { // 2018/07/18 S21_NA#26188 Del Condition

            // Get Ship To Customer Location Code
            // 2017/09/13 S21_NA#21069 Mod Start
//            S21SsmEZDResult shipToRes = NWAL1510Query.getInstance().searchWithConfNum(bizMsg);
        // START 07/17/2020 T.Ogura [QC#57340,ADD]
        if (!ZYPCommonFunc.hasValue(dsCpoConfigPk)) {
            bizMsg.setMessageInfo(NWAM0325E, new String[] {"DS_CPO_CONFIG_PK" });
            return;
        }
        // END   07/17/2020 T.Ogura [QC#57340,ADD]
            S21SsmEZDResult shipToRes = NWAL1510Query.getInstance().searchWithConfPk(bizMsg, dsCpoConfigPk);
            // 2017/09/13 S21_NA#21069 Mod End
            if (shipToRes.isCodeNormal()) {
                // Map<String, Object> resultMap = (Map<String, Object>) shipToRes.getResultObject();
                resultMap = (Map<String, Object>) shipToRes.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustLocCd_H0, (String) resultMap.get("SHIP_TO_CUST_LOC_CD"));
                // 2017/09/13 S21_NA#21069 Add Start
                ZYPEZDItemValueSetter.setValue(bizMsg.billToCustLocCd_H0, (String) resultMap.get("BILL_TO_CUST_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd_H0, (String) resultMap.get("SOLD_TO_CUST_LOC_CD"));
                // 2017/09/13 S21_NA#21069 Add End
                // 2018/07/18 S21_NA#26188 Add Start
                ZYPEZDItemValueSetter.setValue(bizMsg.ctryCd_H0, (String) resultMap.get("SHIP_TO_CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.postCd_H0, (String) resultMap.get("SHIP_TO_POST_CD"));
                // 2018/07/18 S21_NA#26188 Add End
            }

            // Get Delivery Information Primary Key // 2018/07/18 S21_NA#26188 Move Logic1 From
            // S21SsmEZDResult delvDtl = NWAL1510Query.getInstance().getDeliveryDtlWithConfNum(bizMsg);
            // if (delvDtl.isCodeNormal()) {
            //     Map<String, Object> resultMap = (Map<String, Object>) delvDtl.getResultObject();
            //     dsCpoDelyInfoPk = (BigDecimal) resultMap.get("DS_CPO_DELY_INFO_PK");
            // }

            // Get Install Information Primary Key // 2018/07/18 S21_NA#26188 Move Logic2 From
            // S21SsmEZDResult istlDtl = NWAL1510Query.getInstance().getIstlDtlWithConfNum(bizMsg);
            // if (istlDtl.isCodeNormal()) {
            //     Map<String, Object> resultMap = (Map<String, Object>) istlDtl.getResultObject();
            //     dsCpoIstlInfoPk = (BigDecimal) resultMap.get("DS_CPO_ISTL_INFO_PK");
            // }

            // Get Site Survey Primary Key // 2018/07/18 S21_NA#26188 Move Logic3 From
            // S21SsmEZDResult siteSvyDtl = NWAL1510Query.getInstance().getSiteSurveybyConfNum(bizMsg);
            // if (siteSvyDtl.isCodeNormal()) {
            //     Map<String, Object> resultMap = (Map<String, Object>) siteSvyDtl.getResultObject();
            //     dsSiteSrvyPk = (BigDecimal) resultMap.get("DS_SITE_SRVY_PK");
            // }
        // } 2018/07/18 S21_NA#26188 Del Condition

        // Change time to 24H notation
        String chngOpsFromHourMnDi = NWAL1510CommonLogic.changeTime24h(bizMsg.opsFromHourMn_DI.getValue(), bizMsg.opsFromHourMn_AP.getValue());
        String chngOpsToHourMnDi = NWAL1510CommonLogic.changeTime24h(bizMsg.opsToHourMn_DI.getValue(), bizMsg.opsToHourMn_AP.getValue());
        String chngRqstIstlTmDi = NWAL1510CommonLogic.changeTime24h(bizMsg.rqstIstlTm_DI.getValue(), bizMsg.rqstIstlTm_AP.getValue());
        String chngLoadDockAvalFromHourMnSs = NWAL1510CommonLogic.changeTime24h(bizMsg.loadDockAvalFromHourMn_SS.getValue(), bizMsg.loadDockAvalFromHourMn_AP.getValue());
        String chngLoadDockAvalToHourMnSs = NWAL1510CommonLogic.changeTime24h(bizMsg.loadDockAvalToHourMn_SS.getValue(), bizMsg.loadDockAvalToHourMn_AP.getValue());
        String chngCarrDelyTmHourMnSs = NWAL1510CommonLogic.changeTime24h(bizMsg.carrDelyTmHourMn_SS.getValue(), bizMsg.carrDelyTmHourMn_AP.getValue());
        String chngElevAvalFromHourMnSs = NWAL1510CommonLogic.changeTime24h(bizMsg.elevAvalFromHourMn_SS.getValue(), bizMsg.elevAvalFromHourMn_AP.getValue());
        String chngElevAvalToHourMnSs = NWAL1510CommonLogic.changeTime24h(bizMsg.elevAvalToHourMn_SS.getValue(), bizMsg.elevAvalToHourMn_AP.getValue());

        // Header
        NWZC183001PMsg pMsg = new NWZC183001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, bizMsg.cpoOrdNum_H0);
        
        // 2018/07/18 S21_NA#26188 Add Start
        String tabTp = bizMsg.xxDplyTab.getValue();
        boolean massApplyMode = ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDplyCtrlFlg_MU.getValue());
        // 2018/07/18 S21_NA#26188 Add End

        // if (isInputItemDelivery(bizMsg)) { // 2018/07/18 S21_NA#26188 Mod Condition
        if (TAB_INSTALL.equals(tabTp)) {
            
            // 2018/07/18 S21_NA#26188 Move Logic1 To Start
            S21SsmEZDResult delvDtl = NWAL1510Query.getInstance().getDeliveryDtlWithConfNum(bizMsg);
            if (delvDtl.isCodeNormal()) {
                resultMap = (Map<String, Object>) delvDtl.getResultObject();
                dsCpoDelyInfoPk = (BigDecimal) resultMap.get("DS_CPO_DELY_INFO_PK");
            }
            // 2018/07/18 S21_NA#26188 Move Logic1 To Enr

            // CPO Delivery Info List
            NWZC183001_cpoDelyInfoListPMsg delyInfo = (NWZC183001_cpoDelyInfoListPMsg) pMsg.cpoDelyInfoList.get(0);

            if (ZYPCommonFunc.hasValue(dsCpoDelyInfoPk)) {
                ZYPEZDItemValueSetter.setValue(delyInfo.xxModeCd, MODE_MODIFY);
                ZYPEZDItemValueSetter.setValue(delyInfo.dsCpoDelyInfoPk, dsCpoDelyInfoPk);
            } else if (ZYPCommonFunc.hasValue(bizMsg.dsCpoDelyInfoPk_DI)) {
                ZYPEZDItemValueSetter.setValue(delyInfo.xxModeCd, MODE_MODIFY);
                ZYPEZDItemValueSetter.setValue(delyInfo.dsCpoDelyInfoPk, bizMsg.dsCpoDelyInfoPk_DI);
            } else {
                ZYPEZDItemValueSetter.setValue(delyInfo.xxModeCd, MODE_NEW);
            }

            ZYPEZDItemValueSetter.setValue(delyInfo.dsCpoConfigPk, dsCpoConfigPk);
            ZYPEZDItemValueSetter.setValue(delyInfo.dsDelyTpCd, DELY_TP_CD_INSTALL);
            // 2018/07/18 S21_NA#26188 Mod Start
            // ZYPEZDItemValueSetter.setValue(delyInfo.opsFromHourMn, chngOpsFromHourMnDi);
            // ZYPEZDItemValueSetter.setValue(delyInfo.opsToHourMn, chngOpsToHourMnDi);
            // ZYPEZDItemValueSetter.setValue(delyInfo.loadDockAvalFlg, bizMsg.loadDockAvalFlg_DI);
            // ZYPEZDItemValueSetter.setValue(delyInfo.stairCrawReqFlg, bizMsg.stairCrawReqFlg_DI);
            // ZYPEZDItemValueSetter.setValue(delyInfo.stairCrawNum, bizMsg.stairCrawNum_DI);
            // ZYPEZDItemValueSetter.setValue(delyInfo.elevAvalFlg, bizMsg.elevAvalFlg_DI);
            // ZYPEZDItemValueSetter.setValue(delyInfo.delyAddlCmntTxt, bizMsg.delyAddlCmntTxt_DI);
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_D0.getValue()) || !massApplyMode) {
                ZYPEZDItemValueSetter.setValue(delyInfo.opsFromHourMn, chngOpsFromHourMnDi);
                ZYPEZDItemValueSetter.setValue(delyInfo.opsToHourMn, chngOpsToHourMnDi);
            } else if(ZYPCommonFunc.hasValue(dsCpoDelyInfoPk)){
                ZYPEZDItemValueSetter.setValue(delyInfo.opsFromHourMn, getConvertTime(bizMsg, (String) resultMap.get("OPS_FROM_HOUR_MN")));
                ZYPEZDItemValueSetter.setValue(delyInfo.opsToHourMn, getConvertTime(bizMsg, (String) resultMap.get("OPS_TO_HOUR_MN")));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_D1.getValue()) || !massApplyMode) {
                ZYPEZDItemValueSetter.setValue(delyInfo.loadDockAvalFlg, bizMsg.loadDockAvalFlg_DI);
            } else if(ZYPCommonFunc.hasValue(dsCpoDelyInfoPk)){
                ZYPEZDItemValueSetter.setValue(delyInfo.loadDockAvalFlg, (String) resultMap.get("LOAD_DOCK_AVAL_FLG"));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_D2.getValue()) || !massApplyMode) {
                ZYPEZDItemValueSetter.setValue(delyInfo.stairCrawReqFlg, bizMsg.stairCrawReqFlg_DI);
                ZYPEZDItemValueSetter.setValue(delyInfo.stairCrawNum, bizMsg.stairCrawNum_DI);
            } else if(ZYPCommonFunc.hasValue(dsCpoDelyInfoPk)){
                ZYPEZDItemValueSetter.setValue(delyInfo.stairCrawReqFlg, (String) resultMap.get("STAIR_CRAW_REQ_FLG"));
                ZYPEZDItemValueSetter.setValue(delyInfo.stairCrawNum, (String) resultMap.get("STAIR_CRAW_NUM"));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_D3.getValue()) || !massApplyMode) {
                ZYPEZDItemValueSetter.setValue(delyInfo.elevAvalFlg, bizMsg.elevAvalFlg_DI);
            } else if(ZYPCommonFunc.hasValue(dsCpoDelyInfoPk)){
                ZYPEZDItemValueSetter.setValue(delyInfo.elevAvalFlg, (String) resultMap.get("ELEV_AVAL_FLG"));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_D4.getValue()) || !massApplyMode) {
                ZYPEZDItemValueSetter.setValue(delyInfo.delyAddlCmntTxt, bizMsg.delyAddlCmntTxt_DI);
            } else if(ZYPCommonFunc.hasValue(dsCpoDelyInfoPk)){
                ZYPEZDItemValueSetter.setValue(delyInfo.delyAddlCmntTxt, (String) resultMap.get("DELY_ADDL_CMNT_TXT"));
            }
            // 2019/12/26 QC#54725 Add Srart
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_DB.getValue()) || !massApplyMode) {
                ZYPEZDItemValueSetter.setValue(delyInfo.delySchdStsCd, bizMsg.delySchdStsCd_DI);
            } else if(ZYPCommonFunc.hasValue(dsCpoDelyInfoPk)){
                ZYPEZDItemValueSetter.setValue(delyInfo.delySchdStsCd, (String) resultMap.get("DELY_SCHD_STS_CD"));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_DC.getValue()) || !massApplyMode) {
                ZYPEZDItemValueSetter.setValue(delyInfo.xxAttDataCmntTxt, bizMsg.xxAttDataCmntTxt_DI);
            } else if(ZYPCommonFunc.hasValue(dsCpoDelyInfoPk)){
                try {
                    ZYPEZDItemValueSetter.setValue(delyInfo.xxAttDataCmntTxt, clobToString((Clob) resultMap.get("DELY_SCHD_CMNT_CLOD")));
                } catch (SQLException e) {
                    bizMsg.xxAttDataCmntTxt_DI.setErrorInfo(1, NWAM0325E, new String[] {MESSAGE_NAME_SCHD_CMNT});
                }
            }
            // 2019/12/26 QC#54725 Add End
            if(massApplyMode){
                // 2018/08/02 S21_NA#26188-2 Mod Start
                // if(!ZYPCommonFunc.hasValue(delyInfo.loadDockAvalFlg) || // 
                //         !ZYPCommonFunc.hasValue(delyInfo.stairCrawReqFlg) || // 
                //         !ZYPCommonFunc.hasValue(delyInfo.elevAvalFlg)) {
                //     bizMsg.setMessageInfo(NWAM0960E);
                // }
                if (!ZYPCommonFunc.hasValue(delyInfo.loadDockAvalFlg)) {
                    ZYPEZDItemValueSetter.setValue(delyInfo.loadDockAvalFlg, ZYPConstant.FLG_OFF_N);
                }
                if (!ZYPCommonFunc.hasValue(delyInfo.stairCrawReqFlg)) {
                    ZYPEZDItemValueSetter.setValue(delyInfo.stairCrawReqFlg, ZYPConstant.FLG_OFF_N);
                }
                if (!ZYPCommonFunc.hasValue(delyInfo.elevAvalFlg)) {
                    ZYPEZDItemValueSetter.setValue(delyInfo.elevAvalFlg, ZYPConstant.FLG_OFF_N);
                }
                // 2018/08/02 S21_NA#26188-2 Mod End
            }
            // 2018/07/18 S21_NA#26188 Mod End
            pMsg.cpoDelyInfoList.setValidCount(1);
        }
        
        // if (isInputItemInstall(bizMsg)) { // 2018/07/18 S21_NA#26188 Mod Condition
        if (TAB_INSTALL.equals(tabTp)) {
            
            // 2018/07/18 S21_NA#26188 Move Logic2 To Start
            S21SsmEZDResult istlDtl = NWAL1510Query.getInstance().getIstlDtlWithConfNum(bizMsg);
            if (istlDtl.isCodeNormal()) {
                resultMap = (Map<String, Object>) istlDtl.getResultObject();
                dsCpoIstlInfoPk = (BigDecimal) resultMap.get("DS_CPO_ISTL_INFO_PK");
            }
            // 2018/07/18 S21_NA#26188 Move Logic2 To End

            // CPO Install info List
            NWZC183001_cpoInstInfoListPMsg instInfo = (NWZC183001_cpoInstInfoListPMsg) pMsg.cpoInstInfoList.get(0);

            if (ZYPCommonFunc.hasValue(dsCpoIstlInfoPk)) {
                ZYPEZDItemValueSetter.setValue(instInfo.xxModeCd, MODE_MODIFY);
                ZYPEZDItemValueSetter.setValue(instInfo.dsCpoIstlInfoPk, dsCpoIstlInfoPk);
            } else if (ZYPCommonFunc.hasValue(bizMsg.dsCpoIstlInfoPk_DI)) {
                ZYPEZDItemValueSetter.setValue(instInfo.xxModeCd, MODE_MODIFY);
                ZYPEZDItemValueSetter.setValue(instInfo.dsCpoIstlInfoPk, bizMsg.dsCpoIstlInfoPk_DI);
            } else {
                ZYPEZDItemValueSetter.setValue(instInfo.xxModeCd, MODE_NEW);
            }

            ZYPEZDItemValueSetter.setValue(instInfo.dsCpoConfigPk, dsCpoConfigPk);
            // 2018/07/18 S21_NA#26188 Mod Start
            // ZYPEZDItemValueSetter.setValue(instInfo.svcIstlRuleNum, bizMsg.svcIstlRuleNum_DI);
            // ZYPEZDItemValueSetter.setValue(instInfo.istlDivCd, bizMsg.istlDivCd_DI);
            // ZYPEZDItemValueSetter.setValue(instInfo.istlTechCd, bizMsg.istlTechCd_DI);
            // ZYPEZDItemValueSetter.setValue(instInfo.rqstIstlDt, bizMsg.rqstIstlDt_DI);
            // ZYPEZDItemValueSetter.setValue(instInfo.rqstIstlTm, chngRqstIstlTmDi);
            // ZYPEZDItemValueSetter.setValue(instInfo.istlAddlCmntTxt, bizMsg.istlAddlCmntTxt_DI);
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_D5.getValue()) || !massApplyMode) {
                ZYPEZDItemValueSetter.setValue(instInfo.svcIstlRuleNum, bizMsg.svcIstlRuleNum_DI);
                ZYPEZDItemValueSetter.setValue(instInfo.istlDivCd, bizMsg.istlDivCd_DI);
                ZYPEZDItemValueSetter.setValue(instInfo.rqstIstlDt, bizMsg.rqstIstlDt_DI);
                ZYPEZDItemValueSetter.setValue(instInfo.rqstIstlTm, chngRqstIstlTmDi);
                // 2019/10/30 QC#53993 Add Start
                ZYPEZDItemValueSetter.setValue(instInfo.istlBySvcPrvdPtyCd, bizMsg.istlBySvcPrvdPtyCd_DI);
                // 2019/10/30 QC#53993 Add End
            } else if(ZYPCommonFunc.hasValue(dsCpoIstlInfoPk)){
                ZYPEZDItemValueSetter.setValue(instInfo.svcIstlRuleNum, (String) resultMap.get("SVC_ISTL_RULE_NUM"));
                ZYPEZDItemValueSetter.setValue(instInfo.istlDivCd, (String) resultMap.get("ISTL_DIV_CD"));
                ZYPEZDItemValueSetter.setValue(instInfo.rqstIstlDt, (String) resultMap.get("RQST_ISTL_DT"));
                ZYPEZDItemValueSetter.setValue(instInfo.rqstIstlTm, getConvertTime(bizMsg, (String) resultMap.get("RQST_ISTL_TM")));
                // 2019/10/30 QC#53993 Add Start
                ZYPEZDItemValueSetter.setValue(instInfo.istlBySvcPrvdPtyCd, (String) resultMap.get("ISTL_BY_SVC_PRVD_PTY_CD"));
                // 2019/10/30 QC#53993 Add End
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_D7.getValue()) || !massApplyMode) {
                ZYPEZDItemValueSetter.setValue(instInfo.istlTechCd, bizMsg.istlTechCd_DI);
            } else if(ZYPCommonFunc.hasValue(dsCpoIstlInfoPk)){
                ZYPEZDItemValueSetter.setValue(instInfo.istlTechCd, (String) resultMap.get("ISTL_TECH_CD"));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_D9.getValue()) || !massApplyMode) {
                ZYPEZDItemValueSetter.setValue(instInfo.istlAddlCmntTxt, bizMsg.istlAddlCmntTxt_DI);
            } else if(ZYPCommonFunc.hasValue(dsCpoIstlInfoPk)){
                ZYPEZDItemValueSetter.setValue(instInfo.istlAddlCmntTxt, (String) resultMap.get("ISTL_ADDL_CMNT_TXT"));
            }

            // 2019/10/30 QC#53993 Add Start
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_DA.getValue()) || !massApplyMode) {
                ZYPEZDItemValueSetter.setValue(instInfo.svcBySvcPrvdPtyCd, bizMsg.svcBySvcPrvdPtyCd_DI);
            } else if(ZYPCommonFunc.hasValue(dsCpoIstlInfoPk)){
                ZYPEZDItemValueSetter.setValue(instInfo.svcBySvcPrvdPtyCd, (String) resultMap.get("SVC_BY_SVC_PRVD_PTY_CD"));
            }
            // 2019/10/30 QC#53993 Add End

            if(massApplyMode){
                if(!ZYPCommonFunc.hasValue(instInfo.svcIstlRuleNum)) {
                    bizMsg.xxChkBox_D5.setErrorInfo(1, NWAM0960E);
                }
            }
            // 2018/07/18 S21_NA#26188 Mod End

            pMsg.cpoInstInfoList.setValidCount(1);

        }

        // if (isInputItemSiteSurvey(bizMsg)) { // 2018/07/18 S21_NA#26188 Mod Condition
        if (TAB_SURVEY.equals(tabTp)) {

            boolean allEdtMode = ZYPConstant.FLG_ON_Y.equals(bizMsg.xxEdtModeFlg_SS.getValue()); // 2018/07/19 S21_NA#26188 Add 
            
            // 2018/07/18 S21_NA#26188 Move Logic3 To Start
            S21SsmEZDResult siteSvyDtl = NWAL1510Query.getInstance().getSiteSurveybyConfNum(bizMsg);
            if (siteSvyDtl.isCodeNormal()) {
                resultMap = (Map<String, Object>) siteSvyDtl.getResultObject();
                dsSiteSrvyPk = (BigDecimal) resultMap.get("DS_SITE_SRVY_PK");
            }
            // 2018/07/18 S21_NA#26188 Move Logic3 To End

            // Site Survey Info List
            NWZC183001_siteSrvyInfoListPMsg srvyInfo = (NWZC183001_siteSrvyInfoListPMsg) pMsg.siteSrvyInfoList.get(0);

            if (ZYPCommonFunc.hasValue(dsSiteSrvyPk)) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.xxModeCd, MODE_MODIFY);
                ZYPEZDItemValueSetter.setValue(srvyInfo.dsSiteSrvyPk, dsSiteSrvyPk);
            } else if (ZYPCommonFunc.hasValue(bizMsg.dsSiteSrvyPk_SS)) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.xxModeCd, MODE_MODIFY);
                ZYPEZDItemValueSetter.setValue(srvyInfo.dsSiteSrvyPk, bizMsg.dsSiteSrvyPk_SS);
            } else {
                ZYPEZDItemValueSetter.setValue(srvyInfo.xxModeCd, MODE_NEW);
            }

            ZYPEZDItemValueSetter.setValue(srvyInfo.dsCpoConfigPk, dsCpoConfigPk);
            // 2018/07/23 S21_NA#26188 Mod Start
            // ZYPEZDItemValueSetter.setValue(srvyInfo.cmpyInfoAptBldgNm, bizMsg.cmpyInfoAptBldgNm_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.cmpyInfoFlNm, bizMsg.cmpyInfoFlNm_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.cmpyInfoDeptNm, bizMsg.cmpyInfoDeptNm_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.otsdStepNum, bizMsg.otsdStepNum_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.insdStepNum, bizMsg.insdStepNum_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.stairCrawReqFlg, bizMsg.stairCrawReqFlg_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.flgtStairNum, bizMsg.flgtStairNum_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.elevAvalFlg, bizMsg.elevAvalFlg_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.elevAvalFromHourMn, chngElevAvalFromHourMnSs);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.elevAvalToHourMn, chngElevAvalToHourMnSs);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.elevApptReqFlg, bizMsg.elevApptReqFlg_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.elevCtacPsnNm, bizMsg.elevCtacPsnNm_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.elevCtacTelNum, bizMsg.elevCtacTelNum_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.elevProtReqFlg, bizMsg.elevProtReqFlg_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.elevWdt, bizMsg.elevWdt_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.elevDepthNum, bizMsg.elevDepthNum_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.elevCapWt, bizMsg.elevCapWt_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.elevDoorHgt, bizMsg.elevDoorHgt_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.elevDoorWdt, bizMsg.elevDoorWdt_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.stairAndLdgWdt, bizMsg.stairAndLdgWdt_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.crdrWdt, bizMsg.crdrWdt_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.doorWdt, bizMsg.doorWdt_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.loadDockAvalFlg, bizMsg.loadDockAvalFlg_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.loadDockHgt, bizMsg.loadDockHgt_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.trctrAndTrailAccsFlg, bizMsg.trctrAndTrailAccsFlg_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.bldgEntDoorHgt, bizMsg.bldgEntDoorHgt_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.bldgEntDoorWdt, bizMsg.bldgEntDoorWdt_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.loadDockAvalFromHourMn, chngLoadDockAvalFromHourMnSs);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.loadDockAvalToHourMn, chngLoadDockAvalToHourMnSs);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.carrDelyTmHourMn, chngCarrDelyTmHourMnSs);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.delyTrnspOptCd, bizMsg.delyTrnspOptCd_SS);
            // Mod Start 2016/09/20 QC#13738
//            ZYPEZDItemValueSetter.setValue(srvyInfo.siteSrvyAddlCmntTxt, bizMsg.siteSrvyAddlCmntTxt_SS);
            // ZYPEZDItemValueSetter.setValue(srvyInfo.siteSrvyAddlCmntTxt, bizMsg.xxFldValTxt_SS);
            // Mod End 2016/09/20 QC#13738
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_S0.getValue()) || !massApplyMode || allEdtMode) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.cmpyInfoAptBldgNm, bizMsg.cmpyInfoAptBldgNm_SS);
            } else if (ZYPCommonFunc.hasValue(dsSiteSrvyPk)) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.cmpyInfoAptBldgNm, (String) resultMap.get("CMPY_INFO_APT_BLDG_NM"));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_S1.getValue()) || !massApplyMode || allEdtMode) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.cmpyInfoFlNm, bizMsg.cmpyInfoFlNm_SS);
            } else if (ZYPCommonFunc.hasValue(dsSiteSrvyPk)) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.cmpyInfoFlNm, (String) resultMap.get("CMPY_INFO_FL_NM"));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_S2.getValue()) || !massApplyMode || allEdtMode) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.cmpyInfoDeptNm, bizMsg.cmpyInfoDeptNm_SS);
            } else if (ZYPCommonFunc.hasValue(dsSiteSrvyPk)) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.cmpyInfoDeptNm, (String) resultMap.get("CMPY_INFO_DEPT_NM"));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_S3.getValue()) || !massApplyMode || allEdtMode) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevProtReqFlg, bizMsg.elevProtReqFlg_SS);
            } else if (ZYPCommonFunc.hasValue(dsSiteSrvyPk)) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevProtReqFlg, (String) resultMap.get("ELEV_PROT_REQ_FLG"));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_S4.getValue()) || !massApplyMode || allEdtMode) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.otsdStepNum, bizMsg.otsdStepNum_SS);
                ZYPEZDItemValueSetter.setValue(srvyInfo.insdStepNum, bizMsg.insdStepNum_SS);
                ZYPEZDItemValueSetter.setValue(srvyInfo.stairCrawReqFlg, bizMsg.stairCrawReqFlg_SS);
                ZYPEZDItemValueSetter.setValue(srvyInfo.flgtStairNum, bizMsg.flgtStairNum_SS);
                ZYPEZDItemValueSetter.setValue(srvyInfo.stairAndLdgWdt, bizMsg.stairAndLdgWdt_SS);
            } else if (ZYPCommonFunc.hasValue(dsSiteSrvyPk)) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.otsdStepNum, (String) resultMap.get("OTSD_STEP_NUM"));
                ZYPEZDItemValueSetter.setValue(srvyInfo.insdStepNum, (String) resultMap.get("INSD_STEP_NUM"));
                ZYPEZDItemValueSetter.setValue(srvyInfo.stairCrawReqFlg, (String) resultMap.get("STAIR_CRAW_REQ_FLG"));
                ZYPEZDItemValueSetter.setValue(srvyInfo.flgtStairNum, (String) resultMap.get("FLGT_STAIR_NUM"));
                ZYPEZDItemValueSetter.setValue(srvyInfo.stairAndLdgWdt, (BigDecimal) resultMap.get("STAIR_AND_LDG_WDT"));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_S5.getValue()) || !massApplyMode || allEdtMode) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.loadDockAvalFlg, bizMsg.loadDockAvalFlg_SS);
                ZYPEZDItemValueSetter.setValue(srvyInfo.loadDockHgt, bizMsg.loadDockHgt_SS);
                ZYPEZDItemValueSetter.setValue(srvyInfo.loadDockAvalFromHourMn, chngLoadDockAvalFromHourMnSs);
                ZYPEZDItemValueSetter.setValue(srvyInfo.loadDockAvalToHourMn, chngLoadDockAvalToHourMnSs);
            } else if (ZYPCommonFunc.hasValue(dsSiteSrvyPk)) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.loadDockAvalFlg, (String) resultMap.get("LOAD_DOCK_AVAL_FLG"));
                ZYPEZDItemValueSetter.setValue(srvyInfo.loadDockHgt, (BigDecimal) resultMap.get("LOAD_DOCK_HGT"));
                ZYPEZDItemValueSetter.setValue(srvyInfo.loadDockAvalFromHourMn, getConvertTime(bizMsg, (String) resultMap.get("LOAD_DOCK_AVAL_FROM_HOUR_MN")));
                ZYPEZDItemValueSetter.setValue(srvyInfo.loadDockAvalToHourMn, getConvertTime(bizMsg, (String) resultMap.get("LOAD_DOCK_AVAL_TO_HOUR_MN")));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_SC.getValue()) || !massApplyMode || allEdtMode) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.trctrAndTrailAccsFlg, bizMsg.trctrAndTrailAccsFlg_SS);
            } else if (ZYPCommonFunc.hasValue(dsSiteSrvyPk)) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.trctrAndTrailAccsFlg, (String) resultMap.get("TRCTR_AND_TRAIL_ACCS_FLG"));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_SD.getValue()) || !massApplyMode || allEdtMode) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.delyTrnspOptCd, bizMsg.delyTrnspOptCd_SS);
            } else if (ZYPCommonFunc.hasValue(dsSiteSrvyPk)) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.delyTrnspOptCd, (String) resultMap.get("DELY_TRNSP_OPT_CD"));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_SE.getValue()) || !massApplyMode || allEdtMode) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.siteSrvyAddlCmntTxt, bizMsg.xxFldValTxt_SS);
            } else if (ZYPCommonFunc.hasValue(dsSiteSrvyPk)) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.siteSrvyAddlCmntTxt, (String) resultMap.get("SITE_SRVY_ADDL_CMNT_TXT"));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_SF.getValue()) || !massApplyMode || allEdtMode) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevAvalFlg, bizMsg.elevAvalFlg_SS);
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevAvalFromHourMn, chngElevAvalFromHourMnSs);
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevAvalToHourMn, chngElevAvalToHourMnSs);
            } else if (ZYPCommonFunc.hasValue(dsSiteSrvyPk)) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevAvalFlg, (String) resultMap.get("ELEV_AVAL_FLG"));
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevAvalFromHourMn, getConvertTime(bizMsg, (String) resultMap.get("ELEV_AVAL_FROM_HOUR_MN")));
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevAvalToHourMn, getConvertTime(bizMsg, (String) resultMap.get("ELEV_AVAL_TO_HOUR_MN")));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_SH.getValue()) || !massApplyMode || allEdtMode) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevApptReqFlg, bizMsg.elevApptReqFlg_SS);
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevCtacPsnNm, bizMsg.elevCtacPsnNm_SS);
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevCtacTelNum, bizMsg.elevCtacTelNum_SS);
            } else if (ZYPCommonFunc.hasValue(dsSiteSrvyPk)) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevApptReqFlg, (String) resultMap.get("ELEV_APPT_REQ_FLG"));
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevCtacPsnNm, (String) resultMap.get("ELEV_CTAC_PSN_NM"));
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevCtacTelNum, (String) resultMap.get("ELEV_CTAC_TEL_NUM"));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_SJ.getValue()) || !massApplyMode || allEdtMode) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.bldgEntDoorHgt, bizMsg.bldgEntDoorHgt_SS);
                ZYPEZDItemValueSetter.setValue(srvyInfo.bldgEntDoorWdt, bizMsg.bldgEntDoorWdt_SS);
                ZYPEZDItemValueSetter.setValue(srvyInfo.crdrWdt, bizMsg.crdrWdt_SS);
                ZYPEZDItemValueSetter.setValue(srvyInfo.doorWdt, bizMsg.doorWdt_SS);
            } else if (ZYPCommonFunc.hasValue(dsSiteSrvyPk)) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.bldgEntDoorHgt, (BigDecimal) resultMap.get("BLDG_ENT_DOOR_HGT"));
                ZYPEZDItemValueSetter.setValue(srvyInfo.bldgEntDoorWdt, (BigDecimal) resultMap.get("BLDG_ENT_DOOR_WDT"));
                ZYPEZDItemValueSetter.setValue(srvyInfo.crdrWdt, (BigDecimal) resultMap.get("CRDR_WDT"));
                ZYPEZDItemValueSetter.setValue(srvyInfo.doorWdt, (BigDecimal) resultMap.get("DOOR_WDT"));
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_SK.getValue()) || !massApplyMode || allEdtMode) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevWdt, bizMsg.elevWdt_SS);
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevDepthNum, bizMsg.elevDepthNum_SS);
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevCapWt, bizMsg.elevCapWt_SS);
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevDoorHgt, bizMsg.elevDoorHgt_SS);
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevDoorWdt, bizMsg.elevDoorWdt_SS);
            } else if (ZYPCommonFunc.hasValue(dsSiteSrvyPk)) {
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevWdt, (BigDecimal) resultMap.get("ELEV_WDT"));
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevDepthNum, (BigDecimal) resultMap.get("ELEV_DEPTH_NUM"));
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevCapWt, (BigDecimal) resultMap.get("ELEV_CAP_WT"));
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevDoorHgt, (BigDecimal) resultMap.get("ELEV_DOOR_HGT"));
                ZYPEZDItemValueSetter.setValue(srvyInfo.elevDoorWdt, (BigDecimal) resultMap.get("ELEV_DOOR_WDT"));
            }
            if(massApplyMode){
                // 2018/08/02 S21_NA#26188-2 Mod Start
                // if(!ZYPCommonFunc.hasValue(srvyInfo.elevProtReqFlg) || // 
                //        !ZYPCommonFunc.hasValue(srvyInfo.stairCrawReqFlg) || // 
                //        !ZYPCommonFunc.hasValue(srvyInfo.loadDockAvalFlg) || //
                //        !ZYPCommonFunc.hasValue(srvyInfo.trctrAndTrailAccsFlg) || //
                //        !ZYPCommonFunc.hasValue(srvyInfo.elevAvalFlg) || //
                //        !ZYPCommonFunc.hasValue(srvyInfo.elevApptReqFlg) || //
                //        !ZYPCommonFunc.hasValue(srvyInfo.delyTrnspOptCd) || //
                //        !ZYPCommonFunc.hasValue(srvyInfo.bldgEntDoorHgt) || //
                //        !ZYPCommonFunc.hasValue(srvyInfo.bldgEntDoorWdt) || //
                //        !ZYPCommonFunc.hasValue(srvyInfo.crdrWdt) || //
                //        !ZYPCommonFunc.hasValue(srvyInfo.doorWdt)) {
                //    bizMsg.setMessageInfo(NWAM0960E);
                //}
                if (!ZYPCommonFunc.hasValue(srvyInfo.elevProtReqFlg)) {
                    ZYPEZDItemValueSetter.setValue(srvyInfo.elevProtReqFlg, ZYPConstant.FLG_OFF_N);
                }
                if (!ZYPCommonFunc.hasValue(srvyInfo.stairCrawReqFlg)) {
                    ZYPEZDItemValueSetter.setValue(srvyInfo.stairCrawReqFlg, ZYPConstant.FLG_OFF_N);
                }
                if (!ZYPCommonFunc.hasValue(srvyInfo.loadDockAvalFlg)) {
                    ZYPEZDItemValueSetter.setValue(srvyInfo.loadDockAvalFlg, ZYPConstant.FLG_OFF_N);
                }
                if (!ZYPCommonFunc.hasValue(srvyInfo.trctrAndTrailAccsFlg)) {
                    ZYPEZDItemValueSetter.setValue(srvyInfo.trctrAndTrailAccsFlg, ZYPConstant.FLG_OFF_N);
                }
                if (!ZYPCommonFunc.hasValue(srvyInfo.elevAvalFlg)) {
                    ZYPEZDItemValueSetter.setValue(srvyInfo.elevAvalFlg, ZYPConstant.FLG_OFF_N);
                }
                if (!ZYPCommonFunc.hasValue(srvyInfo.elevApptReqFlg)) {
                    ZYPEZDItemValueSetter.setValue(srvyInfo.elevApptReqFlg, ZYPConstant.FLG_OFF_N);
                }
                if (!ZYPCommonFunc.hasValue(srvyInfo.delyTrnspOptCd)) {
                    bizMsg.xxChkBox_SD.setErrorInfo(1, NWAM0960E);
                }
                if(!ZYPCommonFunc.hasValue(srvyInfo.bldgEntDoorHgt) || //
                        !ZYPCommonFunc.hasValue(srvyInfo.bldgEntDoorWdt) || //
                        !ZYPCommonFunc.hasValue(srvyInfo.crdrWdt) || //
                        !ZYPCommonFunc.hasValue(srvyInfo.doorWdt)) {
                    bizMsg.xxChkBox_SJ.setErrorInfo(1, NWAM0960E);
                }
                // 2018/08/02 S21_NA#26188-2 Mod End
            }
            // 2018/07/23 S21_NA#26188 Mod End
            pMsg.siteSrvyInfoList.setValidCount(1);
        }

        NMZC002001PMsg ctacApiPMsg;
        int countIdx = 0;

        // Contact
        // if (isInputItemContact(bizMsg)) { // 2018/07/18 S21_NA#26188 Mod Condition
        if (isChangedItem(bizMsg, sMsg) && TAB_CONTACT.equals(tabTp)) {
            
            boolean replaceMode = ZYPConstant.FLG_ON_Y.equals(bizMsg.xxEdtModeFlg_CT.getValue()); // 2018/07/19 S21_NA#26188 Add
            ctacApiPMsg = new NMZC002001PMsg();
            
            // 2018/07/23 S21_NA#26188 Add Start
            if(massApplyMode && replaceMode){
                S21SsmEZDResult ctacDtl = NWAL1510Query.getInstance().getCtacDtlbyConfNum(bizMsg);
                if (ctacDtl.isCodeNormal()) {
                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ctacDtl.getResultObject();
                    for (int idx = 0; idx < resultList.size(); idx++) {
                        Map<String, Object> map = (Map<String, Object>) resultList.get(idx);
                        NWZC183001_cpoCtacPsnInfoListPMsg cpoCtacPsnInfo = (NWZC183001_cpoCtacPsnInfoListPMsg) pMsg.cpoCtacPsnInfoList.get(countIdx);
                        ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.xxModeCd, MODE_DELETE);
                        ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.dsCpoCtacPsnPk, (BigDecimal)map.get("DS_CPO_CTAC_PSN_PK"));
                        ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnPk, (BigDecimal)map.get("CTAC_PSN_PK"));
                        ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.dsCpoConfigPk, dsCpoConfigPk);
                        ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnTpCd, (String)map.get("CTAC_PSN_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnFirstNm, (String)map.get("CTAC_PSN_FIRST_NM"));
                        ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnLastNm, (String)map.get("CTAC_PSN_LAST_NM"));
                        ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnEmlAddr, (String)map.get("CTAC_PSN_EML_ADDR"));
                        ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnTelNum, (String)map.get("CTAC_PSN_TEL_NUM"));
                        ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnFaxNum, (String)map.get("CTAC_PSN_FAX_NUM"));
                        ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnExtnNum, (String)map.get("CTAC_PSN_EXTN_NUM"));
                        ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacCustRefTpCd, (String)map.get("CTAC_CUST_REF_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnSortNum, new BigDecimal(countIdx));
                        countIdx++;
                    }
                }
            }
            // 2018/07/23 S21_NA#26188 Add End

            for (int idx = 0; idx < bizMsg.C.getValidCount(); idx++) {

                // ctacApiPMsg = new NMZC002001PMsg();
                // 2017/03/01 S21_NA#17807 Add Start
//                NWZC183001_cpoCtacPsnInfoListPMsg cpoCtacPsnInfo = (NWZC183001_cpoCtacPsnInfoListPMsg) pMsg.cpoCtacPsnInfoList.get(idx);
                NWZC183001_cpoCtacPsnInfoListPMsg cpoCtacPsnInfo = (NWZC183001_cpoCtacPsnInfoListPMsg) pMsg.cpoCtacPsnInfoList.get(countIdx);
                // 2017/03/01 S21_NA#17807 Add End

                if (ZYPCommonFunc.hasValue(bizMsg.C.no(idx).dsCpoCtacPsnPk_C0)) {
                    ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.xxModeCd, MODE_MODIFY);
                    ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.dsCpoCtacPsnPk, bizMsg.C.no(idx).dsCpoCtacPsnPk_C0);
                } else {
                    ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.xxModeCd, MODE_NEW);
                }

                // S21_NA#7704 Mod Start
//                if (!ZYPCommonFunc.hasValue(bizMsg.C.no(idx).ctacPsnPk_C0)) {
//
//                    if (!ZYPCommonFunc.hasValue(bizMsg.C.no(idx).dsAcctNum_C0) && !ZYPCommonFunc.hasValue(bizMsg.C.no(idx).locNum_C0)) {
//                        registContactMst(ctacApiPMsg, bizMsg, idx);
//                    } else if (!bizMsg.C.no(idx).dsAcctNum_C0.getValue().equals(bizMsg.shipToCustAcctCd_H0) && !bizMsg.C.no(idx).locNum_C0.getValue().equals(bizMsg.shipToCustLocCd_H0)) {
//                        registContactMst(ctacApiPMsg, bizMsg, idx);
//                    }
//
//                    if ("E".equals(bizMsg.getMessageKind())) {
//                        return;
//                    }
//                    ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnPk, ctacApiPMsg.ctacPsnPk);
//                } else {
//                    ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnPk, bizMsg.C.no(idx).ctacPsnPk_C0);
//                }
                registContactMst(ctacApiPMsg, bizMsg, idx);
                if ("E".equals(bizMsg.getMessageKind())) {
                    return;
                }
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnPk, ctacApiPMsg.ctacPsnPk);
                // S21_NA#7704 Mod End

                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.dsCpoConfigPk, dsCpoConfigPk);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnTpCd, bizMsg.C.no(idx).ctacPsnTpCd_C0);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnFirstNm, bizMsg.C.no(idx).ctacPsnFirstNm_C0);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnLastNm, bizMsg.C.no(idx).ctacPsnLastNm_C0);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnEmlAddr, bizMsg.C.no(idx).ctacPsnEmlAddr_C0);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnTelNum, bizMsg.C.no(idx).ctacPsnTelNum_C0);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnFaxNum, bizMsg.C.no(idx).ctacPsnFaxNum_C0);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnExtnNum, bizMsg.C.no(idx).ctacPsnExtnNum_C0);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacCustRefTpCd, bizMsg.C.no(idx).ctacCustRefTpCd_C0); //S21_NA#16452 Add
                // ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnSortNum, new BigDecimal(idx));
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnSortNum, new BigDecimal(countIdx)); // 2018/07/23 S21_NA#26188 Mod
                countIdx++;
            }

            // if (bizMsg.H.getValidCount() > 1 && countIdx != 0) {
            //     NWAL1510Query.getInstance().getDeleteCtacListbyConfNum(bizMsg, sMsg);
            // }
        }

        // Delete List
        int delIdx = countIdx;
        for (int idx = 0; idx < sMsg.D.getValidCount(); idx++) {
            NWZC183001_cpoCtacPsnInfoListPMsg cpoCtacPsnInfo = (NWZC183001_cpoCtacPsnInfoListPMsg) pMsg.cpoCtacPsnInfoList.get(idx + delIdx);

            ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.xxModeCd, MODE_DELETE);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.dsCpoCtacPsnPk, sMsg.D.no(idx).dsCpoCtacPsnPk_D0);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.dsCpoConfigPk, dsCpoConfigPk);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnTpCd, sMsg.D.no(idx).ctacPsnTpCd_D0);
            countIdx++;
        }
        pMsg.cpoCtacPsnInfoList.setValidCount(countIdx);

        NWZC183001 api = new NWZC183001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            bizMsg.setMessageInfo(NWAM0269E);
        }

        if (!bizMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            bizMsg.setMessageInfo(NZZM0002I);
        }
    }
    
    // 2018/07/23 S21_NA#26188 Add Start
    public static boolean isChangedItem(NWAL1510CMsg cMsg, NWAL1510SMsg sMsg){
        String tabTp = cMsg.xxDplyTab.getValue();
        boolean chgFlg = false;
        if (TAB_INSTALL.equals(tabTp)) {
            if (!S21StringUtil.isEquals(cMsg.opsFromHourMn_DI.getValue(), sMsg.opsFromHourMn_DI.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.opsFromHourMn_AP.getValue(), sMsg.opsFromHourMn_AP.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.opsToHourMn_DI.getValue(), sMsg.opsToHourMn_DI.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.opsToHourMn_AP.getValue(), sMsg.opsToHourMn_AP.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.loadDockAvalFlg_DI.getValue(), sMsg.loadDockAvalFlg_DI.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.stairCrawReqFlg_DI.getValue(), sMsg.stairCrawReqFlg_DI.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.stairCrawNum_DI.getValue(), sMsg.stairCrawNum_DI.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.elevAvalFlg_DI.getValue(), sMsg.elevAvalFlg_DI.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.delyAddlCmntTxt_DI.getValue(), sMsg.delyAddlCmntTxt_DI.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.svcIstlRuleNum_DI.getValue(), sMsg.svcIstlRuleNum_DI.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.istlDivCd_DI.getValue(), sMsg.istlDivCd_DI.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.rqstIstlTm_DI.getValue(), sMsg.rqstIstlTm_DI.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.rqstIstlTm_AP.getValue(), sMsg.rqstIstlTm_AP.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.istlAddlCmntTxt_DI.getValue(), sMsg.istlAddlCmntTxt_DI.getValue()) || //
                    // 2019/12/26 QC#54725 Add Start
                    !S21StringUtil.isEquals(cMsg.delySchdStsCd_DI.getValue(), sMsg.delySchdStsCd_DI.getValue()) || //
                    !S21StringUtil.isEquals(cMsg.xxAttDataCmntTxt_DI.getValue(), sMsg.xxAttDataCmntTxt_DI.getValue()) || //
                    // 2019/12/26 QC#54725 Add End
                    // 2019/10/30 QC#53993 Add Start
                    !S21StringUtil.isEquals(cMsg.istlBySvcPrvdPtyCd_DI.getValue(), sMsg.istlBySvcPrvdPtyCd_DI.getValue()) || //
                    !S21StringUtil.isEquals(cMsg.svcBySvcPrvdPtyCd_DI.getValue(), sMsg.svcBySvcPrvdPtyCd_DI.getValue())) {
                    // 2019/10/30 QC#53993 Add End
                chgFlg = true;
            }
        } else if (TAB_SURVEY.equals(tabTp)) {
            if (!S21StringUtil.isEquals(cMsg.cmpyInfoAptBldgNm_SS.getValue(), sMsg.cmpyInfoAptBldgNm_SS.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.cmpyInfoFlNm_SS.getValue(), sMsg.cmpyInfoFlNm_SS.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.cmpyInfoDeptNm_SS.getValue(), sMsg.cmpyInfoDeptNm_SS.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.elevProtReqFlg_SS.getValue(), sMsg.elevProtReqFlg_SS.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.otsdStepNum_SS.getValue(), sMsg.otsdStepNum_SS.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.loadDockAvalFlg_SS.getValue(), sMsg.loadDockAvalFlg_SS.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.insdStepNum_SS.getValue(), sMsg.insdStepNum_SS.getValue()) || // 
                    NWAL1510CommonLogic.compareBigDecimal(cMsg.loadDockHgt_SS.getValue(), sMsg.loadDockHgt_SS.getValue(), true) != 0 || // 
                    !S21StringUtil.isEquals(cMsg.stairCrawReqFlg_SS.getValue(), sMsg.stairCrawReqFlg_SS.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.flgtStairNum_SS.getValue(), sMsg.flgtStairNum_SS.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.loadDockAvalFromHourMn_SS.getValue(), sMsg.loadDockAvalFromHourMn_SS.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.loadDockAvalFromHourMn_AP.getValue(), sMsg.loadDockAvalFromHourMn_AP.getValue()) || // 
                    NWAL1510CommonLogic.compareBigDecimal(cMsg.stairAndLdgWdt_SS.getValue(), sMsg.stairAndLdgWdt_SS.getValue(), false) != 0 || // 
                    !S21StringUtil.isEquals(cMsg.loadDockAvalToHourMn_SS.getValue(), sMsg.loadDockAvalToHourMn_SS.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.loadDockAvalToHourMn_AP.getValue(), sMsg.loadDockAvalToHourMn_AP.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.trctrAndTrailAccsFlg_SS.getValue(), sMsg.trctrAndTrailAccsFlg_SS.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.xxFldValTxt_SS.getValue(), sMsg.xxFldValTxt_SS.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.elevAvalFlg_SS.getValue(), sMsg.elevAvalFlg_SS.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.elevAvalFromHourMn_SS.getValue(), sMsg.elevAvalFromHourMn_SS.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.elevAvalFromHourMn_AP.getValue(), sMsg.elevAvalFromHourMn_AP.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.elevAvalToHourMn_SS.getValue(), sMsg.elevAvalToHourMn_SS.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.elevAvalToHourMn_AP.getValue(), sMsg.elevAvalToHourMn_AP.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.elevApptReqFlg_SS.getValue(), sMsg.elevApptReqFlg_SS.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.elevCtacPsnNm_SS.getValue(), sMsg.elevCtacPsnNm_SS.getValue()) || // 
                    !S21StringUtil.isEquals(cMsg.elevCtacTelNum_SS.getValue(), sMsg.elevCtacTelNum_SS.getValue()) || // 
                    NWAL1510CommonLogic.compareBigDecimal(cMsg.bldgEntDoorHgt_SS.getValue(), sMsg.bldgEntDoorHgt_SS.getValue(), false) != 0 || // 
                    NWAL1510CommonLogic.compareBigDecimal(cMsg.bldgEntDoorWdt_SS.getValue(), sMsg.bldgEntDoorWdt_SS.getValue(), false) != 0 || // 
                    NWAL1510CommonLogic.compareBigDecimal(cMsg.crdrWdt_SS.getValue(), sMsg.crdrWdt_SS.getValue(), false) != 0 || // 
                    NWAL1510CommonLogic.compareBigDecimal(cMsg.doorWdt_SS.getValue(), sMsg.doorWdt_SS.getValue(), false) != 0 || // 
                    NWAL1510CommonLogic.compareBigDecimal(cMsg.elevWdt_SS.getValue(), sMsg.elevWdt_SS.getValue(), true) != 0 || // 
                    NWAL1510CommonLogic.compareBigDecimal(cMsg.elevDepthNum_SS.getValue(), sMsg.elevDepthNum_SS.getValue(), true) != 0 || // 
                    NWAL1510CommonLogic.compareBigDecimal(cMsg.elevCapWt_SS.getValue(), sMsg.elevCapWt_SS.getValue(), true) != 0 || // 
                    NWAL1510CommonLogic.compareBigDecimal(cMsg.elevDoorHgt_SS.getValue(), sMsg.elevDoorHgt_SS.getValue(), true) != 0 || // 
                    NWAL1510CommonLogic.compareBigDecimal(cMsg.elevDoorWdt_SS.getValue(), sMsg.elevDoorWdt_SS.getValue(), true) != 0 ) { 
                chgFlg = true;
            }
        } else if (TAB_CONTACT.equals(tabTp)) {
            int ctacDtlCntSmsg = sMsg.C.getValidCount();
            int ctacDtlCntCmsg = cMsg.C.getValidCount();
            
            if(ctacDtlCntSmsg != ctacDtlCntCmsg){
                chgFlg = true;
            }
            for (int i = 0; i < cMsg.C.getValidCount(); i++) {
                if(chgFlg){
                    break;
                }
                NWAL1510_CCMsg ctacCmsg = cMsg.C.no(i);
                NWAL1510_CSMsg ctacSmsg = sMsg.C.no(i);
                if (!S21StringUtil.isEquals(ctacCmsg.ctacPsnTpCd_C0.getValue(), ctacSmsg.ctacPsnTpCd_C0.getValue()) || // 
                        !S21StringUtil.isEquals(ctacCmsg.ctacPsnFirstNm_C0.getValue(), ctacSmsg.ctacPsnFirstNm_C0.getValue()) || // 
                        !S21StringUtil.isEquals(ctacCmsg.ctacPsnLastNm_C0.getValue(), ctacSmsg.ctacPsnLastNm_C0.getValue()) || // 
                        !S21StringUtil.isEquals(ctacCmsg.ctacPsnTelNum_C0.getValue(), ctacSmsg.ctacPsnTelNum_C0.getValue()) || // 
                        !S21StringUtil.isEquals(ctacCmsg.ctacPsnExtnNum_C0.getValue(), ctacSmsg.ctacPsnExtnNum_C0.getValue()) || // 
                        !S21StringUtil.isEquals(ctacCmsg.ctacPsnEmlAddr_C0.getValue(), ctacSmsg.ctacPsnEmlAddr_C0.getValue()) || // 
                        !S21StringUtil.isEquals(ctacCmsg.ctacPsnFaxNum_C0.getValue(), ctacSmsg.ctacPsnFaxNum_C0.getValue()) || // 
                        !S21StringUtil.isEquals(ctacCmsg.ctacCustRefTpCd_C0.getValue(), ctacSmsg.ctacCustRefTpCd_C0.getValue())) {
                    chgFlg = true;
                }
            }
        }
        return chgFlg;
    }
    
    public static boolean checkMassApplyCheckBox(NWAL1510CMsg cMsg){

        String tabTp = cMsg.xxDplyTab.getValue();
        boolean massApplyMode = ZYPConstant.FLG_ON_Y.equals(cMsg.xxDplyCtrlFlg_MU.getValue());
        if(!massApplyMode){
            return false;
        }
        if (TAB_INSTALL.equals(tabTp)) {
            if(!ZYPCommonFunc.hasValue(cMsg.xxChkBox_D0) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_D1) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_D2) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_D3) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_D4) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_D5) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_D7) && //
                    // 2019/12/26 QC#54725 Add Start
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_DB) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_DC) && //
                    // 2019/12/26 QC#54725 Add End
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_D9)){
                return false;
            }
        } else if(TAB_SURVEY.equals(tabTp)){
            if(ZYPConstant.FLG_OFF_N.equals(cMsg.xxEdtModeFlg_SS.getValue()) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_S0) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_S1) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_S2) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_S3) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_S4) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_S5) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_SC) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_SD) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_SE) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_SF) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_SG) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_SH) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_SI) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_SJ) && //
                    !ZYPCommonFunc.hasValue(cMsg.xxChkBox_SK)) {
                return false;
            }
                    
        } 
        return true;
    }
    // 2018/07/23 S21_NA#26188 Add End

    /**
     * Regist Contact Person Information
     * @param apiPMsg NMZC002001PMsg
     * @param bizMsg NWAL1510CMsg
     * @param idx Contact Person List index
     */
    public static void registContactMst(NMZC002001PMsg apiPMsg, NWAL1510CMsg bizMsg, int idx) {

        // QC#16452 del Start
        // S21_NA#879 add start
//        S21SsmEZDResult ssmResult = NWAL1510Query.getInstance().getShipToCust(bizMsg);
//        if (ssmResult.isCodeNotFound()) {
//            bizMsg.setMessageInfo(ZZZM9006E, new String[] {"Ship To Cust" });
//            return;
//        }
//        Map<String, Object> shipToCust = (Map<String, Object>) ssmResult.getResultObject();
        // S21_NA#879 add end
        // QC#16452 del End

        // QC#16452 add Start
        S21SsmEZDResult ssmResult = NWAL1510Query.getInstance().getLocationNumber(bizMsg, idx);
        if (!ssmResult.isCodeNotFound()) {
            ZYPEZDItemValueSetter.setValue(apiPMsg.dsAcctNum, "");
            ZYPEZDItemValueSetter.setValue(apiPMsg.locNum, (String) ssmResult.getResultObject());
        }
        // QC#16452 add End
        // Add Start 2016/10/21 M.Ohno S21_NA#15354
        // 2017/03/01 S21_NA#17807 Mod Start
        BigDecimal ctacPsnPk = bizMsg.C.no(idx).ctacPsnPk_C0.getValue();
//        if (ZYPCommonFunc.hasValue(bizMsg.C.no(idx).ctacPsnPk_C0)) {
        if (ZYPCommonFunc.hasValue(ctacPsnPk) && ZYPCommonFunc.hasValue(bizMsg.C.no(idx).dsCpoCtacPsnPk_C0)) { // mod S21_NA#20803
        // 2017/03/01 S21_NA#17807 Mod End
            ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, MODE_MODIFY);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ctacPsnPk, bizMsg.C.no(idx).ctacPsnPk_C0);
            // QC#16452 dell Start
//            // 2017/03/01 S21_NA#17807 Add Start
//            Map<String, Object> dsCtacPsnRelnInfo =  (Map<String, Object>) NWAL1510Query.getInstance().getDsCtacPsnRelnInfo(bizMsg, ctacPsnPk).getResultObject();
//
//            ZYPEZDItemValueSetter.setValue(apiPMsg.dsAcctNum, (String) dsCtacPsnRelnInfo.get("DS_ACCT_NUM"));
//            ZYPEZDItemValueSetter.setValue(apiPMsg.locNum, (String) dsCtacPsnRelnInfo.get("LOC_NUM"));
            // 2017/03/01 S21_NA#17807 Add End
            // QC#16452 dell End
        } else {
            ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, MODE_NEW);
            // QC#16452 dell Start
            // 2017/03/01 S21_NA#17807 Add Start
//            ZYPEZDItemValueSetter.setValue(apiPMsg.dsAcctNum, (String) shipToCust.get("SELL_TO_CUST_CD"));
//            ZYPEZDItemValueSetter.setValue(apiPMsg.locNum, (String) shipToCust.get("LOC_NUM"));
            // 2017/03/01 S21_NA#17807 Add End
            // QC#16452 dell End
        }
        // Add End 2016/10/21 M.Ohno S21_NA#15354

        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);

        // S21_NA#879 modify start
        // ZYPEZDItemValueSetter.setValue(apiPMsg.dsAcctNum,
        // bizMsg.shipToCustAcctCd_H0);
        // ZYPEZDItemValueSetter.setValue(apiPMsg.locNum,
        // bizMsg.shipToCustLocCd_H0);
        // 2017/03/01 S21_NA#17807 Del Start
//        ZYPEZDItemValueSetter.setValue(apiPMsg.dsAcctNum, (String) shipToCust.get("SELL_TO_CUST_CD"));
//        ZYPEZDItemValueSetter.setValue(apiPMsg.locNum, (String) shipToCust.get("LOC_NUM"));
        // 2017/03/01 S21_NA#17807 Del End
        // S21_NA#879 modify end
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(apiPMsg.ctacPsnFirstNm, bizMsg.C.no(idx).ctacPsnFirstNm_C0);
        ZYPEZDItemValueSetter.setValue(apiPMsg.ctacPsnLastNm, bizMsg.C.no(idx).ctacPsnLastNm_C0);
        ZYPEZDItemValueSetter.setValue(apiPMsg.ctacTpCd, bizMsg.C.no(idx).ctacPsnTpCd_C0);

        int cpIdx = 0;

        // 2017/03/01 S21_NA#17807 Add Start
        List<Map<String, Object>> dsCtacPntInfoList = null;
        BigDecimal telDsCtacPntPk = null;
        BigDecimal emlDsCtacPntPk = null;
        BigDecimal faxDsCtacPntPk = null;

        if (ZYPCommonFunc.hasValue(ctacPsnPk) && ZYPCommonFunc.hasValue(bizMsg.C.no(idx).dsCpoCtacPsnPk_C0)) {
            dsCtacPntInfoList =  (List<Map<String, Object>>) NWAL1510Query.getInstance().getDsCtacPntInfo(bizMsg, ctacPsnPk).getResultObject();

            for (int i=0; i<dsCtacPntInfoList.size(); i++) {
                if (DS_CTAC_PNT_TP.PHONE_WORK.equals(dsCtacPntInfoList.get(i).get("DS_CTAC_PNT_TP_CD"))) {
                    telDsCtacPntPk = (BigDecimal) dsCtacPntInfoList.get(i).get("DS_CTAC_PNT_PK");
                } else if (DS_CTAC_PNT_TP.EMAIL_WORK.equals(dsCtacPntInfoList.get(i).get("DS_CTAC_PNT_TP_CD"))) {
                    emlDsCtacPntPk = (BigDecimal) dsCtacPntInfoList.get(i).get("DS_CTAC_PNT_PK");
                } else if (DS_CTAC_PNT_TP.FAX_WORK.equals(dsCtacPntInfoList.get(i).get("DS_CTAC_PNT_TP_CD"))) {
                    faxDsCtacPntPk = (BigDecimal) dsCtacPntInfoList.get(i).get("DS_CTAC_PNT_PK");
                }
            }
        }
        // 2017/03/01 S21_NA#17807 Add End

        if (ZYPCommonFunc.hasValue(bizMsg.C.no(idx).ctacPsnTelNum_C0)) {
            // 2017/03/01 S21_NA#17807 Add Start
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, MODE_NEW);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntTpCd, CTAC_PT_TP_CD_PHONE);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntValTxt, bizMsg.C.no(idx).ctacPsnTelNum_C0);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPsnExtnNum, bizMsg.C.no(idx).ctacPsnExtnNum_C0);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            if (telDsCtacPntPk != null) {
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, MODE_MODIFY);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntPk, telDsCtacPntPk);
            } else {
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, MODE_NEW);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            }
            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntTpCd, CTAC_PT_TP_CD_PHONE);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntValTxt, bizMsg.C.no(idx).ctacPsnTelNum_C0);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPsnExtnNum, bizMsg.C.no(idx).ctacPsnExtnNum_C0);
            // 2019/01/16 QC#29642 Add Start
            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).updCtrlFlg, ZYPConstant.FLG_ON_Y);
            // 2019/01/16 QC#29642 Add End

            cpIdx++;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.C.no(idx).ctacPsnEmlAddr_C0)) {
            // 2017/03/01 S21_NA#17807 Add Start
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, MODE_NEW);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntTpCd, CTAC_PT_TP_CD_MAIL);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntValTxt, bizMsg.C.no(idx).ctacPsnEmlAddr_C0);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            if (emlDsCtacPntPk != null) {
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, MODE_MODIFY);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntPk, emlDsCtacPntPk);
            } else {
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, MODE_NEW);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            }
            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntTpCd, CTAC_PT_TP_CD_MAIL);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntValTxt, bizMsg.C.no(idx).ctacPsnEmlAddr_C0);
            // 2017/03/01 S21_NA#17807 Add End

            cpIdx++;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.C.no(idx).ctacPsnFaxNum_C0)) {
            // 2017/03/01 S21_NA#17807 Add Start
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, MODE_NEW);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntTpCd, CTAC_PT_TP_CD_FAX);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntValTxt, bizMsg.C.no(idx).ctacPsnFaxNum_C0);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            if (faxDsCtacPntPk != null) {
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, MODE_MODIFY);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntPk, faxDsCtacPntPk);
            } else {
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).xxModeCd, MODE_NEW);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            }
            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntTpCd, CTAC_PT_TP_CD_FAX);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ContactPointInfoList.no(cpIdx).dsCtacPntValTxt, bizMsg.C.no(idx).ctacPsnFaxNum_C0);
            // 2017/03/01 S21_NA#17807 Add End

            cpIdx++;
        }
        apiPMsg.ContactPointInfoList.setValidCount(cpIdx);
        new NMZC002001().execute(apiPMsg, ONBATCH_TYPE.ONLINE);

        List<String> errList = S21ApiUtil.getXxMsgIdList(apiPMsg);

        if (!errList.isEmpty()) {
            for (String xxMsgId : errList) {
                if (xxMsgId.endsWith("E")) {
                    // 2017/08/15 S21_NA#16452 Add Start
                    if (xxMsgId.endsWith(NMZM0347E) || xxMsgId.endsWith(NMZM0348E) || xxMsgId.endsWith(NMZM0349E)) {
                        if (xxMsgId.endsWith(NMZM0347E)) {
                            bizMsg.C.no(idx).ctacPsnTelNum_C0.setErrorInfo(1, NMZM0347E);
                        }
                        if (xxMsgId.endsWith(NMZM0348E)) {
                            bizMsg.C.no(idx).ctacPsnEmlAddr_C0.setErrorInfo(1, NMZM0348E);
                        }
                        if (xxMsgId.endsWith(NMZM0349E)) {
                            bizMsg.C.no(idx).ctacPsnFaxNum_C0.setErrorInfo(1, NMZM0349E);
                        }
                        bizMsg.setMessageInfo(NWAM0269E);
                    } else {
                        bizMsg.setMessageInfo(xxMsgId);
                    }
                    // 2017/08/15 S21_NA#16452 Add End
                }
            }
        }

        return;
    }

    /**
     * Is Input Item Delivery
     * @param bizMsg NWAL1510CMsg
     * @return boolean
     */
    public static boolean isInputItemDelivery(NWAL1510CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.opsFromHourMn_DI)
            || ZYPCommonFunc.hasValue(bizMsg.opsToHourMn_DI)
            || ZYPCommonFunc.hasValue(bizMsg.stairCrawNum_DI)
            // 2019/12/26 QC#54725 Mod Start
            //|| ZYPCommonFunc.hasValue(bizMsg.delyAddlCmntTxt_DI)) {
            || ZYPCommonFunc.hasValue(bizMsg.delyAddlCmntTxt_DI)
            || ZYPCommonFunc.hasValue(bizMsg.delySchdStsCd_DI)
            || ZYPCommonFunc.hasValue(bizMsg.xxAttDataCmntTxt_DI)) {
            // 2019/12/26 QC#54725 Mod End
            return true;
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.loadDockAvalFlg_DI.getValue())
            || ZYPConstant.FLG_ON_Y.equals(bizMsg.stairCrawReqFlg_DI.getValue())
            || ZYPConstant.FLG_ON_Y.equals(bizMsg.elevAvalFlg_DI.getValue())) {
            return true;
        }
        // S21_NA#5030 modify start
        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.loadDockAvalFlg_DI.getValue())
                || ZYPConstant.FLG_OFF_N.equals(bizMsg.stairCrawReqFlg_DI.getValue())
                || ZYPConstant.FLG_OFF_N.equals(bizMsg.elevAvalFlg_DI.getValue())) {
                return true;
        }
        // S21_NA#5030 modify End

        return false;
    }

    /**
     * Is Input Item Install
     * @param bizMsg NWAL1510CMsg
     * @return boolean
     */
    public static boolean isInputItemInstall(NWAL1510CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.rqstIstlTm_DI)
            || ZYPCommonFunc.hasValue(bizMsg.svcIstlRuleNum_DI)
            || ZYPCommonFunc.hasValue(bizMsg.istlDivCd_DI)
            || ZYPCommonFunc.hasValue(bizMsg.istlTechCd_DI)
            || ZYPCommonFunc.hasValue(bizMsg.rqstIstlDt_DI)
            || ZYPCommonFunc.hasValue(bizMsg.istlAddlCmntTxt_DI)
            // 2019/10/30 QC#53993 Add Start
            || ZYPCommonFunc.hasValue(bizMsg.istlBySvcPrvdPtyCd_DI)
            || ZYPCommonFunc.hasValue(bizMsg.svcBySvcPrvdPtyCd_DI)) {
            // 2019/10/30 QC#53993 Add End
            return true;
        }
        return false;
    }

    /**
     * Is Input Item SiteSurvey
     * @param bizMsg NWAL1510CMsg
     * @return boolean
     */
    public static boolean isInputItemSiteSurvey(NWAL1510CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.loadDockAvalFromHourMn_SS)
            || ZYPCommonFunc.hasValue(bizMsg.loadDockAvalToHourMn_SS)
            || ZYPCommonFunc.hasValue(bizMsg.carrDelyTmHourMn_SS)
            || ZYPCommonFunc.hasValue(bizMsg.elevAvalFromHourMn_SS)
            || ZYPCommonFunc.hasValue(bizMsg.elevAvalToHourMn_SS)
            || ZYPCommonFunc.hasValue(bizMsg.cmpyInfoAptBldgNm_SS)
            || ZYPCommonFunc.hasValue(bizMsg.cmpyInfoFlNm_SS)
            || ZYPCommonFunc.hasValue(bizMsg.cmpyInfoDeptNm_SS)
            || ZYPCommonFunc.hasValue(bizMsg.otsdStepNum_SS)
            || ZYPCommonFunc.hasValue(bizMsg.insdStepNum_SS)
            || ZYPCommonFunc.hasValue(bizMsg.flgtStairNum_SS)
            || ZYPCommonFunc.hasValue(bizMsg.elevCtacPsnNm_SS)
            || ZYPCommonFunc.hasValue(bizMsg.elevCtacTelNum_SS)
            || ZYPCommonFunc.hasValue(bizMsg.elevWdt_SS)
            || ZYPCommonFunc.hasValue(bizMsg.elevDepthNum_SS)
            || ZYPCommonFunc.hasValue(bizMsg.elevCapWt_SS)
            || ZYPCommonFunc.hasValue(bizMsg.elevDoorHgt_SS)
            || ZYPCommonFunc.hasValue(bizMsg.elevDoorWdt_SS)
            || ZYPCommonFunc.hasValue(bizMsg.stairAndLdgWdt_SS)
            || ZYPCommonFunc.hasValue(bizMsg.crdrWdt_SS)
            || ZYPCommonFunc.hasValue(bizMsg.doorWdt_SS)
            || ZYPCommonFunc.hasValue(bizMsg.loadDockHgt_SS)
            || ZYPCommonFunc.hasValue(bizMsg.bldgEntDoorHgt_SS)
            || ZYPCommonFunc.hasValue(bizMsg.bldgEntDoorWdt_SS)
            || ZYPCommonFunc.hasValue(bizMsg.delyTrnspOptCd_SS)
            // Mod Start 2016/09/20 QC#13738
//            || ZYPCommonFunc.hasValue(bizMsg.siteSrvyAddlCmntTxt_SS)) {
            || ZYPCommonFunc.hasValue(bizMsg.xxFldValTxt_SS)) {
            // Mod End 2016/09/20 QC#13738
            return true;
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.stairCrawReqFlg_SS.getValue())
            || ZYPConstant.FLG_ON_Y.equals(bizMsg.elevAvalFlg_SS.getValue())
            || ZYPConstant.FLG_ON_Y.equals(bizMsg.elevApptReqFlg_SS.getValue())
            || ZYPConstant.FLG_ON_Y.equals(bizMsg.elevProtReqFlg_SS.getValue())
            || ZYPConstant.FLG_ON_Y.equals(bizMsg.loadDockAvalFlg_SS.getValue())
            || ZYPConstant.FLG_ON_Y.equals(bizMsg.trctrAndTrailAccsFlg_SS.getValue())) {
            return true;
        }
        // S21_NA#5030 modify start
        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.stairCrawReqFlg_SS.getValue())
                || ZYPConstant.FLG_OFF_N.equals(bizMsg.elevAvalFlg_SS.getValue())
                || ZYPConstant.FLG_OFF_N.equals(bizMsg.elevApptReqFlg_SS.getValue())
                || ZYPConstant.FLG_OFF_N.equals(bizMsg.elevProtReqFlg_SS.getValue())
                || ZYPConstant.FLG_OFF_N.equals(bizMsg.loadDockAvalFlg_SS.getValue())
                || ZYPConstant.FLG_OFF_N.equals(bizMsg.trctrAndTrailAccsFlg_SS.getValue())) {
                return true;
        }
        // S21_NA#5030 modify end
        return false;
    }

    /**
     * Is Input Item Contact
     * @param bizMsg NWAL1510CMsg
     * @return boolean
     */
    public static boolean isInputItemContact(NWAL1510CMsg bizMsg) {

        if (bizMsg.C.getValidCount() > 0) {
            return true;
        }

        return false;
    }

    /**
     * S21_NA#7626
     * isHeaderOnly
     * @param bizMsg    NWAL1510CMsg
     * @return if header only then return true.
     */
    public static boolean isHeaderOnly(NWAL1510CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NWAL1510Query.getInstance().getOrdConfigCnt(bizMsg);
        Integer cnt = (Integer) ssmResult.getResultObject();

        return cnt == 0;
    }

	// 2017/02/09 S21_NA#17500 Add Start
    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NWAL1510CMsg bizMsg = (NWAL1510CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);

            } else {

                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }
    // 2017/02/09 S21_NA#17500 Add End

    // S21_NA#16035 Add Start
    /**
     * Set Authority
     * @param bizMsg NWAL1510CMsg
     * @param userProfileService S21UserProfileService
     */
    public static void setAuthority(NWAL1510CMsg bizMsg, S21UserProfileService userProfileService) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        int funcIdCnt = 0;
        for (String functionId : functionIds) {
            bizMsg.Z.no(funcIdCnt++).xxFuncId.setValue(functionId);
        }
        bizMsg.Z.setValidCount(funcIdCnt);
    }
    // S21_NA#16035 Add End

    // 2018/01/18 T.Hakodate S21_NA#18460(Sol#087) ADD START
    public static void getServiceInstallTypeEditableFlag(NWAL1510CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1510Query.getInstance().getServiceInstallTypeEditableFlag(bizMsg);
        if (!ssmResult.isCodeNotFound()) {
            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_IT, (String) resultMap.get("SITE_SRVY_SCR_EDTBL_FLG"));
        }else{
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_IT, ZYPConstant.FLG_OFF_N);
        }
    }

    // 2018/01/18 T.Hakodate S21_NA#18460(Sol#087) ADD END

    // 2019/11/01 QC#53993 Add Start
    public static boolean getInstallTypeAndInstallDivision(NWAL1510CMsg bizMsg, String glblCmpyCd) {
        boolean isChangeInstallType = false;
        
        SVC_PRVD_PTYTMsg svcPrvdPtyParam = new SVC_PRVD_PTYTMsg();
        ZYPEZDItemValueSetter.setValue(svcPrvdPtyParam.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcPrvdPtyParam.svcPrvdPtyCd, bizMsg.istlBySvcPrvdPtyCd_DI);

        SVC_PRVD_PTYTMsg svcPrvdPtyMsg = (SVC_PRVD_PTYTMsg)  S21FastTBLAccessor.findByKey(svcPrvdPtyParam);

        if (svcPrvdPtyMsg != null){
            // START 2023/12/12 K.Watanabe [QC#61300, MOD]
            String svcIstlRuleNum = "";
            if ("Y".equals(bizMsg.xxDplyCtrlFlg_DI.getValue())) {
                svcIstlRuleNum = svcPrvdPtyMsg.svcIstlRuleNum.getValue();
            } else {
                svcIstlRuleNum = svcPrvdPtyMsg.svcDeinsRuleNum.getValue();
            }
            //if (ZYPCommonFunc.hasValue(svcPrvdPtyMsg.svcIstlRuleNum)){
            //    if (svcPrvdPtyMsg.svcIstlRuleNum.getValue().equals(bizMsg.svcIstlRuleNum_DI.getValue())){
            if (ZYPCommonFunc.hasValue(svcIstlRuleNum)){
                if (svcIstlRuleNum.equals(bizMsg.svcIstlRuleNum_DI.getValue())){
            // END   2023/12/12 K.Watanabe [QC#61300, MOD]
                    // Nothing
                } else {
                    // START 2023/12/12 K.Watanabe [QC#61300, MOD]
                    //ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, svcPrvdPtyMsg.svcIstlRuleNum);
                    ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, svcIstlRuleNum);
                    // END   2023/12/12 K.Watanabe [QC#61300, MOD]
                    isChangeInstallType = true;
                }
            }
        }

        // START 2023/12/12 K.Watanabe [QC#61300, MOD]
        //if (S21StringUtil.isEquals(bizMsg.svcIstlRuleNum_DI.getValue(), SVC_ISTL_RULE_NUM_NO_INSTALL)){
        if (S21StringUtil.isEquals(bizMsg.svcIstlRuleNum_DI.getValue(), SVC_ISTL_RULE_NUM_NO_INSTALL) || S21StringUtil.isEquals(bizMsg.svcIstlRuleNum_DI.getValue(), SVC_ISTL_RULE_NUM_NO_DEINSTALL)){
        // END   2023/12/12 K.Watanabe [QC#61300, MOD]
            bizMsg.istlDivCd_DI.clear();
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.istlDivCd_DI, svcPrvdPtyMsg.lineBizTpCd);
        }

        return isChangeInstallType;
    }
    // 2019/11/01 QC#53993 Add End

    private static String getConvertTime(NWAL1510CMsg bizMsg, String time) {
        if(!ZYPCommonFunc.hasValue(time)){
            return null;
        }
        String convertTime = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC,//
                ZYPDateUtil.getSalesDate() + time, bizMsg.ctryCd_H0.getValue(), bizMsg.postCd_H0.getValue()).getDateTime().substring(TIME_SUBSTRING_FROM, TIME_SUBSTRING_TO);

        return convertTime;
    }
    
    // 2018/07/20 S21_NA#26188 Add Start
    /**
     * <pre>
     * Conv Time For Screen
     * </pre>
     * @param sMsg NWAL1510SMsg
     */
    public static void convTimeforScreen(NWAL1510SMsg sMsg) {
        convTimetoShort(sMsg.rqstIstlTm_DI, sMsg.rqstIstlTm_AP);
        convTimetoShort(sMsg.opsFromHourMn_DI, sMsg.opsFromHourMn_AP);
        convTimetoShort(sMsg.opsToHourMn_DI, sMsg.opsToHourMn_AP);
        convTimetoShort(sMsg.loadDockAvalFromHourMn_SS, sMsg.loadDockAvalFromHourMn_AP);
        convTimetoShort(sMsg.loadDockAvalToHourMn_SS, sMsg.loadDockAvalToHourMn_AP);
        convTimetoShort(sMsg.carrDelyTmHourMn_SS, sMsg.carrDelyTmHourMn_AP);
        convTimetoShort(sMsg.elevAvalFromHourMn_SS, sMsg.elevAvalFromHourMn_AP);
        convTimetoShort(sMsg.elevAvalToHourMn_SS, sMsg.elevAvalToHourMn_AP);
    }

    private static void convTimetoShort(EZDSStringItem time, EZDSStringItem amPm) {
        if (ZYPCommonFunc.hasValue(time)) {
            Integer convTime = Integer.parseInt(time.getValue()) - NOON;
            if (convTime >= 0) {
                ZYPEZDItemValueSetter.setValue(time, String.format("%04d", convTime));
                ZYPEZDItemValueSetter.setValue(amPm, PM_CD);

            } else {
                if (!ZYPCommonFunc.hasValue(amPm)) {
                    ZYPEZDItemValueSetter.setValue(amPm, AM_CD);
                }
            }
        } else { 
            amPm.setValue("");
        }
    }
    
    /**
     * compare to BigDecimal
     * @param source source value
     * @param target target value
     * @return result (0, > 0 , < 0)
     */
    public static int compareBigDecimal(BigDecimal source, BigDecimal target, boolean rqZeroNull) {

        if (source == null) {
            if (target == null) {
                return 0;
            } else {
                if (rqZeroNull && BigDecimal.ZERO.compareTo(target) == 0) {
                    return 0;
                }
                return -1;
            }
        } else {
            if (target == null) {
                if (rqZeroNull && BigDecimal.ZERO.compareTo(source) == 0) {
                    return 0;
                }
                return 1;
            } else {
                return source.compareTo(target);
            }
        }
    }
    // 2018/07/20 S21_NA#26188 Add End
    
    
    // 2019/10/25 QC#53993 Add Start
    /**
     * setCtacPsnTpPulldown
     * @param bizMsg NWAL1510CMsg
     * @param toBeIstlOrSvcPrvdByFlg true:toBeIstlByFlg='Y' false:svcPrvdByFlg='Y'
     * @param glblCmpyCd Global Company Code
     */
    public static void setSvcPrvdPtyPulldown(NWAL1510CMsg bizMsg, boolean toBeIstlOrSvcPrvdByFlg, String glblCmpyCd) {
        S21SsmEZDResult result = NWAL1510Query.getInstance().getSvcPrvdPty(bizMsg, toBeIstlOrSvcPrvdByFlg, glblCmpyCd);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            for (Map<String, Object> map : list) {
                if (toBeIstlOrSvcPrvdByFlg){
                    bizMsg.istlBySvcPrvdPtyCd_L0.no(i).setValue((String) map.get("SVC_PRVD_PTY_CD"));
                    bizMsg.svcPrvdPtyDescTxt_L0.no(i).setValue((String) map.get("SVC_PRVD_PTY_DESC_TXT"));
                } else {
                    bizMsg.svcBySvcPrvdPtyCd_L0.no(i).setValue((String) map.get("SVC_PRVD_PTY_CD"));
                    bizMsg.svcPrvdPtyDescTxt_L1.no(i).setValue((String) map.get("SVC_PRVD_PTY_DESC_TXT"));
                }

                i++;
            }
        }
    }
    // 2019/10/25 QC#53993 Add End

    // 2019/12/26 QC#54725 Add Srart
    /**
     * clobToString
     * @param clob Clob
     * @return String
     */
    public static String clobToString(Clob clob) throws SQLException {
        if (clob != null) {
            return clob.getSubString(1, (int) clob.length());
        }
        return null;
    }
    // 2019/12/26 QC#54725 Add End
}
