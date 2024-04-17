/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0870;

import static business.blap.NSAL0870.constant.NSAL0870Constant.BUSINESS_ID;
import static business.blap.NSAL0870.constant.NSAL0870Constant.LIMIT_DOWNLOAD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0870.common.NSAL0870CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Fleet Rollup Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Hitachi         T.Mizuki        Create          N/A
 * 2017/01/23   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL0870BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSAL0870CMsg cMsg = (NSAL0870CMsg) arg0;
        NSAL0870SMsg sMsg = (NSAL0870SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0870_INIT".equals(screenAplID)) {
                doProcess_NSAL0870_Init(cMsg, sMsg);
                ZYPGUITableColumn.getColData((NSAL0870CMsg) cMsg, (NSAL0870SMsg) sMsg);
            // START 2017/01/23 K.Ochiai [QC#16331,MOD]
            } else if ("NSAL0870Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL0870Scrn00_CMN_Clear(cMsg, sMsg);
            // END 2017/01/23 K.Ochiai [QC#16331,MOD]
            } else if ("NSAL0870Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0870_PageNext(cMsg, sMsg);
            } else if ("NSAL0870Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0870_PagePrev(cMsg, sMsg);
            } else if ("NSAL0870Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0870_Search(cMsg, sMsg);
            } else if ("NSAL0870Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL0870_TBLColumnSort(cMsg, sMsg);
            } else if ("NSAL0870Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL0870Scrn00_Download((NSAL0870CMsg) cMsg, (NSAL0870SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0870_Init(NSAL0870CMsg cMsg, NSAL0870SMsg sMsg) {

        cMsg.clear();
        sMsg.clear();

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.mtrReadDt_FR, ZYPDateUtil.getSalesDate());
        setValue(cMsg.mtrReadDt_TO, ZYPDateUtil.getSalesDate());

        NSAL0870CommonLogic.createPullDown(cMsg);
    }

    // START 2017/01/23 K.Ochiai [QC#16331,MOD]
    private void doProcess_NSAL0870Scrn00_CMN_Clear(NSAL0870CMsg cMsg, NSAL0870SMsg sMsg) {

        NSAL0870CommonLogic.clearMsg(cMsg, sMsg);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.mtrReadDt_FR, ZYPDateUtil.getSalesDate());
        setValue(cMsg.mtrReadDt_TO, ZYPDateUtil.getSalesDate());

        NSAL0870CommonLogic.createPullDown(cMsg);
    }
    // END 2017/01/23 K.Ochiai [QC#16331,MOD]

    private void doProcess_NSAL0870_TBLColumnSort(NSAL0870CMsg cMsg, NSAL0870SMsg sMsg) {

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

    private void doProcess_NSAL0870_Search(NSAL0870CMsg cMsg, NSAL0870SMsg sMsg) {

        if (!NSAL0870CommonLogic.hasValueSearchCondition(cMsg)) {
            cMsg.setMessageInfo("NSAM0005E");
            return;
        }

        NSAL0870CommonLogic.getSearchData(cMsg, sMsg);
        NSAL0870CommonLogic.formatDsMtrProcTs(sMsg);
        EZDMsg.copy(sMsg, null, cMsg, null);
        NSAL0870CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0870_PagePrev(NSAL0870CMsg cMsg, NSAL0870SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0870CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL0870CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL0870_PageNext(NSAL0870CMsg cMsg, NSAL0870SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0870CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL0870CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    /**
     * do process (download)
     * @param cMsg NSAL0870CMsg
     * @param sMsg NSAL0870SMsg
     */
    private void doProcess_NSAL0870Scrn00_Download(NSAL0870CMsg cMsg, NSAL0870SMsg sMsg) {

        if (!NSAL0870CommonLogic.hasValueSearchCondition(cMsg)) {
            cMsg.setMessageInfo("NSAM0005E");
            return;
        }
        // create csv file
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");

        NSAL0870Query.getInstance().getDownloadData(cMsg, sMsg, LIMIT_DOWNLOAD + 1);

    }
}
