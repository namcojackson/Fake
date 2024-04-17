/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6850;

import static business.blap.NMAL6850.constant.NMAL6850Constant.EVENT_NMAL6850_NMAL6860;
import static business.blap.NMAL6850.constant.NMAL6850Constant.EVENT_NM_CMN_RESET;
import static business.blap.NMAL6850.constant.NMAL6850Constant.EVENT_NM_CMN_RETURN;
import static business.blap.NMAL6850.constant.NMAL6850Constant.EVENT_NM_INIT;
import static business.blap.NMAL6850.constant.NMAL6850Constant.EVENT_NM_ON_CLICK_NEW;
import static business.blap.NMAL6850.constant.NMAL6850Constant.EVENT_NM_ON_CLICK_SEARCH;
import static business.blap.NMAL6850.constant.NMAL6850Constant.EVENT_NM_OPEN_WIN_SUPPLIER;
import static business.blap.NMAL6850.constant.NMAL6850Constant.EVENT_NM_PAGE_NEXT;
import static business.blap.NMAL6850.constant.NMAL6850Constant.EVENT_NM_PAGE_PREV;
import static business.blap.NMAL6850.constant.NMAL6850Constant.MSG_CD_NZZM0000E;
import static business.blap.NMAL6850.constant.NMAL6850Constant.MSG_CD_NZZM0001W;
import static business.blap.NMAL6850.constant.NMAL6850Constant.MSG_CD_NZZM0002I;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRNT_VND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID   : NMAL6850 Supplier Search
 * Function Name : The business process for Search.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/23   CITS            M.Ouchi         Create          N/A
 * 2020/02/28   Fujitsu         C.Hara          Update          QC#55971
 * </pre>
 */
public class NMAL6850BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String scrnAplId = cMsg.getScreenAplID();

            NMAL6850CMsg bizMsg = (NMAL6850CMsg) cMsg;
            NMAL6850SMsg glblMsg = (NMAL6850SMsg) sMsg;

            if (EVENT_NM_INIT.equals(scrnAplId)) {
                doProcessInit(bizMsg, glblMsg);
            } else if (EVENT_NM_ON_CLICK_SEARCH.equals(scrnAplId)) {
                doProcessOnClickSearch(bizMsg, glblMsg);
            } else if (EVENT_NM_ON_CLICK_NEW.equals(scrnAplId)) {
                doProcessOnClickNew(bizMsg, glblMsg);
            } else if (EVENT_NM_PAGE_PREV.equals(scrnAplId)) {
                doProcessPagePrev(bizMsg, glblMsg);
            } else if (EVENT_NM_PAGE_NEXT.equals(scrnAplId)) {
                doProcessPageNext(bizMsg, glblMsg);
            } else if (EVENT_NM_OPEN_WIN_SUPPLIER.equals(scrnAplId)) {
                doProcessOpenWinSupplier(bizMsg, glblMsg);
            } else if (EVENT_NM_CMN_RESET.equals(scrnAplId)) {
                doProcessCmnReset(bizMsg, glblMsg);
            } else if (EVENT_NM_CMN_RETURN.equals(scrnAplId)) {
                doProcessCmnReturn(bizMsg, glblMsg);
            } else if (EVENT_NMAL6850_NMAL6860.equals(scrnAplId)) {
                doProcessOnClickSearch(bizMsg, glblMsg);
            } else {
                throw new S21AbendException("Illegal Screen Application Id. : " + scrnAplId);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <p>
     * Initialization.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     */
    private void doProcessInit(NMAL6850CMsg cMsg, NMAL6850SMsg sMsg) {
        // Initializes.
        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.A);

        // Sets the global company code.
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(sMsg.glblCmpyCd, getGlobalCompanyCode());

        // Creates the pull-down lists.
        // ZYPCodeDataUtil.createPulldownList(SPLY_TP.class,
        // cMsg.splyTpCd_L, cMsg.splyTpDescTxt_L);
        // ZYPCodeDataUtil.createPulldownList(PRNT_VND_TP.class, cMsg.prntVndTpCd_L2, cMsg.prntVndTpDescTxt_L2); // 2020/02/28 QC#55971 Del
    }

    /**
     * <p>
     * Runs the onclick event of "Search" button.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     */
    private void doProcessOnClickSearch(NMAL6850CMsg cMsg, NMAL6850SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);

        // Searches Suppliers.
        S21SsmEZDResult result = NMAL6850Query.getInstance().searchSuppliers(cMsg, sMsg);

        if (result.isCodeNormal()) {
            // Checks if the row counts exceeds the maximum counts.
            int resultCount = result.getQueryResultCount();
            if (resultCount > sMsg.A.length()) {
                // Search results exceeds max count for display.
                resultCount = sMsg.A.length();
                cMsg.setMessageInfo(MSG_CD_NZZM0001W);
            } else {
                cMsg.setMessageInfo(MSG_CD_NZZM0002I);
            }

            // Sets the search result to the table.
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(resultCount);

        } else {
            cMsg.setMessageInfo(MSG_CD_NZZM0000E);
            ZYPTableUtil.clear(cMsg.A);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * <p>
     * Runs the onclick event of "New" button.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     */
    private void doProcessOnClickNew(NMAL6850CMsg cMsg, NMAL6850SMsg sMsg) {
        // Do nothing.
    }

    /**
     * <p>
     * Runs the onclick event of "PagePrev" button.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     */
    private void doProcessPagePrev(NMAL6850CMsg cMsg, NMAL6850SMsg sMsg) {
        doProcessPagination(cMsg, sMsg);

    }

    /**
     * <p>
     * Runs the onclick event of "PageNext" button.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     */
    private void doProcessPageNext(NMAL6850CMsg cMsg, NMAL6850SMsg sMsg) {
        doProcessPagination(cMsg, sMsg);
    }

    /**
     * <p>
     * Pagination process.<br>
     * Copies data from SMsg onto CMsg, and calculates the number of
     * showing from / to.
     * </p>
     * @param cMsg scrnMsg
     * @param sMsg glblMsg
     */
    private void doProcessPagination(NMAL6850CMsg cMsg, NMAL6850SMsg sMsg) {
        // from index.
        int from = cMsg.xxPageShowFromNum.getValueInt();

        // copy data from SMsg onto CMsg
        int row = 0;
        for (; row < cMsg.A.length(); row++) {
            if (from + row + 1 > cMsg.xxPageShowOfNum.getValueInt()) {
                break;
            }
            EZDMsg.copy(sMsg.A.no(from + row), null, cMsg.A.no(row), null);
        }
        cMsg.A.setValidCount(row);

        // set value to paging items.
        cMsg.xxPageShowFromNum.setValue(from + 1);
        cMsg.xxPageShowToNum.setValue(from + row);
    }

    /**
     * <p>
     * Runs the onclick event of "Supplier Number" link.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     */
    private void doProcessOpenWinSupplier(NMAL6850CMsg cMsg, NMAL6850SMsg sMsg) {
        // Do nothing.
    }

    /**
     * <p>
     * Runs the onclick event of "RESET" button.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     */
    private void doProcessCmnReset(NMAL6850CMsg cMsg, NMAL6850SMsg sMsg) {

        // Runs the initialize process.
        doProcessInit(cMsg, sMsg);

        cMsg.setMessageInfo(MSG_CD_NZZM0002I);
    }

    /**
     * <p>
     * Runs the onclick event of "RETURN" button.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     */
    private void doProcessCmnReturn(NMAL6850CMsg cMsg, NMAL6850SMsg sMsg) {
        // Do nothing.
    }
}
