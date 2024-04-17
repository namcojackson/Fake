/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0080;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import business.blap.NFAL0080.common.NFAL0080CommonLogic;
import business.blap.NFAL0080.constant.NFAL0080Constant;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Class name: NFAL0080BL02
 * <dd>The class explanation: Business processing for Component ID :
 * NFAL0080BL02
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0080BL02 extends S21BusinessHandler implements NFAL0080Constant {

    /** Singleton instance. */
    private NFAL0080CommonLogic common = new NFAL0080CommonLogic();

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

            if ("NFAL0080_INIT".equals(screenAplID)) {
                doProcess_NFAL0080_INIT(cMsg, sMsg);
            } else if ("NFAL0080Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NFAL0080_RESET(cMsg, sMsg);
            } else if ("NFAL0080Scrn00_OnChange_COA_SEG_LKUP_TP_CD_TP".equals(screenAplID)) {
                doProcess_NFAL0080Scrn00_OnChange_COA_SEG_LKUP_TP_CD_TP(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NFAL0080_INIT
     * <dd>The method explanation: Initializing.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0080_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0080_INIT================================START", this);

        NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;

        // FIRST_PROD_CTRL_CD pull down(Header)
        NFAL0080CommonLogic.getFirstProdCtrlCdForHeader(bizMsg, sMsg);

        // Get Result
        common.getWholeRecords(bizMsg);

        EZDDebugOutput.println(5, "doProcess_NFAL0080_INIT================================END", this);
    }

    /**
     * Method name: doProcess_NFAL0080_RESET
     * <dd>The method explanation: Reset values.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0080_RESET(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0080_RESET================================START", this);

        NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;

        // Clear Header
        NFAL0080CommonLogic.clearHeader(bizMsg);

        // Get Result
        common.getWholeRecords(bizMsg);

        EZDDebugOutput.println(5, "doProcess_NFAL0080_RESET================================END", this);
    }

    /**
     * Method name:
     * doProcess_NFAL0080Scrn00_OnChange_COA_SEG_LKUP_TP_CD_TP
     * <dd>The method explanation: Reset values.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0080Scrn00_OnChange_COA_SEG_LKUP_TP_CD_TP(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0080Scrn00_OnChange_COA_SEG_LKUP_TP_CD_TP================================START", this);

        NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;

        if (NFAL0080CommonLogic.checkRecordExistence(bizMsg.eligCoaSegPtrnCd.getValue(), bizMsg.coaSegLkupTpCd_3.getValue())) {
            bizMsg.eligCoaSegPtrnCd.setErrorInfo(1, NFAM0070E, new String[] {RECORD, ELIG_COA_SEG_PTRN_TABLE });
            bizMsg.coaSegLkupTpCd_3.setErrorInfo(1, NFAM0070E, new String[] {RECORD, ELIG_COA_SEG_PTRN_TABLE });
            return;
        }

        EZDDebugOutput.println(5, "doProcess_NFAL0080Scrn00_OnChange_COA_SEG_LKUP_TP_CD_TP================================END", this);
    }

}
