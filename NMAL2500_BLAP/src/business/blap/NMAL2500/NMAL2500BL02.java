/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2500;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL2500.common.NMAL2500CommonLogic;
import business.blap.NMAL2500.constant.NMAL2500Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CSR_RG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * Org Resource Search  NMAL2500BL02
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/04   Fujitsu         J.Sakamoto      Create          N/A
 * 2015/10/19   Fujitsu         K.Koitabashi    Update          N/A
 * 2015/02/15   Fujitsu         C.Yokoi         Update          CSA-QC#1931
 * 2016/03/01   Fujitsu         M.suzuki        Update          CSA-QC#4540
 * 2016/03/02   Fujitsu         M.Nakamura      Update          CSA-QC#4533
 * 2016/07/26   Fujitsu         C.Yokoi         Update          CSA-QC#11652
 * 2016/09/01   Fujitsu         C.Yokoi         Update          CSA-QC#11652
 * 2016/09/21   Fujitsu         Mz.Takahashi    Update          QC#11068
 * 2016/06/30   Hitachi         K.Kasai         Update          QC#19617
 * 2018/11/01   Fujitsu         Hd.Sugawara     Update          QC#29014
 * </pre>
 */
public class NMAL2500BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2500CMsg bizMsg = (NMAL2500CMsg) cMsg;
            NMAL2500SMsg glblMsg = (NMAL2500SMsg) sMsg;

            if ("NMAL2500_INIT".equals(screenAplID)) {
                doProcess_NMAL2500_INIT(bizMsg, glblMsg);
            } else if ("NMAL2500Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL2500_Search(bizMsg, glblMsg);
            } else if ("NMAL2500Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2500Scrn00_CMN_Clear(bizMsg, glblMsg);
            } else if ("NMAL2500Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL2500Scrn00_CMN_Reset(bizMsg, glblMsg);
                // Del Start 2018/11/01 QC#29014
            //} else if ("NMAL2500Scrn00_Link_Resource".equals(screenAplID)) {
                //doProcess_NMAL2500Scrn00_Link_Resource(bizMsg, glblMsg);
                // Del End 2018/11/01 QC#29014
            } else if ("NMAL2500Scrn00_OnChange_BusinessArea".equals(screenAplID)) {
                doProcess_NMAL2500Scrn00_OnChange_BusinessArea(bizMsg, glblMsg);
            } else if ("NMAL2500Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NMAL2500Scrn00_OnChangeSavedSearchOption((NMAL2500CMsg) cMsg, (NMAL2500SMsg) sMsg);
            //START 2017/06/30 K.Kasai [QC#19617,ADD]
            } else if ("NMAL2500Scrn00_OnChange_ModeCode".equals(screenAplID)) {
                NMAL2500Scrn00_OnChange_ModeCode(bizMsg, glblMsg);
            //END 2017/06/30 K.Kasai [QC#19617,ADD]

            } else {
                return;
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Init Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2500_INIT(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {

        createPulldownList(cMsg, sMsg);
        NMAL2500CommonLogic.createSavedSearchOptionsPullDown(cMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());

    }

    // Del Start 2018/11/01 QC#29014
//    /**
//     * <pre>
//     * Event for Edit Resource
//     * </pre>
//     * @param bizMsg Business Msg
//     * @param sMsg Global Msg
//     */
//    private void doProcess_NMAL2500Scrn00_Link_Resource(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {
//
//        S21SsmEZDResult ssmResult;
//        ssmResult = NMAL2500Query.getInstance().getPsnNum(cMsg, sMsg);
//        if (!ssmResult.isCodeNormal()) {
//            cMsg.setMessageInfo(NMAL2500Constant.NMAM0038I);
//        } else {
//            List resultList = (List) ssmResult.getResultObject();
//            Map map = (Map) resultList.get(0);
//
//            String psnNum = (String) map.get(NMAL2500Constant.DB_COLUMN_PSN_NUM);
//            if (psnNum != null) {
//                cMsg.psnNum_H3.setValue(psnNum);
//            }
//        }
//
//    }
    // Del End 2018/11/01 QC#29014

    /**
     * <pre>
     * Init Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2500_Search(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {
        if (NMAL2500Constant.SEARCH_MODE_SHOW_HIERARCHY.equals(cMsg.xxModeCd_P1.getValue())) {

            cMsg.T.clear();
            cMsg.T.setValidCount(0);
            cMsg.R.clear();
            cMsg.R.setValidCount(0);
            cMsg.xxRsltFlg_H3.clear();

            S21SsmEZDResult ssmResult = null;
            if (NMAL2500Constant.DISPLAY_TOP_TIERS_ONLY == cMsg.xxRadioBtn_H1.getValueInt()) {
            // 2016/09/01 CSA-QC#11652 Mod Start
               // ssmResult = NMAL2500Query.getInstance().getOrganizationTopTier(cMsg, sMsg);
                ssmResult = NMAL2500Query.getInstance().getOrganizationHierarchy(cMsg, sMsg);
            // 2016/09/01 CSA-QC#11652 Mod End
            } else if (NMAL2500Constant.DISPLAY_ORG_LEVEL_CHILDREN_ONLY == cMsg.xxRadioBtn_H1.getValueInt()) {
                ssmResult = NMAL2500Query.getInstance().getOrganizationHierarchy(cMsg, sMsg);
            } else if (NMAL2500Constant.DISPLAY_ORG_LEVEL_CHILDREN_RESOURCE == cMsg.xxRadioBtn_H1.getValueInt()) {
                ssmResult = NMAL2500Query.getInstance().getOrganizationHierarchyWithResource(cMsg, sMsg);
            } else if (NMAL2500Constant.DISPLAY_ALL_LEVELS_EXPANDED_WITH_RESOURCE_ASSIGNMENT == cMsg.xxRadioBtn_H1.getValueInt()) {
                ssmResult = NMAL2500Query.getInstance().getOrganizationHierarchyWithResource(cMsg, sMsg);
            } else {
                return;
            }

            if (ssmResult.isCodeNormal()) {
                int queryResCnt = ssmResult.getQueryResultCount();
                if (queryResCnt > sMsg.T.length()) {
                    cMsg.setMessageInfo(NMAL2500Constant.NZZM0001W);
                    queryResCnt = sMsg.T.length();
                }

                // Add Start CSA#4556 2016/02/15
                int bizAreaNum = NMAL2500CommonLogic.getSelectBizAreaCd(cMsg);
                // Add End CSA#4556 2016/02/15

                // Move first page date from SMsg to CMsg
                int i = 0;
                for (; i < sMsg.T.getValidCount(); i++) {
                    if (i == cMsg.T.length()) {
                        break;
                    }

                    // 2016/02/15 CSA-QC#1931 Delete Start
                    // convDotToOrgName(sMsg, i);
                    // 2016/02/15 CSA-QC#1931 Delete End

                    EZDMsg.copy(sMsg.T.no(i), null, cMsg.T.no(i), null);

                   ZYPEZDItemValueSetter.setValue(cMsg.T.no(i).bizAreaOrgNm_T, cMsg.bizAreaOrgNm_H1.no(bizAreaNum));
                }
                cMsg.T.setValidCount(i);

            } else {
                cMsg.setMessageInfo(NMAL2500Constant.NMAM0038I);
                return;
            }

            if (NMAL2500Constant.DISPLAY_ORG_LEVEL_CHILDREN_RESOURCE == cMsg.xxRadioBtn_H1.getValueInt()
                    || NMAL2500Constant.DISPLAY_ALL_LEVELS_EXPANDED_WITH_RESOURCE_ASSIGNMENT == cMsg.xxRadioBtn_H1.getValueInt()) {
            // 2016/07/26 CSA-QC#11652 Mod Start
                if (getTargetOrg(cMsg, sMsg, false)) {
            // 2016/07/26 CSA-QC#11652 Mod End
                    if (!getTargetResource(cMsg, sMsg)) {
                        ZYPTableUtil.clear(cMsg.T);
                        ZYPTableUtil.clear(cMsg.R);
                        cMsg.xxRsltFlg_H3.clear();
                        cMsg.setMessageInfo(NMAL2500Constant.NMAM0038I);
                    }
                } else {
                    ZYPTableUtil.clear(cMsg.T);
                    ZYPTableUtil.clear(cMsg.R);
                    cMsg.xxRsltFlg_H3.clear();
                    cMsg.setMessageInfo(NMAL2500Constant.NMAM0038I);
                }
            // 2016/09/01 CSA-QC#11652 Add End
            } else if (NMAL2500Constant.DISPLAY_TOP_TIERS_ONLY == cMsg.xxRadioBtn_H1.getValueInt()) {
                if (!getTargetOrg(cMsg, sMsg, true)) {
                    ZYPTableUtil.clear(cMsg.T);
                    ZYPTableUtil.clear(cMsg.R);
                    cMsg.xxRsltFlg_H3.clear();
                    cMsg.setMessageInfo(NMAL2500Constant.NMAM0038I);
                }
            // 2016/09/01 CSA-QC#11652 Add End
            }else {
            // 2016/07/26 CSA-QC#11652 Mod Start
                if (!getTargetOrg(cMsg, sMsg, false)) {
            // 2016/07/26 CSA-QC#11652 Mod End
                    ZYPTableUtil.clear(cMsg.T);
                    ZYPTableUtil.clear(cMsg.R);
                    cMsg.xxRsltFlg_H3.clear();
                    cMsg.setMessageInfo(NMAL2500Constant.NMAM0038I);
                }
            }

        } else if (NMAL2500Constant.SEARCH_MODE_QUICK_RESOURCE_LOOK_UP.equals(cMsg.xxModeCd_P1.getValue())) {

            cMsg.A.clear();
            cMsg.A.setValidCount(0);

            S21SsmEZDResult ssmResult = NMAL2500Query.getInstance().getResource(cMsg, sMsg);

            if (ssmResult.isCodeNormal()) {

                int queryResCnt = ssmResult.getQueryResultCount();
                if (queryResCnt > sMsg.A.length()) {
                    cMsg.setMessageInfo(NMAL2500Constant.NZZM0001W);
                    queryResCnt = sMsg.A.length();
                }

                // Move first page date from SMsg to CMsg
                int i = 0;
                for (; i < sMsg.A.getValidCount(); i++) {
                    if (i == cMsg.A.length()) {
                        break;
                    }
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                }
                cMsg.A.setValidCount(i);

            } else {
                cMsg.setMessageInfo(NMAL2500Constant.NMAM0038I);
            }
        }
    }


    /**
     * The method explanation: Turn on the flag for Focus on
     * @param cMsg NMAL2500CMsg
     */
    public static void setFocusOnForOrganization(NMAL2500CMsg cMsg) {
        NMAL2500_TCMsg tMsg = null;
        NMAL2500_OCMsg oMsg = null;

        for (int t = 0; t < cMsg.T.getValidCount(); t++) {
            tMsg = cMsg.T.no(t);

            if (!ZYPCommonFunc.hasValue(tMsg.orgCd_T)) {
                continue;
            }

            for (int o = 0; o < cMsg.O.getValidCount(); o++) {
                oMsg = cMsg.O.no(o);
                if (tMsg.orgCd_T.getValue().equals(oMsg.orgCd_O1.getValue())) {
                    oMsg.xxFrtChgMethDnldFlg_O1.setValue(ZYPConstant.FLG_ON_Y);
                    return;
                }
            }
        }
    }

    /**
     * The method explanation: Turn on the flag for Focus on
     * @param cMsg NMAL2500CMsg
     */
    public static void setFocusOnForResource(NMAL2500CMsg cMsg) {
        NMAL2500_TCMsg tMsg = null;
        NMAL2500_RCMsg rMsg = null;

        for (int t = 0; t < cMsg.T.getValidCount(); t++) {
            tMsg = cMsg.T.no(t);
            if (!ZYPCommonFunc.hasValue(tMsg.psnCd_T)) {
                continue;
            }

            for (int r = 0; r < cMsg.R.getValidCount(); r++) {
                rMsg = cMsg.R.no(r);
                if (tMsg.psnCd_T.getValue().equals(rMsg.psnCd_R1.getValue())) {
                    rMsg.xxFrtChgMethDnldFlg_R1.setValue(ZYPConstant.FLG_ON_Y);
                    return;
                }
            }
        }
    }

    /**
     * The method explanation: Obtain Target Organization
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param boolean Top tier search flag
     * @return boolean
     */
    private boolean getTargetOrg(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg, boolean isTopTierSearch) {

        cMsg.O.clear();
        cMsg.O.setValidCount(0);
        // 2016/07/26 CSA-QC#11652 Mod Start
        S21SsmEZDResult ssmResult = null;
        // 2016/09/01 CSA-QC#11652 Mod Start
        if (isTopTierSearch) {
            ssmResult = NMAL2500Query.getInstance().getTargetOrgForTopTier(cMsg, sMsg);
        } else {
            ssmResult = NMAL2500Query.getInstance().getTargetOrg(cMsg, sMsg);
        }
        // 2016/09/01 CSA-QC#11652 Mod End
        // 2016/07/26 CSA-QC#11652 Mod End
        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.O.length()) {
                cMsg.setMessageInfo(NMAL2500Constant.NZZM0001W);
                queryResCnt = sMsg.O.length();
            }

            int i = 0;
            for (; i < sMsg.O.getValidCount(); i++) {
                if (i == cMsg.O.length()) {
                    break;
                }

                // 2016/02/15 CSA-QC#1931 Delete Start
                // convDotToOrgName(sMsg, i);
                // 2016/02/15 CSA-QC#1931 Delete End

                EZDMsg.copy(sMsg.O.no(i), null, cMsg.O.no(i), null);
            }
            cMsg.O.setValidCount(i);

            // 2016/02/15 CSA-QC#1931 Add Start
            setFocusOnForOrganization(cMsg);
            // 2016/02/15 CSA-QC#1931 Add End
        } else {
//            cMsg.setMessageInfo(NMAL2500Constant.NMAM0038I);
            return false;
        }

        return true;
    }
    /**
     * The method explanation: Obtain Target Resource
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @return boolean
     */
