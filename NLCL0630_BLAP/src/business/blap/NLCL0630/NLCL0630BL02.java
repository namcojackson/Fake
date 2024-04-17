/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0630;

import static business.blap.NLCL0630.constant.NLCL0630Constant.EVENT_NM_NLCL0630_BEGIN;
import static business.blap.NLCL0630.constant.NLCL0630Constant.EVENT_NM_NLCL0630_CANCEL;
import static business.blap.NLCL0630.constant.NLCL0630Constant.EVENT_NM_NLCL0630_CMN_RESET;
import static business.blap.NLCL0630.constant.NLCL0630Constant.EVENT_NM_NLCL0630_INIT;
import static business.blap.NLCL0630.constant.NLCL0630Constant.EVENT_NM_NLCL0630_PAGE_NEXT;
import static business.blap.NLCL0630.constant.NLCL0630Constant.EVENT_NM_NLCL0630_PAGE_PREV;
import static business.blap.NLCL0630.constant.NLCL0630Constant.EVENT_NM_NLCL0630_SEARCH;
import static business.blap.NLCL0630.constant.NLCL0630Constant.EVENT_NM_NLCL0630_SEARCH_BRANCH;
import static business.blap.NLCL0630.constant.NLCL0630Constant.EVENT_NM_NLCL0630_SEARCH_TECHNICIAN;
import static business.blap.NLCL0630.constant.NLCL0630Constant.EVENT_NM_NLCL0630_SEARCH_LOCATION;
import static business.blap.NLCL0630.constant.NLCL0630Constant.EVENT_NM_NLCL0630_NLCL0640;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL0630.common.NLCL0630CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NLCL0630 Tech PI Inquiry
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/26/2016   CITS         Makoto Okigami     Create          N/A
 * 08/07/2018   CITS            Y.Iwasaki       Update          QC#27010
 * 09/12/2018   CITS            M.Naito         Update          QC#28190
 * 12/03/2018   Fujitsu         T.Ogura         Update          QC#27978
 * 12/11/2018   Fujitsu         T.Ogura         Update          QC#28755
 *</pre>
 */
public class NLCL0630BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL0630_INIT.equals(screenAplID)
                    || EVENT_NM_NLCL0630_CMN_RESET.equals(screenAplID)) {
                doProcess_NLCL0630_INIT((NLCL0630CMsg) cMsg, (NLCL0630SMsg) sMsg);
            } else if (EVENT_NM_NLCL0630_SEARCH.equals(screenAplID)) {
                doProcess_Search((NLCL0630CMsg) cMsg, (NLCL0630SMsg) sMsg);
            } else if (EVENT_NM_NLCL0630_PAGE_NEXT.equals(screenAplID)) {
                doProcess_PageNext((NLCL0630CMsg) cMsg, (NLCL0630SMsg) sMsg);
            } else if (EVENT_NM_NLCL0630_PAGE_PREV.equals(screenAplID)) {
                doProcess_PagePrev((NLCL0630CMsg) cMsg, (NLCL0630SMsg) sMsg);
            } else if (EVENT_NM_NLCL0630_SEARCH_TECHNICIAN.equals(screenAplID)) {
                doProcess_SearchTechnician((NLCL0630CMsg) cMsg, (NLCL0630SMsg) sMsg);
            } else if (EVENT_NM_NLCL0630_SEARCH_BRANCH.equals(screenAplID)) {
                doProcess_SearchBranch((NLCL0630CMsg) cMsg, (NLCL0630SMsg) sMsg);
            // START 2018/12/11 T.Ogura [QC#28755,ADD]
            } else if (EVENT_NM_NLCL0630_SEARCH_LOCATION.equals(screenAplID)) {
                doProcess_SearchLocation((NLCL0630CMsg) cMsg, (NLCL0630SMsg) sMsg);
            // END   2018/12/11 T.Ogura [QC#28755,ADD]
            } else if (EVENT_NM_NLCL0630_CANCEL.equals(screenAplID)) {
                doProcess_Cancel((NLCL0630CMsg) cMsg, (NLCL0630SMsg) sMsg);
            // START 2018/12/03 T.Ogura [QC#27978,MOD]
            } else if (EVENT_NM_NLCL0630_BEGIN.equals(screenAplID)) {
                doProcess_Begin((NLCL0630CMsg) cMsg, (NLCL0630SMsg) sMsg);
            } else if (EVENT_NM_NLCL0630_NLCL0640.equals(screenAplID)) {
                doProcess_NLCL0640((NLCL0630CMsg) cMsg, (NLCL0630SMsg) sMsg);
            // END   2018/12/03 T.Ogura [QC#27978,MOD]
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     */
    private void doProcess_NLCL0630_INIT(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg) {

        // There is no processing.

    }

    /**
     * Search
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     */
    private void doProcess_Search(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0630CommonLogic.search(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Page Next
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     */
    private void doProcess_PageNext(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());

    }

    /**
     * Page Prev
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     */
    private void doProcess_PagePrev(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);

    }

    /**
     * Search Technician
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     */
    private void doProcess_SearchTechnician(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0630CommonLogic.searchTechnician(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Search Branch
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     */
    private void doProcess_SearchBranch(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0630CommonLogic.searchBranch(cMsg, sMsg, glblCmpyCd);

    }

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * Search Location
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     */
    private void doProcess_SearchLocation(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0630CommonLogic.searchLocation(cMsg, sMsg, glblCmpyCd);

    }
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

    /**
     * Cancel
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     */
    private void doProcess_Cancel(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0630CommonLogic.search(cMsg, sMsg, glblCmpyCd);

    }

    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * Begin
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     */
    private void doProcess_Begin(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0630CommonLogic.search(cMsg, sMsg, glblCmpyCd);

    }
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * NLCL0640
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     */
    private void doProcess_NLCL0640(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0630CommonLogic.search(cMsg, sMsg, glblCmpyCd);

    }
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

}
