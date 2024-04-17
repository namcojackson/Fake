/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1910;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1910.common.NWAL1910CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1910BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/12   Fujitsu         M.Ishii            Create          N/A
 *</pre>
 */
public class NWAL1910BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1910CMsg bizMsg = (NWAL1910CMsg) cMsg;
            NWAL1910SMsg glblMsg = (NWAL1910SMsg) sMsg;

            if ("NWAL1910_INIT".equals(screenAplID)) {
                doProcess_NWAL1910_INIT(bizMsg, glblMsg);
            } else if ("NWAL1910Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWAL1910Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NWAL1910Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWAL1910Scrn00_PagePrev(bizMsg, glblMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    /**
     * INIT
     * @param NWAL1910CMsg bizMsg 
     * @param NWAL1910SMsg glblMsg
     */
    private void doProcess_NWAL1910_INIT(NWAL1910CMsg bizMsg, NWAL1910SMsg glblMsg) {
        NWAL1910CommonLogic.priceHistorySearch(bizMsg, glblMsg, getGlobalCompanyCode());
    }
    /**
     * PageNext
     * @param NWAL1910CMsg bizMsg
     * @param NWAL1910SMsg glblMsg
     */
    private void doProcess_NWAL1910Scrn00_PageNext(NWAL1910CMsg bizMsg, NWAL1910SMsg glblMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < glblMsg.A.getValidCount()) {
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set page
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());
    }

    /**
     * PagePrev
     * @param NWAL1910CMsg bizMsg
     * @param NWAL1910SMsg glblMsg
     */
    private void doProcess_NWAL1910Scrn00_PagePrev(NWAL1910CMsg bizMsg, NWAL1910SMsg glblMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < glblMsg.A.getValidCount()) {
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set page
        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);
    }
}


