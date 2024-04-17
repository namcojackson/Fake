/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */

package business.blap.NPAL1010;

import static business.blap.NPAL1010.constant.NPAL1010Constant.BLANK;
import static business.blap.NPAL1010.constant.NPAL1010Constant.BUSINESS_ID;
import static business.blap.NPAL1010.constant.NPAL1010Constant.DATA_SECUR_ATTR;
import static business.blap.NPAL1010.constant.NPAL1010Constant.EVENT_INIT;
import static business.blap.NPAL1010.constant.NPAL1010Constant.EVENT_ONCLICK_MGRNM;
import static business.blap.NPAL1010.constant.NPAL1010Constant.EVENT_OPENWIN_MGR;
import static business.blap.NPAL1010.constant.NPAL1010Constant.EVENT_SEARCH_LOCATION;
import static business.blap.NPAL1010.constant.NPAL1010Constant.MULTIPLICATION;
import static business.blap.NPAL1010.constant.NPAL1010Constant.NPAM0020E;
import static business.blap.NPAL1010.constant.NPAL1010Constant.NPAM1361E;
import static business.blap.NPAL1010.constant.NPAL1010Constant.ZZM9501E;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1010.common.NPAL1010CommonLogic;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityProfile;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * NPAL1010 Location Info Pop Up
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/06/26   Fujitsu         S.Noguchi       Create          N/A
 * 2012/09/13   Fujitsu         Y.Kamide        Update          N/A
 * 2013/05/14   Fujitsu         H.Mizutani      Update          N/A
 * 2015/04/28   CITS            H.Sugawara      Update          N/A
 * 2016/02/23   CSAI            D.Fukaya        Update          QC#2378
 * 2016/02/24   CSAI            D.Fukaya        Update          QC#4142
 * 03/18/2016   CSAI            Y.Imazu         Update          QC#3134
 * 05/04/2016   CSAI            D.Fukaya        Update          QC#7629
 *</pre>
 */
