/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1240;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL1240.common.NSAL1240CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Config# Search Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Hitachi         A.Kohinata      Create          N/A
 * 2016/08/22   Hitachi         T.Tomita        Update          QC#9215
 *</pre>
 */
public class NSAL1240BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1240CMsg cMsg = (NSAL1240CMsg) arg0;
        NSAL1240SMsg sMsg = (NSAL1240SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        // Common Column Order Text
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1240_INIT".equals(screenAplID)) {
                doProcess_NSAL1240_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NSAL1240Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL1240Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSAL1240Scrn00_CMN_Close".equals(screenAplID)) {
                doProcess_NSAL1240Scrn00_CMN_Close(cMsg, sMsg);
            } else if ("NSAL1240Scrn00_Collapse".equals(screenAplID)) {
                doProcess_NSAL1240Scrn00_Collapse(cMsg, sMsg);
            } else if ("NSAL1240Scrn00_Expand".equals(screenAplID)) {
                doProcess_NSAL1240Scrn00_Expand(cMsg, sMsg);
            } else if ("NSAL1240Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1240Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL1240Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1240Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL1240Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL1240Scrn00_Search(cMsg, sMsg);
            } else if ("NSAL1240Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL1240Scrn00_TBLColumnSort(cMsg, sMsg);
            }
        } finally {
            // Set Common Column Order Text of SMsg
            sMsg.xxComnColOrdTxt.clear();
            setValue(sMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1240_INIT(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg) {

        NSAL1240CommonLogic.clearMsg(cMsg, sMsg);
        NSAL1240CommonLogic.clearSearchResult(cMsg, sMsg);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());
        NSAL1240CommonLogic.createPullDown(cMsg);
        NSAL1240CommonLogic.setSearchCondition(cMsg);

        if (!NSAL1240CommonLogic.hasValueSearchCondition(cMsg)) {
            return;
        }

        NSAL1240CommonLogic.getSearchData(cMsg, sMsg);
        NSAL1240CommonLogic.createDispInfo(cMsg, sMsg);

        NSAL1240CommonLogic.pagenation(cMsg, sMsg, 0);
        // START 2016/08/22 T.Tomita [QC#9215, ADD]
        cMsg.setCommitSMsg(true);
        // END 2016/08/22 T.Tomita [QC#9215, ADD]
    }

    private void doProcess_NSAL1240Scrn00_CMN_Clear(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg) {

        NSAL1240CommonLogic.clearMsg(cMsg, sMsg);
        NSAL1240CommonLogic.clearSearchResult(cMsg, sMsg);
    }

    private void doProcess_NSAL1240Scrn00_CMN_Close(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1240CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        NSAL1240CommonLogic.setOutputParam(cMsg, sMsg);
    }

    private void doProcess_NSAL1240Scrn00_Collapse(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1240CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        NSAL1240CommonLogic.changeDplyCntrl(cMsg, sMsg, ZYPConstant.FLG_OFF_N);
        NSAL1240CommonLogic.createDispInfo(cMsg, sMsg);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1240CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1240Scrn00_Expand(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1240CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        NSAL1240CommonLogic.changeDplyCntrl(cMsg, sMsg, ZYPConstant.FLG_ON_Y);
        NSAL1240CommonLogic.createDispInfo(cMsg, sMsg);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1240CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1240Scrn00_PageNext(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1240CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL1240CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1240Scrn00_PagePrev(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1240CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL1240CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1240Scrn00_Search(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg) {

        if (!NSAL1240CommonLogic.hasValueSearchCondition(cMsg)) {
            NSAL1240CommonLogic.setMessageInfoSearchCondition(cMsg);
            return;
        }

        NSAL1240CommonLogic.clearSearchResult(cMsg, sMsg);
        NSAL1240CommonLogic.getSearchData(cMsg, sMsg);
        NSAL1240CommonLogic.createDispInfo(cMsg, sMsg);

        NSAL1240CommonLogic.pagenation(cMsg, sMsg, 0);
    }

    private void doProcess_NSAL1240Scrn00_TBLColumnSort(NSAL1240CMsg cMsg, NSAL1240SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1240CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

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
