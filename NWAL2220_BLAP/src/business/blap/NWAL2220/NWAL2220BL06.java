/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL2220;

import static business.blap.NWAL2220.constant.NWAL2220Constant.*;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2220.common.NWAL2220CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Import  Search & Result
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/16   Hitachi         T.Tsuchida      Create          N/A
 * 2016/04/26   Hitachi         K.Kojima        Update          QC#6283
 * 2016/06/15   SRAA            K.Aratani       Update          QC#9971
 *</pre>
 */
public class NWAL2220BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NWAL2220CMsg cMsg = (NWAL2220CMsg) arg0;
        NWAL2220SMsg sMsg = (NWAL2220SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NWAL2220Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(cMsg, sMsg);
            } else if ("NWAL2220Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(cMsg, sMsg);
                // START 2016/04/26 K.Kojima [QC#6283,ADD]
            } else if ("NWAL2220Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NWAL2220Scrn00_SaveSearch(cMsg, sMsg);
            } else if ("NWAL2220Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NWAL2220Scrn00_DeleteSearch(cMsg, sMsg);
                // END 2016/04/26 K.Kojima [QC#6283,ADD]
            //QC#9971
            } else if ("NWAL2220Scrn00_CusaRtlRpt".equals(screenAplID)) {
                doProcess_NWAL2220Scrn00_CusaRtlRpt(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    // START 2016/04/26 K.Kojima [QC#6283,ADD]
    /**
     * SaveSearch Event
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NWAL2220Scrn00_SaveSearch(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S) && !ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            cMsg.srchOptNm_S.setErrorInfo(1, ZZM9000E, new String[] {"Search Option Name" });
            return;
        }

        if (NWAL2220CommonLogic.isExistSaveSearchName(cMsg)) {
            cMsg.srchOptNm_S.setErrorInfo(1, NWAM0837E, new String[] {"Save", "Search Option Name" });
            return;
        }

        NWAL2220CommonLogic.callNszc0330forSaveSearch(cMsg, sMsg, getContextUserInfo().getUserId());
    }

    /**
     * DeleteSearch Event
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NWAL2220Scrn00_DeleteSearch(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            cMsg.srchOptPk_S.setErrorInfo(1, NWAM0838E, new String[] {"Saved Search Options" });
            return;
        }

        NWAL2220CommonLogic.callNszc0330forDeleteSearch(cMsg, sMsg, getContextUserInfo().getUserId());
    }
    // END 2016/04/26 K.Kojima [QC#6283,ADD]

    //QC#9971
    /**
     * CUSA Retail Print Request Event
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NWAL2220Scrn00_CusaRtlRpt(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {


        if (NWAL2220CommonLogic.isErrorSearchCondition(cMsg)) {
            return;
        }
        if (NWAL2220CommonLogic.isErrorSearchConditionForCusaRetailReport(cMsg)) {
            return;
        }

        if (NWAL2220CommonLogic.getSearchData(cMsg, sMsg)) {
            NWAL2220CommonLogic.createCusaReportPrintRequest(cMsg, getContextUserInfo().getUserId());
        }
    }
}
