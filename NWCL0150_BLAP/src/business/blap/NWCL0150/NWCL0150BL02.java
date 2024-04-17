/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0150;

import static business.blap.NWCL0150.constant.NWCL0150Constant.EVENT_NM_NWCL0150_CMN_CLEAR;
import static business.blap.NWCL0150.constant.NWCL0150Constant.EVENT_NM_NWCL0150_CMN_RESET;
import static business.blap.NWCL0150.constant.NWCL0150Constant.EVENT_NM_NWCL0150_CMN_SUBMIT;
import static business.blap.NWCL0150.constant.NWCL0150Constant.EVENT_NM_NWCL0150_INIT;
import static business.blap.NWCL0150.constant.NWCL0150Constant.EVENT_NM_NWCL0150_PAGE_JUMP;
import static business.blap.NWCL0150.constant.NWCL0150Constant.EVENT_NM_NWCL0150_PAGE_NEXT;
import static business.blap.NWCL0150.constant.NWCL0150Constant.EVENT_NM_NWCL0150_PAGE_PREV;
import static business.blap.NWCL0150.constant.NWCL0150Constant.EVENT_NM_NWCL0150_SEARCH;
import static business.blap.NWCL0150.constant.NWCL0150Constant.EVENT_NM_NWCL0150_TBL_COL_SORT;
import static business.blap.NWCL0150.constant.NWCL0150Constant.TABLE_A;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWCL0150.common.NWCL0150CommonLogic;

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
 * Business ID : NWCL0150:Lease Package Hold Release Screen
 * Function Name : search business process
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/09/2016   CITS            K.Ogino         Create          N/A
 * 01/09/2018   CITS            W.Honda         Update          QC#21706-2
 * 04/18/2022   CITS            J.Evangelista   Update          QC#59934
 *</pre>
 */
public class NWCL0150BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            // QC#21706-2 2018/01/09 Mod Start
//            if (EVENT_NM_NWCL0150_INIT.equals(screenAplID) //
//                    || EVENT_NM_NWCL0150_CMN_RESET.equals(screenAplID) //
//                    || EVENT_NM_NWCL0150_CMN_SUBMIT.equals(screenAplID)) {
            // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//            if (EVENT_NM_NWCL0150_INIT.equals(screenAplID)) {
            if (EVENT_NM_NWCL0150_INIT.equals(screenAplID)
                || EVENT_NM_NWCL0150_CMN_CLEAR.equals(screenAplID)) {
            // END 2022/04/18 J.Evangelista [QC#59934,MOD]
                doProcess_INIT((NWCL0150CMsg) cMsg, (NWCL0150SMsg) sMsg);
//            } else if (EVENT_NM_NWCL0150_SEARCH.equals(screenAplID)) {
            } else if (EVENT_NM_NWCL0150_SEARCH.equals(screenAplID) //
                || EVENT_NM_NWCL0150_CMN_RESET.equals(screenAplID) //
                || EVENT_NM_NWCL0150_CMN_SUBMIT.equals(screenAplID)) {
                // QC#21706-2 2018/01/09 Mod End
                doProcess_Search((NWCL0150CMsg) cMsg, (NWCL0150SMsg) sMsg);
            } else if (EVENT_NM_NWCL0150_PAGE_PREV.equals(screenAplID)) {
                doProcess_PagePrev((NWCL0150CMsg) cMsg, (NWCL0150SMsg) sMsg);
            } else if (EVENT_NM_NWCL0150_PAGE_NEXT.equals(screenAplID)) {
                doProcess_PageNext((NWCL0150CMsg) cMsg, (NWCL0150SMsg) sMsg);
            } else if (EVENT_NM_NWCL0150_PAGE_JUMP.equals(screenAplID)) {
                doProcess_PageJump((NWCL0150CMsg) cMsg, (NWCL0150SMsg) sMsg);
            } else if (EVENT_NM_NWCL0150_TBL_COL_SORT.equals(screenAplID)) {
                doProcess_TBLColumnSort((NWCL0150CMsg) cMsg, (NWCL0150SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init and Reset and Search Event
     * @param cMsg NWCL0150CMsg
     * @param sMsg NWCL0150SMsg
     */
    private void doProcess_INIT(NWCL0150CMsg cMsg, NWCL0150SMsg sMsg) {

        // Table Initialize
        String xxScrEventNm = null;
        if (ZYPCommonFunc.hasValue(cMsg.xxScrEventNm)) {
            xxScrEventNm = cMsg.xxScrEventNm.getValue();
        }
        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        ZYPEZDItemValueSetter.setValue(cMsg.xxScrEventNm, xxScrEventNm);

        String glblCmpyCd = getGlobalCompanyCode();

        NWCL0150CommonLogic.findCfsLeaseAttrb(glblCmpyCd, cMsg, sMsg);

        if (EVENT_NM_NWCL0150_CMN_SUBMIT.equals(cMsg.xxScrEventNm.getValue())) {
            NWCL0150CommonLogic.findCfsLeasePkgPo(glblCmpyCd, cMsg, sMsg);
        }
        // Create Y/N Pulldown
        cMsg.cfsLeasePkgHldFlg_HF.no(0).setValue(ZYPConstant.FLG_ON_Y);
        cMsg.xxFlgNm_HF.no(0).setValue(ZYPConstant.FLG_ON_Y);
        cMsg.cfsLeasePkgHldFlg_HF.no(1).setValue(ZYPConstant.FLG_OFF_N);
        cMsg.xxFlgNm_HF.no(1).setValue(ZYPConstant.FLG_OFF_N);
        cMsg.leasePkgCratFlg_CF.no(0).setValue(ZYPConstant.FLG_ON_Y);
        cMsg.xxFlgNm_CF.no(0).setValue(ZYPConstant.FLG_ON_Y);
        cMsg.leasePkgCratFlg_CF.no(1).setValue(ZYPConstant.FLG_OFF_N);
        cMsg.xxFlgNm_CF.no(1).setValue(ZYPConstant.FLG_OFF_N);
    }

    /**
     * Init and Reset and Search Event
     * @param cMsg NWCL0150CMsg
     * @param sMsg NWCL0150SMsg
     */
    private void doProcess_Search(NWCL0150CMsg cMsg, NWCL0150SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        // Table Initialize
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        NWCL0150CommonLogic.findCfsLeasePkgPo(glblCmpyCd, cMsg, sMsg);
    }

    /**
     * PageJump Event
     * @param bizMsg NWCL0150CMsg
     * @param glblMsg NWCL0150SMsg
     */
    private void doProcess_PageJump(NWCL0150CMsg bizMsg, NWCL0150SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        NWCL0150CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NWCL0150CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * PageNext Event
     * @param bizMsg NWCL0150CMsg
     * @param glblMsg NWCL0150SMsg
     */
    private void doProcess_PageNext(NWCL0150CMsg bizMsg, NWCL0150SMsg glblMsg) {
        // copy data from glblMsg onto bizMsg
        NWCL0150CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWCL0150CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * PagePrev Event
     * @param bizMsgNWCL0150CMsg
     * @param glblMsg NWCL0150SMsg
     */
    private void doProcess_PagePrev(NWCL0150CMsg bizMsg, NWCL0150SMsg glblMsg) {
        // copy data from glblMsg onto bizMsg
        NWCL0150CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NWCL0150CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * sort table column process
     * @param bizMsg NWCL0150CMsg
     * @param shareMsg NWCL0150SMsg
     */
    private void doProcess_TBLColumnSort(NWCL0150CMsg bizMsg, NWCL0150SMsg glblMsg) {

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

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        }
    }
}