public class NPAL1010BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NPAL1010CMsg bizMsg = (NPAL1010CMsg) cMsg;

            if (EVENT_INIT.equals(screenAplID)) {
                doProcess_NPAL1010_INIT(bizMsg);

            } else if (EVENT_SEARCH_LOCATION.equals(screenAplID)) {
                doProcess_NPAL1010_Search_Location(bizMsg);

            } else if (EVENT_OPENWIN_MGR.equals(screenAplID)) {
                doProcess_NPAL1010Scrn00_OpenWin_Mgr(bizMsg);

            } else if (EVENT_ONCLICK_MGRNM.equals(screenAplID)) {
                doProcess_NPAL1010Scrn00_OnClick_MgrNm(bizMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID :" + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NPAL1010_INIT
     * @param bizMsg NPAL1010CMsg
     */
    private void doProcess_NPAL1010_INIT(NPAL1010CMsg bizMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

            // WDS R-OM025 Inventory Transaction Mizutani Start
        if (BLANK.equals(bizMsg.locRoleTpCd_A2.no(0).getValue())) {

            String locRoleTpString = NMXC100001EnableWH.getLocRoleTpForPopup(glblCmpyCd, BUSINESS_ID);

            if (!ZYPCommonFunc.hasValue(locRoleTpString)) {

                bizMsg.setMessageInfo(ZZM9501E);
                return;

            } else {

                List<String> locRoleTpCdList = Arrays.asList(locRoleTpString.split(","));

                for (int i = 0; i <= locRoleTpCdList.size() - 1; i++) {

                    if (i >= bizMsg.locRoleTpCd_A2.length()) {

                        break;
                    }

                    bizMsg.locRoleTpCd_A2.no(i).setValue(locRoleTpCdList.get(i));
                }
            }
        }

        int count = bizMsg.locRoleTpCd_A2.length();
        ArrayList<String> locRoleTpCdList = new ArrayList<String>();

        for (int i = 0; i < count; i++) {

            if (!ZYPCommonFunc.hasValue(bizMsg.locRoleTpCd_A2.no(i))) {

                break;
            }

            locRoleTpCdList.add(bizMsg.locRoleTpCd_A2.no(i).getValue());
        }

        S21SsmEZDResult ssmResult = NPAL1010CommonLogic.searchUsgPullDown(bizMsg, glblCmpyCd, locRoleTpCdList);

        if (!ssmResult.isCodeNormal()) {

            bizMsg.setMessageInfo(NPAM0020E, new String[]{"LOC_ROLE_TP Table"});
            return;
        }

        List<Map<String, String>> locRoleTpList = (List<Map<String, String>>) ssmResult.getResultObject();
        bizMsg.locRoleTpCd_A2.clear();
        bizMsg.locRoleTpNm_A3.clear();

        for (int i = 0; i < locRoleTpList.size(); i++) {

            if (i >= bizMsg.locRoleTpCd_A2.length() || i >= bizMsg.locRoleTpNm_A3.length()) {

                break;
            }

            Map<String, String> locRoleTpMap = locRoleTpList.get(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.locRoleTpCd_A2.no(i), locRoleTpMap.get("LOC_ROLE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.locRoleTpNm_A3.no(i), locRoleTpMap.get("LOC_ROLE_TP_NM"));
        }

        // START 2015/04/28 H.Sugawara E850 Warehouse Setup
        // WH Category PullDown
        S21SsmEZDResult ssmResultCatg = NPAL1010CommonLogic.searchCatgPullDown(bizMsg, glblCmpyCd);
        bizMsg.rtlWhCatgCd_B2.clear();
        bizMsg.rtlWhCatgDescTxt_B3.clear();

        if (ssmResultCatg.isCodeNormal()) {

            List<Map<String, String>> whCatgList = (List<Map<String, String>>) ssmResultCatg.getResultObject();

            for (int i = 0; i < whCatgList.size(); i++) {

                Map<String, String> locRoleTpMap = whCatgList.get(i);

                if (i >= bizMsg.rtlWhCatgCd_B2.length() || i >= bizMsg.rtlWhCatgDescTxt_B3.length()) {

                    break;
                }

                ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCatgCd_B2.no(i), locRoleTpMap.get("RTL_WH_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCatgDescTxt_B3.no(i), locRoleTpMap.get("RTL_WH_CATG_DESC_TXT"));
            }
        }
        // END 2015/04/28 H.Sugawara E850 Warehouse Setup

        // 10/28/2015 add start
        // Inv Accnt PullDown
        S21SsmEZDResult ssmResultInvtyAcct = NPAL1010CommonLogic.searchInvtyAcctPullDown(bizMsg, glblCmpyCd);
        bizMsg.invtyAcctCd_A2.clear();
        bizMsg.invtyAcctNm_A3.clear();

        if (ssmResultInvtyAcct.isCodeNormal()) {

            List<Map<String, String>> invtyAcctList = (List<Map<String, String>>) ssmResultInvtyAcct.getResultObject();

            for (int i = 0; i < invtyAcctList.size(); i++) {

                Map<String, String> invtyAcctMap = invtyAcctList.get(i);

                if (i >= bizMsg.invtyAcctCd_A2.length() || i >= bizMsg.invtyAcctNm_A3.length()) {

                    break;
                }

                ZYPEZDItemValueSetter.setValue(bizMsg.invtyAcctCd_A2.no(i), invtyAcctMap.get("INVTY_ACCT_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.invtyAcctNm_A3.no(i), invtyAcctMap.get("INVTY_ACCT_NM"));
            }
        }
        // 10/28/2015 add end

        // Invty Ownership PullDown
        S21SsmEZDResult ssmResultInvtyOwner = NPAL1010CommonLogic.searchInvtyOwnerPullDown(bizMsg, glblCmpyCd);
        bizMsg.invtyOwnrCd_A2.clear();
        bizMsg.invtyOwnrDescTxt_A3.clear();

        if (ssmResultInvtyAcct.isCodeNormal()) {

            List<Map<String, String>> invtyOwnerList = (List<Map<String, String>>) ssmResultInvtyOwner.getResultObject();

            for (int i = 0; i < invtyOwnerList.size(); i++) {

                Map<String, String> invtyOwnerMap = invtyOwnerList.get(i);

                if (i >= bizMsg.invtyOwnrCd_A2.length() || i >= bizMsg.invtyOwnrDescTxt_A3.length()) {

                    break;
                }

                ZYPEZDItemValueSetter.setValue(bizMsg.invtyOwnrCd_A2.no(i), invtyOwnerMap.get("INVTY_OWNR_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.invtyOwnrDescTxt_A3.no(i), invtyOwnerMap.get("INVTY_OWNR_DESC_TXT"));
            }
        }

        // WH Ownership PullDown
        S21SsmEZDResult ssmResultWhOperation = NPAL1010CommonLogic.searchWhOperationPullDown(bizMsg, glblCmpyCd);
        bizMsg.whOwnrCd_A2.clear();
        bizMsg.whOwnrDescTxt_A3.clear();

        if (ssmResultWhOperation.isCodeNormal()) {

            List<Map<String, String>> whOperationList = (List<Map<String, String>>) ssmResultWhOperation.getResultObject();

            for (int i = 0; i < whOperationList.size(); i++) {

                Map<String, String> whOperationMap = whOperationList.get(i);

                if (i >= bizMsg.whOwnrCd_A2.length() || i >= bizMsg.whOwnrDescTxt_A3.length()) {

                    break;
                }

                ZYPEZDItemValueSetter.setValue(bizMsg.whOwnrCd_A2.no(i), whOperationMap.get("WH_OWNR_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.whOwnrDescTxt_A3.no(i), whOperationMap.get("WH_OWNR_DESC_TXT"));
            }
        }

        List<String> userProfile = null;

        // When Data security flag is on , get User prifile.
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkInpValFlg.getValue())) {

            userProfile = getWHDataSecurity(bizMsg);
        }

        NPAL1010CommonLogic.searchInstallLcoation(bizMsg, glblCmpyCd, userProfile);
        // WDS R-OM025 Inventory Transaction Mizutani End
    }

    /**
     * doProcess_NPAL1010_Search_InstallLocation
     * @param bizMsg
     * @param shareMsg
     */
    private void doProcess_NPAL1010_Search_Location(NPAL1010CMsg bizMsg) {

        // WDS R-OM025 Inventory Transaction Mizutani Start
        List<String> userProfile = null;

        // When Data security flag is on , get User prifile.
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkInpValFlg.getValue())) {

            userProfile = getWHDataSecurity(bizMsg);
        }
        // WDS R-OM025 Inventory Transaction Mizutani End

        NPAL1010CommonLogic.searchInstallLcoation(bizMsg, getGlobalCompanyCode(), userProfile);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OpenWin_Mgr] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NPAL1010Scrn00_OpenWin_Mgr(NPAL1010CMsg cMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCatgCd)) {
            NPAL1010Query.getInstance().getLocTp(cMsg, getGlobalCompanyCode());
        } else {
            cMsg.locTpCd_H1.clear();
        }
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick_MgrNm] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NPAL1010Scrn00_OnClick_MgrNm(NPAL1010CMsg cMsg) {

        cMsg.fullPsnNm_H1.clear();

        if (ZYPCommonFunc.hasValue(cMsg.whMgrPsnCd_H1)) {

            if (ZYPCommonFunc.hasValue(cMsg.rtlWhCatgCd)) {
                NPAL1010Query.getInstance().getLocTp(cMsg, getGlobalCompanyCode());
            } else {
                cMsg.locTpCd_H1.clear();
            }

            S21SsmEZDResult ssmResult = NPAL1010Query.getInstance().getMgrNm(cMsg, getGlobalCompanyCode());
            if (ssmResult.isCodeNotFound()) {
                cMsg.whMgrPsnCd_H1.setErrorInfo(1, NPAM1361E, new String[] {"Owner" });
                return;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm_H1, (String) ssmResult.getResultObject());
        }
    }

    /**
     * doProcess_NPAL1010_GetProfileService
     * @param bizMsg
     * @param shareMsg
     */
    private List<String> getWHDataSecurity(NPAL1010CMsg bizMsg) {

        S21UserProfileService userProfileService = getUserProfileService();
        S21DataSecurityProfile profile = userProfileService.getDataSecurityProfileFor(BUSINESS_ID);
        List<S21DataSecurityAttributeData> securList = profile.getDataSecurityAttributeDataListFor(DATA_SECUR_ATTR);

        if (securList.size() == 0) {

            return null;

        } else {

            List<String> profileList = new ArrayList<String>();

            for (int i = 0; i <= securList.size() - 1; i++) {

                profileList.add(securList.get(i).getValue());

                // Astalisc means no narrowing Search codition
                if (MULTIPLICATION.equals(securList.get(i).getValue())) {

                    bizMsg.xxChkInpValFlg.setValue(ZYPConstant.FLG_OFF_N);
                }
            }

            return profileList;
        }
    }
}
