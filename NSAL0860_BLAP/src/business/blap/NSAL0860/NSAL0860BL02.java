/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0860;

import static business.blap.NSAL0860.constant.NSAL0860Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL0860.common.NSAL0860CommonLogic;
import business.file.NSAL0860F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Register & Deregister Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/06   Hitachi         Y.Osawa         Create          N/A
 * 2016/06/17   Hitachi         O.Okuma         Update          QC#9951
 *</pre>
 */
public class NSAL0860BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0860CMsg cMsg = (NSAL0860CMsg) arg0;
        NSAL0860SMsg sMsg = (NSAL0860SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        // Common Column Order Text
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0860_INIT".equals(screenAplID)) {
                doProcess_NSAL0860_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NSAL0860Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL0860_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NSAL0860Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0860Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0860Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0860Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL0860Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0860Scrn00_Search(cMsg, sMsg);
            } else if ("NSAL0860Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL0860Scrn00_TBLColumnSort(cMsg, sMsg);
            } else if ("NSAL0860Scrn00_TemplateDownload".equals(screenAplID)) {
                doProcess_NSAL0860Scrn00_TemplateDownload(cMsg, sMsg);
            }
        } finally {
            // Set Common Column Order Text of SMsg
            sMsg.xxComnColOrdTxt.clear();
            setValue(sMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0860_INIT(NSAL0860CMsg cMsg, NSAL0860SMsg sMsg) {

        EZDCStringItem tmpXxComnColOrdTxt = cMsg.xxComnColOrdTxt;

        NSAL0860CommonLogic.clearMsg(cMsg, sMsg);

        setValue(cMsg.xxComnColOrdTxt, tmpXxComnColOrdTxt);

        NSAL0860CommonLogic.createPullDown(cMsg);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));

        NSAL0860CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0860Scrn00_PageNext(NSAL0860CMsg cMsg, NSAL0860SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0860CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL0860CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL0860Scrn00_PagePrev(NSAL0860CMsg cMsg, NSAL0860SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0860CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL0860CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL0860Scrn00_Search(NSAL0860CMsg cMsg, NSAL0860SMsg sMsg) {

        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        cMsg.xxPageShowFromNum.setValue(0);
        cMsg.xxPageShowToNum.setValue(0);

        if (NSAL0860CommonLogic.isErrorSearchCondition(cMsg)) {
            NSAL0860CommonLogic.setMessageInfoSearchCondition(cMsg);
            return;
        }

        NSAL0860CommonLogic.getSearchData(cMsg, sMsg);

        NSAL0860CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0860Scrn00_TBLColumnSort(NSAL0860CMsg cMsg, NSAL0860SMsg sMsg) {

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

    private void doProcess_NSAL0860Scrn00_TemplateDownload(NSAL0860CMsg cMsg, NSAL0860SMsg sMsg) {
        // create csv file
        cMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BIZ_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        NSAL0860F00FMsg fMsg = new NSAL0860F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_D.getTempFilePath(), fMsg);
        NSAL0860CommonLogic.writeCsvFileHeader(csvOutFile, fMsg, cMsg);
        csvOutFile.close();
    }
}
