/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1540;

import static business.blap.NPAL1540.constant.NPAL1540Constant.DISPLAY_VAL_SER_INPUT_ENTERED;
import static business.blap.NPAL1540.constant.NPAL1540Constant.DISPLAY_VAL_SER_INPUT_NO_ENTRY;
import static business.blap.NPAL1540.constant.NPAL1540Constant.EVENT_NM_NPAL1540_ADD_ALL_LINE;
import static business.blap.NPAL1540.constant.NPAL1540Constant.EVENT_NM_NPAL1540_ADD_LINE;
import static business.blap.NPAL1540.constant.NPAL1540Constant.EVENT_NM_NPAL1540_APPLY_PO;
import static business.blap.NPAL1540.constant.NPAL1540Constant.EVENT_NM_NPAL1540_CMN_CLEAR;
import static business.blap.NPAL1540.constant.NPAL1540Constant.EVENT_NM_NPAL1540_CMN_SUBMIT;
import static business.blap.NPAL1540.constant.NPAL1540Constant.EVENT_NM_NPAL1540_DELETE_ROW;
import static business.blap.NPAL1540.constant.NPAL1540Constant.EVENT_NM_NPAL1540_INIT;
import static business.blap.NPAL1540.constant.NPAL1540Constant.EVENT_NM_NPAL1540_NLBL3000;
import static business.blap.NPAL1540.constant.NPAL1540Constant.EVENT_NM_NPAL1540_OPEN_WIN_SER_ENTRY;
import static business.blap.NPAL1540.constant.NPAL1540Constant.EVENT_NM_NPAL1540_PAGE_NEXT;
import static business.blap.NPAL1540.constant.NPAL1540Constant.EVENT_NM_NPAL1540_PAGE_PREV;
import static business.blap.NPAL1540.constant.NPAL1540Constant.EVENT_NM_NPAL1540_SEARCH;
import static business.blap.NPAL1540.constant.NPAL1540Constant.PO_ORD_DTL_LINE_NUM_A1;
import static business.blap.NPAL1540.constant.NPAL1540Constant.SERIAL_POPUP_PARAM_INBOUND;
import static business.blap.NPAL1540.constant.NPAL1540Constant.SERIAL_POPUP_PARAM_SUFFIX;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSMsg;
import business.blap.NPAL1540.common.NPAL1540CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * <pre>
 * Business ID : NPAL1540 Manual ASN Entry
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/19/2016   CITS         Makoto Okigami     Create          N/A
 *</pre>
 */
