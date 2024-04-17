package business.blap.NPAL1260;

import static business.blap.NPAL1260.constant.NPAL1260Constant.NMAM0014E;
import static business.blap.NPAL1260.constant.NPAL1260Constant.NMAM0182E;
import static business.blap.NPAL1260.constant.NPAL1260Constant.ZZM9000E;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1260.common.NPAL1260CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request Inquiry
 * Function Name : update business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/02/2015   CITS       Yasushi Nomura        Create          N/A
 *</pre>
 */
public class NPAL1260BL06 extends S21BusinessHandler {
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NPAL1260Scrn00_SaveSearchOption".equals(screenAplID)) {
                doProcess_SaveSearchOption((NPAL1260CMsg) cMsg, (NPAL1260SMsg) sMsg);
            } else if ("NPAL1260Scrn00_DeleteSearchOption".equals(screenAplID)) {
                doProcess_DeleteSearchOption((NPAL1260CMsg) cMsg, (NPAL1260SMsg) sMsg);
            } else if ("NPAL1260Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NPAL1260CMsg) cMsg, (NPAL1260SMsg) sMsg);
            } else if ("NPAL1260Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NPAL1260CMsg) cMsg, (NPAL1260SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Save Search option
     * @param cMsg NPAL1260CMsg
     * @param sMsg NPAL1260SMsg
     */
    private void doProcess_SaveSearchOption(NPAL1260CMsg cMsg, NPAL1260SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SE) && !ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            cMsg.srchOptNm_TX.setErrorInfo(1, ZZM9000E, new String[] {"Saved Option Name" });
            return;
        }

        if (NPAL1260CommonLogic.isExistSaveSearchName(cMsg)) {
            // Error Message ;
            // You can not [@] this record Because of [@] already
            // exists.
            cMsg.srchOptNm_TX.setErrorInfo(1, NMAM0182E, //
                    new String[] {"Save", "Saved Option Name" });
            return;
        }

        NPAL1260CommonLogic.callNszc0330forSaveSearchOption(cMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * Delete Search option
     * @param cMsg NPAL1260CMsg
     * @param sMsg NPAL1260SMsg
     */
    private void doProcess_DeleteSearchOption(NPAL1260CMsg cMsg, NPAL1260SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SE)) {
            // Message ; [@] is not selected.
            cMsg.srchOptPk_SE.setErrorInfo(1, NMAM0014E, new String[] {"Saved Search Options" });
            return;
        }

        NPAL1260CommonLogic.callNszc0330forDeleteSearchOption(cMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

}
