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
 * 2015/11/06   Hitachi         J.Kim           Create          N/A
 * 2016/02/26   Hitachi         K.Kishimoto     Update          QC#2011
 * 2017/01/16   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL0580BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSAL0580CMsg cMsg = (NSAL0580CMsg) arg0;
        NSAL0580SMsg sMsg = (NSAL0580SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0580_INIT".equals(screenAplID)) {
                doProcess_NSAL0580_INIT(cMsg, sMsg);
            //Mod Start 01/16/2017 <QC#16331>
            } else if ("NSAL0580Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0580Scrn00_CMN_Reset(cMsg, sMsg);
            //Mod End 01/16/2017 <QC#16331>
            } else if ("NSAL0580Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0580Scrn00_CMN_Submit(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Initialize event handler
     * @param cMsg NSAL0580CMsg
     * @param sMsg NSAL0580SMsg
     */
    private void doProcess_NSAL0580_INIT(NSAL0580CMsg cMsg, NSAL0580SMsg sMsg) {

        initialize(cMsg, sMsg);
    }

    // Mod Start 01/16/2017 <QC#16331>
    /**
     * Reset event handler
     * @param cMsg NSAL0580CMsg
     * @param sMsg NSAL0580SMsg
     */
    private void doProcess_NSAL0580Scrn00_CMN_Reset(NSAL0580CMsg cMsg, NSAL0580SMsg sMsg) {

        //Mod Start 02/26/2016 <QC#2011>
        cMsg.dsContrCtrlStsCd_H.clear();
        //Mod End   02/26/2016 <QC#2011>
        cMsg.svcMemoRsnCd_H.clear();
        cMsg.svcCmntTxt.clear();
        initialize(cMsg, sMsg);
    }
    // Mod End 01/16/2017 <QC#16331>

    /**
     * Submit event handler
     * @param cMsg NSAL0580CMsg
     * @param sMsg NSAL0580SMsg
     */
    private void doProcess_NSAL0580Scrn00_CMN_Submit(NSAL0580CMsg cMsg, NSAL0580SMsg sMsg) {

        //Mod Start 02/26/2016 <QC#2011>
        cMsg.dsContrCtrlStsCd_H.clear();
        //Mod End   02/26/2016 <QC#2011>
        cMsg.svcMemoRsnCd_H.clear();
        cMsg.svcCmntTxt.clear();
        initialize(cMsg, sMsg);
    }

    private void initialize(NSAL0580CMsg cMsg, NSAL0580SMsg sMsg) {

        // get global company code
        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        if (!hasValue(cMsg.dsContrPk)) {
            cMsg.setMessageInfo(NSAM0353E, new String[] {"No input parameter" });
            return;
        }

        // Contract Info Pull Down
        NSAL0580CommonLogic.searchDSContractInfo(cMsg, sMsg);

        // Service Memo Reason Pull Down
        NSAL0580CommonLogic.setServiceMemoReasonInfo(cMsg, sMsg);

        EZDMsg.copy(cMsg, null, sMsg, null);
    }

}
