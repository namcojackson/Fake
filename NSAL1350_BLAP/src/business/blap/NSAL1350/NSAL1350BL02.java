/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1350;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1350.common.NSAL1350CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NSAL1350BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/26   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSAL1350BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NSAL1350CMsg bizMsg = (NSAL1350CMsg) cMsg;

            if ("NSAL1350_INIT".equals(screenAplID)) {
                doProcess_NSAL1350_INIT(bizMsg);

            } else if ("NSAL1350Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL1350Scrn00_Search(bizMsg);

            } else if ("NSAL1350_NWAL1130".equals(screenAplID)) {
                doProcess_NSAL1350_NWAL1130(bizMsg);

            } else if ("NSAL1350Scrn00_CMN_Close".equals(screenAplID)) {
                doProcess_NSAL1350Scrn00_CMN_Close(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1350_NWAL1130(NSAL1350CMsg bizMsg) {
        // no operation.

    }

    private void doProcess_NSAL1350Scrn00_CMN_Close(NSAL1350CMsg bizMsg) {
        bizMsg.O.clear();
        List<Integer> checkedList = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        int ixO = 0;
        for (int ixA : checkedList) {
            NSAL1350_OCMsg oBizMsg = bizMsg.O.no(ixO++);
            NSAL1350_ACMsg aBizMsg = bizMsg.A.no(ixA);

            EZDMsg.copy(aBizMsg, "A", oBizMsg, "O");
        }
        bizMsg.O.setValidCount(ixO);
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NSAL1350_INIT(NSAL1350CMsg bizMsg) {
        doProcess_NSAL1350Scrn00_Search(bizMsg);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NSAL1350Scrn00_Search(NSAL1350CMsg bizMsg) {
        NSAL1350CommonLogic.search(bizMsg);
        NSAL1350CommonLogic.createPulldownFromSearchRslt(bizMsg);
    }

}
