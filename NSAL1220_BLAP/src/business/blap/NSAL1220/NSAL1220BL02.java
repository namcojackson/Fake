/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1220;

import static business.blap.NSAL1220.constant.NSAL1220Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL1220.common.NSAL1220CommonLogic;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Contract Branch Revenue Distribution
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL1220BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1220CMsg cMsg = (NSAL1220CMsg) arg0;
        NSAL1220SMsg sMsg = (NSAL1220SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL1220_INIT".equals(screenAplID)) {
                doProcess_NSAL1220_INIT(cMsg);
            } else if ("NSAL1220Scrn00_AddRow".equals(screenAplID)) {
                doProcess_NSAL1220Scrn00_AddRow(cMsg);
            } else if ("NSAL1220Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL1220Scrn00_CMN_Reset(cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NSAL1220_INIT(NSAL1220CMsg cMsg) {
        init(cMsg);
    }

    private void doProcess_NSAL1220Scrn00_AddRow(NSAL1220CMsg cMsg) {
        int count = cMsg.A.getValidCount();
        if (cMsg.A.length() < count + 1) {
            cMsg.setMessageInfo(NSAM0112E);
            return;
        }
        cMsg.A.setValidCount(count + 1);
    }

    private void doProcess_NSAL1220Scrn00_CMN_Reset(NSAL1220CMsg cMsg) {
        init(cMsg);
    }

    private void init(NSAL1220CMsg cMsg) {
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        if (!hasValue(cMsg.dsContrPk)) {
            cMsg.setMessageInfo(NZZM0012E, new String[] {DS_CONTR_PK });
            return;
        }
        if (!REF_MODE.equals(cMsg.xxModeCd.getValue()) && !EDIT_MODE.equals(cMsg.xxModeCd.getValue())) {
            cMsg.setMessageInfo(NZZM0012E, new String[] {UPDATE_MODE });
            return;
        }
        NSAL1220CommonLogic.findContrInfo(cMsg);
        NSAL1220CommonLogic.calc(cMsg);
    }
}
