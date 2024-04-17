/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1130;

import static business.blap.NPAL1130.constant.NPAL1130Constant.NPAL1130_CMN_COL_CLEAR;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPAL1130_CMN_COL_SAVE;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Business ID : NPAL1130 Replenishment Plan Inquiry(Detail)
 * Function Name : 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/04/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */

public class NPAL1130BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (NPAL1130_CMN_COL_CLEAR.equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NPAL1130CMsg) cMsg, (NPAL1130SMsg) sMsg);
            } else if (NPAL1130_CMN_COL_SAVE.equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NPAL1130CMsg) cMsg, (NPAL1130SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

}
