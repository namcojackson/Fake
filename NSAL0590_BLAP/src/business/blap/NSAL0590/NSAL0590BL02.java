package business.blap.NSAL0590;

import static business.blap.NSAL0590.constant.NSAL0590Constant.*;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL0590.common.NSAL0590CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 *
 * Report Group
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Hitachi         J.Kim           Create          N/A
 * 2016/02/09   Hitachi         T.Harada        Update          QC#2946
 * 2016/03/25   Hitachi         M.Gotou         Update          QC#4918
 *</pre>
 */
public class NSAL0590BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSAL0590CMsg cMsg = (NSAL0590CMsg) arg0;
        NSAL0590SMsg sMsg = (NSAL0590SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0590_INIT".equals(screenAplID)) {
                doProcess_NSAL0590_INIT(cMsg, sMsg);
            } else if ("NSAL0590Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0590Scrn00_Search(cMsg, sMsg);
            } else if ("NSAL0590Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0590Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSAL0590Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0590Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NSAL0590Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NSAL0590Scrn00_InsertRow(cMsg, sMsg);
            } else if ("NSAL0590Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0590Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0590Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0590Scrn00_PagePrev(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * Initialize event handler
     * @param cMsg NSAL0590CMsg
     * @param sMsg NSAL0590SMsg
     */
    private void doProcess_NSAL0590_INIT(NSAL0590CMsg cMsg, NSAL0590SMsg sMsg) {

        // START QC#2946
        sMsg.clear();
        sMsg.A.setValidCount(0);
        // END QC#2946

        // get global company code
        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        // START 2016/03/25 M.Gotou [QC#4918, MOD]
//        // End From Date
//        cMsg.dsContrRptGrpStartDt_H.setValue(ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        // Start To Date
        cMsg.dsContrRptGrpStartDt_HE.setValue(ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        // END 2016/03/25 M.Gotou [QC#4918, MOD]
        // End From Date
        cMsg.dsContrRptGrpEndDt_H.setValue(ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        // START 2016/03/25 M.Gotou [QC#4918, ADD]
        NSAL0590CommonLogic.searchDsContrRptGrpInfo(cMsg, sMsg);
        EZDMsg.copy(sMsg.A, null, sMsg.B, null);
        // END 2016/03/25 M.Gotou [QC#4918, ADD]
    }

    /**
     * Reset event handler
     * @param cMsg NSAL0590CMsg
     * @param sMsg NSAL0590SMsg
     */
    private void doProcess_NSAL0590Scrn00_CMN_Reset(NSAL0590CMsg cMsg, NSAL0590SMsg sMsg) {

        // START 2016/03/25 M.Gotou [QC#4918, MOD]
//        NSAL0590CommonLogic.clearTable(cMsg, sMsg);
//        NSAL0590CommonLogic.clearPageNum(cMsg);
//
//        cMsg.clear();
//        sMsg.clear();
//        // get global company code
//        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
//        // End From Date
//        cMsg.dsContrRptGrpStartDt_H.setValue(ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
//        // End From Date
//        cMsg.dsContrRptGrpEndDt_H.setValue(ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        doProcess_NSAL0590_INIT(cMsg, sMsg);
        // END 2016/03/25 M.Gotou [QC#4918, MOD]
    }

    /**
     * Search event handler
     * @param cMsg NSAL0590CMsg
     * @param sMsg NSAL0590SMsg
     */
    private void doProcess_NSAL0590Scrn00_Search(NSAL0590CMsg cMsg, NSAL0590SMsg sMsg) {

        NSAL0590CommonLogic.clearTable(cMsg, sMsg);
        NSAL0590CommonLogic.clearPageNum(cMsg);

        NSAL0590CommonLogic.searchDsContrRptGrpInfo(cMsg, sMsg);
        EZDMsg.copy(sMsg.A, null, sMsg.B, null);
    }

    private void doProcess_NSAL0590Scrn00_InsertRow(NSAL0590CMsg cMsg, NSAL0590SMsg sMsg) {

        int count = sMsg.A.getValidCount();
        if (sMsg.A.length() < count + 1) {
            cMsg.setMessageInfo(NSAM0112E);
            return;
        }

        NSAL0590CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        sMsg.A.setValidCount(count + 1);

        int lastPage = count / cMsg.A.length();
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(lastPage * cMsg.A.length());
        cMsg.xxPageShowToNum.clear();

        NSAL0590CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * Page next event handler
     * @param cMsg NSAL0590CMsg
     * @param sMsg NSAL0590SMsg
     */
    private void doProcess_NSAL0590Scrn00_PageNext(NSAL0590CMsg cMsg, NSAL0590SMsg sMsg) {

        NSAL0590CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt());
        cMsg.xxPageShowToNum.clear();

        NSAL0590CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * Page prev event handler
     * @param cMsg NSAL0590CMsg
     * @param sMsg NSAL0590SMsg
     */
    private void doProcess_NSAL0590Scrn00_PagePrev(NSAL0590CMsg cMsg, NSAL0590SMsg sMsg) {

        NSAL0590CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1);
        cMsg.xxPageShowToNum.clear();
        NSAL0590CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    private void doProcess_NSAL0590Scrn00_CMN_Submit(NSAL0590CMsg cMsg, NSAL0590SMsg sMsg) {
        NSAL0590CommonLogic.searchDsContrRptGrpInfo(cMsg, sMsg);
    }

}
