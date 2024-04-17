/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2410;

import static business.blap.NWAL2410.constant.NWAL2410Constant.NWAM0924E;
import static business.blap.NWAL2410.constant.NWAL2410Constant.NWAM0927E;
import static business.blap.NWAL2410.constant.NWAL2410Constant.NZZM0000E;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2410.common.NWAL2410CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NWAL2410BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/25   Fujitsu         N.Aoyama        Create          QC#16740
 * 2017/02/20   Fujitsu         N.Aoyama        Update          QC#17676
 * 2017/02/21   Fujitsu         N.Aoyama        Update          QC#17676-2
 * 2017/02/23   Fujitsu         N.Aoyama        Update          QC#17676-3
 *</pre>
 */
public class NWAL2410BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2410CMsg bizMsg = (NWAL2410CMsg) cMsg;
            NWAL2410SMsg glblMsg = (NWAL2410SMsg) sMsg;

            if ("NWAL2410_INIT".equals(screenAplID)) {
                doProcess_NWAL2410_INIT(bizMsg, glblMsg);

            } else if ("NWAL2410Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWAL2410Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NWAL2410Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL2410Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NWAL2410Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NWAL2410Scrn00_DeleteRow(bizMsg, glblMsg);

            } else if ("NWAL2410Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NWAL2410Scrn00_InsertRow(bizMsg, glblMsg);

            } else if ("NWAL2410Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NWAL2410Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NWAL2410Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWAL2410Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NWAL2410Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWAL2410Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NWAL2410Scrn00_Search".equals(screenAplID)) {
                doProcess_NWAL2410Scrn00_Search(bizMsg, glblMsg);

            } else if ("NWAL2410Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NWAL2410Scrn00_TBLColumnSort(bizMsg, glblMsg);

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
    private void doProcess_NWAL2410_INIT(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg) {
        // clear
        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.R);

        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.R);
        ZYPTableUtil.clear(glblMsg.B);

        // The create LOB pull down.
        ZYPCodeDataUtil.createPulldownList("DOC_MGT_ORG", bizMsg.docMgtOrgCd_P, bizMsg.docMgtOrgDescTxt_P);

    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2410Scrn00_CMN_Submit(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxRqstFlg.getValue())) {
            doProcess_NWAL2410Scrn00_Search(bizMsg, glblMsg);
            bizMsg.xxRqstFlg.clear();
        }
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2410Scrn00_CMN_Clear(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg) {
        doProcess_NWAL2410_INIT(bizMsg, glblMsg);
    }

    /**
     * DeleteRow Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2410Scrn00_DeleteRow(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg) {
        //
        NWAL2410CommonLogic.saveCurrentPageToSMsg(bizMsg, glblMsg);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                bizMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NWAM0927E);
            }
            bizMsg.setMessageInfo(NWAM0927E);
            return;
        } else {
            ZYPTableUtil.deleteRows(glblMsg.A, selectedRows);
        }

        int fromIdx = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int beforePageNum = (fromIdx / bizMsg.A.length()) + 1;
        int afterMaxPageNum = ((glblMsg.A.getValidCount() - 1) / bizMsg.A.length()) + 1;
        if (afterMaxPageNum < beforePageNum) {
            fromIdx = bizMsg.A.length() * (afterMaxPageNum - 1);
        }

        bizMsg.xxPageShowFromNum.setValue(fromIdx);
        NWAL2410CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * InsertRow Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2410Scrn00_InsertRow(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg) {

        int maxCnt = glblMsg.A.length();
        if (maxCnt == glblMsg.A.getValidCount()) {
            bizMsg.setMessageInfo(NWAM0924E, new String[] {Integer.toString(maxCnt) });
            return;
        }

        // back up CMsg -> SMsg
        NWAL2410CommonLogic.saveCurrentPageToSMsg(bizMsg, glblMsg);

        // Input date is Common Check and Duplicate Check on Screen
        // 2017/02/21 QC#17676-2 UPD START
        // if (!NWAL2410CommonLogic.inputCheck(bizMsg, glblMsg,
        // getGlobalCompanyCode())) {
        if (!NWAL2410CommonLogic.inputCheckForPage(bizMsg, glblMsg, getGlobalCompanyCode())) {
            // 2017/02/21 QC#17676-2 UPD E N D
            return;
        }

        // row count up
        int nextIdx = glblMsg.A.getValidCount();
        glblMsg.A.setValidCount(nextIdx + 1);
        // 2017/02/23 QC#17676-3 DEL START
        // glblMsg.A.no(nextIdx).actvFlg_A.setValue(ZYPConstant.FLG_ON_Y);
        // 2017/02/23 QC#17676-3 DEL E N D

        // Insert row and pagination (check this page or next page)
        int startIndex = NWAL2410CommonLogic.getPageStartRowIndex(bizMsg, nextIdx);

        bizMsg.xxPageShowFromNum.setValue(startIndex);
        NWAL2410CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2410Scrn00_PageJump(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg) {

        NWAL2410CommonLogic.saveCurrentPageToSMsg(bizMsg, glblMsg);

        // Input date is Common Check and Duplicate Check on Screen
        // 2017/02/21 QC#17676-2 UPD START
        // if (!NWAL2410CommonLogic.inputCheck(bizMsg, glblMsg,
        // getGlobalCompanyCode())) {
        if (!NWAL2410CommonLogic.inputCheckForPage(bizMsg, glblMsg, getGlobalCompanyCode())) {
            // 2017/02/21 QC#17676-2 UPD E N D
            return;
        }

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NWAL2410CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2410Scrn00_PageNext(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg) {

        NWAL2410CommonLogic.saveCurrentPageToSMsg(bizMsg, glblMsg);

        // Input date is Common Check and Duplicate Check on Screen
        // 2017/02/21 QC#17676-2 UPD START
        // if (!NWAL2410CommonLogic.inputCheck(bizMsg, glblMsg,
        // getGlobalCompanyCode())) {
        if (!NWAL2410CommonLogic.inputCheckForPage(bizMsg, glblMsg, getGlobalCompanyCode())) {
            // 2017/02/21 QC#17676-2 UPD E N D
            return;
        }

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWAL2410CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2410Scrn00_PagePrev(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg) {

        NWAL2410CommonLogic.saveCurrentPageToSMsg(bizMsg, glblMsg);

        // Input date is Common Check and Duplicate Check on Screen
        // 2017/02/21 QC#17676-2 UPD START
        // if (!NWAL2410CommonLogic.inputCheck(bizMsg, glblMsg,
        // getGlobalCompanyCode())) {
        if (!NWAL2410CommonLogic.inputCheckForPage(bizMsg, glblMsg, getGlobalCompanyCode())) {
            // 2017/02/21 QC#17676-2 UPD E N D
            return;
        }

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NWAL2410CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2410Scrn00_Search(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg) {

        // search
        search(bizMsg, glblMsg);
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2410Scrn00_TBLColumnSort(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg) {
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            // set pagination.
            bizMsg.xxPageShowFromNum.setValue(1);
            NWAL2410CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);

        S21SsmEZDResult ssmResult = NWAL2410Query.getInstance().getThereforeBranchData(bizMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxRqstFlg.getValue())) {
                bizMsg.setMessageInfo(NZZM0000E);
            }

        } else {

            NWAL2410CommonLogic.setResult(ssmResult, bizMsg, glblMsg);

            EZDMsg.copy(glblMsg.A, null, glblMsg.B, null);

            bizMsg.xxPageShowFromNum.setValue(1);
            NWAL2410CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

        }

        bizMsg.setCommitSMsg(true);

    }

}