public class NPAL1540BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1540_INIT.equals(screenAplID)) {
                doProcess_NPAL1540_INIT((NPAL1540CMsg) cMsg, (NPAL1540SMsg) sMsg);
                ZYPGUITableColumn.getColData((NPAL1540CMsg) cMsg, (NPAL1540SMsg) sMsg);
            } else if (EVENT_NM_NPAL1540_CMN_CLEAR.equals(screenAplID)) {
                doProcess_Clear((NPAL1540CMsg) cMsg, (NPAL1540SMsg) sMsg);
            } else if (EVENT_NM_NPAL1540_SEARCH.equals(screenAplID)
                    || EVENT_NM_NPAL1540_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_Search((NPAL1540CMsg) cMsg, (NPAL1540SMsg) sMsg);
            } else if (EVENT_NM_NPAL1540_APPLY_PO.equals(screenAplID)) {
                doProcess_ApplyPo((NPAL1540CMsg) cMsg, (NPAL1540SMsg) sMsg);
            } else if (EVENT_NM_NPAL1540_ADD_ALL_LINE.equals(screenAplID)) {
                doProcess_AddAllLine((NPAL1540CMsg) cMsg, (NPAL1540SMsg) sMsg);
            } else if (EVENT_NM_NPAL1540_ADD_LINE.equals(screenAplID)) {
                doProcess_AddLine((NPAL1540CMsg) cMsg, (NPAL1540SMsg) sMsg);
            } else if (EVENT_NM_NPAL1540_OPEN_WIN_SER_ENTRY.equals(screenAplID)) {
                doProcess_OpenWin_SerEntry((NPAL1540CMsg) cMsg, (NPAL1540SMsg) sMsg);
            } else if (EVENT_NM_NPAL1540_NLBL3000.equals(screenAplID)) {
                doProcess_NLBL3000((NPAL1540CMsg) cMsg, (NPAL1540SMsg) sMsg);
            } else if (EVENT_NM_NPAL1540_DELETE_ROW.equals(screenAplID)) {
                doProcess_DeleteRow((NPAL1540CMsg) cMsg, (NPAL1540SMsg) sMsg);
            } else if (EVENT_NM_NPAL1540_PAGE_NEXT.equals(screenAplID)) {
                doProcess_PageNext((NPAL1540CMsg) cMsg, (NPAL1540SMsg) sMsg);
            } else if (EVENT_NM_NPAL1540_PAGE_PREV.equals(screenAplID)) {
                doProcess_PagePrev((NPAL1540CMsg) cMsg, (NPAL1540SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     */
    private void doProcess_NPAL1540_INIT(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.S);
        sMsg.clear();

        ZYPEZDItemValueSetter.setValue(cMsg.shipDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(sMsg.shipDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(cMsg.asnPlnDelyDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(sMsg.asnPlnDelyDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        NPAL1540CommonLogic.getCcy(cMsg, sMsg, glblCmpyCd);

        if (ZYPCommonFunc.hasValue(cMsg.asnSoNum)) {
            NPAL1540CommonLogic.search(cMsg, sMsg, glblCmpyCd);
        }
    }

    /**
     * Clear
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     */
    private void doProcess_Clear(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg) {

        EZDCStringItem tempXxComnColOrdTxt = cMsg.xxComnColOrdTxt;
        ZYPTableUtil.clear(cMsg.A);
        cMsg.clear();

        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.S);
        sMsg.clear();

        ZYPEZDItemValueSetter.setValue(cMsg.xxComnColOrdTxt, tempXxComnColOrdTxt);

        String glblCmpyCd = getGlobalCompanyCode();

        ZYPEZDItemValueSetter.setValue(cMsg.shipDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(sMsg.shipDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(cMsg.asnPlnDelyDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(sMsg.asnPlnDelyDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        NPAL1540CommonLogic.getCcy(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Search
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     */
    private void doProcess_Search(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPAL1540CommonLogic.search(cMsg, sMsg, glblCmpyCd);
    }

    /**
     * Apply PO
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     */
    private void doProcess_ApplyPo(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPAL1540CommonLogic.applyPo(cMsg, sMsg, glblCmpyCd);
    }

    /**
     * Add All Line
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     */
    private void doProcess_AddAllLine(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPAL1540CommonLogic.updateSMsg(cMsg, sMsg);
        NPAL1540CommonLogic.addAllLine(cMsg, sMsg, glblCmpyCd);
        NPAL1540CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
    }

    /**
     * Add Line
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     */
    private void doProcess_AddLine(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPAL1540CommonLogic.updateSMsg(cMsg, sMsg);
        NPAL1540CommonLogic.addLine(cMsg, sMsg, glblCmpyCd);

        if (!cMsg.dispPoDtlLineNum.isError() && !"E".equals(cMsg.getMessageKind())) {
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(PO_ORD_DTL_LINE_NUM_A1, S21SortKey.ASC);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            int addPos = 0;
            for ( ; addPos < sMsg.A.getValidCount(); addPos++) {
                if (sMsg.A.no(addPos).dispPoDtlLineNum_A1.getValue().equals(cMsg.dispPoDtlLineNum.getValue())) {
                    break;
                }
            }

            int showPageIndex = (addPos / cMsg.A.length()) * cMsg.A.length() + 1;
            cMsg.xxPageShowFromNum.setValue(showPageIndex);
        }

        NPAL1540CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
    }

    /**
     * Open Window Serial Entry
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     */
    private void doProcess_OpenWin_SerEntry(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.P);
        ZYPTableUtil.clear(cMsg.L);

        int selectedRowNum = cMsg.xxNum.getValueInt();
        String asnSoSlpNum = cMsg.A.no(selectedRowNum).asnSoSlpNum_A1.getValue();

        // 0.Suffix
        // The parameter set "8.Serial# List" table suffix
        ZYPEZDItemValueSetter.setValue(cMsg.P.no(0).xxPopPrm, SERIAL_POPUP_PARAM_SUFFIX);
        // 1.Header Number
        ZYPEZDItemValueSetter.setValue(cMsg.P.no(1).xxPopPrm, cMsg.A.no(selectedRowNum).poOrdNum_A1);
        // 2.Merchandise Code --> null
        // 3.Merchandise Name --> null
        // 4.Qty --> Set NPAL1540_SV
        // 5.Mode 1:Edit 2:Read --> Set NPAL1540_SV
        // 6.Transaction Line Number --> null
        // 7.Transaction Line Sub Number --> null
        // 8.Serial# List
        int setSerCount = 0;
        for (int i = 0; i < sMsg.S.getValidCount(); i++) {
            if (setSerCount >= cMsg.L.length() || setSerCount >= cMsg.A.no(selectedRowNum).asnQty_A1.getValueInt()) {
                break;
            }
            if (asnSoSlpNum.equals(sMsg.S.no(i).asnSoSlpNum_S1.getValue())) {
                // 8-1.Header# --> null
                // 8-2.Transaction# --> null
                // 8-3.Merchandise Code
                ZYPEZDItemValueSetter.setValue(cMsg.L.no(setSerCount).mdseCd_L1, cMsg.A.no(selectedRowNum).mdseCd_A1);
                // 8-4.Serial#
                ZYPEZDItemValueSetter.setValue(cMsg.L.no(setSerCount).serNum_L1, sMsg.S.no(i).serNum_S1);
                // 8-5.Enable Update Flag  Y:Edit N:Read --> Set NPAL1540_SV
                // 8-6.Reference Number --> null
                // 8-7.Request Type --> null
                // 8-9.SWH
                ZYPEZDItemValueSetter.setValue(cMsg.L.no(setSerCount).rtlSwhCd_L1, cMsg.A.no(selectedRowNum).destRtlSwhCd_A1);
                // 8-10.Inventory Location Code
                ZYPEZDItemValueSetter.setValue(cMsg.L.no(setSerCount).invtyLocCd_L1, cMsg.A.no(selectedRowNum).invtyLocCd_A1);
                // 8-11.Serial Mandatory Flag
                ZYPEZDItemValueSetter.setValue(cMsg.L.no(setSerCount).serNumTakeFlg_L1, ZYPConstant.FLG_OFF_N);
                setSerCount++;
            }
        }
        for ( ; setSerCount < cMsg.A.no(selectedRowNum).asnQty_A1.getValueInt(); setSerCount++) {
            if (setSerCount >= cMsg.L.length()) {
                break;
            }
            // 8-1.Header# --> null
            // 8-2.Transaction# --> null
            // 8-3.Merchandise Code
            ZYPEZDItemValueSetter.setValue(cMsg.L.no(setSerCount).mdseCd_L1, cMsg.A.no(selectedRowNum).mdseCd_A1);
            // 8-4.Serial# --> null
            // 8-5.Enable Update Flag  Y:Edit N:Read --> Set NPAL1540_SV
            // 8-6.Reference Number --> null
            // 8-7.Request Type --> null
            // 8-9.SWH
            ZYPEZDItemValueSetter.setValue(cMsg.L.no(setSerCount).rtlSwhCd_L1, cMsg.A.no(selectedRowNum).destRtlSwhCd_A1);
            // 8-10.Inventory Location Code
            ZYPEZDItemValueSetter.setValue(cMsg.L.no(setSerCount).invtyLocCd_L1, cMsg.A.no(selectedRowNum).invtyLocCd_A1);
            // 8-11.Serial Mandatory Flag
            ZYPEZDItemValueSetter.setValue(cMsg.L.no(setSerCount).serNumTakeFlg_L1, ZYPConstant.FLG_OFF_N);
        }
        cMsg.L.setValidCount(setSerCount);
        // 9.WH Code
        ZYPEZDItemValueSetter.setValue(cMsg.P.no(9).xxPopPrm, cMsg.A.no(selectedRowNum).destRtlWhCd_A1);
        // 10.WH Name
        ZYPEZDItemValueSetter.setValue(cMsg.P.no(10).xxPopPrm, cMsg.A.no(selectedRowNum).rtlWhNm_A1);
        // 11.Inbound Outbound Code
        ZYPEZDItemValueSetter.setValue(cMsg.P.no(11).xxPopPrm, SERIAL_POPUP_PARAM_INBOUND);
    }

    /**
     * Return NLBL3000
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     */
    private void doProcess_NLBL3000(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg) {

        int selectedRowNum = cMsg.xxNum.getValueInt();
        String asnSoSlpNum = cMsg.A.no(selectedRowNum).asnSoSlpNum_A1.getValue();

        List<Integer> delIdxList = new ArrayList<Integer>();
        for (int i = 0; i < sMsg.S.getValidCount(); i++) {
            if (asnSoSlpNum.equals(sMsg.S.no(i).asnSoSlpNum_S1.getValue())) {
                delIdxList.add(Integer.valueOf(i));
            }
        }
        ZYPTableUtil.deleteRows(sMsg.S, delIdxList);

        int saveSerCount = sMsg.S.getValidCount();
        boolean entered = false;
        for (int i = 0; i < cMsg.L.getValidCount(); i++) {
            if (saveSerCount >= sMsg.S.length()) {
                break;
            }
            if (ZYPCommonFunc.hasValue(cMsg.L.no(i).serNum_L1)) {
                ZYPEZDItemValueSetter.setValue(sMsg.S.no(saveSerCount).asnSoSlpNum_S1, asnSoSlpNum);
                ZYPEZDItemValueSetter.setValue(sMsg.S.no(saveSerCount).serNum_S1, cMsg.L.no(i).serNum_L1);
                entered = true;
                saveSerCount++;
            }
        }
        sMsg.S.setValidCount(saveSerCount);

        if (entered) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectedRowNum).xxScrItem8Txt_A1, DISPLAY_VAL_SER_INPUT_ENTERED);
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectedRowNum).xxScrItem8Txt_A1, DISPLAY_VAL_SER_INPUT_NO_ENTRY);
        }
        NPAL1540CommonLogic.updateSMsg(cMsg, sMsg);
    }

    /**
     * Delete Row
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     */
    private void doProcess_DeleteRow(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPAL1540CommonLogic.updateSMsg(cMsg, sMsg);
        NPAL1540CommonLogic.deleteRow(cMsg, sMsg, glblCmpyCd);
        NPAL1540CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
    }

    /**
     * Page Next
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     */
    private void doProcess_PageNext(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg) {

        NPAL1540CommonLogic.updateSMsg(cMsg, sMsg);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt() + 1);
        NPAL1540CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
    }

    /**
     * Page Prev
     * @param cMsg NPAL1540CMsg
     * @param sMsg NPAL1540SMsg
     */
    private void doProcess_PagePrev(NPAL1540CMsg cMsg, NPAL1540SMsg sMsg) {

        NPAL1540CommonLogic.updateSMsg(cMsg, sMsg);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());
        NPAL1540CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
    }

}
