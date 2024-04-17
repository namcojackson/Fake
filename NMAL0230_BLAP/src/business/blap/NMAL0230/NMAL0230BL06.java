/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0230;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL0230BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/27   Fujitsu         C.Tanaka        Create          CSA
 *</pre>
 */
public class NMAL0230BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL0230CMsg bizMsg = (NMAL0230CMsg) cMsg;
            NMAL0230SMsg glblMsg = (NMAL0230SMsg) sMsg;

            if ("NMAL0230Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);
            } else if ("NMAL0230Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

}
