/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0120;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NFAL0120.common.NFAL0120CommonLogic;
import business.blap.NFAL0120.constant.NFAL0120Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * Class name: NFAL0120BL06
 * <dd>The class explanation: Business processing for Component ID :
 * NFAL0120BL02
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0120BL06 extends S21BusinessHandler implements NFAL0120Constant {
    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
    
    /** Singleton instance. */
    private NFAL0120CommonLogic common = new NFAL0120CommonLogic();

    /** Journal Entry Common Module */
    // private NFACommonJrnlEntry parts = new NFACommonJrnlEntry();
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

            if ("NFAL0120Scrn00_CreateReportBtn".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_CreateReportBtn(cMsg, sMsg);
            } else if ("NFAL0120Scrn00_CreateReportSuppressReclassBtn".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_CreateReportSuppressReclassBtn(cMsg, sMsg);
            } else if ("NFAL0120Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NFAL0120CMsg) cMsg, (NFAL0120SMsg) sMsg);
            } else if ("NFAL0120Scrn00_CMN_ColClear".equals(screenAplID)){
                ZYPGUITableColumn.clearColData((NFAL0120CMsg) cMsg, (NFAL0120SMsg) sMsg);
            } else if ("NFAL0120Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_SaveSearch(cMsg, sMsg);
            } else if ("NFAL0120Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_DeleteSearch(cMsg, sMsg);
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
     * Method name: doProcess_NFAL0120Scrn00_CreateReportBtn
     * <dd>The method explanation: Initializing.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0120Scrn00_CreateReportBtn(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_CreateReportBtn======(NFAL0120BL06)==============START", this);

        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;

        // For suppressing reclass
        bizMsg.eventId.setValue(NO);
        setRecordForReport(bizMsg);

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_CreateReportBtn======(NFAL0120BL06)============END", this);
    }

    /**
     * Method name:
     * doProcess_NFAL0120Scrn00_CreateReportSuppressReclassBtn
     * <dd>The method explanation: Initializing.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0120Scrn00_CreateReportSuppressReclassBtn(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_CreateReportSuppressReclassBtn======(NFAL0120BL06)==============START", this);

        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;

        // For suppressing reclass
        bizMsg.eventId.setValue(YES);
        setRecordForReport(bizMsg);

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_CreateReportSuppressReclassBtn======(NFAL0120BL06)============END", this);
    }

    private void setRecordForReport(NFAL0120CMsg bizMsg) {

        if (isLessThanMaxLimit(bizMsg)) {
            common.clearSearchResult(bizMsg);

            long drCount = common.getResultDrForReport(bizMsg);
            long crCount = common.getResultCrForReport(bizMsg, drCount);

            if ((drCount + crCount) == 0) {
                // When no result found,
                // no report shold be created
                bizMsg.setMessageInfo("NFAM0002E", new String[] {"result" });
            }
        } else {
            // NFAM0008E=0,It has gone over the limit of max record
            // count [ @ records ]. Please review your search criteria
            bizMsg.setMessageInfo("NFAM0008E", new String[] {Long.toString(RECORD_MAX_LIMIT) });
        }
    }

    private boolean isLessThanMaxLimit(NFAL0120CMsg bizMsg) {

        long countDr = common.countResultDr(bizMsg);
        if (countDr > RECORD_MAX_LIMIT) {
            return false;
        }

        long countCr = common.countResultCr(bizMsg);
        if ((countDr + countCr) > RECORD_MAX_LIMIT) {
            return false;
        }
        return true;
    }
    
    /**
     * Method name: doProcess_NFAL0120Scrn00_SaveSearch
     * <dd>The method explanation: Save Search
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0120Scrn00_SaveSearch(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;
        NFAL0120SMsg glblMsg = (NFAL0120SMsg) sMsg;
        
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)
                && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, "ZZM9000E"// [@] field is mandatory.
                    , new String[] {converter.convLabel2i18nLabel(NFAL0120Constant.SCRN_ID_00, "Search Option Name") });
            return;
        }
        if (NFAL0120CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, "NLZM2273E" // You can not [@] this record Because of [@] already exists.
                    , new String[] {
                    converter.convLabel2i18nLabel(NFAL0120Constant.SCRN_ID_00, "Save")
                    , converter.convLabel2i18nLabel(NFAL0120Constant.SCRN_ID_00, "Search Option Name") });
            return;
        }

        NFAL0120CommonLogic.callNszc0330forSaveSearch(
                bizMsg, glblMsg, getContextUserInfo().getUserId(), this.getGlobalCompanyCode());
        
    }
    
    /**
     * Method name: doProcess_NFAL0120Scrn00_DeleteSearch
     * <dd>The method explanation: Delete Search
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0120Scrn00_DeleteSearch(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;
        NFAL0120SMsg glblMsg = (NFAL0120SMsg) sMsg;
        
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            bizMsg.srchOptPk_S.setErrorInfo(1, "NLZM2274E" // [@] is not selected.
                    , new String[] {converter.convLabel2i18nLabel(NFAL0120Constant.SCRN_ID_00, "Saved Search Options") });
            return;
        }

        NFAL0120CommonLogic.callNszc0330forDeleteSearch(
                bizMsg, glblMsg, getContextUserInfo().getUserId(), this.getGlobalCompanyCode());
    }
    
}
