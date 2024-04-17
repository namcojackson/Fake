package business.blap.NSAL0750;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0750.common.NSAL0750CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 *
 * Update PO Information
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2016/12/08   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/02/14   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL0750BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSAL0750CMsg cMsg = (NSAL0750CMsg) arg0;
        NSAL0750SMsg sMsg = (NSAL0750SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0750_INIT".equals(screenAplID)) {
                doProcess_NSAL0750_INIT(cMsg, sMsg);
            // START 2017/02/14 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0750Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0750Scrn00_CMN_Reset(cMsg, sMsg);
            // END   2017/02/14 K.Ochiai [QC#16331, MOD]
            // mod start 2016/12/08 CSA QC#4210
            } else if ("NSAL0750Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0750Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0750Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0750Scrn00_PagePrev(cMsg, sMsg);
            // mod end 2016/12/08 CSA QC#4210
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Initialize event handler
     * @param cMsg NSAL0750CMsg
     * @param sMsg NSAL0750SMsg
     */
    private void doProcess_NSAL0750_INIT(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg) {

        initialize(cMsg, sMsg);
        // mod start 2016/12/08 CSA QC#4210
        // set Paging Data
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        // mod end 2016/12/08 CSA QC#4210
    }

    /**
     * Reset event handler
     * @param cMsg NSAL0750CMsg
     * @param sMsg NSAL0750SMsg
     */
    // START 2017/02/14 K.Ochiai [QC#16331, MOD]
    private void doProcess_NSAL0750Scrn00_CMN_Reset(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg) {

        cMsg.baseBllgTmgCd_H3.clear();
        cMsg.svcMemoRsnCd_H3.clear();
        cMsg.svcCmntTxt.clear();

        initialize(cMsg, sMsg);
    }
    // END   2017/02/14 K.Ochiai [QC#16331, MOD]


    /**
     * initialize event handler
     * @param cMsg NSAL0750CMsg
     * @param sMsg NSAL0750SMsg
     */
    private void initialize(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg) {

        // get global company code
        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        // Invoicing Rule Pull down
        NSAL0750CommonLogic.setInvoicingRuleInfo(cMsg);

        // Service Memo Reason Pull down
        NSAL0750CommonLogic.setServiceMemoReasonInfo(cMsg, sMsg);
        cMsg.xxChkBox_H1.clear();
        cMsg.xxChkBox_H2.clear();

        if (cMsg.P.getValidCount() > 0) {
            sMsg.A.setValidCount(0);
            NSAL0750CommonLogic.setDetailListInfo(cMsg, sMsg);
        }
    }

    // mod start 2016/12/08 CSA QC#4210
    private void doProcess_NSAL0750Scrn00_PageNext(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg) {

        NSAL0750CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0750_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        int pageTo = cMsg.xxPageShowToNum.getValueInt();
        int i = pageTo;
        for (; i < pageTo + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageTo), null);
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageTo);

        cMsg.xxPageShowFromNum.setValue(pageTo + 1);
        cMsg.xxPageShowToNum.setValue(pageTo + acMsgArray.getValidCount());
    }

    private void doProcess_NSAL0750Scrn00_PagePrev(NSAL0750CMsg cMsg, NSAL0750SMsg sMsg) {

        NSAL0750CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0750_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - acMsgArray.length() - 1;
        int i = pageFrom;
        for (; i < pageFrom + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageFrom);

        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pageFrom);
        cMsg.xxPageShowToNum.setValue(pageFrom + acMsgArray.getValidCount() - 1);
    }
    // mod end 2016/12/08 CSA QC#4210
}
