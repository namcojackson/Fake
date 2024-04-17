/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0910;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL0910.common.NSAL0910CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
/**
 *<pre>
 * CFS Invoice Error Correction
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/10   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0910BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0910CMsg cMsg = (NSAL0910CMsg) arg0;
        NSAL0910SMsg sMsg = (NSAL0910SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        // Common Column Order Text
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0910_INIT".equals(screenAplID)) {
                doProcess_NSAL0910_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NSAL0910Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL0910_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NSAL0910Scrn00_CMN_ColClear".equals(screenAplID)) {
                doProcess_NSAL0910Scrn00_CMN_ColClear(cMsg, sMsg);
            } else if ("NSAL0910Scrn00_CMN_ColSave".equals(screenAplID)) {
                doProcess_NSAL0910Scrn00_CMN_ColSave(cMsg, sMsg);
            } else if ("NSAL0910Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0910Scrn00_Search(cMsg, sMsg, true);
            } else if ("NSAL0910Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0910Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0910Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0910Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL0910Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSAL0910Scrn00_PageJump(cMsg, sMsg);
            } else if ("NSAL0910Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0910Scrn00_Search(cMsg, sMsg, false);
            } else if ("NSAL0910Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL0910Scrn00_TBLColumnSort(cMsg, sMsg);
            }
        } finally {
            // Set Common Column Order Text of SMsg
            sMsg.xxComnColOrdTxt.clear();
            setValue(sMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0910_INIT(NSAL0910CMsg cMsg, NSAL0910SMsg sMsg) {

        NSAL0910CommonLogic.clearMsg(cMsg, sMsg);
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());

        NSAL0910CommonLogic.createPullDown(cMsg);

        NSAL0910CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0910Scrn00_CMN_ColClear(NSAL0910CMsg cMsg, NSAL0910SMsg sMsg) {

    }

    private void doProcess_NSAL0910Scrn00_CMN_ColSave(NSAL0910CMsg cMsg, NSAL0910SMsg sMsg) {

    }

    private void doProcess_NSAL0910Scrn00_PageNext(NSAL0910CMsg cMsg, NSAL0910SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0910CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL0910CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL0910Scrn00_PagePrev(NSAL0910CMsg cMsg, NSAL0910SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0910CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL0910CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL0910Scrn00_PageJump(NSAL0910CMsg cMsg, NSAL0910SMsg sMsg) {
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

    private void doProcess_NSAL0910Scrn00_Search(NSAL0910CMsg cMsg, NSAL0910SMsg sMsg, boolean isSubmit) {

        NSAL0910CommonLogic.getSearchData(cMsg, sMsg, isSubmit);

        NSAL0910CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0910Scrn00_TBLColumnSort(NSAL0910CMsg cMsg, NSAL0910SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();
        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0910CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

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

}
