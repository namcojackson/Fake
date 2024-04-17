/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1210;

import static business.blap.NPAL1210.constant.NPAL1210Constant.EVENT_NM_NPAL1210_INIT;
import static business.blap.NPAL1210.constant.NPAL1210Constant.NPAM0002E;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1210.common.NPAL1210CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1210 PO/Inventory Approval History
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/20/2016   CITS            R Shimamoto     Create          N/A
 *</pre>
 */
public class NPAL1210BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1210_INIT.equals(screenAplID)) {
                doProcess_NPAL1210_INIT((NPAL1210CMsg) cMsg, (NPAL1210SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg NPAL1210CMsg
     * @param sMsg NPAL1210SMsg
     */
    private void doProcess_NPAL1210_INIT(NPAL1210CMsg cMsg, NPAL1210SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(cMsg.apvlHistSrcTpCd)
                && !ZYPCommonFunc.hasValue(cMsg.trxRefNum)) {

            cMsg.setMessageInfo(NPAM0002E);
        } else {

            //search
            NPAL1210CommonLogic.search(cMsg, sMsg, glblCmpyCd);
        }

    }
}
