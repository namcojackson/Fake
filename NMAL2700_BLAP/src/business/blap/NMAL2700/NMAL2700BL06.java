/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2700;

import static business.blap.NMAL2700.constant.NMAL2700Constant.BOS;
import static business.blap.NMAL2700.constant.NMAL2700Constant.CHK_A;
import static business.blap.NMAL2700.constant.NMAL2700Constant.GLBLCMPYCD01;
import static business.blap.NMAL2700.constant.NMAL2700Constant.LIST_SIZE_200;
import static business.blap.NMAL2700.constant.NMAL2700Constant.NMAM0072E;
import static business.blap.NMAL2700.constant.NMAL2700Constant.NMAM0176E;
import static business.blap.NMAL2700.constant.NMAL2700Constant.NMAM0835E;
import static business.blap.NMAL2700.constant.NMAL2700Constant.NMAM8020E;
import static business.blap.NMAL2700.constant.NMAL2700Constant.NMAM8337E;
import static business.blap.NMAL2700.constant.NMAL2700Constant.NMAM8576E;
import static business.blap.NMAL2700.constant.NMAL2700Constant.ORG_FUNC_ROLE_TP;
import static business.blap.NMAL2700.constant.NMAL2700Constant.ORG_FUNC_ROLE_TP01;
import static business.blap.NMAL2700.constant.NMAL2700Constant.SALES;
import static business.blap.NMAL2700.constant.NMAL2700Constant.SERVICE;
import static business.blap.NMAL2700.constant.NMAL2700Constant.ZZM9037E;
import static business.blap.NMAL2700.constant.NMAL2700Constant.ZZZM9004E;
import static business.blap.NMAL2700.constant.NMAL2700Constant.ZZZM9006E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL2700.common.NMAL2700CommonLogic;
import business.db.ORG_FUNC_ROLE_TPTMsg;
import business.db.ORG_TOC_CHNG_RQSTTMsg;
import business.db.ORG_TOC_CHNG_RQSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL2700BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/05   Fujitsu         M.Suzuki        Create          N/A
 * 2016/03/08   Fujitsu         M.Suzuki        Update          S21_NA#4304
 * 2016/06/08   Fujitsu         C.Yokoi         Update          CSA-QC#9324
 * 2017/10/06   Fujitsu         H.Ikeda         Update          QC#21352
 *</pre>
 */
