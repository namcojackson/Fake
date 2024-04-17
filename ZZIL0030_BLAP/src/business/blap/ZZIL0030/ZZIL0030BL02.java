package business.blap.ZZIL0030;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.ZZIL0030.common.ZZIL0030CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/15   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0030BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("ZZIL0030Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZIL0030Scrn00_Search((ZZIL0030CMsg) cMsg, (ZZIL0030SMsg) sMsg);
            } else if ("ZZIL0030Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZIL0030Scrn00_PageNext((ZZIL0030CMsg) cMsg, (ZZIL0030SMsg) sMsg);
            } else if ("ZZIL0030Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZIL0030Scrn00_PagePrev((ZZIL0030CMsg) cMsg, (ZZIL0030SMsg) sMsg);
            } else if ("ZZIL0030Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_ZZIL0030Scrn00_CMN_Clear((ZZIL0030CMsg) cMsg, (ZZIL0030SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_ZZIL0030Scrn00_Search(ZZIL0030CMsg cMsg, ZZIL0030SMsg sMsg) {

        ZZIL0030CommonLogic.searchMaster(cMsg, sMsg);

    }

    /**
     * Method: Page Next
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Share Message
     */
    private void doProcess_ZZIL0030Scrn00_PageNext(ZZIL0030CMsg cMsg, ZZIL0030SMsg sMsg) {

        ZZIL0030CMsg bizMsg = cMsg;
        ZZIL0030SMsg shareMsg = sMsg;

        // Store checkbox data from CMsg to SMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        int pagenationPreFrom = pagenationFrom - bizMsg.A.length();
        int j = pagenationPreFrom;
        for (; j < i; j++) {
            if (j < shareMsg.A.getValidCount()) {
                shareMsg.A.no(j).xxChkBox_A.setValue(bizMsg.A.no(j - pagenationPreFrom).xxChkBox_A.getValue());
            } else {
                break;
            }
        }

        // clear bizMsg
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        // copy data from SMsg to CMsg
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < shareMsg.A.getValidCount()) {
                EZDMsg.copy(shareMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());

    }

    /**
     * Method: Page Prev
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_ZZIL0030Scrn00_PagePrev(ZZIL0030CMsg cMsg, ZZIL0030SMsg sMsg) {

        ZZIL0030CMsg bizMsg = cMsg;
        ZZIL0030SMsg shareMsg = sMsg;

        // Store checkbox data from CMsg to SMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        int pagenationPreFrom = pagenationFrom + bizMsg.A.length();
        int j = pagenationPreFrom;
        int pagenationPreTo = pagenationPreFrom + bizMsg.A.length();
        int k = pagenationPreTo;

        for (; j < k; j++) {
            shareMsg.A.no(j).xxChkBox_A.setValue(bizMsg.A.no(j - pagenationPreFrom).xxChkBox_A.getValue());
        }

        // clear bizMsg
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        // copy data from SMsg to CMsg
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < shareMsg.A.getValidCount()) {
                EZDMsg.copy(shareMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);
    }

    /**
     * Method: Clear
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Shared Message
     */
    private void doProcess_ZZIL0030Scrn00_CMN_Clear(ZZIL0030CMsg bizMsg, ZZIL0030SMsg shareMsg) {

        bizMsg.clear();
        bizMsg.A.setValidCount(0);
        shareMsg.clear();
        shareMsg.A.setValidCount(0);

        return;
    }

}
