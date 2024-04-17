/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2260;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2260.common.NWAL2260CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/27   Fujitsu         K.Ishizuka      Create          QC#28899
 *</pre>
 */
public class NWAL2260Scrn00_OpenWin_Address extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2260BMsg scrnMsg = (NWAL2260BMsg) bMsg;
        Object[] params = NWAL2260CommonLogic.prepareAddressLookupPopupParameter(scrnMsg, this.getGlobalCompanyCode());
        setArgForSubScreen(params);

    }
}
