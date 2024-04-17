/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0600;

import static business.blap.NLCL0600.constant.NLCL0600Constant.EVENT_NM_NLCL0600_CMN_SUBMIT;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL0600.common.NLCL0600CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NLCL0600 PI Entry
 * Function Name : update business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/24/2016   CITS         Makoto Okigami     Create          N/A
 *</pre>
 */
public class NLCL0600BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL0600_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_CMN_Submit((NLCL0600CMsg) cMsg, (NLCL0600SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * Submit
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     */
    private void doProcess_CMN_Submit(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0600CommonLogic.submit(cMsg, sMsg, glblCmpyCd);

    }

}
