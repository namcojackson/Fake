/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0580;

import static business.blap.NSAL0580.constant.NSAL0580Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL0580.common.NSAL0580CommonLogic;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 *
 * Status Change
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Hitachi         J.Kim           Create          N/A
 * 2015/12/15   Hitachi         K.Yamada        Update          CSA QC#1254
 * 2016/02/26   Hitachi         K.Kishimoto     Update          QC#2011
 * 2016/12/14   Hitachi         Y.Takeno        Update          QC#16285
 * 2018/08/26   Hitachi         K.Kim           Update          QC#22987
 * 2019/07/18   Hitachi         A.Kohinata      Update          QC#51706
 *</pre>
 */
public class NSAL0580BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0580CMsg cMsg = (NSAL0580CMsg) arg0;
        NSAL0580SMsg sMsg = (NSAL0580SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);
        String screenAplId = cMsg.getScreenAplID();
        try {
            if (screenAplId.equals("NSAL0580Scrn00_CMN_Submit")) {
                doProcess_NSAL0580Scrn00_CMN_Submit(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Submit event handler
     * @param cMsg NSAL0580CMsg
     * @param sMsg NSAL0580SMsg
     */
    private void doProcess_NSAL0580Scrn00_CMN_Submit(NSAL0580CMsg cMsg, NSAL0580SMsg sMsg) {

        //Mod Start 02/26/2016 <QC#2011>
        if (!hasValue(cMsg.dsContrCtrlStsCd_H)) {
            cMsg.dsContrCtrlStsCd_H.setErrorInfo(1, NSAM0212E, new String[] {"New Status" });
            return;
        }
        //Mod End   02/26/2016 <QC#2011>

        if (!hasValue(cMsg.svcMemoRsnCd_H)) {
            cMsg.svcMemoRsnCd_H.setErrorInfo(1, NSAM0212E, new String[] {"Reason Code" });
            return;
        }

        if (!hasValue(cMsg.svcCmntTxt)) {
            cMsg.svcCmntTxt.setErrorInfo(1, NSAM0212E, new String[] {"Notes" });
            return;
        }

        EZDMsg.copy(cMsg, null, sMsg, null);

        // START 2018/08/26 [QC#22987, ADD]
        if (NSAL0580CommonLogic.existUnapprovedBllgHld(cMsg, sMsg)) {
            cMsg.setMessageInfo(NSAM0737E);
            return;
        }
        // END 2018/08/26 [QC#22987, ADD]

        // Update DS Contract
        //Mod Start 02/26/2016 <QC#2011>
        NSAL0580CommonLogic.execStsChng(cMsg, sMsg);
        //Mod End   02/26/2016 <QC#2011>

        // Insert Or Update Service Memo
        NSAL0580CommonLogic.submitSvcMemo(cMsg, sMsg);

        //Add Start 12/14/2016 <QC#16285>
        // Insert Service Memo (for Report)
        NSAL0580CommonLogic.submitSvcMemoForReport(cMsg, sMsg);
        //Add End   12/14/2016 <QC#16285>

        // add start 2019/07/18 QC#51706
        NSAL0580CommonLogic.submitSvcMemoForManBllgHld(cMsg, sMsg);
        // add end 2019/07/18 QC#51706

        // add start 2015/12/15 CSA Defect#1254
        if (!"E".equals(cMsg.getMessageKind())) {
            cMsg.setMessageInfo(NZZM0002I);
        }
        // add end 2015/12/15 CSA Defect#1254
    }
}