//    private void getTargetResource(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {
    private boolean getTargetResource(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {

        if (!NMAL2500CommonLogic.hasResourceSearchResult(cMsg)) {
            return true;
        }

        S21SsmEZDResult ssmResult = NMAL2500Query.getInstance().getTargetResource(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.R.length()) {
                cMsg.setMessageInfo(NMAL2500Constant.NZZM0001W);
                queryResCnt = sMsg.R.length();
            }

            int i = 0;
            for (; i < sMsg.R.getValidCount(); i++) {
                if (i == cMsg.R.length()) {
                    break;
                }

                // 2016/02/15 CSA-QC#1931 Delete Start
                // convDotToOrgName(sMsg, i);
                // 2016/02/15 CSA-QC#1931 Delete End

                EZDMsg.copy(sMsg.R.no(i), null, cMsg.R.no(i), null);
            }
            cMsg.R.setValidCount(i);

            // 2016/02/15 CSA-QC#1931 Add Start
            if (i > 0 && NMAL2500CommonLogic.hasResourceSearchResult(cMsg)) {
                cMsg.xxRsltFlg_H3.setValue(ZYPConstant.FLG_ON_Y);
                setFocusOnForResource(cMsg);
//            } else {
//                setFocusOnForOrganization(cMsg);
            }
            // 2016/02/15 CSA-QC#1931 Add End

        } else {
//            cMsg.setMessageInfo(NMAL2500Constant.NMAM0038I);
            return false;
        }

        return true;
    }

    /**
     * The method explanation: create Pulldown List.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void createPulldownList(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {

        S21UserProfileService profileAccessor = getUserProfileService();
        String globalCmpyCd = profileAccessor.getGlobalCompanyCode();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(NMAL2500Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("globalCmpyCd", globalCmpyCd);
        ssmParam.put("modeCd", NMAL2500Constant.VAR_CHAR_CONST_NM_DBCOLUMN);

        // Business Area
        S21SsmEZDResult bizAreaOrgPulldownList = NMAL2500Query.getInstance().getBizAreaOrgPulldownList(ssmParam);

        if (bizAreaOrgPulldownList.isCodeNormal()) {

            List<Map> bizAreaOrgList = (List<Map>) bizAreaOrgPulldownList.getResultObject();
            NMAL2500CommonLogic.createPulldownList(cMsg.bizAreaOrgCd_H1, cMsg.bizAreaOrgNm_H1, bizAreaOrgList, new String[] {NMAL2500Constant.BIZ_AREA_ORG_CD_DBCOLUMN, NMAL2500Constant.BIZ_AREA_ORG_NM_DBCOLUMN });
        }

        // Search Mode
        S21SsmEZDResult varCharConstPulldownList = NMAL2500Query.getInstance().getVarCharConstPulldownList(ssmParam);

        if (varCharConstPulldownList.isCodeNormal()) {

            List<Map> varCharConstList = (List<Map>) varCharConstPulldownList.getResultObject();
            NMAL2500CommonLogic.createPulldownList(cMsg.xxModeCd_H1, cMsg.xxModeNm23Txt_H1, varCharConstList, new String[] {NMAL2500Constant.VAR_CHAR_CONST_VAL_DBCOLUMN, NMAL2500Constant.VAR_CHAR_CONST_DESC_TXT_DBCOLUMN });
        }

        // Organization Tier
        S21SsmEZDResult orgTierPulldownList = NMAL2500Query.getInstance().getOrgTierPulldownList(ssmParam);

        if (orgTierPulldownList.isCodeNormal()) {

            List<Map> orgTierList = (List<Map>) orgTierPulldownList.getResultObject();
            NMAL2500CommonLogic.createPulldownList(cMsg.orgTierCd_H2, cMsg.orgTierNm_H2, orgTierList, new String[] {NMAL2500Constant.ORG_TIER_CD_DBCOLUMN, NMAL2500Constant.ORG_TIER_NM_DBCOLUMN });
        }

        // Customer Service Request Region Type
        //2016/03/01 S21_NA#4540 Mod Start ------------
        ZYPCodeDataUtil.createPulldownList(CSR_RG_TP.class, cMsg.csrRgTpCd_H2, cMsg.csrRgTpDescTxt_H2);
        //2016/03/01 S21_NA#4540 Mod End --------------

        // Organization Function Role Type
        ssmParam.put("defaultBusinessArea", 1);
        S21SsmEZDResult orgFuncRoleTpPulldownList = NMAL2500Query.getInstance().getOrgFuncRoleTpPulldownList(ssmParam);

        if (orgFuncRoleTpPulldownList.isCodeNormal()) {

            List<Map> orgFuncRoleTpList = (List<Map>) orgFuncRoleTpPulldownList.getResultObject();
            NMAL2500CommonLogic.createPulldownList(cMsg.orgFuncRoleTpCd_H3, cMsg.orgFuncRoleTpNm_H3, orgFuncRoleTpList, new String[] {NMAL2500Constant.ORG_FUNC_ROLE_TP_CD_DBCOLUMN, NMAL2500Constant.ORG_FUNC_ROLE_TP_NM_DBCOLUMN });
        }

    }

    private void doProcess_NMAL2500Scrn00_CMN_Clear(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {

        String currentTab = cMsg.xxDplyTab.getValue();

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.T.clear();
        cMsg.T.setValidCount(0);
        cMsg.clear();
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.T.clear();
        sMsg.T.setValidCount(0);
        sMsg.clear();

        cMsg.xxDplyTab.setValue(currentTab);

        doProcess_NMAL2500_INIT(cMsg, sMsg);
    }

    private void doProcess_NMAL2500Scrn00_CMN_Reset(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {

        String currentTab = cMsg.xxDplyTab.getValue();

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.T.clear();
        cMsg.T.setValidCount(0);
        cMsg.clear();
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.T.clear();
        sMsg.T.setValidCount(0);
        sMsg.clear();

        cMsg.xxDplyTab.setValue(currentTab);

        doProcess_NMAL2500_INIT(cMsg, sMsg);
    }

    //START 2017/06/30 K.Kasai [QC#19617,ADD]
    private void NMAL2500Scrn00_OnChange_ModeCode(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {

        String currentTab = cMsg.xxDplyTab.getValue();
        String searchMode = cMsg.xxModeCd_P1.getValue();

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.T.clear();
        cMsg.T.setValidCount(0);
        cMsg.clear();
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.T.clear();
        sMsg.T.setValidCount(0);
        sMsg.clear();

        cMsg.xxDplyTab.setValue(currentTab);
        cMsg.xxModeCd_P1.setValue(searchMode);

        doProcess_NMAL2500_INIT(cMsg, sMsg);
    }
    //END 2017/06/30 K.Kasai [QC#19617,ADD]

    private void doProcess_NMAL2500Scrn00_OnChange_BusinessArea(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {

        cMsg.orgFuncRoleTpCd_H3.clear();
        cMsg.orgFuncRoleTpNm_H3.clear();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(NMAL2500Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("globalCmpyCd", getGlobalCompanyCode());

        // Organization Function Role Type
        S21SsmEZDResult orgFuncRoleTpWithBusinessAreaPulldownList = NMAL2500Query.getInstance().getOrgFuncRoleTpWithBusinessAreaPulldownList(ssmParam);

        if (orgFuncRoleTpWithBusinessAreaPulldownList.isCodeNormal()) {

            List<Map> orgFuncRoleTpWithBusinessAreaList = (List<Map>) orgFuncRoleTpWithBusinessAreaPulldownList.getResultObject();
            NMAL2500CommonLogic.createPulldownList(cMsg.orgFuncRoleTpCd_H3, cMsg.orgFuncRoleTpNm_H3, orgFuncRoleTpWithBusinessAreaList, new String[] {NMAL2500Constant.ORG_FUNC_ROLE_TP_CD_DBCOLUMN,
                    NMAL2500Constant.ORG_FUNC_ROLE_TP_NM_DBCOLUMN });
        }

    }

    private void convDotToOrgName(NMAL2500SMsg sMsg, int i) {

        String targetTier = sMsg.T.no(i).orgTierCd_T.getValue();

        if ("1".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                sMsg.T.no(i).orgNm_0.setValue(NMAL2500Constant.DOT);
            }
        } else if ("2".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
                clearDotForNoParentOrg(sMsg, i);
            } else {
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                    sMsg.T.no(i).orgNm_0.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                    sMsg.T.no(i).orgNm_1.setValue(NMAL2500Constant.DOT);
                }
            }
        } else if ("3".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
                clearDotForNoParentOrg(sMsg, i);
            } else {
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                    sMsg.T.no(i).orgNm_0.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                    sMsg.T.no(i).orgNm_1.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
                    sMsg.T.no(i).orgNm_2.setValue(NMAL2500Constant.DOT);
                }
            }
        } else if ("4".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
                clearDotForNoParentOrg(sMsg, i);
            } else {
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                    sMsg.T.no(i).orgNm_0.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                    sMsg.T.no(i).orgNm_1.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
                    sMsg.T.no(i).orgNm_2.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
                    sMsg.T.no(i).orgNm_3.setValue(NMAL2500Constant.DOT);
                }
            }
        } else if ("5".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_5)) {
                clearDotForNoParentOrg(sMsg, i);
            } else {
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                    sMsg.T.no(i).orgNm_0.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                    sMsg.T.no(i).orgNm_1.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
                    sMsg.T.no(i).orgNm_2.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
                    sMsg.T.no(i).orgNm_3.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
                    sMsg.T.no(i).orgNm_4.setValue(NMAL2500Constant.DOT);
                }
            }
        } else if ("6".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_6)) {
                clearDotForNoParentOrg(sMsg, i);
            } else {
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                    sMsg.T.no(i).orgNm_0.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                    sMsg.T.no(i).orgNm_1.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
                    sMsg.T.no(i).orgNm_2.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
                    sMsg.T.no(i).orgNm_3.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
                    sMsg.T.no(i).orgNm_4.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_5)) {
                    sMsg.T.no(i).orgNm_5.setValue(NMAL2500Constant.DOT);
                }
            }
        } else if ("7".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_7)) {
                clearDotForNoParentOrg(sMsg, i);
            } else {
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                    sMsg.T.no(i).orgNm_0.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                    sMsg.T.no(i).orgNm_1.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
                    sMsg.T.no(i).orgNm_2.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
                    sMsg.T.no(i).orgNm_3.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
                    sMsg.T.no(i).orgNm_4.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_5)) {
                    sMsg.T.no(i).orgNm_5.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_6)) {
                    sMsg.T.no(i).orgNm_6.setValue(NMAL2500Constant.DOT);
                }
            }
        } else if ("8".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_8)) {
                clearDotForNoParentOrg(sMsg, i);
            } else {
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                    sMsg.T.no(i).orgNm_0.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                    sMsg.T.no(i).orgNm_1.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
                    sMsg.T.no(i).orgNm_2.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
                    sMsg.T.no(i).orgNm_3.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
                    sMsg.T.no(i).orgNm_4.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_5)) {
                    sMsg.T.no(i).orgNm_5.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_6)) {
                    sMsg.T.no(i).orgNm_6.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_7)) {
                    sMsg.T.no(i).orgNm_7.setValue(NMAL2500Constant.DOT);
                }
            }

        } else if ("9".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_9)) {
                clearDotForNoParentOrg(sMsg, i);
            } else {
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                    sMsg.T.no(i).orgNm_0.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                    sMsg.T.no(i).orgNm_1.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
                    sMsg.T.no(i).orgNm_2.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
                    sMsg.T.no(i).orgNm_3.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
                    sMsg.T.no(i).orgNm_4.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_5)) {
                    sMsg.T.no(i).orgNm_5.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_6)) {
                    sMsg.T.no(i).orgNm_6.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_7)) {
                    sMsg.T.no(i).orgNm_7.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_8)) {
                    sMsg.T.no(i).orgNm_8.setValue(NMAL2500Constant.DOT);
                }
            }
        } else if ("10".equals(targetTier)) {
            if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_10)) {
                clearDotForNoParentOrg(sMsg, i);
            } else {
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_0)) {
                    sMsg.T.no(i).orgNm_0.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_1)) {
                    sMsg.T.no(i).orgNm_1.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_2)) {
                    sMsg.T.no(i).orgNm_2.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_3)) {
                    sMsg.T.no(i).orgNm_3.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_4)) {
                    sMsg.T.no(i).orgNm_4.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_5)) {
                    sMsg.T.no(i).orgNm_5.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_6)) {
                    sMsg.T.no(i).orgNm_6.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_7)) {
                    sMsg.T.no(i).orgNm_7.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_8)) {
                    sMsg.T.no(i).orgNm_8.setValue(NMAL2500Constant.DOT);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.T.no(i).orgNm_9)) {
                    sMsg.T.no(i).orgNm_9.setValue(NMAL2500Constant.DOT);
                }
            }
        }
    }

    private void clearDotForNoParentOrg(NMAL2500SMsg sMsg, int i) {

        sMsg.T.no(i).orgNm_2.clear();
        sMsg.T.no(i).orgNm_3.clear();
        sMsg.T.no(i).orgNm_4.clear();
        sMsg.T.no(i).orgNm_5.clear();
        sMsg.T.no(i).orgNm_6.clear();
        sMsg.T.no(i).orgNm_7.clear();
        sMsg.T.no(i).orgNm_8.clear();
        sMsg.T.no(i).orgNm_9.clear();
        sMsg.T.no(i).orgNm_10.clear();

    }

    /**
     * doProcess_NMAL2500Scrn00_OnChangeSavedSearchOption
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NMAL2500Scrn00_OnChangeSavedSearchOption(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            NMAL2500CommonLogic.callNszc0330forSearchOption(cMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
            doProcess_NMAL2500Scrn00_OnChange_BusinessArea(cMsg, sMsg);
        }
    }
}
