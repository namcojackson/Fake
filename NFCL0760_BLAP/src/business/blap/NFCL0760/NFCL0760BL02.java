/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL0760;

import static business.blap.NFCL0760.constant.NFCL0760Constant.CSV_FILE_NAME;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL0760.common.NFCL0760CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         T.Tsuchida      Create          N/A
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 *</pre>
 */
public class NFCL0760BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NFCL0760CMsg cMsg = (NFCL0760CMsg) arg0;
        NFCL0760SMsg sMsg = (NFCL0760SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        // Common Column Order Text
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NFCL0760_INIT".equals(screenAplID)) {
                doProcess_NFCL0760_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NFCL0760Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFCL0760Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NFCL0760Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFCL0760Scrn00_PageJump(cMsg, sMsg);
            } else if ("NFCL0760Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFCL0760Scrn00_PageNext(cMsg, sMsg);
            } else if ("NFCL0760Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFCL0760Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NFCL0760Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFCL0760Scrn00_TBLColumnSort(cMsg, sMsg);
            }
        } finally {
            // Set Common Column Order Text of SMsg
            sMsg.xxComnColOrdTxt.clear();
            setValue(sMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NFCL0760_INIT(NFCL0760CMsg cMsg, NFCL0760SMsg sMsg) {

        if (NFCL0760CommonLogic.isErrorSearchCondition(cMsg)) {
            return;
        }
        NFCL0760CommonLogic.clearMsg(cMsg, sMsg);
        NFCL0760CommonLogic.setInitParams(cMsg, getGlobalCompanyCode());
        NFCL0760CommonLogic.getSearchData(cMsg, sMsg);
        NFCL0760CommonLogic.setData(cMsg, sMsg);
        NFCL0760CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NFCL0760Scrn00_CMN_Download(NFCL0760CMsg cMsg, NFCL0760SMsg sMsg) {
        // START 2017/08/21 T.Tsuchida [QC#19573,MOD]
        //cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME + "_" + cMsg.slsDt.getValue()), ".csv");
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");
        // END 2017/08/21 T.Tsuchida [QC#19573,MOD]
        NFCL0760CommonLogic.writeCsvFile(cMsg, sMsg);
    }

    private void doProcess_NFCL0760Scrn00_PageJump(NFCL0760CMsg cMsg, NFCL0760SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt();
        NFCL0760CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int rowIndex = NFCL0760CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NFCL0760CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NFCL0760Scrn00_PageNext(NFCL0760CMsg cMsg, NFCL0760SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NFCL0760CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NFCL0760CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NFCL0760Scrn00_PagePrev(NFCL0760CMsg cMsg, NFCL0760SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NFCL0760CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NFCL0760CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NFCL0760Scrn00_TBLColumnSort(NFCL0760CMsg cMsg, NFCL0760SMsg sMsg) {

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
