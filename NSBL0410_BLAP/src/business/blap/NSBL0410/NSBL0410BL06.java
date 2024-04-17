/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0410;

import static business.blap.NSBL0410.constant.NSBL0410Constant.*;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0410.common.NSBL0410CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Mods Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Hitachi         M.Gotou         Create          N/A
 * 2018/07/05   Fujitsu         T.Ogura         Update          QC#27067
 *</pre>
 */
public class NSBL0410BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0410CMsg cMsg = (NSBL0410CMsg) arg0;
        NSBL0410SMsg sMsg = (NSBL0410SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0410Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0410Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSBL0410Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NSBL0410CMsg) cMsg, (NSBL0410SMsg) sMsg);
            } else if ("NSBL0410Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NSBL0410CMsg) cMsg, (NSBL0410SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0410Scrn00_CMN_Submit(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg) {

        int rowIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0410CommonLogic.setPagenation(cMsg, sMsg, rowIndex);
        // START 2018/07/05 T.Ogura [QC#27067,ADD]
        cMsg.setCommitSMsg(true);
        // END   2018/07/05 T.Ogura [QC#27067,ADD]

        NSBL0410CommonLogic.updateMod(cMsg, sMsg);

        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NSBM0005I);
            return;
        }
    }

}
