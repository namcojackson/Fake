/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0140;

import static business.blap.NWCL0140.constant.NWCL0140Constant.DELETE;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_ADD_LINE_ELIG_ACCT;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_ADD_LINE_ELIG_ORD_CATG;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_CMN_RESET;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_CMN_SUBMIT;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_DELETE_LINE_ELIG_ACCT;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_DELETE_LINE_ELIG_ORD_CATG;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_INIT;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_OPEN_WIN_REASON_D;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_OPEN_WIN_REASON_H;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_PAGE_JUMP;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_PAGE_JUMP_EL;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_PAGE_JUMP_EX;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_PAGE_NEXT;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_PAGE_NEXT_EL;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_PAGE_NEXT_EX;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_PAGE_PREV;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_PAGE_PREV_EL;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_PAGE_PREV_EX;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_SEARCH_ELIG_ACCT;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_SEARCH_ELIG_ORD_CATG;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_TBL_COL_SORT;
import static business.blap.NWCL0140.constant.NWCL0140Constant.MSG_PARAM_CATG;
import static business.blap.NWCL0140.constant.NWCL0140Constant.NWCM0112E;
import static business.blap.NWCL0140.constant.NWCL0140Constant.NWCM0127E;
import static business.blap.NWCL0140.constant.NWCL0140Constant.NWCM0136E;
import static business.blap.NWCL0140.constant.NWCL0140Constant.TABLE_A;
import static business.blap.NWCL0140.constant.NWCL0140Constant.TABLE_B;
import static business.blap.NWCL0140.constant.NWCL0140Constant.XX_CHK_BOX_EL;
import static business.blap.NWCL0140.constant.NWCL0140Constant.XX_CHK_BOX_EX;
import static business.blap.NWCL0140.constant.NWCL0140Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWCL0140.common.NWCL0140CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * <pre>
 * NWCL0140 CFS Lease Package Maintenance Screen
 * Function Name : search business process
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/30/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NWCL0140BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            String glblCmpyCd = getUserProfileService().getGlobalCompanyCode();
            if (EVENT_NM_NWCL0140_INIT.equals(screenAplID) //
                    || EVENT_NM_NWCL0140_CMN_RESET.equals(screenAplID) //
                    || EVENT_NM_NWCL0140_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_INIT((NWCL0140CMsg) cMsg, (NWCL0140SMsg) sMsg);
            } else if (EVENT_NM_NWCL0140_SEARCH_ELIG_ACCT.equals(screenAplID) || EVENT_NM_NWCL0140_SEARCH_ELIG_ORD_CATG.equals(screenAplID)) {
                doProcess_Search((NWCL0140CMsg) cMsg, (NWCL0140SMsg) sMsg);
            } else if (EVENT_NM_NWCL0140_ADD_LINE_ELIG_ACCT.equals(screenAplID) || EVENT_NM_NWCL0140_ADD_LINE_ELIG_ORD_CATG.equals(screenAplID)) {
                doProcess_AddDetailLine((NWCL0140CMsg) cMsg, (NWCL0140SMsg) sMsg);
            } else if (EVENT_NM_NWCL0140_PAGE_PREV.equals(screenAplID)) {
                doProcess_PagePrev((NWCL0140CMsg) cMsg, (NWCL0140SMsg) sMsg);
            } else if (EVENT_NM_NWCL0140_PAGE_NEXT.equals(screenAplID)) {
                doProcess_PageNext((NWCL0140CMsg) cMsg, (NWCL0140SMsg) sMsg);
            } else if (EVENT_NM_NWCL0140_PAGE_JUMP.equals(screenAplID)) {
                doProcess_PageJump((NWCL0140CMsg) cMsg, (NWCL0140SMsg) sMsg);
            } else if (EVENT_NM_NWCL0140_OPEN_WIN_REASON_H.equals(screenAplID) || EVENT_NM_NWCL0140_OPEN_WIN_REASON_D.equals(screenAplID)) {
                doProcess_OpenWin_OrderReason(glblCmpyCd, (NWCL0140CMsg) cMsg);
            } else if (EVENT_NM_NWCL0140_TBL_COL_SORT.equals(screenAplID)) {
                doProcess_TBLColumnSort((NWCL0140CMsg) cMsg, (NWCL0140SMsg) sMsg);
            } else if (EVENT_NM_NWCL0140_DELETE_LINE_ELIG_ACCT.equals(screenAplID) || EVENT_NM_NWCL0140_DELETE_LINE_ELIG_ORD_CATG.equals(screenAplID)) {
                doProcess_DeleteLine((NWCL0140CMsg) cMsg, (NWCL0140SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init and Reset and Search Event
     * @param cMsg NWCL0140CMsg
     * @param sMsg NWCL0140SMsg
     */
    private void doProcess_INIT(NWCL0140CMsg cMsg, NWCL0140SMsg sMsg) {

        // Table Initialize
        String xxScrEventNm = null;
        if (ZYPCommonFunc.hasValue(cMsg.xxScrEventNm)) {
            xxScrEventNm = cMsg.xxScrEventNm.getValue();
        }
        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(cMsg.D);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.C);
        ZYPTableUtil.clear(sMsg.D);

        ZYPEZDItemValueSetter.setValue(cMsg.xxScrEventNm, xxScrEventNm);

        String glblCmpyCd = getGlobalCompanyCode();

        NWCL0140CommonLogic.findCfsLeaseEligAcct(glblCmpyCd, cMsg, sMsg);
        NWCL0140CommonLogic.findCfsLeaseAttrb(glblCmpyCd, cMsg, sMsg);
        NWCL0140CommonLogic.findCfsLeaseEligAOrdCatg(glblCmpyCd, cMsg, sMsg);
    }

    /**
     * Init and Reset and Search Event
     * @param cMsg NWCL0140CMsg
     * @param sMsg NWCL0140SMsg
     */
    private void doProcess_Search(NWCL0140CMsg cMsg, NWCL0140SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        if (EVENT_NM_NWCL0140_SEARCH_ELIG_ACCT.equals(cMsg.xxScrEventNm.getValue())) {
            // Table Initialize
            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(sMsg.A);
            ZYPTableUtil.clear(sMsg.C);
            NWCL0140CommonLogic.findCfsLeaseEligAcct(glblCmpyCd, cMsg, sMsg);
        } else if (EVENT_NM_NWCL0140_SEARCH_ELIG_ORD_CATG.equals(cMsg.xxScrEventNm.getValue())) {
            // Table Initialize
            ZYPTableUtil.clear(cMsg.B);
            ZYPTableUtil.clear(sMsg.B);
            ZYPTableUtil.clear(sMsg.D);
            NWCL0140CommonLogic.findCfsLeaseEligAOrdCatg(glblCmpyCd, cMsg, sMsg);
        }
    }

    /**
     * Add Line
     * @param cMsg NWCL0140CMsg
     * @param sMsg NWCL0140SMsg
     */
    private void doProcess_AddDetailLine(NWCL0140CMsg cMsg, NWCL0140SMsg sMsg) {

        if (EVENT_NM_NWCL0140_ADD_LINE_ELIG_ACCT.equals(cMsg.xxScrEventNm.getValue())) {
            if (sMsg.A.getValidCount() == sMsg.A.length()) {
                cMsg.setMessageInfo(NWCM0136E, new String[] {String.valueOf(sMsg.A.length()) });
                return;
            }
            NWCL0140CommonLogic.updateGlblMsgEL(cMsg, sMsg);
            cMsg.xxPageShowFromNum_EL.setValue(sMsg.A.getValidCount());
            sMsg.A.setValidCount(sMsg.A.getValidCount() + 1);
            NWCL0140CommonLogic.loadOnePageToCMsgEL(cMsg, cMsg.A, sMsg, sMsg.A);
        } else if (EVENT_NM_NWCL0140_ADD_LINE_ELIG_ORD_CATG.equals(cMsg.xxScrEventNm.getValue())) {
            if (sMsg.B.getValidCount() == sMsg.B.length()) {
                cMsg.setMessageInfo(NWCM0136E, new String[] {String.valueOf(sMsg.B.length()) });
                return;
            }
            NWCL0140CommonLogic.updateGlblMsgEX(cMsg, sMsg);
            cMsg.xxPageShowFromNum_EX.setValue(sMsg.B.getValidCount());
            sMsg.B.setValidCount(sMsg.B.getValidCount() + 1);
            NWCL0140CommonLogic.loadOnePageToCMsgEX(cMsg, cMsg.B, sMsg, sMsg.B);
        }

    }

    /**
     * PageJump Event
     * @param bizMsg NWCL0140CMsg
     * @param glblMsg NWCL0140SMsg
     */
    private void doProcess_PageJump(NWCL0140CMsg bizMsg, NWCL0140SMsg glblMsg) {

        if (EVENT_NM_NWCL0140_PAGE_JUMP_EL.equals(bizMsg.xxScrEventNm.getValue())) {
            // copy data from glblMsg onto bizMsg
            NWCL0140CommonLogic.updateGlblMsgEL(bizMsg, glblMsg);
            bizMsg.xxPageShowFromNum_EL.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum_EL.getValueInt() - 1)) + 1);
            NWCL0140CommonLogic.loadOnePageToCMsgEL(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
        } else if (EVENT_NM_NWCL0140_PAGE_JUMP_EX.equals(bizMsg.xxScrEventNm.getValue())) {
            // copy data from glblMsg onto bizMsg
            NWCL0140CommonLogic.updateGlblMsgEX(bizMsg, glblMsg);
            bizMsg.xxPageShowFromNum_EX.setValue((bizMsg.B.length() * (bizMsg.xxPageShowCurNum_EX.getValueInt() - 1)) + 1);
            NWCL0140CommonLogic.loadOnePageToCMsgEX(bizMsg, bizMsg.B, glblMsg, glblMsg.B);
        }
    }

    /**
     * PageNext Event
     * @param bizMsg NWCL0140CMsg
     * @param glblMsg NWCL0140SMsg
     */
    private void doProcess_PageNext(NWCL0140CMsg bizMsg, NWCL0140SMsg glblMsg) {
        if (EVENT_NM_NWCL0140_PAGE_NEXT_EL.equals(bizMsg.xxScrEventNm.getValue())) {
            NWCL0140CommonLogic.updateGlblMsgEL(bizMsg, glblMsg);
            // copy data from glblMsg onto bizMsg
            bizMsg.xxPageShowFromNum_EL.setValue(bizMsg.xxPageShowToNum_EL.getValueInt() + 1);
            NWCL0140CommonLogic.loadOnePageToCMsgEL(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
        } else if (EVENT_NM_NWCL0140_PAGE_NEXT_EX.equals(bizMsg.xxScrEventNm.getValue())) {
            NWCL0140CommonLogic.updateGlblMsgEX(bizMsg, glblMsg);
            // copy data from glblMsg onto bizMsg
            bizMsg.xxPageShowFromNum_EX.setValue(bizMsg.xxPageShowToNum_EX.getValueInt() + 1);
            NWCL0140CommonLogic.loadOnePageToCMsgEX(bizMsg, bizMsg.B, glblMsg, glblMsg.B);
        }
    }

    /**
     * PagePrev Event
     * @param bizMsgNWCL0140CMsg
     * @param glblMsg NWCL0140SMsg
     */
    private void doProcess_PagePrev(NWCL0140CMsg bizMsg, NWCL0140SMsg glblMsg) {
        if (EVENT_NM_NWCL0140_PAGE_PREV_EL.equals(bizMsg.xxScrEventNm.getValue())) {
            NWCL0140CommonLogic.updateGlblMsgEL(bizMsg, glblMsg);
            // copy data from glblMsg onto bizMsg
            bizMsg.xxPageShowFromNum_EL.setValue(bizMsg.xxPageShowFromNum_EL.getValueInt() - bizMsg.A.length());
            NWCL0140CommonLogic.loadOnePageToCMsgEL(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
        } else if (EVENT_NM_NWCL0140_PAGE_PREV_EX.equals(bizMsg.xxScrEventNm.getValue())) {
            NWCL0140CommonLogic.updateGlblMsgEX(bizMsg, glblMsg);
            // copy data from glblMsg onto bizMsg
            bizMsg.xxPageShowFromNum_EX.setValue(bizMsg.xxPageShowFromNum_EX.getValueInt() - bizMsg.B.length());
            NWCL0140CommonLogic.loadOnePageToCMsgEX(bizMsg, bizMsg.B, glblMsg, glblMsg.B);
        }
    }

    /**
     * doProcess_OpenWin_OrderReason
     * @param bizMsg NWCL0140CMsg
     */
    private void doProcess_OpenWin_OrderReason(String glblCmpyCd, NWCL0140CMsg bizMsg) {
        String dsOrdCatgDescTxt = null;
        EZDCStringItem csItem = null;
        if (ZYPCommonFunc.hasValue(bizMsg.xxRowNum)) {
            dsOrdCatgDescTxt = bizMsg.B.no(bizMsg.xxRowNum.getValueInt()).dsOrdCatgDescTxt_EX.getValue();
            csItem = bizMsg.B.no(bizMsg.xxRowNum.getValueInt()).dsOrdCatgCd_EX;
        } else {
            dsOrdCatgDescTxt = bizMsg.dsOrdCatgDescTxt.getValue();
            csItem = bizMsg.dsOrdCatgCd;
        }
        if (!NWCL0140CommonLogic.findDsOrdCatg(bizMsg, glblCmpyCd, dsOrdCatgDescTxt, csItem)) {
            if (ZYPCommonFunc.hasValue(bizMsg.xxRowNum)) {
                bizMsg.B.no(bizMsg.xxRowNum.getValueInt()).dsOrdCatgDescTxt_EX.clear();
                bizMsg.B.no(bizMsg.xxRowNum.getValueInt()).dsOrdCatgDescTxt_EX.setErrorInfo(1, NWCM0112E, new String[] {MSG_PARAM_CATG });
            } else {
                bizMsg.dsOrdTpDescTxt.clear();
                bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, NWCM0112E, new String[] {MSG_PARAM_CATG });
            }
        }
    }

    /**
     * sort table column process
     * @param bizMsg NWCL0140CMsg
     * @param shareMsg NWCL0140SMsg
     */
    private void doProcess_TBLColumnSort(NWCL0140CMsg bizMsg, NWCL0140SMsg glblMsg) {

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table A
        if (TABLE_A.equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            // copy (SMsg -> CMsg)
            int i = 0;
            for (; i < glblMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            bizMsg.xxPageShowFromNum_EL.setValue(1);
            bizMsg.xxPageShowToNum_EL.setValue(bizMsg.A.getValidCount());
        } else if (TABLE_B.equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(glblMsg.B, glblMsg.B.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.B.getValidCount());

            // copy (SMsg -> CMsg)
            int i = 0;
            for (; i < glblMsg.B.getValidCount(); i++) {
                if (i == bizMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(glblMsg.B.no(i), null, bizMsg.B.no(i), null);
            }
            bizMsg.B.setValidCount(i);

            bizMsg.xxPageShowFromNum_EX.setValue(1);
            bizMsg.xxPageShowToNum_EX.setValue(bizMsg.B.getValidCount());
        }
    }

    /**
     * Delete Line Event
     * @param bizMsg NWCL0140CMsg
     * @param glblMsg NWCL0140SMsg
     */
    private void doProcess_DeleteLine(NWCL0140CMsg bizMsg, NWCL0140SMsg glblMsg) {

        if (EVENT_NM_NWCL0140_DELETE_LINE_ELIG_ACCT.equals(bizMsg.xxScrEventNm.getValue())) {

            NWCL0140CommonLogic.updateGlblMsgEL(bizMsg, glblMsg);
            List<Integer> delIdxEl = ZYPTableUtil.getSelectedRows(glblMsg.A, XX_CHK_BOX_EL, ZYPConstant.CHKBOX_ON_Y);

            if (delIdxEl.size() == 0) {
                if (bizMsg.A.getValidCount() == 0) {
                    bizMsg.setMessageInfo(NWCM0127E);
                    return;
                }
                for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                    glblMsg.A.no(i).xxChkBox_EL.setErrorInfo(1, NWCM0127E);
                }
            }

            List<Integer> delIdxElList = new ArrayList<Integer>();
            try {
                for (int idx : delIdxEl) {
                    if (ZYPCommonFunc.hasValue(glblMsg.A.no(idx).cfsLeaseEligAcctPk_EL)) {
                        int elValidCnt = glblMsg.C.getValidCount();
                        ZYPEZDItemValueSetter.setValue(glblMsg.C.no(elValidCnt).cfsLeaseEligAcctPk_DL, glblMsg.A.no(idx).cfsLeaseEligAcctPk_EL);
                        ZYPEZDItemValueSetter.setValue(glblMsg.C.no(elValidCnt).ezUpTime_DL, glblMsg.A.no(idx).ezUpTime_EL);
                        ZYPEZDItemValueSetter.setValue(glblMsg.C.no(elValidCnt).ezUpTimeZone_DL, glblMsg.A.no(idx).ezUpTimeZone_EL);
                        glblMsg.C.setValidCount(elValidCnt + 1);
                        delIdxElList.add(idx);
                    } else {
                        delIdxElList.add(idx);
                    }
                }
                if (delIdxElList.size() > 0) {
                    ZYPTableUtil.deleteRows(glblMsg.A, delIdxElList);
                    bizMsg.setMessageInfo(ZZZM9003I, new String[] {DELETE });
                }
                if (bizMsg.xxPageShowFromNum_EL.getValueInt() > glblMsg.A.getValidCount()) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_EL, BigDecimal.valueOf(glblMsg.A.getValidCount() - 1));
                }
            } finally {
                NWCL0140CommonLogic.loadOnePageToCMsgEL(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
            }
        } else if (EVENT_NM_NWCL0140_DELETE_LINE_ELIG_ORD_CATG.equals(bizMsg.xxScrEventNm.getValue())) {

            NWCL0140CommonLogic.updateGlblMsgEX(bizMsg, glblMsg);
            List<Integer> delIdxEX = ZYPTableUtil.getSelectedRows(glblMsg.B, XX_CHK_BOX_EX, ZYPConstant.CHKBOX_ON_Y);

            if (delIdxEX.size() == 0) {
                if (bizMsg.B.getValidCount() == 0) {
                    bizMsg.setMessageInfo(NWCM0127E);
                    return;
                }
                for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                    glblMsg.B.no(i).xxChkBox_EX.setErrorInfo(1, NWCM0127E);
                }
            }

            List<Integer> delIdxEXList = new ArrayList<Integer>();
            try {
                for (int idx : delIdxEX) {
                    if (ZYPCommonFunc.hasValue(glblMsg.B.no(idx).cfsLeaseEligOrdCatgPk_EX)) {
                        int exValidCnt = glblMsg.D.getValidCount();
                        ZYPEZDItemValueSetter.setValue(glblMsg.D.no(exValidCnt).cfsLeaseEligOrdCatgPk_DX, glblMsg.B.no(idx).cfsLeaseEligOrdCatgPk_EX);
                        ZYPEZDItemValueSetter.setValue(glblMsg.D.no(exValidCnt).ezUpTime_DX, glblMsg.B.no(idx).ezUpTime_EX);
                        ZYPEZDItemValueSetter.setValue(glblMsg.D.no(exValidCnt).ezUpTimeZone_DX, glblMsg.B.no(idx).ezUpTimeZone_EX);
                        glblMsg.D.setValidCount(exValidCnt + 1);
                        delIdxEXList.add(idx);
                    } else {
                        delIdxEXList.add(idx);
                    }
                }
                if (delIdxEXList.size() > 0) {
                    ZYPTableUtil.deleteRows(glblMsg.B, delIdxEXList);
                    bizMsg.setMessageInfo(ZZZM9003I, new String[] {DELETE });
                }
                if (bizMsg.xxPageShowFromNum_EL.getValueInt() > glblMsg.A.getValidCount()) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_EX, BigDecimal.valueOf(glblMsg.B.getValidCount() - 1));
                }
            } finally {
                NWCL0140CommonLogic.loadOnePageToCMsgEX(bizMsg, bizMsg.B, glblMsg, glblMsg.B);
            }
        }
    }
}
