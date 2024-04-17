/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1020;

import static business.blap.NSAL1020.constant.NSAL1020Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL1020.common.NSAL1020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Supply Order Serial# Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL1020BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        super.preDoProcess(arg0, null);
        NSAL1020CMsg cMsg = (NSAL1020CMsg) arg0;

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL1020_INIT".equals(screenAplID)) {
                doProcess_NSAL1020_INIT(cMsg);
            } else if ("NSAL1020Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL1020Scrn00_Search(cMsg);
            }
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSAL1020_INIT(NSAL1020CMsg cMsg) {

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());

        if (!mandatoryCheck(cMsg)) {
            cMsg.setMessageInfo(NSAM0219E, new String[] {NSAM0219E_PARAM });
            return;
        }

        NSAL1020CommonLogic.getSearchData(cMsg);
    }

    private void doProcess_NSAL1020Scrn00_Search(NSAL1020CMsg cMsg) {

        NSAL1020CommonLogic.getSearchData(cMsg);
    }

    private boolean mandatoryCheck(NSAL1020CMsg cMsg) {

        if (!hasValue(cMsg.ownrAcctNum_PH)) {
            return false;
        }
        if (cMsg.P.getValidCount() == 0) {
            return false;
        }
        if (!hasValue(cMsg.shipToCustCd_PH)) {
            return false;
        }
        if (!hasValue(cMsg.lineBizTpCd_PH)) {
            return false;
        }
        return true;
    }
}
