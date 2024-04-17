/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0400;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0400.common.NSBL0400CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
/**
 *<pre>
 * Mods Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Hitachi         M.Gotou         Create          N/A
 *</pre>
 */
public class NSBL0400BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0400CMsg cMsg = (NSBL0400CMsg) arg0;
        NSBL0400SMsg sMsg = (NSBL0400SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0400_INIT".equals(screenAplID)) {
                doProcess_NSBL0400_INIT(cMsg, sMsg);
            } else if ("NSBL0400Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSBL0400Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSBL0400Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0400Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSBL0400Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0400Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSBL0400Scrn00_Search".equals(screenAplID)) {
                doProcess_NSBL0400Scrn00_Search(cMsg, sMsg);
            } else if ("NSBL0400Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSBL0400Scrn00_TBLColumnSort(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0400_INIT(NSBL0400CMsg cMsg, NSBL0400SMsg sMsg) {

        NSBL0400CommonLogic.clearMsg(cMsg, sMsg);
        NSBL0400CommonLogic.createPullDown(cMsg);
        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

    }

    private void doProcess_NSBL0400Scrn00_CMN_Clear(NSBL0400CMsg cMsg, NSBL0400SMsg sMsg) {

        NSBL0400CommonLogic.clearMsg(cMsg, sMsg);
        NSBL0400CommonLogic.createPullDown(cMsg);
        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
    }

    private void doProcess_NSBL0400Scrn00_PageNext(NSBL0400CMsg cMsg, NSBL0400SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0400CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSBL0400CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0400Scrn00_PagePrev(NSBL0400CMsg cMsg, NSBL0400SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0400CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSBL0400CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0400Scrn00_Search(NSBL0400CMsg cMsg, NSBL0400SMsg sMsg) {

        NSBL0400CommonLogic.getSearchData(cMsg, sMsg);

        NSBL0400CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum);
        NSBL0400CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        NSBL0400CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSBL0400Scrn00_TBLColumnSort(NSBL0400CMsg cMsg, NSBL0400SMsg sMsg) {

        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

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
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
    }

}
