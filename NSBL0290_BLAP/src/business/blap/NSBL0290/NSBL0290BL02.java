/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0290;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0290.common.NSBL0290CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Skill Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/09   Hitachi         J.Kim           Create          N/A
 * 2016/06/02   Hitachi         K.Kasai         Update          QC#9379
 * 2017/01/25   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSBL0290BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0290CMsg cMsg = (NSBL0290CMsg) arg0;
        NSBL0290SMsg sMsg = (NSBL0290SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0290_INIT".equals(screenAplID)) {
                doProcess_NSBL0290_INIT(cMsg, sMsg);
            } else if ("NSBL0290Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSBL0290Scrn00_CMN_Clear(cMsg, sMsg);
            // START 2017/01/25 K.Ochiai [QC#16331,ADD]
            } else if ("NSBL0290Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSBL0290Scrn00_CMN_Reset(cMsg, sMsg);
            // END 2017/01/25 K.Ochiai [QC#16331,ADD]
            } else if ("NSBL0290Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0290Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSBL0290Scrn00_Delete_Row".equals(screenAplID)) {
                doProcess_NSBL0290Scrn00_Delete_Row(cMsg, sMsg);
            } else if ("NSBL0290Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0290Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSBL0290Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0290Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSBL0290Scrn00_Search".equals(screenAplID)) {
                doProcess_NSBL0290Scrn00_Search(cMsg, sMsg);
            } else if ("NSBL0290Scrn00_Insert_Row".equals(screenAplID)) {
                doProcess_NSBL0290Scrn00_Insert_Row(cMsg, sMsg);
            } else if ("NSBL0290Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSBL0290Scrn00_TBLColumnSort(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0290_INIT(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        doProcessInit(cMsg, sMsg);
    }

    private void doProcess_NSBL0290Scrn00_CMN_Clear(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        doProcessInit(cMsg, sMsg);
    }

    // START 2017/01/25 K.Ochiai [QC#16331,ADD]
    private void doProcess_NSBL0290Scrn00_CMN_Reset(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        doProcessSearch(cMsg, sMsg);
    }
    // END 2017/01/25 K.Ochiai [QC#16331,ADD]

    private void doProcess_NSBL0290Scrn00_CMN_Submit(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        doProcessSearch(cMsg, sMsg);
    }

    private void doProcess_NSBL0290Scrn00_Delete_Row(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0290CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        if (!NSBL0290CommonLogic.checkSelect(cMsg, sMsg)) {
            return;
        }

        int beforePageNum = NSBL0290CommonLogic.getPageNum(cMsg.A.length(), cMsg.xxPageShowFromNum.getValueInt());
        int afterMaxPageNum = NSBL0290CommonLogic.getPageNum(cMsg.A.length(), sMsg.A.getValidCount());
        if (afterMaxPageNum < beforePageNum) {
            pageFromNum = cMsg.A.length() * (afterMaxPageNum - 1);
        }
        NSBL0290CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSBL0290Scrn00_Insert_Row(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0290CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int sMsgCount = sMsg.A.getValidCount();
        sMsg.A.setValidCount(sMsgCount + 1);
        setValue(sMsg.A.no(sMsgCount).effFromDt, ZYPDateUtil.getSalesDate());

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        int rowIndex = NSBL0290CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), sMsgCount);
        NSBL0290CommonLogic.pagenation(cMsg, sMsg, rowIndex);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSBL0290Scrn00_PageNext(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        if (!NSBL0290CommonLogic.checkEffFromDt(cMsg)) {
            return;
        }

        NSBL0290CommonLogic.setUpdateFlg(cMsg, sMsg);

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0290CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSBL0290CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0290Scrn00_PagePrev(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        if (!NSBL0290CommonLogic.checkEffFromDt(cMsg)) {
            return;
        }

        NSBL0290CommonLogic.setUpdateFlg(cMsg, sMsg);

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0290CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSBL0290CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0290Scrn00_Search(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        doProcessSearch(cMsg, sMsg);
    }

    private void doProcess_NSBL0290Scrn00_TBLColumnSort(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();
        //add start 2016/06/02 CSA Defect#9379
        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0290CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);
        //add end 2016/06/02 CSA Defect#9379

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

    private void doProcessInit(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        NSBL0290CommonLogic.clearMsg(cMsg, sMsg);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        NSBL0290CommonLogic.createPullDown(cMsg, sMsg);

        NSBL0290CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcessSearch(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        NSBL0290CommonLogic.getSearchData(cMsg, sMsg);

        NSBL0290CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }
}
