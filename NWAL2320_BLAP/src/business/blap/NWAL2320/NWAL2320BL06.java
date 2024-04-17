package business.blap.NWAL2320;

import java.util.LinkedHashMap;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NWAL2320.bean.NWAL2320_ImptHeaderBean;
import business.blap.NWAL2320.common.NWAL2320CommonLogic;
import business.blap.NWAL2320.common.NWAL2320CommonLogicForDsCheck;
import business.blap.NWAL2320.common.NWAL2320CommonLogicForUploadLines;
import business.blap.NWAL2320.common.NWAL2320CommonLogicForValidate;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NWAL2320BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2320CMsg bizMsg = (NWAL2320CMsg) cMsg;
            NWAL2320SMsg glblMsg = (NWAL2320SMsg) sMsg;

            if ("NWAL2320Scrn00_Validate".equals(screenAplID)) {
                doProcess_NWAL2320Scrn00_Validate(bizMsg, glblMsg);
                int pagenationFrom = bizMsg.xxPageShowFromNum_CM.getValueInt() - 1;
                NWAL2320CommonLogic.pagenation(bizMsg, glblMsg, pagenationFrom);
            } else if ("NWAL2320Scrn00_UploadLines".equals(screenAplID)) {
                doProcess_NWAL2320Scrn00_UploadLines(bizMsg, glblMsg);
                int pagenationFrom = bizMsg.xxPageShowFromNum_CM.getValueInt() - 1;
                NWAL2320CommonLogic.pagenation(bizMsg, glblMsg, pagenationFrom);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NWAL2320Scrn00_Validate(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {

        //******************************************************************
        // Simple Validate(Already Checked)
        //******************************************************************
        if (!NWAL2320CommonLogicForValidate.checkValidate(bizMsg, glblMsg)) {
            return;
        }

        //******************************************************************
        // Create Import TMsg Of Shell
        //******************************************************************
        LinkedHashMap<String, NWAL2320_ImptHeaderBean> imptBeansMap = NWAL2320CommonLogic.createImptBeans(bizMsg, glblMsg, false);

        //******************************************************************
        // Deriving Import Data
        //******************************************************************
        NWAL2320CommonLogic.derivingData(bizMsg, imptBeansMap);

        //******************************************************************
        // DS check.(Do not deriving.)
        //******************************************************************
        NWAL2320CommonLogicForDsCheck.dsCheck(bizMsg, imptBeansMap);

        //******************************************************************
        // Set Error Info 
        //******************************************************************
        NWAL2320CommonLogicForValidate.setValidateErr(bizMsg, glblMsg, imptBeansMap);

    }

    private void doProcess_NWAL2320Scrn00_UploadLines(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {

        //******************************************************************
        // Create Import TMsg Of Shell
        //******************************************************************
        LinkedHashMap<String, NWAL2320_ImptHeaderBean> imptBeansMap = NWAL2320CommonLogic.createImptBeans(bizMsg, glblMsg, true);

        //******************************************************************
        // Deriving Import Data
        //******************************************************************
        NWAL2320CommonLogic.derivingData(bizMsg, imptBeansMap);

        //******************************************************************
        // Insert Import TMsg
        //******************************************************************
        NWAL2320CommonLogicForUploadLines.insertImportTables(bizMsg, imptBeansMap);

    }
}
