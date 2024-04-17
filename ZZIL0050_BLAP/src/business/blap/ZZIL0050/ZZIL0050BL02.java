package business.blap.ZZIL0050;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.ZZIL0050.common.ZZIL0050CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/15   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0050BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("ZZIL0050_INIT".equals(screenAplID)) {
                doProcess_ZZIL0050_INIT((ZZIL0050CMsg) cMsg, (ZZIL0050SMsg) sMsg);
            } else if ("ZZIL0050Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZIL0050Scrn00_Search((ZZIL0050CMsg) cMsg, (ZZIL0050SMsg) sMsg);
            } else if ("ZZIL0050Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZIL0050Scrn00_PageNext((ZZIL0050CMsg) cMsg, (ZZIL0050SMsg) sMsg);
            } else if ("ZZIL0050Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZIL0050Scrn00_PagePrev((ZZIL0050CMsg) cMsg, (ZZIL0050SMsg) sMsg);
            } else if ("ZZIL0050Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_ZZIL0050Scrn00_CMN_Clear((ZZIL0050CMsg) cMsg, (ZZIL0050SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_ZZIL0050_INIT(ZZIL0050CMsg cMsg, ZZIL0050SMsg sMsg) {

        ZZIL0050CommonLogic.searchList(cMsg, sMsg);

    }

    private void doProcess_ZZIL0050Scrn00_Search(ZZIL0050CMsg cMsg, ZZIL0050SMsg sMsg) {

        ZZIL0050CommonLogic.searchList(cMsg, sMsg);

    }

    private void doProcess_ZZIL0050Scrn00_PageNext(ZZIL0050CMsg cMsg, ZZIL0050SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());

    }

    private void doProcess_ZZIL0050Scrn00_PagePrev(ZZIL0050CMsg cMsg, ZZIL0050SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);

    }

    private void doProcess_ZZIL0050Scrn00_CMN_Clear(ZZIL0050CMsg cMsg, ZZIL0050SMsg sMsg) {

        ZZIL0050CommonLogic.searchList(cMsg, sMsg);
        cMsg.setMessageInfo(null, null);

    }

}
