/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1030;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL1030.common.NSAL1030CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Contract Invoice Detail Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/29   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL1030BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1030CMsg cMsg = (NSAL1030CMsg) arg0;
        NSAL1030SMsg sMsg = (NSAL1030SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        // Common Column Order Text
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1030_INIT".equals(screenAplID)) {
                doProcess_NSAL1030_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NSAL1030Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL1030Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NSAL1030Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1030Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL1030Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1030Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL1030Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL1030Scrn00_TBLColumnSort(cMsg, sMsg);
            } else if ("NSAL1030_NSAL1120".equals(screenAplID)) {
                doProcess_NSAL1030_NSAL1120(cMsg, sMsg);
            }
        } finally {
            // Set Common Column Order Text of SMsg
            sMsg.xxComnColOrdTxt.clear();
            setValue(sMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1030_INIT(NSAL1030CMsg cMsg, NSAL1030SMsg sMsg) {

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());

        NSAL1030CommonLogic.searchInvoiceInfo(cMsg, sMsg);
    }

    private void doProcess_NSAL1030Scrn00_CMN_Reset(NSAL1030CMsg cMsg, NSAL1030SMsg sMsg) {

        NSAL1030CommonLogic.searchInvoiceInfo(cMsg, sMsg);
    }

    private void doProcess_NSAL1030Scrn00_PageNext(NSAL1030CMsg cMsg, NSAL1030SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1030CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL1030CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1030Scrn00_PagePrev(NSAL1030CMsg cMsg, NSAL1030SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1030CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL1030CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1030Scrn00_TBLColumnSort(NSAL1030CMsg cMsg, NSAL1030SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            NSAL1030CommonLogic.setSeqNum(sMsg);

            // SMsg -> CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Show 1st page
            setValue(cMsg.xxPageShowFromNum, BigDecimal.ONE);
            setValue(cMsg.xxPageShowToNum, new BigDecimal(cMsg.A.getValidCount()));
        }
    }

    private void doProcess_NSAL1030_NSAL1120(NSAL1030CMsg cMsg, NSAL1030SMsg sMsg) {

        NSAL1030CommonLogic.searchInvoiceInfo(cMsg, sMsg);
    }

}
