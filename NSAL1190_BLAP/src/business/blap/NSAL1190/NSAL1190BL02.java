/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1190;

import static business.blap.NSAL1190.constant.NSAL1190Constant.NSAM0015E;
import static business.blap.NSAL1190.constant.NSAL1190Constant.NSAM0112E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1190.common.NSAL1190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_IDX;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
/**
 *<pre>
 * Counters Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Hitachi         O.Okuma         Create          N/A
 * 2017/01/20   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/08/04   Hitachi         T.Kanasaka      Update          QC#18193
 *</pre>
 */
public class NSAL1190BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1190CMsg cMsg = (NSAL1190CMsg) arg0;
        NSAL1190SMsg sMsg = (NSAL1190SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        // Common Column Order Text
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1190_INIT".equals(screenAplID)) {
                doProcess_NSAL1190_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NSAL1190Scrn00_AddLine".equals(screenAplID)) {
                doProcess_NSAL1190Scrn00_AddLine(cMsg, sMsg);
            } else if ("NSAL1190Scrn00_CMN_ColClear".equals(screenAplID)) {
                doProcess_NSAL1190Scrn00_CMN_ColClear(cMsg, sMsg);
            } else if ("NSAL1190Scrn00_CMN_ColSave".equals(screenAplID)) {
                doProcess_NSAL1190Scrn00_CMN_ColSave(cMsg, sMsg);
            // START 2017/01/20 K.Ochiai [QC#16331,MOD]
            } else if ("NSAL1190Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL1190Scrn00_CMN_Clear(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            // END 2017/01/20 K.Ochiai [QC#16331,MOD]
            } else if ("NSAL1190Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1190Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSAL1190Scrn00_DelLine".equals(screenAplID)) {
                doProcess_NSAL1190Scrn00_DelLine(cMsg, sMsg);
            } else if ("NSAL1190Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSAL1190Scrn00_PageJump(cMsg, sMsg);
            } else if ("NSAL1190Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1190Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL1190Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1190Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL1190Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL1190Scrn00_Search(cMsg, sMsg);
            } else if ("NSAL1190Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL1190Scrn00_TBLColumnSort(cMsg, sMsg);
            }
        } finally {
            // Set Common Column Order Text of SMsg
            sMsg.xxComnColOrdTxt.clear();
            setValue(sMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1190_INIT(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {

        NSAL1190CommonLogic.clearMsg(cMsg, sMsg);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));

        NSAL1190CommonLogic.createPullDown(cMsg);
        NSAL1190CommonLogic.pagenation(cMsg, sMsg, 0);
    }

    private void doProcess_NSAL1190Scrn00_AddLine(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {

        int rowIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;

        if (rowIndex < 0) {
            rowIndex = 0;
        }
        NSAL1190CommonLogic.setPagenation(cMsg, sMsg, rowIndex);

        addLine(cMsg, sMsg);
    }

    private void doProcess_NSAL1190Scrn00_CMN_ColClear(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {

    }

    private void doProcess_NSAL1190Scrn00_CMN_ColSave(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {

    }

    // START 2017/01/20 K.Ochiai [QC#16331,MOD]
    private void doProcess_NSAL1190Scrn00_CMN_Clear(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {

        doProcess_NSAL1190_INIT(cMsg, sMsg);
    }
    // END 2017/01/20 K.Ochiai [QC#16331,MOD]

    private void doProcess_NSAL1190Scrn00_CMN_Submit(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {

        NSAL1190CommonLogic.getSearchData(cMsg, sMsg, true);
        NSAL1190CommonLogic.pagenation(cMsg, sMsg, 0);
    }

    private void doProcess_NSAL1190Scrn00_DelLine(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {

        int rowIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1190CommonLogic.setPagenation(cMsg, sMsg, rowIndex);

        deleteLine(cMsg, sMsg);
    }

    private void doProcess_NSAL1190Scrn00_PageJump(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {

        int rowIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1190CommonLogic.setPagenation(cMsg, sMsg, rowIndex);

        int pagenationFrom = NSAL1190CommonLogic.convertPageNumToFirstRowIndex(cMsg);
        NSAL1190CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1190Scrn00_PageNext(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {

        int rowIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1190CommonLogic.setPagenation(cMsg, sMsg, rowIndex);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL1190CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1190Scrn00_PagePrev(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {

        int rowIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1190CommonLogic.setPagenation(cMsg, sMsg, rowIndex);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL1190CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1190Scrn00_Search(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {
        cMsg.setCommitSMsg(true);
        NSAL1190CommonLogic.getSearchData(cMsg, sMsg, false);
        NSAL1190CommonLogic.pagenation(cMsg, sMsg, 0);

    }

    private void doProcess_NSAL1190Scrn00_TBLColumnSort(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {

        int rowIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1190CommonLogic.setPagenation(cMsg, sMsg, rowIndex);

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


    private void addLine(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {

        if (sMsg.A.getValidCount() >= sMsg.A.length()) {
            cMsg.setMessageInfo(NSAM0112E);
            return;
        }

        int newLineIdx = sMsg.A.getValidCount();
        NSAL1190_ASMsg asMsg = sMsg.A.no(newLineIdx);
        setValue(asMsg.xxRowNum_A, new BigDecimal(newLineIdx));
        setValue(asMsg.effFromDt_A, cMsg.slsDt);
        setValue(asMsg.actvFlg_A, ZYPConstant.CHKBOX_ON_Y);
        // START 2017/08/04 T.Kanasaka [QC#18193,ADD]
        setValue(asMsg.mtrIdxCd_A, MTR_IDX.NON_FLEET);
        // END 2017/08/04 T.Kanasaka [QC#18193,ADD]
        sMsg.A.setValidCount(newLineIdx + 1);

        setValue(cMsg.xxPageShowOfNum, new BigDecimal(sMsg.A.getValidCount()));
        setValue(cMsg.xxPageShowTotNum, getLastPageNum(cMsg, sMsg));
        setValue(cMsg.xxPageShowCurNum, cMsg.xxPageShowTotNum);

        int pagenationFrom = NSAL1190CommonLogic.convertPageNumToFirstRowIndex(cMsg);
        NSAL1190CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private BigDecimal getLastPageNum(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {

        if (sMsg.A.getValidCount() <= cMsg.A.length()) {
            return BigDecimal.ONE;

        }

        return BigDecimal.valueOf(sMsg.A.getValidCount()).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP);

    }

    private void deleteLine(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }
        ZYPTableUtil.deleteRows(sMsg.A, selectedRows);
        setValue(cMsg.xxPageShowTotNum, getLastPageNum(cMsg, sMsg));

        if (cMsg.xxPageShowFromNum.getValueInt() < sMsg.A.getValidCount()) {
            setValue(cMsg.xxPageShowCurNum, cMsg.xxPageShowTotNum);
        }

        int pagenationFrom = NSAL1190CommonLogic.convertPageNumToFirstRowIndex(cMsg);
        NSAL1190CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }
}
