/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0280;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL0280.common.NLCL0280CommonLogic;
import business.blap.NLCL0280.constant.NLCL0280Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NLCL0280BL06 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/25   CITS            T.Tokutomi      Create          N/A
 * 05/25/2016   CSAI            Y.Imazu         Update          QC#6864
 *</pre>
 */
public class NLCL0280BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NLCL0280Scrn00_Save_Search".equals(screenAplID)) {
                doProcess_SaveSearch((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);

            } else if ("NLCL0280Scrn00_Delete_Search".equals(screenAplID)) {
                doProcess_DeleteSearch((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);

            } else if ("NLCL0280Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);

            } else if ("NLCL0280Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NLCL0280CMsg) cMsg, (NLCL0280SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_SaveSearch
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_SaveSearch(NLCL0280CMsg cMsg, NLCL0280SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        String usrId = getContextUserInfo().getUserId();

        if (NLCL0280CommonLogic.isExistSaveSearchName(cMsg)) {

            // Error Message : You can not [@] this record Because of [@] already exists.
            cMsg.srchOptNm_H1.setErrorInfo(1, NLCL0280Constant.NLZM2273E, new String[] {"Save", cMsg.srchOptNm_H1.getValue() });
            cMsg.setMessageInfo(NLCL0280Constant.ZZM9037E);
            return;
        }

        NLCL0280CommonLogic.callNszc0330forSaveSearchOption(cMsg, sMsg, usrId, glblCmpyCd);
    }

    /**
     * doProcess_DeleteSearch
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_DeleteSearch(NLCL0280CMsg cMsg, NLCL0280SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        String usrId = getContextUserInfo().getUserId();

        NLCL0280CommonLogic.callNszc0330forDeleteSearchOption(cMsg, sMsg, usrId, glblCmpyCd);

        // Update Save Search pulldown
        NLCL0280CommonLogic.setPulldownSaveSearch(cMsg, glblCmpyCd, usrId);
    }
}
