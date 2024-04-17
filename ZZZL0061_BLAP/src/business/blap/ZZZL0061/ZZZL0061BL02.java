package business.blap.ZZZL0061;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZZL0061BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        ZZZL0061CMsg bizMsg = (ZZZL0061CMsg)cMsg;
        String screenAplID = bizMsg.getScreenAplID();
        try {
            super.preDoProcess(cMsg, sMsg);
            if("ZZZL0061_INIT".equals(screenAplID)) {
                //this.setInitialValues(cMsg, sMsg);
            } else if("ZZZL0061Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZZL0061_PageNext(cMsg, sMsg);
            } else if("ZZZL0061Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZZL0061_PagePrev(cMsg, sMsg);
            } else if("ZZZL0061Scrn00_Search".equals(screenAplID)) {
                //this.setInitialValues(cMsg, sMsg);
                doProcess_ZZZL0061Scrn00_Search(cMsg, sMsg);
            }            
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }
    
    /**
     * Method: Page Next
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_ZZZL0061_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {

        ZZZL0061CMsg bizMsg = (ZZZL0061CMsg) cMsg;
        ZZZL0061SMsg shareMsg = (ZZZL0061SMsg) sMsg;

        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        setPagenation(bizMsg, shareMsg, pagenationFrom);

    }

    /**
     * Method: Page Prev
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_ZZZL0061_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZZL0061CMsg bizMsg = (ZZZL0061CMsg) cMsg;
        ZZZL0061SMsg shareMsg = (ZZZL0061SMsg) sMsg;

        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        setPagenation(bizMsg, shareMsg, pagenationFrom);
 

    }
    
    private void setPagenation(ZZZL0061CMsg cMsg, ZZZL0061SMsg sMsg, int pagenationFrom) {

        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i >= sMsg.A.getValidCount()) {
                break;
            }
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }
    
    /**
     * Method: Search Method for AOM02
     * @param handler EZDCommonHandler
     */
    public static void doProcess_ZZZL0061Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {

        ZZZL0061CMsg bizMsg = (ZZZL0061CMsg) cMsg;
        ZZZL0061SMsg shareMsg = (ZZZL0061SMsg) sMsg;

        // initialize the table data
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        shareMsg.clear();
        shareMsg.A.clear();
        shareMsg.A.setValidCount(0);
        
        S21SsmEZDResult ssmResult = ZZZL0061Query.getInstance().getBatProcLogMV(bizMsg, shareMsg);

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
            bizMsg.A.setValidCount(0);
        }
    }

}
