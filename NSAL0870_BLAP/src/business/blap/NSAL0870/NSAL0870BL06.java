/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0870;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Meter Interface Status Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSAL0870BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSAL0870CMsg cMsg = (NSAL0870CMsg) arg0;
        NSAL0870SMsg sMsg = (NSAL0870SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0870Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(cMsg, sMsg);
            } else if ("NSAL0870Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(cMsg, sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
}