public class NMAL2700BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2700CMsg bizMsg = (NMAL2700CMsg) cMsg;
            NMAL2700SMsg glblMsg = (NMAL2700SMsg) sMsg;

            if ("NMAL2700Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2700Scrn00_CMN_Submit(bizMsg, glblMsg);
            } else if ("NMAL2700Scrn00_DeleteRow_RoleMnt".equals(screenAplID)) {
                doProcess_NMAL2700Scrn00_DeleteRow_RoleMnt(bizMsg, glblMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business
     * @param glblMsg Global
     */
    private void doProcess_NMAL2700Scrn00_CMN_Submit(NMAL2700CMsg bizMsg, NMAL2700SMsg glblMsg) {
        if (checkDuplicate(bizMsg)) {
            bizMsg.setMessageInfo(ZZM9037E);
            return;
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // CREATE
            if (!NMAL2700CommonLogic.isExistRecord(bizMsg, i)) {

                String roleCode = bizMsg.A.no(i).orgFuncRoleTpCd_A.getValue();
                if (isExistRequestRole(roleCode)) {
                    bizMsg.A.no(i).orgFuncRoleTpCd_A.setErrorInfo(1, NMAM8337E, new String[] {roleCode });
                    return;
                }

                ORG_FUNC_ROLE_TPTMsg tMsg = new ORG_FUNC_ROLE_TPTMsg();
                setTmsgValue(bizMsg, i, tMsg);

                EZDTBLAccessor.create(tMsg);
                if (RTNCD_DUPLICATE.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0176E, new String[] {ORG_FUNC_ROLE_TP });
                    return;
                }
                continue;
            }

            // UPDATE
            for (int j = 0; j < glblMsg.A.getValidCount(); j++) {
                if (bizMsg.A.no(i).xxRowId_A.getValue().equals(glblMsg.A.no(j).xxRowId_A.getValue())) {

                    if (isUpdate(bizMsg, glblMsg, i, j)) {

                        String roleCode = bizMsg.A.no(i).orgFuncRoleTpCd_A.getValue();

                        if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).firstOrgCd_A) && !glblMsg.A.no(i).firstOrgCd_A.getValue().equals(bizMsg.A.no(i).firstOrgCd_A.getValue())) {
                            List<Map<String, Object>> resultList = existCheckRequestRole(bizMsg, bizMsg.A.no(i).orgFuncRoleTpCd_A.getValue());
                            if (resultList != null && !resultList.isEmpty()) {
                                bizMsg.setMessageInfo(NMAM8576E);
                                return;
                            }
                        }

                        String sfdcPrflNm = bizMsg.A.no(i).crmSlsPrflNm_A.getValue();
                        if (ZYPCommonFunc.hasValue(sfdcPrflNm)) {
                            if (!isExistSFDCProfile(sfdcPrflNm)) {
                                bizMsg.A.no(i).orgFuncRoleTpCd_A.setErrorInfo(1, ZZZM9006E, new String[] {sfdcPrflNm });
                                return;
                            }
                        }

                        // 2017/10/06 QC#21352 Add Start
                        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(j).actvFlg_A.getValue())) {
                            List<Map<String, Object>> resultList = existCheckRequestRole(bizMsg, roleCode);
                            if (resultList != null && !resultList.isEmpty()) {
                                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
                                String roleTpCd = (String) resultMap.get("ORG_FUNC_ROLE_TP_CD");
                                bizMsg.setMessageInfo(NMAM8337E, new String[] {roleTpCd});
                                return;
                            }
                        }
                        // 2017/10/06 QC#21352 Add End

                        ORG_FUNC_ROLE_TPTMsg tMsg = new ORG_FUNC_ROLE_TPTMsg();
                        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                        ZYPEZDItemValueSetter.setValue(tMsg.orgFuncRoleTpCd, roleCode);
                        tMsg = (ORG_FUNC_ROLE_TPTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                        if (!ZYPDateUtil.isSameTimeStamp(bizMsg.A.no(i).ezUpTime_A.getValue(), bizMsg.A.no(i).ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                            bizMsg.setMessageInfo(ZZZM9004E);
                            return;
                        }

                        setTmsgValue(bizMsg, i, tMsg);
                        EZDTBLAccessor.update(tMsg);
                        if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                            bizMsg.setMessageInfo(NMAM0176E, new String[] {ORG_FUNC_ROLE_TP });
                            return;
                        }
                    }
                }
            }
        }

        // DELETE
        for (int idx = 0; idx < glblMsg.Z.getValidCount(); idx++) {

            List<Map<String, Object>> resultList = existCheckRequestRole(bizMsg, null);
            if (resultList != null && !resultList.isEmpty()) {
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
                String roleCode = (String) resultMap.get("ORG_FUNC_ROLE_TP_CD");
                bizMsg.setMessageInfo(NMAM8337E, new String[] {roleCode });
                return;
            }

            ORG_FUNC_ROLE_TPTMsg tMsg = new ORG_FUNC_ROLE_TPTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tMsg.orgFuncRoleTpCd, bizMsg.Z.no(idx).orgFuncRoleTpCd_Z);
            tMsg = (ORG_FUNC_ROLE_TPTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

            if (tMsg == null) {
                bizMsg.setMessageInfo(ZZZM9004E);
                return;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.Z.no(idx).ezUpTime_Z.getValue(), bizMsg.Z.no(idx).ezUpTimeZone_Z.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
            }

            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM8020E);
                return;
            }
        }
    }

    private boolean isExistSFDCProfile(String sfdcPrflNm) {
        if (NMAL2700Query.getInstance().checkSFDCProfileName(sfdcPrflNm) > 0) {
            return true;
        }
        return false;
    }

    private boolean isExistRequestRole(String roleCode) {
        ORG_TOC_CHNG_RQSTTMsg tMsg = new ORG_TOC_CHNG_RQSTTMsg();
        tMsg.setSQLID("108");
        tMsg.setConditionValue(GLBLCMPYCD01, getGlobalCompanyCode());
        tMsg.setConditionValue(ORG_FUNC_ROLE_TP01, roleCode);
        ORG_TOC_CHNG_RQSTTMsgArray tmsgArray = (ORG_TOC_CHNG_RQSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tmsgArray.length() > 0) {
            return true;
        }
        return false;
    }

    private boolean isUpdate(NMAL2700CMsg bizMsg, NMAL2700SMsg glblMsg, int i, int j) {

        String firstOrgCd = bizMsg.A.no(i).firstOrgCd_A.getValue();
        String orgFuncRoleTpcd = bizMsg.A.no(i).orgFuncRoleTpCd_A.getValue();
        String orgFuncRoleTpNm = bizMsg.A.no(i).orgFuncRoleTpNm_A.getValue();
        String orgFuncRoleTpDescTxt = bizMsg.A.no(i).orgFuncRoleTpDescTxt_A.getValue();
        String actvFlg = bizMsg.A.no(i).actvFlg_A.getValue();
        if (!ZYPCommonFunc.hasValue(actvFlg)) {
            actvFlg = ZYPConstant.FLG_OFF_N;
        }

        String equipFlg = bizMsg.A.no(i).equipFlg_A.getValue();
        if (!ZYPCommonFunc.hasValue(equipFlg)) {
            equipFlg = ZYPConstant.FLG_OFF_N;
        }

        String mgrFlg = bizMsg.A.no(i).mgrFlg_A.getValue();
        if (!ZYPCommonFunc.hasValue(mgrFlg)) {
            mgrFlg = ZYPConstant.FLG_OFF_N;
        }

        String spclFlg = bizMsg.A.no(i).spclFlg_A.getValue();
        if (!ZYPCommonFunc.hasValue(spclFlg)) {
            spclFlg = ZYPConstant.FLG_OFF_N;
        }

        String cmsnFlg = bizMsg.A.no(i).cmsnFlg_A.getValue();
        if (!ZYPCommonFunc.hasValue(cmsnFlg)) {
            cmsnFlg = ZYPConstant.FLG_OFF_N;
        }

        String adminFlg = bizMsg.A.no(i).adminFlg_A.getValue();
        if (!ZYPCommonFunc.hasValue(adminFlg)) {
            adminFlg = ZYPConstant.FLG_OFF_N;
        }

        String gesTpCd = bizMsg.A.no(i).gesTpCd_A.getValue();
        BigDecimal apvlLimitAmt = bizMsg.A.no(i).apvlLimitAmt_A.getValue();

        String slsRepFlg = bizMsg.A.no(i).slsRepFlg_A.getValue();
        if (!ZYPCommonFunc.hasValue(slsRepFlg)) {
            slsRepFlg = ZYPConstant.FLG_OFF_N;
        }

        String asgContrFlg = bizMsg.A.no(i).asgContrFlg_A.getValue();
        if (!ZYPCommonFunc.hasValue(asgContrFlg)) {
            asgContrFlg = ZYPConstant.FLG_OFF_N;
        }

        String thirdPtyTechFlg = bizMsg.A.no(i).thirdPtyTechFlg_A.getValue();
        if (!ZYPCommonFunc.hasValue(thirdPtyTechFlg)) {
            thirdPtyTechFlg = ZYPConstant.FLG_OFF_N;
        }

        String techMstrReqFlg = bizMsg.A.no(i).techMstrReqFlg_A.getValue();
        if (!ZYPCommonFunc.hasValue(techMstrReqFlg)) {
            techMstrReqFlg = ZYPConstant.FLG_OFF_N;
        }

        String crmSlsExclFlg = bizMsg.A.no(i).crmSlsExclFlg_A.getValue();
        if (!ZYPCommonFunc.hasValue(crmSlsExclFlg)) {
            crmSlsExclFlg = ZYPConstant.FLG_OFF_N;
        }

        String crmSlsPrflTpNm = bizMsg.A.no(i).crmSlsPrflNm_A.getValue();
        boolean updateFlag = false;
        if (!firstOrgCd.equals(glblMsg.A.no(j).firstOrgCd_A.getValue())) {
            updateFlag = true;
        }

        if (!orgFuncRoleTpcd.equals(glblMsg.A.no(j).orgFuncRoleTpCd_A.getValue())) {
            updateFlag = true;
        }

        if (!orgFuncRoleTpNm.equals(glblMsg.A.no(j).orgFuncRoleTpNm_A.getValue())) {
            updateFlag = true;
        }

        if (!orgFuncRoleTpDescTxt.equals(glblMsg.A.no(j).orgFuncRoleTpDescTxt_A.getValue())) {
            updateFlag = true;
        }

        if (!actvFlg.equals(glblMsg.A.no(j).actvFlg_A.getValue())) {
            updateFlag = true;
        }

        if (!equipFlg.equals(glblMsg.A.no(j).equipFlg_A.getValue())) {
            updateFlag = true;
        }

        if (!mgrFlg.equals(glblMsg.A.no(j).mgrFlg_A.getValue())) {
            updateFlag = true;
        }

        if (!spclFlg.equals(glblMsg.A.no(j).spclFlg_A.getValue())) {
            updateFlag = true;
        }

        if (!cmsnFlg.equals(glblMsg.A.no(j).cmsnFlg_A.getValue())) {
            updateFlag = true;
        }

        if (!adminFlg.equals(glblMsg.A.no(j).adminFlg_A.getValue())) {
            updateFlag = true;
        }

        if (!gesTpCd.equals(glblMsg.A.no(j).gesTpCd_A.getValue())) {
            updateFlag = true;
        }

        if (ZYPCommonFunc.hasValue(apvlLimitAmt)) {
            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(j).apvlLimitAmt_A.getValue())) {
                updateFlag = true;
            } else if (apvlLimitAmt.compareTo(glblMsg.A.no(j).apvlLimitAmt_A.getValue()) != 0) {
                updateFlag = true;
            }
        } else {
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(j).apvlLimitAmt_A)) {
                updateFlag = true;
            }
        }

        if (!slsRepFlg.equals(glblMsg.A.no(j).slsRepFlg_A.getValue())) {
            updateFlag = true;
        }

        if (!asgContrFlg.equals(glblMsg.A.no(j).asgContrFlg_A.getValue())) {
            updateFlag = true;
        }

        if (!thirdPtyTechFlg.equals(glblMsg.A.no(j).thirdPtyTechFlg_A.getValue())) {
            updateFlag = true;
        }

        if (!techMstrReqFlg.equals(glblMsg.A.no(j).techMstrReqFlg_A.getValue())) {
            updateFlag = true;
        }

        if (!crmSlsExclFlg.equals(glblMsg.A.no(j).crmSlsExclFlg_A.getValue())) {
            updateFlag = true;
        }

        if (!crmSlsPrflTpNm.equals(glblMsg.A.no(j).crmSlsPrflNm_A.getValue())) {
            updateFlag = true;
        }
        return updateFlag;
    }

    private void setTmsgValue(NMAL2700CMsg bizMsg, int i, ORG_FUNC_ROLE_TPTMsg tMsg) {
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.orgFuncRoleTpCd, bizMsg.A.no(i).orgFuncRoleTpCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.orgFuncRoleTpNm, bizMsg.A.no(i).orgFuncRoleTpNm_A);
        ZYPEZDItemValueSetter.setValue(tMsg.orgFuncRoleTpDescTxt, bizMsg.A.no(i).orgFuncRoleTpDescTxt_A);

        String firstOrgCd = bizMsg.A.no(i).firstOrgCd_A.getValue();
        if (SALES.equals(firstOrgCd)) {
            ZYPEZDItemValueSetter.setValue(tMsg.slsAsgEligFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.slsAsgEligFlg, ZYPConstant.FLG_OFF_N);
        }

        if (SALES.equals(firstOrgCd) && ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).spclFlg_A.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.slsAdminEligFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.slsAdminEligFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.multPsnAsgFlg, ZYPConstant.FLG_OFF_N);

        if (SERVICE.equals(firstOrgCd)) {
            ZYPEZDItemValueSetter.setValue(tMsg.techAsgEligFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.techAsgEligFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).techMstrReqFlg_A)) {
            ZYPEZDItemValueSetter.setValue(tMsg.techMstrReqFlg, bizMsg.A.no(i).techMstrReqFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.techMstrReqFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.tocReqFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(tMsg.orgStruTpCd, BOS);

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).actvFlg_A)) {
            ZYPEZDItemValueSetter.setValue(tMsg.actvFlg, bizMsg.A.no(i).actvFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.actvFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).equipFlg_A)) {
            ZYPEZDItemValueSetter.setValue(tMsg.equipFlg, bizMsg.A.no(i).equipFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.equipFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).mgrFlg_A)) {
            ZYPEZDItemValueSetter.setValue(tMsg.mgrFlg, bizMsg.A.no(i).mgrFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.mgrFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).spclFlg_A)) {
            ZYPEZDItemValueSetter.setValue(tMsg.spclFlg, bizMsg.A.no(i).spclFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.spclFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).cmsnFlg_A)) {
            ZYPEZDItemValueSetter.setValue(tMsg.cmsnFlg, bizMsg.A.no(i).cmsnFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.cmsnFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.gesTpCd, bizMsg.A.no(i).gesTpCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.apvlLimitAmt, bizMsg.A.no(i).apvlLimitAmt_A);

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).adminFlg_A)) {
            ZYPEZDItemValueSetter.setValue(tMsg.adminFlg, bizMsg.A.no(i).adminFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.adminFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.firstOrgCd, firstOrgCd);

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).slsRepFlg_A)) {
            ZYPEZDItemValueSetter.setValue(tMsg.slsRepFlg, bizMsg.A.no(i).slsRepFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.slsRepFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).asgContrFlg_A)) {
            ZYPEZDItemValueSetter.setValue(tMsg.asgContrFlg, bizMsg.A.no(i).asgContrFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.asgContrFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.s21OrgReqFlg, ZYPConstant.FLG_ON_Y);

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).thirdPtyTechFlg_A)) {
            ZYPEZDItemValueSetter.setValue(tMsg.thirdPtyTechFlg, bizMsg.A.no(i).thirdPtyTechFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.thirdPtyTechFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).crmSlsExclFlg_A)) {
            ZYPEZDItemValueSetter.setValue(tMsg.crmSlsExclFlg, bizMsg.A.no(i).crmSlsExclFlg_A);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.crmSlsExclFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.crmSlsPrflNm, bizMsg.A.no(i).crmSlsPrflNm_A);
    }

    private boolean checkDuplicate(NMAL2700CMsg bizMsg) {
        ArrayList<String> roleTpCdList = new ArrayList<String>(LIST_SIZE_200);
        ArrayList<String> roleTpNmList = new ArrayList<String>(LIST_SIZE_200);
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (!NMAL2700CommonLogic.isExistRecord(bizMsg, i)) {
                roleTpCdList.add(bizMsg.A.no(i).orgFuncRoleTpCd_A.getValue());
                roleTpNmList.add(bizMsg.A.no(i).orgFuncRoleTpNm_A.getValue());
            }
        }

        if (roleTpCdList.size() == 0) {
            return false;
        }

        S21SsmEZDResult ssmResult = NMAL2700Query.getInstance().roleCodeDuplicatedCheck(bizMsg, roleTpCdList);
        if (!ssmResult.isCodeNotFound()) {

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
                String roleCode = (String) resultMap.get("ORG_FUNC_ROLE_TP_CD");
                for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                    if (bizMsg.A.no(j).orgFuncRoleTpCd_A.getValue().equals(roleCode)) {
                        bizMsg.A.no(j).orgFuncRoleTpCd_A.setErrorInfo(1, NMAM0072E, new String[] {"Role Maintenance Detail" });
                    }
                }
            }
            return true;
        }

        // 2016/06/08 CSA-QC#9324 Add Start
        if (roleTpNmList.size() == 0) {
            return false;
        }

        ssmResult = NMAL2700Query.getInstance().roleNameDuplicatedCheck(bizMsg, roleTpNmList);
        if (!ssmResult.isCodeNotFound()) {

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
                String roleName = (String) resultMap.get("ORG_FUNC_ROLE_TP_NM");
                for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                    if (bizMsg.A.no(j).orgFuncRoleTpNm_A.getValue().equals(roleName)) {
                        bizMsg.A.no(j).orgFuncRoleTpNm_A.setErrorInfo(1, NMAM0072E, new String[] {"Role Maintenance Detail" });
                    }
                }
            }
            return true;
        }
        // 2016/06/08 CSA-QC#9324 Add End

        return false;
    }

    /**
     * DeleteRow_RoleMnt Event
     * @param bizMsg Business
     * @param glblMsg Global
     */
    private void doProcess_NMAL2700Scrn00_DeleteRow_RoleMnt(NMAL2700CMsg bizMsg, NMAL2700SMsg glblMsg) {
        boolean checkFlag = false;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxChkBox_A)) {
                checkFlag = true;
            }
        }

        if (!checkFlag) {
            bizMsg.setMessageInfo(NMAM0835E);
            return;
        }

        List<Map<String, Object>> resultList = existCheckRequestRole(bizMsg, null);
        if (resultList != null && !resultList.isEmpty()) {
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
                String roleCode = (String) resultMap.get("ORG_FUNC_ROLE_TP_CD");
                List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.A, CHK_A, FLG_ON_Y);
                for (int rowIdx : selectRows) {
                    if (bizMsg.A.no(rowIdx).orgFuncRoleTpCd_A.getValue().equals(roleCode)) {
                        bizMsg.A.no(rowIdx).orgFuncRoleTpCd_A.setErrorInfo(1, NMAM8337E, new String[] {roleCode });
                    }
                }
            }
            return;
        }

        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.A, CHK_A, FLG_ON_Y);
        for (int idx : selectRows) {
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(bizMsg.Z.getValidCount()).orgFuncRoleTpCd_Z, bizMsg.A.no(idx).orgFuncRoleTpCd_A);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(bizMsg.Z.getValidCount()).ezUpTime_Z, bizMsg.A.no(idx).ezUpTime_A);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(bizMsg.Z.getValidCount()).ezUpTimeZone_Z, bizMsg.A.no(idx).ezUpTimeZone_A);
            bizMsg.Z.setValidCount(bizMsg.Z.getValidCount() + 1);
        }

        ZYPTableUtil.deleteRows(bizMsg.A, selectRows);
        ZYPTableUtil.deleteRows(glblMsg.A, selectRows);

    }

    private List<Map<String, Object>> existCheckRequestRole(NMAL2700CMsg bizMsg, String orgFuncTp) {
        S21SsmEZDResult ssmResult = NMAL2700Query.getInstance().getRoleRequest(bizMsg, orgFuncTp);
        if (ssmResult.isCodeNormal()) {
            return (List<Map<String, Object>>) ssmResult.getResultObject();
        }
        return null;
    }
}
