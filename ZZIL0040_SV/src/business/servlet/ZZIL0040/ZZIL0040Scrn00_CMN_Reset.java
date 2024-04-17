/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZIL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/18   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0040Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0040BMsg scrnMsg = (ZZIL0040BMsg) bMsg;

        if (scrnMsg.A.getValidCount() > 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).intfcRqstStsTxt_AS.clear();
            }
        }
    }
}
