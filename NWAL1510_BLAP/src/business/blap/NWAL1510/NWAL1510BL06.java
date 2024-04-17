/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1510;

import static business.blap.NWAL1510.common.NWAL1510CommonLogic.forciblyUpdateOfMultiConfig;
import static business.blap.NWAL1510.constant.NWAL1510Constant.RTL_WH_BILL_ONLY;
import static business.blap.NWAL1510.constant.NWAL1510Constant.NWAM0181E;
import static business.blap.NWAL1510.constant.NWAL1510Constant.SVC_ISTL_RULE_NUM_NO_INSTALL;
import static business.blap.NWAL1510.constant.NWAL1510Constant.TAB_CONTACT;
import static business.blap.NWAL1510.constant.NWAL1510Constant.TAB_INSTALL;
import static business.blap.NWAL1510.constant.NWAL1510Constant.TAB_SURVEY;
import static business.blap.NWAL1510.constant.NWAL1510Constant.ZZM9000E;
import static business.blap.NWAL1510.constant.NWAL1510Constant.ZZZM9004E;
import static business.blap.NWAL1510.constant.NWAL1510Constant.ZZZM9006E;
import static business.blap.NWAL1510.constant.NWAL1510Constant.NWAM0974E;

import java.math.BigDecimal;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.DS_CPO_CTAC_PSNTMsg;
import business.db.DS_CPO_DELY_INFOTMsg;
import business.db.DS_CPO_ISTL_INFOTMsg;
import business.db.DS_SITE_SRVYTMsg;
import business.db.SVC_ISTL_RULETMsg;
import business.db.SVC_PRVD_PTYTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * D&I/Contact/Site Survey Business Logic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/27   Fujitsu         S.Ohki          Create          N/A
 * 2015/11/25   Fujitsu         T.Ishii         Update          S21_NA#879
 * 2015/11/26   Fujitsu         S.Ohki          Update          S21_NA#1034
 * 2016/02/09   Fujitsu         S.Ohki          Update          S21_NA#1622
 * 2016/06/06   Fujitsu         S.Ohki          Update          S21_NA#7088
 * 2017/06/28   Fujitsu         M.Yamada        Update          S21_NA#19610
 * 2017/10/23   Fujitsu         A.Kosai         Update          S21_NA#21100
 * 2018/07/18   Fujitsu         K.Ishizuka      Update          S21_NA#26188
 * 2019/11/01   Fujitsu         Mz.Takahashi    Update          QC#53993
 * 2019/11/22   Fujitsu         C.Hara          Update          QC#54213
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 *</pre>
 */
