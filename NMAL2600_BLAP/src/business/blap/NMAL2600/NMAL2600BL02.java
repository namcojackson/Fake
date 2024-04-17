/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2600;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSMsg;

import business.blap.NMAL2600.common.NMAL2600CommonLogic;
import business.blap.NMAL2600.constant.NMAL2600Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * PR Release  NPAL1030BL02
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/04   Fujitsu         J.Sakamoto      Create          N/A
 * 2015/12/01   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/25   Fujitsu         C.Yokoi         Update          CSA-QC#6044
 * 2016/08/24   Fujitsu         C.Yokoi         Update          CSA-QC#11652
 * 2016/09/21   Fujitsu         Mz.Takahashi    Update          QC#11068
 * </pre>
 */
public class NMAL2600BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2600CMsg bizMsg = (NMAL2600CMsg) cMsg;
            NMAL2600SMsg glblMsg = (NMAL2600SMsg) sMsg;

            if ("NMAL2600_INIT".equals(screenAplID)) {
                doProcess_NMAL2600_INIT(bizMsg, glblMsg);
            } else if ("NMAL2600Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL2600_Search(bizMsg, glblMsg);
            } else if ("NMAL2600Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NMAL2600Scrn00_InsertRow(bizMsg, glblMsg);
            } else if ("NMAL2600Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2600Scrn00_CMN_Clear(bizMsg, glblMsg);
            } else if ("NMAL2600Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL2600Scrn00_CMN_Reset(bizMsg, glblMsg);
            } else if ("NMAL2600Scrn00_OpenWin_AccountRules".equals(screenAplID)) {
                doProcess_NMAL2600Scrn00_OpenWin_AccountRules(bizMsg, glblMsg);
            } else if ("NMAL2600Scrn00_OpenWin_ResourceAssign".equals(screenAplID)) {
                doProcess_NMAL2600Scrn00_OpenWin_ResourceAssign(bizMsg, glblMsg);
            } else if ("NMAL2600Scrn00_OpenWin_Territory".equals(screenAplID)) {
                doProcess_NMAL2600Scrn00_OpenWin_Territory(bizMsg, glblMsg);
            } else if ("NMAL2600Scrn00_OpenWin_TerritoryRules".equals(screenAplID)) {
                doProcess_NMAL2600Scrn00_OpenWin_TerritoryRules(bizMsg, glblMsg);
            } else if ("NMAL2600Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NMAL2600Scrn00_OnChangeSavedSearchOption((NMAL2600CMsg) cMsg, (NMAL2600SMsg) sMsg);

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
    private void doProcess_NMAL2600_INIT(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {

        S21UserProfileService profileAccessor = getUserProfileService();
        String globalCmpyCd = profileAccessor.getGlobalCompanyCode();

        NMAL2600CommonLogic.createPulldownList(cMsg, sMsg, globalCmpyCd);

        for (int i = 0; i < NMAL2600Constant.ADV_DEF_CNT; i++) {
            doProcess_NMAL2600Scrn00_InsertRow((NMAL2600CMsg) cMsg, (NMAL2600SMsg) sMsg);
        }

        NMAL2600CommonLogic.createSavedSearchOptionsPullDown(cMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * <pre>
     * Search Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2600_Search(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {

        cMsg.T.clear();
        cMsg.T.setValidCount(0);

        S21SsmEZDResult ssmResult = null;
        if (NMAL2600Constant.RADIO_BUTTON_TOP_TERRITORY_ONLY == cMsg.xxRadioBtn_H1.getValueInt()) {
            // 2016/08/24 CSA-QC#11652 Mod Start
            ssmResult = NMAL2600Query.getInstance().getTerritory(cMsg, sMsg);
            // ssmResult = NMAL2600Query.getInstance().getTerritoryTopTier(cMsg, sMsg);
            // 2016/08/24 CSA-QC#11652 Mod End
        } else if (NMAL2600Constant.RADIO_BUTTON_ALL_TERRITORY_EXPANDED == cMsg.xxRadioBtn_H1.getValueInt()) {
            ssmResult = NMAL2600Query.getInstance().getTerritory(cMsg, sMsg);
        } else if (NMAL2600Constant.RADIO_BUTTON_TERRITORY_AND_CHILDREN_ONLY == cMsg.xxRadioBtn_H1.getValueInt()) {
            ssmResult = NMAL2600Query.getInstance().getTerritory(cMsg, sMsg);
        } else if (NMAL2600Constant.RADIO_BUTTON_TERRITORY_CHILDREN_RULES == cMsg.xxRadioBtn_H1.getValueInt()) {
            ssmResult = NMAL2600Query.getInstance().getTerritoryWithRules(cMsg, sMsg);
        } else {
            return;
        }

        if (ssmResult.isCodeNormal()) {
            cMsg.xxRsltFlg.setValue(ZYPConstant.FLG_ON_Y);

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.T.length()) {
                cMsg.setMessageInfo(NMAL2600Constant.NZZM0001W);
                queryResCnt = sMsg.T.length();
            }

            // Move first page date from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.T.getValidCount(); i++) {
                if (i == cMsg.T.length()) {
                    break;
                }

                // NMAL2600CommonLogic.convDotToOrgName(sMsg, i);
                EZDMsg.copy(sMsg.T.no(i), null, cMsg.T.no(i), null);
            }

            cMsg.T.setValidCount(i);
        } else {
            cMsg.setMessageInfo(NMAL2600Constant.NMAM0038I);
            cMsg.xxRsltFlg.setValue(ZYPConstant.FLG_OFF_N);
            return;
        }

        // 2016/08/24 CSA-QC#11652 Mod Start
        if (NMAL2600Constant.RADIO_BUTTON_TOP_TERRITORY_ONLY == cMsg.xxRadioBtn_H1.getValueInt()) {
            getTargetOrgForTopTierSearch(cMsg, sMsg);
        } else {
            getTargetOrg(cMsg, sMsg);
        }
        // getTargetOrg(cMsg, sMsg);
        // 2016/08/24 CSA-QC#11652 Mod Start
        // 2016/03/26 CSA-QC#6044 Add Start
        setFocusOnForOrganization(cMsg);
        // 2016/03/26 CSA-QC#6044 Add End
    }

    /**
     * <pre>
     * Insert Row Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2600Scrn00_InsertRow(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {

        EZDMsgArray targetArray = cMsg.A;
        int newRowIndex = targetArray.getValidCount();
        targetArray.get(newRowIndex).clear();
        targetArray.setValidCount(newRowIndex + 1);

    }

    /**
     * <pre>
     * Common Clear Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2600Scrn00_CMN_Clear(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.clear();
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.clear();
        doProcess_NMAL2600_INIT(cMsg, sMsg);
    }

    /**
     * <pre>
     * Common Reset Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2600Scrn00_CMN_Reset(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.clear();
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.clear();
        doProcess_NMAL2600_INIT(cMsg, sMsg);
    }

    /**
     * <pre>
     * Press button Account Rules Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2600Scrn00_OpenWin_AccountRules(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.orgNm_P)) {
            if (!NMAL2600CommonLogic.getAdditionalOrg(cMsg, sMsg)) {
                cMsg.setMessageInfo(NMAL2600Constant.NMAM8476E, new String[]{"Account Rules"});
            }
        }
    }

    /**
     * <pre>
     * Press button Resource Assign Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2600Scrn00_OpenWin_ResourceAssign(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.orgNm_P)) {
            if (!NMAL2600CommonLogic.getAdditionalOrg(cMsg, sMsg)) {
                cMsg.setMessageInfo(NMAL2600Constant.NMAM8476E, new String[]{"Resource Assignments"});
            }
        }
    }

    /**
     * <pre>
     * Press button Show Territory Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2600Scrn00_OpenWin_Territory(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.orgNm_P)) {
            cMsg.orgCd_P.clear();
            if (!NMAL2600CommonLogic.getAdditionalOrg(cMsg, sMsg)) {
                cMsg.setMessageInfo(NMAL2600Constant.NMAM8476E, new String[]{"Territory Detail"});
            }
        }
    }

    /**
     * <pre>
     * Press button Show Territory Rules Event
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2600Scrn00_OpenWin_TerritoryRules(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.orgNm_P)) {
            if (!NMAL2600CommonLogic.getAdditionalOrg(cMsg, sMsg)) {
                // 2016/04/25 CSA-QC#7547 Add Start
                cMsg.setMessageInfo(NMAL2600Constant.NMAM8476E, new String[]{"Rules"});
                // 2016/04/25 CSA-QC#7547 Add End
            }
        }
    }

    /**
     * The method explanation: Obtain Target Organization
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void getTargetOrgForTopTierSearch(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {
        cMsg.Q.clear();
        cMsg.Q.setValidCount(0);
        S21SsmEZDResult ssmResult = NMAL2600Query.getInstance().getTargetOrgForTopTierSearch(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.Q.length()) {
                cMsg.setMessageInfo(NMAL2600Constant.NZZM0001W);
                queryResCnt = sMsg.Q.length();
            }

            int i = 0;
            for (; i < sMsg.Q.getValidCount(); i++) {
                if (i == cMsg.Q.length()) {
                    break;
                }

                // NMAL2600CommonLogic.convDotToOrgName(sMsg, i);
                EZDMsg.copy(sMsg.Q.no(i), null, cMsg.Q.no(i), null);
            }
            cMsg.Q.setValidCount(i);

        } else {
            cMsg.setMessageInfo(NMAL2600Constant.NMAM0038I);
            cMsg.xxRsltFlg.setValue(ZYPConstant.FLG_OFF_N);
            return;
        }
    }

    /**
     * The method explanation: Obtain Target Organization
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void getTargetOrg(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg) {

        cMsg.Q.clear();
        cMsg.Q.setValidCount(0);
        S21SsmEZDResult ssmResult = NMAL2600Query.getInstance().getTargetOrg(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.Q.length()) {
                cMsg.setMessageInfo(NMAL2600Constant.NZZM0001W);
                queryResCnt = sMsg.Q.length();
            }

            int i = 0;
            for (; i < sMsg.Q.getValidCount(); i++) {
                if (i == cMsg.Q.length()) {
                    break;
                }

                // NMAL2600CommonLogic.convDotToOrgName(sMsg, i);
                EZDMsg.copy(sMsg.Q.no(i), null, cMsg.Q.no(i), null);
            }
            cMsg.Q.setValidCount(i);

        } else {
            cMsg.setMessageInfo(NMAL2600Constant.NMAM0038I);
            cMsg.xxRsltFlg.setValue(ZYPConstant.FLG_OFF_N);
            return;
        }
    }

    /**
     * The method explanation: Turn on the flag for Focus on
     * @param cMsg NMAL2600CMsg
     */
    public void setFocusOnForOrganization(NMAL2600CMsg cMsg) {
        // 2016/03/26 CSA-QC#6044 Add Start
        NMAL2600_TCMsg tMsg = null;
        NMAL2600_QCMsg qMsg = null;

        // if there is no target, do nothing
        if (cMsg.Q.getValidCount() < 1) {
            return;
        }

        // flag on Y to first display territory
        for (int t = 0; t < cMsg.T.getValidCount(); t++) {
            tMsg = cMsg.T.no(t);

            if (!ZYPCommonFunc.hasValue(tMsg.orgCd_T)) {
                continue;
            }

            for (int q = 0; q < cMsg.Q.getValidCount(); q++) {
                qMsg = cMsg.Q.no(q);

                if (tMsg.orgCd_T.getValue().equals(qMsg.orgCd_Q.getValue())) {
                    qMsg.xxFrtChgMethDnldFlg_Q.setValue(ZYPConstant.FLG_ON_Y);
                    return;
                }
            }
        }
        // 2016/03/26 CSA-QC#6044 Add End
    }

    /**
     * doProcess_NMAL2600Scrn00_OnChangeSavedSearchOption
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NMAL2600Scrn00_OnChangeSavedSearchOption(NMAL2600CMsg cMsg, NMAL2600SMsg sMsg){
        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            cMsg.A.clear();
            cMsg.A.setValidCount(0);
            sMsg.A.clear();
            sMsg.A.setValidCount(0);

            NMAL2600CommonLogic.callNszc0330forSearchOption(cMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }
}
