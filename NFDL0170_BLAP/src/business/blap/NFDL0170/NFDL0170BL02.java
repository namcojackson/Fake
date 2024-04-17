/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0170;

import static business.blap.NFDL0170.constant.NFDL0170Constant.INCLUDE_CLOSED_INVOICES;
import static business.blap.NFDL0170.constant.NFDL0170Constant.TBL_ID;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFDL0170.common.NFDL0170CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Customer Account Search Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/07   Hitachi         T.Tsuchida      Create          QC#19574
 * 2017/09/27   Hitachi         T.Tsuchida      Update          QC#21373
 *</pre>
 */
public class NFDL0170BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFDL0170CMsg bizMsg = (NFDL0170CMsg) cMsg;
            NFDL0170SMsg glblMsg = (NFDL0170SMsg) sMsg;

            if ("NFDL0170_INIT".equals(screenAplID)) {
                doProcess_NFDL0170_INIT(bizMsg, glblMsg);
            } else if ("NFDL0170Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFDL0170Scrn00_CMN_Clear(bizMsg, glblMsg);
            } else if ("NFDL0170Scrn00_PageJump".equals(screenAplID)) {
                    doProcess_NFDL0170Scrn00_PageJump(bizMsg, glblMsg);
            } else if ("NFDL0170Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFDL0170Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NFDL0170Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFDL0170Scrn00_PagePrev(bizMsg, glblMsg);
            } else if ("NFDL0170Scrn00_Search".equals(screenAplID)) {
                doProcess_NFDL0170Scrn00_Search(bizMsg, glblMsg);
            } else if ("NFDL0170Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFDL0170Scrn00_TBLColumnSort(bizMsg, glblMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFDL0170_INIT(NFDL0170CMsg bizMsg, NFDL0170SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());

        // START 2017/09/27 T.Tsuchida [QC#21373,ADD]
        String IncludeClosedInvoices = ZYPCodeDataUtil.getVarCharConstValue(INCLUDE_CLOSED_INVOICES, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_CL, IncludeClosedInvoices);
        // END 2017/09/27 T.Tsuchida [QC#21373,ADD]

        ZYPGUITableColumn.getColData(bizMsg, glblMsg, TBL_ID);
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFDL0170Scrn00_CMN_Clear(NFDL0170CMsg bizMsg, NFDL0170SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        bizMsg.clear();
        bizMsg.A.setValidCount(0);
        glblMsg.clear();
        glblMsg.A.setValidCount(0);

        doProcess_NFDL0170_INIT(bizMsg, glblMsg);
    }

    /**
     * PageJump Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NFDL0170Scrn00_PageJump(NFDL0170CMsg bizMsg, NFDL0170SMsg glblMsg) {

        NFDL0170CommonLogic.saveCurrentPageToSMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NFDL0170CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFDL0170Scrn00_PageNext(NFDL0170CMsg bizMsg, NFDL0170SMsg glblMsg) {

        NFDL0170CommonLogic.saveCurrentPageToSMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NFDL0170CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFDL0170Scrn00_PagePrev(NFDL0170CMsg bizMsg, NFDL0170SMsg glblMsg) {

        NFDL0170CommonLogic.saveCurrentPageToSMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NFDL0170CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFDL0170Scrn00_Search(NFDL0170CMsg bizMsg, NFDL0170SMsg glblMsg) {
        NFDL0170CommonLogic.search(bizMsg, glblMsg);
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFDL0170Scrn00_TBLColumnSort(NFDL0170CMsg bizMsg, NFDL0170SMsg glblMsg) {
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if (TBL_ID.equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            bizMsg.xxPageShowFromNum.setValue(1);
            NFDL0170CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }
}
