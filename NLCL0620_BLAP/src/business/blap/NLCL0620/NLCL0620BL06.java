/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0620;

import static business.blap.NLCL0620.constant.NLCL0620Constant.EVENT_NM_NLCL0620_CMN_SUBMIT;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL0620.common.NLCL0620CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NLCL0620 Tech PI Entry
 * Function Name : update business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/26/2016   CITS         Makoto Okigami     Create          N/A
 *</pre>
 */
public class NLCL0620BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL0620_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_Submit((NLCL0620CMsg) cMsg, (NLCL0620SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * Submit
     * @param cMsg NLCL0620CMsg
     * @param sMsg NLCL0620SMsg
     */
    private void doProcess_Submit(NLCL0620CMsg cMsg, NLCL0620SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0620CommonLogic.submit(cMsg, sMsg, glblCmpyCd);

    }

}
