/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1100;

import static business.blap.NSAL1100.constant.NSAL1100Constant.*;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL1100.common.NSAL1100CommonLogic;
import business.db.SVC_CR_REBILTMsgArray;

import com.canon.cusa.s21.api.NSZ.NSZC053001.constant.NSZC053001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Approval List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Hitachi         A.Kohinata      Create          N/A
 * 2017/09/21   Hitachi         U.Kim           Update          QC#18526
 *</pre>
 */
public class NSAL1100BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1100CMsg cMsg = (NSAL1100CMsg) arg0;
        NSAL1100SMsg sMsg = (NSAL1100SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1100Scrn00_CancelCI".equals(screenAplID)) {
                doProcess_NSAL1100Scrn00_CancelCI(cMsg, sMsg);
            } else if ("NSAL1100Scrn00_Continue".equals(screenAplID)) {
                doProcess_NSAL1100Scrn00_Continue(cMsg, sMsg);
            } else if ("NSAL1100Scrn00_OverrideApprove".equals(screenAplID)) {
                doProcess_NSAL1100Scrn00_OverrideApprove(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1100Scrn00_CancelCI(NSAL1100CMsg cMsg, NSAL1100SMsg sMsg) {

        SVC_CR_REBILTMsgArray tMsgList = NSAL1100CommonLogic.getSvcCrRebil(cMsg);
        if (tMsgList.getValidCount() == 0) {
            cMsg.custIncdtId_H.setErrorInfo(1, NSAM0416E, new String[] {"Customer Incident#", "Service Credit Rebill" });
            cMsg.setMessageInfo(NSAM0416E, new String[] {"Customer Incident#", "Service Credit Rebill" });
            return;
        }

        String svcCrRebilStsCd = tMsgList.no(0).svcCrRebilStsCd.getValue();
        if (!SVC_CR_REBIL_STS.ENTERED.endsWith(svcCrRebilStsCd) && !SVC_CR_REBIL_STS.PENDING_APPROVAL.endsWith(svcCrRebilStsCd)) {
            cMsg.custIncdtId_H.setErrorInfo(1, NSAM0065E);
            cMsg.setMessageInfo(NSAM0065E);
        }

        NSAL1100CommonLogic.callCreditRebillUpdateAPI(cMsg, NSZC053001Constant.MODE_CI_CANCEL);
    }

    private void doProcess_NSAL1100Scrn00_Continue(NSAL1100CMsg cMsg, NSAL1100SMsg sMsg) {
        
        // START 2017/09/21 U.Kim [QC#18526, ADD]
        if(!ZYPCommonFunc.hasValue(cMsg.svcCrRebilRsnTxt)){
            cMsg.svcCrRebilRsnTxt.setErrorInfo(1, ZZZM9025E,  new String[] {"Comment" });
            return;
        }
        // END 2017/09/21 U.Kim [QC#18526, ADD]
        NSAL1100CommonLogic.callCreditRebillUpdateAPI(cMsg, NSZC053001Constant.MODE_SUBMIT_FOR_APPROVAL);
    }

    private void doProcess_NSAL1100Scrn00_OverrideApprove(NSAL1100CMsg cMsg, NSAL1100SMsg sMsg) {

        NSAL1100CommonLogic.callCreditRebillUpdateAPI(cMsg, NSZC053001Constant.MODE_OVERRIDE_APPROVER);
    }
}
