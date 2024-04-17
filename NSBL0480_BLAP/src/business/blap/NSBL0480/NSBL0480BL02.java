/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0480;

import static business.blap.NSBL0480.constant.NSBL0480Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0480.common.NSBL0480CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Access Permits Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/30   Hitachi         J.Kim           Create          N/A
 * 2016/06/08   Hitachi         J.Kim           Update          QC#9650
 * 2016/07/05   Hitachi         J.Kim           Update          QC#9488
 * 2017/02/01   Hitachi         K.Kitachi       Update          QC#16629
 *</pre>
 */
public class NSBL0480BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0480CMsg cMsg = (NSBL0480CMsg) arg0;
        NSBL0480SMsg sMsg = (NSBL0480SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0480_INIT".equals(screenAplID)) {
                doProcess_NSBL0480_INIT(cMsg, sMsg);
            } else if ("NSBL0480Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSBL0480Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSBL0480Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0480Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSBL0480Scrn00_Delete_Row".equals(screenAplID)) {
                doProcess_NSBL0480Scrn00_Delete_Row(cMsg, sMsg);
            } else if ("NSBL0480Scrn00_Insert_Row".equals(screenAplID)) {
                doProcess_NSBL0480Scrn00_Insert_Row(cMsg, sMsg);
            } else if ("NSBL0480Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0480Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSBL0480Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0480Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSBL0480Scrn00_Search_Access".equals(screenAplID)) {
                doProcess_NSBL0480Scrn00_Search_Access(cMsg, sMsg);
            } else if ("NSBL0480Scrn00_Search_Resource".equals(screenAplID)) {
                doProcess_NSBL0480Scrn00_Search_Resource(cMsg, sMsg);
            } else if ("NSBL0480Scrn00_Switch_View".equals(screenAplID)) {
                doProcess_NSBL0480Scrn00_Switch_View(cMsg, sMsg);
            } else if ("NSBL0480Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSBL0480Scrn00_TBLColumnSort(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0480_INIT(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        doProcessInit(cMsg, sMsg);
    }

    private void doProcess_NSBL0480Scrn00_CMN_Clear(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        doProcessInit(cMsg, sMsg);
    }

    private void doProcess_NSBL0480Scrn00_CMN_Submit(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {
            doSearchResource(cMsg, sMsg);
        } else {
            doSearchAccessPermits(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0480Scrn00_Delete_Row(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        int pageFromNum = 0;
        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {
            pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        } else {
            pageFromNum = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
        }

        NSBL0480CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        if (!NSBL0480CommonLogic.deleteRow(cMsg, sMsg)) {
            return;
        }

        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {
            setResourceInfo(cMsg, sMsg);
        } else {
            setAccessPermitsInfo(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0480Scrn00_Insert_Row(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {
            insertRowResource(cMsg, sMsg);
        } else {
            insertRowAccessPermits(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0480Scrn00_PageNext(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {
            doPageNextResource(cMsg, sMsg);
        } else {
            doPageNextAccessPermits(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0480Scrn00_PagePrev(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {
            doPagePrevResource(cMsg, sMsg);
        } else {
            doPagePrevAccessPermits(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0480Scrn00_Search_Access(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        if (!NSBL0480CommonLogic.isErrorSearchCondition(cMsg)) {
            return;
        }

        doSearchAccessPermits(cMsg, sMsg);
    }

    private void doProcess_NSBL0480Scrn00_Search_Resource(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        if (!NSBL0480CommonLogic.isErrorSearchCondition(cMsg)) {
            return;
        }

        doSearchResource(cMsg, sMsg);
    }

    private void doProcess_NSBL0480Scrn00_Switch_View(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        int pageFromNum = 0;
        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {
            pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        } else {
            pageFromNum = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
        }
        NSBL0480CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        if (!NSBL0480CommonLogic.isErrorSwitchViewCondition(cMsg)) {
            return;
        }

        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {
            int targetIdxNum = sMsg.xxRadioBtn_A.getValueInt();
            ZYPEZDItemValueSetter.setValue(cMsg.techCd_B1, sMsg.A.no(targetIdxNum).svcAccsPmitNum_A);
            ZYPEZDItemValueSetter.setValue(cMsg.techCd_B2, sMsg.A.no(targetIdxNum).svcAccsPmitNum_A);
            ZYPEZDItemValueSetter.setValue(cMsg.svcAccsPmitNum, sMsg.A.no(targetIdxNum).svcAccsPmitNum_A);
            ZYPEZDItemValueSetter.setValue(cMsg.svcAccsPmitDescTxt, sMsg.A.no(targetIdxNum).svcAccsPmitDescTxt_A);
            // START 2017/02/01 K.Kitachi [QC#16629, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.svcPmitLvlTpCd, sMsg.A.no(targetIdxNum).svcPmitLvlTpCd_A);
            ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem30Txt, sMsg.A.no(targetIdxNum).xxScrItem30Txt_A);
            // END 2017/02/01 K.Kitachi [QC#16629, ADD]
            setValue(cMsg.xxScrDply, ACCESSPERMITS_MODE);
            doSearchAccessPermits(cMsg, sMsg);
        } else {
            int targetIdxNum = sMsg.xxRadioBtn_B.getValueInt();
            ZYPEZDItemValueSetter.setValue(cMsg.techCd_A1, sMsg.B.no(targetIdxNum).techCd_B);
            ZYPEZDItemValueSetter.setValue(cMsg.techCd_A2, sMsg.B.no(targetIdxNum).techCd_B);
            ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm_A3, sMsg.B.no(targetIdxNum).fullPsnNm_B);
            setValue(cMsg.xxScrDply, RESOURCE_MODE);
            doSearchResource(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0480Scrn00_TBLColumnSort(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {

            // add start 2016/06/08 CSA Defect#9650
            int pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
            NSBL0480CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);
            // add end 2016/06/08 CSA Defect#9650

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
            setValue(cMsg.xxPageShowFromNum_A, BigDecimal.ONE);
            setValue(cMsg.xxPageShowToNum_A, new BigDecimal(cMsg.A.getValidCount()));
        }

        if ("B".equals(sortTblNm)) {

            // add start 2016/06/08 CSA Defect#9650
            int pageFromNum = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
            NSBL0480CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);
            // add end 2016/06/08 CSA Defect#9650

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.B, sMsg.B.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.B.getValidCount());

            // SMsg -> CMsg
            int i = 0;
            for (; i < sMsg.B.getValidCount(); i++) {
                if (i == cMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }
            cMsg.B.setValidCount(i);

            // Show 1st page
            setValue(cMsg.xxPageShowFromNum_B, BigDecimal.ONE);
            setValue(cMsg.xxPageShowToNum_B, new BigDecimal(cMsg.B.getValidCount()));
        }
    }

    private void doProcessInit(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        NSBL0480CommonLogic.clearMsg(cMsg, sMsg);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());
        setValue(cMsg.xxScrDply, RESOURCE_MODE);

        // START 2017/02/01 K.Kitachi [QC#16629, ADD]
        NSBL0480CommonLogic.createPullDown(cMsg);
        // END 2017/02/01 K.Kitachi [QC#16629, ADD]
        NSBL0480CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());
    }

    private void doSearchResource(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);
        NSBL0480CommonLogic.getSearchResourceData(cMsg, sMsg);
        NSBL0480CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());
    }

    private void doSearchAccessPermits(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);
        NSBL0480CommonLogic.getSearchAccessPermitsData(cMsg, sMsg);
        NSBL0480CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum_B.setValue(sMsg.B.getValidCount());
    }

    private void doPageNextResource(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        if (!NSBL0480CommonLogic.checkEffFromDtResourceType(cMsg)) {
            return;
        }

        NSBL0480CommonLogic.setUpdateFlg(cMsg, sMsg);

        int pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        NSBL0480CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt() + cMsg.A.length() - 1;
        NSBL0480CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doPageNextAccessPermits(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        if (!NSBL0480CommonLogic.checkEffFromDtAccessPermits(cMsg)) {
            return;
        }

        NSBL0480CommonLogic.setUpdateFlg(cMsg, sMsg);

        int pageFromNum = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
        NSBL0480CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum_B.getValueInt() + cMsg.B.length() - 1;
        NSBL0480CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doPagePrevResource(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        if (!NSBL0480CommonLogic.checkEffFromDtResourceType(cMsg)) {
            return;
        }

        NSBL0480CommonLogic.setUpdateFlg(cMsg, sMsg);

        int pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        NSBL0480CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt() - cMsg.A.length() - 1;
        NSBL0480CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doPagePrevAccessPermits(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        if (!NSBL0480CommonLogic.checkEffFromDtAccessPermits(cMsg)) {
            return;
        }

        NSBL0480CommonLogic.setUpdateFlg(cMsg, sMsg);

        int pageFromNum = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
        NSBL0480CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum_B.getValueInt() - cMsg.B.length() - 1;
        NSBL0480CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void setResourceInfo(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        int cMsgLength = cMsg.A.length();
        int sMsgValidCount = sMsg.A.getValidCount();
        int pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int xxPageShowFromNum = cMsg.xxPageShowFromNum_A.getValueInt();

        int beforePageNum = NSBL0480CommonLogic.getPageNum(cMsgLength, xxPageShowFromNum);
        int afterMaxPageNum = NSBL0480CommonLogic.getPageNum(cMsgLength, sMsgValidCount);
        if (afterMaxPageNum < beforePageNum) {
            pageFromNum = cMsgLength * (afterMaxPageNum - 1);
        }

        NSBL0480CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
        cMsg.xxPageShowOfNum_A.setValue(sMsgValidCount);
    }

    private void setAccessPermitsInfo(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        int cMsgLength = cMsg.B.length();
        int sMsgValidCount = sMsg.B.getValidCount();
        int pageFromNum = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
        int xxPageShowFromNum = cMsg.xxPageShowFromNum_B.getValueInt();

        int beforePageNum = NSBL0480CommonLogic.getPageNum(cMsgLength, xxPageShowFromNum);
        int afterMaxPageNum = NSBL0480CommonLogic.getPageNum(cMsgLength, sMsgValidCount);
        if (afterMaxPageNum < beforePageNum) {
            pageFromNum = cMsgLength * (afterMaxPageNum - 1);
        }

        NSBL0480CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
        cMsg.xxPageShowOfNum_B.setValue(sMsgValidCount);
    }

    private void insertRowResource(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        NSBL0480CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int sMsgCount = sMsg.A.getValidCount();
        setValue(sMsg.A.no(sMsgCount).effFromDt_A, ZYPDateUtil.getSalesDate());
        sMsg.A.setValidCount(sMsgCount + 1);

        ZYPTableUtil.clear(cMsg.A);
        int rowIndex = NSBL0480CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), sMsgCount);
        NSBL0480CommonLogic.pagenation(cMsg, sMsg, rowIndex);
        cMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());
    }

    private void insertRowAccessPermits(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
        NSBL0480CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int sMsgCount = sMsg.B.getValidCount();
        setValue(sMsg.B.no(sMsgCount).effFromDt_B, ZYPDateUtil.getSalesDate());
        sMsg.B.setValidCount(sMsgCount + 1);

        ZYPTableUtil.clear(cMsg.B);
        int rowIndex = NSBL0480CommonLogic.convertPageNumToFirstRowIndex(cMsg.B.length(), sMsgCount);
        NSBL0480CommonLogic.pagenation(cMsg, sMsg, rowIndex);
        cMsg.xxPageShowOfNum_B.setValue(sMsg.B.getValidCount());
    }
}
