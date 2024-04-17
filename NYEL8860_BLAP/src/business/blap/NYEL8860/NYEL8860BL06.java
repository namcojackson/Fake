/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8860;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NYEL8860BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/26   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NYEL8860BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            /*
            String screenAplID = cMsg.getScreenAplID();
            NYEL8860CMsg bizMsg = (NYEL8860CMsg) cMsg;
            NYEL8860SMsg glblMsg = (NYEL8860SMsg) sMsg;
            */

            //Nothing
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }


}
