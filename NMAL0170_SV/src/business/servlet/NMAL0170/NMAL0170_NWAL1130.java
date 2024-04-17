/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0170;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0170_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         T.Arima          Create          N/A
 *</pre>
 */
public class NMAL0170_NWAL1130 extends S21CommonHandler {

    @Override
    /**
     * Check Input Event
     * - do nothing.
     */
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    /**
     * Set Request Date Event
     * - do nothing.
     */
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
       // do nothing
        return null;
    }

    @Override
    /**
     * Do Process Event
     * - set Item Number from select item number in popup screen. 
     */
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.T.no(0).xxComnScrColValTxt_T)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.supdToMdseCd, scrnMsg.T.no(0).xxComnScrColValTxt_T);
        }
        // focus : item number
        scrnMsg.setFocusItem(scrnMsg.supdToMdseCd);
    }
}
