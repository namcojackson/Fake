/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.blap.ZZIL0120;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.ZZIL0120.common.ZZIL0120CommonLogic;
import business.blap.ZZIL0120.constant.ZZIL0120Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZIL0120BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZIL0120_INIT".equals(screenAplID)) {
                doProcess_ZZIL0120_INIT((ZZIL0120CMsg) cMsg, (ZZIL0120SMsg) sMsg);

            } else if ("ZZIL0120Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZIL0120Scrn00_Search((ZZIL0120CMsg) cMsg, (ZZIL0120SMsg) sMsg);

            } else if ("ZZIL0120Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZIL0120Scrn00_PageNext((ZZIL0120CMsg) cMsg, (ZZIL0120SMsg) sMsg);

            } else if ("ZZIL0120Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZIL0120Scrn00_PagePrev((ZZIL0120CMsg) cMsg, (ZZIL0120SMsg) sMsg);

            } else if ("ZZIL0120Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZIL0120Scrn00_TBLColumnSort((ZZIL0120CMsg) cMsg, (ZZIL0120SMsg) sMsg);

            } else if ("ZZIL0120Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_ZZIL0120Scrn00_CMN_Clear((ZZIL0120CMsg) cMsg, (ZZIL0120SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * initialization processing
     * @param cMsg ZZIL0120CMsg
     * @param sMsg ZZIL0120SMsg
     */
    private void doProcess_ZZIL0120_INIT(ZZIL0120CMsg cMsg, ZZIL0120SMsg sMsg) {
        // ZZIL0120CommonLogic.setConfigListBox(cMsg, sMsg,
        // getGlobalCompanyCode());
    }

    /**
     * search processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0120Scrn00_Search(ZZIL0120CMsg cMsg, ZZIL0120SMsg sMsg) {
        ZZIL0120CommonLogic.getItrlIntfcIds(cMsg, sMsg, getGlobalCompanyCode());
    }

    /**
     * next page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0120Scrn00_PageNext(ZZIL0120CMsg cMsg, ZZIL0120SMsg sMsg) {
        ZZIL0120CommonLogic.pageMove(cMsg.A, sMsg.A, cMsg.xxPageShowFromNum, cMsg.xxPageShowToNum);
    }

    /**
     * previous page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0120Scrn00_PagePrev(ZZIL0120CMsg cMsg, ZZIL0120SMsg sMsg) {
        ZZIL0120CommonLogic.pageMove(cMsg.A, sMsg.A, cMsg.xxPageShowFromNum, cMsg.xxPageShowToNum);
    }

    /**
     * table column sort Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0120Scrn00_TBLColumnSort(ZZIL0120CMsg cMsg, ZZIL0120SMsg sMsg) {
        // Table:A
        String tblName = "A";
        String[] sortItems = ZZIL0120Constant.sortItems_A;
        String[][] baseContents = sMsg.A.no(0).getBaseContents();

        ZZIL0120CommonLogic.tblColumnSort(cMsg.A, sMsg.A, cMsg.xxPageShowFromNum, cMsg.xxPageShowToNum, cMsg.xxSortTblNm.getValue(), cMsg.xxSortItemNm.getValue(), cMsg.xxSortOrdByTxt.getValue(), baseContents, tblName, sortItems);
    }

    /**
     * clear Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0120Scrn00_CMN_Clear(ZZIL0120CMsg cMsg, ZZIL0120SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        doProcess_ZZIL0120_INIT(cMsg, sMsg);
    }

}
