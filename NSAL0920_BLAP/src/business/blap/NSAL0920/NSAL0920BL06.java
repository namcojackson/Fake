package business.blap.NSAL0920;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Contract Billing Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0920BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0920CMsg cMsg = (NSAL0920CMsg) arg0;
        NSAL0920SMsg sMsg = (NSAL0920SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);
        String screenAplId = cMsg.getScreenAplID();
        try {
            if ("NSAL0920Scrn00_CMN_ColSave".equals(screenAplId)) {
                ZYPGUITableColumn.setColData((NSAL0920CMsg) cMsg, (NSAL0920SMsg) sMsg);
            } else if ("NSAL0920Scrn00_CMN_ColClear".equals(screenAplId)) {
                    ZYPGUITableColumn.clearColData((NSAL0920CMsg) cMsg, (NSAL0920SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
}
