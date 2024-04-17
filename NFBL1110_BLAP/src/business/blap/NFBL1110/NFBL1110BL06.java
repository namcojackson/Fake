/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL1110;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import business.blap.NFBL1110.NFBL1110CMsg;
import business.blap.NFBL1110.common.NFBL1110CommonLogic;
import business.blap.NFBL1110.constant.NFBL1110Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * AP Invoice Maintenance Batch Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CUSA            Y.Aikawa        Create          N/A
 * 2016/08/05   Fujitsu         T.Murai         Update          QC#12866
 * 2016/08/05   Hitachi         K.Kojima        Update          QC#12971
 * 2016/08/22   Fujitsu         T.Murai         Update          QC#12830
 * 2016/09/09   Hitachi         K.Kojima        Update          QC#12725
 * 2016/09/21   Fujitsu         W.Honda         Update          Unit Test#201
 * </pre>
 */
public class NFBL1110BL06 extends S21BusinessHandler implements NFBL1110Constant {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();

    /**
     * Method name: doProcess
     * <dd>The method explanation: Call each process by screen id.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            // +++++ [START] : Scrn00
            if ("NFBL1110Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NFBL1110Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NFBL1110Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFBL1110Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NFBL1110Scrn00_CMN_Approve".equals(screenAplID)) {
                doProcess_NFBL1110Scrn00_CMN_Approve(cMsg, sMsg);
            } else if ("NFBL1110Scrn00_CMN_Reject".equals(screenAplID)) {
                doProcess_NFBL1110Scrn00_CMN_Reject(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Scrn01
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NFBL1110Scrn00_CMN_Save
     * <dd>The method explanation: Common [Save] button event.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1110Scrn00_CMN_Save(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_CMN_Save================================START", this);

        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;
        NFBL1110SMsg globalMsg = (NFBL1110SMsg) sMsg;

        // Save Current Invoice Info to SMessage.
        if (NFBL1110CommonLogic.keepCurrentInvInfo(bizMsg, globalMsg, getGlobalCompanyCode())) {
            if (globalMsg.A.getValidCount() > 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.invFlg_NE, ZYPConstant.FLG_OFF_N); // Existing Invoice
                NFBL1110CommonLogic.setCurrentInvIndex(bizMsg);
                // START 2016/09/09 K.Kojima [QC#12725,MOD]
                // if
                // (NFBL1110CommonLogic.createMaintInvBatCommon(bizMsg,
                // globalMsg, AP_DS_WF_STS_CD_00,
                // AP_MAINT_INV_STS_CD_00, getGlobalCompanyCode(),
                // getContextUserInfo().getUserId(),
                // getContextUserInfo().getFullName())) {
                if (NFBL1110CommonLogic.createMaintInvBatCommon(bizMsg, globalMsg, AP_DS_WF_STS_CD_00, AP_MAINT_INV_STS_CD_00, getGlobalCompanyCode())) {
                    // END 2016/09/09 K.Kojima [QC#12725,MOD]
                    refreshScreenInfoAfterUpdate(bizMsg, globalMsg);
                } else {
                    return;
                }
            } else {
                // Delete Invoice.
                if (NFBL1110CommonLogic.deleteMaintInvBatCommon(bizMsg, globalMsg, getGlobalCompanyCode())) {
                    NFBL1110CommonLogic.initializeScreenValue(bizMsg, globalMsg);
                    bizMsg.setMessageInfo(NZZM0002I);
                } else {
                    return;
                }
            }
        } else {
            return;
        }

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_CMN_Save================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1110Scrn00_CMN_Submit
     * <dd>The method explanation: Common [Submit] button event.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1110Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_CMN_Submit================================START", this);

        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;
        NFBL1110SMsg globalMsg = (NFBL1110SMsg) sMsg;

        // Save Current Invoice Info to SMessage.
        if (NFBL1110CommonLogic.keepCurrentInvInfo(bizMsg, globalMsg, getGlobalCompanyCode())) {
            if (globalMsg.A.getValidCount() > 0) {
                // Create Invoice.
                ZYPEZDItemValueSetter.setValue(bizMsg.invFlg_NE, ZYPConstant.FLG_OFF_N); // Existing Invoice
                NFBL1110CommonLogic.setCurrentInvIndex(bizMsg);
                boolean isError = false;
                if (!NFBL1110CommonLogic.checkRunningAmount(bizMsg, globalMsg)) {
                    bizMsg.apCtrlAmt_BA.setErrorInfo(1, NFBM0134E, new String[] {"Amount","Control Amount","Running Total"});
                    isError = true;
                }
                if (!NFBL1110CommonLogic.checkRunningCount(bizMsg, globalMsg)) {
                    // Mod Start 2016/08/05 QC#12866
                    // bizMsg.apCtrlCnt_BA.setErrorInfo(1, NFBM0134E, new String[] {"Count","Control Count","Running Count"});
                    bizMsg.apCtrlCnt_BA.setErrorInfo(1, NFBM0232E);
                    // Mod End 2016/08/05 QC#12866
                    isError = true;
                }
                if (isError) {
                    return;
                }
                // START 2016/09/09 K.Kojima [QC#12725,MOD]
                // if
                // (NFBL1110CommonLogic.createMaintInvBatCommon(bizMsg,
                // globalMsg, AP_DS_WF_STS_CD_00,
                // AP_MAINT_INV_STS_CD_10, getGlobalCompanyCode(),
                // getContextUserInfo().getUserId(),
                // getContextUserInfo().getFullName())) {
                if (NFBL1110CommonLogic.createMaintInvBatCommon(bizMsg, globalMsg, AP_DS_WF_STS_CD_00, AP_MAINT_INV_STS_CD_10, getGlobalCompanyCode())) {
                    // END 2016/09/09 K.Kojima [QC#12725,MOD]
                    refreshScreenInfoAfterUpdate(bizMsg, globalMsg);
                } else {
                    return;
                }
            } else {
                // Delete Invoice.
                if (NFBL1110CommonLogic.deleteMaintInvBatCommon(bizMsg, globalMsg, getGlobalCompanyCode())) {
                    NFBL1110CommonLogic.initializeScreenValue(bizMsg, globalMsg);
                    bizMsg.setMessageInfo(NZZM0002I);
                } else {
                    return;
                }
            }
        } else {
            return;
        }

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_CMN_Submit================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1110Scrn00_CMN_Approve
     * <dd>The method explanation: Common [Approve] button event.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1110Scrn00_CMN_Approve(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_CMN_Approve================================START", this);

        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;
        NFBL1110SMsg globalMsg = (NFBL1110SMsg) sMsg;

        String apDsWfStsCd = AP_DS_WF_STS_CD_00;
        String apMaintInvStsCd = AP_MAINT_INV_STS_CD_12;

        if (NFBL1110CommonLogic.updateCmMaintInvCommon(bizMsg, globalMsg, apDsWfStsCd, apMaintInvStsCd, getGlobalCompanyCode())) {
            refreshScreenInfoAfterUpdate(bizMsg, globalMsg);
        }

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_CMN_Approve================================END", this);
    }

    /**
     * Method name: doProcess_NFBL1110Scrn00_CMN_Reject
     * <dd>The method explanation: Common [Reject] button event.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL1110Scrn00_CMN_Reject(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_CMN_Reject================================START", this);

        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;
        NFBL1110SMsg globalMsg = (NFBL1110SMsg) sMsg;

        String apDsWfStsCd = AP_DS_WF_STS_CD_09;
        String apMaintInvStsCd = AP_MAINT_INV_STS_CD_90;

        if (NFBL1110CommonLogic.updateCmMaintInvCommon(bizMsg, globalMsg, apDsWfStsCd, apMaintInvStsCd, getGlobalCompanyCode())) {
            refreshScreenInfoAfterUpdate(bizMsg, globalMsg);
        }

        EZDDebugOutput.println(5, "doProcess_NFBL1110Scrn00_CMN_Reject================================END", this);
    }

    @SuppressWarnings("unchecked")
    private void refreshScreenInfoAfterUpdate(NFBL1110CMsg cMsg, NFBL1110SMsg sMsg) {

        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;
        NFBL1110SMsg globalMsg = (NFBL1110SMsg) sMsg;

        // START 2016/09/29 W.Honda [Unit Test#201,ADD]
//        NFBL1110CommonLogic.keepAllInvInfoOnSMsg(bizMsg, globalMsg);
        NFBL1110CommonLogic.keepAllInvInfoOnSMsg(bizMsg, globalMsg, getGlobalCompanyCode());
        // END   2016/09/29 W.Honda [Unit Test#201,ADD]
        NFBL1110CommonLogic.setCurrentInvIndex(bizMsg);
        bizMsg.A.setValidCount(0);
        bizMsg.A.clear();
        S21SsmEZDResult ssmResult = NFBL1110CommonLogic.searchRecordByInvInfo(bizMsg.apInvNum_IH.getValue(), bizMsg.apVndCd_HD.getValue());
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            int totRecCnt = NFBL1110CommonLogic.getInvLineTotalRecordCount(bizMsg.apInvNum_IH.getValue(), bizMsg.apVndCd_HD.getValue());
            int intValidCount = 0;
            // Mod Start 2016/08/22 QC#12830
//            if ((totRecCnt / INT_6) > (bizMsg.A.length() / INT_6)) {
//                intValidCount = bizMsg.A.length() / INT_6 * INT_6;
            if (totRecCnt > bizMsg.A.length()) {
                intValidCount = bizMsg.A.length();
            } else {
                intValidCount = totRecCnt;
            }
            bizMsg.A.setValidCount(intValidCount);
            // START 2016/09/29 W.Honda [Unit Test#201,MOD]
//            NFBL1110CommonLogic.setDBInfoToScreenCommon(bizMsg, resultList);
            NFBL1110CommonLogic.setDBInfoToScreenCommon(bizMsg, resultList, getGlobalCompanyCode());
            // START 2016/09/29 W.Honda [Unit Test#201,MOD]
        }
        bizMsg.setMessageInfo(NZZM0002I);
    }

}