public class NWAL1510BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NWAL1510Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NWAL1510Scrn01_CMN_Save((NWAL1510CMsg) cMsg, (NWAL1510SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL1510Scrn01_CMN_Save(NWAL1510CMsg bizMsg, NWAL1510SMsg sMsg) {

        // 2016/06/06 S21_NA#7088 Add Start
        boolean requiredErrFlg = false;
        // 2018/07/18 S21_NA#26188 Add Start
        String tabTp = bizMsg.xxDplyTab.getValue();
        boolean massApplyMode = ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDplyCtrlFlg_MU.getValue());
        // 2018/07/18 S21_NA#26188 Add End

        // if (isInputItemDelivery(bizMsg) || isInputItemInstall(bizMsg) ) { // 2018/07/19 S21_NA#26188 Mod Condition
        if (TAB_INSTALL.equals(tabTp)) {

            // 2019/11/01 QC#53993 Add Start
            SVC_PRVD_PTYTMsg svcPrvdPtyMsg = null;
            // 2019/11/01 QC#53993 Add End

            // if (!ZYPCommonFunc.hasValue(bizMsg.svcIstlRuleNum_DI)) { // 2018/07/19 S21_NA#26188 Mod Condition
            if (!ZYPCommonFunc.hasValue(bizMsg.svcIstlRuleNum_DI) && (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_D5.getValue()) || !massApplyMode)) {
                if ("Y".equals(bizMsg.xxDplyCtrlFlg_DI.getValue())) {
                    bizMsg.svcIstlRuleNum_DI.setErrorInfo(1, ZZM9000E, new String[] {"Install Type"});
                } else {
                    bizMsg.svcIstlRuleNum_DI.setErrorInfo(1, ZZM9000E, new String[] {"De-install Type"});
                }
                requiredErrFlg = true;
            }

            // 2019/11/01 QC#53993 Add Start
            if (ZYPCommonFunc.hasValue(bizMsg.istlBySvcPrvdPtyCd_DI)) {
                SVC_PRVD_PTYTMsg svcPrvdPtyParam = new SVC_PRVD_PTYTMsg();
                ZYPEZDItemValueSetter.setValue(svcPrvdPtyParam.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(svcPrvdPtyParam.svcPrvdPtyCd, bizMsg.istlBySvcPrvdPtyCd_DI);

                svcPrvdPtyMsg = (SVC_PRVD_PTYTMsg)  S21FastTBLAccessor.findByKey(svcPrvdPtyParam);

                if (svcPrvdPtyMsg != null){
                    ZYPEZDItemValueSetter.setValue(bizMsg.istlDivCd_DI, svcPrvdPtyMsg.lineBizTpCd);
                }
            } else {
                if (ZYPCommonFunc.hasValue(bizMsg.svcIstlRuleNum_DI)
                        && !S21StringUtil.isEquals(bizMsg.svcIstlRuleNum_DI.getValue(), SVC_ISTL_RULE_NUM_NO_INSTALL)) {
                    if ("Y".equals(bizMsg.xxDplyCtrlFlg_DI.getValue())) {
                        bizMsg.istlBySvcPrvdPtyCd_DI.setErrorInfo(1, ZZM9000E, new String[] {"To Be Installed By"});
                    } else {
                        bizMsg.istlBySvcPrvdPtyCd_DI.setErrorInfo(1, ZZM9000E, new String[] {"To Be De-Installed By"});
                    }
                    requiredErrFlg = true;
                }
            }

            if (S21StringUtil.isEquals(bizMsg.svcIstlRuleNum_DI.getValue(), SVC_ISTL_RULE_NUM_NO_INSTALL)){
                bizMsg.istlDivCd_DI.clear();
            }

            // 2019/11/01 QC#53993 Add End

            if (!ZYPCommonFunc.hasValue(bizMsg.istlDivCd_DI)) {
                // 2017/10/23 S21_NA#21100 Mod Start
                //if ("Y".equals(bizMsg.xxDplyCtrlFlg_DI.getValue())) {
                //    bizMsg.istlDivCd_DI.setErrorInfo(1, ZZM9000E, new String[] {"Install Division"});
                //} else {
                //    bizMsg.istlDivCd_DI.setErrorInfo(1, ZZM9000E, new String[] {"De-install Division"});
                //}
                //requiredErrFlg = true;
                if (ZYPCommonFunc.hasValue(bizMsg.svcIstlRuleNum_DI)
                        && !S21StringUtil.isEquals(bizMsg.svcIstlRuleNum_DI.getValue(), SVC_ISTL_RULE_NUM_NO_INSTALL)) {
                    if ("Y".equals(bizMsg.xxDplyCtrlFlg_DI.getValue())) {
                        bizMsg.istlDivCd_DI.setErrorInfo(1, ZZM9000E, new String[] {"Install Division"});
                    } else {
                        bizMsg.istlDivCd_DI.setErrorInfo(1, ZZM9000E, new String[] {"De-install Division"});
                    }
                    requiredErrFlg = true;
                }
                // 2017/10/23 S21_NA#21100 Mod End
            }

            // 2019/11/01 QC#53993 Add Start
            if (ZYPCommonFunc.hasValue(bizMsg.istlBySvcPrvdPtyCd_DI)){
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
                            //Nothing
                        } else {
                            SVC_ISTL_RULETMsg sirParam = new SVC_ISTL_RULETMsg();
                            ZYPEZDItemValueSetter.setValue(sirParam.glblCmpyCd, getGlobalCompanyCode());
                            // START 2023/12/12 K.Watanabe [QC#61300, MOD]
                            //ZYPEZDItemValueSetter.setValue(sirParam.svcIstlRuleNum, svcPrvdPtyMsg.svcIstlRuleNum);
                            ZYPEZDItemValueSetter.setValue(sirParam.svcIstlRuleNum, svcIstlRuleNum);
                            // END   2023/12/12 K.Watanabe [QC#61300, MOD]

                            SVC_ISTL_RULETMsg sirMsg = (SVC_ISTL_RULETMsg)  S21FastTBLAccessor.findByKey(sirParam);
                            String svcIstlRuleNm = "";

                            if ((sirMsg != null)  && ZYPCommonFunc.hasValue(sirMsg.svcIstlRuleNm)){
                                svcIstlRuleNm = sirMsg.svcIstlRuleNm.getValue();
                            }

                            if ("Y".equals(bizMsg.xxDplyCtrlFlg_DI.getValue())) {
                                bizMsg.svcIstlRuleNum_DI.setErrorInfo(1, NWAM0974E, new String[] {svcIstlRuleNm});
                            } else {
                                bizMsg.svcIstlRuleNum_DI.setErrorInfo(1, NWAM0974E, new String[] {svcIstlRuleNm});
                            }
                            requiredErrFlg = true;
                        }
                    }
                }
            }
            // 2019/11/01 QC#53993 Add End
        }

        // if (isInputItemSiteSurvey(bizMsg)) { // 2018/07/19 S21_NA#26188 Mod Condition
        if (TAB_SURVEY.equals(tabTp)) {
            boolean allEdtMode = ZYPConstant.FLG_ON_Y.equals(bizMsg.xxEdtModeFlg_SS.getValue()); // 2018/07/19 S21_NA#26188 Add
            // if (!ZYPCommonFunc.hasValue(bizMsg.delyTrnspOptCd_SS)) { // 2018/07/19 S21_NA#26188 Mod Condition
            if (!ZYPCommonFunc.hasValue(bizMsg.delyTrnspOptCd_SS) && (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_SD.getValue()) || !massApplyMode || allEdtMode)) {
                bizMsg.delyTrnspOptCd_SS.setErrorInfo(1, ZZM9000E, new String[] {"Transport Option"});
                requiredErrFlg = true;
            }

            if ((ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_SJ.getValue()) || !massApplyMode || allEdtMode)) { // 2018/07/19 S21_NA#26188 Add Condition
                if (!ZYPCommonFunc.hasValue(bizMsg.bldgEntDoorHgt_SS)) {
                    bizMsg.bldgEntDoorHgt_SS.setErrorInfo(1, ZZM9000E, new String[] {"Building Entrance Height(in,)" });
                    requiredErrFlg = true;
                }

                if (!ZYPCommonFunc.hasValue(bizMsg.bldgEntDoorWdt_SS)) {
                    bizMsg.bldgEntDoorWdt_SS.setErrorInfo(1, ZZM9000E, new String[] {"Building Entrance Width(in.)" });
                    requiredErrFlg = true;
                }

                if (!ZYPCommonFunc.hasValue(bizMsg.crdrWdt_SS)) {
                    bizMsg.crdrWdt_SS.setErrorInfo(1, ZZM9000E, new String[] {"Building Entrance Corridor Width(in.)" });
                    requiredErrFlg = true;
                }

                if (!ZYPCommonFunc.hasValue(bizMsg.doorWdt_SS)) {
                    bizMsg.doorWdt_SS.setErrorInfo(1, ZZM9000E, new String[] {"Building Entrance Door Width(in.)" });
                    requiredErrFlg = true;
                }
            }
            
        }

        // if (isInputItemContact(bizMsg)) { // 2018/07/19 S21_NA#26188 Mod Condition
        if (TAB_CONTACT.equals(tabTp)) {
            for (int i=0; i<bizMsg.C.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(bizMsg.C.no(i).ctacPsnFirstNm_C0)) {
                    bizMsg.C.no(i).ctacPsnFirstNm_C0.setErrorInfo(1, ZZM9000E, new String[] {"First Name"});
                    requiredErrFlg = true;
                }

                if (!ZYPCommonFunc.hasValue(bizMsg.C.no(i).ctacPsnLastNm_C0)) {
                    bizMsg.C.no(i).ctacPsnLastNm_C0.setErrorInfo(1, ZZM9000E, new String[] {"Last Name"});
                    requiredErrFlg = true;
                }
            }
        }

        if (requiredErrFlg) {
            return;
        }
        // 2016/06/06 S21_NA#7088 Add End

        BigDecimal dsCpoConfigPk = null;

        // Checkng CPO Number
        CPOTMsg tMsg = new CPOTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum_H0.getValue());

        if (EZDTBLAccessor.count(tMsg) == 0) {
            bizMsg.cpoOrdNum_H0.setErrorInfo(1, ZZZM9006E, new String[] {"Order Number" });
            return;
        }

        // Checkng Configuration Number
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {
            S21SsmEZDResult ssmResult = NWAL1510Query.getInstance().getConfigurationNum(bizMsg);

            if (ssmResult.isCodeNotFound()) {
                bizMsg.dsOrdPosnNum_H0.setErrorInfo(1, ZZZM9006E, new String[] {"Configuration Num" });
                return;
            } else {
                Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();

                dsCpoConfigPk = (BigDecimal) resultMap.get("DS_CPO_CONFIG_PK");
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.istlTechCd_DI) && (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_D5.getValue()) || !massApplyMode)) { // 2018/07/19 S21_NA#26188 Mod Condition
            // QC#19610
            S21SsmEZDResult ssmResult = NWAL1510Query.getInstance().getTechNm(bizMsg);
            if (ssmResult == null || ssmResult.isCodeNotFound()) {
                bizMsg.istlTechCd_DI.setErrorInfo(1, NWAM0181E, new String[] {"Install Technician" });
                bizMsg.techNm_DI.clear();
                return;
            }
        }

        // Multiple Configuration
        if (bizMsg.H.getValidCount() > 1) {

            for (int i = 0; i < bizMsg.H.getValidCount(); i++) {

                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_H0, bizMsg.H.no(i).dsOrdPosnNum_H1);

                S21SsmEZDResult ssmResult = NWAL1510Query.getInstance().getConfigurationNum(bizMsg);

                if (ssmResult.isCodeNotFound()) {
                    bizMsg.setMessageInfo(ZZZM9006E, new String[] {"Configuration Num" });
                    return;
                } else {
                    Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
                    dsCpoConfigPk = (BigDecimal) resultMap.get("DS_CPO_CONFIG_PK");
                }

                // 2019/11/22 QC#54213 Add Start
                boolean isSkip = false;
                CPO_DTLTMsg cdMsg = new CPO_DTLTMsg();
                cdMsg.setSQLID("502");
                cdMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
                cdMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum_H0.getValue());
                cdMsg.setConditionValue("dsOrdPosnNum01", bizMsg.dsOrdPosnNum_H0.getValue());
                CPO_DTLTMsgArray array = (CPO_DTLTMsgArray) EZDTBLAccessor.findByCondition(cdMsg);
                if (array != null && array.length() != 0) {
                    for (int j = 0; j < array.getValidCount(); j++) {
                        if (!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, array.no(j).baseCmptFlg.getValue())) {
                            continue;
                        }
                        String baseRtlWhCd = array.no(j).rtlWhCd.getValue();
                        if (RTL_WH_BILL_ONLY.contains(baseRtlWhCd)) {
                            isSkip = true;
                        }
                        break;
                    }
                }
                if (isSkip) {
                    continue;
                }
                // 2019/11/22 QC#54213 Add Start

                forciblyUpdateOfMultiConfig(bizMsg, sMsg, getGlobalCompanyCode(), dsCpoConfigPk);

                bizMsg.dsOrdPosnNum_H0.clear();
                bizMsg.shipToCustLocCd_H0.clear();
                sMsg.D.clear();
            }

        } else {

            // Exclusive process
            DS_CPO_DELY_INFOTMsg dsCpoDelyInfoMsg = new DS_CPO_DELY_INFOTMsg();
            ZYPEZDItemValueSetter.setValue(dsCpoDelyInfoMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(dsCpoDelyInfoMsg.dsCpoDelyInfoPk, bizMsg.dsCpoDelyInfoPk_DI);
            ZYPEZDItemValueSetter.setValue(dsCpoDelyInfoMsg.ezUpTime, bizMsg.ezUpTime_DI);
            ZYPEZDItemValueSetter.setValue(dsCpoDelyInfoMsg.ezUpTimeZone, bizMsg.ezUpTimeZone_DI);

            DS_CPO_DELY_INFOTMsg prvDsCpoDelyInfoMsg = (DS_CPO_DELY_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsCpoDelyInfoMsg);
            if (prvDsCpoDelyInfoMsg != null) {
                if (!ZYPDateUtil.isSameTimeStamp(prvDsCpoDelyInfoMsg.ezUpTime.getValue(), prvDsCpoDelyInfoMsg.ezUpTimeZone.getValue(), dsCpoDelyInfoMsg.ezUpTime.getValue(), dsCpoDelyInfoMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
            }

            DS_CPO_ISTL_INFOTMsg dsCpoIstlInfoMsg = new DS_CPO_ISTL_INFOTMsg();
            ZYPEZDItemValueSetter.setValue(dsCpoIstlInfoMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(dsCpoIstlInfoMsg.dsCpoIstlInfoPk, bizMsg.dsCpoIstlInfoPk_DI);
            ZYPEZDItemValueSetter.setValue(dsCpoIstlInfoMsg.ezUpTime, bizMsg.ezUpTime_IS);
            ZYPEZDItemValueSetter.setValue(dsCpoIstlInfoMsg.ezUpTimeZone, bizMsg.ezUpTimeZone_IS);

            DS_CPO_ISTL_INFOTMsg prvDsCpoIstlInfoMsg = (DS_CPO_ISTL_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsCpoIstlInfoMsg);
            if (prvDsCpoIstlInfoMsg != null) {
                if (!ZYPDateUtil.isSameTimeStamp(prvDsCpoIstlInfoMsg.ezUpTime.getValue(), prvDsCpoIstlInfoMsg.ezUpTimeZone.getValue(), dsCpoIstlInfoMsg.ezUpTime.getValue(), dsCpoIstlInfoMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
            }

            DS_SITE_SRVYTMsg dsSiteSrvyMsg = new DS_SITE_SRVYTMsg();
            ZYPEZDItemValueSetter.setValue(dsSiteSrvyMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(dsSiteSrvyMsg.dsSiteSrvyPk, bizMsg.dsSiteSrvyPk_SS);
            ZYPEZDItemValueSetter.setValue(dsSiteSrvyMsg.ezUpTime, bizMsg.ezUpTime_SS);
            ZYPEZDItemValueSetter.setValue(dsSiteSrvyMsg.ezUpTimeZone, bizMsg.ezUpTimeZone_SS);

            DS_SITE_SRVYTMsg prvDsSiteSrvyMsg = (DS_SITE_SRVYTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsSiteSrvyMsg);
            if (prvDsSiteSrvyMsg != null) {
                if (!ZYPDateUtil.isSameTimeStamp(prvDsSiteSrvyMsg.ezUpTime.getValue(), prvDsSiteSrvyMsg.ezUpTimeZone.getValue(), dsSiteSrvyMsg.ezUpTime.getValue(), dsSiteSrvyMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
            }

            for (int idx = 0; idx < bizMsg.C.getValidCount(); idx++) {

                DS_CPO_CTAC_PSNTMsg dsCpoCtacPsnMsg = new DS_CPO_CTAC_PSNTMsg();
                ZYPEZDItemValueSetter.setValue(dsCpoCtacPsnMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(dsCpoCtacPsnMsg.dsCpoCtacPsnPk, bizMsg.C.no(idx).dsCpoCtacPsnPk_C0);
                ZYPEZDItemValueSetter.setValue(dsCpoCtacPsnMsg.ezUpTime, bizMsg.C.no(idx).ezUpTime_C0);
                ZYPEZDItemValueSetter.setValue(dsCpoCtacPsnMsg.ezUpTimeZone, bizMsg.C.no(idx).ezUpTimeZone_C0);

                DS_CPO_CTAC_PSNTMsg prvDsCpoCtacPsnMsg = (DS_CPO_CTAC_PSNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsCpoCtacPsnMsg);
                if (prvDsCpoCtacPsnMsg != null) {
                    if (!ZYPDateUtil.isSameTimeStamp(prvDsCpoCtacPsnMsg.ezUpTime.getValue(), prvDsCpoCtacPsnMsg.ezUpTimeZone.getValue(), dsCpoCtacPsnMsg.ezUpTime.getValue(), dsCpoCtacPsnMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(ZZZM9004E);
                        return;
                    }
                }
            }

            forciblyUpdateOfMultiConfig(bizMsg, sMsg, getGlobalCompanyCode(), dsCpoConfigPk);
        }
    }

}
