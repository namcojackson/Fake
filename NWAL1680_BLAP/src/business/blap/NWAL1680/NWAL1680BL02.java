/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1680;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1680.common.NWAL1680CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWAL1680BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1680CMsg bizMsg = (NWAL1680CMsg) cMsg;

            if ("NWAL1680_INIT".equals(screenAplID)) {
                doProcess_NWAL1680_INIT(bizMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL1680_INIT(NWAL1680CMsg bizMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String cpoOrdNum = bizMsg.cpoOrdNum.getValue();
        NWAL1680CommonLogic.setSummaryCountsByOrder(glblCmpyCd, cpoOrdNum, bizMsg);
        NWAL1680CommonLogic.setSummaryCountsByModel(glblCmpyCd, cpoOrdNum, bizMsg);
    }

}
