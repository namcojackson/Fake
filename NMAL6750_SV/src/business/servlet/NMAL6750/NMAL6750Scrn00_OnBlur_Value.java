/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6750;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6750.common.NMAL6750CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL6750Scrn00_OnChange_Format
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/15   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
public class NMAL6750Scrn00_OnBlur_Value extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;

        int idx = getButtonSelectNumber();
        String fmtVal = NMAL6750CommonLogic.setContactPointValueByFormat(scrnMsg, idx);
        scrnMsg.B.no(idx).dsCtacPntValTxt_B1.setValue(fmtVal);

        scrnMsg.setFocusItem(scrnMsg.B.no(idx).dsCtacPsnExtnNum_B1);

    }
}
