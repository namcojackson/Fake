/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */

package business.blap.NFDL0110;


import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFDL0110.constant.NFDL0110Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Customer Care
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/14/2016   CSAI            K.Lee           Create          Initial
 *</pre>
 */
public class NFDL0110BL06 extends S21BusinessHandler implements NFDL0110Constant {

    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            NFDL0110CMsg bizMsg = (NFDL0110CMsg) cMsg;
            NFDL0110SMsg globalMsg = (NFDL0110SMsg) sMsg;

            if ("NFDL0110Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, globalMsg);
            } else if ("NFDL0110Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, globalMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID :" + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
}
