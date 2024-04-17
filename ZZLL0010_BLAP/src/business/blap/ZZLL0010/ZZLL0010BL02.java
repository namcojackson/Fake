package business.blap.ZZLL0010;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.ZZLL0010.ZZLL0010CMsg;
import business.blap.ZZLL0010.ZZLL0010SMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

public class ZZLL0010BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("ZZLL0010Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZLL0010Scrn00_Search(cMsg, sMsg);

            } else if ("ZZLL0010Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZLL0010Scrn00_PageNext(cMsg, sMsg);

            } else if ("ZZLL0010Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZLL0010Scrn00_PagePrev(cMsg, sMsg);

            } else if ("ZZLL0010Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZLL0010Scrn00_TBLColumnSort(cMsg, sMsg);

            } else if ("ZZLL0010Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_ZZLL0010Scrn00_CMN_Clear(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * Method: Search MNT Table
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_ZZLL0010Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {

        // Keep the search condition
        ZZLL0010CMsg bizMsg = (ZZLL0010CMsg) cMsg;
        ZZLL0010SMsg shareMsg = (ZZLL0010SMsg) sMsg;
        // initialize the table data
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        shareMsg.clear();
        shareMsg.A.clear();
        shareMsg.A.setValidCount(0);
        
        S21SsmEZDResult ssmResult = ZZLL0010Query.getInstance().getMntLogData(bizMsg, shareMsg);
        
        if (ssmResult.isCodeNormal()) {
            // over the maximum length
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > shareMsg.A.length()) {

                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = shareMsg.A.length();
            }

            shareMsg.A.setValidCount(queryResCnt);

            int i = 0;
            for (; i < shareMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(shareMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            // set value for Pagenenation
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);

            // no search result
        } else {
            cMsg.setMessageInfo("ZZZM9005W");
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        }
    }
    
    /**
     * Method: Page Next
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Share Message
     */
    private void doProcess_ZZLL0010Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {

        ZZLL0010CMsg bizMsg = (ZZLL0010CMsg) cMsg;
        ZZLL0010SMsg shareMsg = (ZZLL0010SMsg) sMsg;

        // clear bizMsg
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        
        // copy data from SMsg to CMsg
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < shareMsg.A.getValidCount()) {
                EZDMsg.copy(shareMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());

    }
    
    /**
     * Method: Page Prev
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_ZZLL0010Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZLL0010CMsg bizMsg = (ZZLL0010CMsg) cMsg;
        ZZLL0010SMsg shareMsg = (ZZLL0010SMsg) sMsg;

        // Store checkbox data from CMsg to SMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;

        // clear bizMsg
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        // copy data from SMsg to CMsg
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < shareMsg.A.getValidCount()) {
                EZDMsg.copy(shareMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);

    }
    
    /**
     * Method: TBLColumnSort
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Shared Message
     */
    private void doProcess_ZZLL0010Scrn00_TBLColumnSort(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZLL0010CMsg bizMsg = (ZZLL0010CMsg) cMsg;
        ZZLL0010SMsg shareMsg = (ZZLL0010SMsg) sMsg;

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(shareMsg.A, shareMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, shareMsg.A.getValidCount());

            // Copy SMsg -> CMsg
            int i = 0;
            for (; i < shareMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(shareMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        }
    }
    
    /**
     * Method: Clear
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Shared Message
     */
    private void doProcess_ZZLL0010Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZLL0010CMsg bizMsg = (ZZLL0010CMsg) cMsg;
        ZZLL0010SMsg shareMsg = (ZZLL0010SMsg) sMsg;

        bizMsg.clear();
        bizMsg.A.setValidCount(0);
        shareMsg.clear();
        shareMsg.A.setValidCount(0);

        return;
    }
}
