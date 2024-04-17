/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/22   Fujitsu         Mori            Create          N/A
 *</pre>
 */
package business.blap.NLBL0100;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLBL0100.constant.NLBL0100Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

public class NLBL0100BL02 extends S21BusinessHandler implements NLBL0100Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        try {
            // get EventID
            String screenAplID = cMsg.getScreenAplID();

            // dispatch Event
            if ("NLBL0100_INIT".equals(screenAplID)) {
                doProcess_NLBL0100_INIT((NLBL0100CMsg) cMsg, (NLBL0100SMsg) sMsg);
            } else if ("NLBL0100Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NLBL0100Scrn00_PagePrev((NLBL0100CMsg) cMsg, (NLBL0100SMsg) sMsg);
            } else if ("NLBL0100Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NLBL0100Scrn00_PageNext((NLBL0100CMsg) cMsg, (NLBL0100SMsg) sMsg);
            } else if ("NLBL0100Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NLBL0100Scrn00_TBLColumnSort((NLBL0100CMsg) cMsg, (NLBL0100SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
	
    /**
     * <pre>
     * Process of init screen event
     * </pre>
     * 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0100_INIT(NLBL0100CMsg cMsg, NLBL0100SMsg sMsg) {

        // ****** retrieve Header area info ******
        S21SsmEZDResult headerRs = NLBL0100Query.getInstance().getHistoryHeaderInfo(cMsg);
        
        if (headerRs.isCodeNotFound()) {
            // Impossible case
            cMsg.setMessageInfo("NZZM0000E");
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }

        
        // ****** retrieve Detail List area info ******
        S21SsmEZDResult detailRs = NLBL0100Query.getInstance().getHistoryDetailList(cMsg, sMsg);

        if (detailRs.isCodeNotFound()) {
            cMsg.setMessageInfo("NZZM0000E");
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }

        int queryResCnt = detailRs.getQueryResultCount();
        if (queryResCnt > sMsg.A.length()) {
            cMsg.setMessageInfo("NZZM0001W");
            queryResCnt = sMsg.A.length();
        }

        // ***** Copy to one page's retrieval result (SMsg -> CMsg) *****
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
        cMsg.xxPageShowOfNum.setValue(queryResCnt);
    }

    /**
     * <pre>
     * Process of onClick "PagePrev" event
     * </pre>
     * 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0100Scrn00_PagePrev(NLBL0100CMsg cMsg, NLBL0100SMsg sMsg) {

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
     * <pre>
     * Process of onClick "PageNext" event
     * </pre>
     * 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0100Scrn00_PageNext(NLBL0100CMsg cMsg, NLBL0100SMsg sMsg) {

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
     * <pre>
     * Process of onClick "sort link" event
     * </pre>
     * 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0100Scrn00_TBLColumnSort(NLBL0100CMsg cMsg, NLBL0100SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if (NLBL0100Constant.TBL_NAME_A.equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // Copy (SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // show first page
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        }
    }
	
}
