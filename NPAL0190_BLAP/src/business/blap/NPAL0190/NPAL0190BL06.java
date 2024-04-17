/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL0190;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL0190.constant.NPAL0190Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CITS         K.Ogino            Create          N/A
 *</pre>
 */
public class NPAL0190BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        super.preDoProcess(ezdCMsg, ezdSMsg);
        NPAL0190CMsg cMsg = (NPAL0190CMsg) ezdCMsg;
        NPAL0190SMsg sMsg = (NPAL0190SMsg) ezdSMsg;
        try {
            String screenAplID = ezdCMsg.getScreenAplID();

            if (NPAL0190Constant.CMN_COL_CLEAR.equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(cMsg, sMsg);
            } else if (NPAL0190Constant.NMN_COL_SAVE.equals(screenAplID)) {
                ZYPGUITableColumn.setColData(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
}
