/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */

package business.blap.NLBL3100;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLBL3100.common.NLBL3100CommonLogic;
import business.blap.NLBL3100.constant.NLBL3100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Reman Labor/Expense Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/13/2015   CITS            M.Ito           Create          N/A
 * 2016/12/19   CITS            K.Kameoka       Update          QC#15573
*</pre>
 */
public class NLBL3100BL02 extends S21BusinessHandler implements NLBL3100Constant {

    /**
     * Screen Application ID
     */
    private String screenAplID;

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLBL3100_INIT.equals(screenAplID)) {
                doProcess_NLBL3100_INIT((NLBL3100CMsg) cMsg, (NLBL3100SMsg) sMsg);
            } else if (EVENT_NM_NLBL3100SCRN00_SEARCH.equals(screenAplID)) {
                doProcess_NLBL3100Scrn00_Search((NLBL3100CMsg) cMsg, (NLBL3100SMsg) sMsg);
            } else if (EVENT_NM_NLBL3100SCRN00_PAGE_PREV.equals(screenAplID)) {
                doProcess_NLBL3100Scrn00_PagePrev((NLBL3100CMsg) cMsg, (NLBL3100SMsg) sMsg);
            } else if (EVENT_NM_NLBL3100SCRN00_PAGE_NEXT.equals(screenAplID)) {
                doProcess_NLBL3100Scrn00_PageNext((NLBL3100CMsg) cMsg, (NLBL3100SMsg) sMsg);
            } else if (EVENT_NM_NLBL3100SCRN00_CLEAR.equals(screenAplID)) {
                doProcess_NLBL3100_INIT((NLBL3100CMsg) cMsg, (NLBL3100SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * The method explanation: The method explanation: It is a method
     * of the execution when the event[INIT] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL3100_INIT(NLBL3100CMsg cMsg, NLBL3100SMsg sMsg) {

        EZDDebugOutput.println(1, "PerformanceNLBL3100 start[doProcess_NLBL3100_INIT]", null);

        // =============================================
        // Acquisition of global company code
        // =============================================
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd_G1, getGlobalCompanyCode());
        // QC#15573 Start
        // if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd_H1)) {

        // doProcess_NLBL3100Scrn00_Search(cMsg, sMsg);
        // } else {

        // NLBL3100CommonLogic.clearMsg(cMsg, sMsg);

        // }
        doProcess_NLBL3100Scrn00_Search(cMsg, sMsg);

        // QC#15573 End

        EZDDebugOutput.println(1, "PerformanceNLBL3100 end[doProcess_NLBL3100_INIT]", null);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick_Search] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL3100Scrn00_Search(NLBL3100CMsg cMsg, NLBL3100SMsg sMsg) {

        EZDDebugOutput.println(1, "PerformanceNLBL3100 start[doProcess_NLBL3100Scrn00_Search]", null);

        EZDMsg.copy(cMsg, null, sMsg, null);

        // =================================================
        // Search Retail Warehouse Process
        // =================================================

        S21SsmEZDResult ssmResult = NLBL3100CommonLogic.search(cMsg, sMsg, this);

        if (ssmResult.isCodeNormal()) {

            // 1000over
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.A.length();
            }

            // 1page copy（SMsg -> CMsg）
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // pagePre,PageNext
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

            cMsg.setMessageInfo(NZZM0002I);
        } else {

            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            // When there is no retrieval result of details
            // information
            // Setting of message to 0 acquisition numbers
            cMsg.setMessageInfo(NZZM0000E);
        }

        EZDDebugOutput.println(1, "PerformanceNLBL3100 end[doProcess_NLBL3100Scrn00_Search]", null);

    }

    private void doProcess_NLBL3100Scrn00_PagePrev(NLBL3100CMsg cMsg, NLBL3100SMsg sMsg) {
        // Save current page to SMsg
        NLBL3100CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        int startIndex = 0;

        startIndex = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;

        // Load previous page from SMsg
        NLBL3100CommonLogic.copySMsgToCMsg(cMsg, sMsg, startIndex);
    }

    private void doProcess_NLBL3100Scrn00_PageNext(NLBL3100CMsg cMsg, NLBL3100SMsg sMsg) {
        // Save current page data to SMsg
        NLBL3100CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        // Load next page data from SMsg
        int startIndex = cMsg.xxPageShowToNum.getValueInt();
        NLBL3100CommonLogic.copySMsgToCMsg(cMsg, sMsg, startIndex);
    }

}
