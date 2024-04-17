/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1160;

import static business.blap.NSAL1160.constant.NSAL1160Constant.*;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1160.common.NSAL1160CommonLogic;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 *
 * Supply Watch Notes Action
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/26/2016   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSAL1160BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSAL1160CMsg cMsg = (NSAL1160CMsg) arg0;
        NSAL1160SMsg sMsg = (NSAL1160SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1160_INIT".equals(screenAplID)) {
                doProcess_NSAL1160_INIT(cMsg, sMsg);
            } else if ("NSAL1160Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL1160Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NSAL1160Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1160Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL1160Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1160Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL1160Scrn00_SearchA".equals(screenAplID)) {
                doProcess_NSAL1160Scrn00_SearchA(cMsg, sMsg);
            } else if ("NSAL1160Scrn00_SearchB".equals(screenAplID)) {
                doProcess_NSAL1160Scrn00_SearchB(cMsg, sMsg);
            } else if ("NSAL1160Scrn00_SearchC".equals(screenAplID)) {
                doProcess_NSAL1160Scrn00_SearchC(cMsg, sMsg);
            } else if ("NSAL1160Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL1160Scrn00_TBLColumnSort(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1160_INIT(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {

        if (!NSAL1160CommonLogic.checkMandatory(cMsg, sMsg)) {
            return;
        }

        searchInit(cMsg, sMsg);
    }

    private void doProcess_NSAL1160Scrn00_CMN_Reset(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {
        searchInit(cMsg, sMsg);
    }

    private void doProcess_NSAL1160Scrn00_PageNext(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {

        if (TABLE_A.equals(cMsg.xxModeInd.getValue())) {
            int pageFrom = cMsg.xxPageShowFromNum_A.getValueInt() + cMsg.A.length() - 1;
            NSAL1160CommonLogic.pagenation_A(cMsg, sMsg, pageFrom);
        } else if (TABLE_B.equals(cMsg.xxModeInd.getValue())) {
            int pageFrom = cMsg.xxPageShowFromNum_B.getValueInt() + cMsg.B.length() - 1;
            NSAL1160CommonLogic.pagenation_B(cMsg, sMsg, pageFrom);
        } else {
            int pageFrom = cMsg.xxPageShowFromNum_C.getValueInt() + cMsg.C.length() - 1;
            NSAL1160CommonLogic.pagenation_C(cMsg, sMsg, pageFrom);
        }
    }

    private void doProcess_NSAL1160Scrn00_PagePrev(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {

        if (TABLE_A.equals(cMsg.xxModeInd.getValue())) {
            int pageFrom = cMsg.xxPageShowFromNum_A.getValueInt() - cMsg.A.length() - 1;
            NSAL1160CommonLogic.pagenation_A(cMsg, sMsg, pageFrom);
        } else if (TABLE_B.equals(cMsg.xxModeInd.getValue())) {
            int pageFrom = cMsg.xxPageShowFromNum_B.getValueInt() - cMsg.B.length() - 1;
            NSAL1160CommonLogic.pagenation_B(cMsg, sMsg, pageFrom);
        } else {
            int pageFrom = cMsg.xxPageShowFromNum_C.getValueInt() - cMsg.C.length() - 1;
            NSAL1160CommonLogic.pagenation_C(cMsg, sMsg, pageFrom);
        }
    }

    private void doProcess_NSAL1160Scrn00_SearchA(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {

        clearSearchResultTableA(cMsg, sMsg);
        S21SsmEZDResult ssmResult = NSAL1160CommonLogic.searchNotesInfo(cMsg, sMsg);
        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            return;
        }

        NSAL1160CommonLogic.pagenation_A(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSAL1160Scrn00_SearchB(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {

        clearSearchResultTableB(cMsg, sMsg);
        S21SsmEZDResult ssmResult = NSAL1160CommonLogic.searchWorkflowActionsInfo(cMsg, sMsg);
        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            return;
        }

        NSAL1160CommonLogic.pagenation_B(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum_B.setValue(sMsg.B.getValidCount());
    }

    private void doProcess_NSAL1160Scrn00_SearchC(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {

        clearSearchResultTableC(cMsg, sMsg);
        S21SsmEZDResult ssmResult = NSAL1160CommonLogic.searchEnforcementActions(cMsg, sMsg);
        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            return;
        }

        NSAL1160CommonLogic.pagenation_C(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum_C.setValue(sMsg.C.getValidCount());
    }

    private void doProcess_NSAL1160Scrn00_TBLColumnSort(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {
        if (TABLE_A.equals(cMsg.xxSortTblNm.getValue())) {
            sortColumnTblA(cMsg, sMsg);
        } else if (TABLE_B.equals(cMsg.xxSortTblNm.getValue())) {
            sortColumnTblB(cMsg, sMsg);
        } else {
            sortColumnTblC(cMsg, sMsg);
        }
    }

    private void searchInit(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {

        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        clearSearchResultTableA(cMsg, sMsg);
        clearSearchResultTableB(cMsg, sMsg);
        clearSearchResultTableC(cMsg, sMsg);

        NSAL1160CommonLogic.searchInit(cMsg, sMsg);

        NSAL1160CommonLogic.pagenation_A(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());

        NSAL1160CommonLogic.pagenation_B(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum_B.setValue(sMsg.B.getValidCount());

        NSAL1160CommonLogic.pagenation_C(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum_C.setValue(sMsg.C.getValidCount());
    }

    private static void sortColumnTblA(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {

        NSAL1160CommonLogic.clearPageNum_A(cMsg);

        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

        EZDMsg.copy(sMsg.A, null, cMsg.A, null);

        cMsg.xxPageShowFromNum_A.setValue(1);
        cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());
    }

    private static void sortColumnTblB(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {

        NSAL1160CommonLogic.clearPageNum_B(cMsg);

        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        S21SortTarget sortTarget = new S21SortTarget(sMsg.B, sMsg.B.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.B.getValidCount());

        EZDMsg.copy(sMsg.B, null, cMsg.B, null);

        cMsg.xxPageShowFromNum_B.setValue(1);
        cMsg.xxPageShowToNum_B.setValue(cMsg.B.getValidCount());
        cMsg.xxPageShowOfNum_B.setValue(sMsg.B.getValidCount());
    }

    private static void sortColumnTblC(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {

        NSAL1160CommonLogic.clearPageNum_C(cMsg);

        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        S21SortTarget sortTarget = new S21SortTarget(sMsg.C, sMsg.C.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.C.getValidCount());

        EZDMsg.copy(sMsg.C, null, cMsg.C, null);

        cMsg.xxPageShowFromNum_C.setValue(1);
        cMsg.xxPageShowToNum_C.setValue(cMsg.C.getValidCount());
        cMsg.xxPageShowOfNum_C.setValue(sMsg.C.getValidCount());
    }

    /**
     * clear Search Result(TableA)
     * @param bizMsg NSAL1160CMsg
     * @param globalMsg NSAL1160SMsg
     */
    private void clearSearchResultTableA(NSAL1160CMsg bizMsg, NSAL1160SMsg globalMsg) {

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        globalMsg.A.clear();
        globalMsg.A.setValidCount(0);
        bizMsg.xxPageShowFromNum_A.setValue(0);
        bizMsg.xxPageShowToNum_A.setValue(0);
        bizMsg.xxPageShowOfNum_A.setValue(0);
    }

    /**
     * clear Search Result(TableB)
     * @param bizMsg NSAL1160CMsg
     * @param globalMsg NSAL1160SMsg
     */
    private void clearSearchResultTableB(NSAL1160CMsg bizMsg, NSAL1160SMsg globalMsg) {

        bizMsg.B.clear();
        bizMsg.B.setValidCount(0);
        globalMsg.B.clear();
        globalMsg.B.setValidCount(0);
        bizMsg.xxPageShowFromNum_B.setValue(0);
        bizMsg.xxPageShowToNum_B.setValue(0);
        bizMsg.xxPageShowOfNum_B.setValue(0);
    }

    /**
     * clear Search Result(TableC)
     * @param bizMsg NSAL1160CMsg
     * @param globalMsg NSAL1160SMsg
     */
    private void clearSearchResultTableC(NSAL1160CMsg bizMsg, NSAL1160SMsg globalMsg) {

        bizMsg.C.clear();
        bizMsg.C.setValidCount(0);
        globalMsg.C.clear();
        globalMsg.C.setValidCount(0);
        bizMsg.xxPageShowFromNum_C.setValue(0);
        bizMsg.xxPageShowToNum_C.setValue(0);
        bizMsg.xxPageShowOfNum_C.setValue(0);
    }
}
